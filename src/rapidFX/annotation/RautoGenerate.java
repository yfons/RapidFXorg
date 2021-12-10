package rapidFX.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import rapidFX.core.RapidFX;
/**
 * @implNote
 * During the Initialization with {@link RapidFX#setUp(rapidFX.interfaces.RapidFXComponent...) the RapidFX Setup} all Fields which are tagged with this Annotation will get Initialized with new SimpleObjectProperty<>() as long as they are <b>NULL</b><br>
* During {@link RapidFX#setUp(rapidFX.interfaces.RapidFXComponent...) the RapidFX Setup} of a view the Annotations {@link Rmodel } and {@link Rcontroller } are treated as RautoGenerate
 * @apiNote Field must be of Type ObjectProperty and Null, else it will get ignored
 */
@Target(
{ ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface RautoGenerate
{
}
