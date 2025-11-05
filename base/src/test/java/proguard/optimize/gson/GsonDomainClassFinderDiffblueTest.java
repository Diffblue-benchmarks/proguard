package proguard.optimize.gson;

import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.anyBoolean;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import java.io.PrintWriter;
import java.io.StringWriter;
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
   * Method under test:
   * {@link GsonDomainClassFinder#visitProgramClass(ProgramClass)}
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
   * Method under test:
   * {@link GsonDomainClassFinder#visitProgramClass(ProgramClass)}
   */
  @Test
  void testVisitProgramClass2() {
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
   * Method under test:
   * {@link GsonDomainClassFinder#visitProgramClass(ProgramClass)}
   */
  @Test
  void testVisitProgramClass3() {
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
   * Method under test:
   * {@link GsonDomainClassFinder#visitProgramClass(ProgramClass)}
   */
  @Test
  void testVisitProgramClass4() {
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
   * Method under test:
   * {@link GsonDomainClassFinder#visitProgramClass(ProgramClass)}
   */
  @Test
  void testVisitProgramClass5() {
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
}
