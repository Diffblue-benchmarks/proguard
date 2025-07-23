package proguard.shrink;

import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
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
import proguard.classfile.ProgramClass;
import proguard.classfile.constant.ClassConstant;
import proguard.classfile.constant.Utf8Constant;
import proguard.classfile.constant.visitor.ConstantVisitor;
import proguard.util.Processable;

class InterfaceUsageMarkerDiffblueTest {
  /**
   * Test {@link InterfaceUsageMarker#visitAnyClass(Clazz)}.
   *
   * <ul>
   *   <li>When {@link LibraryClass#LibraryClass()}.
   *   <li>Then throw {@link UnsupportedOperationException}.
   * </ul>
   *
   * <p>Method under test: {@link InterfaceUsageMarker#visitAnyClass(Clazz)}
   */
  @Test
  @DisplayName(
      "Test visitAnyClass(Clazz); when LibraryClass(); then throw UnsupportedOperationException")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void InterfaceUsageMarker.visitAnyClass(Clazz)"})
  void testVisitAnyClass_whenLibraryClass_thenThrowUnsupportedOperationException() {
    // Arrange
    InterfaceUsageMarker interfaceUsageMarker = new InterfaceUsageMarker(new ClassUsageMarker());

    // Act and Assert
    assertThrows(
        UnsupportedOperationException.class,
        () -> interfaceUsageMarker.visitAnyClass(new LibraryClass()));
  }

  /**
   * Test {@link InterfaceUsageMarker#visitProgramClass(ProgramClass)}.
   *
   * <p>Method under test: {@link InterfaceUsageMarker#visitProgramClass(ProgramClass)}
   */
  @Test
  @DisplayName("Test visitProgramClass(ProgramClass)")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void InterfaceUsageMarker.visitProgramClass(ProgramClass)"})
  void testVisitProgramClass() {
    // Arrange
    ClassUsageMarker classUsageMarker = mock(ClassUsageMarker.class);
    doThrow(new UnsupportedOperationException("foo"))
        .when(classUsageMarker)
        .markAsUsed(Mockito.<Processable>any());
    when(classUsageMarker.isPossiblyUsed(Mockito.<Processable>any())).thenReturn(true);
    when(classUsageMarker.isUsed(Mockito.<Processable>any())).thenReturn(true);
    InterfaceUsageMarker interfaceUsageMarker = new InterfaceUsageMarker(classUsageMarker);

    // Act and Assert
    assertThrows(
        UnsupportedOperationException.class,
        () -> interfaceUsageMarker.visitProgramClass(new ProgramClass()));
    verify(classUsageMarker).isPossiblyUsed(isA(Processable.class));
    verify(classUsageMarker).isUsed(isA(Processable.class));
    verify(classUsageMarker).markAsUsed(isA(Processable.class));
  }

  /**
   * Test {@link InterfaceUsageMarker#visitProgramClass(ProgramClass)}.
   *
   * <p>Method under test: {@link InterfaceUsageMarker#visitProgramClass(ProgramClass)}
   */
  @Test
  @DisplayName("Test visitProgramClass(ProgramClass)")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void InterfaceUsageMarker.visitProgramClass(ProgramClass)"})
  void testVisitProgramClass2() {
    // Arrange
    ClassUsageMarker classUsageMarker = mock(ClassUsageMarker.class);
    doThrow(new UnsupportedOperationException("foo"))
        .when(classUsageMarker)
        .markAsUnused(Mockito.<Processable>any());
    when(classUsageMarker.isPossiblyUsed(Mockito.<Processable>any())).thenReturn(true);
    when(classUsageMarker.isUsed(Mockito.<Processable>any())).thenReturn(false);
    InterfaceUsageMarker interfaceUsageMarker = new InterfaceUsageMarker(classUsageMarker);
    ProgramClass programClass = mock(ProgramClass.class);
    doNothing().when(programClass).interfaceConstantsAccept(Mockito.<ConstantVisitor>any());

    // Act and Assert
    assertThrows(
        UnsupportedOperationException.class,
        () -> interfaceUsageMarker.visitProgramClass(programClass));
    verify(programClass).interfaceConstantsAccept(isA(ConstantVisitor.class));
    verify(classUsageMarker).isPossiblyUsed(isA(Processable.class));
    verify(classUsageMarker).isUsed(isA(Processable.class));
    verify(classUsageMarker).markAsUnused(isA(Processable.class));
  }

  /**
   * Test {@link InterfaceUsageMarker#visitProgramClass(ProgramClass)}.
   *
   * <ul>
   *   <li>Given {@link ClassUsageMarker} {@link ClassUsageMarker#isPossiblyUsed(Processable)}
   *       return {@code false}.
   * </ul>
   *
   * <p>Method under test: {@link InterfaceUsageMarker#visitProgramClass(ProgramClass)}
   */
  @Test
  @DisplayName(
      "Test visitProgramClass(ProgramClass); given ClassUsageMarker isPossiblyUsed(Processable) return 'false'")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void InterfaceUsageMarker.visitProgramClass(ProgramClass)"})
  void testVisitProgramClass_givenClassUsageMarkerIsPossiblyUsedReturnFalse() {
    // Arrange
    ClassUsageMarker classUsageMarker = mock(ClassUsageMarker.class);
    when(classUsageMarker.isPossiblyUsed(Mockito.<Processable>any())).thenReturn(false);
    when(classUsageMarker.isUsed(Mockito.<Processable>any())).thenReturn(true);
    InterfaceUsageMarker interfaceUsageMarker = new InterfaceUsageMarker(classUsageMarker);
    ProgramClass programClass = mock(ProgramClass.class);
    doNothing().when(programClass).interfaceConstantsAccept(Mockito.<ConstantVisitor>any());

    // Act
    interfaceUsageMarker.visitProgramClass(programClass);

    // Assert
    verify(programClass).interfaceConstantsAccept(isA(ConstantVisitor.class));
    verify(classUsageMarker).isPossiblyUsed(isA(Processable.class));
    verify(classUsageMarker).isUsed(isA(Processable.class));
  }

  /**
   * Test {@link InterfaceUsageMarker#visitProgramClass(ProgramClass)}.
   *
   * <ul>
   *   <li>Given {@link ClassUsageMarker} {@link ClassUsageMarker#markAsUnused(Processable)} does
   *       nothing.
   * </ul>
   *
   * <p>Method under test: {@link InterfaceUsageMarker#visitProgramClass(ProgramClass)}
   */
  @Test
  @DisplayName(
      "Test visitProgramClass(ProgramClass); given ClassUsageMarker markAsUnused(Processable) does nothing")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void InterfaceUsageMarker.visitProgramClass(ProgramClass)"})
  void testVisitProgramClass_givenClassUsageMarkerMarkAsUnusedDoesNothing() {
    // Arrange
    ClassUsageMarker classUsageMarker = mock(ClassUsageMarker.class);
    doNothing().when(classUsageMarker).markAsUnused(Mockito.<Processable>any());
    when(classUsageMarker.isPossiblyUsed(Mockito.<Processable>any())).thenReturn(true);
    when(classUsageMarker.isUsed(Mockito.<Processable>any())).thenReturn(false);
    InterfaceUsageMarker interfaceUsageMarker = new InterfaceUsageMarker(classUsageMarker);
    ProgramClass programClass = mock(ProgramClass.class);
    doNothing().when(programClass).interfaceConstantsAccept(Mockito.<ConstantVisitor>any());

    // Act
    interfaceUsageMarker.visitProgramClass(programClass);

    // Assert
    verify(programClass).interfaceConstantsAccept(isA(ConstantVisitor.class));
    verify(classUsageMarker).isPossiblyUsed(isA(Processable.class));
    verify(classUsageMarker).isUsed(isA(Processable.class));
    verify(classUsageMarker).markAsUnused(isA(Processable.class));
  }

  /**
   * Test {@link InterfaceUsageMarker#visitProgramClass(ProgramClass)}.
   *
   * <ul>
   *   <li>Given {@link UnsupportedOperationException#UnsupportedOperationException(String)} with
   *       {@code foo}.
   * </ul>
   *
   * <p>Method under test: {@link InterfaceUsageMarker#visitProgramClass(ProgramClass)}
   */
  @Test
  @DisplayName(
      "Test visitProgramClass(ProgramClass); given UnsupportedOperationException(String) with 'foo'")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void InterfaceUsageMarker.visitProgramClass(ProgramClass)"})
  void testVisitProgramClass_givenUnsupportedOperationExceptionWithFoo() {
    // Arrange
    ClassUsageMarker classUsageMarker = mock(ClassUsageMarker.class);
    when(classUsageMarker.isPossiblyUsed(Mockito.<Processable>any())).thenReturn(true);
    when(classUsageMarker.isUsed(Mockito.<Processable>any())).thenReturn(false);
    InterfaceUsageMarker interfaceUsageMarker = new InterfaceUsageMarker(classUsageMarker);
    ProgramClass programClass = mock(ProgramClass.class);
    doThrow(new UnsupportedOperationException("foo"))
        .when(programClass)
        .interfaceConstantsAccept(Mockito.<ConstantVisitor>any());

    // Act and Assert
    assertThrows(
        UnsupportedOperationException.class,
        () -> interfaceUsageMarker.visitProgramClass(programClass));
    verify(programClass).interfaceConstantsAccept(isA(ConstantVisitor.class));
    verify(classUsageMarker).isPossiblyUsed(isA(Processable.class));
    verify(classUsageMarker).isUsed(isA(Processable.class));
  }

  /**
   * Test {@link InterfaceUsageMarker#visitProgramClass(ProgramClass)}.
   *
   * <ul>
   *   <li>Then calls {@link ProgramClass#superClassConstantAccept(ConstantVisitor)}.
   * </ul>
   *
   * <p>Method under test: {@link InterfaceUsageMarker#visitProgramClass(ProgramClass)}
   */
  @Test
  @DisplayName(
      "Test visitProgramClass(ProgramClass); then calls superClassConstantAccept(ConstantVisitor)")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void InterfaceUsageMarker.visitProgramClass(ProgramClass)"})
  void testVisitProgramClass_thenCallsSuperClassConstantAccept() {
    // Arrange
    ClassUsageMarker classUsageMarker = mock(ClassUsageMarker.class);
    doNothing().when(classUsageMarker).markAsUsed(Mockito.<Processable>any());
    when(classUsageMarker.isPossiblyUsed(Mockito.<Processable>any())).thenReturn(true);
    when(classUsageMarker.isUsed(Mockito.<Processable>any())).thenReturn(true);
    InterfaceUsageMarker interfaceUsageMarker = new InterfaceUsageMarker(classUsageMarker);
    ProgramClass programClass = mock(ProgramClass.class);
    doNothing().when(programClass).interfaceConstantsAccept(Mockito.<ConstantVisitor>any());
    doNothing().when(programClass).superClassConstantAccept(Mockito.<ConstantVisitor>any());
    doNothing().when(programClass).thisClassConstantAccept(Mockito.<ConstantVisitor>any());

    // Act
    interfaceUsageMarker.visitProgramClass(programClass);

    // Assert
    verify(programClass).interfaceConstantsAccept(isA(ConstantVisitor.class));
    verify(programClass).superClassConstantAccept(isA(ConstantVisitor.class));
    verify(programClass).thisClassConstantAccept(isA(ConstantVisitor.class));
    verify(classUsageMarker).isPossiblyUsed(isA(Processable.class));
    verify(classUsageMarker).isUsed(isA(Processable.class));
    verify(classUsageMarker).markAsUsed(isA(Processable.class));
  }

  /**
   * Test {@link InterfaceUsageMarker#visitClassConstant(Clazz, ClassConstant)}.
   *
   * <p>Method under test: {@link InterfaceUsageMarker#visitClassConstant(Clazz, ClassConstant)}
   */
  @Test
  @DisplayName("Test visitClassConstant(Clazz, ClassConstant)")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void InterfaceUsageMarker.visitClassConstant(Clazz, ClassConstant)"})
  void testVisitClassConstant() {
    // Arrange
    ShortestUsageMarker usageMarker = new ShortestUsageMarker();
    InterfaceUsageMarker interfaceUsageMarker =
        new InterfaceUsageMarker(new ShortestClassUsageMarker(usageMarker, "Just cause"));
    LibraryClass clazz = new LibraryClass();
    ClassConstant classConstant = new ClassConstant(1, new LibraryClass());

    // Act
    interfaceUsageMarker.visitClassConstant(clazz, classConstant);

    // Assert
    ShortestUsageMark expectedProcessingInfo = usageMarker.currentUsageMark;
    assertSame(expectedProcessingInfo, classConstant.getProcessingInfo());
  }

  /**
   * Test {@link InterfaceUsageMarker#visitClassConstant(Clazz, ClassConstant)}.
   *
   * <p>Method under test: {@link InterfaceUsageMarker#visitClassConstant(Clazz, ClassConstant)}
   */
  @Test
  @DisplayName("Test visitClassConstant(Clazz, ClassConstant)")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void InterfaceUsageMarker.visitClassConstant(Clazz, ClassConstant)"})
  void testVisitClassConstant2() {
    // Arrange
    InterfaceUsageMarker interfaceUsageMarker =
        new InterfaceUsageMarker(
            new ShortestClassUsageMarker(new ShortestUsageMarker(), "Just cause"));
    LibraryClass clazz = new LibraryClass();
    ClassConstant classConstant = new ClassConstant(1, new ProgramClass());

    // Act
    interfaceUsageMarker.visitClassConstant(clazz, classConstant);

    // Assert that nothing has changed
    assertNull(classConstant.getProcessingInfo());
  }

  /**
   * Test {@link InterfaceUsageMarker#visitClassConstant(Clazz, ClassConstant)}.
   *
   * <p>Method under test: {@link InterfaceUsageMarker#visitClassConstant(Clazz, ClassConstant)}
   */
  @Test
  @DisplayName("Test visitClassConstant(Clazz, ClassConstant)")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void InterfaceUsageMarker.visitClassConstant(Clazz, ClassConstant)"})
  void testVisitClassConstant3() {
    // Arrange
    InterfaceUsageMarker interfaceUsageMarker = new InterfaceUsageMarker(new ClassUsageMarker());
    LibraryClass clazz = new LibraryClass();
    ClassConstant classConstant = new ClassConstant(1, new ProgramClass());

    // Act
    interfaceUsageMarker.visitClassConstant(clazz, classConstant);

    // Assert that nothing has changed
    assertNull(classConstant.getProcessingInfo());
  }

  /**
   * Test {@link InterfaceUsageMarker#visitClassConstant(Clazz, ClassConstant)}.
   *
   * <ul>
   *   <li>When {@link ClassConstant#ClassConstant()}.
   *   <li>Then {@link ClassConstant#ClassConstant()} ProcessingInfo is {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link InterfaceUsageMarker#visitClassConstant(Clazz, ClassConstant)}
   */
  @Test
  @DisplayName(
      "Test visitClassConstant(Clazz, ClassConstant); when ClassConstant(); then ClassConstant() ProcessingInfo is 'null'")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void InterfaceUsageMarker.visitClassConstant(Clazz, ClassConstant)"})
  void testVisitClassConstant_whenClassConstant_thenClassConstantProcessingInfoIsNull() {
    // Arrange
    InterfaceUsageMarker interfaceUsageMarker = new InterfaceUsageMarker(new ClassUsageMarker());
    LibraryClass clazz = new LibraryClass();
    ClassConstant classConstant = new ClassConstant();

    // Act
    interfaceUsageMarker.visitClassConstant(clazz, classConstant);

    // Assert that nothing has changed
    assertNull(classConstant.getProcessingInfo());
  }

  /**
   * Test {@link InterfaceUsageMarker#visitClassConstant(Clazz, ClassConstant)}.
   *
   * <ul>
   *   <li>When {@link ClassConstant#ClassConstant()}.
   *   <li>Then {@link ClassConstant#ClassConstant()} ProcessingInfo is {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link InterfaceUsageMarker#visitClassConstant(Clazz, ClassConstant)}
   */
  @Test
  @DisplayName(
      "Test visitClassConstant(Clazz, ClassConstant); when ClassConstant(); then ClassConstant() ProcessingInfo is 'null'")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void InterfaceUsageMarker.visitClassConstant(Clazz, ClassConstant)"})
  void testVisitClassConstant_whenClassConstant_thenClassConstantProcessingInfoIsNull2() {
    // Arrange
    InterfaceUsageMarker interfaceUsageMarker =
        new InterfaceUsageMarker(
            new ShortestClassUsageMarker(new ShortestUsageMarker(), "Just cause"));
    LibraryClass clazz = new LibraryClass();
    ClassConstant classConstant = new ClassConstant();

    // Act
    interfaceUsageMarker.visitClassConstant(clazz, classConstant);

    // Assert that nothing has changed
    assertNull(classConstant.getProcessingInfo());
  }

  /**
   * Test {@link InterfaceUsageMarker#visitUtf8Constant(Clazz, Utf8Constant)}.
   *
   * <p>Method under test: {@link InterfaceUsageMarker#visitUtf8Constant(Clazz, Utf8Constant)}
   */
  @Test
  @DisplayName("Test visitUtf8Constant(Clazz, Utf8Constant)")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void InterfaceUsageMarker.visitUtf8Constant(Clazz, Utf8Constant)"})
  void testVisitUtf8Constant() {
    // Arrange
    ShortestUsageMarker usageMarker = new ShortestUsageMarker();
    InterfaceUsageMarker interfaceUsageMarker =
        new InterfaceUsageMarker(new ShortestClassUsageMarker(usageMarker, "Just cause"));
    LibraryClass clazz = new LibraryClass();
    Utf8Constant utf8Constant = new Utf8Constant("String");

    // Act
    interfaceUsageMarker.visitUtf8Constant(clazz, utf8Constant);

    // Assert
    ShortestUsageMark expectedProcessingInfo = usageMarker.currentUsageMark;
    assertSame(expectedProcessingInfo, utf8Constant.getProcessingInfo());
  }

  /**
   * Test {@link InterfaceUsageMarker#visitUtf8Constant(Clazz, Utf8Constant)}.
   *
   * <ul>
   *   <li>Then {@link Utf8Constant#Utf8Constant(String)} with {@code String} ProcessingInfo is
   *       {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link InterfaceUsageMarker#visitUtf8Constant(Clazz, Utf8Constant)}
   */
  @Test
  @DisplayName(
      "Test visitUtf8Constant(Clazz, Utf8Constant); then Utf8Constant(String) with 'String' ProcessingInfo is 'null'")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void InterfaceUsageMarker.visitUtf8Constant(Clazz, Utf8Constant)"})
  void testVisitUtf8Constant_thenUtf8ConstantWithStringProcessingInfoIsNull() {
    // Arrange
    InterfaceUsageMarker interfaceUsageMarker =
        new InterfaceUsageMarker(new ClassUsageMarker(new ShortestUsageMarker()));
    LibraryClass clazz = new LibraryClass();
    Utf8Constant utf8Constant = new Utf8Constant("String");

    // Act
    interfaceUsageMarker.visitUtf8Constant(clazz, utf8Constant);

    // Assert that nothing has changed
    assertNull(utf8Constant.getProcessingInfo());
  }
}
