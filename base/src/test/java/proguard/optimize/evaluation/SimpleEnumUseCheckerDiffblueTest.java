package proguard.optimize.evaluation;

import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.anyInt;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import proguard.classfile.Clazz;
import proguard.classfile.LibraryClass;
import proguard.classfile.LibraryField;
import proguard.classfile.LibraryMethod;
import proguard.classfile.Method;
import proguard.classfile.ProgramClass;
import proguard.classfile.ProgramMethod;
import proguard.classfile.attribute.BootstrapMethodInfo;
import proguard.classfile.attribute.BootstrapMethodsAttribute;
import proguard.classfile.attribute.CodeAttribute;
import proguard.classfile.attribute.visitor.AttributeVisitor;
import proguard.classfile.attribute.visitor.BootstrapMethodInfoVisitor;
import proguard.classfile.constant.ClassConstant;
import proguard.classfile.constant.Constant;
import proguard.classfile.constant.FieldrefConstant;
import proguard.classfile.constant.MethodHandleConstant;
import proguard.classfile.constant.MethodTypeConstant;
import proguard.classfile.constant.RefConstant;
import proguard.classfile.constant.StringConstant;
import proguard.classfile.constant.visitor.ConstantVisitor;
import proguard.classfile.instruction.ConstantInstruction;
import proguard.classfile.visitor.ClassVisitor;
import proguard.classfile.visitor.MemberVisitor;
import proguard.optimize.info.ClassOptimizationInfo;
import proguard.optimize.info.ProgramClassOptimizationInfo;
import proguard.resources.file.ResourceFile;

class SimpleEnumUseCheckerDiffblueTest {
  /**
   * Test {@link SimpleEnumUseChecker#visitProgramClass(ProgramClass)}.
   *
   * <ul>
   *   <li>Given {@code 8192}.
   *   <li>When {@link ProgramClass} {@link ProgramClass#getAccessFlags()} return {@code 8192}.
   * </ul>
   *
   * <p>Method under test: {@link SimpleEnumUseChecker#visitProgramClass(ProgramClass)}
   */
  @Test
  @DisplayName(
      "Test visitProgramClass(ProgramClass); given '8192'; when ProgramClass getAccessFlags() return '8192'")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void SimpleEnumUseChecker.visitProgramClass(ProgramClass)"})
  void testVisitProgramClass_given8192_whenProgramClassGetAccessFlagsReturn8192() {
    // Arrange
    SimpleEnumUseChecker simpleEnumUseChecker = new SimpleEnumUseChecker();
    ProgramClass programClass = mock(ProgramClass.class);
    when(programClass.getAccessFlags()).thenReturn(8192);
    doNothing().when(programClass).attributesAccept(Mockito.<AttributeVisitor>any());
    doNothing().when(programClass).methodsAccept(Mockito.<MemberVisitor>any());

    // Act
    simpleEnumUseChecker.visitProgramClass(programClass);

    // Assert
    verify(programClass).attributesAccept(isA(AttributeVisitor.class));
    verify(programClass).getAccessFlags();
    verify(programClass).methodsAccept(isA(MemberVisitor.class));
  }

  /**
   * Test {@link SimpleEnumUseChecker#visitProgramClass(ProgramClass)}.
   *
   * <ul>
   *   <li>When {@link ProgramClass} {@link ProgramClass#getAccessFlags()} return one.
   * </ul>
   *
   * <p>Method under test: {@link SimpleEnumUseChecker#visitProgramClass(ProgramClass)}
   */
  @Test
  @DisplayName(
      "Test visitProgramClass(ProgramClass); when ProgramClass getAccessFlags() return one")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void SimpleEnumUseChecker.visitProgramClass(ProgramClass)"})
  void testVisitProgramClass_whenProgramClassGetAccessFlagsReturnOne() {
    // Arrange
    SimpleEnumUseChecker simpleEnumUseChecker = new SimpleEnumUseChecker();
    ProgramClass programClass = mock(ProgramClass.class);
    when(programClass.getAccessFlags()).thenReturn(1);
    doNothing().when(programClass).attributesAccept(Mockito.<AttributeVisitor>any());
    doNothing().when(programClass).methodsAccept(Mockito.<MemberVisitor>any());

    // Act
    simpleEnumUseChecker.visitProgramClass(programClass);

    // Assert
    verify(programClass).attributesAccept(isA(AttributeVisitor.class));
    verify(programClass).getAccessFlags();
    verify(programClass).methodsAccept(isA(MemberVisitor.class));
  }

  /**
   * Test {@link SimpleEnumUseChecker#visitBootstrapMethodsAttribute(Clazz,
   * BootstrapMethodsAttribute)}.
   *
   * <ul>
   *   <li>Then calls {@link BootstrapMethodsAttribute#bootstrapMethodEntriesAccept(Clazz,
   *       BootstrapMethodInfoVisitor)}.
   * </ul>
   *
   * <p>Method under test: {@link SimpleEnumUseChecker#visitBootstrapMethodsAttribute(Clazz,
   * BootstrapMethodsAttribute)}
   */
  @Test
  @DisplayName(
      "Test visitBootstrapMethodsAttribute(Clazz, BootstrapMethodsAttribute); then calls bootstrapMethodEntriesAccept(Clazz, BootstrapMethodInfoVisitor)")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void SimpleEnumUseChecker.visitBootstrapMethodsAttribute(Clazz, BootstrapMethodsAttribute)"
  })
  void testVisitBootstrapMethodsAttribute_thenCallsBootstrapMethodEntriesAccept() {
    // Arrange
    SimpleEnumUseChecker simpleEnumUseChecker = new SimpleEnumUseChecker();
    LibraryClass clazz = new LibraryClass();
    BootstrapMethodsAttribute bootstrapMethodsAttribute = mock(BootstrapMethodsAttribute.class);
    doNothing()
        .when(bootstrapMethodsAttribute)
        .bootstrapMethodEntriesAccept(
            Mockito.<Clazz>any(), Mockito.<BootstrapMethodInfoVisitor>any());

    // Act
    simpleEnumUseChecker.visitBootstrapMethodsAttribute(clazz, bootstrapMethodsAttribute);

    // Assert
    verify(bootstrapMethodsAttribute)
        .bootstrapMethodEntriesAccept(isA(Clazz.class), isA(BootstrapMethodInfoVisitor.class));
  }

  /**
   * Test {@link SimpleEnumUseChecker#visitBootstrapMethodsAttribute(Clazz,
   * BootstrapMethodsAttribute)}.
   *
   * <ul>
   *   <li>Then calls {@link BootstrapMethodInfo#methodArgumentsAccept(Clazz, ConstantVisitor)}.
   * </ul>
   *
   * <p>Method under test: {@link SimpleEnumUseChecker#visitBootstrapMethodsAttribute(Clazz,
   * BootstrapMethodsAttribute)}
   */
  @Test
  @DisplayName(
      "Test visitBootstrapMethodsAttribute(Clazz, BootstrapMethodsAttribute); then calls methodArgumentsAccept(Clazz, ConstantVisitor)")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void SimpleEnumUseChecker.visitBootstrapMethodsAttribute(Clazz, BootstrapMethodsAttribute)"
  })
  void testVisitBootstrapMethodsAttribute_thenCallsMethodArgumentsAccept() {
    // Arrange
    SimpleEnumUseChecker simpleEnumUseChecker = new SimpleEnumUseChecker();
    LibraryClass clazz = new LibraryClass();
    BootstrapMethodInfo bootstrapMethodInfo = mock(BootstrapMethodInfo.class);
    doNothing()
        .when(bootstrapMethodInfo)
        .methodArgumentsAccept(Mockito.<Clazz>any(), Mockito.<ConstantVisitor>any());
    doNothing()
        .when(bootstrapMethodInfo)
        .methodHandleAccept(Mockito.<Clazz>any(), Mockito.<ConstantVisitor>any());

    // Act
    simpleEnumUseChecker.visitBootstrapMethodsAttribute(
        clazz,
        new BootstrapMethodsAttribute(1, 1, new BootstrapMethodInfo[] {bootstrapMethodInfo}));

    // Assert
    verify(bootstrapMethodInfo).methodArgumentsAccept(isA(Clazz.class), isA(ConstantVisitor.class));
    verify(bootstrapMethodInfo).methodHandleAccept(isA(Clazz.class), isA(ConstantVisitor.class));
  }

  /**
   * Test {@link SimpleEnumUseChecker#visitBootstrapMethodInfo(Clazz, BootstrapMethodInfo)}.
   *
   * <p>Method under test: {@link SimpleEnumUseChecker#visitBootstrapMethodInfo(Clazz,
   * BootstrapMethodInfo)}
   */
  @Test
  @DisplayName("Test visitBootstrapMethodInfo(Clazz, BootstrapMethodInfo)")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void SimpleEnumUseChecker.visitBootstrapMethodInfo(Clazz, BootstrapMethodInfo)"
  })
  void testVisitBootstrapMethodInfo() {
    // Arrange
    SimpleEnumUseChecker simpleEnumUseChecker = new SimpleEnumUseChecker();
    LibraryClass clazz = mock(LibraryClass.class);
    doNothing().when(clazz).constantPoolEntryAccept(anyInt(), Mockito.<ConstantVisitor>any());

    // Act
    simpleEnumUseChecker.visitBootstrapMethodInfo(
        clazz, new BootstrapMethodInfo(1, 3, new int[] {1, 0, 1, 0}));

    // Assert
    verify(clazz, atLeast(1)).constantPoolEntryAccept(anyInt(), isA(ConstantVisitor.class));
  }

  /**
   * Test {@link SimpleEnumUseChecker#visitBootstrapMethodInfo(Clazz, BootstrapMethodInfo)}.
   *
   * <ul>
   *   <li>Then calls {@link LibraryClass#constantPoolEntryAccept(int, ConstantVisitor)}.
   * </ul>
   *
   * <p>Method under test: {@link SimpleEnumUseChecker#visitBootstrapMethodInfo(Clazz,
   * BootstrapMethodInfo)}
   */
  @Test
  @DisplayName(
      "Test visitBootstrapMethodInfo(Clazz, BootstrapMethodInfo); then calls constantPoolEntryAccept(int, ConstantVisitor)")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void SimpleEnumUseChecker.visitBootstrapMethodInfo(Clazz, BootstrapMethodInfo)"
  })
  void testVisitBootstrapMethodInfo_thenCallsConstantPoolEntryAccept() {
    // Arrange
    SimpleEnumUseChecker simpleEnumUseChecker = new SimpleEnumUseChecker();
    LibraryClass clazz = mock(LibraryClass.class);
    doNothing().when(clazz).constantPoolEntryAccept(anyInt(), Mockito.<ConstantVisitor>any());

    // Act
    simpleEnumUseChecker.visitBootstrapMethodInfo(clazz, new BootstrapMethodInfo());

    // Assert
    verify(clazz).constantPoolEntryAccept(eq(0), isA(ConstantVisitor.class));
  }

  /**
   * Test {@link SimpleEnumUseChecker#visitBootstrapMethodInfo(Clazz, BootstrapMethodInfo)}.
   *
   * <ul>
   *   <li>When {@link LibraryClass}.
   *   <li>Then calls {@link BootstrapMethodInfo#methodArgumentsAccept(Clazz, ConstantVisitor)}.
   * </ul>
   *
   * <p>Method under test: {@link SimpleEnumUseChecker#visitBootstrapMethodInfo(Clazz,
   * BootstrapMethodInfo)}
   */
  @Test
  @DisplayName(
      "Test visitBootstrapMethodInfo(Clazz, BootstrapMethodInfo); when LibraryClass; then calls methodArgumentsAccept(Clazz, ConstantVisitor)")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void SimpleEnumUseChecker.visitBootstrapMethodInfo(Clazz, BootstrapMethodInfo)"
  })
  void testVisitBootstrapMethodInfo_whenLibraryClass_thenCallsMethodArgumentsAccept() {
    // Arrange
    SimpleEnumUseChecker simpleEnumUseChecker = new SimpleEnumUseChecker();
    LibraryClass clazz = mock(LibraryClass.class);
    BootstrapMethodInfo bootstrapMethodInfo = mock(BootstrapMethodInfo.class);
    doNothing()
        .when(bootstrapMethodInfo)
        .methodArgumentsAccept(Mockito.<Clazz>any(), Mockito.<ConstantVisitor>any());
    doNothing()
        .when(bootstrapMethodInfo)
        .methodHandleAccept(Mockito.<Clazz>any(), Mockito.<ConstantVisitor>any());

    // Act
    simpleEnumUseChecker.visitBootstrapMethodInfo(clazz, bootstrapMethodInfo);

    // Assert
    verify(bootstrapMethodInfo).methodArgumentsAccept(isA(Clazz.class), isA(ConstantVisitor.class));
    verify(bootstrapMethodInfo).methodHandleAccept(isA(Clazz.class), isA(ConstantVisitor.class));
  }

  /**
   * Test {@link SimpleEnumUseChecker#visitStringConstant(Clazz, StringConstant)}.
   *
   * <ul>
   *   <li>Given {@link LibraryClass} {@link LibraryClass#accept(ClassVisitor)} does nothing.
   *   <li>Then calls {@link LibraryClass#accept(ClassVisitor)}.
   * </ul>
   *
   * <p>Method under test: {@link SimpleEnumUseChecker#visitStringConstant(Clazz, StringConstant)}
   */
  @Test
  @DisplayName(
      "Test visitStringConstant(Clazz, StringConstant); given LibraryClass accept(ClassVisitor) does nothing; then calls accept(ClassVisitor)")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void SimpleEnumUseChecker.visitStringConstant(Clazz, StringConstant)"})
  void testVisitStringConstant_givenLibraryClassAcceptDoesNothing_thenCallsAccept() {
    // Arrange
    SimpleEnumUseChecker simpleEnumUseChecker = new SimpleEnumUseChecker();
    LibraryClass clazz = new LibraryClass();
    LibraryClass libraryClass = mock(LibraryClass.class);
    doNothing().when(libraryClass).accept(Mockito.<ClassVisitor>any());
    StringConstant stringConstant = new StringConstant(1, new ResourceFile("foo.txt", 3L));

    stringConstant.referencedClass = libraryClass;
    stringConstant.referencedMember = null;

    // Act
    simpleEnumUseChecker.visitStringConstant(clazz, stringConstant);

    // Assert
    verify(libraryClass).accept(isA(ClassVisitor.class));
  }

  /**
   * Test {@link SimpleEnumUseChecker#visitStringConstant(Clazz, StringConstant)}.
   *
   * <ul>
   *   <li>Then calls {@link StringConstant#referencedClassAccept(ClassVisitor)}.
   * </ul>
   *
   * <p>Method under test: {@link SimpleEnumUseChecker#visitStringConstant(Clazz, StringConstant)}
   */
  @Test
  @DisplayName(
      "Test visitStringConstant(Clazz, StringConstant); then calls referencedClassAccept(ClassVisitor)")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void SimpleEnumUseChecker.visitStringConstant(Clazz, StringConstant)"})
  void testVisitStringConstant_thenCallsReferencedClassAccept() {
    // Arrange
    SimpleEnumUseChecker simpleEnumUseChecker = new SimpleEnumUseChecker();
    LibraryClass clazz = new LibraryClass();
    StringConstant stringConstant = mock(StringConstant.class);
    doNothing().when(stringConstant).referencedClassAccept(Mockito.<ClassVisitor>any());

    // Act
    simpleEnumUseChecker.visitStringConstant(clazz, stringConstant);

    // Assert
    verify(stringConstant).referencedClassAccept(isA(ClassVisitor.class));
  }

  /**
   * Test {@link SimpleEnumUseChecker#visitMethodHandleConstant(Clazz, MethodHandleConstant)}.
   *
   * <ul>
   *   <li>Then calls {@link LibraryClass#constantPoolEntryAccept(int, ConstantVisitor)}.
   * </ul>
   *
   * <p>Method under test: {@link SimpleEnumUseChecker#visitMethodHandleConstant(Clazz,
   * MethodHandleConstant)}
   */
  @Test
  @DisplayName(
      "Test visitMethodHandleConstant(Clazz, MethodHandleConstant); then calls constantPoolEntryAccept(int, ConstantVisitor)")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void SimpleEnumUseChecker.visitMethodHandleConstant(Clazz, MethodHandleConstant)"
  })
  void testVisitMethodHandleConstant_thenCallsConstantPoolEntryAccept() {
    // Arrange
    SimpleEnumUseChecker simpleEnumUseChecker = new SimpleEnumUseChecker();
    LibraryClass clazz = mock(LibraryClass.class);
    doNothing().when(clazz).constantPoolEntryAccept(anyInt(), Mockito.<ConstantVisitor>any());

    // Act
    simpleEnumUseChecker.visitMethodHandleConstant(clazz, new MethodHandleConstant(1, 1));

    // Assert
    verify(clazz).constantPoolEntryAccept(eq(1), isA(ConstantVisitor.class));
  }

  /**
   * Test {@link SimpleEnumUseChecker#visitMethodHandleConstant(Clazz, MethodHandleConstant)}.
   *
   * <ul>
   *   <li>When {@link LibraryClass}.
   *   <li>Then calls {@link MethodHandleConstant#referenceAccept(Clazz, ConstantVisitor)}.
   * </ul>
   *
   * <p>Method under test: {@link SimpleEnumUseChecker#visitMethodHandleConstant(Clazz,
   * MethodHandleConstant)}
   */
  @Test
  @DisplayName(
      "Test visitMethodHandleConstant(Clazz, MethodHandleConstant); when LibraryClass; then calls referenceAccept(Clazz, ConstantVisitor)")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void SimpleEnumUseChecker.visitMethodHandleConstant(Clazz, MethodHandleConstant)"
  })
  void testVisitMethodHandleConstant_whenLibraryClass_thenCallsReferenceAccept() {
    // Arrange
    SimpleEnumUseChecker simpleEnumUseChecker = new SimpleEnumUseChecker();
    LibraryClass clazz = mock(LibraryClass.class);
    MethodHandleConstant methodHandleConstant = mock(MethodHandleConstant.class);
    doNothing()
        .when(methodHandleConstant)
        .referenceAccept(Mockito.<Clazz>any(), Mockito.<ConstantVisitor>any());

    // Act
    simpleEnumUseChecker.visitMethodHandleConstant(clazz, methodHandleConstant);

    // Assert
    verify(methodHandleConstant).referenceAccept(isA(Clazz.class), isA(ConstantVisitor.class));
  }

  /**
   * Test {@link SimpleEnumUseChecker#visitMethodTypeConstant(Clazz, MethodTypeConstant)}.
   *
   * <ul>
   *   <li>Then calls {@link MethodTypeConstant#referencedClassesAccept(ClassVisitor)}.
   * </ul>
   *
   * <p>Method under test: {@link SimpleEnumUseChecker#visitMethodTypeConstant(Clazz,
   * MethodTypeConstant)}
   */
  @Test
  @DisplayName(
      "Test visitMethodTypeConstant(Clazz, MethodTypeConstant); then calls referencedClassesAccept(ClassVisitor)")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void SimpleEnumUseChecker.visitMethodTypeConstant(Clazz, MethodTypeConstant)"
  })
  void testVisitMethodTypeConstant_thenCallsReferencedClassesAccept() {
    // Arrange
    SimpleEnumUseChecker simpleEnumUseChecker = new SimpleEnumUseChecker();
    LibraryClass clazz = new LibraryClass();
    MethodTypeConstant methodTypeConstant = mock(MethodTypeConstant.class);
    doNothing().when(methodTypeConstant).referencedClassesAccept(Mockito.<ClassVisitor>any());

    // Act
    simpleEnumUseChecker.visitMethodTypeConstant(clazz, methodTypeConstant);

    // Assert
    verify(methodTypeConstant).referencedClassesAccept(isA(ClassVisitor.class));
  }

  /**
   * Test {@link SimpleEnumUseChecker#visitMethodTypeConstant(Clazz, MethodTypeConstant)}.
   *
   * <ul>
   *   <li>When {@link LibraryClass} {@link LibraryClass#accept(ClassVisitor)} does nothing.
   *   <li>Then calls {@link LibraryClass#accept(ClassVisitor)}.
   * </ul>
   *
   * <p>Method under test: {@link SimpleEnumUseChecker#visitMethodTypeConstant(Clazz,
   * MethodTypeConstant)}
   */
  @Test
  @DisplayName(
      "Test visitMethodTypeConstant(Clazz, MethodTypeConstant); when LibraryClass accept(ClassVisitor) does nothing; then calls accept(ClassVisitor)")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void SimpleEnumUseChecker.visitMethodTypeConstant(Clazz, MethodTypeConstant)"
  })
  void testVisitMethodTypeConstant_whenLibraryClassAcceptDoesNothing_thenCallsAccept() {
    // Arrange
    SimpleEnumUseChecker simpleEnumUseChecker = new SimpleEnumUseChecker();
    LibraryClass clazz = new LibraryClass();
    LibraryClass libraryClass = mock(LibraryClass.class);
    doNothing().when(libraryClass).accept(Mockito.<ClassVisitor>any());

    // Act
    simpleEnumUseChecker.visitMethodTypeConstant(
        clazz, new MethodTypeConstant(1, new Clazz[] {libraryClass}));

    // Assert
    verify(libraryClass).accept(isA(ClassVisitor.class));
  }

  /**
   * Test {@link SimpleEnumUseChecker#visitAnyRefConstant(Clazz, RefConstant)}.
   *
   * <ul>
   *   <li>Then calls {@link FieldrefConstant#referencedClassAccept(ClassVisitor)}.
   * </ul>
   *
   * <p>Method under test: {@link SimpleEnumUseChecker#visitAnyRefConstant(Clazz, RefConstant)}
   */
  @Test
  @DisplayName(
      "Test visitAnyRefConstant(Clazz, RefConstant); then calls referencedClassAccept(ClassVisitor)")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void SimpleEnumUseChecker.visitAnyRefConstant(Clazz, RefConstant)"})
  void testVisitAnyRefConstant_thenCallsReferencedClassAccept() {
    // Arrange
    SimpleEnumUseChecker simpleEnumUseChecker = new SimpleEnumUseChecker();
    LibraryClass clazz = new LibraryClass();
    FieldrefConstant refConstant = mock(FieldrefConstant.class);
    doNothing().when(refConstant).referencedClassAccept(Mockito.<ClassVisitor>any());

    // Act
    simpleEnumUseChecker.visitAnyRefConstant(clazz, refConstant);

    // Assert
    verify(refConstant).referencedClassAccept(isA(ClassVisitor.class));
  }

  /**
   * Test {@link SimpleEnumUseChecker#visitAnyRefConstant(Clazz, RefConstant)}.
   *
   * <ul>
   *   <li>When {@link LibraryClass} {@link LibraryClass#accept(ClassVisitor)} does nothing.
   *   <li>Then calls {@link LibraryClass#accept(ClassVisitor)}.
   * </ul>
   *
   * <p>Method under test: {@link SimpleEnumUseChecker#visitAnyRefConstant(Clazz, RefConstant)}
   */
  @Test
  @DisplayName(
      "Test visitAnyRefConstant(Clazz, RefConstant); when LibraryClass accept(ClassVisitor) does nothing; then calls accept(ClassVisitor)")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void SimpleEnumUseChecker.visitAnyRefConstant(Clazz, RefConstant)"})
  void testVisitAnyRefConstant_whenLibraryClassAcceptDoesNothing_thenCallsAccept() {
    // Arrange
    SimpleEnumUseChecker simpleEnumUseChecker = new SimpleEnumUseChecker();
    LibraryClass clazz = new LibraryClass();
    LibraryClass referencedClass = mock(LibraryClass.class);
    doNothing().when(referencedClass).accept(Mockito.<ClassVisitor>any());

    // Act
    simpleEnumUseChecker.visitAnyRefConstant(
        clazz,
        new FieldrefConstant(1, 1, referencedClass, new LibraryField(1, "Name", "Descriptor")));

    // Assert
    verify(referencedClass).accept(isA(ClassVisitor.class));
  }

  /**
   * Test {@link SimpleEnumUseChecker#visitClassConstant(Clazz, ClassConstant)}.
   *
   * <ul>
   *   <li>Then calls {@link ClassConstant#referencedClassAccept(ClassVisitor)}.
   * </ul>
   *
   * <p>Method under test: {@link SimpleEnumUseChecker#visitClassConstant(Clazz, ClassConstant)}
   */
  @Test
  @DisplayName(
      "Test visitClassConstant(Clazz, ClassConstant); then calls referencedClassAccept(ClassVisitor)")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void SimpleEnumUseChecker.visitClassConstant(Clazz, ClassConstant)"})
  void testVisitClassConstant_thenCallsReferencedClassAccept() {
    // Arrange
    SimpleEnumUseChecker simpleEnumUseChecker = new SimpleEnumUseChecker();
    LibraryClass clazz = new LibraryClass();
    ClassConstant classConstant = mock(ClassConstant.class);
    doNothing().when(classConstant).referencedClassAccept(Mockito.<ClassVisitor>any());

    // Act
    simpleEnumUseChecker.visitClassConstant(clazz, classConstant);

    // Assert
    verify(classConstant).referencedClassAccept(isA(ClassVisitor.class));
  }

  /**
   * Test {@link SimpleEnumUseChecker#visitClassConstant(Clazz, ClassConstant)}.
   *
   * <ul>
   *   <li>When {@link Clazz} {@link Clazz#accept(ClassVisitor)} does nothing.
   *   <li>Then calls {@link Clazz#accept(ClassVisitor)}.
   * </ul>
   *
   * <p>Method under test: {@link SimpleEnumUseChecker#visitClassConstant(Clazz, ClassConstant)}
   */
  @Test
  @DisplayName(
      "Test visitClassConstant(Clazz, ClassConstant); when Clazz accept(ClassVisitor) does nothing; then calls accept(ClassVisitor)")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void SimpleEnumUseChecker.visitClassConstant(Clazz, ClassConstant)"})
  void testVisitClassConstant_whenClazzAcceptDoesNothing_thenCallsAccept() {
    // Arrange
    SimpleEnumUseChecker simpleEnumUseChecker = new SimpleEnumUseChecker();
    LibraryClass clazz = new LibraryClass();
    Clazz referencedClass = mock(Clazz.class);
    doNothing().when(referencedClass).accept(Mockito.<ClassVisitor>any());

    // Act
    simpleEnumUseChecker.visitClassConstant(clazz, new ClassConstant(1, referencedClass));

    // Assert
    verify(referencedClass).accept(isA(ClassVisitor.class));
  }

  /**
   * Test {@link SimpleEnumUseChecker#visitConstantInstruction(Clazz, Method, CodeAttribute, int,
   * ConstantInstruction)}.
   *
   * <ul>
   *   <li>Then calls {@link ProgramClass#addSubClass(Clazz)}.
   * </ul>
   *
   * <p>Method under test: {@link SimpleEnumUseChecker#visitConstantInstruction(Clazz, Method,
   * CodeAttribute, int, ConstantInstruction)}
   */
  @Test
  @DisplayName(
      "Test visitConstantInstruction(Clazz, Method, CodeAttribute, int, ConstantInstruction); then calls addSubClass(Clazz)")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void SimpleEnumUseChecker.visitConstantInstruction(Clazz, Method, CodeAttribute, int, ConstantInstruction)"
  })
  void testVisitConstantInstruction_thenCallsAddSubClass() {
    // Arrange
    SimpleEnumUseChecker simpleEnumUseChecker = new SimpleEnumUseChecker();
    ProgramClass clazz = mock(ProgramClass.class);
    doNothing().when(clazz).addSubClass(Mockito.<Clazz>any());
    doNothing().when(clazz).constantPoolEntryAccept(anyInt(), Mockito.<ConstantVisitor>any());
    clazz.addSubClass(new LibraryClass());
    LibraryMethod method = new LibraryMethod(1, "Name", "Descriptor");

    CodeAttribute codeAttribute = new CodeAttribute(1);

    // Act
    simpleEnumUseChecker.visitConstantInstruction(
        clazz, method, codeAttribute, 2, new ConstantInstruction((byte) -77, 1));

    // Assert
    verify(clazz).addSubClass(isA(Clazz.class));
    verify(clazz).constantPoolEntryAccept(eq(1), isA(ConstantVisitor.class));
  }

  /**
   * Test {@link SimpleEnumUseChecker#visitConstantInstruction(Clazz, Method, CodeAttribute, int,
   * ConstantInstruction)}.
   *
   * <ul>
   *   <li>Then calls {@link ProgramClass#addSubClass(Clazz)}.
   * </ul>
   *
   * <p>Method under test: {@link SimpleEnumUseChecker#visitConstantInstruction(Clazz, Method,
   * CodeAttribute, int, ConstantInstruction)}
   */
  @Test
  @DisplayName(
      "Test visitConstantInstruction(Clazz, Method, CodeAttribute, int, ConstantInstruction); then calls addSubClass(Clazz)")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void SimpleEnumUseChecker.visitConstantInstruction(Clazz, Method, CodeAttribute, int, ConstantInstruction)"
  })
  void testVisitConstantInstruction_thenCallsAddSubClass2() {
    // Arrange
    SimpleEnumUseChecker simpleEnumUseChecker = new SimpleEnumUseChecker();
    ProgramClass clazz = mock(ProgramClass.class);
    doNothing().when(clazz).addSubClass(Mockito.<Clazz>any());
    doNothing().when(clazz).constantPoolEntryAccept(anyInt(), Mockito.<ConstantVisitor>any());
    clazz.addSubClass(new LibraryClass());
    LibraryMethod method = new LibraryMethod(1, "Name", "Descriptor");

    CodeAttribute codeAttribute = new CodeAttribute(1);

    // Act
    simpleEnumUseChecker.visitConstantInstruction(
        clazz, method, codeAttribute, 2, new ConstantInstruction((byte) -73, 1));

    // Assert
    verify(clazz).addSubClass(isA(Clazz.class));
    verify(clazz, atLeast(1)).constantPoolEntryAccept(eq(1), Mockito.<ConstantVisitor>any());
  }

  /**
   * Test {@link SimpleEnumUseChecker#visitProgramMethod(ProgramClass, ProgramMethod)}.
   *
   * <p>Method under test: {@link SimpleEnumUseChecker#visitProgramMethod(ProgramClass,
   * ProgramMethod)}
   */
  @Test
  @DisplayName("Test visitProgramMethod(ProgramClass, ProgramMethod)")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void SimpleEnumUseChecker.visitProgramMethod(ProgramClass, ProgramMethod)"})
  void testVisitProgramMethod() {
    // Arrange
    SimpleEnumUseChecker simpleEnumUseChecker = new SimpleEnumUseChecker();
    Constant constant = mock(Constant.class);
    doNothing().when(constant).addProcessingFlags((int[]) Mockito.any());
    constant.addProcessingFlags(2, 1, 2, 1);
    ProgramClass programClass =
        new ProgramClass(
            1,
            3,
            new Constant[] {constant},
            1,
            1,
            1,
            "Feature Name",
            1,
            new ClassOptimizationInfo());

    // Act
    simpleEnumUseChecker.visitProgramMethod(programClass, new ProgramMethod());

    // Assert
    verify(constant).addProcessingFlags((int[]) Mockito.any());
  }

  /**
   * Test {@link SimpleEnumUseChecker#visitProgramMethod(ProgramClass, ProgramMethod)}.
   *
   * <p>Method under test: {@link SimpleEnumUseChecker#visitProgramMethod(ProgramClass,
   * ProgramMethod)}
   */
  @Test
  @DisplayName("Test visitProgramMethod(ProgramClass, ProgramMethod)")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void SimpleEnumUseChecker.visitProgramMethod(ProgramClass, ProgramMethod)"})
  void testVisitProgramMethod2() {
    // Arrange
    SimpleEnumUseChecker simpleEnumUseChecker = new SimpleEnumUseChecker();
    Constant constant = mock(Constant.class);
    doNothing().when(constant).addProcessingFlags((int[]) Mockito.any());
    constant.addProcessingFlags(2, 1, 2, 1);
    ProgramClass programClass =
        new ProgramClass(
            1,
            3,
            new Constant[] {constant},
            1,
            1,
            1,
            "Feature Name",
            1,
            new ProgramClassOptimizationInfo());

    // Act
    simpleEnumUseChecker.visitProgramMethod(programClass, new ProgramMethod());

    // Assert
    verify(constant).addProcessingFlags((int[]) Mockito.any());
  }

  /**
   * Test {@link SimpleEnumUseChecker#visitProgramMethod(ProgramClass, ProgramMethod)}.
   *
   * <ul>
   *   <li>Given {@code false}.
   * </ul>
   *
   * <p>Method under test: {@link SimpleEnumUseChecker#visitProgramMethod(ProgramClass,
   * ProgramMethod)}
   */
  @Test
  @DisplayName("Test visitProgramMethod(ProgramClass, ProgramMethod); given 'false'")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void SimpleEnumUseChecker.visitProgramMethod(ProgramClass, ProgramMethod)"})
  void testVisitProgramMethod_givenFalse() {
    // Arrange
    SimpleEnumUseChecker simpleEnumUseChecker = new SimpleEnumUseChecker();
    Constant constant = mock(Constant.class);
    doNothing().when(constant).addProcessingFlags((int[]) Mockito.any());
    constant.addProcessingFlags(2, 1, 2, 1);
    ClassOptimizationInfo classOptimizationInfo = mock(ClassOptimizationInfo.class);
    when(classOptimizationInfo.isSimpleEnum()).thenReturn(false);
    ProgramClass programClass =
        new ProgramClass(
            1, 3, new Constant[] {constant}, 1, 1, 1, "Feature Name", 1, classOptimizationInfo);

    // Act
    simpleEnumUseChecker.visitProgramMethod(programClass, new ProgramMethod());

    // Assert
    verify(classOptimizationInfo).isSimpleEnum();
    verify(constant).addProcessingFlags((int[]) Mockito.any());
  }

  /**
   * Test {@link SimpleEnumUseChecker#visitProgramMethod(ProgramClass, ProgramMethod)}.
   *
   * <ul>
   *   <li>Given {@code String}.
   *   <li>When {@link ProgramClass} {@link ProgramClass#getString(int)} return {@code String}.
   * </ul>
   *
   * <p>Method under test: {@link SimpleEnumUseChecker#visitProgramMethod(ProgramClass,
   * ProgramMethod)}
   */
  @Test
  @DisplayName(
      "Test visitProgramMethod(ProgramClass, ProgramMethod); given 'String'; when ProgramClass getString(int) return 'String'")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void SimpleEnumUseChecker.visitProgramMethod(ProgramClass, ProgramMethod)"})
  void testVisitProgramMethod_givenString_whenProgramClassGetStringReturnString() {
    // Arrange
    SimpleEnumUseChecker simpleEnumUseChecker = new SimpleEnumUseChecker();
    ClassOptimizationInfo classOptimizationInfo = mock(ClassOptimizationInfo.class);
    when(classOptimizationInfo.isSimpleEnum()).thenReturn(true);
    ProgramClass programClass = mock(ProgramClass.class);
    when(programClass.getString(anyInt())).thenReturn("String");
    when(programClass.getProcessingInfo()).thenReturn(classOptimizationInfo);

    // Act
    simpleEnumUseChecker.visitProgramMethod(programClass, new ProgramMethod());

    // Assert
    verify(programClass, atLeast(1)).getString(eq(0));
    verify(classOptimizationInfo).isSimpleEnum();
    verify(programClass).getProcessingInfo();
  }

  /**
   * Test {@link SimpleEnumUseChecker#visitProgramMethod(ProgramClass, ProgramMethod)}.
   *
   * <ul>
   *   <li>When {@link ProgramClass} {@link ProgramClass#getString(int)} return {@code valueOf}.
   *   <li>Then calls {@link ProgramClass#getName()}.
   * </ul>
   *
   * <p>Method under test: {@link SimpleEnumUseChecker#visitProgramMethod(ProgramClass,
   * ProgramMethod)}
   */
  @Test
  @DisplayName(
      "Test visitProgramMethod(ProgramClass, ProgramMethod); when ProgramClass getString(int) return 'valueOf'; then calls getName()")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void SimpleEnumUseChecker.visitProgramMethod(ProgramClass, ProgramMethod)"})
  void testVisitProgramMethod_whenProgramClassGetStringReturnValueOf_thenCallsGetName() {
    // Arrange
    SimpleEnumUseChecker simpleEnumUseChecker = new SimpleEnumUseChecker();
    ClassOptimizationInfo classOptimizationInfo = mock(ClassOptimizationInfo.class);
    when(classOptimizationInfo.isSimpleEnum()).thenReturn(true);
    ProgramClass programClass = mock(ProgramClass.class);
    when(programClass.getName()).thenReturn("Name");
    when(programClass.getString(anyInt())).thenReturn("valueOf");
    when(programClass.getProcessingInfo()).thenReturn(classOptimizationInfo);

    // Act
    simpleEnumUseChecker.visitProgramMethod(programClass, new ProgramMethod());

    // Assert
    verify(programClass).getName();
    verify(programClass, atLeast(1)).getString(eq(0));
    verify(classOptimizationInfo).isSimpleEnum();
    verify(programClass, atLeast(1)).getProcessingInfo();
  }

  /**
   * Test {@link SimpleEnumUseChecker#visitProgramMethod(ProgramClass, ProgramMethod)}.
   *
   * <ul>
   *   <li>When {@link ProgramMethod} {@link ProgramMethod#getName(Clazz)} return {@code Name}.
   *   <li>Then calls {@link ProgramMethod#getDescriptor(Clazz)}.
   * </ul>
   *
   * <p>Method under test: {@link SimpleEnumUseChecker#visitProgramMethod(ProgramClass,
   * ProgramMethod)}
   */
  @Test
  @DisplayName(
      "Test visitProgramMethod(ProgramClass, ProgramMethod); when ProgramMethod getName(Clazz) return 'Name'; then calls getDescriptor(Clazz)")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void SimpleEnumUseChecker.visitProgramMethod(ProgramClass, ProgramMethod)"})
  void testVisitProgramMethod_whenProgramMethodGetNameReturnName_thenCallsGetDescriptor() {
    // Arrange
    SimpleEnumUseChecker simpleEnumUseChecker = new SimpleEnumUseChecker();
    ClassOptimizationInfo classOptimizationInfo = mock(ClassOptimizationInfo.class);
    when(classOptimizationInfo.isSimpleEnum()).thenReturn(true);
    ProgramClass programClass = mock(ProgramClass.class);
    when(programClass.getProcessingInfo()).thenReturn(classOptimizationInfo);
    ProgramMethod programMethod = mock(ProgramMethod.class);
    when(programMethod.getDescriptor(Mockito.<Clazz>any())).thenReturn("Descriptor");
    when(programMethod.getName(Mockito.<Clazz>any())).thenReturn("Name");

    // Act
    simpleEnumUseChecker.visitProgramMethod(programClass, programMethod);

    // Assert
    verify(programMethod).getDescriptor(isA(Clazz.class));
    verify(programMethod).getName(isA(Clazz.class));
    verify(classOptimizationInfo).isSimpleEnum();
    verify(programClass).getProcessingInfo();
  }

  /**
   * Test {@link SimpleEnumUseChecker#visitProgramMethod(ProgramClass, ProgramMethod)}.
   *
   * <ul>
   *   <li>When {@link ProgramMethod} {@link ProgramMethod#getName(Clazz)} return {@code valueOf}.
   *   <li>Then calls {@link ProgramClass#getName()}.
   * </ul>
   *
   * <p>Method under test: {@link SimpleEnumUseChecker#visitProgramMethod(ProgramClass,
   * ProgramMethod)}
   */
  @Test
  @DisplayName(
      "Test visitProgramMethod(ProgramClass, ProgramMethod); when ProgramMethod getName(Clazz) return 'valueOf'; then calls getName()")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void SimpleEnumUseChecker.visitProgramMethod(ProgramClass, ProgramMethod)"})
  void testVisitProgramMethod_whenProgramMethodGetNameReturnValueOf_thenCallsGetName() {
    // Arrange
    SimpleEnumUseChecker simpleEnumUseChecker = new SimpleEnumUseChecker();
    ClassOptimizationInfo classOptimizationInfo = mock(ClassOptimizationInfo.class);
    when(classOptimizationInfo.isSimpleEnum()).thenReturn(true);
    ProgramClass programClass = mock(ProgramClass.class);
    when(programClass.getName()).thenReturn("Name");
    when(programClass.getProcessingInfo()).thenReturn(classOptimizationInfo);
    ProgramMethod programMethod = mock(ProgramMethod.class);
    when(programMethod.getDescriptor(Mockito.<Clazz>any())).thenReturn("Descriptor");
    when(programMethod.getName(Mockito.<Clazz>any())).thenReturn("valueOf");

    // Act
    simpleEnumUseChecker.visitProgramMethod(programClass, programMethod);

    // Assert
    verify(programClass).getName();
    verify(programMethod, atLeast(1)).getDescriptor(isA(Clazz.class));
    verify(programMethod, atLeast(1)).getName(isA(Clazz.class));
    verify(classOptimizationInfo).isSimpleEnum();
    verify(programClass, atLeast(1)).getProcessingInfo();
  }
}
