package proguard.optimize.gson;

import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.anyBoolean;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.io.PrintWriter;
import java.io.StringWriter;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import proguard.classfile.ClassPool;
import proguard.classfile.Clazz;
import proguard.classfile.LibraryClass;
import proguard.classfile.ProgramClass;
import proguard.classfile.util.WarningPrinter;
import proguard.classfile.visitor.ClassVisitor;
import proguard.classfile.visitor.MemberVisitor;

class GsonDomainClassFinderDiffblueTest {
  /**
   * Test {@link GsonDomainClassFinder#visitProgramClass(ProgramClass)}.
   * <ul>
   *   <li>Given {@link ClassPool} {@link ClassPool#addClass(Clazz)} does nothing.</li>
   *   <li>Then calls {@link ClassPool#addClass(Clazz)}.</li>
   * </ul>
   * <p>
   * Method under test: {@link GsonDomainClassFinder#visitProgramClass(ProgramClass)}
   */
  @Test
  @DisplayName("Test visitProgramClass(ProgramClass); given ClassPool addClass(Clazz) does nothing; then calls addClass(Clazz)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "void proguard.optimize.gson.GsonDomainClassFinder.visitProgramClass(proguard.classfile.ProgramClass)"})
  void testVisitProgramClass_givenClassPoolAddClassDoesNothing_thenCallsAddClass() {
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
    ClassPool gsonDomainClassPool = mock(ClassPool.class);
    when(gsonDomainClassPool.getClass(Mockito.<String>any())).thenReturn(null);
    doNothing().when(gsonDomainClassPool).addClass(Mockito.<Clazz>any());
    GsonDomainClassFinder gsonDomainClassFinder = new GsonDomainClassFinder(gsonRuntimeSettings, gsonDomainClassPool,
        new WarningPrinter(new PrintWriter(new StringWriter())));
    ProgramClass programClass = mock(ProgramClass.class);
    when(programClass.getAccessFlags()).thenReturn(1);
    doNothing().when(programClass).accept(Mockito.<ClassVisitor>any());
    doNothing().when(programClass).fieldsAccept(Mockito.<MemberVisitor>any());
    doNothing().when(programClass)
        .hierarchyAccept(anyBoolean(), anyBoolean(), anyBoolean(), anyBoolean(), Mockito.<ClassVisitor>any());
    when(programClass.getName()).thenReturn("Name");

    // Act
    gsonDomainClassFinder.visitProgramClass(programClass);

    // Assert
    verify(gsonDomainClassPool).addClass(isA(Clazz.class));
    verify(gsonDomainClassPool).getClass(eq("Name"));
    verify(programClass).accept(isA(ClassVisitor.class));
    verify(programClass).fieldsAccept(isA(MemberVisitor.class));
    verify(programClass, atLeast(1)).getAccessFlags();
    verify(programClass, atLeast(1)).getName();
    verify(programClass, atLeast(1)).hierarchyAccept(anyBoolean(), eq(true), eq(false), anyBoolean(),
        Mockito.<ClassVisitor>any());
  }

  /**
   * Test {@link GsonDomainClassFinder#visitProgramClass(ProgramClass)}.
   * <ul>
   *   <li>Given {@link ClassPool} {@link ClassPool#getClass(String)} return {@link LibraryClass#LibraryClass()}.</li>
   *   <li>Then calls {@link ClassPool#getClass(String)}.</li>
   * </ul>
   * <p>
   * Method under test: {@link GsonDomainClassFinder#visitProgramClass(ProgramClass)}
   */
  @Test
  @DisplayName("Test visitProgramClass(ProgramClass); given ClassPool getClass(String) return LibraryClass(); then calls getClass(String)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "void proguard.optimize.gson.GsonDomainClassFinder.visitProgramClass(proguard.classfile.ProgramClass)"})
  void testVisitProgramClass_givenClassPoolGetClassReturnLibraryClass_thenCallsGetClass() {
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
    ClassPool gsonDomainClassPool = mock(ClassPool.class);
    when(gsonDomainClassPool.getClass(Mockito.<String>any())).thenReturn(new LibraryClass());
    GsonDomainClassFinder gsonDomainClassFinder = new GsonDomainClassFinder(gsonRuntimeSettings, gsonDomainClassPool,
        new WarningPrinter(new PrintWriter(new StringWriter())));
    ProgramClass programClass = mock(ProgramClass.class);
    when(programClass.getName()).thenReturn("Name");

    // Act
    gsonDomainClassFinder.visitProgramClass(programClass);

    // Assert
    verify(gsonDomainClassPool).getClass(eq("Name"));
    verify(programClass).getName();
  }

  /**
   * Test {@link GsonDomainClassFinder#visitProgramClass(ProgramClass)}.
   * <ul>
   *   <li>Given {@link GsonRuntimeSettings} (default constructor) {@link GsonRuntimeSettings#excludeFieldsWithModifiers} is {@code false}.</li>
   * </ul>
   * <p>
   * Method under test: {@link GsonDomainClassFinder#visitProgramClass(ProgramClass)}
   */
  @Test
  @DisplayName("Test visitProgramClass(ProgramClass); given GsonRuntimeSettings (default constructor) excludeFieldsWithModifiers is 'false'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "void proguard.optimize.gson.GsonDomainClassFinder.visitProgramClass(proguard.classfile.ProgramClass)"})
  void testVisitProgramClass_givenGsonRuntimeSettingsExcludeFieldsWithModifiersIsFalse() {
    // Arrange
    GsonRuntimeSettings gsonRuntimeSettings = new GsonRuntimeSettings();
    gsonRuntimeSettings.addDeserializationExclusionStrategy = true;
    gsonRuntimeSettings.addSerializationExclusionStrategy = true;
    gsonRuntimeSettings.disableInnerClassSerialization = true;
    gsonRuntimeSettings.excludeFieldsWithModifiers = false;
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
    ClassPool gsonDomainClassPool = new ClassPool();
    GsonDomainClassFinder gsonDomainClassFinder = new GsonDomainClassFinder(gsonRuntimeSettings, gsonDomainClassPool,
        new WarningPrinter(new PrintWriter(new StringWriter())));
    ProgramClass programClass = mock(ProgramClass.class);
    when(programClass.getAccessFlags()).thenReturn(1);
    doNothing().when(programClass).accept(Mockito.<ClassVisitor>any());
    doNothing().when(programClass).fieldsAccept(Mockito.<MemberVisitor>any());
    doNothing().when(programClass)
        .hierarchyAccept(anyBoolean(), anyBoolean(), anyBoolean(), anyBoolean(), Mockito.<ClassVisitor>any());
    when(programClass.getName()).thenReturn("Name");

    // Act
    gsonDomainClassFinder.visitProgramClass(programClass);

    // Assert
    verify(programClass).accept(isA(ClassVisitor.class));
    verify(programClass).fieldsAccept(isA(MemberVisitor.class));
    verify(programClass, atLeast(1)).getAccessFlags();
    verify(programClass, atLeast(1)).getName();
    verify(programClass, atLeast(1)).hierarchyAccept(anyBoolean(), eq(true), eq(false), anyBoolean(),
        Mockito.<ClassVisitor>any());
  }

  /**
   * Test {@link GsonDomainClassFinder#visitProgramClass(ProgramClass)}.
   * <ul>
   *   <li>Given minus one.</li>
   *   <li>When {@link ProgramClass} {@link ProgramClass#getAccessFlags()} return minus one.</li>
   * </ul>
   * <p>
   * Method under test: {@link GsonDomainClassFinder#visitProgramClass(ProgramClass)}
   */
  @Test
  @DisplayName("Test visitProgramClass(ProgramClass); given minus one; when ProgramClass getAccessFlags() return minus one")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "void proguard.optimize.gson.GsonDomainClassFinder.visitProgramClass(proguard.classfile.ProgramClass)"})
  void testVisitProgramClass_givenMinusOne_whenProgramClassGetAccessFlagsReturnMinusOne() {
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
    ClassPool gsonDomainClassPool = mock(ClassPool.class);
    when(gsonDomainClassPool.getClass(Mockito.<String>any())).thenReturn(null);
    GsonDomainClassFinder gsonDomainClassFinder = new GsonDomainClassFinder(gsonRuntimeSettings, gsonDomainClassPool,
        new WarningPrinter(new PrintWriter(new StringWriter())));
    ProgramClass programClass = mock(ProgramClass.class);
    when(programClass.getAccessFlags()).thenReturn(-1);
    doNothing().when(programClass).accept(Mockito.<ClassVisitor>any());
    doNothing().when(programClass)
        .hierarchyAccept(anyBoolean(), anyBoolean(), anyBoolean(), anyBoolean(), Mockito.<ClassVisitor>any());
    when(programClass.getName()).thenReturn("Name");

    // Act
    gsonDomainClassFinder.visitProgramClass(programClass);

    // Assert
    verify(gsonDomainClassPool).getClass(eq("Name"));
    verify(programClass).accept(isA(ClassVisitor.class));
    verify(programClass, atLeast(1)).getAccessFlags();
    verify(programClass, atLeast(1)).getName();
    verify(programClass, atLeast(1)).hierarchyAccept(eq(true), eq(true), eq(false), eq(false),
        Mockito.<ClassVisitor>any());
  }

  /**
   * Test {@link GsonDomainClassFinder#visitProgramClass(ProgramClass)}.
   * <ul>
   *   <li>Then calls {@link ProgramClass#fieldsAccept(MemberVisitor)}.</li>
   * </ul>
   * <p>
   * Method under test: {@link GsonDomainClassFinder#visitProgramClass(ProgramClass)}
   */
  @Test
  @DisplayName("Test visitProgramClass(ProgramClass); then calls fieldsAccept(MemberVisitor)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "void proguard.optimize.gson.GsonDomainClassFinder.visitProgramClass(proguard.classfile.ProgramClass)"})
  void testVisitProgramClass_thenCallsFieldsAccept() {
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
    ClassPool gsonDomainClassPool = new ClassPool();
    GsonDomainClassFinder gsonDomainClassFinder = new GsonDomainClassFinder(gsonRuntimeSettings, gsonDomainClassPool,
        new WarningPrinter(new PrintWriter(new StringWriter())));
    ProgramClass programClass = mock(ProgramClass.class);
    when(programClass.getAccessFlags()).thenReturn(1);
    doNothing().when(programClass).accept(Mockito.<ClassVisitor>any());
    doNothing().when(programClass).fieldsAccept(Mockito.<MemberVisitor>any());
    doNothing().when(programClass)
        .hierarchyAccept(anyBoolean(), anyBoolean(), anyBoolean(), anyBoolean(), Mockito.<ClassVisitor>any());
    when(programClass.getName()).thenReturn("Name");

    // Act
    gsonDomainClassFinder.visitProgramClass(programClass);

    // Assert
    verify(programClass).accept(isA(ClassVisitor.class));
    verify(programClass).fieldsAccept(isA(MemberVisitor.class));
    verify(programClass, atLeast(1)).getAccessFlags();
    verify(programClass, atLeast(1)).getName();
    verify(programClass, atLeast(1)).hierarchyAccept(anyBoolean(), eq(true), eq(false), anyBoolean(),
        Mockito.<ClassVisitor>any());
  }
}
