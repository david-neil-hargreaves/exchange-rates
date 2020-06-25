package hsbc.util;

import java.util.ArrayList;
import java.util.List;

/**
 * Provides utility methods for strings.
 */
public class StringUtil {

  private static final String EMPTY_STRING = "";

  private StringUtil() {}

  /**
   * Determines whether or not a string is blank i.e. is null or is empty when trimmed of whitespace
   * characters.
   * 
   * @param string The string.
   * @return true if the string is null or is empty when trimmed of whitespace characters, otherwise
   *         returns false.
   */
  public static boolean isBlank(String string) {
    return string == null || string.trim().isEmpty();
  }

  /**
   * Sanitises the string by trimming off whitespace or substituting an empty string for null.
   * 
   * @param string The string.
   * @return The sanitised string.
   */
  public static String sanitise(String string) {
    if (string == null) {
      return EMPTY_STRING;
    } else {
      return string.trim();
    }
  }

  /**
   * Sanitises the string by trimming off whitespace and converting to upper case or substituting an
   * empty string for null.
   * 
   * @param string The string.
   * @return The sanitised upper-case string.
   */
  public static String sanitiseToUpperCase(String string) {
    return sanitise(string).toUpperCase();
  }

  /**
   * Sanitises a list of strings by trimming off whitespace or substituting an empty string for
   * null. If the list itself is null an empty list is returned.
   * 
   * @param strings The strings.
   * @return The sanitised string.
   */
  public static List<String> sanitiseStringList(List<String> strings) {
    if (strings == null) {
      return new ArrayList<String>();
    } else {
      List<String> sanitisedStrings = new ArrayList<>();
      for (String string : strings) {
        string = sanitise(string);
        sanitisedStrings.add(string);
      }
      return sanitisedStrings;
    }
  }

  /**
   * Sanitises a list of strings by trimming off whitespace and converting to upper case or
   * substituting an empty string for null. If the list itself is null an empty list is returned.
   * 
   * @param strings The strings.
   * @return The sanitised string.
   */
  public static List<String> sanitiseStringListToUpperCase(List<String> strings) {
    List<String> sanitisedStrings = sanitiseStringList(strings);
    List<String> sanitisedStringsToUpperCase = new ArrayList<>();
    for (String string : sanitisedStrings) {
      string = sanitiseToUpperCase(string);
      sanitisedStringsToUpperCase.add(string);
    }
    return sanitisedStringsToUpperCase;
  }

}
