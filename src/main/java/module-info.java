module rapidfx
{
	requires transitive javafx.base;
	requires transitive javafx.graphics;
	requires transitive javafx.controls;

	opens de.github.yfons.rapidfx.examples.HelloWorld;
	exports de.github.yfons.rapidfx.examples.LanguageChanging;
	exports de.github.yfons.rapidfx.premade;
	exports de.github.yfons.rapidfx.rapidFX.simple;
	exports de.github.yfons.rapidfx.rapidFX.annotation;
	exports de.github.yfons.rapidfx.rapidFX.interfaces;
	exports de.github.yfons.rapidfx.rapidFX.core;

	opens de.github.yfons.rapidfx.premade;
	opens de.github.yfons.rapidfx.rapidFX.simple;
	opens de.github.yfons.rapidfx.rapidFX.annotation;
	opens de.github.yfons.rapidfx.rapidFX.interfaces;
	opens de.github.yfons.rapidfx.rapidFX.core;
}