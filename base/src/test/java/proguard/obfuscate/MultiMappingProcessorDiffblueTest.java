package proguard.obfuscate;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.mockito.Mockito.mock;
import com.diffblue.cover.annotations.MethodsUnderTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import proguard.classfile.ClassPool;
import proguard.classfile.util.WarningPrinter;

class MultiMappingProcessorDiffblueTest {
  /**
   * Test {@link MultiMappingProcessor#processClassMapping(String, String)}.
   * <p>
   * Method under test: {@link MultiMappingProcessor#processClassMapping(String, String)}
   */
  @Test
  @DisplayName("Test processClassMapping(String, String)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean MultiMappingProcessor.processClassMapping(String, String)"})
  void testProcessClassMapping() {
    // Arrange, Act and Assert
    assertFalse((new MultiMappingProcessor(
        new MappingProcessor[]{new MappingKeeper(new ClassPool(), mock(WarningPrinter.class))}))
            .processClassMapping("Class Name", "New Class Name"));
  }

  /**
   * Test {@link MultiMappingProcessor#processClassMapping(String, String)}.
   * <ul>
   *   <li>Given {@link WarningPrinter#WarningPrinter(PrintWriter)} with printWriter is {@code null}.</li>
   *   <li>Then return {@code false}.</li>
   * </ul>
   * <p>
   * Method under test: {@link MultiMappingProcessor#processClassMapping(String, String)}
   */
  @Test
  @DisplayName("Test processClassMapping(String, String); given WarningPrinter(PrintWriter) with printWriter is 'null'; then return 'false'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean MultiMappingProcessor.processClassMapping(String, String)"})
  void testProcessClassMapping_givenWarningPrinterWithPrintWriterIsNull_thenReturnFalse() {
    // Arrange
    ClassPool classPool = new ClassPool();

    // Act and Assert
    assertFalse((new MultiMappingProcessor(new MappingProcessor[]{
        new MultiMappingProcessor(new MappingProcessor[]{new MappingKeeper(classPool, new WarningPrinter(null))})}))
            .processClassMapping("Class Name", "New Class Name"));
  }
}
