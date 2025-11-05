package proguard.obfuscate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import org.junit.jupiter.api.Test;
import proguard.classfile.Clazz;
import proguard.classfile.LibraryClass;

class UniqueMemberNameFactoryDiffblueTest {
  /**
   * Method under test:
   * {@link UniqueMemberNameFactory#newInjectedMemberNameFactory(Clazz)}
   */
  @Test
  void testNewInjectedMemberNameFactory() {
    // Arrange, Act and Assert
    assertEquals("$$a", UniqueMemberNameFactory.newInjectedMemberNameFactory(mock(LibraryClass.class)).nextName());
  }

  /**
   * Method under test: {@link UniqueMemberNameFactory#nextName()}
   */
  @Test
  void testNextName() {
    // Arrange, Act and Assert
    assertEquals("$$a",
        UniqueMemberNameFactory.newInjectedMemberNameFactory(new LibraryClass(1, "This Class Name", "Super Class Name"))
            .nextName());
  }
}
