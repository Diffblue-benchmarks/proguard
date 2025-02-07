package proguard.configuration;

import static org.junit.jupiter.api.Assertions.assertEquals;
import com.diffblue.cover.annotations.MethodsUnderTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import proguard.classfile.ClassPool;

class ConfigurationLoggingInstructionSequenceConstantsDiffblueTest {
  /**
   * Test {@link ConfigurationLoggingInstructionSequenceConstants#ConfigurationLoggingInstructionSequenceConstants(ClassPool, ClassPool)}.
   * <p>
   * Method under test: {@link ConfigurationLoggingInstructionSequenceConstants#ConfigurationLoggingInstructionSequenceConstants(ClassPool, ClassPool)}
   */
  @Test
  @DisplayName("Test new ConfigurationLoggingInstructionSequenceConstants(ClassPool, ClassPool)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "void proguard.configuration.ConfigurationLoggingInstructionSequenceConstants.<init>(proguard.classfile.ClassPool, proguard.classfile.ClassPool)"})
  void testNewConfigurationLoggingInstructionSequenceConstants() {
    // Arrange
    ClassPool programClassPool = new ClassPool();

    // Act
    ConfigurationLoggingInstructionSequenceConstants actualConfigurationLoggingInstructionSequenceConstants = new ConfigurationLoggingInstructionSequenceConstants(
        programClassPool, new ClassPool());

    // Assert
    assertEquals(106, actualConfigurationLoggingInstructionSequenceConstants.CONSTANTS.length);
    assertEquals(ConfigurationLogger.ALL_PUBLIC_FIELDS_KEPT,
        actualConfigurationLoggingInstructionSequenceConstants.RESOURCE.length);
  }

  /**
   * Test {@link ConfigurationLoggingInstructionSequenceConstants#ConfigurationLoggingInstructionSequenceConstants(ClassPool, ClassPool)}.
   * <p>
   * Method under test: {@link ConfigurationLoggingInstructionSequenceConstants#ConfigurationLoggingInstructionSequenceConstants(ClassPool, ClassPool)}
   */
  @Test
  @DisplayName("Test new ConfigurationLoggingInstructionSequenceConstants(ClassPool, ClassPool)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "void proguard.configuration.ConfigurationLoggingInstructionSequenceConstants.<init>(proguard.classfile.ClassPool, proguard.classfile.ClassPool)"})
  void testNewConfigurationLoggingInstructionSequenceConstants2() {
    // Arrange and Act
    ConfigurationLoggingInstructionSequenceConstants actualConfigurationLoggingInstructionSequenceConstants = new ConfigurationLoggingInstructionSequenceConstants(
        null, new ClassPool());

    // Assert
    assertEquals(106, actualConfigurationLoggingInstructionSequenceConstants.CONSTANTS.length);
    assertEquals(ConfigurationLogger.ALL_PUBLIC_FIELDS_KEPT,
        actualConfigurationLoggingInstructionSequenceConstants.RESOURCE.length);
  }
}
