package de.github.yfons.rapidfx.rapidFX.core.helper.Exceptions;

import java.lang.reflect.Field;

import de.github.yfons.rapidfx.rapidFX.core.RapidfxRuntimeException;
import de.github.yfons.rapidfx.rapidFX.core.helper.FieldRecord;
import de.github.yfons.rapidfx.rapidFX.core.helper.RmBuilder;

public class ExceptionFactory {

  public static final RapidfxRuntimeException castToProperty(final FieldRecord field) {
    return new RapidfxRuntimeException(
        "\nCouldn't cast the Field to a Property" + RmBuilder.name(field.getFieldName())
            + RmBuilder.clazz(field.getDeclaredClass()) + RmBuilder.type(field.getType()));
  }

  public static final RapidfxRuntimeException noFieldFound(final Object bindTo,
      final String fieldName, Class<?> expectedType) {
    return new RapidfxRuntimeException("\nThe Field was not found"
        + "\nThis can be caused when the Module-Info doesn't \"open PACKAGENAME\" "
        + "where the Class is Part of" + RmBuilder.name(fieldName)
        + RmBuilder.clazz(bindTo.getClass()) + RmBuilder.build(expectedType, "EXPECTED_TYPE")
        + "\n");
  }

  public static final RapidfxRuntimeException emptyField(final Field field) {
    return new RapidfxRuntimeException(
        "\nThe Value of the Field which should be a Property/EventHandler/ChangeListener is null,"
            + " can't bind with null values" + RmBuilder.name(field.getName())
            + RmBuilder.clazz(field.getDeclaringClass()) + RmBuilder.type(field.getType()) + "\n");
  }

  public static final RapidfxRuntimeException incompatiblePropertiesErrorMessage(
      final FieldRecord bindTo, FieldRecord field) {
    return new RapidfxRuntimeException(
        "\nThe Field is not  A EventHandler or A ChangeListener or an Assignable Property"
            + RmBuilder.name(bindTo.getFieldName()) + RmBuilder.clazz(bindTo.getDeclaredClass())
            + RmBuilder.type(bindTo.getType()) + RmBuilder.build(field.getType(), "EXPECTED_TYPE")
            + RmBuilder.build(field.getFieldName(), "BASED_ON_FIELD")
            + RmBuilder.build(field.getDeclaredClass(), "BASED_ON_CLASS"));
  }

}
