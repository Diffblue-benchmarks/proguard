package proguard.optimize.gson;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import com.google.gson.Gson;
import com.google.gson.internal.bind.ObjectTypeAdapter;
import java.util.Map;
import java.util.Map.Entry;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

class _GsonUtilDiffblueTest {
  /**
   * Test {@link _GsonUtil#getTypeAdapter(Gson, Class, Object)} with {@code gson}, {@code declaredType}, {@code value}.
   * <ul>
   *   <li>Then return {@link ObjectTypeAdapter}.</li>
   * </ul>
   * <p>
   * Method under test: {@link _GsonUtil#getTypeAdapter(Gson, Class, Object)}
   */
  @Test
  @DisplayName("Test getTypeAdapter(Gson, Class, Object) with 'gson', 'declaredType', 'value'; then return ObjectTypeAdapter")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"com.google.gson.TypeAdapter _GsonUtil.getTypeAdapter(Gson, Class, Object)"})
  void testGetTypeAdapterWithGsonDeclaredTypeValue_thenReturnObjectTypeAdapter() {
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
   * Test {@link _GsonUtil#getTypeAdapter(Gson, Class, Object)} with {@code gson}, {@code declaredType}, {@code value}.
   * <ul>
   *   <li>Then return toJson {@code Value} is {@code "Value"}.</li>
   * </ul>
   * <p>
   * Method under test: {@link _GsonUtil#getTypeAdapter(Gson, Class, Object)}
   */
  @Test
  @DisplayName("Test getTypeAdapter(Gson, Class, Object) with 'gson', 'declaredType', 'value'; then return toJson 'Value' is '\"Value\"'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"com.google.gson.TypeAdapter _GsonUtil.getTypeAdapter(Gson, Class, Object)"})
  void testGetTypeAdapterWithGsonDeclaredTypeValue_thenReturnToJsonValueIsValue() {
    // Arrange
    Gson gson = new Gson();
    Class<Object> declaredType = Object.class;

    // Act and Assert
    assertEquals("\"Value\"", _GsonUtil.getTypeAdapter(gson, declaredType, "Value").toJson("Value"));
  }

  /**
   * Test {@link _GsonUtil#getTypeAdapter(Gson, Class, Object)} with {@code gson}, {@code declaredType}, {@code value}.
   * <ul>
   *   <li>Then return toJson {@code Value} is {@code "Value"}.</li>
   * </ul>
   * <p>
   * Method under test: {@link _GsonUtil#getTypeAdapter(Gson, Class, Object)}
   */
  @Test
  @DisplayName("Test getTypeAdapter(Gson, Class, Object) with 'gson', 'declaredType', 'value'; then return toJson 'Value' is '\"Value\"'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"com.google.gson.TypeAdapter _GsonUtil.getTypeAdapter(Gson, Class, Object)"})
  void testGetTypeAdapterWithGsonDeclaredTypeValue_thenReturnToJsonValueIsValue2() {
    // Arrange
    Gson gson = new Gson();
    Class<Entry> declaredType = Entry.class;

    // Act and Assert
    assertEquals("\"Value\"", _GsonUtil.getTypeAdapter(gson, declaredType, "Value").toJson("Value"));
  }

  /**
   * Test {@link _GsonUtil#getTypeAdapter(Gson, Class, Object)} with {@code gson}, {@code declaredType}, {@code value}.
   * <ul>
   *   <li>When {@code null}.</li>
   *   <li>Then return {@link ObjectTypeAdapter}.</li>
   * </ul>
   * <p>
   * Method under test: {@link _GsonUtil#getTypeAdapter(Gson, Class, Object)}
   */
  @Test
  @DisplayName("Test getTypeAdapter(Gson, Class, Object) with 'gson', 'declaredType', 'value'; when 'null'; then return ObjectTypeAdapter")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"com.google.gson.TypeAdapter _GsonUtil.getTypeAdapter(Gson, Class, Object)"})
  void testGetTypeAdapterWithGsonDeclaredTypeValue_whenNull_thenReturnObjectTypeAdapter() {
    // Arrange
    Gson gson = new Gson();
    Class<Object> declaredType = Object.class;

    // Act and Assert
    assertTrue(_GsonUtil.getTypeAdapter(gson, declaredType, null) instanceof ObjectTypeAdapter);
  }
}
