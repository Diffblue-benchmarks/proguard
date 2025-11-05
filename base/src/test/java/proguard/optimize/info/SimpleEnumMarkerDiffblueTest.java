package proguard.optimize.info;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.anyBoolean;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.junit.jupiter.api.Test;
import proguard.classfile.Clazz;
import proguard.classfile.LibraryClass;
import proguard.classfile.ProgramClass;

class SimpleEnumMarkerDiffblueTest {
  /**
   * Method under test: {@link SimpleEnumMarker#visitProgramClass(ProgramClass)}
   */
  @Test
  void testVisitProgramClass() {
    // Arrange
    SimpleEnumMarker simpleEnumMarker = new SimpleEnumMarker(true);
    ProgramClass programClass = mock(ProgramClass.class);
    when(programClass.getProcessingInfo()).thenReturn(new ProgramClassOptimizationInfo());

    // Act
    simpleEnumMarker.visitProgramClass(programClass);

    // Assert
    verify(programClass).getProcessingInfo();
  }

  /**
   * Method under test: {@link SimpleEnumMarker#visitProgramClass(ProgramClass)}
   */
  @Test
  void testVisitProgramClass2() {
    // Arrange
    SimpleEnumMarker simpleEnumMarker = new SimpleEnumMarker(true);
    ProgramClassOptimizationInfo programClassOptimizationInfo = mock(ProgramClassOptimizationInfo.class);
    doNothing().when(programClassOptimizationInfo).setSimpleEnum(anyBoolean());
    ProgramClass programClass = mock(ProgramClass.class);
    when(programClass.getProcessingInfo()).thenReturn(programClassOptimizationInfo);

    // Act
    simpleEnumMarker.visitProgramClass(programClass);

    // Assert
    verify(programClassOptimizationInfo).setSimpleEnum(eq(true));
    verify(programClass).getProcessingInfo();
  }

  /**
   * Method under test: {@link SimpleEnumMarker#isSimpleEnum(Clazz)}
   */
  @Test
  void testIsSimpleEnum() {
    // Arrange
    LibraryClass clazz = new LibraryClass();
    clazz.setProcessingInfo(new ClassOptimizationInfo());

    // Act and Assert
    assertFalse(SimpleEnumMarker.isSimpleEnum(clazz));
  }

  /**
   * Method under test: {@link SimpleEnumMarker#isSimpleEnum(Clazz)}
   */
  @Test
  void testIsSimpleEnum2() {
    // Arrange
    LibraryClass clazz = new LibraryClass();
    clazz.setProcessingInfo(new ProgramClassOptimizationInfo());

    // Act and Assert
    assertFalse(SimpleEnumMarker.isSimpleEnum(clazz));
  }

  /**
   * Method under test: {@link SimpleEnumMarker#isSimpleEnum(Clazz)}
   */
  @Test
  void testIsSimpleEnum3() {
    // Arrange
    ProgramClassOptimizationInfo programClassOptimizationInfo = new ProgramClassOptimizationInfo();
    programClassOptimizationInfo.setSimpleEnum(true);

    LibraryClass clazz = new LibraryClass();
    clazz.setProcessingInfo(programClassOptimizationInfo);

    // Act and Assert
    assertTrue(SimpleEnumMarker.isSimpleEnum(clazz));
  }
}
