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
import java.net.URL;
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
  @MethodsUnderTest({"void proguard.WordReader.setBaseDir(java.io.File)"})
  void testSetBaseDir() throws IOException {
    // Arrange
    FileWordReader fileWordReader = new FileWordReader(
        Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toUri().toURL());
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
  @MethodsUnderTest({"void proguard.WordReader.setBaseDir(java.io.File)"})
  void testSetBaseDir2() throws IOException {
    // Arrange
    FileWordReader fileWordReader = new FileWordReader(
        Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toUri().toURL());
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
  @MethodsUnderTest({"java.io.File proguard.WordReader.getBaseDir()"})
  void testGetBaseDir_thenReturnNameIsEmptyString() throws IOException {
    // Arrange
    FileWordReader fileWordReader = new FileWordReader(
        Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toUri().toURL());
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
  @MethodsUnderTest({"java.io.File proguard.WordReader.getBaseDir()"})
  void testGetBaseDir_thenReturnNull() throws IOException {
    // Arrange, Act and Assert
    assertNull(
        (new FileWordReader(Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toUri().toURL())).getBaseDir());
  }

  /**
   * Test {@link WordReader#getBaseURL()}.
   * <p>
   * Method under test: {@link WordReader#getBaseURL()}
   */
  @Test
  @DisplayName("Test getBaseURL()")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"java.net.URL proguard.WordReader.getBaseURL()"})
  void testGetBaseURL() throws IOException {
    // Arrange and Act
    URL actualBaseURL = (new FileWordReader(
        Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toUri().toURL())).getBaseURL();

    // Assert
    String expectedToStringResult = String.join("", "file:",
        Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toString().concat(File.separator));
    assertEquals(expectedToStringResult, actualBaseURL.toString());
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
  @MethodsUnderTest({"java.net.URL proguard.WordReader.getBaseURL()"})
  void testGetBaseURL_thenReturnNull() throws IOException {
    // Arrange
    FileWordReader fileWordReader = new FileWordReader(
        Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toUri().toURL());
    fileWordReader.includeWordReader(new ArgumentWordReader(new String[]{"Arguments"}, Configuration.STD_OUT));

    // Act and Assert
    assertNull(fileWordReader.getBaseURL());
  }

  /**
   * Test {@link WordReader#includeWordReader(WordReader)}.
   * <p>
   * Method under test: {@link WordReader#includeWordReader(WordReader)}
   */
  @Test
  @DisplayName("Test includeWordReader(WordReader)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void proguard.WordReader.includeWordReader(proguard.WordReader)"})
  void testIncludeWordReader() throws IOException {
    // Arrange
    FileWordReader fileWordReader = new FileWordReader(
        Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toUri().toURL());

    // Act
    fileWordReader.includeWordReader(new ArgumentWordReader(new String[]{"Arguments"}, Configuration.STD_OUT));

    // Assert
    assertNull(fileWordReader.getBaseURL());
  }

  /**
   * Test {@link WordReader#nextWord(boolean, boolean)}.
   * <p>
   * Method under test: {@link WordReader#nextWord(boolean, boolean)}
   */
  @Test
  @DisplayName("Test nextWord(boolean, boolean)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"java.lang.String proguard.WordReader.nextWord(boolean, boolean)"})
  void testNextWord() throws IOException {
    // Arrange
    FileWordReader fileWordReader = new FileWordReader(
        Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toUri().toURL());

    // Act and Assert
    assertEquals("java", fileWordReader.nextWord(true, true));
    assertNull(fileWordReader.getBaseDir());
    String expectedToStringResult = String.join("", "file:",
        Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toString().concat(File.separator));
    assertEquals(expectedToStringResult, fileWordReader.getBaseURL().toString());
  }

  /**
   * Test {@link WordReader#nextWord(boolean, boolean)}.
   * <p>
   * Method under test: {@link WordReader#nextWord(boolean, boolean)}
   */
  @Test
  @DisplayName("Test nextWord(boolean, boolean)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"java.lang.String proguard.WordReader.nextWord(boolean, boolean)"})
  void testNextWord2() throws IOException {
    // Arrange
    FileWordReader fileWordReader = new FileWordReader(
        Paths.get(System.getProperty("java.io.tmpdir"), "foo", "foo").toUri().toURL());

    // Act
    String actualNextWordResult = fileWordReader.nextWord(true, true);

    // Assert
    assertNull(fileWordReader.getBaseDir());
    assertNull(actualNextWordResult);
    String expectedToStringResult = String.join("", "file:",
        Paths.get(System.getProperty("java.io.tmpdir"), "foo", "foo").toString().concat(File.separator));
    assertEquals(expectedToStringResult, fileWordReader.getBaseURL().toString());
  }

  /**
   * Test {@link WordReader#nextWord(boolean, boolean)}.
   * <p>
   * Method under test: {@link WordReader#nextWord(boolean, boolean)}
   */
  @Test
  @DisplayName("Test nextWord(boolean, boolean)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"java.lang.String proguard.WordReader.nextWord(boolean, boolean)"})
  void testNextWord3() throws IOException {
    // Arrange
    FileWordReader fileWordReader = new FileWordReader(
        Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toUri().toURL());
    fileWordReader.includeWordReader(new ArgumentWordReader(new String[]{null}, Configuration.STD_OUT));

    // Act and Assert
    assertEquals("java", fileWordReader.nextWord(true, true));
    assertNull(fileWordReader.getBaseDir());
    String expectedToStringResult = String.join("", "file:",
        Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toString().concat(File.separator));
    assertEquals(expectedToStringResult, fileWordReader.getBaseURL().toString());
  }

  /**
   * Test {@link WordReader#nextWord(boolean, boolean)}.
   * <p>
   * Method under test: {@link WordReader#nextWord(boolean, boolean)}
   */
  @Test
  @DisplayName("Test nextWord(boolean, boolean)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"java.lang.String proguard.WordReader.nextWord(boolean, boolean)"})
  void testNextWord4() throws IOException {
    // Arrange
    FileWordReader fileWordReader = new FileWordReader(
        Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toUri().toURL());
    fileWordReader.includeWordReader(new ArgumentWordReader(new String[]{""}, Configuration.STD_OUT));

    // Act and Assert
    assertEquals("java", fileWordReader.nextWord(true, true));
    assertNull(fileWordReader.getBaseDir());
    String expectedToStringResult = String.join("", "file:",
        Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toString().concat(File.separator));
    assertEquals(expectedToStringResult, fileWordReader.getBaseURL().toString());
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
  @MethodsUnderTest({"java.lang.String proguard.WordReader.nextWord(boolean, boolean)"})
  void testNextWord_thenReturnArguments() throws IOException {
    // Arrange
    FileWordReader fileWordReader = new FileWordReader(
        Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toUri().toURL());
    fileWordReader.includeWordReader(new ArgumentWordReader(new String[]{"Arguments"}, Configuration.STD_OUT));

    // Act and Assert
    assertEquals("Arguments", fileWordReader.nextWord(true, true));
  }

  /**
   * Test {@link WordReader#nextWord(boolean, boolean)}.
   * <ul>
   *   <li>When {@code false}.</li>
   * </ul>
   * <p>
   * Method under test: {@link WordReader#nextWord(boolean, boolean)}
   */
  @Test
  @DisplayName("Test nextWord(boolean, boolean); when 'false'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"java.lang.String proguard.WordReader.nextWord(boolean, boolean)"})
  void testNextWord_whenFalse() throws IOException {
    // Arrange
    FileWordReader fileWordReader = new FileWordReader(
        Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toUri().toURL());

    // Act and Assert
    assertEquals("java", fileWordReader.nextWord(false, true));
    assertNull(fileWordReader.getBaseDir());
    String expectedToStringResult = String.join("", "file:",
        Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toString().concat(File.separator));
    assertEquals(expectedToStringResult, fileWordReader.getBaseURL().toString());
  }

  /**
   * Test {@link WordReader#nextWord(boolean, boolean)}.
   * <ul>
   *   <li>When {@code false}.</li>
   * </ul>
   * <p>
   * Method under test: {@link WordReader#nextWord(boolean, boolean)}
   */
  @Test
  @DisplayName("Test nextWord(boolean, boolean); when 'false'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"java.lang.String proguard.WordReader.nextWord(boolean, boolean)"})
  void testNextWord_whenFalse2() throws IOException {
    // Arrange
    FileWordReader fileWordReader = new FileWordReader(
        Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toUri().toURL());

    // Act and Assert
    assertEquals("java", fileWordReader.nextWord(true, false));
    assertNull(fileWordReader.getBaseDir());
    String expectedToStringResult = String.join("", "file:",
        Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toString().concat(File.separator));
    assertEquals(expectedToStringResult, fileWordReader.getBaseURL().toString());
  }

  /**
   * Test {@link WordReader#lastComments()}.
   * <p>
   * Method under test: {@link WordReader#lastComments()}
   */
  @Test
  @DisplayName("Test lastComments()")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"java.lang.String proguard.WordReader.lastComments()"})
  void testLastComments() throws IOException {
    // Arrange, Act and Assert
    assertNull((new FileWordReader(Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toUri().toURL()))
        .lastComments());
  }

  /**
   * Test {@link WordReader#lastComments()}.
   * <p>
   * Method under test: {@link WordReader#lastComments()}
   */
  @Test
  @DisplayName("Test lastComments()")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"java.lang.String proguard.WordReader.lastComments()"})
  void testLastComments2() throws IOException {
    // Arrange
    FileWordReader fileWordReader = new FileWordReader(
        Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toUri().toURL());
    fileWordReader.includeWordReader(new ArgumentWordReader(new String[]{"Arguments"}, Configuration.STD_OUT));

    // Act and Assert
    assertNull(fileWordReader.lastComments());
  }

  /**
   * Test {@link WordReader#locationDescription()}.
   * <p>
   * Method under test: {@link WordReader#locationDescription()}
   */
  @Test
  @DisplayName("Test locationDescription()")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"java.lang.String proguard.WordReader.locationDescription()"})
  void testLocationDescription() throws IOException {
    // Arrange and Act
    String actualLocationDescriptionResult = (new FileWordReader(
        Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toUri().toURL())).locationDescription();

    // Assert
    assertEquals(
        String.join("", "end of line 0 of file 'file:",
            Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toString().concat(File.separator), "'"),
        actualLocationDescriptionResult);
  }

  /**
   * Test {@link WordReader#locationDescription()}.
   * <p>
   * Method under test: {@link WordReader#locationDescription()}
   */
  @Test
  @DisplayName("Test locationDescription()")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"java.lang.String proguard.WordReader.locationDescription()"})
  void testLocationDescription2() throws IOException {
    // Arrange
    FileWordReader fileWordReader = new FileWordReader(
        Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toUri().toURL());
    fileWordReader.includeWordReader(new ArgumentWordReader(new String[]{"Arguments"}, Configuration.STD_OUT));

    // Act
    String actualLocationDescriptionResult = fileWordReader.locationDescription();

    // Assert
    assertEquals(
        String.join("", "end of argument number 0,\n  included from line 0 of file 'file:",
            Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toString().concat(File.separator), "'"),
        actualLocationDescriptionResult);
  }

  /**
   * Test {@link WordReader#close()}.
   * <p>
   * Method under test: {@link WordReader#close()}
   */
  @Test
  @DisplayName("Test close()")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void proguard.WordReader.close()"})
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
  @MethodsUnderTest({"void proguard.WordReader.close()"})
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
  @MethodsUnderTest({"void proguard.WordReader.close()"})
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
  @MethodsUnderTest({"void proguard.WordReader.close()"})
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
