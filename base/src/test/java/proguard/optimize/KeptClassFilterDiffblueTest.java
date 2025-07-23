package proguard.optimize;

import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import proguard.classfile.Clazz;
import proguard.classfile.LibraryClass;
import proguard.classfile.visitor.ClassVisitor;

class KeptClassFilterDiffblueTest {
  /**
   * Test {@link KeptClassFilter#visitAnyClass(Clazz)}.
   *
   * <ul>
   *   <li>Then calls {@link ClassVisitor#visitLibraryClass(LibraryClass)}.
   * </ul>
   *
   * <p>Method under test: {@link KeptClassFilter#visitAnyClass(Clazz)}
   */
  @Test
  @DisplayName("Test visitAnyClass(Clazz); then calls visitLibraryClass(LibraryClass)")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void KeptClassFilter.visitAnyClass(Clazz)"})
  void testVisitAnyClass_thenCallsVisitLibraryClass() {
    // Arrange
    ClassVisitor rejectedVisitor = mock(ClassVisitor.class);
    doNothing().when(rejectedVisitor).visitLibraryClass(Mockito.<LibraryClass>any());
    KeptClassFilter keptClassFilter =
        new KeptClassFilter(mock(ClassVisitor.class), rejectedVisitor);

    // Act
    keptClassFilter.visitAnyClass(new LibraryClass());

    // Assert
    verify(rejectedVisitor).visitLibraryClass(isA(LibraryClass.class));
  }
}
