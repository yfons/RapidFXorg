package de.github.yfons.rapidfx.rapidFX.core.helper.fluent;

import java.util.Objects;

import de.github.yfons.rapidfx.rapidFX.core.helper.RConnSetup;

public abstract class RFLuentAbstract<OBJECT_TYPE>
{
	protected OBJECT_TYPE controller;
	protected RConnSetup setuper = new RConnSetup();
	public RFLuentAbstract(OBJECT_TYPE object)
	{
		Objects.requireNonNull(object);
		this.controller = object;
	}
	public OBJECT_TYPE get() {
		return controller;
	}
}
