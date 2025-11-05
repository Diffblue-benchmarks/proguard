package proguard.optimize.peephole;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.anyBoolean;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import proguard.classfile.Clazz;
import proguard.classfile.LibraryClass;
import proguard.classfile.ProgramClass;
import proguard.classfile.attribute.visitor.AttributeVisitor;
import proguard.classfile.constant.Constant;
import proguard.classfile.constant.visitor.ConstantVisitor;
import proguard.classfile.visitor.ClassVisitor;
import proguard.classfile.visitor.MemberVisitor;
import proguard.optimize.info.ClassOptimizationInfo;
import proguard.optimize.info.ProgramClassOptimizationInfo;

class ClassMergerDiffblueTest {
  /**
   * Method under test: {@link ClassMerger#visitProgramClass(ProgramClass)}
   */
  @Test
  void testVisitProgramClass() {
    // Arrange
    ProgramClass targetClass = mock(ProgramClass.class);
    when(targetClass.getProcessingInfo()).thenReturn("Processing Info");
    ClassMerger classMerger = new ClassMerger(targetClass, true, true, true);
    ProgramClass programClass = mock(ProgramClass.class);
    when(programClass.getProcessingInfo()).thenReturn(new ClassOptimizationInfo());

    // Act
    classMerger.visitProgramClass(programClass);

    // Assert
    verify(programClass).getProcessingInfo();
  }

  /**
   * Method under test: {@link ClassMerger#visitProgramClass(ProgramClass)}
   */
  @Test
  void testVisitProgramClass2() {
    // Arrange
    ProgramClass targetClass = mock(ProgramClass.class);
    when(targetClass.getProcessingInfo()).thenReturn("Processing Info");
    ClassMerger classMerger = new ClassMerger(targetClass, true, true, true);
    ClassOptimizationInfo classOptimizationInfo = mock(ClassOptimizationInfo.class);
    when(classOptimizationInfo.isKept()).thenReturn(true);
    ProgramClass programClass = mock(ProgramClass.class);
    when(programClass.getProcessingInfo()).thenReturn(classOptimizationInfo);

    // Act
    classMerger.visitProgramClass(programClass);

    // Assert
    verify(classOptimizationInfo).isKept();
    verify(programClass).getProcessingInfo();
  }

  /**
   * Method under test: {@link ClassMerger#visitProgramClass(ProgramClass)}
   */
  @Test
  void testVisitProgramClass3() {
    // Arrange
    ProgramClass targetClass = mock(ProgramClass.class);
    when(targetClass.getProcessingInfo()).thenReturn(new ClassOptimizationInfo());
    ClassMerger classMerger = new ClassMerger(targetClass, true, true, true);
    ClassOptimizationInfo classOptimizationInfo = mock(ClassOptimizationInfo.class);
    when(classOptimizationInfo.isKept()).thenReturn(false);
    ProgramClass programClass = mock(ProgramClass.class);
    when(programClass.getProcessingInfo()).thenReturn(classOptimizationInfo);

    // Act
    classMerger.visitProgramClass(programClass);

    // Assert
    verify(classOptimizationInfo).isKept();
    verify(targetClass).getProcessingInfo();
    verify(programClass).getProcessingInfo();
  }

  /**
   * Method under test: {@link ClassMerger#visitProgramClass(ProgramClass)}
   */
  @Test
  void testVisitProgramClass4() {
    // Arrange
    ProgramClass targetClass = mock(ProgramClass.class);
    when(targetClass.extendsOrImplements(Mockito.<Clazz>any())).thenReturn(true);
    when(targetClass.getAccessFlags()).thenReturn(1);
    doNothing().when(targetClass).attributesAccept(Mockito.<AttributeVisitor>any());
    when(targetClass.getProcessingInfo()).thenReturn(new ProgramClassOptimizationInfo());
    ClassMerger classMerger = new ClassMerger(targetClass, true, true, true);
    ClassOptimizationInfo classOptimizationInfo = mock(ClassOptimizationInfo.class);
    when(classOptimizationInfo.getTargetClass()).thenReturn(null);
    when(classOptimizationInfo.isKept()).thenReturn(false);
    ProgramClass programClass = mock(ProgramClass.class);
    when(programClass.getAccessFlags()).thenReturn(1);
    doNothing().when(programClass).attributesAccept(Mockito.<AttributeVisitor>any());
    doNothing().when(programClass).interfaceConstantsAccept(Mockito.<ConstantVisitor>any());
    doNothing().when(programClass).superClassConstantAccept(Mockito.<ConstantVisitor>any());
    when(programClass.getProcessingInfo()).thenReturn(classOptimizationInfo);

    // Act
    classMerger.visitProgramClass(programClass);

    // Assert
    verify(programClass).attributesAccept(isA(AttributeVisitor.class));
    verify(targetClass).extendsOrImplements(isA(Clazz.class));
    verify(targetClass).getAccessFlags();
    verify(programClass, atLeast(1)).getAccessFlags();
    verify(programClass).interfaceConstantsAccept(isA(ConstantVisitor.class));
    verify(programClass).superClassConstantAccept(isA(ConstantVisitor.class));
    verify(classOptimizationInfo).getTargetClass();
    verify(classOptimizationInfo).isKept();
    verify(targetClass, atLeast(1)).getProcessingInfo();
    verify(programClass, atLeast(1)).getProcessingInfo();
  }

  /**
   * Method under test: {@link ClassMerger#visitProgramClass(ProgramClass)}
   */
  @Test
  void testVisitProgramClass5() {
    // Arrange
    ProgramClass targetClass = mock(ProgramClass.class);
    doThrow(new RuntimeException("NestHost")).when(targetClass).methodsAccept(Mockito.<MemberVisitor>any());
    doNothing().when(targetClass).interfaceConstantsAccept(Mockito.<ConstantVisitor>any());
    when(targetClass.extendsOrImplements(Mockito.<Clazz>any())).thenReturn(false);
    when(targetClass.extends_(Mockito.<String>any())).thenReturn(true);
    when(targetClass.getAccessFlags()).thenReturn(1);
    doNothing().when(targetClass).attributesAccept(Mockito.<AttributeVisitor>any());
    doNothing().when(targetClass)
        .hierarchyAccept(anyBoolean(), anyBoolean(), anyBoolean(), anyBoolean(), Mockito.<ClassVisitor>any());
    when(targetClass.getName()).thenReturn("Name");
    when(targetClass.getProcessingInfo()).thenReturn(new ProgramClassOptimizationInfo());
    ClassMerger classMerger = new ClassMerger(targetClass, true, true, true);
    ClassOptimizationInfo classOptimizationInfo = mock(ClassOptimizationInfo.class);
    when(classOptimizationInfo.isDotClassed()).thenReturn(true);
    when(classOptimizationInfo.isInstantiated()).thenReturn(true);
    when(classOptimizationInfo.getTargetClass()).thenReturn(null);
    when(classOptimizationInfo.isKept()).thenReturn(false);
    ProgramClass programClass = mock(ProgramClass.class);
    when(programClass.extends_(Mockito.<String>any())).thenReturn(true);
    doNothing().when(programClass)
        .hierarchyAccept(anyBoolean(), anyBoolean(), anyBoolean(), anyBoolean(), Mockito.<ClassVisitor>any());
    doNothing().when(programClass).methodsAccept(Mockito.<MemberVisitor>any());
    when(programClass.getAccessFlags()).thenReturn(1);
    doNothing().when(programClass).attributesAccept(Mockito.<AttributeVisitor>any());
    doNothing().when(programClass).interfaceConstantsAccept(Mockito.<ConstantVisitor>any());
    doNothing().when(programClass).superClassConstantAccept(Mockito.<ConstantVisitor>any());
    when(programClass.getProcessingInfo()).thenReturn(classOptimizationInfo);
    when(programClass.getName()).thenReturn("Name");

    // Act and Assert
    assertThrows(RuntimeException.class, () -> classMerger.visitProgramClass(programClass));
    verify(programClass).attributesAccept(isA(AttributeVisitor.class));
    verify(targetClass).extendsOrImplements(isA(Clazz.class));
    verify(targetClass).extends_(eq("java/lang/Throwable"));
    verify(programClass).extends_(eq("java/lang/Throwable"));
    verify(targetClass).getAccessFlags();
    verify(programClass, atLeast(1)).getAccessFlags();
    verify(targetClass).getName();
    verify(programClass).getName();
    verify(targetClass, atLeast(1)).hierarchyAccept(eq(true), eq(true), anyBoolean(), eq(false),
        Mockito.<ClassVisitor>any());
    verify(programClass, atLeast(1)).hierarchyAccept(eq(true), eq(true), anyBoolean(), eq(false),
        Mockito.<ClassVisitor>any());
    verify(programClass).interfaceConstantsAccept(isA(ConstantVisitor.class));
    verify(targetClass).methodsAccept(isA(MemberVisitor.class));
    verify(programClass, atLeast(1)).methodsAccept(Mockito.<MemberVisitor>any());
    verify(programClass).superClassConstantAccept(isA(ConstantVisitor.class));
    verify(classOptimizationInfo).getTargetClass();
    verify(classOptimizationInfo).isDotClassed();
    verify(classOptimizationInfo).isInstantiated();
    verify(classOptimizationInfo).isKept();
    verify(targetClass, atLeast(1)).getProcessingInfo();
    verify(programClass, atLeast(1)).getProcessingInfo();
  }

  /**
   * Method under test: {@link ClassMerger#isMergeable(ProgramClass)}
   */
  @Test
  void testIsMergeable() {
    // Arrange
    Constant constant = mock(Constant.class);
    doNothing().when(constant).addProcessingFlags((int[]) any());
    constant.addProcessingFlags(2, 1, 2, 1);
    ClassMerger classMerger = new ClassMerger(
        new ProgramClass(1, 3, new Constant[]{constant}, 1, 1, 1, "Feature Name", 1, new ClassOptimizationInfo()), true,
        true, true);

    // Act
    boolean actualIsMergeableResult = classMerger.isMergeable(new ProgramClass());

    // Assert
    verify(constant).addProcessingFlags((int[]) any());
    assertFalse(actualIsMergeableResult);
  }

  /**
   * Method under test: {@link ClassMerger#isMergeable(ProgramClass)}
   */
  @Test
  void testIsMergeable2() {
    // Arrange
    Constant constant = mock(Constant.class);
    doNothing().when(constant).addProcessingFlags((int[]) any());
    constant.addProcessingFlags(2, 1, 2, 1);
    ClassOptimizationInfo classOptimizationInfo = mock(ClassOptimizationInfo.class);
    when(classOptimizationInfo.isKept()).thenReturn(true);
    ClassMerger classMerger = new ClassMerger(
        new ProgramClass(1, 3, new Constant[]{constant}, 1, 1, 1, "Feature Name", 1, classOptimizationInfo), true, true,
        true);

    // Act
    boolean actualIsMergeableResult = classMerger.isMergeable(new ProgramClass());

    // Assert
    verify(classOptimizationInfo).isKept();
    verify(constant).addProcessingFlags((int[]) any());
    assertFalse(actualIsMergeableResult);
  }

  /**
   * Method under test: {@link ClassMerger#isMergeable(ProgramClass)}
   */
  @Test
  void testIsMergeable3() {
    // Arrange
    ProgramClass targetClass = mock(ProgramClass.class);
    when(targetClass.getProcessingInfo()).thenReturn(mock(ClassOptimizationInfo.class));
    ClassMerger classMerger = new ClassMerger(targetClass, true, true, true);
    ProgramClass programClass = mock(ProgramClass.class);
    when(programClass.getProcessingInfo()).thenReturn(new ClassOptimizationInfo());

    // Act
    boolean actualIsMergeableResult = classMerger.isMergeable(programClass);

    // Assert
    verify(programClass).getProcessingInfo();
    assertFalse(actualIsMergeableResult);
  }

  /**
   * Method under test: {@link ClassMerger#isMergeable(ProgramClass)}
   */
  @Test
  void testIsMergeable4() {
    // Arrange
    new RuntimeException("foo");
    ProgramClass targetClass = mock(ProgramClass.class);
    when(targetClass.extendsOrImplements(Mockito.<Clazz>any())).thenReturn(true);
    when(targetClass.getAccessFlags()).thenReturn(1);
    doNothing().when(targetClass).attributesAccept(Mockito.<AttributeVisitor>any());
    when(targetClass.getProcessingInfo()).thenReturn(new ProgramClassOptimizationInfo());
    doNothing().when(targetClass).addSubClass(Mockito.<Clazz>any());
    targetClass.addSubClass(new LibraryClass());
    ClassMerger classMerger = new ClassMerger(targetClass, true, true, true, mock(ClassVisitor.class));
    ProgramClass programClass = mock(ProgramClass.class);
    when(programClass.getAccessFlags()).thenReturn(1);
    doNothing().when(programClass).attributesAccept(Mockito.<AttributeVisitor>any());
    doNothing().when(programClass).interfaceConstantsAccept(Mockito.<ConstantVisitor>any());
    doNothing().when(programClass).superClassConstantAccept(Mockito.<ConstantVisitor>any());
    when(programClass.getProcessingInfo()).thenReturn(new ProgramClassOptimizationInfo());

    // Act
    boolean actualIsMergeableResult = classMerger.isMergeable(programClass);

    // Assert
    verify(targetClass).addSubClass(isA(Clazz.class));
    verify(programClass).attributesAccept(isA(AttributeVisitor.class));
    verify(targetClass).extendsOrImplements(isA(Clazz.class));
    verify(targetClass).getAccessFlags();
    verify(programClass, atLeast(1)).getAccessFlags();
    verify(programClass).interfaceConstantsAccept(isA(ConstantVisitor.class));
    verify(programClass).superClassConstantAccept(isA(ConstantVisitor.class));
    verify(targetClass, atLeast(1)).getProcessingInfo();
    verify(programClass, atLeast(1)).getProcessingInfo();
    assertFalse(actualIsMergeableResult);
  }

  /**
   * Method under test: {@link ClassMerger#isMergeable(ProgramClass)}
   */
  @Test
  void testIsMergeable5() {
    // Arrange
    new RuntimeException("foo");
    ProgramClass targetClass = mock(ProgramClass.class);
    when(targetClass.extendsOrImplements(Mockito.<Clazz>any())).thenReturn(false);
    when(targetClass.extends_(Mockito.<String>any())).thenReturn(true);
    when(targetClass.extends_(Mockito.<Clazz>any())).thenReturn(true);
    when(targetClass.getAccessFlags()).thenReturn(1);
    doNothing().when(targetClass).attributesAccept(Mockito.<AttributeVisitor>any());
    doNothing().when(targetClass)
        .hierarchyAccept(anyBoolean(), anyBoolean(), anyBoolean(), anyBoolean(), Mockito.<ClassVisitor>any());
    when(targetClass.getProcessingInfo()).thenReturn(new ProgramClassOptimizationInfo());
    doNothing().when(targetClass).addSubClass(Mockito.<Clazz>any());
    targetClass.addSubClass(new LibraryClass());
    ClassMerger classMerger = new ClassMerger(targetClass, true, true, true, mock(ClassVisitor.class));
    ProgramClass programClass = mock(ProgramClass.class);
    when(programClass.extends_(Mockito.<String>any())).thenReturn(true);
    when(programClass.extends_(Mockito.<Clazz>any())).thenReturn(true);
    doNothing().when(programClass)
        .hierarchyAccept(anyBoolean(), anyBoolean(), anyBoolean(), anyBoolean(), Mockito.<ClassVisitor>any());
    doNothing().when(programClass).methodsAccept(Mockito.<MemberVisitor>any());
    when(programClass.getAccessFlags()).thenReturn(1);
    doNothing().when(programClass).attributesAccept(Mockito.<AttributeVisitor>any());
    doNothing().when(programClass).interfaceConstantsAccept(Mockito.<ConstantVisitor>any());
    doNothing().when(programClass).superClassConstantAccept(Mockito.<ConstantVisitor>any());
    when(programClass.getProcessingInfo()).thenReturn(new ProgramClassOptimizationInfo());

    // Act
    boolean actualIsMergeableResult = classMerger.isMergeable(programClass);

    // Assert
    verify(targetClass).addSubClass(isA(Clazz.class));
    verify(targetClass).attributesAccept(isA(AttributeVisitor.class));
    verify(programClass, atLeast(1)).attributesAccept(Mockito.<AttributeVisitor>any());
    verify(targetClass).extendsOrImplements(isA(Clazz.class));
    verify(targetClass).extends_(eq("java/lang/Throwable"));
    verify(programClass).extends_(eq("java/lang/Throwable"));
    verify(targetClass).extends_(isA(Clazz.class));
    verify(programClass).extends_(isA(Clazz.class));
    verify(targetClass).getAccessFlags();
    verify(programClass, atLeast(1)).getAccessFlags();
    verify(targetClass, atLeast(1)).hierarchyAccept(eq(true), eq(true), anyBoolean(), eq(false),
        Mockito.<ClassVisitor>any());
    verify(programClass, atLeast(1)).hierarchyAccept(eq(true), eq(true), anyBoolean(), eq(false),
        Mockito.<ClassVisitor>any());
    verify(programClass).interfaceConstantsAccept(isA(ConstantVisitor.class));
    verify(programClass, atLeast(1)).methodsAccept(Mockito.<MemberVisitor>any());
    verify(programClass).superClassConstantAccept(isA(ConstantVisitor.class));
    verify(targetClass, atLeast(1)).getProcessingInfo();
    verify(programClass, atLeast(1)).getProcessingInfo();
    assertTrue(actualIsMergeableResult);
  }

  /**
   * Method under test: {@link ClassMerger#isMergeable(ProgramClass)}
   */
  @Test
  void testIsMergeable6() {
    // Arrange
    new RuntimeException("foo");
    ProgramClass targetClass = mock(ProgramClass.class);
    when(targetClass.extendsOrImplements(Mockito.<Clazz>any())).thenReturn(false);
    when(targetClass.extends_(Mockito.<String>any())).thenReturn(false);
    when(targetClass.extends_(Mockito.<Clazz>any())).thenReturn(true);
    when(targetClass.getAccessFlags()).thenReturn(1);
    doNothing().when(targetClass).attributesAccept(Mockito.<AttributeVisitor>any());
    doNothing().when(targetClass)
        .hierarchyAccept(anyBoolean(), anyBoolean(), anyBoolean(), anyBoolean(), Mockito.<ClassVisitor>any());
    when(targetClass.getProcessingInfo()).thenReturn(new ProgramClassOptimizationInfo());
    doNothing().when(targetClass).addSubClass(Mockito.<Clazz>any());
    targetClass.addSubClass(new LibraryClass());
    ClassMerger classMerger = new ClassMerger(targetClass, true, true, true, mock(ClassVisitor.class));
    ProgramClass programClass = mock(ProgramClass.class);
    when(programClass.extends_(Mockito.<String>any())).thenReturn(true);
    when(programClass.extends_(Mockito.<Clazz>any())).thenReturn(true);
    doNothing().when(programClass)
        .hierarchyAccept(anyBoolean(), anyBoolean(), anyBoolean(), anyBoolean(), Mockito.<ClassVisitor>any());
    doNothing().when(programClass).methodsAccept(Mockito.<MemberVisitor>any());
    when(programClass.getAccessFlags()).thenReturn(1);
    doNothing().when(programClass).attributesAccept(Mockito.<AttributeVisitor>any());
    doNothing().when(programClass).interfaceConstantsAccept(Mockito.<ConstantVisitor>any());
    doNothing().when(programClass).superClassConstantAccept(Mockito.<ConstantVisitor>any());
    when(programClass.getProcessingInfo()).thenReturn(new ProgramClassOptimizationInfo());

    // Act
    boolean actualIsMergeableResult = classMerger.isMergeable(programClass);

    // Assert
    verify(targetClass).addSubClass(isA(Clazz.class));
    verify(targetClass).attributesAccept(isA(AttributeVisitor.class));
    verify(programClass, atLeast(1)).attributesAccept(Mockito.<AttributeVisitor>any());
    verify(targetClass).extendsOrImplements(isA(Clazz.class));
    verify(targetClass).extends_(eq("java/lang/Throwable"));
    verify(programClass).extends_(eq("java/lang/Throwable"));
    verify(targetClass).extends_(isA(Clazz.class));
    verify(programClass).extends_(isA(Clazz.class));
    verify(targetClass).getAccessFlags();
    verify(programClass, atLeast(1)).getAccessFlags();
    verify(programClass, atLeast(1)).hierarchyAccept(eq(true), eq(true), anyBoolean(), eq(false),
        Mockito.<ClassVisitor>any());
    verify(targetClass, atLeast(1)).hierarchyAccept(eq(true), eq(true), eq(true), eq(false),
        Mockito.<ClassVisitor>any());
    verify(programClass).interfaceConstantsAccept(isA(ConstantVisitor.class));
    verify(programClass, atLeast(1)).methodsAccept(Mockito.<MemberVisitor>any());
    verify(programClass).superClassConstantAccept(isA(ConstantVisitor.class));
    verify(targetClass, atLeast(1)).getProcessingInfo();
    verify(programClass, atLeast(1)).getProcessingInfo();
    assertTrue(actualIsMergeableResult);
  }

  /**
   * Method under test: {@link ClassMerger#isMergeable(ProgramClass)}
   */
  @Test
  void testIsMergeable7() {
    // Arrange
    new RuntimeException("foo");
    ProgramClass targetClass = mock(ProgramClass.class);
    when(targetClass.extendsOrImplements(Mockito.<Clazz>any())).thenReturn(false);
    when(targetClass.extends_(Mockito.<String>any())).thenReturn(true);
    when(targetClass.extends_(Mockito.<Clazz>any())).thenReturn(false);
    when(targetClass.getAccessFlags()).thenReturn(1);
    doNothing().when(targetClass).attributesAccept(Mockito.<AttributeVisitor>any());
    doNothing().when(targetClass)
        .hierarchyAccept(anyBoolean(), anyBoolean(), anyBoolean(), anyBoolean(), Mockito.<ClassVisitor>any());
    when(targetClass.getProcessingInfo()).thenReturn(new ProgramClassOptimizationInfo());
    doNothing().when(targetClass).addSubClass(Mockito.<Clazz>any());
    targetClass.addSubClass(new LibraryClass());
    ClassMerger classMerger = new ClassMerger(targetClass, true, true, true, mock(ClassVisitor.class));
    ProgramClass programClass = mock(ProgramClass.class);
    when(programClass.extends_(Mockito.<String>any())).thenReturn(true);
    when(programClass.extends_(Mockito.<Clazz>any())).thenReturn(true);
    doNothing().when(programClass)
        .hierarchyAccept(anyBoolean(), anyBoolean(), anyBoolean(), anyBoolean(), Mockito.<ClassVisitor>any());
    doNothing().when(programClass).methodsAccept(Mockito.<MemberVisitor>any());
    when(programClass.getAccessFlags()).thenReturn(1);
    doNothing().when(programClass).attributesAccept(Mockito.<AttributeVisitor>any());
    doNothing().when(programClass).interfaceConstantsAccept(Mockito.<ConstantVisitor>any());
    doNothing().when(programClass).superClassConstantAccept(Mockito.<ConstantVisitor>any());
    when(programClass.getProcessingInfo()).thenReturn(new ProgramClassOptimizationInfo());

    // Act
    boolean actualIsMergeableResult = classMerger.isMergeable(programClass);

    // Assert
    verify(targetClass).addSubClass(isA(Clazz.class));
    verify(targetClass).attributesAccept(isA(AttributeVisitor.class));
    verify(programClass, atLeast(1)).attributesAccept(Mockito.<AttributeVisitor>any());
    verify(targetClass).extendsOrImplements(isA(Clazz.class));
    verify(targetClass).extends_(eq("java/lang/Throwable"));
    verify(programClass).extends_(eq("java/lang/Throwable"));
    verify(targetClass).extends_(isA(Clazz.class));
    verify(programClass, atLeast(1)).extends_(isA(Clazz.class));
    verify(targetClass).getAccessFlags();
    verify(programClass, atLeast(1)).getAccessFlags();
    verify(targetClass, atLeast(1)).hierarchyAccept(eq(true), eq(true), anyBoolean(), eq(false),
        Mockito.<ClassVisitor>any());
    verify(programClass, atLeast(1)).hierarchyAccept(eq(true), eq(true), anyBoolean(), eq(false),
        Mockito.<ClassVisitor>any());
    verify(programClass).interfaceConstantsAccept(isA(ConstantVisitor.class));
    verify(programClass, atLeast(1)).methodsAccept(Mockito.<MemberVisitor>any());
    verify(programClass).superClassConstantAccept(isA(ConstantVisitor.class));
    verify(targetClass, atLeast(1)).getProcessingInfo();
    verify(programClass, atLeast(1)).getProcessingInfo();
    assertTrue(actualIsMergeableResult);
  }

  /**
   * Method under test: {@link ClassMerger#isMergeable(ProgramClass)}
   */
  @Test
  void testIsMergeable8() {
    // Arrange
    new RuntimeException("foo");
    ProgramClass targetClass = mock(ProgramClass.class);
    when(targetClass.getAccessFlags()).thenReturn(1536);
    doNothing().when(targetClass).attributesAccept(Mockito.<AttributeVisitor>any());
    when(targetClass.getProcessingInfo()).thenReturn(new ProgramClassOptimizationInfo());
    doNothing().when(targetClass).addSubClass(Mockito.<Clazz>any());
    targetClass.addSubClass(new LibraryClass());
    ClassMerger classMerger = new ClassMerger(targetClass, true, true, true, mock(ClassVisitor.class));
    ProgramClass programClass = mock(ProgramClass.class);
    when(programClass.getAccessFlags()).thenReturn(1);
    doNothing().when(programClass).attributesAccept(Mockito.<AttributeVisitor>any());
    when(programClass.getProcessingInfo()).thenReturn(new ProgramClassOptimizationInfo());

    // Act
    boolean actualIsMergeableResult = classMerger.isMergeable(programClass);

    // Assert
    verify(targetClass).addSubClass(isA(Clazz.class));
    verify(programClass).attributesAccept(isA(AttributeVisitor.class));
    verify(targetClass).getAccessFlags();
    verify(programClass, atLeast(1)).getAccessFlags();
    verify(targetClass, atLeast(1)).getProcessingInfo();
    verify(programClass, atLeast(1)).getProcessingInfo();
    assertFalse(actualIsMergeableResult);
  }

  /**
   * Method under test: {@link ClassMerger#hasSignatureAttribute(Clazz)}
   */
  @Test
  void testHasSignatureAttribute() {
    // Arrange, Act and Assert
    assertFalse(ClassMerger.hasSignatureAttribute(new ProgramClass()));
  }

  /**
   * Method under test: {@link ClassMerger#isNestHostOrMember(Clazz)}
   */
  @Test
  void testIsNestHostOrMember() {
    // Arrange, Act and Assert
    assertFalse(ClassMerger.isNestHostOrMember(new ProgramClass()));
  }

  /**
   * Method under test: {@link ClassMerger#setTargetClass(Clazz, Clazz)}
   */
  @Test
  void testSetTargetClass() {
    // Arrange
    LibraryClass clazz = new LibraryClass();
    ProgramClassOptimizationInfo programClassOptimizationInfo = new ProgramClassOptimizationInfo();
    clazz.setProcessingInfo(programClassOptimizationInfo);

    // Act
    ClassMerger.setTargetClass(clazz, new LibraryClass());

    // Assert
    assertSame(programClassOptimizationInfo, clazz.getProcessingInfo());
  }

  /**
   * Method under test: {@link ClassMerger#getTargetClass(Clazz)}
   */
  @Test
  void testGetTargetClass() {
    // Arrange
    ProgramClassOptimizationInfo programClassOptimizationInfo = new ProgramClassOptimizationInfo();
    programClassOptimizationInfo.setTargetClass(null);

    LibraryClass clazz = new LibraryClass();
    clazz.setProcessingInfo(programClassOptimizationInfo);

    // Act and Assert
    assertNull(ClassMerger.getTargetClass(clazz));
  }

  /**
   * Method under test: {@link ClassMerger#getTargetClass(Clazz)}
   */
  @Test
  void testGetTargetClass2() {
    // Arrange
    LibraryClass clazz = new LibraryClass();
    clazz.setProcessingInfo(new ClassOptimizationInfo());

    // Act and Assert
    assertNull(ClassMerger.getTargetClass(clazz));
  }
}
