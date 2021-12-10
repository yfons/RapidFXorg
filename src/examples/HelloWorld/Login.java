package examples.HelloWorld;

import javafx.application.Platform;
import javafx.beans.property.ObjectProperty;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import rapidFX.abstracts.RapidSimpleController;
import rapidFX.annotation.RautoGenerate;
import rapidFX.annotation.Rcontroller;
import rapidFX.annotation.Rmodel;

public class Login extends RapidSimpleController<LoginView, LoginModel> {
	// Get's Initialized as new SimpleObjectProperty<>()
	@RautoGenerate
	private ObjectProperty<EventHandler<ActionEvent>> okActionProperty;
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
			System.out.println("Hello From The Controller");
			
		});
		closeActionProperty.set(event -> Platform.exit());
		model.setUpModel();
		view.setUpView();
	}

}
