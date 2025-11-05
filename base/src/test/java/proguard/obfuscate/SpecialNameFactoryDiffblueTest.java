package proguard.obfuscate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import org.junit.jupiter.api.Test;

class SpecialNameFactoryDiffblueTest {
  /**
   * Method under test: {@link SpecialNameFactory#reset()}
   */
  @Test
  void testReset() {
    // Arrange
    DictionaryNameFactory nameFactory = mock(DictionaryNameFactory.class);
    doNothing().when(nameFactory).reset();

    // Act
    (new SpecialNameFactory(nameFactory)).reset();

    // Assert
    verify(nameFactory).reset();
  }

  /**
   * Method under test: {@link SpecialNameFactory#nextName()}
   */
  @Test
  void testNextName() {
    // Arrange, Act and Assert
    assertEquals("1_", (new SpecialNameFactory(new NumericNameFactory())).nextName());
    assertEquals("1__", (new SpecialNameFactory(new SpecialNameFactory(new NumericNameFactory()))).nextName());
  }

  /**
   * Method under test: {@link SpecialNameFactory#isSpecialName(String)}
   */
  @Test
  void testIsSpecialName() {
    // Arrange, Act and Assert
    assertFalse(SpecialNameFactory.isSpecialName("Name"));
    assertFalse(SpecialNameFactory.isSpecialName(null));
  }
}
