/*
 * 
 */
package de.github.yfons.rapidfx.examples.advanced;

import de.github.yfons.rapidfx.rapidFX.core.Rapidfx;
import de.github.yfons.rapidfx.rapidFX.simple.RapidSimpleController;
import javafx.event.EventHandler;

/**
 * The Class Login.
 */
public class Login extends RapidSimpleController<LoginView, LoginModel> {
  {
    view  = new LoginView();
    model = new LoginModel();
  }

  /**
   * Instantiates a new login.
   */
  public Login() {
    rapidfxMe();
  }

  /** The login request handler. */
  @SuppressWarnings("unused")
  private EventHandler<?> loginRequestHandler = event -> {
    model.login(view.getLoginBox().getLoginMessage());
  };

  /**
   * Rapidfx me.
   */
  @Override
  public void rapidfxMe() {
    super.rapidfxMe();
    Rapidfx.create(this).generate(view.getLoginBox()).connectWithController(view.getLoginBox());
  }

}
