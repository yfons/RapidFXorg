/*
 * 
 */
package de.github.yfons.rapidfx.rapidFX.simple;

import de.github.yfons.rapidfx.rapidFX.core.Rapidfx;
import de.github.yfons.rapidfx.rapidFX.core.helper.RmBuilder;
import de.github.yfons.rapidfx.rapidFX.interfaces.RapidScheduler;
import de.github.yfons.rapidfx.rapidFX.interfaces.RapidModel;
import de.github.yfons.rapidfx.rapidFX.interfaces.RapidView;
import javafx.scene.layout.Pane;

/**
 * The Class RapidSimpleScheduler.
 *
 * @param <VIEWT> the generic type
 */
public abstract class RapidSimpleScheduler<VIEWT extends RapidView<?>> extends RapidSimple
    implements RapidScheduler {
  protected VIEWT view;

  @Override
  public void rapidfxMe() {
    Rapidfx.create(this)
        .generate(this.view, this)
        .connectWithController(this.view)
        .get();
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
  public String toString() {
    return "Scheduler =>" + RmBuilder.clazz(this.getClass())
        + RmBuilder.build(view.toString(), "VIEW_OBJECT");
  }
}
