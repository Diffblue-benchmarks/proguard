package proguard.optimize.info;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.anyInt;
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
import proguard.classfile.Field;
import proguard.classfile.LibraryClass;
import proguard.classfile.LibraryField;
import proguard.classfile.LibraryMethod;
import proguard.classfile.Member;
import proguard.classfile.Method;
import proguard.classfile.ProgramClass;
import proguard.classfile.ProgramField;
import proguard.classfile.ProgramMethod;
import proguard.classfile.constant.FieldrefConstant;
import proguard.classfile.constant.RefConstant;
import proguard.classfile.constant.StringConstant;
import proguard.classfile.constant.visitor.ConstantVisitor;
import proguard.classfile.visitor.MemberVisitor;

class NonPrivateMemberMarkerDiffblueTest {
  /**
   * Test {@link NonPrivateMemberMarker#visitProgramClass(ProgramClass)}.
   * <ul>
   *   <li>Then calls {@link ProgramClass#constantPoolEntriesAccept(ConstantVisitor)}.</li>
   * </ul>
   * <p>
   * Method under test: {@link NonPrivateMemberMarker#visitProgramClass(ProgramClass)}
   */
  @Test
  @DisplayName("Test visitProgramClass(ProgramClass); then calls constantPoolEntriesAccept(ConstantVisitor)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "void proguard.optimize.info.NonPrivateMemberMarker.visitProgramClass(proguard.classfile.ProgramClass)"})
  void testVisitProgramClass_thenCallsConstantPoolEntriesAccept() {
    // Arrange
    NonPrivateMemberMarker nonPrivateMemberMarker = new NonPrivateMemberMarker();
    ProgramClass programClass = mock(ProgramClass.class);
    doNothing().when(programClass).constantPoolEntriesAccept(Mockito.<ConstantVisitor>any());
    doNothing().when(programClass)
        .methodAccept(Mockito.<String>any(), Mockito.<String>any(), Mockito.<MemberVisitor>any());
    doNothing().when(programClass).methodsAccept(Mockito.<MemberVisitor>any());

    // Act
    nonPrivateMemberMarker.visitProgramClass(programClass);

    // Assert
    verify(programClass).constantPoolEntriesAccept(isA(ConstantVisitor.class));
    verify(programClass, atLeast(1)).methodAccept(Mockito.<String>any(), eq("()V"), isA(MemberVisitor.class));
    verify(programClass).methodsAccept(isA(MemberVisitor.class));
  }

  /**
   * Test {@link NonPrivateMemberMarker#visitStringConstant(Clazz, StringConstant)}.
   * <ul>
   *   <li>Then {@link StringConstant#StringConstant()} {@link StringConstant#referencedMember} {@link ProgramField}.</li>
   * </ul>
   * <p>
   * Method under test: {@link NonPrivateMemberMarker#visitStringConstant(Clazz, StringConstant)}
   */
  @Test
  @DisplayName("Test visitStringConstant(Clazz, StringConstant); then StringConstant() referencedMember ProgramField")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "void proguard.optimize.info.NonPrivateMemberMarker.visitStringConstant(proguard.classfile.Clazz, proguard.classfile.constant.StringConstant)"})
  void testVisitStringConstant_thenStringConstantReferencedMemberProgramField() {
    // Arrange
    NonPrivateMemberMarker nonPrivateMemberMarker = new NonPrivateMemberMarker();
    LibraryClass clazz = new LibraryClass();

    ProgramField programField = new ProgramField();
    LibraryClass clazz2 = new LibraryClass();
    programField
        .setProcessingInfo(new ProgramFieldOptimizationInfo(clazz2, new LibraryField(1, "Name", "Descriptor"), true));
    StringConstant stringConstant = new StringConstant();
    stringConstant.referencedMember = programField;

    // Act
    nonPrivateMemberMarker.visitStringConstant(clazz, stringConstant);

    // Assert
    Member member = stringConstant.referencedMember;
    assertTrue(member instanceof ProgramField);
    Object processingInfo = member.getProcessingInfo();
    assertTrue(processingInfo instanceof ProgramFieldOptimizationInfo);
    assertFalse(((ProgramFieldOptimizationInfo) processingInfo).canBeMadePrivate());
  }

  /**
   * Test {@link NonPrivateMemberMarker#visitAnyRefConstant(Clazz, RefConstant)}.
   * <ul>
   *   <li>Then calls {@link LibraryClass#getClassName(int)}.</li>
   * </ul>
   * <p>
   * Method under test: {@link NonPrivateMemberMarker#visitAnyRefConstant(Clazz, RefConstant)}
   */
  @Test
  @DisplayName("Test visitAnyRefConstant(Clazz, RefConstant); then calls getClassName(int)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "void proguard.optimize.info.NonPrivateMemberMarker.visitAnyRefConstant(proguard.classfile.Clazz, proguard.classfile.constant.RefConstant)"})
  void testVisitAnyRefConstant_thenCallsGetClassName() {
    // Arrange
    NonPrivateMemberMarker nonPrivateMemberMarker = new NonPrivateMemberMarker();
    LibraryClass clazz = mock(LibraryClass.class);
    when(clazz.getClassName(anyInt())).thenReturn("Class Name");
    when(clazz.getName()).thenReturn("Name");

    // Act
    nonPrivateMemberMarker.visitAnyRefConstant(clazz, new FieldrefConstant());

    // Assert
    verify(clazz).getClassName(eq(0));
    verify(clazz).getName();
  }

  /**
   * Test {@link NonPrivateMemberMarker#visitAnyRefConstant(Clazz, RefConstant)}.
   * <ul>
   *   <li>Then calls {@link FieldrefConstant#referencedMemberAccept(MemberVisitor)}.</li>
   * </ul>
   * <p>
   * Method under test: {@link NonPrivateMemberMarker#visitAnyRefConstant(Clazz, RefConstant)}
   */
  @Test
  @DisplayName("Test visitAnyRefConstant(Clazz, RefConstant); then calls referencedMemberAccept(MemberVisitor)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "void proguard.optimize.info.NonPrivateMemberMarker.visitAnyRefConstant(proguard.classfile.Clazz, proguard.classfile.constant.RefConstant)"})
  void testVisitAnyRefConstant_thenCallsReferencedMemberAccept() {
    // Arrange
    NonPrivateMemberMarker nonPrivateMemberMarker = new NonPrivateMemberMarker();
    LibraryClass clazz = mock(LibraryClass.class);
    when(clazz.getName()).thenReturn("Name");
    FieldrefConstant refConstant = mock(FieldrefConstant.class);
    doNothing().when(refConstant).referencedMemberAccept(Mockito.<MemberVisitor>any());
    when(refConstant.getClassName(Mockito.<Clazz>any())).thenReturn("Class Name");

    // Act
    nonPrivateMemberMarker.visitAnyRefConstant(clazz, refConstant);

    // Assert
    verify(clazz).getName();
    verify(refConstant).referencedMemberAccept(isA(MemberVisitor.class));
    verify(refConstant).getClassName(isA(Clazz.class));
  }

  /**
   * Test {@link NonPrivateMemberMarker#visitAnyRefConstant(Clazz, RefConstant)}.
   * <ul>
   *   <li>When {@link LibraryClass} {@link LibraryClass#getClassName(int)} return {@code Name}.</li>
   * </ul>
   * <p>
   * Method under test: {@link NonPrivateMemberMarker#visitAnyRefConstant(Clazz, RefConstant)}
   */
  @Test
  @DisplayName("Test visitAnyRefConstant(Clazz, RefConstant); when LibraryClass getClassName(int) return 'Name'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "void proguard.optimize.info.NonPrivateMemberMarker.visitAnyRefConstant(proguard.classfile.Clazz, proguard.classfile.constant.RefConstant)"})
  void testVisitAnyRefConstant_whenLibraryClassGetClassNameReturnName() {
    // Arrange
    NonPrivateMemberMarker nonPrivateMemberMarker = new NonPrivateMemberMarker();
    LibraryClass clazz = mock(LibraryClass.class);
    when(clazz.getClassName(anyInt())).thenReturn("Name");
    when(clazz.getName()).thenReturn("Name");

    // Act
    nonPrivateMemberMarker.visitAnyRefConstant(clazz, new FieldrefConstant());

    // Assert
    verify(clazz).getClassName(eq(0));
    verify(clazz).getName();
  }

  /**
   * Test {@link NonPrivateMemberMarker#visitProgramMethod(ProgramClass, ProgramMethod)}.
   * <ul>
   *   <li>Then calls {@link ProgramMethodOptimizationInfo#setCanNotBeMadePrivate()}.</li>
   * </ul>
   * <p>
   * Method under test: {@link NonPrivateMemberMarker#visitProgramMethod(ProgramClass, ProgramMethod)}
   */
  @Test
  @DisplayName("Test visitProgramMethod(ProgramClass, ProgramMethod); then calls setCanNotBeMadePrivate()")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "void proguard.optimize.info.NonPrivateMemberMarker.visitProgramMethod(proguard.classfile.ProgramClass, proguard.classfile.ProgramMethod)"})
  void testVisitProgramMethod_thenCallsSetCanNotBeMadePrivate() {
    // Arrange
    NonPrivateMemberMarker nonPrivateMemberMarker = new NonPrivateMemberMarker();
    ProgramClass programClass = new ProgramClass();
    ProgramMethodOptimizationInfo programMethodOptimizationInfo = mock(ProgramMethodOptimizationInfo.class);
    doNothing().when(programMethodOptimizationInfo).setCanNotBeMadePrivate();
    ProgramMethod programMethod = mock(ProgramMethod.class);
    when(programMethod.getProcessingInfo()).thenReturn(programMethodOptimizationInfo);

    // Act
    nonPrivateMemberMarker.visitProgramMethod(programClass, programMethod);

    // Assert
    verify(programMethodOptimizationInfo).setCanNotBeMadePrivate();
    verify(programMethod, atLeast(1)).getProcessingInfo();
  }

  /**
   * Test {@link NonPrivateMemberMarker#canBeMadePrivate(Field)} with {@code field}.
   * <ul>
   *   <li>Given {@link FieldOptimizationInfo} (default constructor).</li>
   *   <li>Then return {@code false}.</li>
   * </ul>
   * <p>
   * Method under test: {@link NonPrivateMemberMarker#canBeMadePrivate(Field)}
   */
  @Test
  @DisplayName("Test canBeMadePrivate(Field) with 'field'; given FieldOptimizationInfo (default constructor); then return 'false'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "boolean proguard.optimize.info.NonPrivateMemberMarker.canBeMadePrivate(proguard.classfile.Field)"})
  void testCanBeMadePrivateWithField_givenFieldOptimizationInfo_thenReturnFalse() {
    // Arrange
    LibraryField field = new LibraryField(1, "Name", "Descriptor");
    field.setProcessingInfo(new FieldOptimizationInfo());

    // Act and Assert
    assertFalse(NonPrivateMemberMarker.canBeMadePrivate(field));
  }

  /**
   * Test {@link NonPrivateMemberMarker#canBeMadePrivate(Field)} with {@code field}.
   * <ul>
   *   <li>Then return {@code true}.</li>
   * </ul>
   * <p>
   * Method under test: {@link NonPrivateMemberMarker#canBeMadePrivate(Field)}
   */
  @Test
  @DisplayName("Test canBeMadePrivate(Field) with 'field'; then return 'true'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "boolean proguard.optimize.info.NonPrivateMemberMarker.canBeMadePrivate(proguard.classfile.Field)"})
  void testCanBeMadePrivateWithField_thenReturnTrue() {
    // Arrange
    LibraryField field = new LibraryField(1, "Name", "Descriptor");
    LibraryClass clazz = new LibraryClass();
    field.setProcessingInfo(new ProgramFieldOptimizationInfo(clazz, new LibraryField(1, "Name", "Descriptor"), true));

    // Act and Assert
    assertTrue(NonPrivateMemberMarker.canBeMadePrivate(field));
  }

  /**
   * Test {@link NonPrivateMemberMarker#canBeMadePrivate(Method)} with {@code method}.
   * <ul>
   *   <li>Given {@link MethodOptimizationInfo} (default constructor).</li>
   *   <li>Then return {@code false}.</li>
   * </ul>
   * <p>
   * Method under test: {@link NonPrivateMemberMarker#canBeMadePrivate(Method)}
   */
  @Test
  @DisplayName("Test canBeMadePrivate(Method) with 'method'; given MethodOptimizationInfo (default constructor); then return 'false'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "boolean proguard.optimize.info.NonPrivateMemberMarker.canBeMadePrivate(proguard.classfile.Method)"})
  void testCanBeMadePrivateWithMethod_givenMethodOptimizationInfo_thenReturnFalse() {
    // Arrange
    LibraryMethod method = new LibraryMethod(1, "Name", "Descriptor");
    method.setProcessingInfo(new MethodOptimizationInfo());

    // Act and Assert
    assertFalse(NonPrivateMemberMarker.canBeMadePrivate(method));
  }
}
