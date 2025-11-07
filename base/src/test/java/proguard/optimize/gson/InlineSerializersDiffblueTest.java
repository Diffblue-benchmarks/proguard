package proguard.optimize.gson;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import proguard.classfile.ClassPool;
import proguard.classfile.LibraryClass;
import proguard.classfile.ProgramClass;
import proguard.classfile.ProgramField;
import proguard.classfile.constant.ClassConstant;
import proguard.classfile.constant.Constant;
import proguard.classfile.editor.CompactCodeAttributeComposer;
import proguard.optimize.gson.InlineSerializers.InlineBooleanSerializer;
import proguard.optimize.gson.InlineSerializers.InlinePrimitiveBooleanSerializer;
import proguard.optimize.gson.InlineSerializers.InlinePrimitiveIntegerSerializer;
import proguard.optimize.gson.InlineSerializers.InlineStringSerializer;
import proguard.testutils.cpa.NamedClass;
import proguard.testutils.cpa.NamedField;

class InlineSerializersDiffblueTest {
  /**
   * Test InlineBooleanSerializer {@link InlineBooleanSerializer#canSerialize(ClassPool, GsonRuntimeSettings)}.
   * <ul>
   *   <li>Then return {@code false}.</li>
   * </ul>
   * <p>
   * Method under test: {@link InlineBooleanSerializer#canSerialize(ClassPool, GsonRuntimeSettings)}
   */
  @Test
  @DisplayName("Test InlineBooleanSerializer canSerialize(ClassPool, GsonRuntimeSettings); then return 'false'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean InlineBooleanSerializer.canSerialize(ClassPool, GsonRuntimeSettings)"})
  void testInlineBooleanSerializerCanSerialize_thenReturnFalse() {
    // Arrange
    InlineBooleanSerializer inlineBooleanSerializer = new InlineBooleanSerializer();
    ClassPool programClassPool = new ClassPool();

    ClassPool classPool = new ClassPool();
    classPool.addClass("java/lang/Boolean", new LibraryClass());
    GsonRuntimeSettings gsonRuntimeSettings = new GsonRuntimeSettings();
    gsonRuntimeSettings.addDeserializationExclusionStrategy = true;
    gsonRuntimeSettings.addSerializationExclusionStrategy = true;
    gsonRuntimeSettings.disableInnerClassSerialization = true;
    gsonRuntimeSettings.excludeFieldsWithModifiers = true;
    gsonRuntimeSettings.excludeFieldsWithoutExposeAnnotation = true;
    gsonRuntimeSettings.generateNonExecutableJson = true;
    gsonRuntimeSettings.instanceCreatorClassPool = new ClassPool();
    gsonRuntimeSettings.registerTypeAdapterFactory = true;
    gsonRuntimeSettings.serializeNulls = true;
    gsonRuntimeSettings.serializeSpecialFloatingPointValues = true;
    gsonRuntimeSettings.setExclusionStrategies = true;
    gsonRuntimeSettings.setFieldNamingPolicy = true;
    gsonRuntimeSettings.setFieldNamingStrategy = true;
    gsonRuntimeSettings.setLongSerializationPolicy = true;
    gsonRuntimeSettings.setVersion = true;
    gsonRuntimeSettings.typeAdapterClassPool = classPool;

    // Act and Assert
    assertFalse(inlineBooleanSerializer.canSerialize(programClassPool, gsonRuntimeSettings));
  }

  /**
   * Test InlineBooleanSerializer {@link InlineBooleanSerializer#serialize(ProgramClass, ProgramField, CompactCodeAttributeComposer, GsonRuntimeSettings)}.
   * <p>
   * Method under test: {@link InlineBooleanSerializer#serialize(ProgramClass, ProgramField, CompactCodeAttributeComposer, GsonRuntimeSettings)}
   */
  @Test
  @DisplayName("Test InlineBooleanSerializer serialize(ProgramClass, ProgramField, CompactCodeAttributeComposer, GsonRuntimeSettings)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "void InlineBooleanSerializer.serialize(ProgramClass, ProgramField, CompactCodeAttributeComposer, GsonRuntimeSettings)"})
  void testInlineBooleanSerializerSerialize() {
    // Arrange
    InlineBooleanSerializer inlineBooleanSerializer = new InlineBooleanSerializer();
    NamedClass programClass = new NamedClass("Member Name");
    NamedField programField = new NamedField("Field Name", "Field Descriptor");

    ProgramClass targetClass = new ProgramClass(2, 1, new Constant[]{new ClassConstant()}, 2, 2, 2);

    CompactCodeAttributeComposer ____ = new CompactCodeAttributeComposer(targetClass);
    GsonRuntimeSettings gsonRuntimeSettings = new GsonRuntimeSettings();
    gsonRuntimeSettings.addDeserializationExclusionStrategy = true;
    gsonRuntimeSettings.addSerializationExclusionStrategy = true;
    gsonRuntimeSettings.disableInnerClassSerialization = true;
    gsonRuntimeSettings.excludeFieldsWithModifiers = true;
    gsonRuntimeSettings.excludeFieldsWithoutExposeAnnotation = true;
    gsonRuntimeSettings.generateNonExecutableJson = true;
    gsonRuntimeSettings.instanceCreatorClassPool = new ClassPool();
    gsonRuntimeSettings.registerTypeAdapterFactory = true;
    gsonRuntimeSettings.serializeNulls = true;
    gsonRuntimeSettings.serializeSpecialFloatingPointValues = true;
    gsonRuntimeSettings.setExclusionStrategies = true;
    gsonRuntimeSettings.setFieldNamingPolicy = true;
    gsonRuntimeSettings.setFieldNamingStrategy = true;
    gsonRuntimeSettings.setLongSerializationPolicy = true;
    gsonRuntimeSettings.setVersion = true;
    gsonRuntimeSettings.typeAdapterClassPool = new ClassPool();

    // Act
    inlineBooleanSerializer.serialize(programClass, programField, ____, gsonRuntimeSettings);

    // Assert
    ProgramClass targetClass2 = ____.getTargetClass();
    assertEquals(13, targetClass2.u2constantPoolCount);
    assertSame(targetClass.constantPool, targetClass2.constantPool);
  }

  /**
   * Test InlineBooleanSerializer {@link InlineBooleanSerializer#serialize(ProgramClass, ProgramField, CompactCodeAttributeComposer, GsonRuntimeSettings)}.
   * <p>
   * Method under test: {@link InlineBooleanSerializer#serialize(ProgramClass, ProgramField, CompactCodeAttributeComposer, GsonRuntimeSettings)}
   */
  @Test
  @DisplayName("Test InlineBooleanSerializer serialize(ProgramClass, ProgramField, CompactCodeAttributeComposer, GsonRuntimeSettings)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "void InlineBooleanSerializer.serialize(ProgramClass, ProgramField, CompactCodeAttributeComposer, GsonRuntimeSettings)"})
  void testInlineBooleanSerializerSerialize2() {
    // Arrange
    InlineBooleanSerializer inlineBooleanSerializer = new InlineBooleanSerializer();
    NamedClass programClass = new NamedClass("Member Name");
    NamedField programField = new NamedField("Field Name", "Field Descriptor");

    ClassConstant classConstant = new ClassConstant();
    ClassConstant classConstant2 = new ClassConstant();
    ProgramClass targetClass = new ProgramClass(2, 3,
        new Constant[]{classConstant, classConstant2, new ClassConstant()}, 2, 2, 2);

    CompactCodeAttributeComposer ____ = new CompactCodeAttributeComposer(targetClass);
    GsonRuntimeSettings gsonRuntimeSettings = new GsonRuntimeSettings();
    gsonRuntimeSettings.addDeserializationExclusionStrategy = true;
    gsonRuntimeSettings.addSerializationExclusionStrategy = true;
    gsonRuntimeSettings.disableInnerClassSerialization = true;
    gsonRuntimeSettings.excludeFieldsWithModifiers = true;
    gsonRuntimeSettings.excludeFieldsWithoutExposeAnnotation = true;
    gsonRuntimeSettings.generateNonExecutableJson = true;
    gsonRuntimeSettings.instanceCreatorClassPool = new ClassPool();
    gsonRuntimeSettings.registerTypeAdapterFactory = true;
    gsonRuntimeSettings.serializeNulls = true;
    gsonRuntimeSettings.serializeSpecialFloatingPointValues = true;
    gsonRuntimeSettings.setExclusionStrategies = true;
    gsonRuntimeSettings.setFieldNamingPolicy = true;
    gsonRuntimeSettings.setFieldNamingStrategy = true;
    gsonRuntimeSettings.setLongSerializationPolicy = true;
    gsonRuntimeSettings.setVersion = true;
    gsonRuntimeSettings.typeAdapterClassPool = new ClassPool();

    // Act
    inlineBooleanSerializer.serialize(programClass, programField, ____, gsonRuntimeSettings);

    // Assert
    ProgramClass targetClass2 = ____.getTargetClass();
    assertEquals(15, targetClass2.u2constantPoolCount);
    assertSame(targetClass.constantPool, targetClass2.constantPool);
  }

  /**
   * Test InlinePrimitiveBooleanSerializer {@link InlinePrimitiveBooleanSerializer#canSerialize(ClassPool, GsonRuntimeSettings)}.
   * <ul>
   *   <li>Then return {@code false}.</li>
   * </ul>
   * <p>
   * Method under test: {@link InlinePrimitiveBooleanSerializer#canSerialize(ClassPool, GsonRuntimeSettings)}
   */
  @Test
  @DisplayName("Test InlinePrimitiveBooleanSerializer canSerialize(ClassPool, GsonRuntimeSettings); then return 'false'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean InlinePrimitiveBooleanSerializer.canSerialize(ClassPool, GsonRuntimeSettings)"})
  void testInlinePrimitiveBooleanSerializerCanSerialize_thenReturnFalse() {
    // Arrange
    InlinePrimitiveBooleanSerializer inlinePrimitiveBooleanSerializer = new InlinePrimitiveBooleanSerializer();
    ClassPool programClassPool = new ClassPool();

    ClassPool classPool = new ClassPool();
    classPool.addClass("java/lang/Boolean", new LibraryClass());
    GsonRuntimeSettings gsonRuntimeSettings = new GsonRuntimeSettings();
    gsonRuntimeSettings.addDeserializationExclusionStrategy = true;
    gsonRuntimeSettings.addSerializationExclusionStrategy = true;
    gsonRuntimeSettings.disableInnerClassSerialization = true;
    gsonRuntimeSettings.excludeFieldsWithModifiers = true;
    gsonRuntimeSettings.excludeFieldsWithoutExposeAnnotation = true;
    gsonRuntimeSettings.generateNonExecutableJson = true;
    gsonRuntimeSettings.instanceCreatorClassPool = new ClassPool();
    gsonRuntimeSettings.registerTypeAdapterFactory = true;
    gsonRuntimeSettings.serializeNulls = true;
    gsonRuntimeSettings.serializeSpecialFloatingPointValues = true;
    gsonRuntimeSettings.setExclusionStrategies = true;
    gsonRuntimeSettings.setFieldNamingPolicy = true;
    gsonRuntimeSettings.setFieldNamingStrategy = true;
    gsonRuntimeSettings.setLongSerializationPolicy = true;
    gsonRuntimeSettings.setVersion = true;
    gsonRuntimeSettings.typeAdapterClassPool = classPool;

    // Act and Assert
    assertFalse(inlinePrimitiveBooleanSerializer.canSerialize(programClassPool, gsonRuntimeSettings));
  }

  /**
   * Test InlinePrimitiveBooleanSerializer {@link InlinePrimitiveBooleanSerializer#canSerialize(ClassPool, GsonRuntimeSettings)}.
   * <ul>
   *   <li>Then return {@code true}.</li>
   * </ul>
   * <p>
   * Method under test: {@link InlinePrimitiveBooleanSerializer#canSerialize(ClassPool, GsonRuntimeSettings)}
   */
  @Test
  @DisplayName("Test InlinePrimitiveBooleanSerializer canSerialize(ClassPool, GsonRuntimeSettings); then return 'true'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean InlinePrimitiveBooleanSerializer.canSerialize(ClassPool, GsonRuntimeSettings)"})
  void testInlinePrimitiveBooleanSerializerCanSerialize_thenReturnTrue() {
    // Arrange
    InlinePrimitiveBooleanSerializer inlinePrimitiveBooleanSerializer = new InlinePrimitiveBooleanSerializer();
    ClassPool programClassPool = new ClassPool();
    GsonRuntimeSettings gsonRuntimeSettings = new GsonRuntimeSettings();
    gsonRuntimeSettings.addDeserializationExclusionStrategy = true;
    gsonRuntimeSettings.addSerializationExclusionStrategy = true;
    gsonRuntimeSettings.disableInnerClassSerialization = true;
    gsonRuntimeSettings.excludeFieldsWithModifiers = true;
    gsonRuntimeSettings.excludeFieldsWithoutExposeAnnotation = true;
    gsonRuntimeSettings.generateNonExecutableJson = true;
    gsonRuntimeSettings.instanceCreatorClassPool = new ClassPool();
    gsonRuntimeSettings.registerTypeAdapterFactory = true;
    gsonRuntimeSettings.serializeNulls = true;
    gsonRuntimeSettings.serializeSpecialFloatingPointValues = true;
    gsonRuntimeSettings.setExclusionStrategies = true;
    gsonRuntimeSettings.setFieldNamingPolicy = true;
    gsonRuntimeSettings.setFieldNamingStrategy = true;
    gsonRuntimeSettings.setLongSerializationPolicy = true;
    gsonRuntimeSettings.setVersion = true;
    gsonRuntimeSettings.typeAdapterClassPool = new ClassPool();

    // Act and Assert
    assertTrue(inlinePrimitiveBooleanSerializer.canSerialize(programClassPool, gsonRuntimeSettings));
  }

  /**
   * Test InlinePrimitiveBooleanSerializer {@link InlinePrimitiveBooleanSerializer#serialize(ProgramClass, ProgramField, CompactCodeAttributeComposer, GsonRuntimeSettings)}.
   * <p>
   * Method under test: {@link InlinePrimitiveBooleanSerializer#serialize(ProgramClass, ProgramField, CompactCodeAttributeComposer, GsonRuntimeSettings)}
   */
  @Test
  @DisplayName("Test InlinePrimitiveBooleanSerializer serialize(ProgramClass, ProgramField, CompactCodeAttributeComposer, GsonRuntimeSettings)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "void InlinePrimitiveBooleanSerializer.serialize(ProgramClass, ProgramField, CompactCodeAttributeComposer, GsonRuntimeSettings)"})
  void testInlinePrimitiveBooleanSerializerSerialize() {
    // Arrange
    InlinePrimitiveBooleanSerializer inlinePrimitiveBooleanSerializer = new InlinePrimitiveBooleanSerializer();
    NamedClass programClass = new NamedClass("Member Name");
    NamedField programField = new NamedField("Field Name", "Field Descriptor");

    ProgramClass targetClass = new ProgramClass(2, 1, new Constant[]{new ClassConstant()}, 2, 2, 2);

    CompactCodeAttributeComposer ____ = new CompactCodeAttributeComposer(targetClass);
    GsonRuntimeSettings gsonRuntimeSettings = new GsonRuntimeSettings();
    gsonRuntimeSettings.addDeserializationExclusionStrategy = true;
    gsonRuntimeSettings.addSerializationExclusionStrategy = true;
    gsonRuntimeSettings.disableInnerClassSerialization = true;
    gsonRuntimeSettings.excludeFieldsWithModifiers = true;
    gsonRuntimeSettings.excludeFieldsWithoutExposeAnnotation = true;
    gsonRuntimeSettings.generateNonExecutableJson = true;
    gsonRuntimeSettings.instanceCreatorClassPool = new ClassPool();
    gsonRuntimeSettings.registerTypeAdapterFactory = true;
    gsonRuntimeSettings.serializeNulls = true;
    gsonRuntimeSettings.serializeSpecialFloatingPointValues = true;
    gsonRuntimeSettings.setExclusionStrategies = true;
    gsonRuntimeSettings.setFieldNamingPolicy = true;
    gsonRuntimeSettings.setFieldNamingStrategy = true;
    gsonRuntimeSettings.setLongSerializationPolicy = true;
    gsonRuntimeSettings.setVersion = true;
    gsonRuntimeSettings.typeAdapterClassPool = new ClassPool();

    // Act
    inlinePrimitiveBooleanSerializer.serialize(programClass, programField, ____, gsonRuntimeSettings);

    // Assert
    ProgramClass targetClass2 = ____.getTargetClass();
    assertEquals(13, targetClass2.u2constantPoolCount);
    assertSame(targetClass.constantPool, targetClass2.constantPool);
  }

  /**
   * Test InlinePrimitiveBooleanSerializer {@link InlinePrimitiveBooleanSerializer#serialize(ProgramClass, ProgramField, CompactCodeAttributeComposer, GsonRuntimeSettings)}.
   * <p>
   * Method under test: {@link InlinePrimitiveBooleanSerializer#serialize(ProgramClass, ProgramField, CompactCodeAttributeComposer, GsonRuntimeSettings)}
   */
  @Test
  @DisplayName("Test InlinePrimitiveBooleanSerializer serialize(ProgramClass, ProgramField, CompactCodeAttributeComposer, GsonRuntimeSettings)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "void InlinePrimitiveBooleanSerializer.serialize(ProgramClass, ProgramField, CompactCodeAttributeComposer, GsonRuntimeSettings)"})
  void testInlinePrimitiveBooleanSerializerSerialize2() {
    // Arrange
    InlinePrimitiveBooleanSerializer inlinePrimitiveBooleanSerializer = new InlinePrimitiveBooleanSerializer();
    NamedClass programClass = new NamedClass("Member Name");
    NamedField programField = new NamedField("Field Name", "Field Descriptor");

    ClassConstant classConstant = new ClassConstant();
    ClassConstant classConstant2 = new ClassConstant();
    ProgramClass targetClass = new ProgramClass(2, 3,
        new Constant[]{classConstant, classConstant2, new ClassConstant()}, 2, 2, 2);

    CompactCodeAttributeComposer ____ = new CompactCodeAttributeComposer(targetClass);
    GsonRuntimeSettings gsonRuntimeSettings = new GsonRuntimeSettings();
    gsonRuntimeSettings.addDeserializationExclusionStrategy = true;
    gsonRuntimeSettings.addSerializationExclusionStrategy = true;
    gsonRuntimeSettings.disableInnerClassSerialization = true;
    gsonRuntimeSettings.excludeFieldsWithModifiers = true;
    gsonRuntimeSettings.excludeFieldsWithoutExposeAnnotation = true;
    gsonRuntimeSettings.generateNonExecutableJson = true;
    gsonRuntimeSettings.instanceCreatorClassPool = new ClassPool();
    gsonRuntimeSettings.registerTypeAdapterFactory = true;
    gsonRuntimeSettings.serializeNulls = true;
    gsonRuntimeSettings.serializeSpecialFloatingPointValues = true;
    gsonRuntimeSettings.setExclusionStrategies = true;
    gsonRuntimeSettings.setFieldNamingPolicy = true;
    gsonRuntimeSettings.setFieldNamingStrategy = true;
    gsonRuntimeSettings.setLongSerializationPolicy = true;
    gsonRuntimeSettings.setVersion = true;
    gsonRuntimeSettings.typeAdapterClassPool = new ClassPool();

    // Act
    inlinePrimitiveBooleanSerializer.serialize(programClass, programField, ____, gsonRuntimeSettings);

    // Assert
    ProgramClass targetClass2 = ____.getTargetClass();
    assertEquals(15, targetClass2.u2constantPoolCount);
    assertSame(targetClass.constantPool, targetClass2.constantPool);
  }

  /**
   * Test InlinePrimitiveIntegerSerializer {@link InlinePrimitiveIntegerSerializer#canSerialize(ClassPool, GsonRuntimeSettings)}.
   * <ul>
   *   <li>Then return {@code false}.</li>
   * </ul>
   * <p>
   * Method under test: {@link InlinePrimitiveIntegerSerializer#canSerialize(ClassPool, GsonRuntimeSettings)}
   */
  @Test
  @DisplayName("Test InlinePrimitiveIntegerSerializer canSerialize(ClassPool, GsonRuntimeSettings); then return 'false'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean InlinePrimitiveIntegerSerializer.canSerialize(ClassPool, GsonRuntimeSettings)"})
  void testInlinePrimitiveIntegerSerializerCanSerialize_thenReturnFalse() {
    // Arrange
    InlinePrimitiveIntegerSerializer inlinePrimitiveIntegerSerializer = new InlinePrimitiveIntegerSerializer();
    ClassPool programClassPool = new ClassPool();

    ClassPool classPool = new ClassPool();
    classPool.addClass("java/lang/Integer", new LibraryClass());
    GsonRuntimeSettings gsonRuntimeSettings = new GsonRuntimeSettings();
    gsonRuntimeSettings.addDeserializationExclusionStrategy = true;
    gsonRuntimeSettings.addSerializationExclusionStrategy = true;
    gsonRuntimeSettings.disableInnerClassSerialization = true;
    gsonRuntimeSettings.excludeFieldsWithModifiers = true;
    gsonRuntimeSettings.excludeFieldsWithoutExposeAnnotation = true;
    gsonRuntimeSettings.generateNonExecutableJson = true;
    gsonRuntimeSettings.instanceCreatorClassPool = new ClassPool();
    gsonRuntimeSettings.registerTypeAdapterFactory = true;
    gsonRuntimeSettings.serializeNulls = true;
    gsonRuntimeSettings.serializeSpecialFloatingPointValues = true;
    gsonRuntimeSettings.setExclusionStrategies = true;
    gsonRuntimeSettings.setFieldNamingPolicy = true;
    gsonRuntimeSettings.setFieldNamingStrategy = true;
    gsonRuntimeSettings.setLongSerializationPolicy = true;
    gsonRuntimeSettings.setVersion = true;
    gsonRuntimeSettings.typeAdapterClassPool = classPool;

    // Act and Assert
    assertFalse(inlinePrimitiveIntegerSerializer.canSerialize(programClassPool, gsonRuntimeSettings));
  }

  /**
   * Test InlinePrimitiveIntegerSerializer {@link InlinePrimitiveIntegerSerializer#canSerialize(ClassPool, GsonRuntimeSettings)}.
   * <ul>
   *   <li>Then return {@code true}.</li>
   * </ul>
   * <p>
   * Method under test: {@link InlinePrimitiveIntegerSerializer#canSerialize(ClassPool, GsonRuntimeSettings)}
   */
  @Test
  @DisplayName("Test InlinePrimitiveIntegerSerializer canSerialize(ClassPool, GsonRuntimeSettings); then return 'true'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean InlinePrimitiveIntegerSerializer.canSerialize(ClassPool, GsonRuntimeSettings)"})
  void testInlinePrimitiveIntegerSerializerCanSerialize_thenReturnTrue() {
    // Arrange
    InlinePrimitiveIntegerSerializer inlinePrimitiveIntegerSerializer = new InlinePrimitiveIntegerSerializer();
    ClassPool programClassPool = new ClassPool();
    GsonRuntimeSettings gsonRuntimeSettings = new GsonRuntimeSettings();
    gsonRuntimeSettings.addDeserializationExclusionStrategy = true;
    gsonRuntimeSettings.addSerializationExclusionStrategy = true;
    gsonRuntimeSettings.disableInnerClassSerialization = true;
    gsonRuntimeSettings.excludeFieldsWithModifiers = true;
    gsonRuntimeSettings.excludeFieldsWithoutExposeAnnotation = true;
    gsonRuntimeSettings.generateNonExecutableJson = true;
    gsonRuntimeSettings.instanceCreatorClassPool = new ClassPool();
    gsonRuntimeSettings.registerTypeAdapterFactory = true;
    gsonRuntimeSettings.serializeNulls = true;
    gsonRuntimeSettings.serializeSpecialFloatingPointValues = true;
    gsonRuntimeSettings.setExclusionStrategies = true;
    gsonRuntimeSettings.setFieldNamingPolicy = true;
    gsonRuntimeSettings.setFieldNamingStrategy = true;
    gsonRuntimeSettings.setLongSerializationPolicy = true;
    gsonRuntimeSettings.setVersion = true;
    gsonRuntimeSettings.typeAdapterClassPool = new ClassPool();

    // Act and Assert
    assertTrue(inlinePrimitiveIntegerSerializer.canSerialize(programClassPool, gsonRuntimeSettings));
  }

  /**
   * Test InlinePrimitiveIntegerSerializer {@link InlinePrimitiveIntegerSerializer#serialize(ProgramClass, ProgramField, CompactCodeAttributeComposer, GsonRuntimeSettings)}.
   * <p>
   * Method under test: {@link InlinePrimitiveIntegerSerializer#serialize(ProgramClass, ProgramField, CompactCodeAttributeComposer, GsonRuntimeSettings)}
   */
  @Test
  @DisplayName("Test InlinePrimitiveIntegerSerializer serialize(ProgramClass, ProgramField, CompactCodeAttributeComposer, GsonRuntimeSettings)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "void InlinePrimitiveIntegerSerializer.serialize(ProgramClass, ProgramField, CompactCodeAttributeComposer, GsonRuntimeSettings)"})
  void testInlinePrimitiveIntegerSerializerSerialize() {
    // Arrange
    InlinePrimitiveIntegerSerializer inlinePrimitiveIntegerSerializer = new InlinePrimitiveIntegerSerializer();
    NamedClass programClass = new NamedClass("Member Name");
    NamedField programField = new NamedField("Field Name", "Field Descriptor");

    ProgramClass targetClass = new ProgramClass(2, 1, new Constant[]{new ClassConstant()}, 2, 2, 2);

    CompactCodeAttributeComposer ____ = new CompactCodeAttributeComposer(targetClass);
    GsonRuntimeSettings gsonRuntimeSettings = new GsonRuntimeSettings();
    gsonRuntimeSettings.addDeserializationExclusionStrategy = true;
    gsonRuntimeSettings.addSerializationExclusionStrategy = true;
    gsonRuntimeSettings.disableInnerClassSerialization = true;
    gsonRuntimeSettings.excludeFieldsWithModifiers = true;
    gsonRuntimeSettings.excludeFieldsWithoutExposeAnnotation = true;
    gsonRuntimeSettings.generateNonExecutableJson = true;
    gsonRuntimeSettings.instanceCreatorClassPool = new ClassPool();
    gsonRuntimeSettings.registerTypeAdapterFactory = true;
    gsonRuntimeSettings.serializeNulls = true;
    gsonRuntimeSettings.serializeSpecialFloatingPointValues = true;
    gsonRuntimeSettings.setExclusionStrategies = true;
    gsonRuntimeSettings.setFieldNamingPolicy = true;
    gsonRuntimeSettings.setFieldNamingStrategy = true;
    gsonRuntimeSettings.setLongSerializationPolicy = true;
    gsonRuntimeSettings.setVersion = true;
    gsonRuntimeSettings.typeAdapterClassPool = new ClassPool();

    // Act
    inlinePrimitiveIntegerSerializer.serialize(programClass, programField, ____, gsonRuntimeSettings);

    // Assert
    ProgramClass targetClass2 = ____.getTargetClass();
    assertEquals(19, targetClass2.u2constantPoolCount);
    assertSame(targetClass.constantPool, targetClass2.constantPool);
  }

  /**
   * Test InlinePrimitiveIntegerSerializer {@link InlinePrimitiveIntegerSerializer#serialize(ProgramClass, ProgramField, CompactCodeAttributeComposer, GsonRuntimeSettings)}.
   * <p>
   * Method under test: {@link InlinePrimitiveIntegerSerializer#serialize(ProgramClass, ProgramField, CompactCodeAttributeComposer, GsonRuntimeSettings)}
   */
  @Test
  @DisplayName("Test InlinePrimitiveIntegerSerializer serialize(ProgramClass, ProgramField, CompactCodeAttributeComposer, GsonRuntimeSettings)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "void InlinePrimitiveIntegerSerializer.serialize(ProgramClass, ProgramField, CompactCodeAttributeComposer, GsonRuntimeSettings)"})
  void testInlinePrimitiveIntegerSerializerSerialize2() {
    // Arrange
    InlinePrimitiveIntegerSerializer inlinePrimitiveIntegerSerializer = new InlinePrimitiveIntegerSerializer();
    NamedClass programClass = new NamedClass("Member Name");
    NamedField programField = new NamedField("Field Name", "Field Descriptor");

    ClassConstant classConstant = new ClassConstant();
    ClassConstant classConstant2 = new ClassConstant();
    ProgramClass targetClass = new ProgramClass(2, 3,
        new Constant[]{classConstant, classConstant2, new ClassConstant()}, 2, 2, 2);

    CompactCodeAttributeComposer ____ = new CompactCodeAttributeComposer(targetClass);
    GsonRuntimeSettings gsonRuntimeSettings = new GsonRuntimeSettings();
    gsonRuntimeSettings.addDeserializationExclusionStrategy = true;
    gsonRuntimeSettings.addSerializationExclusionStrategy = true;
    gsonRuntimeSettings.disableInnerClassSerialization = true;
    gsonRuntimeSettings.excludeFieldsWithModifiers = true;
    gsonRuntimeSettings.excludeFieldsWithoutExposeAnnotation = true;
    gsonRuntimeSettings.generateNonExecutableJson = true;
    gsonRuntimeSettings.instanceCreatorClassPool = new ClassPool();
    gsonRuntimeSettings.registerTypeAdapterFactory = true;
    gsonRuntimeSettings.serializeNulls = true;
    gsonRuntimeSettings.serializeSpecialFloatingPointValues = true;
    gsonRuntimeSettings.setExclusionStrategies = true;
    gsonRuntimeSettings.setFieldNamingPolicy = true;
    gsonRuntimeSettings.setFieldNamingStrategy = true;
    gsonRuntimeSettings.setLongSerializationPolicy = true;
    gsonRuntimeSettings.setVersion = true;
    gsonRuntimeSettings.typeAdapterClassPool = new ClassPool();

    // Act
    inlinePrimitiveIntegerSerializer.serialize(programClass, programField, ____, gsonRuntimeSettings);

    // Assert
    ProgramClass targetClass2 = ____.getTargetClass();
    assertEquals(21, targetClass2.u2constantPoolCount);
    assertSame(targetClass.constantPool, targetClass2.constantPool);
  }

  /**
   * Test InlineStringSerializer {@link InlineStringSerializer#canSerialize(ClassPool, GsonRuntimeSettings)}.
   * <ul>
   *   <li>Then return {@code false}.</li>
   * </ul>
   * <p>
   * Method under test: {@link InlineStringSerializer#canSerialize(ClassPool, GsonRuntimeSettings)}
   */
  @Test
  @DisplayName("Test InlineStringSerializer canSerialize(ClassPool, GsonRuntimeSettings); then return 'false'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean InlineStringSerializer.canSerialize(ClassPool, GsonRuntimeSettings)"})
  void testInlineStringSerializerCanSerialize_thenReturnFalse() {
    // Arrange
    InlineStringSerializer inlineStringSerializer = new InlineStringSerializer();
    ClassPool programClassPool = new ClassPool();

    ClassPool classPool = new ClassPool();
    classPool.addClass("java/lang/String", new LibraryClass());
    GsonRuntimeSettings gsonRuntimeSettings = new GsonRuntimeSettings();
    gsonRuntimeSettings.addDeserializationExclusionStrategy = true;
    gsonRuntimeSettings.addSerializationExclusionStrategy = true;
    gsonRuntimeSettings.disableInnerClassSerialization = true;
    gsonRuntimeSettings.excludeFieldsWithModifiers = true;
    gsonRuntimeSettings.excludeFieldsWithoutExposeAnnotation = true;
    gsonRuntimeSettings.generateNonExecutableJson = true;
    gsonRuntimeSettings.instanceCreatorClassPool = new ClassPool();
    gsonRuntimeSettings.registerTypeAdapterFactory = true;
    gsonRuntimeSettings.serializeNulls = true;
    gsonRuntimeSettings.serializeSpecialFloatingPointValues = true;
    gsonRuntimeSettings.setExclusionStrategies = true;
    gsonRuntimeSettings.setFieldNamingPolicy = true;
    gsonRuntimeSettings.setFieldNamingStrategy = true;
    gsonRuntimeSettings.setLongSerializationPolicy = true;
    gsonRuntimeSettings.setVersion = true;
    gsonRuntimeSettings.typeAdapterClassPool = classPool;

    // Act and Assert
    assertFalse(inlineStringSerializer.canSerialize(programClassPool, gsonRuntimeSettings));
  }

  /**
   * Test InlineStringSerializer {@link InlineStringSerializer#canSerialize(ClassPool, GsonRuntimeSettings)}.
   * <ul>
   *   <li>Then return {@code true}.</li>
   * </ul>
   * <p>
   * Method under test: {@link InlineStringSerializer#canSerialize(ClassPool, GsonRuntimeSettings)}
   */
  @Test
  @DisplayName("Test InlineStringSerializer canSerialize(ClassPool, GsonRuntimeSettings); then return 'true'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean InlineStringSerializer.canSerialize(ClassPool, GsonRuntimeSettings)"})
  void testInlineStringSerializerCanSerialize_thenReturnTrue() {
    // Arrange
    InlineStringSerializer inlineStringSerializer = new InlineStringSerializer();
    ClassPool programClassPool = new ClassPool();
    GsonRuntimeSettings gsonRuntimeSettings = new GsonRuntimeSettings();
    gsonRuntimeSettings.addDeserializationExclusionStrategy = true;
    gsonRuntimeSettings.addSerializationExclusionStrategy = true;
    gsonRuntimeSettings.disableInnerClassSerialization = true;
    gsonRuntimeSettings.excludeFieldsWithModifiers = true;
    gsonRuntimeSettings.excludeFieldsWithoutExposeAnnotation = true;
    gsonRuntimeSettings.generateNonExecutableJson = true;
    gsonRuntimeSettings.instanceCreatorClassPool = new ClassPool();
    gsonRuntimeSettings.registerTypeAdapterFactory = true;
    gsonRuntimeSettings.serializeNulls = true;
    gsonRuntimeSettings.serializeSpecialFloatingPointValues = true;
    gsonRuntimeSettings.setExclusionStrategies = true;
    gsonRuntimeSettings.setFieldNamingPolicy = true;
    gsonRuntimeSettings.setFieldNamingStrategy = true;
    gsonRuntimeSettings.setLongSerializationPolicy = true;
    gsonRuntimeSettings.setVersion = true;
    gsonRuntimeSettings.typeAdapterClassPool = new ClassPool();

    // Act and Assert
    assertTrue(inlineStringSerializer.canSerialize(programClassPool, gsonRuntimeSettings));
  }

  /**
   * Test InlineStringSerializer {@link InlineStringSerializer#serialize(ProgramClass, ProgramField, CompactCodeAttributeComposer, GsonRuntimeSettings)}.
   * <p>
   * Method under test: {@link InlineStringSerializer#serialize(ProgramClass, ProgramField, CompactCodeAttributeComposer, GsonRuntimeSettings)}
   */
  @Test
  @DisplayName("Test InlineStringSerializer serialize(ProgramClass, ProgramField, CompactCodeAttributeComposer, GsonRuntimeSettings)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "void InlineStringSerializer.serialize(ProgramClass, ProgramField, CompactCodeAttributeComposer, GsonRuntimeSettings)"})
  void testInlineStringSerializerSerialize() {
    // Arrange
    InlineStringSerializer inlineStringSerializer = new InlineStringSerializer();
    NamedClass programClass = new NamedClass("Member Name");
    NamedField programField = new NamedField("Field Name", "Field Descriptor");

    ProgramClass targetClass = new ProgramClass(2, 1, new Constant[]{new ClassConstant()}, 2, 2, 2);

    CompactCodeAttributeComposer ____ = new CompactCodeAttributeComposer(targetClass);
    GsonRuntimeSettings gsonRuntimeSettings = new GsonRuntimeSettings();
    gsonRuntimeSettings.addDeserializationExclusionStrategy = true;
    gsonRuntimeSettings.addSerializationExclusionStrategy = true;
    gsonRuntimeSettings.disableInnerClassSerialization = true;
    gsonRuntimeSettings.excludeFieldsWithModifiers = true;
    gsonRuntimeSettings.excludeFieldsWithoutExposeAnnotation = true;
    gsonRuntimeSettings.generateNonExecutableJson = true;
    gsonRuntimeSettings.instanceCreatorClassPool = new ClassPool();
    gsonRuntimeSettings.registerTypeAdapterFactory = true;
    gsonRuntimeSettings.serializeNulls = true;
    gsonRuntimeSettings.serializeSpecialFloatingPointValues = true;
    gsonRuntimeSettings.setExclusionStrategies = true;
    gsonRuntimeSettings.setFieldNamingPolicy = true;
    gsonRuntimeSettings.setFieldNamingStrategy = true;
    gsonRuntimeSettings.setLongSerializationPolicy = true;
    gsonRuntimeSettings.setVersion = true;
    gsonRuntimeSettings.typeAdapterClassPool = new ClassPool();

    // Act
    inlineStringSerializer.serialize(programClass, programField, ____, gsonRuntimeSettings);

    // Assert
    ProgramClass targetClass2 = ____.getTargetClass();
    assertEquals(13, targetClass2.u2constantPoolCount);
    assertSame(targetClass.constantPool, targetClass2.constantPool);
  }

  /**
   * Test InlineStringSerializer {@link InlineStringSerializer#serialize(ProgramClass, ProgramField, CompactCodeAttributeComposer, GsonRuntimeSettings)}.
   * <p>
   * Method under test: {@link InlineStringSerializer#serialize(ProgramClass, ProgramField, CompactCodeAttributeComposer, GsonRuntimeSettings)}
   */
  @Test
  @DisplayName("Test InlineStringSerializer serialize(ProgramClass, ProgramField, CompactCodeAttributeComposer, GsonRuntimeSettings)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "void InlineStringSerializer.serialize(ProgramClass, ProgramField, CompactCodeAttributeComposer, GsonRuntimeSettings)"})
  void testInlineStringSerializerSerialize2() {
    // Arrange
    InlineStringSerializer inlineStringSerializer = new InlineStringSerializer();
    NamedClass programClass = new NamedClass("Member Name");
    NamedField programField = new NamedField("Field Name", "Field Descriptor");

    ClassConstant classConstant = new ClassConstant();
    ClassConstant classConstant2 = new ClassConstant();
    ProgramClass targetClass = new ProgramClass(2, 3,
        new Constant[]{classConstant, classConstant2, new ClassConstant()}, 2, 2, 2);

    CompactCodeAttributeComposer ____ = new CompactCodeAttributeComposer(targetClass);
    GsonRuntimeSettings gsonRuntimeSettings = new GsonRuntimeSettings();
    gsonRuntimeSettings.addDeserializationExclusionStrategy = true;
    gsonRuntimeSettings.addSerializationExclusionStrategy = true;
    gsonRuntimeSettings.disableInnerClassSerialization = true;
    gsonRuntimeSettings.excludeFieldsWithModifiers = true;
    gsonRuntimeSettings.excludeFieldsWithoutExposeAnnotation = true;
    gsonRuntimeSettings.generateNonExecutableJson = true;
    gsonRuntimeSettings.instanceCreatorClassPool = new ClassPool();
    gsonRuntimeSettings.registerTypeAdapterFactory = true;
    gsonRuntimeSettings.serializeNulls = true;
    gsonRuntimeSettings.serializeSpecialFloatingPointValues = true;
    gsonRuntimeSettings.setExclusionStrategies = true;
    gsonRuntimeSettings.setFieldNamingPolicy = true;
    gsonRuntimeSettings.setFieldNamingStrategy = true;
    gsonRuntimeSettings.setLongSerializationPolicy = true;
    gsonRuntimeSettings.setVersion = true;
    gsonRuntimeSettings.typeAdapterClassPool = new ClassPool();

    // Act
    inlineStringSerializer.serialize(programClass, programField, ____, gsonRuntimeSettings);

    // Assert
    ProgramClass targetClass2 = ____.getTargetClass();
    assertEquals(15, targetClass2.u2constantPoolCount);
    assertSame(targetClass.constantPool, targetClass2.constantPool);
  }
}
