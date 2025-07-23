package proguard.optimize.info;

import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import proguard.classfile.Clazz;
import proguard.classfile.LibraryClass;
import proguard.classfile.LibraryMethod;
import proguard.classfile.Method;
import proguard.classfile.attribute.CodeAttribute;
import proguard.fixer.kotlin.KotlinAnnotationCounter;
import proguard.obfuscate.AttributeShrinker;
import proguard.optimize.KeepMarker;

class OptimizationCodeAttributeFilterDiffblueTest {
  /**
   * Test {@link OptimizationCodeAttributeFilter#visitCodeAttribute(Clazz, Method, CodeAttribute)}.
   *
   * <p>Method under test: {@link OptimizationCodeAttributeFilter#visitCodeAttribute(Clazz, Method,
   * CodeAttribute)}
   */
  @Test
  @DisplayName("Test visitCodeAttribute(Clazz, Method, CodeAttribute)")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void OptimizationCodeAttributeFilter.visitCodeAttribute(Clazz, Method, CodeAttribute)"
  })
  void testVisitCodeAttribute() {
    // Arrange
    KotlinAnnotationCounter attributeVisitor = new KotlinAnnotationCounter();
    OptimizationCodeAttributeFilter optimizationCodeAttributeFilter =
        new OptimizationCodeAttributeFilter(attributeVisitor, new KotlinAnnotationCounter());
    LibraryClass clazz = new LibraryClass();
    LibraryMethod method = new LibraryMethod(1, "Name", "Descriptor");

    CodeAttribute codeAttribute = new CodeAttribute(1);

    // Act
    optimizationCodeAttributeFilter.visitCodeAttribute(clazz, method, codeAttribute);

    // Assert that nothing has changed
    assertNull(codeAttribute.getProcessingInfo());
  }

  /**
   * Test {@link OptimizationCodeAttributeFilter#visitCodeAttribute(Clazz, Method, CodeAttribute)}.
   *
   * <p>Method under test: {@link OptimizationCodeAttributeFilter#visitCodeAttribute(Clazz, Method,
   * CodeAttribute)}
   */
  @Test
  @DisplayName("Test visitCodeAttribute(Clazz, Method, CodeAttribute)")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void OptimizationCodeAttributeFilter.visitCodeAttribute(Clazz, Method, CodeAttribute)"
  })
  void testVisitCodeAttribute2() {
    // Arrange
    KotlinAnnotationCounter attributeVisitor = new KotlinAnnotationCounter();
    OptimizationCodeAttributeFilter optimizationCodeAttributeFilter =
        new OptimizationCodeAttributeFilter(attributeVisitor, new KotlinAnnotationCounter());
    LibraryClass clazz = new LibraryClass();
    LibraryMethod method = new LibraryMethod(1, "Name", "Descriptor");

    CodeAttribute codeAttribute = new CodeAttribute(1);
    CodeAttributeOptimizationInfo codeAttributeOptimizationInfo =
        new CodeAttributeOptimizationInfo();
    codeAttribute.setProcessingInfo(codeAttributeOptimizationInfo);

    // Act
    optimizationCodeAttributeFilter.visitCodeAttribute(clazz, method, codeAttribute);

    // Assert that nothing has changed
    Object processingInfo = codeAttribute.getProcessingInfo();
    assertTrue(processingInfo instanceof CodeAttributeOptimizationInfo);
    assertTrue(((CodeAttributeOptimizationInfo) processingInfo).isKept());
    assertSame(codeAttributeOptimizationInfo, processingInfo);
  }

  /**
   * Test {@link OptimizationCodeAttributeFilter#visitCodeAttribute(Clazz, Method, CodeAttribute)}.
   *
   * <p>Method under test: {@link OptimizationCodeAttributeFilter#visitCodeAttribute(Clazz, Method,
   * CodeAttribute)}
   */
  @Test
  @DisplayName("Test visitCodeAttribute(Clazz, Method, CodeAttribute)")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void OptimizationCodeAttributeFilter.visitCodeAttribute(Clazz, Method, CodeAttribute)"
  })
  void testVisitCodeAttribute3() {
    // Arrange
    KotlinAnnotationCounter attributeVisitor = new KotlinAnnotationCounter();
    OptimizationCodeAttributeFilter optimizationCodeAttributeFilter =
        new OptimizationCodeAttributeFilter(attributeVisitor, new KotlinAnnotationCounter());
    LibraryClass clazz = new LibraryClass();
    LibraryMethod method = new LibraryMethod(1, "Name", "Descriptor");

    CodeAttributeOptimizationInfo codeAttributeOptimizationInfo =
        mock(CodeAttributeOptimizationInfo.class);
    when(codeAttributeOptimizationInfo.isKept()).thenReturn(false);

    CodeAttribute codeAttribute = new CodeAttribute(1);
    codeAttribute.setProcessingInfo(codeAttributeOptimizationInfo);

    // Act
    optimizationCodeAttributeFilter.visitCodeAttribute(clazz, method, codeAttribute);

    // Assert
    verify(codeAttributeOptimizationInfo).isKept();
  }

  /**
   * Test {@link OptimizationCodeAttributeFilter#visitCodeAttribute(Clazz, Method, CodeAttribute)}.
   *
   * <p>Method under test: {@link OptimizationCodeAttributeFilter#visitCodeAttribute(Clazz, Method,
   * CodeAttribute)}
   */
  @Test
  @DisplayName("Test visitCodeAttribute(Clazz, Method, CodeAttribute)")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void OptimizationCodeAttributeFilter.visitCodeAttribute(Clazz, Method, CodeAttribute)"
  })
  void testVisitCodeAttribute4() {
    // Arrange
    OptimizationCodeAttributeFilter optimizationCodeAttributeFilter =
        new OptimizationCodeAttributeFilter(null, new KotlinAnnotationCounter());
    LibraryClass clazz = new LibraryClass();
    LibraryMethod method = new LibraryMethod(1, "Name", "Descriptor");

    CodeAttributeOptimizationInfo codeAttributeOptimizationInfo =
        mock(CodeAttributeOptimizationInfo.class);
    when(codeAttributeOptimizationInfo.isKept()).thenReturn(false);

    CodeAttribute codeAttribute = new CodeAttribute(1);
    codeAttribute.setProcessingInfo(codeAttributeOptimizationInfo);

    // Act
    optimizationCodeAttributeFilter.visitCodeAttribute(clazz, method, codeAttribute);

    // Assert
    verify(codeAttributeOptimizationInfo).isKept();
  }

  /**
   * Test {@link OptimizationCodeAttributeFilter#visitCodeAttribute(Clazz, Method, CodeAttribute)}.
   *
   * <p>Method under test: {@link OptimizationCodeAttributeFilter#visitCodeAttribute(Clazz, Method,
   * CodeAttribute)}
   */
  @Test
  @DisplayName("Test visitCodeAttribute(Clazz, Method, CodeAttribute)")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void OptimizationCodeAttributeFilter.visitCodeAttribute(Clazz, Method, CodeAttribute)"
  })
  void testVisitCodeAttribute5() {
    // Arrange
    AttributeShrinker attributeVisitor = new AttributeShrinker();
    OptimizationCodeAttributeFilter optimizationCodeAttributeFilter =
        new OptimizationCodeAttributeFilter(attributeVisitor, new KotlinAnnotationCounter());
    LibraryClass clazz = new LibraryClass();
    LibraryMethod method = new LibraryMethod(1, "Name", "Descriptor");

    CodeAttributeOptimizationInfo codeAttributeOptimizationInfo =
        mock(CodeAttributeOptimizationInfo.class);
    when(codeAttributeOptimizationInfo.isKept()).thenReturn(false);

    CodeAttribute codeAttribute = new CodeAttribute(1);
    codeAttribute.setProcessingInfo(codeAttributeOptimizationInfo);

    // Act
    optimizationCodeAttributeFilter.visitCodeAttribute(clazz, method, codeAttribute);

    // Assert
    verify(codeAttributeOptimizationInfo).isKept();
  }

  /**
   * Test {@link OptimizationCodeAttributeFilter#visitCodeAttribute(Clazz, Method, CodeAttribute)}.
   *
   * <p>Method under test: {@link OptimizationCodeAttributeFilter#visitCodeAttribute(Clazz, Method,
   * CodeAttribute)}
   */
  @Test
  @DisplayName("Test visitCodeAttribute(Clazz, Method, CodeAttribute)")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void OptimizationCodeAttributeFilter.visitCodeAttribute(Clazz, Method, CodeAttribute)"
  })
  void testVisitCodeAttribute6() {
    // Arrange
    KeepMarker attributeVisitor = new KeepMarker();
    OptimizationCodeAttributeFilter optimizationCodeAttributeFilter =
        new OptimizationCodeAttributeFilter(attributeVisitor, new KotlinAnnotationCounter());
    LibraryClass clazz = new LibraryClass();
    LibraryMethod method = new LibraryMethod(1, "Name", "Descriptor");

    CodeAttributeOptimizationInfo codeAttributeOptimizationInfo =
        mock(CodeAttributeOptimizationInfo.class);
    when(codeAttributeOptimizationInfo.isKept()).thenReturn(false);

    CodeAttribute codeAttribute = new CodeAttribute(1);
    codeAttribute.setProcessingInfo(codeAttributeOptimizationInfo);

    // Act
    optimizationCodeAttributeFilter.visitCodeAttribute(clazz, method, codeAttribute);

    // Assert
    verify(codeAttributeOptimizationInfo).isKept();
    Object processingInfo = codeAttribute.getProcessingInfo();
    assertTrue(processingInfo instanceof CodeAttributeOptimizationInfo);
    assertTrue(((CodeAttributeOptimizationInfo) processingInfo).isKept());
  }
}
