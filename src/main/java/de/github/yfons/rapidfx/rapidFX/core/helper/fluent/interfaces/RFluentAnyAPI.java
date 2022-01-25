/*
 * 
 */
package de.github.yfons.rapidfx.rapidFX.core.helper.fluent.interfaces;

import java.lang.annotation.Annotation;

public interface RFluentAnyAPI {

  /**
   * The Interface Generator.
   */
  public interface Generator {

    Connector generate(Object... components);

    <OBJECTT> OBJECTT get();
  }

  /**
   * The Interface Connector.
   */
  public interface Connector {

    Connector connect(Object component, Object connectOn, Class<? extends Annotation> onAnnotation);

    <OBJECTT> OBJECTT get();
  }

}
