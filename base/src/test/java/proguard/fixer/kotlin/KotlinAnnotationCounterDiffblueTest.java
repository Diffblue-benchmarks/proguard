package proguard.fixer.kotlin;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import proguard.classfile.Clazz;
import proguard.classfile.LibraryClass;
import proguard.classfile.LibraryMethod;
import proguard.classfile.Method;
import proguard.classfile.attribute.Attribute;
import proguard.classfile.attribute.BootstrapMethodsAttribute;
import proguard.classfile.attribute.annotation.Annotation;
import proguard.classfile.attribute.annotation.AnnotationsAttribute;
import proguard.classfile.attribute.annotation.ParameterAnnotationsAttribute;
import proguard.classfile.attribute.annotation.RuntimeInvisibleAnnotationsAttribute;
import proguard.classfile.attribute.annotation.RuntimeInvisibleParameterAnnotationsAttribute;
import proguard.classfile.attribute.annotation.visitor.AnnotationVisitor;
import proguard.shrink.SimpleUsageMarker;

class KotlinAnnotationCounterDiffblueTest {
  /**
   * Method under test:
   * {@link KotlinAnnotationCounter#getParameterAnnotationCount(int)}
   */
  @Test
  void testGetParameterAnnotationCount() {
    // Arrange, Act and Assert
    assertEquals(-1, (new KotlinAnnotationCounter()).getParameterAnnotationCount(1));
  }

  /**
   * Method under test:
   * {@link KotlinAnnotationCounter#getParameterAnnotationCount(int)}
   */
  @Test
  void testGetParameterAnnotationCount2() {
    // Arrange
    KotlinAnnotationCounter kotlinAnnotationCounter = new KotlinAnnotationCounter();
    LibraryClass clazz = new LibraryClass();
    LibraryMethod method = new LibraryMethod(-1, "Name", "Descriptor");

    kotlinAnnotationCounter.visitAnyParameterAnnotationsAttribute(clazz, method,
        new RuntimeInvisibleParameterAnnotationsAttribute());

    // Act and Assert
    assertEquals(-1, kotlinAnnotationCounter.getParameterAnnotationCount(1));
  }

  /**
   * Method under test: {@link KotlinAnnotationCounter#reset()}
   */
  @Test
  void testReset() {
    // Arrange
    KotlinAnnotationCounter kotlinAnnotationCounter = new KotlinAnnotationCounter();

    // Act
    KotlinAnnotationCounter actualResetResult = kotlinAnnotationCounter.reset();

    // Assert
    assertEquals(0, actualResetResult.getCount());
    assertSame(kotlinAnnotationCounter, actualResetResult);
  }

  /**
   * Method under test:
   * {@link KotlinAnnotationCounter#visitAnyAnnotationsAttribute(Clazz, AnnotationsAttribute)}
   */
  @Test
  void testVisitAnyAnnotationsAttribute() {
    // Arrange
    KotlinAnnotationCounter kotlinAnnotationCounter = new KotlinAnnotationCounter();
    LibraryClass clazz = new LibraryClass();
    RuntimeInvisibleAnnotationsAttribute annotationsAttribute = mock(RuntimeInvisibleAnnotationsAttribute.class);
    doNothing().when(annotationsAttribute).annotationsAccept(Mockito.<Clazz>any(), Mockito.<AnnotationVisitor>any());

    // Act
    kotlinAnnotationCounter.visitAnyAnnotationsAttribute(clazz, annotationsAttribute);

    // Assert
    verify(annotationsAttribute).annotationsAccept(isA(Clazz.class), isA(AnnotationVisitor.class));
  }

  /**
   * Method under test:
   * {@link KotlinAnnotationCounter#visitAnyParameterAnnotationsAttribute(Clazz, Method, ParameterAnnotationsAttribute)}
   */
  @Test
  void testVisitAnyParameterAnnotationsAttribute() {
    // Arrange
    KotlinAnnotationCounter kotlinAnnotationCounter = new KotlinAnnotationCounter();
    LibraryClass clazz = new LibraryClass();
    LibraryMethod method = new LibraryMethod(1, "Name", "Descriptor");

    RuntimeInvisibleParameterAnnotationsAttribute parameterAnnotationsAttribute = mock(
        RuntimeInvisibleParameterAnnotationsAttribute.class);
    doNothing().when(parameterAnnotationsAttribute)
        .annotationsAccept(Mockito.<Clazz>any(), Mockito.<Method>any(), Mockito.<AnnotationVisitor>any());

    // Act
    kotlinAnnotationCounter.visitAnyParameterAnnotationsAttribute(clazz, method, parameterAnnotationsAttribute);

    // Assert
    verify(parameterAnnotationsAttribute).annotationsAccept(isA(Clazz.class), isA(Method.class),
        isA(AnnotationVisitor.class));
  }

  /**
   * Method under test:
   * {@link KotlinAnnotationCounter#visitAnnotation(Clazz, Annotation)}
   */
  @Test
  void testVisitAnnotation() {
    // Arrange
    KotlinAnnotationCounter kotlinAnnotationCounter = new KotlinAnnotationCounter();
    LibraryClass clazz = new LibraryClass();

    // Act
    kotlinAnnotationCounter.visitAnnotation(clazz, new Annotation());

    // Assert
    assertEquals(1, kotlinAnnotationCounter.getCount());
  }

  /**
   * Methods under test:
   * <ul>
   *   <li>{@link KotlinAnnotationCounter#KotlinAnnotationCounter()}
   *   <li>{@link KotlinAnnotationCounter#visitAnyAttribute(Clazz, Attribute)}
   *   <li>{@link KotlinAnnotationCounter#getCount()}
   * </ul>
   */
  @Test
  void testGettersAndSetters() {
    // Arrange and Act
    KotlinAnnotationCounter actualKotlinAnnotationCounter = new KotlinAnnotationCounter();
    LibraryClass clazz = new LibraryClass();
    actualKotlinAnnotationCounter.visitAnyAttribute(clazz, new BootstrapMethodsAttribute());

    // Assert that nothing has changed
    assertEquals(0, actualKotlinAnnotationCounter.getCount());
  }

  /**
   * Methods under test:
   * <ul>
   *   <li>
   * {@link KotlinAnnotationCounter#KotlinAnnotationCounter(SimpleUsageMarker)}
   *   <li>{@link KotlinAnnotationCounter#visitAnyAttribute(Clazz, Attribute)}
   *   <li>{@link KotlinAnnotationCounter#getCount()}
   * </ul>
   */
  @Test
  void testGettersAndSetters2() {
    // Arrange and Act
    KotlinAnnotationCounter actualKotlinAnnotationCounter = new KotlinAnnotationCounter(new SimpleUsageMarker());
    LibraryClass clazz = new LibraryClass();
    actualKotlinAnnotationCounter.visitAnyAttribute(clazz, new BootstrapMethodsAttribute());

    // Assert that nothing has changed
    assertEquals(0, actualKotlinAnnotationCounter.getCount());
  }
}
