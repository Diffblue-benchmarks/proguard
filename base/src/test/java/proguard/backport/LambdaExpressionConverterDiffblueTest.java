package proguard.backport;

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
   * Test {@link LambdaExpressionConverter#visitProgramClass(ProgramClass)}.
   * <ul>
   *   <li>When {@link ProgramClass} {@link ProgramClass#accept(ClassVisitor)} does nothing.</li>
   *   <li>Then calls {@link ProgramClass#accept(ClassVisitor)}.</li>
   * </ul>
   * <p>
   * Method under test: {@link LambdaExpressionConverter#visitProgramClass(ProgramClass)}
   */
  @Test
  @DisplayName("Test visitProgramClass(ProgramClass); when ProgramClass accept(ClassVisitor) does nothing; then calls accept(ClassVisitor)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void LambdaExpressionConverter.visitProgramClass(ProgramClass)"})
  void testVisitProgramClass_whenProgramClassAcceptDoesNothing_thenCallsAccept() {
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
   * Test {@link LambdaExpressionConverter#visitCodeAttribute(Clazz, Method, CodeAttribute)}.
   * <ul>
   *   <li>Then calls {@link CodeAttribute#instructionsAccept(Clazz, Method, InstructionVisitor)}.</li>
   * </ul>
   * <p>
   * Method under test: {@link LambdaExpressionConverter#visitCodeAttribute(Clazz, Method, CodeAttribute)}
   */
  @Test
  @DisplayName("Test visitCodeAttribute(Clazz, Method, CodeAttribute); then calls instructionsAccept(Clazz, Method, InstructionVisitor)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void LambdaExpressionConverter.visitCodeAttribute(Clazz, Method, CodeAttribute)"})
  void testVisitCodeAttribute_thenCallsInstructionsAccept() {
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
   * Test {@link LambdaExpressionConverter#visitConstantInstruction(Clazz, Method, CodeAttribute, int, ConstantInstruction)}.
   * <ul>
   *   <li>Given {@link InvokeDynamicConstant#InvokeDynamicConstant()}.</li>
   * </ul>
   * <p>
   * Method under test: {@link LambdaExpressionConverter#visitConstantInstruction(Clazz, Method, CodeAttribute, int, ConstantInstruction)}
   */
  @Test
  @DisplayName("Test visitConstantInstruction(Clazz, Method, CodeAttribute, int, ConstantInstruction); given InvokeDynamicConstant()")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "void LambdaExpressionConverter.visitConstantInstruction(Clazz, Method, CodeAttribute, int, ConstantInstruction)"})
  void testVisitConstantInstruction_givenInvokeDynamicConstant() {
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
   * Test {@link LambdaExpressionConverter#visitConstantInstruction(Clazz, Method, CodeAttribute, int, ConstantInstruction)}.
   * <ul>
   *   <li>Then calls {@link InvokeDynamicConstant#getBootstrapMethodAttributeIndex()}.</li>
   * </ul>
   * <p>
   * Method under test: {@link LambdaExpressionConverter#visitConstantInstruction(Clazz, Method, CodeAttribute, int, ConstantInstruction)}
   */
  @Test
  @DisplayName("Test visitConstantInstruction(Clazz, Method, CodeAttribute, int, ConstantInstruction); then calls getBootstrapMethodAttributeIndex()")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "void LambdaExpressionConverter.visitConstantInstruction(Clazz, Method, CodeAttribute, int, ConstantInstruction)"})
  void testVisitConstantInstruction_thenCallsGetBootstrapMethodAttributeIndex() {
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
   * Test {@link LambdaExpressionConverter#visitProgramMethod(ProgramClass, ProgramMethod)}.
   * <ul>
   *   <li>Given {@code $deserializeLambda$}.</li>
   * </ul>
   * <p>
   * Method under test: {@link LambdaExpressionConverter#visitProgramMethod(ProgramClass, ProgramMethod)}
   */
  @Test
  @DisplayName("Test visitProgramMethod(ProgramClass, ProgramMethod); given '$deserializeLambda$'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void LambdaExpressionConverter.visitProgramMethod(ProgramClass, ProgramMethod)"})
  void testVisitProgramMethod_givenDeserializeLambda() {
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

  /**
   * Test {@link LambdaExpressionConverter#visitProgramMethod(ProgramClass, ProgramMethod)}.
   * <ul>
   *   <li>Given {@code String}.</li>
   *   <li>When {@link ProgramClass} {@link ProgramClass#getString(int)} return {@code String}.</li>
   * </ul>
   * <p>
   * Method under test: {@link LambdaExpressionConverter#visitProgramMethod(ProgramClass, ProgramMethod)}
   */
  @Test
  @DisplayName("Test visitProgramMethod(ProgramClass, ProgramMethod); given 'String'; when ProgramClass getString(int) return 'String'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void LambdaExpressionConverter.visitProgramMethod(ProgramClass, ProgramMethod)"})
  void testVisitProgramMethod_givenString_whenProgramClassGetStringReturnString() {
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
}
