package proguard.optimize.gson;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import org.junit.jupiter.api.Test;

class GsonRuntimeSettingsDiffblueTest {
  /**
   * Method under test: default or parameterless constructor of
   * {@link GsonRuntimeSettings}
   */
  @Test
  void testNewGsonRuntimeSettings() {
    // Arrange and Act
    GsonRuntimeSettings actualGsonRuntimeSettings = new GsonRuntimeSettings();

    // Assert
    assertEquals(0, actualGsonRuntimeSettings.instanceCreatorClassPool.size());
    assertEquals(0, actualGsonRuntimeSettings.typeAdapterClassPool.size());
    assertFalse(actualGsonRuntimeSettings.addDeserializationExclusionStrategy);
    assertFalse(actualGsonRuntimeSettings.addSerializationExclusionStrategy);
    assertFalse(actualGsonRuntimeSettings.disableInnerClassSerialization);
    assertFalse(actualGsonRuntimeSettings.excludeFieldsWithModifiers);
    assertFalse(actualGsonRuntimeSettings.excludeFieldsWithoutExposeAnnotation);
    assertFalse(actualGsonRuntimeSettings.generateNonExecutableJson);
    assertFalse(actualGsonRuntimeSettings.registerTypeAdapterFactory);
    assertFalse(actualGsonRuntimeSettings.serializeNulls);
    assertFalse(actualGsonRuntimeSettings.serializeSpecialFloatingPointValues);
    assertFalse(actualGsonRuntimeSettings.setExclusionStrategies);
    assertFalse(actualGsonRuntimeSettings.setFieldNamingPolicy);
    assertFalse(actualGsonRuntimeSettings.setFieldNamingStrategy);
    assertFalse(actualGsonRuntimeSettings.setLongSerializationPolicy);
    assertFalse(actualGsonRuntimeSettings.setVersion);
  }
}
