package de.github.yfons.rapidfx.examples.viewManaging.helloWorldPackage;

import de.github.yfons.rapidfx.rapidFX.annotation.RapidFXautoGenerate;
import de.github.yfons.rapidfx.rapidFX.core.RapidFX;
import de.github.yfons.rapidfx.rapidFX.simple.RapidSimpleController;
import javafx.application.Platform;
import javafx.beans.property.ObjectProperty;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

public class Login extends RapidSimpleController<LoginView, LoginModel>
{
	// Get's Initialized as new SimpleObjectProperty<>()
	@RapidFXautoGenerate
	private ObjectProperty<EventHandler<ActionEvent>> okActionProperty;

	public Login()
	{
		// this way of setting the model and view is prefered as its easier to swap them
		// out afterwards during refactoring
		// model is predefined as modelClass generic type
		model = RapidFX.createNew(LoginModel::new);
		// view is predefined as viewClass generic type
		view = RapidFX.createNew(LoginView::new);
		rapidFXMe();
		// if you want to Execute all Annotations ( Autogenerating + setting bindings )
		// you have to call that in the Controller or with the static
		// RapidFX.rapidGenerate(Controller controller)
		// RapidFX.rapidGenerate(this) is equals to rapidFXgenerateMe();
		setUpController();
	}

	EventHandler<ActionEvent> closeEHAction = event -> {
		Platform.exit();
	};

	// gets Called after RapidFX.rapidGenerate is done so use Constructor to set
	// values before generation and this method after the generation
	// can call corresponding model.setUpModel and view.setUpView if they are
	// defined
	private void setUpController()
	{
		// the ok ActionProperty would have gave a NullPointerException if it's set
		// before RapidFXGenerateMe(), only after that the Autogenerate is done
		okActionProperty.set(event -> {
			model.login();
			System.out.println("Hello From The Controller");
		});
		model.setUpModel();
		view.setUpView();
	}
}
