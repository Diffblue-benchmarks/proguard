package proguard.optimize.peephole;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
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
import proguard.optimize.info.ClassOptimizationInfo;
import proguard.optimize.info.ProgramClassOptimizationInfo;

class RetargetedClassFilterDiffblueTest {
  /**
   * Test {@link RetargetedClassFilter#visitAnyClass(Clazz)}.
   * <ul>
   *   <li>When {@link LibraryClass#LibraryClass()}.</li>
   *   <li>Then throw {@link UnsupportedOperationException}.</li>
   * </ul>
   * <p>
   * Method under test: {@link RetargetedClassFilter#visitAnyClass(Clazz)}
   */
  @Test
  @DisplayName("Test visitAnyClass(Clazz); when LibraryClass(); then throw UnsupportedOperationException")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void proguard.optimize.peephole.RetargetedClassFilter.visitAnyClass(proguard.classfile.Clazz)"})
  void testVisitAnyClass_whenLibraryClass_thenThrowUnsupportedOperationException() {
    // Arrange
    RetargetedClassFilter retargetedClassFilter = new RetargetedClassFilter(mock(ClassVisitor.class),
        mock(ClassVisitor.class));

    // Act and Assert
    assertThrows(UnsupportedOperationException.class, () -> retargetedClassFilter.visitAnyClass(new LibraryClass()));
  }

  /**
   * Test {@link RetargetedClassFilter#visitProgramClass(ProgramClass)}.
   * <p>
   * Method under test: {@link RetargetedClassFilter#visitProgramClass(ProgramClass)}
   */
  @Test
  @DisplayName("Test visitProgramClass(ProgramClass)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "void proguard.optimize.peephole.RetargetedClassFilter.visitProgramClass(proguard.classfile.ProgramClass)"})
  void testVisitProgramClass() {
    // Arrange
    ClassVisitor otherClassVisitor = mock(ClassVisitor.class);
    doNothing().when(otherClassVisitor).visitProgramClass(Mockito.<ProgramClass>any());
    RetargetedClassFilter retargetedClassFilter = new RetargetedClassFilter(mock(ClassVisitor.class),
        otherClassVisitor);
    Constant constant = mock(Constant.class);
    doNothing().when(constant).addProcessingFlags((int[]) Mockito.any());
    constant.addProcessingFlags(2, 1, 2, 1);

    // Act
    retargetedClassFilter.visitProgramClass(
        new ProgramClass(1, 3, new Constant[]{constant}, 1, 1, 1, "Feature Name", 1, new ClassOptimizationInfo()));

    // Assert
    verify(otherClassVisitor).visitProgramClass(isA(ProgramClass.class));
    verify(constant).addProcessingFlags((int[]) Mockito.any());
  }

  /**
   * Test {@link RetargetedClassFilter#visitProgramClass(ProgramClass)}.
   * <p>
   * Method under test: {@link RetargetedClassFilter#visitProgramClass(ProgramClass)}
   */
  @Test
  @DisplayName("Test visitProgramClass(ProgramClass)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "void proguard.optimize.peephole.RetargetedClassFilter.visitProgramClass(proguard.classfile.ProgramClass)"})
  void testVisitProgramClass2() {
    // Arrange
    RetargetedClassFilter retargetedClassFilter = new RetargetedClassFilter(mock(ClassVisitor.class));
    Constant constant = mock(Constant.class);
    doNothing().when(constant).addProcessingFlags((int[]) Mockito.any());
    constant.addProcessingFlags(2, 1, 2, 1);

    // Act
    retargetedClassFilter.visitProgramClass(
        new ProgramClass(1, 3, new Constant[]{constant}, 1, 1, 1, "Feature Name", 1, new ClassOptimizationInfo()));

    // Assert
    verify(constant).addProcessingFlags((int[]) Mockito.any());
  }

  /**
   * Test {@link RetargetedClassFilter#visitProgramClass(ProgramClass)}.
   * <p>
   * Method under test: {@link RetargetedClassFilter#visitProgramClass(ProgramClass)}
   */
  @Test
  @DisplayName("Test visitProgramClass(ProgramClass)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "void proguard.optimize.peephole.RetargetedClassFilter.visitProgramClass(proguard.classfile.ProgramClass)"})
  void testVisitProgramClass3() {
    // Arrange
    ClassVisitor otherClassVisitor = mock(ClassVisitor.class);
    doNothing().when(otherClassVisitor).visitProgramClass(Mockito.<ProgramClass>any());
    RetargetedClassFilter retargetedClassFilter = new RetargetedClassFilter(mock(ClassVisitor.class),
        otherClassVisitor);
    Constant constant = mock(Constant.class);
    doNothing().when(constant).addProcessingFlags((int[]) Mockito.any());
    constant.addProcessingFlags(2, 1, 2, 1);

    // Act
    retargetedClassFilter.visitProgramClass(new ProgramClass(1, 3, new Constant[]{constant}, 1, 1, 1, "Feature Name", 1,
        new ProgramClassOptimizationInfo()));

    // Assert
    verify(otherClassVisitor).visitProgramClass(isA(ProgramClass.class));
    verify(constant).addProcessingFlags((int[]) Mockito.any());
  }

  /**
   * Test {@link RetargetedClassFilter#visitProgramClass(ProgramClass)}.
   * <ul>
   *   <li>Then calls {@link ClassOptimizationInfo#getTargetClass()}.</li>
   * </ul>
   * <p>
   * Method under test: {@link RetargetedClassFilter#visitProgramClass(ProgramClass)}
   */
  @Test
  @DisplayName("Test visitProgramClass(ProgramClass); then calls getTargetClass()")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "void proguard.optimize.peephole.RetargetedClassFilter.visitProgramClass(proguard.classfile.ProgramClass)"})
  void testVisitProgramClass_thenCallsGetTargetClass() {
    // Arrange
    ClassVisitor retargetedClassVisitor = mock(ClassVisitor.class);
    doNothing().when(retargetedClassVisitor).visitProgramClass(Mockito.<ProgramClass>any());
    ClassVisitor otherClassVisitor = mock(ClassVisitor.class);
    doNothing().when(otherClassVisitor).visitProgramClass(Mockito.<ProgramClass>any());
    RetargetedClassFilter retargetedClassFilter = new RetargetedClassFilter(retargetedClassVisitor, otherClassVisitor);
    Constant constant = mock(Constant.class);
    doNothing().when(constant).addProcessingFlags((int[]) Mockito.any());
    constant.addProcessingFlags(2, 1, 2, 1);
    LibraryClass libraryClass = mock(LibraryClass.class);
    when(libraryClass.getProcessingInfo()).thenReturn(new ClassOptimizationInfo());
    ClassOptimizationInfo classOptimizationInfo = mock(ClassOptimizationInfo.class);
    when(classOptimizationInfo.getTargetClass()).thenReturn(libraryClass);

    // Act
    retargetedClassFilter.visitProgramClass(
        new ProgramClass(1, 3, new Constant[]{constant}, 1, 1, 1, "Feature Name", 1, classOptimizationInfo));

    // Assert
    verify(retargetedClassVisitor).visitProgramClass(isA(ProgramClass.class));
    verify(classOptimizationInfo).getTargetClass();
    verify(constant).addProcessingFlags((int[]) Mockito.any());
    verify(libraryClass).getProcessingInfo();
  }

  /**
   * Test {@link RetargetedClassFilter#visitProgramClass(ProgramClass)}.
   * <ul>
   *   <li>Then throw {@link UnsupportedOperationException}.</li>
   * </ul>
   * <p>
   * Method under test: {@link RetargetedClassFilter#visitProgramClass(ProgramClass)}
   */
  @Test
  @DisplayName("Test visitProgramClass(ProgramClass); then throw UnsupportedOperationException")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "void proguard.optimize.peephole.RetargetedClassFilter.visitProgramClass(proguard.classfile.ProgramClass)"})
  void testVisitProgramClass_thenThrowUnsupportedOperationException() {
    // Arrange
    ClassVisitor retargetedClassVisitor = mock(ClassVisitor.class);
    doThrow(new UnsupportedOperationException("foo")).when(retargetedClassVisitor)
        .visitProgramClass(Mockito.<ProgramClass>any());
    RetargetedClassFilter retargetedClassFilter = new RetargetedClassFilter(retargetedClassVisitor);
    Constant constant = mock(Constant.class);
    doNothing().when(constant).addProcessingFlags((int[]) Mockito.any());
    constant.addProcessingFlags(2, 1, 2, 1);
    LibraryClass libraryClass = mock(LibraryClass.class);
    when(libraryClass.getProcessingInfo()).thenReturn(new ClassOptimizationInfo());
    ClassOptimizationInfo classOptimizationInfo = mock(ClassOptimizationInfo.class);
    when(classOptimizationInfo.getTargetClass()).thenReturn(libraryClass);

    // Act and Assert
    assertThrows(UnsupportedOperationException.class, () -> retargetedClassFilter.visitProgramClass(
        new ProgramClass(1, 3, new Constant[]{constant}, 1, 1, 1, "Feature Name", 1, classOptimizationInfo)));
    verify(retargetedClassVisitor).visitProgramClass(isA(ProgramClass.class));
    verify(classOptimizationInfo).getTargetClass();
    verify(constant).addProcessingFlags((int[]) Mockito.any());
    verify(libraryClass).getProcessingInfo();
  }

  /**
   * Test {@link RetargetedClassFilter#visitLibraryClass(LibraryClass)}.
   * <ul>
   *   <li>Given {@link ClassVisitor} {@link ClassVisitor#visitLibraryClass(LibraryClass)} does nothing.</li>
   * </ul>
   * <p>
   * Method under test: {@link RetargetedClassFilter#visitLibraryClass(LibraryClass)}
   */
  @Test
  @DisplayName("Test visitLibraryClass(LibraryClass); given ClassVisitor visitLibraryClass(LibraryClass) does nothing")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "void proguard.optimize.peephole.RetargetedClassFilter.visitLibraryClass(proguard.classfile.LibraryClass)"})
  void testVisitLibraryClass_givenClassVisitorVisitLibraryClassDoesNothing() {
    // Arrange
    ClassVisitor otherClassVisitor = mock(ClassVisitor.class);
    doNothing().when(otherClassVisitor).visitLibraryClass(Mockito.<LibraryClass>any());
    RetargetedClassFilter retargetedClassFilter = new RetargetedClassFilter(mock(ClassVisitor.class),
        otherClassVisitor);

    // Act
    retargetedClassFilter.visitLibraryClass(new LibraryClass());

    // Assert
    verify(otherClassVisitor).visitLibraryClass(isA(LibraryClass.class));
  }

  /**
   * Test {@link RetargetedClassFilter#visitLibraryClass(LibraryClass)}.
   * <ul>
   *   <li>Then throw {@link UnsupportedOperationException}.</li>
   * </ul>
   * <p>
   * Method under test: {@link RetargetedClassFilter#visitLibraryClass(LibraryClass)}
   */
  @Test
  @DisplayName("Test visitLibraryClass(LibraryClass); then throw UnsupportedOperationException")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "void proguard.optimize.peephole.RetargetedClassFilter.visitLibraryClass(proguard.classfile.LibraryClass)"})
  void testVisitLibraryClass_thenThrowUnsupportedOperationException() {
    // Arrange
    ClassVisitor otherClassVisitor = mock(ClassVisitor.class);
    doThrow(new UnsupportedOperationException("foo")).when(otherClassVisitor)
        .visitLibraryClass(Mockito.<LibraryClass>any());
    RetargetedClassFilter retargetedClassFilter = new RetargetedClassFilter(mock(ClassVisitor.class),
        otherClassVisitor);

    // Act and Assert
    assertThrows(UnsupportedOperationException.class,
        () -> retargetedClassFilter.visitLibraryClass(new LibraryClass()));
    verify(otherClassVisitor).visitLibraryClass(isA(LibraryClass.class));
  }
}
