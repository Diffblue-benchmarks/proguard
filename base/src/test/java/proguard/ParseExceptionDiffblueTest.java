package proguard;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import org.junit.jupiter.api.Test;

class ParseExceptionDiffblueTest {
  /**
   * Method under test: {@link ParseException#ParseException()}
   */
  @Test
  void testNewParseException() {
    // Arrange and Act
    ParseException actualParseException = new ParseException();

    // Assert
    assertNull(actualParseException.getMessage());
    assertNull(actualParseException.getCause());
    assertEquals(0, actualParseException.getSuppressed().length);
  }

  /**
   * Method under test: {@link ParseException#ParseException(String)}
   */
  @Test
  void testNewParseException2() {
    // Arrange and Act
    ParseException actualParseException = new ParseException("foo");

    // Assert
    assertEquals("foo", actualParseException.getMessage());
    assertNull(actualParseException.getCause());
    assertEquals(0, actualParseException.getSuppressed().length);
  }
}
