package de.github.yfons.rapidfx.premade.language;

import javafx.beans.property.StringProperty;

public class RTranslator
{
	private static RLanguageManager manager;

	public static void injectLanguageManager(RLanguageManager manager)
	{
		RTranslator.manager = manager;
	}

	public static void bindTranslation(StringProperty... propertyToBindCollection)
	{
		manager.translate(propertyToBindCollection);
	}

	public static void swapLanguages(String language)
	{
		manager.swapLanguage(language);
	}

	/**
	 * Meant to be for Debugging Purposes when you can't reach the Manager
	 * afterwards
	 */
	public void printManager()
	{
		System.out.println(manager.toString());
	}
}
