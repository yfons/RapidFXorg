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
		final Field bindToField = this.findFieldWithSameName(bindTo);
		ReadOnlyProperty<?> viewProperty = castToReadOnlyProperty();
		RConnector connector = new RConnector(viewProperty, bindToField, bindTo);
		connector.connectProperties();
	}

	public void setDefaultValue()
	{
		try
		{
		if (!isClassTypeOfProperty())

				castToPropertyError("Property");

		else if (isNull())
		{
			field.set(objectToGetValues, getDefaultValue());
		}
		} catch (IllegalAccessException | Error e)
		{
			throw new Error("The Field:: "+ field + " :: was not accessible, probably because the Module-info doesn't open the Package\n"+e.getMessage());
		}
	}

	public boolean isAnnotationPresent(final Class<? extends Annotation> annotation)
	{
		return this.field.isAnnotationPresent(annotation);
	}

	private Object getObject() throws IllegalArgumentException, IllegalAccessException
	{
		return field.get(this.objectToGetValues);
	}

	private Field findFieldWithSameName(final RapidFXComponent bindTo)
	{
		final String fieldName = field.getName().intern();
		try
		{
			Field foundField = bindTo.getClass().getDeclaredField(fieldName);
			foundField.setAccessible(true);
			return foundField;
		} catch (NoSuchFieldException e)
		{
			throw new Error("The Field with the Name:: " + fieldName + " ::was not found in the Class:: "
					+ bindTo.getClass()
					+ "\nThis an be caused when the Module-Info doesn't \"open PACKAGENAME\" where the Class is Partof");
		}
	}

	private boolean isNull() throws IllegalAccessException
	{
		return this.field.get(this.objectToGetValues) == null;
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
			System.out.println();
			return t.getDeclaredConstructor().newInstance();
		} catch (InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException
				| NoSuchMethodException | SecurityException | ClassNotFoundException e)
		{
			instantiationError(e);
			return null;
		}
	}

	private ReadOnlyProperty<?> castToReadOnlyProperty() throws IllegalAccessException
	{
		try
		{
			ReadOnlyProperty<?> viewProperty = (ReadOnlyProperty<?>) getObject();
			return viewProperty;
		} catch (ClassCastException e)
		{
			castToPropertyError("ReadOnlyProperty which is The SuperInterface of Property");
			return null;
		}
	}

	private void castToPropertyError(String whatCast) throws Error, IllegalAccessException
	{
		throw new Error("Couldn't cast the Field:: " + field + " :: in the Class:: " + field.getDeclaringClass()
				+ " :: to a " + whatCast + "\nThe Field is of Type :: " + field.getType() + " :: with the Value:: "
				+ getObject());
	}

	private void instantiationError(Exception e) throws Error
	{
		throw new Error(
				"If your Object is of Type ObjectProperty then it will get set to New SimpleXXXProperty, only properties with Simple at the start work with this generation\nSet the values manually to avoid that, as only Fields with <B>value<B> null are affected\n as the binding will still Work with any Properties or something else went wrong while setting the default value for::  "
						+ field + " :: look at RapidFX.core.FieldHandler#getDefaultValue for the Possible Exceptions"
						+ e.getMessage());
	}

}
