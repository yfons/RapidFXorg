package de.github.yfons.rapidfx.examples.advanced;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Launcher extends Application
{
	public static void main(String[] args)
	{
		launch((String[]) null);
	}

	@Override
	public void start(Stage primaryStage) throws Exception
	{
		primaryStage.setScene(new Scene(new Login().getRootPane(), 500, 500));
		primaryStage.show();
	}
}
