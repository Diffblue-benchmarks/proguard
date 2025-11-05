package proguard.optimize;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import proguard.classfile.Clazz;
import proguard.classfile.LibraryClass;
import proguard.classfile.ProgramClass;
import proguard.classfile.constant.Constant;
import proguard.classfile.visitor.ClassVisitor;
import proguard.optimize.info.ProgramClassOptimizationInfo;

class OptimizationInfoClassFilterDiffblueTest {
  /**
   * Method under test: {@link OptimizationInfoClassFilter#visitAnyClass(Clazz)}
   */
  @Test
  void testVisitAnyClass() {
    // Arrange
    OptimizationInfoClassFilter optimizationInfoClassFilter = new OptimizationInfoClassFilter(mock(ClassVisitor.class));

    // Act and Assert
    assertThrows(UnsupportedOperationException.class,
        () -> optimizationInfoClassFilter.visitAnyClass(new LibraryClass()));
  }

  /**
   * Method under test:
   * {@link OptimizationInfoClassFilter#visitProgramClass(ProgramClass)}
   */
  @Test
  void testVisitProgramClass() {
    // Arrange
    ClassVisitor classVisitor = mock(ClassVisitor.class);
    doNothing().when(classVisitor).visitProgramClass(Mockito.<ProgramClass>any());
    OptimizationInfoClassFilter optimizationInfoClassFilter = new OptimizationInfoClassFilter(classVisitor);
    Constant constant = mock(Constant.class);
    doNothing().when(constant).addProcessingFlags((int[]) any());
    constant.addProcessingFlags(2, 1, 2, 1);

    // Act
    optimizationInfoClassFilter.visitProgramClass(new ProgramClass(1, 3, new Constant[]{constant}, 1, 1, 1,
        "Feature Name", 1, new ProgramClassOptimizationInfo()));

    // Assert
    verify(classVisitor).visitProgramClass(isA(ProgramClass.class));
    verify(constant).addProcessingFlags((int[]) any());
  }

  /**
   * Method under test:
   * {@link OptimizationInfoClassFilter#visitProgramClass(ProgramClass)}
   */
  @Test
  void testVisitProgramClass2() {
    // Arrange
    ClassVisitor classVisitor = mock(ClassVisitor.class);
    doThrow(new UnsupportedOperationException("foo")).when(classVisitor).visitProgramClass(Mockito.<ProgramClass>any());
    OptimizationInfoClassFilter optimizationInfoClassFilter = new OptimizationInfoClassFilter(classVisitor);
    Constant constant = mock(Constant.class);
    doNothing().when(constant).addProcessingFlags((int[]) any());
    constant.addProcessingFlags(2, 1, 2, 1);

    // Act and Assert
    assertThrows(UnsupportedOperationException.class,
        () -> optimizationInfoClassFilter.visitProgramClass(new ProgramClass(1, 3, new Constant[]{constant}, 1, 1, 1,
            "Feature Name", 1, new ProgramClassOptimizationInfo())));
    verify(classVisitor).visitProgramClass(isA(ProgramClass.class));
    verify(constant).addProcessingFlags((int[]) any());
  }

  /**
   * Method under test:
   * {@link OptimizationInfoClassFilter#visitLibraryClass(LibraryClass)}
   */
  @Test
  void testVisitLibraryClass() {
    // Arrange
    ClassVisitor classVisitor = mock(ClassVisitor.class);
    doNothing().when(classVisitor).visitLibraryClass(Mockito.<LibraryClass>any());
    OptimizationInfoClassFilter optimizationInfoClassFilter = new OptimizationInfoClassFilter(classVisitor);

    LibraryClass libraryClass = new LibraryClass();
    libraryClass.setProcessingInfo(new ProgramClassOptimizationInfo());

    // Act
    optimizationInfoClassFilter.visitLibraryClass(libraryClass);

    // Assert
    verify(classVisitor).visitLibraryClass(isA(LibraryClass.class));
  }

  /**
   * Method under test:
   * {@link OptimizationInfoClassFilter#visitLibraryClass(LibraryClass)}
   */
  @Test
  void testVisitLibraryClass2() {
    // Arrange
    ClassVisitor classVisitor = mock(ClassVisitor.class);
    doThrow(new UnsupportedOperationException("foo")).when(classVisitor).visitLibraryClass(Mockito.<LibraryClass>any());
    OptimizationInfoClassFilter optimizationInfoClassFilter = new OptimizationInfoClassFilter(classVisitor);

    LibraryClass libraryClass = new LibraryClass();
    libraryClass.setProcessingInfo(new ProgramClassOptimizationInfo());

    // Act and Assert
    assertThrows(UnsupportedOperationException.class,
        () -> optimizationInfoClassFilter.visitLibraryClass(libraryClass));
    verify(classVisitor).visitLibraryClass(isA(LibraryClass.class));
  }
}
