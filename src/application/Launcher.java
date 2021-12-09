package application;

import application.LoginLayout.Login;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import rapidFX.interfaces.RapidFX;
import rapidFX.rapid.RSwitchFactory;
import rapidFX.rapid.RSwitcher;

public class Launcher extends Application
{
	public static void main(String[] args)
	{
		launch((String[]) null);
	}
	
	@Override
	public void start(Stage primaryStage) throws Exception
	{
		Login log = new Login();
		RapidFX.rapidGenerate(log).setUpController();
		primaryStage.setScene(new Scene(log.getView().getRootPane()));
		primaryStage.show();
	}
	
}
