package de.github.yfons.rapidfx.premade.UI;

import java.util.function.Supplier;

import de.github.yfons.rapidfx.premade.UI.elements.BorderPaneContainer;
import de.github.yfons.rapidfx.premade.UI.elements.PaneContainer;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;

public class ContainerFactory {
  public static <BOX extends Pane> PaneContainer<BOX> getBox(Supplier<BOX> supplier) {
    return new PaneContainer<BOX>(supplier);
  }
  public static BorderPaneContainer getBorderPane() {
    return new BorderPaneContainer(BorderPane::new);
  }

}
