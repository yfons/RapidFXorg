package de.github.yfons.rapidfx.premade.language;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Properties;

import de.github.yfons.rapidfx.rapidFX.core.RapidFXRuntimeException;
import de.github.yfons.rapidfx.rapidFX.interfaces.RapidFXComponent;
import de.github.yfons.rapidfx.rapidFX.simple.RapidSimple;
import javafx.beans.binding.Bindings;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;

public abstract class RLanguageManager extends RapidSimple
{
	private File supportedLanguagesFile;
	private final StringProperty language = new SimpleStringProperty("");
	private final Properties supportedLanguages = new Properties();

	private final HashMap<String, StringProperty> languageKeys = new HashMap<>();

	final String hardCodedLanguageDefault;

	public RLanguageManager(String supportLanguages, String hardCodedLanguageDefault, String languageLayout)
	{
		this.hardCodedLanguageDefault = hardCodedLanguageDefault;

		setSupportedLanguages(supportLanguages);

		setFormat(languageLayout);

		language.addListener(languageListener);

		swapToDefault();
	}

	public final void translate(StringProperty... propertyToBindCollection)
	{
		for (var propertyToBind : propertyToBindCollection)
		{
			propertyToBind.bind(languageKeys.get(propertyToBind.get()));
		}
	}

	public final void swapLanguage(String newLanguage)
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

	private final void swapToDefault()
	{
		var hasDefault = supportedLanguages.get("DEFAULT");
		String languageToSwap = hasDefault != null ? (String) hasDefault : hardCodedLanguageDefault;
		swapLanguage(languageToSwap);
	}

	private final void setSupportedLanguages(String supportLanguages)
	{
		supportedLanguagesFile = getFile(supportLanguages);
		read(supportedLanguages, supportedLanguagesFile);
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

	private final void setLanguageFormat(String format) throws IOException
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
	}

	private final ChangeListener<String> languageListener = new ChangeListener<>()
	{

		@Override
		public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue)
		{

			if (newValue != null)
			{
				Properties formatProperty = new Properties();
				read(formatProperty, getFile(newValue));

				languageKeys.forEach((key, item) -> {
					if (formatProperty.containsKey(key))
					{
						item.set(formatProperty.getProperty(key));
					} else
					{
						throw new RapidFXRuntimeException(
								"The key is Absent in the File " 
								+ "\n\t=> KEY => " + key
								+ "\n\t=> FILE => " + getFile(newValue));
					}
				});
			}

		}
	};

	private final File getFile(String fileName)
	{
		URI uri;
		try
		{
			uri = getResourceURI(fileName).toURI();
			return new File(uri);
		} catch (URISyntaxException e)
		{
			throw new RapidFXRuntimeException(
					"During parsing the File Location to a URI/URL an Exception occured"
					+ "\n\t=> NAME => " + fileName 
					+ "\n\t => PATH => " + this.getClass());
		}
	}

	private final void read(Properties props, File fileToRead)
	{
		try
		{
			FileReader reader = new FileReader(fileToRead);
			props.load(reader);
		} catch (IOException e)
		{
			e.printStackTrace();
		}
	}

	public String toString()
	{
		return "Language Manager"
				+ "\n\t=> CLASS => " + this.getClass() 
				+ "\n\t=> HARD_CODED_DEFAULT => " + this.hardCodedLanguageDefault 
				+ "\n\t=> CURRENT_LANGUAGE => " + this.language.get() 
				+ "\n\t=> SUPPORTED_FILE => " + supportedLanguagesFile 
				+ "\n\t=> SUPPORTED_PROPERTIES => " + supportedLanguages 
				+ "\n\t=> LAYOUT_KEYS => " + languageKeys.keySet() 
				+ "\n\t=> LAYOUT_MAP => " + languageKeys.toString()+"\n";
	}
}
