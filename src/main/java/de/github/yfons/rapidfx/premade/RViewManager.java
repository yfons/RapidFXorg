/*
 * 
 */
package de.github.yfons.rapidfx.premade;

import java.util.Set;

import de.github.yfons.rapidfx.rapidFX.interfaces.RapidScheduler;
import javafx.collections.FXCollections;
import javafx.collections.ObservableMap;
import javafx.scene.Scene;

/**
 * The Class RViewManager.
 *
 * @param <keyValue> the generic type
 */
public class RViewManager<keyValue> {

  /** The factory map. */
  private final ObservableMap<keyValue, RapidScheduler> factoryMap = FXCollections
      .observableHashMap();

  /** The scene. */
  private Scene scene;

  /**
   * Instantiates a new r view manager.
   *
   * @param scene the scene
   */
  public RViewManager(Scene scene) {
    this.scene = scene;
  }

  /**
   * Append factory.
   *
   * @param key     the key
   * @param factory the factory
   */
  public void appendFactory(keyValue key, RapidScheduler factory) {
    factoryMap.put(key, factory);
  }

  /**
   * Removes the factory.
   *
   * @param key the key
   */
  public void removeFactory(keyValue key) {
    factoryMap.remove(key);
  }

  /**
   * Gets the map.
   *
   * @return the map
   */
  public ObservableMap<keyValue, RapidScheduler> getMap() {
    return factoryMap;
  }

  /**
   * Gets the keys.
   *
   * @return the keys
   */
  public Set<keyValue> getKeys() {
    return factoryMap.keySet();
  }

  /**
   * Swap to view.
   *
   * @param key the key
   */
  public void swapToView(keyValue key) {
    scene.setRoot(factoryMap.get(key)
        .getView()
        .getRootPane());
  }

  /**
   * To string.
   *
   * @return the string
   */
  @Override
  public String toString() {
    return "View Manager" + "\n\t=> CLASS =>" + this.getClass() + "\n\t=> SCENE => " + this.scene
        + "\n\t=> CURRENT_VIEW => " + this.scene.getRoot() + "\n\t=> VIEW_MAP => " + factoryMap
        + "\n";
  }
}
