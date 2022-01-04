package de.github.yfons.rapidfx.examples.advanced;

import de.github.yfons.rapidfx.rapidFX.core.RapidFX;
import de.github.yfons.rapidfx.rapidFX.simple.RapidSimpleController;
import javafx.event.EventHandler;

public class Login extends RapidSimpleController<LoginView, LoginModel>
{
	public Login()
	{
		view = RapidFX.createNew(LoginView::new);
		model = new LoginModel(view.getLoginBox().getLoginMessage());
		rapidFXMe();
	}

	private EventHandler<?> loginRequestHandler = event -> {
		model.login();
	};
	@Override
	protected RapidSimpleController<LoginView, LoginModel> rapidFXMe()
	{
		super.rapidFXMe();
		RapidFX.create(this)
			.generate(view.getLoginBox())
			.connectWithController(view.getLoginBox());
		return this;
	}

}
