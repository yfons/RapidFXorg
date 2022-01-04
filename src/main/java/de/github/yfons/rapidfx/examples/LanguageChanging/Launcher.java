package de.github.yfons.rapidfx.examples.LanguageChanging;

import de.github.yfons.rapidfx.premade.language.RTranslator;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class Launcher extends Application
{
	public static void main(String[] args)
	{
		launch((String[]) null);
	}

	boolean isEnglish = true;

	@Override
	public void start(Stage primaryStage) throws Exception
	{
		// as these Strings set the textProperty -> the LanguageManager gets the Content
		// and tries to find the Keys which are the same as the Content and then they
		// can get bound with bindTranslation
		Label greeting = new Label("Hello How are you?");
		Button exit = new Button("Leave?");
		Button switcher = new Button("Switch language");
		HBox layoutbox = new HBox(greeting, exit, switcher);

		// Injecting to the Manager which is through its static method available for any
		// Node
		// so any Node can bind its TextProperty to the Manager which is non Static
		LanguageManger manager = new LanguageManger("LANGUAGES.properties", LanguageManger.ENGLISH, "LANGUAGE.layout");
		RTranslator.injectLanguageManager(manager);

		// any textProperty can be bound based on its Current Content if its Key exists
		// in the Language.layout file
		// the value represents always the value from the key in the current
		// language.layout with the current language String
		RTranslator.bindTranslation(greeting.textProperty(), exit.textProperty());

		switcher.onActionProperty().set(event -> {
			if (isEnglish)
			{
				// languages can be swapped to any Language which s listed in the
				// languages.properties
				RTranslator.swapLanguages(LanguageManger.GERMAN);
				isEnglish = false;
				// the Language Manager has a toString Method to read which state the manager is
				// in at the moment, and to check which files are Loaded
				System.out.println(manager.toString());
			} else
			{
				isEnglish = true;
				RTranslator.swapLanguages(LanguageManger.ENGLISH);
				// the Language Manager has a toString Method to read which state the manager is
				// in at the moment, and to check which files are Loaded
				System.out.println(manager.toString());
			}
		});
		exit.onActionProperty().set(event -> Platform.exit());

		Scene languageScene = new Scene(layoutbox, 300, 300);
		primaryStage.setScene(languageScene);
		primaryStage.show();

	}
}
