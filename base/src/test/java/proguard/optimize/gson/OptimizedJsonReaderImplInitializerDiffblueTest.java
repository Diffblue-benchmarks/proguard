package proguard.optimize.gson;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
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
import proguard.classfile.constant.MethodrefConstant;
import proguard.classfile.constant.NameAndTypeConstant;
import proguard.classfile.constant.Utf8Constant;
import proguard.classfile.editor.CodeAttributeEditor;
import proguard.classfile.instruction.Instruction;

class OptimizedJsonReaderImplInitializerDiffblueTest {
  /**
   * Test {@link OptimizedJsonReaderImplInitializer#visitCodeAttribute(Clazz, Method,
   * CodeAttribute)}.
   *
   * <ul>
   *   <li>Then third element {@link ClassConstant}.
   * </ul>
   *
   * <p>Method under test: {@link OptimizedJsonReaderImplInitializer#visitCodeAttribute(Clazz,
   * Method, CodeAttribute)}
   */
  @Test
  @DisplayName(
      "Test visitCodeAttribute(Clazz, Method, CodeAttribute); then third element ClassConstant")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void OptimizedJsonReaderImplInitializer.visitCodeAttribute(Clazz, Method, CodeAttribute)"
  })
  void testVisitCodeAttribute_thenThirdElementClassConstant() {
    // Arrange
    ClassPool programClassPool = mock(ClassPool.class);
    when(programClassPool.getClass(Mockito.<String>any())).thenReturn(null);
    doNothing().when(programClassPool).addClass(Mockito.<Clazz>any());
    programClassPool.addClass(new LibraryClass());
    CodeAttributeEditor codeAttributeEditor = mock(CodeAttributeEditor.class);
    doNothing()
        .when(codeAttributeEditor)
        .replaceInstruction(anyInt(), Mockito.<Instruction[]>any());
    doNothing()
        .when(codeAttributeEditor)
        .visitCodeAttribute(
            Mockito.<Clazz>any(), Mockito.<Method>any(), Mockito.<CodeAttribute>any());
    doNothing().when(codeAttributeEditor).reset(anyInt());
    ClassPool libraryClassPool = new ClassPool();
    OptimizedJsonReaderImplInitializer optimizedJsonReaderImplInitializer =
        new OptimizedJsonReaderImplInitializer(
            programClassPool, libraryClassPool, codeAttributeEditor, new OptimizedJsonInfo());
    ProgramClass clazz = new ProgramClass(1, 1, new Constant[] {new ClassConstant()}, 1, 1, 1);

    LibraryMethod method = new LibraryMethod(1, "Name", "Descriptor");

    // Act
    optimizedJsonReaderImplInitializer.visitCodeAttribute(clazz, method, new CodeAttribute(1));

    // Assert
    verify(programClassPool).addClass(isA(Clazz.class));
    verify(programClassPool, atLeast(1)).getClass(Mockito.<String>any());
    verify(codeAttributeEditor).replaceInstruction(eq(0), isA(Instruction[].class));
    verify(codeAttributeEditor).reset(eq(0));
    verify(codeAttributeEditor)
        .visitCodeAttribute(isA(Clazz.class), isA(Method.class), isA(CodeAttribute.class));
    Constant[] constantArray = clazz.constantPool;
    assertTrue(constantArray[2] instanceof ClassConstant);
    assertTrue(constantArray[6] instanceof MethodrefConstant);
    assertTrue(constantArray[5] instanceof NameAndTypeConstant);
    assertTrue(constantArray[1] instanceof Utf8Constant);
    assertTrue(constantArray[3] instanceof Utf8Constant);
    assertTrue(constantArray[4] instanceof Utf8Constant);
    assertNull(constantArray[10]);
    assertNull(constantArray[11]);
    assertNull(constantArray[12]);
    assertNull(constantArray[13]);
    assertNull(constantArray[14]);
    assertNull(constantArray[15]);
    assertNull(constantArray[7]);
    assertNull(constantArray[8]);
    assertNull(constantArray[9]);
    assertNull(constantArray[Short.SIZE]);
    assertEquals(17, constantArray.length);
    assertEquals(7, clazz.u2constantPoolCount);
  }

  /**
   * Test {@link OptimizedJsonReaderImplInitializer#visitCodeAttribute(Clazz, Method,
   * CodeAttribute)}.
   *
   * <ul>
   *   <li>Then third element {@link ClassConstant}.
   * </ul>
   *
   * <p>Method under test: {@link OptimizedJsonReaderImplInitializer#visitCodeAttribute(Clazz,
   * Method, CodeAttribute)}
   */
  @Test
  @DisplayName(
      "Test visitCodeAttribute(Clazz, Method, CodeAttribute); then third element ClassConstant")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void OptimizedJsonReaderImplInitializer.visitCodeAttribute(Clazz, Method, CodeAttribute)"
  })
  void testVisitCodeAttribute_thenThirdElementClassConstant2() {
    // Arrange
    ClassPool programClassPool = mock(ClassPool.class);
    when(programClassPool.getClass(Mockito.<String>any())).thenReturn(null);
    doNothing().when(programClassPool).addClass(Mockito.<Clazz>any());
    programClassPool.addClass(new LibraryClass());
    ClassPool libraryClassPool = mock(ClassPool.class);
    when(libraryClassPool.getClass(Mockito.<String>any())).thenReturn(null);
    CodeAttributeEditor codeAttributeEditor = mock(CodeAttributeEditor.class);
    doNothing()
        .when(codeAttributeEditor)
        .replaceInstruction(anyInt(), Mockito.<Instruction[]>any());
    doNothing()
        .when(codeAttributeEditor)
        .visitCodeAttribute(
            Mockito.<Clazz>any(), Mockito.<Method>any(), Mockito.<CodeAttribute>any());
    doNothing().when(codeAttributeEditor).reset(anyInt());
    OptimizedJsonReaderImplInitializer optimizedJsonReaderImplInitializer =
        new OptimizedJsonReaderImplInitializer(
            programClassPool, libraryClassPool, codeAttributeEditor, new OptimizedJsonInfo());
    ProgramClass clazz = new ProgramClass(1, 1, new Constant[] {new ClassConstant()}, 1, 1, 1);

    LibraryMethod method = new LibraryMethod(1, "Name", "Descriptor");

    // Act
    optimizedJsonReaderImplInitializer.visitCodeAttribute(clazz, method, new CodeAttribute(1));

    // Assert
    verify(programClassPool).addClass(isA(Clazz.class));
    verify(programClassPool, atLeast(1)).getClass(Mockito.<String>any());
    verify(libraryClassPool, atLeast(1)).getClass(Mockito.<String>any());
    verify(codeAttributeEditor).replaceInstruction(eq(0), isA(Instruction[].class));
    verify(codeAttributeEditor).reset(eq(0));
    verify(codeAttributeEditor)
        .visitCodeAttribute(isA(Clazz.class), isA(Method.class), isA(CodeAttribute.class));
    Constant[] constantArray = clazz.constantPool;
    assertTrue(constantArray[2] instanceof ClassConstant);
    assertTrue(constantArray[6] instanceof MethodrefConstant);
    assertTrue(constantArray[5] instanceof NameAndTypeConstant);
    assertTrue(constantArray[1] instanceof Utf8Constant);
    assertTrue(constantArray[3] instanceof Utf8Constant);
    assertTrue(constantArray[4] instanceof Utf8Constant);
    assertNull(constantArray[10]);
    assertNull(constantArray[11]);
    assertNull(constantArray[12]);
    assertNull(constantArray[13]);
    assertNull(constantArray[14]);
    assertNull(constantArray[15]);
    assertNull(constantArray[7]);
    assertNull(constantArray[8]);
    assertNull(constantArray[9]);
    assertNull(constantArray[Short.SIZE]);
    assertEquals(17, constantArray.length);
    assertEquals(7, clazz.u2constantPoolCount);
  }
}
