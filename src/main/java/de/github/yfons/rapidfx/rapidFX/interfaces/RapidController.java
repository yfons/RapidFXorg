package de.github.yfons.rapidfx.rapidFX.interfaces;

import java.lang.reflect.InvocationTargetException;

import de.github.yfons.rapidfx.rapidFX.core.RapidFX;
import javafx.scene.layout.Pane;

public interface RapidController extends RapidFXComponent
{
	RapidView<?> getView();

	RapidModel getModel();

	Pane getRootPane();

	default void rapidFXgenerateMe()
	{
		try
		{
			RapidFX.rapidGenerate(this);
		} catch (IllegalAccessException | IllegalArgumentException | SecurityException  e)
		{
			e.printStackTrace();
		}
	}
}
