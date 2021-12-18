package de.github.yfons.rapidfx.premade;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Locale;
import java.util.Properties;
import java.util.Set;

import de.github.yfons.rapidfx.rapidFX.interfaces.RapidFXComponent;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;

/**
 * @Deprecated doesnt work yet
 * @author marti
 *
 * @param <KEYS>
 */

public abstract class RLanguageManager implements RapidFXComponent
{
	File supportedLanguagesFile;
	private StringProperty language = new SimpleStringProperty("");
	Properties supportedLanguages = new Properties();
	FileReader reader;

	HashMap<String, StringProperty> languageKeys = new HashMap<>();

	final String hardCodedLanguageDefault;

	public RLanguageManager(String supportLanguages, String hardCodedLanguageDefault, String languageLayout)
	{
		this.hardCodedLanguageDefault = hardCodedLanguageDefault;

		setSupportedLanguages(supportLanguages);

		setFormat(languageLayout);

		language.addListener(languageListener);

		swapToDefault();

	}

	private final void setSupportedLanguages(String supportLanguages)
	{
		supportedLanguagesFile = getFile(supportLanguages);

		read(supportedLanguages);
	}

	private final void setFormat(String languageLayout)
	{
		try
		{
			setLanguageFormat(languageLayout);
		} catch (IOException e)
		{
			e.printStackTrace();
		}
	}

	public final void swapToDefault()
	{
		var hasDefault = supportedLanguages.get("DEFAULT");
		String languageToSwap = hasDefault != null ? (String) hasDefault : hardCodedLanguageDefault;
		swapLanguage(languageToSwap);

	}

	private void setLanguageFormat(String format) throws IOException
	{
		File languageFormat = getFile(format);
		Properties formatProperty = new Properties();
		FileReader reader = new FileReader(languageFormat);
		formatProperty.load(reader);
		Iterator<Object> it = formatProperty.keys().asIterator();
		while (it.hasNext())
		{
			String key = (String) it.next();
			languageKeys.put(key, new SimpleStringProperty());
		}
		System.out.println(languageKeys);
	}

	private final ChangeListener<String> languageListener = new ChangeListener<>()
	{

		@Override
		public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue)
		{

			if (newValue != null)
			{

				Properties formatProperty = new Properties();
				try
				{
					FileReader reader = new FileReader(getFile(newValue));

					formatProperty.load(reader);
					languageKeys.forEach((key, item) -> {
						if (formatProperty.containsKey(key))
						{
							item.set(formatProperty.getProperty(key));
						} else
						{
							System.err.println("KEY: " + key + " :is Absent in " + getFile(newValue));
							System.exit(-1);
						}
					});
				} catch (FileNotFoundException e)
				{
					e.printStackTrace();
				} catch (IOException e)
				{
					e.printStackTrace();
				}
			}
		}
	};

	public void swapLanguage(String newLanguage)
	{
		if (supportedLanguages.containsKey(newLanguage))
		{
			supportedLanguages.putIfAbsent("DEFAULT", newLanguage);
			supportedLanguages.put("DEFAULT", newLanguage);
			try
			{
				supportedLanguages.store(new FileWriter(supportedLanguagesFile), "Hello");
			} catch (IOException e)
			{
				e.printStackTrace();
			}
			language.set((String) supportedLanguages.get(newLanguage));
		}
	}

	private final File getFile(String fileName)
	{
		URI url;
		try
		{
			url = new URI(getResource(fileName));
			return new File(url);
		} catch (URISyntaxException e)
		{
			e.printStackTrace();
			return null;
		}
	}

	private final void read(Properties props)
	{
		try
		{
			FileReader reader = new FileReader(supportedLanguagesFile);
			props.load(reader);
		} catch (IOException e)
		{
			e.printStackTrace();
		}
	}

	public final StringProperty getProperty(String key)
	{
		return languageKeys.get(key);
	}
}
