module de.github.yfons.rapidfx
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
	exports  de.github.yfons.rapidfx.premade.language;
	exports de.github.yfons.rapidfx.examples.viewManaging;
	exports de.github.yfons.rapidfx.examples.viewManaging.helloWorldPackage;
	exports de.github.yfons.rapidfx.rapidFX.core.helper;
	opens de.github.yfons.rapidfx.premade.language;
	opens de.github.yfons.rapidfx.premade;
	opens de.github.yfons.rapidfx.rapidFX.simple;
	opens de.github.yfons.rapidfx.rapidFX.annotation;
	opens de.github.yfons.rapidfx.rapidFX.interfaces;
	opens de.github.yfons.rapidfx.rapidFX.core;
}