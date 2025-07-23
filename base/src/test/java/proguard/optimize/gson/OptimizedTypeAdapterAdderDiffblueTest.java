package proguard.optimize.gson;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.anyBoolean;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.util.HashMap;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import proguard.classfile.ClassPool;
import proguard.classfile.Clazz;
import proguard.classfile.LibraryClass;
import proguard.classfile.ProgramClass;
import proguard.classfile.editor.CodeAttributeEditor;
import proguard.classfile.visitor.ClassVisitor;
import proguard.io.ExtraDataEntryNameMap;

class OptimizedTypeAdapterAdderDiffblueTest {
  /**
   * Test {@link OptimizedTypeAdapterAdder#visitProgramClass(ProgramClass)}.
   *
   * <p>Method under test: {@link OptimizedTypeAdapterAdder#visitProgramClass(ProgramClass)}
   */
  @Test
  @DisplayName("Test visitProgramClass(ProgramClass)")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void OptimizedTypeAdapterAdder.visitProgramClass(ProgramClass)"})
  void testVisitProgramClass() {
    // Arrange
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
    ClassPool programClassPool = new ClassPool();
    ClassPool libraryClassPool = new ClassPool();
    CodeAttributeEditor codeAttributeEditor = new CodeAttributeEditor();
    OptimizedJsonInfo serializationInfo = new OptimizedJsonInfo();
    OptimizedJsonInfo deserializationInfo = new OptimizedJsonInfo();
    ExtraDataEntryNameMap extraDataEntryNameMap = new ExtraDataEntryNameMap();
    OptimizedTypeAdapterAdder optimizedTypeAdapterAdder =
        new OptimizedTypeAdapterAdder(
            programClassPool,
            libraryClassPool,
            codeAttributeEditor,
            serializationInfo,
            deserializationInfo,
            extraDataEntryNameMap,
            new HashMap<>(),
            gsonRuntimeSettings);
    ProgramClass programClass = mock(ProgramClass.class);
    when(programClass.getAccessFlags()).thenReturn(1);
    when(programClass.getName()).thenReturn("Name");

    // Act
    optimizedTypeAdapterAdder.visitProgramClass(programClass);

    // Assert
    verify(programClass).getAccessFlags();
    verify(programClass, atLeast(1)).getName();
  }

  /**
   * Test {@link OptimizedTypeAdapterAdder#visitProgramClass(ProgramClass)}.
   *
   * <p>Method under test: {@link OptimizedTypeAdapterAdder#visitProgramClass(ProgramClass)}
   */
  @Test
  @DisplayName("Test visitProgramClass(ProgramClass)")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void OptimizedTypeAdapterAdder.visitProgramClass(ProgramClass)"})
  void testVisitProgramClass2() {
    // Arrange
    ClassPool programClassPool = mock(ClassPool.class);
    when(programClassPool.getClass(Mockito.<String>any())).thenReturn(null);
    doNothing().when(programClassPool).addClass(Mockito.<Clazz>any());
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
    ClassPool libraryClassPool = new ClassPool();
    CodeAttributeEditor codeAttributeEditor = new CodeAttributeEditor();
    OptimizedJsonInfo serializationInfo = new OptimizedJsonInfo();
    OptimizedJsonInfo deserializationInfo = new OptimizedJsonInfo();
    ExtraDataEntryNameMap extraDataEntryNameMap = new ExtraDataEntryNameMap();
    OptimizedTypeAdapterAdder optimizedTypeAdapterAdder =
        new OptimizedTypeAdapterAdder(
            programClassPool,
            libraryClassPool,
            codeAttributeEditor,
            serializationInfo,
            deserializationInfo,
            extraDataEntryNameMap,
            new HashMap<>(),
            gsonRuntimeSettings);
    ProgramClass programClass = mock(ProgramClass.class);
    when(programClass.getAccessFlags()).thenReturn(1);
    when(programClass.getName()).thenReturn("Name");

    // Act
    optimizedTypeAdapterAdder.visitProgramClass(programClass);

    // Assert
    verify(programClassPool).addClass(isA(Clazz.class));
    verify(programClassPool, atLeast(1)).getClass(Mockito.<String>any());
    verify(programClass).getAccessFlags();
    verify(programClass, atLeast(1)).getName();
  }

  /**
   * Test {@link OptimizedTypeAdapterAdder#visitProgramClass(ProgramClass)}.
   *
   * <ul>
   *   <li>Given {@link ClassPool} {@link ClassPool#getClass(String)} return {@link
   *       LibraryClass#LibraryClass()}.
   *   <li>Then calls {@link ClassPool#getClass(String)}.
   * </ul>
   *
   * <p>Method under test: {@link OptimizedTypeAdapterAdder#visitProgramClass(ProgramClass)}
   */
  @Test
  @DisplayName(
      "Test visitProgramClass(ProgramClass); given ClassPool getClass(String) return LibraryClass(); then calls getClass(String)")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void OptimizedTypeAdapterAdder.visitProgramClass(ProgramClass)"})
  void testVisitProgramClass_givenClassPoolGetClassReturnLibraryClass_thenCallsGetClass() {
    // Arrange
    ClassPool programClassPool = mock(ClassPool.class);
    when(programClassPool.getClass(Mockito.<String>any())).thenReturn(new LibraryClass());
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
    ClassPool libraryClassPool = new ClassPool();
    CodeAttributeEditor codeAttributeEditor = new CodeAttributeEditor();
    OptimizedJsonInfo serializationInfo = new OptimizedJsonInfo();
    OptimizedJsonInfo deserializationInfo = new OptimizedJsonInfo();
    ExtraDataEntryNameMap extraDataEntryNameMap = new ExtraDataEntryNameMap();
    OptimizedTypeAdapterAdder optimizedTypeAdapterAdder =
        new OptimizedTypeAdapterAdder(
            programClassPool,
            libraryClassPool,
            codeAttributeEditor,
            serializationInfo,
            deserializationInfo,
            extraDataEntryNameMap,
            new HashMap<>(),
            gsonRuntimeSettings);
    ProgramClass programClass = mock(ProgramClass.class);
    when(programClass.getName()).thenReturn("Name");

    // Act
    optimizedTypeAdapterAdder.visitProgramClass(programClass);

    // Assert
    verify(programClassPool).getClass(eq("/OptimizedNameTypeAdapter"));
    verify(programClass).getName();
  }

  /**
   * Test {@link OptimizedTypeAdapterAdder#visitProgramClass(ProgramClass)}.
   *
   * <ul>
   *   <li>Given {@link ClassPool} {@link ClassPool#getClass(String)} return {@link
   *       ProgramClass#ProgramClass()}.
   *   <li>Then calls {@link ClassPool#addClass(Clazz)}.
   * </ul>
   *
   * <p>Method under test: {@link OptimizedTypeAdapterAdder#visitProgramClass(ProgramClass)}
   */
  @Test
  @DisplayName(
      "Test visitProgramClass(ProgramClass); given ClassPool getClass(String) return ProgramClass(); then calls addClass(Clazz)")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void OptimizedTypeAdapterAdder.visitProgramClass(ProgramClass)"})
  void testVisitProgramClass_givenClassPoolGetClassReturnProgramClass_thenCallsAddClass() {
    // Arrange
    ClassPool programClassPool = mock(ClassPool.class);
    when(programClassPool.getClass(Mockito.<String>any())).thenReturn(null);
    doNothing().when(programClassPool).addClass(Mockito.<Clazz>any());
    ClassPool libraryClassPool = mock(ClassPool.class);
    when(libraryClassPool.getClass(Mockito.<String>any())).thenReturn(new ProgramClass());
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
    CodeAttributeEditor codeAttributeEditor = new CodeAttributeEditor();
    OptimizedJsonInfo serializationInfo = new OptimizedJsonInfo();
    OptimizedJsonInfo deserializationInfo = new OptimizedJsonInfo();
    ExtraDataEntryNameMap extraDataEntryNameMap = new ExtraDataEntryNameMap();
    OptimizedTypeAdapterAdder optimizedTypeAdapterAdder =
        new OptimizedTypeAdapterAdder(
            programClassPool,
            libraryClassPool,
            codeAttributeEditor,
            serializationInfo,
            deserializationInfo,
            extraDataEntryNameMap,
            new HashMap<>(),
            gsonRuntimeSettings);
    ProgramClass programClass = mock(ProgramClass.class);
    when(programClass.getAccessFlags()).thenReturn(1);
    when(programClass.getName()).thenReturn("Name");

    // Act
    optimizedTypeAdapterAdder.visitProgramClass(programClass);

    // Assert
    verify(programClassPool).addClass(isA(Clazz.class));
    verify(libraryClassPool, atLeast(1)).getClass(Mockito.<String>any());
    verify(programClassPool, atLeast(1)).getClass(Mockito.<String>any());
    verify(programClass).getAccessFlags();
    verify(programClass, atLeast(1)).getName();
  }

  /**
   * Test {@link OptimizedTypeAdapterAdder#visitProgramClass(ProgramClass)}.
   *
   * <ul>
   *   <li>Given minus one.
   *   <li>When {@link ProgramClass} {@link ProgramClass#getAccessFlags()} return minus one.
   * </ul>
   *
   * <p>Method under test: {@link OptimizedTypeAdapterAdder#visitProgramClass(ProgramClass)}
   */
  @Test
  @DisplayName(
      "Test visitProgramClass(ProgramClass); given minus one; when ProgramClass getAccessFlags() return minus one")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void OptimizedTypeAdapterAdder.visitProgramClass(ProgramClass)"})
  void testVisitProgramClass_givenMinusOne_whenProgramClassGetAccessFlagsReturnMinusOne() {
    // Arrange
    ClassPool programClassPool = mock(ClassPool.class);
    when(programClassPool.getClass(Mockito.<String>any())).thenReturn(null);
    doNothing().when(programClassPool).addClass(Mockito.<Clazz>any());
    ProgramClass programClass = mock(ProgramClass.class);
    doThrow(new RuntimeException("Optimized"))
        .when(programClass)
        .hierarchyAccept(
            anyBoolean(), anyBoolean(), anyBoolean(), anyBoolean(), Mockito.<ClassVisitor>any());
    ClassPool libraryClassPool = mock(ClassPool.class);
    when(libraryClassPool.getClass(Mockito.<String>any())).thenReturn(programClass);
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
    CodeAttributeEditor codeAttributeEditor = new CodeAttributeEditor();
    OptimizedJsonInfo serializationInfo = new OptimizedJsonInfo();
    OptimizedJsonInfo deserializationInfo = new OptimizedJsonInfo();
    ExtraDataEntryNameMap extraDataEntryNameMap = new ExtraDataEntryNameMap();
    OptimizedTypeAdapterAdder optimizedTypeAdapterAdder =
        new OptimizedTypeAdapterAdder(
            programClassPool,
            libraryClassPool,
            codeAttributeEditor,
            serializationInfo,
            deserializationInfo,
            extraDataEntryNameMap,
            new HashMap<>(),
            gsonRuntimeSettings);
    ProgramClass programClass2 = mock(ProgramClass.class);
    when(programClass2.getAccessFlags()).thenReturn(-1);
    when(programClass2.getName()).thenReturn("Name");

    // Act and Assert
    assertThrows(
        RuntimeException.class, () -> optimizedTypeAdapterAdder.visitProgramClass(programClass2));
    verify(programClassPool).addClass(isA(Clazz.class));
    verify(programClassPool, atLeast(1)).getClass(Mockito.<String>any());
    verify(libraryClassPool).getClass(eq("com/google/gson/TypeAdapter"));
    verify(programClass2).getAccessFlags();
    verify(programClass2, atLeast(1)).getName();
    verify(programClass)
        .hierarchyAccept(eq(true), eq(true), eq(true), eq(false), isA(ClassVisitor.class));
  }

  /**
   * Test {@link OptimizedTypeAdapterAdder#visitProgramClass(ProgramClass)}.
   *
   * <ul>
   *   <li>Given {@link OptimizedClassConstants#NAME_OPTIMIZED_TYPE_ADAPTER_IMPL}.
   * </ul>
   *
   * <p>Method under test: {@link OptimizedTypeAdapterAdder#visitProgramClass(ProgramClass)}
   */
  @Test
  @DisplayName("Test visitProgramClass(ProgramClass); given NAME_OPTIMIZED_TYPE_ADAPTER_IMPL")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void OptimizedTypeAdapterAdder.visitProgramClass(ProgramClass)"})
  void testVisitProgramClass_givenName_optimized_type_adapter_impl() {
    // Arrange
    ClassPool programClassPool = mock(ClassPool.class);
    when(programClassPool.getClass(Mockito.<String>any())).thenReturn(null);
    doNothing().when(programClassPool).addClass(Mockito.<Clazz>any());
    ProgramClass programClass = mock(ProgramClass.class);
    doNothing().when(programClass).accept(Mockito.<ClassVisitor>any());
    doNothing()
        .when(programClass)
        .hierarchyAccept(
            anyBoolean(), anyBoolean(), anyBoolean(), anyBoolean(), Mockito.<ClassVisitor>any());
    ClassPool libraryClassPool = mock(ClassPool.class);
    when(libraryClassPool.getClass(Mockito.<String>any())).thenReturn(programClass);
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
    CodeAttributeEditor codeAttributeEditor = new CodeAttributeEditor();
    OptimizedJsonInfo serializationInfo = new OptimizedJsonInfo();
    OptimizedJsonInfo deserializationInfo = new OptimizedJsonInfo();
    ExtraDataEntryNameMap extraDataEntryNameMap = new ExtraDataEntryNameMap();
    OptimizedTypeAdapterAdder optimizedTypeAdapterAdder =
        new OptimizedTypeAdapterAdder(
            programClassPool,
            libraryClassPool,
            codeAttributeEditor,
            serializationInfo,
            deserializationInfo,
            extraDataEntryNameMap,
            new HashMap<>(),
            gsonRuntimeSettings);
    ProgramClass programClass2 = mock(ProgramClass.class);
    when(programClass2.getAccessFlags()).thenReturn(1);
    when(programClass2.getName())
        .thenReturn(OptimizedClassConstants.NAME_OPTIMIZED_TYPE_ADAPTER_IMPL);

    // Act
    optimizedTypeAdapterAdder.visitProgramClass(programClass2);

    // Assert
    verify(programClassPool).addClass(isA(Clazz.class));
    verify(libraryClassPool, atLeast(1)).getClass(Mockito.<String>any());
    verify(programClassPool, atLeast(1)).getClass(Mockito.<String>any());
    verify(programClass, atLeast(1)).accept(isA(ClassVisitor.class));
    verify(programClass2).getAccessFlags();
    verify(programClass2, atLeast(1)).getName();
    verify(programClass, atLeast(1))
        .hierarchyAccept(eq(true), eq(true), eq(true), eq(false), Mockito.<ClassVisitor>any());
  }

  /**
   * Test {@link OptimizedTypeAdapterAdder#visitProgramClass(ProgramClass)}.
   *
   * <ul>
   *   <li>Given {@link ProgramClass} {@link ProgramClass#accept(ClassVisitor)} does nothing.
   *   <li>Then calls {@link ProgramClass#accept(ClassVisitor)}.
   * </ul>
   *
   * <p>Method under test: {@link OptimizedTypeAdapterAdder#visitProgramClass(ProgramClass)}
   */
  @Test
  @DisplayName(
      "Test visitProgramClass(ProgramClass); given ProgramClass accept(ClassVisitor) does nothing; then calls accept(ClassVisitor)")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void OptimizedTypeAdapterAdder.visitProgramClass(ProgramClass)"})
  void testVisitProgramClass_givenProgramClassAcceptDoesNothing_thenCallsAccept() {
    // Arrange
    ClassPool programClassPool = mock(ClassPool.class);
    when(programClassPool.getClass(Mockito.<String>any())).thenReturn(null);
    doNothing().when(programClassPool).addClass(Mockito.<Clazz>any());
    ProgramClass programClass = mock(ProgramClass.class);
    doNothing().when(programClass).accept(Mockito.<ClassVisitor>any());
    doNothing()
        .when(programClass)
        .hierarchyAccept(
            anyBoolean(), anyBoolean(), anyBoolean(), anyBoolean(), Mockito.<ClassVisitor>any());
    ClassPool libraryClassPool = mock(ClassPool.class);
    when(libraryClassPool.getClass(Mockito.<String>any())).thenReturn(programClass);
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
    CodeAttributeEditor codeAttributeEditor = new CodeAttributeEditor();
    OptimizedJsonInfo serializationInfo = new OptimizedJsonInfo();
    OptimizedJsonInfo deserializationInfo = new OptimizedJsonInfo();
    ExtraDataEntryNameMap extraDataEntryNameMap = new ExtraDataEntryNameMap();
    OptimizedTypeAdapterAdder optimizedTypeAdapterAdder =
        new OptimizedTypeAdapterAdder(
            programClassPool,
            libraryClassPool,
            codeAttributeEditor,
            serializationInfo,
            deserializationInfo,
            extraDataEntryNameMap,
            new HashMap<>(),
            gsonRuntimeSettings);
    ProgramClass programClass2 = mock(ProgramClass.class);
    when(programClass2.getAccessFlags()).thenReturn(1);
    when(programClass2.getName()).thenReturn("Name");

    // Act
    optimizedTypeAdapterAdder.visitProgramClass(programClass2);

    // Assert
    verify(programClassPool).addClass(isA(Clazz.class));
    verify(libraryClassPool, atLeast(1)).getClass(Mockito.<String>any());
    verify(programClassPool, atLeast(1)).getClass(Mockito.<String>any());
    verify(programClass, atLeast(1)).accept(isA(ClassVisitor.class));
    verify(programClass2).getAccessFlags();
    verify(programClass2, atLeast(1)).getName();
    verify(programClass, atLeast(1))
        .hierarchyAccept(eq(true), eq(true), eq(true), eq(false), Mockito.<ClassVisitor>any());
  }

  /**
   * Test {@link OptimizedTypeAdapterAdder#visitProgramClass(ProgramClass)}.
   *
   * <ul>
   *   <li>Given {@link RuntimeException#RuntimeException(String)} with {@code Optimized}.
   * </ul>
   *
   * <p>Method under test: {@link OptimizedTypeAdapterAdder#visitProgramClass(ProgramClass)}
   */
  @Test
  @DisplayName(
      "Test visitProgramClass(ProgramClass); given RuntimeException(String) with 'Optimized'")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void OptimizedTypeAdapterAdder.visitProgramClass(ProgramClass)"})
  void testVisitProgramClass_givenRuntimeExceptionWithOptimized() {
    // Arrange
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
    ClassPool programClassPool = new ClassPool();
    ClassPool libraryClassPool = new ClassPool();
    CodeAttributeEditor codeAttributeEditor = new CodeAttributeEditor();
    OptimizedJsonInfo serializationInfo = new OptimizedJsonInfo();
    OptimizedJsonInfo deserializationInfo = new OptimizedJsonInfo();
    ExtraDataEntryNameMap extraDataEntryNameMap = new ExtraDataEntryNameMap();
    OptimizedTypeAdapterAdder optimizedTypeAdapterAdder =
        new OptimizedTypeAdapterAdder(
            programClassPool,
            libraryClassPool,
            codeAttributeEditor,
            serializationInfo,
            deserializationInfo,
            extraDataEntryNameMap,
            new HashMap<>(),
            gsonRuntimeSettings);
    ProgramClass programClass = mock(ProgramClass.class);
    when(programClass.getAccessFlags()).thenThrow(new RuntimeException("Optimized"));
    when(programClass.getName()).thenReturn("Name");

    // Act and Assert
    assertThrows(
        RuntimeException.class, () -> optimizedTypeAdapterAdder.visitProgramClass(programClass));
    verify(programClass).getAccessFlags();
    verify(programClass, atLeast(1)).getName();
  }
}
