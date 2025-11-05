package proguard.shrink;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.ArgumentMatchers.isNull;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import proguard.classfile.LibraryClass;
import proguard.util.Processable;

class SimpleUsageMarkerDiffblueTest {
  /**
   * Method under test: {@link SimpleUsageMarker#markAsPossiblyUsed(Processable)}
   */
  @Test
  void testMarkAsPossiblyUsed() {
    // Arrange
    SimpleUsageMarker simpleUsageMarker = new SimpleUsageMarker();
    Processable processable = mock(Processable.class);
    doNothing().when(processable).setProcessingInfo(Mockito.<Object>any());

    // Act
    simpleUsageMarker.markAsPossiblyUsed(processable);

    // Assert
    verify(processable).setProcessingInfo(isA(Object.class));
  }

  /**
   * Method under test: {@link SimpleUsageMarker#isPossiblyUsed(Processable)}
   */
  @Test
  void testIsPossiblyUsed() {
    // Arrange
    SimpleUsageMarker simpleUsageMarker = new SimpleUsageMarker();

    // Act and Assert
    assertFalse(simpleUsageMarker.isPossiblyUsed(new LibraryClass()));
  }

  /**
   * Method under test: {@link SimpleUsageMarker#isPossiblyUsed(Processable)}
   */
  @Test
  void testIsPossiblyUsed2() {
    // Arrange
    SimpleUsageMarker simpleUsageMarker = new SimpleUsageMarker();
    Processable processable = mock(Processable.class);
    when(processable.getProcessingInfo()).thenReturn("Processing Info");

    // Act
    boolean actualIsPossiblyUsedResult = simpleUsageMarker.isPossiblyUsed(processable);

    // Assert
    verify(processable).getProcessingInfo();
    assertFalse(actualIsPossiblyUsedResult);
  }

  /**
   * Method under test: {@link SimpleUsageMarker#markAsUsed(Processable)}
   */
  @Test
  void testMarkAsUsed() {
    // Arrange
    SimpleUsageMarker simpleUsageMarker = new SimpleUsageMarker();
    Processable processable = mock(Processable.class);
    doNothing().when(processable).setProcessingInfo(Mockito.<Object>any());

    // Act
    simpleUsageMarker.markAsUsed(processable);

    // Assert
    verify(processable).setProcessingInfo(isA(Object.class));
  }

  /**
   * Method under test: {@link SimpleUsageMarker#markAsUnused(Processable)}
   */
  @Test
  void testMarkAsUnused() {
    // Arrange
    SimpleUsageMarker simpleUsageMarker = new SimpleUsageMarker();
    Processable processable = mock(Processable.class);
    doNothing().when(processable).setProcessingInfo(Mockito.<Object>any());

    // Act
    simpleUsageMarker.markAsUnused(processable);

    // Assert
    verify(processable).setProcessingInfo(isNull());
  }

  /**
   * Method under test: {@link SimpleUsageMarker#isUsed(Processable)}
   */
  @Test
  void testIsUsed() {
    // Arrange
    SimpleUsageMarker simpleUsageMarker = new SimpleUsageMarker();

    // Act and Assert
    assertFalse(simpleUsageMarker.isUsed(new LibraryClass()));
  }

  /**
   * Method under test: {@link SimpleUsageMarker#isUsed(Processable)}
   */
  @Test
  void testIsUsed2() {
    // Arrange
    SimpleUsageMarker simpleUsageMarker = new SimpleUsageMarker();
    Processable processable = mock(Processable.class);
    when(processable.getProcessingInfo()).thenReturn("Processing Info");

    // Act
    boolean actualIsUsedResult = simpleUsageMarker.isUsed(processable);

    // Assert
    verify(processable).getProcessingInfo();
    assertFalse(actualIsUsedResult);
  }
}
