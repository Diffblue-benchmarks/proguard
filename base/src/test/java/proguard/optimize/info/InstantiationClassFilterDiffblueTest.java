package proguard.optimize.info;

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
import proguard.classfile.visitor.ClassVisitor;

class InstantiationClassFilterDiffblueTest {
  /**
   * Test {@link InstantiationClassFilter#visitAnyClass(Clazz)}.
   * <ul>
   *   <li>Given {@link ClassOptimizationInfo} (default constructor).</li>
   *   <li>Then calls {@link ClassVisitor#visitLibraryClass(LibraryClass)}.</li>
   * </ul>
   * <p>
   * Method under test: {@link InstantiationClassFilter#visitAnyClass(Clazz)}
   */
  @Test
  @DisplayName("Test visitAnyClass(Clazz); given ClassOptimizationInfo (default constructor); then calls visitLibraryClass(LibraryClass)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void InstantiationClassFilter.visitAnyClass(Clazz)"})
  void testVisitAnyClass_givenClassOptimizationInfo_thenCallsVisitLibraryClass() {
    // Arrange
    ClassVisitor classVisitor = mock(ClassVisitor.class);
    doNothing().when(classVisitor).visitLibraryClass(Mockito.<LibraryClass>any());
    InstantiationClassFilter instantiationClassFilter = new InstantiationClassFilter(classVisitor);

    LibraryClass clazz = new LibraryClass();
    clazz.setProcessingInfo(new ClassOptimizationInfo());

    // Act
    instantiationClassFilter.visitAnyClass(clazz);

    // Assert
    verify(classVisitor).visitLibraryClass(isA(LibraryClass.class));
  }
}
