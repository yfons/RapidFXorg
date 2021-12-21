package de.github.yfons.rapidfx.rapidFX.interfaces;

import de.github.yfons.rapidfx.rapidFX.core.RapidFX;

public interface RapidFXComponent
{
	static void RapidFXSetUP(RapidFXComponent component)
	{
		try
		{
			RapidFX.setUp(component);
		} catch (IllegalArgumentException | SecurityException e)
		{
			e.printStackTrace();
		}
	}
}
