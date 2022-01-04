package de.github.yfons.rapidfx.premade.network;

import java.util.HashMap;

public abstract class RFormular<KEY_ENUM extends Enum<?>, CONTENT_TYPE>
{
	protected HashMap<KEY_ENUM, CONTENT_TYPE> contentMap = new HashMap<>();

	public void addContent(KEY_ENUM key, CONTENT_TYPE property)
	{
		contentMap.put(key, property);
	}

	public abstract Object getContent(KEY_ENUM key);

	public final CONTENT_TYPE getContentProperty(KEY_ENUM key)
	{
		return contentMap.get(key);
	}
}
