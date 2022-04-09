/*
 * 
 */
package de.github.yfons.rapidfx.examples.HelloWorld;

import de.github.yfons.rapidfx.rapidFX.annotation.RapidfxAutoGenerate;
import de.github.yfons.rapidfx.rapidFX.annotation.RapidfxController;
import de.github.yfons.rapidfx.rapidFX.core.Rapidfx;
import de.github.yfons.rapidfx.rapidFX.simple.RapidSimpleScheduler;
import de.github.yfons.rapidfx.rapidFX.simple.RapidSimpleStrongScheduler;
import javafx.application.Platform;
import javafx.beans.property.ObjectProperty;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

/**
 * The Class Login.
 */
public class Login extends RapidSimpleScheduler<LoginView> {

  /** The ok action property. */
  // Get's Initialized as new SimpleObjectProperty<>()
  @RapidfxAutoGenerate
  private ObjectProperty<EventHandler<ActionEvent>> okActionProperty;

  /**
   * Instantiates a new login.
   */
  public Login() {
    // view is predefined as viewClass generic type
    view = new LoginView();
    rapidfxMe();
    // if you want to Execute all Annotations ( Autogenerating + setting bindings )
    // you have to call that in the Controller or with the static
    // RapidFX.rapidGenerate(Controller controller)
    // RapidFX.rapidGenerate(this) is equals to rapidFXgenerateMe();
    // as the RapidFX class will call the rapidFXMe() method
    setUpController();
  }

  /** The close eh action. */
 // private EventHandler<ActionEvent> closeEhAction = event -> {
//    Platform.exit();
//  };

  // gets Called after RapidFX.rapidGenerate is done so use Constructor to set
  // values before generation and this method after the generation
  // can call corresponding model.setUpModel and view.setUpView if they are
  /**
   * Sets the up controller.
   */
  // defined
  private void setUpController() {
    // the ok ActionProperty would have gave a NullPointerException if it's set
    // before RapidFXGenerateMe(), only after that the Autogenerate is done
    okActionProperty.set(event -> {
      System.out.println("Hello From The Scheduler");
    });
    view.setUpView();

  }


}
