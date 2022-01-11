/*
 * 
 */
package de.github.yfons.rapidfx.examples.advanced;

import de.github.yfons.rapidfx.premade.network.RMessage;
import de.github.yfons.rapidfx.rapidFX.annotation.RapidfxController;
import de.github.yfons.rapidfx.rapidFX.simple.RapidSimpleView;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.StringProperty;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

/**
 * The Class LoginBox.
 */
public class LoginBox extends RapidSimpleView<VBox> {

  /** The login button. */
  private Button loginButton = new Button("Login");

  /** The password. */
  private Label user = new Label("UserName"), password = new Label("Password");

  /** The user password. */
  private TextField userLogin = new TextField(), userPassword = new TextField();

  /** The message. */
  private RMessage<Account> message;

  /** The user box. */
  private HBox userBox = new HBox(user, userLogin);

  /** The password box. */
  private HBox passwordBox = new HBox(password, userPassword);

  /** The password property. */
  private StringProperty userNameProperty = userLogin.textProperty(),
      passwordProperty = userPassword.textProperty();

  /** The login request handler. */
  @RapidfxController
  private ObjectProperty<EventHandler<ActionEvent>> loginRequestHandler = loginButton
      .onActionProperty();

  /**
   * Instantiates a new login box.
   */
  public LoginBox() {
    message = new RMessage<>();
    message.addContent(Account.NAME, userNameProperty);
    message.addContent(Account.PASSWORD, passwordProperty);
    root = new VBox(userBox, passwordBox, loginButton);

  }

  /**
   * Gets the login message.
   *
   * @return the login message
   */
  public RMessage<Account> getLoginMessage() {
    return message;
  }
}
