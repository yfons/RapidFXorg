/*
 * 
 */
package de.github.yfons.rapidfx.rapidFX.core.helper.fluent;

import java.lang.annotation.Annotation;
import java.util.Objects;

import de.github.yfons.rapidfx.rapidFX.annotation.RapidfxAutoGenerate;
import de.github.yfons.rapidfx.rapidFX.annotation.RapidfxController;
import de.github.yfons.rapidfx.rapidFX.core.helper.RConnSetup;
import de.github.yfons.rapidfx.rapidFX.core.helper.fluent.abstracts.RFLuentAbstract;
import de.github.yfons.rapidfx.rapidFX.core.helper.fluent.interfaces.RFluentAnyAPI;
import de.github.yfons.rapidfx.rapidFX.core.helper.fluent.interfaces.RFluentControllerAPI;
import de.github.yfons.rapidfx.rapidFX.core.helper.fluent.interfaces.RFluentControllerAPI.Connector;
import de.github.yfons.rapidfx.rapidFX.core.helper.resolver.BinderConnector;
import de.github.yfons.rapidfx.rapidFX.interfaces.RapidScheduler;

/**
 * The Class RFluentController is used to Resolve Rapid Annotations on any
 * {@link RapidScheduler}.
 *
 * @param <CONTROLLERT> the generic type
 */
public class RFluentController<CONTROLLERT extends RapidScheduler>
    extends RFLuentAbstract<CONTROLLERT>
    implements RFluentAnyAPI.Generator, RFluentControllerAPI.Connector {

  /**
   * Instantiates a new r fluent controller.
   *
   * @param object the Controller which can be Generated and Connected with
   *               {@link RFluentControllerAPI}.
   */
  public RFluentController(CONTROLLERT object) {
    super(object);
  }

  /**
   * Generates all Fields which are Tagged with {@link RapidfxAutoGenerate} with
   * the default Value.
   *
   * @param components the components
   * @return the connector
   */
  @Override
  public Connector generate(Object... components) {
    super.generate(components);
    return this;
  }

  /**
   * Connect the Component with the Controller based on the
   * {@link RapidfxController} Annotation.
   *
   * @param component the component
   * @return the connector
   */
  @Override
  public Connector connectWithController(Object component) {
    return connect(Objects.requireNonNull(component), this.controller, RapidfxController.class);
  }

  /**
   * Connects the Component on the connectOn Object based on the Annotation with
   * the use of {@link BinderConnector} and {@link RConnSetup}.
   *
   * @return the connector
   */
  @Override
  public Connector connect(Object component, Object connectOn,
      Class<? extends Annotation> onAnnotation) {
    super.connect(component, connectOn, onAnnotation);
    return this;
  }

}
