package proguard.obfuscate;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import org.junit.jupiter.api.Test;
import proguard.classfile.Clazz;
import proguard.classfile.LibraryClass;

class NameFactoryResetterDiffblueTest {
  /**
   * Method under test: {@link NameFactoryResetter#visitAnyClass(Clazz)}
   */
  @Test
  void testVisitAnyClass() {
    // Arrange
    DictionaryNameFactory nameFactory = mock(DictionaryNameFactory.class);
    doNothing().when(nameFactory).reset();
    NameFactoryResetter nameFactoryResetter = new NameFactoryResetter(nameFactory);

    // Act
    nameFactoryResetter.visitAnyClass(new LibraryClass());

    // Assert
    verify(nameFactory).reset();
  }
}
