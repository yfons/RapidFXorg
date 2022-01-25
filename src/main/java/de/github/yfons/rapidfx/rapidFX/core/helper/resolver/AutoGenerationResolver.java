package de.github.yfons.rapidfx.rapidFX.core.helper.resolver;

import de.github.yfons.rapidfx.rapidFX.core.helper.FieldRecord;
import de.github.yfons.rapidfx.rapidFX.core.helper.Exceptions.ExceptionFactory;
import de.github.yfons.rapidfx.rapidFX.core.helper.resolver.abstracts.AbstractResolver;

public class AutoGenerationResolver extends AbstractResolver {
  public AutoGenerationResolver(FieldRecord field) {
    super.field = field;
  }

  /**
   * Sets the default value for a Property, which is SimpleXXXProperty.
   */
  public void setDefaultProperty() {
    if (!field.isTypeOfProperty()) {
      throw ExceptionFactory.castToProperty(field);
    }
    if (field.isNull()) {
      field.setNewSimpleProperty();
    }
  }

}
