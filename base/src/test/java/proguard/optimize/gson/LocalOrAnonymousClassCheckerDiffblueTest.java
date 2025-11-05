package proguard.optimize.gson;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import proguard.classfile.Clazz;
import proguard.classfile.LibraryClass;
import proguard.classfile.ProgramClass;
import proguard.classfile.attribute.visitor.AttributeVisitor;

class LocalOrAnonymousClassCheckerDiffblueTest {
  /**
   * Methods under test:
   * <ul>
   *   <li>default or parameterless constructor of
   * {@link LocalOrAnonymousClassChecker}
   *   <li>{@link LocalOrAnonymousClassChecker#visitAnyClass(Clazz)}
   *   <li>{@link LocalOrAnonymousClassChecker#isLocalOrAnonymous()}
   * </ul>
   */
  @Test
  void testGettersAndSetters() {
    // Arrange and Act
    LocalOrAnonymousClassChecker actualLocalOrAnonymousClassChecker = new LocalOrAnonymousClassChecker();
    actualLocalOrAnonymousClassChecker.visitAnyClass(new LibraryClass());

    // Assert that nothing has changed
    assertFalse(actualLocalOrAnonymousClassChecker.isLocalOrAnonymous());
  }

  /**
   * Method under test:
   * {@link LocalOrAnonymousClassChecker#visitProgramClass(ProgramClass)}
   */
  @Test
  void testVisitProgramClass() {
    // Arrange
    LocalOrAnonymousClassChecker localOrAnonymousClassChecker = new LocalOrAnonymousClassChecker();
    ProgramClass programClass = mock(ProgramClass.class);
    doNothing().when(programClass).attributesAccept(Mockito.<AttributeVisitor>any());

    // Act
    localOrAnonymousClassChecker.visitProgramClass(programClass);

    // Assert
    verify(programClass).attributesAccept(isA(AttributeVisitor.class));
  }
}
