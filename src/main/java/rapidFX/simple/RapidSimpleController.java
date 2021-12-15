package rapidFX.simple;

import javafx.scene.layout.Pane;
import rapidFX.interfaces.RapidController;
import rapidFX.interfaces.RapidModel;
import rapidFX.interfaces.RapidView;

public abstract class RapidSimpleController<RAPID_VIEW_CLASS extends RapidView<?>,RAPID_MODEL_CLASS extends RapidModel> implements RapidController
{
	protected RAPID_VIEW_CLASS view;
	protected RAPID_MODEL_CLASS model;

	public final RAPID_VIEW_CLASS getView()
	{
		return view;
	}
	
	/**
	 * @Override
	 * Should be called from other Controllers when The View and it's components shouldn't be visible
	 */
	public final Pane getRootPane() {
		return view.getRootPane();
	}
	@Override
	public final RAPID_MODEL_CLASS getModel()
	{
		return model;
	}


}
