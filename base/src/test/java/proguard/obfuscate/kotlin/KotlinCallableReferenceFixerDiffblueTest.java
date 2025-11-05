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
import proguard.classfile.constant.StringConstant;
import proguard.classfile.constant.Utf8Constant;
import proguard.classfile.instruction.ConstantInstruction;
import proguard.classfile.instruction.Instruction;
import proguard.classfile.instruction.SimpleInstruction;

class KotlinCallableReferenceFixerDiffblueTest {
  /**
   * Methods under test:
   * <ul>
   *   <li>
   * {@link KotlinCallableReferenceFixer.NameAndSignatureReplacementSequences#getConstants()}
   *   <li>
   * {@link KotlinCallableReferenceFixer.NameAndSignatureReplacementSequences#getSequences()}
   * </ul>
   */
  @Test
  void testNameAndSignatureReplacementSequencesGettersAndSetters() {
    // Arrange
    ClassPool programClassPool = new ClassPool();
    KotlinCallableReferenceFixer.NameAndSignatureReplacementSequences nameAndSignatureReplacementSequences = new KotlinCallableReferenceFixer.NameAndSignatureReplacementSequences(
        "Name", "Signature", programClassPool, new ClassPool());

    // Act
    Constant[] actualConstants = nameAndSignatureReplacementSequences.getConstants();
    Instruction[][][] actualSequences = nameAndSignatureReplacementSequences.getSequences();

    // Assert
    Constant constant = actualConstants[2];
    assertTrue(constant instanceof StringConstant);
    Constant constant2 = actualConstants[4];
    assertTrue(constant2 instanceof StringConstant);
    assertTrue(actualConstants[1] instanceof Utf8Constant);
    assertTrue(actualConstants[3] instanceof Utf8Constant);
    Instruction[][] instructionArray = actualSequences[0];
    Instruction[] instructionArray2 = instructionArray[0];
    Instruction instruction = instructionArray2[0];
    assertTrue(instruction instanceof ConstantInstruction);
    Instruction instruction2 = instructionArray2[1];
    assertTrue(instruction2 instanceof ConstantInstruction);
    Instruction instruction3 = instructionArray2[2];
    assertTrue(instruction3 instanceof ConstantInstruction);
    Instruction instruction4 = instructionArray2[3];
    assertTrue(instruction4 instanceof ConstantInstruction);
    Instruction instruction5 = instructionArray2[4];
    assertTrue(instruction5 instanceof ConstantInstruction);
    Instruction[] instructionArray3 = instructionArray[1];
    assertTrue(instructionArray3[0] instanceof ConstantInstruction);
    Instruction instruction6 = instructionArray3[1];
    assertTrue(instruction6 instanceof ConstantInstruction);
    Instruction instruction7 = instructionArray3[2];
    assertTrue(instruction7 instanceof ConstantInstruction);
    assertTrue(instructionArray3[3] instanceof ConstantInstruction);
    assertTrue(instructionArray3[4] instanceof ConstantInstruction);
    Instruction[][] instructionArray4 = actualSequences[1];
    Instruction[] instructionArray5 = instructionArray4[0];
    assertTrue(instructionArray5[0] instanceof ConstantInstruction);
    assertTrue(instructionArray5[1] instanceof ConstantInstruction);
    assertTrue(instructionArray5[2] instanceof ConstantInstruction);
    assertTrue(instructionArray5[4] instanceof ConstantInstruction);
    Instruction[] instructionArray6 = instructionArray4[1];
    assertTrue(instructionArray6[0] instanceof ConstantInstruction);
    assertTrue(instructionArray6[1] instanceof ConstantInstruction);
    Instruction instruction8 = instructionArray6[2];
    assertTrue(instruction8 instanceof ConstantInstruction);
    assertTrue(instructionArray6[4] instanceof ConstantInstruction);
    Instruction instruction9 = instructionArray5[3];
    assertTrue(instruction9 instanceof SimpleInstruction);
    Instruction instruction10 = instructionArray6[3];
    assertTrue(instruction10 instanceof SimpleInstruction);
    assertNull(((StringConstant) constant).javaLangStringClass);
    assertNull(((StringConstant) constant2).javaLangStringClass);
    assertNull(((StringConstant) constant).referencedClass);
    assertNull(((StringConstant) constant2).referencedClass);
    assertNull(((StringConstant) constant).referencedMember);
    assertNull(((StringConstant) constant2).referencedMember);
    assertNull(actualConstants[0]);
    assertNull(((StringConstant) constant).referencedResourceFile);
    assertNull(((StringConstant) constant2).referencedResourceFile);
    assertEquals((byte) -73, ((ConstantInstruction) instruction5).opcode);
    assertEquals(0, ((StringConstant) constant).referencedResourceId);
    assertEquals(0, ((StringConstant) constant2).referencedResourceId);
    assertEquals(0, ((ConstantInstruction) instruction).constant);
    assertEquals(0, ((ConstantInstruction) instruction2).constant);
    assertEquals(0, ((ConstantInstruction) instruction3).constant);
    assertEquals(0, ((ConstantInstruction) instruction4).constant);
    assertEquals(0, ((ConstantInstruction) instruction5).constant);
    assertEquals(0, ((ConstantInstruction) instruction6).constant);
    assertEquals(0, ((ConstantInstruction) instruction7).constant);
    assertEquals(1, ((StringConstant) constant).u2stringIndex);
    assertEquals(1073741824, ((ConstantInstruction) instruction5).constantIndex);
    assertEquals(1073741827, ((ConstantInstruction) instruction).constantIndex);
    assertEquals(1073741828, ((ConstantInstruction) instruction2).constantIndex);
    assertEquals(1073741829, ((ConstantInstruction) instruction3).constantIndex);
    assertEquals(1073741830, ((ConstantInstruction) instruction4).constantIndex);
    assertEquals(1073741835, ((SimpleInstruction) instruction9).constant);
    assertEquals((byte) 18, ((ConstantInstruction) instruction).opcode);
    assertEquals((byte) 18, ((ConstantInstruction) instruction2).opcode);
    assertEquals((byte) 18, ((ConstantInstruction) instruction3).opcode);
    assertEquals((byte) 18, ((ConstantInstruction) instruction4).opcode);
    assertEquals((byte) 18, ((ConstantInstruction) instruction6).opcode);
    assertEquals((byte) 18, ((ConstantInstruction) instruction7).opcode);
    assertEquals(2, actualSequences.length);
    assertEquals(2, instructionArray.length);
    assertEquals(2, instructionArray4.length);
    assertEquals(2, ((ConstantInstruction) instruction6).constantIndex);
    assertEquals(3, ((StringConstant) constant2).u2stringIndex);
    assertEquals((byte) 3, ((SimpleInstruction) instruction9).opcode);
    assertEquals(4, ((ConstantInstruction) instruction7).constantIndex);
    assertEquals(5, actualConstants.length);
    assertEquals(5, instructionArray2.length);
    assertEquals(5, instructionArray3.length);
    assertEquals(5, instructionArray5.length);
    assertEquals(5, instructionArray6.length);
    assertEquals(instruction7, instruction8);
    assertEquals(instruction9, instruction10);
  }

  /**
   * Method under test:
   * {@link KotlinCallableReferenceFixer.NameAndSignatureReplacementSequences#NameAndSignatureReplacementSequences(String, String, ClassPool, ClassPool)}
   */
  @Test
  void testNameAndSignatureReplacementSequencesNewNameAndSignatureReplacementSequences()
      throws UnsupportedEncodingException {
    // Arrange
    ClassPool programClassPool = new ClassPool();

    // Act
    KotlinCallableReferenceFixer.NameAndSignatureReplacementSequences actualNameAndSignatureReplacementSequences = new KotlinCallableReferenceFixer.NameAndSignatureReplacementSequences(
        "Name", "Signature", programClassPool, new ClassPool());

    // Assert
    Constant[] constants = actualNameAndSignatureReplacementSequences.getConstants();
    Constant constant = constants[2];
    assertTrue(constant instanceof StringConstant);
    Constant constant2 = constants[4];
    assertTrue(constant2 instanceof StringConstant);
    Constant constant3 = constants[1];
    assertTrue(constant3 instanceof Utf8Constant);
    Constant constant4 = constants[3];
    assertTrue(constant4 instanceof Utf8Constant);
    Instruction[][][] sequences = actualNameAndSignatureReplacementSequences.getSequences();
    Instruction[][] instructionArray = sequences[0];
    Instruction[] instructionArray2 = instructionArray[0];
    Instruction instruction = instructionArray2[0];
    assertTrue(instruction instanceof ConstantInstruction);
    Instruction instruction2 = instructionArray2[1];
    assertTrue(instruction2 instanceof ConstantInstruction);
    Instruction instruction3 = instructionArray2[2];
    assertTrue(instruction3 instanceof ConstantInstruction);
    Instruction instruction4 = instructionArray2[3];
    assertTrue(instruction4 instanceof ConstantInstruction);
    Instruction instruction5 = instructionArray2[4];
    assertTrue(instruction5 instanceof ConstantInstruction);
    Instruction[] instructionArray3 = instructionArray[1];
    assertTrue(instructionArray3[0] instanceof ConstantInstruction);
    Instruction instruction6 = instructionArray3[1];
    assertTrue(instruction6 instanceof ConstantInstruction);
    Instruction instruction7 = instructionArray3[2];
    assertTrue(instruction7 instanceof ConstantInstruction);
    assertTrue(instructionArray3[3] instanceof ConstantInstruction);
    assertTrue(instructionArray3[4] instanceof ConstantInstruction);
    Instruction[][] instructionArray4 = sequences[1];
    Instruction[] instructionArray5 = instructionArray4[0];
    assertTrue(instructionArray5[0] instanceof ConstantInstruction);
    assertTrue(instructionArray5[1] instanceof ConstantInstruction);
    assertTrue(instructionArray5[2] instanceof ConstantInstruction);
    assertTrue(instructionArray5[4] instanceof ConstantInstruction);
    Instruction[] instructionArray6 = instructionArray4[1];
    assertTrue(instructionArray6[0] instanceof ConstantInstruction);
    assertTrue(instructionArray6[1] instanceof ConstantInstruction);
    Instruction instruction8 = instructionArray6[2];
    assertTrue(instruction8 instanceof ConstantInstruction);
    assertTrue(instructionArray6[4] instanceof ConstantInstruction);
    Instruction instruction9 = instructionArray5[3];
    assertTrue(instruction9 instanceof SimpleInstruction);
    Instruction instruction10 = instructionArray6[3];
    assertTrue(instruction10 instanceof SimpleInstruction);
    assertEquals("Name", ((Utf8Constant) constant3).getString());
    assertEquals("Signature", ((Utf8Constant) constant4).getString());
    assertEquals("iconst_0", instruction9.getName());
    assertEquals("invokespecial", instruction5.getName());
    assertEquals("ldc", instruction.getName());
    assertEquals("ldc", instruction2.getName());
    assertEquals("ldc", instruction3.getName());
    assertEquals("ldc", instruction4.getName());
    assertEquals("ldc", instruction6.getName());
    assertEquals("ldc", instruction7.getName());
    assertNull(constant3.getProcessingInfo());
    assertNull(constant.getProcessingInfo());
    assertNull(constant4.getProcessingInfo());
    assertNull(constant2.getProcessingInfo());
    assertNull(((StringConstant) constant).javaLangStringClass);
    assertNull(((StringConstant) constant2).javaLangStringClass);
    assertNull(((StringConstant) constant).referencedClass);
    assertNull(((StringConstant) constant2).referencedClass);
    assertNull(((StringConstant) constant).referencedMember);
    assertNull(((StringConstant) constant2).referencedMember);
    assertNull(constants[0]);
    assertNull(((StringConstant) constant).referencedResourceFile);
    assertNull(((StringConstant) constant2).referencedResourceFile);
    assertEquals((byte) -73, ((ConstantInstruction) instruction5).opcode);
    assertEquals(0, constant3.getProcessingFlags());
    assertEquals(0, constant.getProcessingFlags());
    assertEquals(0, constant4.getProcessingFlags());
    assertEquals(0, constant2.getProcessingFlags());
    assertEquals(0, ((StringConstant) constant).referencedResourceId);
    assertEquals(0, ((StringConstant) constant2).referencedResourceId);
    assertEquals(0, ((ConstantInstruction) instruction).constant);
    assertEquals(0, ((ConstantInstruction) instruction2).constant);
    assertEquals(0, ((ConstantInstruction) instruction3).constant);
    assertEquals(0, ((ConstantInstruction) instruction4).constant);
    assertEquals(0, ((ConstantInstruction) instruction5).constant);
    assertEquals(0, ((ConstantInstruction) instruction6).constant);
    assertEquals(0, ((ConstantInstruction) instruction7).constant);
    assertEquals(1, constant3.getTag());
    assertEquals(1, constant4.getTag());
    assertEquals(1, ((StringConstant) constant).u2stringIndex);
    assertEquals(1073741824, ((ConstantInstruction) instruction5).constantIndex);
    assertEquals(1073741827, ((ConstantInstruction) instruction).constantIndex);
    assertEquals(1073741828, ((ConstantInstruction) instruction2).constantIndex);
    assertEquals(1073741829, ((ConstantInstruction) instruction3).constantIndex);
    assertEquals(1073741830, ((ConstantInstruction) instruction4).constantIndex);
    assertEquals(1073741835, ((SimpleInstruction) instruction9).constant);
    assertEquals((byte) 18, ((ConstantInstruction) instruction).opcode);
    assertEquals((byte) 18, ((ConstantInstruction) instruction2).opcode);
    assertEquals((byte) 18, ((ConstantInstruction) instruction3).opcode);
    assertEquals((byte) 18, ((ConstantInstruction) instruction4).opcode);
    assertEquals((byte) 18, ((ConstantInstruction) instruction6).opcode);
    assertEquals((byte) 18, ((ConstantInstruction) instruction7).opcode);
    assertEquals(2, sequences.length);
    assertEquals(2, instructionArray.length);
    assertEquals(2, instructionArray4.length);
    assertEquals(2, ((ConstantInstruction) instruction6).constantIndex);
    assertEquals(3, ((StringConstant) constant2).u2stringIndex);
    assertEquals((byte) 3, ((SimpleInstruction) instruction9).opcode);
    assertEquals(4, ((ConstantInstruction) instruction7).constantIndex);
    assertEquals(5, constants.length);
    assertEquals(5, instructionArray2.length);
    assertEquals(5, instructionArray3.length);
    assertEquals(5, instructionArray5.length);
    assertEquals(5, instructionArray6.length);
    assertEquals(8, constant.getTag());
    assertEquals(8, constant2.getTag());
    assertFalse(constant3.isCategory2());
    assertFalse(constant.isCategory2());
    assertFalse(constant4.isCategory2());
    assertFalse(constant2.isCategory2());
    assertFalse(instruction.isCategory2());
    assertFalse(instruction2.isCategory2());
    assertFalse(instruction3.isCategory2());
    assertFalse(instruction4.isCategory2());
    assertFalse(instruction5.isCategory2());
    assertFalse(instruction6.isCategory2());
    assertFalse(instruction7.isCategory2());
    assertFalse(instruction9.isCategory2());
    assertEquals(instruction7, instruction8);
    assertEquals(instruction9, instruction10);
    byte[] expectedBytes = "Name".getBytes("UTF-8");
    assertArrayEquals(expectedBytes, ((Utf8Constant) constant3).getBytes());
    byte[] expectedBytes2 = "Signature".getBytes("UTF-8");
    assertArrayEquals(expectedBytes2, ((Utf8Constant) constant4).getBytes());
  }

  /**
   * Method under test:
   * {@link KotlinCallableReferenceFixer.NameAndSignatureReplacementSequences#NameAndSignatureReplacementSequences(String, String, ClassPool, ClassPool)}
   */
  @Test
  void testNameAndSignatureReplacementSequencesNewNameAndSignatureReplacementSequences2()
      throws UnsupportedEncodingException {
    // Arrange and Act
    KotlinCallableReferenceFixer.NameAndSignatureReplacementSequences actualNameAndSignatureReplacementSequences = new KotlinCallableReferenceFixer.NameAndSignatureReplacementSequences(
        "Name", "Signature", null, new ClassPool());

    // Assert
    Constant[] constants = actualNameAndSignatureReplacementSequences.getConstants();
    Constant constant = constants[2];
    assertTrue(constant instanceof StringConstant);
    Constant constant2 = constants[4];
    assertTrue(constant2 instanceof StringConstant);
    Constant constant3 = constants[1];
    assertTrue(constant3 instanceof Utf8Constant);
    Constant constant4 = constants[3];
    assertTrue(constant4 instanceof Utf8Constant);
    Instruction[][][] sequences = actualNameAndSignatureReplacementSequences.getSequences();
    Instruction[][] instructionArray = sequences[0];
    Instruction[] instructionArray2 = instructionArray[0];
    Instruction instruction = instructionArray2[0];
    assertTrue(instruction instanceof ConstantInstruction);
    Instruction instruction2 = instructionArray2[1];
    assertTrue(instruction2 instanceof ConstantInstruction);
    Instruction instruction3 = instructionArray2[2];
    assertTrue(instruction3 instanceof ConstantInstruction);
    Instruction instruction4 = instructionArray2[3];
    assertTrue(instruction4 instanceof ConstantInstruction);
    Instruction instruction5 = instructionArray2[4];
    assertTrue(instruction5 instanceof ConstantInstruction);
    Instruction[] instructionArray3 = instructionArray[1];
    assertTrue(instructionArray3[0] instanceof ConstantInstruction);
    Instruction instruction6 = instructionArray3[1];
    assertTrue(instruction6 instanceof ConstantInstruction);
    Instruction instruction7 = instructionArray3[2];
    assertTrue(instruction7 instanceof ConstantInstruction);
    assertTrue(instructionArray3[3] instanceof ConstantInstruction);
    assertTrue(instructionArray3[4] instanceof ConstantInstruction);
    Instruction[][] instructionArray4 = sequences[1];
    Instruction[] instructionArray5 = instructionArray4[0];
    assertTrue(instructionArray5[0] instanceof ConstantInstruction);
    assertTrue(instructionArray5[1] instanceof ConstantInstruction);
    assertTrue(instructionArray5[2] instanceof ConstantInstruction);
    assertTrue(instructionArray5[4] instanceof ConstantInstruction);
    Instruction[] instructionArray6 = instructionArray4[1];
    assertTrue(instructionArray6[0] instanceof ConstantInstruction);
    assertTrue(instructionArray6[1] instanceof ConstantInstruction);
    Instruction instruction8 = instructionArray6[2];
    assertTrue(instruction8 instanceof ConstantInstruction);
    assertTrue(instructionArray6[4] instanceof ConstantInstruction);
    Instruction instruction9 = instructionArray5[3];
    assertTrue(instruction9 instanceof SimpleInstruction);
    Instruction instruction10 = instructionArray6[3];
    assertTrue(instruction10 instanceof SimpleInstruction);
    assertEquals("Name", ((Utf8Constant) constant3).getString());
    assertEquals("Signature", ((Utf8Constant) constant4).getString());
    assertEquals("iconst_0", instruction9.getName());
    assertEquals("invokespecial", instruction5.getName());
    assertEquals("ldc", instruction.getName());
    assertEquals("ldc", instruction2.getName());
    assertEquals("ldc", instruction3.getName());
    assertEquals("ldc", instruction4.getName());
    assertEquals("ldc", instruction6.getName());
    assertEquals("ldc", instruction7.getName());
    assertNull(constant3.getProcessingInfo());
    assertNull(constant.getProcessingInfo());
    assertNull(constant4.getProcessingInfo());
    assertNull(constant2.getProcessingInfo());
    assertNull(((StringConstant) constant).javaLangStringClass);
    assertNull(((StringConstant) constant2).javaLangStringClass);
    assertNull(((StringConstant) constant).referencedClass);
    assertNull(((StringConstant) constant2).referencedClass);
    assertNull(((StringConstant) constant).referencedMember);
    assertNull(((StringConstant) constant2).referencedMember);
    assertNull(constants[0]);
    assertNull(((StringConstant) constant).referencedResourceFile);
    assertNull(((StringConstant) constant2).referencedResourceFile);
    assertEquals((byte) -73, ((ConstantInstruction) instruction5).opcode);
    assertEquals(0, constant3.getProcessingFlags());
    assertEquals(0, constant.getProcessingFlags());
    assertEquals(0, constant4.getProcessingFlags());
    assertEquals(0, constant2.getProcessingFlags());
    assertEquals(0, ((StringConstant) constant).referencedResourceId);
    assertEquals(0, ((StringConstant) constant2).referencedResourceId);
    assertEquals(0, ((ConstantInstruction) instruction).constant);
    assertEquals(0, ((ConstantInstruction) instruction2).constant);
    assertEquals(0, ((ConstantInstruction) instruction3).constant);
    assertEquals(0, ((ConstantInstruction) instruction4).constant);
    assertEquals(0, ((ConstantInstruction) instruction5).constant);
    assertEquals(0, ((ConstantInstruction) instruction6).constant);
    assertEquals(0, ((ConstantInstruction) instruction7).constant);
    assertEquals(1, constant3.getTag());
    assertEquals(1, constant4.getTag());
    assertEquals(1, ((StringConstant) constant).u2stringIndex);
    assertEquals(1073741824, ((ConstantInstruction) instruction5).constantIndex);
    assertEquals(1073741827, ((ConstantInstruction) instruction).constantIndex);
    assertEquals(1073741828, ((ConstantInstruction) instruction2).constantIndex);
    assertEquals(1073741829, ((ConstantInstruction) instruction3).constantIndex);
    assertEquals(1073741830, ((ConstantInstruction) instruction4).constantIndex);
    assertEquals(1073741835, ((SimpleInstruction) instruction9).constant);
    assertEquals((byte) 18, ((ConstantInstruction) instruction).opcode);
    assertEquals((byte) 18, ((ConstantInstruction) instruction2).opcode);
    assertEquals((byte) 18, ((ConstantInstruction) instruction3).opcode);
    assertEquals((byte) 18, ((ConstantInstruction) instruction4).opcode);
    assertEquals((byte) 18, ((ConstantInstruction) instruction6).opcode);
    assertEquals((byte) 18, ((ConstantInstruction) instruction7).opcode);
    assertEquals(2, sequences.length);
    assertEquals(2, instructionArray.length);
    assertEquals(2, instructionArray4.length);
    assertEquals(2, ((ConstantInstruction) instruction6).constantIndex);
    assertEquals(3, ((StringConstant) constant2).u2stringIndex);
    assertEquals((byte) 3, ((SimpleInstruction) instruction9).opcode);
    assertEquals(4, ((ConstantInstruction) instruction7).constantIndex);
    assertEquals(5, constants.length);
    assertEquals(5, instructionArray2.length);
    assertEquals(5, instructionArray3.length);
    assertEquals(5, instructionArray5.length);
    assertEquals(5, instructionArray6.length);
    assertEquals(8, constant.getTag());
    assertEquals(8, constant2.getTag());
    assertFalse(constant3.isCategory2());
    assertFalse(constant.isCategory2());
    assertFalse(constant4.isCategory2());
    assertFalse(constant2.isCategory2());
    assertFalse(instruction.isCategory2());
    assertFalse(instruction2.isCategory2());
    assertFalse(instruction3.isCategory2());
    assertFalse(instruction4.isCategory2());
    assertFalse(instruction5.isCategory2());
    assertFalse(instruction6.isCategory2());
    assertFalse(instruction7.isCategory2());
    assertFalse(instruction9.isCategory2());
    assertEquals(instruction7, instruction8);
    assertEquals(instruction9, instruction10);
    byte[] expectedBytes = "Name".getBytes("UTF-8");
    assertArrayEquals(expectedBytes, ((Utf8Constant) constant3).getBytes());
    byte[] expectedBytes2 = "Signature".getBytes("UTF-8");
    assertArrayEquals(expectedBytes2, ((Utf8Constant) constant4).getBytes());
  }

  /**
   * Methods under test:
   * <ul>
   *   <li>
   * {@link KotlinCallableReferenceFixer.NameOrSignatureReplacementSequences#getConstants()}
   *   <li>
   * {@link KotlinCallableReferenceFixer.NameOrSignatureReplacementSequences#getSequences()}
   * </ul>
   */
  @Test
  void testNameOrSignatureReplacementSequencesGettersAndSetters() {
    // Arrange
    ClassPool programClassPool = new ClassPool();
    KotlinCallableReferenceFixer.NameOrSignatureReplacementSequences nameOrSignatureReplacementSequences = new KotlinCallableReferenceFixer.NameOrSignatureReplacementSequences(
        "Name", programClassPool, new ClassPool());

    // Act
    Constant[] actualConstants = nameOrSignatureReplacementSequences.getConstants();
    Instruction[][][] actualSequences = nameOrSignatureReplacementSequences.getSequences();

    // Assert
    Constant constant = actualConstants[2];
    assertTrue(constant instanceof StringConstant);
    assertTrue(actualConstants[1] instanceof Utf8Constant);
    Instruction[][] instructionArray = actualSequences[0];
    Instruction[] instructionArray2 = instructionArray[0];
    Instruction instruction = instructionArray2[0];
    assertTrue(instruction instanceof ConstantInstruction);
    Instruction[] instructionArray3 = instructionArray[1];
    Instruction instruction2 = instructionArray3[0];
    assertTrue(instruction2 instanceof ConstantInstruction);
    Instruction instruction3 = instructionArray2[1];
    assertTrue(instruction3 instanceof SimpleInstruction);
    assertTrue(instructionArray3[1] instanceof SimpleInstruction);
    assertNull(((StringConstant) constant).javaLangStringClass);
    assertNull(((StringConstant) constant).referencedClass);
    assertNull(((StringConstant) constant).referencedMember);
    assertNull(actualConstants[0]);
    assertNull(((StringConstant) constant).referencedResourceFile);
    assertEquals((byte) -80, ((SimpleInstruction) instruction3).opcode);
    assertEquals(0, ((StringConstant) constant).referencedResourceId);
    assertEquals(0, ((ConstantInstruction) instruction).constant);
    assertEquals(0, ((ConstantInstruction) instruction2).constant);
    assertEquals(0, ((SimpleInstruction) instruction3).constant);
    assertEquals(1, actualSequences.length);
    assertEquals(1, ((StringConstant) constant).u2stringIndex);
    assertEquals(1073741824, ((ConstantInstruction) instruction).constantIndex);
    assertEquals((byte) 18, ((ConstantInstruction) instruction).opcode);
    assertEquals((byte) 18, ((ConstantInstruction) instruction2).opcode);
    assertEquals(2, instructionArray2.length);
    assertEquals(2, instructionArray3.length);
    assertEquals(2, instructionArray.length);
    assertEquals(2, ((ConstantInstruction) instruction2).constantIndex);
    assertEquals(3, actualConstants.length);
  }

  /**
   * Method under test:
   * {@link KotlinCallableReferenceFixer.NameOrSignatureReplacementSequences#NameOrSignatureReplacementSequences(String, ClassPool, ClassPool)}
   */
  @Test
  void testNameOrSignatureReplacementSequencesNewNameOrSignatureReplacementSequences()
      throws UnsupportedEncodingException {
    // Arrange
    ClassPool programClassPool = new ClassPool();

    // Act
    KotlinCallableReferenceFixer.NameOrSignatureReplacementSequences actualNameOrSignatureReplacementSequences = new KotlinCallableReferenceFixer.NameOrSignatureReplacementSequences(
        "Name", programClassPool, new ClassPool());

    // Assert
    Constant[] constants = actualNameOrSignatureReplacementSequences.getConstants();
    Constant constant = constants[2];
    assertTrue(constant instanceof StringConstant);
    Constant constant2 = constants[1];
    assertTrue(constant2 instanceof Utf8Constant);
    Instruction[][][] sequences = actualNameOrSignatureReplacementSequences.getSequences();
    Instruction[][] instructionArray = sequences[0];
    Instruction[] instructionArray2 = instructionArray[0];
    Instruction instruction = instructionArray2[0];
    assertTrue(instruction instanceof ConstantInstruction);
    Instruction[] instructionArray3 = instructionArray[1];
    Instruction instruction2 = instructionArray3[0];
    assertTrue(instruction2 instanceof ConstantInstruction);
    Instruction instruction3 = instructionArray2[1];
    assertTrue(instruction3 instanceof SimpleInstruction);
    assertTrue(instructionArray3[1] instanceof SimpleInstruction);
    assertEquals("Name", ((Utf8Constant) constant2).getString());
    assertEquals("areturn", instruction3.getName());
    assertEquals("ldc", instruction.getName());
    assertEquals("ldc", instruction2.getName());
    assertNull(constant2.getProcessingInfo());
    assertNull(constant.getProcessingInfo());
    assertNull(((StringConstant) constant).javaLangStringClass);
    assertNull(((StringConstant) constant).referencedClass);
    assertNull(((StringConstant) constant).referencedMember);
    assertNull(constants[0]);
    assertNull(((StringConstant) constant).referencedResourceFile);
    assertEquals((byte) -80, ((SimpleInstruction) instruction3).opcode);
    assertEquals(0, constant2.getProcessingFlags());
    assertEquals(0, constant.getProcessingFlags());
    assertEquals(0, ((StringConstant) constant).referencedResourceId);
    assertEquals(0, ((ConstantInstruction) instruction).constant);
    assertEquals(0, ((ConstantInstruction) instruction2).constant);
    assertEquals(0, ((SimpleInstruction) instruction3).constant);
    assertEquals(1, constant2.getTag());
    assertEquals(1, sequences.length);
    assertEquals(1, ((StringConstant) constant).u2stringIndex);
    assertEquals(1073741824, ((ConstantInstruction) instruction).constantIndex);
    assertEquals((byte) 18, ((ConstantInstruction) instruction).opcode);
    assertEquals((byte) 18, ((ConstantInstruction) instruction2).opcode);
    assertEquals(2, instructionArray2.length);
    assertEquals(2, instructionArray3.length);
    assertEquals(2, instructionArray.length);
    assertEquals(2, ((ConstantInstruction) instruction2).constantIndex);
    assertEquals(3, constants.length);
    assertEquals(8, constant.getTag());
    assertFalse(constant2.isCategory2());
    assertFalse(constant.isCategory2());
    assertFalse(instruction.isCategory2());
    assertFalse(instruction3.isCategory2());
    assertFalse(instruction2.isCategory2());
    byte[] expectedBytes = "Name".getBytes("UTF-8");
    assertArrayEquals(expectedBytes, ((Utf8Constant) constant2).getBytes());
  }

  /**
   * Method under test:
   * {@link KotlinCallableReferenceFixer.NameOrSignatureReplacementSequences#NameOrSignatureReplacementSequences(String, ClassPool, ClassPool)}
   */
  @Test
  void testNameOrSignatureReplacementSequencesNewNameOrSignatureReplacementSequences2()
      throws UnsupportedEncodingException {
    // Arrange and Act
    KotlinCallableReferenceFixer.NameOrSignatureReplacementSequences actualNameOrSignatureReplacementSequences = new KotlinCallableReferenceFixer.NameOrSignatureReplacementSequences(
        "Name", null, new ClassPool());

    // Assert
    Constant[] constants = actualNameOrSignatureReplacementSequences.getConstants();
    Constant constant = constants[2];
    assertTrue(constant instanceof StringConstant);
    Constant constant2 = constants[1];
    assertTrue(constant2 instanceof Utf8Constant);
    Instruction[][][] sequences = actualNameOrSignatureReplacementSequences.getSequences();
    Instruction[][] instructionArray = sequences[0];
    Instruction[] instructionArray2 = instructionArray[0];
    Instruction instruction = instructionArray2[0];
    assertTrue(instruction instanceof ConstantInstruction);
    Instruction[] instructionArray3 = instructionArray[1];
    Instruction instruction2 = instructionArray3[0];
    assertTrue(instruction2 instanceof ConstantInstruction);
    Instruction instruction3 = instructionArray2[1];
    assertTrue(instruction3 instanceof SimpleInstruction);
    assertTrue(instructionArray3[1] instanceof SimpleInstruction);
    assertEquals("Name", ((Utf8Constant) constant2).getString());
    assertEquals("areturn", instruction3.getName());
    assertEquals("ldc", instruction.getName());
    assertEquals("ldc", instruction2.getName());
    assertNull(constant2.getProcessingInfo());
    assertNull(constant.getProcessingInfo());
    assertNull(((StringConstant) constant).javaLangStringClass);
    assertNull(((StringConstant) constant).referencedClass);
    assertNull(((StringConstant) constant).referencedMember);
    assertNull(constants[0]);
    assertNull(((StringConstant) constant).referencedResourceFile);
    assertEquals((byte) -80, ((SimpleInstruction) instruction3).opcode);
    assertEquals(0, constant2.getProcessingFlags());
    assertEquals(0, constant.getProcessingFlags());
    assertEquals(0, ((StringConstant) constant).referencedResourceId);
    assertEquals(0, ((ConstantInstruction) instruction).constant);
    assertEquals(0, ((ConstantInstruction) instruction2).constant);
    assertEquals(0, ((SimpleInstruction) instruction3).constant);
    assertEquals(1, constant2.getTag());
    assertEquals(1, sequences.length);
    assertEquals(1, ((StringConstant) constant).u2stringIndex);
    assertEquals(1073741824, ((ConstantInstruction) instruction).constantIndex);
    assertEquals((byte) 18, ((ConstantInstruction) instruction).opcode);
    assertEquals((byte) 18, ((ConstantInstruction) instruction2).opcode);
    assertEquals(2, instructionArray2.length);
    assertEquals(2, instructionArray3.length);
    assertEquals(2, instructionArray.length);
    assertEquals(2, ((ConstantInstruction) instruction2).constantIndex);
    assertEquals(3, constants.length);
    assertEquals(8, constant.getTag());
    assertFalse(constant2.isCategory2());
    assertFalse(constant.isCategory2());
    assertFalse(instruction.isCategory2());
    assertFalse(instruction3.isCategory2());
    assertFalse(instruction2.isCategory2());
    byte[] expectedBytes = "Name".getBytes("UTF-8");
    assertArrayEquals(expectedBytes, ((Utf8Constant) constant2).getBytes());
  }

  /**
   * Methods under test:
   * <ul>
   *   <li>
   * {@link KotlinCallableReferenceFixer.OwnerReplacementSequences#getConstants()}
   *   <li>
   * {@link KotlinCallableReferenceFixer.OwnerReplacementSequences#getSequences()}
   * </ul>
   */
  @Test
  void testOwnerReplacementSequencesGettersAndSetters() {
    // Arrange
    ClassPool programClassPool = new ClassPool();
    KotlinCallableReferenceFixer.OwnerReplacementSequences ownerReplacementSequences = new KotlinCallableReferenceFixer.OwnerReplacementSequences(
        "Name", programClassPool, new ClassPool());

    // Act
    Constant[] actualConstants = ownerReplacementSequences.getConstants();
    Instruction[][][] actualSequences = ownerReplacementSequences.getSequences();

    // Assert
    Constant constant = actualConstants[5];
    assertTrue(constant instanceof ClassConstant);
    Constant constant2 = actualConstants[6];
    assertTrue(constant2 instanceof MethodrefConstant);
    assertTrue(actualConstants[3] instanceof NameAndTypeConstant);
    Constant constant3 = actualConstants[8];
    assertTrue(constant3 instanceof StringConstant);
    assertTrue(actualConstants[1] instanceof Utf8Constant);
    assertTrue(actualConstants[2] instanceof Utf8Constant);
    assertTrue(actualConstants[4] instanceof Utf8Constant);
    assertTrue(actualConstants[7] instanceof Utf8Constant);
    Instruction[][] instructionArray = actualSequences[0];
    Instruction[] instructionArray2 = instructionArray[0];
    Instruction instruction = instructionArray2[0];
    assertTrue(instruction instanceof ConstantInstruction);
    Instruction instruction2 = instructionArray2[1];
    assertTrue(instruction2 instanceof ConstantInstruction);
    Instruction instruction3 = instructionArray2[2];
    assertTrue(instruction3 instanceof ConstantInstruction);
    Instruction[] instructionArray3 = instructionArray[1];
    assertTrue(instructionArray3[0] instanceof ConstantInstruction);
    Instruction instruction4 = instructionArray3[1];
    assertTrue(instruction4 instanceof ConstantInstruction);
    assertTrue(instructionArray3[2] instanceof ConstantInstruction);
    assertNull(((ClassConstant) constant).javaLangClassClass);
    assertNull(((ClassConstant) constant).referencedClass);
    assertNull(((MethodrefConstant) constant2).referencedClass);
    assertNull(((StringConstant) constant3).javaLangStringClass);
    assertNull(((StringConstant) constant3).referencedClass);
    assertNull(((StringConstant) constant3).referencedMember);
    assertNull(((MethodrefConstant) constant2).referencedMethod);
    assertNull(actualConstants[0]);
    assertNull(((StringConstant) constant3).referencedResourceFile);
    assertEquals((byte) -72, ((ConstantInstruction) instruction3).opcode);
    assertEquals(0, ((StringConstant) constant3).referencedResourceId);
    assertEquals(0, ((ConstantInstruction) instruction).constant);
    assertEquals(0, ((ConstantInstruction) instruction2).constant);
    assertEquals(0, ((ConstantInstruction) instruction3).constant);
    assertEquals(0, ((ConstantInstruction) instruction4).constant);
    assertEquals(1, actualSequences.length);
    assertEquals(1073741824, ((ConstantInstruction) instruction).constantIndex);
    assertEquals(1073741825, ((ConstantInstruction) instruction2).constantIndex);
    assertEquals((byte) 18, ((ConstantInstruction) instruction).opcode);
    assertEquals((byte) 18, ((ConstantInstruction) instruction2).opcode);
    assertEquals((byte) 18, ((ConstantInstruction) instruction4).opcode);
    assertEquals(2, instructionArray.length);
    assertEquals(3, instructionArray2.length);
    assertEquals(3, instructionArray3.length);
    assertEquals(4, ((ClassConstant) constant).u2nameIndex);
    assertEquals(6, ((ConstantInstruction) instruction3).constantIndex);
    assertEquals(7, ((StringConstant) constant3).u2stringIndex);
    assertEquals(8, ((ConstantInstruction) instruction4).constantIndex);
    assertEquals(9, actualConstants.length);
  }

  /**
   * Method under test:
   * {@link KotlinCallableReferenceFixer.OwnerReplacementSequences#OwnerReplacementSequences(String, ClassPool, ClassPool)}
   */
  @Test
  void testOwnerReplacementSequencesNewOwnerReplacementSequences() throws UnsupportedEncodingException {
    // Arrange
    ClassPool programClassPool = new ClassPool();

    // Act
    KotlinCallableReferenceFixer.OwnerReplacementSequences actualOwnerReplacementSequences = new KotlinCallableReferenceFixer.OwnerReplacementSequences(
        "Name", programClassPool, new ClassPool());

    // Assert
    Constant[] constants = actualOwnerReplacementSequences.getConstants();
    Constant constant = constants[5];
    assertTrue(constant instanceof ClassConstant);
    Constant constant2 = constants[6];
    assertTrue(constant2 instanceof MethodrefConstant);
    Constant constant3 = constants[3];
    assertTrue(constant3 instanceof NameAndTypeConstant);
    Constant constant4 = constants[8];
    assertTrue(constant4 instanceof StringConstant);
    Constant constant5 = constants[1];
    assertTrue(constant5 instanceof Utf8Constant);
    Constant constant6 = constants[2];
    assertTrue(constant6 instanceof Utf8Constant);
    Constant constant7 = constants[4];
    assertTrue(constant7 instanceof Utf8Constant);
    Constant constant8 = constants[7];
    assertTrue(constant8 instanceof Utf8Constant);
    Instruction[][][] sequences = actualOwnerReplacementSequences.getSequences();
    Instruction[][] instructionArray = sequences[0];
    Instruction[] instructionArray2 = instructionArray[0];
    Instruction instruction = instructionArray2[0];
    assertTrue(instruction instanceof ConstantInstruction);
    Instruction instruction2 = instructionArray2[1];
    assertTrue(instruction2 instanceof ConstantInstruction);
    Instruction instruction3 = instructionArray2[2];
    assertTrue(instruction3 instanceof ConstantInstruction);
    Instruction[] instructionArray3 = instructionArray[1];
    assertTrue(instructionArray3[0] instanceof ConstantInstruction);
    Instruction instruction4 = instructionArray3[1];
    assertTrue(instruction4 instanceof ConstantInstruction);
    assertTrue(instructionArray3[2] instanceof ConstantInstruction);
    assertEquals("(Ljava/lang/Class;Ljava/lang/String;)Lkotlin/reflect/KDeclarationContainer;",
        ((Utf8Constant) constant6).getString());
    assertEquals("Name", ((Utf8Constant) constant8).getString());
    assertEquals("getOrCreateKotlinPackage", ((Utf8Constant) constant5).getString());
    assertEquals("invokestatic", instruction3.getName());
    assertEquals("kotlin/jvm/internal/Reflection", ((Utf8Constant) constant7).getString());
    assertEquals("ldc", instruction.getName());
    assertEquals("ldc", instruction2.getName());
    assertEquals("ldc", instruction4.getName());
    assertNull(constant5.getProcessingInfo());
    assertNull(constant6.getProcessingInfo());
    assertNull(constant3.getProcessingInfo());
    assertNull(constant7.getProcessingInfo());
    assertNull(constant.getProcessingInfo());
    assertNull(constant2.getProcessingInfo());
    assertNull(constant8.getProcessingInfo());
    assertNull(constant4.getProcessingInfo());
    assertNull(((ClassConstant) constant).javaLangClassClass);
    assertNull(((ClassConstant) constant).referencedClass);
    assertNull(((MethodrefConstant) constant2).referencedClass);
    assertNull(((StringConstant) constant4).javaLangStringClass);
    assertNull(((StringConstant) constant4).referencedClass);
    assertNull(((StringConstant) constant4).referencedMember);
    assertNull(((MethodrefConstant) constant2).referencedMethod);
    assertNull(constants[0]);
    assertNull(((StringConstant) constant4).referencedResourceFile);
    assertEquals((byte) -72, ((ConstantInstruction) instruction3).opcode);
    assertEquals(0, constant5.getProcessingFlags());
    assertEquals(0, constant6.getProcessingFlags());
    assertEquals(0, constant3.getProcessingFlags());
    assertEquals(0, constant7.getProcessingFlags());
    assertEquals(0, constant.getProcessingFlags());
    assertEquals(0, constant2.getProcessingFlags());
    assertEquals(0, constant8.getProcessingFlags());
    assertEquals(0, constant4.getProcessingFlags());
    assertEquals(0, ((StringConstant) constant4).referencedResourceId);
    assertEquals(0, ((ConstantInstruction) instruction).constant);
    assertEquals(0, ((ConstantInstruction) instruction2).constant);
    assertEquals(0, ((ConstantInstruction) instruction3).constant);
    assertEquals(0, ((ConstantInstruction) instruction4).constant);
    assertEquals(1, constant5.getTag());
    assertEquals(1, constant6.getTag());
    assertEquals(1, constant7.getTag());
    assertEquals(1, constant8.getTag());
    assertEquals(1, sequences.length);
    assertEquals(10, constant2.getTag());
    assertEquals(1073741824, ((ConstantInstruction) instruction).constantIndex);
    assertEquals(1073741825, ((ConstantInstruction) instruction2).constantIndex);
    assertEquals(12, constant3.getTag());
    assertEquals((byte) 18, ((ConstantInstruction) instruction).opcode);
    assertEquals((byte) 18, ((ConstantInstruction) instruction2).opcode);
    assertEquals((byte) 18, ((ConstantInstruction) instruction4).opcode);
    assertEquals(2, instructionArray.length);
    assertEquals(3, ((MethodrefConstant) constant2).getNameAndTypeIndex());
    assertEquals(3, instructionArray2.length);
    assertEquals(3, instructionArray3.length);
    assertEquals(4, ((ClassConstant) constant).u2nameIndex);
    assertEquals(5, ((MethodrefConstant) constant2).getClassIndex());
    assertEquals(6, ((ConstantInstruction) instruction3).constantIndex);
    assertEquals(7, constant.getTag());
    assertEquals(7, ((StringConstant) constant4).u2stringIndex);
    byte[] bytes = ((Utf8Constant) constant6).getBytes();
    assertEquals(75, bytes.length);
    assertEquals(8, constant4.getTag());
    assertEquals(8, ((ConstantInstruction) instruction4).constantIndex);
    assertEquals(9, constants.length);
    assertFalse(constant5.isCategory2());
    assertFalse(constant6.isCategory2());
    assertFalse(constant3.isCategory2());
    assertFalse(constant7.isCategory2());
    assertFalse(constant.isCategory2());
    assertFalse(constant2.isCategory2());
    assertFalse(constant8.isCategory2());
    assertFalse(constant4.isCategory2());
    assertFalse(instruction.isCategory2());
    assertFalse(instruction2.isCategory2());
    assertFalse(instruction3.isCategory2());
    assertFalse(instruction4.isCategory2());
    assertEquals('(', bytes[0]);
    assertEquals('/', bytes[11]);
    assertEquals('/', bytes[23]);
    assertEquals('/', bytes[52]);
    assertEquals('/', bytes[6]);
    assertEquals(';', bytes[17]);
    assertEquals(';', bytes[74]);
    assertEquals('C', bytes[12]);
    assertEquals('C', bytes[65]);
    assertEquals('D', bytes[54]);
    assertEquals('K', bytes[53]);
    assertEquals('L', bytes[1]);
    assertEquals('L', bytes[18]);
    assertEquals('a', bytes[14]);
    assertEquals('a', bytes[20]);
    assertEquals('a', bytes[22]);
    assertEquals('a', bytes[3]);
    assertEquals('a', bytes[5]);
    assertEquals('a', bytes[58]);
    assertEquals('a', bytes[60]);
    assertEquals('a', bytes[69]);
    assertEquals('a', bytes[8]);
    assertEquals('c', bytes[50]);
    assertEquals('c', bytes[56]);
    assertEquals('e', bytes[55]);
    assertEquals('e', bytes[72]);
    assertEquals('g', bytes[10]);
    assertEquals('i', bytes[62]);
    assertEquals('i', bytes[70]);
    assertEquals('j', bytes[19]);
    assertEquals('j', bytes[2]);
    assertEquals('l', bytes[13]);
    assertEquals('l', bytes[24]);
    assertEquals('l', bytes[57]);
    assertEquals('l', bytes[7]);
    assertEquals('n', bytes[67]);
    assertEquals('n', bytes[71]);
    assertEquals('n', bytes[9]);
    assertEquals('n', bytes[Double.SIZE]);
    assertEquals('o', bytes[63]);
    assertEquals('o', bytes[66]);
    assertEquals('r', bytes[59]);
    assertEquals('r', bytes[73]);
    assertEquals('s', bytes[15]);
    assertEquals('s', bytes[Short.SIZE]);
    assertEquals('t', bytes[51]);
    assertEquals('t', bytes[61]);
    assertEquals('t', bytes[68]);
    assertEquals('v', bytes[21]);
    assertEquals('v', bytes[4]);
    byte[] expectedBytes = "Name".getBytes("UTF-8");
    assertArrayEquals(expectedBytes, ((Utf8Constant) constant8).getBytes());
    byte[] expectedBytes2 = "getOrCreateKotlinPackage".getBytes("UTF-8");
    assertArrayEquals(expectedBytes2, ((Utf8Constant) constant5).getBytes());
    byte[] expectedBytes3 = "kotlin/jvm/internal/Reflection".getBytes("UTF-8");
    assertArrayEquals(expectedBytes3, ((Utf8Constant) constant7).getBytes());
  }

  /**
   * Method under test:
   * {@link KotlinCallableReferenceFixer.OwnerReplacementSequences#OwnerReplacementSequences(String, ClassPool, ClassPool)}
   */
  @Test
  void testOwnerReplacementSequencesNewOwnerReplacementSequences2() throws UnsupportedEncodingException {
    // Arrange and Act
    KotlinCallableReferenceFixer.OwnerReplacementSequences actualOwnerReplacementSequences = new KotlinCallableReferenceFixer.OwnerReplacementSequences(
        "Name", null, new ClassPool());

    // Assert
    Constant[] constants = actualOwnerReplacementSequences.getConstants();
    Constant constant = constants[5];
    assertTrue(constant instanceof ClassConstant);
    Constant constant2 = constants[6];
    assertTrue(constant2 instanceof MethodrefConstant);
    Constant constant3 = constants[3];
    assertTrue(constant3 instanceof NameAndTypeConstant);
    Constant constant4 = constants[8];
    assertTrue(constant4 instanceof StringConstant);
    Constant constant5 = constants[1];
    assertTrue(constant5 instanceof Utf8Constant);
    Constant constant6 = constants[2];
    assertTrue(constant6 instanceof Utf8Constant);
    Constant constant7 = constants[4];
    assertTrue(constant7 instanceof Utf8Constant);
    Constant constant8 = constants[7];
    assertTrue(constant8 instanceof Utf8Constant);
    Instruction[][][] sequences = actualOwnerReplacementSequences.getSequences();
    Instruction[][] instructionArray = sequences[0];
    Instruction[] instructionArray2 = instructionArray[0];
    Instruction instruction = instructionArray2[0];
    assertTrue(instruction instanceof ConstantInstruction);
    Instruction instruction2 = instructionArray2[1];
    assertTrue(instruction2 instanceof ConstantInstruction);
    Instruction instruction3 = instructionArray2[2];
    assertTrue(instruction3 instanceof ConstantInstruction);
    Instruction[] instructionArray3 = instructionArray[1];
    assertTrue(instructionArray3[0] instanceof ConstantInstruction);
    Instruction instruction4 = instructionArray3[1];
    assertTrue(instruction4 instanceof ConstantInstruction);
    assertTrue(instructionArray3[2] instanceof ConstantInstruction);
    assertEquals("(Ljava/lang/Class;Ljava/lang/String;)Lkotlin/reflect/KDeclarationContainer;",
        ((Utf8Constant) constant6).getString());
    assertEquals("Name", ((Utf8Constant) constant8).getString());
    assertEquals("getOrCreateKotlinPackage", ((Utf8Constant) constant5).getString());
    assertEquals("invokestatic", instruction3.getName());
    assertEquals("kotlin/jvm/internal/Reflection", ((Utf8Constant) constant7).getString());
    assertEquals("ldc", instruction.getName());
    assertEquals("ldc", instruction2.getName());
    assertEquals("ldc", instruction4.getName());
    assertNull(constant5.getProcessingInfo());
    assertNull(constant6.getProcessingInfo());
    assertNull(constant3.getProcessingInfo());
    assertNull(constant7.getProcessingInfo());
    assertNull(constant.getProcessingInfo());
    assertNull(constant2.getProcessingInfo());
    assertNull(constant8.getProcessingInfo());
    assertNull(constant4.getProcessingInfo());
    assertNull(((ClassConstant) constant).javaLangClassClass);
    assertNull(((ClassConstant) constant).referencedClass);
    assertNull(((MethodrefConstant) constant2).referencedClass);
    assertNull(((StringConstant) constant4).javaLangStringClass);
    assertNull(((StringConstant) constant4).referencedClass);
    assertNull(((StringConstant) constant4).referencedMember);
    assertNull(((MethodrefConstant) constant2).referencedMethod);
    assertNull(constants[0]);
    assertNull(((StringConstant) constant4).referencedResourceFile);
    assertEquals((byte) -72, ((ConstantInstruction) instruction3).opcode);
    assertEquals(0, constant5.getProcessingFlags());
    assertEquals(0, constant6.getProcessingFlags());
    assertEquals(0, constant3.getProcessingFlags());
    assertEquals(0, constant7.getProcessingFlags());
    assertEquals(0, constant.getProcessingFlags());
    assertEquals(0, constant2.getProcessingFlags());
    assertEquals(0, constant8.getProcessingFlags());
    assertEquals(0, constant4.getProcessingFlags());
    assertEquals(0, ((StringConstant) constant4).referencedResourceId);
    assertEquals(0, ((ConstantInstruction) instruction).constant);
    assertEquals(0, ((ConstantInstruction) instruction2).constant);
    assertEquals(0, ((ConstantInstruction) instruction3).constant);
    assertEquals(0, ((ConstantInstruction) instruction4).constant);
    assertEquals(1, constant5.getTag());
    assertEquals(1, constant6.getTag());
    assertEquals(1, constant7.getTag());
    assertEquals(1, constant8.getTag());
    assertEquals(1, sequences.length);
    assertEquals(10, constant2.getTag());
    assertEquals(1073741824, ((ConstantInstruction) instruction).constantIndex);
    assertEquals(1073741825, ((ConstantInstruction) instruction2).constantIndex);
    assertEquals(12, constant3.getTag());
    assertEquals((byte) 18, ((ConstantInstruction) instruction).opcode);
    assertEquals((byte) 18, ((ConstantInstruction) instruction2).opcode);
    assertEquals((byte) 18, ((ConstantInstruction) instruction4).opcode);
    assertEquals(2, instructionArray.length);
    assertEquals(3, ((MethodrefConstant) constant2).getNameAndTypeIndex());
    assertEquals(3, instructionArray2.length);
    assertEquals(3, instructionArray3.length);
    assertEquals(4, ((ClassConstant) constant).u2nameIndex);
    assertEquals(5, ((MethodrefConstant) constant2).getClassIndex());
    assertEquals(6, ((ConstantInstruction) instruction3).constantIndex);
    assertEquals(7, constant.getTag());
    assertEquals(7, ((StringConstant) constant4).u2stringIndex);
    byte[] bytes = ((Utf8Constant) constant6).getBytes();
    assertEquals(75, bytes.length);
    assertEquals(8, constant4.getTag());
    assertEquals(8, ((ConstantInstruction) instruction4).constantIndex);
    assertEquals(9, constants.length);
    assertFalse(constant5.isCategory2());
    assertFalse(constant6.isCategory2());
    assertFalse(constant3.isCategory2());
    assertFalse(constant7.isCategory2());
    assertFalse(constant.isCategory2());
    assertFalse(constant2.isCategory2());
    assertFalse(constant8.isCategory2());
    assertFalse(constant4.isCategory2());
    assertFalse(instruction.isCategory2());
    assertFalse(instruction2.isCategory2());
    assertFalse(instruction3.isCategory2());
    assertFalse(instruction4.isCategory2());
    assertEquals('(', bytes[0]);
    assertEquals('/', bytes[11]);
    assertEquals('/', bytes[23]);
    assertEquals('/', bytes[52]);
    assertEquals('/', bytes[6]);
    assertEquals(';', bytes[17]);
    assertEquals(';', bytes[74]);
    assertEquals('C', bytes[12]);
    assertEquals('C', bytes[65]);
    assertEquals('D', bytes[54]);
    assertEquals('K', bytes[53]);
    assertEquals('L', bytes[1]);
    assertEquals('L', bytes[18]);
    assertEquals('a', bytes[14]);
    assertEquals('a', bytes[20]);
    assertEquals('a', bytes[22]);
    assertEquals('a', bytes[3]);
    assertEquals('a', bytes[5]);
    assertEquals('a', bytes[58]);
    assertEquals('a', bytes[60]);
    assertEquals('a', bytes[69]);
    assertEquals('a', bytes[8]);
    assertEquals('c', bytes[50]);
    assertEquals('c', bytes[56]);
    assertEquals('e', bytes[55]);
    assertEquals('e', bytes[72]);
    assertEquals('g', bytes[10]);
    assertEquals('i', bytes[62]);
    assertEquals('i', bytes[70]);
    assertEquals('j', bytes[19]);
    assertEquals('j', bytes[2]);
    assertEquals('l', bytes[13]);
    assertEquals('l', bytes[24]);
    assertEquals('l', bytes[57]);
    assertEquals('l', bytes[7]);
    assertEquals('n', bytes[67]);
    assertEquals('n', bytes[71]);
    assertEquals('n', bytes[9]);
    assertEquals('n', bytes[Double.SIZE]);
    assertEquals('o', bytes[63]);
    assertEquals('o', bytes[66]);
    assertEquals('r', bytes[59]);
    assertEquals('r', bytes[73]);
    assertEquals('s', bytes[15]);
    assertEquals('s', bytes[Short.SIZE]);
    assertEquals('t', bytes[51]);
    assertEquals('t', bytes[61]);
    assertEquals('t', bytes[68]);
    assertEquals('v', bytes[21]);
    assertEquals('v', bytes[4]);
    byte[] expectedBytes = "Name".getBytes("UTF-8");
    assertArrayEquals(expectedBytes, ((Utf8Constant) constant8).getBytes());
    byte[] expectedBytes2 = "getOrCreateKotlinPackage".getBytes("UTF-8");
    assertArrayEquals(expectedBytes2, ((Utf8Constant) constant5).getBytes());
    byte[] expectedBytes3 = "kotlin/jvm/internal/Reflection".getBytes("UTF-8");
    assertArrayEquals(expectedBytes3, ((Utf8Constant) constant7).getBytes());
  }
}
