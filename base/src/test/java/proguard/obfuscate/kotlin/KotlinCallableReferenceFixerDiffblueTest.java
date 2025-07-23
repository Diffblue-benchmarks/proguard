package proguard.obfuscate.kotlin;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.io.UnsupportedEncodingException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import proguard.classfile.ClassPool;
import proguard.classfile.Clazz;
import proguard.classfile.LibraryClass;
import proguard.classfile.ProgramClass;
import proguard.classfile.constant.ClassConstant;
import proguard.classfile.constant.Constant;
import proguard.classfile.constant.MethodrefConstant;
import proguard.classfile.constant.NameAndTypeConstant;
import proguard.classfile.constant.StringConstant;
import proguard.classfile.constant.Utf8Constant;
import proguard.classfile.instruction.ConstantInstruction;
import proguard.classfile.instruction.Instruction;
import proguard.classfile.instruction.SimpleInstruction;
import proguard.obfuscate.kotlin.KotlinCallableReferenceFixer.NameAndSignatureReplacementSequences;
import proguard.obfuscate.kotlin.KotlinCallableReferenceFixer.NameOrSignatureReplacementSequences;
import proguard.obfuscate.kotlin.KotlinCallableReferenceFixer.OwnerReplacementSequences;

class KotlinCallableReferenceFixerDiffblueTest {
  /**
   * Test NameAndSignatureReplacementSequences getters and setters.
   *
   * <p>Methods under test:
   *
   * <ul>
   *   <li>{@link NameAndSignatureReplacementSequences#getConstants()}
   *   <li>{@link NameAndSignatureReplacementSequences#getSequences()}
   * </ul>
   */
  @Test
  @DisplayName("Test NameAndSignatureReplacementSequences getters and setters")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({
    "Constant[] NameAndSignatureReplacementSequences.getConstants()",
    "Instruction[][][] NameAndSignatureReplacementSequences.getSequences()"
  })
  void testNameAndSignatureReplacementSequencesGettersAndSetters() {
    // Arrange
    ClassPool programClassPool = new ClassPool();
    NameAndSignatureReplacementSequences nameAndSignatureReplacementSequences =
        new NameAndSignatureReplacementSequences(
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
   * Test NameAndSignatureReplacementSequences {@link
   * NameAndSignatureReplacementSequences#NameAndSignatureReplacementSequences(String, String,
   * ClassPool, ClassPool)}.
   *
   * <p>Method under test: {@link
   * NameAndSignatureReplacementSequences#NameAndSignatureReplacementSequences(String, String,
   * ClassPool, ClassPool)}
   */
  @Test
  @DisplayName(
      "Test NameAndSignatureReplacementSequences new NameAndSignatureReplacementSequences(String, String, ClassPool, ClassPool)")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void NameAndSignatureReplacementSequences.<init>(String, String, ClassPool, ClassPool)"
  })
  void testNameAndSignatureReplacementSequencesNewNameAndSignatureReplacementSequences()
      throws UnsupportedEncodingException {
    // Arrange
    ClassPool programClassPool = new ClassPool();

    // Act
    NameAndSignatureReplacementSequences actualNameAndSignatureReplacementSequences =
        new NameAndSignatureReplacementSequences(
            "Name", "Signature", programClassPool, new ClassPool());

    // Assert
    Constant[] constants = actualNameAndSignatureReplacementSequences.getConstants();
    Constant constant = constants[1];
    assertTrue(constant instanceof Utf8Constant);
    Constant constant2 = constants[3];
    assertTrue(constant2 instanceof Utf8Constant);
    Instruction[][][] sequences = actualNameAndSignatureReplacementSequences.getSequences();
    assertEquals(2, sequences.length);
    Instruction[][] instructionArray = sequences[0];
    assertEquals(2, instructionArray.length);
    Instruction[][] instructionArray2 = sequences[1];
    assertEquals(2, instructionArray2.length);
    assertEquals(5, constants.length);
    assertEquals(5, (instructionArray[1]).length);
    assertEquals(5, (instructionArray2[0]).length);
    assertEquals(5, (instructionArray2[1]).length);
    byte[] expectedBytes = "Name".getBytes("UTF-8");
    assertArrayEquals(expectedBytes, ((Utf8Constant) constant).getBytes());
    byte[] expectedBytes2 = "Signature".getBytes("UTF-8");
    assertArrayEquals(expectedBytes2, ((Utf8Constant) constant2).getBytes());
  }

  /**
   * Test NameAndSignatureReplacementSequences {@link
   * NameAndSignatureReplacementSequences#NameAndSignatureReplacementSequences(String, String,
   * ClassPool, ClassPool)}.
   *
   * <p>Method under test: {@link
   * NameAndSignatureReplacementSequences#NameAndSignatureReplacementSequences(String, String,
   * ClassPool, ClassPool)}
   */
  @Test
  @DisplayName(
      "Test NameAndSignatureReplacementSequences new NameAndSignatureReplacementSequences(String, String, ClassPool, ClassPool)")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void NameAndSignatureReplacementSequences.<init>(String, String, ClassPool, ClassPool)"
  })
  void testNameAndSignatureReplacementSequencesNewNameAndSignatureReplacementSequences2()
      throws UnsupportedEncodingException {
    // Arrange and Act
    NameAndSignatureReplacementSequences actualNameAndSignatureReplacementSequences =
        new NameAndSignatureReplacementSequences("Name", "Signature", null, new ClassPool());

    // Assert
    Constant[] constants = actualNameAndSignatureReplacementSequences.getConstants();
    Constant constant = constants[1];
    assertTrue(constant instanceof Utf8Constant);
    Constant constant2 = constants[3];
    assertTrue(constant2 instanceof Utf8Constant);
    Instruction[][][] sequences = actualNameAndSignatureReplacementSequences.getSequences();
    assertEquals(2, sequences.length);
    Instruction[][] instructionArray = sequences[0];
    assertEquals(2, instructionArray.length);
    Instruction[][] instructionArray2 = sequences[1];
    assertEquals(2, instructionArray2.length);
    assertEquals(5, constants.length);
    assertEquals(5, (instructionArray[1]).length);
    assertEquals(5, (instructionArray2[0]).length);
    assertEquals(5, (instructionArray2[1]).length);
    byte[] expectedBytes = "Name".getBytes("UTF-8");
    assertArrayEquals(expectedBytes, ((Utf8Constant) constant).getBytes());
    byte[] expectedBytes2 = "Signature".getBytes("UTF-8");
    assertArrayEquals(expectedBytes2, ((Utf8Constant) constant2).getBytes());
  }

  /**
   * Test NameOrSignatureReplacementSequences getters and setters.
   *
   * <p>Methods under test:
   *
   * <ul>
   *   <li>{@link NameOrSignatureReplacementSequences#getConstants()}
   *   <li>{@link NameOrSignatureReplacementSequences#getSequences()}
   * </ul>
   */
  @Test
  @DisplayName("Test NameOrSignatureReplacementSequences getters and setters")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({
    "Constant[] NameOrSignatureReplacementSequences.getConstants()",
    "Instruction[][][] NameOrSignatureReplacementSequences.getSequences()"
  })
  void testNameOrSignatureReplacementSequencesGettersAndSetters() {
    // Arrange
    ClassPool programClassPool = new ClassPool();
    NameOrSignatureReplacementSequences nameOrSignatureReplacementSequences =
        new NameOrSignatureReplacementSequences("Name", programClassPool, new ClassPool());

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
   * Test NameOrSignatureReplacementSequences {@link
   * NameOrSignatureReplacementSequences#NameOrSignatureReplacementSequences(String, ClassPool,
   * ClassPool)}.
   *
   * <p>Method under test: {@link
   * NameOrSignatureReplacementSequences#NameOrSignatureReplacementSequences(String, ClassPool,
   * ClassPool)}
   */
  @Test
  @DisplayName(
      "Test NameOrSignatureReplacementSequences new NameOrSignatureReplacementSequences(String, ClassPool, ClassPool)")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void NameOrSignatureReplacementSequences.<init>(String, ClassPool, ClassPool)"
  })
  void testNameOrSignatureReplacementSequencesNewNameOrSignatureReplacementSequences() {
    // Arrange
    ClassPool programClassPool = new ClassPool();

    // Act
    NameOrSignatureReplacementSequences actualNameOrSignatureReplacementSequences =
        new NameOrSignatureReplacementSequences("Name", programClassPool, new ClassPool());

    // Assert
    Constant[] constants = actualNameOrSignatureReplacementSequences.getConstants();
    assertTrue(constants[2] instanceof StringConstant);
    assertTrue(constants[1] instanceof Utf8Constant);
    assertNull(constants[0]);
    Instruction[][][] sequences = actualNameOrSignatureReplacementSequences.getSequences();
    assertEquals(1, sequences.length);
    assertEquals(2, (sequences[0]).length);
    assertEquals(3, constants.length);
  }

  /**
   * Test NameOrSignatureReplacementSequences {@link
   * NameOrSignatureReplacementSequences#NameOrSignatureReplacementSequences(String, ClassPool,
   * ClassPool)}.
   *
   * <p>Method under test: {@link
   * NameOrSignatureReplacementSequences#NameOrSignatureReplacementSequences(String, ClassPool,
   * ClassPool)}
   */
  @Test
  @DisplayName(
      "Test NameOrSignatureReplacementSequences new NameOrSignatureReplacementSequences(String, ClassPool, ClassPool)")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void NameOrSignatureReplacementSequences.<init>(String, ClassPool, ClassPool)"
  })
  void testNameOrSignatureReplacementSequencesNewNameOrSignatureReplacementSequences2() {
    // Arrange and Act
    NameOrSignatureReplacementSequences actualNameOrSignatureReplacementSequences =
        new NameOrSignatureReplacementSequences("Name", null, new ClassPool());

    // Assert
    Constant[] constants = actualNameOrSignatureReplacementSequences.getConstants();
    assertTrue(constants[2] instanceof StringConstant);
    assertTrue(constants[1] instanceof Utf8Constant);
    assertNull(constants[0]);
    Instruction[][][] sequences = actualNameOrSignatureReplacementSequences.getSequences();
    assertEquals(1, sequences.length);
    assertEquals(2, (sequences[0]).length);
    assertEquals(3, constants.length);
  }

  /**
   * Test OwnerReplacementSequences getters and setters.
   *
   * <p>Methods under test:
   *
   * <ul>
   *   <li>{@link OwnerReplacementSequences#getConstants()}
   *   <li>{@link OwnerReplacementSequences#getSequences()}
   * </ul>
   */
  @Test
  @DisplayName("Test OwnerReplacementSequences getters and setters")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({
    "Constant[] OwnerReplacementSequences.getConstants()",
    "Instruction[][][] OwnerReplacementSequences.getSequences()"
  })
  void testOwnerReplacementSequencesGettersAndSetters() {
    // Arrange
    ClassPool programClassPool = new ClassPool();
    OwnerReplacementSequences ownerReplacementSequences =
        new OwnerReplacementSequences("Name", programClassPool, new ClassPool());

    // Act
    Constant[] actualConstants = ownerReplacementSequences.getConstants();
    Instruction[][][] actualSequences = ownerReplacementSequences.getSequences();

    // Assert
    Constant constant = actualConstants[5];
    assertTrue(constant instanceof ClassConstant);
    Constant constant2 = actualConstants[6];
    assertTrue(constant2 instanceof MethodrefConstant);
    Constant constant3 = actualConstants[3];
    assertTrue(constant3 instanceof NameAndTypeConstant);
    Constant constant4 = actualConstants[8];
    assertTrue(constant4 instanceof StringConstant);
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
    assertNull(((StringConstant) constant4).javaLangStringClass);
    assertNull(((StringConstant) constant4).referencedClass);
    assertNull(((StringConstant) constant4).referencedMember);
    assertNull(((MethodrefConstant) constant2).referencedMethod);
    assertNull(actualConstants[0]);
    assertNull(((StringConstant) constant4).referencedResourceFile);
    assertEquals((byte) -72, ((ConstantInstruction) instruction3).opcode);
    assertEquals(0, ((StringConstant) constant4).referencedResourceId);
    assertEquals(0, ((ConstantInstruction) instruction).constant);
    assertEquals(0, ((ConstantInstruction) instruction2).constant);
    assertEquals(0, ((ConstantInstruction) instruction3).constant);
    assertEquals(0, ((ConstantInstruction) instruction4).constant);
    assertEquals(1, actualSequences.length);
    assertEquals(1, ((NameAndTypeConstant) constant3).u2nameIndex);
    assertEquals(1073741824, ((ConstantInstruction) instruction).constantIndex);
    assertEquals(1073741825, ((ConstantInstruction) instruction2).constantIndex);
    assertEquals((byte) 18, ((ConstantInstruction) instruction).opcode);
    assertEquals((byte) 18, ((ConstantInstruction) instruction2).opcode);
    assertEquals((byte) 18, ((ConstantInstruction) instruction4).opcode);
    assertEquals(2, instructionArray.length);
    assertEquals(2, ((NameAndTypeConstant) constant3).u2descriptorIndex);
    assertEquals(3, instructionArray2.length);
    assertEquals(3, instructionArray3.length);
    assertEquals(4, ((ClassConstant) constant).u2nameIndex);
    assertEquals(6, ((ConstantInstruction) instruction3).constantIndex);
    assertEquals(7, ((StringConstant) constant4).u2stringIndex);
    assertEquals(8, ((ConstantInstruction) instruction4).constantIndex);
    assertEquals(9, actualConstants.length);
  }

  /**
   * Test OwnerReplacementSequences {@link
   * OwnerReplacementSequences#OwnerReplacementSequences(String, ClassPool, ClassPool)}.
   *
   * <p>Method under test: {@link OwnerReplacementSequences#OwnerReplacementSequences(String,
   * ClassPool, ClassPool)}
   */
  @Test
  @DisplayName(
      "Test OwnerReplacementSequences new OwnerReplacementSequences(String, ClassPool, ClassPool)")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void OwnerReplacementSequences.<init>(String, ClassPool, ClassPool)"})
  void testOwnerReplacementSequencesNewOwnerReplacementSequences()
      throws UnsupportedEncodingException {
    // Arrange
    ClassPool programClassPool = new ClassPool();

    // Act and Assert
    Constant[] constants =
        new OwnerReplacementSequences("Name", programClassPool, new ClassPool()).getConstants();
    Constant constant = constants[5];
    assertTrue(constant instanceof ClassConstant);
    Constant constant2 = constants[1];
    assertTrue(constant2 instanceof Utf8Constant);
    Constant constant3 = constants[4];
    assertTrue(constant3 instanceof Utf8Constant);
    Constant constant4 = constants[7];
    assertTrue(constant4 instanceof Utf8Constant);
    assertNull(((ClassConstant) constant).referencedClass);
    assertEquals(9, constants.length);
    byte[] expectedBytes = "Name".getBytes("UTF-8");
    assertArrayEquals(expectedBytes, ((Utf8Constant) constant4).getBytes());
    byte[] expectedBytes2 = "getOrCreateKotlinPackage".getBytes("UTF-8");
    assertArrayEquals(expectedBytes2, ((Utf8Constant) constant2).getBytes());
    byte[] expectedBytes3 = "kotlin/jvm/internal/Reflection".getBytes("UTF-8");
    assertArrayEquals(expectedBytes3, ((Utf8Constant) constant3).getBytes());
  }

  /**
   * Test OwnerReplacementSequences {@link
   * OwnerReplacementSequences#OwnerReplacementSequences(String, ClassPool, ClassPool)}.
   *
   * <p>Method under test: {@link OwnerReplacementSequences#OwnerReplacementSequences(String,
   * ClassPool, ClassPool)}
   */
  @Test
  @DisplayName(
      "Test OwnerReplacementSequences new OwnerReplacementSequences(String, ClassPool, ClassPool)")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void OwnerReplacementSequences.<init>(String, ClassPool, ClassPool)"})
  void testOwnerReplacementSequencesNewOwnerReplacementSequences2()
      throws UnsupportedEncodingException {
    // Arrange and Act
    OwnerReplacementSequences actualOwnerReplacementSequences =
        new OwnerReplacementSequences("Name", null, new ClassPool());

    // Assert
    Constant[] constants = actualOwnerReplacementSequences.getConstants();
    Constant constant = constants[1];
    assertTrue(constant instanceof Utf8Constant);
    Constant constant2 = constants[4];
    assertTrue(constant2 instanceof Utf8Constant);
    Constant constant3 = constants[7];
    assertTrue(constant3 instanceof Utf8Constant);
    Instruction[][][] sequences = actualOwnerReplacementSequences.getSequences();
    Instruction[][] instructionArray = sequences[0];
    Instruction[] instructionArray2 = instructionArray[1];
    assertTrue(instructionArray2[0] instanceof ConstantInstruction);
    assertTrue(instructionArray2[2] instanceof ConstantInstruction);
    assertEquals(1, sequences.length);
    assertEquals(2, instructionArray.length);
    assertEquals(3, instructionArray2.length);
    assertEquals(9, constants.length);
    byte[] expectedBytes = "Name".getBytes("UTF-8");
    assertArrayEquals(expectedBytes, ((Utf8Constant) constant3).getBytes());
    byte[] expectedBytes2 = "getOrCreateKotlinPackage".getBytes("UTF-8");
    assertArrayEquals(expectedBytes2, ((Utf8Constant) constant).getBytes());
    byte[] expectedBytes3 = "kotlin/jvm/internal/Reflection".getBytes("UTF-8");
    assertArrayEquals(expectedBytes3, ((Utf8Constant) constant2).getBytes());
  }

  /**
   * Test OwnerReplacementSequences {@link
   * OwnerReplacementSequences#OwnerReplacementSequences(String, ClassPool, ClassPool)}.
   *
   * <p>Method under test: {@link OwnerReplacementSequences#OwnerReplacementSequences(String,
   * ClassPool, ClassPool)}
   */
  @Test
  @DisplayName(
      "Test OwnerReplacementSequences new OwnerReplacementSequences(String, ClassPool, ClassPool)")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void OwnerReplacementSequences.<init>(String, ClassPool, ClassPool)"})
  void testOwnerReplacementSequencesNewOwnerReplacementSequences3()
      throws UnsupportedEncodingException {
    // Arrange
    ClassPool programClassPool = new ClassPool();
    LibraryClass clazz =
        new LibraryClass(1, "kotlin/jvm/internal/Reflection", "kotlin/jvm/internal/Reflection");

    programClassPool.addClass("kotlin/jvm/internal/Reflection", clazz);

    // Act and Assert
    Constant[] constants =
        new OwnerReplacementSequences("Name", programClassPool, new ClassPool()).getConstants();
    Constant constant = constants[5];
    Clazz clazz2 = ((ClassConstant) constant).referencedClass;
    assertTrue(clazz2 instanceof LibraryClass);
    assertTrue(constant instanceof ClassConstant);
    Constant constant2 = constants[1];
    assertTrue(constant2 instanceof Utf8Constant);
    Constant constant3 = constants[4];
    assertTrue(constant3 instanceof Utf8Constant);
    Constant constant4 = constants[7];
    assertTrue(constant4 instanceof Utf8Constant);
    assertEquals(9, constants.length);
    assertSame(clazz, clazz2);
    byte[] expectedBytes = "Name".getBytes("UTF-8");
    assertArrayEquals(expectedBytes, ((Utf8Constant) constant4).getBytes());
    byte[] expectedBytes2 = "getOrCreateKotlinPackage".getBytes("UTF-8");
    assertArrayEquals(expectedBytes2, ((Utf8Constant) constant2).getBytes());
    byte[] expectedBytes3 = "kotlin/jvm/internal/Reflection".getBytes("UTF-8");
    assertArrayEquals(expectedBytes3, ((Utf8Constant) constant3).getBytes());
  }

  /**
   * Test OwnerReplacementSequences {@link
   * OwnerReplacementSequences#OwnerReplacementSequences(String, ClassPool, ClassPool)}.
   *
   * <p>Method under test: {@link OwnerReplacementSequences#OwnerReplacementSequences(String,
   * ClassPool, ClassPool)}
   */
  @Test
  @DisplayName(
      "Test OwnerReplacementSequences new OwnerReplacementSequences(String, ClassPool, ClassPool)")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void OwnerReplacementSequences.<init>(String, ClassPool, ClassPool)"})
  void testOwnerReplacementSequencesNewOwnerReplacementSequences4()
      throws UnsupportedEncodingException {
    // Arrange
    ClassPool programClassPool = new ClassPool();
    ProgramClass clazz = new ProgramClass();
    programClassPool.addClass("kotlin/jvm/internal/Reflection", clazz);

    // Act and Assert
    Constant[] constants =
        new OwnerReplacementSequences("Name", programClassPool, new ClassPool()).getConstants();
    Constant constant = constants[5];
    assertTrue(constant instanceof ClassConstant);
    Constant constant2 = constants[1];
    assertTrue(constant2 instanceof Utf8Constant);
    Constant constant3 = constants[4];
    assertTrue(constant3 instanceof Utf8Constant);
    Constant constant4 = constants[7];
    assertTrue(constant4 instanceof Utf8Constant);
    assertEquals(9, constants.length);
    assertSame(clazz, ((ClassConstant) constant).referencedClass);
    byte[] expectedBytes = "Name".getBytes("UTF-8");
    assertArrayEquals(expectedBytes, ((Utf8Constant) constant4).getBytes());
    byte[] expectedBytes2 = "getOrCreateKotlinPackage".getBytes("UTF-8");
    assertArrayEquals(expectedBytes2, ((Utf8Constant) constant2).getBytes());
    byte[] expectedBytes3 = "kotlin/jvm/internal/Reflection".getBytes("UTF-8");
    assertArrayEquals(expectedBytes3, ((Utf8Constant) constant3).getBytes());
  }

  /**
   * Test OwnerReplacementSequences {@link
   * OwnerReplacementSequences#OwnerReplacementSequences(String, ClassPool, ClassPool)}.
   *
   * <p>Method under test: {@link OwnerReplacementSequences#OwnerReplacementSequences(String,
   * ClassPool, ClassPool)}
   */
  @Test
  @DisplayName(
      "Test OwnerReplacementSequences new OwnerReplacementSequences(String, ClassPool, ClassPool)")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void OwnerReplacementSequences.<init>(String, ClassPool, ClassPool)"})
  void testOwnerReplacementSequencesNewOwnerReplacementSequences5()
      throws UnsupportedEncodingException {
    // Arrange
    ClassPool programClassPool = new ClassPool();
    ClassConstant classConstant = new ClassConstant();
    ProgramClass clazz =
        new ProgramClass(1, 3, new Constant[] {classConstant, new ClassConstant()}, 1, 1, 1);

    programClassPool.addClass("kotlin/jvm/internal/Reflection", clazz);

    // Act and Assert
    Constant[] constants =
        new OwnerReplacementSequences("Name", programClassPool, new ClassPool()).getConstants();
    Constant constant = constants[5];
    assertTrue(constant instanceof ClassConstant);
    Constant constant2 = constants[1];
    assertTrue(constant2 instanceof Utf8Constant);
    Constant constant3 = constants[4];
    assertTrue(constant3 instanceof Utf8Constant);
    Constant constant4 = constants[7];
    assertTrue(constant4 instanceof Utf8Constant);
    assertEquals(9, constants.length);
    assertSame(clazz, ((ClassConstant) constant).referencedClass);
    byte[] expectedBytes = "Name".getBytes("UTF-8");
    assertArrayEquals(expectedBytes, ((Utf8Constant) constant4).getBytes());
    byte[] expectedBytes2 = "getOrCreateKotlinPackage".getBytes("UTF-8");
    assertArrayEquals(expectedBytes2, ((Utf8Constant) constant2).getBytes());
    byte[] expectedBytes3 = "kotlin/jvm/internal/Reflection".getBytes("UTF-8");
    assertArrayEquals(expectedBytes3, ((Utf8Constant) constant3).getBytes());
  }

  /**
   * Test OwnerReplacementSequences {@link
   * OwnerReplacementSequences#OwnerReplacementSequences(String, ClassPool, ClassPool)}.
   *
   * <p>Method under test: {@link OwnerReplacementSequences#OwnerReplacementSequences(String,
   * ClassPool, ClassPool)}
   */
  @Test
  @DisplayName(
      "Test OwnerReplacementSequences new OwnerReplacementSequences(String, ClassPool, ClassPool)")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void OwnerReplacementSequences.<init>(String, ClassPool, ClassPool)"})
  void testOwnerReplacementSequencesNewOwnerReplacementSequences6()
      throws UnsupportedEncodingException {
    // Arrange
    ClassPool programClassPool = new ClassPool();
    ClassConstant classConstant = new ClassConstant();
    ProgramClass clazz =
        new ProgramClass(
            1,
            3,
            new Constant[] {
              classConstant,
              new ClassConstant(
                  1,
                  new LibraryClass(
                      1, "kotlin/jvm/internal/Reflection", "kotlin/jvm/internal/Reflection"))
            },
            1,
            1,
            1);

    programClassPool.addClass("kotlin/jvm/internal/Reflection", clazz);

    // Act and Assert
    Constant[] constants =
        new OwnerReplacementSequences("Name", programClassPool, new ClassPool()).getConstants();
    Constant constant = constants[5];
    assertTrue(constant instanceof ClassConstant);
    Constant constant2 = constants[1];
    assertTrue(constant2 instanceof Utf8Constant);
    Constant constant3 = constants[4];
    assertTrue(constant3 instanceof Utf8Constant);
    Constant constant4 = constants[7];
    assertTrue(constant4 instanceof Utf8Constant);
    assertEquals(9, constants.length);
    assertSame(clazz, ((ClassConstant) constant).referencedClass);
    byte[] expectedBytes = "Name".getBytes("UTF-8");
    assertArrayEquals(expectedBytes, ((Utf8Constant) constant4).getBytes());
    byte[] expectedBytes2 = "getOrCreateKotlinPackage".getBytes("UTF-8");
    assertArrayEquals(expectedBytes2, ((Utf8Constant) constant2).getBytes());
    byte[] expectedBytes3 = "kotlin/jvm/internal/Reflection".getBytes("UTF-8");
    assertArrayEquals(expectedBytes3, ((Utf8Constant) constant3).getBytes());
  }

  /**
   * Test OwnerReplacementSequences {@link
   * OwnerReplacementSequences#OwnerReplacementSequences(String, ClassPool, ClassPool)}.
   *
   * <p>Method under test: {@link OwnerReplacementSequences#OwnerReplacementSequences(String,
   * ClassPool, ClassPool)}
   */
  @Test
  @DisplayName(
      "Test OwnerReplacementSequences new OwnerReplacementSequences(String, ClassPool, ClassPool)")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void OwnerReplacementSequences.<init>(String, ClassPool, ClassPool)"})
  void testOwnerReplacementSequencesNewOwnerReplacementSequences7()
      throws UnsupportedEncodingException {
    // Arrange
    ClassPool programClassPool = new ClassPool();
    ClassConstant classConstant = new ClassConstant();
    ProgramClass clazz =
        new ProgramClass(
            1,
            3,
            new Constant[] {classConstant, new ClassConstant(1, new ProgramClass())},
            1,
            1,
            1);

    programClassPool.addClass("kotlin/jvm/internal/Reflection", clazz);

    // Act and Assert
    Constant[] constants =
        new OwnerReplacementSequences("Name", programClassPool, new ClassPool()).getConstants();
    Constant constant = constants[5];
    assertTrue(constant instanceof ClassConstant);
    Constant constant2 = constants[1];
    assertTrue(constant2 instanceof Utf8Constant);
    Constant constant3 = constants[4];
    assertTrue(constant3 instanceof Utf8Constant);
    Constant constant4 = constants[7];
    assertTrue(constant4 instanceof Utf8Constant);
    assertEquals(9, constants.length);
    assertSame(clazz, ((ClassConstant) constant).referencedClass);
    byte[] expectedBytes = "Name".getBytes("UTF-8");
    assertArrayEquals(expectedBytes, ((Utf8Constant) constant4).getBytes());
    byte[] expectedBytes2 = "getOrCreateKotlinPackage".getBytes("UTF-8");
    assertArrayEquals(expectedBytes2, ((Utf8Constant) constant2).getBytes());
    byte[] expectedBytes3 = "kotlin/jvm/internal/Reflection".getBytes("UTF-8");
    assertArrayEquals(expectedBytes3, ((Utf8Constant) constant3).getBytes());
  }
}
