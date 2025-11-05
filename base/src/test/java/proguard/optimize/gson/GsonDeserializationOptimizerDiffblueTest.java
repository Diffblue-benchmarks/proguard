package proguard.optimize.gson;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import proguard.classfile.ClassPool;
import proguard.classfile.ProgramClass;
import proguard.classfile.visitor.MemberVisitor;
import proguard.io.ExtraDataEntryNameMap;

class GsonDeserializationOptimizerDiffblueTest {
  /**
   * Method under test:
   * {@link GsonDeserializationOptimizer#visitProgramClass(ProgramClass)}
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
    OptimizedJsonInfo deserializationInfo = new OptimizedJsonInfo();
    GsonDeserializationOptimizer gsonDeserializationOptimizer = new GsonDeserializationOptimizer(programClassPool,
        libraryClassPool, gsonRuntimeSettings, deserializationInfo, true, new ExtraDataEntryNameMap());
    ProgramClass programClass = mock(ProgramClass.class);
    doThrow(new RuntimeException("<init>")).when(programClass).methodsAccept(Mockito.<MemberVisitor>any());

    // Act and Assert
    assertThrows(RuntimeException.class, () -> gsonDeserializationOptimizer.visitProgramClass(programClass));
    verify(programClass).methodsAccept(isA(MemberVisitor.class));
  }
}
