package de.github.yfons.rapidfx.examples.advanced;

import de.github.yfons.rapidfx.premade.network.RMessage;
import de.github.yfons.rapidfx.rapidFX.annotation.RapidFXcontroller;
import de.github.yfons.rapidfx.rapidFX.simple.RapidSimpleView;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.StringProperty;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class LoginBox extends RapidSimpleView<VBox>
{
	private Button loginButton = new Button("Login");
	private Label user = new Label("UserName"), password = new Label("Password");
	private TextField userLogin = new TextField(), userPassword = new TextField();
	private RMessage<Account> message;
	private HBox userBox = new HBox(user, userLogin);
	private HBox passwordBox = new HBox(password, userPassword);
	private StringProperty userNameProperty = userLogin.textProperty(), passwordProperty = userPassword.textProperty();
	@RapidFXcontroller
	private ObjectProperty<EventHandler<javafx.event.ActionEvent>> loginRequestHandler = loginButton.onActionProperty();
	public LoginBox()
	{
		message = new RMessage<>();
		message.addContent(Account.NAME, userNameProperty);
		message.addContent(Account.PASSWORD, passwordProperty);
		root = new VBox(userBox, passwordBox, loginButton);

	}
	public RMessage<Account> getLoginMessage()
	{
		return message;
	}
}
