package proguard.normalize;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import proguard.AppView;
import proguard.classfile.ClassPool;
import proguard.classfile.LibraryClass;
import proguard.classfile.visitor.ClassPoolVisitor;
import proguard.classfile.visitor.ClassVisitor;

class StringNormalizerDiffblueTest {
  /**
   * Method under test: {@link StringNormalizer#execute(AppView)}
   */
  @Test
  void testExecute() throws Exception {
    // Arrange
    StringNormalizer stringNormalizer = new StringNormalizer();
    ClassPool programClassPool = mock(ClassPool.class);
    doNothing().when(programClassPool).accept(Mockito.<ClassPoolVisitor>any());

    // Act
    stringNormalizer.execute(new AppView(programClassPool, new ClassPool()));

    // Assert
    verify(programClassPool).accept(isA(ClassPoolVisitor.class));
  }

  /**
   * Method under test: {@link StringNormalizer#execute(AppView)}
   */
  @Test
  void testExecute2() throws Exception {
    // Arrange
    StringNormalizer stringNormalizer = new StringNormalizer();
    LibraryClass clazz = mock(LibraryClass.class);
    doNothing().when(clazz).accept(Mockito.<ClassVisitor>any());

    ClassPool programClassPool = new ClassPool();
    programClassPool.addClass("Name", clazz);

    // Act
    stringNormalizer.execute(new AppView(programClassPool, new ClassPool()));

    // Assert
    verify(clazz).accept(isA(ClassVisitor.class));
  }

  /**
   * Method under test: default or parameterless constructor of
   * {@link StringNormalizer}
   */
  @Test
  void testNewStringNormalizer() {
    // Arrange, Act and Assert
    assertEquals("proguard.normalize.StringNormalizer", (new StringNormalizer()).getName());
  }
}
