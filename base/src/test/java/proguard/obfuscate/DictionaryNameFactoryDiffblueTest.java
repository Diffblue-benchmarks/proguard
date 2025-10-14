package proguard.obfuscate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.io.IOException;
import java.io.StringReader;
import java.net.URL;
import java.nio.file.Paths;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

class DictionaryNameFactoryDiffblueTest {
  /**
   * Test {@link DictionaryNameFactory#nextName()}.
   *
   * <p>Method under test: {@link DictionaryNameFactory#nextName()}
   */
  @Test
  @DisplayName("Test nextName()")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"java.lang.String DictionaryNameFactory.nextName()"})
  void testNextName() throws IOException {
    // Arrange
    URL url = Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toUri().toURL();
    StringReader reader = new StringReader("foo");
    DictionaryNameFactory nameFactory = new DictionaryNameFactory(reader, new NumericNameFactory());

    DictionaryNameFactory dictionaryNameFactory = new DictionaryNameFactory(url, nameFactory);

    // Act and Assert
    assertEquals("foo", dictionaryNameFactory.nextName());
  }

  /**
   * Test {@link DictionaryNameFactory#nextName()}.
   *
   * <ul>
   *   <li>Given {@link StringReader#StringReader(String)} with {@code foo}.
   *   <li>Then return {@code foo}.
   * </ul>
   *
   * <p>Method under test: {@link DictionaryNameFactory#nextName()}
   */
  @Test
  @DisplayName("Test nextName(); given StringReader(String) with 'foo'; then return 'foo'")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"java.lang.String DictionaryNameFactory.nextName()"})
  void testNextName_givenStringReaderWithFoo_thenReturnFoo() throws IOException {
    // Arrange
    StringReader reader = new StringReader("foo");
    DictionaryNameFactory dictionaryNameFactory =
        new DictionaryNameFactory(reader, new NumericNameFactory());

    // Act and Assert
    assertEquals("foo", dictionaryNameFactory.nextName());
  }

  /**
   * Test {@link DictionaryNameFactory#nextName()}.
   *
   * <ul>
   *   <li>Then return {@code 1}.
   * </ul>
   *
   * <p>Method under test: {@link DictionaryNameFactory#nextName()}
   */
  @Test
  @DisplayName("Test nextName(); then return '1'")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"java.lang.String DictionaryNameFactory.nextName()"})
  void testNextName_thenReturn1() throws IOException {
    // Arrange
    URL url = Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toUri().toURL();
    DictionaryNameFactory dictionaryNameFactory =
        new DictionaryNameFactory(url, new NumericNameFactory());

    // Act and Assert
    assertEquals("1", dictionaryNameFactory.nextName());
  }
}
