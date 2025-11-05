package proguard.obfuscate;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.mockito.Mockito.mock;
import org.junit.jupiter.api.Test;
import proguard.classfile.ClassPool;
import proguard.classfile.util.WarningPrinter;

class MultiMappingProcessorDiffblueTest {
  /**
   * Method under test:
   * {@link MultiMappingProcessor#processClassMapping(String, String)}
   */
  @Test
  void testProcessClassMapping() {
    // Arrange, Act and Assert
    assertFalse((new MultiMappingProcessor(
        new MappingProcessor[]{new MappingKeeper(new ClassPool(), mock(WarningPrinter.class))}))
            .processClassMapping("Class Name", "New Class Name"));
  }

  /**
   * Method under test:
   * {@link MultiMappingProcessor#processClassMapping(String, String)}
   */
  @Test
  void testProcessClassMapping2() {
    // Arrange
    ClassPool classPool = new ClassPool();

    // Act and Assert
    assertFalse((new MultiMappingProcessor(new MappingProcessor[]{
        new MultiMappingProcessor(new MappingProcessor[]{new MappingKeeper(classPool, new WarningPrinter(null))})}))
            .processClassMapping("Class Name", "New Class Name"));
  }
}
