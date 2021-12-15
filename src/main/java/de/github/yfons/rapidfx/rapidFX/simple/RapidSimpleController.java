package de.github.yfons.rapidfx.rapidFX.simple;

import de.github.yfons.rapidfx.rapidFX.interfaces.RapidController;
import de.github.yfons.rapidfx.rapidFX.interfaces.RapidModel;
import de.github.yfons.rapidfx.rapidFX.interfaces.RapidView;
import javafx.scene.layout.Pane;

public abstract class RapidSimpleController<RAPID_VIEW_CLASS extends RapidView<?>, RAPID_MODEL_CLASS extends RapidModel>
		implements RapidController
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

	@Override
	public final RAPID_MODEL_CLASS getModel()
	{
		return model;
	}

}
