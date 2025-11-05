package proguard;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import java.io.File;
import java.io.IOException;
import org.junit.jupiter.api.Test;

class ArgumentWordReaderDiffblueTest {
  /**
   * Method under test: {@link ArgumentWordReader#nextLine()}
   */
  @Test
  void testNextLine() throws IOException {
    // Arrange, Act and Assert
    assertEquals("Arguments", (new ArgumentWordReader(new String[]{"Arguments"}, Configuration.STD_OUT)).nextLine());
    assertNull((new ArgumentWordReader(new String[]{}, Configuration.STD_OUT)).nextLine());
  }

  /**
   * Method under test: {@link ArgumentWordReader#lineLocationDescription()}
   */
  @Test
  void testLineLocationDescription() {
    // Arrange, Act and Assert
    assertEquals("argument number 0",
        (new ArgumentWordReader(new String[]{"Arguments"}, Configuration.STD_OUT)).lineLocationDescription());
  }

  /**
   * Method under test:
   * {@link ArgumentWordReader#ArgumentWordReader(String[], File)}
   */
  @Test
  void testNewArgumentWordReader() {
    // Arrange
    File baseDir = Configuration.STD_OUT;

    // Act
    ArgumentWordReader actualArgumentWordReader = new ArgumentWordReader(new String[]{"Arguments"}, baseDir);

    // Assert
    assertNull(actualArgumentWordReader.getBaseURL());
    assertSame(baseDir, actualArgumentWordReader.getBaseDir());
  }
}
