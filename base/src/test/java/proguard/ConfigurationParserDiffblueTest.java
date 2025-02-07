package proguard;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.anyBoolean;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Paths;
import java.util.Properties;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

class ConfigurationParserDiffblueTest {
  /**
   * Test {@link ConfigurationParser#ConfigurationParser(WordReader, Properties)}.
   * <ul>
   *   <li>Given {@code Next Word}.</li>
   *   <li>Then calls {@link WordReader#nextWord(boolean, boolean)}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ConfigurationParser#ConfigurationParser(WordReader, Properties)}
   */
  @Test
  @DisplayName("Test new ConfigurationParser(WordReader, Properties); given 'Next Word'; then calls nextWord(boolean, boolean)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void proguard.ConfigurationParser.<init>(proguard.WordReader, java.util.Properties)"})
  void testNewConfigurationParser_givenNextWord_thenCallsNextWord() throws IOException {
    // Arrange
    ArgumentWordReader reader = mock(ArgumentWordReader.class);
    when(reader.nextWord(anyBoolean(), anyBoolean())).thenReturn("Next Word");

    // Act
    new ConfigurationParser(reader, new Properties());

    // Assert
    verify(reader).nextWord(eq(false), eq(false));
  }

  /**
   * Test {@link ConfigurationParser#parse(Configuration)} with {@code configuration}.
   * <ul>
   *   <li>Then throw {@link ParseException}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ConfigurationParser#parse(Configuration)}
   */
  @Test
  @DisplayName("Test parse(Configuration) with 'configuration'; then throw ParseException")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void proguard.ConfigurationParser.parse(proguard.Configuration)"})
  void testParseWithConfiguration_thenThrowParseException() throws IOException, ParseException {
    // Arrange
    URL url = Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toUri().toURL();

    // Act and Assert
    assertThrows(ParseException.class,
        () -> (new ConfigurationParser(url, new Properties())).parse(mock(Configuration.class)));
  }

  /**
   * Test {@link ConfigurationParser#parseClassSpecificationArguments(boolean, boolean, boolean)} with {@code readFirstWord}, {@code allowClassMembers}, {@code allowValues}.
   * <p>
   * Method under test: {@link ConfigurationParser#parseClassSpecificationArguments(boolean, boolean, boolean)}
   */
  @Test
  @DisplayName("Test parseClassSpecificationArguments(boolean, boolean, boolean) with 'readFirstWord', 'allowClassMembers', 'allowValues'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "proguard.ClassSpecification proguard.ConfigurationParser.parseClassSpecificationArguments(boolean, boolean, boolean)"})
  void testParseClassSpecificationArgumentsWithReadFirstWordAllowClassMembersAllowValues()
      throws IOException, ParseException {
    // Arrange
    URL url = Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toUri().toURL();

    // Act and Assert
    assertThrows(ParseException.class,
        () -> (new ConfigurationParser(url, new Properties())).parseClassSpecificationArguments(true, true, true));
  }

  /**
   * Test {@link ConfigurationParser#parseClassSpecificationArguments(boolean, boolean, boolean)} with {@code readFirstWord}, {@code allowClassMembers}, {@code allowValues}.
   * <p>
   * Method under test: {@link ConfigurationParser#parseClassSpecificationArguments(boolean, boolean, boolean)}
   */
  @Test
  @DisplayName("Test parseClassSpecificationArguments(boolean, boolean, boolean) with 'readFirstWord', 'allowClassMembers', 'allowValues'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "proguard.ClassSpecification proguard.ConfigurationParser.parseClassSpecificationArguments(boolean, boolean, boolean)"})
  void testParseClassSpecificationArgumentsWithReadFirstWordAllowClassMembersAllowValues2()
      throws IOException, ParseException {
    // Arrange
    URL url = Paths.get(System.getProperty("java.io.tmpdir"), "META-INF").toUri().toURL();

    // Act and Assert
    assertThrows(ParseException.class,
        () -> (new ConfigurationParser(url, new Properties())).parseClassSpecificationArguments(true, true, true));
  }

  /**
   * Test {@link ConfigurationParser#parseClassSpecificationArguments(boolean, boolean, boolean)} with {@code readFirstWord}, {@code allowClassMembers}, {@code allowValues}.
   * <p>
   * Method under test: {@link ConfigurationParser#parseClassSpecificationArguments(boolean, boolean, boolean)}
   */
  @Test
  @DisplayName("Test parseClassSpecificationArguments(boolean, boolean, boolean) with 'readFirstWord', 'allowClassMembers', 'allowValues'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "proguard.ClassSpecification proguard.ConfigurationParser.parseClassSpecificationArguments(boolean, boolean, boolean)"})
  void testParseClassSpecificationArgumentsWithReadFirstWordAllowClassMembersAllowValues3()
      throws IOException, ParseException {
    // Arrange
    URL url = Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toUri().toURL();

    // Act and Assert
    assertThrows(ParseException.class,
        () -> (new ConfigurationParser(url, new Properties())).parseClassSpecificationArguments(false, true, true));
  }

  /**
   * Test {@link ConfigurationParser#parseClassSpecificationArguments(boolean, boolean, boolean)} with {@code readFirstWord}, {@code allowClassMembers}, {@code allowValues}.
   * <p>
   * Method under test: {@link ConfigurationParser#parseClassSpecificationArguments(boolean, boolean, boolean)}
   */
  @Test
  @DisplayName("Test parseClassSpecificationArguments(boolean, boolean, boolean) with 'readFirstWord', 'allowClassMembers', 'allowValues'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "proguard.ClassSpecification proguard.ConfigurationParser.parseClassSpecificationArguments(boolean, boolean, boolean)"})
  void testParseClassSpecificationArgumentsWithReadFirstWordAllowClassMembersAllowValues4()
      throws IOException, ParseException {
    // Arrange
    URL url = Paths.get(System.getProperty("java.io.tmpdir"), "META-INF").toUri().toURL();

    // Act and Assert
    assertThrows(ParseException.class,
        () -> (new ConfigurationParser(url, new Properties())).parseClassSpecificationArguments(false, true, true));
  }

  /**
   * Test {@link ConfigurationParser#parseClassSpecificationArguments()}.
   * <ul>
   *   <li>Then throw {@link ParseException}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ConfigurationParser#parseClassSpecificationArguments()}
   */
  @Test
  @DisplayName("Test parseClassSpecificationArguments(); then throw ParseException")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"proguard.ClassSpecification proguard.ConfigurationParser.parseClassSpecificationArguments()"})
  void testParseClassSpecificationArguments_thenThrowParseException() throws IOException, ParseException {
    // Arrange
    URL url = Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toUri().toURL();

    // Act and Assert
    assertThrows(ParseException.class,
        () -> (new ConfigurationParser(url, new Properties())).parseClassSpecificationArguments());
  }
}
