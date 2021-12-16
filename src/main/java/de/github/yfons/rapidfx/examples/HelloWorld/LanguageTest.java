package de.github.yfons.rapidfx.examples.HelloWorld;

import de.github.yfons.rapidfx.premade.RLanguageManager;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class LanguageTest extends Application
{
	public static void main(String[] args)
	{
		launch(args);
	}
	@Override
	public void start(Stage primaryStage) throws Exception
	{
		RLanguageManager rl = new RLanguageManager<>("LANGUAGES.Properties", "FRENCH","LANGUAGE.layout");
		Button test = new Button();
		test.textProperty().bind(rl.getProperty("BUTTON.login.text"));
		test.onActionProperty().set(event -> {
			rl.swapLanguage("ENGLISH");
		});
		primaryStage.setScene(new Scene(test));
		primaryStage.show();
	}

}
