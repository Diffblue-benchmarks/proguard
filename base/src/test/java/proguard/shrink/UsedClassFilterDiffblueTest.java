package proguard.shrink;

import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import proguard.classfile.Clazz;
import proguard.classfile.LibraryClass;
import proguard.classfile.visitor.ClassVisitor;

class UsedClassFilterDiffblueTest {
  /**
   * Method under test: {@link UsedClassFilter#visitAnyClass(Clazz)}
   */
  @Test
  void testVisitAnyClass() {
    // Arrange
    ClassVisitor unusedClassVisitor = mock(ClassVisitor.class);
    doNothing().when(unusedClassVisitor).visitLibraryClass(Mockito.<LibraryClass>any());
    UsedClassFilter usedClassFilter = new UsedClassFilter(new SimpleUsageMarker(), mock(ClassVisitor.class),
        unusedClassVisitor);

    // Act
    usedClassFilter.visitAnyClass(new LibraryClass());

    // Assert
    verify(unusedClassVisitor).visitLibraryClass(isA(LibraryClass.class));
  }
}
