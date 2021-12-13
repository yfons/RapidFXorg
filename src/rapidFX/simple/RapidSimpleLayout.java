package rapidFX.simple;

import javafx.scene.Node;
import rapidFX.interfaces.RapidLayout;

public abstract class RapidSimpleLayout<layoutNode extends Node> implements RapidLayout<layoutNode>
{
	protected layoutNode layout;
	@Override
	public final layoutNode getLayout()
	{
		return this.layout;
	}
}
