package proguard.shrink;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.ArgumentMatchers.isNull;
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
import proguard.classfile.LibraryField;
import proguard.classfile.LibraryMember;
import proguard.classfile.LibraryMethod;
import proguard.classfile.ProgramClass;
import proguard.classfile.ProgramField;
import proguard.classfile.ProgramMethod;
import proguard.classfile.visitor.MemberVisitor;
import proguard.classfile.visitor.MethodImplementationFilter;
import proguard.fixer.kotlin.KotlinAnnotationCounter;
import proguard.obfuscate.kotlin.KotlinValueParameterUsageMarker;
import proguard.testutils.cpa.NamedMember;
import proguard.util.Processable;

class UsedMemberFilterDiffblueTest {
  /**
   * Test {@link UsedMemberFilter#visitProgramField(ProgramClass, ProgramField)}.
   * <p>
   * Method under test: {@link UsedMemberFilter#visitProgramField(ProgramClass, ProgramField)}
   */
  @Test
  @DisplayName("Test visitProgramField(ProgramClass, ProgramField)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void UsedMemberFilter.visitProgramField(ProgramClass, ProgramField)"})
  void testVisitProgramField() {
    // Arrange
    ShortestUsageMarker usageMarker = mock(ShortestUsageMarker.class);
    when(usageMarker.isUsed(Mockito.<Processable>any())).thenReturn(true);
    KotlinAnnotationCounter usedMemberFilter = new KotlinAnnotationCounter();
    UsedMemberFilter usedMemberFilter2 = new UsedMemberFilter(usageMarker, usedMemberFilter,
        new KotlinAnnotationCounter());
    ProgramClass programClass = new ProgramClass();

    // Act
    usedMemberFilter2.visitProgramField(programClass, new ProgramField());

    // Assert
    verify(usageMarker).isUsed(isA(Processable.class));
  }

  /**
   * Test {@link UsedMemberFilter#visitProgramField(ProgramClass, ProgramField)}.
   * <p>
   * Method under test: {@link UsedMemberFilter#visitProgramField(ProgramClass, ProgramField)}
   */
  @Test
  @DisplayName("Test visitProgramField(ProgramClass, ProgramField)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void UsedMemberFilter.visitProgramField(ProgramClass, ProgramField)"})
  void testVisitProgramField2() {
    // Arrange
    ShortestUsageMarker usageMarker = mock(ShortestUsageMarker.class);
    when(usageMarker.isUsed(Mockito.<Processable>any())).thenReturn(false);
    MemberVisitor usedMemberFilter = mock(MemberVisitor.class);
    UsedMemberFilter usedMemberFilter2 = new UsedMemberFilter(usageMarker, usedMemberFilter,
        new KotlinValueParameterUsageMarker());

    // Act
    usedMemberFilter2.visitProgramField(new ProgramClass(), null);

    // Assert
    verify(usageMarker).isUsed((Processable) isNull());
  }

  /**
   * Test {@link UsedMemberFilter#visitProgramField(ProgramClass, ProgramField)}.
   * <ul>
   *   <li>Then calls {@link MemberVisitor#visitProgramField(ProgramClass, ProgramField)}.</li>
   * </ul>
   * <p>
   * Method under test: {@link UsedMemberFilter#visitProgramField(ProgramClass, ProgramField)}
   */
  @Test
  @DisplayName("Test visitProgramField(ProgramClass, ProgramField); then calls visitProgramField(ProgramClass, ProgramField)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void UsedMemberFilter.visitProgramField(ProgramClass, ProgramField)"})
  void testVisitProgramField_thenCallsVisitProgramField() {
    // Arrange
    ShortestUsageMarker usageMarker = mock(ShortestUsageMarker.class);
    when(usageMarker.isUsed(Mockito.<Processable>any())).thenReturn(true);
    MemberVisitor usedMemberFilter = mock(MemberVisitor.class);
    doNothing().when(usedMemberFilter).visitProgramField(Mockito.<ProgramClass>any(), Mockito.<ProgramField>any());
    UsedMemberFilter usedMemberFilter2 = new UsedMemberFilter(usageMarker, usedMemberFilter,
        new KotlinAnnotationCounter());
    ProgramClass programClass = new ProgramClass();

    // Act
    usedMemberFilter2.visitProgramField(programClass, new ProgramField());

    // Assert
    verify(usedMemberFilter).visitProgramField(isA(ProgramClass.class), isA(ProgramField.class));
    verify(usageMarker).isUsed(isA(Processable.class));
  }

  /**
   * Test {@link UsedMemberFilter#visitProgramField(ProgramClass, ProgramField)}.
   * <ul>
   *   <li>Then throw {@link UnsupportedOperationException}.</li>
   * </ul>
   * <p>
   * Method under test: {@link UsedMemberFilter#visitProgramField(ProgramClass, ProgramField)}
   */
  @Test
  @DisplayName("Test visitProgramField(ProgramClass, ProgramField); then throw UnsupportedOperationException")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void UsedMemberFilter.visitProgramField(ProgramClass, ProgramField)"})
  void testVisitProgramField_thenThrowUnsupportedOperationException() {
    // Arrange
    ShortestUsageMarker usageMarker = mock(ShortestUsageMarker.class);
    when(usageMarker.isUsed(Mockito.<Processable>any())).thenReturn(false);
    MemberVisitor usedMemberFilter = mock(MemberVisitor.class);
    UsedMemberFilter usedMemberFilter2 = new UsedMemberFilter(usageMarker, usedMemberFilter,
        new MethodImplementationFilter(new KotlinAnnotationCounter()));
    ProgramClass programClass = new ProgramClass();

    // Act and Assert
    assertThrows(UnsupportedOperationException.class,
        () -> usedMemberFilter2.visitProgramField(programClass, new ProgramField()));
    verify(usageMarker).isUsed(isA(Processable.class));
  }

  /**
   * Test {@link UsedMemberFilter#visitProgramMethod(ProgramClass, ProgramMethod)}.
   * <p>
   * Method under test: {@link UsedMemberFilter#visitProgramMethod(ProgramClass, ProgramMethod)}
   */
  @Test
  @DisplayName("Test visitProgramMethod(ProgramClass, ProgramMethod)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void UsedMemberFilter.visitProgramMethod(ProgramClass, ProgramMethod)"})
  void testVisitProgramMethod() {
    // Arrange
    ShortestUsageMarker usageMarker = mock(ShortestUsageMarker.class);
    when(usageMarker.isUsed(Mockito.<Processable>any())).thenReturn(true);
    KotlinAnnotationCounter usedMemberFilter = new KotlinAnnotationCounter();
    UsedMemberFilter usedMemberFilter2 = new UsedMemberFilter(usageMarker, usedMemberFilter,
        new KotlinAnnotationCounter());
    ProgramClass programClass = new ProgramClass();

    // Act
    usedMemberFilter2.visitProgramMethod(programClass, new ProgramMethod());

    // Assert
    verify(usageMarker).isUsed(isA(Processable.class));
  }

  /**
   * Test {@link UsedMemberFilter#visitProgramMethod(ProgramClass, ProgramMethod)}.
   * <ul>
   *   <li>Given {@link ShortestUsageMarker} {@link ShortestUsageMarker#isUsed(Processable)} return {@code false}.</li>
   * </ul>
   * <p>
   * Method under test: {@link UsedMemberFilter#visitProgramMethod(ProgramClass, ProgramMethod)}
   */
  @Test
  @DisplayName("Test visitProgramMethod(ProgramClass, ProgramMethod); given ShortestUsageMarker isUsed(Processable) return 'false'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void UsedMemberFilter.visitProgramMethod(ProgramClass, ProgramMethod)"})
  void testVisitProgramMethod_givenShortestUsageMarkerIsUsedReturnFalse() {
    // Arrange
    ShortestUsageMarker usageMarker = mock(ShortestUsageMarker.class);
    when(usageMarker.isUsed(Mockito.<Processable>any())).thenReturn(false);
    MemberVisitor usedMemberFilter = mock(MemberVisitor.class);
    UsedMemberFilter usedMemberFilter2 = new UsedMemberFilter(usageMarker, usedMemberFilter,
        new KotlinAnnotationCounter());
    ProgramClass programClass = new ProgramClass();

    // Act
    usedMemberFilter2.visitProgramMethod(programClass, new NamedMember("Member Name", "Descriptor"));

    // Assert
    verify(usageMarker).isUsed(isA(Processable.class));
  }

  /**
   * Test {@link UsedMemberFilter#visitProgramMethod(ProgramClass, ProgramMethod)}.
   * <ul>
   *   <li>Then calls {@link MemberVisitor#visitProgramMethod(ProgramClass, ProgramMethod)}.</li>
   * </ul>
   * <p>
   * Method under test: {@link UsedMemberFilter#visitProgramMethod(ProgramClass, ProgramMethod)}
   */
  @Test
  @DisplayName("Test visitProgramMethod(ProgramClass, ProgramMethod); then calls visitProgramMethod(ProgramClass, ProgramMethod)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void UsedMemberFilter.visitProgramMethod(ProgramClass, ProgramMethod)"})
  void testVisitProgramMethod_thenCallsVisitProgramMethod() {
    // Arrange
    ShortestUsageMarker usageMarker = mock(ShortestUsageMarker.class);
    when(usageMarker.isUsed(Mockito.<Processable>any())).thenReturn(true);
    MemberVisitor usedMemberFilter = mock(MemberVisitor.class);
    doNothing().when(usedMemberFilter).visitProgramMethod(Mockito.<ProgramClass>any(), Mockito.<ProgramMethod>any());
    UsedMemberFilter usedMemberFilter2 = new UsedMemberFilter(usageMarker, usedMemberFilter,
        new KotlinAnnotationCounter());
    ProgramClass programClass = new ProgramClass();

    // Act
    usedMemberFilter2.visitProgramMethod(programClass, new ProgramMethod());

    // Assert
    verify(usedMemberFilter).visitProgramMethod(isA(ProgramClass.class), isA(ProgramMethod.class));
    verify(usageMarker).isUsed(isA(Processable.class));
  }

  /**
   * Test {@link UsedMemberFilter#visitLibraryField(LibraryClass, LibraryField)}.
   * <p>
   * Method under test: {@link UsedMemberFilter#visitLibraryField(LibraryClass, LibraryField)}
   */
  @Test
  @DisplayName("Test visitLibraryField(LibraryClass, LibraryField)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void UsedMemberFilter.visitLibraryField(LibraryClass, LibraryField)"})
  void testVisitLibraryField() {
    // Arrange
    ShortestUsageMarker usageMarker = mock(ShortestUsageMarker.class);
    when(usageMarker.isUsed(Mockito.<Processable>any())).thenReturn(true);
    KotlinAnnotationCounter usedMemberFilter = new KotlinAnnotationCounter();
    UsedMemberFilter usedMemberFilter2 = new UsedMemberFilter(usageMarker, usedMemberFilter,
        new KotlinAnnotationCounter());
    LibraryClass libraryClass = new LibraryClass();

    // Act
    usedMemberFilter2.visitLibraryField(libraryClass, new LibraryField(1, "Name", "Descriptor"));

    // Assert
    verify(usageMarker).isUsed(isA(Processable.class));
  }

  /**
   * Test {@link UsedMemberFilter#visitLibraryField(LibraryClass, LibraryField)}.
   * <ul>
   *   <li>Given {@link ShortestUsageMarker} {@link ShortestUsageMarker#isUsed(Processable)} return {@code false}.</li>
   *   <li>Then calls {@link LibraryMember#accept(Clazz, MemberVisitor)}.</li>
   * </ul>
   * <p>
   * Method under test: {@link UsedMemberFilter#visitLibraryField(LibraryClass, LibraryField)}
   */
  @Test
  @DisplayName("Test visitLibraryField(LibraryClass, LibraryField); given ShortestUsageMarker isUsed(Processable) return 'false'; then calls accept(Clazz, MemberVisitor)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void UsedMemberFilter.visitLibraryField(LibraryClass, LibraryField)"})
  void testVisitLibraryField_givenShortestUsageMarkerIsUsedReturnFalse_thenCallsAccept() {
    // Arrange
    ShortestUsageMarker usageMarker = mock(ShortestUsageMarker.class);
    when(usageMarker.isUsed(Mockito.<Processable>any())).thenReturn(false);
    MemberVisitor usedMemberFilter = mock(MemberVisitor.class);
    UsedMemberFilter usedMemberFilter2 = new UsedMemberFilter(usageMarker, usedMemberFilter,
        new KotlinAnnotationCounter());
    LibraryClass libraryClass = new LibraryClass();
    LibraryField libraryField = mock(LibraryField.class);
    doNothing().when(libraryField).accept(Mockito.<Clazz>any(), Mockito.<MemberVisitor>any());

    // Act
    usedMemberFilter2.visitLibraryField(libraryClass, libraryField);

    // Assert
    verify(libraryField).accept(isA(Clazz.class), isA(MemberVisitor.class));
    verify(usageMarker).isUsed(isA(Processable.class));
  }

  /**
   * Test {@link UsedMemberFilter#visitLibraryField(LibraryClass, LibraryField)}.
   * <ul>
   *   <li>Then calls {@link MemberVisitor#visitLibraryField(LibraryClass, LibraryField)}.</li>
   * </ul>
   * <p>
   * Method under test: {@link UsedMemberFilter#visitLibraryField(LibraryClass, LibraryField)}
   */
  @Test
  @DisplayName("Test visitLibraryField(LibraryClass, LibraryField); then calls visitLibraryField(LibraryClass, LibraryField)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void UsedMemberFilter.visitLibraryField(LibraryClass, LibraryField)"})
  void testVisitLibraryField_thenCallsVisitLibraryField() {
    // Arrange
    ShortestUsageMarker usageMarker = mock(ShortestUsageMarker.class);
    when(usageMarker.isUsed(Mockito.<Processable>any())).thenReturn(true);
    MemberVisitor usedMemberFilter = mock(MemberVisitor.class);
    doNothing().when(usedMemberFilter).visitLibraryField(Mockito.<LibraryClass>any(), Mockito.<LibraryField>any());
    UsedMemberFilter usedMemberFilter2 = new UsedMemberFilter(usageMarker, usedMemberFilter,
        new KotlinAnnotationCounter());
    LibraryClass libraryClass = new LibraryClass();

    // Act
    usedMemberFilter2.visitLibraryField(libraryClass, new LibraryField(1, "Name", "Descriptor"));

    // Assert
    verify(usedMemberFilter).visitLibraryField(isA(LibraryClass.class), isA(LibraryField.class));
    verify(usageMarker).isUsed(isA(Processable.class));
  }

  /**
   * Test {@link UsedMemberFilter#visitLibraryMethod(LibraryClass, LibraryMethod)}.
   * <p>
   * Method under test: {@link UsedMemberFilter#visitLibraryMethod(LibraryClass, LibraryMethod)}
   */
  @Test
  @DisplayName("Test visitLibraryMethod(LibraryClass, LibraryMethod)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void UsedMemberFilter.visitLibraryMethod(LibraryClass, LibraryMethod)"})
  void testVisitLibraryMethod() {
    // Arrange
    ShortestUsageMarker usageMarker = mock(ShortestUsageMarker.class);
    when(usageMarker.isUsed(Mockito.<Processable>any())).thenReturn(true);
    KotlinAnnotationCounter usedMemberFilter = new KotlinAnnotationCounter();
    UsedMemberFilter usedMemberFilter2 = new UsedMemberFilter(usageMarker, usedMemberFilter,
        new KotlinAnnotationCounter());
    LibraryClass libraryClass = new LibraryClass();

    // Act
    usedMemberFilter2.visitLibraryMethod(libraryClass, new LibraryMethod(1, "Name", "Descriptor"));

    // Assert
    verify(usageMarker).isUsed(isA(Processable.class));
  }

  /**
   * Test {@link UsedMemberFilter#visitLibraryMethod(LibraryClass, LibraryMethod)}.
   * <ul>
   *   <li>Given {@link ShortestUsageMarker} {@link ShortestUsageMarker#isUsed(Processable)} return {@code false}.</li>
   *   <li>Then calls {@link LibraryMember#accept(Clazz, MemberVisitor)}.</li>
   * </ul>
   * <p>
   * Method under test: {@link UsedMemberFilter#visitLibraryMethod(LibraryClass, LibraryMethod)}
   */
  @Test
  @DisplayName("Test visitLibraryMethod(LibraryClass, LibraryMethod); given ShortestUsageMarker isUsed(Processable) return 'false'; then calls accept(Clazz, MemberVisitor)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void UsedMemberFilter.visitLibraryMethod(LibraryClass, LibraryMethod)"})
  void testVisitLibraryMethod_givenShortestUsageMarkerIsUsedReturnFalse_thenCallsAccept() {
    // Arrange
    ShortestUsageMarker usageMarker = mock(ShortestUsageMarker.class);
    when(usageMarker.isUsed(Mockito.<Processable>any())).thenReturn(false);
    MemberVisitor usedMemberFilter = mock(MemberVisitor.class);
    UsedMemberFilter usedMemberFilter2 = new UsedMemberFilter(usageMarker, usedMemberFilter,
        new KotlinAnnotationCounter());
    LibraryClass libraryClass = new LibraryClass();
    LibraryMethod libraryMethod = mock(LibraryMethod.class);
    doNothing().when(libraryMethod).accept(Mockito.<Clazz>any(), Mockito.<MemberVisitor>any());

    // Act
    usedMemberFilter2.visitLibraryMethod(libraryClass, libraryMethod);

    // Assert
    verify(libraryMethod).accept(isA(Clazz.class), isA(MemberVisitor.class));
    verify(usageMarker).isUsed(isA(Processable.class));
  }

  /**
   * Test {@link UsedMemberFilter#visitLibraryMethod(LibraryClass, LibraryMethod)}.
   * <ul>
   *   <li>Then calls {@link MemberVisitor#visitLibraryMethod(LibraryClass, LibraryMethod)}.</li>
   * </ul>
   * <p>
   * Method under test: {@link UsedMemberFilter#visitLibraryMethod(LibraryClass, LibraryMethod)}
   */
  @Test
  @DisplayName("Test visitLibraryMethod(LibraryClass, LibraryMethod); then calls visitLibraryMethod(LibraryClass, LibraryMethod)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void UsedMemberFilter.visitLibraryMethod(LibraryClass, LibraryMethod)"})
  void testVisitLibraryMethod_thenCallsVisitLibraryMethod() {
    // Arrange
    ShortestUsageMarker usageMarker = mock(ShortestUsageMarker.class);
    when(usageMarker.isUsed(Mockito.<Processable>any())).thenReturn(true);
    MemberVisitor usedMemberFilter = mock(MemberVisitor.class);
    doNothing().when(usedMemberFilter).visitLibraryMethod(Mockito.<LibraryClass>any(), Mockito.<LibraryMethod>any());
    UsedMemberFilter usedMemberFilter2 = new UsedMemberFilter(usageMarker, usedMemberFilter,
        new KotlinAnnotationCounter());
    LibraryClass libraryClass = new LibraryClass();

    // Act
    usedMemberFilter2.visitLibraryMethod(libraryClass, new LibraryMethod(1, "Name", "Descriptor"));

    // Assert
    verify(usedMemberFilter).visitLibraryMethod(isA(LibraryClass.class), isA(LibraryMethod.class));
    verify(usageMarker).isUsed(isA(Processable.class));
  }
}
