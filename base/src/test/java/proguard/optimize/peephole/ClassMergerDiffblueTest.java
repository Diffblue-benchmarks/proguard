package proguard.optimize.peephole;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.anyBoolean;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import com.diffblue.cover.annotations.MethodsUnderTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
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
   * Test {@link ClassMerger#visitProgramClass(ProgramClass)}.
   * <p>
   * Method under test: {@link ClassMerger#visitProgramClass(ProgramClass)}
   */
  @Test
  @DisplayName("Test visitProgramClass(ProgramClass)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void proguard.optimize.peephole.ClassMerger.visitProgramClass(proguard.classfile.ProgramClass)"})
  void testVisitProgramClass() {
    // Arrange
    ProgramClass targetClass = mock(ProgramClass.class);
    when(targetClass.getProcessingInfo()).thenReturn(new ClassOptimizationInfo());
    ClassMerger classMerger = new ClassMerger(targetClass, true, true, true);
    ProgramClass programClass = mock(ProgramClass.class);
    when(programClass.getProcessingInfo()).thenReturn(null);

    // Act
    classMerger.visitProgramClass(programClass);

    // Assert
    verify(targetClass).getProcessingInfo();
    verify(programClass).getProcessingInfo();
  }

  /**
   * Test {@link ClassMerger#visitProgramClass(ProgramClass)}.
   * <ul>
   *   <li>Given {@link ClassOptimizationInfo} (default constructor).</li>
   * </ul>
   * <p>
   * Method under test: {@link ClassMerger#visitProgramClass(ProgramClass)}
   */
  @Test
  @DisplayName("Test visitProgramClass(ProgramClass); given ClassOptimizationInfo (default constructor)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void proguard.optimize.peephole.ClassMerger.visitProgramClass(proguard.classfile.ProgramClass)"})
  void testVisitProgramClass_givenClassOptimizationInfo() {
    // Arrange
    ClassMerger classMerger = new ClassMerger(mock(ProgramClass.class), true, true, true);
    ProgramClass programClass = mock(ProgramClass.class);
    when(programClass.getProcessingInfo()).thenReturn(new ClassOptimizationInfo());

    // Act
    classMerger.visitProgramClass(programClass);

    // Assert
    verify(programClass).getProcessingInfo();
  }

  /**
   * Test {@link ClassMerger#visitProgramClass(ProgramClass)}.
   * <ul>
   *   <li>Given {@link ClassOptimizationInfo} {@link ClassOptimizationInfo#isKept()} return {@code true}.</li>
   *   <li>Then calls {@link ClassOptimizationInfo#isKept()}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ClassMerger#visitProgramClass(ProgramClass)}
   */
  @Test
  @DisplayName("Test visitProgramClass(ProgramClass); given ClassOptimizationInfo isKept() return 'true'; then calls isKept()")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void proguard.optimize.peephole.ClassMerger.visitProgramClass(proguard.classfile.ProgramClass)"})
  void testVisitProgramClass_givenClassOptimizationInfoIsKeptReturnTrue_thenCallsIsKept() {
    // Arrange
    ClassOptimizationInfo classOptimizationInfo = mock(ClassOptimizationInfo.class);
    when(classOptimizationInfo.isKept()).thenReturn(true);
    ProgramClass targetClass = mock(ProgramClass.class);
    when(targetClass.getProcessingInfo()).thenReturn(classOptimizationInfo);
    ClassMerger classMerger = new ClassMerger(targetClass, true, true, true);
    ProgramClass programClass = mock(ProgramClass.class);
    when(programClass.getProcessingInfo()).thenReturn(null);

    // Act
    classMerger.visitProgramClass(programClass);

    // Assert
    verify(classOptimizationInfo).isKept();
    verify(targetClass).getProcessingInfo();
    verify(programClass).getProcessingInfo();
  }

  /**
   * Test {@link ClassMerger#isMergeable(ProgramClass)}.
   * <p>
   * Method under test: {@link ClassMerger#isMergeable(ProgramClass)}
   */
  @Test
  @DisplayName("Test isMergeable(ProgramClass)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean proguard.optimize.peephole.ClassMerger.isMergeable(proguard.classfile.ProgramClass)"})
  void testIsMergeable() {
    // Arrange
    Constant constant = mock(Constant.class);
    doNothing().when(constant).addProcessingFlags((int[]) Mockito.any());
    constant.addProcessingFlags(2, 1, 2, 1);
    ClassMerger classMerger = new ClassMerger(
        new ProgramClass(1, 3, new Constant[]{constant}, 1, 1, 1, "Feature Name", 1, new ClassOptimizationInfo()), true,
        true, true);

    // Act
    boolean actualIsMergeableResult = classMerger.isMergeable(new ProgramClass());

    // Assert
    verify(constant).addProcessingFlags((int[]) Mockito.any());
    assertFalse(actualIsMergeableResult);
  }

  /**
   * Test {@link ClassMerger#isMergeable(ProgramClass)}.
   * <ul>
   *   <li>Given {@link ClassOptimizationInfo} (default constructor).</li>
   * </ul>
   * <p>
   * Method under test: {@link ClassMerger#isMergeable(ProgramClass)}
   */
  @Test
  @DisplayName("Test isMergeable(ProgramClass); given ClassOptimizationInfo (default constructor)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean proguard.optimize.peephole.ClassMerger.isMergeable(proguard.classfile.ProgramClass)"})
  void testIsMergeable_givenClassOptimizationInfo() {
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
   * Test {@link ClassMerger#isMergeable(ProgramClass)}.
   * <ul>
   *   <li>Given {@link ClassOptimizationInfo} {@link ClassOptimizationInfo#isKept()} return {@code true}.</li>
   *   <li>Then calls {@link ClassOptimizationInfo#isKept()}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ClassMerger#isMergeable(ProgramClass)}
   */
  @Test
  @DisplayName("Test isMergeable(ProgramClass); given ClassOptimizationInfo isKept() return 'true'; then calls isKept()")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean proguard.optimize.peephole.ClassMerger.isMergeable(proguard.classfile.ProgramClass)"})
  void testIsMergeable_givenClassOptimizationInfoIsKeptReturnTrue_thenCallsIsKept() {
    // Arrange
    Constant constant = mock(Constant.class);
    doNothing().when(constant).addProcessingFlags((int[]) Mockito.any());
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
    verify(constant).addProcessingFlags((int[]) Mockito.any());
    assertFalse(actualIsMergeableResult);
  }

  /**
   * Test {@link ClassMerger#isMergeable(ProgramClass)}.
   * <ul>
   *   <li>Given {@link ProgramClass} {@link ProgramClass#extendsOrImplements(Clazz)} return {@code true}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ClassMerger#isMergeable(ProgramClass)}
   */
  @Test
  @DisplayName("Test isMergeable(ProgramClass); given ProgramClass extendsOrImplements(Clazz) return 'true'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean proguard.optimize.peephole.ClassMerger.isMergeable(proguard.classfile.ProgramClass)"})
  void testIsMergeable_givenProgramClassExtendsOrImplementsReturnTrue() {
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
   * Test {@link ClassMerger#isMergeable(ProgramClass)}.
   * <ul>
   *   <li>Given {@link ProgramClass} {@link ProgramClass#extends_(String)} return {@code false}.</li>
   *   <li>Then return {@code true}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ClassMerger#isMergeable(ProgramClass)}
   */
  @Test
  @DisplayName("Test isMergeable(ProgramClass); given ProgramClass extends_(String) return 'false'; then return 'true'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean proguard.optimize.peephole.ClassMerger.isMergeable(proguard.classfile.ProgramClass)"})
  void testIsMergeable_givenProgramClassExtends_ReturnFalse_thenReturnTrue() {
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
   * Test {@link ClassMerger#isMergeable(ProgramClass)}.
   * <ul>
   *   <li>Given {@link ProgramClass} {@link ProgramClass#extends_(Clazz)} return {@code false}.</li>
   *   <li>Then return {@code true}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ClassMerger#isMergeable(ProgramClass)}
   */
  @Test
  @DisplayName("Test isMergeable(ProgramClass); given ProgramClass extends_(Clazz) return 'false'; then return 'true'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean proguard.optimize.peephole.ClassMerger.isMergeable(proguard.classfile.ProgramClass)"})
  void testIsMergeable_givenProgramClassExtends_ReturnFalse_thenReturnTrue2() {
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
   * Test {@link ClassMerger#isMergeable(ProgramClass)}.
   * <ul>
   *   <li>Given {@link ProgramClass} {@link ProgramClass#extends_(String)} return {@code true}.</li>
   *   <li>Then return {@code true}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ClassMerger#isMergeable(ProgramClass)}
   */
  @Test
  @DisplayName("Test isMergeable(ProgramClass); given ProgramClass extends_(String) return 'true'; then return 'true'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean proguard.optimize.peephole.ClassMerger.isMergeable(proguard.classfile.ProgramClass)"})
  void testIsMergeable_givenProgramClassExtends_ReturnTrue_thenReturnTrue() {
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
   * Test {@link ClassMerger#isMergeable(ProgramClass)}.
   * <ul>
   *   <li>Given {@link ProgramClass} {@link ProgramClass#getAccessFlags()} return {@code 1536}.</li>
   *   <li>Then return {@code false}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ClassMerger#isMergeable(ProgramClass)}
   */
  @Test
  @DisplayName("Test isMergeable(ProgramClass); given ProgramClass getAccessFlags() return '1536'; then return 'false'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean proguard.optimize.peephole.ClassMerger.isMergeable(proguard.classfile.ProgramClass)"})
  void testIsMergeable_givenProgramClassGetAccessFlagsReturn1536_thenReturnFalse() {
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
   * Test {@link ClassMerger#hasSignatureAttribute(Clazz)}.
   * <ul>
   *   <li>When {@link ProgramClass#ProgramClass()}.</li>
   *   <li>Then return {@code false}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ClassMerger#hasSignatureAttribute(Clazz)}
   */
  @Test
  @DisplayName("Test hasSignatureAttribute(Clazz); when ProgramClass(); then return 'false'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean proguard.optimize.peephole.ClassMerger.hasSignatureAttribute(proguard.classfile.Clazz)"})
  void testHasSignatureAttribute_whenProgramClass_thenReturnFalse() {
    // Arrange, Act and Assert
    assertFalse(ClassMerger.hasSignatureAttribute(new ProgramClass()));
  }

  /**
   * Test {@link ClassMerger#isNestHostOrMember(Clazz)}.
   * <ul>
   *   <li>When {@link ProgramClass#ProgramClass()}.</li>
   *   <li>Then return {@code false}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ClassMerger#isNestHostOrMember(Clazz)}
   */
  @Test
  @DisplayName("Test isNestHostOrMember(Clazz); when ProgramClass(); then return 'false'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean proguard.optimize.peephole.ClassMerger.isNestHostOrMember(proguard.classfile.Clazz)"})
  void testIsNestHostOrMember_whenProgramClass_thenReturnFalse() {
    // Arrange, Act and Assert
    assertFalse(ClassMerger.isNestHostOrMember(new ProgramClass()));
  }

  /**
   * Test {@link ClassMerger#setTargetClass(Clazz, Clazz)}.
   * <ul>
   *   <li>Then {@link LibraryClass#LibraryClass()} ProcessingInfo {@link ProgramClassOptimizationInfo}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ClassMerger#setTargetClass(Clazz, Clazz)}
   */
  @Test
  @DisplayName("Test setTargetClass(Clazz, Clazz); then LibraryClass() ProcessingInfo ProgramClassOptimizationInfo")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "void proguard.optimize.peephole.ClassMerger.setTargetClass(proguard.classfile.Clazz, proguard.classfile.Clazz)"})
  void testSetTargetClass_thenLibraryClassProcessingInfoProgramClassOptimizationInfo() {
    // Arrange
    LibraryClass clazz = new LibraryClass();
    clazz.setProcessingInfo(new ProgramClassOptimizationInfo());
    LibraryClass targetClass = new LibraryClass();

    // Act
    ClassMerger.setTargetClass(clazz, targetClass);

    // Assert
    Object processingInfo = clazz.getProcessingInfo();
    assertTrue(processingInfo instanceof ProgramClassOptimizationInfo);
    assertSame(targetClass, ((ProgramClassOptimizationInfo) processingInfo).getTargetClass());
  }

  /**
   * Test {@link ClassMerger#getTargetClass(Clazz)}.
   * <ul>
   *   <li>Given {@link ClassOptimizationInfo} (default constructor).</li>
   * </ul>
   * <p>
   * Method under test: {@link ClassMerger#getTargetClass(Clazz)}
   */
  @Test
  @DisplayName("Test getTargetClass(Clazz); given ClassOptimizationInfo (default constructor)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "proguard.classfile.Clazz proguard.optimize.peephole.ClassMerger.getTargetClass(proguard.classfile.Clazz)"})
  void testGetTargetClass_givenClassOptimizationInfo() {
    // Arrange
    LibraryClass clazz = new LibraryClass();
    clazz.setProcessingInfo(new ClassOptimizationInfo());

    // Act and Assert
    assertNull(ClassMerger.getTargetClass(clazz));
  }

  /**
   * Test {@link ClassMerger#getTargetClass(Clazz)}.
   * <ul>
   *   <li>Given {@link ProgramClassOptimizationInfo} (default constructor) TargetClass is {@code null}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ClassMerger#getTargetClass(Clazz)}
   */
  @Test
  @DisplayName("Test getTargetClass(Clazz); given ProgramClassOptimizationInfo (default constructor) TargetClass is 'null'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "proguard.classfile.Clazz proguard.optimize.peephole.ClassMerger.getTargetClass(proguard.classfile.Clazz)"})
  void testGetTargetClass_givenProgramClassOptimizationInfoTargetClassIsNull() {
    // Arrange
    ProgramClassOptimizationInfo programClassOptimizationInfo = new ProgramClassOptimizationInfo();
    programClassOptimizationInfo.setTargetClass(null);

    LibraryClass clazz = new LibraryClass();
    clazz.setProcessingInfo(programClassOptimizationInfo);

    // Act and Assert
    assertNull(ClassMerger.getTargetClass(clazz));
  }
}
