package de.github.yfons.rapidfx.rapidFX.simple;

import de.github.yfons.rapidfx.rapidFX.annotation.RapidFXmodel;
import de.github.yfons.rapidfx.rapidFX.core.RapidFX;
import de.github.yfons.rapidfx.rapidFX.interfaces.RapidController;
import de.github.yfons.rapidfx.rapidFX.interfaces.RapidModel;
import de.github.yfons.rapidfx.rapidFX.interfaces.RapidView;

public abstract class RapidSimpleController<RAPID_VIEW_CLASS extends RapidView<?>, RAPID_MODEL_CLASS extends RapidModel>
		extends RapidSimpleWeakController<RAPID_VIEW_CLASS> implements RapidController
{
	protected RAPID_MODEL_CLASS model;

	@Override
	protected RapidSimpleController<RAPID_VIEW_CLASS, RAPID_MODEL_CLASS> rapidFXMe()
	{
		super.rapidFXMe();
		return RapidFX.create(this)
				.generate(model)
				.connect(view, model, RapidFXmodel.class)
				.get();
	}

	/*
	 * @Override
	 */
	@Override
	public final RAPID_MODEL_CLASS getModel()
	{
		return model;
	}

	@Override
	public String toString()
	{
		return "Controller =>" + "\n\t=> CLASS => " + this.getClass() + "\n\t=> VIEW_OBJECT =>\n" + view.toString()
				+ "\n\t=> MODEL_OBJECT =>\n" + model.toString() + "\n";
	}
}
