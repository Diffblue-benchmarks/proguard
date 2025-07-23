package proguard.optimize.gson;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.ArrayList;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import proguard.classfile.ClassPool;
import proguard.classfile.LibraryClass;
import proguard.classfile.util.WarningPrinter;
import proguard.testutils.cpa.NamedClass;

class GsonContextDiffblueTest {
  /**
   * Test {@link GsonContext#setupFor(ClassPool, ClassPool, WarningPrinter)}.
   *
   * <p>Method under test: {@link GsonContext#setupFor(ClassPool, ClassPool, WarningPrinter)}
   */
  @Test
  @DisplayName("Test setupFor(ClassPool, ClassPool, WarningPrinter)")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void GsonContext.setupFor(ClassPool, ClassPool, WarningPrinter)"})
  void testSetupFor() {
    // Arrange
    GsonContext gsonContext = new GsonContext();

    ClassPool programClassPool = new ClassPool();
    programClassPool.addClass(
        "!com/google/gson/**", new LibraryClass(1, "!com/google/gson/**", "!com/google/gson/**"));
    ClassPool libraryClassPool = new ClassPool();

    // Act
    gsonContext.setupFor(
        programClassPool,
        libraryClassPool,
        new WarningPrinter(new PrintWriter(new StringWriter())));

    // Assert
    assertEquals(0, gsonContext.gsonDomainClassPool.size());
    GsonRuntimeSettings gsonRuntimeSettings = gsonContext.gsonRuntimeSettings;
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
   * Test {@link GsonContext#setupFor(ClassPool, ClassPool, WarningPrinter)}.
   *
   * <p>Method under test: {@link GsonContext#setupFor(ClassPool, ClassPool, WarningPrinter)}
   */
  @Test
  @DisplayName("Test setupFor(ClassPool, ClassPool, WarningPrinter)")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void GsonContext.setupFor(ClassPool, ClassPool, WarningPrinter)"})
  void testSetupFor2() {
    // Arrange
    GsonContext gsonContext = new GsonContext();

    ClassPool programClassPool = new ClassPool();
    programClassPool.addClass(
        "!com/google/gson/**",
        new LibraryClass(1, GsonClassConstants.NAME_GSON_BUILDER, "!com/google/gson/**"));
    ClassPool libraryClassPool = new ClassPool();

    // Act
    gsonContext.setupFor(
        programClassPool,
        libraryClassPool,
        new WarningPrinter(new PrintWriter(new StringWriter())));

    // Assert
    assertEquals(0, gsonContext.gsonDomainClassPool.size());
    GsonRuntimeSettings gsonRuntimeSettings = gsonContext.gsonRuntimeSettings;
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
   * Test {@link GsonContext#setupFor(ClassPool, ClassPool, WarningPrinter)}.
   *
   * <ul>
   *   <li>Given {@link NamedClass#NamedClass(String)} with memberName is {@code
   *       !com/google/gson/**}.
   * </ul>
   *
   * <p>Method under test: {@link GsonContext#setupFor(ClassPool, ClassPool, WarningPrinter)}
   */
  @Test
  @DisplayName(
      "Test setupFor(ClassPool, ClassPool, WarningPrinter); given NamedClass(String) with memberName is '!com/google/gson/**'")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void GsonContext.setupFor(ClassPool, ClassPool, WarningPrinter)"})
  void testSetupFor_givenNamedClassWithMemberNameIsComGoogleGson() {
    // Arrange
    GsonContext gsonContext = new GsonContext();

    ClassPool programClassPool = new ClassPool();
    programClassPool.addClass("!com/google/gson/**", new NamedClass("!com/google/gson/**"));
    ClassPool libraryClassPool = new ClassPool();

    // Act
    gsonContext.setupFor(
        programClassPool,
        libraryClassPool,
        new WarningPrinter(new PrintWriter(new StringWriter())));

    // Assert
    assertEquals(0, gsonContext.gsonDomainClassPool.size());
    GsonRuntimeSettings gsonRuntimeSettings = gsonContext.gsonRuntimeSettings;
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
   * Test {@link GsonContext#setupFor(ClassPool, ClassPool, WarningPrinter)}.
   *
   * <ul>
   *   <li>Then {@link GsonContext} (default constructor) {@link GsonContext#gsonDomainClassPool}
   *       size is zero.
   * </ul>
   *
   * <p>Method under test: {@link GsonContext#setupFor(ClassPool, ClassPool, WarningPrinter)}
   */
  @Test
  @DisplayName(
      "Test setupFor(ClassPool, ClassPool, WarningPrinter); then GsonContext (default constructor) gsonDomainClassPool size is zero")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void GsonContext.setupFor(ClassPool, ClassPool, WarningPrinter)"})
  void testSetupFor_thenGsonContextGsonDomainClassPoolSizeIsZero() {
    // Arrange
    GsonContext gsonContext = new GsonContext();
    ClassPool programClassPool = new ClassPool();
    ClassPool libraryClassPool = new ClassPool();

    // Act
    gsonContext.setupFor(
        programClassPool,
        libraryClassPool,
        new WarningPrinter(new PrintWriter(new StringWriter())));

    // Assert
    assertEquals(0, gsonContext.gsonDomainClassPool.size());
    GsonRuntimeSettings gsonRuntimeSettings = gsonContext.gsonRuntimeSettings;
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
   * Test {@link GsonContext#setupFor(ClassPool, ClassPool, WarningPrinter)}.
   *
   * <ul>
   *   <li>When {@link ClassPool#ClassPool(Iterable)} with classes is {@link ArrayList#ArrayList()}.
   * </ul>
   *
   * <p>Method under test: {@link GsonContext#setupFor(ClassPool, ClassPool, WarningPrinter)}
   */
  @Test
  @DisplayName(
      "Test setupFor(ClassPool, ClassPool, WarningPrinter); when ClassPool(Iterable) with classes is ArrayList()")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void GsonContext.setupFor(ClassPool, ClassPool, WarningPrinter)"})
  void testSetupFor_whenClassPoolWithClassesIsArrayList() {
    // Arrange
    GsonContext gsonContext = new GsonContext();
    ClassPool programClassPool = new ClassPool(new ArrayList<>());
    ClassPool libraryClassPool = new ClassPool();

    // Act
    gsonContext.setupFor(
        programClassPool,
        libraryClassPool,
        new WarningPrinter(new PrintWriter(new StringWriter())));

    // Assert
    assertEquals(0, gsonContext.gsonDomainClassPool.size());
    GsonRuntimeSettings gsonRuntimeSettings = gsonContext.gsonRuntimeSettings;
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
   * Test new {@link GsonContext} (default constructor).
   *
   * <p>Method under test: default or parameterless constructor of {@link GsonContext}
   */
  @Test
  @DisplayName("Test new GsonContext (default constructor)")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void GsonContext.<init>()"})
  void testNewGsonContext() {
    // Arrange and Act
    GsonContext actualGsonContext = new GsonContext();

    // Assert
    assertNull(actualGsonContext.gsonDomainClassPool);
    assertNull(actualGsonContext.gsonRuntimeSettings);
  }
}
