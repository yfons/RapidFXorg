package de.github.yfons.rapidfx.rapidFX.core.helper.fluent;

import java.lang.annotation.Annotation;
import java.util.Objects;

import de.github.yfons.rapidfx.rapidFX.annotation.RapidFXcontroller;
import de.github.yfons.rapidfx.rapidFX.core.helper.fluent.RFluentControllerAPI.Connector;
import de.github.yfons.rapidfx.rapidFX.interfaces.RapidController;

@SuppressWarnings("unchecked")
public class RFluentController<CONTROLLER_TYPE extends RapidController> extends RFLuentAbstract<CONTROLLER_TYPE>
		implements RFluentControllerAPI.Generator, RFluentControllerAPI.Connector
{
	public RFluentController(CONTROLLER_TYPE object)
	{
		super(object);
	}

	public Connector generate(Object... components)
	{
		for ( Object obj : components) {
			Objects.requireNonNull(obj);
		}
		setuper.setUp(components);
		return this;
	}

	public Connector connectWithController(Object component)
	{
		return connect(Objects.requireNonNull(component), this.controller, RapidFXcontroller.class);
	}

	@Override
	public Connector connect(Object component, Object connectOn, Class<? extends Annotation> onAnnotation)
	{
		setuper.connect(Objects.requireNonNull(component), Objects.requireNonNull(connectOn),
				Objects.requireNonNull(onAnnotation));
		return this;
	}

}
