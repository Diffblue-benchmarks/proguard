package proguard.obfuscate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import com.diffblue.cover.annotations.MethodsUnderTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

class SpecialNameFactoryDiffblueTest {
  /**
   * Test {@link SpecialNameFactory#nextName()}.
   * <ul>
   *   <li>Then return {@code 1_}.</li>
   * </ul>
   * <p>
   * Method under test: {@link SpecialNameFactory#nextName()}
   */
  @Test
  @DisplayName("Test nextName(); then return '1_'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"java.lang.String proguard.obfuscate.SpecialNameFactory.nextName()"})
  void testNextName_thenReturn1() {
    // Arrange, Act and Assert
    assertEquals("1_", (new SpecialNameFactory(new NumericNameFactory())).nextName());
  }

  /**
   * Test {@link SpecialNameFactory#nextName()}.
   * <ul>
   *   <li>Then return {@code 1__}.</li>
   * </ul>
   * <p>
   * Method under test: {@link SpecialNameFactory#nextName()}
   */
  @Test
  @DisplayName("Test nextName(); then return '1__'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"java.lang.String proguard.obfuscate.SpecialNameFactory.nextName()"})
  void testNextName_thenReturn12() {
    // Arrange, Act and Assert
    assertEquals("1__", (new SpecialNameFactory(new SpecialNameFactory(new NumericNameFactory()))).nextName());
  }

  /**
   * Test {@link SpecialNameFactory#isSpecialName(String)}.
   * <ul>
   *   <li>When {@code Name}.</li>
   *   <li>Then return {@code false}.</li>
   * </ul>
   * <p>
   * Method under test: {@link SpecialNameFactory#isSpecialName(String)}
   */
  @Test
  @DisplayName("Test isSpecialName(String); when 'Name'; then return 'false'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean proguard.obfuscate.SpecialNameFactory.isSpecialName(java.lang.String)"})
  void testIsSpecialName_whenName_thenReturnFalse() {
    // Arrange, Act and Assert
    assertFalse(SpecialNameFactory.isSpecialName("Name"));
  }

  /**
   * Test {@link SpecialNameFactory#isSpecialName(String)}.
   * <ul>
   *   <li>When {@code null}.</li>
   *   <li>Then return {@code false}.</li>
   * </ul>
   * <p>
   * Method under test: {@link SpecialNameFactory#isSpecialName(String)}
   */
  @Test
  @DisplayName("Test isSpecialName(String); when 'null'; then return 'false'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean proguard.obfuscate.SpecialNameFactory.isSpecialName(java.lang.String)"})
  void testIsSpecialName_whenNull_thenReturnFalse() {
    // Arrange, Act and Assert
    assertFalse(SpecialNameFactory.isSpecialName(null));
  }
}
