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
import com.diffblue.cover.annotations.MethodsUnderTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import proguard.classfile.Clazz;
import proguard.classfile.LibraryClass;
import proguard.classfile.LibraryField;
import proguard.classfile.LibraryMember;
import proguard.classfile.LibraryMethod;
import proguard.classfile.ProgramClass;
import proguard.classfile.ProgramField;
import proguard.classfile.ProgramMember;
import proguard.classfile.ProgramMethod;
import proguard.classfile.visitor.MemberVisitor;
import proguard.fixer.kotlin.KotlinAnnotationCounter;
import proguard.obfuscate.MemberNameCleaner;
import proguard.optimize.info.FieldOptimizationInfo;
import proguard.optimize.info.MethodOptimizationInfo;
import proguard.optimize.info.ProgramFieldOptimizationInfo;

class KeptMemberFilterDiffblueTest {
  /**
   * Test {@link KeptMemberFilter#visitProgramField(ProgramClass, ProgramField)}.
   * <p>
   * Method under test: {@link KeptMemberFilter#visitProgramField(ProgramClass, ProgramField)}
   */
  @Test
  @DisplayName("Test visitProgramField(ProgramClass, ProgramField)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void KeptMemberFilter.visitProgramField(ProgramClass, ProgramField)"})
  void testVisitProgramField() {
    // Arrange
    KeptMemberFilter keptMemberFilter = new KeptMemberFilter(mock(MemberVisitor.class));
    ProgramClass programClass = new ProgramClass();
    ProgramField programField = mock(ProgramField.class);
    LibraryClass clazz = new LibraryClass();
    when(programField.getProcessingInfo())
        .thenReturn(new ProgramFieldOptimizationInfo(clazz, new LibraryField(1, "Name", "Descriptor"), true));

    // Act
    keptMemberFilter.visitProgramField(programClass, programField);

    // Assert
    verify(programField).getProcessingInfo();
  }

  /**
   * Test {@link KeptMemberFilter#visitProgramField(ProgramClass, ProgramField)}.
   * <ul>
   *   <li>Then calls {@link MemberVisitor#visitProgramField(ProgramClass, ProgramField)}.</li>
   * </ul>
   * <p>
   * Method under test: {@link KeptMemberFilter#visitProgramField(ProgramClass, ProgramField)}
   */
  @Test
  @DisplayName("Test visitProgramField(ProgramClass, ProgramField); then calls visitProgramField(ProgramClass, ProgramField)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void KeptMemberFilter.visitProgramField(ProgramClass, ProgramField)"})
  void testVisitProgramField_thenCallsVisitProgramField() {
    // Arrange
    MemberVisitor memberVisitor = mock(MemberVisitor.class);
    doNothing().when(memberVisitor).visitProgramField(Mockito.<ProgramClass>any(), Mockito.<ProgramField>any());
    KeptMemberFilter keptMemberFilter = new KeptMemberFilter(memberVisitor);
    ProgramClass programClass = new ProgramClass();
    ProgramField programField = mock(ProgramField.class);
    when(programField.getProcessingInfo()).thenReturn(new FieldOptimizationInfo());

    // Act
    keptMemberFilter.visitProgramField(programClass, programField);

    // Assert
    verify(memberVisitor).visitProgramField(isA(ProgramClass.class), isA(ProgramField.class));
    verify(programField).getProcessingInfo();
  }

  /**
   * Test {@link KeptMemberFilter#visitProgramField(ProgramClass, ProgramField)}.
   * <ul>
   *   <li>When {@link ProgramField} {@link ProgramMember#accept(Clazz, MemberVisitor)} does nothing.</li>
   *   <li>Then calls {@link ProgramMember#accept(Clazz, MemberVisitor)}.</li>
   * </ul>
   * <p>
   * Method under test: {@link KeptMemberFilter#visitProgramField(ProgramClass, ProgramField)}
   */
  @Test
  @DisplayName("Test visitProgramField(ProgramClass, ProgramField); when ProgramField accept(Clazz, MemberVisitor) does nothing; then calls accept(Clazz, MemberVisitor)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void KeptMemberFilter.visitProgramField(ProgramClass, ProgramField)"})
  void testVisitProgramField_whenProgramFieldAcceptDoesNothing_thenCallsAccept() {
    // Arrange
    KeptMemberFilter keptMemberFilter = new KeptMemberFilter(new KotlinAnnotationCounter());
    ProgramClass programClass = new ProgramClass();
    ProgramField programField = mock(ProgramField.class);
    doNothing().when(programField).accept(Mockito.<Clazz>any(), Mockito.<MemberVisitor>any());
    when(programField.getProcessingInfo()).thenReturn(new FieldOptimizationInfo());

    // Act
    keptMemberFilter.visitProgramField(programClass, programField);

    // Assert
    verify(programField).accept(isA(Clazz.class), isA(MemberVisitor.class));
    verify(programField).getProcessingInfo();
  }

  /**
   * Test {@link KeptMemberFilter#visitProgramMethod(ProgramClass, ProgramMethod)}.
   * <ul>
   *   <li>Given {@link MethodOptimizationInfo} {@link MethodOptimizationInfo#isKept()} return {@code false}.</li>
   * </ul>
   * <p>
   * Method under test: {@link KeptMemberFilter#visitProgramMethod(ProgramClass, ProgramMethod)}
   */
  @Test
  @DisplayName("Test visitProgramMethod(ProgramClass, ProgramMethod); given MethodOptimizationInfo isKept() return 'false'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void KeptMemberFilter.visitProgramMethod(ProgramClass, ProgramMethod)"})
  void testVisitProgramMethod_givenMethodOptimizationInfoIsKeptReturnFalse() {
    // Arrange
    KeptMemberFilter keptMemberFilter = new KeptMemberFilter(mock(MemberVisitor.class));
    ProgramClass programClass = new ProgramClass();
    MethodOptimizationInfo methodOptimizationInfo = mock(MethodOptimizationInfo.class);
    when(methodOptimizationInfo.isKept()).thenReturn(false);
    ProgramMethod programMethod = mock(ProgramMethod.class);
    when(programMethod.getProcessingInfo()).thenReturn(methodOptimizationInfo);

    // Act
    keptMemberFilter.visitProgramMethod(programClass, programMethod);

    // Assert
    verify(methodOptimizationInfo).isKept();
    verify(programMethod, atLeast(1)).getProcessingInfo();
  }

  /**
   * Test {@link KeptMemberFilter#visitProgramMethod(ProgramClass, ProgramMethod)}.
   * <ul>
   *   <li>Given {@link MethodOptimizationInfo} {@link MethodOptimizationInfo#isKept()} return {@code true}.</li>
   * </ul>
   * <p>
   * Method under test: {@link KeptMemberFilter#visitProgramMethod(ProgramClass, ProgramMethod)}
   */
  @Test
  @DisplayName("Test visitProgramMethod(ProgramClass, ProgramMethod); given MethodOptimizationInfo isKept() return 'true'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void KeptMemberFilter.visitProgramMethod(ProgramClass, ProgramMethod)"})
  void testVisitProgramMethod_givenMethodOptimizationInfoIsKeptReturnTrue() {
    // Arrange
    MemberVisitor memberVisitor = mock(MemberVisitor.class);
    doNothing().when(memberVisitor).visitProgramMethod(Mockito.<ProgramClass>any(), Mockito.<ProgramMethod>any());
    KeptMemberFilter keptMemberFilter = new KeptMemberFilter(memberVisitor);
    ProgramClass programClass = new ProgramClass();
    MethodOptimizationInfo methodOptimizationInfo = mock(MethodOptimizationInfo.class);
    when(methodOptimizationInfo.isKept()).thenReturn(true);
    ProgramMethod programMethod = mock(ProgramMethod.class);
    when(programMethod.getProcessingInfo()).thenReturn(methodOptimizationInfo);

    // Act
    keptMemberFilter.visitProgramMethod(programClass, programMethod);

    // Assert
    verify(memberVisitor).visitProgramMethod(isA(ProgramClass.class), isA(ProgramMethod.class));
    verify(methodOptimizationInfo).isKept();
    verify(programMethod, atLeast(1)).getProcessingInfo();
  }

  /**
   * Test {@link KeptMemberFilter#visitProgramMethod(ProgramClass, ProgramMethod)}.
   * <ul>
   *   <li>Then calls {@link MemberVisitor#visitProgramMethod(ProgramClass, ProgramMethod)}.</li>
   * </ul>
   * <p>
   * Method under test: {@link KeptMemberFilter#visitProgramMethod(ProgramClass, ProgramMethod)}
   */
  @Test
  @DisplayName("Test visitProgramMethod(ProgramClass, ProgramMethod); then calls visitProgramMethod(ProgramClass, ProgramMethod)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void KeptMemberFilter.visitProgramMethod(ProgramClass, ProgramMethod)"})
  void testVisitProgramMethod_thenCallsVisitProgramMethod() {
    // Arrange
    MemberVisitor memberVisitor = mock(MemberVisitor.class);
    doNothing().when(memberVisitor).visitProgramMethod(Mockito.<ProgramClass>any(), Mockito.<ProgramMethod>any());
    KeptMemberFilter keptMemberFilter = new KeptMemberFilter(memberVisitor);
    ProgramClass programClass = new ProgramClass();
    ProgramMethod programMethod = mock(ProgramMethod.class);
    when(programMethod.getProcessingInfo()).thenReturn(new MethodOptimizationInfo());

    // Act
    keptMemberFilter.visitProgramMethod(programClass, programMethod);

    // Assert
    verify(memberVisitor).visitProgramMethod(isA(ProgramClass.class), isA(ProgramMethod.class));
    verify(programMethod, atLeast(1)).getProcessingInfo();
  }

  /**
   * Test {@link KeptMemberFilter#visitProgramMethod(ProgramClass, ProgramMethod)}.
   * <ul>
   *   <li>When {@link ProgramMethod} {@link ProgramMember#accept(Clazz, MemberVisitor)} does nothing.</li>
   *   <li>Then calls {@link ProgramMember#accept(Clazz, MemberVisitor)}.</li>
   * </ul>
   * <p>
   * Method under test: {@link KeptMemberFilter#visitProgramMethod(ProgramClass, ProgramMethod)}
   */
  @Test
  @DisplayName("Test visitProgramMethod(ProgramClass, ProgramMethod); when ProgramMethod accept(Clazz, MemberVisitor) does nothing; then calls accept(Clazz, MemberVisitor)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void KeptMemberFilter.visitProgramMethod(ProgramClass, ProgramMethod)"})
  void testVisitProgramMethod_whenProgramMethodAcceptDoesNothing_thenCallsAccept() {
    // Arrange
    KeptMemberFilter keptMemberFilter = new KeptMemberFilter(new KotlinAnnotationCounter());
    ProgramClass programClass = new ProgramClass();
    ProgramMethod programMethod = mock(ProgramMethod.class);
    doNothing().when(programMethod).accept(Mockito.<Clazz>any(), Mockito.<MemberVisitor>any());
    when(programMethod.getProcessingInfo()).thenReturn(new MethodOptimizationInfo());

    // Act
    keptMemberFilter.visitProgramMethod(programClass, programMethod);

    // Assert
    verify(programMethod).accept(isA(Clazz.class), isA(MemberVisitor.class));
    verify(programMethod, atLeast(1)).getProcessingInfo();
  }

  /**
   * Test {@link KeptMemberFilter#visitLibraryField(LibraryClass, LibraryField)}.
   * <p>
   * Method under test: {@link KeptMemberFilter#visitLibraryField(LibraryClass, LibraryField)}
   */
  @Test
  @DisplayName("Test visitLibraryField(LibraryClass, LibraryField)")
  @Tag("MaintainedByDiffblue")
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
   * <p>
   * Method under test: {@link KeptMemberFilter#visitLibraryField(LibraryClass, LibraryField)}
   */
  @Test
  @DisplayName("Test visitLibraryField(LibraryClass, LibraryField)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void KeptMemberFilter.visitLibraryField(LibraryClass, LibraryField)"})
  void testVisitLibraryField2() {
    // Arrange
    KeptMemberFilter keptMemberFilter = new KeptMemberFilter(new KotlinAnnotationCounter());
    LibraryClass libraryClass = new LibraryClass();

    LibraryField libraryField = new LibraryField(1, "Name", "Descriptor");
    LibraryClass clazz = new LibraryClass();
    ProgramFieldOptimizationInfo programFieldOptimizationInfo = new ProgramFieldOptimizationInfo(clazz,
        new LibraryField(1, "Name", "Descriptor"), true);

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
   * <ul>
   *   <li>Given {@link KeptMemberFilter#KeptMemberFilter(MemberVisitor)} with memberVisitor is {@link MemberNameCleaner} (default constructor).</li>
   * </ul>
   * <p>
   * Method under test: {@link KeptMemberFilter#visitLibraryField(LibraryClass, LibraryField)}
   */
  @Test
  @DisplayName("Test visitLibraryField(LibraryClass, LibraryField); given KeptMemberFilter(MemberVisitor) with memberVisitor is MemberNameCleaner (default constructor)")
  @Tag("MaintainedByDiffblue")
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
   * <ul>
   *   <li>When {@link LibraryField#LibraryField(int, String, String)} with u2accessFlags is one and {@code Name} and {@code Descriptor}.</li>
   * </ul>
   * <p>
   * Method under test: {@link KeptMemberFilter#visitLibraryField(LibraryClass, LibraryField)}
   */
  @Test
  @DisplayName("Test visitLibraryField(LibraryClass, LibraryField); when LibraryField(int, String, String) with u2accessFlags is one and 'Name' and 'Descriptor'")
  @Tag("MaintainedByDiffblue")
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
   * <ul>
   *   <li>Given {@link MethodOptimizationInfo} {@link MethodOptimizationInfo#isKept()} return {@code false}.</li>
   * </ul>
   * <p>
   * Method under test: {@link KeptMemberFilter#visitLibraryMethod(LibraryClass, LibraryMethod)}
   */
  @Test
  @DisplayName("Test visitLibraryMethod(LibraryClass, LibraryMethod); given MethodOptimizationInfo isKept() return 'false'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void KeptMemberFilter.visitLibraryMethod(LibraryClass, LibraryMethod)"})
  void testVisitLibraryMethod_givenMethodOptimizationInfoIsKeptReturnFalse() {
    // Arrange
    KeptMemberFilter keptMemberFilter = new KeptMemberFilter(mock(MemberVisitor.class));
    LibraryClass libraryClass = new LibraryClass();
    MethodOptimizationInfo methodOptimizationInfo = mock(MethodOptimizationInfo.class);
    when(methodOptimizationInfo.isKept()).thenReturn(false);
    LibraryMethod libraryMethod = mock(LibraryMethod.class);
    when(libraryMethod.getProcessingInfo()).thenReturn(methodOptimizationInfo);

    // Act
    keptMemberFilter.visitLibraryMethod(libraryClass, libraryMethod);

    // Assert
    verify(methodOptimizationInfo).isKept();
    verify(libraryMethod, atLeast(1)).getProcessingInfo();
  }

  /**
   * Test {@link KeptMemberFilter#visitLibraryMethod(LibraryClass, LibraryMethod)}.
   * <ul>
   *   <li>Given {@link MethodOptimizationInfo} {@link MethodOptimizationInfo#isKept()} return {@code true}.</li>
   * </ul>
   * <p>
   * Method under test: {@link KeptMemberFilter#visitLibraryMethod(LibraryClass, LibraryMethod)}
   */
  @Test
  @DisplayName("Test visitLibraryMethod(LibraryClass, LibraryMethod); given MethodOptimizationInfo isKept() return 'true'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void KeptMemberFilter.visitLibraryMethod(LibraryClass, LibraryMethod)"})
  void testVisitLibraryMethod_givenMethodOptimizationInfoIsKeptReturnTrue() {
    // Arrange
    MemberVisitor memberVisitor = mock(MemberVisitor.class);
    doNothing().when(memberVisitor).visitLibraryMethod(Mockito.<LibraryClass>any(), Mockito.<LibraryMethod>any());
    KeptMemberFilter keptMemberFilter = new KeptMemberFilter(memberVisitor);
    LibraryClass libraryClass = new LibraryClass();
    MethodOptimizationInfo methodOptimizationInfo = mock(MethodOptimizationInfo.class);
    when(methodOptimizationInfo.isKept()).thenReturn(true);
    LibraryMethod libraryMethod = mock(LibraryMethod.class);
    when(libraryMethod.getProcessingInfo()).thenReturn(methodOptimizationInfo);

    // Act
    keptMemberFilter.visitLibraryMethod(libraryClass, libraryMethod);

    // Assert
    verify(memberVisitor).visitLibraryMethod(isA(LibraryClass.class), isA(LibraryMethod.class));
    verify(methodOptimizationInfo).isKept();
    verify(libraryMethod, atLeast(1)).getProcessingInfo();
  }

  /**
   * Test {@link KeptMemberFilter#visitLibraryMethod(LibraryClass, LibraryMethod)}.
   * <ul>
   *   <li>Then calls {@link MemberVisitor#visitLibraryMethod(LibraryClass, LibraryMethod)}.</li>
   * </ul>
   * <p>
   * Method under test: {@link KeptMemberFilter#visitLibraryMethod(LibraryClass, LibraryMethod)}
   */
  @Test
  @DisplayName("Test visitLibraryMethod(LibraryClass, LibraryMethod); then calls visitLibraryMethod(LibraryClass, LibraryMethod)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void KeptMemberFilter.visitLibraryMethod(LibraryClass, LibraryMethod)"})
  void testVisitLibraryMethod_thenCallsVisitLibraryMethod() {
    // Arrange
    MemberVisitor memberVisitor = mock(MemberVisitor.class);
    doNothing().when(memberVisitor).visitLibraryMethod(Mockito.<LibraryClass>any(), Mockito.<LibraryMethod>any());
    KeptMemberFilter keptMemberFilter = new KeptMemberFilter(memberVisitor);
    LibraryClass libraryClass = new LibraryClass();
    LibraryMethod libraryMethod = mock(LibraryMethod.class);
    when(libraryMethod.getProcessingInfo()).thenReturn(new MethodOptimizationInfo());

    // Act
    keptMemberFilter.visitLibraryMethod(libraryClass, libraryMethod);

    // Assert
    verify(memberVisitor).visitLibraryMethod(isA(LibraryClass.class), isA(LibraryMethod.class));
    verify(libraryMethod, atLeast(1)).getProcessingInfo();
  }

  /**
   * Test {@link KeptMemberFilter#visitLibraryMethod(LibraryClass, LibraryMethod)}.
   * <ul>
   *   <li>When {@link LibraryMethod} {@link LibraryMember#accept(Clazz, MemberVisitor)} does nothing.</li>
   *   <li>Then calls {@link LibraryMember#accept(Clazz, MemberVisitor)}.</li>
   * </ul>
   * <p>
   * Method under test: {@link KeptMemberFilter#visitLibraryMethod(LibraryClass, LibraryMethod)}
   */
  @Test
  @DisplayName("Test visitLibraryMethod(LibraryClass, LibraryMethod); when LibraryMethod accept(Clazz, MemberVisitor) does nothing; then calls accept(Clazz, MemberVisitor)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void KeptMemberFilter.visitLibraryMethod(LibraryClass, LibraryMethod)"})
  void testVisitLibraryMethod_whenLibraryMethodAcceptDoesNothing_thenCallsAccept() {
    // Arrange
    KeptMemberFilter keptMemberFilter = new KeptMemberFilter(new KotlinAnnotationCounter());
    LibraryClass libraryClass = new LibraryClass();
    LibraryMethod libraryMethod = mock(LibraryMethod.class);
    doNothing().when(libraryMethod).accept(Mockito.<Clazz>any(), Mockito.<MemberVisitor>any());
    when(libraryMethod.getProcessingInfo()).thenReturn(new MethodOptimizationInfo());

    // Act
    keptMemberFilter.visitLibraryMethod(libraryClass, libraryMethod);

    // Assert
    verify(libraryMethod).accept(isA(Clazz.class), isA(MemberVisitor.class));
    verify(libraryMethod, atLeast(1)).getProcessingInfo();
  }
}
