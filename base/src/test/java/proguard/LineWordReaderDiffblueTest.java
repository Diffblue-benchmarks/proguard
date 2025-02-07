package proguard;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.io.File;
import java.io.IOException;
import java.io.LineNumberReader;
import java.io.StringReader;
import java.net.URL;
import java.nio.file.Paths;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

class LineWordReaderDiffblueTest {
  /**
   * Test {@link LineWordReader#LineWordReader(LineNumberReader, String, URL)}.
   * <ul>
   *   <li>Then return BaseDir is {@code null}.</li>
   * </ul>
   * <p>
   * Method under test: {@link LineWordReader#LineWordReader(LineNumberReader, String, URL)}
   */
  @Test
  @DisplayName("Test new LineWordReader(LineNumberReader, String, URL); then return BaseDir is 'null'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void proguard.LineWordReader.<init>(java.io.LineNumberReader, java.lang.String, java.io.File)",
      "void proguard.LineWordReader.<init>(java.io.LineNumberReader, java.lang.String, java.net.URL)"})
  void testNewLineWordReader_thenReturnBaseDirIsNull() throws IOException {
    // Arrange
    URL baseURL = Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toUri().toURL();

    // Act
    LineWordReader actualLineWordReader = new LineWordReader(new LineNumberReader(new StringReader("foo"), 1),
        "The characteristics of someone or something", baseURL);

    // Assert
    assertNull(actualLineWordReader.getBaseDir());
    String expectedToStringResult = String.join("", "file:",
        Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toString().concat(File.separator));
    URL baseURL2 = actualLineWordReader.getBaseURL();
    assertEquals(expectedToStringResult, baseURL2.toString());
    assertSame(baseURL, baseURL2);
  }

  /**
   * Test {@link LineWordReader#LineWordReader(LineNumberReader, String, File)}.
   * <ul>
   *   <li>When {@link Configuration#STD_OUT}.</li>
   *   <li>Then return BaseURL is {@code null}.</li>
   * </ul>
   * <p>
   * Method under test: {@link LineWordReader#LineWordReader(LineNumberReader, String, File)}
   */
  @Test
  @DisplayName("Test new LineWordReader(LineNumberReader, String, File); when STD_OUT; then return BaseURL is 'null'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void proguard.LineWordReader.<init>(java.io.LineNumberReader, java.lang.String, java.io.File)",
      "void proguard.LineWordReader.<init>(java.io.LineNumberReader, java.lang.String, java.net.URL)"})
  void testNewLineWordReader_whenStd_out_thenReturnBaseURLIsNull() throws IOException {
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
   * Test {@link LineWordReader#nextLine()}.
   * <ul>
   *   <li>Given {@link StringReader#StringReader(String)} with {@code foo}.</li>
   *   <li>Then return {@code foo}.</li>
   * </ul>
   * <p>
   * Method under test: {@link LineWordReader#nextLine()}
   */
  @Test
  @DisplayName("Test nextLine(); given StringReader(String) with 'foo'; then return 'foo'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"java.lang.String proguard.LineWordReader.nextLine()"})
  void testNextLine_givenStringReaderWithFoo_thenReturnFoo() throws IOException {
    // Arrange, Act and Assert
    assertEquals("foo", (new LineWordReader(new LineNumberReader(new StringReader("foo"), 1),
        "The characteristics of someone or something", Configuration.STD_OUT)).nextLine());
  }

  /**
   * Test {@link LineWordReader#lineLocationDescription()}.
   * <p>
   * Method under test: {@link LineWordReader#lineLocationDescription()}
   */
  @Test
  @DisplayName("Test lineLocationDescription()")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"java.lang.String proguard.LineWordReader.lineLocationDescription()"})
  void testLineLocationDescription() throws IOException {
    // Arrange, Act and Assert
    assertEquals("line 0 of The characteristics of someone or something",
        (new LineWordReader(new LineNumberReader(new StringReader("foo"), 1),
            "The characteristics of someone or something", Configuration.STD_OUT)).lineLocationDescription());
  }
}
