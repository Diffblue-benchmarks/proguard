package proguard.optimize.info;

import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.doNothing;
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
import proguard.classfile.LibraryMember;
import proguard.classfile.LibraryMethod;
import proguard.classfile.ProgramClass;
import proguard.classfile.ProgramMember;
import proguard.classfile.ProgramMethod;
import proguard.classfile.visitor.MemberVisitor;
import proguard.fixer.kotlin.KotlinAnnotationCounter;

class SideEffectMethodFilterDiffblueTest {
  /**
   * Test {@link SideEffectMethodFilter#visitProgramMethod(ProgramClass, ProgramMethod)}.
   * <ul>
   *   <li>Given {@link MethodOptimizationInfo} {@link MethodOptimizationInfo#hasSideEffects()} return {@code false}.</li>
   * </ul>
   * <p>
   * Method under test: {@link SideEffectMethodFilter#visitProgramMethod(ProgramClass, ProgramMethod)}
   */
  @Test
  @DisplayName("Test visitProgramMethod(ProgramClass, ProgramMethod); given MethodOptimizationInfo hasSideEffects() return 'false'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "void proguard.optimize.info.SideEffectMethodFilter.visitProgramMethod(proguard.classfile.ProgramClass, proguard.classfile.ProgramMethod)"})
  void testVisitProgramMethod_givenMethodOptimizationInfoHasSideEffectsReturnFalse() {
    // Arrange
    SideEffectMethodFilter sideEffectMethodFilter = new SideEffectMethodFilter(mock(MemberVisitor.class));
    ProgramClass programClass = new ProgramClass();
    MethodOptimizationInfo methodOptimizationInfo = mock(MethodOptimizationInfo.class);
    when(methodOptimizationInfo.hasSideEffects()).thenReturn(false);
    ProgramMethod programMethod = mock(ProgramMethod.class);
    when(programMethod.getProcessingInfo()).thenReturn(methodOptimizationInfo);

    // Act
    sideEffectMethodFilter.visitProgramMethod(programClass, programMethod);

    // Assert
    verify(methodOptimizationInfo).hasSideEffects();
    verify(programMethod, atLeast(1)).getProcessingInfo();
  }

  /**
   * Test {@link SideEffectMethodFilter#visitProgramMethod(ProgramClass, ProgramMethod)}.
   * <ul>
   *   <li>Given {@link MethodOptimizationInfo} {@link MethodOptimizationInfo#hasSideEffects()} return {@code true}.</li>
   * </ul>
   * <p>
   * Method under test: {@link SideEffectMethodFilter#visitProgramMethod(ProgramClass, ProgramMethod)}
   */
  @Test
  @DisplayName("Test visitProgramMethod(ProgramClass, ProgramMethod); given MethodOptimizationInfo hasSideEffects() return 'true'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "void proguard.optimize.info.SideEffectMethodFilter.visitProgramMethod(proguard.classfile.ProgramClass, proguard.classfile.ProgramMethod)"})
  void testVisitProgramMethod_givenMethodOptimizationInfoHasSideEffectsReturnTrue() {
    // Arrange
    MemberVisitor memberVisitor = mock(MemberVisitor.class);
    doNothing().when(memberVisitor).visitProgramMethod(Mockito.<ProgramClass>any(), Mockito.<ProgramMethod>any());
    SideEffectMethodFilter sideEffectMethodFilter = new SideEffectMethodFilter(memberVisitor);
    ProgramClass programClass = new ProgramClass();
    MethodOptimizationInfo methodOptimizationInfo = mock(MethodOptimizationInfo.class);
    when(methodOptimizationInfo.hasSideEffects()).thenReturn(true);
    ProgramMethod programMethod = mock(ProgramMethod.class);
    when(programMethod.getProcessingInfo()).thenReturn(methodOptimizationInfo);

    // Act
    sideEffectMethodFilter.visitProgramMethod(programClass, programMethod);

    // Assert
    verify(memberVisitor).visitProgramMethod(isA(ProgramClass.class), isA(ProgramMethod.class));
    verify(methodOptimizationInfo).hasSideEffects();
    verify(programMethod, atLeast(1)).getProcessingInfo();
  }

  /**
   * Test {@link SideEffectMethodFilter#visitProgramMethod(ProgramClass, ProgramMethod)}.
   * <ul>
   *   <li>Then calls {@link ProgramMember#accept(Clazz, MemberVisitor)}.</li>
   * </ul>
   * <p>
   * Method under test: {@link SideEffectMethodFilter#visitProgramMethod(ProgramClass, ProgramMethod)}
   */
  @Test
  @DisplayName("Test visitProgramMethod(ProgramClass, ProgramMethod); then calls accept(Clazz, MemberVisitor)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "void proguard.optimize.info.SideEffectMethodFilter.visitProgramMethod(proguard.classfile.ProgramClass, proguard.classfile.ProgramMethod)"})
  void testVisitProgramMethod_thenCallsAccept() {
    // Arrange
    SideEffectMethodFilter sideEffectMethodFilter = new SideEffectMethodFilter(new KotlinAnnotationCounter());
    ProgramClass programClass = new ProgramClass();
    ProgramMethod programMethod = mock(ProgramMethod.class);
    doNothing().when(programMethod).accept(Mockito.<Clazz>any(), Mockito.<MemberVisitor>any());
    when(programMethod.getProcessingInfo()).thenReturn(new MethodOptimizationInfo());

    // Act
    sideEffectMethodFilter.visitProgramMethod(programClass, programMethod);

    // Assert
    verify(programMethod).accept(isA(Clazz.class), isA(MemberVisitor.class));
    verify(programMethod, atLeast(1)).getProcessingInfo();
  }

  /**
   * Test {@link SideEffectMethodFilter#visitProgramMethod(ProgramClass, ProgramMethod)}.
   * <ul>
   *   <li>Then calls {@link MemberVisitor#visitProgramMethod(ProgramClass, ProgramMethod)}.</li>
   * </ul>
   * <p>
   * Method under test: {@link SideEffectMethodFilter#visitProgramMethod(ProgramClass, ProgramMethod)}
   */
  @Test
  @DisplayName("Test visitProgramMethod(ProgramClass, ProgramMethod); then calls visitProgramMethod(ProgramClass, ProgramMethod)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "void proguard.optimize.info.SideEffectMethodFilter.visitProgramMethod(proguard.classfile.ProgramClass, proguard.classfile.ProgramMethod)"})
  void testVisitProgramMethod_thenCallsVisitProgramMethod() {
    // Arrange
    MemberVisitor memberVisitor = mock(MemberVisitor.class);
    doNothing().when(memberVisitor).visitProgramMethod(Mockito.<ProgramClass>any(), Mockito.<ProgramMethod>any());
    SideEffectMethodFilter sideEffectMethodFilter = new SideEffectMethodFilter(memberVisitor);
    ProgramClass programClass = new ProgramClass();
    ProgramMethod programMethod = mock(ProgramMethod.class);
    when(programMethod.getProcessingInfo()).thenReturn(new MethodOptimizationInfo());

    // Act
    sideEffectMethodFilter.visitProgramMethod(programClass, programMethod);

    // Assert
    verify(memberVisitor).visitProgramMethod(isA(ProgramClass.class), isA(ProgramMethod.class));
    verify(programMethod, atLeast(1)).getProcessingInfo();
  }

  /**
   * Test {@link SideEffectMethodFilter#visitLibraryMethod(LibraryClass, LibraryMethod)}.
   * <ul>
   *   <li>Given {@link MethodOptimizationInfo} {@link MethodOptimizationInfo#hasSideEffects()} return {@code false}.</li>
   * </ul>
   * <p>
   * Method under test: {@link SideEffectMethodFilter#visitLibraryMethod(LibraryClass, LibraryMethod)}
   */
  @Test
  @DisplayName("Test visitLibraryMethod(LibraryClass, LibraryMethod); given MethodOptimizationInfo hasSideEffects() return 'false'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "void proguard.optimize.info.SideEffectMethodFilter.visitLibraryMethod(proguard.classfile.LibraryClass, proguard.classfile.LibraryMethod)"})
  void testVisitLibraryMethod_givenMethodOptimizationInfoHasSideEffectsReturnFalse() {
    // Arrange
    SideEffectMethodFilter sideEffectMethodFilter = new SideEffectMethodFilter(mock(MemberVisitor.class));
    LibraryClass libraryClass = new LibraryClass();
    MethodOptimizationInfo methodOptimizationInfo = mock(MethodOptimizationInfo.class);
    when(methodOptimizationInfo.hasSideEffects()).thenReturn(false);
    LibraryMethod libraryMethod = mock(LibraryMethod.class);
    when(libraryMethod.getProcessingInfo()).thenReturn(methodOptimizationInfo);

    // Act
    sideEffectMethodFilter.visitLibraryMethod(libraryClass, libraryMethod);

    // Assert
    verify(methodOptimizationInfo).hasSideEffects();
    verify(libraryMethod, atLeast(1)).getProcessingInfo();
  }

  /**
   * Test {@link SideEffectMethodFilter#visitLibraryMethod(LibraryClass, LibraryMethod)}.
   * <ul>
   *   <li>Given {@link MethodOptimizationInfo} {@link MethodOptimizationInfo#hasSideEffects()} return {@code true}.</li>
   * </ul>
   * <p>
   * Method under test: {@link SideEffectMethodFilter#visitLibraryMethod(LibraryClass, LibraryMethod)}
   */
  @Test
  @DisplayName("Test visitLibraryMethod(LibraryClass, LibraryMethod); given MethodOptimizationInfo hasSideEffects() return 'true'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "void proguard.optimize.info.SideEffectMethodFilter.visitLibraryMethod(proguard.classfile.LibraryClass, proguard.classfile.LibraryMethod)"})
  void testVisitLibraryMethod_givenMethodOptimizationInfoHasSideEffectsReturnTrue() {
    // Arrange
    MemberVisitor memberVisitor = mock(MemberVisitor.class);
    doNothing().when(memberVisitor).visitLibraryMethod(Mockito.<LibraryClass>any(), Mockito.<LibraryMethod>any());
    SideEffectMethodFilter sideEffectMethodFilter = new SideEffectMethodFilter(memberVisitor);
    LibraryClass libraryClass = new LibraryClass();
    MethodOptimizationInfo methodOptimizationInfo = mock(MethodOptimizationInfo.class);
    when(methodOptimizationInfo.hasSideEffects()).thenReturn(true);
    LibraryMethod libraryMethod = mock(LibraryMethod.class);
    when(libraryMethod.getProcessingInfo()).thenReturn(methodOptimizationInfo);

    // Act
    sideEffectMethodFilter.visitLibraryMethod(libraryClass, libraryMethod);

    // Assert
    verify(memberVisitor).visitLibraryMethod(isA(LibraryClass.class), isA(LibraryMethod.class));
    verify(methodOptimizationInfo).hasSideEffects();
    verify(libraryMethod, atLeast(1)).getProcessingInfo();
  }

  /**
   * Test {@link SideEffectMethodFilter#visitLibraryMethod(LibraryClass, LibraryMethod)}.
   * <ul>
   *   <li>Then calls {@link LibraryMember#accept(Clazz, MemberVisitor)}.</li>
   * </ul>
   * <p>
   * Method under test: {@link SideEffectMethodFilter#visitLibraryMethod(LibraryClass, LibraryMethod)}
   */
  @Test
  @DisplayName("Test visitLibraryMethod(LibraryClass, LibraryMethod); then calls accept(Clazz, MemberVisitor)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "void proguard.optimize.info.SideEffectMethodFilter.visitLibraryMethod(proguard.classfile.LibraryClass, proguard.classfile.LibraryMethod)"})
  void testVisitLibraryMethod_thenCallsAccept() {
    // Arrange
    SideEffectMethodFilter sideEffectMethodFilter = new SideEffectMethodFilter(new KotlinAnnotationCounter());
    LibraryClass libraryClass = new LibraryClass();
    LibraryMethod libraryMethod = mock(LibraryMethod.class);
    doNothing().when(libraryMethod).accept(Mockito.<Clazz>any(), Mockito.<MemberVisitor>any());
    when(libraryMethod.getProcessingInfo()).thenReturn(new MethodOptimizationInfo());

    // Act
    sideEffectMethodFilter.visitLibraryMethod(libraryClass, libraryMethod);

    // Assert
    verify(libraryMethod).accept(isA(Clazz.class), isA(MemberVisitor.class));
    verify(libraryMethod, atLeast(1)).getProcessingInfo();
  }

  /**
   * Test {@link SideEffectMethodFilter#visitLibraryMethod(LibraryClass, LibraryMethod)}.
   * <ul>
   *   <li>Then calls {@link MemberVisitor#visitLibraryMethod(LibraryClass, LibraryMethod)}.</li>
   * </ul>
   * <p>
   * Method under test: {@link SideEffectMethodFilter#visitLibraryMethod(LibraryClass, LibraryMethod)}
   */
  @Test
  @DisplayName("Test visitLibraryMethod(LibraryClass, LibraryMethod); then calls visitLibraryMethod(LibraryClass, LibraryMethod)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "void proguard.optimize.info.SideEffectMethodFilter.visitLibraryMethod(proguard.classfile.LibraryClass, proguard.classfile.LibraryMethod)"})
  void testVisitLibraryMethod_thenCallsVisitLibraryMethod() {
    // Arrange
    MemberVisitor memberVisitor = mock(MemberVisitor.class);
    doNothing().when(memberVisitor).visitLibraryMethod(Mockito.<LibraryClass>any(), Mockito.<LibraryMethod>any());
    SideEffectMethodFilter sideEffectMethodFilter = new SideEffectMethodFilter(memberVisitor);
    LibraryClass libraryClass = new LibraryClass();
    LibraryMethod libraryMethod = mock(LibraryMethod.class);
    when(libraryMethod.getProcessingInfo()).thenReturn(new MethodOptimizationInfo());

    // Act
    sideEffectMethodFilter.visitLibraryMethod(libraryClass, libraryMethod);

    // Assert
    verify(memberVisitor).visitLibraryMethod(isA(LibraryClass.class), isA(LibraryMethod.class));
    verify(libraryMethod, atLeast(1)).getProcessingInfo();
  }
}
