/*
 * 
 */
package de.github.yfons.rapidfx.rapidFX.simple;

import de.github.yfons.rapidfx.rapidFX.annotation.RapidfxModel;
import de.github.yfons.rapidfx.rapidFX.core.Rapidfx;
import de.github.yfons.rapidfx.rapidFX.core.helper.RmBuilder;
import de.github.yfons.rapidfx.rapidFX.interfaces.RapidController;
import de.github.yfons.rapidfx.rapidFX.interfaces.RapidModel;
import de.github.yfons.rapidfx.rapidFX.interfaces.RapidView;

/**
 * The Class RapidSimpleController which gives a base Connection of 1 View and 1 Model.
 *
 * @param <RAPID_VIEW_CLASS>  the type of the View
 * @param <RAPID_MODEL_CLASS> the type of the Model
 */
public abstract class RapidSimpleController<RAPID_VIEW_CLASS extends RapidView<?>, RAPID_MODEL_CLASS extends RapidModel>
    extends RapidSimpleWeakController<RAPID_VIEW_CLASS> implements RapidController {

  protected RAPID_MODEL_CLASS model;

  @Override
  public void rapidfxMe() {
    super.rapidfxMe();
    Rapidfx.create(this).generate(model).connect(view, model, RapidfxModel.class).get();
  }

  /**
   * Gets the model.
   *
   * @return the model
   */
  @Override
  public final RAPID_MODEL_CLASS getModel() {
    return model;
  }

  @Override
  public String toString() {
    return super.toString() + RmBuilder.build(model.toString(), "MODEL");
  }
}
