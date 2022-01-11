/*
 * 
 */
package de.github.yfons.rapidfx.premade.language;

import javafx.beans.property.StringProperty;

/**
 * The Class RTranslator.
 */
public class RTranslator {

  /** The manager. */
  private static RLanguageManager manager;

  /**
   * Inject language manager.
   *
   * @param manager the manager
   */
  public static void injectLanguageManager(RLanguageManager manager) {
    RTranslator.manager = manager;
  }

  /**
   * Bind translation.
   *
   * @param propertyToBindCollection the property to bind collection
   */
  public static void bindTranslation(StringProperty... propertyToBindCollection) {
    manager.translate(propertyToBindCollection);
  }

  /**
   * Swap languages.
   *
   * @param language the language
   */
  public static void swapLanguages(String language) {
    manager.swapLanguage(language);
  }

  /**
   * Meant to be for Debugging Purposes when you can't reach the Manager
   * afterwards.
   */
  public void printManager() {
    System.out.println(manager.toString());
  }
}
