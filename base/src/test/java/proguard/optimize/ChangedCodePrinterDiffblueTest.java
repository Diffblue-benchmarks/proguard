package proguard.optimize;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import java.io.UnsupportedEncodingException;
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
import proguard.classfile.attribute.visitor.AllBootstrapMethodInfoVisitor;
import proguard.classfile.attribute.visitor.BootstrapMethodInfoVisitor;
import proguard.fixer.kotlin.KotlinAnnotationCounter;
import proguard.obfuscate.kotlin.KotlinSourceDebugExtensionAttributeObfuscator;

class ChangedCodePrinterDiffblueTest {
  /**
   * Method under test:
   * {@link ChangedCodePrinter#visitUnknownAttribute(Clazz, UnknownAttribute)}
   */
  @Test
  void testVisitUnknownAttribute() {
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
   * Method under test:
   * {@link ChangedCodePrinter#visitSourceDebugExtensionAttribute(Clazz, SourceDebugExtensionAttribute)}
   */
  @Test
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
   * Method under test:
   * {@link ChangedCodePrinter#visitSourceDebugExtensionAttribute(Clazz, SourceDebugExtensionAttribute)}
   */
  @Test
  void testVisitSourceDebugExtensionAttribute2() {
    // Arrange
    ChangedCodePrinter changedCodePrinter = new ChangedCodePrinter(
        new AllBootstrapMethodInfoVisitor(mock(BootstrapMethodInfoVisitor.class)));
    LibraryClass clazz = new LibraryClass();
    SourceDebugExtensionAttribute sourceDebugExtensionAttribute = new SourceDebugExtensionAttribute();

    // Act
    changedCodePrinter.visitSourceDebugExtensionAttribute(clazz, sourceDebugExtensionAttribute);

    // Assert that nothing has changed
    assertEquals(0, sourceDebugExtensionAttribute.u4attributeLength);
  }

  /**
   * Method under test:
   * {@link ChangedCodePrinter#visitSourceDebugExtensionAttribute(Clazz, SourceDebugExtensionAttribute)}
   */
  @Test
  void testVisitSourceDebugExtensionAttribute3() throws UnsupportedEncodingException {
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
   * Method under test:
   * {@link ChangedCodePrinter#visitSourceFileAttribute(Clazz, SourceFileAttribute)}
   */
  @Test
  void testVisitSourceFileAttribute() {
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
   * Method under test:
   * {@link ChangedCodePrinter#visitSourceDirAttribute(Clazz, SourceDirAttribute)}
   */
  @Test
  void testVisitSourceDirAttribute() {
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
   * Method under test:
   * {@link ChangedCodePrinter#visitInnerClassesAttribute(Clazz, InnerClassesAttribute)}
   */
  @Test
  void testVisitInnerClassesAttribute() {
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
   * Method under test:
   * {@link ChangedCodePrinter#visitEnclosingMethodAttribute(Clazz, EnclosingMethodAttribute)}
   */
  @Test
  void testVisitEnclosingMethodAttribute() {
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
   * Method under test:
   * {@link ChangedCodePrinter#visitNestHostAttribute(Clazz, NestHostAttribute)}
   */
  @Test
  void testVisitNestHostAttribute() {
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
   * Method under test:
   * {@link ChangedCodePrinter#visitNestMembersAttribute(Clazz, NestMembersAttribute)}
   */
  @Test
  void testVisitNestMembersAttribute() {
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
   * Method under test:
   * {@link ChangedCodePrinter#visitPermittedSubclassesAttribute(Clazz, PermittedSubclassesAttribute)}
   */
  @Test
  void testVisitPermittedSubclassesAttribute() {
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
   * Method under test:
   * {@link ChangedCodePrinter#visitModuleAttribute(Clazz, ModuleAttribute)}
   */
  @Test
  void testVisitModuleAttribute() {
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
   * Method under test:
   * {@link ChangedCodePrinter#visitModuleMainClassAttribute(Clazz, ModuleMainClassAttribute)}
   */
  @Test
  void testVisitModuleMainClassAttribute() {
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
   * Method under test:
   * {@link ChangedCodePrinter#visitModulePackagesAttribute(Clazz, ModulePackagesAttribute)}
   */
  @Test
  void testVisitModulePackagesAttribute() {
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
   * Method under test:
   * {@link ChangedCodePrinter#visitDeprecatedAttribute(Clazz, Field, DeprecatedAttribute)}
   */
  @Test
  void testVisitDeprecatedAttribute() {
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
   * Method under test:
   * {@link ChangedCodePrinter#visitDeprecatedAttribute(Clazz, Method, DeprecatedAttribute)}
   */
  @Test
  void testVisitDeprecatedAttribute2() {
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
   * Method under test:
   * {@link ChangedCodePrinter#visitDeprecatedAttribute(Clazz, DeprecatedAttribute)}
   */
  @Test
  void testVisitDeprecatedAttribute3() {
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
   * Method under test:
   * {@link ChangedCodePrinter#visitSyntheticAttribute(Clazz, Field, SyntheticAttribute)}
   */
  @Test
  void testVisitSyntheticAttribute() {
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
   * Method under test:
   * {@link ChangedCodePrinter#visitSyntheticAttribute(Clazz, Method, SyntheticAttribute)}
   */
  @Test
  void testVisitSyntheticAttribute2() {
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
   * Method under test:
   * {@link ChangedCodePrinter#visitSyntheticAttribute(Clazz, SyntheticAttribute)}
   */
  @Test
  void testVisitSyntheticAttribute3() {
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
   * Method under test:
   * {@link ChangedCodePrinter#visitSignatureAttribute(Clazz, Field, SignatureAttribute)}
   */
  @Test
  void testVisitSignatureAttribute() {
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
   * Method under test:
   * {@link ChangedCodePrinter#visitSignatureAttribute(Clazz, Method, SignatureAttribute)}
   */
  @Test
  void testVisitSignatureAttribute2() {
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
   * Method under test:
   * {@link ChangedCodePrinter#visitSignatureAttribute(Clazz, SignatureAttribute)}
   */
  @Test
  void testVisitSignatureAttribute3() {
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
   * Method under test:
   * {@link ChangedCodePrinter#visitConstantValueAttribute(Clazz, Field, ConstantValueAttribute)}
   */
  @Test
  void testVisitConstantValueAttribute() {
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
   * Method under test:
   * {@link ChangedCodePrinter#visitMethodParametersAttribute(Clazz, Method, MethodParametersAttribute)}
   */
  @Test
  void testVisitMethodParametersAttribute() {
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
   * Method under test:
   * {@link ChangedCodePrinter#visitExceptionsAttribute(Clazz, Method, ExceptionsAttribute)}
   */
  @Test
  void testVisitExceptionsAttribute() {
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
   * Method under test:
   * {@link ChangedCodePrinter#visitStackMapAttribute(Clazz, Method, CodeAttribute, StackMapAttribute)}
   */
  @Test
  void testVisitStackMapAttribute() {
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
   * Method under test:
   * {@link ChangedCodePrinter#visitStackMapTableAttribute(Clazz, Method, CodeAttribute, StackMapTableAttribute)}
   */
  @Test
  void testVisitStackMapTableAttribute() {
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
   * Method under test:
   * {@link ChangedCodePrinter#visitLineNumberTableAttribute(Clazz, Method, CodeAttribute, LineNumberTableAttribute)}
   */
  @Test
  void testVisitLineNumberTableAttribute() {
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
   * Method under test:
   * {@link ChangedCodePrinter#visitLocalVariableTableAttribute(Clazz, Method, CodeAttribute, LocalVariableTableAttribute)}
   */
  @Test
  void testVisitLocalVariableTableAttribute() {
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
   * Method under test:
   * {@link ChangedCodePrinter#visitLocalVariableTypeTableAttribute(Clazz, Method, CodeAttribute, LocalVariableTypeTableAttribute)}
   */
  @Test
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
   * Method under test:
   * {@link ChangedCodePrinter#visitRuntimeVisibleAnnotationsAttribute(Clazz, Field, RuntimeVisibleAnnotationsAttribute)}
   */
  @Test
  void testVisitRuntimeVisibleAnnotationsAttribute() {
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
   * Method under test:
   * {@link ChangedCodePrinter#visitRuntimeVisibleAnnotationsAttribute(Clazz, Method, RuntimeVisibleAnnotationsAttribute)}
   */
  @Test
  void testVisitRuntimeVisibleAnnotationsAttribute2() {
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
   * Method under test:
   * {@link ChangedCodePrinter#visitRuntimeVisibleAnnotationsAttribute(Clazz, RuntimeVisibleAnnotationsAttribute)}
   */
  @Test
  void testVisitRuntimeVisibleAnnotationsAttribute3() {
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
   * Method under test:
   * {@link ChangedCodePrinter#visitRuntimeInvisibleAnnotationsAttribute(Clazz, Field, RuntimeInvisibleAnnotationsAttribute)}
   */
  @Test
  void testVisitRuntimeInvisibleAnnotationsAttribute() {
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
   * Method under test:
   * {@link ChangedCodePrinter#visitRuntimeInvisibleAnnotationsAttribute(Clazz, Method, RuntimeInvisibleAnnotationsAttribute)}
   */
  @Test
  void testVisitRuntimeInvisibleAnnotationsAttribute2() {
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
   * Method under test:
   * {@link ChangedCodePrinter#visitRuntimeInvisibleAnnotationsAttribute(Clazz, RuntimeInvisibleAnnotationsAttribute)}
   */
  @Test
  void testVisitRuntimeInvisibleAnnotationsAttribute3() {
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
   * Method under test:
   * {@link ChangedCodePrinter#visitRuntimeVisibleParameterAnnotationsAttribute(Clazz, Method, RuntimeVisibleParameterAnnotationsAttribute)}
   */
  @Test
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
   * Method under test:
   * {@link ChangedCodePrinter#visitRuntimeInvisibleParameterAnnotationsAttribute(Clazz, Method, RuntimeInvisibleParameterAnnotationsAttribute)}
   */
  @Test
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
   * Method under test:
   * {@link ChangedCodePrinter#visitRuntimeVisibleTypeAnnotationsAttribute(Clazz, Field, RuntimeVisibleTypeAnnotationsAttribute)}
   */
  @Test
  void testVisitRuntimeVisibleTypeAnnotationsAttribute() {
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
   * Method under test:
   * {@link ChangedCodePrinter#visitRuntimeVisibleTypeAnnotationsAttribute(Clazz, Method, CodeAttribute, RuntimeVisibleTypeAnnotationsAttribute)}
   */
  @Test
  void testVisitRuntimeVisibleTypeAnnotationsAttribute2() {
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
   * Method under test:
   * {@link ChangedCodePrinter#visitRuntimeVisibleTypeAnnotationsAttribute(Clazz, Method, RuntimeVisibleTypeAnnotationsAttribute)}
   */
  @Test
  void testVisitRuntimeVisibleTypeAnnotationsAttribute3() {
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
   * Method under test:
   * {@link ChangedCodePrinter#visitRuntimeVisibleTypeAnnotationsAttribute(Clazz, RuntimeVisibleTypeAnnotationsAttribute)}
   */
  @Test
  void testVisitRuntimeVisibleTypeAnnotationsAttribute4() {
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
   * Method under test:
   * {@link ChangedCodePrinter#visitRuntimeInvisibleTypeAnnotationsAttribute(Clazz, Field, RuntimeInvisibleTypeAnnotationsAttribute)}
   */
  @Test
  void testVisitRuntimeInvisibleTypeAnnotationsAttribute() {
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
   * Method under test:
   * {@link ChangedCodePrinter#visitRuntimeInvisibleTypeAnnotationsAttribute(Clazz, Method, CodeAttribute, RuntimeInvisibleTypeAnnotationsAttribute)}
   */
  @Test
  void testVisitRuntimeInvisibleTypeAnnotationsAttribute2() {
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
   * Method under test:
   * {@link ChangedCodePrinter#visitRuntimeInvisibleTypeAnnotationsAttribute(Clazz, Method, RuntimeInvisibleTypeAnnotationsAttribute)}
   */
  @Test
  void testVisitRuntimeInvisibleTypeAnnotationsAttribute3() {
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
   * Method under test:
   * {@link ChangedCodePrinter#visitRuntimeInvisibleTypeAnnotationsAttribute(Clazz, RuntimeInvisibleTypeAnnotationsAttribute)}
   */
  @Test
  void testVisitRuntimeInvisibleTypeAnnotationsAttribute4() {
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
   * Method under test:
   * {@link ChangedCodePrinter#visitAnnotationDefaultAttribute(Clazz, Method, AnnotationDefaultAttribute)}
   */
  @Test
  void testVisitAnnotationDefaultAttribute() {
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
