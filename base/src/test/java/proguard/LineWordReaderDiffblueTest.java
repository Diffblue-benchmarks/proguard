package proguard;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.isNull;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.io.File;
import java.io.IOException;
import java.io.LineNumberReader;
import java.io.StringReader;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

class LineWordReaderDiffblueTest {
  /**
   * Test {@link LineWordReader#LineWordReader(LineNumberReader, String, File)}.
   *
   * <ul>
   *   <li>When {@link Configuration#STD_OUT}.
   *   <li>Then return BaseURL is {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link LineWordReader#LineWordReader(LineNumberReader, String, File)}
   */
  @Test
  @DisplayName(
      "Test new LineWordReader(LineNumberReader, String, File); when STD_OUT; then return BaseURL is 'null'")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void LineWordReader.<init>(LineNumberReader, String, File)",
    "void LineWordReader.<init>(LineNumberReader, String, java.net.URL)"
  })
  void testNewLineWordReader_whenStd_out_thenReturnBaseURLIsNull() throws IOException {
    // Arrange
    LineNumberReader lineNumberReader = new LineNumberReader(new StringReader("foo"), 1);
    File baseDir = Configuration.STD_OUT;

    // Act
    LineWordReader actualLineWordReader =
        new LineWordReader(
            lineNumberReader, "The characteristics of someone or something", baseDir);

    // Assert
    assertNull(actualLineWordReader.getBaseURL());
    assertSame(baseDir, actualLineWordReader.getBaseDir());
  }

  /**
   * Test {@link LineWordReader#nextLine()}.
   *
   * <ul>
   *   <li>Given {@link StringReader#StringReader(String)} with {@code foo}.
   *   <li>Then return {@code foo}.
   * </ul>
   *
   * <p>Method under test: {@link LineWordReader#nextLine()}
   */
  @Test
  @DisplayName("Test nextLine(); given StringReader(String) with 'foo'; then return 'foo'")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"String LineWordReader.nextLine()"})
  void testNextLine_givenStringReaderWithFoo_thenReturnFoo() throws IOException {
    // Arrange
    LineNumberReader lineNumberReader = new LineNumberReader(new StringReader("foo"), 1);
    LineWordReader lineWordReader =
        new LineWordReader(
            lineNumberReader, "The characteristics of someone or something", Configuration.STD_OUT);

    // Act and Assert
    assertEquals("foo", lineWordReader.nextLine());
  }

  /**
   * Test {@link LineWordReader#lineLocationDescription()}.
   *
   * <p>Method under test: {@link LineWordReader#lineLocationDescription()}
   */
  @Test
  @DisplayName("Test lineLocationDescription()")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"String LineWordReader.lineLocationDescription()"})
  void testLineLocationDescription() throws IOException {
    // Arrange
    LineNumberReader lineNumberReader = new LineNumberReader(new StringReader("foo"), 1);
    LineWordReader lineWordReader =
        new LineWordReader(
            lineNumberReader, "The characteristics of someone or something", Configuration.STD_OUT);

    // Act and Assert
    assertEquals(
        "line 0 of The characteristics of someone or something",
        lineWordReader.lineLocationDescription());
  }

  /**
   * Test {@link LineWordReader#close()}.
   *
   * <p>Method under test: {@link LineWordReader#close()}
   */
  @Test
  @DisplayName("Test close()")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void LineWordReader.close()"})
  void testClose() throws IOException {
    // Arrange
    LineNumberReader lineNumberReader = new LineNumberReader(new StringReader("foo"), 1);
    LineWordReader lineWordReader =
        new LineWordReader(
            lineNumberReader, "The characteristics of someone or something", Configuration.STD_OUT);

    // Act
    lineWordReader.close();

    // Assert that nothing has changed
    File baseDir = lineWordReader.getBaseDir();
    assertEquals("", baseDir.getName());
    assertFalse(baseDir.isAbsolute());
  }

  /**
   * Test {@link LineWordReader#close()}.
   *
   * <p>Method under test: {@link LineWordReader#close()}
   */
  @Test
  @DisplayName("Test close()")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void LineWordReader.close()"})
  void testClose2() throws IOException {
    // Arrange
    LineWordReader lineWordReader =
        new LineWordReader(
            null, "The characteristics of someone or something", Configuration.STD_OUT);

    // Act
    lineWordReader.close();

    // Assert that nothing has changed
    File baseDir = lineWordReader.getBaseDir();
    assertEquals("", baseDir.getName());
    assertFalse(baseDir.isAbsolute());
  }

  /**
   * Test {@link LineWordReader#close()}.
   *
   * <p>Method under test: {@link LineWordReader#close()}
   */
  @Test
  @DisplayName("Test close()")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void LineWordReader.close()"})
  void testClose3() throws IOException {
    // Arrange
    String[] arguments = new String[] {"Arguments"};

    ArgumentWordReader newIncludeWordReader =
        new ArgumentWordReader(arguments, Configuration.STD_OUT);
    newIncludeWordReader.includeWordReader(null);
    LineNumberReader lineNumberReader = new LineNumberReader(new StringReader("foo"), 1);

    LineWordReader lineWordReader =
        new LineWordReader(
            lineNumberReader, "The characteristics of someone or something", Configuration.STD_OUT);
    lineWordReader.includeWordReader(newIncludeWordReader);

    // Act
    lineWordReader.close();

    // Assert that nothing has changed
    File baseDir = lineWordReader.getBaseDir();
    assertEquals("", baseDir.getName());
    assertFalse(baseDir.isAbsolute());
  }

  /**
   * Test {@link LineWordReader#close()}.
   *
   * <p>Method under test: {@link LineWordReader#close()}
   */
  @Test
  @DisplayName("Test close()")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void LineWordReader.close()"})
  void testClose4() throws IOException {
    // Arrange
    LineNumberReader lineNumberReader = new LineNumberReader(new StringReader("foo"), 1);

    LineWordReader newIncludeWordReader =
        new LineWordReader(
            lineNumberReader, "The characteristics of someone or something", Configuration.STD_OUT);
    newIncludeWordReader.includeWordReader(null);
    LineNumberReader lineNumberReader2 = new LineNumberReader(new StringReader("foo"), 1);

    LineWordReader lineWordReader =
        new LineWordReader(
            lineNumberReader2,
            "The characteristics of someone or something",
            Configuration.STD_OUT);
    lineWordReader.includeWordReader(newIncludeWordReader);

    // Act
    lineWordReader.close();

    // Assert that nothing has changed
    File baseDir = lineWordReader.getBaseDir();
    assertEquals("", baseDir.getName());
    assertFalse(baseDir.isAbsolute());
  }

  /**
   * Test {@link LineWordReader#close()}.
   *
   * <ul>
   *   <li>Given {@link ArgumentWordReader} {@link ArgumentWordReader#close()} does nothing.
   *   <li>Then calls {@link ArgumentWordReader#close()}.
   * </ul>
   *
   * <p>Method under test: {@link LineWordReader#close()}
   */
  @Test
  @DisplayName("Test close(); given ArgumentWordReader close() does nothing; then calls close()")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void LineWordReader.close()"})
  void testClose_givenArgumentWordReaderCloseDoesNothing_thenCallsClose() throws IOException {
    // Arrange
    ArgumentWordReader newIncludeWordReader = mock(ArgumentWordReader.class);
    doNothing().when(newIncludeWordReader).close();
    doNothing().when(newIncludeWordReader).includeWordReader(Mockito.<WordReader>any());
    newIncludeWordReader.includeWordReader(null);
    LineNumberReader lineNumberReader = new LineNumberReader(new StringReader("foo"), 1);

    LineWordReader lineWordReader =
        new LineWordReader(
            lineNumberReader, "The characteristics of someone or something", Configuration.STD_OUT);
    lineWordReader.includeWordReader(newIncludeWordReader);

    // Act
    lineWordReader.close();

    // Assert
    verify(newIncludeWordReader).close();
    verify(newIncludeWordReader).includeWordReader(isNull());
    File baseDir = lineWordReader.getBaseDir();
    assertEquals("", baseDir.getName());
    assertFalse(baseDir.isAbsolute());
  }

  /**
   * Test {@link LineWordReader#close()}.
   *
   * <ul>
   *   <li>Given {@link ArgumentWordReader} {@link ArgumentWordReader#close()} throw {@link
   *       IOException#IOException()}.
   *   <li>Then throw {@link IOException}.
   * </ul>
   *
   * <p>Method under test: {@link LineWordReader#close()}
   */
  @Test
  @DisplayName(
      "Test close(); given ArgumentWordReader close() throw IOException(); then throw IOException")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void LineWordReader.close()"})
  void testClose_givenArgumentWordReaderCloseThrowIOException_thenThrowIOException()
      throws IOException {
    // Arrange
    ArgumentWordReader newIncludeWordReader = mock(ArgumentWordReader.class);
    doThrow(new IOException()).when(newIncludeWordReader).close();
    doNothing().when(newIncludeWordReader).includeWordReader(Mockito.<WordReader>any());
    newIncludeWordReader.includeWordReader(null);
    LineNumberReader lineNumberReader = new LineNumberReader(new StringReader("foo"), 1);

    LineWordReader lineWordReader =
        new LineWordReader(
            lineNumberReader, "The characteristics of someone or something", Configuration.STD_OUT);
    lineWordReader.includeWordReader(newIncludeWordReader);

    // Act and Assert
    assertThrows(IOException.class, () -> lineWordReader.close());
    verify(newIncludeWordReader).close();
    verify(newIncludeWordReader).includeWordReader(isNull());
  }
}
