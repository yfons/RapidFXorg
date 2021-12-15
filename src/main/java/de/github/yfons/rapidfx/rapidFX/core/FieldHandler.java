package de.github.yfons.rapidfx.rapidFX.core;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;

import de.github.yfons.rapidfx.rapidFX.interfaces.RapidFXComponent;
import javafx.beans.property.Property;
import javafx.beans.property.ReadOnlyProperty;

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
	 * @throws RapidFXException
	 */
	public void bindProperties(final RapidFXComponent bindTo) throws RapidFXException
	{
		final Field bindToField = this.findFieldWithSameName(bindTo);
		ReadOnlyProperty<?> viewProperty = castToReadOnlyProperty();
		RConnector connector = new RConnector(viewProperty, bindToField, bindTo);
		connector.connectProperties();
	}

	public void setDefaultValue() throws RapidFXException
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
			throw new RapidFXException("The Field:: " + field
					+ " :: was not accessible, probably because the Module-info doesn't open the Package\n"
					+ e.getMessage());
		}
	}

	public boolean isAnnotationPresent(final Class<? extends Annotation> annotation)
	{
		return this.field.isAnnotationPresent(annotation);
	}

	private Object getObject() throws RapidFXException
	{
		try
		{
			return field.get(this.objectToGetValues);
		} catch (IllegalArgumentException | IllegalAccessException e)
		{
			throw new RapidFXException(e.getMessage());
		}
	}

	private Field findFieldWithSameName(final RapidFXComponent bindTo) throws RapidFXException
	{
		final String fieldName = field.getName().intern();
		try
		{
			Field foundField = bindTo.getClass().getDeclaredField(fieldName);
			foundField.setAccessible(true);
			return foundField;
		} catch (NoSuchFieldException e)
		{
			throw new RapidFXException("The Field with the Name:: " + fieldName + " ::was not found in the Class:: "
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

	public Object getDefaultValue() throws RapidFXException
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

	private ReadOnlyProperty<?> castToReadOnlyProperty() throws RapidFXException
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

	private void castToPropertyError(String whatCast) throws IllegalArgumentException, RapidFXException
	{
		throw new RapidFXException("Couldn't cast the Field:: " + field + " :: in the Class:: "
				+ field.getDeclaringClass() + " :: to a " + whatCast + "\nThe Field is of Type :: " + field.getType()
				+ " :: with the Value:: " + getObject());
	}

	private void instantiationError(Exception e) throws RapidFXException
	{
		throw new RapidFXException(
				"If your Object is of Type ObjectProperty then it will get set to New SimpleXXXProperty, only properties with Simple at the start work with this generation\nSet the values manually to avoid that, as only Fields with <B>value<B> null are affected\n as the binding will still Work with any Properties or something else went wrong while setting the default value for::  "
						+ field + " :: look at RapidFX.core.FieldHandler#getDefaultValue for the Possible Exceptions"
						+ e.getMessage());
	}

}
