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

class CaughtClassMarkerDiffblueTest {
  /**
   * Method under test: {@link CaughtClassMarker#visitProgramClass(ProgramClass)}
   */
  @Test
  void testVisitProgramClass() {
    // Arrange
    CaughtClassMarker caughtClassMarker = new CaughtClassMarker();
    ProgramClass programClass = mock(ProgramClass.class);
    when(programClass.getProcessingInfo()).thenReturn(new ProgramClassOptimizationInfo());

    // Act
    caughtClassMarker.visitProgramClass(programClass);

    // Assert
    verify(programClass).getProcessingInfo();
  }

  /**
   * Method under test: {@link CaughtClassMarker#visitProgramClass(ProgramClass)}
   */
  @Test
  void testVisitProgramClass2() {
    // Arrange
    CaughtClassMarker caughtClassMarker = new CaughtClassMarker();
    ProgramClassOptimizationInfo programClassOptimizationInfo = mock(ProgramClassOptimizationInfo.class);
    doNothing().when(programClassOptimizationInfo).setCaught();
    ProgramClass programClass = mock(ProgramClass.class);
    when(programClass.getProcessingInfo()).thenReturn(programClassOptimizationInfo);

    // Act
    caughtClassMarker.visitProgramClass(programClass);

    // Assert
    verify(programClassOptimizationInfo).setCaught();
    verify(programClass).getProcessingInfo();
  }

  /**
   * Method under test: {@link CaughtClassMarker#isCaught(Clazz)}
   */
  @Test
  void testIsCaught() {
    // Arrange
    LibraryClass clazz = new LibraryClass();
    clazz.setProcessingInfo(new ClassOptimizationInfo());

    // Act and Assert
    assertTrue(CaughtClassMarker.isCaught(clazz));
  }

  /**
   * Method under test: {@link CaughtClassMarker#isCaught(Clazz)}
   */
  @Test
  void testIsCaught2() {
    // Arrange
    LibraryClass clazz = new LibraryClass();
    clazz.setProcessingInfo(new ProgramClassOptimizationInfo());

    // Act and Assert
    assertFalse(CaughtClassMarker.isCaught(clazz));
  }
}
