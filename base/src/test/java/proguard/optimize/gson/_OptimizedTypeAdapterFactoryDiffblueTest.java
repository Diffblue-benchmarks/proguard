package proguard.optimize.gson;

import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.mock;
import com.diffblue.cover.annotations.MethodsUnderTest;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

class _OptimizedTypeAdapterFactoryDiffblueTest {
  /**
   * Test {@link _OptimizedTypeAdapterFactory#create(Gson, TypeToken)}.
   * <p>
   * Method under test: {@link _OptimizedTypeAdapterFactory#create(Gson, TypeToken)}
   */
  @Test
  @DisplayName("Test create(Gson, TypeToken)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"com.google.gson.TypeAdapter _OptimizedTypeAdapterFactory.create(Gson, TypeToken)"})
  void testCreate() {
    // Arrange
    _OptimizedTypeAdapterFactory _OptimizedTypeAdapterFactory = new _OptimizedTypeAdapterFactory();

    // Act and Assert
    assertNull(_OptimizedTypeAdapterFactory.<Object>create(new Gson(), mock(TypeToken.class)));
  }
}
