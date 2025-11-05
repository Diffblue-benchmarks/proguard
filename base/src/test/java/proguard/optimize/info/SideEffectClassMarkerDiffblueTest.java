package proguard.optimize.info;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.junit.jupiter.api.Test;
import proguard.classfile.Clazz;
import proguard.classfile.LibraryClass;
import proguard.classfile.ProgramClass;

class SideEffectClassMarkerDiffblueTest {
  /**
   * Method under test:
   * {@link SideEffectClassMarker#visitProgramClass(ProgramClass)}
   */
  @Test
  void testVisitProgramClass() {
    // Arrange
    SideEffectClassMarker sideEffectClassMarker = new SideEffectClassMarker();
    ProgramClass programClass = mock(ProgramClass.class);
    when(programClass.getProcessingInfo()).thenReturn(new ProgramClassOptimizationInfo());

    // Act
    sideEffectClassMarker.visitProgramClass(programClass);

    // Assert
    verify(programClass).getProcessingInfo();
  }

  /**
   * Method under test:
   * {@link SideEffectClassMarker#visitProgramClass(ProgramClass)}
   */
  @Test
  void testVisitProgramClass2() {
    // Arrange
    SideEffectClassMarker sideEffectClassMarker = new SideEffectClassMarker();
    ProgramClassOptimizationInfo programClassOptimizationInfo = mock(ProgramClassOptimizationInfo.class);
    doNothing().when(programClassOptimizationInfo).setSideEffects();
    ProgramClass programClass = mock(ProgramClass.class);
    when(programClass.getProcessingInfo()).thenReturn(programClassOptimizationInfo);

    // Act
    sideEffectClassMarker.visitProgramClass(programClass);

    // Assert
    verify(programClassOptimizationInfo).setSideEffects();
    verify(programClass).getProcessingInfo();
  }

  /**
   * Method under test: {@link SideEffectClassMarker#hasSideEffects(Clazz)}
   */
  @Test
  void testHasSideEffects() {
    // Arrange
    LibraryClass clazz = new LibraryClass();
    clazz.processingInfo = new ClassOptimizationInfo();

    // Act and Assert
    assertTrue(SideEffectClassMarker.hasSideEffects(clazz));
  }

  /**
   * Method under test: {@link SideEffectClassMarker#hasSideEffects(Clazz)}
   */
  @Test
  void testHasSideEffects2() {
    // Arrange
    LibraryClass clazz = new LibraryClass();
    clazz.processingInfo = new ProgramClassOptimizationInfo();

    // Act and Assert
    assertFalse(SideEffectClassMarker.hasSideEffects(clazz));
  }
}
