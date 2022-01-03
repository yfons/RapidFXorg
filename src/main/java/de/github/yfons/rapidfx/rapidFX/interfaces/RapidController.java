package de.github.yfons.rapidfx.rapidFX.interfaces;

import javafx.scene.layout.Pane;

public interface RapidController
{
	RapidView<?> getView();

	RapidModel getModel();

	Pane getRootPane();
}
