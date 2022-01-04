package de.github.yfons.rapidfx.rapidFX.core.helper.fluent;

import java.lang.annotation.Annotation;

import de.github.yfons.rapidfx.rapidFX.interfaces.RapidController;

public interface RFluentControllerAPI
{
	public interface Generator
	{
		Connector generate(Object... components);

		RapidController get();
	}

	public interface Connector
	{
		Connector connect(Object component, Object connectOn, Class<? extends Annotation> onAnnotation);

		Connector connectWithController(Object component);

		<CONTROLLER_TYPE extends RapidController>CONTROLLER_TYPE get();
	}
}