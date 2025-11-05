package proguard;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import org.junit.jupiter.api.Test;

class WordReaderDiffblueTest {
  /**
   * Method under test: {@link WordReader#setBaseDir(File)}
   */
  @Test
  void testSetBaseDir() throws IOException {
    // Arrange
    FileWordReader fileWordReader = new FileWordReader(
        Paths.get(System.getProperty("java.io.tmpdir"), "").toUri().toURL());
    File baseDir = Configuration.STD_OUT;

    // Act
    fileWordReader.setBaseDir(baseDir);

    // Assert
    assertSame(baseDir, fileWordReader.getBaseDir());
  }

  /**
   * Method under test: {@link WordReader#setBaseDir(File)}
   */
  @Test
  void testSetBaseDir2() throws IOException {
    // Arrange
    FileWordReader fileWordReader = new FileWordReader(
        Paths.get(System.getProperty("java.io.tmpdir"), "").toUri().toURL());
    fileWordReader.includeWordReader(new ArgumentWordReader(new String[]{"Arguments"}, Configuration.STD_OUT));
    File baseDir = Configuration.STD_OUT;

    // Act
    fileWordReader.setBaseDir(baseDir);

    // Assert
    assertSame(baseDir, fileWordReader.getBaseDir());
  }

  /**
   * Method under test: {@link WordReader#getBaseDir()}
   */
  @Test
  void testGetBaseDir() throws IOException {
    // Arrange, Act and Assert
    assertNull((new FileWordReader(Paths.get(System.getProperty("java.io.tmpdir"), "").toUri().toURL())).getBaseDir());
  }

  /**
   * Method under test: {@link WordReader#getBaseDir()}
   */
  @Test
  void testGetBaseDir2() throws IOException {
    // Arrange
    FileWordReader fileWordReader = new FileWordReader(
        Paths.get(System.getProperty("java.io.tmpdir"), "").toUri().toURL());
    fileWordReader.includeWordReader(new ArgumentWordReader(new String[]{"Arguments"}, Configuration.STD_OUT));

    // Act
    File actualBaseDir = fileWordReader.getBaseDir();

    // Assert
    assertEquals("", actualBaseDir.getName());
    assertFalse(actualBaseDir.isAbsolute());
  }

  /**
   * Method under test: {@link WordReader#getBaseURL()}
   */
  @Test
  void testGetBaseURL() throws IOException {
    // Arrange
    FileWordReader fileWordReader = new FileWordReader(
        Paths.get(System.getProperty("java.io.tmpdir"), "").toUri().toURL());
    fileWordReader.includeWordReader(new ArgumentWordReader(new String[]{"Arguments"}, Configuration.STD_OUT));

    // Act and Assert
    assertNull(fileWordReader.getBaseURL());
  }

  /**
   * Method under test: {@link WordReader#includeWordReader(WordReader)}
   */
  @Test
  void testIncludeWordReader() throws IOException {
    // Arrange
    FileWordReader fileWordReader = new FileWordReader(
        Paths.get(System.getProperty("java.io.tmpdir"), "").toUri().toURL());

    // Act
    fileWordReader.includeWordReader(mock(ArgumentWordReader.class));

    // Assert
    assertNull(fileWordReader.getBaseURL());
  }

  /**
   * Method under test: {@link WordReader#nextWord(boolean, boolean)}
   */
  @Test
  void testNextWord() throws IOException {
    // Arrange
    FileWordReader fileWordReader = new FileWordReader(
        Paths.get(System.getProperty("java.io.tmpdir"), "").toUri().toURL());
    fileWordReader.includeWordReader(new ArgumentWordReader(new String[]{"Arguments"}, Configuration.STD_OUT));

    // Act and Assert
    assertEquals("Arguments", fileWordReader.nextWord(true, true));
    assertNull(fileWordReader.getBaseURL());
  }

  /**
   * Method under test: {@link WordReader#lastComments()}
   */
  @Test
  void testLastComments() throws IOException {
    // Arrange, Act and Assert
    assertNull(
        (new FileWordReader(Paths.get(System.getProperty("java.io.tmpdir"), "").toUri().toURL())).lastComments());
  }

  /**
   * Method under test: {@link WordReader#lastComments()}
   */
  @Test
  void testLastComments2() throws IOException {
    // Arrange
    FileWordReader fileWordReader = new FileWordReader(
        Paths.get(System.getProperty("java.io.tmpdir"), "").toUri().toURL());
    fileWordReader.includeWordReader(new ArgumentWordReader(new String[]{"Arguments"}, Configuration.STD_OUT));

    // Act and Assert
    assertNull(fileWordReader.lastComments());
  }

  /**
   * Method under test: {@link WordReader#close()}
   */
  @Test
  void testClose() throws IOException {
    // Arrange
    ArgumentWordReader argumentWordReader = new ArgumentWordReader(new String[]{"Arguments"}, Configuration.STD_OUT);

    // Act
    argumentWordReader.close();

    // Assert that nothing has changed
    File baseDir = argumentWordReader.getBaseDir();
    assertEquals("", baseDir.getName());
    assertFalse(baseDir.isAbsolute());
  }

  /**
   * Method under test: {@link WordReader#close()}
   */
  @Test
  void testClose2() throws IOException {
    // Arrange
    ArgumentWordReader argumentWordReader = new ArgumentWordReader(new String[]{"Arguments"}, Configuration.STD_OUT);
    argumentWordReader.includeWordReader(new ArgumentWordReader(new String[]{"Arguments"}, Configuration.STD_OUT));

    // Act
    argumentWordReader.close();

    // Assert
    File baseDir = argumentWordReader.getBaseDir();
    assertEquals("", baseDir.getName());
    assertFalse(baseDir.isAbsolute());
  }

  /**
   * Method under test: {@link WordReader#close()}
   */
  @Test
  void testClose3() throws IOException {
    // Arrange
    FileWordReader newIncludeWordReader = mock(FileWordReader.class);
    doNothing().when(newIncludeWordReader).close();

    ArgumentWordReader argumentWordReader = new ArgumentWordReader(new String[]{"Arguments"}, Configuration.STD_OUT);
    argumentWordReader.includeWordReader(newIncludeWordReader);

    // Act
    argumentWordReader.close();

    // Assert
    verify(newIncludeWordReader).close();
    File baseDir = argumentWordReader.getBaseDir();
    assertEquals("", baseDir.getName());
    assertFalse(baseDir.isAbsolute());
  }

  /**
   * Method under test: {@link WordReader#close()}
   */
  @Test
  void testClose4() throws IOException {
    // Arrange
    FileWordReader newIncludeWordReader = mock(FileWordReader.class);
    doThrow(new IOException("foo")).when(newIncludeWordReader).close();

    ArgumentWordReader argumentWordReader = new ArgumentWordReader(new String[]{"Arguments"}, Configuration.STD_OUT);
    argumentWordReader.includeWordReader(newIncludeWordReader);

    // Act and Assert
    assertThrows(IOException.class, () -> argumentWordReader.close());
    verify(newIncludeWordReader).close();
  }
}
