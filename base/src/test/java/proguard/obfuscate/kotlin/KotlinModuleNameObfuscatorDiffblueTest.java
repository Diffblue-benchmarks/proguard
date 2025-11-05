package proguard.obfuscate.kotlin;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.junit.jupiter.api.Test;
import proguard.obfuscate.DictionaryNameFactory;
import proguard.obfuscate.NameFactory;
import proguard.resources.kotlinmodule.KotlinModule;

class KotlinModuleNameObfuscatorDiffblueTest {
  /**
   * Method under test:
   * {@link KotlinModuleNameObfuscator#KotlinModuleNameObfuscator(NameFactory)}
   */
  @Test
  void testNewKotlinModuleNameObfuscator() {
    // Arrange
    DictionaryNameFactory nameFactory = mock(DictionaryNameFactory.class);
    doNothing().when(nameFactory).reset();

    // Act
    new KotlinModuleNameObfuscator(nameFactory);

    // Assert
    verify(nameFactory).reset();
  }

  /**
   * Method under test:
   * {@link KotlinModuleNameObfuscator#visitKotlinModule(KotlinModule)}
   */
  @Test
  void testVisitKotlinModule() {
    // Arrange
    DictionaryNameFactory nameFactory = mock(DictionaryNameFactory.class);
    when(nameFactory.nextName()).thenReturn("Next Name");
    doNothing().when(nameFactory).reset();

    // Act
    (new KotlinModuleNameObfuscator(nameFactory)).visitKotlinModule(mock(KotlinModule.class));

    // Assert
    verify(nameFactory).nextName();
    verify(nameFactory).reset();
  }
}
