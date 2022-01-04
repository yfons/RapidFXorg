package de.github.yfons.rapidfx.rapidFX.core.helper.fluent;

import java.lang.annotation.Annotation;

public interface RFluentAnyAPI
{
		public interface Generator
		{
			Connector generate(Object... components);
			<OBJECT_TYPE> OBJECT_TYPE get();
		}

		public interface Connector
		{
			Connector connect(Object component, Object connectOn, Class<? extends Annotation> onAnnotation);
			<OBJECT_TYPE> OBJECT_TYPE get();
		}
	
}
