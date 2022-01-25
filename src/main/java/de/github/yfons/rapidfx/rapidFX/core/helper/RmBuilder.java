package de.github.yfons.rapidfx.rapidFX.core.helper;

/**
 * Can be used for printing lines in the Format "\\n\\t=> INFO => information".
 */
public class RmBuilder {
  private enum ExcType {
    CLASS, TYPE, NAME;
  }

  /**
   * Builds the String in the Format "\\n\\t=> info => information".
   *
   * @return the result String
   */
  public static String build(Object information, String info) {
    return "\n\t=> " + info + " => " + information.toString();
  }

  /**
   * Builds the String in the Format "\\n\\t=> CLASS => information".
   *
   * @return the result String
   */
  public static String clazz(Object information) {
    return build(information, ExcType.CLASS.toString());
  }

  /**
   * Builds the String in the Format "\\n\\t=> TYPE => information".
   *
   * @return the result String
   */
  public static String type(Object information) {
    return build(information, ExcType.TYPE.toString());
  }

  /**
   * Builds the String in the Format "\\n\\t=> NAME => information".
   *
   * @return the result String
   */
  public static String name(Object information) {
    return build(information, ExcType.NAME.toString());
  }
}
