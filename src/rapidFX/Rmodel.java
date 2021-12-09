package rapidFX;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Attributes Tagged with this Annotation in the View will get Bound to the
 * model Binding is based on Attribute name which is searched in the model
 * <a>{@link rapidFX.interfaces.RapidController#rapidGenerate(rapidFX.interfaces.RapidController)}</a>
 * 
 * @author marti
 *
 */
@Target(
{ ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface Rmodel
{
	
}
