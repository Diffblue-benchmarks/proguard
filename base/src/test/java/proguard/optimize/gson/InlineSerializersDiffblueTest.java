package proguard.optimize.gson;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
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
import proguard.classfile.constant.FieldrefConstant;
import proguard.classfile.constant.MethodrefConstant;
import proguard.classfile.constant.NameAndTypeConstant;
import proguard.classfile.constant.Utf8Constant;
import proguard.classfile.editor.CompactCodeAttributeComposer;
import proguard.classfile.kotlin.KotlinConstants;
import proguard.optimize.gson.InlineSerializers.InlineBooleanSerializer;
import proguard.optimize.gson.InlineSerializers.InlinePrimitiveBooleanSerializer;
import proguard.optimize.gson.InlineSerializers.InlinePrimitiveIntegerSerializer;
import proguard.optimize.gson.InlineSerializers.InlineStringSerializer;
import proguard.testutils.cpa.NamedClass;
import proguard.testutils.cpa.NamedField;

class InlineSerializersDiffblueTest {
  /**
   * Test InlineBooleanSerializer {@link InlineBooleanSerializer#canSerialize(ClassPool,
   * GsonRuntimeSettings)}.
   *
   * <p>Method under test: {@link InlineBooleanSerializer#canSerialize(ClassPool,
   * GsonRuntimeSettings)}
   */
  @Test
  @DisplayName("Test InlineBooleanSerializer canSerialize(ClassPool, GsonRuntimeSettings)")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({
    "boolean InlineBooleanSerializer.canSerialize(ClassPool, GsonRuntimeSettings)"
  })
  void testInlineBooleanSerializerCanSerialize() {
    // Arrange
    InlineBooleanSerializer inlineBooleanSerializer = new InlineBooleanSerializer();

    ClassPool programClassPool = KotlinConstants.dummyClassPool;
    LibraryClass clazz = new LibraryClass(1, "java/lang/Boolean", "java/lang/Boolean");
    programClassPool.addClass(GsonClassConstants.NAME_JSON_WRITER, clazz);

    ClassPool classPool = KotlinConstants.dummyClassPool;
    classPool.addClass("java/lang/Boolean", null);
    GsonRuntimeSettings gsonRuntimeSettings = new GsonRuntimeSettings();
    gsonRuntimeSettings.addDeserializationExclusionStrategy = true;
    gsonRuntimeSettings.addSerializationExclusionStrategy = true;
    gsonRuntimeSettings.disableInnerClassSerialization = true;
    gsonRuntimeSettings.excludeFieldsWithModifiers = true;
    gsonRuntimeSettings.excludeFieldsWithoutExposeAnnotation = true;
    gsonRuntimeSettings.generateNonExecutableJson = true;
    gsonRuntimeSettings.instanceCreatorClassPool = KotlinConstants.dummyClassPool;
    gsonRuntimeSettings.registerTypeAdapterFactory = true;
    gsonRuntimeSettings.serializeNulls = true;
    gsonRuntimeSettings.serializeSpecialFloatingPointValues = true;
    gsonRuntimeSettings.setExclusionStrategies = true;
    gsonRuntimeSettings.setFieldNamingPolicy = true;
    gsonRuntimeSettings.setFieldNamingStrategy = true;
    gsonRuntimeSettings.setLongSerializationPolicy = true;
    gsonRuntimeSettings.setVersion = true;
    gsonRuntimeSettings.typeAdapterClassPool = KotlinConstants.dummyClassPool;
    gsonRuntimeSettings.typeAdapterClassPool = classPool;

    // Act and Assert
    assertFalse(inlineBooleanSerializer.canSerialize(programClassPool, gsonRuntimeSettings));
  }

  /**
   * Test InlineBooleanSerializer {@link InlineBooleanSerializer#canSerialize(ClassPool,
   * GsonRuntimeSettings)}.
   *
   * <ul>
   *   <li>Given {@link LibraryClass#LibraryClass()}.
   * </ul>
   *
   * <p>Method under test: {@link InlineBooleanSerializer#canSerialize(ClassPool,
   * GsonRuntimeSettings)}
   */
  @Test
  @DisplayName(
      "Test InlineBooleanSerializer canSerialize(ClassPool, GsonRuntimeSettings); given LibraryClass()")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({
    "boolean InlineBooleanSerializer.canSerialize(ClassPool, GsonRuntimeSettings)"
  })
  void testInlineBooleanSerializerCanSerialize_givenLibraryClass() {
    // Arrange
    InlineBooleanSerializer inlineBooleanSerializer = new InlineBooleanSerializer();

    ClassPool programClassPool = KotlinConstants.dummyClassPool;
    programClassPool.addClass(GsonClassConstants.NAME_JSON_WRITER, new LibraryClass());

    ClassPool classPool = KotlinConstants.dummyClassPool;
    classPool.addClass("java/lang/Boolean", new LibraryClass());
    GsonRuntimeSettings gsonRuntimeSettings = new GsonRuntimeSettings();
    gsonRuntimeSettings.addDeserializationExclusionStrategy = true;
    gsonRuntimeSettings.addSerializationExclusionStrategy = true;
    gsonRuntimeSettings.disableInnerClassSerialization = true;
    gsonRuntimeSettings.excludeFieldsWithModifiers = true;
    gsonRuntimeSettings.excludeFieldsWithoutExposeAnnotation = true;
    gsonRuntimeSettings.generateNonExecutableJson = true;
    gsonRuntimeSettings.instanceCreatorClassPool = KotlinConstants.dummyClassPool;
    gsonRuntimeSettings.registerTypeAdapterFactory = true;
    gsonRuntimeSettings.serializeNulls = true;
    gsonRuntimeSettings.serializeSpecialFloatingPointValues = true;
    gsonRuntimeSettings.setExclusionStrategies = true;
    gsonRuntimeSettings.setFieldNamingPolicy = true;
    gsonRuntimeSettings.setFieldNamingStrategy = true;
    gsonRuntimeSettings.setLongSerializationPolicy = true;
    gsonRuntimeSettings.setVersion = true;
    gsonRuntimeSettings.typeAdapterClassPool = KotlinConstants.dummyClassPool;
    gsonRuntimeSettings.typeAdapterClassPool = classPool;

    // Act and Assert
    assertFalse(inlineBooleanSerializer.canSerialize(programClassPool, gsonRuntimeSettings));
  }

  /**
   * Test InlineBooleanSerializer {@link InlineBooleanSerializer#canSerialize(ClassPool,
   * GsonRuntimeSettings)}.
   *
   * <ul>
   *   <li>Given {@link ProgramClass#ProgramClass()}.
   * </ul>
   *
   * <p>Method under test: {@link InlineBooleanSerializer#canSerialize(ClassPool,
   * GsonRuntimeSettings)}
   */
  @Test
  @DisplayName(
      "Test InlineBooleanSerializer canSerialize(ClassPool, GsonRuntimeSettings); given ProgramClass()")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({
    "boolean InlineBooleanSerializer.canSerialize(ClassPool, GsonRuntimeSettings)"
  })
  void testInlineBooleanSerializerCanSerialize_givenProgramClass() {
    // Arrange
    InlineBooleanSerializer inlineBooleanSerializer = new InlineBooleanSerializer();

    ClassPool programClassPool = KotlinConstants.dummyClassPool;
    programClassPool.addClass(GsonClassConstants.NAME_JSON_WRITER, new ProgramClass());

    ClassPool classPool = KotlinConstants.dummyClassPool;
    classPool.addClass("java/lang/Boolean", null);
    GsonRuntimeSettings gsonRuntimeSettings = new GsonRuntimeSettings();
    gsonRuntimeSettings.addDeserializationExclusionStrategy = true;
    gsonRuntimeSettings.addSerializationExclusionStrategy = true;
    gsonRuntimeSettings.disableInnerClassSerialization = true;
    gsonRuntimeSettings.excludeFieldsWithModifiers = true;
    gsonRuntimeSettings.excludeFieldsWithoutExposeAnnotation = true;
    gsonRuntimeSettings.generateNonExecutableJson = true;
    gsonRuntimeSettings.instanceCreatorClassPool = KotlinConstants.dummyClassPool;
    gsonRuntimeSettings.registerTypeAdapterFactory = true;
    gsonRuntimeSettings.serializeNulls = true;
    gsonRuntimeSettings.serializeSpecialFloatingPointValues = true;
    gsonRuntimeSettings.setExclusionStrategies = true;
    gsonRuntimeSettings.setFieldNamingPolicy = true;
    gsonRuntimeSettings.setFieldNamingStrategy = true;
    gsonRuntimeSettings.setLongSerializationPolicy = true;
    gsonRuntimeSettings.setVersion = true;
    gsonRuntimeSettings.typeAdapterClassPool = KotlinConstants.dummyClassPool;
    gsonRuntimeSettings.typeAdapterClassPool = classPool;

    // Act and Assert
    assertFalse(inlineBooleanSerializer.canSerialize(programClassPool, gsonRuntimeSettings));
  }

  /**
   * Test InlineBooleanSerializer {@link InlineBooleanSerializer#serialize(ProgramClass,
   * ProgramField, CompactCodeAttributeComposer, GsonRuntimeSettings)}.
   *
   * <p>Method under test: {@link InlineBooleanSerializer#serialize(ProgramClass, ProgramField,
   * CompactCodeAttributeComposer, GsonRuntimeSettings)}
   */
  @Test
  @DisplayName(
      "Test InlineBooleanSerializer serialize(ProgramClass, ProgramField, CompactCodeAttributeComposer, GsonRuntimeSettings)")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void InlineBooleanSerializer.serialize(ProgramClass, ProgramField, CompactCodeAttributeComposer, GsonRuntimeSettings)"
  })
  void testInlineBooleanSerializerSerialize() {
    // Arrange
    InlineBooleanSerializer inlineBooleanSerializer = new InlineBooleanSerializer();
    NamedClass programClass = new NamedClass("Member Name");
    NamedField programField = new NamedField("Field Name", "Field Descriptor");
    Constant[] constantPool = new Constant[] {new ClassConstant()};
    ProgramClass targetClass = new ProgramClass(2, 0, constantPool, 2, 2, 2);
    CompactCodeAttributeComposer ____ = new CompactCodeAttributeComposer(targetClass);
    GsonRuntimeSettings gsonRuntimeSettings = new GsonRuntimeSettings();
    gsonRuntimeSettings.addDeserializationExclusionStrategy = true;
    gsonRuntimeSettings.addSerializationExclusionStrategy = true;
    gsonRuntimeSettings.disableInnerClassSerialization = true;
    gsonRuntimeSettings.excludeFieldsWithModifiers = true;
    gsonRuntimeSettings.excludeFieldsWithoutExposeAnnotation = true;
    gsonRuntimeSettings.generateNonExecutableJson = true;
    gsonRuntimeSettings.instanceCreatorClassPool = KotlinConstants.dummyClassPool;
    gsonRuntimeSettings.registerTypeAdapterFactory = true;
    gsonRuntimeSettings.serializeNulls = true;
    gsonRuntimeSettings.serializeSpecialFloatingPointValues = true;
    gsonRuntimeSettings.setExclusionStrategies = true;
    gsonRuntimeSettings.setFieldNamingPolicy = true;
    gsonRuntimeSettings.setFieldNamingStrategy = true;
    gsonRuntimeSettings.setLongSerializationPolicy = true;
    gsonRuntimeSettings.setVersion = true;
    gsonRuntimeSettings.typeAdapterClassPool = KotlinConstants.dummyClassPool;

    // Act
    inlineBooleanSerializer.serialize(programClass, programField, ____, gsonRuntimeSettings);

    // Assert
    ProgramClass targetClass2 = ____.getTargetClass();
    assertEquals(12, targetClass2.u2constantPoolCount);
    assertSame(targetClass.constantPool, targetClass2.constantPool);
  }

  /**
   * Test InlineBooleanSerializer {@link InlineBooleanSerializer#serialize(ProgramClass,
   * ProgramField, CompactCodeAttributeComposer, GsonRuntimeSettings)}.
   *
   * <p>Method under test: {@link InlineBooleanSerializer#serialize(ProgramClass, ProgramField,
   * CompactCodeAttributeComposer, GsonRuntimeSettings)}
   */
  @Test
  @DisplayName(
      "Test InlineBooleanSerializer serialize(ProgramClass, ProgramField, CompactCodeAttributeComposer, GsonRuntimeSettings)")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void InlineBooleanSerializer.serialize(ProgramClass, ProgramField, CompactCodeAttributeComposer, GsonRuntimeSettings)"
  })
  void testInlineBooleanSerializerSerialize2() {
    // Arrange
    InlineBooleanSerializer inlineBooleanSerializer = new InlineBooleanSerializer();
    NamedClass programClass = new NamedClass("Member Name");
    NamedField programField = new NamedField("Field Name", "Field Descriptor");
    ClassConstant classConstant = new ClassConstant();
    ClassConstant classConstant2 = new ClassConstant();
    ProgramClass targetClass =
        new ProgramClass(
            2, 3, new Constant[] {classConstant, classConstant2, new ClassConstant()}, 2, 2, 2);
    CompactCodeAttributeComposer ____ = new CompactCodeAttributeComposer(targetClass);
    GsonRuntimeSettings gsonRuntimeSettings = new GsonRuntimeSettings();
    gsonRuntimeSettings.addDeserializationExclusionStrategy = true;
    gsonRuntimeSettings.addSerializationExclusionStrategy = true;
    gsonRuntimeSettings.disableInnerClassSerialization = true;
    gsonRuntimeSettings.excludeFieldsWithModifiers = true;
    gsonRuntimeSettings.excludeFieldsWithoutExposeAnnotation = true;
    gsonRuntimeSettings.generateNonExecutableJson = true;
    gsonRuntimeSettings.instanceCreatorClassPool = KotlinConstants.dummyClassPool;
    gsonRuntimeSettings.registerTypeAdapterFactory = true;
    gsonRuntimeSettings.serializeNulls = true;
    gsonRuntimeSettings.serializeSpecialFloatingPointValues = true;
    gsonRuntimeSettings.setExclusionStrategies = true;
    gsonRuntimeSettings.setFieldNamingPolicy = true;
    gsonRuntimeSettings.setFieldNamingStrategy = true;
    gsonRuntimeSettings.setLongSerializationPolicy = true;
    gsonRuntimeSettings.setVersion = true;
    gsonRuntimeSettings.typeAdapterClassPool = KotlinConstants.dummyClassPool;

    // Act
    inlineBooleanSerializer.serialize(programClass, programField, ____, gsonRuntimeSettings);

    // Assert
    ProgramClass targetClass2 = ____.getTargetClass();
    assertEquals(15, targetClass2.u2constantPoolCount);
    assertSame(targetClass.constantPool, targetClass2.constantPool);
  }

  /**
   * Test InlineBooleanSerializer {@link InlineBooleanSerializer#serialize(ProgramClass,
   * ProgramField, CompactCodeAttributeComposer, GsonRuntimeSettings)}.
   *
   * <ul>
   *   <li>Then sixth element {@link ClassConstant}.
   * </ul>
   *
   * <p>Method under test: {@link InlineBooleanSerializer#serialize(ProgramClass, ProgramField,
   * CompactCodeAttributeComposer, GsonRuntimeSettings)}
   */
  @Test
  @DisplayName(
      "Test InlineBooleanSerializer serialize(ProgramClass, ProgramField, CompactCodeAttributeComposer, GsonRuntimeSettings); then sixth element ClassConstant")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void InlineBooleanSerializer.serialize(ProgramClass, ProgramField, CompactCodeAttributeComposer, GsonRuntimeSettings)"
  })
  void testInlineBooleanSerializerSerialize_thenSixthElementClassConstant() {
    // Arrange
    InlineBooleanSerializer inlineBooleanSerializer = new InlineBooleanSerializer();
    NamedClass programClass = new NamedClass(GsonClassConstants.NAME_JSON_WRITER);
    NamedField programField = new NamedField("Field Name", "Field Descriptor");
    Constant[] constantPool = new Constant[] {new ClassConstant()};
    CompactCodeAttributeComposer ____ =
        new CompactCodeAttributeComposer(new ProgramClass(2, 1, constantPool, 2, 2, 2));
    GsonRuntimeSettings gsonRuntimeSettings = new GsonRuntimeSettings();
    gsonRuntimeSettings.addDeserializationExclusionStrategy = true;
    gsonRuntimeSettings.addSerializationExclusionStrategy = true;
    gsonRuntimeSettings.disableInnerClassSerialization = true;
    gsonRuntimeSettings.excludeFieldsWithModifiers = true;
    gsonRuntimeSettings.excludeFieldsWithoutExposeAnnotation = true;
    gsonRuntimeSettings.generateNonExecutableJson = true;
    gsonRuntimeSettings.instanceCreatorClassPool = KotlinConstants.dummyClassPool;
    gsonRuntimeSettings.registerTypeAdapterFactory = true;
    gsonRuntimeSettings.serializeNulls = true;
    gsonRuntimeSettings.serializeSpecialFloatingPointValues = true;
    gsonRuntimeSettings.setExclusionStrategies = true;
    gsonRuntimeSettings.setFieldNamingPolicy = true;
    gsonRuntimeSettings.setFieldNamingStrategy = true;
    gsonRuntimeSettings.setLongSerializationPolicy = true;
    gsonRuntimeSettings.setVersion = true;
    gsonRuntimeSettings.typeAdapterClassPool = KotlinConstants.dummyClassPool;

    // Act
    inlineBooleanSerializer.serialize(programClass, programField, ____, gsonRuntimeSettings);

    // Assert
    ProgramClass targetClass = ____.getTargetClass();
    Constant[] constantArray = targetClass.constantPool;
    assertTrue(constantArray[5] instanceof ClassConstant);
    assertTrue(constantArray[10] instanceof MethodrefConstant);
    assertNull(constantArray[11]);
    assertEquals(11, targetClass.u2constantPoolCount);
    assertEquals(17, constantArray.length);
  }

  /**
   * Test InlineBooleanSerializer {@link InlineBooleanSerializer#serialize(ProgramClass,
   * ProgramField, CompactCodeAttributeComposer, GsonRuntimeSettings)}.
   *
   * <ul>
   *   <li>Then twelfth element {@link ClassConstant}.
   * </ul>
   *
   * <p>Method under test: {@link InlineBooleanSerializer#serialize(ProgramClass, ProgramField,
   * CompactCodeAttributeComposer, GsonRuntimeSettings)}
   */
  @Test
  @DisplayName(
      "Test InlineBooleanSerializer serialize(ProgramClass, ProgramField, CompactCodeAttributeComposer, GsonRuntimeSettings); then twelfth element ClassConstant")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void InlineBooleanSerializer.serialize(ProgramClass, ProgramField, CompactCodeAttributeComposer, GsonRuntimeSettings)"
  })
  void testInlineBooleanSerializerSerialize_thenTwelfthElementClassConstant() {
    // Arrange
    InlineBooleanSerializer inlineBooleanSerializer = new InlineBooleanSerializer();
    NamedClass programClass = new NamedClass("Member Name");
    NamedField programField = new NamedField("Field Name", "Field Descriptor");
    Constant[] constantPool = new Constant[] {new ClassConstant()};
    CompactCodeAttributeComposer ____ =
        new CompactCodeAttributeComposer(new ProgramClass(2, 1, constantPool, 2, 2, 2));
    GsonRuntimeSettings gsonRuntimeSettings = new GsonRuntimeSettings();
    gsonRuntimeSettings.addDeserializationExclusionStrategy = true;
    gsonRuntimeSettings.addSerializationExclusionStrategy = true;
    gsonRuntimeSettings.disableInnerClassSerialization = true;
    gsonRuntimeSettings.excludeFieldsWithModifiers = true;
    gsonRuntimeSettings.excludeFieldsWithoutExposeAnnotation = true;
    gsonRuntimeSettings.generateNonExecutableJson = true;
    gsonRuntimeSettings.instanceCreatorClassPool = KotlinConstants.dummyClassPool;
    gsonRuntimeSettings.registerTypeAdapterFactory = true;
    gsonRuntimeSettings.serializeNulls = true;
    gsonRuntimeSettings.serializeSpecialFloatingPointValues = true;
    gsonRuntimeSettings.setExclusionStrategies = true;
    gsonRuntimeSettings.setFieldNamingPolicy = true;
    gsonRuntimeSettings.setFieldNamingStrategy = true;
    gsonRuntimeSettings.setLongSerializationPolicy = true;
    gsonRuntimeSettings.setVersion = true;
    gsonRuntimeSettings.typeAdapterClassPool = KotlinConstants.dummyClassPool;

    // Act
    inlineBooleanSerializer.serialize(programClass, programField, ____, gsonRuntimeSettings);

    // Assert
    ProgramClass targetClass = ____.getTargetClass();
    Constant[] constantArray = targetClass.constantPool;
    assertTrue(constantArray[11] instanceof ClassConstant);
    assertTrue(constantArray[6] instanceof FieldrefConstant);
    assertTrue(constantArray[12] instanceof MethodrefConstant);
    assertTrue(constantArray[10] instanceof Utf8Constant);
    assertTrue(constantArray[2] instanceof Utf8Constant);
    assertTrue(constantArray[8] instanceof Utf8Constant);
    assertEquals(13, targetClass.u2constantPoolCount);
    assertEquals(17, constantArray.length);
  }

  /**
   * Test InlinePrimitiveBooleanSerializer {@link
   * InlinePrimitiveBooleanSerializer#canSerialize(ClassPool, GsonRuntimeSettings)}.
   *
   * <p>Method under test: {@link InlinePrimitiveBooleanSerializer#canSerialize(ClassPool,
   * GsonRuntimeSettings)}
   */
  @Test
  @DisplayName("Test InlinePrimitiveBooleanSerializer canSerialize(ClassPool, GsonRuntimeSettings)")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({
    "boolean InlinePrimitiveBooleanSerializer.canSerialize(ClassPool, GsonRuntimeSettings)"
  })
  void testInlinePrimitiveBooleanSerializerCanSerialize() {
    // Arrange
    InlinePrimitiveBooleanSerializer inlinePrimitiveBooleanSerializer =
        new InlinePrimitiveBooleanSerializer();

    ClassPool classPool = KotlinConstants.dummyClassPool;
    classPool.addClass("java/lang/Boolean", new LibraryClass());
    GsonRuntimeSettings gsonRuntimeSettings = new GsonRuntimeSettings();
    gsonRuntimeSettings.addDeserializationExclusionStrategy = true;
    gsonRuntimeSettings.addSerializationExclusionStrategy = true;
    gsonRuntimeSettings.disableInnerClassSerialization = true;
    gsonRuntimeSettings.excludeFieldsWithModifiers = true;
    gsonRuntimeSettings.excludeFieldsWithoutExposeAnnotation = true;
    gsonRuntimeSettings.generateNonExecutableJson = true;
    gsonRuntimeSettings.instanceCreatorClassPool = KotlinConstants.dummyClassPool;
    gsonRuntimeSettings.registerTypeAdapterFactory = true;
    gsonRuntimeSettings.serializeNulls = true;
    gsonRuntimeSettings.serializeSpecialFloatingPointValues = true;
    gsonRuntimeSettings.setExclusionStrategies = true;
    gsonRuntimeSettings.setFieldNamingPolicy = true;
    gsonRuntimeSettings.setFieldNamingStrategy = true;
    gsonRuntimeSettings.setLongSerializationPolicy = true;
    gsonRuntimeSettings.setVersion = true;
    gsonRuntimeSettings.typeAdapterClassPool = KotlinConstants.dummyClassPool;
    gsonRuntimeSettings.typeAdapterClassPool = classPool;

    // Act and Assert
    assertFalse(
        inlinePrimitiveBooleanSerializer.canSerialize(
            KotlinConstants.dummyClassPool, gsonRuntimeSettings));
  }

  /**
   * Test InlinePrimitiveBooleanSerializer {@link
   * InlinePrimitiveBooleanSerializer#serialize(ProgramClass, ProgramField,
   * CompactCodeAttributeComposer, GsonRuntimeSettings)}.
   *
   * <p>Method under test: {@link InlinePrimitiveBooleanSerializer#serialize(ProgramClass,
   * ProgramField, CompactCodeAttributeComposer, GsonRuntimeSettings)}
   */
  @Test
  @DisplayName(
      "Test InlinePrimitiveBooleanSerializer serialize(ProgramClass, ProgramField, CompactCodeAttributeComposer, GsonRuntimeSettings)")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void InlinePrimitiveBooleanSerializer.serialize(ProgramClass, ProgramField, CompactCodeAttributeComposer, GsonRuntimeSettings)"
  })
  void testInlinePrimitiveBooleanSerializerSerialize() {
    // Arrange
    InlinePrimitiveBooleanSerializer inlinePrimitiveBooleanSerializer =
        new InlinePrimitiveBooleanSerializer();
    NamedClass programClass = new NamedClass("Member Name");
    NamedField programField = new NamedField("Field Name", "Field Descriptor");
    Constant[] constantPool = new Constant[] {new ClassConstant()};
    ProgramClass targetClass = new ProgramClass(2, 0, constantPool, 2, 2, 2);
    CompactCodeAttributeComposer ____ = new CompactCodeAttributeComposer(targetClass);
    GsonRuntimeSettings gsonRuntimeSettings = new GsonRuntimeSettings();
    gsonRuntimeSettings.addDeserializationExclusionStrategy = true;
    gsonRuntimeSettings.addSerializationExclusionStrategy = true;
    gsonRuntimeSettings.disableInnerClassSerialization = true;
    gsonRuntimeSettings.excludeFieldsWithModifiers = true;
    gsonRuntimeSettings.excludeFieldsWithoutExposeAnnotation = true;
    gsonRuntimeSettings.generateNonExecutableJson = true;
    gsonRuntimeSettings.instanceCreatorClassPool = KotlinConstants.dummyClassPool;
    gsonRuntimeSettings.registerTypeAdapterFactory = true;
    gsonRuntimeSettings.serializeNulls = true;
    gsonRuntimeSettings.serializeSpecialFloatingPointValues = true;
    gsonRuntimeSettings.setExclusionStrategies = true;
    gsonRuntimeSettings.setFieldNamingPolicy = true;
    gsonRuntimeSettings.setFieldNamingStrategy = true;
    gsonRuntimeSettings.setLongSerializationPolicy = true;
    gsonRuntimeSettings.setVersion = true;
    gsonRuntimeSettings.typeAdapterClassPool = KotlinConstants.dummyClassPool;

    // Act
    inlinePrimitiveBooleanSerializer.serialize(
        programClass, programField, ____, gsonRuntimeSettings);

    // Assert
    ProgramClass targetClass2 = ____.getTargetClass();
    assertEquals(12, targetClass2.u2constantPoolCount);
    assertSame(targetClass.constantPool, targetClass2.constantPool);
  }

  /**
   * Test InlinePrimitiveBooleanSerializer {@link
   * InlinePrimitiveBooleanSerializer#serialize(ProgramClass, ProgramField,
   * CompactCodeAttributeComposer, GsonRuntimeSettings)}.
   *
   * <p>Method under test: {@link InlinePrimitiveBooleanSerializer#serialize(ProgramClass,
   * ProgramField, CompactCodeAttributeComposer, GsonRuntimeSettings)}
   */
  @Test
  @DisplayName(
      "Test InlinePrimitiveBooleanSerializer serialize(ProgramClass, ProgramField, CompactCodeAttributeComposer, GsonRuntimeSettings)")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void InlinePrimitiveBooleanSerializer.serialize(ProgramClass, ProgramField, CompactCodeAttributeComposer, GsonRuntimeSettings)"
  })
  void testInlinePrimitiveBooleanSerializerSerialize2() {
    // Arrange
    InlinePrimitiveBooleanSerializer inlinePrimitiveBooleanSerializer =
        new InlinePrimitiveBooleanSerializer();
    NamedClass programClass = new NamedClass("Member Name");
    NamedField programField = new NamedField("Field Name", "Field Descriptor");
    ClassConstant classConstant = new ClassConstant();
    ClassConstant classConstant2 = new ClassConstant();
    ProgramClass targetClass =
        new ProgramClass(
            2, 3, new Constant[] {classConstant, classConstant2, new ClassConstant()}, 2, 2, 2);
    CompactCodeAttributeComposer ____ = new CompactCodeAttributeComposer(targetClass);
    GsonRuntimeSettings gsonRuntimeSettings = new GsonRuntimeSettings();
    gsonRuntimeSettings.addDeserializationExclusionStrategy = true;
    gsonRuntimeSettings.addSerializationExclusionStrategy = true;
    gsonRuntimeSettings.disableInnerClassSerialization = true;
    gsonRuntimeSettings.excludeFieldsWithModifiers = true;
    gsonRuntimeSettings.excludeFieldsWithoutExposeAnnotation = true;
    gsonRuntimeSettings.generateNonExecutableJson = true;
    gsonRuntimeSettings.instanceCreatorClassPool = KotlinConstants.dummyClassPool;
    gsonRuntimeSettings.registerTypeAdapterFactory = true;
    gsonRuntimeSettings.serializeNulls = true;
    gsonRuntimeSettings.serializeSpecialFloatingPointValues = true;
    gsonRuntimeSettings.setExclusionStrategies = true;
    gsonRuntimeSettings.setFieldNamingPolicy = true;
    gsonRuntimeSettings.setFieldNamingStrategy = true;
    gsonRuntimeSettings.setLongSerializationPolicy = true;
    gsonRuntimeSettings.setVersion = true;
    gsonRuntimeSettings.typeAdapterClassPool = KotlinConstants.dummyClassPool;

    // Act
    inlinePrimitiveBooleanSerializer.serialize(
        programClass, programField, ____, gsonRuntimeSettings);

    // Assert
    ProgramClass targetClass2 = ____.getTargetClass();
    assertEquals(15, targetClass2.u2constantPoolCount);
    assertSame(targetClass.constantPool, targetClass2.constantPool);
  }

  /**
   * Test InlinePrimitiveBooleanSerializer {@link
   * InlinePrimitiveBooleanSerializer#serialize(ProgramClass, ProgramField,
   * CompactCodeAttributeComposer, GsonRuntimeSettings)}.
   *
   * <ul>
   *   <li>Then sixth element {@link ClassConstant}.
   * </ul>
   *
   * <p>Method under test: {@link InlinePrimitiveBooleanSerializer#serialize(ProgramClass,
   * ProgramField, CompactCodeAttributeComposer, GsonRuntimeSettings)}
   */
  @Test
  @DisplayName(
      "Test InlinePrimitiveBooleanSerializer serialize(ProgramClass, ProgramField, CompactCodeAttributeComposer, GsonRuntimeSettings); then sixth element ClassConstant")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void InlinePrimitiveBooleanSerializer.serialize(ProgramClass, ProgramField, CompactCodeAttributeComposer, GsonRuntimeSettings)"
  })
  void testInlinePrimitiveBooleanSerializerSerialize_thenSixthElementClassConstant() {
    // Arrange
    InlinePrimitiveBooleanSerializer inlinePrimitiveBooleanSerializer =
        new InlinePrimitiveBooleanSerializer();
    NamedClass programClass = new NamedClass(GsonClassConstants.NAME_JSON_WRITER);
    NamedField programField = new NamedField("Field Name", "Field Descriptor");
    Constant[] constantPool = new Constant[] {new ClassConstant()};
    CompactCodeAttributeComposer ____ =
        new CompactCodeAttributeComposer(new ProgramClass(2, 1, constantPool, 2, 2, 2));
    GsonRuntimeSettings gsonRuntimeSettings = new GsonRuntimeSettings();
    gsonRuntimeSettings.addDeserializationExclusionStrategy = true;
    gsonRuntimeSettings.addSerializationExclusionStrategy = true;
    gsonRuntimeSettings.disableInnerClassSerialization = true;
    gsonRuntimeSettings.excludeFieldsWithModifiers = true;
    gsonRuntimeSettings.excludeFieldsWithoutExposeAnnotation = true;
    gsonRuntimeSettings.generateNonExecutableJson = true;
    gsonRuntimeSettings.instanceCreatorClassPool = KotlinConstants.dummyClassPool;
    gsonRuntimeSettings.registerTypeAdapterFactory = true;
    gsonRuntimeSettings.serializeNulls = true;
    gsonRuntimeSettings.serializeSpecialFloatingPointValues = true;
    gsonRuntimeSettings.setExclusionStrategies = true;
    gsonRuntimeSettings.setFieldNamingPolicy = true;
    gsonRuntimeSettings.setFieldNamingStrategy = true;
    gsonRuntimeSettings.setLongSerializationPolicy = true;
    gsonRuntimeSettings.setVersion = true;
    gsonRuntimeSettings.typeAdapterClassPool = KotlinConstants.dummyClassPool;

    // Act
    inlinePrimitiveBooleanSerializer.serialize(
        programClass, programField, ____, gsonRuntimeSettings);

    // Assert
    ProgramClass targetClass = ____.getTargetClass();
    Constant[] constantArray = targetClass.constantPool;
    assertTrue(constantArray[5] instanceof ClassConstant);
    assertTrue(constantArray[10] instanceof MethodrefConstant);
    assertNull(constantArray[11]);
    assertEquals(11, targetClass.u2constantPoolCount);
    assertEquals(17, constantArray.length);
  }

  /**
   * Test InlinePrimitiveBooleanSerializer {@link
   * InlinePrimitiveBooleanSerializer#serialize(ProgramClass, ProgramField,
   * CompactCodeAttributeComposer, GsonRuntimeSettings)}.
   *
   * <ul>
   *   <li>Then twelfth element {@link ClassConstant}.
   * </ul>
   *
   * <p>Method under test: {@link InlinePrimitiveBooleanSerializer#serialize(ProgramClass,
   * ProgramField, CompactCodeAttributeComposer, GsonRuntimeSettings)}
   */
  @Test
  @DisplayName(
      "Test InlinePrimitiveBooleanSerializer serialize(ProgramClass, ProgramField, CompactCodeAttributeComposer, GsonRuntimeSettings); then twelfth element ClassConstant")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void InlinePrimitiveBooleanSerializer.serialize(ProgramClass, ProgramField, CompactCodeAttributeComposer, GsonRuntimeSettings)"
  })
  void testInlinePrimitiveBooleanSerializerSerialize_thenTwelfthElementClassConstant() {
    // Arrange
    InlinePrimitiveBooleanSerializer inlinePrimitiveBooleanSerializer =
        new InlinePrimitiveBooleanSerializer();
    NamedClass programClass = new NamedClass("Member Name");
    NamedField programField = new NamedField("Field Name", "Field Descriptor");
    Constant[] constantPool = new Constant[] {new ClassConstant()};
    CompactCodeAttributeComposer ____ =
        new CompactCodeAttributeComposer(new ProgramClass(2, 1, constantPool, 2, 2, 2));
    GsonRuntimeSettings gsonRuntimeSettings = new GsonRuntimeSettings();
    gsonRuntimeSettings.addDeserializationExclusionStrategy = true;
    gsonRuntimeSettings.addSerializationExclusionStrategy = true;
    gsonRuntimeSettings.disableInnerClassSerialization = true;
    gsonRuntimeSettings.excludeFieldsWithModifiers = true;
    gsonRuntimeSettings.excludeFieldsWithoutExposeAnnotation = true;
    gsonRuntimeSettings.generateNonExecutableJson = true;
    gsonRuntimeSettings.instanceCreatorClassPool = KotlinConstants.dummyClassPool;
    gsonRuntimeSettings.registerTypeAdapterFactory = true;
    gsonRuntimeSettings.serializeNulls = true;
    gsonRuntimeSettings.serializeSpecialFloatingPointValues = true;
    gsonRuntimeSettings.setExclusionStrategies = true;
    gsonRuntimeSettings.setFieldNamingPolicy = true;
    gsonRuntimeSettings.setFieldNamingStrategy = true;
    gsonRuntimeSettings.setLongSerializationPolicy = true;
    gsonRuntimeSettings.setVersion = true;
    gsonRuntimeSettings.typeAdapterClassPool = KotlinConstants.dummyClassPool;

    // Act
    inlinePrimitiveBooleanSerializer.serialize(
        programClass, programField, ____, gsonRuntimeSettings);

    // Assert
    ProgramClass targetClass = ____.getTargetClass();
    Constant[] constantArray = targetClass.constantPool;
    assertTrue(constantArray[11] instanceof ClassConstant);
    assertTrue(constantArray[6] instanceof FieldrefConstant);
    assertTrue(constantArray[12] instanceof MethodrefConstant);
    assertTrue(constantArray[10] instanceof Utf8Constant);
    assertTrue(constantArray[2] instanceof Utf8Constant);
    assertTrue(constantArray[8] instanceof Utf8Constant);
    assertEquals(13, targetClass.u2constantPoolCount);
    assertEquals(17, constantArray.length);
  }

  /**
   * Test InlinePrimitiveIntegerSerializer {@link
   * InlinePrimitiveIntegerSerializer#canSerialize(ClassPool, GsonRuntimeSettings)}.
   *
   * <p>Method under test: {@link InlinePrimitiveIntegerSerializer#canSerialize(ClassPool,
   * GsonRuntimeSettings)}
   */
  @Test
  @DisplayName("Test InlinePrimitiveIntegerSerializer canSerialize(ClassPool, GsonRuntimeSettings)")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({
    "boolean InlinePrimitiveIntegerSerializer.canSerialize(ClassPool, GsonRuntimeSettings)"
  })
  void testInlinePrimitiveIntegerSerializerCanSerialize() {
    // Arrange
    InlinePrimitiveIntegerSerializer inlinePrimitiveIntegerSerializer =
        new InlinePrimitiveIntegerSerializer();

    ClassPool classPool = KotlinConstants.dummyClassPool;
    classPool.addClass("java/lang/Integer", null);
    GsonRuntimeSettings gsonRuntimeSettings = new GsonRuntimeSettings();
    gsonRuntimeSettings.addDeserializationExclusionStrategy = true;
    gsonRuntimeSettings.addSerializationExclusionStrategy = true;
    gsonRuntimeSettings.disableInnerClassSerialization = true;
    gsonRuntimeSettings.excludeFieldsWithModifiers = true;
    gsonRuntimeSettings.excludeFieldsWithoutExposeAnnotation = true;
    gsonRuntimeSettings.generateNonExecutableJson = true;
    gsonRuntimeSettings.instanceCreatorClassPool = KotlinConstants.dummyClassPool;
    gsonRuntimeSettings.registerTypeAdapterFactory = true;
    gsonRuntimeSettings.serializeNulls = true;
    gsonRuntimeSettings.serializeSpecialFloatingPointValues = true;
    gsonRuntimeSettings.setExclusionStrategies = true;
    gsonRuntimeSettings.setFieldNamingPolicy = true;
    gsonRuntimeSettings.setFieldNamingStrategy = true;
    gsonRuntimeSettings.setLongSerializationPolicy = true;
    gsonRuntimeSettings.setVersion = true;
    gsonRuntimeSettings.typeAdapterClassPool = KotlinConstants.dummyClassPool;
    gsonRuntimeSettings.typeAdapterClassPool = classPool;

    // Act and Assert
    assertTrue(
        inlinePrimitiveIntegerSerializer.canSerialize(
            KotlinConstants.dummyClassPool, gsonRuntimeSettings));
  }

  /**
   * Test InlinePrimitiveIntegerSerializer {@link
   * InlinePrimitiveIntegerSerializer#serialize(ProgramClass, ProgramField,
   * CompactCodeAttributeComposer, GsonRuntimeSettings)}.
   *
   * <p>Method under test: {@link InlinePrimitiveIntegerSerializer#serialize(ProgramClass,
   * ProgramField, CompactCodeAttributeComposer, GsonRuntimeSettings)}
   */
  @Test
  @DisplayName(
      "Test InlinePrimitiveIntegerSerializer serialize(ProgramClass, ProgramField, CompactCodeAttributeComposer, GsonRuntimeSettings)")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void InlinePrimitiveIntegerSerializer.serialize(ProgramClass, ProgramField, CompactCodeAttributeComposer, GsonRuntimeSettings)"
  })
  void testInlinePrimitiveIntegerSerializerSerialize() {
    // Arrange
    InlinePrimitiveIntegerSerializer inlinePrimitiveIntegerSerializer =
        new InlinePrimitiveIntegerSerializer();
    NamedClass programClass = new NamedClass("Member Name");
    NamedField programField = new NamedField("Field Name", "Field Descriptor");
    Constant[] constantPool = new Constant[] {new ClassConstant()};
    ProgramClass targetClass = new ProgramClass(2, 0, constantPool, 2, 2, 2);
    CompactCodeAttributeComposer ____ = new CompactCodeAttributeComposer(targetClass);
    GsonRuntimeSettings gsonRuntimeSettings = new GsonRuntimeSettings();
    gsonRuntimeSettings.addDeserializationExclusionStrategy = true;
    gsonRuntimeSettings.addSerializationExclusionStrategy = true;
    gsonRuntimeSettings.disableInnerClassSerialization = true;
    gsonRuntimeSettings.excludeFieldsWithModifiers = true;
    gsonRuntimeSettings.excludeFieldsWithoutExposeAnnotation = true;
    gsonRuntimeSettings.generateNonExecutableJson = true;
    gsonRuntimeSettings.instanceCreatorClassPool = KotlinConstants.dummyClassPool;
    gsonRuntimeSettings.registerTypeAdapterFactory = true;
    gsonRuntimeSettings.serializeNulls = true;
    gsonRuntimeSettings.serializeSpecialFloatingPointValues = true;
    gsonRuntimeSettings.setExclusionStrategies = true;
    gsonRuntimeSettings.setFieldNamingPolicy = true;
    gsonRuntimeSettings.setFieldNamingStrategy = true;
    gsonRuntimeSettings.setLongSerializationPolicy = true;
    gsonRuntimeSettings.setVersion = true;
    gsonRuntimeSettings.typeAdapterClassPool = KotlinConstants.dummyClassPool;

    // Act
    inlinePrimitiveIntegerSerializer.serialize(
        programClass, programField, ____, gsonRuntimeSettings);

    // Assert
    ProgramClass targetClass2 = ____.getTargetClass();
    assertEquals(18, targetClass2.u2constantPoolCount);
    assertSame(targetClass.constantPool, targetClass2.constantPool);
  }

  /**
   * Test InlinePrimitiveIntegerSerializer {@link
   * InlinePrimitiveIntegerSerializer#serialize(ProgramClass, ProgramField,
   * CompactCodeAttributeComposer, GsonRuntimeSettings)}.
   *
   * <p>Method under test: {@link InlinePrimitiveIntegerSerializer#serialize(ProgramClass,
   * ProgramField, CompactCodeAttributeComposer, GsonRuntimeSettings)}
   */
  @Test
  @DisplayName(
      "Test InlinePrimitiveIntegerSerializer serialize(ProgramClass, ProgramField, CompactCodeAttributeComposer, GsonRuntimeSettings)")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void InlinePrimitiveIntegerSerializer.serialize(ProgramClass, ProgramField, CompactCodeAttributeComposer, GsonRuntimeSettings)"
  })
  void testInlinePrimitiveIntegerSerializerSerialize2() {
    // Arrange
    InlinePrimitiveIntegerSerializer inlinePrimitiveIntegerSerializer =
        new InlinePrimitiveIntegerSerializer();
    NamedClass programClass = new NamedClass("Member Name");
    NamedField programField = new NamedField("Field Name", "Field Descriptor");
    ClassConstant classConstant = new ClassConstant();
    ClassConstant classConstant2 = new ClassConstant();
    ProgramClass targetClass =
        new ProgramClass(
            2, 3, new Constant[] {classConstant, classConstant2, new ClassConstant()}, 2, 2, 2);
    CompactCodeAttributeComposer ____ = new CompactCodeAttributeComposer(targetClass);
    GsonRuntimeSettings gsonRuntimeSettings = new GsonRuntimeSettings();
    gsonRuntimeSettings.addDeserializationExclusionStrategy = true;
    gsonRuntimeSettings.addSerializationExclusionStrategy = true;
    gsonRuntimeSettings.disableInnerClassSerialization = true;
    gsonRuntimeSettings.excludeFieldsWithModifiers = true;
    gsonRuntimeSettings.excludeFieldsWithoutExposeAnnotation = true;
    gsonRuntimeSettings.generateNonExecutableJson = true;
    gsonRuntimeSettings.instanceCreatorClassPool = KotlinConstants.dummyClassPool;
    gsonRuntimeSettings.registerTypeAdapterFactory = true;
    gsonRuntimeSettings.serializeNulls = true;
    gsonRuntimeSettings.serializeSpecialFloatingPointValues = true;
    gsonRuntimeSettings.setExclusionStrategies = true;
    gsonRuntimeSettings.setFieldNamingPolicy = true;
    gsonRuntimeSettings.setFieldNamingStrategy = true;
    gsonRuntimeSettings.setLongSerializationPolicy = true;
    gsonRuntimeSettings.setVersion = true;
    gsonRuntimeSettings.typeAdapterClassPool = KotlinConstants.dummyClassPool;

    // Act
    inlinePrimitiveIntegerSerializer.serialize(
        programClass, programField, ____, gsonRuntimeSettings);

    // Assert
    ProgramClass targetClass2 = ____.getTargetClass();
    assertEquals(21, targetClass2.u2constantPoolCount);
    assertSame(targetClass.constantPool, targetClass2.constantPool);
  }

  /**
   * Test InlinePrimitiveIntegerSerializer {@link
   * InlinePrimitiveIntegerSerializer#serialize(ProgramClass, ProgramField,
   * CompactCodeAttributeComposer, GsonRuntimeSettings)}.
   *
   * <ul>
   *   <li>Then sixteenth element {@link ClassConstant}.
   * </ul>
   *
   * <p>Method under test: {@link InlinePrimitiveIntegerSerializer#serialize(ProgramClass,
   * ProgramField, CompactCodeAttributeComposer, GsonRuntimeSettings)}
   */
  @Test
  @DisplayName(
      "Test InlinePrimitiveIntegerSerializer serialize(ProgramClass, ProgramField, CompactCodeAttributeComposer, GsonRuntimeSettings); then sixteenth element ClassConstant")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void InlinePrimitiveIntegerSerializer.serialize(ProgramClass, ProgramField, CompactCodeAttributeComposer, GsonRuntimeSettings)"
  })
  void testInlinePrimitiveIntegerSerializerSerialize_thenSixteenthElementClassConstant() {
    // Arrange
    InlinePrimitiveIntegerSerializer inlinePrimitiveIntegerSerializer =
        new InlinePrimitiveIntegerSerializer();
    NamedClass programClass = new NamedClass("java/lang/Integer");
    NamedField programField = new NamedField("Field Name", "Field Descriptor");
    Constant[] constantPool = new Constant[] {new ClassConstant()};
    CompactCodeAttributeComposer ____ =
        new CompactCodeAttributeComposer(new ProgramClass(2, 1, constantPool, 2, 2, 2));
    GsonRuntimeSettings gsonRuntimeSettings = new GsonRuntimeSettings();
    gsonRuntimeSettings.addDeserializationExclusionStrategy = true;
    gsonRuntimeSettings.addSerializationExclusionStrategy = true;
    gsonRuntimeSettings.disableInnerClassSerialization = true;
    gsonRuntimeSettings.excludeFieldsWithModifiers = true;
    gsonRuntimeSettings.excludeFieldsWithoutExposeAnnotation = true;
    gsonRuntimeSettings.generateNonExecutableJson = true;
    gsonRuntimeSettings.instanceCreatorClassPool = KotlinConstants.dummyClassPool;
    gsonRuntimeSettings.registerTypeAdapterFactory = true;
    gsonRuntimeSettings.serializeNulls = true;
    gsonRuntimeSettings.serializeSpecialFloatingPointValues = true;
    gsonRuntimeSettings.setExclusionStrategies = true;
    gsonRuntimeSettings.setFieldNamingPolicy = true;
    gsonRuntimeSettings.setFieldNamingStrategy = true;
    gsonRuntimeSettings.setLongSerializationPolicy = true;
    gsonRuntimeSettings.setVersion = true;
    gsonRuntimeSettings.typeAdapterClassPool = KotlinConstants.dummyClassPool;

    // Act
    inlinePrimitiveIntegerSerializer.serialize(
        programClass, programField, ____, gsonRuntimeSettings);

    // Assert
    ProgramClass targetClass = ____.getTargetClass();
    Constant[] constantArray = targetClass.constantPool;
    assertTrue(constantArray[15] instanceof ClassConstant);
    assertTrue(constantArray[5] instanceof ClassConstant);
    assertTrue(constantArray[10] instanceof MethodrefConstant);
    assertTrue(constantArray[Short.SIZE] instanceof MethodrefConstant);
    assertTrue(constantArray[13] instanceof NameAndTypeConstant);
    assertTrue(constantArray[11] instanceof Utf8Constant);
    assertEquals(17, constantArray.length);
    assertEquals(17, targetClass.u2constantPoolCount);
  }

  /**
   * Test InlinePrimitiveIntegerSerializer {@link
   * InlinePrimitiveIntegerSerializer#serialize(ProgramClass, ProgramField,
   * CompactCodeAttributeComposer, GsonRuntimeSettings)}.
   *
   * <ul>
   *   <li>Then twelfth element {@link ClassConstant}.
   * </ul>
   *
   * <p>Method under test: {@link InlinePrimitiveIntegerSerializer#serialize(ProgramClass,
   * ProgramField, CompactCodeAttributeComposer, GsonRuntimeSettings)}
   */
  @Test
  @DisplayName(
      "Test InlinePrimitiveIntegerSerializer serialize(ProgramClass, ProgramField, CompactCodeAttributeComposer, GsonRuntimeSettings); then twelfth element ClassConstant")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void InlinePrimitiveIntegerSerializer.serialize(ProgramClass, ProgramField, CompactCodeAttributeComposer, GsonRuntimeSettings)"
  })
  void testInlinePrimitiveIntegerSerializerSerialize_thenTwelfthElementClassConstant() {
    // Arrange
    InlinePrimitiveIntegerSerializer inlinePrimitiveIntegerSerializer =
        new InlinePrimitiveIntegerSerializer();
    NamedClass programClass = new NamedClass("Member Name");
    NamedField programField = new NamedField("Field Name", "Field Descriptor");
    Constant[] constantPool = new Constant[] {new ClassConstant()};
    CompactCodeAttributeComposer ____ =
        new CompactCodeAttributeComposer(new ProgramClass(2, 1, constantPool, 2, 2, 2));
    GsonRuntimeSettings gsonRuntimeSettings = new GsonRuntimeSettings();
    gsonRuntimeSettings.addDeserializationExclusionStrategy = true;
    gsonRuntimeSettings.addSerializationExclusionStrategy = true;
    gsonRuntimeSettings.disableInnerClassSerialization = true;
    gsonRuntimeSettings.excludeFieldsWithModifiers = true;
    gsonRuntimeSettings.excludeFieldsWithoutExposeAnnotation = true;
    gsonRuntimeSettings.generateNonExecutableJson = true;
    gsonRuntimeSettings.instanceCreatorClassPool = KotlinConstants.dummyClassPool;
    gsonRuntimeSettings.registerTypeAdapterFactory = true;
    gsonRuntimeSettings.serializeNulls = true;
    gsonRuntimeSettings.serializeSpecialFloatingPointValues = true;
    gsonRuntimeSettings.setExclusionStrategies = true;
    gsonRuntimeSettings.setFieldNamingPolicy = true;
    gsonRuntimeSettings.setFieldNamingStrategy = true;
    gsonRuntimeSettings.setLongSerializationPolicy = true;
    gsonRuntimeSettings.setVersion = true;
    gsonRuntimeSettings.typeAdapterClassPool = KotlinConstants.dummyClassPool;

    // Act
    inlinePrimitiveIntegerSerializer.serialize(
        programClass, programField, ____, gsonRuntimeSettings);

    // Assert
    ProgramClass targetClass = ____.getTargetClass();
    Constant[] constantArray = targetClass.constantPool;
    assertTrue(constantArray[11] instanceof ClassConstant);
    assertTrue(constantArray[17] instanceof ClassConstant);
    assertTrue(constantArray[12] instanceof MethodrefConstant);
    assertTrue(constantArray[18] instanceof MethodrefConstant);
    assertTrue(constantArray[15] instanceof NameAndTypeConstant);
    assertTrue(constantArray[10] instanceof Utf8Constant);
    assertTrue(constantArray[Short.SIZE] instanceof Utf8Constant);
    assertEquals(19, targetClass.u2constantPoolCount);
    assertEquals(33, constantArray.length);
  }

  /**
   * Test InlineStringSerializer {@link InlineStringSerializer#canSerialize(ClassPool,
   * GsonRuntimeSettings)}.
   *
   * <p>Method under test: {@link InlineStringSerializer#canSerialize(ClassPool,
   * GsonRuntimeSettings)}
   */
  @Test
  @DisplayName("Test InlineStringSerializer canSerialize(ClassPool, GsonRuntimeSettings)")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean InlineStringSerializer.canSerialize(ClassPool, GsonRuntimeSettings)"})
  void testInlineStringSerializerCanSerialize() {
    // Arrange
    InlineStringSerializer inlineStringSerializer = new InlineStringSerializer();

    ClassPool classPool = KotlinConstants.dummyClassPool;
    classPool.addClass("java/lang/String", null);
    GsonRuntimeSettings gsonRuntimeSettings = new GsonRuntimeSettings();
    gsonRuntimeSettings.addDeserializationExclusionStrategy = true;
    gsonRuntimeSettings.addSerializationExclusionStrategy = true;
    gsonRuntimeSettings.disableInnerClassSerialization = true;
    gsonRuntimeSettings.excludeFieldsWithModifiers = true;
    gsonRuntimeSettings.excludeFieldsWithoutExposeAnnotation = true;
    gsonRuntimeSettings.generateNonExecutableJson = true;
    gsonRuntimeSettings.instanceCreatorClassPool = KotlinConstants.dummyClassPool;
    gsonRuntimeSettings.registerTypeAdapterFactory = true;
    gsonRuntimeSettings.serializeNulls = true;
    gsonRuntimeSettings.serializeSpecialFloatingPointValues = true;
    gsonRuntimeSettings.setExclusionStrategies = true;
    gsonRuntimeSettings.setFieldNamingPolicy = true;
    gsonRuntimeSettings.setFieldNamingStrategy = true;
    gsonRuntimeSettings.setLongSerializationPolicy = true;
    gsonRuntimeSettings.setVersion = true;
    gsonRuntimeSettings.typeAdapterClassPool = KotlinConstants.dummyClassPool;
    gsonRuntimeSettings.typeAdapterClassPool = classPool;

    // Act and Assert
    assertTrue(
        inlineStringSerializer.canSerialize(KotlinConstants.dummyClassPool, gsonRuntimeSettings));
  }

  /**
   * Test InlineStringSerializer {@link InlineStringSerializer#serialize(ProgramClass, ProgramField,
   * CompactCodeAttributeComposer, GsonRuntimeSettings)}.
   *
   * <p>Method under test: {@link InlineStringSerializer#serialize(ProgramClass, ProgramField,
   * CompactCodeAttributeComposer, GsonRuntimeSettings)}
   */
  @Test
  @DisplayName(
      "Test InlineStringSerializer serialize(ProgramClass, ProgramField, CompactCodeAttributeComposer, GsonRuntimeSettings)")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void InlineStringSerializer.serialize(ProgramClass, ProgramField, CompactCodeAttributeComposer, GsonRuntimeSettings)"
  })
  void testInlineStringSerializerSerialize() {
    // Arrange
    InlineStringSerializer inlineStringSerializer = new InlineStringSerializer();
    NamedClass programClass = new NamedClass("Member Name");
    NamedField programField = new NamedField("Field Name", "Field Descriptor");
    Constant[] constantPool = new Constant[] {new ClassConstant()};
    ProgramClass targetClass = new ProgramClass(2, 0, constantPool, 2, 2, 2);
    CompactCodeAttributeComposer ____ = new CompactCodeAttributeComposer(targetClass);
    GsonRuntimeSettings gsonRuntimeSettings = new GsonRuntimeSettings();
    gsonRuntimeSettings.addDeserializationExclusionStrategy = true;
    gsonRuntimeSettings.addSerializationExclusionStrategy = true;
    gsonRuntimeSettings.disableInnerClassSerialization = true;
    gsonRuntimeSettings.excludeFieldsWithModifiers = true;
    gsonRuntimeSettings.excludeFieldsWithoutExposeAnnotation = true;
    gsonRuntimeSettings.generateNonExecutableJson = true;
    gsonRuntimeSettings.instanceCreatorClassPool = KotlinConstants.dummyClassPool;
    gsonRuntimeSettings.registerTypeAdapterFactory = true;
    gsonRuntimeSettings.serializeNulls = true;
    gsonRuntimeSettings.serializeSpecialFloatingPointValues = true;
    gsonRuntimeSettings.setExclusionStrategies = true;
    gsonRuntimeSettings.setFieldNamingPolicy = true;
    gsonRuntimeSettings.setFieldNamingStrategy = true;
    gsonRuntimeSettings.setLongSerializationPolicy = true;
    gsonRuntimeSettings.setVersion = true;
    gsonRuntimeSettings.typeAdapterClassPool = KotlinConstants.dummyClassPool;

    // Act
    inlineStringSerializer.serialize(programClass, programField, ____, gsonRuntimeSettings);

    // Assert
    ProgramClass targetClass2 = ____.getTargetClass();
    assertEquals(12, targetClass2.u2constantPoolCount);
    assertSame(targetClass.constantPool, targetClass2.constantPool);
  }

  /**
   * Test InlineStringSerializer {@link InlineStringSerializer#serialize(ProgramClass, ProgramField,
   * CompactCodeAttributeComposer, GsonRuntimeSettings)}.
   *
   * <p>Method under test: {@link InlineStringSerializer#serialize(ProgramClass, ProgramField,
   * CompactCodeAttributeComposer, GsonRuntimeSettings)}
   */
  @Test
  @DisplayName(
      "Test InlineStringSerializer serialize(ProgramClass, ProgramField, CompactCodeAttributeComposer, GsonRuntimeSettings)")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void InlineStringSerializer.serialize(ProgramClass, ProgramField, CompactCodeAttributeComposer, GsonRuntimeSettings)"
  })
  void testInlineStringSerializerSerialize2() {
    // Arrange
    InlineStringSerializer inlineStringSerializer = new InlineStringSerializer();
    NamedClass programClass = new NamedClass("Member Name");
    NamedField programField = new NamedField("Field Name", "Field Descriptor");
    ClassConstant classConstant = new ClassConstant();
    ClassConstant classConstant2 = new ClassConstant();
    ProgramClass targetClass =
        new ProgramClass(
            2, 3, new Constant[] {classConstant, classConstant2, new ClassConstant()}, 2, 2, 2);
    CompactCodeAttributeComposer ____ = new CompactCodeAttributeComposer(targetClass);
    GsonRuntimeSettings gsonRuntimeSettings = new GsonRuntimeSettings();
    gsonRuntimeSettings.addDeserializationExclusionStrategy = true;
    gsonRuntimeSettings.addSerializationExclusionStrategy = true;
    gsonRuntimeSettings.disableInnerClassSerialization = true;
    gsonRuntimeSettings.excludeFieldsWithModifiers = true;
    gsonRuntimeSettings.excludeFieldsWithoutExposeAnnotation = true;
    gsonRuntimeSettings.generateNonExecutableJson = true;
    gsonRuntimeSettings.instanceCreatorClassPool = KotlinConstants.dummyClassPool;
    gsonRuntimeSettings.registerTypeAdapterFactory = true;
    gsonRuntimeSettings.serializeNulls = true;
    gsonRuntimeSettings.serializeSpecialFloatingPointValues = true;
    gsonRuntimeSettings.setExclusionStrategies = true;
    gsonRuntimeSettings.setFieldNamingPolicy = true;
    gsonRuntimeSettings.setFieldNamingStrategy = true;
    gsonRuntimeSettings.setLongSerializationPolicy = true;
    gsonRuntimeSettings.setVersion = true;
    gsonRuntimeSettings.typeAdapterClassPool = KotlinConstants.dummyClassPool;

    // Act
    inlineStringSerializer.serialize(programClass, programField, ____, gsonRuntimeSettings);

    // Assert
    ProgramClass targetClass2 = ____.getTargetClass();
    assertEquals(15, targetClass2.u2constantPoolCount);
    assertSame(targetClass.constantPool, targetClass2.constantPool);
  }

  /**
   * Test InlineStringSerializer {@link InlineStringSerializer#serialize(ProgramClass, ProgramField,
   * CompactCodeAttributeComposer, GsonRuntimeSettings)}.
   *
   * <ul>
   *   <li>Then sixth element {@link ClassConstant}.
   * </ul>
   *
   * <p>Method under test: {@link InlineStringSerializer#serialize(ProgramClass, ProgramField,
   * CompactCodeAttributeComposer, GsonRuntimeSettings)}
   */
  @Test
  @DisplayName(
      "Test InlineStringSerializer serialize(ProgramClass, ProgramField, CompactCodeAttributeComposer, GsonRuntimeSettings); then sixth element ClassConstant")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void InlineStringSerializer.serialize(ProgramClass, ProgramField, CompactCodeAttributeComposer, GsonRuntimeSettings)"
  })
  void testInlineStringSerializerSerialize_thenSixthElementClassConstant() {
    // Arrange
    InlineStringSerializer inlineStringSerializer = new InlineStringSerializer();
    NamedClass programClass = new NamedClass(GsonClassConstants.NAME_JSON_WRITER);
    NamedField programField = new NamedField("Field Name", "Field Descriptor");
    Constant[] constantPool = new Constant[] {new ClassConstant()};
    CompactCodeAttributeComposer ____ =
        new CompactCodeAttributeComposer(new ProgramClass(2, 1, constantPool, 2, 2, 2));
    GsonRuntimeSettings gsonRuntimeSettings = new GsonRuntimeSettings();
    gsonRuntimeSettings.addDeserializationExclusionStrategy = true;
    gsonRuntimeSettings.addSerializationExclusionStrategy = true;
    gsonRuntimeSettings.disableInnerClassSerialization = true;
    gsonRuntimeSettings.excludeFieldsWithModifiers = true;
    gsonRuntimeSettings.excludeFieldsWithoutExposeAnnotation = true;
    gsonRuntimeSettings.generateNonExecutableJson = true;
    gsonRuntimeSettings.instanceCreatorClassPool = KotlinConstants.dummyClassPool;
    gsonRuntimeSettings.registerTypeAdapterFactory = true;
    gsonRuntimeSettings.serializeNulls = true;
    gsonRuntimeSettings.serializeSpecialFloatingPointValues = true;
    gsonRuntimeSettings.setExclusionStrategies = true;
    gsonRuntimeSettings.setFieldNamingPolicy = true;
    gsonRuntimeSettings.setFieldNamingStrategy = true;
    gsonRuntimeSettings.setLongSerializationPolicy = true;
    gsonRuntimeSettings.setVersion = true;
    gsonRuntimeSettings.typeAdapterClassPool = KotlinConstants.dummyClassPool;

    // Act
    inlineStringSerializer.serialize(programClass, programField, ____, gsonRuntimeSettings);

    // Assert
    ProgramClass targetClass = ____.getTargetClass();
    Constant[] constantArray = targetClass.constantPool;
    assertTrue(constantArray[5] instanceof ClassConstant);
    assertTrue(constantArray[10] instanceof MethodrefConstant);
    assertNull(constantArray[11]);
    assertEquals(11, targetClass.u2constantPoolCount);
    assertEquals(17, constantArray.length);
  }

  /**
   * Test InlineStringSerializer {@link InlineStringSerializer#serialize(ProgramClass, ProgramField,
   * CompactCodeAttributeComposer, GsonRuntimeSettings)}.
   *
   * <ul>
   *   <li>Then twelfth element {@link ClassConstant}.
   * </ul>
   *
   * <p>Method under test: {@link InlineStringSerializer#serialize(ProgramClass, ProgramField,
   * CompactCodeAttributeComposer, GsonRuntimeSettings)}
   */
  @Test
  @DisplayName(
      "Test InlineStringSerializer serialize(ProgramClass, ProgramField, CompactCodeAttributeComposer, GsonRuntimeSettings); then twelfth element ClassConstant")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void InlineStringSerializer.serialize(ProgramClass, ProgramField, CompactCodeAttributeComposer, GsonRuntimeSettings)"
  })
  void testInlineStringSerializerSerialize_thenTwelfthElementClassConstant() {
    // Arrange
    InlineStringSerializer inlineStringSerializer = new InlineStringSerializer();
    NamedClass programClass = new NamedClass("Member Name");
    NamedField programField = new NamedField("Field Name", "Field Descriptor");
    Constant[] constantPool = new Constant[] {new ClassConstant()};
    CompactCodeAttributeComposer ____ =
        new CompactCodeAttributeComposer(new ProgramClass(2, 1, constantPool, 2, 2, 2));
    GsonRuntimeSettings gsonRuntimeSettings = new GsonRuntimeSettings();
    gsonRuntimeSettings.addDeserializationExclusionStrategy = true;
    gsonRuntimeSettings.addSerializationExclusionStrategy = true;
    gsonRuntimeSettings.disableInnerClassSerialization = true;
    gsonRuntimeSettings.excludeFieldsWithModifiers = true;
    gsonRuntimeSettings.excludeFieldsWithoutExposeAnnotation = true;
    gsonRuntimeSettings.generateNonExecutableJson = true;
    gsonRuntimeSettings.instanceCreatorClassPool = KotlinConstants.dummyClassPool;
    gsonRuntimeSettings.registerTypeAdapterFactory = true;
    gsonRuntimeSettings.serializeNulls = true;
    gsonRuntimeSettings.serializeSpecialFloatingPointValues = true;
    gsonRuntimeSettings.setExclusionStrategies = true;
    gsonRuntimeSettings.setFieldNamingPolicy = true;
    gsonRuntimeSettings.setFieldNamingStrategy = true;
    gsonRuntimeSettings.setLongSerializationPolicy = true;
    gsonRuntimeSettings.setVersion = true;
    gsonRuntimeSettings.typeAdapterClassPool = KotlinConstants.dummyClassPool;

    // Act
    inlineStringSerializer.serialize(programClass, programField, ____, gsonRuntimeSettings);

    // Assert
    ProgramClass targetClass = ____.getTargetClass();
    Constant[] constantArray = targetClass.constantPool;
    assertTrue(constantArray[11] instanceof ClassConstant);
    assertTrue(constantArray[6] instanceof FieldrefConstant);
    assertTrue(constantArray[12] instanceof MethodrefConstant);
    assertTrue(constantArray[10] instanceof Utf8Constant);
    assertTrue(constantArray[2] instanceof Utf8Constant);
    assertTrue(constantArray[8] instanceof Utf8Constant);
    assertEquals(13, targetClass.u2constantPoolCount);
    assertEquals(17, constantArray.length);
  }
}
