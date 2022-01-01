package de.github.yfons.rapidfx.rapidFX.core.helper;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;

import de.github.yfons.rapidfx.rapidFX.core.RapidFXRuntimeException;
import de.github.yfons.rapidfx.rapidFX.interfaces.RapidFXComponent;
import javafx.beans.property.Property;
import javafx.beans.property.ReadOnlyProperty;

public class FieldHandler<T>
{
	private final Field field;
	private final Object objectToGetValues;
	private final Class<?> fieldClass;

	public FieldHandler(Field field, Object objectToGetValues)
	{
		this.field = field;
		this.field.setAccessible(true);
		this.fieldClass = field.getType();
		this.objectToGetValues = objectToGetValues;
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
	public void bindProperties(final Object bindTo)
	{
		final var bindToField = this.findFieldWithSameName(bindTo);

		if (isNull())
		{
			isEmptyFieldErrorMessage(field);
			return;
		}
		if (isNull(bindToField, bindTo))
		{
			isEmptyFieldErrorMessage(bindToField);
			return;
		}
		launchConnector(bindTo, bindToField);
	}

	private void launchConnector(final Object bindTo, final Field bindToField)
	{
		final var connector = new RConnector(bindToField, bindTo);
		final var viewProperty = castToReadOnlyProperty();
		connector.setPropertyFrom(field, viewProperty);
		connector.connectProperties();
	}

	private void isEmptyFieldErrorMessage(final Field bindToField)
	{
		throw new RapidFXRuntimeException(
				"\nThe Value of the Field which should be a Property/EventHandler/ChangeListener is null, can't bind to null"
						+ "\n\t=> NAME => " + bindToField.getName() 
						+ "\n\t=> CLASS => " + bindToField.getDeclaringClass()
						+ "\n\t=> TYPE => " + bindToField.getType()+"\n");
	}

	public void setDefaultValue()
	{
		try
		{
			if (!isClassTypeOfProperty())
			{
				castToPropertyErrorMessage("Property");
			} else if (isNull())
			{
				field.set(objectToGetValues, getDefaultValue());
			}
		} catch (IllegalAccessException e)
		{
			illegalAccessArgumentErrorMessage(e);
		}
	}

	private void illegalAccessArgumentErrorMessage(Exception e)
	{
		throw new RapidFXRuntimeException(
				"\nThe Field was not accessible to set a RautoGenerate value , probably caused by the Module-info doesn't open/exports the Package"
						+ "\n\t=> Name =>" + field.getName() 
						+ "\n\t=> Class => " + field.getDeclaringClass()
						+ "\n\t=> Type => " + field.getType() +"\n"+ e.getMessage());
	}

	public boolean isAnnotationPresent(final Class<? extends Annotation> annotation)
	{
		return this.field.isAnnotationPresent(annotation);
	}

	private Object getObject(Field fields, Object comp)
	{
		field.setAccessible(true);
		try
		{
			return fields.get(comp);
		} catch (IllegalArgumentException | IllegalAccessException e)
		{
			illegalAccessArgumentErrorMessage(e);
			return null;
		}
	}

	private Field findFieldWithSameName(final Object bindTo)
	{
		final var fieldName = field.getName().intern();
		try
		{
			final var foundField = bindTo.getClass().getDeclaredField(fieldName);
			foundField.setAccessible(true);
			return foundField;
		} catch (NoSuchFieldException e)
		{
			throw new RapidFXRuntimeException(
					"\nThe Field was not found\nThis can be caused when the Module-Info doesn't \"open PACKAGENAME\" where the Class is Part of"
							+ "\n\t=> NAME => " + fieldName 
							+ "\n\t=> CLASS => " + bindTo.getClass()
							+ "\n\t=> EXPECTED TYPE => " + field.getType() + "\n");
		} catch (IllegalArgumentException e)
		{
			e.printStackTrace();
			return null;
		}
	}

	private boolean isNull(Field fields, Object comp)
	{
		return getObject(fields, comp) == null;
	}

	private boolean isNull()
	{
		return isNull(this.field, this.objectToGetValues);
	}

	private boolean isClassTypeOfProperty()
	{
		return Property.class.isAssignableFrom(this.fieldClass);
	}

	public final Object getDefaultValue()
	{
		try
		{
			final var t = Class.forName(field.getType().getPackageName() + ".Simple" + field.getType().getSimpleName());
			return t.getDeclaredConstructor().newInstance();
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException | IllegalArgumentException
				| InvocationTargetException | NoSuchMethodException | SecurityException e)
		{
			throw new RapidFXRuntimeException("\nDuring the try to set a default value an Exception Occured\nValues=>"+
					"\nIf your Object is of Type ObjectProperty then it will get set to New SimpleXXXProperty, only properties with Simple at the start work with this generation\nSet the values manually to avoid that, as only Fields with <B>value<B> null are affected\n as the binding will still Work with any Properties or something else went wrong while setting the default value for::  "
					+ "\n\t=> NAME => "+field.getName()
					+ "\n\t=> TYPE => " + field.getType() 
					+ "\n\t=> SEARCHED =>"+ field.getType().getPackageName() +" + .Simple + " +field.getType().getSimpleName()+"\n");
		}

	}

	private ReadOnlyProperty<?> castToReadOnlyProperty()
	{
		return (ReadOnlyProperty<?>) getObject(this.field, this.objectToGetValues);
	}

	private void castToPropertyErrorMessage(String whatCast)
	{
		throw new RapidFXRuntimeException("\nCouldn't cast the Field to a " + whatCast 
				+ "\n\t=> NAME => " + field.getName()
				+ "\n\t=> CLASS => " + field.getDeclaringClass() 
				+ "\n\t=> TYPE => " + field.getType());
	}
}
