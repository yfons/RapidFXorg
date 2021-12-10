package rapidFX.core;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import rapidFX.annotation.RautoGenerate;
import rapidFX.annotation.Rcontroller;
import rapidFX.annotation.Rmodel;
import rapidFX.interfaces.RapidController;
import rapidFX.interfaces.RapidFXComponent;
import rapidFX.interfaces.RapidView;

public final class RapidFX
{
	public static void setUp(RapidFXComponent... toSetUpObjects) throws IllegalArgumentException, IllegalAccessException
	{
		for (var toSetUp : toSetUpObjects) {

			Field[] fields = toSetUp.getClass().getDeclaredFields();

			for (var field : fields) {
				field.setAccessible(true);
				if (field.isAnnotationPresent(RautoGenerate.class) && field.get(toSetUp) == null) {
					setTheDefaultFieldValue(toSetUp, field);
				}
				boolean isRcOrRmPresent = (field.isAnnotationPresent(Rmodel.class)
						|| field.isAnnotationPresent(Rcontroller.class)) ;
				if (isRcOrRmPresent && !RapidView.class.isAssignableFrom(toSetUp.getClass())) {
					throw new IllegalArgumentException(
							"@Rmodel and @RController Annotations are only allowed in classes which implement the: "
									+ RapidView.class + "\r\n" + "but the Field:: " + field.getName()
									+ "\r\n violated that rule in the CLASS:: " + field.getDeclaringClass()
									+ "\r\n this 'can be' fixed by changing the Annotation to @RautoGenerate");
				} 
				else if ( isRcOrRmPresent && field.get(toSetUp) == null && RapidView.class.isAssignableFrom(toSetUp.getClass())) {
					setTheDefaultFieldValue(toSetUp, field);
				}
			}
		}
	}

	private static void setTheDefaultFieldValue(RapidFXComponent toSetUp, Field field)
	{
		try {
			field.set(toSetUp, getDefaultValueForType(field.getType()));
		} catch (Exception e) {
			e.getMessage();
		}
	}


	private static Object getDefaultValueForType(Class<?> type)
	{
		return new SimpleObjectProperty<>();
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static void connect(final RapidView bindFrom, final RapidFXComponent bindTo,
			final Class<? extends Annotation> annotation) throws IllegalArgumentException, IllegalAccessException
	{
		final var bindToFields = bindTo.getClass().getDeclaredFields();
		final var bindFromFields = bindFrom.getClass().getDeclaredFields();
		for (Field fieldFrom : bindFromFields) {
			fieldFrom.setAccessible(true);
			if (fieldFrom.isAnnotationPresent(annotation)) {
				if (fieldFrom.get(bindFrom) instanceof ObjectProperty bindFromProperty) {
					boolean checkIfCorrespondingAttributeIsAvailable = false;
					for (Field bindToField : bindToFields) {
						bindToField.setAccessible(true);
						if (bindToField.getName().equals(fieldFrom.getName().intern())) {
							ObjectProperty<?> bindToProperty = (ObjectProperty<?>) bindToField.get(bindTo);
							bindFromProperty.bind(bindToProperty);
							checkIfCorrespondingAttributeIsAvailable = true;
							break;
						}
					}
					if (!checkIfCorrespondingAttributeIsAvailable) {
						throw new IllegalStateException("The Field:: " + fieldFrom.getName()
								+ " was not found in:: " + bindTo.toString());
					}
				} else {
					throw new IllegalArgumentException(
							"The Field is in an Invalid State or  NULL or not from Type ObjectProperty:: " + fieldFrom.getName()+ " :: Content is:: "+fieldFrom.get(bindFrom));
				}
			}
		}
	}

	public static RapidController rapidGenerate(RapidController controller)
			throws IllegalArgumentException, IllegalAccessException, NoSuchFieldException, SecurityException
	{
		final var model = controller.getModel();
		final var view = controller.getView();

		setUp(model, controller, view);

		connect(view, controller, Rcontroller.class);
		connect(view, model, Rmodel.class);

		return controller;
	}
}
