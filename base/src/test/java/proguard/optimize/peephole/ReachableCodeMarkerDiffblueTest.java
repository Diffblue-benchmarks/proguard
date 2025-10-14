package proguard.optimize.peephole;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import proguard.classfile.Clazz;
import proguard.classfile.LibraryClass;
import proguard.classfile.LibraryMethod;
import proguard.classfile.Method;
import proguard.classfile.attribute.CodeAttribute;
import proguard.classfile.instruction.BranchInstruction;

class ReachableCodeMarkerDiffblueTest {
  /**
   * Test {@link ReachableCodeMarker#isReachable(int)} with {@code offset}.
   *
   * <ul>
   *   <li>When two.
   *   <li>Then return {@code false}.
   * </ul>
   *
   * <p>Method under test: {@link ReachableCodeMarker#isReachable(int)}
   */
  @Test
  @DisplayName("Test isReachable(int) with 'offset'; when two; then return 'false'")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean ReachableCodeMarker.isReachable(int)"})
  void testIsReachableWithOffset_whenTwo_thenReturnFalse() {
    // Arrange, Act and Assert
    assertFalse(new ReachableCodeMarker().isReachable(2));
  }

  /**
   * Test {@link ReachableCodeMarker#isReachable(int, int)} with {@code startOffset}, {@code
   * endOffset}.
   *
   * <ul>
   *   <li>When one.
   *   <li>Then return {@code false}.
   * </ul>
   *
   * <p>Method under test: {@link ReachableCodeMarker#isReachable(int, int)}
   */
  @Test
  @DisplayName(
      "Test isReachable(int, int) with 'startOffset', 'endOffset'; when one; then return 'false'")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean ReachableCodeMarker.isReachable(int, int)"})
  void testIsReachableWithStartOffsetEndOffset_whenOne_thenReturnFalse() {
    // Arrange, Act and Assert
    assertFalse(new ReachableCodeMarker().isReachable(1, 3));
  }

  /**
   * Test {@link ReachableCodeMarker#visitCodeAttribute(Clazz, Method, CodeAttribute)}.
   *
   * <p>Method under test: {@link ReachableCodeMarker#visitCodeAttribute(Clazz, Method,
   * CodeAttribute)}
   */
  @Test
  @DisplayName("Test visitCodeAttribute(Clazz, Method, CodeAttribute)")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void ReachableCodeMarker.visitCodeAttribute(Clazz, Method, CodeAttribute)"})
  void testVisitCodeAttribute() {
    // Arrange
    ReachableCodeMarker reachableCodeMarker = new ReachableCodeMarker();
    LibraryClass clazz = new LibraryClass();
    LibraryMethod method = new LibraryMethod(1, "Name", "Descriptor");
    CodeAttribute codeAttribute =
        new CodeAttribute(1, 3, 3, 3, new byte[] {-87, -96, 'A', -96, 'A', -96, 'A', -96});

    // Act
    reachableCodeMarker.visitCodeAttribute(clazz, method, codeAttribute);

    // Assert that nothing has changed
    assertFalse(reachableCodeMarker.isReachable(2));
  }

  /**
   * Test {@link ReachableCodeMarker#visitCodeAttribute(Clazz, Method, CodeAttribute)}.
   *
   * <p>Method under test: {@link ReachableCodeMarker#visitCodeAttribute(Clazz, Method,
   * CodeAttribute)}
   */
  @Test
  @DisplayName("Test visitCodeAttribute(Clazz, Method, CodeAttribute)")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void ReachableCodeMarker.visitCodeAttribute(Clazz, Method, CodeAttribute)"})
  void testVisitCodeAttribute2() {
    // Arrange
    ReachableCodeMarker reachableCodeMarker = new ReachableCodeMarker();
    LibraryClass clazz = new LibraryClass();
    LibraryMethod method = new LibraryMethod(1, "Name", "Descriptor");
    CodeAttribute codeAttribute =
        new CodeAttribute(1, 3, 3, 3, new byte[] {'A', -87, 'A', -96, 'A', -96, 'A', -96});

    // Act
    reachableCodeMarker.visitCodeAttribute(clazz, method, codeAttribute);

    // Assert that nothing has changed
    assertFalse(reachableCodeMarker.isReachable(2));
  }

  /**
   * Test {@link ReachableCodeMarker#visitCodeAttribute(Clazz, Method, CodeAttribute)}.
   *
   * <p>Method under test: {@link ReachableCodeMarker#visitCodeAttribute(Clazz, Method,
   * CodeAttribute)}
   */
  @Test
  @DisplayName("Test visitCodeAttribute(Clazz, Method, CodeAttribute)")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void ReachableCodeMarker.visitCodeAttribute(Clazz, Method, CodeAttribute)"})
  void testVisitCodeAttribute3() {
    // Arrange
    ReachableCodeMarker reachableCodeMarker = new ReachableCodeMarker();
    LibraryClass clazz = new LibraryClass();
    LibraryMethod method = new LibraryMethod(1, "Name", "Descriptor");
    CodeAttribute codeAttribute =
        new CodeAttribute(
            1,
            3,
            3,
            3,
            new byte[] {-84, 3, 'A', 3, 'A', 3, 'A', 3, 'A', 3, 'A', 3, 'A', 3, 'A', 3});

    // Act
    reachableCodeMarker.visitCodeAttribute(clazz, method, codeAttribute);

    // Assert that nothing has changed
    assertFalse(reachableCodeMarker.isReachable(2));
  }

  /**
   * Test {@link ReachableCodeMarker#visitCodeAttribute(Clazz, Method, CodeAttribute)}.
   *
   * <p>Method under test: {@link ReachableCodeMarker#visitCodeAttribute(Clazz, Method,
   * CodeAttribute)}
   */
  @Test
  @DisplayName("Test visitCodeAttribute(Clazz, Method, CodeAttribute)")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void ReachableCodeMarker.visitCodeAttribute(Clazz, Method, CodeAttribute)"})
  void testVisitCodeAttribute4() {
    // Arrange
    ReachableCodeMarker reachableCodeMarker = new ReachableCodeMarker();
    LibraryClass clazz = new LibraryClass();
    LibraryMethod method = new LibraryMethod(1, "Name", "Descriptor");
    CodeAttribute codeAttribute =
        new CodeAttribute(
            1,
            3,
            3,
            3,
            new byte[] {-83, 3, 'A', 3, 'A', 3, 'A', 3, 'A', 3, 'A', 3, 'A', 3, 'A', 3});

    // Act
    reachableCodeMarker.visitCodeAttribute(clazz, method, codeAttribute);

    // Assert that nothing has changed
    assertFalse(reachableCodeMarker.isReachable(2));
  }

  /**
   * Test {@link ReachableCodeMarker#visitCodeAttribute(Clazz, Method, CodeAttribute)}.
   *
   * <ul>
   *   <li>Then {@link ReachableCodeMarker} (default constructor) Reachable is two.
   * </ul>
   *
   * <p>Method under test: {@link ReachableCodeMarker#visitCodeAttribute(Clazz, Method,
   * CodeAttribute)}
   */
  @Test
  @DisplayName(
      "Test visitCodeAttribute(Clazz, Method, CodeAttribute); then ReachableCodeMarker (default constructor) Reachable is two")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void ReachableCodeMarker.visitCodeAttribute(Clazz, Method, CodeAttribute)"})
  void testVisitCodeAttribute_thenReachableCodeMarkerReachableIsTwo() {
    // Arrange
    ReachableCodeMarker reachableCodeMarker = new ReachableCodeMarker();
    LibraryClass clazz = new LibraryClass();
    LibraryMethod method = new LibraryMethod(1, "Name", "Descriptor");
    CodeAttribute codeAttribute =
        new CodeAttribute(
            1,
            3,
            3,
            3,
            new byte[] {'A', 3, -87, 3, 'A', 3, 'A', 3, 'A', 3, 'A', 3, 'A', 3, 'A', 3});

    // Act
    reachableCodeMarker.visitCodeAttribute(clazz, method, codeAttribute);

    // Assert
    assertTrue(reachableCodeMarker.isReachable(2));
  }

  /**
   * Test {@link ReachableCodeMarker#visitBranchInstruction(Clazz, Method, CodeAttribute, int,
   * BranchInstruction)}.
   *
   * <p>Method under test: {@link ReachableCodeMarker#visitBranchInstruction(Clazz, Method,
   * CodeAttribute, int, BranchInstruction)}
   */
  @Test
  @DisplayName("Test visitBranchInstruction(Clazz, Method, CodeAttribute, int, BranchInstruction)")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void ReachableCodeMarker.visitBranchInstruction(Clazz, Method, CodeAttribute, int, BranchInstruction)"
  })
  void testVisitBranchInstruction() {
    // Arrange
    ReachableCodeMarker reachableCodeMarker = new ReachableCodeMarker();
    LibraryClass clazz = new LibraryClass();
    LibraryMethod method = new LibraryMethod(1, "Name", "Descriptor");
    CodeAttribute codeAttribute =
        new CodeAttribute(1, 3, 3, 3, new byte[] {'A', 'X', 'A', -84, 'A', 'X', 'A', 'X'});

    // Act
    reachableCodeMarker.visitBranchInstruction(
        clazz, method, codeAttribute, 2, new BranchInstruction((byte) -89, 1));

    // Assert that nothing has changed
    assertFalse(reachableCodeMarker.isReachable(2));
  }

  /**
   * Test {@link ReachableCodeMarker#visitBranchInstruction(Clazz, Method, CodeAttribute, int,
   * BranchInstruction)}.
   *
   * <p>Method under test: {@link ReachableCodeMarker#visitBranchInstruction(Clazz, Method,
   * CodeAttribute, int, BranchInstruction)}
   */
  @Test
  @DisplayName("Test visitBranchInstruction(Clazz, Method, CodeAttribute, int, BranchInstruction)")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void ReachableCodeMarker.visitBranchInstruction(Clazz, Method, CodeAttribute, int, BranchInstruction)"
  })
  void testVisitBranchInstruction2() {
    // Arrange
    ReachableCodeMarker reachableCodeMarker = new ReachableCodeMarker();
    LibraryClass clazz = new LibraryClass();
    LibraryMethod method = new LibraryMethod(1, "Name", "Descriptor");
    CodeAttribute codeAttribute =
        new CodeAttribute(1, 3, 3, 3, new byte[] {'A', 'X', 'A', -84, 'A', 'X', 'A', 'X'});

    // Act
    reachableCodeMarker.visitBranchInstruction(
        clazz, method, codeAttribute, 2, new BranchInstruction((byte) -56, 1));

    // Assert that nothing has changed
    assertFalse(reachableCodeMarker.isReachable(2));
  }

  /**
   * Test {@link ReachableCodeMarker#visitBranchInstruction(Clazz, Method, CodeAttribute, int,
   * BranchInstruction)}.
   *
   * <p>Method under test: {@link ReachableCodeMarker#visitBranchInstruction(Clazz, Method,
   * CodeAttribute, int, BranchInstruction)}
   */
  @Test
  @DisplayName("Test visitBranchInstruction(Clazz, Method, CodeAttribute, int, BranchInstruction)")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void ReachableCodeMarker.visitBranchInstruction(Clazz, Method, CodeAttribute, int, BranchInstruction)"
  })
  void testVisitBranchInstruction3() {
    // Arrange
    ReachableCodeMarker reachableCodeMarker = new ReachableCodeMarker();
    LibraryClass clazz = new LibraryClass();
    LibraryMethod method = new LibraryMethod(1, "Name", "Descriptor");
    CodeAttribute codeAttribute =
        new CodeAttribute(1, 3, 3, 3, new byte[] {'A', 'X', 'A', -84, 'A', 'X', 'A', 'X'});

    // Act
    reachableCodeMarker.visitBranchInstruction(
        clazz, method, codeAttribute, 2, new BranchInstruction((byte) 'A', 0));

    // Assert
    assertTrue(reachableCodeMarker.isReachable(2));
  }

  /**
   * Test {@link ReachableCodeMarker#visitBranchInstruction(Clazz, Method, CodeAttribute, int,
   * BranchInstruction)}.
   *
   * <p>Method under test: {@link ReachableCodeMarker#visitBranchInstruction(Clazz, Method,
   * CodeAttribute, int, BranchInstruction)}
   */
  @Test
  @DisplayName("Test visitBranchInstruction(Clazz, Method, CodeAttribute, int, BranchInstruction)")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void ReachableCodeMarker.visitBranchInstruction(Clazz, Method, CodeAttribute, int, BranchInstruction)"
  })
  void testVisitBranchInstruction4() {
    // Arrange
    ReachableCodeMarker reachableCodeMarker = new ReachableCodeMarker();
    LibraryClass clazz = new LibraryClass();
    LibraryMethod method = new LibraryMethod(1, "Name", "Descriptor");
    CodeAttribute codeAttribute =
        new CodeAttribute(1, 3, 3, 3, new byte[] {'A', 'X', 'A', -84, 'A', 'X', 'A', 'X'});

    // Act
    reachableCodeMarker.visitBranchInstruction(
        clazz, method, codeAttribute, 2, new BranchInstruction((byte) 'A', -1));

    // Assert
    assertTrue(reachableCodeMarker.isReachable(2));
  }

  /**
   * Test {@link ReachableCodeMarker#visitBranchInstruction(Clazz, Method, CodeAttribute, int,
   * BranchInstruction)}.
   *
   * <ul>
   *   <li>Then not {@link ReachableCodeMarker} (default constructor) Reachable is two.
   * </ul>
   *
   * <p>Method under test: {@link ReachableCodeMarker#visitBranchInstruction(Clazz, Method,
   * CodeAttribute, int, BranchInstruction)}
   */
  @Test
  @DisplayName(
      "Test visitBranchInstruction(Clazz, Method, CodeAttribute, int, BranchInstruction); then not ReachableCodeMarker (default constructor) Reachable is two")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void ReachableCodeMarker.visitBranchInstruction(Clazz, Method, CodeAttribute, int, BranchInstruction)"
  })
  void testVisitBranchInstruction_thenNotReachableCodeMarkerReachableIsTwo() {
    // Arrange
    ReachableCodeMarker reachableCodeMarker = new ReachableCodeMarker();
    LibraryClass clazz = new LibraryClass();
    LibraryMethod method = new LibraryMethod(1, "Name", "Descriptor");
    CodeAttribute codeAttribute =
        new CodeAttribute(1, 3, 3, 3, new byte[] {'A', 'X', 'A', -84, 'A', 'X', 'A', 'X'});

    // Act
    reachableCodeMarker.visitBranchInstruction(
        clazz, method, codeAttribute, 2, new BranchInstruction((byte) 'A', 1));

    // Assert that nothing has changed
    assertFalse(reachableCodeMarker.isReachable(2));
  }

  /**
   * Test {@link ReachableCodeMarker#visitBranchInstruction(Clazz, Method, CodeAttribute, int,
   * BranchInstruction)}.
   *
   * <ul>
   *   <li>Then not {@link ReachableCodeMarker} (default constructor) Reachable is two.
   * </ul>
   *
   * <p>Method under test: {@link ReachableCodeMarker#visitBranchInstruction(Clazz, Method,
   * CodeAttribute, int, BranchInstruction)}
   */
  @Test
  @DisplayName(
      "Test visitBranchInstruction(Clazz, Method, CodeAttribute, int, BranchInstruction); then not ReachableCodeMarker (default constructor) Reachable is two")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void ReachableCodeMarker.visitBranchInstruction(Clazz, Method, CodeAttribute, int, BranchInstruction)"
  })
  void testVisitBranchInstruction_thenNotReachableCodeMarkerReachableIsTwo2() {
    // Arrange
    ReachableCodeMarker reachableCodeMarker = new ReachableCodeMarker();
    LibraryClass clazz = new LibraryClass();
    LibraryMethod method = new LibraryMethod(1, "Name", "Descriptor");
    CodeAttribute codeAttribute =
        new CodeAttribute(1, 3, 3, 3, new byte[] {'A', 'X', 'A', -83, 'A', 'X', 'A', 'X'});

    // Act
    reachableCodeMarker.visitBranchInstruction(
        clazz, method, codeAttribute, 2, new BranchInstruction((byte) 'A', 1));

    // Assert that nothing has changed
    assertFalse(reachableCodeMarker.isReachable(2));
  }

  /**
   * Test {@link ReachableCodeMarker#visitBranchInstruction(Clazz, Method, CodeAttribute, int,
   * BranchInstruction)}.
   *
   * <ul>
   *   <li>Then not {@link ReachableCodeMarker} (default constructor) Reachable is two.
   * </ul>
   *
   * <p>Method under test: {@link ReachableCodeMarker#visitBranchInstruction(Clazz, Method,
   * CodeAttribute, int, BranchInstruction)}
   */
  @Test
  @DisplayName(
      "Test visitBranchInstruction(Clazz, Method, CodeAttribute, int, BranchInstruction); then not ReachableCodeMarker (default constructor) Reachable is two")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void ReachableCodeMarker.visitBranchInstruction(Clazz, Method, CodeAttribute, int, BranchInstruction)"
  })
  void testVisitBranchInstruction_thenNotReachableCodeMarkerReachableIsTwo3() {
    // Arrange
    ReachableCodeMarker reachableCodeMarker = new ReachableCodeMarker();
    LibraryClass clazz = new LibraryClass();
    LibraryMethod method = new LibraryMethod(1, "Name", "Descriptor");
    CodeAttribute codeAttribute =
        new CodeAttribute(1, 3, 3, 3, new byte[] {'A', 'X', 'A', -82, 'A', 'X', 'A', 'X'});

    // Act
    reachableCodeMarker.visitBranchInstruction(
        clazz, method, codeAttribute, 2, new BranchInstruction((byte) 'A', 1));

    // Assert that nothing has changed
    assertFalse(reachableCodeMarker.isReachable(2));
  }

  /**
   * Test {@link ReachableCodeMarker#visitBranchInstruction(Clazz, Method, CodeAttribute, int,
   * BranchInstruction)}.
   *
   * <ul>
   *   <li>Then not {@link ReachableCodeMarker} (default constructor) Reachable is two.
   * </ul>
   *
   * <p>Method under test: {@link ReachableCodeMarker#visitBranchInstruction(Clazz, Method,
   * CodeAttribute, int, BranchInstruction)}
   */
  @Test
  @DisplayName(
      "Test visitBranchInstruction(Clazz, Method, CodeAttribute, int, BranchInstruction); then not ReachableCodeMarker (default constructor) Reachable is two")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void ReachableCodeMarker.visitBranchInstruction(Clazz, Method, CodeAttribute, int, BranchInstruction)"
  })
  void testVisitBranchInstruction_thenNotReachableCodeMarkerReachableIsTwo4() {
    // Arrange
    ReachableCodeMarker reachableCodeMarker = new ReachableCodeMarker();
    LibraryClass clazz = new LibraryClass();
    LibraryMethod method = new LibraryMethod(1, "Name", "Descriptor");
    CodeAttribute codeAttribute =
        new CodeAttribute(1, 3, 3, 3, new byte[] {'A', 'X', 'A', -81, 'A', 'X', 'A', 'X'});

    // Act
    reachableCodeMarker.visitBranchInstruction(
        clazz, method, codeAttribute, 2, new BranchInstruction((byte) 'A', 1));

    // Assert that nothing has changed
    assertFalse(reachableCodeMarker.isReachable(2));
  }

  /**
   * Test {@link ReachableCodeMarker#visitBranchInstruction(Clazz, Method, CodeAttribute, int,
   * BranchInstruction)}.
   *
   * <ul>
   *   <li>Then not {@link ReachableCodeMarker} (default constructor) Reachable is two.
   * </ul>
   *
   * <p>Method under test: {@link ReachableCodeMarker#visitBranchInstruction(Clazz, Method,
   * CodeAttribute, int, BranchInstruction)}
   */
  @Test
  @DisplayName(
      "Test visitBranchInstruction(Clazz, Method, CodeAttribute, int, BranchInstruction); then not ReachableCodeMarker (default constructor) Reachable is two")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void ReachableCodeMarker.visitBranchInstruction(Clazz, Method, CodeAttribute, int, BranchInstruction)"
  })
  void testVisitBranchInstruction_thenNotReachableCodeMarkerReachableIsTwo5() {
    // Arrange
    ReachableCodeMarker reachableCodeMarker = new ReachableCodeMarker();
    LibraryClass clazz = new LibraryClass();
    LibraryMethod method = new LibraryMethod(1, "Name", "Descriptor");
    CodeAttribute codeAttribute =
        new CodeAttribute(1, 3, 3, 3, new byte[] {'A', 'X', 'A', -80, 'A', 'X', 'A', 'X'});

    // Act
    reachableCodeMarker.visitBranchInstruction(
        clazz, method, codeAttribute, 2, new BranchInstruction((byte) 'A', 1));

    // Assert that nothing has changed
    assertFalse(reachableCodeMarker.isReachable(2));
  }

  /**
   * Test new {@link ReachableCodeMarker} (default constructor).
   *
   * <p>Method under test: default or parameterless constructor of {@link ReachableCodeMarker}
   */
  @Test
  @DisplayName("Test new ReachableCodeMarker (default constructor)")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void ReachableCodeMarker.<init>()"})
  void testNewReachableCodeMarker() {
    // Arrange, Act and Assert
    assertFalse(new ReachableCodeMarker().isReachable(2));
  }
}
