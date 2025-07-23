package proguard.optimize.gson;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import proguard.classfile.Clazz;
import proguard.classfile.LibraryClass;
import proguard.classfile.ProgramClass;
import proguard.classfile.attribute.visitor.AttributeVisitor;

class LocalOrAnonymousClassCheckerDiffblueTest {
  /**
   * Test {@link LocalOrAnonymousClassChecker#visitProgramClass(ProgramClass)}.
   *
   * <ul>
   *   <li>Then calls {@link ProgramClass#attributesAccept(AttributeVisitor)}.
   * </ul>
   *
   * <p>Method under test: {@link LocalOrAnonymousClassChecker#visitProgramClass(ProgramClass)}
   */
  @Test
  @DisplayName(
      "Test visitProgramClass(ProgramClass); then calls attributesAccept(AttributeVisitor)")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void LocalOrAnonymousClassChecker.visitProgramClass(ProgramClass)"})
  void testVisitProgramClass_thenCallsAttributesAccept() {
    // Arrange
    LocalOrAnonymousClassChecker localOrAnonymousClassChecker = new LocalOrAnonymousClassChecker();
    ProgramClass programClass = mock(ProgramClass.class);
    doNothing().when(programClass).attributesAccept(Mockito.<AttributeVisitor>any());

    // Act
    localOrAnonymousClassChecker.visitProgramClass(programClass);

    // Assert
    verify(programClass).attributesAccept(isA(AttributeVisitor.class));
  }

  /**
   * Test getters and setters.
   *
   * <p>Methods under test:
   *
   * <ul>
   *   <li>default or parameterless constructor of {@link LocalOrAnonymousClassChecker}
   *   <li>{@link LocalOrAnonymousClassChecker#visitAnyClass(Clazz)}
   *   <li>{@link LocalOrAnonymousClassChecker#isLocalOrAnonymous()}
   * </ul>
   */
  @Test
  @DisplayName("Test getters and setters")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void LocalOrAnonymousClassChecker.<init>()",
    "boolean LocalOrAnonymousClassChecker.isLocalOrAnonymous()",
    "void LocalOrAnonymousClassChecker.visitAnyClass(Clazz)"
  })
  void testGettersAndSetters() {
    // Arrange and Act
    LocalOrAnonymousClassChecker actualLocalOrAnonymousClassChecker =
        new LocalOrAnonymousClassChecker();
    actualLocalOrAnonymousClassChecker.visitAnyClass(new LibraryClass());

    // Assert
    assertFalse(actualLocalOrAnonymousClassChecker.isLocalOrAnonymous());
  }
}
