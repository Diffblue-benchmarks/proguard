package proguard.obfuscate;

import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import proguard.classfile.Clazz;
import proguard.classfile.LibraryClass;
import proguard.classfile.ProgramClass;
import proguard.classfile.visitor.ClassVisitor;
import proguard.testutils.cpa.NamedClass;

class OriginalClassNameFilterDiffblueTest {
  /**
   * Method under test: {@link OriginalClassNameFilter#visitAnyClass(Clazz)}
   */
  @Test
  void testVisitAnyClass() {
    // Arrange
    ClassVisitor rejectedClassVisitor = mock(ClassVisitor.class);
    doNothing().when(rejectedClassVisitor).visitLibraryClass(Mockito.<LibraryClass>any());
    OriginalClassNameFilter originalClassNameFilter = new OriginalClassNameFilter(mock(ClassVisitor.class),
        rejectedClassVisitor);

    // Act
    originalClassNameFilter.visitAnyClass(new LibraryClass(1, "This Class Name", "Super Class Name"));

    // Assert
    verify(rejectedClassVisitor).visitLibraryClass(isA(LibraryClass.class));
  }

  /**
   * Method under test: {@link OriginalClassNameFilter#visitAnyClass(Clazz)}
   */
  @Test
  void testVisitAnyClass2() {
    // Arrange
    ClassVisitor rejectedClassVisitor = mock(ClassVisitor.class);
    doNothing().when(rejectedClassVisitor).visitLibraryClass(Mockito.<LibraryClass>any());
    OriginalClassNameFilter originalClassNameFilter = new OriginalClassNameFilter(mock(ClassVisitor.class),
        rejectedClassVisitor);

    LibraryClass clazz = new LibraryClass(1, "This Class Name", "Super Class Name");
    clazz.setProcessingInfo("Processing Info");

    // Act
    originalClassNameFilter.visitAnyClass(clazz);

    // Assert
    verify(rejectedClassVisitor).visitLibraryClass(isA(LibraryClass.class));
  }

  /**
   * Method under test: {@link OriginalClassNameFilter#visitAnyClass(Clazz)}
   */
  @Test
  void testVisitAnyClass3() {
    // Arrange
    ClassVisitor acceptedClassVisitor = mock(ClassVisitor.class);
    doNothing().when(acceptedClassVisitor).visitLibraryClass(Mockito.<LibraryClass>any());
    ClassVisitor rejectedClassVisitor = mock(ClassVisitor.class);
    doNothing().when(rejectedClassVisitor).visitLibraryClass(Mockito.<LibraryClass>any());
    OriginalClassNameFilter originalClassNameFilter = new OriginalClassNameFilter(acceptedClassVisitor,
        rejectedClassVisitor);

    LibraryClass clazz = new LibraryClass(1, "Processing Info", "Super Class Name");
    clazz.setProcessingInfo("Processing Info");

    // Act
    originalClassNameFilter.visitAnyClass(clazz);

    // Assert
    verify(acceptedClassVisitor).visitLibraryClass(isA(LibraryClass.class));
  }

  /**
   * Method under test: {@link OriginalClassNameFilter#visitAnyClass(Clazz)}
   */
  @Test
  void testVisitAnyClass4() {
    // Arrange
    ClassVisitor rejectedClassVisitor = mock(ClassVisitor.class);
    doNothing().when(rejectedClassVisitor).visitProgramClass(Mockito.<ProgramClass>any());
    OriginalClassNameFilter originalClassNameFilter = new OriginalClassNameFilter(mock(ClassVisitor.class),
        rejectedClassVisitor);

    NamedClass clazz = new NamedClass("Member Name");
    clazz.addExtraFeatureName("Processing Info");
    clazz.setProcessingInfo("Processing Info");

    // Act
    originalClassNameFilter.visitAnyClass(clazz);

    // Assert
    verify(rejectedClassVisitor).visitProgramClass(isA(ProgramClass.class));
  }
}
