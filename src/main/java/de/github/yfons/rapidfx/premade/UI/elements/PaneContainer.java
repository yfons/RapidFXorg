package de.github.yfons.rapidfx.premade.UI.elements;

import java.util.function.Supplier;

import de.github.yfons.rapidfx.premade.UI.AbstractPaneContainer;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

public class PaneContainer<PANE extends Pane> extends AbstractPaneContainer<PANE,PaneContainer<PANE>>{

  public PaneContainer(Supplier<PANE> supplier) {
    super(supplier);
  }
}
