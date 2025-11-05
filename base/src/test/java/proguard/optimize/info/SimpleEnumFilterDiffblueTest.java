package proguard.optimize.info;

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
import proguard.classfile.visitor.ClassVisitor;

class SimpleEnumFilterDiffblueTest {
  /**
   * Method under test: {@link SimpleEnumFilter#visitAnyClass(Clazz)}
   */
  @Test
  void testVisitAnyClass() {
    // Arrange
    SimpleEnumFilter simpleEnumFilter = new SimpleEnumFilter(mock(ClassVisitor.class), mock(ClassVisitor.class));

    // Act and Assert
    assertThrows(UnsupportedOperationException.class, () -> simpleEnumFilter.visitAnyClass(new LibraryClass()));
  }

  /**
   * Method under test: {@link SimpleEnumFilter#visitLibraryClass(LibraryClass)}
   */
  @Test
  void testVisitLibraryClass() {
    // Arrange
    ClassVisitor otherClassVisitor = mock(ClassVisitor.class);
    doNothing().when(otherClassVisitor).visitLibraryClass(Mockito.<LibraryClass>any());
    SimpleEnumFilter simpleEnumFilter = new SimpleEnumFilter(mock(ClassVisitor.class), otherClassVisitor);

    // Act
    simpleEnumFilter.visitLibraryClass(new LibraryClass());

    // Assert
    verify(otherClassVisitor).visitLibraryClass(isA(LibraryClass.class));
  }

  /**
   * Method under test: {@link SimpleEnumFilter#visitLibraryClass(LibraryClass)}
   */
  @Test
  void testVisitLibraryClass2() {
    // Arrange
    ClassVisitor otherClassVisitor = mock(ClassVisitor.class);
    doThrow(new UnsupportedOperationException("foo")).when(otherClassVisitor)
        .visitLibraryClass(Mockito.<LibraryClass>any());
    SimpleEnumFilter simpleEnumFilter = new SimpleEnumFilter(mock(ClassVisitor.class), otherClassVisitor);

    // Act and Assert
    assertThrows(UnsupportedOperationException.class, () -> simpleEnumFilter.visitLibraryClass(new LibraryClass()));
    verify(otherClassVisitor).visitLibraryClass(isA(LibraryClass.class));
  }
}
