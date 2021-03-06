/*
 * 
 */
package de.github.yfons.rapidfx.rapidFX.core.helper.fluent.interfaces;

import de.github.yfons.rapidfx.rapidFX.annotation.RapidfxAutoGenerate;
import de.github.yfons.rapidfx.rapidFX.interfaces.RapidScheduler;
import java.lang.annotation.Annotation;

/**
 * The Interface RFluentControllerAPI.
 */
public interface RFluentControllerAPI {

  /**
   * The Connector. Which will resolve the Given annotations.
   */
  public interface Connector extends RFluentAnyAPI.Connector {
    Connector connectWithController(Object component);
  }
}