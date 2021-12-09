package rapidFX.rSwitch;

import javafx.beans.property.ObjectProperty;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.control.Control;
import rapidFX.abstracts.RapidSimpleFactory;
import rapidFX.interfaces.RapidController;

public abstract class RSwitchFactory<ControllerClass extends RapidController,ShownNode extends Control> extends RapidSimpleFactory<ControllerClass>
{
	protected ShownNode shownNode;
	protected boolean isSelfRefreshing = false;
	protected ObjectProperty<Node> centerProperty;
	protected final EventHandler<Event> onMouseSwapToView = event -> swapToView();
	protected final EventHandler<ActionEvent> onActionSwapToView = event -> swapToView();

	public RSwitchFactory(ObjectProperty<Node> centerProperty)
	{
		this.centerProperty = centerProperty;
	}

	public RSwitchFactory()
	{
		this.setNewController();
	}
	public RSwitchFactory(boolean b) {
		this();
		isSelfRefreshing = b;
	}


	public final void setNodeProperty(ObjectProperty<Node> var1)
	{
		this.centerProperty = var1;
	}

	public final void setSelfRefreshing(boolean var1)
	{
		this.isSelfRefreshing = var1;
	}

	final void handleEvent(Event var1)
	{
		this.swapToView();
	}

	protected final void swapToView()
	{
		if (this.isSelfRefreshing)
			this.setNewController();
			this.centerProperty.set(this.controllerProperty.get().getView().getRootPane());
	}

	public ShownNode getShownNode()
	{
		return shownNode;
	}

	public abstract RSwitchFactory<ControllerClass, ShownNode> setUpShownNode();
}
