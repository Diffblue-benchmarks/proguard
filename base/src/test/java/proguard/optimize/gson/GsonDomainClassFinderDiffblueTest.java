package proguard.optimize.gson;

import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.anyBoolean;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import com.diffblue.cover.annotations.ManagedByDiffblue;
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
import proguard.classfile.kotlin.KotlinConstants;
import proguard.classfile.util.WarningPrinter;
import proguard.classfile.visitor.ClassVisitor;
import proguard.classfile.visitor.MemberVisitor;

class GsonDomainClassFinderDiffblueTest {
  /**
   * Test {@link GsonDomainClassFinder#visitProgramClass(ProgramClass)}.
   *
   * <p>Method under test: {@link GsonDomainClassFinder#visitProgramClass(ProgramClass)}
   */
  @Test
  @DisplayName("Test visitProgramClass(ProgramClass)")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void GsonDomainClassFinder.visitProgramClass(ProgramClass)"})
  void testVisitProgramClass() {
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
    ClassPool gsonDomainClassPool = new ClassPool();
    WarningPrinter warningPrinter = new WarningPrinter(new PrintWriter(new StringWriter()));

    GsonDomainClassFinder gsonDomainClassFinder =
        new GsonDomainClassFinder(gsonRuntimeSettings, gsonDomainClassPool, warningPrinter);

    ProgramClass programClass = mock(ProgramClass.class);
    when(programClass.getAccessFlags()).thenReturn(1);
    doNothing().when(programClass).accept(Mockito.<ClassVisitor>any());
    doNothing().when(programClass).fieldsAccept(Mockito.<MemberVisitor>any());
    doNothing()
        .when(programClass)
        .hierarchyAccept(
            anyBoolean(), anyBoolean(), anyBoolean(), anyBoolean(), Mockito.<ClassVisitor>any());
    when(programClass.getName()).thenReturn("Name");

    // Act
    gsonDomainClassFinder.visitProgramClass(programClass);

    // Assert
    verify(programClass).accept(isA(ClassVisitor.class));
    verify(programClass).fieldsAccept(isA(MemberVisitor.class));
    verify(programClass, atLeast(1)).getAccessFlags();
    verify(programClass, atLeast(1)).getName();
    verify(programClass, atLeast(1))
        .hierarchyAccept(
            anyBoolean(), eq(true), eq(false), anyBoolean(), Mockito.<ClassVisitor>any());
  }

  /**
   * Test {@link GsonDomainClassFinder#visitProgramClass(ProgramClass)}.
   *
   * <ul>
   *   <li>Given {@link ClassPool} {@link ClassPool#addClass(Clazz)} does nothing.
   *   <li>Then calls {@link ClassPool#addClass(Clazz)}.
   * </ul>
   *
   * <p>Method under test: {@link GsonDomainClassFinder#visitProgramClass(ProgramClass)}
   */
  @Test
  @DisplayName(
      "Test visitProgramClass(ProgramClass); given ClassPool addClass(Clazz) does nothing; then calls addClass(Clazz)")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void GsonDomainClassFinder.visitProgramClass(ProgramClass)"})
  void testVisitProgramClass_givenClassPoolAddClassDoesNothing_thenCallsAddClass() {
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

    ClassPool gsonDomainClassPool = mock(ClassPool.class);
    when(gsonDomainClassPool.getClass(Mockito.<String>any())).thenReturn(null);
    doNothing().when(gsonDomainClassPool).addClass(Mockito.<Clazz>any());
    WarningPrinter warningPrinter = new WarningPrinter(new PrintWriter(new StringWriter()));

    GsonDomainClassFinder gsonDomainClassFinder =
        new GsonDomainClassFinder(gsonRuntimeSettings, gsonDomainClassPool, warningPrinter);

    ProgramClass programClass = mock(ProgramClass.class);
    when(programClass.getAccessFlags()).thenReturn(1);
    doNothing().when(programClass).accept(Mockito.<ClassVisitor>any());
    doNothing().when(programClass).fieldsAccept(Mockito.<MemberVisitor>any());
    doNothing()
        .when(programClass)
        .hierarchyAccept(
            anyBoolean(), anyBoolean(), anyBoolean(), anyBoolean(), Mockito.<ClassVisitor>any());
    when(programClass.getName()).thenReturn("Name");

    // Act
    gsonDomainClassFinder.visitProgramClass(programClass);

    // Assert
    verify(gsonDomainClassPool).addClass(isA(Clazz.class));
    verify(gsonDomainClassPool).getClass("Name");
    verify(programClass).accept(isA(ClassVisitor.class));
    verify(programClass).fieldsAccept(isA(MemberVisitor.class));
    verify(programClass, atLeast(1)).getAccessFlags();
    verify(programClass, atLeast(1)).getName();
    verify(programClass, atLeast(1))
        .hierarchyAccept(
            anyBoolean(), eq(true), eq(false), anyBoolean(), Mockito.<ClassVisitor>any());
  }

  /**
   * Test {@link GsonDomainClassFinder#visitProgramClass(ProgramClass)}.
   *
   * <ul>
   *   <li>Given {@link ClassPool} {@link ClassPool#getClass(String)} return {@link
   *       LibraryClass#LibraryClass()}.
   *   <li>Then calls {@link ClassPool#getClass(String)}.
   * </ul>
   *
   * <p>Method under test: {@link GsonDomainClassFinder#visitProgramClass(ProgramClass)}
   */
  @Test
  @DisplayName(
      "Test visitProgramClass(ProgramClass); given ClassPool getClass(String) return LibraryClass(); then calls getClass(String)")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void GsonDomainClassFinder.visitProgramClass(ProgramClass)"})
  void testVisitProgramClass_givenClassPoolGetClassReturnLibraryClass_thenCallsGetClass() {
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

    ClassPool gsonDomainClassPool = mock(ClassPool.class);
    when(gsonDomainClassPool.getClass(Mockito.<String>any())).thenReturn(new LibraryClass());
    WarningPrinter warningPrinter = new WarningPrinter(new PrintWriter(new StringWriter()));

    GsonDomainClassFinder gsonDomainClassFinder =
        new GsonDomainClassFinder(gsonRuntimeSettings, gsonDomainClassPool, warningPrinter);

    ProgramClass programClass = mock(ProgramClass.class);
    when(programClass.getName()).thenReturn("Name");

    // Act
    gsonDomainClassFinder.visitProgramClass(programClass);

    // Assert
    verify(gsonDomainClassPool).getClass("Name");
    verify(programClass).getName();
  }

  /**
   * Test {@link GsonDomainClassFinder#visitProgramClass(ProgramClass)}.
   *
   * <ul>
   *   <li>Given {@link GsonRuntimeSettings} (default constructor) {@link
   *       GsonRuntimeSettings#excludeFieldsWithModifiers} is {@code false}.
   * </ul>
   *
   * <p>Method under test: {@link GsonDomainClassFinder#visitProgramClass(ProgramClass)}
   */
  @Test
  @DisplayName(
      "Test visitProgramClass(ProgramClass); given GsonRuntimeSettings (default constructor) excludeFieldsWithModifiers is 'false'")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void GsonDomainClassFinder.visitProgramClass(ProgramClass)"})
  void testVisitProgramClass_givenGsonRuntimeSettingsExcludeFieldsWithModifiersIsFalse() {
    // Arrange
    GsonRuntimeSettings gsonRuntimeSettings = new GsonRuntimeSettings();
    gsonRuntimeSettings.addDeserializationExclusionStrategy = true;
    gsonRuntimeSettings.addSerializationExclusionStrategy = true;
    gsonRuntimeSettings.disableInnerClassSerialization = true;
    gsonRuntimeSettings.excludeFieldsWithModifiers = false;
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

    ClassPool gsonDomainClassPool = mock(ClassPool.class);
    when(gsonDomainClassPool.getClass(Mockito.<String>any())).thenReturn(null);
    doNothing().when(gsonDomainClassPool).addClass(Mockito.<Clazz>any());
    WarningPrinter warningPrinter = new WarningPrinter(new PrintWriter(new StringWriter()));

    GsonDomainClassFinder gsonDomainClassFinder =
        new GsonDomainClassFinder(gsonRuntimeSettings, gsonDomainClassPool, warningPrinter);

    ProgramClass programClass = mock(ProgramClass.class);
    when(programClass.getAccessFlags()).thenReturn(1);
    doNothing().when(programClass).accept(Mockito.<ClassVisitor>any());
    doNothing().when(programClass).fieldsAccept(Mockito.<MemberVisitor>any());
    doNothing()
        .when(programClass)
        .hierarchyAccept(
            anyBoolean(), anyBoolean(), anyBoolean(), anyBoolean(), Mockito.<ClassVisitor>any());
    when(programClass.getName()).thenReturn("Name");

    // Act
    gsonDomainClassFinder.visitProgramClass(programClass);

    // Assert
    verify(gsonDomainClassPool).addClass(isA(Clazz.class));
    verify(gsonDomainClassPool).getClass("Name");
    verify(programClass).accept(isA(ClassVisitor.class));
    verify(programClass).fieldsAccept(isA(MemberVisitor.class));
    verify(programClass, atLeast(1)).getAccessFlags();
    verify(programClass, atLeast(1)).getName();
    verify(programClass, atLeast(1))
        .hierarchyAccept(
            anyBoolean(), eq(true), eq(false), anyBoolean(), Mockito.<ClassVisitor>any());
  }

  /**
   * Test {@link GsonDomainClassFinder#visitProgramClass(ProgramClass)}.
   *
   * <ul>
   *   <li>Given minus one.
   *   <li>When {@link ProgramClass} {@link ProgramClass#getAccessFlags()} return minus one.
   * </ul>
   *
   * <p>Method under test: {@link GsonDomainClassFinder#visitProgramClass(ProgramClass)}
   */
  @Test
  @DisplayName(
      "Test visitProgramClass(ProgramClass); given minus one; when ProgramClass getAccessFlags() return minus one")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void GsonDomainClassFinder.visitProgramClass(ProgramClass)"})
  void testVisitProgramClass_givenMinusOne_whenProgramClassGetAccessFlagsReturnMinusOne() {
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

    ClassPool gsonDomainClassPool = mock(ClassPool.class);
    when(gsonDomainClassPool.getClass(Mockito.<String>any())).thenReturn(null);
    WarningPrinter warningPrinter = new WarningPrinter(new PrintWriter(new StringWriter()));

    GsonDomainClassFinder gsonDomainClassFinder =
        new GsonDomainClassFinder(gsonRuntimeSettings, gsonDomainClassPool, warningPrinter);

    ProgramClass programClass = mock(ProgramClass.class);
    when(programClass.getAccessFlags()).thenReturn(-1);
    doNothing().when(programClass).accept(Mockito.<ClassVisitor>any());
    doNothing()
        .when(programClass)
        .hierarchyAccept(
            anyBoolean(), anyBoolean(), anyBoolean(), anyBoolean(), Mockito.<ClassVisitor>any());
    when(programClass.getName()).thenReturn("Name");

    // Act
    gsonDomainClassFinder.visitProgramClass(programClass);

    // Assert
    verify(gsonDomainClassPool).getClass("Name");
    verify(programClass).accept(isA(ClassVisitor.class));
    verify(programClass, atLeast(1)).getAccessFlags();
    verify(programClass, atLeast(1)).getName();
    verify(programClass, atLeast(1))
        .hierarchyAccept(eq(true), eq(true), eq(false), eq(false), Mockito.<ClassVisitor>any());
  }
}
