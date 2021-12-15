module rapidfx
{
	requires javafx.base;
	requires transitive javafx.graphics;
	requires javafx.controls;
	opens examples.HelloWorld;
	exports premade;
	exports rapidFX.simple;
	exports rapidFX.annotation;
	exports rapidFX.interfaces;
	exports rapidFX.core;
}