package de.github.yfons.rapidfx.rapidFX.core.helper;

import java.lang.reflect.Field;

import de.github.yfons.rapidfx.rapidFX.core.RapidFXException;
import de.github.yfons.rapidfx.rapidFX.interfaces.RapidFXComponent;
import javafx.beans.property.Property;
import javafx.beans.property.ReadOnlyProperty;
import javafx.beans.value.ChangeListener;
import javafx.event.Event;
import javafx.event.EventHandler;

public class RConnector
{
	private final ReadOnlyProperty<?> viewProperty;
	private final Field bindToField;
	private final Object bindToFieldObject;

	public RConnector(ReadOnlyProperty<?> viewProperty2, Field bindToField, RapidFXComponent bindTo)
			throws RapidFXException

	{
		this.viewProperty = viewProperty2;
		this.bindToField = bindToField;
		try
		{
			this.bindToFieldObject = bindToField.get(bindTo);
		} catch (IllegalArgumentException | IllegalAccessException e)
		{
			throw new RapidFXException(e.getMessage());
		}
	}

	public void connectProperties()
	{
		if (bindToFieldObject instanceof ChangeListener<?> listener)
		{
			addListener(listener);
		} else
		{
			connectOnPropertyInterface((Property<?>)viewProperty);
		}
	}

	private void connectOnPropertyInterface(Property property)
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
	{
		throw new Error(
				"One of these Fields is not a Property or A EventHandler or it's wrong mixed or null or its not Supported::\n"
						+ bindToField.getName() + " ::in the class:: " + bindToField.getDeclaringClass() + "\n"
						+ viewProperty.getName() + " :: of ");
	}


	private  void addListener(ChangeListener listener)
	{
		viewProperty.addListener(listener);
	}

	private void setHandlerOnProperty(Property<EventHandler<?>> property, EventHandler<?> bindToHandler)
	{
		property.setValue(bindToHandler);
	}


	private <TYPE> void bindProperty(Property<TYPE> bindToProperty, Property<TYPE> property)
	{
		bindToProperty.bind(property);
	}
}
