package rapidFX.simple;

import javafx.scene.Node;
import rapidFX.interfaces.RapidLayout;

public abstract class RapidSimpleLayout<LAYOUT_NODE_TYPE extends Node> implements RapidLayout<LAYOUT_NODE_TYPE>
{
	protected LAYOUT_NODE_TYPE layout;
	@Override
	public final LAYOUT_NODE_TYPE getLayout()
	{
		return this.layout;
	}
}
