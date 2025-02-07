package proguard.optimize.info;

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
import proguard.classfile.visitor.ClassVisitor;

class SimpleEnumFilterDiffblueTest {
  /**
   * Test {@link SimpleEnumFilter#visitAnyClass(Clazz)}.
   * <ul>
   *   <li>When {@link LibraryClass#LibraryClass()}.</li>
   *   <li>Then throw {@link UnsupportedOperationException}.</li>
   * </ul>
   * <p>
   * Method under test: {@link SimpleEnumFilter#visitAnyClass(Clazz)}
   */
  @Test
  @DisplayName("Test visitAnyClass(Clazz); when LibraryClass(); then throw UnsupportedOperationException")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void proguard.optimize.info.SimpleEnumFilter.visitAnyClass(proguard.classfile.Clazz)"})
  void testVisitAnyClass_whenLibraryClass_thenThrowUnsupportedOperationException() {
    // Arrange
    SimpleEnumFilter simpleEnumFilter = new SimpleEnumFilter(mock(ClassVisitor.class), mock(ClassVisitor.class));

    // Act and Assert
    assertThrows(UnsupportedOperationException.class, () -> simpleEnumFilter.visitAnyClass(new LibraryClass()));
  }

  /**
   * Test {@link SimpleEnumFilter#visitLibraryClass(LibraryClass)}.
   * <ul>
   *   <li>Given {@link ClassVisitor} {@link ClassVisitor#visitLibraryClass(LibraryClass)} does nothing.</li>
   * </ul>
   * <p>
   * Method under test: {@link SimpleEnumFilter#visitLibraryClass(LibraryClass)}
   */
  @Test
  @DisplayName("Test visitLibraryClass(LibraryClass); given ClassVisitor visitLibraryClass(LibraryClass) does nothing")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void proguard.optimize.info.SimpleEnumFilter.visitLibraryClass(proguard.classfile.LibraryClass)"})
  void testVisitLibraryClass_givenClassVisitorVisitLibraryClassDoesNothing() {
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
   * Test {@link SimpleEnumFilter#visitLibraryClass(LibraryClass)}.
   * <ul>
   *   <li>Then throw {@link UnsupportedOperationException}.</li>
   * </ul>
   * <p>
   * Method under test: {@link SimpleEnumFilter#visitLibraryClass(LibraryClass)}
   */
  @Test
  @DisplayName("Test visitLibraryClass(LibraryClass); then throw UnsupportedOperationException")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void proguard.optimize.info.SimpleEnumFilter.visitLibraryClass(proguard.classfile.LibraryClass)"})
  void testVisitLibraryClass_thenThrowUnsupportedOperationException() {
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
