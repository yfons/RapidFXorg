package rapidFX.core;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;

import javafx.beans.property.Property;
import javafx.beans.property.ReadOnlyProperty;
import rapidFX.interfaces.RapidFXComponent;

public class FieldHandler<T>
{
	private final Field field;
	private final RapidFXComponent objectToGetValues;
	private final Class<?> fieldClass;

	public FieldHandler(Field field, RapidFXComponent objectToGetValues)
	{
		this.field = field;
		this.fieldClass = field.getType();
		this.objectToGetValues = objectToGetValues;
		field.setAccessible(true);
	}

	public Object getObject() throws IllegalArgumentException, IllegalAccessException
	{
		return field.get(this.objectToGetValues);
	}

	public boolean isAnnotationPresent(final Class<? extends Annotation> annotation)
	{
		return this.field.isAnnotationPresent(annotation);
	}

	public Field findFieldWithSameName(final RapidFXComponent bindTo, String addOn)
	{
		final String fieldName = field.getName().intern() + addOn;
		try
		{
			Field foundField = bindTo.getClass().getDeclaredField(fieldName);
			foundField.setAccessible(true);
			return foundField;
		} catch (NoSuchFieldException e)
		{
			System.err.println(
					"The Field with the Name:: " + fieldName + " ::was not found in the Class:: " + bindTo.getClass());
			System.exit(-1);
			return null;
		}
	}

	public boolean isNull() throws IllegalAccessException
	{
		return this.field.get(this.objectToGetValues) == null;
	}

	public void setDefaultValue() throws IllegalAccessException, IllegalArgumentException, ClassNotFoundException,
			InstantiationException, InvocationTargetException, NoSuchMethodException, SecurityException
	{
		if (!isClassTypeOfProperty())
			throw new Error("The Field:: " + field
					+ " :: is not of the Type Property or is Assignable from the Class Property");
		else if (isNull())
		{
			field.set(objectToGetValues, getDefaultValue());
		}
	}

	private boolean isClassTypeOfProperty()
	{
		return Property.class.isAssignableFrom(fieldClass);
	}

	public Object getDefaultValue()
	{
		Class<?> t;
		try
		{
			t = Class.forName(field.getType().getPackageName() + ".Simple" + field.getType().getSimpleName());
			return t.getDeclaredConstructor().newInstance();
		} catch (InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException
				| NoSuchMethodException | SecurityException | ClassNotFoundException e)
		{
			throw new Error(
					"If your Object is of Type ObjectProperty then it will get set to New SimpleObjectProperty, only properties with Simple at the start work with this generation, set the values manually to avoid that as the binding will still Work with any Properties or something else went wrong while setting the default value for::  "
							+ field
							+ " :: look at RapidFX.core.FieldHandler#getDefaultValue for the Possible Exceptions"
							+ e.getMessage());

		}
	}

	/**
	 * Works with property bind Property and with Property add Eventhandler and
	 * Property add ChangeListener
	 *
	 * @param bindTo
	 * @throws IllegalArgumentException
	 * @throws IllegalAccessException
	 */
	public void bindProperties(final RapidFXComponent bindTo) throws IllegalArgumentException, IllegalAccessException
	{
		final Field bindToField = this.findFieldWithSameName(bindTo, "");
		ReadOnlyProperty<?> viewProperty = (ReadOnlyProperty<?>) getObject();
		RConnector connector = new RConnector(viewProperty, bindToField, bindTo);
		connector.connectProperties();
	}
}
