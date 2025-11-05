package proguard.optimize.info;

import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import proguard.classfile.ClassPool;
import proguard.classfile.visitor.ClassPoolVisitor;

class RepeatedClassPoolVisitorDiffblueTest {
  /**
   * Method under test: {@link RepeatedClassPoolVisitor#visitClassPool(ClassPool)}
   */
  @Test
  void testVisitClassPool() {
    // Arrange
    ClassPoolVisitor classPoolVisitor = mock(ClassPoolVisitor.class);
    doNothing().when(classPoolVisitor).visitClassPool(Mockito.<ClassPool>any());
    RepeatedClassPoolVisitor repeatedClassPoolVisitor = new RepeatedClassPoolVisitor(new MutableBoolean(),
        classPoolVisitor);

    // Act
    repeatedClassPoolVisitor.visitClassPool(new ClassPool());

    // Assert
    verify(classPoolVisitor).visitClassPool(isA(ClassPool.class));
  }
}
