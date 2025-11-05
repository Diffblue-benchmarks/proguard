package proguard.optimize;

import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import proguard.classfile.Clazz;
import proguard.classfile.LibraryClass;
import proguard.classfile.visitor.ClassVisitor;

class KeptClassFilterDiffblueTest {
  /**
   * Method under test: {@link KeptClassFilter#visitAnyClass(Clazz)}
   */
  @Test
  void testVisitAnyClass() {
    // Arrange
    ClassVisitor rejectedVisitor = mock(ClassVisitor.class);
    doNothing().when(rejectedVisitor).visitLibraryClass(Mockito.<LibraryClass>any());
    KeptClassFilter keptClassFilter = new KeptClassFilter(mock(ClassVisitor.class), rejectedVisitor);

    // Act
    keptClassFilter.visitAnyClass(new LibraryClass());

    // Assert
    verify(rejectedVisitor).visitLibraryClass(isA(LibraryClass.class));
  }
}
