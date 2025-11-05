package proguard.obfuscate.kotlin;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import java.io.UnsupportedEncodingException;
import org.junit.jupiter.api.Test;
import proguard.classfile.ClassPool;
import proguard.classfile.constant.ClassConstant;
import proguard.classfile.constant.Constant;
import proguard.classfile.constant.MethodrefConstant;
import proguard.classfile.constant.NameAndTypeConstant;
import proguard.classfile.constant.Utf8Constant;
import proguard.classfile.instruction.BranchInstruction;
import proguard.classfile.instruction.ConstantInstruction;
import proguard.classfile.instruction.Instruction;
import proguard.classfile.instruction.SimpleInstruction;
import proguard.classfile.instruction.VariableInstruction;

class KotlinUnsupportedExceptionReplacementSequencesDiffblueTest {
  /**
   * Methods under test:
   * <ul>
   *   <li>{@link KotlinUnsupportedExceptionReplacementSequences#getConstants()}
   *   <li>{@link KotlinUnsupportedExceptionReplacementSequences#getSequences()}
   * </ul>
   */
  @Test
  void testGettersAndSetters() {
    // Arrange
    ClassPool programClassPool = new ClassPool();
    KotlinUnsupportedExceptionReplacementSequences kotlinUnsupportedExceptionReplacementSequences = new KotlinUnsupportedExceptionReplacementSequences(
        programClassPool, new ClassPool());

    // Act
    Constant[] actualConstants = kotlinUnsupportedExceptionReplacementSequences.getConstants();
    Instruction[][][] actualSequences = kotlinUnsupportedExceptionReplacementSequences.getSequences();

    // Assert
    Constant constant = actualConstants[2];
    assertTrue(constant instanceof ClassConstant);
    Constant constant2 = actualConstants[6];
    assertTrue(constant2 instanceof MethodrefConstant);
    Constant constant3 = actualConstants[9];
    assertTrue(constant3 instanceof MethodrefConstant);
    assertTrue(actualConstants[5] instanceof NameAndTypeConstant);
    assertTrue(actualConstants[8] instanceof NameAndTypeConstant);
    assertTrue(actualConstants[1] instanceof Utf8Constant);
    assertTrue(actualConstants[3] instanceof Utf8Constant);
    assertTrue(actualConstants[4] instanceof Utf8Constant);
    assertTrue(actualConstants[7] instanceof Utf8Constant);
    Instruction[][] instructionArray = actualSequences[0];
    Instruction[] instructionArray2 = instructionArray[0];
    Instruction instruction = instructionArray2[1];
    assertTrue(instruction instanceof BranchInstruction);
    Instruction[] instructionArray3 = instructionArray[1];
    assertTrue(instructionArray3[1] instanceof BranchInstruction);
    Instruction instruction2 = instructionArray2[2];
    assertTrue(instruction2 instanceof ConstantInstruction);
    Instruction instruction3 = instructionArray2[4];
    assertTrue(instruction3 instanceof ConstantInstruction);
    Instruction instruction4 = instructionArray2[5];
    assertTrue(instruction4 instanceof ConstantInstruction);
    assertTrue(instructionArray3[2] instanceof ConstantInstruction);
    Instruction instruction5 = instructionArray3[4];
    assertTrue(instruction5 instanceof ConstantInstruction);
    Instruction instruction6 = instructionArray2[3];
    assertTrue(instruction6 instanceof SimpleInstruction);
    assertTrue(instructionArray3[3] instanceof SimpleInstruction);
    Instruction instruction7 = instructionArray2[0];
    assertTrue(instruction7 instanceof VariableInstruction);
    assertTrue(instructionArray3[0] instanceof VariableInstruction);
    assertNull(((ClassConstant) constant).javaLangClassClass);
    assertNull(((ClassConstant) constant).referencedClass);
    assertNull(((MethodrefConstant) constant2).referencedClass);
    assertNull(((MethodrefConstant) constant3).referencedClass);
    assertNull(((MethodrefConstant) constant2).referencedMethod);
    assertNull(((MethodrefConstant) constant3).referencedMethod);
    assertNull(actualConstants[0]);
    assertEquals((byte) -58, ((BranchInstruction) instruction).opcode);
    assertEquals((byte) -69, ((ConstantInstruction) instruction2).opcode);
    assertEquals((byte) -73, ((ConstantInstruction) instruction4).opcode);
    assertEquals((byte) -73, ((ConstantInstruction) instruction5).opcode);
    assertEquals(0, ((ConstantInstruction) instruction2).constant);
    assertEquals(0, ((ConstantInstruction) instruction3).constant);
    assertEquals(0, ((ConstantInstruction) instruction4).constant);
    assertEquals(0, ((ConstantInstruction) instruction5).constant);
    assertEquals(0, ((SimpleInstruction) instruction6).constant);
    assertEquals(0, ((VariableInstruction) instruction7).constant);
    assertEquals(1, actualSequences.length);
    assertEquals(1, ((ClassConstant) constant).u2nameIndex);
    assertEquals(10, actualConstants.length);
    assertEquals(1073741824, ((ConstantInstruction) instruction3).constantIndex);
    assertEquals(1073741825, ((BranchInstruction) instruction).branchOffset);
    assertEquals(1073741827, ((VariableInstruction) instruction7).variableIndex);
    assertEquals((byte) 18, ((ConstantInstruction) instruction3).opcode);
    assertEquals(2, instructionArray.length);
    assertEquals(2, ((ConstantInstruction) instruction2).constantIndex);
    assertEquals((byte) 25, ((VariableInstruction) instruction7).opcode);
    assertEquals(5, instructionArray3.length);
    assertEquals(6, instructionArray2.length);
    assertEquals(6, ((ConstantInstruction) instruction4).constantIndex);
    assertEquals(9, ((ConstantInstruction) instruction5).constantIndex);
    assertEquals('Y', ((SimpleInstruction) instruction6).opcode);
  }

  /**
   * Method under test:
   * {@link KotlinUnsupportedExceptionReplacementSequences#KotlinUnsupportedExceptionReplacementSequences(ClassPool, ClassPool)}
   */
  @Test
  void testNewKotlinUnsupportedExceptionReplacementSequences() throws UnsupportedEncodingException {
    // Arrange
    ClassPool programClassPool = new ClassPool();

    // Act
    KotlinUnsupportedExceptionReplacementSequences actualKotlinUnsupportedExceptionReplacementSequences = new KotlinUnsupportedExceptionReplacementSequences(
        programClassPool, new ClassPool());

    // Assert
    Constant[] constants = actualKotlinUnsupportedExceptionReplacementSequences.getConstants();
    Constant constant = constants[2];
    assertTrue(constant instanceof ClassConstant);
    Constant constant2 = constants[6];
    assertTrue(constant2 instanceof MethodrefConstant);
    Constant constant3 = constants[9];
    assertTrue(constant3 instanceof MethodrefConstant);
    Constant constant4 = constants[5];
    assertTrue(constant4 instanceof NameAndTypeConstant);
    Constant constant5 = constants[8];
    assertTrue(constant5 instanceof NameAndTypeConstant);
    Constant constant6 = constants[1];
    assertTrue(constant6 instanceof Utf8Constant);
    Constant constant7 = constants[3];
    assertTrue(constant7 instanceof Utf8Constant);
    Constant constant8 = constants[4];
    assertTrue(constant8 instanceof Utf8Constant);
    Constant constant9 = constants[7];
    assertTrue(constant9 instanceof Utf8Constant);
    Instruction[][][] sequences = actualKotlinUnsupportedExceptionReplacementSequences.getSequences();
    Instruction[][] instructionArray = sequences[0];
    Instruction[] instructionArray2 = instructionArray[0];
    Instruction instruction = instructionArray2[1];
    assertTrue(instruction instanceof BranchInstruction);
    Instruction[] instructionArray3 = instructionArray[1];
    assertTrue(instructionArray3[1] instanceof BranchInstruction);
    Instruction instruction2 = instructionArray2[2];
    assertTrue(instruction2 instanceof ConstantInstruction);
    Instruction instruction3 = instructionArray2[4];
    assertTrue(instruction3 instanceof ConstantInstruction);
    Instruction instruction4 = instructionArray2[5];
    assertTrue(instruction4 instanceof ConstantInstruction);
    assertTrue(instructionArray3[2] instanceof ConstantInstruction);
    Instruction instruction5 = instructionArray3[4];
    assertTrue(instruction5 instanceof ConstantInstruction);
    Instruction instruction6 = instructionArray2[3];
    assertTrue(instruction6 instanceof SimpleInstruction);
    assertTrue(instructionArray3[3] instanceof SimpleInstruction);
    Instruction instruction7 = instructionArray2[0];
    assertTrue(instruction7 instanceof VariableInstruction);
    assertTrue(instructionArray3[0] instanceof VariableInstruction);
    assertEquals("()V", ((Utf8Constant) constant9).getString());
    assertEquals("(Ljava/lang/String;)V", ((Utf8Constant) constant8).getString());
    assertEquals("<init>", ((Utf8Constant) constant7).getString());
    assertEquals("aload", instruction7.getName());
    assertEquals("dup", instruction6.getName());
    assertEquals("ifnull", instruction.getName());
    assertEquals("invokespecial", instruction4.getName());
    assertEquals("invokespecial", instruction5.getName());
    assertEquals("java/lang/UnsupportedOperationException", ((Utf8Constant) constant6).getString());
    assertEquals("ldc", instruction3.getName());
    assertEquals("new", instruction2.getName());
    assertNull(constant6.getProcessingInfo());
    assertNull(constant.getProcessingInfo());
    assertNull(constant7.getProcessingInfo());
    assertNull(constant8.getProcessingInfo());
    assertNull(constant4.getProcessingInfo());
    assertNull(constant2.getProcessingInfo());
    assertNull(constant9.getProcessingInfo());
    assertNull(constant5.getProcessingInfo());
    assertNull(constant3.getProcessingInfo());
    assertNull(((ClassConstant) constant).javaLangClassClass);
    assertNull(((ClassConstant) constant).referencedClass);
    assertNull(((MethodrefConstant) constant2).referencedClass);
    assertNull(((MethodrefConstant) constant3).referencedClass);
    assertNull(((MethodrefConstant) constant2).referencedMethod);
    assertNull(((MethodrefConstant) constant3).referencedMethod);
    assertNull(constants[0]);
    assertEquals((byte) -58, ((BranchInstruction) instruction).opcode);
    assertEquals((byte) -69, ((ConstantInstruction) instruction2).opcode);
    assertEquals((byte) -73, ((ConstantInstruction) instruction4).opcode);
    assertEquals((byte) -73, ((ConstantInstruction) instruction5).opcode);
    assertEquals(0, constant6.getProcessingFlags());
    assertEquals(0, constant.getProcessingFlags());
    assertEquals(0, constant7.getProcessingFlags());
    assertEquals(0, constant8.getProcessingFlags());
    assertEquals(0, constant4.getProcessingFlags());
    assertEquals(0, constant2.getProcessingFlags());
    assertEquals(0, constant9.getProcessingFlags());
    assertEquals(0, constant5.getProcessingFlags());
    assertEquals(0, constant3.getProcessingFlags());
    assertEquals(0, ((ConstantInstruction) instruction2).constant);
    assertEquals(0, ((ConstantInstruction) instruction3).constant);
    assertEquals(0, ((ConstantInstruction) instruction4).constant);
    assertEquals(0, ((ConstantInstruction) instruction5).constant);
    assertEquals(0, ((SimpleInstruction) instruction6).constant);
    assertEquals(0, ((VariableInstruction) instruction7).constant);
    assertEquals(1, constant6.getTag());
    assertEquals(1, constant7.getTag());
    assertEquals(1, constant8.getTag());
    assertEquals(1, constant9.getTag());
    assertEquals(1, sequences.length);
    assertEquals(1, ((ClassConstant) constant).u2nameIndex);
    assertEquals(10, constant2.getTag());
    assertEquals(10, constant3.getTag());
    assertEquals(10, constants.length);
    assertEquals(1073741824, ((ConstantInstruction) instruction3).constantIndex);
    assertEquals(1073741825, ((BranchInstruction) instruction).branchOffset);
    assertEquals(1073741827, ((VariableInstruction) instruction7).variableIndex);
    assertEquals(12, constant4.getTag());
    assertEquals(12, constant5.getTag());
    assertEquals((byte) 18, ((ConstantInstruction) instruction3).opcode);
    assertEquals(2, ((MethodrefConstant) constant2).getClassIndex());
    assertEquals(2, ((MethodrefConstant) constant3).getClassIndex());
    assertEquals(2, instructionArray.length);
    assertEquals(2, ((ConstantInstruction) instruction2).constantIndex);
    assertEquals((byte) 25, ((VariableInstruction) instruction7).opcode);
    assertEquals(5, ((MethodrefConstant) constant2).getNameAndTypeIndex());
    assertEquals(5, instructionArray3.length);
    assertEquals(6, instructionArray2.length);
    assertEquals(6, ((ConstantInstruction) instruction4).constantIndex);
    assertEquals(7, constant.getTag());
    assertEquals(8, ((MethodrefConstant) constant3).getNameAndTypeIndex());
    assertEquals(9, ((ConstantInstruction) instruction5).constantIndex);
    assertFalse(constant6.isCategory2());
    assertFalse(constant.isCategory2());
    assertFalse(constant7.isCategory2());
    assertFalse(constant8.isCategory2());
    assertFalse(constant4.isCategory2());
    assertFalse(constant2.isCategory2());
    assertFalse(constant9.isCategory2());
    assertFalse(constant5.isCategory2());
    assertFalse(constant3.isCategory2());
    assertFalse(instruction7.isCategory2());
    assertFalse(instruction.isCategory2());
    assertFalse(instruction2.isCategory2());
    assertFalse(instruction6.isCategory2());
    assertFalse(instruction3.isCategory2());
    assertFalse(instruction4.isCategory2());
    assertFalse(instruction5.isCategory2());
    assertFalse(((VariableInstruction) instruction7).isStore());
    assertTrue(((VariableInstruction) instruction7).isLoad());
    assertEquals('Y', ((SimpleInstruction) instruction6).opcode);
    byte[] expectedBytes = "()V".getBytes("UTF-8");
    assertArrayEquals(expectedBytes, ((Utf8Constant) constant9).getBytes());
    byte[] expectedBytes2 = "(Ljava/lang/String;)V".getBytes("UTF-8");
    assertArrayEquals(expectedBytes2, ((Utf8Constant) constant8).getBytes());
    byte[] expectedBytes3 = "<init>".getBytes("UTF-8");
    assertArrayEquals(expectedBytes3, ((Utf8Constant) constant7).getBytes());
    byte[] expectedBytes4 = "java/lang/UnsupportedOperationException".getBytes("UTF-8");
    assertArrayEquals(expectedBytes4, ((Utf8Constant) constant6).getBytes());
  }

  /**
   * Method under test:
   * {@link KotlinUnsupportedExceptionReplacementSequences#KotlinUnsupportedExceptionReplacementSequences(ClassPool, ClassPool)}
   */
  @Test
  void testNewKotlinUnsupportedExceptionReplacementSequences2() throws UnsupportedEncodingException {
    // Arrange and Act
    KotlinUnsupportedExceptionReplacementSequences actualKotlinUnsupportedExceptionReplacementSequences = new KotlinUnsupportedExceptionReplacementSequences(
        null, new ClassPool());

    // Assert
    Constant[] constants = actualKotlinUnsupportedExceptionReplacementSequences.getConstants();
    Constant constant = constants[2];
    assertTrue(constant instanceof ClassConstant);
    Constant constant2 = constants[6];
    assertTrue(constant2 instanceof MethodrefConstant);
    Constant constant3 = constants[9];
    assertTrue(constant3 instanceof MethodrefConstant);
    Constant constant4 = constants[5];
    assertTrue(constant4 instanceof NameAndTypeConstant);
    Constant constant5 = constants[8];
    assertTrue(constant5 instanceof NameAndTypeConstant);
    Constant constant6 = constants[1];
    assertTrue(constant6 instanceof Utf8Constant);
    Constant constant7 = constants[3];
    assertTrue(constant7 instanceof Utf8Constant);
    Constant constant8 = constants[4];
    assertTrue(constant8 instanceof Utf8Constant);
    Constant constant9 = constants[7];
    assertTrue(constant9 instanceof Utf8Constant);
    Instruction[][][] sequences = actualKotlinUnsupportedExceptionReplacementSequences.getSequences();
    Instruction[][] instructionArray = sequences[0];
    Instruction[] instructionArray2 = instructionArray[0];
    Instruction instruction = instructionArray2[1];
    assertTrue(instruction instanceof BranchInstruction);
    Instruction[] instructionArray3 = instructionArray[1];
    assertTrue(instructionArray3[1] instanceof BranchInstruction);
    Instruction instruction2 = instructionArray2[2];
    assertTrue(instruction2 instanceof ConstantInstruction);
    Instruction instruction3 = instructionArray2[4];
    assertTrue(instruction3 instanceof ConstantInstruction);
    Instruction instruction4 = instructionArray2[5];
    assertTrue(instruction4 instanceof ConstantInstruction);
    assertTrue(instructionArray3[2] instanceof ConstantInstruction);
    Instruction instruction5 = instructionArray3[4];
    assertTrue(instruction5 instanceof ConstantInstruction);
    Instruction instruction6 = instructionArray2[3];
    assertTrue(instruction6 instanceof SimpleInstruction);
    assertTrue(instructionArray3[3] instanceof SimpleInstruction);
    Instruction instruction7 = instructionArray2[0];
    assertTrue(instruction7 instanceof VariableInstruction);
    assertTrue(instructionArray3[0] instanceof VariableInstruction);
    assertEquals("()V", ((Utf8Constant) constant9).getString());
    assertEquals("(Ljava/lang/String;)V", ((Utf8Constant) constant8).getString());
    assertEquals("<init>", ((Utf8Constant) constant7).getString());
    assertEquals("aload", instruction7.getName());
    assertEquals("dup", instruction6.getName());
    assertEquals("ifnull", instruction.getName());
    assertEquals("invokespecial", instruction4.getName());
    assertEquals("invokespecial", instruction5.getName());
    assertEquals("java/lang/UnsupportedOperationException", ((Utf8Constant) constant6).getString());
    assertEquals("ldc", instruction3.getName());
    assertEquals("new", instruction2.getName());
    assertNull(constant6.getProcessingInfo());
    assertNull(constant.getProcessingInfo());
    assertNull(constant7.getProcessingInfo());
    assertNull(constant8.getProcessingInfo());
    assertNull(constant4.getProcessingInfo());
    assertNull(constant2.getProcessingInfo());
    assertNull(constant9.getProcessingInfo());
    assertNull(constant5.getProcessingInfo());
    assertNull(constant3.getProcessingInfo());
    assertNull(((ClassConstant) constant).javaLangClassClass);
    assertNull(((ClassConstant) constant).referencedClass);
    assertNull(((MethodrefConstant) constant2).referencedClass);
    assertNull(((MethodrefConstant) constant3).referencedClass);
    assertNull(((MethodrefConstant) constant2).referencedMethod);
    assertNull(((MethodrefConstant) constant3).referencedMethod);
    assertNull(constants[0]);
    assertEquals((byte) -58, ((BranchInstruction) instruction).opcode);
    assertEquals((byte) -69, ((ConstantInstruction) instruction2).opcode);
    assertEquals((byte) -73, ((ConstantInstruction) instruction4).opcode);
    assertEquals((byte) -73, ((ConstantInstruction) instruction5).opcode);
    assertEquals(0, constant6.getProcessingFlags());
    assertEquals(0, constant.getProcessingFlags());
    assertEquals(0, constant7.getProcessingFlags());
    assertEquals(0, constant8.getProcessingFlags());
    assertEquals(0, constant4.getProcessingFlags());
    assertEquals(0, constant2.getProcessingFlags());
    assertEquals(0, constant9.getProcessingFlags());
    assertEquals(0, constant5.getProcessingFlags());
    assertEquals(0, constant3.getProcessingFlags());
    assertEquals(0, ((ConstantInstruction) instruction2).constant);
    assertEquals(0, ((ConstantInstruction) instruction3).constant);
    assertEquals(0, ((ConstantInstruction) instruction4).constant);
    assertEquals(0, ((ConstantInstruction) instruction5).constant);
    assertEquals(0, ((SimpleInstruction) instruction6).constant);
    assertEquals(0, ((VariableInstruction) instruction7).constant);
    assertEquals(1, constant6.getTag());
    assertEquals(1, constant7.getTag());
    assertEquals(1, constant8.getTag());
    assertEquals(1, constant9.getTag());
    assertEquals(1, sequences.length);
    assertEquals(1, ((ClassConstant) constant).u2nameIndex);
    assertEquals(10, constant2.getTag());
    assertEquals(10, constant3.getTag());
    assertEquals(10, constants.length);
    assertEquals(1073741824, ((ConstantInstruction) instruction3).constantIndex);
    assertEquals(1073741825, ((BranchInstruction) instruction).branchOffset);
    assertEquals(1073741827, ((VariableInstruction) instruction7).variableIndex);
    assertEquals(12, constant4.getTag());
    assertEquals(12, constant5.getTag());
    assertEquals((byte) 18, ((ConstantInstruction) instruction3).opcode);
    assertEquals(2, ((MethodrefConstant) constant2).getClassIndex());
    assertEquals(2, ((MethodrefConstant) constant3).getClassIndex());
    assertEquals(2, instructionArray.length);
    assertEquals(2, ((ConstantInstruction) instruction2).constantIndex);
    assertEquals((byte) 25, ((VariableInstruction) instruction7).opcode);
    assertEquals(5, ((MethodrefConstant) constant2).getNameAndTypeIndex());
    assertEquals(5, instructionArray3.length);
    assertEquals(6, instructionArray2.length);
    assertEquals(6, ((ConstantInstruction) instruction4).constantIndex);
    assertEquals(7, constant.getTag());
    assertEquals(8, ((MethodrefConstant) constant3).getNameAndTypeIndex());
    assertEquals(9, ((ConstantInstruction) instruction5).constantIndex);
    assertFalse(constant6.isCategory2());
    assertFalse(constant.isCategory2());
    assertFalse(constant7.isCategory2());
    assertFalse(constant8.isCategory2());
    assertFalse(constant4.isCategory2());
    assertFalse(constant2.isCategory2());
    assertFalse(constant9.isCategory2());
    assertFalse(constant5.isCategory2());
    assertFalse(constant3.isCategory2());
    assertFalse(instruction7.isCategory2());
    assertFalse(instruction.isCategory2());
    assertFalse(instruction2.isCategory2());
    assertFalse(instruction6.isCategory2());
    assertFalse(instruction3.isCategory2());
    assertFalse(instruction4.isCategory2());
    assertFalse(instruction5.isCategory2());
    assertFalse(((VariableInstruction) instruction7).isStore());
    assertTrue(((VariableInstruction) instruction7).isLoad());
    assertEquals('Y', ((SimpleInstruction) instruction6).opcode);
    byte[] expectedBytes = "()V".getBytes("UTF-8");
    assertArrayEquals(expectedBytes, ((Utf8Constant) constant9).getBytes());
    byte[] expectedBytes2 = "(Ljava/lang/String;)V".getBytes("UTF-8");
    assertArrayEquals(expectedBytes2, ((Utf8Constant) constant8).getBytes());
    byte[] expectedBytes3 = "<init>".getBytes("UTF-8");
    assertArrayEquals(expectedBytes3, ((Utf8Constant) constant7).getBytes());
    byte[] expectedBytes4 = "java/lang/UnsupportedOperationException".getBytes("UTF-8");
    assertArrayEquals(expectedBytes4, ((Utf8Constant) constant6).getBytes());
  }
}
