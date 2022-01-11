/*
 * 
 */
package de.github.yfons.rapidfx.premade.network;

import java.util.HashMap;

/**
 * The Class RFormular.
 *
 * @param <KEY_ENUM>     the generic type
 * @param <CONTENT_TYPE> the generic type
 */
public abstract class RFormular<KEY_ENUM extends Enum<?>, CONTENT_TYPE> {

  /** The content map. */
  protected HashMap<KEY_ENUM, CONTENT_TYPE> contentMap = new HashMap<>();

  /**
   * Adds the content.
   *
   * @param key      the key
   * @param property the property
   */
  public void addContent(KEY_ENUM key, CONTENT_TYPE property) {
    contentMap.put(key, property);
  }

  /**
   * Gets the content.
   *
   * @param key the key
   * @return the content
   */
  public abstract Object getContent(KEY_ENUM key);

  /**
   * Gets the content property.
   *
   * @param key the key
   * @return the content property
   */
  public final CONTENT_TYPE getContentProperty(KEY_ENUM key) {
    return contentMap.get(key);
  }
}
