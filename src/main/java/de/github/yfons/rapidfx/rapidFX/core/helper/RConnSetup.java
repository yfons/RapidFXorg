/*
 * 
 */
package de.github.yfons.rapidfx.rapidFX.core.helper;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.Random;

import de.github.yfons.rapidfx.rapidFX.annotation.RapidfxAutoGenerate;
import de.github.yfons.rapidfx.rapidFX.annotation.RapidfxController;
import de.github.yfons.rapidfx.rapidFX.annotation.RapidfxModel;
import de.github.yfons.rapidfx.rapidFX.core.Rapidfx;
import de.github.yfons.rapidfx.rapidFX.interfaces.RapidController;

/**
 * The Class RConnSetup is used to generate and resolve the Rapid Annotations
 * e.g. {@link RapidfxAutoGenerate} and {@link RapidfxController} and
 * {@link RapidfxModel}.
 */
public class RConnSetup {
  protected static final Class<RapidfxAutoGenerate> AUTO_GENERATION    = RapidfxAutoGenerate.class;
  protected static final Class<RapidfxController>   VIEW_TO_CONTROLLER = RapidfxController.class;
  protected static final Class<RapidfxModel>        VIEW_TO_MODEL      = RapidfxModel.class;

  /**
   * {@summary all ObjectProperty which are tagged with RautoGenerate will get the
   * default value of "new SimpleXXXProperty<>".}
   *
   * @implNote Unit Testing this Method can be used only on a Single Object to see
   *           if its fully Functional, this should be the case for the Controller
   *           and the Model.
   *
   * @param toSetUpObjects Collection that need to Initialize all
   *                       RapifxAutoGenerate annotated fields.
   */
  public void setUp(Object... toSetUpObjects) {

    for (var toSetUp : toSetUpObjects) {

      final Field[] fields = toSetUp.getClass().getDeclaredFields();

      for (var field : fields) {
        final var fieldHandler = new FieldHandler(new FieldRecord(field,toSetUp));

        if (fieldHandler.isAnnotationPresent(AUTO_GENERATION)) {
          fieldHandler.setDefaultProperty();
        }
      }
    }
  }

  /**
   * {@summary Connects all fields in the first Object which are Tagged with the
   * Annotation with Fields in the second Object which have the same Name.}
   *
   * @param bindFrom   the Object where the Fields with the Annotation get
   *                   searched
   * @param bindTo     the Object where the Fields with the same name get searched
   * @param annotation the annotation which should be found in the bindFrom Object
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
   */
  public void connect(Object bindFrom, Object bindTo, Class<? extends Annotation> annotation) {
    final var bindFromFields = bindFrom.getClass().getDeclaredFields();

    for (Field bindFromField : bindFromFields) {
      final FieldHandler fieldHandler = new FieldHandler(new FieldRecord(bindFromField, bindFrom));

      if (fieldHandler.isAnnotationPresent(annotation)) {
        fieldHandler.bindWith(bindTo);
      }
    }
  }
}
