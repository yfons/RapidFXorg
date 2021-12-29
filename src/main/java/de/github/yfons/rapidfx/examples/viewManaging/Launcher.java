package de.github.yfons.rapidfx.examples.viewManaging;

import de.github.yfons.rapidfx.examples.viewManaging.helloWorldPackage.Login;
import de.github.yfons.rapidfx.premade.RViewManager;
import de.github.yfons.rapidfx.rapidFX.core.RapidFX;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

/**
 * this Example is not made in "well" written Code, it should just summarize how to use the RViewManager
 * @author marti
 */
public class Launcher extends Application
{
	public static void main(String[] args)
	{
		launch((String[]) null);
	}

	public void start(Stage primaryStage) throws Exception
	{
		var log1 = RapidFX.create(Login::new);
		var log2 = RapidFX.create(Login::new);
		Scene scene = new Scene( log2.getRootPane() );
		
		RViewManager<String> manager = new RViewManager<>(scene);
		manager.appendFactory("First View", log1);
		manager.appendFactory("Second View", log2);
		

		
		// normally the second view would have been shown, but here you change it to the first one
		manager.swapToView("First View");
		
		System.out.println(manager);
		Button button = new Button("Switch Button");
		
		// the eventhandler switches on action to the Second View, as the button will disappear -> only exists in First View
		button.onActionProperty().set(event -> manager.swapToView("Second View"));
		
		log1.getRootPane().getChildren().add(button);
		
		primaryStage.setScene(scene);
		primaryStage.show();

	}

}
