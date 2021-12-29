package de.github.yfons.rapidfx.rapidFX.interfaces;

import de.github.yfons.rapidfx.rapidFX.core.RapidFX;

public interface RapidFXComponent
{
	default void RapidFXSetUPMe()
	{
			RapidFX.setUp(this);
	}
}
