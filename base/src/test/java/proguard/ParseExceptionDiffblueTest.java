package proguard;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import com.diffblue.cover.annotations.MethodsUnderTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

class ParseExceptionDiffblueTest {
  /**
   * Test {@link ParseException#ParseException()}.
   * <ul>
   *   <li>Then return Message is {@code null}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ParseException#ParseException()}
   */
  @Test
  @DisplayName("Test new ParseException(); then return Message is 'null'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void proguard.ParseException.<init>()", "void proguard.ParseException.<init>(java.lang.String)"})
  void testNewParseException_thenReturnMessageIsNull() {
    // Arrange and Act
    ParseException actualParseException = new ParseException();

    // Assert
    assertNull(actualParseException.getMessage());
    assertNull(actualParseException.getCause());
    assertEquals(0, actualParseException.getSuppressed().length);
  }

  /**
   * Test {@link ParseException#ParseException(String)}.
   * <ul>
   *   <li>When {@code foo}.</li>
   *   <li>Then return Message is {@code foo}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ParseException#ParseException(String)}
   */
  @Test
  @DisplayName("Test new ParseException(String); when 'foo'; then return Message is 'foo'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void proguard.ParseException.<init>()", "void proguard.ParseException.<init>(java.lang.String)"})
  void testNewParseException_whenFoo_thenReturnMessageIsFoo() {
    // Arrange and Act
    ParseException actualParseException = new ParseException("foo");

    // Assert
    assertEquals("foo", actualParseException.getMessage());
    assertNull(actualParseException.getCause());
    assertEquals(0, actualParseException.getSuppressed().length);
  }
}
