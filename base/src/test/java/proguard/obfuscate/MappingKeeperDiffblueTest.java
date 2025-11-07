package proguard.obfuscate;

import static org.junit.jupiter.api.Assertions.assertFalse;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.io.PrintWriter;
import java.io.StringWriter;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import proguard.classfile.ClassPool;
import proguard.classfile.util.WarningPrinter;

class MappingKeeperDiffblueTest {
  /**
   * Test {@link MappingKeeper#processClassMapping(String, String)}.
   * <ul>
   *   <li>Then return {@code false}.</li>
   * </ul>
   * <p>
   * Method under test: {@link MappingKeeper#processClassMapping(String, String)}
   */
  @Test
  @DisplayName("Test processClassMapping(String, String); then return 'false'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean MappingKeeper.processClassMapping(String, String)"})
  void testProcessClassMapping_thenReturnFalse() {
    // Arrange
    ClassPool classPool = new ClassPool();

    // Act and Assert
    assertFalse((new MappingKeeper(classPool, new WarningPrinter(new PrintWriter(new StringWriter()))))
        .processClassMapping("Class Name", "New Class Name"));
  }
}
