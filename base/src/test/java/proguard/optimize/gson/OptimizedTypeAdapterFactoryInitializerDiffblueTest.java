package proguard.optimize.gson;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.util.HashMap;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import proguard.classfile.Clazz;
import proguard.classfile.LibraryClass;
import proguard.classfile.LibraryMethod;
import proguard.classfile.Method;
import proguard.classfile.attribute.CodeAttribute;
import proguard.classfile.editor.CodeAttributeEditor;
import proguard.classfile.instruction.BranchInstruction;
import proguard.classfile.instruction.Instruction;
import proguard.classfile.kotlin.KotlinConstants;

class OptimizedTypeAdapterFactoryInitializerDiffblueTest {
  /**
   * Test {@link OptimizedTypeAdapterFactoryInitializer#visitAnyInstruction(Clazz, Method,
   * CodeAttribute, int, Instruction)}.
   *
   * <ul>
   *   <li>Given {@code A}.
   *   <li>Then calls {@link BranchInstruction#actualOpcode()}.
   * </ul>
   *
   * <p>Method under test: {@link OptimizedTypeAdapterFactoryInitializer#visitAnyInstruction(Clazz,
   * Method, CodeAttribute, int, Instruction)}
   */
  @Test
  @DisplayName(
      "Test visitAnyInstruction(Clazz, Method, CodeAttribute, int, Instruction); given 'A'; then calls actualOpcode()")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void OptimizedTypeAdapterFactoryInitializer.visitAnyInstruction(Clazz, Method, CodeAttribute, int, Instruction)"
  })
  void testVisitAnyInstruction_givenA_thenCallsActualOpcode() {
    // Arrange
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
    CodeAttributeEditor codeAttributeEditor = new CodeAttributeEditor();

    OptimizedTypeAdapterFactoryInitializer optimizedTypeAdapterFactoryInitializer =
        new OptimizedTypeAdapterFactoryInitializer(
            KotlinConstants.dummyClassPool,
            codeAttributeEditor,
            new HashMap<>(),
            gsonRuntimeSettings);
    LibraryClass clazz = new LibraryClass();
    LibraryMethod method =
        new LibraryMethod(
            1,
            OptimizedClassConstants.METHOD_NAME_CREATE,
            OptimizedClassConstants.METHOD_TYPE_CREATE);
    CodeAttribute codeAttribute = new CodeAttribute(1);

    BranchInstruction instruction = mock(BranchInstruction.class);
    when(instruction.actualOpcode()).thenReturn((byte) 'A');

    // Act
    optimizedTypeAdapterFactoryInitializer.visitAnyInstruction(
        clazz, method, codeAttribute, 2, instruction);

    // Assert
    verify(instruction).actualOpcode();
  }
}
