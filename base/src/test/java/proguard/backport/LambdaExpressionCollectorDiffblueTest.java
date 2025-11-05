package proguard.backport;

import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import java.util.HashMap;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import proguard.classfile.Clazz;
import proguard.classfile.LibraryClass;
import proguard.classfile.attribute.visitor.AttributeVisitor;
import proguard.classfile.constant.InvokeDynamicConstant;

class LambdaExpressionCollectorDiffblueTest {
  /**
   * Method under test:
   * {@link LambdaExpressionCollector#visitInvokeDynamicConstant(Clazz, InvokeDynamicConstant)}
   */
  @Test
  void testVisitInvokeDynamicConstant() {
    // Arrange
    LambdaExpressionCollector lambdaExpressionCollector = new LambdaExpressionCollector(new HashMap<>());
    LibraryClass clazz = mock(LibraryClass.class);
    doNothing().when(clazz).attributesAccept(Mockito.<AttributeVisitor>any());

    // Act
    lambdaExpressionCollector.visitInvokeDynamicConstant(clazz, new InvokeDynamicConstant());

    // Assert
    verify(clazz).attributesAccept(isA(AttributeVisitor.class));
  }

  /**
   * Method under test:
   * {@link LambdaExpressionCollector#visitInvokeDynamicConstant(Clazz, InvokeDynamicConstant)}
   */
  @Test
  void testVisitInvokeDynamicConstant2() {
    // Arrange
    LambdaExpressionCollector lambdaExpressionCollector = new LambdaExpressionCollector(new HashMap<>());
    LibraryClass clazz = mock(LibraryClass.class);
    doNothing().when(clazz).attributesAccept(Mockito.<AttributeVisitor>any());
    InvokeDynamicConstant invokeDynamicConstant = mock(InvokeDynamicConstant.class);
    when(invokeDynamicConstant.getBootstrapMethodAttributeIndex()).thenReturn(1);

    // Act
    lambdaExpressionCollector.visitInvokeDynamicConstant(clazz, invokeDynamicConstant);

    // Assert
    verify(clazz).attributesAccept(isA(AttributeVisitor.class));
    verify(invokeDynamicConstant).getBootstrapMethodAttributeIndex();
  }
}
