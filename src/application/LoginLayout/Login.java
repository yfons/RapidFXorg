package application.LoginLayout;

import javafx.application.Platform;
import javafx.beans.property.ObjectProperty;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import rapidFX.RautoGenerate;
import rapidFX.abstracts.RapidSimpleController;

public class Login extends RapidSimpleController<LoginView, LoginModel>
{
	@RautoGenerate
	private ObjectProperty<EventHandler<ActionEvent>> okAction;
	@RautoGenerate
	private ObjectProperty<EventHandler<ActionEvent>> closeAction;
	
	public Login()
	{
		model = new LoginModel();
		view = new LoginView();
	}
	
	@Override
	public void setUpController()
	{
		okAction.set(event -> model.login());
		closeAction.set(event -> Platform.exit());
		model.setUpModel();
		view.setUpView();
	}
	
}
