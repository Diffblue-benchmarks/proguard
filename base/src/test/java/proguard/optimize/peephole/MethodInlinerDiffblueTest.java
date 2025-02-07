package proguard.optimize.peephole;

import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.ArgumentMatchers.isNull;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.io.UnsupportedEncodingException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import proguard.classfile.Clazz;
import proguard.classfile.LibraryClass;
import proguard.classfile.LibraryMember;
import proguard.classfile.LibraryMethod;
import proguard.classfile.Method;
import proguard.classfile.ProgramClass;
import proguard.classfile.ProgramMember;
import proguard.classfile.ProgramMethod;
import proguard.classfile.attribute.CodeAttribute;
import proguard.classfile.attribute.ExtendedLineNumberInfo;
import proguard.classfile.attribute.LineNumberInfo;
import proguard.classfile.attribute.LineNumberTableAttribute;
import proguard.classfile.attribute.visitor.AttributeVisitor;
import proguard.classfile.attribute.visitor.ExceptionInfoVisitor;
import proguard.classfile.attribute.visitor.LineNumberInfoVisitor;
import proguard.classfile.constant.AnyMethodrefConstant;
import proguard.classfile.constant.InterfaceMethodrefConstant;
import proguard.classfile.instruction.visitor.InstructionVisitor;
import proguard.classfile.visitor.MemberVisitor;
import proguard.testutils.cpa.NamedClass;

class MethodInlinerDiffblueTest {
  /**
   * Test {@link MethodInliner#visitCodeAttribute(Clazz, Method, CodeAttribute)}.
   * <p>
   * Method under test: {@link MethodInliner#visitCodeAttribute(Clazz, Method, CodeAttribute)}
   */
  @Test
  @DisplayName("Test visitCodeAttribute(Clazz, Method, CodeAttribute)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "void proguard.optimize.peephole.MethodInliner.visitCodeAttribute(proguard.classfile.Clazz, proguard.classfile.Method, proguard.classfile.attribute.CodeAttribute)"})
  void testVisitCodeAttribute() throws UnsupportedEncodingException {
    // Arrange
    ShortMethodInliner shortMethodInliner = new ShortMethodInliner(true, true, true);
    NamedClass clazz = new NamedClass("Unexpected error while inlining method:");
    ProgramMethod method = mock(ProgramMethod.class);
    when(method.getDescriptor(Mockito.<Clazz>any())).thenReturn("Descriptor");
    when(method.getName(Mockito.<Clazz>any())).thenReturn("Name");

    // Act
    shortMethodInliner.visitCodeAttribute(clazz, method, new CodeAttribute(1, 3, 3, 3, "AXAXAXAX".getBytes("UTF-8")));

    // Assert
    verify(method, atLeast(1)).getDescriptor(isA(Clazz.class));
    verify(method, atLeast(1)).getName(isA(Clazz.class));
  }

  /**
   * Test {@link MethodInliner#visitCodeAttribute(Clazz, Method, CodeAttribute)}.
   * <ul>
   *   <li>Given {@code Descriptor}.</li>
   *   <li>Then calls {@link ProgramMember#getDescriptor(Clazz)}.</li>
   * </ul>
   * <p>
   * Method under test: {@link MethodInliner#visitCodeAttribute(Clazz, Method, CodeAttribute)}
   */
  @Test
  @DisplayName("Test visitCodeAttribute(Clazz, Method, CodeAttribute); given 'Descriptor'; then calls getDescriptor(Clazz)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "void proguard.optimize.peephole.MethodInliner.visitCodeAttribute(proguard.classfile.Clazz, proguard.classfile.Method, proguard.classfile.attribute.CodeAttribute)"})
  void testVisitCodeAttribute_givenDescriptor_thenCallsGetDescriptor() {
    // Arrange
    ShortMethodInliner shortMethodInliner = new ShortMethodInliner(true, true, true);
    NamedClass clazz = new NamedClass("Unexpected error while inlining method:");
    ProgramMethod method = mock(ProgramMethod.class);
    when(method.getDescriptor(Mockito.<Clazz>any())).thenReturn("Descriptor");
    when(method.getName(Mockito.<Clazz>any())).thenReturn("Name");

    // Act
    shortMethodInliner.visitCodeAttribute(clazz, method, new CodeAttribute(1));

    // Assert
    verify(method, atLeast(1)).getDescriptor(isA(Clazz.class));
    verify(method, atLeast(1)).getName(isA(Clazz.class));
  }

  /**
   * Test {@link MethodInliner#visitCodeAttribute(Clazz, Method, CodeAttribute)}.
   * <ul>
   *   <li>Given {@code Descriptor}.</li>
   *   <li>When {@code null}.</li>
   *   <li>Then calls {@link ProgramMember#getDescriptor(Clazz)}.</li>
   * </ul>
   * <p>
   * Method under test: {@link MethodInliner#visitCodeAttribute(Clazz, Method, CodeAttribute)}
   */
  @Test
  @DisplayName("Test visitCodeAttribute(Clazz, Method, CodeAttribute); given 'Descriptor'; when 'null'; then calls getDescriptor(Clazz)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "void proguard.optimize.peephole.MethodInliner.visitCodeAttribute(proguard.classfile.Clazz, proguard.classfile.Method, proguard.classfile.attribute.CodeAttribute)"})
  void testVisitCodeAttribute_givenDescriptor_whenNull_thenCallsGetDescriptor() {
    // Arrange
    ShortMethodInliner shortMethodInliner = new ShortMethodInliner(true, true, true);
    NamedClass clazz = new NamedClass("Unexpected error while inlining method:");
    ProgramMethod method = mock(ProgramMethod.class);
    when(method.getDescriptor(Mockito.<Clazz>any())).thenReturn("Descriptor");
    when(method.getName(Mockito.<Clazz>any())).thenReturn("Name");

    // Act
    shortMethodInliner.visitCodeAttribute(clazz, method, null);

    // Assert
    verify(method).getDescriptor(isA(Clazz.class));
    verify(method).getName(isA(Clazz.class));
  }

  /**
   * Test {@link MethodInliner#visitCodeAttribute(Clazz, Method, CodeAttribute)}.
   * <ul>
   *   <li>Given {@code <init>}.</li>
   *   <li>When {@link ProgramMethod} {@link ProgramMember#getName(Clazz)} return {@code <init>}.</li>
   * </ul>
   * <p>
   * Method under test: {@link MethodInliner#visitCodeAttribute(Clazz, Method, CodeAttribute)}
   */
  @Test
  @DisplayName("Test visitCodeAttribute(Clazz, Method, CodeAttribute); given '<init>'; when ProgramMethod getName(Clazz) return '<init>'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "void proguard.optimize.peephole.MethodInliner.visitCodeAttribute(proguard.classfile.Clazz, proguard.classfile.Method, proguard.classfile.attribute.CodeAttribute)"})
  void testVisitCodeAttribute_givenInit_whenProgramMethodGetNameReturnInit() {
    // Arrange
    ShortMethodInliner shortMethodInliner = new ShortMethodInliner(true, true, true);
    NamedClass clazz = new NamedClass("Unexpected error while inlining method:");
    ProgramMethod method = mock(ProgramMethod.class);
    when(method.getDescriptor(Mockito.<Clazz>any())).thenReturn("Descriptor");
    when(method.getName(Mockito.<Clazz>any())).thenReturn("<init>");

    // Act
    shortMethodInliner.visitCodeAttribute(clazz, method, new CodeAttribute(1));

    // Assert
    verify(method, atLeast(1)).getDescriptor(isA(Clazz.class));
    verify(method, atLeast(1)).getName(isA(Clazz.class));
  }

  /**
   * Test {@link MethodInliner#visitCodeAttribute(Clazz, Method, CodeAttribute)}.
   * <ul>
   *   <li>Given one.</li>
   *   <li>Then calls {@link ProgramMethod#accept(ProgramClass, MemberVisitor)}.</li>
   * </ul>
   * <p>
   * Method under test: {@link MethodInliner#visitCodeAttribute(Clazz, Method, CodeAttribute)}
   */
  @Test
  @DisplayName("Test visitCodeAttribute(Clazz, Method, CodeAttribute); given one; then calls accept(ProgramClass, MemberVisitor)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "void proguard.optimize.peephole.MethodInliner.visitCodeAttribute(proguard.classfile.Clazz, proguard.classfile.Method, proguard.classfile.attribute.CodeAttribute)"})
  void testVisitCodeAttribute_givenOne_thenCallsAccept() {
    // Arrange
    ShortMethodInliner shortMethodInliner = new ShortMethodInliner(true, true, true);
    NamedClass clazz = new NamedClass("Unexpected error while inlining method:");
    ProgramMethod method = mock(ProgramMethod.class);
    when(method.getProcessingFlags()).thenReturn(1);
    doNothing().when(method).accept(Mockito.<ProgramClass>any(), Mockito.<MemberVisitor>any());
    when(method.getName(Mockito.<Clazz>any())).thenReturn("Name");
    CodeAttribute codeAttribute = mock(CodeAttribute.class);
    doNothing().when(codeAttribute)
        .accept(Mockito.<Clazz>any(), Mockito.<Method>any(), Mockito.<AttributeVisitor>any());
    doNothing().when(codeAttribute)
        .attributesAccept(Mockito.<Clazz>any(), Mockito.<Method>any(), Mockito.<AttributeVisitor>any());
    doNothing().when(codeAttribute)
        .exceptionsAccept(Mockito.<Clazz>any(), Mockito.<Method>any(), Mockito.<ExceptionInfoVisitor>any());
    doNothing().when(codeAttribute)
        .instructionsAccept(Mockito.<Clazz>any(), Mockito.<Method>any(), Mockito.<InstructionVisitor>any());

    // Act
    shortMethodInliner.visitCodeAttribute(clazz, method, codeAttribute);

    // Assert
    verify(method).getName(isA(Clazz.class));
    verify(method).accept(isA(ProgramClass.class), isA(MemberVisitor.class));
    verify(codeAttribute).accept(isA(Clazz.class), isA(Method.class), isA(AttributeVisitor.class));
    verify(codeAttribute).attributesAccept(isA(Clazz.class), isA(Method.class), isA(AttributeVisitor.class));
    verify(codeAttribute).exceptionsAccept(isA(Clazz.class), isA(Method.class), isA(ExceptionInfoVisitor.class));
    verify(codeAttribute).instructionsAccept(isA(Clazz.class), isA(Method.class), isA(InstructionVisitor.class));
    verify(method).getProcessingFlags();
  }

  /**
   * Test {@link MethodInliner#visitCodeAttribute0(Clazz, Method, CodeAttribute)}.
   * <ul>
   *   <li>Given one.</li>
   *   <li>Then calls {@link ProgramMember#getName(Clazz)}.</li>
   * </ul>
   * <p>
   * Method under test: {@link MethodInliner#visitCodeAttribute0(Clazz, Method, CodeAttribute)}
   */
  @Test
  @DisplayName("Test visitCodeAttribute0(Clazz, Method, CodeAttribute); given one; then calls getName(Clazz)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "void proguard.optimize.peephole.MethodInliner.visitCodeAttribute0(proguard.classfile.Clazz, proguard.classfile.Method, proguard.classfile.attribute.CodeAttribute)"})
  void testVisitCodeAttribute0_givenOne_thenCallsGetName() {
    // Arrange
    ShortMethodInliner shortMethodInliner = new ShortMethodInliner(true, true, true);
    ProgramClass clazz = mock(ProgramClass.class);
    doNothing().when(clazz).addExtraFeatureName(Mockito.<String>any());
    clazz.addExtraFeatureName("Feature Name");
    ProgramMethod method = mock(ProgramMethod.class);
    when(method.getProcessingFlags()).thenReturn(1);
    doNothing().when(method).accept(Mockito.<ProgramClass>any(), Mockito.<MemberVisitor>any());
    when(method.getName(Mockito.<Clazz>any())).thenReturn("Name");
    CodeAttribute codeAttribute = mock(CodeAttribute.class);
    doNothing().when(codeAttribute)
        .accept(Mockito.<Clazz>any(), Mockito.<Method>any(), Mockito.<AttributeVisitor>any());
    doNothing().when(codeAttribute)
        .attributesAccept(Mockito.<Clazz>any(), Mockito.<Method>any(), Mockito.<AttributeVisitor>any());
    doNothing().when(codeAttribute)
        .exceptionsAccept(Mockito.<Clazz>any(), Mockito.<Method>any(), Mockito.<ExceptionInfoVisitor>any());
    doNothing().when(codeAttribute)
        .instructionsAccept(Mockito.<Clazz>any(), Mockito.<Method>any(), Mockito.<InstructionVisitor>any());

    // Act
    shortMethodInliner.visitCodeAttribute0(clazz, method, codeAttribute);

    // Assert
    verify(method).getName(isA(Clazz.class));
    verify(method).accept(isA(ProgramClass.class), isA(MemberVisitor.class));
    verify(codeAttribute).accept(isA(Clazz.class), isA(Method.class), isA(AttributeVisitor.class));
    verify(codeAttribute).attributesAccept(isA(Clazz.class), isA(Method.class), isA(AttributeVisitor.class));
    verify(codeAttribute).exceptionsAccept(isA(Clazz.class), isA(Method.class), isA(ExceptionInfoVisitor.class));
    verify(codeAttribute).instructionsAccept(isA(Clazz.class), isA(Method.class), isA(InstructionVisitor.class));
    verify(clazz).addExtraFeatureName(eq("Feature Name"));
    verify(method).getProcessingFlags();
  }

  /**
   * Test {@link MethodInliner#visitLineNumberTableAttribute(Clazz, Method, CodeAttribute, LineNumberTableAttribute)}.
   * <ul>
   *   <li>Then calls {@link LineNumberTableAttribute#lineNumbersAccept(Clazz, Method, CodeAttribute, LineNumberInfoVisitor)}.</li>
   * </ul>
   * <p>
   * Method under test: {@link MethodInliner#visitLineNumberTableAttribute(Clazz, Method, CodeAttribute, LineNumberTableAttribute)}
   */
  @Test
  @DisplayName("Test visitLineNumberTableAttribute(Clazz, Method, CodeAttribute, LineNumberTableAttribute); then calls lineNumbersAccept(Clazz, Method, CodeAttribute, LineNumberInfoVisitor)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "void proguard.optimize.peephole.MethodInliner.visitLineNumberTableAttribute(proguard.classfile.Clazz, proguard.classfile.Method, proguard.classfile.attribute.CodeAttribute, proguard.classfile.attribute.LineNumberTableAttribute)"})
  void testVisitLineNumberTableAttribute_thenCallsLineNumbersAccept() {
    // Arrange
    ShortMethodInliner shortMethodInliner = new ShortMethodInliner(true, true, true);
    LibraryClass clazz = new LibraryClass();
    LibraryMethod method = new LibraryMethod(1, "Name", "Descriptor");

    CodeAttribute codeAttribute = new CodeAttribute(1);
    LineNumberTableAttribute lineNumberTableAttribute = mock(LineNumberTableAttribute.class);
    doNothing().when(lineNumberTableAttribute)
        .lineNumbersAccept(Mockito.<Clazz>any(), Mockito.<Method>any(), Mockito.<CodeAttribute>any(),
            Mockito.<LineNumberInfoVisitor>any());

    // Act
    shortMethodInliner.visitLineNumberTableAttribute(clazz, method, codeAttribute, lineNumberTableAttribute);

    // Assert
    verify(lineNumberTableAttribute).lineNumbersAccept(isA(Clazz.class), isA(Method.class), isA(CodeAttribute.class),
        isA(LineNumberInfoVisitor.class));
  }

  /**
   * Test {@link MethodInliner#visitAnyMethodrefConstant(Clazz, AnyMethodrefConstant)}.
   * <ul>
   *   <li>Then calls {@link LibraryMember#accept(Clazz, MemberVisitor)}.</li>
   * </ul>
   * <p>
   * Method under test: {@link MethodInliner#visitAnyMethodrefConstant(Clazz, AnyMethodrefConstant)}
   */
  @Test
  @DisplayName("Test visitAnyMethodrefConstant(Clazz, AnyMethodrefConstant); then calls accept(Clazz, MemberVisitor)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "void proguard.optimize.peephole.MethodInliner.visitAnyMethodrefConstant(proguard.classfile.Clazz, proguard.classfile.constant.AnyMethodrefConstant)"})
  void testVisitAnyMethodrefConstant_thenCallsAccept() {
    // Arrange
    ShortMethodInliner shortMethodInliner = new ShortMethodInliner(true, true, true);
    LibraryClass clazz = new LibraryClass();
    LibraryMethod libraryMethod = mock(LibraryMethod.class);
    doNothing().when(libraryMethod).accept(Mockito.<Clazz>any(), Mockito.<MemberVisitor>any());
    InterfaceMethodrefConstant anyMethodrefConstant = new InterfaceMethodrefConstant();
    anyMethodrefConstant.referencedMethod = libraryMethod;

    // Act
    shortMethodInliner.visitAnyMethodrefConstant(clazz, anyMethodrefConstant);

    // Assert
    verify(libraryMethod).accept((Clazz) isNull(), isA(MemberVisitor.class));
  }

  /**
   * Test {@link MethodInliner#visitAnyMethodrefConstant(Clazz, AnyMethodrefConstant)}.
   * <ul>
   *   <li>Then calls {@link AnyMethodrefConstant#referencedMethodAccept(MemberVisitor)}.</li>
   * </ul>
   * <p>
   * Method under test: {@link MethodInliner#visitAnyMethodrefConstant(Clazz, AnyMethodrefConstant)}
   */
  @Test
  @DisplayName("Test visitAnyMethodrefConstant(Clazz, AnyMethodrefConstant); then calls referencedMethodAccept(MemberVisitor)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "void proguard.optimize.peephole.MethodInliner.visitAnyMethodrefConstant(proguard.classfile.Clazz, proguard.classfile.constant.AnyMethodrefConstant)"})
  void testVisitAnyMethodrefConstant_thenCallsReferencedMethodAccept() {
    // Arrange
    ShortMethodInliner shortMethodInliner = new ShortMethodInliner(true, true, true);
    LibraryClass clazz = new LibraryClass();
    InterfaceMethodrefConstant anyMethodrefConstant = mock(InterfaceMethodrefConstant.class);
    doNothing().when(anyMethodrefConstant).referencedMethodAccept(Mockito.<MemberVisitor>any());

    // Act
    shortMethodInliner.visitAnyMethodrefConstant(clazz, anyMethodrefConstant);

    // Assert
    verify(anyMethodrefConstant).referencedMethodAccept(isA(MemberVisitor.class));
  }

  /**
   * Test {@link MethodInliner#visitLineNumberInfo(Clazz, Method, CodeAttribute, LineNumberInfo)}.
   * <ul>
   *   <li>Given {@link IllegalArgumentException#IllegalArgumentException(String)} with {@code foo}.</li>
   *   <li>Then calls {@link ExtendedLineNumberInfo#getSource()}.</li>
   * </ul>
   * <p>
   * Method under test: {@link MethodInliner#visitLineNumberInfo(Clazz, Method, CodeAttribute, LineNumberInfo)}
   */
  @Test
  @DisplayName("Test visitLineNumberInfo(Clazz, Method, CodeAttribute, LineNumberInfo); given IllegalArgumentException(String) with 'foo'; then calls getSource()")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "void proguard.optimize.peephole.MethodInliner.visitLineNumberInfo(proguard.classfile.Clazz, proguard.classfile.Method, proguard.classfile.attribute.CodeAttribute, proguard.classfile.attribute.LineNumberInfo)"})
  void testVisitLineNumberInfo_givenIllegalArgumentExceptionWithFoo_thenCallsGetSource() {
    // Arrange
    ShortMethodInliner shortMethodInliner = new ShortMethodInliner(true, true, true);
    LibraryClass clazz = new LibraryClass();
    LibraryMethod method = new LibraryMethod(1, "Name", "Descriptor");

    CodeAttribute codeAttribute = new CodeAttribute(1);
    ExtendedLineNumberInfo lineNumberInfo = mock(ExtendedLineNumberInfo.class);
    when(lineNumberInfo.getSource()).thenThrow(new IllegalArgumentException("foo"));

    // Act
    shortMethodInliner.visitLineNumberInfo(clazz, method, codeAttribute, lineNumberInfo);

    // Assert
    verify(lineNumberInfo).getSource();
  }
}
