package de.github.yfons.rapidfx.examples.HelloWorld;

import de.github.yfons.rapidfx.rapidFX.core.RapidFX;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Launcher extends Application
{
	public static void main(String[] args)
	{
		launch((String[]) null);
	}

	public void start(Stage primaryStage) throws Exception
	{
		Login log1 = RapidFX.build(Login::new).build();
		
		// The Controller supports a toString Method to see Inside in which State it is currently
		System.out.println(log1.toString());
		
		
		primaryStage.setScene(new Scene(log1.getView().getRootPane()));
		primaryStage.show();
		
	}

}
