package proguard.configuration;

import static org.junit.jupiter.api.Assertions.assertEquals;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import proguard.classfile.Clazz;
import proguard.classfile.LibraryClass;
import proguard.classfile.LibraryMethod;
import proguard.classfile.Method;
import proguard.classfile.attribute.CodeAttribute;
import proguard.classfile.constant.ClassConstant;
import proguard.classfile.constant.Constant;
import proguard.classfile.editor.CodeAttributeEditor;
import proguard.classfile.instruction.BranchInstruction;
import proguard.classfile.instruction.Instruction;
import proguard.classfile.util.BranchTargetFinder;

class ConfigurationLoggingInstructionSequenceReplacerDiffblueTest {
  /**
   * Test {@link ConfigurationLoggingInstructionSequenceReplacer#matchedArgument(Clazz, Method,
   * CodeAttribute, int, int)} with {@code clazz}, {@code method}, {@code codeAttribute}, {@code
   * offset}, {@code argument}.
   *
   * <p>Method under test: {@link
   * ConfigurationLoggingInstructionSequenceReplacer#matchedArgument(Clazz, Method, CodeAttribute,
   * int, int)}
   */
  @Test
  @DisplayName(
      "Test matchedArgument(Clazz, Method, CodeAttribute, int, int) with 'clazz', 'method', 'codeAttribute', 'offset', 'argument'")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({
    "int ConfigurationLoggingInstructionSequenceReplacer.matchedArgument(Clazz, Method, CodeAttribute, int, int)"
  })
  void testMatchedArgumentWithClazzMethodCodeAttributeOffsetArgument() {
    // Arrange
    BranchTargetFinder branchTargetFinder = new BranchTargetFinder();
    ConfigurationLoggingInstructionSequenceReplacer
        configurationLoggingInstructionSequenceReplacer =
            new ConfigurationLoggingInstructionSequenceReplacer(
                new Constant[] {new ClassConstant()},
                new Instruction[] {new BranchInstruction((byte) 'A', 1)},
                new Constant[] {new ClassConstant()},
                new Instruction[] {new BranchInstruction((byte) 'A', 1)},
                branchTargetFinder,
                new CodeAttributeEditor());
    LibraryClass clazz = new LibraryClass();
    LibraryMethod method = new LibraryMethod(1, "Name", "Descriptor");

    // Act and Assert
    assertEquals(
        1,
        configurationLoggingInstructionSequenceReplacer.matchedArgument(
            clazz,
            method,
            new CodeAttribute(1),
            2,
            ConfigurationLoggingInstructionSequenceConstants.LOCAL_VARIABLE_INDEX_2));
  }

  /**
   * Test {@link ConfigurationLoggingInstructionSequenceReplacer#matchedArgument(Clazz, Method,
   * CodeAttribute, int, int)} with {@code clazz}, {@code method}, {@code codeAttribute}, {@code
   * offset}, {@code argument}.
   *
   * <ul>
   *   <li>Then return two.
   * </ul>
   *
   * <p>Method under test: {@link
   * ConfigurationLoggingInstructionSequenceReplacer#matchedArgument(Clazz, Method, CodeAttribute,
   * int, int)}
   */
  @Test
  @DisplayName(
      "Test matchedArgument(Clazz, Method, CodeAttribute, int, int) with 'clazz', 'method', 'codeAttribute', 'offset', 'argument'; then return two")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({
    "int ConfigurationLoggingInstructionSequenceReplacer.matchedArgument(Clazz, Method, CodeAttribute, int, int)"
  })
  void testMatchedArgumentWithClazzMethodCodeAttributeOffsetArgument_thenReturnTwo() {
    // Arrange
    BranchTargetFinder branchTargetFinder = new BranchTargetFinder();
    ConfigurationLoggingInstructionSequenceReplacer
        configurationLoggingInstructionSequenceReplacer =
            new ConfigurationLoggingInstructionSequenceReplacer(
                new Constant[] {new ClassConstant()},
                new Instruction[] {new BranchInstruction((byte) 'A', 1)},
                new Constant[] {new ClassConstant()},
                new Instruction[] {new BranchInstruction((byte) 'A', 1)},
                branchTargetFinder,
                new CodeAttributeEditor());
    LibraryClass clazz = new LibraryClass();
    LibraryMethod method = new LibraryMethod(1, "Name", "Descriptor");

    // Act and Assert
    assertEquals(
        2,
        configurationLoggingInstructionSequenceReplacer.matchedArgument(
            clazz,
            method,
            new CodeAttribute(1),
            2,
            ConfigurationLoggingInstructionSequenceConstants.LOCAL_VARIABLE_INDEX_3));
  }

  /**
   * Test {@link ConfigurationLoggingInstructionSequenceReplacer#matchedArgument(Clazz, Method,
   * CodeAttribute, int, int)} with {@code clazz}, {@code method}, {@code codeAttribute}, {@code
   * offset}, {@code argument}.
   *
   * <ul>
   *   <li>Then return zero.
   * </ul>
   *
   * <p>Method under test: {@link
   * ConfigurationLoggingInstructionSequenceReplacer#matchedArgument(Clazz, Method, CodeAttribute,
   * int, int)}
   */
  @Test
  @DisplayName(
      "Test matchedArgument(Clazz, Method, CodeAttribute, int, int) with 'clazz', 'method', 'codeAttribute', 'offset', 'argument'; then return zero")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({
    "int ConfigurationLoggingInstructionSequenceReplacer.matchedArgument(Clazz, Method, CodeAttribute, int, int)"
  })
  void testMatchedArgumentWithClazzMethodCodeAttributeOffsetArgument_thenReturnZero() {
    // Arrange
    BranchTargetFinder branchTargetFinder = new BranchTargetFinder();
    ConfigurationLoggingInstructionSequenceReplacer
        configurationLoggingInstructionSequenceReplacer =
            new ConfigurationLoggingInstructionSequenceReplacer(
                new Constant[] {new ClassConstant()},
                new Instruction[] {new BranchInstruction((byte) 'A', 1)},
                new Constant[] {new ClassConstant()},
                new Instruction[] {new BranchInstruction((byte) 'A', 1)},
                branchTargetFinder,
                new CodeAttributeEditor());
    LibraryClass clazz = new LibraryClass();
    LibraryMethod method = new LibraryMethod(1, "Name", "Descriptor");

    // Act and Assert
    assertEquals(
        0,
        configurationLoggingInstructionSequenceReplacer.matchedArgument(
            clazz,
            method,
            new CodeAttribute(1),
            2,
            ConfigurationLoggingInstructionSequenceConstants.LOCAL_VARIABLE_INDEX_1));
  }

  /**
   * Test {@link ConfigurationLoggingInstructionSequenceReplacer#matchedArgument(Clazz, Method,
   * CodeAttribute, int, int)} with {@code clazz}, {@code method}, {@code codeAttribute}, {@code
   * offset}, {@code argument}.
   *
   * <ul>
   *   <li>When one.
   * </ul>
   *
   * <p>Method under test: {@link
   * ConfigurationLoggingInstructionSequenceReplacer#matchedArgument(Clazz, Method, CodeAttribute,
   * int, int)}
   */
  @Test
  @DisplayName(
      "Test matchedArgument(Clazz, Method, CodeAttribute, int, int) with 'clazz', 'method', 'codeAttribute', 'offset', 'argument'; when one")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({
    "int ConfigurationLoggingInstructionSequenceReplacer.matchedArgument(Clazz, Method, CodeAttribute, int, int)"
  })
  void testMatchedArgumentWithClazzMethodCodeAttributeOffsetArgument_whenOne() {
    // Arrange
    BranchTargetFinder branchTargetFinder = new BranchTargetFinder();
    ConfigurationLoggingInstructionSequenceReplacer
        configurationLoggingInstructionSequenceReplacer =
            new ConfigurationLoggingInstructionSequenceReplacer(
                new Constant[] {new ClassConstant()},
                new Instruction[] {new BranchInstruction((byte) 'A', 1)},
                new Constant[] {new ClassConstant()},
                new Instruction[] {new BranchInstruction((byte) 'A', 1)},
                branchTargetFinder,
                new CodeAttributeEditor());
    LibraryClass clazz = new LibraryClass();
    LibraryMethod method = new LibraryMethod(1, "Name", "Descriptor");

    // Act and Assert
    assertEquals(
        1,
        configurationLoggingInstructionSequenceReplacer.matchedArgument(
            clazz, method, new CodeAttribute(1), 2, 1));
  }
}
