package proguard.obfuscate;

import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.doNothing;
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
import proguard.testutils.cpa.NamedClass;

class OriginalClassNameFilterDiffblueTest {
  /**
   * Test {@link OriginalClassNameFilter#visitAnyClass(Clazz)}.
   * <p>
   * Method under test: {@link OriginalClassNameFilter#visitAnyClass(Clazz)}
   */
  @Test
  @DisplayName("Test visitAnyClass(Clazz)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void OriginalClassNameFilter.visitAnyClass(Clazz)"})
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
   * Test {@link OriginalClassNameFilter#visitAnyClass(Clazz)}.
   * <p>
   * Method under test: {@link OriginalClassNameFilter#visitAnyClass(Clazz)}
   */
  @Test
  @DisplayName("Test visitAnyClass(Clazz)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void OriginalClassNameFilter.visitAnyClass(Clazz)"})
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
   * Test {@link OriginalClassNameFilter#visitAnyClass(Clazz)}.
   * <p>
   * Method under test: {@link OriginalClassNameFilter#visitAnyClass(Clazz)}
   */
  @Test
  @DisplayName("Test visitAnyClass(Clazz)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void OriginalClassNameFilter.visitAnyClass(Clazz)"})
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
   * Test {@link OriginalClassNameFilter#visitAnyClass(Clazz)}.
   * <ul>
   *   <li>Then calls {@link ClassVisitor#visitProgramClass(ProgramClass)}.</li>
   * </ul>
   * <p>
   * Method under test: {@link OriginalClassNameFilter#visitAnyClass(Clazz)}
   */
  @Test
  @DisplayName("Test visitAnyClass(Clazz); then calls visitProgramClass(ProgramClass)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void OriginalClassNameFilter.visitAnyClass(Clazz)"})
  void testVisitAnyClass_thenCallsVisitProgramClass() {
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
