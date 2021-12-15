package examples.HelloWorld;

import javafx.application.Platform;
import javafx.beans.property.ObjectProperty;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import rapidFX.annotation.RautoGenerate;
import rapidFX.simple.RapidSimpleController;

public class Login extends RapidSimpleController<LoginView, LoginModel> {
	// Get's Initialized as new SimpleObjectProperty<>()
	@RautoGenerate
	private ObjectProperty<EventHandler<ActionEvent>> okActionProperty;

	public Login() {
		// model is predefined as modelClass generic type
		model = new LoginModel();
		// view is predefined as viewClass generic type
		view = new LoginView();

		// if you want to Execute all Annotations ( Autogenerating + setting bindings ) you have to call that in the Controller or with the static RapidFX.rapidGenerate(Controller controller)
		RapidFXgenerateMe();
		setUpController();
	}
	EventHandler<ActionEvent> closeEHAction = event ->{
		Platform.exit();
	};
	// gets Called after RapidFX.rapidGenerate is done so use Constructor to set values before generation and this method after the generation
	// can call corresponding model.setUpModel and view.setUpView if they are defined
	public void setUpController() {
		// the ok ActionProperty would have gave a NullPointerException if it's set before RapidFXGenerateMe(), only after that the Autogenerate is done
		okActionProperty.set(event -> {
			model.login();
			System.out.println("Hello From The Controller");
		});
		model.setUpModel();
		view.setUpView();
	}


}
