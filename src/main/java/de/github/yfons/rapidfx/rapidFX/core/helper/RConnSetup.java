package de.github.yfons.rapidfx.rapidFX.core.helper;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;

import de.github.yfons.rapidfx.rapidFX.core.RapidFX;
import de.github.yfons.rapidfx.rapidFX.interfaces.RapidController;

public class RConnSetup
{
	/**
	 * all ObjectProperty which are tagged with RautoGenerate will get the default
	 * value of "new SimpleObjectProperty<>" <br>
	 * Rmodel and Rcontroller are only allowed in Classes which implement the
	 * RapidView the System will stop if this Rule is violated<br>
	 * <br>
	 * Should only be used on Classes which are not affected by the
	 * {@link RapidFX#rapidGenerate(RapidController) rapidGeneration} but still want
	 * the {@link RapidFX#setUp(RapidFXComponent...) auto Generation } for
	 * Initializing their Fields <br>
	 * for Unit Testing this Method can be used only on a Single Object to see if
	 * its fully Functional, this should be the case for the Controller and the
	 * Model
	 *
	 * @param toSetUpObjects
	 */
	public void setUp(Object... toSetUpObjects)
	{

		for (var toSetUp : toSetUpObjects)
		{

			final Field[] fields = toSetUp.getClass().getDeclaredFields();

			for (var field : fields)
			{
				final var fieldHandler = new FieldHandler<>(field, toSetUp);

				if (fieldHandler.isAnnotationPresent(RapidFX.AUTO_GENERATION_ANNOTATION))
				{
					fieldHandler.setDefaultValue();
				}
			}
		}
	}

	/**
	 * Connects all fields in the View which are Tagged as Rmodel with the model
	 * Connects all fields in the View which are Tagged as Rcontroller with the
	 * controller
	 *
	 * @apiNote the restriction to connect only Rcontroller or Rmodel to a
	 *          controller or Model is not set. The RapidFXComponent can be here Any
	 *          RapidFXComponent<br>
	 *          in theory you can tag ObjectProperty Fields in your view with any
	 *          annotation and bind them to any other Class as it's based on
	 *          searching Fields with the given annotation and searching in the
	 *          bindTo Object the Fields with the same Name <br>
	 *          this can be used to test only certain parts by changing the
	 *          Annotations, but in the Real Application RapidFX.rapidGenerate()
	 *          should be used to set the bindings and connecting the view
	 * @param bindFrom
	 * @param bindTo
	 * @param annotation
	 */
	public void connect(final Object bindFrom, final Object bindTo, final Class<? extends Annotation> annotation)
	{
		final var bindFromFields = bindFrom.getClass().getDeclaredFields();

		for (Field bindFromField : bindFromFields)
		{
			final FieldHandler<?> fieldHandler = new FieldHandler<>(bindFromField, bindFrom);

			if (fieldHandler.isAnnotationPresent(annotation))
			{
				fieldHandler.bindProperties(bindTo);
			}
		}
	}
}
