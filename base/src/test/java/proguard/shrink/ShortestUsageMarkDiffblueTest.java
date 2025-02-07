package proguard.shrink;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import com.diffblue.cover.annotations.MethodsUnderTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import proguard.classfile.Clazz;
import proguard.classfile.LibraryClass;
import proguard.classfile.LibraryField;
import proguard.classfile.Member;
import proguard.classfile.visitor.ClassVisitor;

class ShortestUsageMarkDiffblueTest {
  /**
   * Test getters and setters.
   * <p>
   * Methods under test:
   * <ul>
   *   <li>{@link ShortestUsageMark#ShortestUsageMark(String)}
   *   <li>{@link ShortestUsageMark#getReason()}
   *   <li>{@link ShortestUsageMark#isCertain()}
   * </ul>
   */
  @Test
  @DisplayName("Test getters and setters")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void proguard.shrink.ShortestUsageMark.<init>(java.lang.String)",
      "java.lang.String proguard.shrink.ShortestUsageMark.getReason()",
      "boolean proguard.shrink.ShortestUsageMark.isCertain()"})
  void testGettersAndSetters() {
    // Arrange and Act
    ShortestUsageMark actualShortestUsageMark = new ShortestUsageMark("Just cause");
    String actualReason = actualShortestUsageMark.getReason();

    // Assert
    assertEquals("Just cause", actualReason);
    assertTrue(actualShortestUsageMark.isCertain());
  }

  /**
   * Test {@link ShortestUsageMark#ShortestUsageMark(ShortestUsageMark, String, int, Clazz)}.
   * <ul>
   *   <li>Then return Reason is {@code Just cause}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ShortestUsageMark#ShortestUsageMark(ShortestUsageMark, String, int, Clazz)}
   */
  @Test
  @DisplayName("Test new ShortestUsageMark(ShortestUsageMark, String, int, Clazz); then return Reason is 'Just cause'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "void proguard.shrink.ShortestUsageMark.<init>(proguard.shrink.ShortestUsageMark, java.lang.String, int, proguard.classfile.Clazz)"})
  void testNewShortestUsageMark_thenReturnReasonIsJustCause() {
    // Arrange
    ShortestUsageMark previousUsageMark = new ShortestUsageMark("Just cause");

    // Act
    ShortestUsageMark actualShortestUsageMark = new ShortestUsageMark(previousUsageMark, "Just cause", 1,
        new LibraryClass());

    // Assert
    assertEquals("Just cause", actualShortestUsageMark.getReason());
    assertTrue(actualShortestUsageMark.isCertain());
  }

  /**
   * Test {@link ShortestUsageMark#ShortestUsageMark(ShortestUsageMark, String, int, Clazz, Member)}.
   * <ul>
   *   <li>Then return Reason is {@code Just cause}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ShortestUsageMark#ShortestUsageMark(ShortestUsageMark, String, int, Clazz, Member)}
   */
  @Test
  @DisplayName("Test new ShortestUsageMark(ShortestUsageMark, String, int, Clazz, Member); then return Reason is 'Just cause'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "void proguard.shrink.ShortestUsageMark.<init>(proguard.shrink.ShortestUsageMark, java.lang.String, int, proguard.classfile.Clazz, proguard.classfile.Member)"})
  void testNewShortestUsageMark_thenReturnReasonIsJustCause2() {
    // Arrange
    ShortestUsageMark previousUsageMark = new ShortestUsageMark("Just cause");
    LibraryClass clazz = new LibraryClass();

    // Act
    ShortestUsageMark actualShortestUsageMark = new ShortestUsageMark(previousUsageMark, "Just cause", 1, clazz,
        new LibraryField(1, "Name", "Descriptor"));

    // Assert
    assertEquals("Just cause", actualShortestUsageMark.getReason());
    assertTrue(actualShortestUsageMark.isCertain());
  }

  /**
   * Test {@link ShortestUsageMark#ShortestUsageMark(ShortestUsageMark, boolean)}.
   * <ul>
   *   <li>Then return Reason is {@code Just cause}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ShortestUsageMark#ShortestUsageMark(ShortestUsageMark, boolean)}
   */
  @Test
  @DisplayName("Test new ShortestUsageMark(ShortestUsageMark, boolean); then return Reason is 'Just cause'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void proguard.shrink.ShortestUsageMark.<init>(proguard.shrink.ShortestUsageMark, boolean)"})
  void testNewShortestUsageMark_thenReturnReasonIsJustCause3() {
    // Arrange and Act
    ShortestUsageMark actualShortestUsageMark = new ShortestUsageMark(new ShortestUsageMark("Just cause"), true);

    // Assert
    assertEquals("Just cause", actualShortestUsageMark.getReason());
    assertTrue(actualShortestUsageMark.isCertain());
  }

  /**
   * Test {@link ShortestUsageMark#isShorter(ShortestUsageMark)}.
   * <ul>
   *   <li>Then return {@code true}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ShortestUsageMark#isShorter(ShortestUsageMark)}
   */
  @Test
  @DisplayName("Test isShorter(ShortestUsageMark); then return 'true'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean proguard.shrink.ShortestUsageMark.isShorter(proguard.shrink.ShortestUsageMark)"})
  void testIsShorter_thenReturnTrue() {
    // Arrange
    ShortestUsageMark shortestUsageMark = new ShortestUsageMark("Just cause");
    ShortestUsageMark previousUsageMark = new ShortestUsageMark("Just cause");

    // Act and Assert
    assertTrue(
        shortestUsageMark.isShorter(new ShortestUsageMark(previousUsageMark, "Just cause", 1, new LibraryClass())));
  }

  /**
   * Test {@link ShortestUsageMark#isShorter(ShortestUsageMark)}.
   * <ul>
   *   <li>When {@link ShortestUsageMark#ShortestUsageMark(String)} with reason is {@code Just cause}.</li>
   *   <li>Then return {@code false}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ShortestUsageMark#isShorter(ShortestUsageMark)}
   */
  @Test
  @DisplayName("Test isShorter(ShortestUsageMark); when ShortestUsageMark(String) with reason is 'Just cause'; then return 'false'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean proguard.shrink.ShortestUsageMark.isShorter(proguard.shrink.ShortestUsageMark)"})
  void testIsShorter_whenShortestUsageMarkWithReasonIsJustCause_thenReturnFalse() {
    // Arrange
    ShortestUsageMark shortestUsageMark = new ShortestUsageMark("Just cause");

    // Act and Assert
    assertFalse(shortestUsageMark.isShorter(new ShortestUsageMark("Just cause")));
  }

  /**
   * Test {@link ShortestUsageMark#isCausedBy(Clazz, Member)} with {@code clazz}, {@code member}.
   * <ul>
   *   <li>When {@link LibraryClass#LibraryClass()}.</li>
   *   <li>Then return {@code false}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ShortestUsageMark#isCausedBy(Clazz, Member)}
   */
  @Test
  @DisplayName("Test isCausedBy(Clazz, Member) with 'clazz', 'member'; when LibraryClass(); then return 'false'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "boolean proguard.shrink.ShortestUsageMark.isCausedBy(proguard.classfile.Clazz, proguard.classfile.Member)"})
  void testIsCausedByWithClazzMember_whenLibraryClass_thenReturnFalse() {
    // Arrange
    ShortestUsageMark shortestUsageMark = new ShortestUsageMark("Just cause");
    LibraryClass clazz = new LibraryClass();

    // Act and Assert
    assertFalse(shortestUsageMark.isCausedBy(clazz, new LibraryField(1, "Name", "Descriptor")));
  }

  /**
   * Test {@link ShortestUsageMark#isCausedBy(Clazz)} with {@code clazz}.
   * <ul>
   *   <li>When {@link LibraryClass#LibraryClass()}.</li>
   *   <li>Then return {@code false}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ShortestUsageMark#isCausedBy(Clazz)}
   */
  @Test
  @DisplayName("Test isCausedBy(Clazz) with 'clazz'; when LibraryClass(); then return 'false'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean proguard.shrink.ShortestUsageMark.isCausedBy(proguard.classfile.Clazz)"})
  void testIsCausedByWithClazz_whenLibraryClass_thenReturnFalse() {
    // Arrange
    ShortestUsageMark shortestUsageMark = new ShortestUsageMark("Just cause");

    // Act and Assert
    assertFalse(shortestUsageMark.isCausedBy(new LibraryClass()));
  }

  /**
   * Test {@link ShortestUsageMark#isCausedByMember(Clazz)}.
   * <ul>
   *   <li>When {@link LibraryClass#LibraryClass()}.</li>
   *   <li>Then return {@code false}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ShortestUsageMark#isCausedByMember(Clazz)}
   */
  @Test
  @DisplayName("Test isCausedByMember(Clazz); when LibraryClass(); then return 'false'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean proguard.shrink.ShortestUsageMark.isCausedByMember(proguard.classfile.Clazz)"})
  void testIsCausedByMember_whenLibraryClass_thenReturnFalse() {
    // Arrange
    ShortestUsageMark shortestUsageMark = new ShortestUsageMark("Just cause");

    // Act and Assert
    assertFalse(shortestUsageMark.isCausedByMember(new LibraryClass()));
  }

  /**
   * Test {@link ShortestUsageMark#acceptClassVisitor(ClassVisitor)}.
   * <ul>
   *   <li>Then calls {@link ClassVisitor#visitLibraryClass(LibraryClass)}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ShortestUsageMark#acceptClassVisitor(ClassVisitor)}
   */
  @Test
  @DisplayName("Test acceptClassVisitor(ClassVisitor); then calls visitLibraryClass(LibraryClass)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "void proguard.shrink.ShortestUsageMark.acceptClassVisitor(proguard.classfile.visitor.ClassVisitor)"})
  void testAcceptClassVisitor_thenCallsVisitLibraryClass() {
    // Arrange
    ShortestUsageMark previousUsageMark = new ShortestUsageMark("Just cause");
    ShortestUsageMark shortestUsageMark = new ShortestUsageMark(previousUsageMark, "Just cause", 1, new LibraryClass());
    ClassVisitor classVisitor = mock(ClassVisitor.class);
    doNothing().when(classVisitor).visitLibraryClass(Mockito.<LibraryClass>any());

    // Act
    shortestUsageMark.acceptClassVisitor(classVisitor);

    // Assert
    verify(classVisitor).visitLibraryClass(isA(LibraryClass.class));
  }

  /**
   * Test {@link ShortestUsageMark#toString()}.
   * <ul>
   *   <li>Then return {@code certain=true, depth=0: Just cause(none): (none)}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ShortestUsageMark#toString()}
   */
  @Test
  @DisplayName("Test toString(); then return 'certain=true, depth=0: Just cause(none): (none)'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"java.lang.String proguard.shrink.ShortestUsageMark.toString()"})
  void testToString_thenReturnCertainTrueDepth0JustCauseNoneNone() {
    // Arrange, Act and Assert
    assertEquals("certain=true, depth=0: Just cause(none): (none)", (new ShortestUsageMark("Just cause")).toString());
  }

  /**
   * Test {@link ShortestUsageMark#toString()}.
   * <ul>
   *   <li>Then return {@code certain=true, depth=1: Just causenull: (none)}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ShortestUsageMark#toString()}
   */
  @Test
  @DisplayName("Test toString(); then return 'certain=true, depth=1: Just causenull: (none)'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"java.lang.String proguard.shrink.ShortestUsageMark.toString()"})
  void testToString_thenReturnCertainTrueDepth1JustCausenullNone() {
    // Arrange
    ShortestUsageMark previousUsageMark = new ShortestUsageMark("Just cause");

    // Act and Assert
    assertEquals("certain=true, depth=1: Just causenull: (none)",
        (new ShortestUsageMark(previousUsageMark, "Just cause", 1, new LibraryClass())).toString());
  }
}
