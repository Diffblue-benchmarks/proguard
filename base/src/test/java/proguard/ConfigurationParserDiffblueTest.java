package proguard;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.anyBoolean;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Paths;
import java.util.Properties;
import org.junit.jupiter.api.Test;

class ConfigurationParserDiffblueTest {
  /**
   * Method under test:
   * {@link ConfigurationParser#ConfigurationParser(WordReader, Properties)}
   */
  @Test
  void testNewConfigurationParser() throws IOException {
    // Arrange
    ArgumentWordReader reader = mock(ArgumentWordReader.class);
    when(reader.nextWord(anyBoolean(), anyBoolean())).thenReturn("Next Word");

    // Act
    new ConfigurationParser(reader, new Properties());

    // Assert
    verify(reader).nextWord(eq(false), eq(false));
  }

  /**
   * Method under test: {@link ConfigurationParser#parse(Configuration)}
   */
  @Test
  void testParse() throws IOException, ParseException {
    // Arrange
    URL url = Paths.get(System.getProperty("java.io.tmpdir"), "").toUri().toURL();

    // Act and Assert
    assertThrows(ParseException.class,
        () -> (new ConfigurationParser(url, new Properties())).parse(mock(Configuration.class)));
  }

  /**
   * Method under test:
   * {@link ConfigurationParser#parseClassSpecificationArguments()}
   */
  @Test
  void testParseClassSpecificationArguments() throws IOException, ParseException {
    // Arrange
    URL url = Paths.get(System.getProperty("java.io.tmpdir"), "").toUri().toURL();

    // Act and Assert
    assertThrows(ParseException.class,
        () -> (new ConfigurationParser(url, new Properties())).parseClassSpecificationArguments());
  }

  /**
   * Method under test:
   * {@link ConfigurationParser#parseClassSpecificationArguments(boolean, boolean, boolean)}
   */
  @Test
  void testParseClassSpecificationArguments2() throws IOException, ParseException {
    // Arrange
    URL url = Paths.get(System.getProperty("java.io.tmpdir"), "").toUri().toURL();

    // Act and Assert
    assertThrows(ParseException.class,
        () -> (new ConfigurationParser(url, new Properties())).parseClassSpecificationArguments(true, true, true));
  }

  /**
   * Method under test:
   * {@link ConfigurationParser#parseClassSpecificationArguments(boolean, boolean, boolean)}
   */
  @Test
  void testParseClassSpecificationArguments3() throws IOException, ParseException {
    // Arrange
    URL url = Paths.get(System.getProperty("java.io.tmpdir"), "").toUri().toURL();

    // Act and Assert
    assertThrows(ParseException.class,
        () -> (new ConfigurationParser(url, new Properties())).parseClassSpecificationArguments(false, true, true));
  }
}
