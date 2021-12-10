package rapidFX.simple;

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

	@Override
	public final modelClass getModel()
	{
		return model;
	}

	@Override
	public abstract void setUpController();

}
