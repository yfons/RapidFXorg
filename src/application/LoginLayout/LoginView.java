package application.LoginLayout;

import javafx.beans.property.ObjectProperty;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import rapidFX.Rcontroller;
import rapidFX.Rmodel;
import rapidFX.abstracts.RapidSimpleView;

public class LoginView extends RapidSimpleView<VBox>
{
	private final Label title = new Label();
	private final Button ok = new Button("OK");
	private final Button close = new Button("Close");
	
	@Rcontroller
	private ObjectProperty<EventHandler<ActionEvent>> okAction = ok.onActionProperty() ;
	@Rcontroller
	private ObjectProperty<EventHandler<ActionEvent>> closeAction = close.onActionProperty();
	@Rmodel
	private ObjectProperty<String> titleText;
	
	
	LoginView()
	{
		root = new VBox();
		root.getChildren().addAll(title,new HBox(ok,close));
	}


	@Override
	public void setUpView()
	{
		title.textProperty().bind(titleText);
	}
}
