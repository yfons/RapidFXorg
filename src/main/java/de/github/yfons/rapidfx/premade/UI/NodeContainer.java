package de.github.yfons.rapidfx.premade.UI;

import java.util.function.Supplier;

import javafx.scene.Node;

public class NodeContainer<NODE extends Node, CONTAINER extends NodeContainer<NODE,CONTAINER>>  {
  protected NODE root;
  public NodeContainer(Supplier<NODE> supplier) {
    root = supplier.get();
  }
  public CONTAINER id(String id) {
    root.setId(id);
    return (CONTAINER) this;
  }

  public CONTAINER enable() {
    root.setDisable(false);
    return (CONTAINER) this;
  }

  public CONTAINER disable() {
    root.setDisable(true);
    return (CONTAINER) this;
  }

  public CONTAINER visible() {
    root.setVisible(true);
    return (CONTAINER) this;
  }

  public CONTAINER invisible() {
    root.setVisible(false);
    return (CONTAINER) this;
  }
  public CONTAINER prefWidth(double prefWidth){
    root.prefWidth(prefWidth);
    return (CONTAINER) this;
  }
  public CONTAINER maxWidth(double maxWidth){
    root.maxWidth(maxWidth);
    return (CONTAINER) this;
  }
  public CONTAINER minWidth(double minWidth){
    root.minWidth(minWidth);
    return (CONTAINER) this;
  }
  public CONTAINER prefHeight(double prefHeight){
    root.prefHeight(prefHeight);
    return (CONTAINER) this;
  }
  public CONTAINER maxHeight(double maxHeight){
    root.maxHeight(maxHeight);
    return (CONTAINER) this;
  }
  public CONTAINER minHeight(double minHeight){
    root.minHeight(minHeight);
  
    return (CONTAINER) this;
  }
  public CONTAINER rotate(double rotation) {
    root.setRotate(rotation);
    return (CONTAINER) this;
  }
  public NODE get() {
   return root; 
  }
}
