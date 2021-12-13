package rapidFX.simple;

import javafx.scene.layout.Pane;
import rapidFX.interfaces.RapidView;

public abstract class RapidSimpleView<rootPane extends Pane> implements RapidView<rootPane>
{
	protected rootPane root;

	@Override
	public rootPane getRootPane()
	{
		return root;
	}
}
