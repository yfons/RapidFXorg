package de.github.yfons.rapidfx.rapidFX.core;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.function.Supplier;

import de.github.yfons.rapidfx.rapidFX.annotation.RapidFXautoGenerate;
import de.github.yfons.rapidfx.rapidFX.annotation.RapidFXcontroller;
import de.github.yfons.rapidfx.rapidFX.annotation.RapidFXmodel;
import de.github.yfons.rapidfx.rapidFX.core.helper.FieldHandler;
import de.github.yfons.rapidfx.rapidFX.core.helper.RCreator;
import de.github.yfons.rapidfx.rapidFX.interfaces.RapidController;
import de.github.yfons.rapidfx.rapidFX.interfaces.RapidFXComponent;
import de.github.yfons.rapidfx.rapidFX.interfaces.RapidView;

public class RapidFX
{
	private static final Class<RapidFXautoGenerate> AUTO_GENERATION_ANNOTATION = RapidFXautoGenerate.class;
	private static final Class<RapidFXcontroller> VIEW_TO_CONTROLLER_ANNOTATION = RapidFXcontroller.class;
	private static final Class<RapidFXmodel> VIEW_TO_MODEL_ANNOTATION = RapidFXmodel.class;

	/**
	 * Should be used to create new Instances instead of new XYZ() as it makes it
	 * easier to switch out These Objects<br>
	 * Setting the variables through an external method is recommended the
	 * constructor should only instantiate the Objects
	 * 
	 * @param <CLASS_FOR_NEW_INSTANCE> eg MyController::new
	 * @param constructor
	 * @return
	 * @throws RapidFXException
	 */
	public static <CLASS_FOR_NEW_INSTANCE extends RapidFXComponent> CLASS_FOR_NEW_INSTANCE create(
			final Supplier<CLASS_FOR_NEW_INSTANCE> constructor)
	{
		return new RCreator<CLASS_FOR_NEW_INSTANCE>(constructor).create();
	}

	/**
	 * @implNote the Setup and Connect Methods will get Called properly for the
	 *           Controller and the View and the Model so it's not necessary to call
	 *           them {@summary Takes from the Controller the View and the Model and
	 *           RautoGenerates all Attributes + binds the Properties from the View}
	 * @apiNote Watch {@link RapidFX#connect(RapidView, RapidFXComponent, Class)
	 *          connect} and {@link setUp} for more Information
	 * @param controller
	 * @return
	 */
	public static <controllClass extends RapidController> controllClass rapidGenerate(final controllClass controller)
	{
		final var model = controller.getModel();
		final var view = controller.getView();

		setUp(model, controller, view);

		connect(view, controller, VIEW_TO_CONTROLLER_ANNOTATION);
		connect(view, model, VIEW_TO_MODEL_ANNOTATION);

		return controller;
	}

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
	public static void setUp(RapidFXComponent... toSetUpObjects)
	{
		for (var toSetUp : toSetUpObjects)
		{
			final Field[] fields = toSetUp.getClass().getDeclaredFields();

			for (var field : fields)
			{
				final var fieldHandler = new FieldHandler<>(field, toSetUp);

				if (fieldHandler.isAnnotationPresent(AUTO_GENERATION_ANNOTATION))
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
	 * @param view
	 * @param bindTo
	 * @param annotation
	 */
	public static void connect(final RapidView<?> view, final RapidFXComponent bindTo,
			final Class<? extends Annotation> annotation)
	{
		final var viewFields = view.getClass().getDeclaredFields();

		for (Field viewField : viewFields)
		{
			final FieldHandler<?> fieldHandler = new FieldHandler<>(viewField, view);

			if (fieldHandler.isAnnotationPresent(annotation))
			{
				fieldHandler.bindProperties(bindTo);
			}
		}
	}
}
