package proguard.optimize.gson;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.anyBoolean;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import java.util.HashMap;
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
   * Method under test:
   * {@link OptimizedTypeAdapterAdder#visitProgramClass(ProgramClass)}
   */
  @Test
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
    OptimizedTypeAdapterAdder optimizedTypeAdapterAdder = new OptimizedTypeAdapterAdder(programClassPool,
        libraryClassPool, codeAttributeEditor, serializationInfo, deserializationInfo, extraDataEntryNameMap,
        new HashMap<>(), gsonRuntimeSettings);
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
   * Method under test:
   * {@link OptimizedTypeAdapterAdder#visitProgramClass(ProgramClass)}
   */
  @Test
  void testVisitProgramClass2() {
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
    OptimizedTypeAdapterAdder optimizedTypeAdapterAdder = new OptimizedTypeAdapterAdder(programClassPool,
        libraryClassPool, codeAttributeEditor, serializationInfo, deserializationInfo, extraDataEntryNameMap,
        new HashMap<>(), gsonRuntimeSettings);
    ProgramClass programClass = mock(ProgramClass.class);
    when(programClass.getAccessFlags()).thenThrow(new RuntimeException("Optimized"));
    when(programClass.getName()).thenReturn("Name");

    // Act and Assert
    assertThrows(RuntimeException.class, () -> optimizedTypeAdapterAdder.visitProgramClass(programClass));
    verify(programClass).getAccessFlags();
    verify(programClass, atLeast(1)).getName();
  }

  /**
   * Method under test:
   * {@link OptimizedTypeAdapterAdder#visitProgramClass(ProgramClass)}
   */
  @Test
  void testVisitProgramClass3() {
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
    OptimizedTypeAdapterAdder optimizedTypeAdapterAdder = new OptimizedTypeAdapterAdder(programClassPool,
        libraryClassPool, codeAttributeEditor, serializationInfo, deserializationInfo, extraDataEntryNameMap,
        new HashMap<>(), gsonRuntimeSettings);
    ProgramClass programClass = mock(ProgramClass.class);
    when(programClass.getName()).thenReturn("Name");

    // Act
    optimizedTypeAdapterAdder.visitProgramClass(programClass);

    // Assert
    verify(programClassPool).getClass(eq("/OptimizedNameTypeAdapter"));
    verify(programClass).getName();
  }

  /**
   * Method under test:
   * {@link OptimizedTypeAdapterAdder#visitProgramClass(ProgramClass)}
   */
  @Test
  void testVisitProgramClass4() {
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
    OptimizedTypeAdapterAdder optimizedTypeAdapterAdder = new OptimizedTypeAdapterAdder(programClassPool,
        libraryClassPool, codeAttributeEditor, serializationInfo, deserializationInfo, extraDataEntryNameMap,
        new HashMap<>(), gsonRuntimeSettings);
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
   * Method under test:
   * {@link OptimizedTypeAdapterAdder#visitProgramClass(ProgramClass)}
   */
  @Test
  void testVisitProgramClass5() {
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
    OptimizedTypeAdapterAdder optimizedTypeAdapterAdder = new OptimizedTypeAdapterAdder(programClassPool,
        libraryClassPool, codeAttributeEditor, serializationInfo, deserializationInfo, extraDataEntryNameMap,
        new HashMap<>(), gsonRuntimeSettings);
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
   * Method under test:
   * {@link OptimizedTypeAdapterAdder#visitProgramClass(ProgramClass)}
   */
  @Test
  void testVisitProgramClass6() {
    // Arrange
    ClassPool programClassPool = mock(ClassPool.class);
    when(programClassPool.getClass(Mockito.<String>any())).thenReturn(null);
    doNothing().when(programClassPool).addClass(Mockito.<Clazz>any());
    ProgramClass programClass = mock(ProgramClass.class);
    doNothing().when(programClass).accept(Mockito.<ClassVisitor>any());
    doNothing().when(programClass)
        .hierarchyAccept(anyBoolean(), anyBoolean(), anyBoolean(), anyBoolean(), Mockito.<ClassVisitor>any());
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
    OptimizedTypeAdapterAdder optimizedTypeAdapterAdder = new OptimizedTypeAdapterAdder(programClassPool,
        libraryClassPool, codeAttributeEditor, serializationInfo, deserializationInfo, extraDataEntryNameMap,
        new HashMap<>(), gsonRuntimeSettings);
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
    verify(programClass, atLeast(1)).hierarchyAccept(eq(true), eq(true), eq(true), eq(false),
        Mockito.<ClassVisitor>any());
  }

  /**
   * Method under test:
   * {@link OptimizedTypeAdapterAdder#visitProgramClass(ProgramClass)}
   */
  @Test
  void testVisitProgramClass7() {
    // Arrange
    ClassPool programClassPool = mock(ClassPool.class);
    when(programClassPool.getClass(Mockito.<String>any())).thenReturn(null);
    doNothing().when(programClassPool).addClass(Mockito.<Clazz>any());
    ProgramClass programClass = mock(ProgramClass.class);
    doNothing().when(programClass).accept(Mockito.<ClassVisitor>any());
    doNothing().when(programClass)
        .hierarchyAccept(anyBoolean(), anyBoolean(), anyBoolean(), anyBoolean(), Mockito.<ClassVisitor>any());
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
    OptimizedTypeAdapterAdder optimizedTypeAdapterAdder = new OptimizedTypeAdapterAdder(programClassPool,
        libraryClassPool, codeAttributeEditor, serializationInfo, deserializationInfo, extraDataEntryNameMap,
        new HashMap<>(), gsonRuntimeSettings);
    ProgramClass programClass2 = mock(ProgramClass.class);
    when(programClass2.getAccessFlags()).thenReturn(-1);
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
    verify(programClass, atLeast(1)).hierarchyAccept(eq(true), eq(true), eq(true), eq(false),
        Mockito.<ClassVisitor>any());
  }

  /**
   * Method under test:
   * {@link OptimizedTypeAdapterAdder#visitProgramClass(ProgramClass)}
   */
  @Test
  void testVisitProgramClass8() {
    // Arrange
    ClassPool programClassPool = mock(ClassPool.class);
    when(programClassPool.getClass(Mockito.<String>any())).thenReturn(null);
    doNothing().when(programClassPool).addClass(Mockito.<Clazz>any());
    ProgramClass programClass = mock(ProgramClass.class);
    doNothing().when(programClass).accept(Mockito.<ClassVisitor>any());
    doNothing().when(programClass)
        .hierarchyAccept(anyBoolean(), anyBoolean(), anyBoolean(), anyBoolean(), Mockito.<ClassVisitor>any());
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
    OptimizedTypeAdapterAdder optimizedTypeAdapterAdder = new OptimizedTypeAdapterAdder(programClassPool,
        libraryClassPool, codeAttributeEditor, serializationInfo, deserializationInfo, extraDataEntryNameMap,
        new HashMap<>(), gsonRuntimeSettings);
    ProgramClass programClass2 = mock(ProgramClass.class);
    when(programClass2.getAccessFlags()).thenReturn(1);
    when(programClass2.getName()).thenReturn(OptimizedClassConstants.NAME_OPTIMIZED_TYPE_ADAPTER_IMPL);

    // Act
    optimizedTypeAdapterAdder.visitProgramClass(programClass2);

    // Assert
    verify(programClassPool).addClass(isA(Clazz.class));
    verify(libraryClassPool, atLeast(1)).getClass(Mockito.<String>any());
    verify(programClassPool, atLeast(1)).getClass(Mockito.<String>any());
    verify(programClass, atLeast(1)).accept(isA(ClassVisitor.class));
    verify(programClass2).getAccessFlags();
    verify(programClass2, atLeast(1)).getName();
    verify(programClass, atLeast(1)).hierarchyAccept(eq(true), eq(true), eq(true), eq(false),
        Mockito.<ClassVisitor>any());
  }
}
