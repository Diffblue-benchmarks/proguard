package proguard.optimize.peephole;

import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.doNothing;
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
import proguard.classfile.constant.ClassConstant;
import proguard.classfile.constant.Constant;
import proguard.classfile.constant.DoubleConstant;
import proguard.optimize.info.ClassOptimizationInfo;
import proguard.optimize.info.ProgramClassOptimizationInfo;

class WrapperClassMergerDiffblueTest {
  /**
   * Test {@link WrapperClassMerger#visitProgramClass(ProgramClass)}.
   *
   * <p>Method under test: {@link WrapperClassMerger#visitProgramClass(ProgramClass)}
   */
  @Test
  @DisplayName("Test visitProgramClass(ProgramClass)")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void WrapperClassMerger.visitProgramClass(ProgramClass)"})
  void testVisitProgramClass() {
    // Arrange
    WrapperClassMerger wrapperClassMerger = new WrapperClassMerger(true);
    ClassOptimizationInfo classOptimizationInfo = mock(ClassOptimizationInfo.class);
    when(classOptimizationInfo.getWrappedClass()).thenReturn(new LibraryClass());
    ProgramClass programClass = mock(ProgramClass.class);
    when(programClass.getProcessingInfo()).thenReturn(classOptimizationInfo);
    when(programClass.getSuperName()).thenReturn("java/lang/Object");

    // Act
    wrapperClassMerger.visitProgramClass(programClass);

    // Assert
    verify(programClass).getSuperName();
    verify(classOptimizationInfo).getWrappedClass();
    verify(programClass).getProcessingInfo();
  }

  /**
   * Test {@link WrapperClassMerger#visitProgramClass(ProgramClass)}.
   *
   * <p>Method under test: {@link WrapperClassMerger#visitProgramClass(ProgramClass)}
   */
  @Test
  @DisplayName("Test visitProgramClass(ProgramClass)")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void WrapperClassMerger.visitProgramClass(ProgramClass)"})
  void testVisitProgramClass2() {
    // Arrange
    WrapperClassMerger wrapperClassMerger = new WrapperClassMerger(true);
    ClassOptimizationInfo classOptimizationInfo = mock(ClassOptimizationInfo.class);
    when(classOptimizationInfo.isKept()).thenReturn(false);
    when(classOptimizationInfo.getWrappedClass())
        .thenReturn(
            new ProgramClass(
                1,
                3,
                new Constant[] {new ClassConstant()},
                1,
                1,
                1,
                "java/lang/Object",
                1,
                new ClassOptimizationInfo()));
    ProgramClass programClass = mock(ProgramClass.class);
    when(programClass.getProcessingInfo()).thenReturn(classOptimizationInfo);
    when(programClass.getSuperName()).thenReturn("java/lang/Object");

    // Act
    wrapperClassMerger.visitProgramClass(programClass);

    // Assert
    verify(programClass).getSuperName();
    verify(classOptimizationInfo).getWrappedClass();
    verify(classOptimizationInfo).isKept();
    verify(programClass, atLeast(1)).getProcessingInfo();
  }

  /**
   * Test {@link WrapperClassMerger#visitProgramClass(ProgramClass)}.
   *
   * <p>Method under test: {@link WrapperClassMerger#visitProgramClass(ProgramClass)}
   */
  @Test
  @DisplayName("Test visitProgramClass(ProgramClass)")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void WrapperClassMerger.visitProgramClass(ProgramClass)"})
  void testVisitProgramClass3() {
    // Arrange
    WrapperClassMerger wrapperClassMerger = new WrapperClassMerger(true);
    LibraryClass libraryClass = mock(LibraryClass.class);
    when(libraryClass.getProcessingInfo()).thenReturn(new ClassOptimizationInfo());
    doNothing().when(libraryClass).addSubClass(Mockito.<Clazz>any());
    libraryClass.addSubClass(new LibraryClass());
    ClassOptimizationInfo classOptimizationInfo = mock(ClassOptimizationInfo.class);
    when(classOptimizationInfo.isKept()).thenReturn(false);
    when(classOptimizationInfo.getTargetClass()).thenReturn(libraryClass);
    ClassConstant classConstant = new ClassConstant();
    when(classOptimizationInfo.getWrappedClass())
        .thenReturn(
            new ProgramClass(
                1,
                3,
                new Constant[] {classConstant, new DoubleConstant()},
                1,
                1,
                1,
                "java/lang/Object",
                1,
                null));
    ProgramClass programClass = mock(ProgramClass.class);
    when(programClass.getProcessingInfo()).thenReturn(classOptimizationInfo);
    when(programClass.getSuperName()).thenReturn("java/lang/Object");

    // Act
    wrapperClassMerger.visitProgramClass(programClass);

    // Assert
    verify(libraryClass).addSubClass(isA(Clazz.class));
    verify(programClass).getSuperName();
    verify(classOptimizationInfo).getTargetClass();
    verify(classOptimizationInfo).getWrappedClass();
    verify(classOptimizationInfo).isKept();
    verify(libraryClass).getProcessingInfo();
    verify(programClass, atLeast(1)).getProcessingInfo();
  }

  /**
   * Test {@link WrapperClassMerger#visitProgramClass(ProgramClass)}.
   *
   * <p>Method under test: {@link WrapperClassMerger#visitProgramClass(ProgramClass)}
   */
  @Test
  @DisplayName("Test visitProgramClass(ProgramClass)")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void WrapperClassMerger.visitProgramClass(ProgramClass)"})
  void testVisitProgramClass4() {
    // Arrange
    WrapperClassMerger wrapperClassMerger = new WrapperClassMerger(true);
    LibraryClass libraryClass = mock(LibraryClass.class);
    when(libraryClass.getProcessingInfo()).thenReturn(new ProgramClassOptimizationInfo());
    doNothing().when(libraryClass).addSubClass(Mockito.<Clazz>any());
    libraryClass.addSubClass(new LibraryClass());
    ClassOptimizationInfo classOptimizationInfo = mock(ClassOptimizationInfo.class);
    when(classOptimizationInfo.isKept()).thenReturn(false);
    when(classOptimizationInfo.getTargetClass()).thenReturn(libraryClass);
    ClassConstant classConstant = new ClassConstant();
    when(classOptimizationInfo.getWrappedClass())
        .thenReturn(
            new ProgramClass(
                1,
                3,
                new Constant[] {classConstant, new DoubleConstant()},
                1,
                1,
                1,
                "java/lang/Object",
                1,
                null));
    ProgramClass programClass = mock(ProgramClass.class);
    when(programClass.getProcessingInfo()).thenReturn(classOptimizationInfo);
    when(programClass.getSuperName()).thenReturn("java/lang/Object");

    // Act
    wrapperClassMerger.visitProgramClass(programClass);

    // Assert
    verify(libraryClass).addSubClass(isA(Clazz.class));
    verify(programClass).getSuperName();
    verify(classOptimizationInfo).getTargetClass();
    verify(classOptimizationInfo).getWrappedClass();
    verify(classOptimizationInfo).isKept();
    verify(libraryClass).getProcessingInfo();
    verify(programClass, atLeast(1)).getProcessingInfo();
  }

  /**
   * Test {@link WrapperClassMerger#visitProgramClass(ProgramClass)}.
   *
   * <ul>
   *   <li>Given {@link ClassOptimizationInfo} (default constructor).
   * </ul>
   *
   * <p>Method under test: {@link WrapperClassMerger#visitProgramClass(ProgramClass)}
   */
  @Test
  @DisplayName(
      "Test visitProgramClass(ProgramClass); given ClassOptimizationInfo (default constructor)")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void WrapperClassMerger.visitProgramClass(ProgramClass)"})
  void testVisitProgramClass_givenClassOptimizationInfo() {
    // Arrange
    WrapperClassMerger wrapperClassMerger = new WrapperClassMerger(true);
    ProgramClass programClass = mock(ProgramClass.class);
    when(programClass.getProcessingInfo()).thenReturn(new ClassOptimizationInfo());
    when(programClass.getSuperName()).thenReturn("java/lang/Object");

    // Act
    wrapperClassMerger.visitProgramClass(programClass);

    // Assert
    verify(programClass).getSuperName();
    verify(programClass).getProcessingInfo();
  }

  /**
   * Test {@link WrapperClassMerger#visitProgramClass(ProgramClass)}.
   *
   * <ul>
   *   <li>Given {@link ClassOptimizationInfo} {@link ClassOptimizationInfo#isKept()} return {@code
   *       true}.
   *   <li>Then calls {@link ClassOptimizationInfo#isKept()}.
   * </ul>
   *
   * <p>Method under test: {@link WrapperClassMerger#visitProgramClass(ProgramClass)}
   */
  @Test
  @DisplayName(
      "Test visitProgramClass(ProgramClass); given ClassOptimizationInfo isKept() return 'true'; then calls isKept()")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void WrapperClassMerger.visitProgramClass(ProgramClass)"})
  void testVisitProgramClass_givenClassOptimizationInfoIsKeptReturnTrue_thenCallsIsKept() {
    // Arrange
    WrapperClassMerger wrapperClassMerger = new WrapperClassMerger(true);
    ClassOptimizationInfo classOptimizationInfo = mock(ClassOptimizationInfo.class);
    when(classOptimizationInfo.isKept()).thenReturn(true);
    when(classOptimizationInfo.getWrappedClass()).thenReturn(new ProgramClass());
    ProgramClass programClass = mock(ProgramClass.class);
    when(programClass.getProcessingInfo()).thenReturn(classOptimizationInfo);
    when(programClass.getSuperName()).thenReturn("java/lang/Object");

    // Act
    wrapperClassMerger.visitProgramClass(programClass);

    // Assert
    verify(programClass).getSuperName();
    verify(classOptimizationInfo).getWrappedClass();
    verify(classOptimizationInfo).isKept();
    verify(programClass, atLeast(1)).getProcessingInfo();
  }

  /**
   * Test {@link WrapperClassMerger#visitProgramClass(ProgramClass)}.
   *
   * <ul>
   *   <li>Given {@link ProgramClassOptimizationInfo} (default constructor).
   * </ul>
   *
   * <p>Method under test: {@link WrapperClassMerger#visitProgramClass(ProgramClass)}
   */
  @Test
  @DisplayName(
      "Test visitProgramClass(ProgramClass); given ProgramClassOptimizationInfo (default constructor)")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void WrapperClassMerger.visitProgramClass(ProgramClass)"})
  void testVisitProgramClass_givenProgramClassOptimizationInfo() {
    // Arrange
    WrapperClassMerger wrapperClassMerger = new WrapperClassMerger(true);
    ProgramClass programClass = mock(ProgramClass.class);
    when(programClass.getProcessingInfo()).thenReturn(new ProgramClassOptimizationInfo());
    when(programClass.getSuperName()).thenReturn("java/lang/Object");

    // Act
    wrapperClassMerger.visitProgramClass(programClass);

    // Assert
    verify(programClass).getSuperName();
    verify(programClass).getProcessingInfo();
  }
}
