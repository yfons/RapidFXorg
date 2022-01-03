package de.github.yfons.rapidfx.rapidFX.simple;

import de.github.yfons.rapidfx.rapidFX.interfaces.RapidModel;

public abstract class RapidSimpleModel implements RapidModel
{

	@Override
	public String toString() {
		return "\t\tModel =>\n\t\t=> CLASS => " +this.getClass();
	}
}
