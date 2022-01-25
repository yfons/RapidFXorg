package de.github.yfons.rapidfx.rapidFX.core.helper.resolver.abstracts;

import java.lang.annotation.Annotation;

import de.github.yfons.rapidfx.rapidFX.core.helper.FieldRecord;
import de.github.yfons.rapidfx.rapidFX.simple.RapidSimple;

public abstract class AbstractResolver extends RapidSimple {
  protected FieldRecord field;

  public boolean isAnnotationPresent(Class<? extends Annotation> annotation) {
    return field.isAnnotationPresent(annotation);
  }
}
