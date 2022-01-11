/*
 * 
 */
package de.github.yfons.rapidfx.examples.HelloWorld;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * The Class Launcher.
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
    Login log1 = new Login();

    // The Controller supports a toString Method to see Inside in which State it is
    // currently
    System.out.println(log1.toString());

    primaryStage.setScene(new Scene(log1.getView().getRootPane()));
    primaryStage.show();

  }

}
