package de.github.yfons.rapidfx.premade.UI.elements;

import java.util.function.Supplier;

import de.github.yfons.rapidfx.premade.UI.NodeContainer;
import de.github.yfons.rapidfx.rapidFX.interfaces.RapidView;
import javafx.scene.Node;
import javafx.scene.layout.BorderPane;

public class BorderPaneContainer extends NodeContainer<BorderPane,BorderPaneContainer>{

  public BorderPaneContainer(Supplier<BorderPane> supplier) {
    super(supplier);
  }
  public BorderPaneContainer top(Node node) {
    root.setTop(node);
    return this;
  }
  public BorderPaneContainer top(NodeContainer<?,?> cont) {
    root.setTop(cont.get());
    return this;
  }
  public BorderPaneContainer top(RapidView<?> view) {
    root.setTop(view.getRootPane());
    return this;
  }
  public BorderPaneContainer right(Node node) {
    root.setRight(node);
    return this;
  }
  public BorderPaneContainer right(NodeContainer<?,?> cont) {
    root.setRight(cont.get());
    return this;
  }
  public BorderPaneContainer right(RapidView<?> view) {
    root.setRight(view.getRootPane());
    return this;
  }
  public BorderPaneContainer left(Node node) {
    root.setLeft(node);
    return this;
  }
  public BorderPaneContainer left(NodeContainer<?,?> cont) {
    root.setLeft(cont.get());
    return this;
  }
  public BorderPaneContainer left(RapidView<?> view) {
    root.setLeft(view.getRootPane());
    return this;
  }
  public BorderPaneContainer bottom(Node node) {
    root.setBottom(node);
    return this;
  }
  public BorderPaneContainer bottom(NodeContainer<?,?> cont) {
    root.setBottom(cont.get());
    return this;
  }
  public BorderPaneContainer bottom(RapidView<?> view) {
    root.setBottom(view.getRootPane());
    return this;
  }
  public BorderPaneContainer center(Node node) {
    root.setCenter(node);
    return this;
  }
  public BorderPaneContainer center(NodeContainer<?,?> cont) {
    root.setCenter(cont.get());
    return this;
  }
  public BorderPaneContainer center(RapidView<?> view) {
    root.setCenter(view.getRootPane());
    return this;
  }
  
}
