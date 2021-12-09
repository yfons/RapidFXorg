package rapidFX.abstracts;

import javafx.scene.layout.Pane;
import rapidFX.interfaces.RapidView;

public abstract class RapidSimpleView<rootPane extends Pane> implements RapidView
{
	protected rootPane root;
	@Override
	public rootPane getRootPane()
	{
		return root;
	}
	@Override
	public abstract void setUpView();

}
