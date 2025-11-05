package proguard.optimize.gson;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;
import proguard.classfile.ClassPool;
import proguard.classfile.LibraryClass;
import proguard.classfile.ProgramClass;
import proguard.classfile.ProgramField;
import proguard.classfile.constant.ClassConstant;
import proguard.classfile.constant.Constant;
import proguard.classfile.editor.CompactCodeAttributeComposer;
import proguard.testutils.cpa.NamedClass;
import proguard.testutils.cpa.NamedField;

class InlineSerializersDiffblueTest {
  /**
   * Method under test:
   * {@link InlineSerializers.InlineBooleanSerializer#canSerialize(ClassPool, GsonRuntimeSettings)}
   */
  @Test
  void testInlineBooleanSerializerCanSerialize() {
    // Arrange
    InlineSerializers.InlineBooleanSerializer inlineBooleanSerializer = new InlineSerializers.InlineBooleanSerializer();
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
   * Method under test:
   * {@link InlineSerializers.InlineBooleanSerializer#serialize(ProgramClass, ProgramField, CompactCodeAttributeComposer, GsonRuntimeSettings)}
   */
  @Test
  void testInlineBooleanSerializerSerialize() {
    // Arrange
    InlineSerializers.InlineBooleanSerializer inlineBooleanSerializer = new InlineSerializers.InlineBooleanSerializer();
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
    assertEquals(9, ____.getCodeLength());
    assertSame(targetClass, ____.getTargetClass());
  }

  /**
   * Method under test:
   * {@link InlineSerializers.InlineBooleanSerializer#serialize(ProgramClass, ProgramField, CompactCodeAttributeComposer, GsonRuntimeSettings)}
   */
  @Test
  void testInlineBooleanSerializerSerialize2() {
    // Arrange
    InlineSerializers.InlineBooleanSerializer inlineBooleanSerializer = new InlineSerializers.InlineBooleanSerializer();
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
    assertEquals(9, ____.getCodeLength());
    assertSame(targetClass, ____.getTargetClass());
  }

  /**
   * Method under test:
   * {@link InlineSerializers.InlinePrimitiveBooleanSerializer#canSerialize(ClassPool, GsonRuntimeSettings)}
   */
  @Test
  void testInlinePrimitiveBooleanSerializerCanSerialize() {
    // Arrange
    InlineSerializers.InlinePrimitiveBooleanSerializer inlinePrimitiveBooleanSerializer = new InlineSerializers.InlinePrimitiveBooleanSerializer();
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
   * Method under test:
   * {@link InlineSerializers.InlinePrimitiveBooleanSerializer#canSerialize(ClassPool, GsonRuntimeSettings)}
   */
  @Test
  void testInlinePrimitiveBooleanSerializerCanSerialize2() {
    // Arrange
    InlineSerializers.InlinePrimitiveBooleanSerializer inlinePrimitiveBooleanSerializer = new InlineSerializers.InlinePrimitiveBooleanSerializer();
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
   * Method under test:
   * {@link InlineSerializers.InlinePrimitiveBooleanSerializer#serialize(ProgramClass, ProgramField, CompactCodeAttributeComposer, GsonRuntimeSettings)}
   */
  @Test
  void testInlinePrimitiveBooleanSerializerSerialize() {
    // Arrange
    InlineSerializers.InlinePrimitiveBooleanSerializer inlinePrimitiveBooleanSerializer = new InlineSerializers.InlinePrimitiveBooleanSerializer();
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
    assertEquals(9, ____.getCodeLength());
    assertSame(targetClass, ____.getTargetClass());
  }

  /**
   * Method under test:
   * {@link InlineSerializers.InlinePrimitiveBooleanSerializer#serialize(ProgramClass, ProgramField, CompactCodeAttributeComposer, GsonRuntimeSettings)}
   */
  @Test
  void testInlinePrimitiveBooleanSerializerSerialize2() {
    // Arrange
    InlineSerializers.InlinePrimitiveBooleanSerializer inlinePrimitiveBooleanSerializer = new InlineSerializers.InlinePrimitiveBooleanSerializer();
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
    assertEquals(9, ____.getCodeLength());
    assertSame(targetClass, ____.getTargetClass());
  }

  /**
   * Method under test:
   * {@link InlineSerializers.InlinePrimitiveIntegerSerializer#canSerialize(ClassPool, GsonRuntimeSettings)}
   */
  @Test
  void testInlinePrimitiveIntegerSerializerCanSerialize() {
    // Arrange
    InlineSerializers.InlinePrimitiveIntegerSerializer inlinePrimitiveIntegerSerializer = new InlineSerializers.InlinePrimitiveIntegerSerializer();
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
   * Method under test:
   * {@link InlineSerializers.InlinePrimitiveIntegerSerializer#canSerialize(ClassPool, GsonRuntimeSettings)}
   */
  @Test
  void testInlinePrimitiveIntegerSerializerCanSerialize2() {
    // Arrange
    InlineSerializers.InlinePrimitiveIntegerSerializer inlinePrimitiveIntegerSerializer = new InlineSerializers.InlinePrimitiveIntegerSerializer();
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
   * Method under test:
   * {@link InlineSerializers.InlinePrimitiveIntegerSerializer#serialize(ProgramClass, ProgramField, CompactCodeAttributeComposer, GsonRuntimeSettings)}
   */
  @Test
  void testInlinePrimitiveIntegerSerializerSerialize() {
    // Arrange
    InlineSerializers.InlinePrimitiveIntegerSerializer inlinePrimitiveIntegerSerializer = new InlineSerializers.InlinePrimitiveIntegerSerializer();
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
    assertEquals(12, ____.getCodeLength());
    assertSame(targetClass, ____.getTargetClass());
  }

  /**
   * Method under test:
   * {@link InlineSerializers.InlinePrimitiveIntegerSerializer#serialize(ProgramClass, ProgramField, CompactCodeAttributeComposer, GsonRuntimeSettings)}
   */
  @Test
  void testInlinePrimitiveIntegerSerializerSerialize2() {
    // Arrange
    InlineSerializers.InlinePrimitiveIntegerSerializer inlinePrimitiveIntegerSerializer = new InlineSerializers.InlinePrimitiveIntegerSerializer();
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
    assertEquals(12, ____.getCodeLength());
    assertSame(targetClass, ____.getTargetClass());
  }

  /**
   * Method under test:
   * {@link InlineSerializers.InlineStringSerializer#canSerialize(ClassPool, GsonRuntimeSettings)}
   */
  @Test
  void testInlineStringSerializerCanSerialize() {
    // Arrange
    InlineSerializers.InlineStringSerializer inlineStringSerializer = new InlineSerializers.InlineStringSerializer();
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
   * Method under test:
   * {@link InlineSerializers.InlineStringSerializer#canSerialize(ClassPool, GsonRuntimeSettings)}
   */
  @Test
  void testInlineStringSerializerCanSerialize2() {
    // Arrange
    InlineSerializers.InlineStringSerializer inlineStringSerializer = new InlineSerializers.InlineStringSerializer();
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
   * Method under test:
   * {@link InlineSerializers.InlineStringSerializer#serialize(ProgramClass, ProgramField, CompactCodeAttributeComposer, GsonRuntimeSettings)}
   */
  @Test
  void testInlineStringSerializerSerialize() {
    // Arrange
    InlineSerializers.InlineStringSerializer inlineStringSerializer = new InlineSerializers.InlineStringSerializer();
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
    assertEquals(9, ____.getCodeLength());
    assertSame(targetClass, ____.getTargetClass());
  }

  /**
   * Method under test:
   * {@link InlineSerializers.InlineStringSerializer#serialize(ProgramClass, ProgramField, CompactCodeAttributeComposer, GsonRuntimeSettings)}
   */
  @Test
  void testInlineStringSerializerSerialize2() {
    // Arrange
    InlineSerializers.InlineStringSerializer inlineStringSerializer = new InlineSerializers.InlineStringSerializer();
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
    assertEquals(9, ____.getCodeLength());
    assertSame(targetClass, ____.getTargetClass());
  }
}
