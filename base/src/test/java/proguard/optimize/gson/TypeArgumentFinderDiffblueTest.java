package proguard.optimize.gson;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.anyInt;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import proguard.classfile.ClassPool;
import proguard.classfile.Clazz;
import proguard.classfile.LibraryClass;
import proguard.classfile.LibraryMethod;
import proguard.classfile.Method;
import proguard.classfile.attribute.CodeAttribute;
import proguard.classfile.constant.ClassConstant;
import proguard.classfile.constant.Constant;
import proguard.classfile.constant.FieldrefConstant;
import proguard.classfile.constant.RefConstant;
import proguard.classfile.instruction.BranchInstruction;
import proguard.classfile.instruction.Instruction;
import proguard.classfile.instruction.VariableInstruction;
import proguard.evaluation.PartialEvaluator;

class TypeArgumentFinderDiffblueTest {
  /**
   * Method under test:
   * {@link TypeArgumentFinder#visitVariableInstruction(Clazz, Method, CodeAttribute, int, VariableInstruction)}
   */
  @Test
  void testVisitVariableInstruction() {
    // Arrange
    ClassPool programClassPool = new ClassPool();
    ClassPool libraryClassPool = new ClassPool();
    TypeArgumentFinder typeArgumentFinder = new TypeArgumentFinder(programClassPool, libraryClassPool,
        new PartialEvaluator());
    LibraryClass clazz = new LibraryClass();
    LibraryMethod method = new LibraryMethod(1, "Name", "Descriptor");

    CodeAttribute codeAttribute = new CodeAttribute(1);
    VariableInstruction variableInstruction = mock(VariableInstruction.class);
    when(variableInstruction.canonicalOpcode()).thenReturn((byte) 'A');

    // Act
    typeArgumentFinder.visitVariableInstruction(clazz, method, codeAttribute, 2, variableInstruction);

    // Assert
    verify(variableInstruction).canonicalOpcode();
  }

  /**
   * Methods under test:
   * <ul>
   *   <li>
   * {@link TypeArgumentFinder#TypeArgumentFinder(ClassPool, ClassPool, PartialEvaluator)}
   *   <li>{@link TypeArgumentFinder#visitAnyConstant(Clazz, Constant)}
   *   <li>
   * {@link TypeArgumentFinder#visitAnyInstruction(Clazz, Method, CodeAttribute, int, Instruction)}
   * </ul>
   */
  @Test
  void testGettersAndSetters() {
    // Arrange
    ClassPool programClassPool = new ClassPool();
    ClassPool libraryClassPool = new ClassPool();

    // Act
    TypeArgumentFinder actualTypeArgumentFinder = new TypeArgumentFinder(programClassPool, libraryClassPool,
        new PartialEvaluator());
    LibraryClass clazz = new LibraryClass();
    actualTypeArgumentFinder.visitAnyConstant(clazz, new ClassConstant());
    LibraryClass clazz2 = new LibraryClass();
    LibraryMethod method = new LibraryMethod(1, "Name", "Descriptor");

    CodeAttribute codeAttribute = new CodeAttribute(1);
    actualTypeArgumentFinder.visitAnyInstruction(clazz2, method, codeAttribute, 2,
        new BranchInstruction((byte) 'A', 1));

    // Assert that nothing has changed
    assertNull(actualTypeArgumentFinder.typeArgumentClasses);
  }

  /**
   * Method under test:
   * {@link TypeArgumentFinder#visitAnyRefConstant(Clazz, RefConstant)}
   */
  @Test
  void testVisitAnyRefConstant() {
    // Arrange
    ClassPool programClassPool = new ClassPool();
    ClassPool libraryClassPool = new ClassPool();
    TypeArgumentFinder typeArgumentFinder = new TypeArgumentFinder(programClassPool, libraryClassPool,
        new PartialEvaluator());
    LibraryClass clazz = mock(LibraryClass.class);
    when(clazz.getClassName(anyInt())).thenReturn("Class Name");

    // Act
    typeArgumentFinder.visitAnyRefConstant(clazz, new FieldrefConstant());

    // Assert
    verify(clazz).getClassName(eq(0));
    assertArrayEquals(new String[]{"Class Name"}, typeArgumentFinder.typeArgumentClasses);
  }

  /**
   * Method under test:
   * {@link TypeArgumentFinder#visitAnyRefConstant(Clazz, RefConstant)}
   */
  @Test
  void testVisitAnyRefConstant2() {
    // Arrange
    ClassPool programClassPool = new ClassPool();
    ClassPool libraryClassPool = new ClassPool();
    TypeArgumentFinder typeArgumentFinder = new TypeArgumentFinder(programClassPool, libraryClassPool,
        new PartialEvaluator());
    LibraryClass clazz = mock(LibraryClass.class);
    FieldrefConstant refConstant = mock(FieldrefConstant.class);
    when(refConstant.getClassName(Mockito.<Clazz>any())).thenReturn("Class Name");

    // Act
    typeArgumentFinder.visitAnyRefConstant(clazz, refConstant);

    // Assert
    verify(refConstant).getClassName(isA(Clazz.class));
    assertArrayEquals(new String[]{"Class Name"}, typeArgumentFinder.typeArgumentClasses);
  }

  /**
   * Method under test:
   * {@link TypeArgumentFinder#visitClassConstant(Clazz, ClassConstant)}
   */
  @Test
  void testVisitClassConstant() {
    // Arrange
    ClassPool programClassPool = new ClassPool();
    ClassPool libraryClassPool = new ClassPool();
    TypeArgumentFinder typeArgumentFinder = new TypeArgumentFinder(programClassPool, libraryClassPool,
        new PartialEvaluator());
    LibraryClass clazz = mock(LibraryClass.class);
    when(clazz.getString(anyInt())).thenReturn("String");

    // Act
    typeArgumentFinder.visitClassConstant(clazz, new ClassConstant());

    // Assert
    verify(clazz).getString(eq(0));
    assertArrayEquals(new String[]{"String"}, typeArgumentFinder.typeArgumentClasses);
  }

  /**
   * Method under test:
   * {@link TypeArgumentFinder#visitClassConstant(Clazz, ClassConstant)}
   */
  @Test
  void testVisitClassConstant2() {
    // Arrange
    ClassPool programClassPool = new ClassPool();
    ClassPool libraryClassPool = new ClassPool();
    TypeArgumentFinder typeArgumentFinder = new TypeArgumentFinder(programClassPool, libraryClassPool,
        new PartialEvaluator());
    LibraryClass clazz = mock(LibraryClass.class);
    ClassConstant classConstant = mock(ClassConstant.class);
    when(classConstant.getName(Mockito.<Clazz>any())).thenReturn("Name");

    // Act
    typeArgumentFinder.visitClassConstant(clazz, classConstant);

    // Assert
    verify(classConstant).getName(isA(Clazz.class));
    assertArrayEquals(new String[]{"Name"}, typeArgumentFinder.typeArgumentClasses);
  }
}
