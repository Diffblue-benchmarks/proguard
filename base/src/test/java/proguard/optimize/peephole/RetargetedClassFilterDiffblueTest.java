package proguard.optimize.peephole;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import com.diffblue.cover.annotations.ManagedByDiffblue;
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
   *
   * <ul>
   *   <li>When {@link LibraryClass#LibraryClass()}.
   *   <li>Then throw {@link UnsupportedOperationException}.
   * </ul>
   *
   * <p>Method under test: {@link RetargetedClassFilter#visitAnyClass(Clazz)}
   */
  @Test
  @DisplayName(
      "Test visitAnyClass(Clazz); when LibraryClass(); then throw UnsupportedOperationException")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void RetargetedClassFilter.visitAnyClass(Clazz)"})
  void testVisitAnyClass_whenLibraryClass_thenThrowUnsupportedOperationException() {
    // Arrange
    RetargetedClassFilter retargetedClassFilter =
        new RetargetedClassFilter(mock(ClassVisitor.class), mock(ClassVisitor.class));

    // Act and Assert
    assertThrows(
        UnsupportedOperationException.class,
        () -> retargetedClassFilter.visitAnyClass(new LibraryClass()));
  }

  /**
   * Test {@link RetargetedClassFilter#visitProgramClass(ProgramClass)}.
   *
   * <p>Method under test: {@link RetargetedClassFilter#visitProgramClass(ProgramClass)}
   */
  @Test
  @DisplayName("Test visitProgramClass(ProgramClass)")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void RetargetedClassFilter.visitProgramClass(ProgramClass)"})
  void testVisitProgramClass() {
    // Arrange
    ClassVisitor otherClassVisitor = mock(ClassVisitor.class);
    doNothing().when(otherClassVisitor).visitProgramClass(Mockito.<ProgramClass>any());
    RetargetedClassFilter retargetedClassFilter =
        new RetargetedClassFilter(mock(ClassVisitor.class), otherClassVisitor);
    Constant constant = mock(Constant.class);
    doNothing().when(constant).addProcessingFlags((int[]) Mockito.any());
    constant.addProcessingFlags(2, 1, 2, 1);

    // Act
    retargetedClassFilter.visitProgramClass(
        new ProgramClass(
            1,
            3,
            new Constant[] {constant},
            1,
            1,
            1,
            "Feature Name",
            1,
            new ClassOptimizationInfo()));

    // Assert
    verify(otherClassVisitor).visitProgramClass(isA(ProgramClass.class));
    verify(constant).addProcessingFlags((int[]) Mockito.any());
  }

  /**
   * Test {@link RetargetedClassFilter#visitProgramClass(ProgramClass)}.
   *
   * <p>Method under test: {@link RetargetedClassFilter#visitProgramClass(ProgramClass)}
   */
  @Test
  @DisplayName("Test visitProgramClass(ProgramClass)")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void RetargetedClassFilter.visitProgramClass(ProgramClass)"})
  void testVisitProgramClass2() {
    // Arrange
    RetargetedClassFilter retargetedClassFilter =
        new RetargetedClassFilter(mock(ClassVisitor.class));
    Constant constant = mock(Constant.class);
    doNothing().when(constant).addProcessingFlags((int[]) Mockito.any());
    constant.addProcessingFlags(2, 1, 2, 1);

    // Act
    retargetedClassFilter.visitProgramClass(
        new ProgramClass(
            1,
            3,
            new Constant[] {constant},
            1,
            1,
            1,
            "Feature Name",
            1,
            new ClassOptimizationInfo()));

    // Assert
    verify(constant).addProcessingFlags((int[]) Mockito.any());
  }

  /**
   * Test {@link RetargetedClassFilter#visitProgramClass(ProgramClass)}.
   *
   * <p>Method under test: {@link RetargetedClassFilter#visitProgramClass(ProgramClass)}
   */
  @Test
  @DisplayName("Test visitProgramClass(ProgramClass)")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void RetargetedClassFilter.visitProgramClass(ProgramClass)"})
  void testVisitProgramClass3() {
    // Arrange
    ClassVisitor otherClassVisitor = mock(ClassVisitor.class);
    doNothing().when(otherClassVisitor).visitProgramClass(Mockito.<ProgramClass>any());
    RetargetedClassFilter retargetedClassFilter =
        new RetargetedClassFilter(mock(ClassVisitor.class), otherClassVisitor);
    Constant constant = mock(Constant.class);
    doNothing().when(constant).addProcessingFlags((int[]) Mockito.any());
    constant.addProcessingFlags(2, 1, 2, 1);

    // Act
    retargetedClassFilter.visitProgramClass(
        new ProgramClass(
            1,
            3,
            new Constant[] {constant},
            1,
            1,
            1,
            "Feature Name",
            1,
            new ProgramClassOptimizationInfo()));

    // Assert
    verify(otherClassVisitor).visitProgramClass(isA(ProgramClass.class));
    verify(constant).addProcessingFlags((int[]) Mockito.any());
  }

  /**
   * Test {@link RetargetedClassFilter#visitProgramClass(ProgramClass)}.
   *
   * <ul>
   *   <li>Given {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link RetargetedClassFilter#visitProgramClass(ProgramClass)}
   */
  @Test
  @DisplayName("Test visitProgramClass(ProgramClass); given 'null'")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void RetargetedClassFilter.visitProgramClass(ProgramClass)"})
  void testVisitProgramClass_givenNull() {
    // Arrange
    ClassVisitor otherClassVisitor = mock(ClassVisitor.class);
    doNothing().when(otherClassVisitor).visitProgramClass(Mockito.<ProgramClass>any());
    RetargetedClassFilter retargetedClassFilter =
        new RetargetedClassFilter(mock(ClassVisitor.class), otherClassVisitor);
    Constant constant = mock(Constant.class);
    doNothing().when(constant).addProcessingFlags((int[]) Mockito.any());
    constant.addProcessingFlags(2, 1, 2, 1);
    ClassOptimizationInfo classOptimizationInfo = mock(ClassOptimizationInfo.class);
    when(classOptimizationInfo.getTargetClass()).thenReturn(null);

    // Act
    retargetedClassFilter.visitProgramClass(
        new ProgramClass(
            1, 3, new Constant[] {constant}, 1, 1, 1, "Feature Name", 1, classOptimizationInfo));

    // Assert
    verify(otherClassVisitor).visitProgramClass(isA(ProgramClass.class));
    verify(classOptimizationInfo).getTargetClass();
    verify(constant).addProcessingFlags((int[]) Mockito.any());
  }

  /**
   * Test {@link RetargetedClassFilter#visitProgramClass(ProgramClass)}.
   *
   * <ul>
   *   <li>Then calls {@link LibraryClass#getProcessingInfo()}.
   * </ul>
   *
   * <p>Method under test: {@link RetargetedClassFilter#visitProgramClass(ProgramClass)}
   */
  @Test
  @DisplayName("Test visitProgramClass(ProgramClass); then calls getProcessingInfo()")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void RetargetedClassFilter.visitProgramClass(ProgramClass)"})
  void testVisitProgramClass_thenCallsGetProcessingInfo() {
    // Arrange
    ClassVisitor retargetedClassVisitor = mock(ClassVisitor.class);
    doNothing().when(retargetedClassVisitor).visitProgramClass(Mockito.<ProgramClass>any());
    RetargetedClassFilter retargetedClassFilter =
        new RetargetedClassFilter(retargetedClassVisitor, mock(ClassVisitor.class));
    Constant constant = mock(Constant.class);
    doNothing().when(constant).addProcessingFlags((int[]) Mockito.any());
    constant.addProcessingFlags(2, 1, 2, 1);
    LibraryClass libraryClass = mock(LibraryClass.class);
    when(libraryClass.getProcessingInfo()).thenReturn(new ClassOptimizationInfo());
    ClassOptimizationInfo classOptimizationInfo = mock(ClassOptimizationInfo.class);
    when(classOptimizationInfo.getTargetClass()).thenReturn(libraryClass);

    // Act
    retargetedClassFilter.visitProgramClass(
        new ProgramClass(
            1, 3, new Constant[] {constant}, 1, 1, 1, "Feature Name", 1, classOptimizationInfo));

    // Assert
    verify(retargetedClassVisitor).visitProgramClass(isA(ProgramClass.class));
    verify(classOptimizationInfo).getTargetClass();
    verify(constant).addProcessingFlags((int[]) Mockito.any());
    verify(libraryClass).getProcessingInfo();
  }

  /**
   * Test {@link RetargetedClassFilter#visitProgramClass(ProgramClass)}.
   *
   * <ul>
   *   <li>Then throw {@link UnsupportedOperationException}.
   * </ul>
   *
   * <p>Method under test: {@link RetargetedClassFilter#visitProgramClass(ProgramClass)}
   */
  @Test
  @DisplayName("Test visitProgramClass(ProgramClass); then throw UnsupportedOperationException")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void RetargetedClassFilter.visitProgramClass(ProgramClass)"})
  void testVisitProgramClass_thenThrowUnsupportedOperationException() {
    // Arrange
    ClassVisitor otherClassVisitor = mock(ClassVisitor.class);
    doThrow(new UnsupportedOperationException("foo"))
        .when(otherClassVisitor)
        .visitProgramClass(Mockito.<ProgramClass>any());
    RetargetedClassFilter retargetedClassFilter =
        new RetargetedClassFilter(mock(ClassVisitor.class), otherClassVisitor);
    Constant constant = mock(Constant.class);
    doNothing().when(constant).addProcessingFlags((int[]) Mockito.any());
    constant.addProcessingFlags(2, 1, 2, 1);

    // Act and Assert
    assertThrows(
        UnsupportedOperationException.class,
        () ->
            retargetedClassFilter.visitProgramClass(
                new ProgramClass(
                    1,
                    3,
                    new Constant[] {constant},
                    1,
                    1,
                    1,
                    "Feature Name",
                    1,
                    new ClassOptimizationInfo())));
    verify(otherClassVisitor).visitProgramClass(isA(ProgramClass.class));
    verify(constant).addProcessingFlags((int[]) Mockito.any());
  }

  /**
   * Test {@link RetargetedClassFilter#visitLibraryClass(LibraryClass)}.
   *
   * <ul>
   *   <li>Given {@link ClassVisitor} {@link ClassVisitor#visitLibraryClass(LibraryClass)} does
   *       nothing.
   * </ul>
   *
   * <p>Method under test: {@link RetargetedClassFilter#visitLibraryClass(LibraryClass)}
   */
  @Test
  @DisplayName(
      "Test visitLibraryClass(LibraryClass); given ClassVisitor visitLibraryClass(LibraryClass) does nothing")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void RetargetedClassFilter.visitLibraryClass(LibraryClass)"})
  void testVisitLibraryClass_givenClassVisitorVisitLibraryClassDoesNothing() {
    // Arrange
    ClassVisitor otherClassVisitor = mock(ClassVisitor.class);
    doNothing().when(otherClassVisitor).visitLibraryClass(Mockito.<LibraryClass>any());
    RetargetedClassFilter retargetedClassFilter =
        new RetargetedClassFilter(mock(ClassVisitor.class), otherClassVisitor);

    // Act
    retargetedClassFilter.visitLibraryClass(new LibraryClass());

    // Assert
    verify(otherClassVisitor).visitLibraryClass(isA(LibraryClass.class));
  }

  /**
   * Test {@link RetargetedClassFilter#visitLibraryClass(LibraryClass)}.
   *
   * <ul>
   *   <li>Then throw {@link UnsupportedOperationException}.
   * </ul>
   *
   * <p>Method under test: {@link RetargetedClassFilter#visitLibraryClass(LibraryClass)}
   */
  @Test
  @DisplayName("Test visitLibraryClass(LibraryClass); then throw UnsupportedOperationException")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void RetargetedClassFilter.visitLibraryClass(LibraryClass)"})
  void testVisitLibraryClass_thenThrowUnsupportedOperationException() {
    // Arrange
    ClassVisitor otherClassVisitor = mock(ClassVisitor.class);
    doThrow(new UnsupportedOperationException("foo"))
        .when(otherClassVisitor)
        .visitLibraryClass(Mockito.<LibraryClass>any());
    RetargetedClassFilter retargetedClassFilter =
        new RetargetedClassFilter(mock(ClassVisitor.class), otherClassVisitor);

    // Act and Assert
    assertThrows(
        UnsupportedOperationException.class,
        () -> retargetedClassFilter.visitLibraryClass(new LibraryClass()));
    verify(otherClassVisitor).visitLibraryClass(isA(LibraryClass.class));
  }
}
