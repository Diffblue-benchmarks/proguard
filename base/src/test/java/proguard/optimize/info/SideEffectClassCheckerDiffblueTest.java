package proguard.optimize.info;

import static org.junit.jupiter.api.Assertions.assertFalse;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import proguard.classfile.Clazz;
import proguard.classfile.LibraryClass;
import proguard.classfile.LibraryField;
import proguard.classfile.Member;

class SideEffectClassCheckerDiffblueTest {
  /**
   * Test {@link SideEffectClassChecker#mayHaveSideEffects(Clazz, Clazz, Member)} with {@code
   * referencingClass}, {@code referencedClass}, {@code referencedMember}.
   *
   * <p>Method under test: {@link SideEffectClassChecker#mayHaveSideEffects(Clazz, Clazz, Member)}
   */
  @Test
  @DisplayName(
      "Test mayHaveSideEffects(Clazz, Clazz, Member) with 'referencingClass', 'referencedClass', 'referencedMember'")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean SideEffectClassChecker.mayHaveSideEffects(Clazz, Clazz, Member)"})
  void testMayHaveSideEffectsWithReferencingClassReferencedClassReferencedMember() {
    // Arrange
    LibraryClass referencingClass = new LibraryClass();
    LibraryClass referencedClass = new LibraryClass();

    // Act and Assert
    assertFalse(
        SideEffectClassChecker.mayHaveSideEffects(
            referencingClass, referencedClass, new LibraryField(1, "Name", "Descriptor")));
  }
}
