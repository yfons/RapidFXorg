package de.github.yfons.rapidfx.rapidFX.core.helper.resolver;

import java.util.Objects;

import de.github.yfons.rapidfx.rapidFX.core.RapidfxRuntimeException;
import de.github.yfons.rapidfx.rapidFX.core.helper.FieldRecord;
import de.github.yfons.rapidfx.rapidFX.core.helper.Exceptions.ExceptionFactory;
import de.github.yfons.rapidfx.rapidFX.core.helper.resolver.abstracts.AbstractBinder;

public class BindingResolver extends AbstractBinder {

  public BindingResolver(FieldRecord bindFromField) {
    super.field = bindFromField;
  }

  /*
   * Overwrites any results of previous used setBindToField() methods
   */
  public void bindWith(final Object bindTo) {
    setBindToField(bindTo);
    if(bindToField != null) {
      launchConnector();
    }
    
  }

  public void setBindToField(final Object bindTo) {
    this.bindToField = this.findFieldWithSameName(bindTo);
    if(bindToField == null) return;
    if (bindToField.isNull()) {
      throw ExceptionFactory.emptyField(bindToField.field());
    }
  }

  public void launchConnector() {
    final var connector = new BinderConnector(Objects.requireNonNull(bindToField), field);
    connector.connectProperties();
  }

  protected final FieldRecord findFieldWithSameName(final Object bindTo) {
    final var fieldName = field.getFieldName();
    try {
      final var foundField = bindTo.getClass()
          .getDeclaredField(fieldName);
      foundField.setAccessible(true);
      return new FieldRecord(foundField, bindTo);
    } catch (NoSuchFieldException e) {
      // TODO if debug = kein Feld gefunden
      return null;
    } catch (IllegalArgumentException e) {
      throw new RapidfxRuntimeException(e.getMessage());
    }
  }
}
