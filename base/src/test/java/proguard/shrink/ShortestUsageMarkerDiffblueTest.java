package proguard.shrink;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import proguard.classfile.LibraryClass;
import proguard.classfile.ProgramClass;
import proguard.classfile.ProgramField;
import proguard.classfile.ProgramMember;
import proguard.classfile.constant.ClassConstant;
import proguard.classfile.constant.Constant;
import proguard.util.Processable;

class ShortestUsageMarkerDiffblueTest {
  /**
   * Test {@link ShortestUsageMarker#isUsed(Processable)}.
   *
   * <ul>
   *   <li>Given {@code Processable}.
   * </ul>
   *
   * <p>Method under test: {@link ShortestUsageMarker#isUsed(Processable)}
   */
  @Test
  @DisplayName("Test isUsed(Processable); given 'Processable'")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean ShortestUsageMarker.isUsed(Processable)"})
  void testIsUsed_givenProcessable() {
    // Arrange
    ShortestUsageMarker shortestUsageMarker = new ShortestUsageMarker();

    LibraryClass processable = new LibraryClass(1, "This Class Name", "Super Class Name");
    processable.setProcessingInfo("Processable");

    // Act and Assert
    assertFalse(shortestUsageMarker.isUsed(processable));
  }

  /**
   * Test {@link ShortestUsageMarker#isUsed(Processable)}.
   *
   * <ul>
   *   <li>Given {@link ShortestUsageMark#ShortestUsageMark(String)} with reason is {@code Just
   *       cause}.
   *   <li>Then return {@code true}.
   * </ul>
   *
   * <p>Method under test: {@link ShortestUsageMarker#isUsed(Processable)}
   */
  @Test
  @DisplayName(
      "Test isUsed(Processable); given ShortestUsageMark(String) with reason is 'Just cause'; then return 'true'")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean ShortestUsageMarker.isUsed(Processable)"})
  void testIsUsed_givenShortestUsageMarkWithReasonIsJustCause_thenReturnTrue() {
    // Arrange
    ShortestUsageMarker shortestUsageMarker = new ShortestUsageMarker();

    LibraryClass processable = new LibraryClass(1, "This Class Name", "Super Class Name");
    processable.setProcessingInfo(new ShortestUsageMark("Just cause"));

    // Act and Assert
    assertTrue(shortestUsageMarker.isUsed(processable));
  }

  /**
   * Test {@link ShortestUsageMarker#isUsed(Processable)}.
   *
   * <ul>
   *   <li>When {@link LibraryClass#LibraryClass()}.
   *   <li>Then return {@code false}.
   * </ul>
   *
   * <p>Method under test: {@link ShortestUsageMarker#isUsed(Processable)}
   */
  @Test
  @DisplayName("Test isUsed(Processable); when LibraryClass(); then return 'false'")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean ShortestUsageMarker.isUsed(Processable)"})
  void testIsUsed_whenLibraryClass_thenReturnFalse() {
    // Arrange
    ShortestUsageMarker shortestUsageMarker = new ShortestUsageMarker();

    // Act and Assert
    assertFalse(shortestUsageMarker.isUsed(new LibraryClass()));
  }

  /**
   * Test {@link ShortestUsageMarker#shouldBeMarkedAsUsed(Processable)} with {@code processable}.
   *
   * <p>Method under test: {@link ShortestUsageMarker#shouldBeMarkedAsUsed(Processable)}
   */
  @Test
  @DisplayName("Test shouldBeMarkedAsUsed(Processable) with 'processable'")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean ShortestUsageMarker.shouldBeMarkedAsUsed(Processable)"})
  void testShouldBeMarkedAsUsedWithProcessable() {
    // Arrange
    ShortestUsageMarker shortestUsageMarker = new ShortestUsageMarker();
    shortestUsageMarker.setCurrentUsageMark(new ShortestUsageMark("Just cause"));

    LibraryClass processable = new LibraryClass(1, "This Class Name", "Super Class Name");
    ShortestUsageMark previousUsageMark = new ShortestUsageMark("Just cause");
    processable.setProcessingInfo(
        new ShortestUsageMark(previousUsageMark, "Just cause", 1, new LibraryClass()));

    // Act and Assert
    assertTrue(shortestUsageMarker.shouldBeMarkedAsUsed(processable));
  }

  /**
   * Test {@link ShortestUsageMarker#shouldBeMarkedAsUsed(Processable)} with {@code processable}.
   *
   * <ul>
   *   <li>Given {@code Processable}.
   * </ul>
   *
   * <p>Method under test: {@link ShortestUsageMarker#shouldBeMarkedAsUsed(Processable)}
   */
  @Test
  @DisplayName("Test shouldBeMarkedAsUsed(Processable) with 'processable'; given 'Processable'")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean ShortestUsageMarker.shouldBeMarkedAsUsed(Processable)"})
  void testShouldBeMarkedAsUsedWithProcessable_givenProcessable() {
    // Arrange
    ShortestUsageMarker shortestUsageMarker = new ShortestUsageMarker();

    LibraryClass processable = new LibraryClass(1, "This Class Name", "Super Class Name");
    processable.setProcessingInfo("Processable");

    // Act and Assert
    assertTrue(shortestUsageMarker.shouldBeMarkedAsUsed(processable));
  }

  /**
   * Test {@link ShortestUsageMarker#shouldBeMarkedAsUsed(Processable)} with {@code processable}.
   *
   * <ul>
   *   <li>Then return {@code false}.
   * </ul>
   *
   * <p>Method under test: {@link ShortestUsageMarker#shouldBeMarkedAsUsed(Processable)}
   */
  @Test
  @DisplayName("Test shouldBeMarkedAsUsed(Processable) with 'processable'; then return 'false'")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean ShortestUsageMarker.shouldBeMarkedAsUsed(Processable)"})
  void testShouldBeMarkedAsUsedWithProcessable_thenReturnFalse() {
    // Arrange
    ShortestUsageMarker shortestUsageMarker = new ShortestUsageMarker();
    shortestUsageMarker.setCurrentUsageMark(new ShortestUsageMark("Just cause"));

    LibraryClass processable = new LibraryClass(1, "This Class Name", "Super Class Name");
    processable.setProcessingInfo(new ShortestUsageMark("Just cause"));

    // Act and Assert
    assertFalse(shortestUsageMarker.shouldBeMarkedAsUsed(processable));
  }

  /**
   * Test {@link ShortestUsageMarker#shouldBeMarkedAsUsed(Processable)} with {@code processable}.
   *
   * <ul>
   *   <li>When {@link LibraryClass#LibraryClass()}.
   *   <li>Then return {@code true}.
   * </ul>
   *
   * <p>Method under test: {@link ShortestUsageMarker#shouldBeMarkedAsUsed(Processable)}
   */
  @Test
  @DisplayName(
      "Test shouldBeMarkedAsUsed(Processable) with 'processable'; when LibraryClass(); then return 'true'")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean ShortestUsageMarker.shouldBeMarkedAsUsed(Processable)"})
  void testShouldBeMarkedAsUsedWithProcessable_whenLibraryClass_thenReturnTrue() {
    // Arrange
    ShortestUsageMarker shortestUsageMarker = new ShortestUsageMarker();

    // Act and Assert
    assertTrue(shortestUsageMarker.shouldBeMarkedAsUsed(new LibraryClass()));
  }

  /**
   * Test {@link ShortestUsageMarker#shouldBeMarkedAsUsed(ProgramClass)} with {@code programClass}.
   *
   * <p>Method under test: {@link ShortestUsageMarker#shouldBeMarkedAsUsed(ProgramClass)}
   */
  @Test
  @DisplayName("Test shouldBeMarkedAsUsed(ProgramClass) with 'programClass'")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean ShortestUsageMarker.shouldBeMarkedAsUsed(ProgramClass)"})
  void testShouldBeMarkedAsUsedWithProgramClass() {
    // Arrange
    ShortestUsageMarker shortestUsageMarker = new ShortestUsageMarker();
    shortestUsageMarker.setCurrentUsageMark(new ShortestUsageMark("Just cause"));

    ProgramClass programClass =
        new ProgramClass(1, 3, new Constant[] {new ClassConstant()}, 1, 1, 1);
    ShortestUsageMark previousUsageMark = new ShortestUsageMark("Just cause");
    programClass.setProcessingInfo(
        new ShortestUsageMark(previousUsageMark, "Just cause", 1, new LibraryClass()));

    // Act and Assert
    assertTrue(shortestUsageMarker.shouldBeMarkedAsUsed(programClass));
  }

  /**
   * Test {@link ShortestUsageMarker#shouldBeMarkedAsUsed(ProgramClass, ProgramMember)} with {@code
   * programClass}, {@code programMember}.
   *
   * <p>Method under test: {@link ShortestUsageMarker#shouldBeMarkedAsUsed(ProgramClass,
   * ProgramMember)}
   */
  @Test
  @DisplayName(
      "Test shouldBeMarkedAsUsed(ProgramClass, ProgramMember) with 'programClass', 'programMember'")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({
    "boolean ShortestUsageMarker.shouldBeMarkedAsUsed(ProgramClass, ProgramMember)"
  })
  void testShouldBeMarkedAsUsedWithProgramClassProgramMember() {
    // Arrange
    ShortestUsageMarker shortestUsageMarker = new ShortestUsageMarker();
    shortestUsageMarker.setCurrentUsageMark(new ShortestUsageMark("Just cause"));
    ProgramClass programClass = new ProgramClass();

    ProgramField programMember = new ProgramField(1, 1, 1, new LibraryClass());
    ShortestUsageMark previousUsageMark = new ShortestUsageMark("Just cause");
    programMember.setProcessingInfo(
        new ShortestUsageMark(previousUsageMark, "Just cause", 1, new LibraryClass()));

    // Act and Assert
    assertTrue(shortestUsageMarker.shouldBeMarkedAsUsed(programClass, programMember));
  }

  /**
   * Test {@link ShortestUsageMarker#shouldBeMarkedAsUsed(ProgramClass, ProgramMember)} with {@code
   * programClass}, {@code programMember}.
   *
   * <ul>
   *   <li>Given {@code Program Member}.
   * </ul>
   *
   * <p>Method under test: {@link ShortestUsageMarker#shouldBeMarkedAsUsed(ProgramClass,
   * ProgramMember)}
   */
  @Test
  @DisplayName(
      "Test shouldBeMarkedAsUsed(ProgramClass, ProgramMember) with 'programClass', 'programMember'; given 'Program Member'")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({
    "boolean ShortestUsageMarker.shouldBeMarkedAsUsed(ProgramClass, ProgramMember)"
  })
  void testShouldBeMarkedAsUsedWithProgramClassProgramMember_givenProgramMember() {
    // Arrange
    ShortestUsageMarker shortestUsageMarker = new ShortestUsageMarker();
    ProgramClass programClass = new ProgramClass();

    ProgramField programMember = new ProgramField(1, 1, 1, new LibraryClass());
    programMember.setProcessingInfo("Program Member");

    // Act and Assert
    assertTrue(shortestUsageMarker.shouldBeMarkedAsUsed(programClass, programMember));
  }

  /**
   * Test {@link ShortestUsageMarker#shouldBeMarkedAsUsed(ProgramClass, ProgramMember)} with {@code
   * programClass}, {@code programMember}.
   *
   * <ul>
   *   <li>Then return {@code false}.
   * </ul>
   *
   * <p>Method under test: {@link ShortestUsageMarker#shouldBeMarkedAsUsed(ProgramClass,
   * ProgramMember)}
   */
  @Test
  @DisplayName(
      "Test shouldBeMarkedAsUsed(ProgramClass, ProgramMember) with 'programClass', 'programMember'; then return 'false'")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({
    "boolean ShortestUsageMarker.shouldBeMarkedAsUsed(ProgramClass, ProgramMember)"
  })
  void testShouldBeMarkedAsUsedWithProgramClassProgramMember_thenReturnFalse() {
    // Arrange
    ShortestUsageMarker shortestUsageMarker = new ShortestUsageMarker();
    shortestUsageMarker.setCurrentUsageMark(new ShortestUsageMark("Just cause"));
    ProgramClass programClass = new ProgramClass();

    ProgramField programMember = new ProgramField(1, 1, 1, new LibraryClass());
    programMember.setProcessingInfo(new ShortestUsageMark("Just cause"));

    // Act and Assert
    assertFalse(shortestUsageMarker.shouldBeMarkedAsUsed(programClass, programMember));
  }

  /**
   * Test {@link ShortestUsageMarker#shouldBeMarkedAsUsed(ProgramClass, ProgramMember)} with {@code
   * programClass}, {@code programMember}.
   *
   * <ul>
   *   <li>When {@link ProgramField#ProgramField()}.
   * </ul>
   *
   * <p>Method under test: {@link ShortestUsageMarker#shouldBeMarkedAsUsed(ProgramClass,
   * ProgramMember)}
   */
  @Test
  @DisplayName(
      "Test shouldBeMarkedAsUsed(ProgramClass, ProgramMember) with 'programClass', 'programMember'; when ProgramField()")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({
    "boolean ShortestUsageMarker.shouldBeMarkedAsUsed(ProgramClass, ProgramMember)"
  })
  void testShouldBeMarkedAsUsedWithProgramClassProgramMember_whenProgramField() {
    // Arrange
    ShortestUsageMarker shortestUsageMarker = new ShortestUsageMarker();
    ProgramClass programClass = new ProgramClass();

    // Act and Assert
    assertTrue(shortestUsageMarker.shouldBeMarkedAsUsed(programClass, new ProgramField()));
  }

  /**
   * Test {@link ShortestUsageMarker#shouldBeMarkedAsUsed(ProgramClass)} with {@code programClass}.
   *
   * <ul>
   *   <li>Given {@code Program Class}.
   * </ul>
   *
   * <p>Method under test: {@link ShortestUsageMarker#shouldBeMarkedAsUsed(ProgramClass)}
   */
  @Test
  @DisplayName("Test shouldBeMarkedAsUsed(ProgramClass) with 'programClass'; given 'Program Class'")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean ShortestUsageMarker.shouldBeMarkedAsUsed(ProgramClass)"})
  void testShouldBeMarkedAsUsedWithProgramClass_givenProgramClass() {
    // Arrange
    ShortestUsageMarker shortestUsageMarker = new ShortestUsageMarker();

    ProgramClass programClass =
        new ProgramClass(1, 3, new Constant[] {new ClassConstant()}, 1, 1, 1);
    programClass.setProcessingInfo("Program Class");

    // Act and Assert
    assertTrue(shortestUsageMarker.shouldBeMarkedAsUsed(programClass));
  }

  /**
   * Test {@link ShortestUsageMarker#shouldBeMarkedAsUsed(ProgramClass)} with {@code programClass}.
   *
   * <ul>
   *   <li>Then return {@code false}.
   * </ul>
   *
   * <p>Method under test: {@link ShortestUsageMarker#shouldBeMarkedAsUsed(ProgramClass)}
   */
  @Test
  @DisplayName("Test shouldBeMarkedAsUsed(ProgramClass) with 'programClass'; then return 'false'")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean ShortestUsageMarker.shouldBeMarkedAsUsed(ProgramClass)"})
  void testShouldBeMarkedAsUsedWithProgramClass_thenReturnFalse() {
    // Arrange
    ShortestUsageMarker shortestUsageMarker = new ShortestUsageMarker();
    shortestUsageMarker.setCurrentUsageMark(new ShortestUsageMark("Just cause"));

    ProgramClass programClass =
        new ProgramClass(1, 3, new Constant[] {new ClassConstant()}, 1, 1, 1);
    programClass.setProcessingInfo(new ShortestUsageMark("Just cause"));

    // Act and Assert
    assertFalse(shortestUsageMarker.shouldBeMarkedAsUsed(programClass));
  }

  /**
   * Test {@link ShortestUsageMarker#shouldBeMarkedAsUsed(ProgramClass)} with {@code programClass}.
   *
   * <ul>
   *   <li>When {@link ProgramClass#ProgramClass()}.
   *   <li>Then return {@code true}.
   * </ul>
   *
   * <p>Method under test: {@link ShortestUsageMarker#shouldBeMarkedAsUsed(ProgramClass)}
   */
  @Test
  @DisplayName(
      "Test shouldBeMarkedAsUsed(ProgramClass) with 'programClass'; when ProgramClass(); then return 'true'")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean ShortestUsageMarker.shouldBeMarkedAsUsed(ProgramClass)"})
  void testShouldBeMarkedAsUsedWithProgramClass_whenProgramClass_thenReturnTrue() {
    // Arrange
    ShortestUsageMarker shortestUsageMarker = new ShortestUsageMarker();

    // Act and Assert
    assertTrue(shortestUsageMarker.shouldBeMarkedAsUsed(new ProgramClass()));
  }

  /**
   * Test {@link ShortestUsageMarker#markAsPossiblyUsed(Processable)}.
   *
   * <ul>
   *   <li>Then {@link LibraryClass#LibraryClass()} ProcessingInfo {@link ShortestUsageMark}.
   * </ul>
   *
   * <p>Method under test: {@link ShortestUsageMarker#markAsPossiblyUsed(Processable)}
   */
  @Test
  @DisplayName(
      "Test markAsPossiblyUsed(Processable); then LibraryClass() ProcessingInfo ShortestUsageMark")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void ShortestUsageMarker.markAsPossiblyUsed(Processable)"})
  void testMarkAsPossiblyUsed_thenLibraryClassProcessingInfoShortestUsageMark() {
    // Arrange
    ShortestUsageMarker shortestUsageMarker = new ShortestUsageMarker();
    shortestUsageMarker.setCurrentUsageMark(new ShortestUsageMark("Just cause"));
    LibraryClass processable = new LibraryClass();

    // Act
    shortestUsageMarker.markAsPossiblyUsed(processable);

    // Assert
    Object processingInfo = processable.getProcessingInfo();
    assertTrue(processingInfo instanceof ShortestUsageMark);
    assertEquals("Just cause", ((ShortestUsageMark) processingInfo).getReason());
    assertFalse(((ShortestUsageMark) processingInfo).isCertain());
  }

  /**
   * Test {@link ShortestUsageMarker#shouldBeMarkedAsPossiblyUsed(Processable)} with {@code
   * processable}.
   *
   * <p>Method under test: {@link ShortestUsageMarker#shouldBeMarkedAsPossiblyUsed(Processable)}
   */
  @Test
  @DisplayName("Test shouldBeMarkedAsPossiblyUsed(Processable) with 'processable'")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean ShortestUsageMarker.shouldBeMarkedAsPossiblyUsed(Processable)"})
  void testShouldBeMarkedAsPossiblyUsedWithProcessable() {
    // Arrange
    ShortestUsageMarker shortestUsageMarker = new ShortestUsageMarker();
    shortestUsageMarker.setCurrentUsageMark(new ShortestUsageMark("Just cause"));

    LibraryClass processable = new LibraryClass(1, "This Class Name", "Super Class Name");
    ShortestUsageMark previousUsageMark = new ShortestUsageMark("Just cause");
    processable.setProcessingInfo(
        new ShortestUsageMark(previousUsageMark, "Just cause", 1, new LibraryClass()));

    // Act and Assert
    assertFalse(shortestUsageMarker.shouldBeMarkedAsPossiblyUsed(processable));
  }

  /**
   * Test {@link ShortestUsageMarker#shouldBeMarkedAsPossiblyUsed(Processable)} with {@code
   * processable}.
   *
   * <ul>
   *   <li>Given {@code Processable}.
   * </ul>
   *
   * <p>Method under test: {@link ShortestUsageMarker#shouldBeMarkedAsPossiblyUsed(Processable)}
   */
  @Test
  @DisplayName(
      "Test shouldBeMarkedAsPossiblyUsed(Processable) with 'processable'; given 'Processable'")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean ShortestUsageMarker.shouldBeMarkedAsPossiblyUsed(Processable)"})
  void testShouldBeMarkedAsPossiblyUsedWithProcessable_givenProcessable() {
    // Arrange
    ShortestUsageMarker shortestUsageMarker = new ShortestUsageMarker();

    LibraryClass processable = new LibraryClass(1, "This Class Name", "Super Class Name");
    processable.setProcessingInfo("Processable");

    // Act and Assert
    assertTrue(shortestUsageMarker.shouldBeMarkedAsPossiblyUsed(processable));
  }

  /**
   * Test {@link ShortestUsageMarker#shouldBeMarkedAsPossiblyUsed(Processable)} with {@code
   * processable}.
   *
   * <ul>
   *   <li>Then return {@code false}.
   * </ul>
   *
   * <p>Method under test: {@link ShortestUsageMarker#shouldBeMarkedAsPossiblyUsed(Processable)}
   */
  @Test
  @DisplayName(
      "Test shouldBeMarkedAsPossiblyUsed(Processable) with 'processable'; then return 'false'")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean ShortestUsageMarker.shouldBeMarkedAsPossiblyUsed(Processable)"})
  void testShouldBeMarkedAsPossiblyUsedWithProcessable_thenReturnFalse() {
    // Arrange
    ShortestUsageMarker shortestUsageMarker = new ShortestUsageMarker();
    shortestUsageMarker.setCurrentUsageMark(new ShortestUsageMark("Just cause"));

    LibraryClass processable = new LibraryClass(1, "This Class Name", "Super Class Name");
    processable.setProcessingInfo(new ShortestUsageMark("Just cause"));

    // Act and Assert
    assertFalse(shortestUsageMarker.shouldBeMarkedAsPossiblyUsed(processable));
  }

  /**
   * Test {@link ShortestUsageMarker#shouldBeMarkedAsPossiblyUsed(Processable)} with {@code
   * processable}.
   *
   * <ul>
   *   <li>When {@link LibraryClass#LibraryClass()}.
   *   <li>Then return {@code true}.
   * </ul>
   *
   * <p>Method under test: {@link ShortestUsageMarker#shouldBeMarkedAsPossiblyUsed(Processable)}
   */
  @Test
  @DisplayName(
      "Test shouldBeMarkedAsPossiblyUsed(Processable) with 'processable'; when LibraryClass(); then return 'true'")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean ShortestUsageMarker.shouldBeMarkedAsPossiblyUsed(Processable)"})
  void testShouldBeMarkedAsPossiblyUsedWithProcessable_whenLibraryClass_thenReturnTrue() {
    // Arrange
    ShortestUsageMarker shortestUsageMarker = new ShortestUsageMarker();

    // Act and Assert
    assertTrue(shortestUsageMarker.shouldBeMarkedAsPossiblyUsed(new LibraryClass()));
  }

  /**
   * Test {@link ShortestUsageMarker#shouldBeMarkedAsPossiblyUsed(ProgramClass, ProgramMember)} with
   * {@code programClass}, {@code programMember}.
   *
   * <p>Method under test: {@link ShortestUsageMarker#shouldBeMarkedAsPossiblyUsed(ProgramClass,
   * ProgramMember)}
   */
  @Test
  @DisplayName(
      "Test shouldBeMarkedAsPossiblyUsed(ProgramClass, ProgramMember) with 'programClass', 'programMember'")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({
    "boolean ShortestUsageMarker.shouldBeMarkedAsPossiblyUsed(ProgramClass, ProgramMember)"
  })
  void testShouldBeMarkedAsPossiblyUsedWithProgramClassProgramMember() {
    // Arrange
    ShortestUsageMarker shortestUsageMarker = new ShortestUsageMarker();
    shortestUsageMarker.setCurrentUsageMark(new ShortestUsageMark("Just cause"));
    ProgramClass programClass = new ProgramClass();

    ProgramField programMember = new ProgramField(1, 1, 1, new LibraryClass());
    ShortestUsageMark previousUsageMark = new ShortestUsageMark("Just cause");
    programMember.setProcessingInfo(
        new ShortestUsageMark(previousUsageMark, "Just cause", 1, new LibraryClass()));

    // Act and Assert
    assertFalse(shortestUsageMarker.shouldBeMarkedAsPossiblyUsed(programClass, programMember));
  }

  /**
   * Test {@link ShortestUsageMarker#shouldBeMarkedAsPossiblyUsed(ProgramClass, ProgramMember)} with
   * {@code programClass}, {@code programMember}.
   *
   * <ul>
   *   <li>Given {@code Program Member}.
   * </ul>
   *
   * <p>Method under test: {@link ShortestUsageMarker#shouldBeMarkedAsPossiblyUsed(ProgramClass,
   * ProgramMember)}
   */
  @Test
  @DisplayName(
      "Test shouldBeMarkedAsPossiblyUsed(ProgramClass, ProgramMember) with 'programClass', 'programMember'; given 'Program Member'")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({
    "boolean ShortestUsageMarker.shouldBeMarkedAsPossiblyUsed(ProgramClass, ProgramMember)"
  })
  void testShouldBeMarkedAsPossiblyUsedWithProgramClassProgramMember_givenProgramMember() {
    // Arrange
    ShortestUsageMarker shortestUsageMarker = new ShortestUsageMarker();
    ProgramClass programClass = new ProgramClass();

    ProgramField programMember = new ProgramField(1, 1, 1, new LibraryClass());
    programMember.setProcessingInfo("Program Member");

    // Act and Assert
    assertTrue(shortestUsageMarker.shouldBeMarkedAsPossiblyUsed(programClass, programMember));
  }

  /**
   * Test {@link ShortestUsageMarker#shouldBeMarkedAsPossiblyUsed(ProgramClass, ProgramMember)} with
   * {@code programClass}, {@code programMember}.
   *
   * <ul>
   *   <li>Then return {@code false}.
   * </ul>
   *
   * <p>Method under test: {@link ShortestUsageMarker#shouldBeMarkedAsPossiblyUsed(ProgramClass,
   * ProgramMember)}
   */
  @Test
  @DisplayName(
      "Test shouldBeMarkedAsPossiblyUsed(ProgramClass, ProgramMember) with 'programClass', 'programMember'; then return 'false'")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({
    "boolean ShortestUsageMarker.shouldBeMarkedAsPossiblyUsed(ProgramClass, ProgramMember)"
  })
  void testShouldBeMarkedAsPossiblyUsedWithProgramClassProgramMember_thenReturnFalse() {
    // Arrange
    ShortestUsageMarker shortestUsageMarker = new ShortestUsageMarker();
    shortestUsageMarker.setCurrentUsageMark(new ShortestUsageMark("Just cause"));
    ProgramClass programClass = new ProgramClass();

    ProgramField programMember = new ProgramField(1, 1, 1, new LibraryClass());
    programMember.setProcessingInfo(new ShortestUsageMark("Just cause"));

    // Act and Assert
    assertFalse(shortestUsageMarker.shouldBeMarkedAsPossiblyUsed(programClass, programMember));
  }

  /**
   * Test {@link ShortestUsageMarker#shouldBeMarkedAsPossiblyUsed(ProgramClass, ProgramMember)} with
   * {@code programClass}, {@code programMember}.
   *
   * <ul>
   *   <li>When {@link ProgramField#ProgramField()}.
   * </ul>
   *
   * <p>Method under test: {@link ShortestUsageMarker#shouldBeMarkedAsPossiblyUsed(ProgramClass,
   * ProgramMember)}
   */
  @Test
  @DisplayName(
      "Test shouldBeMarkedAsPossiblyUsed(ProgramClass, ProgramMember) with 'programClass', 'programMember'; when ProgramField()")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({
    "boolean ShortestUsageMarker.shouldBeMarkedAsPossiblyUsed(ProgramClass, ProgramMember)"
  })
  void testShouldBeMarkedAsPossiblyUsedWithProgramClassProgramMember_whenProgramField() {
    // Arrange
    ShortestUsageMarker shortestUsageMarker = new ShortestUsageMarker();
    ProgramClass programClass = new ProgramClass();

    // Act and Assert
    assertTrue(shortestUsageMarker.shouldBeMarkedAsPossiblyUsed(programClass, new ProgramField()));
  }

  /**
   * Test {@link ShortestUsageMarker#isPossiblyUsed(Processable)}.
   *
   * <ul>
   *   <li>Given {@code Processable}.
   * </ul>
   *
   * <p>Method under test: {@link ShortestUsageMarker#isPossiblyUsed(Processable)}
   */
  @Test
  @DisplayName("Test isPossiblyUsed(Processable); given 'Processable'")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean ShortestUsageMarker.isPossiblyUsed(Processable)"})
  void testIsPossiblyUsed_givenProcessable() {
    // Arrange
    ShortestUsageMarker shortestUsageMarker = new ShortestUsageMarker();

    LibraryClass processable = new LibraryClass(1, "This Class Name", "Super Class Name");
    processable.setProcessingInfo("Processable");

    // Act and Assert
    assertFalse(shortestUsageMarker.isPossiblyUsed(processable));
  }

  /**
   * Test {@link ShortestUsageMarker#isPossiblyUsed(Processable)}.
   *
   * <ul>
   *   <li>Given {@link ShortestUsageMark#ShortestUsageMark(String)} with reason is {@code Just
   *       cause}.
   * </ul>
   *
   * <p>Method under test: {@link ShortestUsageMarker#isPossiblyUsed(Processable)}
   */
  @Test
  @DisplayName(
      "Test isPossiblyUsed(Processable); given ShortestUsageMark(String) with reason is 'Just cause'")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean ShortestUsageMarker.isPossiblyUsed(Processable)"})
  void testIsPossiblyUsed_givenShortestUsageMarkWithReasonIsJustCause() {
    // Arrange
    ShortestUsageMarker shortestUsageMarker = new ShortestUsageMarker();

    LibraryClass processable = new LibraryClass(1, "This Class Name", "Super Class Name");
    processable.setProcessingInfo(new ShortestUsageMark("Just cause"));

    // Act and Assert
    assertFalse(shortestUsageMarker.isPossiblyUsed(processable));
  }

  /**
   * Test {@link ShortestUsageMarker#isPossiblyUsed(Processable)}.
   *
   * <ul>
   *   <li>When {@link LibraryClass#LibraryClass()}.
   *   <li>Then return {@code false}.
   * </ul>
   *
   * <p>Method under test: {@link ShortestUsageMarker#isPossiblyUsed(Processable)}
   */
  @Test
  @DisplayName("Test isPossiblyUsed(Processable); when LibraryClass(); then return 'false'")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean ShortestUsageMarker.isPossiblyUsed(Processable)"})
  void testIsPossiblyUsed_whenLibraryClass_thenReturnFalse() {
    // Arrange
    ShortestUsageMarker shortestUsageMarker = new ShortestUsageMarker();

    // Act and Assert
    assertFalse(shortestUsageMarker.isPossiblyUsed(new LibraryClass()));
  }

  /**
   * Test {@link ShortestUsageMarker#getShortestUsageMark(Processable)}.
   *
   * <p>Method under test: {@link ShortestUsageMarker#getShortestUsageMark(Processable)}
   */
  @Test
  @DisplayName("Test getShortestUsageMark(Processable)")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"ShortestUsageMark ShortestUsageMarker.getShortestUsageMark(Processable)"})
  void testGetShortestUsageMark() {
    // Arrange
    ShortestUsageMarker shortestUsageMarker = new ShortestUsageMarker();

    // Act and Assert
    assertNull(shortestUsageMarker.getShortestUsageMark(new LibraryClass()));
  }

  /**
   * Test new {@link ShortestUsageMarker} (default constructor).
   *
   * <p>Method under test: default or parameterless constructor of {@link ShortestUsageMarker}
   */
  @Test
  @DisplayName("Test new ShortestUsageMarker (default constructor)")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void ShortestUsageMarker.<init>()"})
  void testNewShortestUsageMarker() {
    // Arrange, Act and Assert
    assertNull(new ShortestUsageMarker().currentUsageMark);
  }
}
