package proguard.optimize;

import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;
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
import proguard.classfile.LibraryField;
import proguard.classfile.LibraryMethod;
import proguard.classfile.ProgramClass;
import proguard.classfile.ProgramField;
import proguard.classfile.ProgramMethod;
import proguard.classfile.visitor.MemberVisitor;
import proguard.classfile.visitor.MethodImplementationFilter;
import proguard.fixer.kotlin.KotlinAnnotationCounter;
import proguard.obfuscate.MemberNameCleaner;
import proguard.optimize.info.FieldOptimizationInfo;
import proguard.optimize.info.MethodOptimizationInfo;
import proguard.optimize.info.ProgramFieldOptimizationInfo;

class KeptMemberFilterDiffblueTest {
  /**
   * Test {@link KeptMemberFilter#visitProgramField(ProgramClass, ProgramField)}.
   *
   * <ul>
   *   <li>Given {@link KeptMemberFilter#KeptMemberFilter(MemberVisitor)} with memberVisitor is
   *       {@link MemberNameCleaner} (default constructor).
   * </ul>
   *
   * <p>Method under test: {@link KeptMemberFilter#visitProgramField(ProgramClass, ProgramField)}
   */
  @Test
  @DisplayName(
      "Test visitProgramField(ProgramClass, ProgramField); given KeptMemberFilter(MemberVisitor) with memberVisitor is MemberNameCleaner (default constructor)")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void KeptMemberFilter.visitProgramField(ProgramClass, ProgramField)"})
  void testVisitProgramField_givenKeptMemberFilterWithMemberVisitorIsMemberNameCleaner() {
    // Arrange
    KeptMemberFilter keptMemberFilter = new KeptMemberFilter(new MemberNameCleaner());
    ProgramClass programClass = new ProgramClass();

    ProgramField programField = mock(ProgramField.class);
    when(programField.getProcessingInfo()).thenReturn(new FieldOptimizationInfo());
    doNothing().when(programField).addProcessingFlags((int[]) Mockito.any());
    doNothing().when(programField).setProcessingInfo(Mockito.<Object>any());
    programField.addProcessingFlags(2, 1, 2, 1);
    programField.setProcessingInfo(1);

    // Act
    keptMemberFilter.visitProgramField(programClass, programField);

    // Assert
    verify(programField).addProcessingFlags((int[]) Mockito.any());
    verify(programField, atLeast(1)).getProcessingInfo();
    verify(programField, atLeast(1)).setProcessingInfo(Mockito.<Object>any());
  }

  /**
   * Test {@link KeptMemberFilter#visitProgramField(ProgramClass, ProgramField)}.
   *
   * <ul>
   *   <li>When {@link ProgramField} {@link ProgramField#accept(Clazz, MemberVisitor)} does nothing.
   *   <li>Then calls {@link ProgramField#accept(Clazz, MemberVisitor)}.
   * </ul>
   *
   * <p>Method under test: {@link KeptMemberFilter#visitProgramField(ProgramClass, ProgramField)}
   */
  @Test
  @DisplayName(
      "Test visitProgramField(ProgramClass, ProgramField); when ProgramField accept(Clazz, MemberVisitor) does nothing; then calls accept(Clazz, MemberVisitor)")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void KeptMemberFilter.visitProgramField(ProgramClass, ProgramField)"})
  void testVisitProgramField_whenProgramFieldAcceptDoesNothing_thenCallsAccept() {
    // Arrange
    KeptMemberFilter keptMemberFilter = new KeptMemberFilter(new KotlinAnnotationCounter());
    ProgramClass programClass = new ProgramClass();

    ProgramField programField = mock(ProgramField.class);
    doNothing().when(programField).accept(Mockito.<Clazz>any(), Mockito.<MemberVisitor>any());
    when(programField.getProcessingInfo()).thenReturn(new FieldOptimizationInfo());
    doNothing().when(programField).addProcessingFlags((int[]) Mockito.any());
    doNothing().when(programField).setProcessingInfo(Mockito.<Object>any());
    programField.addProcessingFlags(2, 1, 2, 1);
    programField.setProcessingInfo(1);

    // Act
    keptMemberFilter.visitProgramField(programClass, programField);

    // Assert
    verify(programField).accept(isA(Clazz.class), isA(MemberVisitor.class));
    verify(programField).addProcessingFlags((int[]) Mockito.any());
    verify(programField).getProcessingInfo();
    verify(programField).setProcessingInfo(isA(Object.class));
  }

  /**
   * Test {@link KeptMemberFilter#visitProgramMethod(ProgramClass, ProgramMethod)}.
   *
   * <ul>
   *   <li>Given {@link MethodOptimizationInfo} {@link MethodOptimizationInfo#isKept()} return
   *       {@code false}.
   * </ul>
   *
   * <p>Method under test: {@link KeptMemberFilter#visitProgramMethod(ProgramClass, ProgramMethod)}
   */
  @Test
  @DisplayName(
      "Test visitProgramMethod(ProgramClass, ProgramMethod); given MethodOptimizationInfo isKept() return 'false'")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void KeptMemberFilter.visitProgramMethod(ProgramClass, ProgramMethod)"})
  void testVisitProgramMethod_givenMethodOptimizationInfoIsKeptReturnFalse() {
    // Arrange
    KeptMemberFilter keptMemberFilter = new KeptMemberFilter(new KotlinAnnotationCounter());
    ProgramClass programClass = new ProgramClass();

    MethodOptimizationInfo methodOptimizationInfo = mock(MethodOptimizationInfo.class);
    when(methodOptimizationInfo.isKept()).thenReturn(false);

    ProgramMethod programMethod = new ProgramMethod();
    programMethod.setProcessingInfo(methodOptimizationInfo);

    // Act
    keptMemberFilter.visitProgramMethod(programClass, programMethod);

    // Assert
    verify(methodOptimizationInfo).isKept();
  }

  /**
   * Test {@link KeptMemberFilter#visitProgramMethod(ProgramClass, ProgramMethod)}.
   *
   * <ul>
   *   <li>Then calls {@link MethodImplementationFilter#visitProgramMethod(ProgramClass,
   *       ProgramMethod)}.
   * </ul>
   *
   * <p>Method under test: {@link KeptMemberFilter#visitProgramMethod(ProgramClass, ProgramMethod)}
   */
  @Test
  @DisplayName(
      "Test visitProgramMethod(ProgramClass, ProgramMethod); then calls visitProgramMethod(ProgramClass, ProgramMethod)")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void KeptMemberFilter.visitProgramMethod(ProgramClass, ProgramMethod)"})
  void testVisitProgramMethod_thenCallsVisitProgramMethod() {
    // Arrange
    MethodImplementationFilter memberVisitor = mock(MethodImplementationFilter.class);
    doNothing()
        .when(memberVisitor)
        .visitProgramMethod(Mockito.<ProgramClass>any(), Mockito.<ProgramMethod>any());
    KeptMemberFilter keptMemberFilter = new KeptMemberFilter(memberVisitor);
    ProgramClass programClass = new ProgramClass();

    MethodOptimizationInfo methodOptimizationInfo = mock(MethodOptimizationInfo.class);
    when(methodOptimizationInfo.isKept()).thenReturn(true);

    ProgramMethod programMethod = new ProgramMethod();
    programMethod.setProcessingInfo(methodOptimizationInfo);

    // Act
    keptMemberFilter.visitProgramMethod(programClass, programMethod);

    // Assert
    verify(memberVisitor).visitProgramMethod(isA(ProgramClass.class), isA(ProgramMethod.class));
    verify(methodOptimizationInfo).isKept();
  }

  /**
   * Test {@link KeptMemberFilter#visitLibraryField(LibraryClass, LibraryField)}.
   *
   * <p>Method under test: {@link KeptMemberFilter#visitLibraryField(LibraryClass, LibraryField)}
   */
  @Test
  @DisplayName("Test visitLibraryField(LibraryClass, LibraryField)")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void KeptMemberFilter.visitLibraryField(LibraryClass, LibraryField)"})
  void testVisitLibraryField() {
    // Arrange
    KeptMemberFilter keptMemberFilter = new KeptMemberFilter(new KotlinAnnotationCounter());
    LibraryClass libraryClass = new LibraryClass();

    LibraryField libraryField = new LibraryField(1, "Name", "Descriptor");
    FieldOptimizationInfo fieldOptimizationInfo = new FieldOptimizationInfo();
    libraryField.setProcessingInfo(fieldOptimizationInfo);

    // Act
    keptMemberFilter.visitLibraryField(libraryClass, libraryField);

    // Assert that nothing has changed
    Object processingInfo = libraryField.getProcessingInfo();
    assertTrue(processingInfo instanceof FieldOptimizationInfo);
    assertSame(fieldOptimizationInfo, processingInfo);
  }

  /**
   * Test {@link KeptMemberFilter#visitLibraryField(LibraryClass, LibraryField)}.
   *
   * <p>Method under test: {@link KeptMemberFilter#visitLibraryField(LibraryClass, LibraryField)}
   */
  @Test
  @DisplayName("Test visitLibraryField(LibraryClass, LibraryField)")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void KeptMemberFilter.visitLibraryField(LibraryClass, LibraryField)"})
  void testVisitLibraryField2() {
    // Arrange
    KeptMemberFilter keptMemberFilter = new KeptMemberFilter(new KotlinAnnotationCounter());
    LibraryClass libraryClass = new LibraryClass();

    LibraryField libraryField = new LibraryField(1, "Name", "Descriptor");
    LibraryClass clazz = new LibraryClass();
    LibraryField field = new LibraryField(1, "Name", "Descriptor");

    ProgramFieldOptimizationInfo programFieldOptimizationInfo =
        new ProgramFieldOptimizationInfo(clazz, field, true);
    libraryField.setProcessingInfo(programFieldOptimizationInfo);

    // Act
    keptMemberFilter.visitLibraryField(libraryClass, libraryField);

    // Assert that nothing has changed
    Object processingInfo = libraryField.getProcessingInfo();
    assertTrue(processingInfo instanceof ProgramFieldOptimizationInfo);
    assertSame(programFieldOptimizationInfo, processingInfo);
  }

  /**
   * Test {@link KeptMemberFilter#visitLibraryField(LibraryClass, LibraryField)}.
   *
   * <ul>
   *   <li>Given {@link KeptMemberFilter#KeptMemberFilter(MemberVisitor)} with memberVisitor is
   *       {@link MemberNameCleaner} (default constructor).
   * </ul>
   *
   * <p>Method under test: {@link KeptMemberFilter#visitLibraryField(LibraryClass, LibraryField)}
   */
  @Test
  @DisplayName(
      "Test visitLibraryField(LibraryClass, LibraryField); given KeptMemberFilter(MemberVisitor) with memberVisitor is MemberNameCleaner (default constructor)")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void KeptMemberFilter.visitLibraryField(LibraryClass, LibraryField)"})
  void testVisitLibraryField_givenKeptMemberFilterWithMemberVisitorIsMemberNameCleaner() {
    // Arrange
    KeptMemberFilter keptMemberFilter = new KeptMemberFilter(new MemberNameCleaner());
    LibraryClass libraryClass = new LibraryClass();

    LibraryField libraryField = new LibraryField(1, "Name", "Descriptor");
    libraryField.setProcessingInfo(new FieldOptimizationInfo());

    // Act
    keptMemberFilter.visitLibraryField(libraryClass, libraryField);

    // Assert
    assertNull(libraryField.getProcessingInfo());
  }

  /**
   * Test {@link KeptMemberFilter#visitLibraryField(LibraryClass, LibraryField)}.
   *
   * <ul>
   *   <li>When {@link LibraryField#LibraryField(int, String, String)} with u2accessFlags is one and
   *       {@code Name} and {@code Descriptor}.
   * </ul>
   *
   * <p>Method under test: {@link KeptMemberFilter#visitLibraryField(LibraryClass, LibraryField)}
   */
  @Test
  @DisplayName(
      "Test visitLibraryField(LibraryClass, LibraryField); when LibraryField(int, String, String) with u2accessFlags is one and 'Name' and 'Descriptor'")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void KeptMemberFilter.visitLibraryField(LibraryClass, LibraryField)"})
  void testVisitLibraryField_whenLibraryFieldWithU2accessFlagsIsOneAndNameAndDescriptor() {
    // Arrange
    KeptMemberFilter keptMemberFilter = new KeptMemberFilter(new KotlinAnnotationCounter());
    LibraryClass libraryClass = new LibraryClass();
    LibraryField libraryField = new LibraryField(1, "Name", "Descriptor");

    // Act
    keptMemberFilter.visitLibraryField(libraryClass, libraryField);

    // Assert that nothing has changed
    assertNull(libraryField.getProcessingInfo());
  }

  /**
   * Test {@link KeptMemberFilter#visitLibraryMethod(LibraryClass, LibraryMethod)}.
   *
   * <ul>
   *   <li>Given {@link MethodOptimizationInfo} {@link MethodOptimizationInfo#isKept()} return
   *       {@code false}.
   * </ul>
   *
   * <p>Method under test: {@link KeptMemberFilter#visitLibraryMethod(LibraryClass, LibraryMethod)}
   */
  @Test
  @DisplayName(
      "Test visitLibraryMethod(LibraryClass, LibraryMethod); given MethodOptimizationInfo isKept() return 'false'")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void KeptMemberFilter.visitLibraryMethod(LibraryClass, LibraryMethod)"})
  void testVisitLibraryMethod_givenMethodOptimizationInfoIsKeptReturnFalse() {
    // Arrange
    KeptMemberFilter keptMemberFilter = new KeptMemberFilter(new KotlinAnnotationCounter());
    LibraryClass libraryClass = new LibraryClass();

    MethodOptimizationInfo methodOptimizationInfo = mock(MethodOptimizationInfo.class);
    when(methodOptimizationInfo.isKept()).thenReturn(false);

    LibraryMethod libraryMethod = new LibraryMethod(1, "Name", "Descriptor");
    libraryMethod.setProcessingInfo(methodOptimizationInfo);

    // Act
    keptMemberFilter.visitLibraryMethod(libraryClass, libraryMethod);

    // Assert
    verify(methodOptimizationInfo).isKept();
  }

  /**
   * Test {@link KeptMemberFilter#visitLibraryMethod(LibraryClass, LibraryMethod)}.
   *
   * <ul>
   *   <li>Then calls {@link MethodImplementationFilter#visitLibraryMethod(LibraryClass,
   *       LibraryMethod)}.
   * </ul>
   *
   * <p>Method under test: {@link KeptMemberFilter#visitLibraryMethod(LibraryClass, LibraryMethod)}
   */
  @Test
  @DisplayName(
      "Test visitLibraryMethod(LibraryClass, LibraryMethod); then calls visitLibraryMethod(LibraryClass, LibraryMethod)")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void KeptMemberFilter.visitLibraryMethod(LibraryClass, LibraryMethod)"})
  void testVisitLibraryMethod_thenCallsVisitLibraryMethod() {
    // Arrange
    MethodImplementationFilter memberVisitor = mock(MethodImplementationFilter.class);
    doNothing()
        .when(memberVisitor)
        .visitLibraryMethod(Mockito.<LibraryClass>any(), Mockito.<LibraryMethod>any());
    KeptMemberFilter keptMemberFilter = new KeptMemberFilter(memberVisitor);
    LibraryClass libraryClass = new LibraryClass();

    MethodOptimizationInfo methodOptimizationInfo = mock(MethodOptimizationInfo.class);
    when(methodOptimizationInfo.isKept()).thenReturn(true);

    LibraryMethod libraryMethod = new LibraryMethod(1, "Name", "Descriptor");
    libraryMethod.setProcessingInfo(methodOptimizationInfo);

    // Act
    keptMemberFilter.visitLibraryMethod(libraryClass, libraryMethod);

    // Assert
    verify(memberVisitor).visitLibraryMethod(isA(LibraryClass.class), isA(LibraryMethod.class));
    verify(methodOptimizationInfo).isKept();
  }
}
