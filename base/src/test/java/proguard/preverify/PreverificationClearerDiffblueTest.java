package proguard.preverify;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import proguard.AppView;
import proguard.classfile.ClassPool;
import proguard.classfile.visitor.ClassVisitor;

class PreverificationClearerDiffblueTest {
  /**
   * Method under test: {@link PreverificationClearer#execute(AppView)}
   */
  @Test
  void testExecute() {
    // Arrange
    PreverificationClearer preverificationClearer = new PreverificationClearer();
    ClassPool programClassPool = mock(ClassPool.class);
    doNothing().when(programClassPool).classesAccept(Mockito.<ClassVisitor>any());

    // Act
    preverificationClearer.execute(new AppView(programClassPool, new ClassPool()));

    // Assert
    verify(programClassPool).classesAccept(isA(ClassVisitor.class));
  }

  /**
   * Method under test: default or parameterless constructor of
   * {@link PreverificationClearer}
   */
  @Test
  void testNewPreverificationClearer() {
    // Arrange, Act and Assert
    assertEquals("proguard.preverify.PreverificationClearer", (new PreverificationClearer()).getName());
  }
}
