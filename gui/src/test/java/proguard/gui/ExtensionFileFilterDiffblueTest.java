package proguard.gui;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.io.File;
import java.nio.file.Paths;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

class ExtensionFileFilterDiffblueTest {
  /**
   * Test getters and setters.
   * <p>
   * Methods under test:
   * <ul>
   *   <li>{@link ExtensionFileFilter#ExtensionFileFilter(String, String[])}
   *   <li>{@link ExtensionFileFilter#getDescription()}
   * </ul>
   */
  @Test
  @DisplayName("Test getters and setters")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void ExtensionFileFilter.<init>(String, String[])",
      "String ExtensionFileFilter.getDescription()"})
  void testGettersAndSetters() {
    // Arrange, Act and Assert
    assertEquals("The characteristics of someone or something",
        (new ExtensionFileFilter("The characteristics of someone or something", new String[]{"Extensions"}))
            .getDescription());
  }

  /**
   * Test {@link ExtensionFileFilter#accept(File)}.
   * <p>
   * Method under test: {@link ExtensionFileFilter#accept(File)}
   */
  @Test
  @DisplayName("Test accept(File)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean ExtensionFileFilter.accept(File)"})
  void testAccept() {
    // Arrange
    ExtensionFileFilter extensionFileFilter = new ExtensionFileFilter("The characteristics of someone or something",
        new String[]{""});

    // Act and Assert
    assertTrue(extensionFileFilter.accept(Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toFile()));
  }

  /**
   * Test {@link ExtensionFileFilter#accept(File)}.
   * <ul>
   *   <li>Then return {@code false}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ExtensionFileFilter#accept(File)}
   */
  @Test
  @DisplayName("Test accept(File); then return 'false'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean ExtensionFileFilter.accept(File)"})
  void testAccept_thenReturnFalse() {
    // Arrange
    ExtensionFileFilter extensionFileFilter = new ExtensionFileFilter("The characteristics of someone or something",
        new String[]{"Extensions"});

    // Act and Assert
    assertFalse(extensionFileFilter.accept(Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toFile()));
  }

  /**
   * Test {@link ExtensionFileFilter#accept(File)}.
   * <ul>
   *   <li>When Property is {@code java.io.tmpdir} is empty string toFile.</li>
   *   <li>Then return {@code true}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ExtensionFileFilter#accept(File)}
   */
  @Test
  @DisplayName("Test accept(File); when Property is 'java.io.tmpdir' is empty string toFile; then return 'true'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean ExtensionFileFilter.accept(File)"})
  void testAccept_whenPropertyIsJavaIoTmpdirIsEmptyStringToFile_thenReturnTrue() {
    // Arrange
    ExtensionFileFilter extensionFileFilter = new ExtensionFileFilter("The characteristics of someone or something",
        new String[]{"Extensions"});

    // Act and Assert
    assertTrue(extensionFileFilter.accept(Paths.get(System.getProperty("java.io.tmpdir"), "").toFile()));
  }
}
