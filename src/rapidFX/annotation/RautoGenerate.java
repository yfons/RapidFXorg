package rapidFX.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import rapidFX.interfaces.RapidFX;
/**
 * During the Initialization of a entire Controller in {@link RapidFX#rapidGenerate(rapidFX.interfaces.RapidController)} or a single Component {@link RapidFX#setUp(rapidFX.interfaces.RapidFXComponent...)}
 * 1. RautoGenerate tagged Fields will get the Value = new SimpleObjectProperty<>() if they are null
 * 2. Rmodel and Rcontroller get Treated in {@link RapidFX#setUp(rapidFX.interfaces.RapidFXComponent...)} as RautoGenerate Annotation
 * @apiNote Field must be of Type ObjectProperty
 */
@Target(
{ ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface RautoGenerate
{
}
