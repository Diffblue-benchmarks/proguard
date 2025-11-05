package proguard.optimize.gson;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import com.google.gson.Gson;
import com.google.gson.internal.bind.ObjectTypeAdapter;
import java.util.Map;
import org.junit.jupiter.api.Test;

class _GsonUtilDiffblueTest {
  /**
   * Method under test: {@link _GsonUtil#getTypeAdapter(Gson, Class, Object)}
   */
  @Test
  void testGetTypeAdapter() {
    // Arrange
    Gson gson = new Gson();
    Class<Object> declaredType = Object.class;

    // Act and Assert
    assertEquals("\"Value\"", _GsonUtil.getTypeAdapter(gson, declaredType, "Value").toJson("Value"));
  }

  /**
   * Method under test: {@link _GsonUtil#getTypeAdapter(Gson, Class, Object)}
   */
  @Test
  void testGetTypeAdapter2() {
    // Arrange
    Gson gson = new Gson();
    Class<Map.Entry> declaredType = Map.Entry.class;

    // Act and Assert
    assertEquals("\"Value\"", _GsonUtil.getTypeAdapter(gson, declaredType, "Value").toJson("Value"));
  }

  /**
   * Method under test: {@link _GsonUtil#getTypeAdapter(Gson, Class, Object)}
   */
  @Test
  void testGetTypeAdapter3() {
    // Arrange
    Gson gson = new Gson();
    Class<Object> declaredType = Object.class;
    Gson gson2 = new Gson();
    _OptimizedJsonReaderImpl optimizedJsonReader = new _OptimizedJsonReaderImpl();

    // Act and Assert
    assertTrue(_GsonUtil.getTypeAdapter(gson, declaredType, new _OptimizedTypeAdapterImpl(gson2, optimizedJsonReader,
        new _OptimizedJsonWriterImpl())) instanceof ObjectTypeAdapter);
  }

  /**
   * Method under test: {@link _GsonUtil#getTypeAdapter(Gson, Class, Object)}
   */
  @Test
  void testGetTypeAdapter4() {
    // Arrange
    Gson gson = new Gson();
    Class<Object> declaredType = Object.class;

    // Act and Assert
    assertTrue(_GsonUtil.getTypeAdapter(gson, declaredType, null) instanceof ObjectTypeAdapter);
  }
}
