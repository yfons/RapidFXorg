/*
 * 
 */
package de.github.yfons.rapidfx.premade.network;

import javafx.beans.property.Property;

/**
 * The Class RMessage.
 *
 * @param <ENUM_KEY> the generic type
 */
public class RMessage<ENUM_KEY extends Enum<?>> extends RFormular<ENUM_KEY, Property<?>> {

  /**
   * Gets the content.
   *
   * @param key the key
   * @return the content
   */
  @Override
  public Property<?> getContent(ENUM_KEY key) {
    return contentMap.get(key);
  }

}
