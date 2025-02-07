package proguard.obfuscate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import com.diffblue.cover.annotations.MethodsUnderTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

class NumericNameFactoryDiffblueTest {
  /**
   * Test {@link NumericNameFactory#nextName()}.
   * <p>
   * Method under test: {@link NumericNameFactory#nextName()}
   */
  @Test
  @DisplayName("Test nextName()")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"java.lang.String proguard.obfuscate.NumericNameFactory.nextName()"})
  void testNextName() {
    // Arrange, Act and Assert
    assertEquals("1", (new NumericNameFactory()).nextName());
  }
}
