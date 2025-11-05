package proguard.optimize.info;

import static org.junit.jupiter.api.Assertions.assertFalse;
import org.junit.jupiter.api.Test;
import proguard.classfile.Clazz;
import proguard.classfile.LibraryClass;
import proguard.classfile.LibraryField;
import proguard.classfile.Member;

class SideEffectClassCheckerDiffblueTest {
  /**
   * Method under test:
   * {@link SideEffectClassChecker#mayHaveSideEffects(Clazz, Clazz, Member)}
   */
  @Test
  void testMayHaveSideEffects() {
    // Arrange
    LibraryClass referencingClass = new LibraryClass();
    LibraryClass referencedClass = new LibraryClass();

    // Act and Assert
    assertFalse(SideEffectClassChecker.mayHaveSideEffects(referencingClass, referencedClass,
        new LibraryField(1, "Name", "Descriptor")));
  }
}
