package rapidFX.simple;

import javafx.scene.Node;
import rapidFX.interfaces.RapidLayout;

public abstract class RapidSimpleLayout<layoutNode extends Node> implements RapidLayout
{
	protected layoutNode layout;
	@Override
	public final layoutNode getLayout()
	{
		return this.layout;
	}

	@Override
	public abstract void setUpLayout();

}
