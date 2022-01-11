/*
 * 
 */
package de.github.yfons.rapidfx.rapidFX.core.helper.fluent;

import java.lang.annotation.Annotation;
import java.util.Objects;

import de.github.yfons.rapidfx.rapidFX.core.helper.fluent.RFluentAnyAPI.Connector;


/**
 * The Class RFluentAny is used for using the Rapid Annotations on any Object.
 *
 * @param <OBJECT_TYPE> the generic type of the Object.
 */
@SuppressWarnings("unchecked")
public class RFluentAny<OBJECT_TYPE> extends RFLuentAbstract<OBJECT_TYPE>
    implements RFluentAnyAPI.Connector, RFluentAnyAPI.Generator {
  /**
   * Instantiates a new r fluent any.
   *
   * @param object the object
   */
  public RFluentAny(OBJECT_TYPE object) {
    super(object);
  }
}
