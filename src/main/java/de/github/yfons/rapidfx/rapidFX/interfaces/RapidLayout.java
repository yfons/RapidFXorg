/*
 * 
 */
package de.github.yfons.rapidfx.rapidFX.interfaces;

import javafx.scene.Node;

/**
 * The Interface RapidLayout.
 *
 * @param <NODET> the generic type
 */
public interface RapidLayout<NODET extends Node> {

  NODET getLayout();

}
