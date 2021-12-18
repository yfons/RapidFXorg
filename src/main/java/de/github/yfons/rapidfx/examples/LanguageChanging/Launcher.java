package de.github.yfons.rapidfx.examples.LanguageChanging;

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
		launch((String[])null);
	}
	boolean isEnglish = true;
	@Override
	public void start(Stage primaryStage) throws Exception
	{
		
		Label greeting = new Label();
		Button exit = new Button();
		Button switcher = new Button("Switch language");
		HBox layoutbox = new HBox(greeting,exit,switcher);
		
		LanguageManger.bindToKey(LanguageManger.BUTTON_NAVIGATION_EXIT, exit.textProperty());
		LanguageManger.bindToKey(LanguageManger.BUTTON_NAVIGATION_GREETING, greeting.textProperty());
		
		switcher.onActionProperty().set(event ->{
			if(isEnglish) {
				LanguageManger.swapLanguages(LanguageManger.GERMAN);
				isEnglish = false;
			}
			else {
				isEnglish = true;
				LanguageManger.swapLanguages(LanguageManger.ENGLISH);
			}
		});
		exit.onActionProperty().set(event -> Platform.exit());
		
		Scene languageScene = new Scene(layoutbox,300,300);
		primaryStage.setScene(languageScene);
		primaryStage.show();
		
	}
}
