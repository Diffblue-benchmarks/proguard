package proguard.optimize.info;

import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import com.diffblue.cover.annotations.MethodsUnderTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import proguard.classfile.Clazz;
import proguard.classfile.LibraryClass;
import proguard.classfile.LibraryMethod;
import proguard.classfile.Method;
import proguard.classfile.attribute.CodeAttribute;
import proguard.fixer.kotlin.KotlinAnnotationCounter;
import proguard.optimize.KeepMarker;
import proguard.util.SimpleProcessable;

class OptimizationCodeAttributeFilterDiffblueTest {
  /**
   * Test {@link OptimizationCodeAttributeFilter#visitCodeAttribute(Clazz, Method, CodeAttribute)}.
   * <ul>
   *   <li>Given {@link CodeAttributeOptimizationInfo} (default constructor).</li>
   * </ul>
   * <p>
   * Method under test: {@link OptimizationCodeAttributeFilter#visitCodeAttribute(Clazz, Method, CodeAttribute)}
   */
  @Test
  @DisplayName("Test visitCodeAttribute(Clazz, Method, CodeAttribute); given CodeAttributeOptimizationInfo (default constructor)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void OptimizationCodeAttributeFilter.visitCodeAttribute(Clazz, Method, CodeAttribute)"})
  void testVisitCodeAttribute_givenCodeAttributeOptimizationInfo() {
    // Arrange
    KotlinAnnotationCounter attributeVisitor = new KotlinAnnotationCounter();
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
   * Test {@link OptimizationCodeAttributeFilter#visitCodeAttribute(Clazz, Method, CodeAttribute)}.
   * <ul>
   *   <li>Given {@link CodeAttributeOptimizationInfo} {@link CodeAttributeOptimizationInfo#isKept()} return {@code false}.</li>
   * </ul>
   * <p>
   * Method under test: {@link OptimizationCodeAttributeFilter#visitCodeAttribute(Clazz, Method, CodeAttribute)}
   */
  @Test
  @DisplayName("Test visitCodeAttribute(Clazz, Method, CodeAttribute); given CodeAttributeOptimizationInfo isKept() return 'false'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void OptimizationCodeAttributeFilter.visitCodeAttribute(Clazz, Method, CodeAttribute)"})
  void testVisitCodeAttribute_givenCodeAttributeOptimizationInfoIsKeptReturnFalse() {
    // Arrange
    KotlinAnnotationCounter attributeVisitor = new KotlinAnnotationCounter();
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
    verify(codeAttributeOptimizationInfo).isKept();
    verify(codeAttribute).getProcessingInfo();
  }

  /**
   * Test {@link OptimizationCodeAttributeFilter#visitCodeAttribute(Clazz, Method, CodeAttribute)}.
   * <ul>
   *   <li>Then calls {@link CodeAttributeOptimizationInfo#isKept()}.</li>
   * </ul>
   * <p>
   * Method under test: {@link OptimizationCodeAttributeFilter#visitCodeAttribute(Clazz, Method, CodeAttribute)}
   */
  @Test
  @DisplayName("Test visitCodeAttribute(Clazz, Method, CodeAttribute); then calls isKept()")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void OptimizationCodeAttributeFilter.visitCodeAttribute(Clazz, Method, CodeAttribute)"})
  void testVisitCodeAttribute_thenCallsIsKept() {
    // Arrange
    KotlinAnnotationCounter attributeVisitor = new KotlinAnnotationCounter();
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
   * Test {@link OptimizationCodeAttributeFilter#visitCodeAttribute(Clazz, Method, CodeAttribute)}.
   * <ul>
   *   <li>Then calls {@link SimpleProcessable#setProcessingInfo(Object)}.</li>
   * </ul>
   * <p>
   * Method under test: {@link OptimizationCodeAttributeFilter#visitCodeAttribute(Clazz, Method, CodeAttribute)}
   */
  @Test
  @DisplayName("Test visitCodeAttribute(Clazz, Method, CodeAttribute); then calls setProcessingInfo(Object)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void OptimizationCodeAttributeFilter.visitCodeAttribute(Clazz, Method, CodeAttribute)"})
  void testVisitCodeAttribute_thenCallsSetProcessingInfo() {
    // Arrange
    KotlinAnnotationCounter attributeVisitor = new KotlinAnnotationCounter();
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
