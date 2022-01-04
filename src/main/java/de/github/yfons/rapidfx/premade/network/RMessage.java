package de.github.yfons.rapidfx.premade.network;

import javafx.beans.property.Property;

public class RMessage<ENUM_KEY extends Enum<?>> extends RFormular<ENUM_KEY, Property<?>>
{

	@Override
	public Property<?> getContent(ENUM_KEY key)
	{
		// TODO Auto-generated method stub
		return contentMap.get(key);
	}

}
