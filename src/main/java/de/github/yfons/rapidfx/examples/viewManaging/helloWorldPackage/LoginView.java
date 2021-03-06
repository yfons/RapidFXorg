/*
 * 
 */
package de.github.yfons.rapidfx.examples.viewManaging.helloWorldPackage;

import de.github.yfons.rapidfx.rapidFX.annotation.RapidfxController;
import de.github.yfons.rapidfx.rapidFX.annotation.RapidfxModel;
import de.github.yfons.rapidfx.rapidFX.simple.RapidSimpleView;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.StringProperty;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

/**
 * The Class LoginView.
 */
class LoginView extends RapidSimpleView<VBox> {

  /** The title. */
  private final Label title = new Label();

  /** The ok. */
  private final Button ok = new Button("OK");

  /** The close. */
  private final Button close = new Button("Close");

 
  @RapidfxController
  private ObjectProperty<EventHandler<ActionEvent>> okActionProperty;
  // Get's not Initialized because its not null
  // it's also possible to add an Eventhandler/ChangeListener with this setup just
  // name the EventHandler/ChangeListener in the Controller like you did in the
  // view
  // changelistener work also for ReadonlyPropertys
  /** The close EH action. */
  // Get's bound to the closeActionProperty in the Controller
  @RapidfxController
  private final ObjectProperty<EventHandler<ActionEvent>> closeEHAction = close.onActionProperty();

  /** The title text property. */
  // Get's bound to the titleText in the Model as all Propertys can be bound
  @RapidfxModel
  private final StringProperty titleTextProperty = title.textProperty();

  /**
   * Instantiates a new login view.
   */
  LoginView() {
    // root is predefined in the RapidSimpleView as Object of the Generic type which
    // is mentioned at the class declaration
    // also the getter is already there, with the Generic type
    root = new VBox();
    // gets set before annotations get Read so it will bind ok.onActionProperty() to
    // the Controller
    okActionProperty = ok.onActionProperty();
    root.getChildren()
        .addAll(title, new HBox(ok, close));
  }

  /**
   * Sets the up view.
   */
  public void setUpView() {
    // will not find the CSS as the File LoginView.css is not in the current package
    // cssStyleRoot();
  }
}
