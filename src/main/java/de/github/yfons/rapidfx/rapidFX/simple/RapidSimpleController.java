package de.github.yfons.rapidfx.rapidFX.simple;

import de.github.yfons.rapidfx.rapidFX.core.RapidFX;
import de.github.yfons.rapidfx.rapidFX.interfaces.RapidController;
import de.github.yfons.rapidfx.rapidFX.interfaces.RapidModel;
import de.github.yfons.rapidfx.rapidFX.interfaces.RapidView;
import javafx.scene.layout.Pane;

public abstract class RapidSimpleController<RAPID_VIEW_CLASS extends RapidView<?>, RAPID_MODEL_CLASS extends RapidModel>
		extends RapidSimple implements RapidController
{
	protected RAPID_VIEW_CLASS view;
	protected RAPID_MODEL_CLASS model;

	public final RAPID_VIEW_CLASS getView()
	{
		return view;
	}

	/**
	 * @Override Should be called from other Controllers when The View and it's
	 *           components shouldn't be visible
	 */
	public final Pane getRootPane()
	{
		return view.getRootPane();
	}

	/*
	 * @Override
	 */
	public final RAPID_MODEL_CLASS getModel()
	{
		return model;
	}

	/**
	 * RapidFX Generates The Controller with his View and Model together View
	 * bindings will get Automatically set based on RapidFX Annotations
	 */
	protected final void rapidFXgenerateMe()
	{
		RapidFX.rapidGenerate(this);
	}

	public String toString()
	{
		return "Controller =>" 
				+ "\n\t=> CLASS => " + this.getClass() 
		+ "\n\t=> VIEW_OBJECT =>\n" + view.toString() 
		+ "\n\t=> MODEL_OBJECT =>\n" + model.toString() + "\n";
	}
}
