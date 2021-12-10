package rapidFX.prebuilds.rSwitch;

import javafx.beans.property.ObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import rapidFX.annotation.RautoGenerate;
import rapidFX.interfaces.RapidModel;

public class RSwitcherModel implements RapidModel
{
	@RautoGenerate
	private ObjectProperty<Node> contentProperty;
	@RautoGenerate
	private ObjectProperty<ObservableList<Node>> navBarChildrenList;

	public RSwitcherModel()
	{

	}
	
	@Override
	public void setUpModel()
	{
		this.navBarChildrenList.set(FXCollections.observableArrayList());
	}

	ObjectProperty<Node> getContentProperty()
	{
		return this.contentProperty;
	}

	void appendFactories(RSwitchFactory<?,?>... factories)
	{
		for (var k : factories)
		{
			k.setNodeProperty(contentProperty);
			this.navBarChildrenList.get().add(k.setUpShownNode().getShownNode());
		}
	}

}
