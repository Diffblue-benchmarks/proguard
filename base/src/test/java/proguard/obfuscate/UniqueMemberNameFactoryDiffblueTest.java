package proguard.obfuscate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import proguard.classfile.LibraryClass;

class UniqueMemberNameFactoryDiffblueTest {
  /**
   * Test {@link UniqueMemberNameFactory#nextName()}.
   *
   * <p>Method under test: {@link UniqueMemberNameFactory#nextName()}
   */
  @Test
  @DisplayName("Test nextName()")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"java.lang.String UniqueMemberNameFactory.nextName()"})
  void testNextName() {
    // Arrange
    UniqueMemberNameFactory delegateNameFactory =
        UniqueMemberNameFactory.newInjectedMemberNameFactory(
            new LibraryClass(1, "This Class Name", "Super Class Name"));

    // Act and Assert
    assertEquals(
        "$$a",
        new UniqueMemberNameFactory(
                delegateNameFactory, new LibraryClass(1, "This Class Name", "Super Class Name"))
            .nextName());
  }

  /**
   * Test {@link UniqueMemberNameFactory#nextName()}.
   *
   * <ul>
   *   <li>Then return {@code $$a}.
   * </ul>
   *
   * <p>Method under test: {@link UniqueMemberNameFactory#nextName()}
   */
  @Test
  @DisplayName("Test nextName(); then return '$$a'")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"java.lang.String UniqueMemberNameFactory.nextName()"})
  void testNextName_thenReturnA() {
    // Arrange, Act and Assert
    assertEquals(
        "$$a",
        UniqueMemberNameFactory.newInjectedMemberNameFactory(
                new LibraryClass(1, "This Class Name", "Super Class Name"))
            .nextName());
  }
}
