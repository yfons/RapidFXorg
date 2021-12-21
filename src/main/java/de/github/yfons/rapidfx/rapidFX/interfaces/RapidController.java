package de.github.yfons.rapidfx.rapidFX.interfaces;

import javafx.scene.layout.Pane;

public interface RapidController extends RapidFXComponent
{
	RapidView<?> getView();

	RapidModel getModel();

	Pane getRootPane();
}
