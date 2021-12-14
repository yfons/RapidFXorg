module scdfg
{
	requires transitive javafx.graphics;
	requires transitive javafx.controls;
	requires javafx.base;

	exports rapidFX.annotation;
	exports rapidFX.core;
	exports rapidFX.interfaces;
	exports rapidFX.simple;
	exports examples.HelloWorld;
	opens examples.HelloWorld;
}
