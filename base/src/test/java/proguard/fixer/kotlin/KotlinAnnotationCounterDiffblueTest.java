package proguard.fixer.kotlin;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import com.diffblue.cover.annotations.MethodsUnderTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
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
import proguard.shrink.ShortestUsageMarker;
import proguard.shrink.SimpleUsageMarker;

class KotlinAnnotationCounterDiffblueTest {
  /**
   * Test getters and setters.
   * <p>
   * Methods under test:
   * <ul>
   *   <li>{@link KotlinAnnotationCounter#KotlinAnnotationCounter()}
   *   <li>{@link KotlinAnnotationCounter#visitAnyAttribute(Clazz, Attribute)}
   *   <li>{@link KotlinAnnotationCounter#getCount()}
   * </ul>
   */
  @Test
  @DisplayName("Test getters and setters")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void proguard.fixer.kotlin.KotlinAnnotationCounter.<init>()",
      "void proguard.fixer.kotlin.KotlinAnnotationCounter.<init>(proguard.shrink.SimpleUsageMarker)",
      "int proguard.fixer.kotlin.KotlinAnnotationCounter.getCount()",
      "void proguard.fixer.kotlin.KotlinAnnotationCounter.visitAnyAttribute(proguard.classfile.Clazz, proguard.classfile.attribute.Attribute)"})
  void testGettersAndSetters() {
    // Arrange and Act
    KotlinAnnotationCounter actualKotlinAnnotationCounter = new KotlinAnnotationCounter();
    LibraryClass clazz = new LibraryClass();
    actualKotlinAnnotationCounter.visitAnyAttribute(clazz, new BootstrapMethodsAttribute());

    // Assert
    assertEquals(0, actualKotlinAnnotationCounter.getCount());
  }

  /**
   * Test getters and setters.
   * <ul>
   *   <li>When {@link SimpleUsageMarker} (default constructor).</li>
   * </ul>
   * <p>
   * Methods under test:
   * <ul>
   *   <li>{@link KotlinAnnotationCounter#KotlinAnnotationCounter(SimpleUsageMarker)}
   *   <li>{@link KotlinAnnotationCounter#visitAnyAttribute(Clazz, Attribute)}
   *   <li>{@link KotlinAnnotationCounter#getCount()}
   * </ul>
   */
  @Test
  @DisplayName("Test getters and setters; when SimpleUsageMarker (default constructor)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void proguard.fixer.kotlin.KotlinAnnotationCounter.<init>()",
      "void proguard.fixer.kotlin.KotlinAnnotationCounter.<init>(proguard.shrink.SimpleUsageMarker)",
      "int proguard.fixer.kotlin.KotlinAnnotationCounter.getCount()",
      "void proguard.fixer.kotlin.KotlinAnnotationCounter.visitAnyAttribute(proguard.classfile.Clazz, proguard.classfile.attribute.Attribute)"})
  void testGettersAndSetters_whenSimpleUsageMarker() {
    // Arrange and Act
    KotlinAnnotationCounter actualKotlinAnnotationCounter = new KotlinAnnotationCounter(new SimpleUsageMarker());
    LibraryClass clazz = new LibraryClass();
    actualKotlinAnnotationCounter.visitAnyAttribute(clazz, new BootstrapMethodsAttribute());

    // Assert
    assertEquals(0, actualKotlinAnnotationCounter.getCount());
  }

  /**
   * Test {@link KotlinAnnotationCounter#getParameterAnnotationCount(int)}.
   * <p>
   * Method under test: {@link KotlinAnnotationCounter#getParameterAnnotationCount(int)}
   */
  @Test
  @DisplayName("Test getParameterAnnotationCount(int)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"int proguard.fixer.kotlin.KotlinAnnotationCounter.getParameterAnnotationCount(int)"})
  void testGetParameterAnnotationCount() {
    // Arrange, Act and Assert
    assertEquals(-1, (new KotlinAnnotationCounter()).getParameterAnnotationCount(1));
  }

  /**
   * Test {@link KotlinAnnotationCounter#getParameterAnnotationCount(int)}.
   * <p>
   * Method under test: {@link KotlinAnnotationCounter#getParameterAnnotationCount(int)}
   */
  @Test
  @DisplayName("Test getParameterAnnotationCount(int)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"int proguard.fixer.kotlin.KotlinAnnotationCounter.getParameterAnnotationCount(int)"})
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
   * Test {@link KotlinAnnotationCounter#reset()}.
   * <p>
   * Method under test: {@link KotlinAnnotationCounter#reset()}
   */
  @Test
  @DisplayName("Test reset()")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "proguard.fixer.kotlin.KotlinAnnotationCounter proguard.fixer.kotlin.KotlinAnnotationCounter.reset()"})
  void testReset() {
    // Arrange
    KotlinAnnotationCounter kotlinAnnotationCounter = new KotlinAnnotationCounter();

    // Act and Assert
    assertSame(kotlinAnnotationCounter, kotlinAnnotationCounter.reset());
  }

  /**
   * Test {@link KotlinAnnotationCounter#visitAnyAnnotationsAttribute(Clazz, AnnotationsAttribute)}.
   * <ul>
   *   <li>Then calls {@link AnnotationsAttribute#annotationsAccept(Clazz, AnnotationVisitor)}.</li>
   * </ul>
   * <p>
   * Method under test: {@link KotlinAnnotationCounter#visitAnyAnnotationsAttribute(Clazz, AnnotationsAttribute)}
   */
  @Test
  @DisplayName("Test visitAnyAnnotationsAttribute(Clazz, AnnotationsAttribute); then calls annotationsAccept(Clazz, AnnotationVisitor)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "void proguard.fixer.kotlin.KotlinAnnotationCounter.visitAnyAnnotationsAttribute(proguard.classfile.Clazz, proguard.classfile.attribute.annotation.AnnotationsAttribute)"})
  void testVisitAnyAnnotationsAttribute_thenCallsAnnotationsAccept() {
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
   * Test {@link KotlinAnnotationCounter#visitAnyParameterAnnotationsAttribute(Clazz, Method, ParameterAnnotationsAttribute)}.
   * <ul>
   *   <li>Then calls {@link ParameterAnnotationsAttribute#annotationsAccept(Clazz, Method, AnnotationVisitor)}.</li>
   * </ul>
   * <p>
   * Method under test: {@link KotlinAnnotationCounter#visitAnyParameterAnnotationsAttribute(Clazz, Method, ParameterAnnotationsAttribute)}
   */
  @Test
  @DisplayName("Test visitAnyParameterAnnotationsAttribute(Clazz, Method, ParameterAnnotationsAttribute); then calls annotationsAccept(Clazz, Method, AnnotationVisitor)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "void proguard.fixer.kotlin.KotlinAnnotationCounter.visitAnyParameterAnnotationsAttribute(proguard.classfile.Clazz, proguard.classfile.Method, proguard.classfile.attribute.annotation.ParameterAnnotationsAttribute)"})
  void testVisitAnyParameterAnnotationsAttribute_thenCallsAnnotationsAccept() {
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
   * Test {@link KotlinAnnotationCounter#visitAnnotation(Clazz, Annotation)} with {@code clazz}, {@code annotation}.
   * <p>
   * Method under test: {@link KotlinAnnotationCounter#visitAnnotation(Clazz, Annotation)}
   */
  @Test
  @DisplayName("Test visitAnnotation(Clazz, Annotation) with 'clazz', 'annotation'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "void proguard.fixer.kotlin.KotlinAnnotationCounter.visitAnnotation(proguard.classfile.Clazz, proguard.classfile.attribute.annotation.Annotation)"})
  void testVisitAnnotationWithClazzAnnotation() {
    // Arrange
    KotlinAnnotationCounter kotlinAnnotationCounter = new KotlinAnnotationCounter(new SimpleUsageMarker());
    LibraryClass clazz = new LibraryClass();

    // Act
    kotlinAnnotationCounter.visitAnnotation(clazz, new Annotation());

    // Assert that nothing has changed
    assertEquals(0, kotlinAnnotationCounter.getCount());
  }

  /**
   * Test {@link KotlinAnnotationCounter#visitAnnotation(Clazz, Annotation)} with {@code clazz}, {@code annotation}.
   * <p>
   * Method under test: {@link KotlinAnnotationCounter#visitAnnotation(Clazz, Annotation)}
   */
  @Test
  @DisplayName("Test visitAnnotation(Clazz, Annotation) with 'clazz', 'annotation'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "void proguard.fixer.kotlin.KotlinAnnotationCounter.visitAnnotation(proguard.classfile.Clazz, proguard.classfile.attribute.annotation.Annotation)"})
  void testVisitAnnotationWithClazzAnnotation2() {
    // Arrange
    KotlinAnnotationCounter kotlinAnnotationCounter = new KotlinAnnotationCounter(new ShortestUsageMarker());
    LibraryClass clazz = new LibraryClass();

    // Act
    kotlinAnnotationCounter.visitAnnotation(clazz, new Annotation());

    // Assert that nothing has changed
    assertEquals(0, kotlinAnnotationCounter.getCount());
  }

  /**
   * Test {@link KotlinAnnotationCounter#visitAnnotation(Clazz, Annotation)} with {@code clazz}, {@code annotation}.
   * <ul>
   *   <li>Then {@link KotlinAnnotationCounter#KotlinAnnotationCounter()} Count is one.</li>
   * </ul>
   * <p>
   * Method under test: {@link KotlinAnnotationCounter#visitAnnotation(Clazz, Annotation)}
   */
  @Test
  @DisplayName("Test visitAnnotation(Clazz, Annotation) with 'clazz', 'annotation'; then KotlinAnnotationCounter() Count is one")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "void proguard.fixer.kotlin.KotlinAnnotationCounter.visitAnnotation(proguard.classfile.Clazz, proguard.classfile.attribute.annotation.Annotation)"})
  void testVisitAnnotationWithClazzAnnotation_thenKotlinAnnotationCounterCountIsOne() {
    // Arrange
    KotlinAnnotationCounter kotlinAnnotationCounter = new KotlinAnnotationCounter();
    LibraryClass clazz = new LibraryClass();

    // Act
    kotlinAnnotationCounter.visitAnnotation(clazz, new Annotation());

    // Assert
    assertEquals(1, kotlinAnnotationCounter.getCount());
  }
}
