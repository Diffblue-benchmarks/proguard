package proguard;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import com.diffblue.cover.annotations.MethodsUnderTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import proguard.classfile.Clazz;
import proguard.classfile.LibraryClass;
import proguard.classfile.ProgramClass;
import proguard.classfile.visitor.ClassVisitor;
import proguard.classfile.visitor.ImplementedClassFilter;

class SubclassedClassFilterDiffblueTest {
  /**
   * Test {@link SubclassedClassFilter#visitAnyClass(Clazz)}.
   * <ul>
   *   <li>When {@link LibraryClass#LibraryClass()}.</li>
   *   <li>Then throw {@link UnsupportedOperationException}.</li>
   * </ul>
   * <p>
   * Method under test: {@link SubclassedClassFilter#visitAnyClass(Clazz)}
   */
  @Test
  @DisplayName("Test visitAnyClass(Clazz); when LibraryClass(); then throw UnsupportedOperationException")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void SubclassedClassFilter.visitAnyClass(Clazz)"})
  void testVisitAnyClass_whenLibraryClass_thenThrowUnsupportedOperationException() {
    // Arrange
    SubclassedClassFilter subclassedClassFilter = new SubclassedClassFilter(mock(ClassVisitor.class));

    // Act and Assert
    assertThrows(UnsupportedOperationException.class, () -> subclassedClassFilter.visitAnyClass(new LibraryClass()));
  }

  /**
   * Test {@link SubclassedClassFilter#visitProgramClass(ProgramClass)}.
   * <ul>
   *   <li>Given one.</li>
   *   <li>When {@link ProgramClass#ProgramClass()} {@link ProgramClass#subClassCount} is one.</li>
   * </ul>
   * <p>
   * Method under test: {@link SubclassedClassFilter#visitProgramClass(ProgramClass)}
   */
  @Test
  @DisplayName("Test visitProgramClass(ProgramClass); given one; when ProgramClass() subClassCount is one")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void SubclassedClassFilter.visitProgramClass(ProgramClass)"})
  void testVisitProgramClass_givenOne_whenProgramClassSubClassCountIsOne() {
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
   * Test {@link SubclassedClassFilter#visitProgramClass(ProgramClass)}.
   * <ul>
   *   <li>Then throw {@link UnsupportedOperationException}.</li>
   * </ul>
   * <p>
   * Method under test: {@link SubclassedClassFilter#visitProgramClass(ProgramClass)}
   */
  @Test
  @DisplayName("Test visitProgramClass(ProgramClass); then throw UnsupportedOperationException")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void SubclassedClassFilter.visitProgramClass(ProgramClass)"})
  void testVisitProgramClass_thenThrowUnsupportedOperationException() {
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
   * Test {@link SubclassedClassFilter#visitProgramClass(ProgramClass)}.
   * <ul>
   *   <li>When {@link ProgramClass#ProgramClass()} addSubClass {@link LibraryClass#LibraryClass()}.</li>
   * </ul>
   * <p>
   * Method under test: {@link SubclassedClassFilter#visitProgramClass(ProgramClass)}
   */
  @Test
  @DisplayName("Test visitProgramClass(ProgramClass); when ProgramClass() addSubClass LibraryClass()")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void SubclassedClassFilter.visitProgramClass(ProgramClass)"})
  void testVisitProgramClass_whenProgramClassAddSubClassLibraryClass() {
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
   * Test {@link SubclassedClassFilter#visitLibraryClass(LibraryClass)}.
   * <p>
   * Method under test: {@link SubclassedClassFilter#visitLibraryClass(LibraryClass)}
   */
  @Test
  @DisplayName("Test visitLibraryClass(LibraryClass)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void SubclassedClassFilter.visitLibraryClass(LibraryClass)"})
  void testVisitLibraryClass() {
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

  /**
   * Test {@link SubclassedClassFilter#visitLibraryClass(LibraryClass)}.
   * <ul>
   *   <li>Given {@link ClassVisitor} {@link ClassVisitor#visitLibraryClass(LibraryClass)} does nothing.</li>
   * </ul>
   * <p>
   * Method under test: {@link SubclassedClassFilter#visitLibraryClass(LibraryClass)}
   */
  @Test
  @DisplayName("Test visitLibraryClass(LibraryClass); given ClassVisitor visitLibraryClass(LibraryClass) does nothing")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void SubclassedClassFilter.visitLibraryClass(LibraryClass)"})
  void testVisitLibraryClass_givenClassVisitorVisitLibraryClassDoesNothing() {
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
   * Test {@link SubclassedClassFilter#visitLibraryClass(LibraryClass)}.
   * <ul>
   *   <li>Then throw {@link UnsupportedOperationException}.</li>
   * </ul>
   * <p>
   * Method under test: {@link SubclassedClassFilter#visitLibraryClass(LibraryClass)}
   */
  @Test
  @DisplayName("Test visitLibraryClass(LibraryClass); then throw UnsupportedOperationException")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void SubclassedClassFilter.visitLibraryClass(LibraryClass)"})
  void testVisitLibraryClass_thenThrowUnsupportedOperationException() {
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
}
