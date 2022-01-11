/*
 * 
 */
package de.github.yfons.rapidfx.rapidFX.simple;

import de.github.yfons.rapidfx.premade.network.RMessage;
import de.github.yfons.rapidfx.rapidFX.core.helper.RmBuilder;
import de.github.yfons.rapidfx.rapidFX.interfaces.RapidView;
import javafx.scene.layout.Pane;

/**
 * The Class RapidSimpleView.
 *
 * @param <ROOT_PANE_TYPE> the generic type
 */
public class RapidSimpleView<ROOT_PANE_TYPE extends Pane> extends RapidSimple
    implements RapidView<ROOT_PANE_TYPE> {

  /** The root. */
  protected ROOT_PANE_TYPE root;

  /**
   * Gets the root pane.
   *
   * @return the root pane
   */
  @Override
  public final ROOT_PANE_TYPE getRootPane() {
    return root;
  }

  /**
   * adds to the root node which is given by {@link RapidSimpleView#root} a Style
   * Sheet which is named after the VIEW_CLASS_NAME + .css <br>
   * e.g ExamplePaneView.java -> ExamplePaneView.css
   */
  protected final void cssStyleRoot() {
    root.getStylesheets().add(findCssStyleSheet());
  }

  /**
   * Doesn't do Anything by Default.
   *
   * @param <ENUMTYPE> the generic type
   * @param message    the message
   */
  public <ENUMTYPE extends Enum<?>> void addSubViews(RMessage<ENUMTYPE> message) {

  }

  /**
   * Find css style sheet.
   *
   * @return the string
   */
  protected final String findCssStyleSheet() {
    return getResource(getClass().getSimpleName() + ".css");
  }

  /**
   * To string.
   *
   * @return the string
   */
  @Override
  public String toString() {
    return "\t"+RmBuilder.clazz(getClass()) + RmBuilder.build(this.root, "ROOT_PANE");
  }
}
