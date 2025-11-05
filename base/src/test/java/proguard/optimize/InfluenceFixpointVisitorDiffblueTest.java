package proguard.optimize;

import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import proguard.classfile.ClassPool;
import proguard.classfile.visitor.ClassVisitor;

class InfluenceFixpointVisitorDiffblueTest {
  /**
   * Method under test:
   * {@link InfluenceFixpointVisitor#InfluenceFixpointVisitor(InfluenceFixpointVisitor.MemberVisitorFactory)}
   */
  @Test
  void testNewInfluenceFixpointVisitor() {
    // Arrange and Act
    InfluenceFixpointVisitor actualInfluenceFixpointVisitor = new InfluenceFixpointVisitor(
        mock(InfluenceFixpointVisitor.MemberVisitorFactory.class));
    new RuntimeException("foo");
    ClassPool classPool = mock(ClassPool.class);
    doNothing().when(classPool).classesAccept(Mockito.<ClassVisitor>any());
    actualInfluenceFixpointVisitor.visitClassPool(classPool);

    // Assert
    verify(classPool, atLeast(1)).classesAccept(Mockito.<ClassVisitor>any());
  }

  /**
   * Method under test: {@link InfluenceFixpointVisitor#visitClassPool(ClassPool)}
   */
  @Test
  void testVisitClassPool() {
    // Arrange
    InfluenceFixpointVisitor influenceFixpointVisitor = new InfluenceFixpointVisitor(
        mock(InfluenceFixpointVisitor.MemberVisitorFactory.class));
    ClassPool classPool = mock(ClassPool.class);
    doNothing().when(classPool).classesAccept(Mockito.<ClassVisitor>any());

    // Act
    influenceFixpointVisitor.visitClassPool(classPool);

    // Assert
    verify(classPool, atLeast(1)).classesAccept(Mockito.<ClassVisitor>any());
  }
}
