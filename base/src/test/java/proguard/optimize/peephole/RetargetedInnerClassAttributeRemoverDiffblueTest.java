package proguard.optimize.peephole;

import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.anyInt;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import proguard.classfile.Clazz;
import proguard.classfile.LibraryClass;
import proguard.classfile.attribute.EnclosingMethodAttribute;
import proguard.classfile.attribute.InnerClassesAttribute;
import proguard.classfile.attribute.InnerClassesInfo;
import proguard.classfile.constant.visitor.ConstantVisitor;
import proguard.optimize.info.ClassOptimizationInfo;

class RetargetedInnerClassAttributeRemoverDiffblueTest {
  /**
   * Method under test:
   * {@link RetargetedInnerClassAttributeRemover#visitInnerClassesAttribute(Clazz, InnerClassesAttribute)}
   */
  @Test
  void testVisitInnerClassesAttribute() {
    // Arrange
    RetargetedInnerClassAttributeRemover retargetedInnerClassAttributeRemover = new RetargetedInnerClassAttributeRemover();
    ClassOptimizationInfo classOptimizationInfo = mock(ClassOptimizationInfo.class);
    when(classOptimizationInfo.getTargetClass()).thenReturn(null);
    Clazz clazz = mock(Clazz.class);
    doNothing().when(clazz).constantPoolEntryAccept(anyInt(), Mockito.<ConstantVisitor>any());
    when(clazz.getProcessingInfo()).thenReturn(classOptimizationInfo);

    // Act
    retargetedInnerClassAttributeRemover.visitInnerClassesAttribute(clazz,
        new InnerClassesAttribute(1, 1, new InnerClassesInfo[]{new InnerClassesInfo(1, 1, 1, 1)}));

    // Assert
    verify(clazz, atLeast(1)).constantPoolEntryAccept(eq(1), isA(ConstantVisitor.class));
    verify(classOptimizationInfo).getTargetClass();
    verify(clazz).getProcessingInfo();
  }

  /**
   * Method under test:
   * {@link RetargetedInnerClassAttributeRemover#visitInnerClassesAttribute(Clazz, InnerClassesAttribute)}
   */
  @Test
  void testVisitInnerClassesAttribute2() {
    // Arrange
    RetargetedInnerClassAttributeRemover retargetedInnerClassAttributeRemover = new RetargetedInnerClassAttributeRemover();
    ClassOptimizationInfo classOptimizationInfo = mock(ClassOptimizationInfo.class);
    when(classOptimizationInfo.getTargetClass()).thenReturn(null);
    Clazz clazz = mock(Clazz.class);
    when(clazz.getProcessingInfo()).thenReturn(classOptimizationInfo);

    // Act
    retargetedInnerClassAttributeRemover.visitInnerClassesAttribute(clazz,
        new InnerClassesAttribute(1, 0, new InnerClassesInfo[]{new InnerClassesInfo(1, 1, 1, 1)}));

    // Assert
    verify(classOptimizationInfo).getTargetClass();
    verify(clazz).getProcessingInfo();
  }

  /**
   * Method under test:
   * {@link RetargetedInnerClassAttributeRemover#visitInnerClassesAttribute(Clazz, InnerClassesAttribute)}
   */
  @Test
  void testVisitInnerClassesAttribute3() {
    // Arrange
    RetargetedInnerClassAttributeRemover retargetedInnerClassAttributeRemover = new RetargetedInnerClassAttributeRemover();
    ClassOptimizationInfo classOptimizationInfo = mock(ClassOptimizationInfo.class);
    when(classOptimizationInfo.getTargetClass()).thenReturn(null);
    Clazz clazz = mock(Clazz.class);
    when(clazz.getProcessingInfo()).thenReturn(classOptimizationInfo);
    InnerClassesInfo innerClassesInfo = mock(InnerClassesInfo.class);
    doNothing().when(innerClassesInfo).innerClassConstantAccept(Mockito.<Clazz>any(), Mockito.<ConstantVisitor>any());
    doNothing().when(innerClassesInfo).outerClassConstantAccept(Mockito.<Clazz>any(), Mockito.<ConstantVisitor>any());

    // Act
    retargetedInnerClassAttributeRemover.visitInnerClassesAttribute(clazz,
        new InnerClassesAttribute(1, 1, new InnerClassesInfo[]{innerClassesInfo}));

    // Assert
    verify(innerClassesInfo, atLeast(1)).innerClassConstantAccept(isA(Clazz.class), isA(ConstantVisitor.class));
    verify(innerClassesInfo, atLeast(1)).outerClassConstantAccept(isA(Clazz.class), isA(ConstantVisitor.class));
    verify(classOptimizationInfo).getTargetClass();
    verify(clazz).getProcessingInfo();
  }

  /**
   * Method under test:
   * {@link RetargetedInnerClassAttributeRemover#visitEnclosingMethodAttribute(Clazz, EnclosingMethodAttribute)}
   */
  @Test
  void testVisitEnclosingMethodAttribute() {
    // Arrange
    RetargetedInnerClassAttributeRemover retargetedInnerClassAttributeRemover = new RetargetedInnerClassAttributeRemover();
    Clazz clazz = mock(Clazz.class);
    when(clazz.getProcessingInfo()).thenReturn(new ClassOptimizationInfo());

    // Act
    retargetedInnerClassAttributeRemover.visitEnclosingMethodAttribute(clazz, new EnclosingMethodAttribute(1, 1, 1));

    // Assert that nothing has changed
    verify(clazz).getProcessingInfo();
  }

  /**
   * Method under test:
   * {@link RetargetedInnerClassAttributeRemover#visitInnerClassesInfo(Clazz, InnerClassesInfo)}
   */
  @Test
  void testVisitInnerClassesInfo() {
    // Arrange
    RetargetedInnerClassAttributeRemover retargetedInnerClassAttributeRemover = new RetargetedInnerClassAttributeRemover();
    LibraryClass clazz = mock(LibraryClass.class);
    doNothing().when(clazz).constantPoolEntryAccept(anyInt(), Mockito.<ConstantVisitor>any());

    // Act
    retargetedInnerClassAttributeRemover.visitInnerClassesInfo(clazz, new InnerClassesInfo(1, 1, 1, 1));

    // Assert
    verify(clazz, atLeast(1)).constantPoolEntryAccept(eq(1), isA(ConstantVisitor.class));
  }

  /**
   * Method under test:
   * {@link RetargetedInnerClassAttributeRemover#visitInnerClassesInfo(Clazz, InnerClassesInfo)}
   */
  @Test
  void testVisitInnerClassesInfo2() {
    // Arrange
    RetargetedInnerClassAttributeRemover retargetedInnerClassAttributeRemover = new RetargetedInnerClassAttributeRemover();
    LibraryClass clazz = mock(LibraryClass.class);
    InnerClassesInfo innerClassesInfo = mock(InnerClassesInfo.class);
    doNothing().when(innerClassesInfo).innerClassConstantAccept(Mockito.<Clazz>any(), Mockito.<ConstantVisitor>any());
    doNothing().when(innerClassesInfo).outerClassConstantAccept(Mockito.<Clazz>any(), Mockito.<ConstantVisitor>any());

    // Act
    retargetedInnerClassAttributeRemover.visitInnerClassesInfo(clazz, innerClassesInfo);

    // Assert
    verify(innerClassesInfo).innerClassConstantAccept(isA(Clazz.class), isA(ConstantVisitor.class));
    verify(innerClassesInfo).outerClassConstantAccept(isA(Clazz.class), isA(ConstantVisitor.class));
  }
}
