package examples.HelloWorld;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import rapidFX.core.RapidFX;
import rapidFX.prebuilds.rSwitch.RSwitcher;

public class Launcher extends Application {
	public static void main(String[] args) {
		launch((String[]) null);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		Login log = new Login();
		/**
		 * 1. RapidFX executes the RapidFX Annotations with -> RapidFX.setUp() which gives all Attributes which are tagged and null a new SimpleObjectProperty
		 * 2. takes all Rcontroller tagged Fields(attributes) in the view and binds them to the fields in the Controller with the same name
		 * 3. takes all Rmodel tagged Fields(attributes) in the view and binds them to the fields in the Model with the same name
		 * NOTES: tagging in the Controller with Rcontroller or Rmodel is equals to RautoGenerate as it gets treated as RautoGenerate in setup but no bindings occur during connection
		 * NOTES: tagging in the Model with RController or Rmodel is equals to RautoGenerate as it gets treated as RautoGenerate in setup but no bindings occur during connection
		 * NOTES: tagging in the view with RautoGenerate will not bind them, they will just get generated
		 * 
		 * use in the Model always RautoGenerate for Properties which get bound from the outside, inside properties for the logic should be self initialized
		 * use in Controllers with the same reason as for the Model always RautoGenerate
		 * 
		 * after these initilizations -> the method setUpController gets called which is basically the content of the constructor but now its safe that all tagged properties are setup properly
		 */
		RapidFX.rapidGenerate(log).setUpController();
		primaryStage.setScene(new Scene(log.getView().getRootPane()));
		// RSwitcher r = new RSwitcher();
		primaryStage.show();
		
	}

}
