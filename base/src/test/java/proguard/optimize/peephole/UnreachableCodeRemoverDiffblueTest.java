package proguard.optimize.peephole;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.junit.jupiter.api.Test;
import proguard.classfile.Clazz;
import proguard.classfile.LibraryClass;
import proguard.classfile.LibraryMethod;
import proguard.classfile.Method;
import proguard.classfile.attribute.CodeAttribute;

class UnreachableCodeRemoverDiffblueTest {
  /**
   * Method under test:
   * {@link UnreachableCodeRemover#visitCodeAttribute(Clazz, Method, CodeAttribute)}
   */
  @Test
  void testVisitCodeAttribute() {
    // Arrange
    UnreachableCodeRemover unreachableCodeRemover = new UnreachableCodeRemover();
    LibraryClass clazz = mock(LibraryClass.class);
    when(clazz.getName()).thenReturn("Name");
    LibraryMethod method = new LibraryMethod(1, "Name", "  Exception   = [{}] ({})");

    CodeAttribute codeAttribute = new CodeAttribute(1, 3, 3, 3, new byte[]{-87, -96, 'A', -96, 'A', -96, 'A', -96});

    // Act
    unreachableCodeRemover.visitCodeAttribute(clazz, method, codeAttribute);

    // Assert
    verify(clazz).getName();
    assertEquals(0, codeAttribute.u2maxStack);
    assertEquals(161, codeAttribute.u2maxLocals);
    assertEquals(2, codeAttribute.u4codeLength);
  }
}
