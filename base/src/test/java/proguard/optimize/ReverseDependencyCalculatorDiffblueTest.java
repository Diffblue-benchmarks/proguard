package proguard.optimize;

import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import proguard.classfile.ClassPool;
import proguard.classfile.visitor.ClassVisitor;

class ReverseDependencyCalculatorDiffblueTest {
  /**
   * Method under test:
   * {@link ReverseDependencyCalculator#reverseDependencyStore()}
   */
  @Test
  void testReverseDependencyStore() {
    // Arrange
    ClassPool classPool = mock(ClassPool.class);
    doNothing().when(classPool).classesAccept(Mockito.<ClassVisitor>any());

    // Act
    (new ReverseDependencyCalculator(classPool)).reverseDependencyStore();

    // Assert
    verify(classPool, atLeast(1)).classesAccept(Mockito.<ClassVisitor>any());
  }
}
