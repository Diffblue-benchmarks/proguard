package proguard.obfuscate.kotlin;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import proguard.classfile.ClassPool;
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

class KotlinIntrinsicsReplacementSequencesDiffblueTest {
  /**
   * Test {@link
   * KotlinIntrinsicsReplacementSequences#KotlinIntrinsicsReplacementSequences(ClassPool,
   * ClassPool)}.
   *
   * <p>Method under test: {@link
   * KotlinIntrinsicsReplacementSequences#KotlinIntrinsicsReplacementSequences(ClassPool,
   * ClassPool)}
   */
  @Test
  @DisplayName("Test new KotlinIntrinsicsReplacementSequences(ClassPool, ClassPool)")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void KotlinIntrinsicsReplacementSequences.<init>(ClassPool, ClassPool)"})
  void testNewKotlinIntrinsicsReplacementSequences() {
    // Arrange
    ClassPool programClassPool = new ClassPool();
    programClassPool.addClass(
        "kotlin/jvm/internal/Intrinsics",
        new LibraryClass(
            Short.SIZE, "kotlin/jvm/internal/Intrinsics", "kotlin/jvm/internal/Intrinsics"));

    // Act and Assert
    Constant[] constants =
        new KotlinIntrinsicsReplacementSequences(programClassPool, new ClassPool()).getConstants();
    assertTrue(constants[5] instanceof ClassConstant);
    assertTrue(constants[1] instanceof Utf8Constant);
    assertTrue(constants[10] instanceof Utf8Constant);
    assertTrue(constants[13] instanceof Utf8Constant);
    assertTrue(constants[15] instanceof Utf8Constant);
    assertTrue(constants[18] instanceof Utf8Constant);
    assertTrue(constants[2] instanceof Utf8Constant);
    assertTrue(constants[21] instanceof Utf8Constant);
    assertTrue(constants[24] instanceof Utf8Constant);
    assertTrue(constants[4] instanceof Utf8Constant);
    assertTrue(constants[45] instanceof Utf8Constant);
    assertTrue(constants[48] instanceof Utf8Constant);
    assertTrue(constants[53] instanceof Utf8Constant);
    assertTrue(constants[58] instanceof Utf8Constant);
    assertTrue(constants[63] instanceof Utf8Constant);
    assertTrue(constants[7] instanceof Utf8Constant);
    assertEquals(68, constants.length);
  }

  /**
   * Test {@link
   * KotlinIntrinsicsReplacementSequences#KotlinIntrinsicsReplacementSequences(ClassPool,
   * ClassPool)}.
   *
   * <ul>
   *   <li>Given {@link ProgramClass#ProgramClass()}.
   * </ul>
   *
   * <p>Method under test: {@link
   * KotlinIntrinsicsReplacementSequences#KotlinIntrinsicsReplacementSequences(ClassPool,
   * ClassPool)}
   */
  @Test
  @DisplayName(
      "Test new KotlinIntrinsicsReplacementSequences(ClassPool, ClassPool); given ProgramClass()")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void KotlinIntrinsicsReplacementSequences.<init>(ClassPool, ClassPool)"})
  void testNewKotlinIntrinsicsReplacementSequences_givenProgramClass() {
    // Arrange
    ClassPool programClassPool = new ClassPool();
    programClassPool.addClass("kotlin/jvm/internal/Intrinsics", new ProgramClass());

    // Act and Assert
    Constant[] constants =
        new KotlinIntrinsicsReplacementSequences(programClassPool, new ClassPool()).getConstants();
    assertTrue(constants[5] instanceof ClassConstant);
    assertTrue(constants[1] instanceof Utf8Constant);
    assertTrue(constants[10] instanceof Utf8Constant);
    assertTrue(constants[13] instanceof Utf8Constant);
    assertTrue(constants[15] instanceof Utf8Constant);
    assertTrue(constants[18] instanceof Utf8Constant);
    assertTrue(constants[2] instanceof Utf8Constant);
    assertTrue(constants[21] instanceof Utf8Constant);
    assertTrue(constants[24] instanceof Utf8Constant);
    assertTrue(constants[4] instanceof Utf8Constant);
    assertTrue(constants[45] instanceof Utf8Constant);
    assertTrue(constants[48] instanceof Utf8Constant);
    assertTrue(constants[53] instanceof Utf8Constant);
    assertTrue(constants[58] instanceof Utf8Constant);
    assertTrue(constants[63] instanceof Utf8Constant);
    assertTrue(constants[7] instanceof Utf8Constant);
    assertEquals(68, constants.length);
  }

  /**
   * Test {@link
   * KotlinIntrinsicsReplacementSequences#KotlinIntrinsicsReplacementSequences(ClassPool,
   * ClassPool)}.
   *
   * <ul>
   *   <li>Then second element return {@link Utf8Constant}.
   * </ul>
   *
   * <p>Method under test: {@link
   * KotlinIntrinsicsReplacementSequences#KotlinIntrinsicsReplacementSequences(ClassPool,
   * ClassPool)}
   */
  @Test
  @DisplayName(
      "Test new KotlinIntrinsicsReplacementSequences(ClassPool, ClassPool); then second element return Utf8Constant")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void KotlinIntrinsicsReplacementSequences.<init>(ClassPool, ClassPool)"})
  void testNewKotlinIntrinsicsReplacementSequences_thenSecondElementReturnUtf8Constant() {
    // Arrange
    ClassPool programClassPool = new ClassPool();

    // Act and Assert
    Constant[] constants =
        new KotlinIntrinsicsReplacementSequences(programClassPool, new ClassPool()).getConstants();
    assertTrue(constants[1] instanceof Utf8Constant);
    assertTrue(constants[10] instanceof Utf8Constant);
    assertTrue(constants[13] instanceof Utf8Constant);
    assertTrue(constants[15] instanceof Utf8Constant);
    assertTrue(constants[18] instanceof Utf8Constant);
    assertTrue(constants[2] instanceof Utf8Constant);
    assertTrue(constants[21] instanceof Utf8Constant);
    assertTrue(constants[24] instanceof Utf8Constant);
    assertTrue(constants[4] instanceof Utf8Constant);
    assertTrue(constants[45] instanceof Utf8Constant);
    assertTrue(constants[48] instanceof Utf8Constant);
    assertTrue(constants[53] instanceof Utf8Constant);
    assertTrue(constants[58] instanceof Utf8Constant);
    assertTrue(constants[63] instanceof Utf8Constant);
    assertTrue(constants[7] instanceof Utf8Constant);
    assertEquals(68, constants.length);
  }

  /**
   * Test {@link
   * KotlinIntrinsicsReplacementSequences#KotlinIntrinsicsReplacementSequences(ClassPool,
   * ClassPool)}.
   *
   * <ul>
   *   <li>When {@code null}.
   *   <li>Then return array length is {@link Short#SIZE}.
   * </ul>
   *
   * <p>Method under test: {@link
   * KotlinIntrinsicsReplacementSequences#KotlinIntrinsicsReplacementSequences(ClassPool,
   * ClassPool)}
   */
  @Test
  @DisplayName(
      "Test new KotlinIntrinsicsReplacementSequences(ClassPool, ClassPool); when 'null'; then return array length is SIZE")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void KotlinIntrinsicsReplacementSequences.<init>(ClassPool, ClassPool)"})
  void testNewKotlinIntrinsicsReplacementSequences_whenNull_thenReturnArrayLengthIsSize() {
    // Arrange and Act
    KotlinIntrinsicsReplacementSequences actualKotlinIntrinsicsReplacementSequences =
        new KotlinIntrinsicsReplacementSequences(null, new ClassPool());

    // Assert
    assertEquals(68, actualKotlinIntrinsicsReplacementSequences.getConstants().length);
    assertEquals(Short.SIZE, actualKotlinIntrinsicsReplacementSequences.getSequences().length);
  }

  /**
   * Test getters and setters.
   *
   * <p>Methods under test:
   *
   * <ul>
   *   <li>{@link KotlinIntrinsicsReplacementSequences#getConstants()}
   *   <li>{@link KotlinIntrinsicsReplacementSequences#getSequences()}
   * </ul>
   */
  @Test
  @DisplayName("Test getters and setters")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({
    "Constant[] KotlinIntrinsicsReplacementSequences.getConstants()",
    "Instruction[][][] KotlinIntrinsicsReplacementSequences.getSequences()"
  })
  void testGettersAndSetters() {
    // Arrange
    ClassPool programClassPool = new ClassPool();
    KotlinIntrinsicsReplacementSequences kotlinIntrinsicsReplacementSequences =
        new KotlinIntrinsicsReplacementSequences(programClassPool, new ClassPool());

    // Act
    Constant[] actualConstants = kotlinIntrinsicsReplacementSequences.getConstants();
    Instruction[][][] actualSequences = kotlinIntrinsicsReplacementSequences.getSequences();

    // Assert
    Constant constant = actualConstants[5];
    assertTrue(constant instanceof ClassConstant);
    Constant constant2 = actualConstants[12];
    assertTrue(constant2 instanceof MethodrefConstant);
    Constant constant3 = actualConstants[17];
    assertTrue(constant3 instanceof MethodrefConstant);
    Constant constant4 = actualConstants[20];
    assertTrue(constant4 instanceof MethodrefConstant);
    Constant constant5 = actualConstants[23];
    assertTrue(constant5 instanceof MethodrefConstant);
    Constant constant6 = actualConstants[44];
    assertTrue(constant6 instanceof MethodrefConstant);
    Constant constant7 = actualConstants[47];
    assertTrue(constant7 instanceof MethodrefConstant);
    Constant constant8 = actualConstants[50];
    assertTrue(constant8 instanceof MethodrefConstant);
    Constant constant9 = actualConstants[52];
    assertTrue(constant9 instanceof MethodrefConstant);
    Constant constant10 = actualConstants[55];
    assertTrue(constant10 instanceof MethodrefConstant);
    Constant constant11 = actualConstants[57];
    assertTrue(constant11 instanceof MethodrefConstant);
    Constant constant12 = actualConstants[6];
    assertTrue(constant12 instanceof MethodrefConstant);
    Constant constant13 = actualConstants[60];
    assertTrue(constant13 instanceof MethodrefConstant);
    Constant constant14 = actualConstants[62];
    assertTrue(constant14 instanceof MethodrefConstant);
    Constant constant15 = actualConstants[65];
    assertTrue(constant15 instanceof MethodrefConstant);
    Constant constant16 = actualConstants[67];
    assertTrue(constant16 instanceof MethodrefConstant);
    Constant constant17 = actualConstants[9];
    assertTrue(constant17 instanceof MethodrefConstant);
    Constant constant18 = actualConstants[11];
    assertTrue(constant18 instanceof NameAndTypeConstant);
    Constant constant19 = actualConstants[19];
    assertTrue(constant19 instanceof NameAndTypeConstant);
    Constant constant20 = actualConstants[22];
    assertTrue(constant20 instanceof NameAndTypeConstant);
    Constant constant21 = actualConstants[3];
    assertTrue(constant21 instanceof NameAndTypeConstant);
    Constant constant22 = actualConstants[43];
    assertTrue(constant22 instanceof NameAndTypeConstant);
    Constant constant23 = actualConstants[46];
    assertTrue(constant23 instanceof NameAndTypeConstant);
    Constant constant24 = actualConstants[49];
    assertTrue(constant24 instanceof NameAndTypeConstant);
    Constant constant25 = actualConstants[51];
    assertTrue(constant25 instanceof NameAndTypeConstant);
    Constant constant26 = actualConstants[54];
    assertTrue(constant26 instanceof NameAndTypeConstant);
    Constant constant27 = actualConstants[56];
    assertTrue(constant27 instanceof NameAndTypeConstant);
    Constant constant28 = actualConstants[59];
    assertTrue(constant28 instanceof NameAndTypeConstant);
    Constant constant29 = actualConstants[61];
    assertTrue(constant29 instanceof NameAndTypeConstant);
    Constant constant30 = actualConstants[66];
    assertTrue(constant30 instanceof NameAndTypeConstant);
    Constant constant31 = actualConstants[8];
    assertTrue(constant31 instanceof NameAndTypeConstant);
    Constant constant32 = actualConstants[Double.SIZE];
    assertTrue(constant32 instanceof NameAndTypeConstant);
    Constant constant33 = actualConstants[Short.SIZE];
    assertTrue(constant33 instanceof NameAndTypeConstant);
    Constant constant34 = actualConstants[14];
    assertTrue(constant34 instanceof StringConstant);
    assertTrue(actualConstants[1] instanceof Utf8Constant);
    assertTrue(actualConstants[10] instanceof Utf8Constant);
    assertTrue(actualConstants[13] instanceof Utf8Constant);
    assertTrue(actualConstants[15] instanceof Utf8Constant);
    assertTrue(actualConstants[18] instanceof Utf8Constant);
    assertTrue(actualConstants[2] instanceof Utf8Constant);
    assertTrue(actualConstants[21] instanceof Utf8Constant);
    assertTrue(actualConstants[24] instanceof Utf8Constant);
    assertTrue(actualConstants[4] instanceof Utf8Constant);
    assertTrue(actualConstants[45] instanceof Utf8Constant);
    assertTrue(actualConstants[48] instanceof Utf8Constant);
    assertTrue(actualConstants[53] instanceof Utf8Constant);
    assertTrue(actualConstants[58] instanceof Utf8Constant);
    assertTrue(actualConstants[63] instanceof Utf8Constant);
    assertTrue(actualConstants[7] instanceof Utf8Constant);
    Instruction[][] instructionArray = actualSequences[0];
    Instruction[] instructionArray2 = instructionArray[0];
    Instruction instruction = instructionArray2[0];
    assertTrue(instruction instanceof ConstantInstruction);
    Instruction instruction2 = instructionArray2[1];
    assertTrue(instruction2 instanceof ConstantInstruction);
    Instruction[] instructionArray3 = instructionArray[1];
    Instruction instruction3 = instructionArray3[0];
    assertTrue(instruction3 instanceof ConstantInstruction);
    Instruction[][] instructionArray4 = actualSequences[1];
    Instruction[] instructionArray5 = instructionArray4[0];
    assertTrue(instructionArray5[0] instanceof ConstantInstruction);
    Instruction instruction4 = instructionArray5[1];
    assertTrue(instruction4 instanceof ConstantInstruction);
    Instruction[] instructionArray6 = instructionArray4[1];
    Instruction instruction5 = instructionArray6[0];
    assertTrue(instruction5 instanceof ConstantInstruction);
    assertTrue(instructionArray6[1] instanceof ConstantInstruction);
    Instruction[][] instructionArray7 = actualSequences[10];
    Instruction[] instructionArray8 = instructionArray7[0];
    assertTrue(instructionArray8[0] instanceof ConstantInstruction);
    Instruction instruction6 = instructionArray8[1];
    assertTrue(instruction6 instanceof ConstantInstruction);
    Instruction[] instructionArray9 = instructionArray7[1];
    assertTrue(instructionArray9[0] instanceof ConstantInstruction);
    Instruction instruction7 = instructionArray9[1];
    assertTrue(instruction7 instanceof ConstantInstruction);
    Instruction[][] instructionArray10 = actualSequences[11];
    Instruction[] instructionArray11 = instructionArray10[0];
    assertTrue(instructionArray11[0] instanceof ConstantInstruction);
    Instruction instruction8 = instructionArray11[1];
    assertTrue(instruction8 instanceof ConstantInstruction);
    Instruction[] instructionArray12 = instructionArray10[1];
    Instruction instruction9 = instructionArray12[0];
    assertTrue(instruction9 instanceof ConstantInstruction);
    Instruction[][] instructionArray13 = actualSequences[12];
    Instruction[] instructionArray14 = instructionArray13[0];
    assertTrue(instructionArray14[0] instanceof ConstantInstruction);
    Instruction instruction10 = instructionArray14[1];
    assertTrue(instruction10 instanceof ConstantInstruction);
    Instruction[] instructionArray15 = instructionArray13[1];
    Instruction instruction11 = instructionArray15[0];
    assertTrue(instruction11 instanceof ConstantInstruction);
    Instruction[][] instructionArray16 = actualSequences[13];
    Instruction[] instructionArray17 = instructionArray16[0];
    assertTrue(instructionArray17[0] instanceof ConstantInstruction);
    Instruction instruction12 = instructionArray17[1];
    assertTrue(instruction12 instanceof ConstantInstruction);
    Instruction[] instructionArray18 = instructionArray16[1];
    Instruction instruction13 = instructionArray18[0];
    assertTrue(instruction13 instanceof ConstantInstruction);
    Instruction[][] instructionArray19 = actualSequences[14];
    Instruction[] instructionArray20 = instructionArray19[0];
    assertTrue(instructionArray20[0] instanceof ConstantInstruction);
    Instruction instruction14 = instructionArray20[1];
    assertTrue(instruction14 instanceof ConstantInstruction);
    Instruction[] instructionArray21 = instructionArray19[1];
    Instruction instruction15 = instructionArray21[0];
    assertTrue(instruction15 instanceof ConstantInstruction);
    Instruction[][] instructionArray22 = actualSequences[15];
    Instruction[] instructionArray23 = instructionArray22[0];
    assertTrue(instructionArray23[0] instanceof ConstantInstruction);
    Instruction instruction16 = instructionArray23[1];
    assertTrue(instruction16 instanceof ConstantInstruction);
    Instruction[] instructionArray24 = instructionArray22[1];
    Instruction instruction17 = instructionArray24[0];
    assertTrue(instruction17 instanceof ConstantInstruction);
    Instruction[][] instructionArray25 = actualSequences[2];
    Instruction[] instructionArray26 = instructionArray25[0];
    assertTrue(instructionArray26[0] instanceof ConstantInstruction);
    Instruction instruction18 = instructionArray26[1];
    assertTrue(instruction18 instanceof ConstantInstruction);
    Instruction[] instructionArray27 = instructionArray25[1];
    assertTrue(instructionArray27[0] instanceof ConstantInstruction);
    Instruction instruction19 = instructionArray27[1];
    assertTrue(instruction19 instanceof ConstantInstruction);
    Instruction[][] instructionArray28 = actualSequences[3];
    Instruction[] instructionArray29 = instructionArray28[0];
    assertTrue(instructionArray29[0] instanceof ConstantInstruction);
    Instruction instruction20 = instructionArray29[1];
    assertTrue(instruction20 instanceof ConstantInstruction);
    Instruction[] instructionArray30 = instructionArray28[1];
    assertTrue(instructionArray30[0] instanceof ConstantInstruction);
    Instruction instruction21 = instructionArray30[1];
    assertTrue(instruction21 instanceof ConstantInstruction);
    Instruction[][] instructionArray31 = actualSequences[4];
    Instruction[] instructionArray32 = instructionArray31[0];
    assertTrue(instructionArray32[0] instanceof ConstantInstruction);
    Instruction instruction22 = instructionArray32[1];
    assertTrue(instruction22 instanceof ConstantInstruction);
    Instruction[] instructionArray33 = instructionArray31[1];
    assertTrue(instructionArray33[0] instanceof ConstantInstruction);
    Instruction instruction23 = instructionArray33[1];
    assertTrue(instruction23 instanceof ConstantInstruction);
    Instruction[][] instructionArray34 = actualSequences[5];
    Instruction[] instructionArray35 = instructionArray34[0];
    assertTrue(instructionArray35[0] instanceof ConstantInstruction);
    Instruction instruction24 = instructionArray35[1];
    assertTrue(instruction24 instanceof ConstantInstruction);
    Instruction[] instructionArray36 = instructionArray34[1];
    assertTrue(instructionArray36[0] instanceof ConstantInstruction);
    Instruction instruction25 = instructionArray36[1];
    assertTrue(instruction25 instanceof ConstantInstruction);
    Instruction[][] instructionArray37 = actualSequences[6];
    Instruction[] instructionArray38 = instructionArray37[0];
    assertTrue(instructionArray38[0] instanceof ConstantInstruction);
    Instruction instruction26 = instructionArray38[1];
    assertTrue(instruction26 instanceof ConstantInstruction);
    Instruction[] instructionArray39 = instructionArray37[1];
    assertTrue(instructionArray39[0] instanceof ConstantInstruction);
    Instruction instruction27 = instructionArray39[1];
    assertTrue(instruction27 instanceof ConstantInstruction);
    Instruction[][] instructionArray40 = actualSequences[7];
    Instruction[] instructionArray41 = instructionArray40[0];
    assertTrue(instructionArray41[0] instanceof ConstantInstruction);
    Instruction instruction28 = instructionArray41[1];
    assertTrue(instruction28 instanceof ConstantInstruction);
    Instruction instruction29 = instructionArray41[2];
    assertTrue(instruction29 instanceof ConstantInstruction);
    Instruction[] instructionArray42 = instructionArray40[1];
    assertTrue(instructionArray42[0] instanceof ConstantInstruction);
    assertTrue(instructionArray42[1] instanceof ConstantInstruction);
    Instruction instruction30 = instructionArray42[2];
    assertTrue(instruction30 instanceof ConstantInstruction);
    Instruction[][] instructionArray43 = actualSequences[8];
    Instruction[] instructionArray44 = instructionArray43[0];
    assertTrue(instructionArray44[0] instanceof ConstantInstruction);
    Instruction instruction31 = instructionArray44[1];
    assertTrue(instruction31 instanceof ConstantInstruction);
    Instruction instruction32 = instructionArray44[2];
    assertTrue(instruction32 instanceof ConstantInstruction);
    Instruction[] instructionArray45 = instructionArray43[1];
    assertTrue(instructionArray45[0] instanceof ConstantInstruction);
    assertTrue(instructionArray45[1] instanceof ConstantInstruction);
    Instruction instruction33 = instructionArray45[2];
    assertTrue(instruction33 instanceof ConstantInstruction);
    Instruction[][] instructionArray46 = actualSequences[9];
    Instruction[] instructionArray47 = instructionArray46[0];
    assertTrue(instructionArray47[0] instanceof ConstantInstruction);
    Instruction instruction34 = instructionArray47[1];
    assertTrue(instruction34 instanceof ConstantInstruction);
    Instruction[] instructionArray48 = instructionArray46[1];
    assertTrue(instructionArray48[0] instanceof ConstantInstruction);
    Instruction instruction35 = instructionArray48[1];
    assertTrue(instruction35 instanceof ConstantInstruction);
    assertNull(((ClassConstant) constant).javaLangClassClass);
    assertNull(((ClassConstant) constant).referencedClass);
    assertNull(((MethodrefConstant) constant2).referencedClass);
    assertNull(((MethodrefConstant) constant3).referencedClass);
    assertNull(((MethodrefConstant) constant4).referencedClass);
    assertNull(((MethodrefConstant) constant5).referencedClass);
    assertNull(((MethodrefConstant) constant6).referencedClass);
    assertNull(((MethodrefConstant) constant7).referencedClass);
    assertNull(((MethodrefConstant) constant8).referencedClass);
    assertNull(((MethodrefConstant) constant9).referencedClass);
    assertNull(((MethodrefConstant) constant10).referencedClass);
    assertNull(((MethodrefConstant) constant11).referencedClass);
    assertNull(((MethodrefConstant) constant12).referencedClass);
    assertNull(((MethodrefConstant) constant13).referencedClass);
    assertNull(((MethodrefConstant) constant14).referencedClass);
    assertNull(((MethodrefConstant) constant15).referencedClass);
    assertNull(((MethodrefConstant) constant16).referencedClass);
    assertNull(((MethodrefConstant) constant17).referencedClass);
    assertNull(((StringConstant) constant34).javaLangStringClass);
    assertNull(((StringConstant) constant34).referencedClass);
    assertNull(((StringConstant) constant34).referencedMember);
    assertNull(((MethodrefConstant) constant2).referencedMethod);
    assertNull(((MethodrefConstant) constant3).referencedMethod);
    assertNull(((MethodrefConstant) constant4).referencedMethod);
    assertNull(((MethodrefConstant) constant5).referencedMethod);
    assertNull(((MethodrefConstant) constant6).referencedMethod);
    assertNull(((MethodrefConstant) constant7).referencedMethod);
    assertNull(((MethodrefConstant) constant8).referencedMethod);
    assertNull(((MethodrefConstant) constant9).referencedMethod);
    assertNull(((MethodrefConstant) constant10).referencedMethod);
    assertNull(((MethodrefConstant) constant11).referencedMethod);
    assertNull(((MethodrefConstant) constant12).referencedMethod);
    assertNull(((MethodrefConstant) constant13).referencedMethod);
    assertNull(((MethodrefConstant) constant14).referencedMethod);
    assertNull(((MethodrefConstant) constant15).referencedMethod);
    assertNull(((MethodrefConstant) constant16).referencedMethod);
    assertNull(((MethodrefConstant) constant17).referencedMethod);
    assertNull(actualConstants[0]);
    assertNull(((StringConstant) constant34).referencedResourceFile);
    assertEquals((byte) -72, ((ConstantInstruction) instruction2).opcode);
    assertEquals((byte) -72, ((ConstantInstruction) instruction3).opcode);
    assertEquals((byte) -72, ((ConstantInstruction) instruction4).opcode);
    assertEquals((byte) -72, ((ConstantInstruction) instruction6).opcode);
    assertEquals((byte) -72, ((ConstantInstruction) instruction8).opcode);
    assertEquals((byte) -72, ((ConstantInstruction) instruction9).opcode);
    assertEquals((byte) -72, ((ConstantInstruction) instruction10).opcode);
    assertEquals((byte) -72, ((ConstantInstruction) instruction11).opcode);
    assertEquals((byte) -72, ((ConstantInstruction) instruction12).opcode);
    assertEquals((byte) -72, ((ConstantInstruction) instruction13).opcode);
    assertEquals((byte) -72, ((ConstantInstruction) instruction14).opcode);
    assertEquals((byte) -72, ((ConstantInstruction) instruction15).opcode);
    assertEquals((byte) -72, ((ConstantInstruction) instruction16).opcode);
    assertEquals((byte) -72, ((ConstantInstruction) instruction17).opcode);
    assertEquals((byte) -72, ((ConstantInstruction) instruction18).opcode);
    assertEquals((byte) -72, ((ConstantInstruction) instruction20).opcode);
    assertEquals((byte) -72, ((ConstantInstruction) instruction22).opcode);
    assertEquals((byte) -72, ((ConstantInstruction) instruction24).opcode);
    assertEquals((byte) -72, ((ConstantInstruction) instruction26).opcode);
    assertEquals((byte) -72, ((ConstantInstruction) instruction29).opcode);
    assertEquals((byte) -72, ((ConstantInstruction) instruction32).opcode);
    assertEquals((byte) -72, ((ConstantInstruction) instruction34).opcode);
    assertEquals(0, ((StringConstant) constant34).referencedResourceId);
    assertEquals(0, ((ConstantInstruction) instruction).constant);
    assertEquals(0, ((ConstantInstruction) instruction2).constant);
    assertEquals(0, ((ConstantInstruction) instruction3).constant);
    assertEquals(0, ((ConstantInstruction) instruction4).constant);
    assertEquals(0, ((ConstantInstruction) instruction5).constant);
    assertEquals(0, ((ConstantInstruction) instruction6).constant);
    assertEquals(0, ((ConstantInstruction) instruction8).constant);
    assertEquals(0, ((ConstantInstruction) instruction9).constant);
    assertEquals(0, ((ConstantInstruction) instruction10).constant);
    assertEquals(0, ((ConstantInstruction) instruction11).constant);
    assertEquals(0, ((ConstantInstruction) instruction12).constant);
    assertEquals(0, ((ConstantInstruction) instruction13).constant);
    assertEquals(0, ((ConstantInstruction) instruction14).constant);
    assertEquals(0, ((ConstantInstruction) instruction15).constant);
    assertEquals(0, ((ConstantInstruction) instruction16).constant);
    assertEquals(0, ((ConstantInstruction) instruction17).constant);
    assertEquals(0, ((ConstantInstruction) instruction18).constant);
    assertEquals(0, ((ConstantInstruction) instruction20).constant);
    assertEquals(0, ((ConstantInstruction) instruction22).constant);
    assertEquals(0, ((ConstantInstruction) instruction24).constant);
    assertEquals(0, ((ConstantInstruction) instruction26).constant);
    assertEquals(0, ((ConstantInstruction) instruction28).constant);
    assertEquals(0, ((ConstantInstruction) instruction29).constant);
    assertEquals(0, ((ConstantInstruction) instruction32).constant);
    assertEquals(0, ((ConstantInstruction) instruction34).constant);
    assertEquals(1, instructionArray3.length);
    assertEquals(1, instructionArray12.length);
    assertEquals(1, instructionArray15.length);
    assertEquals(1, instructionArray18.length);
    assertEquals(1, instructionArray21.length);
    assertEquals(1, instructionArray24.length);
    assertEquals(1, ((NameAndTypeConstant) constant21).u2nameIndex);
    assertEquals(1, ((NameAndTypeConstant) constant31).u2nameIndex);
    assertEquals(10, ((NameAndTypeConstant) constant18).u2nameIndex);
    assertEquals(1073741824, ((ConstantInstruction) instruction).constantIndex);
    assertEquals(1073741825, ((ConstantInstruction) instruction28).constantIndex);
    assertEquals(12, ((ConstantInstruction) instruction4).constantIndex);
    assertEquals(13, ((StringConstant) constant34).u2stringIndex);
    assertEquals(14, ((ConstantInstruction) instruction5).constantIndex);
    assertEquals(15, ((NameAndTypeConstant) constant33).u2nameIndex);
    assertEquals(17, ((ConstantInstruction) instruction18).constantIndex);
    assertEquals(18, ((NameAndTypeConstant) constant19).u2nameIndex);
    assertEquals((byte) 18, ((ConstantInstruction) instruction).opcode);
    assertEquals((byte) 18, ((ConstantInstruction) instruction5).opcode);
    assertEquals((byte) 18, ((ConstantInstruction) instruction28).opcode);
    assertEquals(2, instructionArray2.length);
    assertEquals(2, instructionArray5.length);
    assertEquals(2, instructionArray6.length);
    assertEquals(2, instructionArray8.length);
    assertEquals(2, instructionArray9.length);
    assertEquals(2, instructionArray11.length);
    assertEquals(2, instructionArray14.length);
    assertEquals(2, instructionArray17.length);
    assertEquals(2, instructionArray20.length);
    assertEquals(2, instructionArray23.length);
    assertEquals(2, instructionArray26.length);
    assertEquals(2, instructionArray27.length);
    assertEquals(2, instructionArray29.length);
    assertEquals(2, instructionArray30.length);
    assertEquals(2, instructionArray32.length);
    assertEquals(2, instructionArray33.length);
    assertEquals(2, instructionArray35.length);
    assertEquals(2, instructionArray36.length);
    assertEquals(2, instructionArray38.length);
    assertEquals(2, instructionArray39.length);
    assertEquals(2, instructionArray47.length);
    assertEquals(2, instructionArray48.length);
    assertEquals(2, instructionArray.length);
    assertEquals(2, instructionArray4.length);
    assertEquals(2, instructionArray7.length);
    assertEquals(2, instructionArray10.length);
    assertEquals(2, instructionArray13.length);
    assertEquals(2, instructionArray16.length);
    assertEquals(2, instructionArray19.length);
    assertEquals(2, instructionArray22.length);
    assertEquals(2, instructionArray25.length);
    assertEquals(2, instructionArray28.length);
    assertEquals(2, instructionArray31.length);
    assertEquals(2, instructionArray34.length);
    assertEquals(2, instructionArray37.length);
    assertEquals(2, instructionArray40.length);
    assertEquals(2, instructionArray43.length);
    assertEquals(2, instructionArray46.length);
    assertEquals(2, ((NameAndTypeConstant) constant18).u2descriptorIndex);
    assertEquals(2, ((NameAndTypeConstant) constant19).u2descriptorIndex);
    assertEquals(2, ((NameAndTypeConstant) constant20).u2descriptorIndex);
    assertEquals(2, ((NameAndTypeConstant) constant21).u2descriptorIndex);
    assertEquals(2, ((NameAndTypeConstant) constant33).u2descriptorIndex);
    assertEquals(20, ((ConstantInstruction) instruction20).constantIndex);
    assertEquals(21, ((NameAndTypeConstant) constant20).u2nameIndex);
    assertEquals(23, ((ConstantInstruction) instruction22).constantIndex);
    assertEquals(26, ((ConstantInstruction) instruction24).constantIndex);
    assertEquals(29, ((ConstantInstruction) instruction26).constantIndex);
    assertEquals(3, instructionArray41.length);
    assertEquals(3, instructionArray42.length);
    assertEquals(3, instructionArray44.length);
    assertEquals(3, instructionArray45.length);
    assertEquals(34, ((ConstantInstruction) instruction32).constantIndex);
    assertEquals(36, ((NameAndTypeConstant) constant22).u2descriptorIndex);
    assertEquals(36, ((NameAndTypeConstant) constant24).u2descriptorIndex);
    assertEquals(36, ((NameAndTypeConstant) constant26).u2descriptorIndex);
    assertEquals(36, ((NameAndTypeConstant) constant28).u2descriptorIndex);
    assertEquals(36, ((NameAndTypeConstant) constant32).u2descriptorIndex);
    assertEquals(38, ((ConstantInstruction) instruction34).constantIndex);
    assertEquals(4, ((ClassConstant) constant).u2nameIndex);
    assertEquals(41, ((ConstantInstruction) instruction6).constantIndex);
    assertEquals(42, ((NameAndTypeConstant) constant22).u2nameIndex);
    assertEquals(42, ((NameAndTypeConstant) constant23).u2nameIndex);
    assertEquals(44, ((ConstantInstruction) instruction8).constantIndex);
    assertEquals(45, ((NameAndTypeConstant) constant23).u2descriptorIndex);
    assertEquals(45, ((NameAndTypeConstant) constant25).u2descriptorIndex);
    assertEquals(45, ((NameAndTypeConstant) constant27).u2descriptorIndex);
    assertEquals(45, ((NameAndTypeConstant) constant29).u2descriptorIndex);
    assertEquals(45, ((NameAndTypeConstant) constant30).u2descriptorIndex);
    assertEquals(47, ((ConstantInstruction) instruction9).constantIndex);
    assertEquals(48, ((NameAndTypeConstant) constant24).u2nameIndex);
    assertEquals(48, ((NameAndTypeConstant) constant25).u2nameIndex);
    assertEquals(50, ((ConstantInstruction) instruction10).constantIndex);
    assertEquals(52, ((ConstantInstruction) instruction11).constantIndex);
    assertEquals(53, ((NameAndTypeConstant) constant26).u2nameIndex);
    assertEquals(53, ((NameAndTypeConstant) constant27).u2nameIndex);
    assertEquals(55, ((ConstantInstruction) instruction12).constantIndex);
    assertEquals(57, ((ConstantInstruction) instruction13).constantIndex);
    assertEquals(58, ((NameAndTypeConstant) constant28).u2nameIndex);
    assertEquals(58, ((NameAndTypeConstant) constant29).u2nameIndex);
    assertEquals(6, ((ConstantInstruction) instruction2).constantIndex);
    assertEquals(60, ((ConstantInstruction) instruction14).constantIndex);
    assertEquals(62, ((ConstantInstruction) instruction15).constantIndex);
    assertEquals(63, ((NameAndTypeConstant) constant30).u2nameIndex);
    assertEquals(63, ((NameAndTypeConstant) constant32).u2nameIndex);
    assertEquals(65, ((ConstantInstruction) instruction16).constantIndex);
    assertEquals(67, ((ConstantInstruction) instruction17).constantIndex);
    assertEquals(68, actualConstants.length);
    assertEquals(7, ((NameAndTypeConstant) constant31).u2descriptorIndex);
    assertEquals(9, ((ConstantInstruction) instruction3).constantIndex);
    assertEquals(Integer.SIZE, ((ConstantInstruction) instruction29).constantIndex);
    assertEquals(Short.SIZE, actualSequences.length);
    assertEquals(instruction6, instruction7);
    assertEquals(instruction18, instruction19);
    assertEquals(instruction20, instruction21);
    assertEquals(instruction22, instruction23);
    assertEquals(instruction24, instruction25);
    assertEquals(instruction26, instruction27);
    assertEquals(instruction28, instruction31);
    assertEquals(instruction29, instruction30);
    assertEquals(instruction32, instruction33);
    assertEquals(instruction34, instruction35);
  }
}
