/*
 * 
 */
package de.github.yfons.rapidfx.rapidFX.simple;

import de.github.yfons.rapidfx.rapidFX.core.helper.RmBuilder;
import de.github.yfons.rapidfx.rapidFX.interfaces.RapidModel;

/**
 * The Class RapidSimpleModel.
 */
public abstract class RapidSimpleModel implements RapidModel {

  @Override
  public String toString() {
    return RmBuilder.clazz(getClass());
  }
}
