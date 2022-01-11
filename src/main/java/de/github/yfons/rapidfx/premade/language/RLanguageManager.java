/*
 * 
 */
package de.github.yfons.rapidfx.premade.language;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Objects;
import java.util.Properties;

import de.github.yfons.rapidfx.rapidFX.core.RapidfxRuntimeException;
import de.github.yfons.rapidfx.rapidFX.core.helper.RmBuilder;
import de.github.yfons.rapidfx.rapidFX.simple.RapidSimple;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;

/**
 * The Class RLanguageManager.
 */
public abstract class RLanguageManager extends RlanguageManagerAbstract {

  protected final File supportedLanguagesFile;
  protected final StringProperty language = new SimpleStringProperty("");

  protected Properties supportedLanguages = new Properties();

  protected final HashMap<String, StringProperty> languageKeys = new HashMap<>();

  /** The hard coded language default. */
  protected final String hardCodedLanguageDefault;

  /**
   * Instantiates a new r language manager.
   *
   * @param supportLanguages         the support languages
   * @param hardCodedLanguageDefault the hard coded language default
   * @param languageLayout           the language layout
   */
  public RLanguageManager(String supportLanguages, String hardCodedLanguageDefault,
      String languageLayout) {
    supportedLanguagesFile = getFile(supportLanguages);
    this.hardCodedLanguageDefault = hardCodedLanguageDefault;

    setSupportedLanguages(supportLanguages);
    setFormat(languageLayout);

    language.addListener(languageListener);

    swapToDefault();
  }

  /**
   * Translate.
   *
   * @param propertyToBindCollection the property to bind collection
   */
  public final void translate(StringProperty... propertyToBindCollection) {
    for (var propertyToBind : propertyToBindCollection) {
      propertyToBind.bind(languageKeys.get(propertyToBind.get()));
    }
  }

  /**
   * Swap language.
   *
   * @param newLanguage the new language
   */
  public final void swapLanguage(String newLanguage) {
    if (supportedLanguages.containsKey(newLanguage)) {
      supportedLanguages.put("DEFAULT", newLanguage);
      storeSupportedLanguagesWithDefaultKey();
      language.set((String) supportedLanguages.get(newLanguage));
    }
  }

  private void storeSupportedLanguagesWithDefaultKey() {
    try {
      supportedLanguages.store(new FileWriter(supportedLanguagesFile),
          "\\u0020 for Spaces, and \\u003d for =");
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  /**
   * Swap to default.
   */
  private final void swapToDefault() {
    var    hasDefault     = supportedLanguages.get("DEFAULT");
    String languageToSwap = hasDefault != null ? (String) hasDefault : hardCodedLanguageDefault;
    swapLanguage(languageToSwap);
  }

  /**
   * Sets the supported languages.
   *
   * @param supportLanguages the new supported languages
   */
  private final void setSupportedLanguages(String supportLanguages) { 
    supportedLanguages = readProperties(supportLanguages);
  }

  /**
   * Sets the format.
   *
   * @param languageLayout the new format
   */
  private final void setFormat(String languageLayout) {
    Objects.requireNonNull(languageLayout);
    try {
      setLanguageFormat(languageLayout);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  /**
   * Sets the language format.
   *
   * @param format the new language format
   * @throws IOException Signals that an I/O exception has occurred.
   */
  private final void setLanguageFormat(String format) throws IOException {
    File       languageFormat = getFile(format);
    Properties formatProperty = new Properties();
    FileReader reader         = new FileReader(languageFormat);
    setLanguageKeyProperties(formatProperty, reader);
  }

  private void setLanguageKeyProperties(Properties formatProperty, FileReader reader)
      throws IOException {
    formatProperty.load(reader);
    Iterator<Object> it = formatProperty.keys().asIterator();
    while (it.hasNext()) {
      String key = (String) it.next();
      languageKeys.put(key, new SimpleStringProperty());
    }
  }

  /** The language listener. */
  private final ChangeListener<String> languageListener = new ChangeListener<>() {

    @Override
    public void changed(ObservableValue<? extends String> observable, String oldValue,
        String newValue) {

      if (newValue != null) {
        Properties formatProperty = readProperties(newValue);
        
        languageKeys.forEach((key, item) -> {
          setNewPropertyValue(formatProperty, key, item);
        });
      }

    }

    private void setNewPropertyValue(Properties formatProperty, String key, StringProperty item) {
      if (formatProperty.containsKey(key)) {
        item.set(formatProperty.getProperty(key));
      } else {
        item.set(key);
      }
    }
  };




  /**
   * To string.
   *
   * @return the string
   */
  @Override
  public String toString() {
    return "Language Manager" + RmBuilder.clazz(this.getClass())
        + RmBuilder.build(this.hardCodedLanguageDefault, "HARD_CODED_DEFAULT")
        + RmBuilder.build(this.language.get(), "CURRENT_LANGUAGE")
        + RmBuilder.build(supportedLanguagesFile, "SUPPORTED_FILE")
        + RmBuilder.build(supportedLanguages, "SUPPORTED_PROPERTIES")
        + RmBuilder.build(languageKeys.keySet(), "LAYOUT_KEYS")
        + RmBuilder.build(languageKeys.toString(), "LAYOUT_MAP") + "\n";
  }
}
