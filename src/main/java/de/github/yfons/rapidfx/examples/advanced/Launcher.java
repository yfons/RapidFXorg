/*
 * 
 */
package de.github.yfons.rapidfx.examples.advanced;

import java.util.Random;

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
    primaryStage.setScene(new Scene(new Login().getRootPane(), 500, 500));
    primaryStage.show();
  }
}
