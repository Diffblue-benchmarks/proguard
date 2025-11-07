package proguard.shrink;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.atLeast;
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
import proguard.classfile.LibraryField;
import proguard.classfile.LibraryMethod;
import proguard.classfile.Method;
import proguard.classfile.ProgramClass;
import proguard.classfile.ProgramMethod;
import proguard.classfile.attribute.annotation.Annotation;
import proguard.classfile.attribute.annotation.AnnotationDefaultAttribute;
import proguard.classfile.attribute.annotation.AnnotationElementValue;
import proguard.classfile.attribute.annotation.AnnotationsAttribute;
import proguard.classfile.attribute.annotation.ArrayElementValue;
import proguard.classfile.attribute.annotation.ClassElementValue;
import proguard.classfile.attribute.annotation.ConstantElementValue;
import proguard.classfile.attribute.annotation.ElementValue;
import proguard.classfile.attribute.annotation.EnumConstantElementValue;
import proguard.classfile.attribute.annotation.ParameterAnnotationsAttribute;
import proguard.classfile.attribute.annotation.RuntimeInvisibleAnnotationsAttribute;
import proguard.classfile.attribute.annotation.RuntimeInvisibleParameterAnnotationsAttribute;
import proguard.classfile.constant.ClassConstant;
import proguard.classfile.constant.Constant;
import proguard.classfile.constant.DoubleConstant;
import proguard.classfile.constant.DynamicConstant;
import proguard.classfile.constant.FieldrefConstant;
import proguard.classfile.constant.FloatConstant;
import proguard.classfile.constant.IntegerConstant;
import proguard.testutils.cpa.NamedClass;
import proguard.util.Processable;
import proguard.util.SimpleProcessable;

class AnnotationUsageMarkerDiffblueTest {
  /**
   * Test {@link AnnotationUsageMarker#visitAnyAnnotationsAttribute(Clazz, AnnotationsAttribute)}.
   * <ul>
   *   <li>Then array length is one.</li>
   * </ul>
   * <p>
   * Method under test: {@link AnnotationUsageMarker#visitAnyAnnotationsAttribute(Clazz, AnnotationsAttribute)}
   */
  @Test
  @DisplayName("Test visitAnyAnnotationsAttribute(Clazz, AnnotationsAttribute); then array length is one")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void AnnotationUsageMarker.visitAnyAnnotationsAttribute(Clazz, AnnotationsAttribute)"})
  void testVisitAnyAnnotationsAttribute_thenArrayLengthIsOne() {
    // Arrange
    AnnotationUsageMarker annotationUsageMarker = new AnnotationUsageMarker(new ClassUsageMarker());
    LibraryClass clazz = new LibraryClass();
    RuntimeInvisibleAnnotationsAttribute annotationsAttribute = new RuntimeInvisibleAnnotationsAttribute(1, 1,
        new Annotation[]{new Annotation()});

    // Act
    annotationUsageMarker.visitAnyAnnotationsAttribute(clazz, annotationsAttribute);

    // Assert
    assertEquals(1, annotationsAttribute.annotations.length);
  }

  /**
   * Test {@link AnnotationUsageMarker#visitAnyParameterAnnotationsAttribute(Clazz, Method, ParameterAnnotationsAttribute)}.
   * <ul>
   *   <li>Then array length is one.</li>
   * </ul>
   * <p>
   * Method under test: {@link AnnotationUsageMarker#visitAnyParameterAnnotationsAttribute(Clazz, Method, ParameterAnnotationsAttribute)}
   */
  @Test
  @DisplayName("Test visitAnyParameterAnnotationsAttribute(Clazz, Method, ParameterAnnotationsAttribute); then array length is one")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "void AnnotationUsageMarker.visitAnyParameterAnnotationsAttribute(Clazz, Method, ParameterAnnotationsAttribute)"})
  void testVisitAnyParameterAnnotationsAttribute_thenArrayLengthIsOne() {
    // Arrange
    AnnotationUsageMarker annotationUsageMarker = new AnnotationUsageMarker(new ClassUsageMarker());
    LibraryClass clazz = new LibraryClass();
    LibraryMethod method = new LibraryMethod(1, "Name", "Descriptor");

    RuntimeInvisibleParameterAnnotationsAttribute parameterAnnotationsAttribute = new RuntimeInvisibleParameterAnnotationsAttribute(
        1, 1, new int[]{1, 1, 3, 1}, new Annotation[][]{new Annotation[]{new Annotation()}});

    // Act
    annotationUsageMarker.visitAnyParameterAnnotationsAttribute(clazz, method, parameterAnnotationsAttribute);

    // Assert
    Annotation[][] annotationArray = parameterAnnotationsAttribute.parameterAnnotations;
    assertEquals(1, (annotationArray[0]).length);
    assertEquals(1, annotationArray.length);
  }

  /**
   * Test {@link AnnotationUsageMarker#visitAnnotationDefaultAttribute(Clazz, Method, AnnotationDefaultAttribute)}.
   * <p>
   * Method under test: {@link AnnotationUsageMarker#visitAnnotationDefaultAttribute(Clazz, Method, AnnotationDefaultAttribute)}
   */
  @Test
  @DisplayName("Test visitAnnotationDefaultAttribute(Clazz, Method, AnnotationDefaultAttribute)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "void AnnotationUsageMarker.visitAnnotationDefaultAttribute(Clazz, Method, AnnotationDefaultAttribute)"})
  void testVisitAnnotationDefaultAttribute() {
    // Arrange
    AnnotationUsageMarker annotationUsageMarker = new AnnotationUsageMarker(new ClassUsageMarker());
    LibraryClass clazz = new LibraryClass();
    LibraryMethod method = new LibraryMethod(1, "Name", "Descriptor");

    AnnotationDefaultAttribute annotationDefaultAttribute = new AnnotationDefaultAttribute(1,
        new AnnotationElementValue(1, new Annotation()));

    // Act
    annotationUsageMarker.visitAnnotationDefaultAttribute(clazz, method, annotationDefaultAttribute);

    // Assert
    assertTrue(annotationDefaultAttribute.defaultValue instanceof AnnotationElementValue);
  }

  /**
   * Test {@link AnnotationUsageMarker#visitAnnotationDefaultAttribute(Clazz, Method, AnnotationDefaultAttribute)}.
   * <p>
   * Method under test: {@link AnnotationUsageMarker#visitAnnotationDefaultAttribute(Clazz, Method, AnnotationDefaultAttribute)}
   */
  @Test
  @DisplayName("Test visitAnnotationDefaultAttribute(Clazz, Method, AnnotationDefaultAttribute)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "void AnnotationUsageMarker.visitAnnotationDefaultAttribute(Clazz, Method, AnnotationDefaultAttribute)"})
  void testVisitAnnotationDefaultAttribute2() {
    // Arrange
    AnnotationUsageMarker annotationUsageMarker = new AnnotationUsageMarker(new ClassUsageMarker());
    LibraryClass clazz = new LibraryClass();
    LibraryMethod method = new LibraryMethod(1, "Name", "Descriptor");

    AnnotationDefaultAttribute annotationDefaultAttribute = new AnnotationDefaultAttribute(1, new ArrayElementValue());

    // Act
    annotationUsageMarker.visitAnnotationDefaultAttribute(clazz, method, annotationDefaultAttribute);

    // Assert
    assertTrue(annotationDefaultAttribute.defaultValue instanceof ArrayElementValue);
  }

  /**
   * Test {@link AnnotationUsageMarker#visitAnnotationDefaultAttribute(Clazz, Method, AnnotationDefaultAttribute)}.
   * <p>
   * Method under test: {@link AnnotationUsageMarker#visitAnnotationDefaultAttribute(Clazz, Method, AnnotationDefaultAttribute)}
   */
  @Test
  @DisplayName("Test visitAnnotationDefaultAttribute(Clazz, Method, AnnotationDefaultAttribute)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "void AnnotationUsageMarker.visitAnnotationDefaultAttribute(Clazz, Method, AnnotationDefaultAttribute)"})
  void testVisitAnnotationDefaultAttribute3() {
    // Arrange
    AnnotationUsageMarker annotationUsageMarker = new AnnotationUsageMarker(new ClassUsageMarker());
    LibraryClass clazz = new LibraryClass();
    LibraryMethod method = new LibraryMethod(1, "Name", "Descriptor");

    AnnotationDefaultAttribute annotationDefaultAttribute = new AnnotationDefaultAttribute(1,
        new ClassElementValue(1, 1));

    // Act
    annotationUsageMarker.visitAnnotationDefaultAttribute(clazz, method, annotationDefaultAttribute);

    // Assert
    assertTrue(annotationDefaultAttribute.defaultValue instanceof ClassElementValue);
  }

  /**
   * Test {@link AnnotationUsageMarker#visitAnnotationDefaultAttribute(Clazz, Method, AnnotationDefaultAttribute)}.
   * <p>
   * Method under test: {@link AnnotationUsageMarker#visitAnnotationDefaultAttribute(Clazz, Method, AnnotationDefaultAttribute)}
   */
  @Test
  @DisplayName("Test visitAnnotationDefaultAttribute(Clazz, Method, AnnotationDefaultAttribute)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "void AnnotationUsageMarker.visitAnnotationDefaultAttribute(Clazz, Method, AnnotationDefaultAttribute)"})
  void testVisitAnnotationDefaultAttribute4() {
    // Arrange
    AnnotationUsageMarker annotationUsageMarker = new AnnotationUsageMarker(new ClassUsageMarker());
    LibraryClass clazz = new LibraryClass();
    LibraryMethod method = new LibraryMethod(1, "Name", "Descriptor");

    AnnotationDefaultAttribute annotationDefaultAttribute = new AnnotationDefaultAttribute(1,
        new ConstantElementValue('\u0001'));

    // Act
    annotationUsageMarker.visitAnnotationDefaultAttribute(clazz, method, annotationDefaultAttribute);

    // Assert
    assertTrue(annotationDefaultAttribute.defaultValue instanceof ConstantElementValue);
  }

  /**
   * Test {@link AnnotationUsageMarker#visitAnnotationDefaultAttribute(Clazz, Method, AnnotationDefaultAttribute)}.
   * <p>
   * Method under test: {@link AnnotationUsageMarker#visitAnnotationDefaultAttribute(Clazz, Method, AnnotationDefaultAttribute)}
   */
  @Test
  @DisplayName("Test visitAnnotationDefaultAttribute(Clazz, Method, AnnotationDefaultAttribute)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "void AnnotationUsageMarker.visitAnnotationDefaultAttribute(Clazz, Method, AnnotationDefaultAttribute)"})
  void testVisitAnnotationDefaultAttribute5() {
    // Arrange
    AnnotationUsageMarker annotationUsageMarker = new AnnotationUsageMarker(new ClassUsageMarker());
    LibraryClass clazz = new LibraryClass();
    LibraryMethod method = new LibraryMethod(1, "Name", "Descriptor");

    AnnotationDefaultAttribute annotationDefaultAttribute = new AnnotationDefaultAttribute(1,
        new EnumConstantElementValue(1, 1, 1));

    // Act
    annotationUsageMarker.visitAnnotationDefaultAttribute(clazz, method, annotationDefaultAttribute);

    // Assert
    assertTrue(annotationDefaultAttribute.defaultValue instanceof EnumConstantElementValue);
  }

  /**
   * Test {@link AnnotationUsageMarker#visitAnnotationDefaultAttribute(Clazz, Method, AnnotationDefaultAttribute)}.
   * <p>
   * Method under test: {@link AnnotationUsageMarker#visitAnnotationDefaultAttribute(Clazz, Method, AnnotationDefaultAttribute)}
   */
  @Test
  @DisplayName("Test visitAnnotationDefaultAttribute(Clazz, Method, AnnotationDefaultAttribute)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "void AnnotationUsageMarker.visitAnnotationDefaultAttribute(Clazz, Method, AnnotationDefaultAttribute)"})
  void testVisitAnnotationDefaultAttribute6() {
    // Arrange
    ShortestUsageMarker usageMarker = new ShortestUsageMarker();
    AnnotationUsageMarker annotationUsageMarker = new AnnotationUsageMarker(
        new ShortestClassUsageMarker(usageMarker, "Just cause"));
    LibraryClass clazz = new LibraryClass();
    LibraryMethod method = new LibraryMethod(1, "Name", "Descriptor");

    AnnotationDefaultAttribute annotationDefaultAttribute = new AnnotationDefaultAttribute(1,
        new AnnotationElementValue(1, new Annotation()));

    // Act
    annotationUsageMarker.visitAnnotationDefaultAttribute(clazz, method, annotationDefaultAttribute);

    // Assert
    ElementValue elementValue = annotationDefaultAttribute.defaultValue;
    assertTrue(elementValue instanceof AnnotationElementValue);
    ShortestUsageMark shortestUsageMark = usageMarker.currentUsageMark;
    assertSame(shortestUsageMark, annotationDefaultAttribute.getProcessingInfo());
    assertSame(shortestUsageMark, elementValue.getProcessingInfo());
    assertSame(shortestUsageMark, ((AnnotationElementValue) elementValue).annotationValue.getProcessingInfo());
  }

  /**
   * Test {@link AnnotationUsageMarker#visitAnnotation(Clazz, Annotation)} with {@code clazz}, {@code annotation}.
   * <p>
   * Method under test: {@link AnnotationUsageMarker#visitAnnotation(Clazz, Annotation)}
   */
  @Test
  @DisplayName("Test visitAnnotation(Clazz, Annotation) with 'clazz', 'annotation'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void AnnotationUsageMarker.visitAnnotation(Clazz, Annotation)"})
  void testVisitAnnotationWithClazzAnnotation() {
    // Arrange
    ShortestUsageMarker usageMarker = new ShortestUsageMarker();
    AnnotationUsageMarker annotationUsageMarker = new AnnotationUsageMarker(
        new ShortestClassUsageMarker(usageMarker, "Just cause"));
    LibraryClass clazz = new LibraryClass();
    Annotation annotation = new Annotation();

    // Act
    annotationUsageMarker.visitAnnotation(clazz, annotation);

    // Assert
    ShortestUsageMark expectedProcessingInfo = usageMarker.currentUsageMark;
    assertSame(expectedProcessingInfo, annotation.getProcessingInfo());
  }

  /**
   * Test {@link AnnotationUsageMarker#visitConstantElementValue(Clazz, Annotation, ConstantElementValue)}.
   * <p>
   * Method under test: {@link AnnotationUsageMarker#visitConstantElementValue(Clazz, Annotation, ConstantElementValue)}
   */
  @Test
  @DisplayName("Test visitConstantElementValue(Clazz, Annotation, ConstantElementValue)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void AnnotationUsageMarker.visitConstantElementValue(Clazz, Annotation, ConstantElementValue)"})
  void testVisitConstantElementValue() {
    // Arrange
    ShortestUsageMarker usageMarker = new ShortestUsageMarker();
    AnnotationUsageMarker annotationUsageMarker = new AnnotationUsageMarker(
        new ShortestClassUsageMarker(usageMarker, "Just cause"));
    LibraryClass clazz = new LibraryClass();
    Annotation annotation = new Annotation();
    ConstantElementValue constantElementValue = new ConstantElementValue('A');

    // Act
    annotationUsageMarker.visitConstantElementValue(clazz, annotation, constantElementValue);

    // Assert
    ShortestUsageMark expectedProcessingInfo = usageMarker.currentUsageMark;
    assertSame(expectedProcessingInfo, constantElementValue.getProcessingInfo());
  }

  /**
   * Test {@link AnnotationUsageMarker#visitEnumConstantElementValue(Clazz, Annotation, EnumConstantElementValue)}.
   * <p>
   * Method under test: {@link AnnotationUsageMarker#visitEnumConstantElementValue(Clazz, Annotation, EnumConstantElementValue)}
   */
  @Test
  @DisplayName("Test visitEnumConstantElementValue(Clazz, Annotation, EnumConstantElementValue)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "void AnnotationUsageMarker.visitEnumConstantElementValue(Clazz, Annotation, EnumConstantElementValue)"})
  void testVisitEnumConstantElementValue() {
    // Arrange
    ShortestUsageMarker usageMarker = new ShortestUsageMarker();
    AnnotationUsageMarker annotationUsageMarker = new AnnotationUsageMarker(
        new ShortestClassUsageMarker(usageMarker, "Just cause"));
    LibraryClass clazz = new LibraryClass();
    Annotation annotation = new Annotation();
    EnumConstantElementValue enumConstantElementValue = new EnumConstantElementValue(1, 1, 1);

    // Act
    annotationUsageMarker.visitEnumConstantElementValue(clazz, annotation, enumConstantElementValue);

    // Assert
    ShortestUsageMark expectedProcessingInfo = usageMarker.currentUsageMark;
    assertSame(expectedProcessingInfo, enumConstantElementValue.getProcessingInfo());
  }

  /**
   * Test {@link AnnotationUsageMarker#visitClassElementValue(Clazz, Annotation, ClassElementValue)}.
   * <p>
   * Method under test: {@link AnnotationUsageMarker#visitClassElementValue(Clazz, Annotation, ClassElementValue)}
   */
  @Test
  @DisplayName("Test visitClassElementValue(Clazz, Annotation, ClassElementValue)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void AnnotationUsageMarker.visitClassElementValue(Clazz, Annotation, ClassElementValue)"})
  void testVisitClassElementValue() {
    // Arrange
    ShortestUsageMarker usageMarker = new ShortestUsageMarker();
    AnnotationUsageMarker annotationUsageMarker = new AnnotationUsageMarker(
        new ShortestClassUsageMarker(usageMarker, "Just cause"));
    LibraryClass clazz = new LibraryClass();
    Annotation annotation = new Annotation();
    ClassElementValue classElementValue = new ClassElementValue(1, 1);

    // Act
    annotationUsageMarker.visitClassElementValue(clazz, annotation, classElementValue);

    // Assert
    ShortestUsageMark expectedProcessingInfo = usageMarker.currentUsageMark;
    assertSame(expectedProcessingInfo, classElementValue.getProcessingInfo());
  }

  /**
   * Test {@link AnnotationUsageMarker#visitAnnotationElementValue(Clazz, Annotation, AnnotationElementValue)}.
   * <p>
   * Method under test: {@link AnnotationUsageMarker#visitAnnotationElementValue(Clazz, Annotation, AnnotationElementValue)}
   */
  @Test
  @DisplayName("Test visitAnnotationElementValue(Clazz, Annotation, AnnotationElementValue)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "void AnnotationUsageMarker.visitAnnotationElementValue(Clazz, Annotation, AnnotationElementValue)"})
  void testVisitAnnotationElementValue() {
    // Arrange
    ShortestUsageMarker usageMarker = new ShortestUsageMarker();
    AnnotationUsageMarker annotationUsageMarker = new AnnotationUsageMarker(
        new ShortestClassUsageMarker(usageMarker, "Just cause"));
    LibraryClass clazz = new LibraryClass();
    Annotation annotation = new Annotation();
    AnnotationElementValue annotationElementValue = new AnnotationElementValue(1, new Annotation());

    // Act
    annotationUsageMarker.visitAnnotationElementValue(clazz, annotation, annotationElementValue);

    // Assert
    ShortestUsageMark shortestUsageMark = usageMarker.currentUsageMark;
    assertSame(shortestUsageMark, annotationElementValue.getProcessingInfo());
    assertSame(shortestUsageMark, annotationElementValue.annotationValue.getProcessingInfo());
  }

  /**
   * Test {@link AnnotationUsageMarker#visitArrayElementValue(Clazz, Annotation, ArrayElementValue)}.
   * <p>
   * Method under test: {@link AnnotationUsageMarker#visitArrayElementValue(Clazz, Annotation, ArrayElementValue)}
   */
  @Test
  @DisplayName("Test visitArrayElementValue(Clazz, Annotation, ArrayElementValue)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void AnnotationUsageMarker.visitArrayElementValue(Clazz, Annotation, ArrayElementValue)"})
  void testVisitArrayElementValue() {
    // Arrange
    ShortestUsageMarker usageMarker = new ShortestUsageMarker();
    AnnotationUsageMarker annotationUsageMarker = new AnnotationUsageMarker(
        new ShortestClassUsageMarker(usageMarker, "Just cause"));
    LibraryClass clazz = new LibraryClass();
    Annotation annotation = new Annotation();
    ArrayElementValue arrayElementValue = new ArrayElementValue();

    // Act
    annotationUsageMarker.visitArrayElementValue(clazz, annotation, arrayElementValue);

    // Assert
    ShortestUsageMark expectedProcessingInfo = usageMarker.currentUsageMark;
    assertSame(expectedProcessingInfo, arrayElementValue.getProcessingInfo());
  }

  /**
   * Test {@link AnnotationUsageMarker#visitAnyConstant(Clazz, Constant)}.
   * <p>
   * Method under test: {@link AnnotationUsageMarker#visitAnyConstant(Clazz, Constant)}
   */
  @Test
  @DisplayName("Test visitAnyConstant(Clazz, Constant)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void AnnotationUsageMarker.visitAnyConstant(Clazz, Constant)"})
  void testVisitAnyConstant() {
    // Arrange
    ShortestUsageMarker usageMarker = new ShortestUsageMarker();
    AnnotationUsageMarker annotationUsageMarker = new AnnotationUsageMarker(
        new ShortestClassUsageMarker(usageMarker, "Just cause"));
    LibraryClass clazz = new LibraryClass();
    ClassConstant constant = new ClassConstant();

    // Act
    annotationUsageMarker.visitAnyConstant(clazz, constant);

    // Assert
    ShortestUsageMark expectedProcessingInfo = usageMarker.currentUsageMark;
    assertSame(expectedProcessingInfo, constant.getProcessingInfo());
  }

  /**
   * Test {@link AnnotationUsageMarker#visitAnyConstant(Clazz, Constant)}.
   * <p>
   * Method under test: {@link AnnotationUsageMarker#visitAnyConstant(Clazz, Constant)}
   */
  @Test
  @DisplayName("Test visitAnyConstant(Clazz, Constant)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void AnnotationUsageMarker.visitAnyConstant(Clazz, Constant)"})
  void testVisitAnyConstant2() {
    // Arrange
    ShortestUsageMarker usageMarker = new ShortestUsageMarker();
    AnnotationUsageMarker annotationUsageMarker = new AnnotationUsageMarker(
        new ShortestClassUsageMarker(usageMarker, "Just cause"));
    LibraryClass clazz = new LibraryClass();
    DoubleConstant constant = new DoubleConstant();

    // Act
    annotationUsageMarker.visitAnyConstant(clazz, constant);

    // Assert
    ShortestUsageMark expectedProcessingInfo = usageMarker.currentUsageMark;
    assertSame(expectedProcessingInfo, constant.getProcessingInfo());
  }

  /**
   * Test {@link AnnotationUsageMarker#visitAnyConstant(Clazz, Constant)}.
   * <p>
   * Method under test: {@link AnnotationUsageMarker#visitAnyConstant(Clazz, Constant)}
   */
  @Test
  @DisplayName("Test visitAnyConstant(Clazz, Constant)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void AnnotationUsageMarker.visitAnyConstant(Clazz, Constant)"})
  void testVisitAnyConstant3() {
    // Arrange
    ShortestUsageMarker usageMarker = new ShortestUsageMarker();
    AnnotationUsageMarker annotationUsageMarker = new AnnotationUsageMarker(
        new ShortestClassUsageMarker(usageMarker, "Just cause"));
    LibraryClass clazz = new LibraryClass();
    FieldrefConstant constant = new FieldrefConstant();

    // Act
    annotationUsageMarker.visitAnyConstant(clazz, constant);

    // Assert
    ShortestUsageMark expectedProcessingInfo = usageMarker.currentUsageMark;
    assertSame(expectedProcessingInfo, constant.getProcessingInfo());
  }

  /**
   * Test {@link AnnotationUsageMarker#visitAnyConstant(Clazz, Constant)}.
   * <p>
   * Method under test: {@link AnnotationUsageMarker#visitAnyConstant(Clazz, Constant)}
   */
  @Test
  @DisplayName("Test visitAnyConstant(Clazz, Constant)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void AnnotationUsageMarker.visitAnyConstant(Clazz, Constant)"})
  void testVisitAnyConstant4() {
    // Arrange
    AnnotationUsageMarker annotationUsageMarker = new AnnotationUsageMarker(new ClassUsageMarker());
    LibraryClass clazz = new LibraryClass();
    ClassConstant constant = new ClassConstant(1, new LibraryClass(5, "This Class Name", "Super Class Name"));

    // Act
    annotationUsageMarker.visitAnyConstant(clazz, constant);

    // Assert
    assertTrue(constant.referencedClass instanceof LibraryClass);
  }

  /**
   * Test {@link AnnotationUsageMarker#visitAnyConstant(Clazz, Constant)}.
   * <p>
   * Method under test: {@link AnnotationUsageMarker#visitAnyConstant(Clazz, Constant)}
   */
  @Test
  @DisplayName("Test visitAnyConstant(Clazz, Constant)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void AnnotationUsageMarker.visitAnyConstant(Clazz, Constant)"})
  void testVisitAnyConstant5() {
    // Arrange
    AnnotationUsageMarker annotationUsageMarker = new AnnotationUsageMarker(new ClassUsageMarker());
    LibraryClass clazz = new LibraryClass();
    LibraryClass referencedClass = new LibraryClass(5, "This Class Name", "Super Class Name");

    FieldrefConstant constant = new FieldrefConstant(1, 1, referencedClass, new LibraryField(5, "Name", "Descriptor"));

    // Act
    annotationUsageMarker.visitAnyConstant(clazz, constant);

    // Assert
    assertTrue(constant.referencedClass instanceof LibraryClass);
  }

  /**
   * Test {@link AnnotationUsageMarker#visitAnyConstant(Clazz, Constant)}.
   * <p>
   * Method under test: {@link AnnotationUsageMarker#visitAnyConstant(Clazz, Constant)}
   */
  @Test
  @DisplayName("Test visitAnyConstant(Clazz, Constant)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void AnnotationUsageMarker.visitAnyConstant(Clazz, Constant)"})
  void testVisitAnyConstant6() {
    // Arrange
    ShortestUsageMarker usageMarker = new ShortestUsageMarker();
    AnnotationUsageMarker annotationUsageMarker = new AnnotationUsageMarker(
        new ShortestClassUsageMarker(usageMarker, "Just cause"));
    LibraryClass clazz = new LibraryClass();
    FloatConstant constant = new FloatConstant(10.0f);

    // Act
    annotationUsageMarker.visitAnyConstant(clazz, constant);

    // Assert
    ShortestUsageMark expectedProcessingInfo = usageMarker.currentUsageMark;
    assertSame(expectedProcessingInfo, constant.getProcessingInfo());
  }

  /**
   * Test {@link AnnotationUsageMarker#visitAnyConstant(Clazz, Constant)}.
   * <p>
   * Method under test: {@link AnnotationUsageMarker#visitAnyConstant(Clazz, Constant)}
   */
  @Test
  @DisplayName("Test visitAnyConstant(Clazz, Constant)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void AnnotationUsageMarker.visitAnyConstant(Clazz, Constant)"})
  void testVisitAnyConstant7() {
    // Arrange
    ShortestUsageMarker usageMarker = new ShortestUsageMarker();
    AnnotationUsageMarker annotationUsageMarker = new AnnotationUsageMarker(
        new ShortestClassUsageMarker(usageMarker, "Just cause"));
    LibraryClass clazz = new LibraryClass();
    IntegerConstant constant = new IntegerConstant(42);

    // Act
    annotationUsageMarker.visitAnyConstant(clazz, constant);

    // Assert
    ShortestUsageMark expectedProcessingInfo = usageMarker.currentUsageMark;
    assertSame(expectedProcessingInfo, constant.getProcessingInfo());
  }

  /**
   * Test {@link AnnotationUsageMarker#visitClassConstant(Clazz, ClassConstant)}.
   * <p>
   * Method under test: {@link AnnotationUsageMarker#visitClassConstant(Clazz, ClassConstant)}
   */
  @Test
  @DisplayName("Test visitClassConstant(Clazz, ClassConstant)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void AnnotationUsageMarker.visitClassConstant(Clazz, ClassConstant)"})
  void testVisitClassConstant() {
    // Arrange
    ShortestUsageMarker usageMarker = new ShortestUsageMarker();
    AnnotationUsageMarker annotationUsageMarker = new AnnotationUsageMarker(
        new ShortestClassUsageMarker(usageMarker, "Just cause"));
    LibraryClass clazz = new LibraryClass();
    ClassConstant classConstant = new ClassConstant();

    // Act
    annotationUsageMarker.visitClassConstant(clazz, classConstant);

    // Assert
    ShortestUsageMark expectedProcessingInfo = usageMarker.currentUsageMark;
    assertSame(expectedProcessingInfo, classConstant.getProcessingInfo());
  }

  /**
   * Test {@link AnnotationUsageMarker#visitClassConstant(Clazz, ClassConstant)}.
   * <p>
   * Method under test: {@link AnnotationUsageMarker#visitClassConstant(Clazz, ClassConstant)}
   */
  @Test
  @DisplayName("Test visitClassConstant(Clazz, ClassConstant)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void AnnotationUsageMarker.visitClassConstant(Clazz, ClassConstant)"})
  void testVisitClassConstant2() {
    // Arrange
    AnnotationUsageMarker annotationUsageMarker = new AnnotationUsageMarker(new ClassUsageMarker());
    LibraryClass clazz = new LibraryClass();
    ClassConstant classConstant = new ClassConstant(1, new LibraryClass(5, "kotlin/Metadata", "kotlin/Metadata"));

    // Act
    annotationUsageMarker.visitClassConstant(clazz, classConstant);

    // Assert that nothing has changed
    assertNull(classConstant.getProcessingInfo());
  }

  /**
   * Test {@link AnnotationUsageMarker#visitClassConstant(Clazz, ClassConstant)}.
   * <p>
   * Method under test: {@link AnnotationUsageMarker#visitClassConstant(Clazz, ClassConstant)}
   */
  @Test
  @DisplayName("Test visitClassConstant(Clazz, ClassConstant)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void AnnotationUsageMarker.visitClassConstant(Clazz, ClassConstant)"})
  void testVisitClassConstant3() {
    // Arrange
    AnnotationUsageMarker annotationUsageMarker = new AnnotationUsageMarker(new ClassUsageMarker());
    LibraryClass clazz = new LibraryClass();
    ClassConstant classConstant = new ClassConstant(1, new NamedClass("kotlin/Metadata"));

    // Act
    annotationUsageMarker.visitClassConstant(clazz, classConstant);

    // Assert that nothing has changed
    assertNull(classConstant.getProcessingInfo());
  }

  /**
   * Test {@link AnnotationUsageMarker#visitClassConstant(Clazz, ClassConstant)}.
   * <ul>
   *   <li>Then first element {@link DoubleConstant}.</li>
   * </ul>
   * <p>
   * Method under test: {@link AnnotationUsageMarker#visitClassConstant(Clazz, ClassConstant)}
   */
  @Test
  @DisplayName("Test visitClassConstant(Clazz, ClassConstant); then first element DoubleConstant")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void AnnotationUsageMarker.visitClassConstant(Clazz, ClassConstant)"})
  void testVisitClassConstant_thenFirstElementDoubleConstant() {
    // Arrange
    ShortestUsageMarker usageMarker = new ShortestUsageMarker();
    AnnotationUsageMarker annotationUsageMarker = new AnnotationUsageMarker(
        new ShortestClassUsageMarker(usageMarker, "Just cause"));
    ProgramClass clazz = new ProgramClass(5, 3, new Constant[]{new DoubleConstant(10.0d)}, 5, 5, 5);

    ClassConstant classConstant = new ClassConstant();

    // Act
    annotationUsageMarker.visitClassConstant(clazz, classConstant);

    // Assert
    Constant[] constantArray = clazz.constantPool;
    Constant constant = constantArray[0];
    assertTrue(constant instanceof DoubleConstant);
    assertEquals(1, constantArray.length);
    Object processingInfo = constant.getProcessingInfo();
    assertSame(processingInfo, classConstant.getProcessingInfo());
    assertSame(usageMarker.currentUsageMark, processingInfo);
  }

  /**
   * Test {@link AnnotationUsageMarker#visitClassConstant(Clazz, ClassConstant)}.
   * <ul>
   *   <li>Then first element {@link DynamicConstant}.</li>
   * </ul>
   * <p>
   * Method under test: {@link AnnotationUsageMarker#visitClassConstant(Clazz, ClassConstant)}
   */
  @Test
  @DisplayName("Test visitClassConstant(Clazz, ClassConstant); then first element DynamicConstant")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void AnnotationUsageMarker.visitClassConstant(Clazz, ClassConstant)"})
  void testVisitClassConstant_thenFirstElementDynamicConstant() {
    // Arrange
    ShortestUsageMarker usageMarker = new ShortestUsageMarker();
    AnnotationUsageMarker annotationUsageMarker = new AnnotationUsageMarker(
        new ShortestClassUsageMarker(usageMarker, "Just cause"));
    ProgramClass clazz = new ProgramClass(5, 3, new Constant[]{new DynamicConstant()}, 5, 5, 5);

    ClassConstant classConstant = new ClassConstant();

    // Act
    annotationUsageMarker.visitClassConstant(clazz, classConstant);

    // Assert
    Constant[] constantArray = clazz.constantPool;
    Constant constant = constantArray[0];
    assertTrue(constant instanceof DynamicConstant);
    assertEquals(1, constantArray.length);
    Object processingInfo = constant.getProcessingInfo();
    assertSame(processingInfo, classConstant.getProcessingInfo());
    assertSame(usageMarker.currentUsageMark, processingInfo);
  }

  /**
   * Test {@link AnnotationUsageMarker#visitClassConstant(Clazz, ClassConstant)}.
   * <ul>
   *   <li>Then first element {@link FieldrefConstant}.</li>
   * </ul>
   * <p>
   * Method under test: {@link AnnotationUsageMarker#visitClassConstant(Clazz, ClassConstant)}
   */
  @Test
  @DisplayName("Test visitClassConstant(Clazz, ClassConstant); then first element FieldrefConstant")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void AnnotationUsageMarker.visitClassConstant(Clazz, ClassConstant)"})
  void testVisitClassConstant_thenFirstElementFieldrefConstant() {
    // Arrange
    ShortestUsageMarker usageMarker = new ShortestUsageMarker();
    AnnotationUsageMarker annotationUsageMarker = new AnnotationUsageMarker(
        new ShortestClassUsageMarker(usageMarker, "Just cause"));
    ProgramClass clazz = new ProgramClass(5, 3, new Constant[]{new FieldrefConstant()}, 5, 5, 5);

    ClassConstant classConstant = new ClassConstant();

    // Act
    annotationUsageMarker.visitClassConstant(clazz, classConstant);

    // Assert
    Constant[] constantArray = clazz.constantPool;
    Constant constant = constantArray[0];
    assertTrue(constant instanceof FieldrefConstant);
    assertEquals(1, constantArray.length);
    Object processingInfo = constant.getProcessingInfo();
    assertSame(processingInfo, classConstant.getProcessingInfo());
    assertSame(usageMarker.currentUsageMark, processingInfo);
  }

  /**
   * Test {@link AnnotationUsageMarker#visitClassConstant(Clazz, ClassConstant)}.
   * <ul>
   *   <li>Then first element {@link FloatConstant}.</li>
   * </ul>
   * <p>
   * Method under test: {@link AnnotationUsageMarker#visitClassConstant(Clazz, ClassConstant)}
   */
  @Test
  @DisplayName("Test visitClassConstant(Clazz, ClassConstant); then first element FloatConstant")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void AnnotationUsageMarker.visitClassConstant(Clazz, ClassConstant)"})
  void testVisitClassConstant_thenFirstElementFloatConstant() {
    // Arrange
    ShortestUsageMarker usageMarker = new ShortestUsageMarker();
    AnnotationUsageMarker annotationUsageMarker = new AnnotationUsageMarker(
        new ShortestClassUsageMarker(usageMarker, "Just cause"));
    ProgramClass clazz = new ProgramClass(5, 3, new Constant[]{new FloatConstant(10.0f)}, 5, 5, 5);

    ClassConstant classConstant = new ClassConstant();

    // Act
    annotationUsageMarker.visitClassConstant(clazz, classConstant);

    // Assert
    Constant[] constantArray = clazz.constantPool;
    Constant constant = constantArray[0];
    assertTrue(constant instanceof FloatConstant);
    assertEquals(1, constantArray.length);
    Object processingInfo = constant.getProcessingInfo();
    assertSame(processingInfo, classConstant.getProcessingInfo());
    assertSame(usageMarker.currentUsageMark, processingInfo);
  }

  /**
   * Test {@link AnnotationUsageMarker#visitAnyClass(Clazz)}.
   * <ul>
   *   <li>When {@link LibraryClass#LibraryClass()}.</li>
   *   <li>Then throw {@link UnsupportedOperationException}.</li>
   * </ul>
   * <p>
   * Method under test: {@link AnnotationUsageMarker#visitAnyClass(Clazz)}
   */
  @Test
  @DisplayName("Test visitAnyClass(Clazz); when LibraryClass(); then throw UnsupportedOperationException")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void AnnotationUsageMarker.visitAnyClass(Clazz)"})
  void testVisitAnyClass_whenLibraryClass_thenThrowUnsupportedOperationException() {
    // Arrange
    AnnotationUsageMarker annotationUsageMarker = new AnnotationUsageMarker(new ClassUsageMarker());

    // Act and Assert
    assertThrows(UnsupportedOperationException.class, () -> annotationUsageMarker.visitAnyClass(new LibraryClass()));
  }

  /**
   * Test {@link AnnotationUsageMarker#visitProgramClass(ProgramClass)}.
   * <ul>
   *   <li>Given {@code 1048576}.</li>
   * </ul>
   * <p>
   * Method under test: {@link AnnotationUsageMarker#visitProgramClass(ProgramClass)}
   */
  @Test
  @DisplayName("Test visitProgramClass(ProgramClass); given '1048576'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void AnnotationUsageMarker.visitProgramClass(ProgramClass)"})
  void testVisitProgramClass_given1048576() {
    // Arrange
    ClassUsageMarker classUsageMarker = mock(ClassUsageMarker.class);
    when(classUsageMarker.isUsed(Mockito.<Processable>any())).thenReturn(true);
    AnnotationUsageMarker annotationUsageMarker = new AnnotationUsageMarker(classUsageMarker);
    ProgramClass programClass = mock(ProgramClass.class);
    when(programClass.getProcessingFlags()).thenReturn(1048576);
    when(programClass.getName()).thenReturn("kotlin/coroutines/jvm/internal/DebugMetadata");

    // Act
    annotationUsageMarker.visitProgramClass(programClass);

    // Assert
    verify(programClass).getName();
    verify(classUsageMarker).isUsed(isA(Processable.class));
    verify(programClass).getProcessingFlags();
  }

  /**
   * Test {@link AnnotationUsageMarker#visitProgramClass(ProgramClass)}.
   * <ul>
   *   <li>Given {@code Name}.</li>
   *   <li>When {@link ProgramClass} {@link ProgramClass#getName()} return {@code Name}.</li>
   * </ul>
   * <p>
   * Method under test: {@link AnnotationUsageMarker#visitProgramClass(ProgramClass)}
   */
  @Test
  @DisplayName("Test visitProgramClass(ProgramClass); given 'Name'; when ProgramClass getName() return 'Name'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void AnnotationUsageMarker.visitProgramClass(ProgramClass)"})
  void testVisitProgramClass_givenName_whenProgramClassGetNameReturnName() {
    // Arrange
    ClassUsageMarker classUsageMarker = mock(ClassUsageMarker.class);
    when(classUsageMarker.isUsed(Mockito.<Processable>any())).thenReturn(true);
    AnnotationUsageMarker annotationUsageMarker = new AnnotationUsageMarker(classUsageMarker);
    ProgramClass programClass = mock(ProgramClass.class);
    when(programClass.getName()).thenReturn("Name");

    // Act
    annotationUsageMarker.visitProgramClass(programClass);

    // Assert
    verify(programClass).getName();
    verify(classUsageMarker).isUsed(isA(Processable.class));
  }

  /**
   * Test {@link AnnotationUsageMarker#visitProgramClass(ProgramClass)}.
   * <ul>
   *   <li>Given one.</li>
   *   <li>When {@link ProgramClass} {@link SimpleProcessable#getProcessingFlags()} return one.</li>
   * </ul>
   * <p>
   * Method under test: {@link AnnotationUsageMarker#visitProgramClass(ProgramClass)}
   */
  @Test
  @DisplayName("Test visitProgramClass(ProgramClass); given one; when ProgramClass getProcessingFlags() return one")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void AnnotationUsageMarker.visitProgramClass(ProgramClass)"})
  void testVisitProgramClass_givenOne_whenProgramClassGetProcessingFlagsReturnOne() {
    // Arrange
    ClassUsageMarker classUsageMarker = mock(ClassUsageMarker.class);
    when(classUsageMarker.isUsed(Mockito.<Processable>any())).thenReturn(true);
    AnnotationUsageMarker annotationUsageMarker = new AnnotationUsageMarker(classUsageMarker);
    ProgramClass programClass = mock(ProgramClass.class);
    when(programClass.getProcessingFlags()).thenReturn(1);
    when(programClass.getName()).thenReturn("kotlin/coroutines/jvm/internal/DebugMetadata");

    // Act
    annotationUsageMarker.visitProgramClass(programClass);

    // Assert
    verify(programClass).getName();
    verify(classUsageMarker).isUsed(isA(Processable.class));
    verify(programClass).getProcessingFlags();
  }

  /**
   * Test {@link AnnotationUsageMarker#visitLibraryClass(LibraryClass)}.
   * <ul>
   *   <li>Given {@code Name}.</li>
   *   <li>When {@link LibraryClass} {@link LibraryClass#getName()} return {@code Name}.</li>
   * </ul>
   * <p>
   * Method under test: {@link AnnotationUsageMarker#visitLibraryClass(LibraryClass)}
   */
  @Test
  @DisplayName("Test visitLibraryClass(LibraryClass); given 'Name'; when LibraryClass getName() return 'Name'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void AnnotationUsageMarker.visitLibraryClass(LibraryClass)"})
  void testVisitLibraryClass_givenName_whenLibraryClassGetNameReturnName() {
    // Arrange
    AnnotationUsageMarker annotationUsageMarker = new AnnotationUsageMarker(new ClassUsageMarker());
    LibraryClass libraryClass = mock(LibraryClass.class);
    when(libraryClass.getName()).thenReturn("Name");

    // Act
    annotationUsageMarker.visitLibraryClass(libraryClass);

    // Assert
    verify(libraryClass, atLeast(1)).getName();
  }

  /**
   * Test {@link AnnotationUsageMarker#visitProgramMethod(ProgramClass, ProgramMethod)}.
   * <ul>
   *   <li>Given {@link ClassUsageMarker} {@link ClassUsageMarker#isUsed(Processable)} return {@code true}.</li>
   *   <li>Then calls {@link ClassUsageMarker#isUsed(Processable)}.</li>
   * </ul>
   * <p>
   * Method under test: {@link AnnotationUsageMarker#visitProgramMethod(ProgramClass, ProgramMethod)}
   */
  @Test
  @DisplayName("Test visitProgramMethod(ProgramClass, ProgramMethod); given ClassUsageMarker isUsed(Processable) return 'true'; then calls isUsed(Processable)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void AnnotationUsageMarker.visitProgramMethod(ProgramClass, ProgramMethod)"})
  void testVisitProgramMethod_givenClassUsageMarkerIsUsedReturnTrue_thenCallsIsUsed() {
    // Arrange
    ClassUsageMarker classUsageMarker = mock(ClassUsageMarker.class);
    when(classUsageMarker.isUsed(Mockito.<Processable>any())).thenReturn(true);
    AnnotationUsageMarker annotationUsageMarker = new AnnotationUsageMarker(classUsageMarker);
    ProgramClass programClass = new ProgramClass();

    // Act
    annotationUsageMarker.visitProgramMethod(programClass, new ProgramMethod());

    // Assert
    verify(classUsageMarker).isUsed(isA(Processable.class));
  }
}
