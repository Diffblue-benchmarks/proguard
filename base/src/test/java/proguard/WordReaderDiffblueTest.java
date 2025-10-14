package proguard;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.ArgumentMatchers.isNull;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Paths;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

class WordReaderDiffblueTest {
  /**
   * Test {@link WordReader#setBaseDir(File)}.
   *
   * <ul>
   *   <li>Given {@link ArgumentWordReader} {@link ArgumentWordReader#setBaseDir(File)} does
   *       nothing.
   *   <li>Then calls {@link ArgumentWordReader#setBaseDir(File)}.
   * </ul>
   *
   * <p>Method under test: {@link WordReader#setBaseDir(File)}
   */
  @Test
  @DisplayName(
      "Test setBaseDir(File); given ArgumentWordReader setBaseDir(File) does nothing; then calls setBaseDir(File)")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void WordReader.setBaseDir(File)"})
  void testSetBaseDir_givenArgumentWordReaderSetBaseDirDoesNothing_thenCallsSetBaseDir() {
    // Arrange
    ArgumentWordReader newIncludeWordReader = mock(ArgumentWordReader.class);
    doNothing().when(newIncludeWordReader).setBaseDir(Mockito.<File>any());
    String[] arguments = new String[] {"Arguments"};

    ArgumentWordReader argumentWordReader =
        new ArgumentWordReader(arguments, Configuration.STD_OUT);
    argumentWordReader.includeWordReader(newIncludeWordReader);

    // Act
    argumentWordReader.setBaseDir(Configuration.STD_OUT);

    // Assert
    verify(newIncludeWordReader).setBaseDir(isA(File.class));
  }

  /**
   * Test {@link WordReader#getBaseDir()}.
   *
   * <p>Method under test: {@link WordReader#getBaseDir()}
   */
  @Test
  @DisplayName("Test getBaseDir()")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"File WordReader.getBaseDir()"})
  void testGetBaseDir() {
    // Arrange
    String[] arguments = new String[] {"Arguments"};

    ArgumentWordReader newIncludeWordReader =
        new ArgumentWordReader(arguments, Configuration.STD_OUT);
    newIncludeWordReader.includeWordReader(null);
    String[] arguments2 = new String[] {"Arguments"};

    ArgumentWordReader argumentWordReader =
        new ArgumentWordReader(arguments2, Configuration.STD_OUT);
    argumentWordReader.includeWordReader(newIncludeWordReader);

    // Act
    File actualBaseDir = argumentWordReader.getBaseDir();

    // Assert
    assertEquals("", actualBaseDir.getName());
    assertFalse(actualBaseDir.isAbsolute());
  }

  /**
   * Test {@link WordReader#getBaseDir()}.
   *
   * <ul>
   *   <li>Then return Name is empty string.
   * </ul>
   *
   * <p>Method under test: {@link WordReader#getBaseDir()}
   */
  @Test
  @DisplayName("Test getBaseDir(); then return Name is empty string")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"File WordReader.getBaseDir()"})
  void testGetBaseDir_thenReturnNameIsEmptyString() {
    // Arrange
    String[] arguments = new String[] {"Arguments"};

    ArgumentWordReader argumentWordReader =
        new ArgumentWordReader(arguments, Configuration.STD_OUT);
    argumentWordReader.includeWordReader(null);

    // Act
    File actualBaseDir = argumentWordReader.getBaseDir();

    // Assert
    assertEquals("", actualBaseDir.getName());
    assertFalse(actualBaseDir.isAbsolute());
  }

  /**
   * Test {@link WordReader#getBaseURL()}.
   *
   * <p>Method under test: {@link WordReader#getBaseURL()}
   */
  @Test
  @DisplayName("Test getBaseURL()")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"URL WordReader.getBaseURL()"})
  void testGetBaseURL() {
    // Arrange
    String[] arguments = new String[] {"Arguments"};

    ArgumentWordReader newIncludeWordReader =
        new ArgumentWordReader(arguments, Configuration.STD_OUT);
    newIncludeWordReader.includeWordReader(null);
    String[] arguments2 = new String[] {"Arguments"};

    ArgumentWordReader argumentWordReader =
        new ArgumentWordReader(arguments2, Configuration.STD_OUT);
    argumentWordReader.includeWordReader(newIncludeWordReader);

    // Act and Assert
    assertNull(argumentWordReader.getBaseURL());
  }

  /**
   * Test {@link WordReader#getBaseURL()}.
   *
   * <ul>
   *   <li>Then return {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link WordReader#getBaseURL()}
   */
  @Test
  @DisplayName("Test getBaseURL(); then return 'null'")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"URL WordReader.getBaseURL()"})
  void testGetBaseURL_thenReturnNull() {
    // Arrange
    String[] arguments = new String[] {"Arguments"};

    ArgumentWordReader argumentWordReader =
        new ArgumentWordReader(arguments, Configuration.STD_OUT);
    argumentWordReader.includeWordReader(null);

    // Act and Assert
    assertNull(argumentWordReader.getBaseURL());
  }

  /**
   * Test {@link WordReader#nextWord(boolean, boolean)}.
   *
   * <ul>
   *   <li>Then return {@code Arguments}.
   * </ul>
   *
   * <p>Method under test: {@link WordReader#nextWord(boolean, boolean)}
   */
  @Test
  @DisplayName("Test nextWord(boolean, boolean); then return 'Arguments'")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"String WordReader.nextWord(boolean, boolean)"})
  void testNextWord_thenReturnArguments() throws IOException {
    // Arrange
    URL url = Paths.get(System.getProperty("java.io.tmpdir"), "").toUri().toURL();

    FileWordReader fileWordReader = new FileWordReader(url);
    String[] arguments = new String[] {"Arguments"};
    ArgumentWordReader newIncludeWordReader =
        new ArgumentWordReader(arguments, Configuration.STD_OUT);
    fileWordReader.includeWordReader(newIncludeWordReader);

    // Act and Assert
    assertEquals("Arguments", fileWordReader.nextWord(true, true));
  }

  /**
   * Test {@link WordReader#lastComments()}.
   *
   * <p>Method under test: {@link WordReader#lastComments()}
   */
  @Test
  @DisplayName("Test lastComments()")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"String WordReader.lastComments()"})
  void testLastComments() throws IOException {
    // Arrange
    String[] arguments = new String[] {"Arguments"};

    ArgumentWordReader newIncludeWordReader =
        new ArgumentWordReader(arguments, Configuration.STD_OUT);
    newIncludeWordReader.includeWordReader(null);
    String[] arguments2 = new String[] {"Arguments"};

    ArgumentWordReader argumentWordReader =
        new ArgumentWordReader(arguments2, Configuration.STD_OUT);
    argumentWordReader.includeWordReader(newIncludeWordReader);

    // Act and Assert
    assertNull(argumentWordReader.lastComments());
  }

  /**
   * Test {@link WordReader#lastComments()}.
   *
   * <ul>
   *   <li>Then return {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link WordReader#lastComments()}
   */
  @Test
  @DisplayName("Test lastComments(); then return 'null'")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"String WordReader.lastComments()"})
  void testLastComments_thenReturnNull() throws IOException {
    // Arrange
    String[] arguments = new String[] {"Arguments"};

    ArgumentWordReader argumentWordReader =
        new ArgumentWordReader(arguments, Configuration.STD_OUT);
    argumentWordReader.includeWordReader(null);

    // Act and Assert
    assertNull(argumentWordReader.lastComments());
  }

  /**
   * Test {@link WordReader#close()}.
   *
   * <p>Method under test: {@link WordReader#close()}
   */
  @Test
  @DisplayName("Test close()")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void WordReader.close()"})
  void testClose() throws IOException {
    // Arrange
    String[] arguments = new String[] {"Arguments"};
    ArgumentWordReader argumentWordReader =
        new ArgumentWordReader(arguments, Configuration.STD_OUT);

    // Act
    argumentWordReader.close();

    // Assert that nothing has changed
    File baseDir = argumentWordReader.getBaseDir();
    assertEquals("", baseDir.getName());
    assertFalse(baseDir.isAbsolute());
  }

  /**
   * Test {@link WordReader#close()}.
   *
   * <p>Method under test: {@link WordReader#close()}
   */
  @Test
  @DisplayName("Test close()")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void WordReader.close()"})
  void testClose2() throws IOException {
    // Arrange
    String[] arguments = new String[] {"Arguments"};

    ArgumentWordReader newIncludeWordReader =
        new ArgumentWordReader(arguments, Configuration.STD_OUT);
    newIncludeWordReader.includeWordReader(null);
    String[] arguments2 = new String[] {"Arguments"};

    ArgumentWordReader argumentWordReader =
        new ArgumentWordReader(arguments2, Configuration.STD_OUT);
    argumentWordReader.includeWordReader(newIncludeWordReader);

    // Act
    argumentWordReader.close();

    // Assert that nothing has changed
    File baseDir = argumentWordReader.getBaseDir();
    assertEquals("", baseDir.getName());
    assertFalse(baseDir.isAbsolute());
  }

  /**
   * Test {@link WordReader#close()}.
   *
   * <ul>
   *   <li>Given {@link FileWordReader} {@link FileWordReader#close()} does nothing.
   *   <li>Then calls {@link FileWordReader#close()}.
   * </ul>
   *
   * <p>Method under test: {@link WordReader#close()}
   */
  @Test
  @DisplayName("Test close(); given FileWordReader close() does nothing; then calls close()")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void WordReader.close()"})
  void testClose_givenFileWordReaderCloseDoesNothing_thenCallsClose() throws IOException {
    // Arrange
    FileWordReader newIncludeWordReader = mock(FileWordReader.class);
    doNothing().when(newIncludeWordReader).close();
    doNothing().when(newIncludeWordReader).includeWordReader(Mockito.<WordReader>any());
    newIncludeWordReader.includeWordReader(null);
    String[] arguments = new String[] {"Arguments"};

    ArgumentWordReader argumentWordReader =
        new ArgumentWordReader(arguments, Configuration.STD_OUT);
    argumentWordReader.includeWordReader(newIncludeWordReader);

    // Act
    argumentWordReader.close();

    // Assert
    verify(newIncludeWordReader).close();
    verify(newIncludeWordReader).includeWordReader(isNull());
    File baseDir = argumentWordReader.getBaseDir();
    assertEquals("", baseDir.getName());
    assertFalse(baseDir.isAbsolute());
  }

  /**
   * Test {@link WordReader#close()}.
   *
   * <ul>
   *   <li>Given {@link FileWordReader} {@link FileWordReader#close()} throw {@link
   *       IOException#IOException()}.
   *   <li>Then throw {@link IOException}.
   * </ul>
   *
   * <p>Method under test: {@link WordReader#close()}
   */
  @Test
  @DisplayName(
      "Test close(); given FileWordReader close() throw IOException(); then throw IOException")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void WordReader.close()"})
  void testClose_givenFileWordReaderCloseThrowIOException_thenThrowIOException()
      throws IOException {
    // Arrange
    FileWordReader newIncludeWordReader = mock(FileWordReader.class);
    doThrow(new IOException()).when(newIncludeWordReader).close();
    doNothing().when(newIncludeWordReader).includeWordReader(Mockito.<WordReader>any());
    newIncludeWordReader.includeWordReader(null);
    String[] arguments = new String[] {"Arguments"};

    ArgumentWordReader argumentWordReader =
        new ArgumentWordReader(arguments, Configuration.STD_OUT);
    argumentWordReader.includeWordReader(newIncludeWordReader);

    // Act and Assert
    assertThrows(IOException.class, () -> argumentWordReader.close());
    verify(newIncludeWordReader).close();
    verify(newIncludeWordReader).includeWordReader(isNull());
  }
}
