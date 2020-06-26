package hsbc.util;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;
import org.junit.Test;

public class StringUtilTest {

  private static final String NOT_BLANK_STRING = "Not blank string";
  private static final String EMPTY_STRING = "";
  private static final String LINE_SEPARATOR_ONLY = System.lineSeparator();
  private static final String BLANK_STRING = "  ";
  private static final List<String> STRING_LIST = new ArrayList<String>();
  private static final String LOWER_CASE_1 = " eur ";
  private static final String UPPER_CASE_1 = "EUR";
  private static final String LOWER_CASE_2 = "Gbp";
  private static final String UPPER_CASE_2 = "GBP";
  private static final String UPPER_CASE_3 = "HKD";

  static {
    STRING_LIST.add(NOT_BLANK_STRING);
    STRING_LIST.add(EMPTY_STRING);
  }

  private static final List<String> STRING_LIST_WITH_WHITESPACE = new ArrayList<String>();

  static {
    STRING_LIST_WITH_WHITESPACE.add(NOT_BLANK_STRING);
    STRING_LIST_WITH_WHITESPACE.add(LINE_SEPARATOR_ONLY);
  }

  private static final List<String> SANITISED_STRING_LIST = new ArrayList<String>();

  static {
    SANITISED_STRING_LIST.add(NOT_BLANK_STRING);
    SANITISED_STRING_LIST.add(EMPTY_STRING);
  }

  private static final List<String> EMPTY_STRING_LIST = new ArrayList<String>();

  private static final List<String> STRING_LIST_BEFORE_UPPER_CASE_SANITISATION =
      new ArrayList<String>();

  static {
    STRING_LIST_BEFORE_UPPER_CASE_SANITISATION.add(LOWER_CASE_1);
    STRING_LIST_BEFORE_UPPER_CASE_SANITISATION.add(LOWER_CASE_2);
    STRING_LIST_BEFORE_UPPER_CASE_SANITISATION.add(UPPER_CASE_3);
  }

  private static final List<String> STRING_LIST_AFTER_UPPER_CASE_SANITISATION =
      new ArrayList<String>();

  static {
    STRING_LIST_AFTER_UPPER_CASE_SANITISATION.add(UPPER_CASE_1);
    STRING_LIST_AFTER_UPPER_CASE_SANITISATION.add(UPPER_CASE_2);
    STRING_LIST_AFTER_UPPER_CASE_SANITISATION.add(UPPER_CASE_3);
  }

  private static final String ATTRIBUTE_IS_BLANK = "Is blank?";
  private static final String ATTRIBUTE_SANITISED_STRING = "Sanitised string";
  private static final String ATTRIBUTE_SANITISED_STRINGS = "Sanitised strings";
  private static final String ATTRIBUTE_SANITISED_UPPER_CASE_STRING = "Sanitised upper-case string";
  private static final String ATTRIBUTE_SANITISED_UPPER_CASE_STRINGS =
      "Sanitised upper-case strings";

  @Test
  public void testIsBlankNullString() {
    assertEquals(ATTRIBUTE_IS_BLANK, true, StringUtil.isBlank(null));
  }

  @Test
  public void testIsBlankEmptyString() {
    assertEquals(ATTRIBUTE_IS_BLANK, true, StringUtil.isBlank(EMPTY_STRING));
  }

  @Test
  public void testIsBlankBlankString() {
    assertEquals(ATTRIBUTE_IS_BLANK, true, StringUtil.isBlank(BLANK_STRING));
  }

  @Test
  public void testIsBlankWhitespaceString() {
    assertEquals(ATTRIBUTE_IS_BLANK, true, StringUtil.isBlank(LINE_SEPARATOR_ONLY));
  }

  @Test
  public void testIsBlankNotBlankString() {
    assertEquals(ATTRIBUTE_IS_BLANK, false, StringUtil.isBlank(NOT_BLANK_STRING));
  }

  @Test
  public void testSanitiseNoChange() {
    assertEquals(ATTRIBUTE_SANITISED_STRING, NOT_BLANK_STRING,
        StringUtil.sanitise(NOT_BLANK_STRING));
  }

  @Test
  public void testSanitiseSpace() {
    assertEquals(ATTRIBUTE_SANITISED_STRING, EMPTY_STRING, StringUtil.sanitise(BLANK_STRING));
  }

  @Test
  public void testSanitiseWhiteSpace() {
    assertEquals(ATTRIBUTE_SANITISED_STRING, EMPTY_STRING,
        StringUtil.sanitise(LINE_SEPARATOR_ONLY));
  }

  @Test
  public void testSanitiseNull() {
    assertEquals(ATTRIBUTE_SANITISED_STRING, EMPTY_STRING, StringUtil.sanitise(null));
  }

  @Test
  public void testSanitiseStringListNoChange() {
    assertEquals(ATTRIBUTE_SANITISED_STRINGS, STRING_LIST,
        StringUtil.sanitiseStringList(STRING_LIST));
  }

  @Test
  public void testSanitiseStringListWhiteSpace() {
    assertEquals(ATTRIBUTE_SANITISED_STRINGS, SANITISED_STRING_LIST,
        StringUtil.sanitiseStringList(STRING_LIST_WITH_WHITESPACE));
  }

  @Test
  public void testSanitiseStringListNull() {
    assertEquals(ATTRIBUTE_SANITISED_STRINGS, EMPTY_STRING_LIST,
        StringUtil.sanitiseStringList(null));
  }

  @Test
  public void testSanitiseToUpperCase() {
    assertEquals(ATTRIBUTE_SANITISED_UPPER_CASE_STRING, UPPER_CASE_1,
        StringUtil.sanitiseToUpperCase(LOWER_CASE_1));
  }

  @Test
  public void testSanitiseStringListToUpperCase() {
    assertEquals(ATTRIBUTE_SANITISED_UPPER_CASE_STRINGS, STRING_LIST_AFTER_UPPER_CASE_SANITISATION,
        StringUtil.sanitiseStringListToUpperCase(STRING_LIST_BEFORE_UPPER_CASE_SANITISATION));
  }

}
