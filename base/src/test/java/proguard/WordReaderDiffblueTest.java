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
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

class WordReaderDiffblueTest {
  /**
   * Test {@link WordReader#setBaseDir(File)}.
   * <p>
   * Method under test: {@link WordReader#setBaseDir(File)}
   */
  @Test
  @DisplayName("Test setBaseDir(File)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void WordReader.setBaseDir(File)"})
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
   * Test {@link WordReader#setBaseDir(File)}.
   * <p>
   * Method under test: {@link WordReader#setBaseDir(File)}
   */
  @Test
  @DisplayName("Test setBaseDir(File)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void WordReader.setBaseDir(File)"})
  void testSetBaseDir2() throws IOException {
    // Arrange
    FileWordReader fileWordReader = new FileWordReader(
        Paths.get(System.getProperty("java.io.tmpdir"), "").toUri().toURL());
    fileWordReader.includeWordReader(new ArgumentWordReader(new String[]{"Arguments"}, Configuration.STD_OUT));
    File baseDir = Configuration.STD_OUT;

    // Act
    fileWordReader.setBaseDir(baseDir);

    // Assert that nothing has changed
    assertSame(baseDir, fileWordReader.getBaseDir());
  }

  /**
   * Test {@link WordReader#getBaseDir()}.
   * <ul>
   *   <li>Then return Name is empty string.</li>
   * </ul>
   * <p>
   * Method under test: {@link WordReader#getBaseDir()}
   */
  @Test
  @DisplayName("Test getBaseDir(); then return Name is empty string")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"File WordReader.getBaseDir()"})
  void testGetBaseDir_thenReturnNameIsEmptyString() throws IOException {
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
   * Test {@link WordReader#getBaseDir()}.
   * <ul>
   *   <li>Then return {@code null}.</li>
   * </ul>
   * <p>
   * Method under test: {@link WordReader#getBaseDir()}
   */
  @Test
  @DisplayName("Test getBaseDir(); then return 'null'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"File WordReader.getBaseDir()"})
  void testGetBaseDir_thenReturnNull() throws IOException {
    // Arrange, Act and Assert
    assertNull((new FileWordReader(Paths.get(System.getProperty("java.io.tmpdir"), "").toUri().toURL())).getBaseDir());
  }

  /**
   * Test {@link WordReader#getBaseURL()}.
   * <ul>
   *   <li>Then return {@code null}.</li>
   * </ul>
   * <p>
   * Method under test: {@link WordReader#getBaseURL()}
   */
  @Test
  @DisplayName("Test getBaseURL(); then return 'null'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"java.net.URL WordReader.getBaseURL()"})
  void testGetBaseURL_thenReturnNull() throws IOException {
    // Arrange
    FileWordReader fileWordReader = new FileWordReader(
        Paths.get(System.getProperty("java.io.tmpdir"), "").toUri().toURL());
    fileWordReader.includeWordReader(new ArgumentWordReader(new String[]{"Arguments"}, Configuration.STD_OUT));

    // Act and Assert
    assertNull(fileWordReader.getBaseURL());
  }

  /**
   * Test {@link WordReader#nextWord(boolean, boolean)}.
   * <ul>
   *   <li>Then return {@code Arguments}.</li>
   * </ul>
   * <p>
   * Method under test: {@link WordReader#nextWord(boolean, boolean)}
   */
  @Test
  @DisplayName("Test nextWord(boolean, boolean); then return 'Arguments'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"String WordReader.nextWord(boolean, boolean)"})
  void testNextWord_thenReturnArguments() throws IOException {
    // Arrange
    FileWordReader fileWordReader = new FileWordReader(
        Paths.get(System.getProperty("java.io.tmpdir"), "").toUri().toURL());
    fileWordReader.includeWordReader(new ArgumentWordReader(new String[]{"Arguments"}, Configuration.STD_OUT));

    // Act and Assert
    assertEquals("Arguments", fileWordReader.nextWord(true, true));
  }

  /**
   * Test {@link WordReader#lastComments()}.
   * <p>
   * Method under test: {@link WordReader#lastComments()}
   */
  @Test
  @DisplayName("Test lastComments()")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"String WordReader.lastComments()"})
  void testLastComments() throws IOException {
    // Arrange, Act and Assert
    assertNull(
        (new FileWordReader(Paths.get(System.getProperty("java.io.tmpdir"), "").toUri().toURL())).lastComments());
  }

  /**
   * Test {@link WordReader#lastComments()}.
   * <p>
   * Method under test: {@link WordReader#lastComments()}
   */
  @Test
  @DisplayName("Test lastComments()")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"String WordReader.lastComments()"})
  void testLastComments2() throws IOException {
    // Arrange
    FileWordReader fileWordReader = new FileWordReader(
        Paths.get(System.getProperty("java.io.tmpdir"), "").toUri().toURL());
    fileWordReader.includeWordReader(new ArgumentWordReader(new String[]{"Arguments"}, Configuration.STD_OUT));

    // Act and Assert
    assertNull(fileWordReader.lastComments());
  }

  /**
   * Test {@link WordReader#close()}.
   * <p>
   * Method under test: {@link WordReader#close()}
   */
  @Test
  @DisplayName("Test close()")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void WordReader.close()"})
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
   * Test {@link WordReader#close()}.
   * <p>
   * Method under test: {@link WordReader#close()}
   */
  @Test
  @DisplayName("Test close()")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void WordReader.close()"})
  void testClose2() throws IOException {
    // Arrange
    ArgumentWordReader argumentWordReader = new ArgumentWordReader(new String[]{"Arguments"}, Configuration.STD_OUT);
    argumentWordReader.includeWordReader(new ArgumentWordReader(new String[]{"Arguments"}, Configuration.STD_OUT));

    // Act
    argumentWordReader.close();

    // Assert that nothing has changed
    File baseDir = argumentWordReader.getBaseDir();
    assertEquals("", baseDir.getName());
    assertFalse(baseDir.isAbsolute());
  }

  /**
   * Test {@link WordReader#close()}.
   * <ul>
   *   <li>Given {@link FileWordReader} {@link LineWordReader#close()} does nothing.</li>
   *   <li>Then calls {@link LineWordReader#close()}.</li>
   * </ul>
   * <p>
   * Method under test: {@link WordReader#close()}
   */
  @Test
  @DisplayName("Test close(); given FileWordReader close() does nothing; then calls close()")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void WordReader.close()"})
  void testClose_givenFileWordReaderCloseDoesNothing_thenCallsClose() throws IOException {
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
   * Test {@link WordReader#close()}.
   * <ul>
   *   <li>Given {@link FileWordReader} {@link LineWordReader#close()} throw {@link IOException#IOException(String)} with {@code foo}.</li>
   *   <li>Then throw {@link IOException}.</li>
   * </ul>
   * <p>
   * Method under test: {@link WordReader#close()}
   */
  @Test
  @DisplayName("Test close(); given FileWordReader close() throw IOException(String) with 'foo'; then throw IOException")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void WordReader.close()"})
  void testClose_givenFileWordReaderCloseThrowIOExceptionWithFoo_thenThrowIOException() throws IOException {
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
