package proguard.gui;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import java.io.File;
import java.nio.file.Paths;
import org.junit.jupiter.api.Test;

class ExtensionFileFilterDiffblueTest {
  /**
   * Method under test: {@link ExtensionFileFilter#accept(File)}
   */
  @Test
  void testAccept() {
    // Arrange
    ExtensionFileFilter extensionFileFilter = new ExtensionFileFilter("The characteristics of someone or something",
        new String[]{"Extensions"});

    // Act and Assert
    assertFalse(extensionFileFilter.accept(Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toFile()));
  }

  /**
   * Method under test: {@link ExtensionFileFilter#accept(File)}
   */
  @Test
  void testAccept2() {
    // Arrange
    ExtensionFileFilter extensionFileFilter = new ExtensionFileFilter("The characteristics of someone or something",
        new String[]{""});

    // Act and Assert
    assertTrue(extensionFileFilter.accept(Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toFile()));
  }

  /**
   * Method under test: {@link ExtensionFileFilter#accept(File)}
   */
  @Test
  void testAccept3() {
    // Arrange
    ExtensionFileFilter extensionFileFilter = new ExtensionFileFilter("The characteristics of someone or something",
        new String[]{"Extensions"});

    // Act and Assert
    assertTrue(extensionFileFilter.accept(Paths.get(System.getProperty("java.io.tmpdir"), "").toFile()));
  }

  /**
   * Methods under test:
   * <ul>
   *   <li>{@link ExtensionFileFilter#ExtensionFileFilter(String, String[])}
   *   <li>{@link ExtensionFileFilter#getDescription()}
   * </ul>
   */
  @Test
  void testGettersAndSetters() {
    // Arrange, Act and Assert
    assertEquals("The characteristics of someone or something",
        (new ExtensionFileFilter("The characteristics of someone or something", new String[]{"Extensions"}))
            .getDescription());
  }
}
