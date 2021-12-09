package examples.HelloWorld;

import javafx.application.Platform;
import javafx.beans.property.ObjectProperty;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import rapidFX.RautoGenerate;
import rapidFX.Rmodel;
import rapidFX.abstracts.RapidSimpleController;

public class Login extends RapidSimpleController<LoginView, LoginModel> {
	// Get's Initialized as new SimpleObjectProperty<>()
	@RautoGenerate
	private ObjectProperty<EventHandler<ActionEvent>> okActionProperty;
	// Get's Initialized as new SimpleObjectProperty<>()
	// Get's bound to the greetingsProperty in the Model
	@Rmodel
	private ObjectProperty<String> greetingsProperty;
	// Get's Initialized as new SimpleObjectProperty<>()
	@RautoGenerate
	private ObjectProperty<EventHandler<ActionEvent>> closeActionProperty;

	public Login() {
		// model is predefined as modelClass generic type
		model = new LoginModel();
		// view is predefined as viewClass generic type 
		view = new LoginView();
	}

	// normally gets Called after RapidFX.rapidGenerate is done so use Constructor to set values before generation and this method after the generation
	// can call corresponding model.setUpModel and view.setUpView
	@Override
	public void setUpController() {
		okActionProperty.set(event -> {
			model.login();
			System.out.println("Hello From The Controller and the Model"+ this.greetingsProperty.get());
			
		});
		closeActionProperty.set(event -> Platform.exit());
		model.setUpModel();
		view.setUpView();
	}

}
