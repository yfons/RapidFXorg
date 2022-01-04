package de.github.yfons.rapidfx.rapidFX.core.helper;

import java.lang.reflect.Field;

import de.github.yfons.rapidfx.rapidFX.core.RapidFXRuntimeException;
import javafx.beans.property.Property;
import javafx.beans.property.ReadOnlyProperty;
import javafx.beans.value.ChangeListener;
import javafx.event.EventHandler;

@SuppressWarnings(
{ "unchecked", "rawtypes" })
public class RConnector
{
	private ReadOnlyProperty<?> readOnlybindFromProperty;
	private final Field bindToField;
	private Object bindToFieldObject;

	private Property bindFromProperty;
	private Field fieldFromProperty;

	public RConnector(Field bindToField, Object bindTo)
	{

		this.bindToField = bindToField;
		try
		{
			this.bindToFieldObject = bindToField.get(bindTo);
		} catch (IllegalArgumentException | IllegalAccessException e)
		{
			throw new RapidFXRuntimeException(
					"THIS EXCEPTION SHOULDN'T HAPPEN => post to  https://github.com/yfons/RapidFXorg  if it happens An error Occured during executing => this.bindToFieldObject = bindToField.get(bindTo); <= in the constructor of:: "
							+ this.getClass() + "\n\t=> Values => this.bindToFieldObject == " + this.bindToFieldObject
							+ "\n" + "bindToField == " + bindToField + "\nException occured::" + e.getMessage());

		}
	}

	public void setPropertyFrom(Field fieldFromProperty, ReadOnlyProperty<?> viewProperty2)
	{
		this.fieldFromProperty = fieldFromProperty;
		this.readOnlybindFromProperty = viewProperty2;
		try
		{
			this.bindFromProperty = (Property<?>) readOnlybindFromProperty;
		} catch (Exception e)
		{

		}
	}

	public void connectProperties()
	{
		if (bindToFieldObject instanceof ChangeListener<?> listener)
		{
			addListener(listener);
		} else
		{
			connectOnPropertyInterface();
		}
	}

	private void connectOnPropertyInterface()
	{
		if (bindToFieldObject instanceof EventHandler<?> bindToHandler)
		{
			setHandlerOnProperty(bindToHandler);
		} else if (bindToFieldObject instanceof Property<?> bindToProperty)
		{
			if (bindToField.getType().isAssignableFrom(readOnlybindFromProperty.getClass()))
			{
				bindProperty(bindToProperty);
			} else
			{
				incompatiblePropertiesErrorMessage(bindToField);
			}
		}
	}

	private void incompatiblePropertiesErrorMessage(final Field bindToField)
	{
		throw new RapidFXRuntimeException(
				"\nThe Field is not  A EventHandler or A ChangeListener or an Assignable Property" + "\n\t=> NAME => "
						+ bindToField.getName() + "\n\t=> CLASS => " + bindToField.getDeclaringClass()
						+ "\n\t=> TYPE => " + bindToField.getType() + "\n\t=> EXPECTED_TYPE => "
						+ fieldFromProperty.getType() + "\n\t=> BASED_ON_FIELD => " + fieldFromProperty.getName()
						+ "\n\t=> BASED_ON_CLASS => " + fieldFromProperty.getDeclaringClass());
	}

	private void addListener(ChangeListener listener)
	{
		readOnlybindFromProperty.addListener(listener);
	}

	private void setHandlerOnProperty(EventHandler<?> bindToHandler)
	{
		bindFromProperty.setValue(bindToHandler);
	}

	private <TYPE> void bindProperty(Property<TYPE> bindToProperty)
	{
		bindFromProperty.bind(bindToProperty);
	}
}
