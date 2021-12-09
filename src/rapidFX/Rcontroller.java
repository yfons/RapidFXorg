package rapidFX;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import rapidFX.interfaces.RapidFX;

/**
 * {@summary During the Initialization of a entire Controller in
 * {@link RapidFX#rapidGenerate(rapidFX.interfaces.RapidController)} or a single
 * Component {@link RapidFX#setUp(rapidFX.interfaces.RapidFXComponent...)} <br>1.
 * Gets treated as {@link RautoGenerate} and field Value = new
 * SimpleObjectProperty<>() if it's null <br> 2. Get the Field Name from the Field
 * tagged with {@link Rcontroller} <br>3. Field from the View get's taken and gets
 * bound to the Field in the Controller which has the exact same name }
 * @apiNote Field must be of Type ObjectProperty
 */
@Target({ ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface Rcontroller {

}
