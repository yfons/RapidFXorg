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
 * The Interface RapidFXmodel.
 *
 * @apiNote Field must be of Type Property and not Null ( as long as
 *          {@link Rapidfx#setUp(rapidFX.interfaces.RapidFXComponent...) the
 *          Setup} was called before it won't be null) else it will crash<br>
 * @implNote During
 *           {@link Rapidfx#rapidGenerate(rapidFX.interfaces.RapidController)
 *           the Rapid Generation} all Fields tagged with this Annotation will
 *           get bound to the Field in the Model which has the same name and is
 *           also a ObjectProperty which is NOT null
 */
@Target({ ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface RapidfxModel {

}
