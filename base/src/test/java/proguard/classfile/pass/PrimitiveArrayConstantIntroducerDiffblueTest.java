package proguard.classfile.pass;

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
import proguard.classfile.visitor.ClassVisitor;

class PrimitiveArrayConstantIntroducerDiffblueTest {
  /**
   * Method under test: {@link PrimitiveArrayConstantIntroducer#execute(AppView)}
   */
  @Test
  void testExecute() {
    // Arrange
    PrimitiveArrayConstantIntroducer primitiveArrayConstantIntroducer = new PrimitiveArrayConstantIntroducer();
    ClassPool programClassPool = mock(ClassPool.class);
    doNothing().when(programClassPool).classesAccept(Mockito.<ClassVisitor>any());

    // Act
    primitiveArrayConstantIntroducer.execute(new AppView(programClassPool, new ClassPool()));

    // Assert
    verify(programClassPool).classesAccept(isA(ClassVisitor.class));
  }

  /**
   * Method under test: {@link PrimitiveArrayConstantIntroducer#execute(AppView)}
   */
  @Test
  void testExecute2() {
    // Arrange
    PrimitiveArrayConstantIntroducer primitiveArrayConstantIntroducer = new PrimitiveArrayConstantIntroducer();
    LibraryClass clazz = mock(LibraryClass.class);
    doNothing().when(clazz).accept(Mockito.<ClassVisitor>any());

    ClassPool programClassPool = new ClassPool();
    programClassPool.addClass("Name", clazz);

    // Act
    primitiveArrayConstantIntroducer.execute(new AppView(programClassPool, new ClassPool()));

    // Assert
    verify(clazz).accept(isA(ClassVisitor.class));
  }

  /**
   * Method under test: default or parameterless constructor of
   * {@link PrimitiveArrayConstantIntroducer}
   */
  @Test
  void testNewPrimitiveArrayConstantIntroducer() {
    // Arrange, Act and Assert
    assertEquals("proguard.classfile.pass.PrimitiveArrayConstantIntroducer",
        (new PrimitiveArrayConstantIntroducer()).getName());
  }
}
