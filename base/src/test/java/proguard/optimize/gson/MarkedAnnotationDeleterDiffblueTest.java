package proguard.optimize.gson;

import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import com.diffblue.cover.annotations.MethodsUnderTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import proguard.classfile.Clazz;
import proguard.classfile.Method;
import proguard.classfile.ProgramClass;
import proguard.classfile.ProgramMethod;
import proguard.classfile.attribute.annotation.RuntimeInvisibleParameterAnnotationsAttribute;
import proguard.classfile.attribute.annotation.RuntimeVisibleParameterAnnotationsAttribute;

class MarkedAnnotationDeleterDiffblueTest {
  /**
   * Test {@link MarkedAnnotationDeleter#visitRuntimeVisibleParameterAnnotationsAttribute(Clazz, Method, RuntimeVisibleParameterAnnotationsAttribute)}.
   * <p>
   * Method under test: {@link MarkedAnnotationDeleter#visitRuntimeVisibleParameterAnnotationsAttribute(Clazz, Method, RuntimeVisibleParameterAnnotationsAttribute)}
   */
  @Test
  @DisplayName("Test visitRuntimeVisibleParameterAnnotationsAttribute(Clazz, Method, RuntimeVisibleParameterAnnotationsAttribute)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "void proguard.optimize.gson.MarkedAnnotationDeleter.visitRuntimeVisibleParameterAnnotationsAttribute(proguard.classfile.Clazz, proguard.classfile.Method, proguard.classfile.attribute.annotation.RuntimeVisibleParameterAnnotationsAttribute)"})
  void testVisitRuntimeVisibleParameterAnnotationsAttribute() {
    // Arrange
    MarkedAnnotationDeleter markedAnnotationDeleter = new MarkedAnnotationDeleter("Mark");
    ProgramClass clazz = mock(ProgramClass.class);
    doNothing().when(clazz).addExtraFeatureName(Mockito.<String>any());
    clazz.addExtraFeatureName("RuntimeVisibleParameterAnnotations");

    // Act
    markedAnnotationDeleter.visitRuntimeVisibleParameterAnnotationsAttribute(clazz, null,
        new RuntimeVisibleParameterAnnotationsAttribute());

    // Assert
    verify(clazz).addExtraFeatureName(eq("RuntimeVisibleParameterAnnotations"));
  }

  /**
   * Test {@link MarkedAnnotationDeleter#visitRuntimeVisibleParameterAnnotationsAttribute(Clazz, Method, RuntimeVisibleParameterAnnotationsAttribute)}.
   * <ul>
   *   <li>When {@link ProgramMethod#ProgramMethod()}.</li>
   * </ul>
   * <p>
   * Method under test: {@link MarkedAnnotationDeleter#visitRuntimeVisibleParameterAnnotationsAttribute(Clazz, Method, RuntimeVisibleParameterAnnotationsAttribute)}
   */
  @Test
  @DisplayName("Test visitRuntimeVisibleParameterAnnotationsAttribute(Clazz, Method, RuntimeVisibleParameterAnnotationsAttribute); when ProgramMethod()")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "void proguard.optimize.gson.MarkedAnnotationDeleter.visitRuntimeVisibleParameterAnnotationsAttribute(proguard.classfile.Clazz, proguard.classfile.Method, proguard.classfile.attribute.annotation.RuntimeVisibleParameterAnnotationsAttribute)"})
  void testVisitRuntimeVisibleParameterAnnotationsAttribute_whenProgramMethod() {
    // Arrange
    MarkedAnnotationDeleter markedAnnotationDeleter = new MarkedAnnotationDeleter("Mark");
    ProgramClass clazz = mock(ProgramClass.class);
    doNothing().when(clazz).addExtraFeatureName(Mockito.<String>any());
    clazz.addExtraFeatureName("RuntimeVisibleParameterAnnotations");
    ProgramMethod method = new ProgramMethod();

    // Act
    markedAnnotationDeleter.visitRuntimeVisibleParameterAnnotationsAttribute(clazz, method,
        new RuntimeVisibleParameterAnnotationsAttribute());

    // Assert
    verify(clazz).addExtraFeatureName(eq("RuntimeVisibleParameterAnnotations"));
  }

  /**
   * Test {@link MarkedAnnotationDeleter#visitRuntimeInvisibleParameterAnnotationsAttribute(Clazz, Method, RuntimeInvisibleParameterAnnotationsAttribute)}.
   * <p>
   * Method under test: {@link MarkedAnnotationDeleter#visitRuntimeInvisibleParameterAnnotationsAttribute(Clazz, Method, RuntimeInvisibleParameterAnnotationsAttribute)}
   */
  @Test
  @DisplayName("Test visitRuntimeInvisibleParameterAnnotationsAttribute(Clazz, Method, RuntimeInvisibleParameterAnnotationsAttribute)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "void proguard.optimize.gson.MarkedAnnotationDeleter.visitRuntimeInvisibleParameterAnnotationsAttribute(proguard.classfile.Clazz, proguard.classfile.Method, proguard.classfile.attribute.annotation.RuntimeInvisibleParameterAnnotationsAttribute)"})
  void testVisitRuntimeInvisibleParameterAnnotationsAttribute() {
    // Arrange
    MarkedAnnotationDeleter markedAnnotationDeleter = new MarkedAnnotationDeleter("Mark");
    ProgramClass clazz = mock(ProgramClass.class);
    doNothing().when(clazz).addExtraFeatureName(Mockito.<String>any());
    clazz.addExtraFeatureName("RuntimeInvisibleParameterAnnotations");

    // Act
    markedAnnotationDeleter.visitRuntimeInvisibleParameterAnnotationsAttribute(clazz, null,
        new RuntimeInvisibleParameterAnnotationsAttribute());

    // Assert
    verify(clazz).addExtraFeatureName(eq("RuntimeInvisibleParameterAnnotations"));
  }

  /**
   * Test {@link MarkedAnnotationDeleter#visitRuntimeInvisibleParameterAnnotationsAttribute(Clazz, Method, RuntimeInvisibleParameterAnnotationsAttribute)}.
   * <ul>
   *   <li>When {@link ProgramMethod#ProgramMethod()}.</li>
   * </ul>
   * <p>
   * Method under test: {@link MarkedAnnotationDeleter#visitRuntimeInvisibleParameterAnnotationsAttribute(Clazz, Method, RuntimeInvisibleParameterAnnotationsAttribute)}
   */
  @Test
  @DisplayName("Test visitRuntimeInvisibleParameterAnnotationsAttribute(Clazz, Method, RuntimeInvisibleParameterAnnotationsAttribute); when ProgramMethod()")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "void proguard.optimize.gson.MarkedAnnotationDeleter.visitRuntimeInvisibleParameterAnnotationsAttribute(proguard.classfile.Clazz, proguard.classfile.Method, proguard.classfile.attribute.annotation.RuntimeInvisibleParameterAnnotationsAttribute)"})
  void testVisitRuntimeInvisibleParameterAnnotationsAttribute_whenProgramMethod() {
    // Arrange
    MarkedAnnotationDeleter markedAnnotationDeleter = new MarkedAnnotationDeleter("Mark");
    ProgramClass clazz = mock(ProgramClass.class);
    doNothing().when(clazz).addExtraFeatureName(Mockito.<String>any());
    clazz.addExtraFeatureName("RuntimeInvisibleParameterAnnotations");
    ProgramMethod method = new ProgramMethod();

    // Act
    markedAnnotationDeleter.visitRuntimeInvisibleParameterAnnotationsAttribute(clazz, method,
        new RuntimeInvisibleParameterAnnotationsAttribute());

    // Assert
    verify(clazz).addExtraFeatureName(eq("RuntimeInvisibleParameterAnnotations"));
  }
}
