/*
 * 
 */
package de.github.yfons.rapidfx.rapidFX.core.helper.resolver;

import de.github.yfons.rapidfx.rapidFX.core.helper.FieldRecord;
import de.github.yfons.rapidfx.rapidFX.core.helper.Exceptions.ExceptionFactory;
import de.github.yfons.rapidfx.rapidFX.core.helper.resolver.abstracts.AbstractBinder;
import javafx.beans.binding.Bindings;
import javafx.beans.property.Property;
import javafx.beans.property.ReadOnlyProperty;
import javafx.beans.value.ChangeListener;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;

/**
 * The Class RConnector binds the Properties of two Fields. <br>
 * It can also be used to set an {@link EventHandler} or a
 * {@link ChangeListener} on a Property with reflection
 */
@SuppressWarnings({ "unchecked", "rawtypes" })
public class BinderConnector extends AbstractBinder {

  protected ReadOnlyProperty<?> readOnlybindFromProperty;
  protected Property            bindFromProperty;

  /**
   * @param bindToField the field which includes the Property which should be used
   *                    bind the BindFromField Property
   * @param bindTo      the bind to Property
   */
  public BinderConnector(FieldRecord bindToContent, FieldRecord bindFromContent) {
    this.bindToField              = bindToContent;
    this.field                    = bindFromContent;
    this.readOnlybindFromProperty = bindFromContent.castToReadOnlyProperty();
  }

  /**
   * Connect the given bindFromPropertyw with the current property<br>
   * The setPropertyFrom method should be called before.
   */
  public void connectProperties() {
    if (this.bindToField.isNull()) {
      throw ExceptionFactory.emptyField(bindToField.field());
    }
    if (this.bindToField.getObject() instanceof ObservableList<?> list) {
      connectAsList(list);
    }
    if (this.bindToField.getObject() instanceof ChangeListener<?> listener) {
      addListener(listener);
    } else {
      this.bindFromProperty = field.castToProperty();
      connectAsProperty();
    }
  }

  private void connectAsList(ObservableList list) {
    Bindings.bindContentBidirectional(list, (ObservableList<?>) bindToField.getObject());
  }

  protected void connectAsProperty() {
    if (bindToField.getObject() instanceof EventHandler<?> handler) {
      setHandlerOnProperty(handler);
    } else if (this.bindToField.getObject() instanceof Property<?>) {
      bindOnPropertyInterface();
    }
  }

  protected void bindOnPropertyInterface() {
    if (isAssignable()) {
      bindProperty();
    } else {
      throw ExceptionFactory.incompatiblePropertiesErrorMessage(bindToField, field);
    }
  }

  protected final boolean isAssignable() {
    return this.bindToField.getType()
        .isAssignableFrom(readOnlybindFromProperty.getClass());
  }

  /**
   * Adds the listener on Property.
   *
   * @param listener the new {@link ChangeListener} on property
   */
  protected void addListener(ChangeListener listener) {
    readOnlybindFromProperty.addListener(listener);
  }

  /**
   * Sets the handler on property.
   *
   * @param bindToHandler the new {@link EventHandler} on property
   */
  protected void setHandlerOnProperty(EventHandler<?> bindToHandler) {
    bindFromProperty.setValue(bindToHandler);
  }

  /**
   * Bind property as bidirectional.
   *
   * @param <T>            the generic type of the Property which can be anything
   *                       as long as it is compatible with the other Property
   * @param bindToProperty the Current Property will be bound to the
   *                       bindToProperty
   */
  protected <T> void bindProperty() {
    Bindings.bindBidirectional(bindFromProperty, bindToField.castToProperty());
  }

}
