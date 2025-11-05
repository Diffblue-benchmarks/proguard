package proguard.obfuscate;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.junit.jupiter.api.Test;
import proguard.classfile.LibraryClass;
import proguard.util.Processable;

class AttributeUsageMarkerDiffblueTest {
  /**
   * Method under test: {@link AttributeUsageMarker#isUsed(Processable)}
   */
  @Test
  void testIsUsed() {
    // Arrange, Act and Assert
    assertFalse(AttributeUsageMarker.isUsed(new LibraryClass()));
  }

  /**
   * Method under test: {@link AttributeUsageMarker#isUsed(Processable)}
   */
  @Test
  void testIsUsed2() {
    // Arrange
    Processable processable = mock(Processable.class);
    when(processable.getProcessingInfo()).thenReturn("Processing Info");

    // Act
    boolean actualIsUsedResult = AttributeUsageMarker.isUsed(processable);

    // Assert
    verify(processable).getProcessingInfo();
    assertFalse(actualIsUsedResult);
  }
}
