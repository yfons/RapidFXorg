package de.github.yfons.rapidfx.rapidFX.core.helper;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.Objects;

import de.github.yfons.rapidfx.rapidFX.core.RapidfxRuntimeException;
import javafx.beans.property.Property;
import javafx.beans.property.ReadOnlyProperty;

public record FieldRecord(Field field,Object component) {
  public FieldRecord(Field field,Object component) {
    this.field = field;
    this.component = component;
    field.setAccessible(true);
  }
  public Object getObject() {
    try {
      return field.get(component);
    } catch (IllegalArgumentException | IllegalAccessException e) {
      throw rapidIllegalAccessException(e);
    }
  }
  public boolean isAnnotationPresent(final Class<? extends Annotation> annotation) {
    return this.field.isAnnotationPresent(annotation);
  }
  public boolean isNull() {
    return getObject() == null;
  }
  public boolean isTypeOfProperty() {
    return Property.class.isAssignableFrom(this.field.getType());
  }
  public ReadOnlyProperty<?> castToReadOnlyProperty() {
    return (ReadOnlyProperty<?>) getObject();
  }
  public Property<?> castToProperty(){
    return (Property<?>)getObject();
  }
  private RapidfxRuntimeException rapidIllegalAccessException(Exception e) {
    return new RapidfxRuntimeException(
        "\nThe Field was not accessible to set a RautoGenerate value ,"
            + " probably caused by the Module-info doesn't open/exports the Package"
            + RmBuilder.name(field.getName()) + RmBuilder.clazz(field.getDeclaringClass())
            + RmBuilder.type(field.getType()) + "\n" + e.getMessage());
  }
  public String getFieldName() {
    return field.getName().intern();
  }
  public Class<? extends Field> getFieldClass() {
    return field.getClass();
  }
  public Class<?> getDeclaredClass() {
    return field.getDeclaringClass();
  }
  public Class<?> getType() {
    return field.getType();
  }
  private final Object getDefaultValue() {
    try {
      final var t = Class
          .forName(field.getType().getPackageName() + ".Simple" + field.getType().getSimpleName());
      return t.getDeclaredConstructor().newInstance();
    } catch (ClassNotFoundException | InstantiationException | IllegalAccessException
        | IllegalArgumentException | InvocationTargetException | NoSuchMethodException
        | SecurityException e) {
      throw noInstantiationPossible();
    }
  }
  private RapidfxRuntimeException noInstantiationPossible() {
    return new RapidfxRuntimeException(
        "\nDuring the try to set a default value an Exception Occured"
            + "\nIf your Object is of Type ObjectProperty "
            + "then it will get set to New SimpleXXXProperty, "
            + "only properties with Simple at the start work with this generation"
            + "\nSet the values manually to avoid that, "
            + "as only Fields with <B>value<B> null are affected"
            + "\n as the binding will still Work with any Properties "
            + "or something else went wrong while setting the default value"
            + RmBuilder.name(field.getName()) + RmBuilder.type(field.getType())
            + RmBuilder.build(field.getType().getPackageName() + " + .Simple + "
                + field.getType().getSimpleName(), "SEARCHED")
            + "\n");
  
  }
  public void setNewSimpleProperty() 
  {
    try {
      this.field.set(component, getDefaultValue());
    } catch (IllegalArgumentException | IllegalAccessException e) {
      throw rapidIllegalAccessException(e);
    }
  }
}
