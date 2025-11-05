package proguard.classfile.visitor;

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
import proguard.classfile.constant.ClassConstant;
import proguard.classfile.constant.Constant;

class InjectedClassFilterDiffblueTest {
  /**
   * Method under test: {@link InjectedClassFilter#visitAnyClass(Clazz)}
   */
  @Test
  void testVisitAnyClass() {
    // Arrange
    InjectedClassFilter injectedClassFilter = new InjectedClassFilter(mock(ClassVisitor.class),
        mock(ClassVisitor.class));

    // Act and Assert
    assertThrows(UnsupportedOperationException.class, () -> injectedClassFilter.visitAnyClass(new LibraryClass()));
  }

  /**
   * Method under test:
   * {@link InjectedClassFilter#visitProgramClass(ProgramClass)}
   */
  @Test
  void testVisitProgramClass() {
    // Arrange
    ClassVisitor otherClassVisitor = mock(ClassVisitor.class);
    doNothing().when(otherClassVisitor).visitProgramClass(Mockito.<ProgramClass>any());
    InjectedClassFilter injectedClassFilter = new InjectedClassFilter(mock(ClassVisitor.class), otherClassVisitor);

    // Act
    injectedClassFilter.visitProgramClass(new ProgramClass());

    // Assert
    verify(otherClassVisitor).visitProgramClass(isA(ProgramClass.class));
  }

  /**
   * Method under test:
   * {@link InjectedClassFilter#visitProgramClass(ProgramClass)}
   */
  @Test
  void testVisitProgramClass2() {
    // Arrange
    ClassVisitor injectedClassVisitor = mock(ClassVisitor.class);
    doNothing().when(injectedClassVisitor).visitProgramClass(Mockito.<ProgramClass>any());
    ClassVisitor otherClassVisitor = mock(ClassVisitor.class);
    doNothing().when(otherClassVisitor).visitProgramClass(Mockito.<ProgramClass>any());
    InjectedClassFilter injectedClassFilter = new InjectedClassFilter(injectedClassVisitor, otherClassVisitor);

    // Act
    injectedClassFilter.visitProgramClass(new ProgramClass(512, 3, new Constant[]{new ClassConstant()}, 512, 512, 512,
        "Feature Name", 512, "Processing Info"));

    // Assert
    verify(injectedClassVisitor).visitProgramClass(isA(ProgramClass.class));
  }

  /**
   * Method under test:
   * {@link InjectedClassFilter#visitProgramClass(ProgramClass)}
   */
  @Test
  void testVisitProgramClass3() {
    // Arrange
    ClassVisitor injectedClassVisitor = mock(ClassVisitor.class);
    doThrow(new UnsupportedOperationException("foo")).when(injectedClassVisitor)
        .visitProgramClass(Mockito.<ProgramClass>any());
    ClassVisitor otherClassVisitor = mock(ClassVisitor.class);
    doNothing().when(otherClassVisitor).visitProgramClass(Mockito.<ProgramClass>any());
    InjectedClassFilter injectedClassFilter = new InjectedClassFilter(injectedClassVisitor, otherClassVisitor);

    // Act and Assert
    assertThrows(UnsupportedOperationException.class, () -> injectedClassFilter.visitProgramClass(new ProgramClass(512,
        3, new Constant[]{new ClassConstant()}, 512, 512, 512, "Feature Name", 512, "Processing Info")));
    verify(injectedClassVisitor).visitProgramClass(isA(ProgramClass.class));
  }

  /**
   * Method under test:
   * {@link InjectedClassFilter#visitLibraryClass(LibraryClass)}
   */
  @Test
  void testVisitLibraryClass() {
    // Arrange
    ClassVisitor otherClassVisitor = mock(ClassVisitor.class);
    doNothing().when(otherClassVisitor).visitLibraryClass(Mockito.<LibraryClass>any());
    InjectedClassFilter injectedClassFilter = new InjectedClassFilter(mock(ClassVisitor.class), otherClassVisitor);

    // Act
    injectedClassFilter.visitLibraryClass(new LibraryClass());

    // Assert
    verify(otherClassVisitor).visitLibraryClass(isA(LibraryClass.class));
  }

  /**
   * Method under test:
   * {@link InjectedClassFilter#visitLibraryClass(LibraryClass)}
   */
  @Test
  void testVisitLibraryClass2() {
    // Arrange
    ClassVisitor otherClassVisitor = mock(ClassVisitor.class);
    doThrow(new UnsupportedOperationException("foo")).when(otherClassVisitor)
        .visitLibraryClass(Mockito.<LibraryClass>any());
    InjectedClassFilter injectedClassFilter = new InjectedClassFilter(mock(ClassVisitor.class), otherClassVisitor);

    // Act and Assert
    assertThrows(UnsupportedOperationException.class, () -> injectedClassFilter.visitLibraryClass(new LibraryClass()));
    verify(otherClassVisitor).visitLibraryClass(isA(LibraryClass.class));
  }
}
