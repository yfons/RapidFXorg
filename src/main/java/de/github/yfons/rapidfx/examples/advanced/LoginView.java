/*
 * 
 */
package de.github.yfons.rapidfx.examples.advanced;

import de.github.yfons.rapidfx.rapidFX.simple.RapidSimple;
import de.github.yfons.rapidfx.rapidFX.simple.RapidSimpleView;
import javafx.scene.layout.BorderPane;

/**
 * The Class LoginView.
 */
public class LoginView extends RapidSimpleView<BorderPane> {

  /** The log box. */
  LoginBox logBox = new LoginBox();

  /**
   * Instantiates a new login view.
   */
  public LoginView() {
    root = new BorderPane();
    root.setCenter(logBox.getRootPane());
  }

  /**
   * Gets the login box.
   *
   * @return the login box
   */
  public LoginBox getLoginBox() {
    return logBox;
  }
 
}

