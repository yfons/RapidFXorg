package de.github.yfons.rapidfx.rapidFX.core;

import de.github.yfons.rapidfx.rapidFX.core.helper.fluent.RFluentAny;
import de.github.yfons.rapidfx.rapidFX.core.helper.fluent.RFluentController;
import de.github.yfons.rapidfx.rapidFX.interfaces.RapidScheduler;
import de.github.yfons.rapidfx.rapidFX.simple.RapidSimple;

/**
 * {@summary Is the Core Class for getting the Core functionalities of the Rapid
 * Framework.}
 *
 * @author marti
 */
public class Rapidfx {

  /**
   * {@summary gives a Builder for setting up the Object with the Rapid
   * Annotations.}
   *
   * @implNote doesn't do anything yet with the Object.
   * @param <CONTROLLERT> the generic type
   * @param controller    the controller
   * @return A Builder to configure rapidfxMe() method.
   */
  public static <CONTROLLERT extends RapidScheduler> RFluentController<CONTROLLERT> create(
      final CONTROLLERT controller) {
    
    
    return new RFluentController<>(controller);
  }

  /**
   * {@summary gives a Builder for setting up the Object with the Rapid
   * Annotations, works with any Object.}
   *
   * @implNote doesn't do anything yet with the Object.
   * @param <OBJECTT> the generic type
   * @param obj       the Object which should be getting setup
   * @return A Builder to configure rapidfxMe() method.
   */
  public static <OBJECTT> RFluentAny<OBJECTT> createAny(OBJECTT obj) {
    return new RFluentAny<>(obj);
  }

  /**
   * {@summary calls the rapidfxMe method of the RapidSimple Object and returns
   * it. }
   *
   * @param <RSIMPLET>   the type of the Parameter.
   * @param simpleObject the simple object.
   * @return The same Object after Querying the build Methods.
   */
  public static <RSIMPLET extends RapidSimple> RSIMPLET rapidGenerate(final RSIMPLET simpleObject) {
    simpleObject.rapidfxMe();
    return simpleObject;
  }
}
