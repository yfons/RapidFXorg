package de.github.yfons.rapidfx.examples.LanguageChanging;

import de.github.yfons.rapidfx.premade.RLanguageManager;
import javafx.beans.property.StringProperty;

public class LanguageManger extends RLanguageManager
{
	private static LanguageManger manager = new LanguageManger("LANGUAGES.properties", "ENGLISH", "LANGUAGE.layout");

	public LanguageManger(String supportLanguages, String hardCodedLanguageDefault, String languageLayout)
	{
		super(supportLanguages, hardCodedLanguageDefault, languageLayout);
	}

	public static void bindToKey(String key, StringProperty propertyToBind)
	{
		propertyToBind.bind(manager.getProperty(key));
	}

	public static void swapLanguages(String language)
	{
		manager.swapLanguage(language);
	}

	public static final String GERMAN = "GERMAN";
	public static final String ENGLISH = "ENGLISH";
	public static final String BUTTON_NAVIGATION_GREETING = "BUTTON_NAVIGATION_GREETING";
	public static final String BUTTON_NAVIGATION_EXIT = "BUTTON_NAVIGATION_EXIT";
}
