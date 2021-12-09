package rapidFX.abstracts;

import javafx.scene.layout.Pane;
import rapidFX.interfaces.RapidView;

public abstract class RapidSimpleView<T extends Pane> implements RapidView
{
	protected T root;
	@Override
	public T getRootPane()
	{
		return root;
	}
	@Override
	public abstract void setUpView();
	
}
