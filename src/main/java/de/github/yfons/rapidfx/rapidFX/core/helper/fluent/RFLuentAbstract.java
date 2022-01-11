/*
 * 
 */
package de.github.yfons.rapidfx.rapidFX.core.helper.fluent;

import java.lang.annotation.Annotation;
import java.util.Objects;

import de.github.yfons.rapidfx.rapidFX.core.helper.RConnSetup;
import de.github.yfons.rapidfx.rapidFX.core.helper.fluent.RFluentAnyAPI.Connector;

/**
 * The Class RFLuentAbstract.
 *
 * @param <OBJECT_TYPE> the generic type
 */
public abstract class RFLuentAbstract<OBJECT_TYPE> implements RFluentAnyAPI.Generator, Connector{

  protected OBJECT_TYPE controller;
  protected RConnSetup setuper = new RConnSetup();

  public RFLuentAbstract(OBJECT_TYPE object) {
    Objects.requireNonNull(object);
    this.controller = object;
  }

  public OBJECT_TYPE get() {
    return controller;
  }
  public RFluentAnyAPI.Connector generate(Object... components) {
    for (Object obj : components) {
      Objects.requireNonNull(obj);
    }
    setuper.setUp(components);
    return this;
  }
  /**
   * Gets the object.
   *
   * @return the object
   */
  public OBJECT_TYPE getObject() {
    return this.controller;
  }
  @Override
  public Connector connect(Object component, Object connectOn,
      Class<? extends Annotation> onAnnotation) {
      setuper.connect(Objects.requireNonNull(component), Objects.requireNonNull(connectOn),
          Objects.requireNonNull(onAnnotation));
      return this;
    
  }
}
