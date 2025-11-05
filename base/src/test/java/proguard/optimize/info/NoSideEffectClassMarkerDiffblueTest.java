package proguard.optimize.info;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertSame;
import org.junit.jupiter.api.Test;
import proguard.classfile.Clazz;
import proguard.classfile.LibraryClass;

class NoSideEffectClassMarkerDiffblueTest {
  /**
   * Method under test: {@link NoSideEffectClassMarker#visitAnyClass(Clazz)}
   */
  @Test
  void testVisitAnyClass() {
    // Arrange
    NoSideEffectClassMarker noSideEffectClassMarker = new NoSideEffectClassMarker();

    LibraryClass clazz = new LibraryClass();
    ClassOptimizationInfo classOptimizationInfo = new ClassOptimizationInfo();
    clazz.setProcessingInfo(classOptimizationInfo);

    // Act
    noSideEffectClassMarker.visitAnyClass(clazz);

    // Assert
    assertSame(classOptimizationInfo, clazz.getProcessingInfo());
  }

  /**
   * Method under test: {@link NoSideEffectClassMarker#hasNoSideEffects(Clazz)}
   */
  @Test
  void testHasNoSideEffects() {
    // Arrange
    LibraryClass clazz = new LibraryClass();
    clazz.setProcessingInfo(new ClassOptimizationInfo());

    // Act and Assert
    assertFalse(NoSideEffectClassMarker.hasNoSideEffects(clazz));
  }
}
