package rapidFX.interfaces;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import rapidFX.annotation.RautoGenerate;
import rapidFX.annotation.Rcontroller;
import rapidFX.annotation.Rmodel;

public final class RapidFX
{
	public static void setUp(RapidFXComponent... toSetUpObjects) throws IllegalArgumentException, IllegalAccessException
	{
		for (var toSetUp : toSetUpObjects)
		{

			Field[] fields = toSetUp.getClass().getDeclaredFields();

			for (var field : fields)
			{
				field.setAccessible(true);
				if (containsRapidAnnotations(field) && field.get(toSetUp) == null )
				{
					try
					{
						field.set(toSetUp, getDefaultValueForType(field.getType()));
					} catch (Exception e)
					{
						e.getMessage();
					}
				}
			}
		}
	}

	private static boolean containsRapidAnnotations(Field field)
	{
		return field.isAnnotationPresent(RautoGenerate.class) || field.isAnnotationPresent(Rmodel.class) || field.isAnnotationPresent(Rcontroller.class);
	}

	private static Object getDefaultValueForType(Class<?> type)
	{
		return new SimpleObjectProperty<>();
	}

	@SuppressWarnings({ "rawtypes","unchecked" })
	public static void connect(final RapidView bindFrom, final RapidFXComponent bindTo, final Class<? extends Annotation> annotation)
			throws IllegalArgumentException, IllegalAccessException
	{
		final var bindToFields = bindTo.getClass().getDeclaredFields();
		final var bindFromFields = bindFrom.getClass().getDeclaredFields();
		for (Field fieldFrom : bindFromFields)
		{
			fieldFrom.setAccessible(true);
			if (fieldFrom.isAnnotationPresent(annotation))
			{
				if (fieldFrom.get(bindFrom) instanceof ObjectProperty bindFromProperty)
				{
					boolean checkIfCorrespondingAttributeIsAvailable = false;
					for (Field bindToField : bindToFields) {
						bindToField.setAccessible(true);
						if (bindToField.getName().equals(fieldFrom.getName().intern()))
						{
							ObjectProperty<?> bindToProperty = (ObjectProperty<?>) bindToField.get(bindTo);
							bindFromProperty.bind( bindToProperty);
							checkIfCorrespondingAttributeIsAvailable = true;
							System.out.println("RapidFX:: from: "+ bindFrom+" bind to: "+bindTo +" on Field: "+ bindToField);
							break;
						}

					}
					if (!checkIfCorrespondingAttributeIsAvailable)
					{
						throw new IllegalStateException(
								"The Field: " + fieldFrom.getName().toString() + " was not found in: " + bindTo.toString());
					}
				} else
				{
					throw new IllegalArgumentException(
							"The Field is not from Type ObjectProperty: " + fieldFrom.getName());
				}
			}
		}
	}
	public static RapidController rapidGenerate(RapidController controller)
			throws IllegalArgumentException, IllegalAccessException, NoSuchFieldException, SecurityException
	{
		final var model = controller.getModel();
		final var view = controller.getView();

		setUp(model,controller,view);

		connect(view, controller, Rcontroller.class);
		connect(view, model, Rmodel.class);

		return controller;
	}
}
