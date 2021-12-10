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
	public static RapidController rapidGenerate(final RapidController controller)
			throws IllegalArgumentException, IllegalAccessException, NoSuchFieldException, SecurityException
	{
		final var model = controller.getModel();
		final var view = controller.getView();

		setUp(model, controller, view);

		connect(view, controller, Rcontroller.class);
		connect(view, model, Rmodel.class);

		return controller;
	}

	public static void setUp(RapidFXComponent... toSetUpObjects) throws IllegalArgumentException, IllegalAccessException
	{
		for (var toSetUp : toSetUpObjects) {

			Field[] fields = toSetUp.getClass().getDeclaredFields();

			for (var field : fields) {

				field.setAccessible(true);

				if (isRapidModelOrRapidControllerPresent(field)) {
					if (isSetUpClassAView(toSetUp)) {
						if (isFieldNull(toSetUp, field)) {
							setDefaultFieldValue(toSetUp, field);
						}
					} else {
						throw new IllegalArgumentException(
								"@Rmodel and @RController Annotations are only allowed in classes which implement the: "
										+ RapidView.class + "\r\n" + "but the Field:: " + field.getName()
										+ "\r\n violated that rule in the CLASS:: " + field.getDeclaringClass()
										+ "\r\n this 'can be' fixed by changing the Annotation to @RautoGenerate");
					}
				} else if (isRautoGeneratePresent(field) && isFieldNull(toSetUp, field)) {
					setDefaultFieldValue(toSetUp, field);
				}
			}
		}
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static void connect(final RapidView view, final RapidFXComponent bindTo,
			final Class<? extends Annotation> annotation) throws IllegalArgumentException, IllegalAccessException
	{
		final var bindToFields = bindTo.getClass().getDeclaredFields();
		final var viewFields = view.getClass().getDeclaredFields();

		for (Field viewField : viewFields) {
			viewField.setAccessible(true);
			if (isAnnotationPresent(annotation, viewField)) {
				if (viewField.get(view) instanceof ObjectProperty viewProperty) {
					boolean isFieldExisting = false;
					for (Field bindToField : bindToFields) {
						bindToField.setAccessible(true);
						if (isBindFieldNameEqualToViewFieldName(viewField, bindToField)) {
							ObjectProperty<?> bindToProperty = (ObjectProperty<?>) bindToField.get(bindTo);
							viewProperty.bind(bindToProperty);
							isFieldExisting = true;
							break;
						}
					}
					if (!isFieldExisting) {
						throw new IllegalStateException(
								"The Field:: " + viewField.getName() + " was not found in:: " + bindTo.toString());
					}
				} else {
					throw new IllegalArgumentException(
							"The Field is in an Invalid State or  NULL or not from Type ObjectProperty:: "
									+ viewField.getName() + " :: Content is:: " + viewField.get(view));
				}
			}
		}
	}

	private static boolean isBindFieldNameEqualToViewFieldName(Field viewField, Field bindToField)
	{
		return bindToField.getName().equals(viewField.getName().intern());
	}

	private static boolean isAnnotationPresent(final Class<? extends Annotation> annotation, Field fieldFrom)
	{
		return fieldFrom.isAnnotationPresent(annotation);
	}
	private static boolean isSetUpClassAView(RapidFXComponent toSetUp)
	{
		return RapidView.class.isAssignableFrom(toSetUp.getClass());
	}

	private static boolean isRautoGeneratePresent(Field field)
	{
		return field.isAnnotationPresent(RautoGenerate.class);
	}

	private static boolean isFieldNull(RapidFXComponent toSetUp, Field field) throws IllegalAccessException
	{
		return field.get(toSetUp) == null;
	}

	private static boolean isRapidModelOrRapidControllerPresent(Field field)
	{
		return isAnnotationPresent(Rmodel.class, field) || isAnnotationPresent(Rcontroller.class, field);
	}

	private static void setDefaultFieldValue(RapidFXComponent toSetUp, Field field)
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
}
