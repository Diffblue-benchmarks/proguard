package proguard;

import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import java.io.File;
import java.io.IOException;
import java.io.LineNumberReader;
import java.io.StringReader;
import org.junit.jupiter.api.Test;

class LineWordReaderDiffblueTest {
  /**
   * Method under test:
   * {@link LineWordReader#LineWordReader(LineNumberReader, String, File)}
   */
  @Test
  void testNewLineWordReader() throws IOException {
    // Arrange
    File baseDir = Configuration.STD_OUT;

    // Act
    LineWordReader actualLineWordReader = new LineWordReader(new LineNumberReader(new StringReader("foo"), 1),
        "The characteristics of someone or something", baseDir);

    // Assert
    assertNull(actualLineWordReader.getBaseURL());
    assertSame(baseDir, actualLineWordReader.getBaseDir());
  }

  /**
   * Method under test:
   * {@link LineWordReader#LineWordReader(LineNumberReader, String, File)}
   */
  @Test
  void testNewLineWordReader2() throws IOException {
    // Arrange
    File baseDir = Configuration.STD_OUT;

    // Act
    LineWordReader actualLineWordReader = new LineWordReader(new LineNumberReader(new StringReader("foo"), 1),
        "The characteristics of someone or something", baseDir);

    // Assert
    assertNull(actualLineWordReader.getBaseURL());
    assertSame(baseDir, actualLineWordReader.getBaseDir());
  }
}
