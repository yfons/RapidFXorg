/*
 * 
 */
package de.github.yfons.rapidfx.rapidFX.simple;

import de.github.yfons.rapidfx.rapidFX.core.helper.RmBuilder;
import de.github.yfons.rapidfx.rapidFX.interfaces.RapidLayout;
import javafx.scene.Node;

/**
 * The Class RapidSimpleLayout.
 *
 * @param <LAYOUT_NODE_TYPE> the generic type
 */
public abstract class RapidSimpleLayout<LAYOUT_NODE_TYPE extends Node> extends RapidSimple
    implements RapidLayout<LAYOUT_NODE_TYPE> {

  /** The layout. */
  protected LAYOUT_NODE_TYPE layout;

  /**
   * Gets the layout.
   *
   * @return the layout
   */
  @Override
  public final LAYOUT_NODE_TYPE getLayout() {
    return this.layout;
  }

  @Override
public String toString() {
	return "RapidSimpleLayout [layout=" + layout + "], [getClass()=" + getClass() + "]";
}

}
