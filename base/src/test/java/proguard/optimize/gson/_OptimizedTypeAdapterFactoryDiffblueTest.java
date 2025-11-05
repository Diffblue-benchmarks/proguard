package proguard.optimize.gson;

import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.mock;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.junit.jupiter.api.Test;

class _OptimizedTypeAdapterFactoryDiffblueTest {
  /**
   * Method under test:
   * {@link _OptimizedTypeAdapterFactory#create(Gson, TypeToken)}
   */
  @Test
  void testCreate() {
    // Arrange
    _OptimizedTypeAdapterFactory _OptimizedTypeAdapterFactory = new _OptimizedTypeAdapterFactory();

    // Act and Assert
    assertNull(_OptimizedTypeAdapterFactory.<Object>create(new Gson(), mock(TypeToken.class)));
  }
}
