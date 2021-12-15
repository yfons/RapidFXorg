package rapidFX.simple;

import javafx.scene.layout.Pane;
import rapidFX.interfaces.RapidController;
import rapidFX.interfaces.RapidModel;
import rapidFX.interfaces.RapidView;

public abstract class RapidSimpleController<viewClass extends RapidView<?>,modelClass extends RapidModel> implements RapidController
{
	protected viewClass view;
	protected modelClass model;

	public final viewClass getView()
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
	public final modelClass getModel()
	{
		return model;
	}

}
