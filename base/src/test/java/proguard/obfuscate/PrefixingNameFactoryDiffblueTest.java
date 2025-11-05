package proguard.obfuscate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import org.junit.jupiter.api.Test;

class PrefixingNameFactoryDiffblueTest {
  /**
   * Method under test: {@link PrefixingNameFactory#nextName()}
   */
  @Test
  void testNextName() {
    // Arrange, Act and Assert
    assertEquals("Prefix1", (new PrefixingNameFactory(new NumericNameFactory(), "Prefix")).nextName());
    assertEquals("PrefixPrefix1",
        (new PrefixingNameFactory(new PrefixingNameFactory(new NumericNameFactory(), "Prefix"), "Prefix")).nextName());
  }

  /**
   * Method under test: {@link PrefixingNameFactory#reset()}
   */
  @Test
  void testReset() {
    // Arrange
    DictionaryNameFactory delegateNameFactory = mock(DictionaryNameFactory.class);
    doNothing().when(delegateNameFactory).reset();

    // Act
    (new PrefixingNameFactory(delegateNameFactory, "Prefix")).reset();

    // Assert
    verify(delegateNameFactory).reset();
  }
}
