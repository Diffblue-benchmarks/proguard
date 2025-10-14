package proguard.optimize.gson;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.anyInt;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.io.UnsupportedEncodingException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import proguard.classfile.ClassPool;
import proguard.classfile.Clazz;
import proguard.classfile.LibraryClass;
import proguard.classfile.LibraryMethod;
import proguard.classfile.Method;
import proguard.classfile.ProgramClass;
import proguard.classfile.attribute.CodeAttribute;
import proguard.classfile.constant.ClassConstant;
import proguard.classfile.constant.Constant;
import proguard.classfile.constant.IntegerConstant;
import proguard.classfile.constant.Utf8Constant;
import proguard.classfile.editor.CodeAttributeEditor;
import proguard.classfile.instruction.Instruction;
import proguard.classfile.kotlin.KotlinConstants;

class OptimizedJsonWriterImplInitializerDiffblueTest {
  /**
   * Test {@link OptimizedJsonWriterImplInitializer#visitCodeAttribute(Clazz, Method,
   * CodeAttribute)}.
   *
   * <p>Method under test: {@link OptimizedJsonWriterImplInitializer#visitCodeAttribute(Clazz,
   * Method, CodeAttribute)}
   */
  @Test
  @DisplayName("Test visitCodeAttribute(Clazz, Method, CodeAttribute)")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void OptimizedJsonWriterImplInitializer.visitCodeAttribute(Clazz, Method, CodeAttribute)"
  })
  void testVisitCodeAttribute() {
    // Arrange
    CodeAttributeEditor codeAttributeEditor = mock(CodeAttributeEditor.class);
    doNothing()
        .when(codeAttributeEditor)
        .replaceInstruction(anyInt(), Mockito.<Instruction[]>any());
    doNothing()
        .when(codeAttributeEditor)
        .visitCodeAttribute(
            Mockito.<Clazz>any(), Mockito.<Method>any(), Mockito.<CodeAttribute>any());
    doNothing().when(codeAttributeEditor).reset(anyInt());
    OptimizedJsonWriterImplInitializer optimizedJsonWriterImplInitializer =
        new OptimizedJsonWriterImplInitializer(
            null, KotlinConstants.dummyClassPool, codeAttributeEditor, new OptimizedJsonInfo());
    ClassConstant classConstant = new ClassConstant();
    Constant[] constantPool = new Constant[] {classConstant};
    ProgramClass clazz = new ProgramClass(1, 1, constantPool, 1, 1, 1);
    LibraryMethod method = new LibraryMethod(1, "Name", "Descriptor");

    CodeAttribute codeAttribute = mock(CodeAttribute.class);
    doNothing().when(codeAttribute).addProcessingFlags((int[]) Mockito.any());
    codeAttribute.addProcessingFlags(2, 1, 2, 1);

    // Act
    optimizedJsonWriterImplInitializer.visitCodeAttribute(clazz, method, codeAttribute);

    // Assert
    verify(codeAttributeEditor).replaceInstruction(eq(0), isA(Instruction[].class));
    verify(codeAttributeEditor).reset(0);
    verify(codeAttributeEditor)
        .visitCodeAttribute(isA(Clazz.class), isA(Method.class), isA(CodeAttribute.class));
    verify(codeAttribute).addProcessingFlags((int[]) Mockito.any());
    Constant[] constantArray = clazz.constantPool;
    Constant constant = constantArray[0];
    assertTrue(constant instanceof ClassConstant);
    assertTrue(constantArray[1] instanceof IntegerConstant);
    assertEquals(17, constantArray.length);
    assertEquals(4, clazz.u2constantPoolCount);
    assertSame(classConstant, constant);
  }

  /**
   * Test {@link OptimizedJsonWriterImplInitializer#visitCodeAttribute(Clazz, Method,
   * CodeAttribute)}.
   *
   * <ul>
   *   <li>Then first element {@link ClassConstant}.
   * </ul>
   *
   * <p>Method under test: {@link OptimizedJsonWriterImplInitializer#visitCodeAttribute(Clazz,
   * Method, CodeAttribute)}
   */
  @Test
  @DisplayName(
      "Test visitCodeAttribute(Clazz, Method, CodeAttribute); then first element ClassConstant")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void OptimizedJsonWriterImplInitializer.visitCodeAttribute(Clazz, Method, CodeAttribute)"
  })
  void testVisitCodeAttribute_thenFirstElementClassConstant() {
    // Arrange
    CodeAttributeEditor codeAttributeEditor = mock(CodeAttributeEditor.class);
    doNothing()
        .when(codeAttributeEditor)
        .replaceInstruction(anyInt(), Mockito.<Instruction[]>any());
    doNothing()
        .when(codeAttributeEditor)
        .visitCodeAttribute(
            Mockito.<Clazz>any(), Mockito.<Method>any(), Mockito.<CodeAttribute>any());
    doNothing().when(codeAttributeEditor).reset(anyInt());
    OptimizedJsonWriterImplInitializer optimizedJsonWriterImplInitializer =
        new OptimizedJsonWriterImplInitializer(
            KotlinConstants.dummyClassPool,
            KotlinConstants.dummyClassPool,
            codeAttributeEditor,
            new OptimizedJsonInfo());
    ClassConstant classConstant = new ClassConstant();
    Constant[] constantPool = new Constant[] {classConstant};
    ProgramClass clazz = new ProgramClass(1, 1, constantPool, 1, 1, 1);
    LibraryMethod method = new LibraryMethod(1, "Name", "Descriptor");

    CodeAttribute codeAttribute = mock(CodeAttribute.class);
    doNothing().when(codeAttribute).addProcessingFlags((int[]) Mockito.any());
    codeAttribute.addProcessingFlags(2, 1, 2, 1);

    // Act
    optimizedJsonWriterImplInitializer.visitCodeAttribute(clazz, method, codeAttribute);

    // Assert
    verify(codeAttributeEditor).replaceInstruction(eq(0), isA(Instruction[].class));
    verify(codeAttributeEditor).reset(0);
    verify(codeAttributeEditor)
        .visitCodeAttribute(isA(Clazz.class), isA(Method.class), isA(CodeAttribute.class));
    verify(codeAttribute).addProcessingFlags((int[]) Mockito.any());
    Constant[] constantArray = clazz.constantPool;
    Constant constant = constantArray[0];
    assertTrue(constant instanceof ClassConstant);
    assertTrue(constantArray[1] instanceof IntegerConstant);
    assertEquals(17, constantArray.length);
    assertEquals(4, clazz.u2constantPoolCount);
    assertSame(classConstant, constant);
  }

  /**
   * Test {@link OptimizedJsonWriterImplInitializer#visitCodeAttribute(Clazz, Method,
   * CodeAttribute)}.
   *
   * <ul>
   *   <li>Then fourth element {@link ClassConstant#javaLangClassClass} {@link LibraryClass}.
   * </ul>
   *
   * <p>Method under test: {@link OptimizedJsonWriterImplInitializer#visitCodeAttribute(Clazz,
   * Method, CodeAttribute)}
   */
  @Test
  @DisplayName(
      "Test visitCodeAttribute(Clazz, Method, CodeAttribute); then fourth element javaLangClassClass LibraryClass")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void OptimizedJsonWriterImplInitializer.visitCodeAttribute(Clazz, Method, CodeAttribute)"
  })
  void testVisitCodeAttribute_thenFourthElementJavaLangClassClassLibraryClass()
      throws UnsupportedEncodingException {
    // Arrange
    ClassPool libraryClassPool = mock(ClassPool.class);
    LibraryClass libraryClass = new LibraryClass();
    when(libraryClassPool.getClass(Mockito.<String>any())).thenReturn(libraryClass);

    CodeAttributeEditor codeAttributeEditor = mock(CodeAttributeEditor.class);
    doNothing()
        .when(codeAttributeEditor)
        .replaceInstruction(anyInt(), Mockito.<Instruction[]>any());
    doNothing()
        .when(codeAttributeEditor)
        .visitCodeAttribute(
            Mockito.<Clazz>any(), Mockito.<Method>any(), Mockito.<CodeAttribute>any());
    doNothing().when(codeAttributeEditor).reset(anyInt());

    OptimizedJsonWriterImplInitializer optimizedJsonWriterImplInitializer =
        new OptimizedJsonWriterImplInitializer(
            KotlinConstants.dummyClassPool,
            libraryClassPool,
            codeAttributeEditor,
            new OptimizedJsonInfo());
    Constant[] constantPool = new Constant[] {new ClassConstant()};
    ProgramClass clazz = new ProgramClass(1, 1, constantPool, 1, 1, 1);
    LibraryMethod method = new LibraryMethod(1, "Name", "Descriptor");

    CodeAttribute codeAttribute = mock(CodeAttribute.class);
    doNothing().when(codeAttribute).addProcessingFlags((int[]) Mockito.any());
    codeAttribute.addProcessingFlags(2, 1, 2, 1);

    // Act
    optimizedJsonWriterImplInitializer.visitCodeAttribute(clazz, method, codeAttribute);

    // Assert
    verify(libraryClassPool, atLeast(1)).getClass(Mockito.<String>any());
    verify(codeAttributeEditor).replaceInstruction(eq(0), isA(Instruction[].class));
    verify(codeAttributeEditor).reset(0);
    verify(codeAttributeEditor)
        .visitCodeAttribute(isA(Clazz.class), isA(Method.class), isA(CodeAttribute.class));
    verify(codeAttribute).addProcessingFlags((int[]) Mockito.any());
    Constant[] constantArray = clazz.constantPool;
    Constant constant = constantArray[3];
    Clazz clazz2 = ((ClassConstant) constant).javaLangClassClass;
    assertTrue(clazz2 instanceof LibraryClass);
    assertTrue(constant instanceof ClassConstant);
    Constant constant2 = constantArray[2];
    assertTrue(constant2 instanceof Utf8Constant);
    assertEquals(17, constantArray.length);
    assertSame(libraryClass, clazz2);
    assertSame(libraryClass, ((ClassConstant) constant).referencedClass);
    assertArrayEquals("java/lang/String".getBytes("UTF-8"), ((Utf8Constant) constant2).getBytes());
  }

  /**
   * Test {@link OptimizedJsonWriterImplInitializer#visitCodeAttribute(Clazz, Method,
   * CodeAttribute)}.
   *
   * <ul>
   *   <li>Then third element {@link ClassConstant}.
   * </ul>
   *
   * <p>Method under test: {@link OptimizedJsonWriterImplInitializer#visitCodeAttribute(Clazz,
   * Method, CodeAttribute)}
   */
  @Test
  @DisplayName(
      "Test visitCodeAttribute(Clazz, Method, CodeAttribute); then third element ClassConstant")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void OptimizedJsonWriterImplInitializer.visitCodeAttribute(Clazz, Method, CodeAttribute)"
  })
  void testVisitCodeAttribute_thenThirdElementClassConstant() {
    // Arrange
    CodeAttributeEditor codeAttributeEditor = mock(CodeAttributeEditor.class);
    doNothing()
        .when(codeAttributeEditor)
        .replaceInstruction(anyInt(), Mockito.<Instruction[]>any());
    doNothing()
        .when(codeAttributeEditor)
        .visitCodeAttribute(
            Mockito.<Clazz>any(), Mockito.<Method>any(), Mockito.<CodeAttribute>any());
    doNothing().when(codeAttributeEditor).reset(anyInt());
    OptimizedJsonWriterImplInitializer optimizedJsonWriterImplInitializer =
        new OptimizedJsonWriterImplInitializer(
            KotlinConstants.dummyClassPool,
            KotlinConstants.dummyClassPool,
            codeAttributeEditor,
            new OptimizedJsonInfo());
    Constant[] constantPool = new Constant[] {new ClassConstant()};
    ProgramClass clazz = new ProgramClass(1, 0, constantPool, 1, 1, 1);
    LibraryMethod method = new LibraryMethod(1, "Name", "Descriptor");

    CodeAttribute codeAttribute = mock(CodeAttribute.class);
    doNothing().when(codeAttribute).addProcessingFlags((int[]) Mockito.any());
    codeAttribute.addProcessingFlags(2, 1, 2, 1);

    // Act
    optimizedJsonWriterImplInitializer.visitCodeAttribute(clazz, method, codeAttribute);

    // Assert
    verify(codeAttributeEditor).replaceInstruction(eq(0), isA(Instruction[].class));
    verify(codeAttributeEditor).reset(0);
    verify(codeAttributeEditor)
        .visitCodeAttribute(isA(Clazz.class), isA(Method.class), isA(CodeAttribute.class));
    verify(codeAttribute).addProcessingFlags((int[]) Mockito.any());
    Constant[] constantArray = clazz.constantPool;
    assertTrue(constantArray[2] instanceof ClassConstant);
    assertTrue(constantArray[0] instanceof IntegerConstant);
    assertTrue(constantArray[1] instanceof Utf8Constant);
    assertNull(constantArray[3]);
    assertEquals(17, constantArray.length);
    assertEquals(3, clazz.u2constantPoolCount);
  }
}
