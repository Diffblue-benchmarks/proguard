package proguard.optimize.gson;

import static org.junit.jupiter.api.Assertions.assertNull;
import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;
import java.io.IOException;
import java.io.StringReader;
import org.junit.jupiter.api.Test;

class _OptimizedTypeAdapterImplDiffblueTest {
  /**
   * Method under test: {@link _OptimizedTypeAdapterImpl#read(JsonReader)}
   */
  @Test
  void testRead() throws IOException {
    // Arrange
    Gson gson = new Gson();
    _OptimizedJsonReaderImpl optimizedJsonReader = new _OptimizedJsonReaderImpl();
    _OptimizedTypeAdapterImpl _OptimizedTypeAdapterImpl = new _OptimizedTypeAdapterImpl(gson, optimizedJsonReader,
        new _OptimizedJsonWriterImpl());

    // Act and Assert
    assertNull(_OptimizedTypeAdapterImpl.read(new JsonReader(new StringReader("foo"))));
  }
}
