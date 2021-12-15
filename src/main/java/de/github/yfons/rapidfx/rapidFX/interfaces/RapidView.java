package de.github.yfons.rapidfx.rapidFX.interfaces;

import javafx.scene.layout.Pane;

public interface RapidView<T extends Pane> extends RapidFXComponent
{
	T getRootPane();

}
