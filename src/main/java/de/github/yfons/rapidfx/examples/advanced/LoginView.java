package de.github.yfons.rapidfx.examples.advanced;

import de.github.yfons.rapidfx.rapidFX.simple.RapidSimpleView;
import javafx.scene.layout.BorderPane;

public class LoginView extends RapidSimpleView<BorderPane>
{

	LoginBox logBox = new LoginBox();

	public LoginView()
	{
		root = new BorderPane();
		root.setCenter(logBox.getRootPane());
	}

	public LoginBox getLoginBox() {
		return logBox;
	}

}
