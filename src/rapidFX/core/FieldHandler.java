package rapidFX.core;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import rapidFX.annotation.RautoGenerate;
import rapidFX.annotation.Rcontroller;
import rapidFX.annotation.Rmodel;
import rapidFX.interfaces.RapidFXComponent;
import rapidFX.interfaces.RapidView;

record FieldHandler(Field field, RapidFXComponent objectToGetValues) {
	public FieldHandler(Field field, RapidFXComponent objectToGetValues)
	{
		this.field = field;
		this.objectToGetValues = objectToGetValues;
		field.setAccessible(true);
	}

	@SuppressWarnings("unchecked")
	public ObjectProperty<Object> getObject() throws IllegalArgumentException, IllegalAccessException
	{
		return (ObjectProperty<Object>) field.get(this.objectToGetValues);
	}

	public boolean isRautoGeneratePresent()
	{
		return this.field.isAnnotationPresent(RautoGenerate.class);
	}

	public boolean isRapidAnnotationPresent()
	{
		if (isAnnotationPresent(RautoGenerate.class)) return true;
		boolean rcOrmPresent = isAnnotationPresent(Rmodel.class) || isAnnotationPresent(Rcontroller.class);
		
		if (!rcOrmPresent) return true;
		else if (isFieldInARapidView()) {
			return true;
		} else {
			throw new IllegalArgumentException(annotationIsNotInAView());
		}

	}

	public boolean isAnnotationPresent(final Class<? extends Annotation> annotation)
	{
		return this.field.isAnnotationPresent(annotation);
	}

	public Field findFieldWithSameName(final RapidFXComponent bindTo)
	{
		final String fieldName = field.getName().intern();
		try {
			Field foundField = bindTo.getClass().getDeclaredField(fieldName);
			foundField.setAccessible(true);
			return foundField;
		} catch (NoSuchFieldException e) {
			System.err.println(
					"The Field with the Name:: " + fieldName + " ::was not found in the Class:: " + bindTo.getClass());
			System.exit(1);
			return null;
		}
	}

	public boolean isNull() throws IllegalAccessException
	{
		return this.field.get(this.objectToGetValues) == null;
	}

	public void setDefaultValue()
	{
		try {
			if (isNull()) {
				field.set(objectToGetValues, getDefaultValue());
			}
		} catch (Exception e) {
			e.getMessage();
		}

	}

	public SimpleObjectProperty<Object> getDefaultValue()
	{
		return new SimpleObjectProperty<>();
	}

	public boolean isFieldInARapidView()
	{
		return RapidView.class.isAssignableFrom(this.objectToGetValues.getClass());
	}

	private String annotationIsNotInAView()
	{
		return "@Rmodel and @RController Annotations are only allowed in classes which implement the: "
				+ RapidView.class + "\r\n" + "but the Field:: " + this.field.getName()
				+ " ::violated that rule in the CLASS:: " + this.field.getDeclaringClass()
				+ "\r\n this 'can be' fixed by changing the Annotation to @RautoGenerate";
	}
}
