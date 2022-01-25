/*
 * 
 */
package de.github.yfons.rapidfx.examples.viewManaging;

import de.github.yfons.rapidfx.examples.viewManaging.helloWorldPackage.Login;
import de.github.yfons.rapidfx.premade.RViewManager;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

/**
 * this Example is not made in "well" written Code, it should just summarize how
 * to use the RViewManager.
 *
 * @author marti
 */
public class Launcher extends Application {

  /**
   * The main method.
   *
   * @param args the arguments
   */
  public static void main(String[] args) {
    launch((String[]) null);
  }

  /**
   * Start.
   *
   * @param primaryStage the primary stage
   * @throws Exception the exception
   */
  @Override
  public void start(Stage primaryStage) throws Exception {
    var   log1  = new Login();
    var   log2  = new Login();
    Scene scene = new Scene(log2.getRootPane());

    RViewManager<String> manager = new RViewManager<>(scene);
    manager.appendFactory("First View", log1);
    manager.appendFactory("Second View", log2);

    // normally the second view would have been shown, but here you change it to the
    // first one
    manager.swapToView("First View");

    System.out.println(manager);
    Button button = new Button("Switch Button");

    // the eventhandler switches on action to the Second View, as the button will
    // disappear -> only exists in First View
    button.onActionProperty()
        .set(event -> manager.swapToView("Second View"));

    log1.getRootPane()
        .getChildren()
        .add(button);

    primaryStage.setScene(scene);
    primaryStage.show();

  }

}
