package proguard.optimize;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.io.UnsupportedEncodingException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import proguard.classfile.Clazz;
import proguard.classfile.Field;
import proguard.classfile.LibraryClass;
import proguard.classfile.LibraryField;
import proguard.classfile.LibraryMethod;
import proguard.classfile.Method;
import proguard.classfile.attribute.CodeAttribute;
import proguard.classfile.attribute.ConstantValueAttribute;
import proguard.classfile.attribute.DeprecatedAttribute;
import proguard.classfile.attribute.EnclosingMethodAttribute;
import proguard.classfile.attribute.ExceptionsAttribute;
import proguard.classfile.attribute.InnerClassesAttribute;
import proguard.classfile.attribute.LineNumberTableAttribute;
import proguard.classfile.attribute.LocalVariableTableAttribute;
import proguard.classfile.attribute.LocalVariableTypeTableAttribute;
import proguard.classfile.attribute.MethodParametersAttribute;
import proguard.classfile.attribute.NestHostAttribute;
import proguard.classfile.attribute.NestMembersAttribute;
import proguard.classfile.attribute.PermittedSubclassesAttribute;
import proguard.classfile.attribute.SignatureAttribute;
import proguard.classfile.attribute.SourceDebugExtensionAttribute;
import proguard.classfile.attribute.SourceDirAttribute;
import proguard.classfile.attribute.SourceFileAttribute;
import proguard.classfile.attribute.SyntheticAttribute;
import proguard.classfile.attribute.UnknownAttribute;
import proguard.classfile.attribute.annotation.AnnotationDefaultAttribute;
import proguard.classfile.attribute.annotation.RuntimeInvisibleAnnotationsAttribute;
import proguard.classfile.attribute.annotation.RuntimeInvisibleParameterAnnotationsAttribute;
import proguard.classfile.attribute.annotation.RuntimeInvisibleTypeAnnotationsAttribute;
import proguard.classfile.attribute.annotation.RuntimeVisibleAnnotationsAttribute;
import proguard.classfile.attribute.annotation.RuntimeVisibleParameterAnnotationsAttribute;
import proguard.classfile.attribute.annotation.RuntimeVisibleTypeAnnotationsAttribute;
import proguard.classfile.attribute.module.ModuleAttribute;
import proguard.classfile.attribute.module.ModuleMainClassAttribute;
import proguard.classfile.attribute.module.ModulePackagesAttribute;
import proguard.classfile.attribute.preverification.StackMapAttribute;
import proguard.classfile.attribute.preverification.StackMapTableAttribute;
import proguard.classfile.attribute.visitor.AllAttributeVisitor;
import proguard.classfile.attribute.visitor.AttributeVisitor;
import proguard.fixer.kotlin.KotlinAnnotationCounter;
import proguard.obfuscate.kotlin.KotlinSourceDebugExtensionAttributeObfuscator;

class ChangedCodePrinterDiffblueTest {
  /**
   * Test {@link ChangedCodePrinter#visitUnknownAttribute(Clazz, UnknownAttribute)}.
   * <ul>
   *   <li>Then calls {@link AttributeVisitor#visitUnknownAttribute(Clazz, UnknownAttribute)}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ChangedCodePrinter#visitUnknownAttribute(Clazz, UnknownAttribute)}
   */
  @Test
  @DisplayName("Test visitUnknownAttribute(Clazz, UnknownAttribute); then calls visitUnknownAttribute(Clazz, UnknownAttribute)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "void proguard.optimize.ChangedCodePrinter.visitUnknownAttribute(proguard.classfile.Clazz, proguard.classfile.attribute.UnknownAttribute)"})
  void testVisitUnknownAttribute_thenCallsVisitUnknownAttribute() {
    // Arrange
    AllAttributeVisitor attributeVisitor = mock(AllAttributeVisitor.class);
    doNothing().when(attributeVisitor).visitUnknownAttribute(Mockito.<Clazz>any(), Mockito.<UnknownAttribute>any());
    ChangedCodePrinter changedCodePrinter = new ChangedCodePrinter(attributeVisitor);
    LibraryClass clazz = new LibraryClass();

    // Act
    changedCodePrinter.visitUnknownAttribute(clazz, new UnknownAttribute(1, 3));

    // Assert
    verify(attributeVisitor).visitUnknownAttribute(isA(Clazz.class), isA(UnknownAttribute.class));
  }

  /**
   * Test {@link ChangedCodePrinter#visitSourceDebugExtensionAttribute(Clazz, SourceDebugExtensionAttribute)}.
   * <p>
   * Method under test: {@link ChangedCodePrinter#visitSourceDebugExtensionAttribute(Clazz, SourceDebugExtensionAttribute)}
   */
  @Test
  @DisplayName("Test visitSourceDebugExtensionAttribute(Clazz, SourceDebugExtensionAttribute)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "void proguard.optimize.ChangedCodePrinter.visitSourceDebugExtensionAttribute(proguard.classfile.Clazz, proguard.classfile.attribute.SourceDebugExtensionAttribute)"})
  void testVisitSourceDebugExtensionAttribute() {
    // Arrange
    ChangedCodePrinter changedCodePrinter = new ChangedCodePrinter(new KotlinAnnotationCounter());
    LibraryClass clazz = new LibraryClass();
    SourceDebugExtensionAttribute sourceDebugExtensionAttribute = new SourceDebugExtensionAttribute();

    // Act
    changedCodePrinter.visitSourceDebugExtensionAttribute(clazz, sourceDebugExtensionAttribute);

    // Assert that nothing has changed
    assertEquals(0, sourceDebugExtensionAttribute.u4attributeLength);
  }

  /**
   * Test {@link ChangedCodePrinter#visitSourceDebugExtensionAttribute(Clazz, SourceDebugExtensionAttribute)}.
   * <p>
   * Method under test: {@link ChangedCodePrinter#visitSourceDebugExtensionAttribute(Clazz, SourceDebugExtensionAttribute)}
   */
  @Test
  @DisplayName("Test visitSourceDebugExtensionAttribute(Clazz, SourceDebugExtensionAttribute)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "void proguard.optimize.ChangedCodePrinter.visitSourceDebugExtensionAttribute(proguard.classfile.Clazz, proguard.classfile.attribute.SourceDebugExtensionAttribute)"})
  void testVisitSourceDebugExtensionAttribute2() throws UnsupportedEncodingException {
    // Arrange
    ChangedCodePrinter changedCodePrinter = new ChangedCodePrinter(new KotlinSourceDebugExtensionAttributeObfuscator());
    LibraryClass clazz = new LibraryClass();
    SourceDebugExtensionAttribute sourceDebugExtensionAttribute = new SourceDebugExtensionAttribute();

    // Act
    changedCodePrinter.visitSourceDebugExtensionAttribute(clazz, sourceDebugExtensionAttribute);

    // Assert
    assertEquals(45, sourceDebugExtensionAttribute.u4attributeLength);
    assertArrayEquals("SMAP\n\nKotlin\n*S Kotlin\n*F\n+ 1 \n\n*L\n1#1,1:1\n*E".getBytes("UTF-8"),
        sourceDebugExtensionAttribute.info);
  }

  /**
   * Test {@link ChangedCodePrinter#visitSourceFileAttribute(Clazz, SourceFileAttribute)}.
   * <ul>
   *   <li>Then calls {@link AttributeVisitor#visitSourceFileAttribute(Clazz, SourceFileAttribute)}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ChangedCodePrinter#visitSourceFileAttribute(Clazz, SourceFileAttribute)}
   */
  @Test
  @DisplayName("Test visitSourceFileAttribute(Clazz, SourceFileAttribute); then calls visitSourceFileAttribute(Clazz, SourceFileAttribute)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "void proguard.optimize.ChangedCodePrinter.visitSourceFileAttribute(proguard.classfile.Clazz, proguard.classfile.attribute.SourceFileAttribute)"})
  void testVisitSourceFileAttribute_thenCallsVisitSourceFileAttribute() {
    // Arrange
    AllAttributeVisitor attributeVisitor = mock(AllAttributeVisitor.class);
    doNothing().when(attributeVisitor)
        .visitSourceFileAttribute(Mockito.<Clazz>any(), Mockito.<SourceFileAttribute>any());
    ChangedCodePrinter changedCodePrinter = new ChangedCodePrinter(attributeVisitor);
    LibraryClass clazz = new LibraryClass();

    // Act
    changedCodePrinter.visitSourceFileAttribute(clazz, new SourceFileAttribute(1, 1));

    // Assert
    verify(attributeVisitor).visitSourceFileAttribute(isA(Clazz.class), isA(SourceFileAttribute.class));
  }

  /**
   * Test {@link ChangedCodePrinter#visitSourceDirAttribute(Clazz, SourceDirAttribute)}.
   * <ul>
   *   <li>Then calls {@link AttributeVisitor#visitSourceDirAttribute(Clazz, SourceDirAttribute)}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ChangedCodePrinter#visitSourceDirAttribute(Clazz, SourceDirAttribute)}
   */
  @Test
  @DisplayName("Test visitSourceDirAttribute(Clazz, SourceDirAttribute); then calls visitSourceDirAttribute(Clazz, SourceDirAttribute)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "void proguard.optimize.ChangedCodePrinter.visitSourceDirAttribute(proguard.classfile.Clazz, proguard.classfile.attribute.SourceDirAttribute)"})
  void testVisitSourceDirAttribute_thenCallsVisitSourceDirAttribute() {
    // Arrange
    AllAttributeVisitor attributeVisitor = mock(AllAttributeVisitor.class);
    doNothing().when(attributeVisitor).visitSourceDirAttribute(Mockito.<Clazz>any(), Mockito.<SourceDirAttribute>any());
    ChangedCodePrinter changedCodePrinter = new ChangedCodePrinter(attributeVisitor);
    LibraryClass clazz = new LibraryClass();

    // Act
    changedCodePrinter.visitSourceDirAttribute(clazz, new SourceDirAttribute(1, 1));

    // Assert
    verify(attributeVisitor).visitSourceDirAttribute(isA(Clazz.class), isA(SourceDirAttribute.class));
  }

  /**
   * Test {@link ChangedCodePrinter#visitInnerClassesAttribute(Clazz, InnerClassesAttribute)}.
   * <ul>
   *   <li>Then calls {@link AttributeVisitor#visitInnerClassesAttribute(Clazz, InnerClassesAttribute)}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ChangedCodePrinter#visitInnerClassesAttribute(Clazz, InnerClassesAttribute)}
   */
  @Test
  @DisplayName("Test visitInnerClassesAttribute(Clazz, InnerClassesAttribute); then calls visitInnerClassesAttribute(Clazz, InnerClassesAttribute)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "void proguard.optimize.ChangedCodePrinter.visitInnerClassesAttribute(proguard.classfile.Clazz, proguard.classfile.attribute.InnerClassesAttribute)"})
  void testVisitInnerClassesAttribute_thenCallsVisitInnerClassesAttribute() {
    // Arrange
    AllAttributeVisitor attributeVisitor = mock(AllAttributeVisitor.class);
    doNothing().when(attributeVisitor)
        .visitInnerClassesAttribute(Mockito.<Clazz>any(), Mockito.<InnerClassesAttribute>any());
    ChangedCodePrinter changedCodePrinter = new ChangedCodePrinter(attributeVisitor);
    LibraryClass clazz = new LibraryClass();

    // Act
    changedCodePrinter.visitInnerClassesAttribute(clazz, new InnerClassesAttribute());

    // Assert
    verify(attributeVisitor).visitInnerClassesAttribute(isA(Clazz.class), isA(InnerClassesAttribute.class));
  }

  /**
   * Test {@link ChangedCodePrinter#visitEnclosingMethodAttribute(Clazz, EnclosingMethodAttribute)}.
   * <ul>
   *   <li>Then calls {@link AttributeVisitor#visitEnclosingMethodAttribute(Clazz, EnclosingMethodAttribute)}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ChangedCodePrinter#visitEnclosingMethodAttribute(Clazz, EnclosingMethodAttribute)}
   */
  @Test
  @DisplayName("Test visitEnclosingMethodAttribute(Clazz, EnclosingMethodAttribute); then calls visitEnclosingMethodAttribute(Clazz, EnclosingMethodAttribute)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "void proguard.optimize.ChangedCodePrinter.visitEnclosingMethodAttribute(proguard.classfile.Clazz, proguard.classfile.attribute.EnclosingMethodAttribute)"})
  void testVisitEnclosingMethodAttribute_thenCallsVisitEnclosingMethodAttribute() {
    // Arrange
    AllAttributeVisitor attributeVisitor = mock(AllAttributeVisitor.class);
    doNothing().when(attributeVisitor)
        .visitEnclosingMethodAttribute(Mockito.<Clazz>any(), Mockito.<EnclosingMethodAttribute>any());
    ChangedCodePrinter changedCodePrinter = new ChangedCodePrinter(attributeVisitor);
    LibraryClass clazz = new LibraryClass();

    // Act
    changedCodePrinter.visitEnclosingMethodAttribute(clazz, new EnclosingMethodAttribute(1, 1, 1));

    // Assert
    verify(attributeVisitor).visitEnclosingMethodAttribute(isA(Clazz.class), isA(EnclosingMethodAttribute.class));
  }

  /**
   * Test {@link ChangedCodePrinter#visitNestHostAttribute(Clazz, NestHostAttribute)}.
   * <ul>
   *   <li>Then calls {@link AttributeVisitor#visitNestHostAttribute(Clazz, NestHostAttribute)}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ChangedCodePrinter#visitNestHostAttribute(Clazz, NestHostAttribute)}
   */
  @Test
  @DisplayName("Test visitNestHostAttribute(Clazz, NestHostAttribute); then calls visitNestHostAttribute(Clazz, NestHostAttribute)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "void proguard.optimize.ChangedCodePrinter.visitNestHostAttribute(proguard.classfile.Clazz, proguard.classfile.attribute.NestHostAttribute)"})
  void testVisitNestHostAttribute_thenCallsVisitNestHostAttribute() {
    // Arrange
    AllAttributeVisitor attributeVisitor = mock(AllAttributeVisitor.class);
    doNothing().when(attributeVisitor).visitNestHostAttribute(Mockito.<Clazz>any(), Mockito.<NestHostAttribute>any());
    ChangedCodePrinter changedCodePrinter = new ChangedCodePrinter(attributeVisitor);
    LibraryClass clazz = new LibraryClass();

    // Act
    changedCodePrinter.visitNestHostAttribute(clazz, new NestHostAttribute(1, 1));

    // Assert
    verify(attributeVisitor).visitNestHostAttribute(isA(Clazz.class), isA(NestHostAttribute.class));
  }

  /**
   * Test {@link ChangedCodePrinter#visitNestMembersAttribute(Clazz, NestMembersAttribute)}.
   * <ul>
   *   <li>Then calls {@link AttributeVisitor#visitNestMembersAttribute(Clazz, NestMembersAttribute)}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ChangedCodePrinter#visitNestMembersAttribute(Clazz, NestMembersAttribute)}
   */
  @Test
  @DisplayName("Test visitNestMembersAttribute(Clazz, NestMembersAttribute); then calls visitNestMembersAttribute(Clazz, NestMembersAttribute)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "void proguard.optimize.ChangedCodePrinter.visitNestMembersAttribute(proguard.classfile.Clazz, proguard.classfile.attribute.NestMembersAttribute)"})
  void testVisitNestMembersAttribute_thenCallsVisitNestMembersAttribute() {
    // Arrange
    AllAttributeVisitor attributeVisitor = mock(AllAttributeVisitor.class);
    doNothing().when(attributeVisitor)
        .visitNestMembersAttribute(Mockito.<Clazz>any(), Mockito.<NestMembersAttribute>any());
    ChangedCodePrinter changedCodePrinter = new ChangedCodePrinter(attributeVisitor);
    LibraryClass clazz = new LibraryClass();

    // Act
    changedCodePrinter.visitNestMembersAttribute(clazz, new NestMembersAttribute());

    // Assert
    verify(attributeVisitor).visitNestMembersAttribute(isA(Clazz.class), isA(NestMembersAttribute.class));
  }

  /**
   * Test {@link ChangedCodePrinter#visitPermittedSubclassesAttribute(Clazz, PermittedSubclassesAttribute)}.
   * <ul>
   *   <li>Then calls {@link AttributeVisitor#visitPermittedSubclassesAttribute(Clazz, PermittedSubclassesAttribute)}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ChangedCodePrinter#visitPermittedSubclassesAttribute(Clazz, PermittedSubclassesAttribute)}
   */
  @Test
  @DisplayName("Test visitPermittedSubclassesAttribute(Clazz, PermittedSubclassesAttribute); then calls visitPermittedSubclassesAttribute(Clazz, PermittedSubclassesAttribute)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "void proguard.optimize.ChangedCodePrinter.visitPermittedSubclassesAttribute(proguard.classfile.Clazz, proguard.classfile.attribute.PermittedSubclassesAttribute)"})
  void testVisitPermittedSubclassesAttribute_thenCallsVisitPermittedSubclassesAttribute() {
    // Arrange
    AllAttributeVisitor attributeVisitor = mock(AllAttributeVisitor.class);
    doNothing().when(attributeVisitor)
        .visitPermittedSubclassesAttribute(Mockito.<Clazz>any(), Mockito.<PermittedSubclassesAttribute>any());
    ChangedCodePrinter changedCodePrinter = new ChangedCodePrinter(attributeVisitor);
    LibraryClass clazz = new LibraryClass();

    // Act
    changedCodePrinter.visitPermittedSubclassesAttribute(clazz, new PermittedSubclassesAttribute());

    // Assert
    verify(attributeVisitor).visitPermittedSubclassesAttribute(isA(Clazz.class),
        isA(PermittedSubclassesAttribute.class));
  }

  /**
   * Test {@link ChangedCodePrinter#visitModuleAttribute(Clazz, ModuleAttribute)}.
   * <ul>
   *   <li>Then calls {@link AttributeVisitor#visitModuleAttribute(Clazz, ModuleAttribute)}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ChangedCodePrinter#visitModuleAttribute(Clazz, ModuleAttribute)}
   */
  @Test
  @DisplayName("Test visitModuleAttribute(Clazz, ModuleAttribute); then calls visitModuleAttribute(Clazz, ModuleAttribute)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "void proguard.optimize.ChangedCodePrinter.visitModuleAttribute(proguard.classfile.Clazz, proguard.classfile.attribute.module.ModuleAttribute)"})
  void testVisitModuleAttribute_thenCallsVisitModuleAttribute() {
    // Arrange
    AllAttributeVisitor attributeVisitor = mock(AllAttributeVisitor.class);
    doNothing().when(attributeVisitor).visitModuleAttribute(Mockito.<Clazz>any(), Mockito.<ModuleAttribute>any());
    ChangedCodePrinter changedCodePrinter = new ChangedCodePrinter(attributeVisitor);
    LibraryClass clazz = new LibraryClass();

    // Act
    changedCodePrinter.visitModuleAttribute(clazz, new ModuleAttribute());

    // Assert
    verify(attributeVisitor).visitModuleAttribute(isA(Clazz.class), isA(ModuleAttribute.class));
  }

  /**
   * Test {@link ChangedCodePrinter#visitModuleMainClassAttribute(Clazz, ModuleMainClassAttribute)}.
   * <ul>
   *   <li>Then calls {@link AttributeVisitor#visitModuleMainClassAttribute(Clazz, ModuleMainClassAttribute)}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ChangedCodePrinter#visitModuleMainClassAttribute(Clazz, ModuleMainClassAttribute)}
   */
  @Test
  @DisplayName("Test visitModuleMainClassAttribute(Clazz, ModuleMainClassAttribute); then calls visitModuleMainClassAttribute(Clazz, ModuleMainClassAttribute)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "void proguard.optimize.ChangedCodePrinter.visitModuleMainClassAttribute(proguard.classfile.Clazz, proguard.classfile.attribute.module.ModuleMainClassAttribute)"})
  void testVisitModuleMainClassAttribute_thenCallsVisitModuleMainClassAttribute() {
    // Arrange
    AllAttributeVisitor attributeVisitor = mock(AllAttributeVisitor.class);
    doNothing().when(attributeVisitor)
        .visitModuleMainClassAttribute(Mockito.<Clazz>any(), Mockito.<ModuleMainClassAttribute>any());
    ChangedCodePrinter changedCodePrinter = new ChangedCodePrinter(attributeVisitor);
    LibraryClass clazz = new LibraryClass();

    // Act
    changedCodePrinter.visitModuleMainClassAttribute(clazz, new ModuleMainClassAttribute(1, 1));

    // Assert
    verify(attributeVisitor).visitModuleMainClassAttribute(isA(Clazz.class), isA(ModuleMainClassAttribute.class));
  }

  /**
   * Test {@link ChangedCodePrinter#visitModulePackagesAttribute(Clazz, ModulePackagesAttribute)}.
   * <ul>
   *   <li>Then calls {@link AttributeVisitor#visitModulePackagesAttribute(Clazz, ModulePackagesAttribute)}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ChangedCodePrinter#visitModulePackagesAttribute(Clazz, ModulePackagesAttribute)}
   */
  @Test
  @DisplayName("Test visitModulePackagesAttribute(Clazz, ModulePackagesAttribute); then calls visitModulePackagesAttribute(Clazz, ModulePackagesAttribute)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "void proguard.optimize.ChangedCodePrinter.visitModulePackagesAttribute(proguard.classfile.Clazz, proguard.classfile.attribute.module.ModulePackagesAttribute)"})
  void testVisitModulePackagesAttribute_thenCallsVisitModulePackagesAttribute() {
    // Arrange
    AllAttributeVisitor attributeVisitor = mock(AllAttributeVisitor.class);
    doNothing().when(attributeVisitor)
        .visitModulePackagesAttribute(Mockito.<Clazz>any(), Mockito.<ModulePackagesAttribute>any());
    ChangedCodePrinter changedCodePrinter = new ChangedCodePrinter(attributeVisitor);
    LibraryClass clazz = new LibraryClass();

    // Act
    changedCodePrinter.visitModulePackagesAttribute(clazz, new ModulePackagesAttribute());

    // Assert
    verify(attributeVisitor).visitModulePackagesAttribute(isA(Clazz.class), isA(ModulePackagesAttribute.class));
  }

  /**
   * Test {@link ChangedCodePrinter#visitDeprecatedAttribute(Clazz, DeprecatedAttribute)} with {@code clazz}, {@code deprecatedAttribute}.
   * <p>
   * Method under test: {@link ChangedCodePrinter#visitDeprecatedAttribute(Clazz, DeprecatedAttribute)}
   */
  @Test
  @DisplayName("Test visitDeprecatedAttribute(Clazz, DeprecatedAttribute) with 'clazz', 'deprecatedAttribute'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "void proguard.optimize.ChangedCodePrinter.visitDeprecatedAttribute(proguard.classfile.Clazz, proguard.classfile.attribute.DeprecatedAttribute)"})
  void testVisitDeprecatedAttributeWithClazzDeprecatedAttribute() {
    // Arrange
    AllAttributeVisitor attributeVisitor = mock(AllAttributeVisitor.class);
    doNothing().when(attributeVisitor)
        .visitDeprecatedAttribute(Mockito.<Clazz>any(), Mockito.<DeprecatedAttribute>any());
    ChangedCodePrinter changedCodePrinter = new ChangedCodePrinter(attributeVisitor);
    LibraryClass clazz = new LibraryClass();

    // Act
    changedCodePrinter.visitDeprecatedAttribute(clazz, new DeprecatedAttribute(1));

    // Assert
    verify(attributeVisitor).visitDeprecatedAttribute(isA(Clazz.class), isA(DeprecatedAttribute.class));
  }

  /**
   * Test {@link ChangedCodePrinter#visitDeprecatedAttribute(Clazz, Field, DeprecatedAttribute)} with {@code clazz}, {@code field}, {@code deprecatedAttribute}.
   * <p>
   * Method under test: {@link ChangedCodePrinter#visitDeprecatedAttribute(Clazz, Field, DeprecatedAttribute)}
   */
  @Test
  @DisplayName("Test visitDeprecatedAttribute(Clazz, Field, DeprecatedAttribute) with 'clazz', 'field', 'deprecatedAttribute'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "void proguard.optimize.ChangedCodePrinter.visitDeprecatedAttribute(proguard.classfile.Clazz, proguard.classfile.Field, proguard.classfile.attribute.DeprecatedAttribute)"})
  void testVisitDeprecatedAttributeWithClazzFieldDeprecatedAttribute() {
    // Arrange
    AllAttributeVisitor attributeVisitor = mock(AllAttributeVisitor.class);
    doNothing().when(attributeVisitor)
        .visitDeprecatedAttribute(Mockito.<Clazz>any(), Mockito.<Field>any(), Mockito.<DeprecatedAttribute>any());
    ChangedCodePrinter changedCodePrinter = new ChangedCodePrinter(attributeVisitor);
    LibraryClass clazz = new LibraryClass();
    LibraryField field = new LibraryField(1, "Name", "Descriptor");

    // Act
    changedCodePrinter.visitDeprecatedAttribute(clazz, (Field) field, new DeprecatedAttribute(1));

    // Assert
    verify(attributeVisitor).visitDeprecatedAttribute(isA(Clazz.class), isA(Field.class),
        isA(DeprecatedAttribute.class));
  }

  /**
   * Test {@link ChangedCodePrinter#visitDeprecatedAttribute(Clazz, Method, DeprecatedAttribute)} with {@code clazz}, {@code method}, {@code deprecatedAttribute}.
   * <p>
   * Method under test: {@link ChangedCodePrinter#visitDeprecatedAttribute(Clazz, Method, DeprecatedAttribute)}
   */
  @Test
  @DisplayName("Test visitDeprecatedAttribute(Clazz, Method, DeprecatedAttribute) with 'clazz', 'method', 'deprecatedAttribute'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "void proguard.optimize.ChangedCodePrinter.visitDeprecatedAttribute(proguard.classfile.Clazz, proguard.classfile.Method, proguard.classfile.attribute.DeprecatedAttribute)"})
  void testVisitDeprecatedAttributeWithClazzMethodDeprecatedAttribute() {
    // Arrange
    AllAttributeVisitor attributeVisitor = mock(AllAttributeVisitor.class);
    doNothing().when(attributeVisitor)
        .visitDeprecatedAttribute(Mockito.<Clazz>any(), Mockito.<Method>any(), Mockito.<DeprecatedAttribute>any());
    ChangedCodePrinter changedCodePrinter = new ChangedCodePrinter(attributeVisitor);
    LibraryClass clazz = new LibraryClass();
    LibraryMethod method = new LibraryMethod(1, "Name", "Descriptor");

    // Act
    changedCodePrinter.visitDeprecatedAttribute(clazz, (Method) method, new DeprecatedAttribute(1));

    // Assert
    verify(attributeVisitor).visitDeprecatedAttribute(isA(Clazz.class), isA(Method.class),
        isA(DeprecatedAttribute.class));
  }

  /**
   * Test {@link ChangedCodePrinter#visitSyntheticAttribute(Clazz, Field, SyntheticAttribute)} with {@code clazz}, {@code field}, {@code syntheticAttribute}.
   * <p>
   * Method under test: {@link ChangedCodePrinter#visitSyntheticAttribute(Clazz, Field, SyntheticAttribute)}
   */
  @Test
  @DisplayName("Test visitSyntheticAttribute(Clazz, Field, SyntheticAttribute) with 'clazz', 'field', 'syntheticAttribute'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "void proguard.optimize.ChangedCodePrinter.visitSyntheticAttribute(proguard.classfile.Clazz, proguard.classfile.Field, proguard.classfile.attribute.SyntheticAttribute)"})
  void testVisitSyntheticAttributeWithClazzFieldSyntheticAttribute() {
    // Arrange
    AllAttributeVisitor attributeVisitor = mock(AllAttributeVisitor.class);
    doNothing().when(attributeVisitor)
        .visitSyntheticAttribute(Mockito.<Clazz>any(), Mockito.<Field>any(), Mockito.<SyntheticAttribute>any());
    ChangedCodePrinter changedCodePrinter = new ChangedCodePrinter(attributeVisitor);
    LibraryClass clazz = new LibraryClass();
    LibraryField field = new LibraryField(1, "Name", "Descriptor");

    // Act
    changedCodePrinter.visitSyntheticAttribute(clazz, (Field) field, new SyntheticAttribute(1));

    // Assert
    verify(attributeVisitor).visitSyntheticAttribute(isA(Clazz.class), isA(Field.class), isA(SyntheticAttribute.class));
  }

  /**
   * Test {@link ChangedCodePrinter#visitSyntheticAttribute(Clazz, Method, SyntheticAttribute)} with {@code clazz}, {@code method}, {@code syntheticAttribute}.
   * <p>
   * Method under test: {@link ChangedCodePrinter#visitSyntheticAttribute(Clazz, Method, SyntheticAttribute)}
   */
  @Test
  @DisplayName("Test visitSyntheticAttribute(Clazz, Method, SyntheticAttribute) with 'clazz', 'method', 'syntheticAttribute'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "void proguard.optimize.ChangedCodePrinter.visitSyntheticAttribute(proguard.classfile.Clazz, proguard.classfile.Method, proguard.classfile.attribute.SyntheticAttribute)"})
  void testVisitSyntheticAttributeWithClazzMethodSyntheticAttribute() {
    // Arrange
    AllAttributeVisitor attributeVisitor = mock(AllAttributeVisitor.class);
    doNothing().when(attributeVisitor)
        .visitSyntheticAttribute(Mockito.<Clazz>any(), Mockito.<Method>any(), Mockito.<SyntheticAttribute>any());
    ChangedCodePrinter changedCodePrinter = new ChangedCodePrinter(attributeVisitor);
    LibraryClass clazz = new LibraryClass();
    LibraryMethod method = new LibraryMethod(1, "Name", "Descriptor");

    // Act
    changedCodePrinter.visitSyntheticAttribute(clazz, (Method) method, new SyntheticAttribute(1));

    // Assert
    verify(attributeVisitor).visitSyntheticAttribute(isA(Clazz.class), isA(Method.class),
        isA(SyntheticAttribute.class));
  }

  /**
   * Test {@link ChangedCodePrinter#visitSyntheticAttribute(Clazz, SyntheticAttribute)} with {@code clazz}, {@code syntheticAttribute}.
   * <p>
   * Method under test: {@link ChangedCodePrinter#visitSyntheticAttribute(Clazz, SyntheticAttribute)}
   */
  @Test
  @DisplayName("Test visitSyntheticAttribute(Clazz, SyntheticAttribute) with 'clazz', 'syntheticAttribute'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "void proguard.optimize.ChangedCodePrinter.visitSyntheticAttribute(proguard.classfile.Clazz, proguard.classfile.attribute.SyntheticAttribute)"})
  void testVisitSyntheticAttributeWithClazzSyntheticAttribute() {
    // Arrange
    AllAttributeVisitor attributeVisitor = mock(AllAttributeVisitor.class);
    doNothing().when(attributeVisitor).visitSyntheticAttribute(Mockito.<Clazz>any(), Mockito.<SyntheticAttribute>any());
    ChangedCodePrinter changedCodePrinter = new ChangedCodePrinter(attributeVisitor);
    LibraryClass clazz = new LibraryClass();

    // Act
    changedCodePrinter.visitSyntheticAttribute(clazz, new SyntheticAttribute(1));

    // Assert
    verify(attributeVisitor).visitSyntheticAttribute(isA(Clazz.class), isA(SyntheticAttribute.class));
  }

  /**
   * Test {@link ChangedCodePrinter#visitSignatureAttribute(Clazz, Field, SignatureAttribute)} with {@code clazz}, {@code field}, {@code syntheticAttribute}.
   * <p>
   * Method under test: {@link ChangedCodePrinter#visitSignatureAttribute(Clazz, Field, SignatureAttribute)}
   */
  @Test
  @DisplayName("Test visitSignatureAttribute(Clazz, Field, SignatureAttribute) with 'clazz', 'field', 'syntheticAttribute'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "void proguard.optimize.ChangedCodePrinter.visitSignatureAttribute(proguard.classfile.Clazz, proguard.classfile.Field, proguard.classfile.attribute.SignatureAttribute)"})
  void testVisitSignatureAttributeWithClazzFieldSyntheticAttribute() {
    // Arrange
    AllAttributeVisitor attributeVisitor = mock(AllAttributeVisitor.class);
    doNothing().when(attributeVisitor)
        .visitSignatureAttribute(Mockito.<Clazz>any(), Mockito.<Field>any(), Mockito.<SignatureAttribute>any());
    ChangedCodePrinter changedCodePrinter = new ChangedCodePrinter(attributeVisitor);
    LibraryClass clazz = new LibraryClass();
    LibraryField field = new LibraryField(1, "Name", "Descriptor");

    // Act
    changedCodePrinter.visitSignatureAttribute(clazz, (Field) field, new SignatureAttribute(1, 1));

    // Assert
    verify(attributeVisitor).visitSignatureAttribute(isA(Clazz.class), isA(Field.class), isA(SignatureAttribute.class));
  }

  /**
   * Test {@link ChangedCodePrinter#visitSignatureAttribute(Clazz, Method, SignatureAttribute)} with {@code clazz}, {@code method}, {@code syntheticAttribute}.
   * <p>
   * Method under test: {@link ChangedCodePrinter#visitSignatureAttribute(Clazz, Method, SignatureAttribute)}
   */
  @Test
  @DisplayName("Test visitSignatureAttribute(Clazz, Method, SignatureAttribute) with 'clazz', 'method', 'syntheticAttribute'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "void proguard.optimize.ChangedCodePrinter.visitSignatureAttribute(proguard.classfile.Clazz, proguard.classfile.Method, proguard.classfile.attribute.SignatureAttribute)"})
  void testVisitSignatureAttributeWithClazzMethodSyntheticAttribute() {
    // Arrange
    AllAttributeVisitor attributeVisitor = mock(AllAttributeVisitor.class);
    doNothing().when(attributeVisitor)
        .visitSignatureAttribute(Mockito.<Clazz>any(), Mockito.<Method>any(), Mockito.<SignatureAttribute>any());
    ChangedCodePrinter changedCodePrinter = new ChangedCodePrinter(attributeVisitor);
    LibraryClass clazz = new LibraryClass();
    LibraryMethod method = new LibraryMethod(1, "Name", "Descriptor");

    // Act
    changedCodePrinter.visitSignatureAttribute(clazz, (Method) method, new SignatureAttribute(1, 1));

    // Assert
    verify(attributeVisitor).visitSignatureAttribute(isA(Clazz.class), isA(Method.class),
        isA(SignatureAttribute.class));
  }

  /**
   * Test {@link ChangedCodePrinter#visitSignatureAttribute(Clazz, SignatureAttribute)} with {@code clazz}, {@code syntheticAttribute}.
   * <p>
   * Method under test: {@link ChangedCodePrinter#visitSignatureAttribute(Clazz, SignatureAttribute)}
   */
  @Test
  @DisplayName("Test visitSignatureAttribute(Clazz, SignatureAttribute) with 'clazz', 'syntheticAttribute'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "void proguard.optimize.ChangedCodePrinter.visitSignatureAttribute(proguard.classfile.Clazz, proguard.classfile.attribute.SignatureAttribute)"})
  void testVisitSignatureAttributeWithClazzSyntheticAttribute() {
    // Arrange
    AllAttributeVisitor attributeVisitor = mock(AllAttributeVisitor.class);
    doNothing().when(attributeVisitor).visitSignatureAttribute(Mockito.<Clazz>any(), Mockito.<SignatureAttribute>any());
    ChangedCodePrinter changedCodePrinter = new ChangedCodePrinter(attributeVisitor);
    LibraryClass clazz = new LibraryClass();

    // Act
    changedCodePrinter.visitSignatureAttribute(clazz, new SignatureAttribute(1, 1));

    // Assert
    verify(attributeVisitor).visitSignatureAttribute(isA(Clazz.class), isA(SignatureAttribute.class));
  }

  /**
   * Test {@link ChangedCodePrinter#visitConstantValueAttribute(Clazz, Field, ConstantValueAttribute)}.
   * <ul>
   *   <li>Then calls {@link AttributeVisitor#visitConstantValueAttribute(Clazz, Field, ConstantValueAttribute)}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ChangedCodePrinter#visitConstantValueAttribute(Clazz, Field, ConstantValueAttribute)}
   */
  @Test
  @DisplayName("Test visitConstantValueAttribute(Clazz, Field, ConstantValueAttribute); then calls visitConstantValueAttribute(Clazz, Field, ConstantValueAttribute)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "void proguard.optimize.ChangedCodePrinter.visitConstantValueAttribute(proguard.classfile.Clazz, proguard.classfile.Field, proguard.classfile.attribute.ConstantValueAttribute)"})
  void testVisitConstantValueAttribute_thenCallsVisitConstantValueAttribute() {
    // Arrange
    AllAttributeVisitor attributeVisitor = mock(AllAttributeVisitor.class);
    doNothing().when(attributeVisitor)
        .visitConstantValueAttribute(Mockito.<Clazz>any(), Mockito.<Field>any(), Mockito.<ConstantValueAttribute>any());
    ChangedCodePrinter changedCodePrinter = new ChangedCodePrinter(attributeVisitor);
    LibraryClass clazz = new LibraryClass();
    LibraryField field = new LibraryField(1, "Name", "Descriptor");

    // Act
    changedCodePrinter.visitConstantValueAttribute(clazz, field, new ConstantValueAttribute(1, 1));

    // Assert
    verify(attributeVisitor).visitConstantValueAttribute(isA(Clazz.class), isA(Field.class),
        isA(ConstantValueAttribute.class));
  }

  /**
   * Test {@link ChangedCodePrinter#visitMethodParametersAttribute(Clazz, Method, MethodParametersAttribute)}.
   * <ul>
   *   <li>Then calls {@link AttributeVisitor#visitMethodParametersAttribute(Clazz, Method, MethodParametersAttribute)}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ChangedCodePrinter#visitMethodParametersAttribute(Clazz, Method, MethodParametersAttribute)}
   */
  @Test
  @DisplayName("Test visitMethodParametersAttribute(Clazz, Method, MethodParametersAttribute); then calls visitMethodParametersAttribute(Clazz, Method, MethodParametersAttribute)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "void proguard.optimize.ChangedCodePrinter.visitMethodParametersAttribute(proguard.classfile.Clazz, proguard.classfile.Method, proguard.classfile.attribute.MethodParametersAttribute)"})
  void testVisitMethodParametersAttribute_thenCallsVisitMethodParametersAttribute() {
    // Arrange
    AllAttributeVisitor attributeVisitor = mock(AllAttributeVisitor.class);
    doNothing().when(attributeVisitor)
        .visitMethodParametersAttribute(Mockito.<Clazz>any(), Mockito.<Method>any(),
            Mockito.<MethodParametersAttribute>any());
    ChangedCodePrinter changedCodePrinter = new ChangedCodePrinter(attributeVisitor);
    LibraryClass clazz = new LibraryClass();
    LibraryMethod method = new LibraryMethod(1, "Name", "Descriptor");

    // Act
    changedCodePrinter.visitMethodParametersAttribute(clazz, method, new MethodParametersAttribute());

    // Assert
    verify(attributeVisitor).visitMethodParametersAttribute(isA(Clazz.class), isA(Method.class),
        isA(MethodParametersAttribute.class));
  }

  /**
   * Test {@link ChangedCodePrinter#visitExceptionsAttribute(Clazz, Method, ExceptionsAttribute)}.
   * <ul>
   *   <li>Then calls {@link AttributeVisitor#visitExceptionsAttribute(Clazz, Method, ExceptionsAttribute)}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ChangedCodePrinter#visitExceptionsAttribute(Clazz, Method, ExceptionsAttribute)}
   */
  @Test
  @DisplayName("Test visitExceptionsAttribute(Clazz, Method, ExceptionsAttribute); then calls visitExceptionsAttribute(Clazz, Method, ExceptionsAttribute)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "void proguard.optimize.ChangedCodePrinter.visitExceptionsAttribute(proguard.classfile.Clazz, proguard.classfile.Method, proguard.classfile.attribute.ExceptionsAttribute)"})
  void testVisitExceptionsAttribute_thenCallsVisitExceptionsAttribute() {
    // Arrange
    AllAttributeVisitor attributeVisitor = mock(AllAttributeVisitor.class);
    doNothing().when(attributeVisitor)
        .visitExceptionsAttribute(Mockito.<Clazz>any(), Mockito.<Method>any(), Mockito.<ExceptionsAttribute>any());
    ChangedCodePrinter changedCodePrinter = new ChangedCodePrinter(attributeVisitor);
    LibraryClass clazz = new LibraryClass();
    LibraryMethod method = new LibraryMethod(1, "Name", "Descriptor");

    // Act
    changedCodePrinter.visitExceptionsAttribute(clazz, method, new ExceptionsAttribute());

    // Assert
    verify(attributeVisitor).visitExceptionsAttribute(isA(Clazz.class), isA(Method.class),
        isA(ExceptionsAttribute.class));
  }

  /**
   * Test {@link ChangedCodePrinter#visitStackMapAttribute(Clazz, Method, CodeAttribute, StackMapAttribute)}.
   * <ul>
   *   <li>Then calls {@link AttributeVisitor#visitStackMapAttribute(Clazz, Method, CodeAttribute, StackMapAttribute)}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ChangedCodePrinter#visitStackMapAttribute(Clazz, Method, CodeAttribute, StackMapAttribute)}
   */
  @Test
  @DisplayName("Test visitStackMapAttribute(Clazz, Method, CodeAttribute, StackMapAttribute); then calls visitStackMapAttribute(Clazz, Method, CodeAttribute, StackMapAttribute)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "void proguard.optimize.ChangedCodePrinter.visitStackMapAttribute(proguard.classfile.Clazz, proguard.classfile.Method, proguard.classfile.attribute.CodeAttribute, proguard.classfile.attribute.preverification.StackMapAttribute)"})
  void testVisitStackMapAttribute_thenCallsVisitStackMapAttribute() {
    // Arrange
    AllAttributeVisitor attributeVisitor = mock(AllAttributeVisitor.class);
    doNothing().when(attributeVisitor)
        .visitStackMapAttribute(Mockito.<Clazz>any(), Mockito.<Method>any(), Mockito.<CodeAttribute>any(),
            Mockito.<StackMapAttribute>any());
    ChangedCodePrinter changedCodePrinter = new ChangedCodePrinter(attributeVisitor);
    LibraryClass clazz = new LibraryClass();
    LibraryMethod method = new LibraryMethod(1, "Name", "Descriptor");

    CodeAttribute codeAttribute = new CodeAttribute(1);

    // Act
    changedCodePrinter.visitStackMapAttribute(clazz, method, codeAttribute, new StackMapAttribute());

    // Assert
    verify(attributeVisitor).visitStackMapAttribute(isA(Clazz.class), isA(Method.class), isA(CodeAttribute.class),
        isA(StackMapAttribute.class));
  }

  /**
   * Test {@link ChangedCodePrinter#visitStackMapTableAttribute(Clazz, Method, CodeAttribute, StackMapTableAttribute)}.
   * <ul>
   *   <li>Then calls {@link AttributeVisitor#visitStackMapTableAttribute(Clazz, Method, CodeAttribute, StackMapTableAttribute)}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ChangedCodePrinter#visitStackMapTableAttribute(Clazz, Method, CodeAttribute, StackMapTableAttribute)}
   */
  @Test
  @DisplayName("Test visitStackMapTableAttribute(Clazz, Method, CodeAttribute, StackMapTableAttribute); then calls visitStackMapTableAttribute(Clazz, Method, CodeAttribute, StackMapTableAttribute)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "void proguard.optimize.ChangedCodePrinter.visitStackMapTableAttribute(proguard.classfile.Clazz, proguard.classfile.Method, proguard.classfile.attribute.CodeAttribute, proguard.classfile.attribute.preverification.StackMapTableAttribute)"})
  void testVisitStackMapTableAttribute_thenCallsVisitStackMapTableAttribute() {
    // Arrange
    AllAttributeVisitor attributeVisitor = mock(AllAttributeVisitor.class);
    doNothing().when(attributeVisitor)
        .visitStackMapTableAttribute(Mockito.<Clazz>any(), Mockito.<Method>any(), Mockito.<CodeAttribute>any(),
            Mockito.<StackMapTableAttribute>any());
    ChangedCodePrinter changedCodePrinter = new ChangedCodePrinter(attributeVisitor);
    LibraryClass clazz = new LibraryClass();
    LibraryMethod method = new LibraryMethod(1, "Name", "Descriptor");

    CodeAttribute codeAttribute = new CodeAttribute(1);

    // Act
    changedCodePrinter.visitStackMapTableAttribute(clazz, method, codeAttribute, new StackMapTableAttribute());

    // Assert
    verify(attributeVisitor).visitStackMapTableAttribute(isA(Clazz.class), isA(Method.class), isA(CodeAttribute.class),
        isA(StackMapTableAttribute.class));
  }

  /**
   * Test {@link ChangedCodePrinter#visitLineNumberTableAttribute(Clazz, Method, CodeAttribute, LineNumberTableAttribute)}.
   * <ul>
   *   <li>Then calls {@link AttributeVisitor#visitLineNumberTableAttribute(Clazz, Method, CodeAttribute, LineNumberTableAttribute)}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ChangedCodePrinter#visitLineNumberTableAttribute(Clazz, Method, CodeAttribute, LineNumberTableAttribute)}
   */
  @Test
  @DisplayName("Test visitLineNumberTableAttribute(Clazz, Method, CodeAttribute, LineNumberTableAttribute); then calls visitLineNumberTableAttribute(Clazz, Method, CodeAttribute, LineNumberTableAttribute)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "void proguard.optimize.ChangedCodePrinter.visitLineNumberTableAttribute(proguard.classfile.Clazz, proguard.classfile.Method, proguard.classfile.attribute.CodeAttribute, proguard.classfile.attribute.LineNumberTableAttribute)"})
  void testVisitLineNumberTableAttribute_thenCallsVisitLineNumberTableAttribute() {
    // Arrange
    AllAttributeVisitor attributeVisitor = mock(AllAttributeVisitor.class);
    doNothing().when(attributeVisitor)
        .visitLineNumberTableAttribute(Mockito.<Clazz>any(), Mockito.<Method>any(), Mockito.<CodeAttribute>any(),
            Mockito.<LineNumberTableAttribute>any());
    ChangedCodePrinter changedCodePrinter = new ChangedCodePrinter(attributeVisitor);
    LibraryClass clazz = new LibraryClass();
    LibraryMethod method = new LibraryMethod(1, "Name", "Descriptor");

    CodeAttribute codeAttribute = new CodeAttribute(1);

    // Act
    changedCodePrinter.visitLineNumberTableAttribute(clazz, method, codeAttribute, new LineNumberTableAttribute());

    // Assert
    verify(attributeVisitor).visitLineNumberTableAttribute(isA(Clazz.class), isA(Method.class),
        isA(CodeAttribute.class), isA(LineNumberTableAttribute.class));
  }

  /**
   * Test {@link ChangedCodePrinter#visitLocalVariableTableAttribute(Clazz, Method, CodeAttribute, LocalVariableTableAttribute)}.
   * <ul>
   *   <li>Then calls {@link AttributeVisitor#visitLocalVariableTableAttribute(Clazz, Method, CodeAttribute, LocalVariableTableAttribute)}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ChangedCodePrinter#visitLocalVariableTableAttribute(Clazz, Method, CodeAttribute, LocalVariableTableAttribute)}
   */
  @Test
  @DisplayName("Test visitLocalVariableTableAttribute(Clazz, Method, CodeAttribute, LocalVariableTableAttribute); then calls visitLocalVariableTableAttribute(Clazz, Method, CodeAttribute, LocalVariableTableAttribute)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "void proguard.optimize.ChangedCodePrinter.visitLocalVariableTableAttribute(proguard.classfile.Clazz, proguard.classfile.Method, proguard.classfile.attribute.CodeAttribute, proguard.classfile.attribute.LocalVariableTableAttribute)"})
  void testVisitLocalVariableTableAttribute_thenCallsVisitLocalVariableTableAttribute() {
    // Arrange
    AllAttributeVisitor attributeVisitor = mock(AllAttributeVisitor.class);
    doNothing().when(attributeVisitor)
        .visitLocalVariableTableAttribute(Mockito.<Clazz>any(), Mockito.<Method>any(), Mockito.<CodeAttribute>any(),
            Mockito.<LocalVariableTableAttribute>any());
    ChangedCodePrinter changedCodePrinter = new ChangedCodePrinter(attributeVisitor);
    LibraryClass clazz = new LibraryClass();
    LibraryMethod method = new LibraryMethod(1, "Name", "Descriptor");

    CodeAttribute codeAttribute = new CodeAttribute(1);

    // Act
    changedCodePrinter.visitLocalVariableTableAttribute(clazz, method, codeAttribute,
        new LocalVariableTableAttribute());

    // Assert
    verify(attributeVisitor).visitLocalVariableTableAttribute(isA(Clazz.class), isA(Method.class),
        isA(CodeAttribute.class), isA(LocalVariableTableAttribute.class));
  }

  /**
   * Test {@link ChangedCodePrinter#visitLocalVariableTypeTableAttribute(Clazz, Method, CodeAttribute, LocalVariableTypeTableAttribute)}.
   * <p>
   * Method under test: {@link ChangedCodePrinter#visitLocalVariableTypeTableAttribute(Clazz, Method, CodeAttribute, LocalVariableTypeTableAttribute)}
   */
  @Test
  @DisplayName("Test visitLocalVariableTypeTableAttribute(Clazz, Method, CodeAttribute, LocalVariableTypeTableAttribute)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "void proguard.optimize.ChangedCodePrinter.visitLocalVariableTypeTableAttribute(proguard.classfile.Clazz, proguard.classfile.Method, proguard.classfile.attribute.CodeAttribute, proguard.classfile.attribute.LocalVariableTypeTableAttribute)"})
  void testVisitLocalVariableTypeTableAttribute() {
    // Arrange
    AllAttributeVisitor attributeVisitor = mock(AllAttributeVisitor.class);
    doNothing().when(attributeVisitor)
        .visitLocalVariableTypeTableAttribute(Mockito.<Clazz>any(), Mockito.<Method>any(), Mockito.<CodeAttribute>any(),
            Mockito.<LocalVariableTypeTableAttribute>any());
    ChangedCodePrinter changedCodePrinter = new ChangedCodePrinter(attributeVisitor);
    LibraryClass clazz = new LibraryClass();
    LibraryMethod method = new LibraryMethod(1, "Name", "Descriptor");

    CodeAttribute codeAttribute = new CodeAttribute(1);

    // Act
    changedCodePrinter.visitLocalVariableTypeTableAttribute(clazz, method, codeAttribute,
        new LocalVariableTypeTableAttribute());

    // Assert
    verify(attributeVisitor).visitLocalVariableTypeTableAttribute(isA(Clazz.class), isA(Method.class),
        isA(CodeAttribute.class), isA(LocalVariableTypeTableAttribute.class));
  }

  /**
   * Test {@link ChangedCodePrinter#visitRuntimeVisibleAnnotationsAttribute(Clazz, Field, RuntimeVisibleAnnotationsAttribute)} with {@code clazz}, {@code field}, {@code runtimeVisibleAnnotationsAttribute}.
   * <p>
   * Method under test: {@link ChangedCodePrinter#visitRuntimeVisibleAnnotationsAttribute(Clazz, Field, RuntimeVisibleAnnotationsAttribute)}
   */
  @Test
  @DisplayName("Test visitRuntimeVisibleAnnotationsAttribute(Clazz, Field, RuntimeVisibleAnnotationsAttribute) with 'clazz', 'field', 'runtimeVisibleAnnotationsAttribute'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "void proguard.optimize.ChangedCodePrinter.visitRuntimeVisibleAnnotationsAttribute(proguard.classfile.Clazz, proguard.classfile.Field, proguard.classfile.attribute.annotation.RuntimeVisibleAnnotationsAttribute)"})
  void testVisitRuntimeVisibleAnnotationsAttributeWithClazzFieldRuntimeVisibleAnnotationsAttribute() {
    // Arrange
    AllAttributeVisitor attributeVisitor = mock(AllAttributeVisitor.class);
    doNothing().when(attributeVisitor)
        .visitRuntimeVisibleAnnotationsAttribute(Mockito.<Clazz>any(), Mockito.<Field>any(),
            Mockito.<RuntimeVisibleAnnotationsAttribute>any());
    ChangedCodePrinter changedCodePrinter = new ChangedCodePrinter(attributeVisitor);
    LibraryClass clazz = new LibraryClass();
    LibraryField field = new LibraryField(1, "Name", "Descriptor");

    // Act
    changedCodePrinter.visitRuntimeVisibleAnnotationsAttribute(clazz, (Field) field,
        new RuntimeVisibleAnnotationsAttribute());

    // Assert
    verify(attributeVisitor).visitRuntimeVisibleAnnotationsAttribute(isA(Clazz.class), isA(Field.class),
        isA(RuntimeVisibleAnnotationsAttribute.class));
  }

  /**
   * Test {@link ChangedCodePrinter#visitRuntimeVisibleAnnotationsAttribute(Clazz, Method, RuntimeVisibleAnnotationsAttribute)} with {@code clazz}, {@code method}, {@code runtimeVisibleAnnotationsAttribute}.
   * <p>
   * Method under test: {@link ChangedCodePrinter#visitRuntimeVisibleAnnotationsAttribute(Clazz, Method, RuntimeVisibleAnnotationsAttribute)}
   */
  @Test
  @DisplayName("Test visitRuntimeVisibleAnnotationsAttribute(Clazz, Method, RuntimeVisibleAnnotationsAttribute) with 'clazz', 'method', 'runtimeVisibleAnnotationsAttribute'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "void proguard.optimize.ChangedCodePrinter.visitRuntimeVisibleAnnotationsAttribute(proguard.classfile.Clazz, proguard.classfile.Method, proguard.classfile.attribute.annotation.RuntimeVisibleAnnotationsAttribute)"})
  void testVisitRuntimeVisibleAnnotationsAttributeWithClazzMethodRuntimeVisibleAnnotationsAttribute() {
    // Arrange
    AllAttributeVisitor attributeVisitor = mock(AllAttributeVisitor.class);
    doNothing().when(attributeVisitor)
        .visitRuntimeVisibleAnnotationsAttribute(Mockito.<Clazz>any(), Mockito.<Method>any(),
            Mockito.<RuntimeVisibleAnnotationsAttribute>any());
    ChangedCodePrinter changedCodePrinter = new ChangedCodePrinter(attributeVisitor);
    LibraryClass clazz = new LibraryClass();
    LibraryMethod method = new LibraryMethod(1, "Name", "Descriptor");

    // Act
    changedCodePrinter.visitRuntimeVisibleAnnotationsAttribute(clazz, (Method) method,
        new RuntimeVisibleAnnotationsAttribute());

    // Assert
    verify(attributeVisitor).visitRuntimeVisibleAnnotationsAttribute(isA(Clazz.class), isA(Method.class),
        isA(RuntimeVisibleAnnotationsAttribute.class));
  }

  /**
   * Test {@link ChangedCodePrinter#visitRuntimeVisibleAnnotationsAttribute(Clazz, RuntimeVisibleAnnotationsAttribute)} with {@code clazz}, {@code runtimeVisibleAnnotationsAttribute}.
   * <p>
   * Method under test: {@link ChangedCodePrinter#visitRuntimeVisibleAnnotationsAttribute(Clazz, RuntimeVisibleAnnotationsAttribute)}
   */
  @Test
  @DisplayName("Test visitRuntimeVisibleAnnotationsAttribute(Clazz, RuntimeVisibleAnnotationsAttribute) with 'clazz', 'runtimeVisibleAnnotationsAttribute'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "void proguard.optimize.ChangedCodePrinter.visitRuntimeVisibleAnnotationsAttribute(proguard.classfile.Clazz, proguard.classfile.attribute.annotation.RuntimeVisibleAnnotationsAttribute)"})
  void testVisitRuntimeVisibleAnnotationsAttributeWithClazzRuntimeVisibleAnnotationsAttribute() {
    // Arrange
    AllAttributeVisitor attributeVisitor = mock(AllAttributeVisitor.class);
    doNothing().when(attributeVisitor)
        .visitRuntimeVisibleAnnotationsAttribute(Mockito.<Clazz>any(),
            Mockito.<RuntimeVisibleAnnotationsAttribute>any());
    ChangedCodePrinter changedCodePrinter = new ChangedCodePrinter(attributeVisitor);
    LibraryClass clazz = new LibraryClass();

    // Act
    changedCodePrinter.visitRuntimeVisibleAnnotationsAttribute(clazz, new RuntimeVisibleAnnotationsAttribute());

    // Assert
    verify(attributeVisitor).visitRuntimeVisibleAnnotationsAttribute(isA(Clazz.class),
        isA(RuntimeVisibleAnnotationsAttribute.class));
  }

  /**
   * Test {@link ChangedCodePrinter#visitRuntimeInvisibleAnnotationsAttribute(Clazz, Field, RuntimeInvisibleAnnotationsAttribute)} with {@code clazz}, {@code field}, {@code runtimeInvisibleAnnotationsAttribute}.
   * <p>
   * Method under test: {@link ChangedCodePrinter#visitRuntimeInvisibleAnnotationsAttribute(Clazz, Field, RuntimeInvisibleAnnotationsAttribute)}
   */
  @Test
  @DisplayName("Test visitRuntimeInvisibleAnnotationsAttribute(Clazz, Field, RuntimeInvisibleAnnotationsAttribute) with 'clazz', 'field', 'runtimeInvisibleAnnotationsAttribute'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "void proguard.optimize.ChangedCodePrinter.visitRuntimeInvisibleAnnotationsAttribute(proguard.classfile.Clazz, proguard.classfile.Field, proguard.classfile.attribute.annotation.RuntimeInvisibleAnnotationsAttribute)"})
  void testVisitRuntimeInvisibleAnnotationsAttributeWithClazzFieldRuntimeInvisibleAnnotationsAttribute() {
    // Arrange
    AllAttributeVisitor attributeVisitor = mock(AllAttributeVisitor.class);
    doNothing().when(attributeVisitor)
        .visitRuntimeInvisibleAnnotationsAttribute(Mockito.<Clazz>any(), Mockito.<Field>any(),
            Mockito.<RuntimeInvisibleAnnotationsAttribute>any());
    ChangedCodePrinter changedCodePrinter = new ChangedCodePrinter(attributeVisitor);
    LibraryClass clazz = new LibraryClass();
    LibraryField field = new LibraryField(1, "Name", "Descriptor");

    // Act
    changedCodePrinter.visitRuntimeInvisibleAnnotationsAttribute(clazz, (Field) field,
        new RuntimeInvisibleAnnotationsAttribute());

    // Assert
    verify(attributeVisitor).visitRuntimeInvisibleAnnotationsAttribute(isA(Clazz.class), isA(Field.class),
        isA(RuntimeInvisibleAnnotationsAttribute.class));
  }

  /**
   * Test {@link ChangedCodePrinter#visitRuntimeInvisibleAnnotationsAttribute(Clazz, Method, RuntimeInvisibleAnnotationsAttribute)} with {@code clazz}, {@code method}, {@code runtimeInvisibleAnnotationsAttribute}.
   * <p>
   * Method under test: {@link ChangedCodePrinter#visitRuntimeInvisibleAnnotationsAttribute(Clazz, Method, RuntimeInvisibleAnnotationsAttribute)}
   */
  @Test
  @DisplayName("Test visitRuntimeInvisibleAnnotationsAttribute(Clazz, Method, RuntimeInvisibleAnnotationsAttribute) with 'clazz', 'method', 'runtimeInvisibleAnnotationsAttribute'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "void proguard.optimize.ChangedCodePrinter.visitRuntimeInvisibleAnnotationsAttribute(proguard.classfile.Clazz, proguard.classfile.Method, proguard.classfile.attribute.annotation.RuntimeInvisibleAnnotationsAttribute)"})
  void testVisitRuntimeInvisibleAnnotationsAttributeWithClazzMethodRuntimeInvisibleAnnotationsAttribute() {
    // Arrange
    AllAttributeVisitor attributeVisitor = mock(AllAttributeVisitor.class);
    doNothing().when(attributeVisitor)
        .visitRuntimeInvisibleAnnotationsAttribute(Mockito.<Clazz>any(), Mockito.<Method>any(),
            Mockito.<RuntimeInvisibleAnnotationsAttribute>any());
    ChangedCodePrinter changedCodePrinter = new ChangedCodePrinter(attributeVisitor);
    LibraryClass clazz = new LibraryClass();
    LibraryMethod method = new LibraryMethod(1, "Name", "Descriptor");

    // Act
    changedCodePrinter.visitRuntimeInvisibleAnnotationsAttribute(clazz, (Method) method,
        new RuntimeInvisibleAnnotationsAttribute());

    // Assert
    verify(attributeVisitor).visitRuntimeInvisibleAnnotationsAttribute(isA(Clazz.class), isA(Method.class),
        isA(RuntimeInvisibleAnnotationsAttribute.class));
  }

  /**
   * Test {@link ChangedCodePrinter#visitRuntimeInvisibleAnnotationsAttribute(Clazz, RuntimeInvisibleAnnotationsAttribute)} with {@code clazz}, {@code runtimeInvisibleAnnotationsAttribute}.
   * <p>
   * Method under test: {@link ChangedCodePrinter#visitRuntimeInvisibleAnnotationsAttribute(Clazz, RuntimeInvisibleAnnotationsAttribute)}
   */
  @Test
  @DisplayName("Test visitRuntimeInvisibleAnnotationsAttribute(Clazz, RuntimeInvisibleAnnotationsAttribute) with 'clazz', 'runtimeInvisibleAnnotationsAttribute'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "void proguard.optimize.ChangedCodePrinter.visitRuntimeInvisibleAnnotationsAttribute(proguard.classfile.Clazz, proguard.classfile.attribute.annotation.RuntimeInvisibleAnnotationsAttribute)"})
  void testVisitRuntimeInvisibleAnnotationsAttributeWithClazzRuntimeInvisibleAnnotationsAttribute() {
    // Arrange
    AllAttributeVisitor attributeVisitor = mock(AllAttributeVisitor.class);
    doNothing().when(attributeVisitor)
        .visitRuntimeInvisibleAnnotationsAttribute(Mockito.<Clazz>any(),
            Mockito.<RuntimeInvisibleAnnotationsAttribute>any());
    ChangedCodePrinter changedCodePrinter = new ChangedCodePrinter(attributeVisitor);
    LibraryClass clazz = new LibraryClass();

    // Act
    changedCodePrinter.visitRuntimeInvisibleAnnotationsAttribute(clazz, new RuntimeInvisibleAnnotationsAttribute());

    // Assert
    verify(attributeVisitor).visitRuntimeInvisibleAnnotationsAttribute(isA(Clazz.class),
        isA(RuntimeInvisibleAnnotationsAttribute.class));
  }

  /**
   * Test {@link ChangedCodePrinter#visitRuntimeVisibleParameterAnnotationsAttribute(Clazz, Method, RuntimeVisibleParameterAnnotationsAttribute)}.
   * <p>
   * Method under test: {@link ChangedCodePrinter#visitRuntimeVisibleParameterAnnotationsAttribute(Clazz, Method, RuntimeVisibleParameterAnnotationsAttribute)}
   */
  @Test
  @DisplayName("Test visitRuntimeVisibleParameterAnnotationsAttribute(Clazz, Method, RuntimeVisibleParameterAnnotationsAttribute)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "void proguard.optimize.ChangedCodePrinter.visitRuntimeVisibleParameterAnnotationsAttribute(proguard.classfile.Clazz, proguard.classfile.Method, proguard.classfile.attribute.annotation.RuntimeVisibleParameterAnnotationsAttribute)"})
  void testVisitRuntimeVisibleParameterAnnotationsAttribute() {
    // Arrange
    AllAttributeVisitor attributeVisitor = mock(AllAttributeVisitor.class);
    doNothing().when(attributeVisitor)
        .visitRuntimeVisibleParameterAnnotationsAttribute(Mockito.<Clazz>any(), Mockito.<Method>any(),
            Mockito.<RuntimeVisibleParameterAnnotationsAttribute>any());
    ChangedCodePrinter changedCodePrinter = new ChangedCodePrinter(attributeVisitor);
    LibraryClass clazz = new LibraryClass();
    LibraryMethod method = new LibraryMethod(1, "Name", "Descriptor");

    // Act
    changedCodePrinter.visitRuntimeVisibleParameterAnnotationsAttribute(clazz, method,
        new RuntimeVisibleParameterAnnotationsAttribute());

    // Assert
    verify(attributeVisitor).visitRuntimeVisibleParameterAnnotationsAttribute(isA(Clazz.class), isA(Method.class),
        isA(RuntimeVisibleParameterAnnotationsAttribute.class));
  }

  /**
   * Test {@link ChangedCodePrinter#visitRuntimeInvisibleParameterAnnotationsAttribute(Clazz, Method, RuntimeInvisibleParameterAnnotationsAttribute)}.
   * <p>
   * Method under test: {@link ChangedCodePrinter#visitRuntimeInvisibleParameterAnnotationsAttribute(Clazz, Method, RuntimeInvisibleParameterAnnotationsAttribute)}
   */
  @Test
  @DisplayName("Test visitRuntimeInvisibleParameterAnnotationsAttribute(Clazz, Method, RuntimeInvisibleParameterAnnotationsAttribute)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "void proguard.optimize.ChangedCodePrinter.visitRuntimeInvisibleParameterAnnotationsAttribute(proguard.classfile.Clazz, proguard.classfile.Method, proguard.classfile.attribute.annotation.RuntimeInvisibleParameterAnnotationsAttribute)"})
  void testVisitRuntimeInvisibleParameterAnnotationsAttribute() {
    // Arrange
    AllAttributeVisitor attributeVisitor = mock(AllAttributeVisitor.class);
    doNothing().when(attributeVisitor)
        .visitRuntimeInvisibleParameterAnnotationsAttribute(Mockito.<Clazz>any(), Mockito.<Method>any(),
            Mockito.<RuntimeInvisibleParameterAnnotationsAttribute>any());
    ChangedCodePrinter changedCodePrinter = new ChangedCodePrinter(attributeVisitor);
    LibraryClass clazz = new LibraryClass();
    LibraryMethod method = new LibraryMethod(1, "Name", "Descriptor");

    // Act
    changedCodePrinter.visitRuntimeInvisibleParameterAnnotationsAttribute(clazz, method,
        new RuntimeInvisibleParameterAnnotationsAttribute());

    // Assert
    verify(attributeVisitor).visitRuntimeInvisibleParameterAnnotationsAttribute(isA(Clazz.class), isA(Method.class),
        isA(RuntimeInvisibleParameterAnnotationsAttribute.class));
  }

  /**
   * Test {@link ChangedCodePrinter#visitRuntimeVisibleTypeAnnotationsAttribute(Clazz, Field, RuntimeVisibleTypeAnnotationsAttribute)} with {@code clazz}, {@code field}, {@code runtimeVisibleTypeAnnotationsAttribute}.
   * <p>
   * Method under test: {@link ChangedCodePrinter#visitRuntimeVisibleTypeAnnotationsAttribute(Clazz, Field, RuntimeVisibleTypeAnnotationsAttribute)}
   */
  @Test
  @DisplayName("Test visitRuntimeVisibleTypeAnnotationsAttribute(Clazz, Field, RuntimeVisibleTypeAnnotationsAttribute) with 'clazz', 'field', 'runtimeVisibleTypeAnnotationsAttribute'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "void proguard.optimize.ChangedCodePrinter.visitRuntimeVisibleTypeAnnotationsAttribute(proguard.classfile.Clazz, proguard.classfile.Field, proguard.classfile.attribute.annotation.RuntimeVisibleTypeAnnotationsAttribute)"})
  void testVisitRuntimeVisibleTypeAnnotationsAttributeWithClazzFieldRuntimeVisibleTypeAnnotationsAttribute() {
    // Arrange
    AllAttributeVisitor attributeVisitor = mock(AllAttributeVisitor.class);
    doNothing().when(attributeVisitor)
        .visitRuntimeVisibleTypeAnnotationsAttribute(Mockito.<Clazz>any(), Mockito.<Field>any(),
            Mockito.<RuntimeVisibleTypeAnnotationsAttribute>any());
    ChangedCodePrinter changedCodePrinter = new ChangedCodePrinter(attributeVisitor);
    LibraryClass clazz = new LibraryClass();
    LibraryField field = new LibraryField(1, "Name", "Descriptor");

    // Act
    changedCodePrinter.visitRuntimeVisibleTypeAnnotationsAttribute(clazz, (Field) field,
        new RuntimeVisibleTypeAnnotationsAttribute());

    // Assert
    verify(attributeVisitor).visitRuntimeVisibleTypeAnnotationsAttribute(isA(Clazz.class), isA(Field.class),
        isA(RuntimeVisibleTypeAnnotationsAttribute.class));
  }

  /**
   * Test {@link ChangedCodePrinter#visitRuntimeVisibleTypeAnnotationsAttribute(Clazz, Method, CodeAttribute, RuntimeVisibleTypeAnnotationsAttribute)} with {@code clazz}, {@code method}, {@code codeAttribute}, {@code runtimeVisibleTypeAnnotationsAttribute}.
   * <p>
   * Method under test: {@link ChangedCodePrinter#visitRuntimeVisibleTypeAnnotationsAttribute(Clazz, Method, CodeAttribute, RuntimeVisibleTypeAnnotationsAttribute)}
   */
  @Test
  @DisplayName("Test visitRuntimeVisibleTypeAnnotationsAttribute(Clazz, Method, CodeAttribute, RuntimeVisibleTypeAnnotationsAttribute) with 'clazz', 'method', 'codeAttribute', 'runtimeVisibleTypeAnnotationsAttribute'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "void proguard.optimize.ChangedCodePrinter.visitRuntimeVisibleTypeAnnotationsAttribute(proguard.classfile.Clazz, proguard.classfile.Method, proguard.classfile.attribute.CodeAttribute, proguard.classfile.attribute.annotation.RuntimeVisibleTypeAnnotationsAttribute)"})
  void testVisitRuntimeVisibleTypeAnnotationsAttributeWithClazzMethodCodeAttributeRuntimeVisibleTypeAnnotationsAttribute() {
    // Arrange
    AllAttributeVisitor attributeVisitor = mock(AllAttributeVisitor.class);
    doNothing().when(attributeVisitor)
        .visitRuntimeVisibleTypeAnnotationsAttribute(Mockito.<Clazz>any(), Mockito.<Method>any(),
            Mockito.<CodeAttribute>any(), Mockito.<RuntimeVisibleTypeAnnotationsAttribute>any());
    ChangedCodePrinter changedCodePrinter = new ChangedCodePrinter(attributeVisitor);
    LibraryClass clazz = new LibraryClass();
    LibraryMethod method = new LibraryMethod(1, "Name", "Descriptor");

    CodeAttribute codeAttribute = new CodeAttribute(1);

    // Act
    changedCodePrinter.visitRuntimeVisibleTypeAnnotationsAttribute(clazz, method, codeAttribute,
        new RuntimeVisibleTypeAnnotationsAttribute());

    // Assert
    verify(attributeVisitor).visitRuntimeVisibleTypeAnnotationsAttribute(isA(Clazz.class), isA(Method.class),
        isA(CodeAttribute.class), isA(RuntimeVisibleTypeAnnotationsAttribute.class));
  }

  /**
   * Test {@link ChangedCodePrinter#visitRuntimeVisibleTypeAnnotationsAttribute(Clazz, Method, RuntimeVisibleTypeAnnotationsAttribute)} with {@code clazz}, {@code method}, {@code runtimeVisibleTypeAnnotationsAttribute}.
   * <p>
   * Method under test: {@link ChangedCodePrinter#visitRuntimeVisibleTypeAnnotationsAttribute(Clazz, Method, RuntimeVisibleTypeAnnotationsAttribute)}
   */
  @Test
  @DisplayName("Test visitRuntimeVisibleTypeAnnotationsAttribute(Clazz, Method, RuntimeVisibleTypeAnnotationsAttribute) with 'clazz', 'method', 'runtimeVisibleTypeAnnotationsAttribute'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "void proguard.optimize.ChangedCodePrinter.visitRuntimeVisibleTypeAnnotationsAttribute(proguard.classfile.Clazz, proguard.classfile.Method, proguard.classfile.attribute.annotation.RuntimeVisibleTypeAnnotationsAttribute)"})
  void testVisitRuntimeVisibleTypeAnnotationsAttributeWithClazzMethodRuntimeVisibleTypeAnnotationsAttribute() {
    // Arrange
    AllAttributeVisitor attributeVisitor = mock(AllAttributeVisitor.class);
    doNothing().when(attributeVisitor)
        .visitRuntimeVisibleTypeAnnotationsAttribute(Mockito.<Clazz>any(), Mockito.<Method>any(),
            Mockito.<RuntimeVisibleTypeAnnotationsAttribute>any());
    ChangedCodePrinter changedCodePrinter = new ChangedCodePrinter(attributeVisitor);
    LibraryClass clazz = new LibraryClass();
    LibraryMethod method = new LibraryMethod(1, "Name", "Descriptor");

    // Act
    changedCodePrinter.visitRuntimeVisibleTypeAnnotationsAttribute(clazz, (Method) method,
        new RuntimeVisibleTypeAnnotationsAttribute());

    // Assert
    verify(attributeVisitor).visitRuntimeVisibleTypeAnnotationsAttribute(isA(Clazz.class), isA(Method.class),
        isA(RuntimeVisibleTypeAnnotationsAttribute.class));
  }

  /**
   * Test {@link ChangedCodePrinter#visitRuntimeVisibleTypeAnnotationsAttribute(Clazz, RuntimeVisibleTypeAnnotationsAttribute)} with {@code clazz}, {@code runtimeVisibleTypeAnnotationsAttribute}.
   * <p>
   * Method under test: {@link ChangedCodePrinter#visitRuntimeVisibleTypeAnnotationsAttribute(Clazz, RuntimeVisibleTypeAnnotationsAttribute)}
   */
  @Test
  @DisplayName("Test visitRuntimeVisibleTypeAnnotationsAttribute(Clazz, RuntimeVisibleTypeAnnotationsAttribute) with 'clazz', 'runtimeVisibleTypeAnnotationsAttribute'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "void proguard.optimize.ChangedCodePrinter.visitRuntimeVisibleTypeAnnotationsAttribute(proguard.classfile.Clazz, proguard.classfile.attribute.annotation.RuntimeVisibleTypeAnnotationsAttribute)"})
  void testVisitRuntimeVisibleTypeAnnotationsAttributeWithClazzRuntimeVisibleTypeAnnotationsAttribute() {
    // Arrange
    AllAttributeVisitor attributeVisitor = mock(AllAttributeVisitor.class);
    doNothing().when(attributeVisitor)
        .visitRuntimeVisibleTypeAnnotationsAttribute(Mockito.<Clazz>any(),
            Mockito.<RuntimeVisibleTypeAnnotationsAttribute>any());
    ChangedCodePrinter changedCodePrinter = new ChangedCodePrinter(attributeVisitor);
    LibraryClass clazz = new LibraryClass();

    // Act
    changedCodePrinter.visitRuntimeVisibleTypeAnnotationsAttribute(clazz, new RuntimeVisibleTypeAnnotationsAttribute());

    // Assert
    verify(attributeVisitor).visitRuntimeVisibleTypeAnnotationsAttribute(isA(Clazz.class),
        isA(RuntimeVisibleTypeAnnotationsAttribute.class));
  }

  /**
   * Test {@link ChangedCodePrinter#visitRuntimeInvisibleTypeAnnotationsAttribute(Clazz, Field, RuntimeInvisibleTypeAnnotationsAttribute)} with {@code clazz}, {@code field}, {@code runtimeInvisibleTypeAnnotationsAttribute}.
   * <p>
   * Method under test: {@link ChangedCodePrinter#visitRuntimeInvisibleTypeAnnotationsAttribute(Clazz, Field, RuntimeInvisibleTypeAnnotationsAttribute)}
   */
  @Test
  @DisplayName("Test visitRuntimeInvisibleTypeAnnotationsAttribute(Clazz, Field, RuntimeInvisibleTypeAnnotationsAttribute) with 'clazz', 'field', 'runtimeInvisibleTypeAnnotationsAttribute'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "void proguard.optimize.ChangedCodePrinter.visitRuntimeInvisibleTypeAnnotationsAttribute(proguard.classfile.Clazz, proguard.classfile.Field, proguard.classfile.attribute.annotation.RuntimeInvisibleTypeAnnotationsAttribute)"})
  void testVisitRuntimeInvisibleTypeAnnotationsAttributeWithClazzFieldRuntimeInvisibleTypeAnnotationsAttribute() {
    // Arrange
    AllAttributeVisitor attributeVisitor = mock(AllAttributeVisitor.class);
    doNothing().when(attributeVisitor)
        .visitRuntimeInvisibleTypeAnnotationsAttribute(Mockito.<Clazz>any(), Mockito.<Field>any(),
            Mockito.<RuntimeInvisibleTypeAnnotationsAttribute>any());
    ChangedCodePrinter changedCodePrinter = new ChangedCodePrinter(attributeVisitor);
    LibraryClass clazz = new LibraryClass();
    LibraryField field = new LibraryField(1, "Name", "Descriptor");

    // Act
    changedCodePrinter.visitRuntimeInvisibleTypeAnnotationsAttribute(clazz, (Field) field,
        new RuntimeInvisibleTypeAnnotationsAttribute());

    // Assert
    verify(attributeVisitor).visitRuntimeInvisibleTypeAnnotationsAttribute(isA(Clazz.class), isA(Field.class),
        isA(RuntimeInvisibleTypeAnnotationsAttribute.class));
  }

  /**
   * Test {@link ChangedCodePrinter#visitRuntimeInvisibleTypeAnnotationsAttribute(Clazz, Method, CodeAttribute, RuntimeInvisibleTypeAnnotationsAttribute)} with {@code clazz}, {@code method}, {@code codeAttribute}, {@code runtimeInvisibleTypeAnnotationsAttribute}.
   * <p>
   * Method under test: {@link ChangedCodePrinter#visitRuntimeInvisibleTypeAnnotationsAttribute(Clazz, Method, CodeAttribute, RuntimeInvisibleTypeAnnotationsAttribute)}
   */
  @Test
  @DisplayName("Test visitRuntimeInvisibleTypeAnnotationsAttribute(Clazz, Method, CodeAttribute, RuntimeInvisibleTypeAnnotationsAttribute) with 'clazz', 'method', 'codeAttribute', 'runtimeInvisibleTypeAnnotationsAttribute'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "void proguard.optimize.ChangedCodePrinter.visitRuntimeInvisibleTypeAnnotationsAttribute(proguard.classfile.Clazz, proguard.classfile.Method, proguard.classfile.attribute.CodeAttribute, proguard.classfile.attribute.annotation.RuntimeInvisibleTypeAnnotationsAttribute)"})
  void testVisitRuntimeInvisibleTypeAnnotationsAttributeWithClazzMethodCodeAttributeRuntimeInvisibleTypeAnnotationsAttribute() {
    // Arrange
    AllAttributeVisitor attributeVisitor = mock(AllAttributeVisitor.class);
    doNothing().when(attributeVisitor)
        .visitRuntimeInvisibleTypeAnnotationsAttribute(Mockito.<Clazz>any(), Mockito.<Method>any(),
            Mockito.<CodeAttribute>any(), Mockito.<RuntimeInvisibleTypeAnnotationsAttribute>any());
    ChangedCodePrinter changedCodePrinter = new ChangedCodePrinter(attributeVisitor);
    LibraryClass clazz = new LibraryClass();
    LibraryMethod method = new LibraryMethod(1, "Name", "Descriptor");

    CodeAttribute codeAttribute = new CodeAttribute(1);

    // Act
    changedCodePrinter.visitRuntimeInvisibleTypeAnnotationsAttribute(clazz, method, codeAttribute,
        new RuntimeInvisibleTypeAnnotationsAttribute());

    // Assert
    verify(attributeVisitor).visitRuntimeInvisibleTypeAnnotationsAttribute(isA(Clazz.class), isA(Method.class),
        isA(CodeAttribute.class), isA(RuntimeInvisibleTypeAnnotationsAttribute.class));
  }

  /**
   * Test {@link ChangedCodePrinter#visitRuntimeInvisibleTypeAnnotationsAttribute(Clazz, Method, RuntimeInvisibleTypeAnnotationsAttribute)} with {@code clazz}, {@code method}, {@code runtimeInvisibleTypeAnnotationsAttribute}.
   * <p>
   * Method under test: {@link ChangedCodePrinter#visitRuntimeInvisibleTypeAnnotationsAttribute(Clazz, Method, RuntimeInvisibleTypeAnnotationsAttribute)}
   */
  @Test
  @DisplayName("Test visitRuntimeInvisibleTypeAnnotationsAttribute(Clazz, Method, RuntimeInvisibleTypeAnnotationsAttribute) with 'clazz', 'method', 'runtimeInvisibleTypeAnnotationsAttribute'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "void proguard.optimize.ChangedCodePrinter.visitRuntimeInvisibleTypeAnnotationsAttribute(proguard.classfile.Clazz, proguard.classfile.Method, proguard.classfile.attribute.annotation.RuntimeInvisibleTypeAnnotationsAttribute)"})
  void testVisitRuntimeInvisibleTypeAnnotationsAttributeWithClazzMethodRuntimeInvisibleTypeAnnotationsAttribute() {
    // Arrange
    AllAttributeVisitor attributeVisitor = mock(AllAttributeVisitor.class);
    doNothing().when(attributeVisitor)
        .visitRuntimeInvisibleTypeAnnotationsAttribute(Mockito.<Clazz>any(), Mockito.<Method>any(),
            Mockito.<RuntimeInvisibleTypeAnnotationsAttribute>any());
    ChangedCodePrinter changedCodePrinter = new ChangedCodePrinter(attributeVisitor);
    LibraryClass clazz = new LibraryClass();
    LibraryMethod method = new LibraryMethod(1, "Name", "Descriptor");

    // Act
    changedCodePrinter.visitRuntimeInvisibleTypeAnnotationsAttribute(clazz, (Method) method,
        new RuntimeInvisibleTypeAnnotationsAttribute());

    // Assert
    verify(attributeVisitor).visitRuntimeInvisibleTypeAnnotationsAttribute(isA(Clazz.class), isA(Method.class),
        isA(RuntimeInvisibleTypeAnnotationsAttribute.class));
  }

  /**
   * Test {@link ChangedCodePrinter#visitRuntimeInvisibleTypeAnnotationsAttribute(Clazz, RuntimeInvisibleTypeAnnotationsAttribute)} with {@code clazz}, {@code runtimeInvisibleTypeAnnotationsAttribute}.
   * <p>
   * Method under test: {@link ChangedCodePrinter#visitRuntimeInvisibleTypeAnnotationsAttribute(Clazz, RuntimeInvisibleTypeAnnotationsAttribute)}
   */
  @Test
  @DisplayName("Test visitRuntimeInvisibleTypeAnnotationsAttribute(Clazz, RuntimeInvisibleTypeAnnotationsAttribute) with 'clazz', 'runtimeInvisibleTypeAnnotationsAttribute'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "void proguard.optimize.ChangedCodePrinter.visitRuntimeInvisibleTypeAnnotationsAttribute(proguard.classfile.Clazz, proguard.classfile.attribute.annotation.RuntimeInvisibleTypeAnnotationsAttribute)"})
  void testVisitRuntimeInvisibleTypeAnnotationsAttributeWithClazzRuntimeInvisibleTypeAnnotationsAttribute() {
    // Arrange
    AllAttributeVisitor attributeVisitor = mock(AllAttributeVisitor.class);
    doNothing().when(attributeVisitor)
        .visitRuntimeInvisibleTypeAnnotationsAttribute(Mockito.<Clazz>any(),
            Mockito.<RuntimeInvisibleTypeAnnotationsAttribute>any());
    ChangedCodePrinter changedCodePrinter = new ChangedCodePrinter(attributeVisitor);
    LibraryClass clazz = new LibraryClass();

    // Act
    changedCodePrinter.visitRuntimeInvisibleTypeAnnotationsAttribute(clazz,
        new RuntimeInvisibleTypeAnnotationsAttribute());

    // Assert
    verify(attributeVisitor).visitRuntimeInvisibleTypeAnnotationsAttribute(isA(Clazz.class),
        isA(RuntimeInvisibleTypeAnnotationsAttribute.class));
  }

  /**
   * Test {@link ChangedCodePrinter#visitAnnotationDefaultAttribute(Clazz, Method, AnnotationDefaultAttribute)}.
   * <ul>
   *   <li>Then calls {@link AttributeVisitor#visitAnnotationDefaultAttribute(Clazz, Method, AnnotationDefaultAttribute)}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ChangedCodePrinter#visitAnnotationDefaultAttribute(Clazz, Method, AnnotationDefaultAttribute)}
   */
  @Test
  @DisplayName("Test visitAnnotationDefaultAttribute(Clazz, Method, AnnotationDefaultAttribute); then calls visitAnnotationDefaultAttribute(Clazz, Method, AnnotationDefaultAttribute)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "void proguard.optimize.ChangedCodePrinter.visitAnnotationDefaultAttribute(proguard.classfile.Clazz, proguard.classfile.Method, proguard.classfile.attribute.annotation.AnnotationDefaultAttribute)"})
  void testVisitAnnotationDefaultAttribute_thenCallsVisitAnnotationDefaultAttribute() {
    // Arrange
    AllAttributeVisitor attributeVisitor = mock(AllAttributeVisitor.class);
    doNothing().when(attributeVisitor)
        .visitAnnotationDefaultAttribute(Mockito.<Clazz>any(), Mockito.<Method>any(),
            Mockito.<AnnotationDefaultAttribute>any());
    ChangedCodePrinter changedCodePrinter = new ChangedCodePrinter(attributeVisitor);
    LibraryClass clazz = new LibraryClass();
    LibraryMethod method = new LibraryMethod(1, "Name", "Descriptor");

    // Act
    changedCodePrinter.visitAnnotationDefaultAttribute(clazz, method, new AnnotationDefaultAttribute());

    // Assert
    verify(attributeVisitor).visitAnnotationDefaultAttribute(isA(Clazz.class), isA(Method.class),
        isA(AnnotationDefaultAttribute.class));
  }
}
