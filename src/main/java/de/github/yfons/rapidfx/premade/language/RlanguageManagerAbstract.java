package de.github.yfons.rapidfx.premade.language;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

import de.github.yfons.rapidfx.rapidFX.simple.RapidSimple;
import javafx.beans.property.StringProperty;

public abstract class RlanguageManagerAbstract extends RapidSimple{
  /**
   * Read.
   *
   * @param props      the props
   * @param fileToRead the file to read
   */
  protected final Properties readProperties(String fileName) {
    Properties props = new Properties();
    try {
      FileReader reader = new FileReader(getFile(fileName));
      props.load(reader);
      
    } catch (IOException e) {
      e.printStackTrace();
    }
    return props;
  }
  public abstract void translate(StringProperty... propertyToBindCollection);
  public abstract void swapLanguage(String newLanguage);
}
