package proguard.obfuscate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import java.io.IOException;
import java.io.StringReader;
import java.net.URL;
import java.nio.file.Paths;
import org.junit.jupiter.api.Test;

class DictionaryNameFactoryDiffblueTest {
  /**
   * Method under test: {@link DictionaryNameFactory#nextName()}
   */
  @Test
  void testNextName() throws IOException {
    // Arrange
    StringReader reader = new StringReader("foo");

    // Act and Assert
    assertEquals("foo", (new DictionaryNameFactory(reader, new NumericNameFactory())).nextName());
  }

  /**
   * Method under test: {@link DictionaryNameFactory#nextName()}
   */
  @Test
  void testNextName2() throws IOException {
    // Arrange
    URL url = Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toUri().toURL();

    // Act and Assert
    assertEquals("1", (new DictionaryNameFactory(url, new NumericNameFactory())).nextName());
  }

  /**
   * Method under test: {@link DictionaryNameFactory#nextName()}
   */
  @Test
  void testNextName3() throws IOException {
    // Arrange
    URL url = Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toUri().toURL();
    StringReader reader = new StringReader("foo");

    // Act and Assert
    assertEquals("foo",
        (new DictionaryNameFactory(url, new DictionaryNameFactory(reader, new NumericNameFactory()))).nextName());
  }
}
