package rapidFX.core;

import java.lang.reflect.Field;

import javafx.beans.property.Property;
import javafx.beans.property.ReadOnlyProperty;
import javafx.beans.value.ChangeListener;
import javafx.event.EventHandler;
import rapidFX.interfaces.RapidFXComponent;

class RConnector
{
	private final ReadOnlyProperty<?> viewProperty;
	private final Field bindToField;
	private final Object bindToFieldObject;

	public RConnector(ReadOnlyProperty<?> viewProperty2, Field bindToField, RapidFXComponent bindTo)
			throws IllegalArgumentException, IllegalAccessException
	{
		this.viewProperty = viewProperty2;
		this.bindToField = bindToField;
		this.bindToFieldObject = bindToField.get(bindTo);
	}

	public void connectProperties()
	{
		try
		{
			if (bindToFieldObject instanceof ChangeListener<?> listener)
			{
				addListener(listener);
			} else
			{
				Property<?> property = (Property<?>) viewProperty;
				connectOnPropertyInterface(property);
			}
		} catch (Exception e)
		{
			e.printStackTrace();
		}
	}

	@SuppressWarnings(
	{ "rawtypes", "unchecked" })
	private void connectOnPropertyInterface(Property property) throws IllegalAccessException, Error
	{
		if (bindToFieldObject instanceof EventHandler<?> bindToHandler)
		{
			setHandlerOnProperty(property, bindToHandler);
		} else if (bindToFieldObject instanceof Property<?> bindToProperty)
		{
			bindProperty(property, bindToProperty);
		} else
		{
			throwIncompatiblePropertiesError(bindToField);
		}
	}

	private void throwIncompatiblePropertiesError(final Field bindToField)
			throws Error, IllegalAccessException
	{
		throw new Error(
				"One of these Fields is not a Property or A EventHandler or it's wrong mixed or null or its not Supported:: "
						+ bindToField.getName()+ " ::in the class:: " + bindToField.getDeclaringClass());
	}

	@SuppressWarnings(
	{ "unchecked", "rawtypes" })
	private void addListener(ChangeListener listener)
	{
		viewProperty.addListener(listener);
	}

	private void setHandlerOnProperty(Property<EventHandler<?>> property, EventHandler<?> bindToHandler)
	{
		property.setValue(bindToHandler);
	}

	@SuppressWarnings(
	{ "unchecked", "rawtypes" })
	private void bindProperty(Property bindToProperty, Property property)
	{
		bindToProperty.bind(property);
	}
}
