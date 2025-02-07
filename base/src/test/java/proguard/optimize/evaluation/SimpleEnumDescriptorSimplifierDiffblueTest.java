package proguard.optimize.evaluation;

import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.anyInt;
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
import proguard.classfile.LibraryMethod;
import proguard.classfile.Method;
import proguard.classfile.ProgramClass;
import proguard.classfile.ProgramField;
import proguard.classfile.ProgramMethod;
import proguard.classfile.attribute.CodeAttribute;
import proguard.classfile.attribute.LocalVariableInfo;
import proguard.classfile.attribute.SignatureAttribute;
import proguard.classfile.constant.InvokeDynamicConstant;
import proguard.classfile.constant.MethodTypeConstant;
import proguard.classfile.constant.visitor.ConstantVisitor;
import proguard.classfile.visitor.MemberVisitor;
import proguard.optimize.info.ClassOptimizationInfo;
import proguard.optimize.info.ProgramClassOptimizationInfo;

class SimpleEnumDescriptorSimplifierDiffblueTest {
  /**
   * Test {@link SimpleEnumDescriptorSimplifier#visitProgramClass(ProgramClass)}.
   * <ul>
   *   <li>Given {@code Name}.</li>
   *   <li>Then calls {@link ProgramClass#constantPoolEntriesAccept(ConstantVisitor)}.</li>
   * </ul>
   * <p>
   * Method under test: {@link SimpleEnumDescriptorSimplifier#visitProgramClass(ProgramClass)}
   */
  @Test
  @DisplayName("Test visitProgramClass(ProgramClass); given 'Name'; then calls constantPoolEntriesAccept(ConstantVisitor)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "void proguard.optimize.evaluation.SimpleEnumDescriptorSimplifier.visitProgramClass(proguard.classfile.ProgramClass)"})
  void testVisitProgramClass_givenName_thenCallsConstantPoolEntriesAccept() {
    // Arrange
    SimpleEnumDescriptorSimplifier simpleEnumDescriptorSimplifier = new SimpleEnumDescriptorSimplifier();
    ProgramClass programClass = mock(ProgramClass.class);
    when(programClass.getName()).thenReturn("Name");
    doNothing().when(programClass).constantPoolEntriesAccept(Mockito.<ConstantVisitor>any());
    doNothing().when(programClass).fieldsAccept(Mockito.<MemberVisitor>any());
    doNothing().when(programClass).methodsAccept(Mockito.<MemberVisitor>any());

    // Act
    simpleEnumDescriptorSimplifier.visitProgramClass(programClass);

    // Assert
    verify(programClass).constantPoolEntriesAccept(isA(ConstantVisitor.class));
    verify(programClass).fieldsAccept(isA(MemberVisitor.class));
    verify(programClass).getName();
    verify(programClass).methodsAccept(isA(MemberVisitor.class));
  }

  /**
   * Test {@link SimpleEnumDescriptorSimplifier#visitInvokeDynamicConstant(Clazz, InvokeDynamicConstant)}.
   * <ul>
   *   <li>Given {@code Before: [{}]}.</li>
   *   <li>When {@link LibraryClass} {@link LibraryClass#getType(int)} return {@code Before: [{}]}.</li>
   * </ul>
   * <p>
   * Method under test: {@link SimpleEnumDescriptorSimplifier#visitInvokeDynamicConstant(Clazz, InvokeDynamicConstant)}
   */
  @Test
  @DisplayName("Test visitInvokeDynamicConstant(Clazz, InvokeDynamicConstant); given 'Before: [{}]'; when LibraryClass getType(int) return 'Before: [{}]'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "void proguard.optimize.evaluation.SimpleEnumDescriptorSimplifier.visitInvokeDynamicConstant(proguard.classfile.Clazz, proguard.classfile.constant.InvokeDynamicConstant)"})
  void testVisitInvokeDynamicConstant_givenBefore_whenLibraryClassGetTypeReturnBefore() {
    // Arrange
    SimpleEnumDescriptorSimplifier simpleEnumDescriptorSimplifier = new SimpleEnumDescriptorSimplifier();
    LibraryClass clazz = mock(LibraryClass.class);
    when(clazz.getType(anyInt())).thenReturn("  Before: [{}]");

    // Act
    simpleEnumDescriptorSimplifier.visitInvokeDynamicConstant(clazz,
        new InvokeDynamicConstant(1, 1, new Clazz[]{new LibraryClass()}));

    // Assert
    verify(clazz).getType(eq(1));
  }

  /**
   * Test {@link SimpleEnumDescriptorSimplifier#visitInvokeDynamicConstant(Clazz, InvokeDynamicConstant)}.
   * <ul>
   *   <li>Given {@code Type}.</li>
   *   <li>When {@link LibraryClass}.</li>
   *   <li>Then calls {@link InvokeDynamicConstant#getType(Clazz)}.</li>
   * </ul>
   * <p>
   * Method under test: {@link SimpleEnumDescriptorSimplifier#visitInvokeDynamicConstant(Clazz, InvokeDynamicConstant)}
   */
  @Test
  @DisplayName("Test visitInvokeDynamicConstant(Clazz, InvokeDynamicConstant); given 'Type'; when LibraryClass; then calls getType(Clazz)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "void proguard.optimize.evaluation.SimpleEnumDescriptorSimplifier.visitInvokeDynamicConstant(proguard.classfile.Clazz, proguard.classfile.constant.InvokeDynamicConstant)"})
  void testVisitInvokeDynamicConstant_givenType_whenLibraryClass_thenCallsGetType() {
    // Arrange
    SimpleEnumDescriptorSimplifier simpleEnumDescriptorSimplifier = new SimpleEnumDescriptorSimplifier();
    LibraryClass clazz = mock(LibraryClass.class);
    InvokeDynamicConstant invokeDynamicConstant = mock(InvokeDynamicConstant.class);
    when(invokeDynamicConstant.getType(Mockito.<Clazz>any())).thenReturn("Type");

    // Act
    simpleEnumDescriptorSimplifier.visitInvokeDynamicConstant(clazz, invokeDynamicConstant);

    // Assert
    verify(invokeDynamicConstant).getType(isA(Clazz.class));
  }

  /**
   * Test {@link SimpleEnumDescriptorSimplifier#visitInvokeDynamicConstant(Clazz, InvokeDynamicConstant)}.
   * <ul>
   *   <li>Then calls {@link LibraryClass#getType(int)}.</li>
   * </ul>
   * <p>
   * Method under test: {@link SimpleEnumDescriptorSimplifier#visitInvokeDynamicConstant(Clazz, InvokeDynamicConstant)}
   */
  @Test
  @DisplayName("Test visitInvokeDynamicConstant(Clazz, InvokeDynamicConstant); then calls getType(int)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "void proguard.optimize.evaluation.SimpleEnumDescriptorSimplifier.visitInvokeDynamicConstant(proguard.classfile.Clazz, proguard.classfile.constant.InvokeDynamicConstant)"})
  void testVisitInvokeDynamicConstant_thenCallsGetType() {
    // Arrange
    SimpleEnumDescriptorSimplifier simpleEnumDescriptorSimplifier = new SimpleEnumDescriptorSimplifier();
    LibraryClass clazz = mock(LibraryClass.class);
    when(clazz.getType(anyInt())).thenReturn("Type");

    // Act
    simpleEnumDescriptorSimplifier.visitInvokeDynamicConstant(clazz, new InvokeDynamicConstant());

    // Assert
    verify(clazz).getType(eq(0));
  }

  /**
   * Test {@link SimpleEnumDescriptorSimplifier#visitMethodTypeConstant(Clazz, MethodTypeConstant)}.
   * <ul>
   *   <li>Given {@code Before: [{}]}.</li>
   *   <li>When {@link LibraryClass} {@link LibraryClass#getString(int)} return {@code Before: [{}]}.</li>
   * </ul>
   * <p>
   * Method under test: {@link SimpleEnumDescriptorSimplifier#visitMethodTypeConstant(Clazz, MethodTypeConstant)}
   */
  @Test
  @DisplayName("Test visitMethodTypeConstant(Clazz, MethodTypeConstant); given 'Before: [{}]'; when LibraryClass getString(int) return 'Before: [{}]'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "void proguard.optimize.evaluation.SimpleEnumDescriptorSimplifier.visitMethodTypeConstant(proguard.classfile.Clazz, proguard.classfile.constant.MethodTypeConstant)"})
  void testVisitMethodTypeConstant_givenBefore_whenLibraryClassGetStringReturnBefore() {
    // Arrange
    SimpleEnumDescriptorSimplifier simpleEnumDescriptorSimplifier = new SimpleEnumDescriptorSimplifier();
    LibraryClass clazz = mock(LibraryClass.class);
    when(clazz.getString(anyInt())).thenReturn("  Before: [{}]");

    // Act
    simpleEnumDescriptorSimplifier.visitMethodTypeConstant(clazz,
        new MethodTypeConstant(1, new Clazz[]{new LibraryClass()}));

    // Assert
    verify(clazz).getString(eq(1));
  }

  /**
   * Test {@link SimpleEnumDescriptorSimplifier#visitMethodTypeConstant(Clazz, MethodTypeConstant)}.
   * <ul>
   *   <li>Given {@code String}.</li>
   *   <li>When {@link LibraryClass} {@link LibraryClass#getString(int)} return {@code String}.</li>
   * </ul>
   * <p>
   * Method under test: {@link SimpleEnumDescriptorSimplifier#visitMethodTypeConstant(Clazz, MethodTypeConstant)}
   */
  @Test
  @DisplayName("Test visitMethodTypeConstant(Clazz, MethodTypeConstant); given 'String'; when LibraryClass getString(int) return 'String'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "void proguard.optimize.evaluation.SimpleEnumDescriptorSimplifier.visitMethodTypeConstant(proguard.classfile.Clazz, proguard.classfile.constant.MethodTypeConstant)"})
  void testVisitMethodTypeConstant_givenString_whenLibraryClassGetStringReturnString() {
    // Arrange
    SimpleEnumDescriptorSimplifier simpleEnumDescriptorSimplifier = new SimpleEnumDescriptorSimplifier();
    LibraryClass clazz = mock(LibraryClass.class);
    when(clazz.getString(anyInt())).thenReturn("String");

    // Act
    simpleEnumDescriptorSimplifier.visitMethodTypeConstant(clazz, new MethodTypeConstant());

    // Assert
    verify(clazz).getString(eq(0));
  }

  /**
   * Test {@link SimpleEnumDescriptorSimplifier#visitMethodTypeConstant(Clazz, MethodTypeConstant)}.
   * <ul>
   *   <li>Given {@code String}.</li>
   *   <li>When {@link LibraryClass} {@link LibraryClass#getString(int)} return {@code String}.</li>
   * </ul>
   * <p>
   * Method under test: {@link SimpleEnumDescriptorSimplifier#visitMethodTypeConstant(Clazz, MethodTypeConstant)}
   */
  @Test
  @DisplayName("Test visitMethodTypeConstant(Clazz, MethodTypeConstant); given 'String'; when LibraryClass getString(int) return 'String'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "void proguard.optimize.evaluation.SimpleEnumDescriptorSimplifier.visitMethodTypeConstant(proguard.classfile.Clazz, proguard.classfile.constant.MethodTypeConstant)"})
  void testVisitMethodTypeConstant_givenString_whenLibraryClassGetStringReturnString2() {
    // Arrange
    SimpleEnumDescriptorSimplifier simpleEnumDescriptorSimplifier = new SimpleEnumDescriptorSimplifier();
    LibraryClass clazz = mock(LibraryClass.class);
    when(clazz.getString(anyInt())).thenReturn("String");

    // Act
    simpleEnumDescriptorSimplifier.visitMethodTypeConstant(clazz,
        new MethodTypeConstant(1, new Clazz[]{new LibraryClass()}));

    // Assert
    verify(clazz).getString(eq(1));
  }

  /**
   * Test {@link SimpleEnumDescriptorSimplifier#visitMethodTypeConstant(Clazz, MethodTypeConstant)}.
   * <ul>
   *   <li>Given {@code Type}.</li>
   *   <li>When {@link LibraryClass}.</li>
   *   <li>Then calls {@link MethodTypeConstant#getType(Clazz)}.</li>
   * </ul>
   * <p>
   * Method under test: {@link SimpleEnumDescriptorSimplifier#visitMethodTypeConstant(Clazz, MethodTypeConstant)}
   */
  @Test
  @DisplayName("Test visitMethodTypeConstant(Clazz, MethodTypeConstant); given 'Type'; when LibraryClass; then calls getType(Clazz)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "void proguard.optimize.evaluation.SimpleEnumDescriptorSimplifier.visitMethodTypeConstant(proguard.classfile.Clazz, proguard.classfile.constant.MethodTypeConstant)"})
  void testVisitMethodTypeConstant_givenType_whenLibraryClass_thenCallsGetType() {
    // Arrange
    SimpleEnumDescriptorSimplifier simpleEnumDescriptorSimplifier = new SimpleEnumDescriptorSimplifier();
    LibraryClass clazz = mock(LibraryClass.class);
    MethodTypeConstant methodTypeConstant = mock(MethodTypeConstant.class);
    when(methodTypeConstant.getType(Mockito.<Clazz>any())).thenReturn("Type");

    // Act
    simpleEnumDescriptorSimplifier.visitMethodTypeConstant(clazz, methodTypeConstant);

    // Assert
    verify(methodTypeConstant).getType(isA(Clazz.class));
  }

  /**
   * Test {@link SimpleEnumDescriptorSimplifier#visitProgramField(ProgramClass, ProgramField)}.
   * <ul>
   *   <li>Given {@link ClassOptimizationInfo} (default constructor).</li>
   * </ul>
   * <p>
   * Method under test: {@link SimpleEnumDescriptorSimplifier#visitProgramField(ProgramClass, ProgramField)}
   */
  @Test
  @DisplayName("Test visitProgramField(ProgramClass, ProgramField); given ClassOptimizationInfo (default constructor)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "void proguard.optimize.evaluation.SimpleEnumDescriptorSimplifier.visitProgramField(proguard.classfile.ProgramClass, proguard.classfile.ProgramField)"})
  void testVisitProgramField_givenClassOptimizationInfo() {
    // Arrange
    SimpleEnumDescriptorSimplifier simpleEnumDescriptorSimplifier = new SimpleEnumDescriptorSimplifier();
    ProgramClass programClass = mock(ProgramClass.class);
    when(programClass.getString(anyInt())).thenReturn("String");
    Clazz referencedClass = mock(Clazz.class);
    when(referencedClass.getProcessingInfo()).thenReturn(new ClassOptimizationInfo());

    // Act
    simpleEnumDescriptorSimplifier.visitProgramField(programClass, new ProgramField(1, 1, 1, referencedClass));

    // Assert
    verify(programClass).getString(eq(1));
    verify(referencedClass).getProcessingInfo();
  }

  /**
   * Test {@link SimpleEnumDescriptorSimplifier#visitProgramField(ProgramClass, ProgramField)}.
   * <ul>
   *   <li>Given {@link ProgramClassOptimizationInfo} (default constructor).</li>
   * </ul>
   * <p>
   * Method under test: {@link SimpleEnumDescriptorSimplifier#visitProgramField(ProgramClass, ProgramField)}
   */
  @Test
  @DisplayName("Test visitProgramField(ProgramClass, ProgramField); given ProgramClassOptimizationInfo (default constructor)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "void proguard.optimize.evaluation.SimpleEnumDescriptorSimplifier.visitProgramField(proguard.classfile.ProgramClass, proguard.classfile.ProgramField)"})
  void testVisitProgramField_givenProgramClassOptimizationInfo() {
    // Arrange
    SimpleEnumDescriptorSimplifier simpleEnumDescriptorSimplifier = new SimpleEnumDescriptorSimplifier();
    ProgramClass programClass = mock(ProgramClass.class);
    when(programClass.getString(anyInt())).thenReturn("String");
    Clazz referencedClass = mock(Clazz.class);
    when(referencedClass.getProcessingInfo()).thenReturn(new ProgramClassOptimizationInfo());

    // Act
    simpleEnumDescriptorSimplifier.visitProgramField(programClass, new ProgramField(1, 1, 1, referencedClass));

    // Assert
    verify(programClass).getString(eq(1));
    verify(referencedClass).getProcessingInfo();
  }

  /**
   * Test {@link SimpleEnumDescriptorSimplifier#visitProgramField(ProgramClass, ProgramField)}.
   * <ul>
   *   <li>Given {@code String}.</li>
   *   <li>When {@link ProgramField#ProgramField()}.</li>
   *   <li>Then calls {@link ProgramClass#getString(int)}.</li>
   * </ul>
   * <p>
   * Method under test: {@link SimpleEnumDescriptorSimplifier#visitProgramField(ProgramClass, ProgramField)}
   */
  @Test
  @DisplayName("Test visitProgramField(ProgramClass, ProgramField); given 'String'; when ProgramField(); then calls getString(int)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "void proguard.optimize.evaluation.SimpleEnumDescriptorSimplifier.visitProgramField(proguard.classfile.ProgramClass, proguard.classfile.ProgramField)"})
  void testVisitProgramField_givenString_whenProgramField_thenCallsGetString() {
    // Arrange
    SimpleEnumDescriptorSimplifier simpleEnumDescriptorSimplifier = new SimpleEnumDescriptorSimplifier();
    ProgramClass programClass = mock(ProgramClass.class);
    when(programClass.getString(anyInt())).thenReturn("String");

    // Act
    simpleEnumDescriptorSimplifier.visitProgramField(programClass, new ProgramField());

    // Assert
    verify(programClass).getString(eq(0));
  }

  /**
   * Test {@link SimpleEnumDescriptorSimplifier#visitProgramMethod(ProgramClass, ProgramMethod)}.
   * <ul>
   *   <li>Given {@code Before: [{}]}.</li>
   *   <li>When {@link ProgramClass} {@link ProgramClass#getString(int)} return {@code Before: [{}]}.</li>
   * </ul>
   * <p>
   * Method under test: {@link SimpleEnumDescriptorSimplifier#visitProgramMethod(ProgramClass, ProgramMethod)}
   */
  @Test
  @DisplayName("Test visitProgramMethod(ProgramClass, ProgramMethod); given 'Before: [{}]'; when ProgramClass getString(int) return 'Before: [{}]'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "void proguard.optimize.evaluation.SimpleEnumDescriptorSimplifier.visitProgramMethod(proguard.classfile.ProgramClass, proguard.classfile.ProgramMethod)"})
  void testVisitProgramMethod_givenBefore_whenProgramClassGetStringReturnBefore() {
    // Arrange
    SimpleEnumDescriptorSimplifier simpleEnumDescriptorSimplifier = new SimpleEnumDescriptorSimplifier();
    ProgramClass programClass = mock(ProgramClass.class);
    when(programClass.getString(anyInt())).thenReturn("  Before: [{}]");

    // Act
    simpleEnumDescriptorSimplifier.visitProgramMethod(programClass,
        new ProgramMethod(1, 1, 1, new Clazz[]{new LibraryClass()}));

    // Assert
    verify(programClass).getString(eq(1));
  }

  /**
   * Test {@link SimpleEnumDescriptorSimplifier#visitProgramMethod(ProgramClass, ProgramMethod)}.
   * <ul>
   *   <li>Given {@code String}.</li>
   *   <li>When {@link ProgramMethod#ProgramMethod()}.</li>
   *   <li>Then calls {@link ProgramClass#getString(int)}.</li>
   * </ul>
   * <p>
   * Method under test: {@link SimpleEnumDescriptorSimplifier#visitProgramMethod(ProgramClass, ProgramMethod)}
   */
  @Test
  @DisplayName("Test visitProgramMethod(ProgramClass, ProgramMethod); given 'String'; when ProgramMethod(); then calls getString(int)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "void proguard.optimize.evaluation.SimpleEnumDescriptorSimplifier.visitProgramMethod(proguard.classfile.ProgramClass, proguard.classfile.ProgramMethod)"})
  void testVisitProgramMethod_givenString_whenProgramMethod_thenCallsGetString() {
    // Arrange
    SimpleEnumDescriptorSimplifier simpleEnumDescriptorSimplifier = new SimpleEnumDescriptorSimplifier();
    ProgramClass programClass = mock(ProgramClass.class);
    when(programClass.getString(anyInt())).thenReturn("String");

    // Act
    simpleEnumDescriptorSimplifier.visitProgramMethod(programClass, new ProgramMethod());

    // Assert
    verify(programClass).getString(eq(0));
  }

  /**
   * Test {@link SimpleEnumDescriptorSimplifier#visitProgramMethod(ProgramClass, ProgramMethod)}.
   * <ul>
   *   <li>Then calls {@link ProgramClass#getString(int)}.</li>
   * </ul>
   * <p>
   * Method under test: {@link SimpleEnumDescriptorSimplifier#visitProgramMethod(ProgramClass, ProgramMethod)}
   */
  @Test
  @DisplayName("Test visitProgramMethod(ProgramClass, ProgramMethod); then calls getString(int)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "void proguard.optimize.evaluation.SimpleEnumDescriptorSimplifier.visitProgramMethod(proguard.classfile.ProgramClass, proguard.classfile.ProgramMethod)"})
  void testVisitProgramMethod_thenCallsGetString() {
    // Arrange
    SimpleEnumDescriptorSimplifier simpleEnumDescriptorSimplifier = new SimpleEnumDescriptorSimplifier();
    ProgramClass programClass = mock(ProgramClass.class);
    when(programClass.getString(anyInt())).thenReturn("String");

    // Act
    simpleEnumDescriptorSimplifier.visitProgramMethod(programClass,
        new ProgramMethod(1, 1, 1, new Clazz[]{new LibraryClass()}));

    // Assert
    verify(programClass).getString(eq(1));
  }

  /**
   * Test {@link SimpleEnumDescriptorSimplifier#visitSignatureAttribute(Clazz, SignatureAttribute)} with {@code clazz}, {@code signatureAttribute}.
   * <ul>
   *   <li>Then calls {@link SignatureAttribute#getSignature(Clazz)}.</li>
   * </ul>
   * <p>
   * Method under test: {@link SimpleEnumDescriptorSimplifier#visitSignatureAttribute(Clazz, SignatureAttribute)}
   */
  @Test
  @DisplayName("Test visitSignatureAttribute(Clazz, SignatureAttribute) with 'clazz', 'signatureAttribute'; then calls getSignature(Clazz)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "void proguard.optimize.evaluation.SimpleEnumDescriptorSimplifier.visitSignatureAttribute(proguard.classfile.Clazz, proguard.classfile.attribute.SignatureAttribute)"})
  void testVisitSignatureAttributeWithClazzSignatureAttribute_thenCallsGetSignature() {
    // Arrange
    SimpleEnumDescriptorSimplifier simpleEnumDescriptorSimplifier = new SimpleEnumDescriptorSimplifier();
    LibraryClass clazz = mock(LibraryClass.class);
    SignatureAttribute signatureAttribute = mock(SignatureAttribute.class);
    when(signatureAttribute.getSignature(Mockito.<Clazz>any())).thenReturn("Signature");

    // Act
    simpleEnumDescriptorSimplifier.visitSignatureAttribute(clazz, signatureAttribute);

    // Assert
    verify(signatureAttribute).getSignature(isA(Clazz.class));
  }

  /**
   * Test {@link SimpleEnumDescriptorSimplifier#visitSignatureAttribute(Clazz, SignatureAttribute)} with {@code clazz}, {@code signatureAttribute}.
   * <ul>
   *   <li>Then calls {@link LibraryClass#getString(int)}.</li>
   * </ul>
   * <p>
   * Method under test: {@link SimpleEnumDescriptorSimplifier#visitSignatureAttribute(Clazz, SignatureAttribute)}
   */
  @Test
  @DisplayName("Test visitSignatureAttribute(Clazz, SignatureAttribute) with 'clazz', 'signatureAttribute'; then calls getString(int)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "void proguard.optimize.evaluation.SimpleEnumDescriptorSimplifier.visitSignatureAttribute(proguard.classfile.Clazz, proguard.classfile.attribute.SignatureAttribute)"})
  void testVisitSignatureAttributeWithClazzSignatureAttribute_thenCallsGetString() {
    // Arrange
    SimpleEnumDescriptorSimplifier simpleEnumDescriptorSimplifier = new SimpleEnumDescriptorSimplifier();
    LibraryClass clazz = mock(LibraryClass.class);
    when(clazz.getString(anyInt())).thenReturn("String");

    // Act
    simpleEnumDescriptorSimplifier.visitSignatureAttribute(clazz, new SignatureAttribute(1, 1));

    // Assert
    verify(clazz).getString(eq(1));
  }

  /**
   * Test {@link SimpleEnumDescriptorSimplifier#visitLocalVariableInfo(Clazz, Method, CodeAttribute, LocalVariableInfo)}.
   * <ul>
   *   <li>Given {@code Descriptor}.</li>
   *   <li>Then calls {@link LocalVariableInfo#getDescriptor(Clazz)}.</li>
   * </ul>
   * <p>
   * Method under test: {@link SimpleEnumDescriptorSimplifier#visitLocalVariableInfo(Clazz, Method, CodeAttribute, LocalVariableInfo)}
   */
  @Test
  @DisplayName("Test visitLocalVariableInfo(Clazz, Method, CodeAttribute, LocalVariableInfo); given 'Descriptor'; then calls getDescriptor(Clazz)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "void proguard.optimize.evaluation.SimpleEnumDescriptorSimplifier.visitLocalVariableInfo(proguard.classfile.Clazz, proguard.classfile.Method, proguard.classfile.attribute.CodeAttribute, proguard.classfile.attribute.LocalVariableInfo)"})
  void testVisitLocalVariableInfo_givenDescriptor_thenCallsGetDescriptor() {
    // Arrange
    SimpleEnumDescriptorSimplifier simpleEnumDescriptorSimplifier = new SimpleEnumDescriptorSimplifier();
    LibraryClass clazz = mock(LibraryClass.class);
    LibraryMethod method = new LibraryMethod(1, "Name", "Descriptor");

    CodeAttribute codeAttribute = new CodeAttribute(1);
    LocalVariableInfo localVariableInfo = mock(LocalVariableInfo.class);
    when(localVariableInfo.getDescriptor(Mockito.<Clazz>any())).thenReturn("Descriptor");

    // Act
    simpleEnumDescriptorSimplifier.visitLocalVariableInfo(clazz, method, codeAttribute, localVariableInfo);

    // Assert
    verify(localVariableInfo).getDescriptor(isA(Clazz.class));
  }

  /**
   * Test {@link SimpleEnumDescriptorSimplifier#visitLocalVariableInfo(Clazz, Method, CodeAttribute, LocalVariableInfo)}.
   * <ul>
   *   <li>Given {@code String}.</li>
   *   <li>Then calls {@link LibraryClass#getString(int)}.</li>
   * </ul>
   * <p>
   * Method under test: {@link SimpleEnumDescriptorSimplifier#visitLocalVariableInfo(Clazz, Method, CodeAttribute, LocalVariableInfo)}
   */
  @Test
  @DisplayName("Test visitLocalVariableInfo(Clazz, Method, CodeAttribute, LocalVariableInfo); given 'String'; then calls getString(int)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "void proguard.optimize.evaluation.SimpleEnumDescriptorSimplifier.visitLocalVariableInfo(proguard.classfile.Clazz, proguard.classfile.Method, proguard.classfile.attribute.CodeAttribute, proguard.classfile.attribute.LocalVariableInfo)"})
  void testVisitLocalVariableInfo_givenString_thenCallsGetString() {
    // Arrange
    SimpleEnumDescriptorSimplifier simpleEnumDescriptorSimplifier = new SimpleEnumDescriptorSimplifier();
    LibraryClass clazz = mock(LibraryClass.class);
    when(clazz.getString(anyInt())).thenReturn("String");
    LibraryMethod method = new LibraryMethod(1, "Name", "Descriptor");

    CodeAttribute codeAttribute = new CodeAttribute(1);

    // Act
    simpleEnumDescriptorSimplifier.visitLocalVariableInfo(clazz, method, codeAttribute,
        new LocalVariableInfo(1, 3, 1, 1, 1));

    // Assert
    verify(clazz).getString(eq(1));
  }
}
