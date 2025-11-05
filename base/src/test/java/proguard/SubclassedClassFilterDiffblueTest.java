package proguard;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import proguard.classfile.Clazz;
import proguard.classfile.LibraryClass;
import proguard.classfile.ProgramClass;
import proguard.classfile.visitor.ClassVisitor;
import proguard.classfile.visitor.ImplementedClassFilter;

class SubclassedClassFilterDiffblueTest {
  /**
   * Method under test: {@link SubclassedClassFilter#visitAnyClass(Clazz)}
   */
  @Test
  void testVisitAnyClass() {
    // Arrange
    SubclassedClassFilter subclassedClassFilter = new SubclassedClassFilter(mock(ClassVisitor.class));

    // Act and Assert
    assertThrows(UnsupportedOperationException.class, () -> subclassedClassFilter.visitAnyClass(new LibraryClass()));
  }

  /**
   * Method under test:
   * {@link SubclassedClassFilter#visitProgramClass(ProgramClass)}
   */
  @Test
  void testVisitProgramClass() {
    // Arrange
    ClassVisitor classVisitor = mock(ClassVisitor.class);
    doNothing().when(classVisitor).visitProgramClass(Mockito.<ProgramClass>any());
    SubclassedClassFilter subclassedClassFilter = new SubclassedClassFilter(classVisitor);
    ProgramClass programClass = new ProgramClass();
    programClass.subClassCount = 1;

    // Act
    subclassedClassFilter.visitProgramClass(programClass);

    // Assert
    verify(classVisitor).visitProgramClass(isA(ProgramClass.class));
  }

  /**
   * Method under test:
   * {@link SubclassedClassFilter#visitProgramClass(ProgramClass)}
   */
  @Test
  void testVisitProgramClass2() {
    // Arrange
    ClassVisitor classVisitor = mock(ClassVisitor.class);
    doThrow(new UnsupportedOperationException("foo")).when(classVisitor).visitProgramClass(Mockito.<ProgramClass>any());
    SubclassedClassFilter subclassedClassFilter = new SubclassedClassFilter(classVisitor);
    ProgramClass programClass = new ProgramClass();
    programClass.subClassCount = 1;

    // Act and Assert
    assertThrows(UnsupportedOperationException.class, () -> subclassedClassFilter.visitProgramClass(programClass));
    verify(classVisitor).visitProgramClass(isA(ProgramClass.class));
  }

  /**
   * Method under test:
   * {@link SubclassedClassFilter#visitProgramClass(ProgramClass)}
   */
  @Test
  void testVisitProgramClass3() {
    // Arrange
    ClassVisitor rejectedClassVisistor = mock(ClassVisitor.class);
    doNothing().when(rejectedClassVisistor).visitProgramClass(Mockito.<ProgramClass>any());
    SubclassedClassFilter subclassedClassFilter = new SubclassedClassFilter(
        new ImplementedClassFilter(new LibraryClass(), true, mock(ClassVisitor.class), rejectedClassVisistor));

    ProgramClass programClass = new ProgramClass();
    programClass.addSubClass(new LibraryClass());

    // Act
    subclassedClassFilter.visitProgramClass(programClass);

    // Assert
    verify(rejectedClassVisistor).visitProgramClass(isA(ProgramClass.class));
  }

  /**
   * Method under test:
   * {@link SubclassedClassFilter#visitLibraryClass(LibraryClass)}
   */
  @Test
  void testVisitLibraryClass() {
    // Arrange
    ClassVisitor classVisitor = mock(ClassVisitor.class);
    doNothing().when(classVisitor).visitLibraryClass(Mockito.<LibraryClass>any());
    SubclassedClassFilter subclassedClassFilter = new SubclassedClassFilter(classVisitor);
    LibraryClass libraryClass = new LibraryClass();
    libraryClass.subClassCount = 1;

    // Act
    subclassedClassFilter.visitLibraryClass(libraryClass);

    // Assert
    verify(classVisitor).visitLibraryClass(isA(LibraryClass.class));
  }

  /**
   * Method under test:
   * {@link SubclassedClassFilter#visitLibraryClass(LibraryClass)}
   */
  @Test
  void testVisitLibraryClass2() {
    // Arrange
    ClassVisitor classVisitor = mock(ClassVisitor.class);
    doThrow(new UnsupportedOperationException("foo")).when(classVisitor).visitLibraryClass(Mockito.<LibraryClass>any());
    SubclassedClassFilter subclassedClassFilter = new SubclassedClassFilter(classVisitor);
    LibraryClass libraryClass = new LibraryClass();
    libraryClass.subClassCount = 1;

    // Act and Assert
    assertThrows(UnsupportedOperationException.class, () -> subclassedClassFilter.visitLibraryClass(libraryClass));
    verify(classVisitor).visitLibraryClass(isA(LibraryClass.class));
  }

  /**
   * Method under test:
   * {@link SubclassedClassFilter#visitLibraryClass(LibraryClass)}
   */
  @Test
  void testVisitLibraryClass3() {
    // Arrange
    ClassVisitor rejectedClassVisistor = mock(ClassVisitor.class);
    doNothing().when(rejectedClassVisistor).visitLibraryClass(Mockito.<LibraryClass>any());
    SubclassedClassFilter subclassedClassFilter = new SubclassedClassFilter(
        new ImplementedClassFilter(new LibraryClass(), true, mock(ClassVisitor.class), rejectedClassVisistor));

    LibraryClass libraryClass = new LibraryClass();
    libraryClass.addSubClass(new LibraryClass());

    // Act
    subclassedClassFilter.visitLibraryClass(libraryClass);

    // Assert
    verify(rejectedClassVisistor).visitLibraryClass(isA(LibraryClass.class));
  }
}
