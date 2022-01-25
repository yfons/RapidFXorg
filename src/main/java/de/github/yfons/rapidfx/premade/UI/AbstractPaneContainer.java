package de.github.yfons.rapidfx.premade.UI;

import java.util.Objects;
import java.util.function.Supplier;

import de.github.yfons.rapidfx.rapidFX.interfaces.RapidView;
import javafx.scene.Node;
import javafx.scene.layout.Pane;

public abstract class AbstractPaneContainer<PANE extends Pane,CONTAINER extends AbstractPaneContainer<PANE,CONTAINER>> extends NodeContainer<PANE, CONTAINER>{
  public AbstractPaneContainer(Supplier<PANE> supplier) {
    super(supplier);
  }
  public CONTAINER add(Node... nodes) {
    root.getChildren().addAll(nodes);
    return (CONTAINER) this;
  }
  public CONTAINER add(RapidView<?>... views) {
    for(var view : views) {
      root.getChildren().add(view.getRootPane());
    }
    return (CONTAINER) this;
  }
  public CONTAINER add(NodeContainer<?,?>... container) {
    for(var con : container) {
      root.getChildren().add(con.get());
    }
    return (CONTAINER) this;
  }

}
