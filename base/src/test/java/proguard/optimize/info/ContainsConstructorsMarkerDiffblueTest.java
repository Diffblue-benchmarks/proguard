package proguard.optimize.info;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.anyInt;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.junit.jupiter.api.Test;
import proguard.classfile.Clazz;
import proguard.classfile.LibraryClass;
import proguard.classfile.ProgramClass;
import proguard.classfile.ProgramMethod;

class ContainsConstructorsMarkerDiffblueTest {
  /**
   * Method under test:
   * {@link ContainsConstructorsMarker#visitProgramMethod(ProgramClass, ProgramMethod)}
   */
  @Test
  void testVisitProgramMethod() {
    // Arrange
    ContainsConstructorsMarker containsConstructorsMarker = new ContainsConstructorsMarker();
    ProgramClass programClass = mock(ProgramClass.class);
    when(programClass.getString(anyInt())).thenReturn("String");

    // Act
    containsConstructorsMarker.visitProgramMethod(programClass, new ProgramMethod());

    // Assert
    verify(programClass).getString(eq(0));
  }

  /**
   * Method under test:
   * {@link ContainsConstructorsMarker#visitProgramMethod(ProgramClass, ProgramMethod)}
   */
  @Test
  void testVisitProgramMethod2() {
    // Arrange
    ContainsConstructorsMarker containsConstructorsMarker = new ContainsConstructorsMarker();
    ProgramClass programClass = mock(ProgramClass.class);
    when(programClass.getProcessingInfo()).thenReturn(new ProgramClassOptimizationInfo());
    when(programClass.getString(anyInt())).thenReturn("<init>");

    // Act
    containsConstructorsMarker.visitProgramMethod(programClass, new ProgramMethod());

    // Assert
    verify(programClass).getString(eq(0));
    verify(programClass).getProcessingInfo();
  }

  /**
   * Method under test:
   * {@link ContainsConstructorsMarker#visitProgramMethod(ProgramClass, ProgramMethod)}
   */
  @Test
  void testVisitProgramMethod3() {
    // Arrange
    ContainsConstructorsMarker containsConstructorsMarker = new ContainsConstructorsMarker();
    ProgramClassOptimizationInfo programClassOptimizationInfo = mock(ProgramClassOptimizationInfo.class);
    doNothing().when(programClassOptimizationInfo).setContainsConstructors();
    ProgramClass programClass = mock(ProgramClass.class);
    when(programClass.getProcessingInfo()).thenReturn(programClassOptimizationInfo);
    when(programClass.getString(anyInt())).thenReturn("<init>");

    // Act
    containsConstructorsMarker.visitProgramMethod(programClass, new ProgramMethod());

    // Assert
    verify(programClass).getString(eq(0));
    verify(programClassOptimizationInfo).setContainsConstructors();
    verify(programClass).getProcessingInfo();
  }

  /**
   * Method under test:
   * {@link ContainsConstructorsMarker#containsConstructors(Clazz)}
   */
  @Test
  void testContainsConstructors() {
    // Arrange
    LibraryClass clazz = new LibraryClass();
    clazz.setProcessingInfo(new ClassOptimizationInfo());

    // Act and Assert
    assertTrue(ContainsConstructorsMarker.containsConstructors(clazz));
  }

  /**
   * Method under test:
   * {@link ContainsConstructorsMarker#containsConstructors(Clazz)}
   */
  @Test
  void testContainsConstructors2() {
    // Arrange
    LibraryClass clazz = new LibraryClass();
    clazz.setProcessingInfo(new ProgramClassOptimizationInfo());

    // Act and Assert
    assertFalse(ContainsConstructorsMarker.containsConstructors(clazz));
  }
}
