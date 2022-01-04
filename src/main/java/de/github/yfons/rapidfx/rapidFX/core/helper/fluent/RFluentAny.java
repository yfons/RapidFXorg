package de.github.yfons.rapidfx.rapidFX.core.helper.fluent;

import java.lang.annotation.Annotation;
import java.util.Objects;
import de.github.yfons.rapidfx.rapidFX.core.helper.fluent.RFluentAnyAPI.Connector;

@SuppressWarnings("unchecked")
public class RFluentAny<OBJECT_TYPE> extends RFLuentAbstract<OBJECT_TYPE> implements RFluentAnyAPI.Connector, RFluentAnyAPI.Generator
{
	public RFluentAny(OBJECT_TYPE object)
	{
		super(object);
	}

	public Connector generate(Object... components)
	{
		for (Object obj : components)
		{
			Objects.requireNonNull(obj);
		}
		setuper.setUp(components);
		return this;
	}

	@Override
	public Connector connect(Object component, Object connectOn, Class<? extends Annotation> onAnnotation)
	{
		setuper.connect(Objects.requireNonNull(component), Objects.requireNonNull(connectOn),
				Objects.requireNonNull(onAnnotation));
		return this;
	}
	public OBJECT_TYPE getObject() {
		return this.controller;
	}

}
