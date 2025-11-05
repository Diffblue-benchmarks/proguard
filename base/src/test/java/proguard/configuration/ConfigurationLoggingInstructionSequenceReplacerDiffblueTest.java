package proguard.configuration;

import static org.junit.jupiter.api.Assertions.assertEquals;
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
   * Method under test:
   * {@link ConfigurationLoggingInstructionSequenceReplacer#matchedArgument(Clazz, Method, CodeAttribute, int, int)}
   */
  @Test
  void testMatchedArgument() {
    // Arrange
    BranchTargetFinder branchTargetFinder = new BranchTargetFinder();
    ConfigurationLoggingInstructionSequenceReplacer configurationLoggingInstructionSequenceReplacer = new ConfigurationLoggingInstructionSequenceReplacer(
        new Constant[]{new ClassConstant()}, new Instruction[]{new BranchInstruction((byte) 'A', 1)},
        new Constant[]{new ClassConstant()}, new Instruction[]{new BranchInstruction((byte) 'A', 1)},
        branchTargetFinder, new CodeAttributeEditor());
    LibraryClass clazz = new LibraryClass();
    LibraryMethod method = new LibraryMethod(1, "Name", "Descriptor");

    // Act and Assert
    assertEquals(1,
        configurationLoggingInstructionSequenceReplacer.matchedArgument(clazz, method, new CodeAttribute(1), 2, 1));
  }

  /**
   * Method under test:
   * {@link ConfigurationLoggingInstructionSequenceReplacer#matchedArgument(Clazz, Method, CodeAttribute, int, int)}
   */
  @Test
  void testMatchedArgument2() {
    // Arrange
    BranchTargetFinder branchTargetFinder = new BranchTargetFinder();
    ConfigurationLoggingInstructionSequenceReplacer configurationLoggingInstructionSequenceReplacer = new ConfigurationLoggingInstructionSequenceReplacer(
        new Constant[]{new ClassConstant()}, new Instruction[]{new BranchInstruction((byte) 'A', 1)},
        new Constant[]{new ClassConstant()}, new Instruction[]{new BranchInstruction((byte) 'A', 1)},
        branchTargetFinder, new CodeAttributeEditor());
    LibraryClass clazz = new LibraryClass();
    LibraryMethod method = new LibraryMethod(1, "Name", "Descriptor");

    // Act and Assert
    assertEquals(0, configurationLoggingInstructionSequenceReplacer.matchedArgument(clazz, method, new CodeAttribute(1),
        2, ConfigurationLoggingInstructionSequenceConstants.LOCAL_VARIABLE_INDEX_1));
  }

  /**
   * Method under test:
   * {@link ConfigurationLoggingInstructionSequenceReplacer#matchedArgument(Clazz, Method, CodeAttribute, int, int)}
   */
  @Test
  void testMatchedArgument3() {
    // Arrange
    BranchTargetFinder branchTargetFinder = new BranchTargetFinder();
    ConfigurationLoggingInstructionSequenceReplacer configurationLoggingInstructionSequenceReplacer = new ConfigurationLoggingInstructionSequenceReplacer(
        new Constant[]{new ClassConstant()}, new Instruction[]{new BranchInstruction((byte) 'A', 1)},
        new Constant[]{new ClassConstant()}, new Instruction[]{new BranchInstruction((byte) 'A', 1)},
        branchTargetFinder, new CodeAttributeEditor());
    LibraryClass clazz = new LibraryClass();
    LibraryMethod method = new LibraryMethod(1, "Name", "Descriptor");

    // Act and Assert
    assertEquals(1, configurationLoggingInstructionSequenceReplacer.matchedArgument(clazz, method, new CodeAttribute(1),
        2, ConfigurationLoggingInstructionSequenceConstants.LOCAL_VARIABLE_INDEX_2));
  }

  /**
   * Method under test:
   * {@link ConfigurationLoggingInstructionSequenceReplacer#matchedArgument(Clazz, Method, CodeAttribute, int, int)}
   */
  @Test
  void testMatchedArgument4() {
    // Arrange
    BranchTargetFinder branchTargetFinder = new BranchTargetFinder();
    ConfigurationLoggingInstructionSequenceReplacer configurationLoggingInstructionSequenceReplacer = new ConfigurationLoggingInstructionSequenceReplacer(
        new Constant[]{new ClassConstant()}, new Instruction[]{new BranchInstruction((byte) 'A', 1)},
        new Constant[]{new ClassConstant()}, new Instruction[]{new BranchInstruction((byte) 'A', 1)},
        branchTargetFinder, new CodeAttributeEditor());
    LibraryClass clazz = new LibraryClass();
    LibraryMethod method = new LibraryMethod(1, "Name", "Descriptor");

    // Act and Assert
    assertEquals(2, configurationLoggingInstructionSequenceReplacer.matchedArgument(clazz, method, new CodeAttribute(1),
        2, ConfigurationLoggingInstructionSequenceConstants.LOCAL_VARIABLE_INDEX_3));
  }
}
