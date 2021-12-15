package examples.HelloWorld;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.StringProperty;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import rapidFX.annotation.RapidFXcontroller;
import rapidFX.annotation.RapidFXmodel;
import rapidFX.simple.RapidSimpleView;

public class LoginView extends RapidSimpleView<VBox>
{
	private final Label title = new Label();
	private final Button ok = new Button("OK");
	private final Button close = new Button("Close");

	// Get's not Initialized because the constructor of Login is called before the RapidFX.rapidGenerate method
	// in the Login Object the view gets Created before the initializations happen and in the constructor the okActionProperty will get set to the value in the Contructor
	// Get's bound to the okActionProperty in the Controller
	// for the Naming the controller names should get created before so it's easier to swap the views
	@RapidFXcontroller
	private  ObjectProperty<EventHandler<ActionEvent>> okActionProperty ;
	// Get's not Initialized because its not null
	// it's also possible to add an Eventhandler/ChangeListener with this setup just name the EventHandler/ChangeListener in the Controller like you did in the view
	// changelistener work also for ReadonlyPropertys
	// Get's bound to the closeActionProperty in the Controller
	@RapidFXcontroller
	private final ObjectProperty<EventHandler<ActionEvent>> closeEHAction = close.onActionProperty();
	// Get's bound to the titleText in the Model as all Propertys can be bound
	@RapidFXmodel
	private final StringProperty titleTextProperty = title.textProperty();


	LoginView()
	{
		// root is predefined in the RapidSimpleView as Object of the Generic type which is mentioned at the class declaration
		// also the getter is already there, with the Generic type
		root = new VBox();
		// gets set before annotations get Read so it will bind ok.onActionProperty() to the Controller
		okActionProperty = ok.onActionProperty() ;
		root.getChildren().addAll(title,new HBox(ok,close));
	}

	public void setUpView()
	{
		// will not find the CSS as the File LoginView.css is not in the current package
		// cssStyleRoot();
	}
}
