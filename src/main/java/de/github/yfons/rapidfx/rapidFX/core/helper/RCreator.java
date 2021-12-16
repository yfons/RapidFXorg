package de.github.yfons.rapidfx.rapidFX.core.helper;

import java.util.function.Supplier;

import de.github.yfons.rapidfx.rapidFX.interfaces.RapidFXComponent;

public record RCreator<TYPE extends RapidFXComponent> (Supplier<TYPE> constructor)
{
	
	public TYPE create()
	{
		return constructor.get();
	}
}
