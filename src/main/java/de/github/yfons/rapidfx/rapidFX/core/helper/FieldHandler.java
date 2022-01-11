package de.github.yfons.rapidfx.rapidFX.core.helper;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;

import de.github.yfons.rapidfx.rapidFX.core.RapidfxRuntimeException;

public class FieldHandler {
  protected final FieldRecord bindFromField;
  protected FieldRecord bindToField;

  public FieldHandler(FieldRecord bindFromField) {
    this.bindFromField = bindFromField;
  }

  public boolean isAnnotationPresent(Class<? extends Annotation> annotation) {
    return bindFromField.isAnnotationPresent(annotation);
  }

  /**
   * Sets the default value for a Property, which is SimpleXXXProperty.
   */
  public void setDefaultProperty() {
    if (!bindFromField.isTypeOfProperty()) {
      throw castToPropertyErrorMessage();
    } else if (bindFromField.isNull()) {
      bindFromField.setNewSimpleProperty();
    }
  }

  public void bindWith(final Object bindTo) {
    this.bindToField = this.findFieldWithSameName(bindTo);

    if (bindFromField.isNull()) {
      throw isEmptyFieldErrorMessage(bindFromField.field());
    }
    if (bindToField.isNull()) {
      throw isEmptyFieldErrorMessage(bindToField.field());
    }
    launchConnector(bindToField);
  }

  private void launchConnector(final FieldRecord bindTo) {
    final var connector = new RConnector(bindTo,bindFromField);
    connector.connectProperties();
  }

  private FieldRecord findFieldWithSameName(final Object bindTo) {
    final var fieldName = bindFromField.getFieldName();
    try {
      final var foundField = bindTo.getClass().getDeclaredField(fieldName);
      foundField.setAccessible(true);
      return new FieldRecord(foundField, bindTo);
    } catch (NoSuchFieldException e) {
      throw rapidNoFieldException(bindTo, fieldName);
    } catch (IllegalArgumentException e) {
      throw new RapidfxRuntimeException(e.getMessage());
    }
  }

  private RapidfxRuntimeException rapidNoFieldException(final Object bindTo, final String fieldName) {
    return new RapidfxRuntimeException("\nThe Field was not found"
        + "\nThis can be caused when the Module-Info doesn't \"open PACKAGENAME\" "
        + "where the Class is Part of" + RmBuilder.name(fieldName)
        + RmBuilder.clazz(bindTo.getClass())
        + RmBuilder.build(bindToField.getType(), "EXPECTED_TYPE") + "\n");
  }
  
  protected RapidfxRuntimeException isEmptyFieldErrorMessage(final Field field) {
    return new RapidfxRuntimeException(
        "\nThe Value of the Field which should be a Property/EventHandler/ChangeListener is null,"
            + " can't bind with null values" + RmBuilder.name(field.getName())
            + RmBuilder.clazz(field.getDeclaringClass())
            + RmBuilder.type(field.getType()) + "\n");
  }
  
  protected RapidfxRuntimeException castToPropertyErrorMessage() {
    return new RapidfxRuntimeException(
        "\nCouldn't cast the Field to a Property"  + RmBuilder.name(bindFromField.getFieldName())
            + RmBuilder.clazz(bindFromField.getDeclaredClass())
            + RmBuilder.type(bindFromField.getType()));
  }
}
