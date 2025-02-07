package proguard.optimize;

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
import proguard.classfile.constant.Constant;
import proguard.classfile.visitor.ClassVisitor;
import proguard.optimize.info.ProgramClassOptimizationInfo;

class OptimizationInfoClassFilterDiffblueTest {
  /**
   * Test {@link OptimizationInfoClassFilter#visitAnyClass(Clazz)}.
   * <ul>
   *   <li>When {@link LibraryClass#LibraryClass()}.</li>
   *   <li>Then throw {@link UnsupportedOperationException}.</li>
   * </ul>
   * <p>
   * Method under test: {@link OptimizationInfoClassFilter#visitAnyClass(Clazz)}
   */
  @Test
  @DisplayName("Test visitAnyClass(Clazz); when LibraryClass(); then throw UnsupportedOperationException")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void proguard.optimize.OptimizationInfoClassFilter.visitAnyClass(proguard.classfile.Clazz)"})
  void testVisitAnyClass_whenLibraryClass_thenThrowUnsupportedOperationException() {
    // Arrange
    OptimizationInfoClassFilter optimizationInfoClassFilter = new OptimizationInfoClassFilter(mock(ClassVisitor.class));

    // Act and Assert
    assertThrows(UnsupportedOperationException.class,
        () -> optimizationInfoClassFilter.visitAnyClass(new LibraryClass()));
  }

  /**
   * Test {@link OptimizationInfoClassFilter#visitProgramClass(ProgramClass)}.
   * <ul>
   *   <li>Given {@link ClassVisitor} {@link ClassVisitor#visitProgramClass(ProgramClass)} does nothing.</li>
   * </ul>
   * <p>
   * Method under test: {@link OptimizationInfoClassFilter#visitProgramClass(ProgramClass)}
   */
  @Test
  @DisplayName("Test visitProgramClass(ProgramClass); given ClassVisitor visitProgramClass(ProgramClass) does nothing")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "void proguard.optimize.OptimizationInfoClassFilter.visitProgramClass(proguard.classfile.ProgramClass)"})
  void testVisitProgramClass_givenClassVisitorVisitProgramClassDoesNothing() {
    // Arrange
    ClassVisitor classVisitor = mock(ClassVisitor.class);
    doNothing().when(classVisitor).visitProgramClass(Mockito.<ProgramClass>any());
    OptimizationInfoClassFilter optimizationInfoClassFilter = new OptimizationInfoClassFilter(classVisitor);
    Constant constant = mock(Constant.class);
    doNothing().when(constant).addProcessingFlags((int[]) Mockito.any());
    constant.addProcessingFlags(2, 1, 2, 1);

    // Act
    optimizationInfoClassFilter.visitProgramClass(new ProgramClass(1, 3, new Constant[]{constant}, 1, 1, 1,
        "Feature Name", 1, new ProgramClassOptimizationInfo()));

    // Assert
    verify(classVisitor).visitProgramClass(isA(ProgramClass.class));
    verify(constant).addProcessingFlags((int[]) Mockito.any());
  }

  /**
   * Test {@link OptimizationInfoClassFilter#visitProgramClass(ProgramClass)}.
   * <ul>
   *   <li>Then throw {@link UnsupportedOperationException}.</li>
   * </ul>
   * <p>
   * Method under test: {@link OptimizationInfoClassFilter#visitProgramClass(ProgramClass)}
   */
  @Test
  @DisplayName("Test visitProgramClass(ProgramClass); then throw UnsupportedOperationException")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "void proguard.optimize.OptimizationInfoClassFilter.visitProgramClass(proguard.classfile.ProgramClass)"})
  void testVisitProgramClass_thenThrowUnsupportedOperationException() {
    // Arrange
    ClassVisitor classVisitor = mock(ClassVisitor.class);
    doThrow(new UnsupportedOperationException("foo")).when(classVisitor).visitProgramClass(Mockito.<ProgramClass>any());
    OptimizationInfoClassFilter optimizationInfoClassFilter = new OptimizationInfoClassFilter(classVisitor);
    Constant constant = mock(Constant.class);
    doNothing().when(constant).addProcessingFlags((int[]) Mockito.any());
    constant.addProcessingFlags(2, 1, 2, 1);

    // Act and Assert
    assertThrows(UnsupportedOperationException.class,
        () -> optimizationInfoClassFilter.visitProgramClass(new ProgramClass(1, 3, new Constant[]{constant}, 1, 1, 1,
            "Feature Name", 1, new ProgramClassOptimizationInfo())));
    verify(classVisitor).visitProgramClass(isA(ProgramClass.class));
    verify(constant).addProcessingFlags((int[]) Mockito.any());
  }

  /**
   * Test {@link OptimizationInfoClassFilter#visitLibraryClass(LibraryClass)}.
   * <ul>
   *   <li>Given {@link ClassVisitor} {@link ClassVisitor#visitLibraryClass(LibraryClass)} does nothing.</li>
   * </ul>
   * <p>
   * Method under test: {@link OptimizationInfoClassFilter#visitLibraryClass(LibraryClass)}
   */
  @Test
  @DisplayName("Test visitLibraryClass(LibraryClass); given ClassVisitor visitLibraryClass(LibraryClass) does nothing")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "void proguard.optimize.OptimizationInfoClassFilter.visitLibraryClass(proguard.classfile.LibraryClass)"})
  void testVisitLibraryClass_givenClassVisitorVisitLibraryClassDoesNothing() {
    // Arrange
    ClassVisitor classVisitor = mock(ClassVisitor.class);
    doNothing().when(classVisitor).visitLibraryClass(Mockito.<LibraryClass>any());
    OptimizationInfoClassFilter optimizationInfoClassFilter = new OptimizationInfoClassFilter(classVisitor);

    LibraryClass libraryClass = new LibraryClass();
    libraryClass.setProcessingInfo(new ProgramClassOptimizationInfo());

    // Act
    optimizationInfoClassFilter.visitLibraryClass(libraryClass);

    // Assert
    verify(classVisitor).visitLibraryClass(isA(LibraryClass.class));
  }

  /**
   * Test {@link OptimizationInfoClassFilter#visitLibraryClass(LibraryClass)}.
   * <ul>
   *   <li>Then throw {@link UnsupportedOperationException}.</li>
   * </ul>
   * <p>
   * Method under test: {@link OptimizationInfoClassFilter#visitLibraryClass(LibraryClass)}
   */
  @Test
  @DisplayName("Test visitLibraryClass(LibraryClass); then throw UnsupportedOperationException")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "void proguard.optimize.OptimizationInfoClassFilter.visitLibraryClass(proguard.classfile.LibraryClass)"})
  void testVisitLibraryClass_thenThrowUnsupportedOperationException() {
    // Arrange
    ClassVisitor classVisitor = mock(ClassVisitor.class);
    doThrow(new UnsupportedOperationException("foo")).when(classVisitor).visitLibraryClass(Mockito.<LibraryClass>any());
    OptimizationInfoClassFilter optimizationInfoClassFilter = new OptimizationInfoClassFilter(classVisitor);

    LibraryClass libraryClass = new LibraryClass();
    libraryClass.setProcessingInfo(new ProgramClassOptimizationInfo());

    // Act and Assert
    assertThrows(UnsupportedOperationException.class,
        () -> optimizationInfoClassFilter.visitLibraryClass(libraryClass));
    verify(classVisitor).visitLibraryClass(isA(LibraryClass.class));
  }
}
