/*
 * 
 */
package de.github.yfons.rapidfx.rapidFX.interfaces;

import javafx.scene.layout.Pane;

/**
 * The Interface RapidView.
 *
 * @param <T> the generic type
 */
public interface RapidView<T extends Pane> {

  T getRootPane();
}
