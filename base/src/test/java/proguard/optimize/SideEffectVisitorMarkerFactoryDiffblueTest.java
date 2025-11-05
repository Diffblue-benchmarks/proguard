package proguard.optimize;

import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;
import proguard.classfile.visitor.MemberVisitor;
import proguard.fixer.kotlin.KotlinAnnotationCounter;

class SideEffectVisitorMarkerFactoryDiffblueTest {
  /**
   * Method under test:
   * {@link SideEffectVisitorMarkerFactory#createMemberVisitor(MemberVisitor)}
   */
  @Test
  void testCreateMemberVisitor() {
    // Arrange
    SideEffectVisitorMarkerFactory sideEffectVisitorMarkerFactory = new SideEffectVisitorMarkerFactory(true);

    // Act and Assert
    assertTrue(sideEffectVisitorMarkerFactory
        .createMemberVisitor(new KotlinAnnotationCounter()) instanceof OptimizationInfoMemberFilter);
  }

  /**
   * Method under test:
   * {@link SideEffectVisitorMarkerFactory#createMemberVisitor(MemberVisitor)}
   */
  @Test
  void testCreateMemberVisitor2() {
    // Arrange
    SideEffectVisitorMarkerFactory sideEffectVisitorMarkerFactory = new SideEffectVisitorMarkerFactory(false);

    // Act and Assert
    assertTrue(sideEffectVisitorMarkerFactory
        .createMemberVisitor(new KotlinAnnotationCounter()) instanceof OptimizationInfoMemberFilter);
  }
}
