package proguard.optimize.peephole;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import proguard.classfile.Clazz;
import proguard.classfile.LibraryClass;
import proguard.classfile.ProgramClass;
import proguard.classfile.constant.Constant;
import proguard.classfile.visitor.ClassVisitor;
import proguard.optimize.info.ClassOptimizationInfo;
import proguard.optimize.info.ProgramClassOptimizationInfo;

class RetargetedClassFilterDiffblueTest {
  /**
   * Method under test: {@link RetargetedClassFilter#visitAnyClass(Clazz)}
   */
  @Test
  void testVisitAnyClass() {
    // Arrange
    RetargetedClassFilter retargetedClassFilter = new RetargetedClassFilter(mock(ClassVisitor.class),
        mock(ClassVisitor.class));

    // Act and Assert
    assertThrows(UnsupportedOperationException.class, () -> retargetedClassFilter.visitAnyClass(new LibraryClass()));
  }

  /**
   * Method under test:
   * {@link RetargetedClassFilter#visitProgramClass(ProgramClass)}
   */
  @Test
  void testVisitProgramClass() {
    // Arrange
    ClassVisitor otherClassVisitor = mock(ClassVisitor.class);
    doNothing().when(otherClassVisitor).visitProgramClass(Mockito.<ProgramClass>any());
    RetargetedClassFilter retargetedClassFilter = new RetargetedClassFilter(mock(ClassVisitor.class),
        otherClassVisitor);
    Constant constant = mock(Constant.class);
    doNothing().when(constant).addProcessingFlags((int[]) any());
    constant.addProcessingFlags(2, 1, 2, 1);

    // Act
    retargetedClassFilter.visitProgramClass(
        new ProgramClass(1, 3, new Constant[]{constant}, 1, 1, 1, "Feature Name", 1, new ClassOptimizationInfo()));

    // Assert
    verify(otherClassVisitor).visitProgramClass(isA(ProgramClass.class));
    verify(constant).addProcessingFlags((int[]) any());
  }

  /**
   * Method under test:
   * {@link RetargetedClassFilter#visitProgramClass(ProgramClass)}
   */
  @Test
  void testVisitProgramClass2() {
    // Arrange
    RetargetedClassFilter retargetedClassFilter = new RetargetedClassFilter(mock(ClassVisitor.class));
    Constant constant = mock(Constant.class);
    doNothing().when(constant).addProcessingFlags((int[]) any());
    constant.addProcessingFlags(2, 1, 2, 1);

    // Act
    retargetedClassFilter.visitProgramClass(
        new ProgramClass(1, 3, new Constant[]{constant}, 1, 1, 1, "Feature Name", 1, new ClassOptimizationInfo()));

    // Assert that nothing has changed
    verify(constant).addProcessingFlags((int[]) any());
  }

  /**
   * Method under test:
   * {@link RetargetedClassFilter#visitProgramClass(ProgramClass)}
   */
  @Test
  void testVisitProgramClass3() {
    // Arrange
    ClassVisitor otherClassVisitor = mock(ClassVisitor.class);
    doNothing().when(otherClassVisitor).visitProgramClass(Mockito.<ProgramClass>any());
    RetargetedClassFilter retargetedClassFilter = new RetargetedClassFilter(mock(ClassVisitor.class),
        otherClassVisitor);
    Constant constant = mock(Constant.class);
    doNothing().when(constant).addProcessingFlags((int[]) any());
    constant.addProcessingFlags(2, 1, 2, 1);

    // Act
    retargetedClassFilter.visitProgramClass(new ProgramClass(1, 3, new Constant[]{constant}, 1, 1, 1, "Feature Name", 1,
        new ProgramClassOptimizationInfo()));

    // Assert
    verify(otherClassVisitor).visitProgramClass(isA(ProgramClass.class));
    verify(constant).addProcessingFlags((int[]) any());
  }

  /**
   * Method under test:
   * {@link RetargetedClassFilter#visitProgramClass(ProgramClass)}
   */
  @Test
  void testVisitProgramClass4() {
    // Arrange
    ClassVisitor retargetedClassVisitor = mock(ClassVisitor.class);
    doNothing().when(retargetedClassVisitor).visitProgramClass(Mockito.<ProgramClass>any());
    ClassVisitor otherClassVisitor = mock(ClassVisitor.class);
    doNothing().when(otherClassVisitor).visitProgramClass(Mockito.<ProgramClass>any());
    RetargetedClassFilter retargetedClassFilter = new RetargetedClassFilter(retargetedClassVisitor, otherClassVisitor);
    Constant constant = mock(Constant.class);
    doNothing().when(constant).addProcessingFlags((int[]) any());
    constant.addProcessingFlags(2, 1, 2, 1);
    LibraryClass libraryClass = mock(LibraryClass.class);
    when(libraryClass.getProcessingInfo()).thenReturn(new ClassOptimizationInfo());
    ClassOptimizationInfo classOptimizationInfo = mock(ClassOptimizationInfo.class);
    when(classOptimizationInfo.getTargetClass()).thenReturn(libraryClass);

    // Act
    retargetedClassFilter.visitProgramClass(
        new ProgramClass(1, 3, new Constant[]{constant}, 1, 1, 1, "Feature Name", 1, classOptimizationInfo));

    // Assert
    verify(retargetedClassVisitor).visitProgramClass(isA(ProgramClass.class));
    verify(classOptimizationInfo).getTargetClass();
    verify(constant).addProcessingFlags((int[]) any());
    verify(libraryClass).getProcessingInfo();
  }

  /**
   * Method under test:
   * {@link RetargetedClassFilter#visitProgramClass(ProgramClass)}
   */
  @Test
  void testVisitProgramClass5() {
    // Arrange
    ClassVisitor retargetedClassVisitor = mock(ClassVisitor.class);
    doThrow(new UnsupportedOperationException("foo")).when(retargetedClassVisitor)
        .visitProgramClass(Mockito.<ProgramClass>any());
    RetargetedClassFilter retargetedClassFilter = new RetargetedClassFilter(retargetedClassVisitor);
    Constant constant = mock(Constant.class);
    doNothing().when(constant).addProcessingFlags((int[]) any());
    constant.addProcessingFlags(2, 1, 2, 1);
    LibraryClass libraryClass = mock(LibraryClass.class);
    when(libraryClass.getProcessingInfo()).thenReturn(new ClassOptimizationInfo());
    ClassOptimizationInfo classOptimizationInfo = mock(ClassOptimizationInfo.class);
    when(classOptimizationInfo.getTargetClass()).thenReturn(libraryClass);

    // Act and Assert
    assertThrows(UnsupportedOperationException.class, () -> retargetedClassFilter.visitProgramClass(
        new ProgramClass(1, 3, new Constant[]{constant}, 1, 1, 1, "Feature Name", 1, classOptimizationInfo)));
    verify(retargetedClassVisitor).visitProgramClass(isA(ProgramClass.class));
    verify(classOptimizationInfo).getTargetClass();
    verify(constant).addProcessingFlags((int[]) any());
    verify(libraryClass).getProcessingInfo();
  }

  /**
   * Method under test:
   * {@link RetargetedClassFilter#visitLibraryClass(LibraryClass)}
   */
  @Test
  void testVisitLibraryClass() {
    // Arrange
    ClassVisitor otherClassVisitor = mock(ClassVisitor.class);
    doNothing().when(otherClassVisitor).visitLibraryClass(Mockito.<LibraryClass>any());
    RetargetedClassFilter retargetedClassFilter = new RetargetedClassFilter(mock(ClassVisitor.class),
        otherClassVisitor);

    // Act
    retargetedClassFilter.visitLibraryClass(new LibraryClass());

    // Assert
    verify(otherClassVisitor).visitLibraryClass(isA(LibraryClass.class));
  }

  /**
   * Method under test:
   * {@link RetargetedClassFilter#visitLibraryClass(LibraryClass)}
   */
  @Test
  void testVisitLibraryClass2() {
    // Arrange
    ClassVisitor otherClassVisitor = mock(ClassVisitor.class);
    doThrow(new UnsupportedOperationException("foo")).when(otherClassVisitor)
        .visitLibraryClass(Mockito.<LibraryClass>any());
    RetargetedClassFilter retargetedClassFilter = new RetargetedClassFilter(mock(ClassVisitor.class),
        otherClassVisitor);

    // Act and Assert
    assertThrows(UnsupportedOperationException.class,
        () -> retargetedClassFilter.visitLibraryClass(new LibraryClass()));
    verify(otherClassVisitor).visitLibraryClass(isA(LibraryClass.class));
  }
}
