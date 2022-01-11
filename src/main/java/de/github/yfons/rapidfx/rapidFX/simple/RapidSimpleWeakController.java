/*
 * 
 */
package de.github.yfons.rapidfx.rapidFX.simple;

import de.github.yfons.rapidfx.rapidFX.core.Rapidfx;
import de.github.yfons.rapidfx.rapidFX.core.helper.RmBuilder;
import de.github.yfons.rapidfx.rapidFX.interfaces.RapidController;
import de.github.yfons.rapidfx.rapidFX.interfaces.RapidModel;
import de.github.yfons.rapidfx.rapidFX.interfaces.RapidView;
import javafx.scene.layout.Pane;

/**
 * The Class RapidSimpleWeakController.
 *
 * @param <VIEWT> the generic type
 */
public abstract class RapidSimpleWeakController<VIEWT extends RapidView<?>> extends RapidSimple
    implements RapidController {
  protected VIEWT view;

  @Override
  public void rapidfxMe() {
    Rapidfx.create(this).generate(this.view, this).connectWithController(this.view).get();
  }

  @Override
  public VIEWT getView() {
    return view;
  }

  @Override
  public final Pane getRootPane() {
    return view.getRootPane();
  }

  @Override
  public RapidModel getModel() {
    return null;
  }

  @Override
  public String toString() {
    return "Controller =>" + RmBuilder.clazz(this.getClass())
        + RmBuilder.build(view.toString(), "VIEW_OBJECT");
  }
}
