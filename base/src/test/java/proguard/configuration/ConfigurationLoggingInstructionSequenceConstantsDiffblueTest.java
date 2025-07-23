package proguard.configuration;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import proguard.classfile.ClassPool;
import proguard.classfile.LibraryClass;
import proguard.classfile.ProgramClass;
import proguard.classfile.constant.Constant;
import proguard.classfile.constant.Utf8Constant;

class ConfigurationLoggingInstructionSequenceConstantsDiffblueTest {
  /**
   * Test {@link
   * ConfigurationLoggingInstructionSequenceConstants#ConfigurationLoggingInstructionSequenceConstants(ClassPool,
   * ClassPool)}.
   *
   * <p>Method under test: {@link
   * ConfigurationLoggingInstructionSequenceConstants#ConfigurationLoggingInstructionSequenceConstants(ClassPool,
   * ClassPool)}
   */
  @Test
  @DisplayName("Test new ConfigurationLoggingInstructionSequenceConstants(ClassPool, ClassPool)")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void ConfigurationLoggingInstructionSequenceConstants.<init>(ClassPool, ClassPool)"
  })
  void testNewConfigurationLoggingInstructionSequenceConstants() {
    // Arrange
    ClassPool programClassPool = new ClassPool();

    // Act and Assert
    Constant[] constantArray =
        new ConfigurationLoggingInstructionSequenceConstants(programClassPool, new ClassPool())
            .CONSTANTS;
    assertTrue(constantArray[1] instanceof Utf8Constant);
    assertTrue(constantArray[10] instanceof Utf8Constant);
    assertTrue(constantArray[100] instanceof Utf8Constant);
    assertTrue(constantArray[103] instanceof Utf8Constant);
    assertTrue(constantArray[13] instanceof Utf8Constant);
    assertTrue(constantArray[18] instanceof Utf8Constant);
    assertTrue(constantArray[2] instanceof Utf8Constant);
    assertTrue(constantArray[21] instanceof Utf8Constant);
    assertTrue(constantArray[24] instanceof Utf8Constant);
    assertTrue(constantArray[4] instanceof Utf8Constant);
    assertTrue(constantArray[7] instanceof Utf8Constant);
    assertTrue(constantArray[8] instanceof Utf8Constant);
    assertTrue(constantArray[83] instanceof Utf8Constant);
    assertTrue(constantArray[84] instanceof Utf8Constant);
    assertTrue(constantArray[87] instanceof Utf8Constant);
    assertTrue(constantArray[90] instanceof Utf8Constant);
    assertTrue(constantArray[93] instanceof Utf8Constant);
    assertTrue(constantArray[94] instanceof Utf8Constant);
    assertTrue(constantArray[97] instanceof Utf8Constant);
    assertTrue(constantArray[ConfigurationLogger.ALL_PUBLIC_FIELDS_KEPT] instanceof Utf8Constant);
    assertEquals(106, constantArray.length);
  }

  /**
   * Test {@link
   * ConfigurationLoggingInstructionSequenceConstants#ConfigurationLoggingInstructionSequenceConstants(ClassPool,
   * ClassPool)}.
   *
   * <p>Method under test: {@link
   * ConfigurationLoggingInstructionSequenceConstants#ConfigurationLoggingInstructionSequenceConstants(ClassPool,
   * ClassPool)}
   */
  @Test
  @DisplayName("Test new ConfigurationLoggingInstructionSequenceConstants(ClassPool, ClassPool)")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void ConfigurationLoggingInstructionSequenceConstants.<init>(ClassPool, ClassPool)"
  })
  void testNewConfigurationLoggingInstructionSequenceConstants2() {
    // Arrange and Act
    ConfigurationLoggingInstructionSequenceConstants
        actualConfigurationLoggingInstructionSequenceConstants =
            new ConfigurationLoggingInstructionSequenceConstants(null, new ClassPool());

    // Assert
    assertEquals(106, actualConfigurationLoggingInstructionSequenceConstants.CONSTANTS.length);
    assertEquals(
        ConfigurationLogger.ALL_PUBLIC_FIELDS_KEPT,
        actualConfigurationLoggingInstructionSequenceConstants.RESOURCE.length);
  }

  /**
   * Test {@link
   * ConfigurationLoggingInstructionSequenceConstants#ConfigurationLoggingInstructionSequenceConstants(ClassPool,
   * ClassPool)}.
   *
   * <p>Method under test: {@link
   * ConfigurationLoggingInstructionSequenceConstants#ConfigurationLoggingInstructionSequenceConstants(ClassPool,
   * ClassPool)}
   */
  @Test
  @DisplayName("Test new ConfigurationLoggingInstructionSequenceConstants(ClassPool, ClassPool)")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void ConfigurationLoggingInstructionSequenceConstants.<init>(ClassPool, ClassPool)"
  })
  void testNewConfigurationLoggingInstructionSequenceConstants3() {
    // Arrange
    ClassPool programClassPool = new ClassPool();
    programClassPool.addClass(
        "java/lang/Class",
        new LibraryClass(
            ConfigurationLogger.ALL_PUBLIC_FIELDS_KEPT, "java/lang/Class", "java/lang/Class"));

    // Act and Assert
    assertEquals(
        106,
        new ConfigurationLoggingInstructionSequenceConstants(programClassPool, new ClassPool())
            .CONSTANTS
            .length);
  }

  /**
   * Test {@link
   * ConfigurationLoggingInstructionSequenceConstants#ConfigurationLoggingInstructionSequenceConstants(ClassPool,
   * ClassPool)}.
   *
   * <ul>
   *   <li>Given {@link ProgramClass#ProgramClass()}.
   * </ul>
   *
   * <p>Method under test: {@link
   * ConfigurationLoggingInstructionSequenceConstants#ConfigurationLoggingInstructionSequenceConstants(ClassPool,
   * ClassPool)}
   */
  @Test
  @DisplayName(
      "Test new ConfigurationLoggingInstructionSequenceConstants(ClassPool, ClassPool); given ProgramClass()")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void ConfigurationLoggingInstructionSequenceConstants.<init>(ClassPool, ClassPool)"
  })
  void testNewConfigurationLoggingInstructionSequenceConstants_givenProgramClass() {
    // Arrange
    ClassPool programClassPool = new ClassPool();
    programClassPool.addClass("java/lang/Class", new ProgramClass());

    // Act and Assert
    assertEquals(
        106,
        new ConfigurationLoggingInstructionSequenceConstants(programClassPool, new ClassPool())
            .CONSTANTS
            .length);
  }
}
