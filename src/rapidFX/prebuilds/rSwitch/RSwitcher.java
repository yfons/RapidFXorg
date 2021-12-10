package rapidFX.prebuilds.rSwitch;

import javafx.beans.property.ObjectProperty;
import javafx.scene.Node;
import rapidFX.Position;
import rapidFX.abstracts.RapidSimpleController;
import rapidFX.interfaces.RapidController;

public class RSwitcher extends RapidSimpleController<RSwitcherView, RSwitcherModel> implements RapidController
{
	Node shownNode;

	public RSwitcher()
	{
		view = new RSwitcherView();
		model = new RSwitcherModel();
	}

	@Override
	public void setUpController()
	{
		model.setUpModel();
		view.setUpView();
	}

	public void setPosition(Position pos)
	{
		view.setPosition(pos);
	}

	public void appendFactories(RSwitchFactory<?, ?>... factories)
	{
		model.appendFactories(factories);
	}

	public ObjectProperty<Node> getContentProperty()
	{
		return model.getContentProperty();
	}
}
