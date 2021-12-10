package rapidFX.prebuilds.rSwitch;

import javafx.beans.property.ObjectProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import rapidFX.Position;
import rapidFX.abstracts.RapidSimpleView;
import rapidFX.annotation.RautoGenerate;
import rapidFX.annotation.Rmodel;
import rapidFX.interfaces.RapidView;

public class RSwitcherView extends RapidSimpleView<BorderPane>
{
	private final HBox hNavbar = new HBox();
	private final VBox vNavbar = new VBox();
	private Pane navbar;
	@RautoGenerate
	private ObjectProperty<Position> positionProperty;
	@Rmodel
	private ObjectProperty<ObservableList<Node>> navBarChildrenList;
	@Rmodel
	private ObjectProperty<Node> contentProperty;

	RSwitcherView()
	{
		root = new BorderPane();
		contentProperty = root.centerProperty();
	}

	public void setUpView()
	{
		this.positionProperty.addListener(positionListener);
		navBarChildrenList.get().addListener(listChangeListener);
		root.setTop(navbar = hNavbar);
	}

	public void setPosition(Position pos)
	{
		this.positionProperty.set(pos);
	}

	public Pane getNavBar()
	{
		return navbar;
	}

	private final ListChangeListener<Node> listChangeListener = new ListChangeListener<>() {
		@Override
		public void onChanged(Change<? extends Node> c)
		{
			c.next();
			navbar.getChildren().addAll(c.getAddedSubList());
		}
	};
	private final ChangeListener<Position> positionListener = new ChangeListener<>() {

		@Override
		public void changed(ObservableValue<? extends Position> observable, Position oldValue, Position newValue)
		{
			handleOldValue(oldValue);
			handleNewValue(newValue);
		}

		private void handleNewValue(Position newValue)
		{
			if (newValue != null) {
				switch (newValue) {
					case BOTTOM:
						switchFromTo(vNavbar, hNavbar);
						root.setBottom(navbar);
						break;
					case LEFT:
						switchFromTo(hNavbar, vNavbar);
						root.setLeft(navbar);
						break;
					case RIGHT:
						switchFromTo(hNavbar, vNavbar);
						root.setRight(navbar);
						break;
					case TOP:
						switchFromTo(vNavbar, hNavbar);
						root.setTop(navbar);
						break;
				}
			}
		}

		private void switchFromTo(Pane from, Pane to)
		{
			if (from.getChildren().size() != 0)
				to.getChildren().addAll(from.getChildren());

			navbar = to;
			navBarChildrenList.set(navbar.getChildren());
		}

		private void handleOldValue(Position oldValue)
		{
			switch (oldValue) {
				case BOTTOM:
					root.setBottom(null);
					break;
				case LEFT:
					root.setLeft(null);
					break;
				case RIGHT:
					root.setRight(null);
					break;
				case TOP:
					root.setTop(null);
					break;
			}
		}

	};

}
