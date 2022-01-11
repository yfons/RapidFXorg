/*
 * 
 */
package de.github.yfons.rapidfx.rapidFX.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import de.github.yfons.rapidfx.rapidFX.core.Rapidfx;

/**
 * {@summary During the Initialization all Fields which are tagged with this
 * Annotation will get Initialized with new SimpleXXXProperty<>() as long as
 * they are NULL.}
 *
 * @apiNote Field must be of Type ObjectProperty and Null, else it will get
 *          ignored
 */
@Target({ ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface RapidfxAutoGenerate {
}
