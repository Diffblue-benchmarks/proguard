package proguard.optimize;

import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.ArgumentMatchers.isNull;
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
import proguard.classfile.LibraryField;
import proguard.classfile.ProgramClass;
import proguard.classfile.ProgramField;
import proguard.classfile.ProgramMethod;
import proguard.classfile.visitor.MemberVisitor;
import proguard.fixer.kotlin.KotlinAnnotationCounter;
import proguard.obfuscate.MemberNameCleaner;
import proguard.optimize.info.MethodOptimizationInfo;
import proguard.optimize.info.ProgramFieldOptimizationInfo;
import proguard.optimize.info.ProgramMethodOptimizationInfo;

class OptimizationInfoMemberFilterDiffblueTest {
  /**
   * Test {@link OptimizationInfoMemberFilter#visitProgramField(ProgramClass, ProgramField)}.
   *
   * <ul>
   *   <li>Then calls {@link ProgramField#setProcessingInfo(Object)}.
   * </ul>
   *
   * <p>Method under test: {@link OptimizationInfoMemberFilter#visitProgramField(ProgramClass,
   * ProgramField)}
   */
  @Test
  @DisplayName(
      "Test visitProgramField(ProgramClass, ProgramField); then calls setProcessingInfo(Object)")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void OptimizationInfoMemberFilter.visitProgramField(ProgramClass, ProgramField)"
  })
  void testVisitProgramField_thenCallsSetProcessingInfo() {
    // Arrange
    OptimizationInfoMemberFilter optimizationInfoMemberFilter =
        new OptimizationInfoMemberFilter(new MemberNameCleaner(), mock(MemberVisitor.class));
    ProgramClass programClass = new ProgramClass();
    ProgramField programField = mock(ProgramField.class);
    doNothing().when(programField).setProcessingInfo(Mockito.<Object>any());
    LibraryClass clazz = new LibraryClass();
    when(programField.getProcessingInfo())
        .thenReturn(
            new ProgramFieldOptimizationInfo(
                clazz, new LibraryField(1, "Name", "Descriptor"), true));

    // Act
    optimizationInfoMemberFilter.visitProgramField(programClass, programField);

    // Assert
    verify(programField, atLeast(1)).getProcessingInfo();
    verify(programField).setProcessingInfo(isNull());
  }

  /**
   * Test {@link OptimizationInfoMemberFilter#visitProgramField(ProgramClass, ProgramField)}.
   *
   * <ul>
   *   <li>Then calls {@link MemberVisitor#visitProgramField(ProgramClass, ProgramField)}.
   * </ul>
   *
   * <p>Method under test: {@link OptimizationInfoMemberFilter#visitProgramField(ProgramClass,
   * ProgramField)}
   */
  @Test
  @DisplayName(
      "Test visitProgramField(ProgramClass, ProgramField); then calls visitProgramField(ProgramClass, ProgramField)")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void OptimizationInfoMemberFilter.visitProgramField(ProgramClass, ProgramField)"
  })
  void testVisitProgramField_thenCallsVisitProgramField() {
    // Arrange
    MemberVisitor otherMemberVisitor = mock(MemberVisitor.class);
    doNothing()
        .when(otherMemberVisitor)
        .visitProgramField(Mockito.<ProgramClass>any(), Mockito.<ProgramField>any());
    OptimizationInfoMemberFilter optimizationInfoMemberFilter =
        new OptimizationInfoMemberFilter(new KotlinAnnotationCounter(), otherMemberVisitor);
    ProgramClass programClass = new ProgramClass();

    // Act
    optimizationInfoMemberFilter.visitProgramField(programClass, new ProgramField());

    // Assert
    verify(otherMemberVisitor).visitProgramField(isA(ProgramClass.class), isA(ProgramField.class));
  }

  /**
   * Test {@link OptimizationInfoMemberFilter#visitProgramField(ProgramClass, ProgramField)}.
   *
   * <ul>
   *   <li>When {@link ProgramField} {@link ProgramField#accept(Clazz, MemberVisitor)} does nothing.
   *   <li>Then calls {@link ProgramField#accept(Clazz, MemberVisitor)}.
   * </ul>
   *
   * <p>Method under test: {@link OptimizationInfoMemberFilter#visitProgramField(ProgramClass,
   * ProgramField)}
   */
  @Test
  @DisplayName(
      "Test visitProgramField(ProgramClass, ProgramField); when ProgramField accept(Clazz, MemberVisitor) does nothing; then calls accept(Clazz, MemberVisitor)")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void OptimizationInfoMemberFilter.visitProgramField(ProgramClass, ProgramField)"
  })
  void testVisitProgramField_whenProgramFieldAcceptDoesNothing_thenCallsAccept() {
    // Arrange
    OptimizationInfoMemberFilter optimizationInfoMemberFilter =
        new OptimizationInfoMemberFilter(new KotlinAnnotationCounter(), mock(MemberVisitor.class));
    ProgramClass programClass = new ProgramClass();
    ProgramField programField = mock(ProgramField.class);
    doNothing().when(programField).accept(Mockito.<Clazz>any(), Mockito.<MemberVisitor>any());
    LibraryClass clazz = new LibraryClass();
    when(programField.getProcessingInfo())
        .thenReturn(
            new ProgramFieldOptimizationInfo(
                clazz, new LibraryField(1, "Name", "Descriptor"), true));

    // Act
    optimizationInfoMemberFilter.visitProgramField(programClass, programField);

    // Assert
    verify(programField).accept(isA(Clazz.class), isA(MemberVisitor.class));
    verify(programField).getProcessingInfo();
  }

  /**
   * Test {@link OptimizationInfoMemberFilter#visitProgramMethod(ProgramClass, ProgramMethod)}.
   *
   * <ul>
   *   <li>Given {@link MethodOptimizationInfo} (default constructor).
   * </ul>
   *
   * <p>Method under test: {@link OptimizationInfoMemberFilter#visitProgramMethod(ProgramClass,
   * ProgramMethod)}
   */
  @Test
  @DisplayName(
      "Test visitProgramMethod(ProgramClass, ProgramMethod); given MethodOptimizationInfo (default constructor)")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void OptimizationInfoMemberFilter.visitProgramMethod(ProgramClass, ProgramMethod)"
  })
  void testVisitProgramMethod_givenMethodOptimizationInfo() {
    // Arrange
    MemberVisitor otherMemberVisitor = mock(MemberVisitor.class);
    doNothing()
        .when(otherMemberVisitor)
        .visitProgramMethod(Mockito.<ProgramClass>any(), Mockito.<ProgramMethod>any());
    OptimizationInfoMemberFilter optimizationInfoMemberFilter =
        new OptimizationInfoMemberFilter(new KotlinAnnotationCounter(), otherMemberVisitor);
    ProgramClass programClass = new ProgramClass();
    ProgramMethod programMethod = mock(ProgramMethod.class);
    when(programMethod.getProcessingInfo()).thenReturn(new MethodOptimizationInfo());

    // Act
    optimizationInfoMemberFilter.visitProgramMethod(programClass, programMethod);

    // Assert
    verify(otherMemberVisitor)
        .visitProgramMethod(isA(ProgramClass.class), isA(ProgramMethod.class));
    verify(programMethod, atLeast(1)).getProcessingInfo();
  }

  /**
   * Test {@link OptimizationInfoMemberFilter#visitProgramMethod(ProgramClass, ProgramMethod)}.
   *
   * <ul>
   *   <li>Then calls {@link ProgramMethod#setProcessingInfo(Object)}.
   * </ul>
   *
   * <p>Method under test: {@link OptimizationInfoMemberFilter#visitProgramMethod(ProgramClass,
   * ProgramMethod)}
   */
  @Test
  @DisplayName(
      "Test visitProgramMethod(ProgramClass, ProgramMethod); then calls setProcessingInfo(Object)")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void OptimizationInfoMemberFilter.visitProgramMethod(ProgramClass, ProgramMethod)"
  })
  void testVisitProgramMethod_thenCallsSetProcessingInfo() {
    // Arrange
    OptimizationInfoMemberFilter optimizationInfoMemberFilter =
        new OptimizationInfoMemberFilter(new MemberNameCleaner(), mock(MemberVisitor.class));
    ProgramClass programClass = new ProgramClass();
    ProgramMethod programMethod = mock(ProgramMethod.class);
    doNothing().when(programMethod).setProcessingInfo(Mockito.<Object>any());
    when(programMethod.getProcessingInfo()).thenReturn(mock(ProgramMethodOptimizationInfo.class));

    // Act
    optimizationInfoMemberFilter.visitProgramMethod(programClass, programMethod);

    // Assert
    verify(programMethod, atLeast(1)).getProcessingInfo();
    verify(programMethod).setProcessingInfo(isNull());
  }

  /**
   * Test {@link OptimizationInfoMemberFilter#visitProgramMethod(ProgramClass, ProgramMethod)}.
   *
   * <ul>
   *   <li>Then calls {@link MemberVisitor#visitProgramMethod(ProgramClass, ProgramMethod)}.
   * </ul>
   *
   * <p>Method under test: {@link OptimizationInfoMemberFilter#visitProgramMethod(ProgramClass,
   * ProgramMethod)}
   */
  @Test
  @DisplayName(
      "Test visitProgramMethod(ProgramClass, ProgramMethod); then calls visitProgramMethod(ProgramClass, ProgramMethod)")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void OptimizationInfoMemberFilter.visitProgramMethod(ProgramClass, ProgramMethod)"
  })
  void testVisitProgramMethod_thenCallsVisitProgramMethod() {
    // Arrange
    MemberVisitor otherMemberVisitor = mock(MemberVisitor.class);
    doNothing()
        .when(otherMemberVisitor)
        .visitProgramMethod(Mockito.<ProgramClass>any(), Mockito.<ProgramMethod>any());
    OptimizationInfoMemberFilter optimizationInfoMemberFilter =
        new OptimizationInfoMemberFilter(new KotlinAnnotationCounter(), otherMemberVisitor);
    ProgramClass programClass = new ProgramClass();

    // Act
    optimizationInfoMemberFilter.visitProgramMethod(programClass, new ProgramMethod());

    // Assert
    verify(otherMemberVisitor)
        .visitProgramMethod(isA(ProgramClass.class), isA(ProgramMethod.class));
  }

  /**
   * Test {@link OptimizationInfoMemberFilter#visitProgramMethod(ProgramClass, ProgramMethod)}.
   *
   * <ul>
   *   <li>When {@link ProgramMethod} {@link ProgramMethod#accept(Clazz, MemberVisitor)} does
   *       nothing.
   *   <li>Then calls {@link ProgramMethod#accept(Clazz, MemberVisitor)}.
   * </ul>
   *
   * <p>Method under test: {@link OptimizationInfoMemberFilter#visitProgramMethod(ProgramClass,
   * ProgramMethod)}
   */
  @Test
  @DisplayName(
      "Test visitProgramMethod(ProgramClass, ProgramMethod); when ProgramMethod accept(Clazz, MemberVisitor) does nothing; then calls accept(Clazz, MemberVisitor)")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void OptimizationInfoMemberFilter.visitProgramMethod(ProgramClass, ProgramMethod)"
  })
  void testVisitProgramMethod_whenProgramMethodAcceptDoesNothing_thenCallsAccept() {
    // Arrange
    OptimizationInfoMemberFilter optimizationInfoMemberFilter =
        new OptimizationInfoMemberFilter(new KotlinAnnotationCounter(), mock(MemberVisitor.class));
    ProgramClass programClass = new ProgramClass();
    ProgramMethod programMethod = mock(ProgramMethod.class);
    doNothing().when(programMethod).accept(Mockito.<Clazz>any(), Mockito.<MemberVisitor>any());
    when(programMethod.getProcessingInfo()).thenReturn(mock(ProgramMethodOptimizationInfo.class));

    // Act
    optimizationInfoMemberFilter.visitProgramMethod(programClass, programMethod);

    // Assert
    verify(programMethod).accept(isA(Clazz.class), isA(MemberVisitor.class));
    verify(programMethod, atLeast(1)).getProcessingInfo();
  }
}
