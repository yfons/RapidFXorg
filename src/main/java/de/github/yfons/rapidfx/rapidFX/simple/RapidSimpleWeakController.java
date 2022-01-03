package de.github.yfons.rapidfx.rapidFX.simple;

import de.github.yfons.rapidfx.rapidFX.core.RapidFX;
import de.github.yfons.rapidfx.rapidFX.interfaces.RapidController;
import de.github.yfons.rapidfx.rapidFX.interfaces.RapidModel;
import de.github.yfons.rapidfx.rapidFX.interfaces.RapidView;
import javafx.scene.layout.Pane;

public abstract class RapidSimpleWeakController <RAPID_VIEW_CLASS extends RapidView<?>> extends RapidSimple implements RapidController
{
	protected RAPID_VIEW_CLASS view;

	protected void rapidFXMe() {
		RapidFX.rapidGenerate(this);
	}

	@Override
	public RAPID_VIEW_CLASS getView()
	{
		return view;
	}
	/**
	 * @Override Should be called from other Controllers when The View and it's
	 *           components shouldn't be visible
	 */
	@Override
	public final Pane getRootPane()
	{
		return view.getRootPane();
	}
	@Override
	public RapidModel getModel() {
		return null;
	}
	@Override
	public String toString()
	{
		return "Controller =>"
				+ "\n\t=> CLASS => " + this.getClass()
		+ "\n\t=> VIEW_OBJECT =>\n" + view.toString() + "\n";
	}
}
