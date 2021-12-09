package examples.HelloWorld;

import javafx.beans.property.ObjectProperty;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import rapidFX.abstracts.RapidSimpleView;
import rapidFX.annotation.Rcontroller;
import rapidFX.annotation.Rmodel;

public class LoginView extends RapidSimpleView<VBox>
{
	private final Label title = new Label();
	private final Button ok = new Button("OK");
	private final Button close = new Button("Close");
	
	// Get's not Initialized because the constructor of Login is called before the RapidFX.rapidGenerate method
	// in the Login Object the view gets Created before the initializations happen and in the constructor the okActionProperty will get set to the value in the Contructor
	// Get's bound to the okActionProperty in the Controller
	@Rcontroller
	private  ObjectProperty<EventHandler<ActionEvent>> okActionProperty ;
	// Won't get Initialized because its not null 
	// Get's bound to the closeActionProperty in the Controller
	@Rcontroller
	private final ObjectProperty<EventHandler<ActionEvent>> closeActionProperty = close.onActionProperty();
	// Get's initialized as new SimpleObjectProperty<>()
	// Get's bound to the titleText in the Model
	@Rmodel
	private ObjectProperty<String> titleText;


	LoginView()
	{
		// root is predefined in the RapidSimpleView as Object of the Generic type which is mentioned at the class declaration
		root = new VBox();
		// gets set before annotations get Read so it will bind ok.onActionProperty() to the Controller
		okActionProperty = ok.onActionProperty() ;
		root.getChildren().addAll(title,new HBox(ok,close));
	}


	@Override
	public void setUpView()
	{
		// problem with the automatic bindings is they only work on ObjectProperties currently
		title.textProperty().bind(titleText);

		// will not find LoginView.css in the current directory, as it searches for ViewClassName.css which is currently LoginView.css
		//loadMyCss();
	}
}
