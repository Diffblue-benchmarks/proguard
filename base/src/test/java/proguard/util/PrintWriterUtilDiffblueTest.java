package proguard.util;

import static org.junit.jupiter.api.Assertions.assertEquals;
import java.io.File;
import java.nio.file.Paths;
import org.junit.jupiter.api.Test;
import proguard.Configuration;

class PrintWriterUtilDiffblueTest {
  /**
   * Method under test: {@link PrintWriterUtil#fileName(File)}
   */
  @Test
  void testFileName() {
    // Arrange, Act and Assert
    assertEquals("standard output", PrintWriterUtil.fileName(Configuration.STD_OUT));
  }

  /**
   * Method under test: {@link PrintWriterUtil#fileName(File)}
   */
  @Test
  void testFileName2() {
    // Arrange and Act
    String actualFileNameResult = PrintWriterUtil
        .fileName(Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toFile());

    // Assert
    assertEquals(Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toString(), actualFileNameResult);
  }
}
