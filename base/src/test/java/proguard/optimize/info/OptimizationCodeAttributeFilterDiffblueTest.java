package proguard.optimize.info;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import proguard.classfile.Clazz;
import proguard.classfile.LibraryClass;
import proguard.classfile.LibraryMethod;
import proguard.classfile.Method;
import proguard.classfile.attribute.CodeAttribute;
import proguard.classfile.attribute.visitor.AttributeVisitor;
import proguard.fixer.kotlin.KotlinAnnotationCounter;
import proguard.obfuscate.RenamedFlagSetter;
import proguard.optimize.KeepMarker;

class OptimizationCodeAttributeFilterDiffblueTest {
  /**
   * Method under test:
   * {@link OptimizationCodeAttributeFilter#visitCodeAttribute(Clazz, Method, CodeAttribute)}
   */
  @Test
  void testVisitCodeAttribute() {
    // Arrange
    AttributeVisitor attributeVisitor = mock(AttributeVisitor.class);
    doNothing().when(attributeVisitor)
        .visitCodeAttribute(Mockito.<Clazz>any(), Mockito.<Method>any(), Mockito.<CodeAttribute>any());
    OptimizationCodeAttributeFilter optimizationCodeAttributeFilter = new OptimizationCodeAttributeFilter(
        attributeVisitor, new KotlinAnnotationCounter());
    LibraryClass clazz = new LibraryClass();
    LibraryMethod method = new LibraryMethod(1, "Name", "Descriptor");

    // Act
    optimizationCodeAttributeFilter.visitCodeAttribute(clazz, method, new CodeAttribute(1));

    // Assert
    verify(attributeVisitor).visitCodeAttribute(isA(Clazz.class), isA(Method.class), isA(CodeAttribute.class));
  }

  /**
   * Method under test:
   * {@link OptimizationCodeAttributeFilter#visitCodeAttribute(Clazz, Method, CodeAttribute)}
   */
  @Test
  void testVisitCodeAttribute2() {
    // Arrange
    AttributeVisitor attributeVisitor = mock(AttributeVisitor.class);
    OptimizationCodeAttributeFilter optimizationCodeAttributeFilter = new OptimizationCodeAttributeFilter(
        attributeVisitor, new KotlinAnnotationCounter());
    LibraryClass clazz = new LibraryClass();
    LibraryMethod method = new LibraryMethod(1, "Name", "Descriptor");

    CodeAttribute codeAttribute = mock(CodeAttribute.class);
    when(codeAttribute.getProcessingInfo()).thenReturn(new CodeAttributeOptimizationInfo());

    // Act
    optimizationCodeAttributeFilter.visitCodeAttribute(clazz, method, codeAttribute);

    // Assert
    verify(codeAttribute).getProcessingInfo();
  }

  /**
   * Method under test:
   * {@link OptimizationCodeAttributeFilter#visitCodeAttribute(Clazz, Method, CodeAttribute)}
   */
  @Test
  void testVisitCodeAttribute3() {
    // Arrange
    AttributeVisitor attributeVisitor = mock(AttributeVisitor.class);
    OptimizationCodeAttributeFilter optimizationCodeAttributeFilter = new OptimizationCodeAttributeFilter(
        attributeVisitor, new KotlinAnnotationCounter());
    LibraryClass clazz = new LibraryClass();
    LibraryMethod method = new LibraryMethod(1, "Name", "Descriptor");

    CodeAttributeOptimizationInfo codeAttributeOptimizationInfo = mock(CodeAttributeOptimizationInfo.class);
    when(codeAttributeOptimizationInfo.isKept()).thenReturn(true);
    CodeAttribute codeAttribute = mock(CodeAttribute.class);
    when(codeAttribute.getProcessingInfo()).thenReturn(codeAttributeOptimizationInfo);

    // Act
    optimizationCodeAttributeFilter.visitCodeAttribute(clazz, method, codeAttribute);

    // Assert
    verify(codeAttributeOptimizationInfo).isKept();
    verify(codeAttribute).getProcessingInfo();
  }

  /**
   * Method under test:
   * {@link OptimizationCodeAttributeFilter#visitCodeAttribute(Clazz, Method, CodeAttribute)}
   */
  @Test
  void testVisitCodeAttribute4() {
    // Arrange
    AttributeVisitor attributeVisitor = mock(AttributeVisitor.class);
    OptimizationCodeAttributeFilter optimizationCodeAttributeFilter = new OptimizationCodeAttributeFilter(
        attributeVisitor, new RenamedFlagSetter());
    LibraryClass clazz = new LibraryClass();
    LibraryMethod method = new LibraryMethod(1, "Name", "Descriptor");

    CodeAttributeOptimizationInfo codeAttributeOptimizationInfo = mock(CodeAttributeOptimizationInfo.class);
    when(codeAttributeOptimizationInfo.isKept()).thenReturn(true);
    CodeAttribute codeAttribute = mock(CodeAttribute.class);
    when(codeAttribute.getProcessingInfo()).thenReturn(codeAttributeOptimizationInfo);

    // Act and Assert
    assertThrows(UnsupportedOperationException.class,
        () -> optimizationCodeAttributeFilter.visitCodeAttribute(clazz, method, codeAttribute));
    verify(codeAttributeOptimizationInfo).isKept();
    verify(codeAttribute).getProcessingInfo();
  }

  /**
   * Method under test:
   * {@link OptimizationCodeAttributeFilter#visitCodeAttribute(Clazz, Method, CodeAttribute)}
   */
  @Test
  void testVisitCodeAttribute5() {
    // Arrange
    AttributeVisitor attributeVisitor = mock(AttributeVisitor.class);
    doNothing().when(attributeVisitor)
        .visitCodeAttribute(Mockito.<Clazz>any(), Mockito.<Method>any(), Mockito.<CodeAttribute>any());
    OptimizationCodeAttributeFilter optimizationCodeAttributeFilter = new OptimizationCodeAttributeFilter(
        attributeVisitor, new KotlinAnnotationCounter());
    LibraryClass clazz = new LibraryClass();
    LibraryMethod method = new LibraryMethod(1, "Name", "Descriptor");

    CodeAttributeOptimizationInfo codeAttributeOptimizationInfo = mock(CodeAttributeOptimizationInfo.class);
    when(codeAttributeOptimizationInfo.isKept()).thenReturn(false);
    CodeAttribute codeAttribute = mock(CodeAttribute.class);
    when(codeAttribute.getProcessingInfo()).thenReturn(codeAttributeOptimizationInfo);

    // Act
    optimizationCodeAttributeFilter.visitCodeAttribute(clazz, method, codeAttribute);

    // Assert
    verify(attributeVisitor).visitCodeAttribute(isA(Clazz.class), isA(Method.class), isA(CodeAttribute.class));
    verify(codeAttributeOptimizationInfo).isKept();
    verify(codeAttribute).getProcessingInfo();
  }

  /**
   * Method under test:
   * {@link OptimizationCodeAttributeFilter#visitCodeAttribute(Clazz, Method, CodeAttribute)}
   */
  @Test
  void testVisitCodeAttribute6() {
    // Arrange
    AttributeVisitor attributeVisitor = mock(AttributeVisitor.class);
    OptimizationCodeAttributeFilter optimizationCodeAttributeFilter = new OptimizationCodeAttributeFilter(
        attributeVisitor, new KeepMarker());
    LibraryClass clazz = new LibraryClass();
    LibraryMethod method = new LibraryMethod(1, "Name", "Descriptor");

    CodeAttributeOptimizationInfo codeAttributeOptimizationInfo = mock(CodeAttributeOptimizationInfo.class);
    when(codeAttributeOptimizationInfo.isKept()).thenReturn(true);
    CodeAttribute codeAttribute = mock(CodeAttribute.class);
    doNothing().when(codeAttribute).setProcessingInfo(Mockito.<Object>any());
    when(codeAttribute.getProcessingInfo()).thenReturn(codeAttributeOptimizationInfo);

    // Act
    optimizationCodeAttributeFilter.visitCodeAttribute(clazz, method, codeAttribute);

    // Assert
    verify(codeAttributeOptimizationInfo).isKept();
    verify(codeAttribute).getProcessingInfo();
    verify(codeAttribute).setProcessingInfo(isA(Object.class));
  }
}
