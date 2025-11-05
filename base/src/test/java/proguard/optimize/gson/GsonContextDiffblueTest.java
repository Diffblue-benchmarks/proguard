package proguard.optimize.gson;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.ArrayList;
import org.junit.jupiter.api.Test;
import proguard.classfile.ClassPool;
import proguard.classfile.LibraryClass;
import proguard.classfile.util.WarningPrinter;
import proguard.testutils.cpa.NamedClass;

class GsonContextDiffblueTest {
  /**
   * Method under test: default or parameterless constructor of
   * {@link GsonContext}
   */
  @Test
  void testNewGsonContext() {
    // Arrange and Act
    GsonContext actualGsonContext = new GsonContext();

    // Assert
    assertNull(actualGsonContext.gsonDomainClassPool);
    assertNull(actualGsonContext.gsonRuntimeSettings);
  }

  /**
   * Method under test:
   * {@link GsonContext#setupFor(ClassPool, ClassPool, WarningPrinter)}
   */
  @Test
  void testSetupFor() {
    // Arrange
    GsonContext gsonContext = new GsonContext();
    ClassPool programClassPool = new ClassPool();
    ClassPool libraryClassPool = new ClassPool();

    // Act
    gsonContext.setupFor(programClassPool, libraryClassPool, new WarningPrinter(new PrintWriter(new StringWriter())));

    // Assert
    assertEquals(0, gsonContext.gsonDomainClassPool.size());
    GsonRuntimeSettings gsonRuntimeSettings = gsonContext.gsonRuntimeSettings;
    assertEquals(0, gsonRuntimeSettings.instanceCreatorClassPool.size());
    assertEquals(0, gsonRuntimeSettings.typeAdapterClassPool.size());
    assertFalse(gsonRuntimeSettings.addDeserializationExclusionStrategy);
    assertFalse(gsonRuntimeSettings.addSerializationExclusionStrategy);
    assertFalse(gsonRuntimeSettings.disableInnerClassSerialization);
    assertFalse(gsonRuntimeSettings.excludeFieldsWithModifiers);
    assertFalse(gsonRuntimeSettings.excludeFieldsWithoutExposeAnnotation);
    assertFalse(gsonRuntimeSettings.generateNonExecutableJson);
    assertFalse(gsonRuntimeSettings.registerTypeAdapterFactory);
    assertFalse(gsonRuntimeSettings.serializeNulls);
    assertFalse(gsonRuntimeSettings.serializeSpecialFloatingPointValues);
    assertFalse(gsonRuntimeSettings.setExclusionStrategies);
    assertFalse(gsonRuntimeSettings.setFieldNamingPolicy);
    assertFalse(gsonRuntimeSettings.setFieldNamingStrategy);
    assertFalse(gsonRuntimeSettings.setLongSerializationPolicy);
    assertFalse(gsonRuntimeSettings.setVersion);
  }

  /**
   * Method under test:
   * {@link GsonContext#setupFor(ClassPool, ClassPool, WarningPrinter)}
   */
  @Test
  void testSetupFor2() {
    // Arrange
    GsonContext gsonContext = new GsonContext();
    ClassPool programClassPool = new ClassPool(new ArrayList<>());
    ClassPool libraryClassPool = new ClassPool();

    // Act
    gsonContext.setupFor(programClassPool, libraryClassPool, new WarningPrinter(new PrintWriter(new StringWriter())));

    // Assert
    assertEquals(0, gsonContext.gsonDomainClassPool.size());
    GsonRuntimeSettings gsonRuntimeSettings = gsonContext.gsonRuntimeSettings;
    assertEquals(0, gsonRuntimeSettings.instanceCreatorClassPool.size());
    assertEquals(0, gsonRuntimeSettings.typeAdapterClassPool.size());
    assertFalse(gsonRuntimeSettings.addDeserializationExclusionStrategy);
    assertFalse(gsonRuntimeSettings.addSerializationExclusionStrategy);
    assertFalse(gsonRuntimeSettings.disableInnerClassSerialization);
    assertFalse(gsonRuntimeSettings.excludeFieldsWithModifiers);
    assertFalse(gsonRuntimeSettings.excludeFieldsWithoutExposeAnnotation);
    assertFalse(gsonRuntimeSettings.generateNonExecutableJson);
    assertFalse(gsonRuntimeSettings.registerTypeAdapterFactory);
    assertFalse(gsonRuntimeSettings.serializeNulls);
    assertFalse(gsonRuntimeSettings.serializeSpecialFloatingPointValues);
    assertFalse(gsonRuntimeSettings.setExclusionStrategies);
    assertFalse(gsonRuntimeSettings.setFieldNamingPolicy);
    assertFalse(gsonRuntimeSettings.setFieldNamingStrategy);
    assertFalse(gsonRuntimeSettings.setLongSerializationPolicy);
    assertFalse(gsonRuntimeSettings.setVersion);
  }

  /**
   * Method under test:
   * {@link GsonContext#setupFor(ClassPool, ClassPool, WarningPrinter)}
   */
  @Test
  void testSetupFor3() {
    // Arrange
    GsonContext gsonContext = new GsonContext();

    ClassPool programClassPool = new ClassPool();
    programClassPool.addClass("!com/google/gson/**", new LibraryClass(1, "!com/google/gson/**", "!com/google/gson/**"));
    ClassPool libraryClassPool = new ClassPool();

    // Act
    gsonContext.setupFor(programClassPool, libraryClassPool, new WarningPrinter(new PrintWriter(new StringWriter())));

    // Assert
    assertEquals(0, gsonContext.gsonDomainClassPool.size());
    GsonRuntimeSettings gsonRuntimeSettings = gsonContext.gsonRuntimeSettings;
    assertEquals(0, gsonRuntimeSettings.instanceCreatorClassPool.size());
    assertEquals(0, gsonRuntimeSettings.typeAdapterClassPool.size());
    assertFalse(gsonRuntimeSettings.addDeserializationExclusionStrategy);
    assertFalse(gsonRuntimeSettings.addSerializationExclusionStrategy);
    assertFalse(gsonRuntimeSettings.disableInnerClassSerialization);
    assertFalse(gsonRuntimeSettings.excludeFieldsWithModifiers);
    assertFalse(gsonRuntimeSettings.excludeFieldsWithoutExposeAnnotation);
    assertFalse(gsonRuntimeSettings.generateNonExecutableJson);
    assertFalse(gsonRuntimeSettings.registerTypeAdapterFactory);
    assertFalse(gsonRuntimeSettings.serializeNulls);
    assertFalse(gsonRuntimeSettings.serializeSpecialFloatingPointValues);
    assertFalse(gsonRuntimeSettings.setExclusionStrategies);
    assertFalse(gsonRuntimeSettings.setFieldNamingPolicy);
    assertFalse(gsonRuntimeSettings.setFieldNamingStrategy);
    assertFalse(gsonRuntimeSettings.setLongSerializationPolicy);
    assertFalse(gsonRuntimeSettings.setVersion);
  }

  /**
   * Method under test:
   * {@link GsonContext#setupFor(ClassPool, ClassPool, WarningPrinter)}
   */
  @Test
  void testSetupFor4() {
    // Arrange
    GsonContext gsonContext = new GsonContext();

    ClassPool programClassPool = new ClassPool();
    programClassPool.addClass("!com/google/gson/**", new NamedClass("!com/google/gson/**"));
    ClassPool libraryClassPool = new ClassPool();

    // Act
    gsonContext.setupFor(programClassPool, libraryClassPool, new WarningPrinter(new PrintWriter(new StringWriter())));

    // Assert
    assertEquals(0, gsonContext.gsonDomainClassPool.size());
    GsonRuntimeSettings gsonRuntimeSettings = gsonContext.gsonRuntimeSettings;
    assertEquals(0, gsonRuntimeSettings.instanceCreatorClassPool.size());
    assertEquals(0, gsonRuntimeSettings.typeAdapterClassPool.size());
    assertFalse(gsonRuntimeSettings.addDeserializationExclusionStrategy);
    assertFalse(gsonRuntimeSettings.addSerializationExclusionStrategy);
    assertFalse(gsonRuntimeSettings.disableInnerClassSerialization);
    assertFalse(gsonRuntimeSettings.excludeFieldsWithModifiers);
    assertFalse(gsonRuntimeSettings.excludeFieldsWithoutExposeAnnotation);
    assertFalse(gsonRuntimeSettings.generateNonExecutableJson);
    assertFalse(gsonRuntimeSettings.registerTypeAdapterFactory);
    assertFalse(gsonRuntimeSettings.serializeNulls);
    assertFalse(gsonRuntimeSettings.serializeSpecialFloatingPointValues);
    assertFalse(gsonRuntimeSettings.setExclusionStrategies);
    assertFalse(gsonRuntimeSettings.setFieldNamingPolicy);
    assertFalse(gsonRuntimeSettings.setFieldNamingStrategy);
    assertFalse(gsonRuntimeSettings.setLongSerializationPolicy);
    assertFalse(gsonRuntimeSettings.setVersion);
  }

  /**
   * Method under test:
   * {@link GsonContext#setupFor(ClassPool, ClassPool, WarningPrinter)}
   */
  @Test
  void testSetupFor5() {
    // Arrange
    GsonContext gsonContext = new GsonContext();

    ClassPool programClassPool = new ClassPool();
    programClassPool.addClass("!com/google/gson/**",
        new LibraryClass(1, GsonClassConstants.NAME_GSON_BUILDER, "!com/google/gson/**"));
    ClassPool libraryClassPool = new ClassPool();

    // Act
    gsonContext.setupFor(programClassPool, libraryClassPool, new WarningPrinter(new PrintWriter(new StringWriter())));

    // Assert
    assertEquals(0, gsonContext.gsonDomainClassPool.size());
    GsonRuntimeSettings gsonRuntimeSettings = gsonContext.gsonRuntimeSettings;
    assertEquals(0, gsonRuntimeSettings.instanceCreatorClassPool.size());
    assertEquals(0, gsonRuntimeSettings.typeAdapterClassPool.size());
    assertFalse(gsonRuntimeSettings.addDeserializationExclusionStrategy);
    assertFalse(gsonRuntimeSettings.addSerializationExclusionStrategy);
    assertFalse(gsonRuntimeSettings.disableInnerClassSerialization);
    assertFalse(gsonRuntimeSettings.excludeFieldsWithModifiers);
    assertFalse(gsonRuntimeSettings.excludeFieldsWithoutExposeAnnotation);
    assertFalse(gsonRuntimeSettings.generateNonExecutableJson);
    assertFalse(gsonRuntimeSettings.registerTypeAdapterFactory);
    assertFalse(gsonRuntimeSettings.serializeNulls);
    assertFalse(gsonRuntimeSettings.serializeSpecialFloatingPointValues);
    assertFalse(gsonRuntimeSettings.setExclusionStrategies);
    assertFalse(gsonRuntimeSettings.setFieldNamingPolicy);
    assertFalse(gsonRuntimeSettings.setFieldNamingStrategy);
    assertFalse(gsonRuntimeSettings.setLongSerializationPolicy);
    assertFalse(gsonRuntimeSettings.setVersion);
  }
}
