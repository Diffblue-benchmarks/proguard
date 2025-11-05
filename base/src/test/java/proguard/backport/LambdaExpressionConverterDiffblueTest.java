package proguard.backport;

import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.anyInt;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.doNothing;
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
import proguard.classfile.ProgramClass;
import proguard.classfile.ProgramMethod;
import proguard.classfile.attribute.CodeAttribute;
import proguard.classfile.constant.InvokeDynamicConstant;
import proguard.classfile.instruction.ConstantInstruction;
import proguard.classfile.instruction.visitor.InstructionVisitor;
import proguard.classfile.visitor.ClassVisitor;
import proguard.io.ExtraDataEntryNameMap;

class LambdaExpressionConverterDiffblueTest {
  /**
   * Method under test:
   * {@link LambdaExpressionConverter#visitProgramClass(ProgramClass)}
   */
  @Test
  void testVisitProgramClass() {
    // Arrange
    ClassPool programClassPool = new ClassPool();
    ClassPool libraryClassPool = new ClassPool();
    LambdaExpressionConverter lambdaExpressionConverter = new LambdaExpressionConverter(programClassPool,
        libraryClassPool, new ExtraDataEntryNameMap(), mock(ClassVisitor.class));
    ProgramClass programClass = mock(ProgramClass.class);
    doNothing().when(programClass).accept(Mockito.<ClassVisitor>any());

    // Act
    lambdaExpressionConverter.visitProgramClass(programClass);

    // Assert
    verify(programClass).accept(isA(ClassVisitor.class));
  }

  /**
   * Method under test:
   * {@link LambdaExpressionConverter#visitCodeAttribute(Clazz, Method, CodeAttribute)}
   */
  @Test
  void testVisitCodeAttribute() {
    // Arrange
    ClassPool programClassPool = new ClassPool();
    ClassPool libraryClassPool = new ClassPool();
    LambdaExpressionConverter lambdaExpressionConverter = new LambdaExpressionConverter(programClassPool,
        libraryClassPool, new ExtraDataEntryNameMap(), mock(ClassVisitor.class));
    LibraryClass clazz = new LibraryClass();
    LibraryMethod method = new LibraryMethod(1, "Name", "Descriptor");

    CodeAttribute codeAttribute = mock(CodeAttribute.class);
    doNothing().when(codeAttribute)
        .instructionsAccept(Mockito.<Clazz>any(), Mockito.<Method>any(), Mockito.<InstructionVisitor>any());

    // Act
    lambdaExpressionConverter.visitCodeAttribute(clazz, method, codeAttribute);

    // Assert
    verify(codeAttribute).instructionsAccept(isA(Clazz.class), isA(Method.class), isA(InstructionVisitor.class));
  }

  /**
   * Method under test:
   * {@link LambdaExpressionConverter#visitConstantInstruction(Clazz, Method, CodeAttribute, int, ConstantInstruction)}
   */
  @Test
  void testVisitConstantInstruction() {
    // Arrange
    ClassPool programClassPool = mock(ClassPool.class);
    doNothing().when(programClassPool).addClass(Mockito.<Clazz>any());
    programClassPool.addClass(new LibraryClass());
    ClassPool libraryClassPool = new ClassPool();
    LambdaExpressionConverter lambdaExpressionConverter = new LambdaExpressionConverter(programClassPool,
        libraryClassPool, new ExtraDataEntryNameMap(), mock(ClassVisitor.class));
    ProgramClass clazz = mock(ProgramClass.class);
    when(clazz.getConstant(anyInt())).thenReturn(new InvokeDynamicConstant());
    LibraryMethod method = new LibraryMethod(1, "Name", "Descriptor");

    CodeAttribute codeAttribute = new CodeAttribute(1);

    // Act
    lambdaExpressionConverter.visitConstantInstruction(clazz, method, codeAttribute, 2,
        new ConstantInstruction((byte) -70, 1));

    // Assert
    verify(programClassPool).addClass(isA(Clazz.class));
    verify(clazz).getConstant(eq(1));
  }

  /**
   * Method under test:
   * {@link LambdaExpressionConverter#visitConstantInstruction(Clazz, Method, CodeAttribute, int, ConstantInstruction)}
   */
  @Test
  void testVisitConstantInstruction2() {
    // Arrange
    ClassPool programClassPool = mock(ClassPool.class);
    doNothing().when(programClassPool).addClass(Mockito.<Clazz>any());
    programClassPool.addClass(new LibraryClass());
    ClassPool libraryClassPool = new ClassPool();
    LambdaExpressionConverter lambdaExpressionConverter = new LambdaExpressionConverter(programClassPool,
        libraryClassPool, new ExtraDataEntryNameMap(), mock(ClassVisitor.class));
    InvokeDynamicConstant invokeDynamicConstant = mock(InvokeDynamicConstant.class);
    when(invokeDynamicConstant.getBootstrapMethodAttributeIndex()).thenReturn(1);
    ProgramClass clazz = mock(ProgramClass.class);
    when(clazz.getConstant(anyInt())).thenReturn(invokeDynamicConstant);
    LibraryMethod method = new LibraryMethod(1, "Name", "Descriptor");

    CodeAttribute codeAttribute = new CodeAttribute(1);

    // Act
    lambdaExpressionConverter.visitConstantInstruction(clazz, method, codeAttribute, 2,
        new ConstantInstruction((byte) -70, 1));

    // Assert
    verify(programClassPool).addClass(isA(Clazz.class));
    verify(clazz).getConstant(eq(1));
    verify(invokeDynamicConstant).getBootstrapMethodAttributeIndex();
  }

  /**
   * Method under test:
   * {@link LambdaExpressionConverter#visitProgramMethod(ProgramClass, ProgramMethod)}
   */
  @Test
  void testVisitProgramMethod() {
    // Arrange
    ClassPool programClassPool = new ClassPool();
    ClassPool libraryClassPool = new ClassPool();
    LambdaExpressionConverter lambdaExpressionConverter = new LambdaExpressionConverter(programClassPool,
        libraryClassPool, new ExtraDataEntryNameMap(), mock(ClassVisitor.class));
    ProgramClass programClass = mock(ProgramClass.class);
    when(programClass.getString(anyInt())).thenReturn("String");

    // Act
    lambdaExpressionConverter.visitProgramMethod(programClass, new ProgramMethod());

    // Assert
    verify(programClass).getString(eq(0));
  }

  /**
   * Method under test:
   * {@link LambdaExpressionConverter#visitProgramMethod(ProgramClass, ProgramMethod)}
   */
  @Test
  void testVisitProgramMethod2() {
    // Arrange
    ClassPool programClassPool = new ClassPool();
    ClassPool libraryClassPool = new ClassPool();
    LambdaExpressionConverter lambdaExpressionConverter = new LambdaExpressionConverter(programClassPool,
        libraryClassPool, new ExtraDataEntryNameMap(), mock(ClassVisitor.class));
    ProgramClass programClass = mock(ProgramClass.class);
    when(programClass.getString(anyInt())).thenReturn("$deserializeLambda$");

    // Act
    lambdaExpressionConverter.visitProgramMethod(programClass, new ProgramMethod());

    // Assert
    verify(programClass, atLeast(1)).getString(eq(0));
  }
}
