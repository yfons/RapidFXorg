package de.github.yfons.rapidfx.examples.LanguageChanging;

import de.github.yfons.rapidfx.premade.language.RLanguageManager;

/*
 *  This class extends the Rlanguagemanager so the Language Files will get searched in this directory with
 *  getClass.getResource("xyz").toString()
 *  -> nothing to override here just the class path is set to your current package
 *  -> the static final keys for the Language Files are optional to Write
 */
public class LanguageManger extends RLanguageManager
{
	public LanguageManger(String supportLanguages, String hardCodedLanguageDefault, String languageLayout)
	{
		super(supportLanguages, hardCodedLanguageDefault, languageLayout);
	}

	public static final String GERMAN = "GERMAN";
	public static final String ENGLISH = "ENGLISH";
}
