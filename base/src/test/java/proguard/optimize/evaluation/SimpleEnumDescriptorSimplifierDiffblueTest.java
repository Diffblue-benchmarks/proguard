package proguard.optimize.evaluation;

import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.anyInt;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
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
import proguard.classfile.attribute.LocalVariableTableAttribute;
import proguard.classfile.attribute.LocalVariableTypeTableAttribute;
import proguard.classfile.attribute.SignatureAttribute;
import proguard.classfile.attribute.visitor.AttributeVisitor;
import proguard.classfile.attribute.visitor.LocalVariableInfoVisitor;
import proguard.classfile.attribute.visitor.LocalVariableTypeInfoVisitor;
import proguard.classfile.constant.InvokeDynamicConstant;
import proguard.classfile.constant.MethodTypeConstant;
import proguard.classfile.constant.visitor.ConstantVisitor;
import proguard.classfile.visitor.MemberVisitor;
import proguard.optimize.info.ClassOptimizationInfo;
import proguard.optimize.info.ProgramClassOptimizationInfo;

class SimpleEnumDescriptorSimplifierDiffblueTest {
  /**
   * Method under test:
   * {@link SimpleEnumDescriptorSimplifier#visitProgramClass(ProgramClass)}
   */
  @Test
  void testVisitProgramClass() {
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
   * Method under test:
   * {@link SimpleEnumDescriptorSimplifier#visitInvokeDynamicConstant(Clazz, InvokeDynamicConstant)}
   */
  @Test
  void testVisitInvokeDynamicConstant() {
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
   * Method under test:
   * {@link SimpleEnumDescriptorSimplifier#visitInvokeDynamicConstant(Clazz, InvokeDynamicConstant)}
   */
  @Test
  void testVisitInvokeDynamicConstant2() {
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
   * Method under test:
   * {@link SimpleEnumDescriptorSimplifier#visitInvokeDynamicConstant(Clazz, InvokeDynamicConstant)}
   */
  @Test
  void testVisitInvokeDynamicConstant3() {
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
   * Method under test:
   * {@link SimpleEnumDescriptorSimplifier#visitMethodTypeConstant(Clazz, MethodTypeConstant)}
   */
  @Test
  void testVisitMethodTypeConstant() {
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
   * Method under test:
   * {@link SimpleEnumDescriptorSimplifier#visitMethodTypeConstant(Clazz, MethodTypeConstant)}
   */
  @Test
  void testVisitMethodTypeConstant2() {
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
   * Method under test:
   * {@link SimpleEnumDescriptorSimplifier#visitMethodTypeConstant(Clazz, MethodTypeConstant)}
   */
  @Test
  void testVisitMethodTypeConstant3() {
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
   * Method under test:
   * {@link SimpleEnumDescriptorSimplifier#visitMethodTypeConstant(Clazz, MethodTypeConstant)}
   */
  @Test
  void testVisitMethodTypeConstant4() {
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
   * Method under test:
   * {@link SimpleEnumDescriptorSimplifier#visitProgramField(ProgramClass, ProgramField)}
   */
  @Test
  void testVisitProgramField() {
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
   * Method under test:
   * {@link SimpleEnumDescriptorSimplifier#visitProgramField(ProgramClass, ProgramField)}
   */
  @Test
  void testVisitProgramField2() {
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
   * Method under test:
   * {@link SimpleEnumDescriptorSimplifier#visitProgramField(ProgramClass, ProgramField)}
   */
  @Test
  void testVisitProgramField3() {
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
   * Method under test:
   * {@link SimpleEnumDescriptorSimplifier#visitProgramMethod(ProgramClass, ProgramMethod)}
   */
  @Test
  void testVisitProgramMethod() {
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
   * Method under test:
   * {@link SimpleEnumDescriptorSimplifier#visitProgramMethod(ProgramClass, ProgramMethod)}
   */
  @Test
  void testVisitProgramMethod2() {
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
   * Method under test:
   * {@link SimpleEnumDescriptorSimplifier#visitProgramMethod(ProgramClass, ProgramMethod)}
   */
  @Test
  void testVisitProgramMethod3() {
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
   * Method under test:
   * {@link SimpleEnumDescriptorSimplifier#visitCodeAttribute(Clazz, Method, CodeAttribute)}
   */
  @Test
  void testVisitCodeAttribute() {
    // Arrange
    SimpleEnumDescriptorSimplifier simpleEnumDescriptorSimplifier = new SimpleEnumDescriptorSimplifier();
    LibraryClass clazz = new LibraryClass();
    LibraryMethod method = new LibraryMethod(1, "Name", "Descriptor");

    CodeAttribute codeAttribute = mock(CodeAttribute.class);
    doNothing().when(codeAttribute)
        .attributesAccept(Mockito.<Clazz>any(), Mockito.<Method>any(), Mockito.<AttributeVisitor>any());

    // Act
    simpleEnumDescriptorSimplifier.visitCodeAttribute(clazz, method, codeAttribute);

    // Assert
    verify(codeAttribute).attributesAccept(isA(Clazz.class), isA(Method.class), isA(AttributeVisitor.class));
  }

  /**
   * Method under test:
   * {@link SimpleEnumDescriptorSimplifier#visitLocalVariableTableAttribute(Clazz, Method, CodeAttribute, LocalVariableTableAttribute)}
   */
  @Test
  void testVisitLocalVariableTableAttribute() {
    // Arrange
    SimpleEnumDescriptorSimplifier simpleEnumDescriptorSimplifier = new SimpleEnumDescriptorSimplifier();
    LibraryClass clazz = new LibraryClass();
    LibraryMethod method = new LibraryMethod(1, "Name", "Descriptor");

    CodeAttribute codeAttribute = new CodeAttribute(1);
    LocalVariableTableAttribute localVariableTableAttribute = mock(LocalVariableTableAttribute.class);
    doNothing().when(localVariableTableAttribute)
        .localVariablesAccept(Mockito.<Clazz>any(), Mockito.<Method>any(), Mockito.<CodeAttribute>any(),
            Mockito.<LocalVariableInfoVisitor>any());

    // Act
    simpleEnumDescriptorSimplifier.visitLocalVariableTableAttribute(clazz, method, codeAttribute,
        localVariableTableAttribute);

    // Assert
    verify(localVariableTableAttribute).localVariablesAccept(isA(Clazz.class), isA(Method.class),
        isA(CodeAttribute.class), isA(LocalVariableInfoVisitor.class));
  }

  /**
   * Method under test:
   * {@link SimpleEnumDescriptorSimplifier#visitLocalVariableTypeTableAttribute(Clazz, Method, CodeAttribute, LocalVariableTypeTableAttribute)}
   */
  @Test
  void testVisitLocalVariableTypeTableAttribute() {
    // Arrange
    SimpleEnumDescriptorSimplifier simpleEnumDescriptorSimplifier = new SimpleEnumDescriptorSimplifier();
    LibraryClass clazz = new LibraryClass();
    LibraryMethod method = new LibraryMethod(1, "Name", "Descriptor");

    CodeAttribute codeAttribute = new CodeAttribute(1);
    LocalVariableTypeTableAttribute localVariableTypeTableAttribute = mock(LocalVariableTypeTableAttribute.class);
    doNothing().when(localVariableTypeTableAttribute)
        .localVariablesAccept(Mockito.<Clazz>any(), Mockito.<Method>any(), Mockito.<CodeAttribute>any(),
            Mockito.<LocalVariableTypeInfoVisitor>any());

    // Act
    simpleEnumDescriptorSimplifier.visitLocalVariableTypeTableAttribute(clazz, method, codeAttribute,
        localVariableTypeTableAttribute);

    // Assert
    verify(localVariableTypeTableAttribute).localVariablesAccept(isA(Clazz.class), isA(Method.class),
        isA(CodeAttribute.class), isA(LocalVariableTypeInfoVisitor.class));
  }

  /**
   * Method under test:
   * {@link SimpleEnumDescriptorSimplifier#visitSignatureAttribute(Clazz, SignatureAttribute)}
   */
  @Test
  void testVisitSignatureAttribute() {
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
   * Method under test:
   * {@link SimpleEnumDescriptorSimplifier#visitSignatureAttribute(Clazz, SignatureAttribute)}
   */
  @Test
  void testVisitSignatureAttribute2() {
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
   * Method under test:
   * {@link SimpleEnumDescriptorSimplifier#visitLocalVariableInfo(Clazz, Method, CodeAttribute, LocalVariableInfo)}
   */
  @Test
  void testVisitLocalVariableInfo() {
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

  /**
   * Method under test:
   * {@link SimpleEnumDescriptorSimplifier#visitLocalVariableInfo(Clazz, Method, CodeAttribute, LocalVariableInfo)}
   */
  @Test
  void testVisitLocalVariableInfo2() {
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
}
