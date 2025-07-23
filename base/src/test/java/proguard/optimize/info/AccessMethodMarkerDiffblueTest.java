package proguard.optimize.info;

import static org.junit.jupiter.api.Assertions.assertTrue;
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
import proguard.classfile.Member;
import proguard.classfile.Method;
import proguard.classfile.ProgramClass;
import proguard.classfile.attribute.CodeAttribute;
import proguard.classfile.constant.ClassConstant;
import proguard.classfile.constant.Constant;
import proguard.classfile.constant.DoubleConstant;
import proguard.classfile.constant.DynamicConstant;
import proguard.classfile.constant.FieldrefConstant;
import proguard.classfile.constant.FloatConstant;
import proguard.classfile.constant.IntegerConstant;
import proguard.classfile.constant.InvokeDynamicConstant;
import proguard.classfile.constant.LongConstant;
import proguard.classfile.constant.MethodHandleConstant;
import proguard.classfile.constant.MethodTypeConstant;
import proguard.classfile.constant.RefConstant;
import proguard.classfile.constant.StringConstant;
import proguard.classfile.constant.visitor.ConstantVisitor;
import proguard.classfile.instruction.ConstantInstruction;
import proguard.classfile.visitor.ClassVisitor;
import proguard.classfile.visitor.MemberVisitor;
import proguard.resources.file.ResourceFile;

class AccessMethodMarkerDiffblueTest {
  /**
   * Test {@link AccessMethodMarker#visitStringConstant(Clazz, StringConstant)}.
   *
   * <ul>
   *   <li>Given {@code 42}.
   *   <li>Then calls {@link LibraryField#accept(Clazz, MemberVisitor)}.
   * </ul>
   *
   * <p>Method under test: {@link AccessMethodMarker#visitStringConstant(Clazz, StringConstant)}
   */
  @Test
  @DisplayName(
      "Test visitStringConstant(Clazz, StringConstant); given '42'; then calls accept(Clazz, MemberVisitor)")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void AccessMethodMarker.visitStringConstant(Clazz, StringConstant)"})
  void testVisitStringConstant_given42_thenCallsAccept() {
    // Arrange
    AccessMethodMarker accessMethodMarker = new AccessMethodMarker();
    LibraryClass clazz = new LibraryClass();
    ResourceFile referencedResourceFile = mock(ResourceFile.class);
    doNothing().when(referencedResourceFile).addExtraFeatureName(Mockito.<String>any());
    referencedResourceFile.addExtraFeatureName("42");
    LibraryField libraryField = mock(LibraryField.class);
    doNothing().when(libraryField).accept(Mockito.<Clazz>any(), Mockito.<MemberVisitor>any());
    StringConstant stringConstant = new StringConstant(1, referencedResourceFile);

    stringConstant.referencedClass = new ProgramClass();
    stringConstant.referencedMember = libraryField;

    // Act
    accessMethodMarker.visitStringConstant(clazz, stringConstant);

    // Assert
    verify(libraryField).accept(isA(Clazz.class), isA(MemberVisitor.class));
    verify(referencedResourceFile).addExtraFeatureName(eq("42"));
  }

  /**
   * Test {@link AccessMethodMarker#visitStringConstant(Clazz, StringConstant)}.
   *
   * <ul>
   *   <li>Then calls {@link LibraryClass#accept(ClassVisitor)}.
   * </ul>
   *
   * <p>Method under test: {@link AccessMethodMarker#visitStringConstant(Clazz, StringConstant)}
   */
  @Test
  @DisplayName("Test visitStringConstant(Clazz, StringConstant); then calls accept(ClassVisitor)")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void AccessMethodMarker.visitStringConstant(Clazz, StringConstant)"})
  void testVisitStringConstant_thenCallsAccept() {
    // Arrange
    AccessMethodMarker accessMethodMarker = new AccessMethodMarker();
    LibraryClass clazz = new LibraryClass();
    LibraryClass libraryClass = mock(LibraryClass.class);
    doNothing().when(libraryClass).accept(Mockito.<ClassVisitor>any());
    StringConstant stringConstant = new StringConstant(1, new ResourceFile("foo.txt", 3L));

    stringConstant.referencedClass = libraryClass;
    stringConstant.referencedMember = null;

    // Act
    accessMethodMarker.visitStringConstant(clazz, stringConstant);

    // Assert
    verify(libraryClass).accept(isA(ClassVisitor.class));
  }

  /**
   * Test {@link AccessMethodMarker#visitStringConstant(Clazz, StringConstant)}.
   *
   * <ul>
   *   <li>Then calls {@link Clazz#constantPoolEntryAccept(int, ConstantVisitor)}.
   * </ul>
   *
   * <p>Method under test: {@link AccessMethodMarker#visitStringConstant(Clazz, StringConstant)}
   */
  @Test
  @DisplayName(
      "Test visitStringConstant(Clazz, StringConstant); then calls constantPoolEntryAccept(int, ConstantVisitor)")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void AccessMethodMarker.visitStringConstant(Clazz, StringConstant)"})
  void testVisitStringConstant_thenCallsConstantPoolEntryAccept() {
    // Arrange
    Clazz clazz = mock(Clazz.class);
    doNothing().when(clazz).constantPoolEntryAccept(anyInt(), Mockito.<ConstantVisitor>any());
    ProgramMethodOptimizationInfo programMethodOptimizationInfo =
        mock(ProgramMethodOptimizationInfo.class);
    doNothing().when(programMethodOptimizationInfo).setAccessesPackageCode();
    Method method = mock(Method.class);
    when(method.getProcessingInfo()).thenReturn(programMethodOptimizationInfo);

    AccessMethodMarker accessMethodMarker = new AccessMethodMarker();
    CodeAttribute codeAttribute = new CodeAttribute(1);
    accessMethodMarker.visitConstantInstruction(
        clazz, method, codeAttribute, 2, new ConstantInstruction((byte) 'A', 1));
    LibraryClass clazz2 = new LibraryClass();
    StringConstant stringConstant = new StringConstant(1, new ResourceFile("foo.txt", 3L));

    stringConstant.referencedClass = mock(LibraryClass.class);
    stringConstant.referencedMember = new LibraryField();

    // Act
    accessMethodMarker.visitStringConstant(clazz2, stringConstant);

    // Assert
    verify(clazz).constantPoolEntryAccept(eq(1), isA(ConstantVisitor.class));
    verify(programMethodOptimizationInfo).setAccessesPackageCode();
    verify(method, atLeast(1)).getProcessingInfo();
  }

  /**
   * Test {@link AccessMethodMarker#visitStringConstant(Clazz, StringConstant)}.
   *
   * <ul>
   *   <li>Then calls {@link StringConstant#referencedClassAccept(ClassVisitor)}.
   * </ul>
   *
   * <p>Method under test: {@link AccessMethodMarker#visitStringConstant(Clazz, StringConstant)}
   */
  @Test
  @DisplayName(
      "Test visitStringConstant(Clazz, StringConstant); then calls referencedClassAccept(ClassVisitor)")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void AccessMethodMarker.visitStringConstant(Clazz, StringConstant)"})
  void testVisitStringConstant_thenCallsReferencedClassAccept() {
    // Arrange
    AccessMethodMarker accessMethodMarker = new AccessMethodMarker();
    LibraryClass clazz = new LibraryClass();
    StringConstant stringConstant = mock(StringConstant.class);
    doNothing().when(stringConstant).referencedClassAccept(Mockito.<ClassVisitor>any());
    doNothing().when(stringConstant).referencedMemberAccept(Mockito.<MemberVisitor>any());

    // Act
    accessMethodMarker.visitStringConstant(clazz, stringConstant);

    // Assert
    verify(stringConstant).referencedClassAccept(isA(ClassVisitor.class));
    verify(stringConstant).referencedMemberAccept(isA(MemberVisitor.class));
  }

  /**
   * Test {@link AccessMethodMarker#visitDynamicConstant(Clazz, DynamicConstant)}.
   *
   * <ul>
   *   <li>Then calls {@link DynamicConstant#bootstrapMethodHandleAccept(Clazz, ConstantVisitor)}.
   * </ul>
   *
   * <p>Method under test: {@link AccessMethodMarker#visitDynamicConstant(Clazz, DynamicConstant)}
   */
  @Test
  @DisplayName(
      "Test visitDynamicConstant(Clazz, DynamicConstant); then calls bootstrapMethodHandleAccept(Clazz, ConstantVisitor)")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void AccessMethodMarker.visitDynamicConstant(Clazz, DynamicConstant)"})
  void testVisitDynamicConstant_thenCallsBootstrapMethodHandleAccept() {
    // Arrange
    AccessMethodMarker accessMethodMarker = new AccessMethodMarker();
    LibraryClass clazz = new LibraryClass();
    DynamicConstant dynamicConstant = mock(DynamicConstant.class);
    doNothing()
        .when(dynamicConstant)
        .bootstrapMethodHandleAccept(Mockito.<Clazz>any(), Mockito.<ConstantVisitor>any());

    // Act
    accessMethodMarker.visitDynamicConstant(clazz, dynamicConstant);

    // Assert
    verify(dynamicConstant)
        .bootstrapMethodHandleAccept(isA(Clazz.class), isA(ConstantVisitor.class));
  }

  /**
   * Test {@link AccessMethodMarker#visitInvokeDynamicConstant(Clazz, InvokeDynamicConstant)}.
   *
   * <ul>
   *   <li>Then calls {@link InvokeDynamicConstant#bootstrapMethodHandleAccept(Clazz,
   *       ConstantVisitor)}.
   * </ul>
   *
   * <p>Method under test: {@link AccessMethodMarker#visitInvokeDynamicConstant(Clazz,
   * InvokeDynamicConstant)}
   */
  @Test
  @DisplayName(
      "Test visitInvokeDynamicConstant(Clazz, InvokeDynamicConstant); then calls bootstrapMethodHandleAccept(Clazz, ConstantVisitor)")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void AccessMethodMarker.visitInvokeDynamicConstant(Clazz, InvokeDynamicConstant)"
  })
  void testVisitInvokeDynamicConstant_thenCallsBootstrapMethodHandleAccept() {
    // Arrange
    AccessMethodMarker accessMethodMarker = new AccessMethodMarker();
    LibraryClass clazz = new LibraryClass();
    InvokeDynamicConstant invokeDynamicConstant = mock(InvokeDynamicConstant.class);
    doNothing()
        .when(invokeDynamicConstant)
        .bootstrapMethodHandleAccept(Mockito.<Clazz>any(), Mockito.<ConstantVisitor>any());

    // Act
    accessMethodMarker.visitInvokeDynamicConstant(clazz, invokeDynamicConstant);

    // Assert
    verify(invokeDynamicConstant)
        .bootstrapMethodHandleAccept(isA(Clazz.class), isA(ConstantVisitor.class));
  }

  /**
   * Test {@link AccessMethodMarker#visitMethodHandleConstant(Clazz, MethodHandleConstant)}.
   *
   * <ul>
   *   <li>When {@link LibraryClass} {@link LibraryClass#accept(ClassVisitor)} does nothing.
   *   <li>Then calls {@link LibraryClass#accept(ClassVisitor)}.
   * </ul>
   *
   * <p>Method under test: {@link AccessMethodMarker#visitMethodHandleConstant(Clazz,
   * MethodHandleConstant)}
   */
  @Test
  @DisplayName(
      "Test visitMethodHandleConstant(Clazz, MethodHandleConstant); when LibraryClass accept(ClassVisitor) does nothing; then calls accept(ClassVisitor)")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void AccessMethodMarker.visitMethodHandleConstant(Clazz, MethodHandleConstant)"
  })
  void testVisitMethodHandleConstant_whenLibraryClassAcceptDoesNothing_thenCallsAccept() {
    // Arrange
    AccessMethodMarker accessMethodMarker = new AccessMethodMarker();
    LibraryClass referencedClass = mock(LibraryClass.class);
    doNothing().when(referencedClass).accept(Mockito.<ClassVisitor>any());
    ClassConstant classConstant = new ClassConstant(1, referencedClass);

    ProgramClass clazz =
        new ProgramClass(1, 3, new Constant[] {new ClassConstant(), classConstant}, 1, 1, 1);

    // Act
    accessMethodMarker.visitMethodHandleConstant(clazz, new MethodHandleConstant(1, 1));

    // Assert
    verify(referencedClass).accept(isA(ClassVisitor.class));
  }

  /**
   * Test {@link AccessMethodMarker#visitAnyRefConstant(Clazz, RefConstant)}.
   *
   * <p>Method under test: {@link AccessMethodMarker#visitAnyRefConstant(Clazz, RefConstant)}
   */
  @Test
  @DisplayName("Test visitAnyRefConstant(Clazz, RefConstant)")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void AccessMethodMarker.visitAnyRefConstant(Clazz, RefConstant)"})
  void testVisitAnyRefConstant() {
    // Arrange
    AccessMethodMarker accessMethodMarker = new AccessMethodMarker();
    ProgramClass clazz =
        new ProgramClass(
            1,
            3,
            new Constant[] {
              new ClassConstant(1, new LibraryClass(1, "This Class Name", "Super Class Name"))
            },
            1,
            1,
            1);

    FieldrefConstant refConstant = mock(FieldrefConstant.class);
    doNothing().when(refConstant).referencedMemberAccept(Mockito.<MemberVisitor>any());
    doNothing().when(refConstant).referencedClassAccept(Mockito.<ClassVisitor>any());

    // Act
    accessMethodMarker.visitAnyRefConstant(clazz, refConstant);

    // Assert
    verify(refConstant).referencedMemberAccept(isA(MemberVisitor.class));
    verify(refConstant).referencedClassAccept(isA(ClassVisitor.class));
  }

  /**
   * Test {@link AccessMethodMarker#visitAnyRefConstant(Clazz, RefConstant)}.
   *
   * <p>Method under test: {@link AccessMethodMarker#visitAnyRefConstant(Clazz, RefConstant)}
   */
  @Test
  @DisplayName("Test visitAnyRefConstant(Clazz, RefConstant)")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void AccessMethodMarker.visitAnyRefConstant(Clazz, RefConstant)"})
  void testVisitAnyRefConstant2() {
    // Arrange
    AccessMethodMarker accessMethodMarker = new AccessMethodMarker();
    ProgramClass clazz = new ProgramClass(1, 3, new Constant[] {new DynamicConstant()}, 1, 1, 1);

    FieldrefConstant refConstant = mock(FieldrefConstant.class);
    doNothing().when(refConstant).referencedMemberAccept(Mockito.<MemberVisitor>any());
    doNothing().when(refConstant).referencedClassAccept(Mockito.<ClassVisitor>any());

    // Act
    accessMethodMarker.visitAnyRefConstant(clazz, refConstant);

    // Assert
    verify(refConstant).referencedMemberAccept(isA(MemberVisitor.class));
    verify(refConstant).referencedClassAccept(isA(ClassVisitor.class));
  }

  /**
   * Test {@link AccessMethodMarker#visitAnyRefConstant(Clazz, RefConstant)}.
   *
   * <p>Method under test: {@link AccessMethodMarker#visitAnyRefConstant(Clazz, RefConstant)}
   */
  @Test
  @DisplayName("Test visitAnyRefConstant(Clazz, RefConstant)")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void AccessMethodMarker.visitAnyRefConstant(Clazz, RefConstant)"})
  void testVisitAnyRefConstant3() {
    // Arrange
    AccessMethodMarker accessMethodMarker = new AccessMethodMarker();
    ProgramClass clazz = new ProgramClass(1, 3, new Constant[] {new IntegerConstant(42)}, 1, 1, 1);

    FieldrefConstant refConstant = mock(FieldrefConstant.class);
    doNothing().when(refConstant).referencedMemberAccept(Mockito.<MemberVisitor>any());
    doNothing().when(refConstant).referencedClassAccept(Mockito.<ClassVisitor>any());

    // Act
    accessMethodMarker.visitAnyRefConstant(clazz, refConstant);

    // Assert
    verify(refConstant).referencedMemberAccept(isA(MemberVisitor.class));
    verify(refConstant).referencedClassAccept(isA(ClassVisitor.class));
  }

  /**
   * Test {@link AccessMethodMarker#visitAnyRefConstant(Clazz, RefConstant)}.
   *
   * <p>Method under test: {@link AccessMethodMarker#visitAnyRefConstant(Clazz, RefConstant)}
   */
  @Test
  @DisplayName("Test visitAnyRefConstant(Clazz, RefConstant)")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void AccessMethodMarker.visitAnyRefConstant(Clazz, RefConstant)"})
  void testVisitAnyRefConstant4() {
    // Arrange
    AccessMethodMarker accessMethodMarker = new AccessMethodMarker();
    ProgramClass clazz =
        new ProgramClass(1, 3, new Constant[] {new InvokeDynamicConstant()}, 1, 1, 1);

    FieldrefConstant refConstant = mock(FieldrefConstant.class);
    doNothing().when(refConstant).referencedMemberAccept(Mockito.<MemberVisitor>any());
    doNothing().when(refConstant).referencedClassAccept(Mockito.<ClassVisitor>any());

    // Act
    accessMethodMarker.visitAnyRefConstant(clazz, refConstant);

    // Assert
    verify(refConstant).referencedMemberAccept(isA(MemberVisitor.class));
    verify(refConstant).referencedClassAccept(isA(ClassVisitor.class));
  }

  /**
   * Test {@link AccessMethodMarker#visitAnyRefConstant(Clazz, RefConstant)}.
   *
   * <p>Method under test: {@link AccessMethodMarker#visitAnyRefConstant(Clazz, RefConstant)}
   */
  @Test
  @DisplayName("Test visitAnyRefConstant(Clazz, RefConstant)")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void AccessMethodMarker.visitAnyRefConstant(Clazz, RefConstant)"})
  void testVisitAnyRefConstant5() {
    // Arrange
    AccessMethodMarker accessMethodMarker = new AccessMethodMarker();
    ProgramClass clazz = new ProgramClass(1, 3, new Constant[] {new MethodTypeConstant()}, 1, 1, 1);

    FieldrefConstant refConstant = mock(FieldrefConstant.class);
    doNothing().when(refConstant).referencedMemberAccept(Mockito.<MemberVisitor>any());
    doNothing().when(refConstant).referencedClassAccept(Mockito.<ClassVisitor>any());

    // Act
    accessMethodMarker.visitAnyRefConstant(clazz, refConstant);

    // Assert
    verify(refConstant).referencedMemberAccept(isA(MemberVisitor.class));
    verify(refConstant).referencedClassAccept(isA(ClassVisitor.class));
  }

  /**
   * Test {@link AccessMethodMarker#visitAnyRefConstant(Clazz, RefConstant)}.
   *
   * <ul>
   *   <li>When array of {@link Constant} with {@link DoubleConstant#DoubleConstant(double)} with
   *       value is ten.
   * </ul>
   *
   * <p>Method under test: {@link AccessMethodMarker#visitAnyRefConstant(Clazz, RefConstant)}
   */
  @Test
  @DisplayName(
      "Test visitAnyRefConstant(Clazz, RefConstant); when array of Constant with DoubleConstant(double) with value is ten")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void AccessMethodMarker.visitAnyRefConstant(Clazz, RefConstant)"})
  void testVisitAnyRefConstant_whenArrayOfConstantWithDoubleConstantWithValueIsTen() {
    // Arrange
    AccessMethodMarker accessMethodMarker = new AccessMethodMarker();
    ProgramClass clazz =
        new ProgramClass(1, 3, new Constant[] {new DoubleConstant(10.0d)}, 1, 1, 1);

    FieldrefConstant refConstant = mock(FieldrefConstant.class);
    doNothing().when(refConstant).referencedMemberAccept(Mockito.<MemberVisitor>any());
    doNothing().when(refConstant).referencedClassAccept(Mockito.<ClassVisitor>any());

    // Act
    accessMethodMarker.visitAnyRefConstant(clazz, refConstant);

    // Assert
    verify(refConstant).referencedMemberAccept(isA(MemberVisitor.class));
    verify(refConstant).referencedClassAccept(isA(ClassVisitor.class));
  }

  /**
   * Test {@link AccessMethodMarker#visitAnyRefConstant(Clazz, RefConstant)}.
   *
   * <ul>
   *   <li>When array of {@link Constant} with {@link FloatConstant#FloatConstant(float)} with value
   *       is ten.
   * </ul>
   *
   * <p>Method under test: {@link AccessMethodMarker#visitAnyRefConstant(Clazz, RefConstant)}
   */
  @Test
  @DisplayName(
      "Test visitAnyRefConstant(Clazz, RefConstant); when array of Constant with FloatConstant(float) with value is ten")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void AccessMethodMarker.visitAnyRefConstant(Clazz, RefConstant)"})
  void testVisitAnyRefConstant_whenArrayOfConstantWithFloatConstantWithValueIsTen() {
    // Arrange
    AccessMethodMarker accessMethodMarker = new AccessMethodMarker();
    ProgramClass clazz = new ProgramClass(1, 3, new Constant[] {new FloatConstant(10.0f)}, 1, 1, 1);

    FieldrefConstant refConstant = mock(FieldrefConstant.class);
    doNothing().when(refConstant).referencedMemberAccept(Mockito.<MemberVisitor>any());
    doNothing().when(refConstant).referencedClassAccept(Mockito.<ClassVisitor>any());

    // Act
    accessMethodMarker.visitAnyRefConstant(clazz, refConstant);

    // Assert
    verify(refConstant).referencedMemberAccept(isA(MemberVisitor.class));
    verify(refConstant).referencedClassAccept(isA(ClassVisitor.class));
  }

  /**
   * Test {@link AccessMethodMarker#visitAnyRefConstant(Clazz, RefConstant)}.
   *
   * <ul>
   *   <li>When array of {@link Constant} with {@link LongConstant#LongConstant(long)} with value is
   *       forty-two.
   * </ul>
   *
   * <p>Method under test: {@link AccessMethodMarker#visitAnyRefConstant(Clazz, RefConstant)}
   */
  @Test
  @DisplayName(
      "Test visitAnyRefConstant(Clazz, RefConstant); when array of Constant with LongConstant(long) with value is forty-two")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void AccessMethodMarker.visitAnyRefConstant(Clazz, RefConstant)"})
  void testVisitAnyRefConstant_whenArrayOfConstantWithLongConstantWithValueIsFortyTwo() {
    // Arrange
    AccessMethodMarker accessMethodMarker = new AccessMethodMarker();
    ProgramClass clazz = new ProgramClass(1, 3, new Constant[] {new LongConstant(42L)}, 1, 1, 1);

    FieldrefConstant refConstant = mock(FieldrefConstant.class);
    doNothing().when(refConstant).referencedMemberAccept(Mockito.<MemberVisitor>any());
    doNothing().when(refConstant).referencedClassAccept(Mockito.<ClassVisitor>any());

    // Act
    accessMethodMarker.visitAnyRefConstant(clazz, refConstant);

    // Assert
    verify(refConstant).referencedMemberAccept(isA(MemberVisitor.class));
    verify(refConstant).referencedClassAccept(isA(ClassVisitor.class));
  }

  /**
   * Test {@link AccessMethodMarker#visitAnyRefConstant(Clazz, RefConstant)}.
   *
   * <ul>
   *   <li>When {@link LibraryClass} {@link LibraryClass#accept(ClassVisitor)} does nothing.
   *   <li>Then calls {@link LibraryClass#accept(ClassVisitor)}.
   * </ul>
   *
   * <p>Method under test: {@link AccessMethodMarker#visitAnyRefConstant(Clazz, RefConstant)}
   */
  @Test
  @DisplayName(
      "Test visitAnyRefConstant(Clazz, RefConstant); when LibraryClass accept(ClassVisitor) does nothing; then calls accept(ClassVisitor)")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void AccessMethodMarker.visitAnyRefConstant(Clazz, RefConstant)"})
  void testVisitAnyRefConstant_whenLibraryClassAcceptDoesNothing_thenCallsAccept() {
    // Arrange
    AccessMethodMarker accessMethodMarker = new AccessMethodMarker();
    LibraryClass referencedClass = mock(LibraryClass.class);
    doNothing().when(referencedClass).accept(Mockito.<ClassVisitor>any());
    ProgramClass clazz =
        new ProgramClass(1, 3, new Constant[] {new ClassConstant(1, referencedClass)}, 1, 1, 1);

    FieldrefConstant refConstant = mock(FieldrefConstant.class);
    doNothing().when(refConstant).referencedMemberAccept(Mockito.<MemberVisitor>any());
    doNothing().when(refConstant).referencedClassAccept(Mockito.<ClassVisitor>any());

    // Act
    accessMethodMarker.visitAnyRefConstant(clazz, refConstant);

    // Assert
    verify(referencedClass).accept(isA(ClassVisitor.class));
    verify(refConstant).referencedMemberAccept(isA(MemberVisitor.class));
    verify(refConstant).referencedClassAccept(isA(ClassVisitor.class));
  }

  /**
   * Test {@link AccessMethodMarker#visitAnyRefConstant(Clazz, RefConstant)}.
   *
   * <ul>
   *   <li>When {@link LibraryClass#LibraryClass()}.
   *   <li>Then calls {@link FieldrefConstant#referencedMemberAccept(MemberVisitor)}.
   * </ul>
   *
   * <p>Method under test: {@link AccessMethodMarker#visitAnyRefConstant(Clazz, RefConstant)}
   */
  @Test
  @DisplayName(
      "Test visitAnyRefConstant(Clazz, RefConstant); when LibraryClass(); then calls referencedMemberAccept(MemberVisitor)")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void AccessMethodMarker.visitAnyRefConstant(Clazz, RefConstant)"})
  void testVisitAnyRefConstant_whenLibraryClass_thenCallsReferencedMemberAccept() {
    // Arrange
    AccessMethodMarker accessMethodMarker = new AccessMethodMarker();
    LibraryClass clazz = new LibraryClass();
    FieldrefConstant refConstant = mock(FieldrefConstant.class);
    doNothing().when(refConstant).referencedMemberAccept(Mockito.<MemberVisitor>any());
    doNothing().when(refConstant).referencedClassAccept(Mockito.<ClassVisitor>any());

    // Act
    accessMethodMarker.visitAnyRefConstant(clazz, refConstant);

    // Assert
    verify(refConstant).referencedMemberAccept(isA(MemberVisitor.class));
    verify(refConstant).referencedClassAccept(isA(ClassVisitor.class));
  }

  /**
   * Test {@link AccessMethodMarker#visitClassConstant(Clazz, ClassConstant)}.
   *
   * <ul>
   *   <li>Then calls {@link Clazz#constantPoolEntryAccept(int, ConstantVisitor)}.
   * </ul>
   *
   * <p>Method under test: {@link AccessMethodMarker#visitClassConstant(Clazz, ClassConstant)}
   */
  @Test
  @DisplayName(
      "Test visitClassConstant(Clazz, ClassConstant); then calls constantPoolEntryAccept(int, ConstantVisitor)")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void AccessMethodMarker.visitClassConstant(Clazz, ClassConstant)"})
  void testVisitClassConstant_thenCallsConstantPoolEntryAccept() {
    // Arrange
    Clazz clazz = mock(Clazz.class);
    doNothing().when(clazz).constantPoolEntryAccept(anyInt(), Mockito.<ConstantVisitor>any());
    ProgramMethodOptimizationInfo programMethodOptimizationInfo =
        mock(ProgramMethodOptimizationInfo.class);
    doNothing().when(programMethodOptimizationInfo).setAccessesPackageCode();
    Method method = mock(Method.class);
    when(method.getProcessingInfo()).thenReturn(programMethodOptimizationInfo);

    AccessMethodMarker accessMethodMarker = new AccessMethodMarker();
    CodeAttribute codeAttribute = new CodeAttribute(1);
    accessMethodMarker.visitConstantInstruction(
        clazz, method, codeAttribute, 2, new ConstantInstruction((byte) 'A', 1));
    LibraryClass clazz2 = new LibraryClass();

    // Act
    accessMethodMarker.visitClassConstant(clazz2, new ClassConstant(1, new LibraryClass()));

    // Assert
    verify(clazz).constantPoolEntryAccept(eq(1), isA(ConstantVisitor.class));
    verify(programMethodOptimizationInfo).setAccessesPackageCode();
    verify(method, atLeast(1)).getProcessingInfo();
  }

  /**
   * Test {@link AccessMethodMarker#visitClassConstant(Clazz, ClassConstant)}.
   *
   * <ul>
   *   <li>Then calls {@link ClassConstant#referencedClassAccept(ClassVisitor)}.
   * </ul>
   *
   * <p>Method under test: {@link AccessMethodMarker#visitClassConstant(Clazz, ClassConstant)}
   */
  @Test
  @DisplayName(
      "Test visitClassConstant(Clazz, ClassConstant); then calls referencedClassAccept(ClassVisitor)")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void AccessMethodMarker.visitClassConstant(Clazz, ClassConstant)"})
  void testVisitClassConstant_thenCallsReferencedClassAccept() {
    // Arrange
    AccessMethodMarker accessMethodMarker = new AccessMethodMarker();
    LibraryClass clazz = new LibraryClass();
    ClassConstant classConstant = mock(ClassConstant.class);
    doNothing().when(classConstant).referencedClassAccept(Mockito.<ClassVisitor>any());

    // Act
    accessMethodMarker.visitClassConstant(clazz, classConstant);

    // Assert
    verify(classConstant).referencedClassAccept(isA(ClassVisitor.class));
  }

  /**
   * Test {@link AccessMethodMarker#visitClassConstant(Clazz, ClassConstant)}.
   *
   * <ul>
   *   <li>When {@link LibraryClass} {@link LibraryClass#accept(ClassVisitor)} does nothing.
   *   <li>Then calls {@link LibraryClass#accept(ClassVisitor)}.
   * </ul>
   *
   * <p>Method under test: {@link AccessMethodMarker#visitClassConstant(Clazz, ClassConstant)}
   */
  @Test
  @DisplayName(
      "Test visitClassConstant(Clazz, ClassConstant); when LibraryClass accept(ClassVisitor) does nothing; then calls accept(ClassVisitor)")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void AccessMethodMarker.visitClassConstant(Clazz, ClassConstant)"})
  void testVisitClassConstant_whenLibraryClassAcceptDoesNothing_thenCallsAccept() {
    // Arrange
    AccessMethodMarker accessMethodMarker = new AccessMethodMarker();
    LibraryClass clazz = new LibraryClass();
    LibraryClass referencedClass = mock(LibraryClass.class);
    doNothing().when(referencedClass).accept(Mockito.<ClassVisitor>any());

    // Act
    accessMethodMarker.visitClassConstant(clazz, new ClassConstant(1, referencedClass));

    // Assert
    verify(referencedClass).accept(isA(ClassVisitor.class));
  }

  /**
   * Test {@link AccessMethodMarker#visitAnyClass(Clazz)}.
   *
   * <ul>
   *   <li>Then calls {@link Clazz#constantPoolEntryAccept(int, ConstantVisitor)}.
   * </ul>
   *
   * <p>Method under test: {@link AccessMethodMarker#visitAnyClass(Clazz)}
   */
  @Test
  @DisplayName(
      "Test visitAnyClass(Clazz); then calls constantPoolEntryAccept(int, ConstantVisitor)")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void AccessMethodMarker.visitAnyClass(Clazz)"})
  void testVisitAnyClass_thenCallsConstantPoolEntryAccept() {
    // Arrange
    Clazz clazz = mock(Clazz.class);
    doNothing().when(clazz).constantPoolEntryAccept(anyInt(), Mockito.<ConstantVisitor>any());
    ProgramMethodOptimizationInfo programMethodOptimizationInfo =
        mock(ProgramMethodOptimizationInfo.class);
    doNothing().when(programMethodOptimizationInfo).setAccessesPackageCode();
    Method method = mock(Method.class);
    when(method.getProcessingInfo()).thenReturn(programMethodOptimizationInfo);

    AccessMethodMarker accessMethodMarker = new AccessMethodMarker();
    CodeAttribute codeAttribute = new CodeAttribute(1);
    accessMethodMarker.visitConstantInstruction(
        clazz, method, codeAttribute, 2, new ConstantInstruction((byte) 'A', 1));

    // Act
    accessMethodMarker.visitAnyClass(new LibraryClass());

    // Assert
    verify(clazz).constantPoolEntryAccept(eq(1), isA(ConstantVisitor.class));
    verify(programMethodOptimizationInfo).setAccessesPackageCode();
    verify(method, atLeast(1)).getProcessingInfo();
  }

  /**
   * Test {@link AccessMethodMarker#visitAnyMember(Clazz, Member)}.
   *
   * <ul>
   *   <li>Then calls {@link ProgramMethodOptimizationInfo#setAccessesPackageCode()}.
   * </ul>
   *
   * <p>Method under test: {@link AccessMethodMarker#visitAnyMember(Clazz, Member)}
   */
  @Test
  @DisplayName("Test visitAnyMember(Clazz, Member); then calls setAccessesPackageCode()")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void AccessMethodMarker.visitAnyMember(Clazz, Member)"})
  void testVisitAnyMember_thenCallsSetAccessesPackageCode() {
    // Arrange
    Clazz clazz = mock(Clazz.class);
    doNothing().when(clazz).constantPoolEntryAccept(anyInt(), Mockito.<ConstantVisitor>any());
    ProgramMethodOptimizationInfo programMethodOptimizationInfo =
        mock(ProgramMethodOptimizationInfo.class);
    doNothing().when(programMethodOptimizationInfo).setAccessesPackageCode();
    Method method = mock(Method.class);
    when(method.getProcessingInfo()).thenReturn(programMethodOptimizationInfo);

    AccessMethodMarker accessMethodMarker = new AccessMethodMarker();
    CodeAttribute codeAttribute = new CodeAttribute(1);
    accessMethodMarker.visitConstantInstruction(
        clazz, method, codeAttribute, 2, new ConstantInstruction((byte) 'A', 1));
    LibraryClass clazz2 = new LibraryClass();

    // Act
    accessMethodMarker.visitAnyMember(clazz2, new LibraryField(0, "Name", "Descriptor"));

    // Assert
    verify(clazz).constantPoolEntryAccept(eq(1), isA(ConstantVisitor.class));
    verify(programMethodOptimizationInfo).setAccessesPackageCode();
    verify(method, atLeast(1)).getProcessingInfo();
  }

  /**
   * Test {@link AccessMethodMarker#visitAnyMember(Clazz, Member)}.
   *
   * <ul>
   *   <li>Then calls {@link ProgramMethodOptimizationInfo#setAccessesPrivateCode()}.
   * </ul>
   *
   * <p>Method under test: {@link AccessMethodMarker#visitAnyMember(Clazz, Member)}
   */
  @Test
  @DisplayName("Test visitAnyMember(Clazz, Member); then calls setAccessesPrivateCode()")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void AccessMethodMarker.visitAnyMember(Clazz, Member)"})
  void testVisitAnyMember_thenCallsSetAccessesPrivateCode() {
    // Arrange
    Clazz clazz = mock(Clazz.class);
    doNothing().when(clazz).constantPoolEntryAccept(anyInt(), Mockito.<ConstantVisitor>any());
    ProgramMethodOptimizationInfo programMethodOptimizationInfo =
        mock(ProgramMethodOptimizationInfo.class);
    doNothing().when(programMethodOptimizationInfo).setAccessesPrivateCode();
    Method method = mock(Method.class);
    when(method.getProcessingInfo()).thenReturn(programMethodOptimizationInfo);

    AccessMethodMarker accessMethodMarker = new AccessMethodMarker();
    CodeAttribute codeAttribute = new CodeAttribute(1);
    accessMethodMarker.visitConstantInstruction(
        clazz, method, codeAttribute, 2, new ConstantInstruction((byte) 'A', 1));
    LibraryClass clazz2 = new LibraryClass();

    // Act
    accessMethodMarker.visitAnyMember(clazz2, new LibraryField(2, "Name", "Descriptor"));

    // Assert
    verify(clazz).constantPoolEntryAccept(eq(1), isA(ConstantVisitor.class));
    verify(programMethodOptimizationInfo).setAccessesPrivateCode();
    verify(method, atLeast(1)).getProcessingInfo();
  }

  /**
   * Test {@link AccessMethodMarker#visitAnyMember(Clazz, Member)}.
   *
   * <ul>
   *   <li>Then calls {@link ProgramMethodOptimizationInfo#setAccessesProtectedCode()}.
   * </ul>
   *
   * <p>Method under test: {@link AccessMethodMarker#visitAnyMember(Clazz, Member)}
   */
  @Test
  @DisplayName("Test visitAnyMember(Clazz, Member); then calls setAccessesProtectedCode()")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void AccessMethodMarker.visitAnyMember(Clazz, Member)"})
  void testVisitAnyMember_thenCallsSetAccessesProtectedCode() {
    // Arrange
    Clazz clazz = mock(Clazz.class);
    doNothing().when(clazz).constantPoolEntryAccept(anyInt(), Mockito.<ConstantVisitor>any());
    ProgramMethodOptimizationInfo programMethodOptimizationInfo =
        mock(ProgramMethodOptimizationInfo.class);
    doNothing().when(programMethodOptimizationInfo).setAccessesProtectedCode();
    Method method = mock(Method.class);
    when(method.getProcessingInfo()).thenReturn(programMethodOptimizationInfo);

    AccessMethodMarker accessMethodMarker = new AccessMethodMarker();
    CodeAttribute codeAttribute = new CodeAttribute(1);
    accessMethodMarker.visitConstantInstruction(
        clazz, method, codeAttribute, 2, new ConstantInstruction((byte) 'A', 1));
    LibraryClass clazz2 = new LibraryClass();

    // Act
    accessMethodMarker.visitAnyMember(clazz2, new LibraryField(4, "Name", "Descriptor"));

    // Assert
    verify(clazz).constantPoolEntryAccept(eq(1), isA(ConstantVisitor.class));
    verify(programMethodOptimizationInfo).setAccessesProtectedCode();
    verify(method, atLeast(1)).getProcessingInfo();
  }

  /**
   * Test {@link AccessMethodMarker#accessesPrivateCode(Method)}.
   *
   * <ul>
   *   <li>Given {@link MethodOptimizationInfo} (default constructor).
   *   <li>Then return {@code true}.
   * </ul>
   *
   * <p>Method under test: {@link AccessMethodMarker#accessesPrivateCode(Method)}
   */
  @Test
  @DisplayName(
      "Test accessesPrivateCode(Method); given MethodOptimizationInfo (default constructor); then return 'true'")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean AccessMethodMarker.accessesPrivateCode(Method)"})
  void testAccessesPrivateCode_givenMethodOptimizationInfo_thenReturnTrue() {
    // Arrange
    LibraryMethod method = new LibraryMethod(1, "Name", "Descriptor");
    method.setProcessingInfo(new MethodOptimizationInfo());

    // Act and Assert
    assertTrue(AccessMethodMarker.accessesPrivateCode(method));
  }

  /**
   * Test {@link AccessMethodMarker#accessesPackageCode(Method)}.
   *
   * <ul>
   *   <li>Given {@link MethodOptimizationInfo} (default constructor).
   *   <li>Then return {@code true}.
   * </ul>
   *
   * <p>Method under test: {@link AccessMethodMarker#accessesPackageCode(Method)}
   */
  @Test
  @DisplayName(
      "Test accessesPackageCode(Method); given MethodOptimizationInfo (default constructor); then return 'true'")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean AccessMethodMarker.accessesPackageCode(Method)"})
  void testAccessesPackageCode_givenMethodOptimizationInfo_thenReturnTrue() {
    // Arrange
    LibraryMethod method = new LibraryMethod(1, "Name", "Descriptor");
    method.setProcessingInfo(new MethodOptimizationInfo());

    // Act and Assert
    assertTrue(AccessMethodMarker.accessesPackageCode(method));
  }

  /**
   * Test {@link AccessMethodMarker#accessesProtectedCode(Method)}.
   *
   * <ul>
   *   <li>Given {@link MethodOptimizationInfo} (default constructor).
   *   <li>Then return {@code true}.
   * </ul>
   *
   * <p>Method under test: {@link AccessMethodMarker#accessesProtectedCode(Method)}
   */
  @Test
  @DisplayName(
      "Test accessesProtectedCode(Method); given MethodOptimizationInfo (default constructor); then return 'true'")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean AccessMethodMarker.accessesProtectedCode(Method)"})
  void testAccessesProtectedCode_givenMethodOptimizationInfo_thenReturnTrue() {
    // Arrange
    LibraryMethod method = new LibraryMethod(1, "Name", "Descriptor");
    method.setProcessingInfo(new MethodOptimizationInfo());

    // Act and Assert
    assertTrue(AccessMethodMarker.accessesProtectedCode(method));
  }
}
