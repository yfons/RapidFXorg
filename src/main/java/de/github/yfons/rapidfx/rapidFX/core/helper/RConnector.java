/*
 * 
 */
package de.github.yfons.rapidfx.rapidFX.core.helper;

import java.lang.reflect.Field;

import de.github.yfons.rapidfx.rapidFX.core.RapidfxRuntimeException;
import javafx.beans.property.Property;
import javafx.beans.property.ReadOnlyProperty;
import javafx.beans.value.ChangeListener;
import javafx.event.EventHandler;

/**
 * The Class RConnector binds the Properties of two Fields. <br>
 * It can also be used to set an {@link EventHandler} or a
 * {@link ChangeListener} on a Property with reflection
 */
@SuppressWarnings({ "unchecked", "rawtypes" })
public class RConnector {

  private ReadOnlyProperty<?> readOnlybindFromProperty;
  private FieldRecord   bindFromContent;
  private FieldRecord   bindToContent;

  /**
   * Instantiates a new r connector.
   *
   * @param bindToField the field which includes the Property which should be used
   *                    bind the BindFromField Property
   * @param bindTo      the bind to Property
   */
  public RConnector(FieldRecord bindToContent,FieldRecord bindFromContent) {
    this.bindToContent = bindToContent;
    this.bindFromContent = bindFromContent;
    this.readOnlybindFromProperty = bindFromContent.castToReadOnlyProperty();
  }

  /**
   * Connect the given bindFromPropertyw with the current property<br>
   * The setPropertyFrom method should be called before.
   */
  public void connectProperties() {
    if (bindToContent.getObject() instanceof ChangeListener<?> listener) {
      addListener(listener);
    } else {
      this.bindFromProperty = bindFromContent.castToProperty();
      connectOnPropertyInterface();
    }
  }

  private void connectOnPropertyInterface() {
    if (bindToContent.getObject() instanceof EventHandler<?> handler) {
      setHandlerOnProperty(handler);
    } else if (bindToContent.getObject() instanceof Property<?>) {
      bindOnPropertyInterface();
    }
  }

  private Property bindFromProperty;
  private void bindOnPropertyInterface() {
    if (isAssignable()) {
      bindProperty();
    } else {
      throw incompatiblePropertiesErrorMessage(bindToContent.field());
    }
  }

  private boolean isAssignable() {
    return bindToContent.getType().isAssignableFrom(readOnlybindFromProperty.getClass());
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
   * Bind property.
   *
   * @param <T>            the generic type of the Property which can be anything
   *                       as long as it is compatible with the other Property
   * @param bindToProperty the Current Property will be bound to the
   *                       bindToProperty
   */
  protected <T> void bindProperty() {
    bindFromProperty.bind(this.bindFromProperty);
  }
  private RapidfxRuntimeException incompatiblePropertiesErrorMessage(final Field bindToField) {
    return new RapidfxRuntimeException(
        "\nThe Field is not  A EventHandler or A ChangeListener or an Assignable Property"
            + RmBuilder.name(bindToField.getName())
            + RmBuilder.clazz(bindToField.getDeclaringClass())
            + RmBuilder.type(bindToField.getType())
            + RmBuilder.build(bindFromContent.getType(), "EXPECTED_TYPE")
            + RmBuilder.build(bindFromContent.getFieldName(), "BASED_ON_FIELD")
            + RmBuilder.build(bindFromContent.getDeclaredClass(), "BASED_ON_CLASS"));
  }
}
