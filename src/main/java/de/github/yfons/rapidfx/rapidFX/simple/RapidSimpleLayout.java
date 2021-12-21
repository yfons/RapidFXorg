package de.github.yfons.rapidfx.rapidFX.simple;

import de.github.yfons.rapidfx.rapidFX.interfaces.RapidLayout;
import javafx.scene.Node;

public abstract class RapidSimpleLayout<LAYOUT_NODE_TYPE extends Node>extends RapidSimple implements RapidLayout<LAYOUT_NODE_TYPE> {
	protected LAYOUT_NODE_TYPE layout;

	@Override
	public final LAYOUT_NODE_TYPE getLayout()
	{
		return this.layout;
	}
}
