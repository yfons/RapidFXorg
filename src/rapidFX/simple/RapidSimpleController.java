package rapidFX.simple;

import javafx.scene.layout.Pane;
import rapidFX.interfaces.RapidController;
import rapidFX.interfaces.RapidModel;
import rapidFX.interfaces.RapidView;

public abstract class RapidSimpleController<viewClass extends RapidView,modelClass extends RapidModel> implements RapidController
{
	protected viewClass view;
	protected modelClass model;
	@Override
	public final viewClass getView()
	{
		return view;
	}
	public final Pane getRootPane() {
		return view.getRootPane();
	}
	@Override
	public final modelClass getModel()
	{
		return model;
	}

}
