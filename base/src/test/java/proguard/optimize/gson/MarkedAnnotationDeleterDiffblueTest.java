package proguard.optimize.gson;

import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
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
   * Method under test:
   * {@link MarkedAnnotationDeleter#visitRuntimeVisibleParameterAnnotationsAttribute(Clazz, Method, RuntimeVisibleParameterAnnotationsAttribute)}
   */
  @Test
  void testVisitRuntimeVisibleParameterAnnotationsAttribute() {
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
   * Method under test:
   * {@link MarkedAnnotationDeleter#visitRuntimeVisibleParameterAnnotationsAttribute(Clazz, Method, RuntimeVisibleParameterAnnotationsAttribute)}
   */
  @Test
  void testVisitRuntimeVisibleParameterAnnotationsAttribute2() {
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
   * Method under test:
   * {@link MarkedAnnotationDeleter#visitRuntimeInvisibleParameterAnnotationsAttribute(Clazz, Method, RuntimeInvisibleParameterAnnotationsAttribute)}
   */
  @Test
  void testVisitRuntimeInvisibleParameterAnnotationsAttribute() {
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

  /**
   * Method under test:
   * {@link MarkedAnnotationDeleter#visitRuntimeInvisibleParameterAnnotationsAttribute(Clazz, Method, RuntimeInvisibleParameterAnnotationsAttribute)}
   */
  @Test
  void testVisitRuntimeInvisibleParameterAnnotationsAttribute2() {
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
}
