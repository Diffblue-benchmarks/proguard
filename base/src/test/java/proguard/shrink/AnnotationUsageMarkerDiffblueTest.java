package proguard.shrink;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
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
import proguard.util.Processable;

class AnnotationUsageMarkerDiffblueTest {
  /**
   * Method under test:
   * {@link AnnotationUsageMarker#visitAnyAnnotationsAttribute(Clazz, AnnotationsAttribute)}
   */
  @Test
  void testVisitAnyAnnotationsAttribute() {
    // Arrange
    AnnotationUsageMarker annotationUsageMarker = new AnnotationUsageMarker(new ClassUsageMarker());
    LibraryClass clazz = new LibraryClass();
    Annotation annotation = new Annotation();
    RuntimeInvisibleAnnotationsAttribute annotationsAttribute = new RuntimeInvisibleAnnotationsAttribute(1, 1,
        new Annotation[]{annotation});

    // Act
    annotationUsageMarker.visitAnyAnnotationsAttribute(clazz, annotationsAttribute);

    // Assert
    Annotation[] annotationArray = annotationsAttribute.annotations;
    assertEquals(1, annotationArray.length);
    assertSame(annotation, annotationArray[0]);
  }

  /**
   * Method under test:
   * {@link AnnotationUsageMarker#visitAnyParameterAnnotationsAttribute(Clazz, Method, ParameterAnnotationsAttribute)}
   */
  @Test
  void testVisitAnyParameterAnnotationsAttribute() {
    // Arrange
    AnnotationUsageMarker annotationUsageMarker = new AnnotationUsageMarker(new ClassUsageMarker());
    LibraryClass clazz = new LibraryClass();
    LibraryMethod method = new LibraryMethod(1, "Name", "Descriptor");

    Annotation annotation = new Annotation();
    RuntimeInvisibleParameterAnnotationsAttribute parameterAnnotationsAttribute = new RuntimeInvisibleParameterAnnotationsAttribute(
        1, 1, new int[]{1, 1, 3, 1}, new Annotation[][]{new Annotation[]{annotation}});

    // Act
    annotationUsageMarker.visitAnyParameterAnnotationsAttribute(clazz, method, parameterAnnotationsAttribute);

    // Assert
    Annotation[][] annotationArray = parameterAnnotationsAttribute.parameterAnnotations;
    Annotation[] annotationArray2 = annotationArray[0];
    assertEquals(1, annotationArray2.length);
    assertEquals(1, annotationArray.length);
    assertSame(annotation, annotationArray2[0]);
  }

  /**
   * Method under test:
   * {@link AnnotationUsageMarker#visitAnnotationDefaultAttribute(Clazz, Method, AnnotationDefaultAttribute)}
   */
  @Test
  void testVisitAnnotationDefaultAttribute() {
    // Arrange
    AnnotationUsageMarker annotationUsageMarker = new AnnotationUsageMarker(new ClassUsageMarker());
    LibraryClass clazz = new LibraryClass();
    LibraryMethod method = new LibraryMethod(1, "Name", "Descriptor");

    AnnotationElementValue defaultValue = new AnnotationElementValue(1, new Annotation());

    AnnotationDefaultAttribute annotationDefaultAttribute = new AnnotationDefaultAttribute(1, defaultValue);

    // Act
    annotationUsageMarker.visitAnnotationDefaultAttribute(clazz, method, annotationDefaultAttribute);

    // Assert
    ElementValue elementValue = annotationDefaultAttribute.defaultValue;
    assertTrue(elementValue instanceof AnnotationElementValue);
    assertSame(defaultValue.annotationValue, ((AnnotationElementValue) elementValue).annotationValue);
  }

  /**
   * Method under test:
   * {@link AnnotationUsageMarker#visitAnnotationDefaultAttribute(Clazz, Method, AnnotationDefaultAttribute)}
   */
  @Test
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
   * Method under test:
   * {@link AnnotationUsageMarker#visitAnnotationDefaultAttribute(Clazz, Method, AnnotationDefaultAttribute)}
   */
  @Test
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
   * Method under test:
   * {@link AnnotationUsageMarker#visitAnnotationDefaultAttribute(Clazz, Method, AnnotationDefaultAttribute)}
   */
  @Test
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
   * Method under test:
   * {@link AnnotationUsageMarker#visitAnnotationDefaultAttribute(Clazz, Method, AnnotationDefaultAttribute)}
   */
  @Test
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
   * Method under test:
   * {@link AnnotationUsageMarker#visitAnnotationDefaultAttribute(Clazz, Method, AnnotationDefaultAttribute)}
   */
  @Test
  void testVisitAnnotationDefaultAttribute6() {
    // Arrange
    ShortestUsageMarker usageMarker = new ShortestUsageMarker();
    AnnotationUsageMarker annotationUsageMarker = new AnnotationUsageMarker(
        new ShortestClassUsageMarker(usageMarker, "Just cause"));
    LibraryClass clazz = new LibraryClass();
    LibraryMethod method = new LibraryMethod(1, "Name", "Descriptor");

    AnnotationElementValue defaultValue = new AnnotationElementValue(1, new Annotation());

    AnnotationDefaultAttribute annotationDefaultAttribute = new AnnotationDefaultAttribute(1, defaultValue);

    // Act
    annotationUsageMarker.visitAnnotationDefaultAttribute(clazz, method, annotationDefaultAttribute);

    // Assert
    ElementValue elementValue = annotationDefaultAttribute.defaultValue;
    assertTrue(elementValue instanceof AnnotationElementValue);
    assertSame(defaultValue.annotationValue, ((AnnotationElementValue) elementValue).annotationValue);
    ShortestUsageMark shortestUsageMark = usageMarker.currentUsageMark;
    assertSame(shortestUsageMark, annotationDefaultAttribute.getProcessingInfo());
    assertSame(shortestUsageMark, elementValue.getProcessingInfo());
  }

  /**
   * Method under test:
   * {@link AnnotationUsageMarker#visitAnnotation(Clazz, Annotation)}
   */
  @Test
  void testVisitAnnotation() {
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
   * Method under test:
   * {@link AnnotationUsageMarker#visitConstantElementValue(Clazz, Annotation, ConstantElementValue)}
   */
  @Test
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
   * Method under test:
   * {@link AnnotationUsageMarker#visitEnumConstantElementValue(Clazz, Annotation, EnumConstantElementValue)}
   */
  @Test
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
   * Method under test:
   * {@link AnnotationUsageMarker#visitClassElementValue(Clazz, Annotation, ClassElementValue)}
   */
  @Test
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
   * Method under test:
   * {@link AnnotationUsageMarker#visitAnnotationElementValue(Clazz, Annotation, AnnotationElementValue)}
   */
  @Test
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
   * Method under test:
   * {@link AnnotationUsageMarker#visitArrayElementValue(Clazz, Annotation, ArrayElementValue)}
   */
  @Test
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
   * Method under test:
   * {@link AnnotationUsageMarker#visitAnyConstant(Clazz, Constant)}
   */
  @Test
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
   * Method under test:
   * {@link AnnotationUsageMarker#visitAnyConstant(Clazz, Constant)}
   */
  @Test
  void testVisitAnyConstant2() {
    // Arrange
    AnnotationUsageMarker annotationUsageMarker = new AnnotationUsageMarker(new ClassUsageMarker());
    ClassConstant classConstant = new ClassConstant();
    ProgramClass clazz = new ProgramClass(5, 3, new Constant[]{classConstant}, 5, 5, 5);

    ClassConstant constant = new ClassConstant();

    // Act
    annotationUsageMarker.visitAnyConstant(clazz, constant);

    // Assert
    assertEquals(classConstant, constant);
  }

  /**
   * Method under test:
   * {@link AnnotationUsageMarker#visitAnyConstant(Clazz, Constant)}
   */
  @Test
  void testVisitAnyConstant3() {
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
   * Method under test:
   * {@link AnnotationUsageMarker#visitAnyConstant(Clazz, Constant)}
   */
  @Test
  void testVisitAnyConstant4() {
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
   * Method under test:
   * {@link AnnotationUsageMarker#visitAnyConstant(Clazz, Constant)}
   */
  @Test
  void testVisitAnyConstant5() {
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
   * Method under test:
   * {@link AnnotationUsageMarker#visitAnyConstant(Clazz, Constant)}
   */
  @Test
  void testVisitAnyConstant6() {
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
   * Method under test:
   * {@link AnnotationUsageMarker#visitAnyConstant(Clazz, Constant)}
   */
  @Test
  void testVisitAnyConstant7() {
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
   * Method under test:
   * {@link AnnotationUsageMarker#visitAnyConstant(Clazz, Constant)}
   */
  @Test
  void testVisitAnyConstant8() {
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
   * Method under test:
   * {@link AnnotationUsageMarker#visitClassConstant(Clazz, ClassConstant)}
   */
  @Test
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
   * Method under test:
   * {@link AnnotationUsageMarker#visitClassConstant(Clazz, ClassConstant)}
   */
  @Test
  void testVisitClassConstant2() {
    // Arrange
    AnnotationUsageMarker annotationUsageMarker = new AnnotationUsageMarker(new ClassUsageMarker());
    ClassConstant classConstant = new ClassConstant();
    ProgramClass clazz = new ProgramClass(5, 3, new Constant[]{classConstant}, 5, 5, 5);

    ClassConstant classConstant2 = new ClassConstant();

    // Act
    annotationUsageMarker.visitClassConstant(clazz, classConstant2);

    // Assert
    assertEquals(classConstant, classConstant2);
  }

  /**
   * Method under test:
   * {@link AnnotationUsageMarker#visitClassConstant(Clazz, ClassConstant)}
   */
  @Test
  void testVisitClassConstant3() {
    // Arrange
    AnnotationUsageMarker annotationUsageMarker = new AnnotationUsageMarker(new ClassUsageMarker());
    DoubleConstant doubleConstant = new DoubleConstant(10.0d);
    ProgramClass clazz = new ProgramClass(5, 3, new Constant[]{doubleConstant}, 5, 5, 5);

    // Act
    annotationUsageMarker.visitClassConstant(clazz, new ClassConstant());

    // Assert
    Constant[] constantArray = clazz.constantPool;
    Constant constant = constantArray[0];
    assertTrue(constant instanceof DoubleConstant);
    assertEquals(1, constantArray.length);
    assertSame(doubleConstant, constant);
  }

  /**
   * Method under test:
   * {@link AnnotationUsageMarker#visitClassConstant(Clazz, ClassConstant)}
   */
  @Test
  void testVisitClassConstant4() {
    // Arrange
    AnnotationUsageMarker annotationUsageMarker = new AnnotationUsageMarker(new ClassUsageMarker());
    DynamicConstant dynamicConstant = new DynamicConstant();
    ProgramClass clazz = new ProgramClass(5, 3, new Constant[]{dynamicConstant}, 5, 5, 5);

    // Act
    annotationUsageMarker.visitClassConstant(clazz, new ClassConstant());

    // Assert
    Constant[] constantArray = clazz.constantPool;
    Constant constant = constantArray[0];
    assertTrue(constant instanceof DynamicConstant);
    assertEquals(1, constantArray.length);
    assertSame(dynamicConstant, constant);
  }

  /**
   * Method under test:
   * {@link AnnotationUsageMarker#visitClassConstant(Clazz, ClassConstant)}
   */
  @Test
  void testVisitClassConstant5() {
    // Arrange
    AnnotationUsageMarker annotationUsageMarker = new AnnotationUsageMarker(new ClassUsageMarker());
    FieldrefConstant fieldrefConstant = new FieldrefConstant();
    ProgramClass clazz = new ProgramClass(5, 3, new Constant[]{fieldrefConstant}, 5, 5, 5);

    // Act
    annotationUsageMarker.visitClassConstant(clazz, new ClassConstant());

    // Assert
    Constant[] constantArray = clazz.constantPool;
    Constant constant = constantArray[0];
    assertTrue(constant instanceof FieldrefConstant);
    assertEquals(1, constantArray.length);
    assertSame(fieldrefConstant, constant);
  }

  /**
   * Method under test:
   * {@link AnnotationUsageMarker#visitClassConstant(Clazz, ClassConstant)}
   */
  @Test
  void testVisitClassConstant6() {
    // Arrange
    AnnotationUsageMarker annotationUsageMarker = new AnnotationUsageMarker(new ClassUsageMarker());
    FloatConstant floatConstant = new FloatConstant(10.0f);
    ProgramClass clazz = new ProgramClass(5, 3, new Constant[]{floatConstant}, 5, 5, 5);

    // Act
    annotationUsageMarker.visitClassConstant(clazz, new ClassConstant());

    // Assert
    Constant[] constantArray = clazz.constantPool;
    Constant constant = constantArray[0];
    assertTrue(constant instanceof FloatConstant);
    assertEquals(1, constantArray.length);
    assertSame(floatConstant, constant);
  }

  /**
   * Method under test:
   * {@link AnnotationUsageMarker#visitClassConstant(Clazz, ClassConstant)}
   */
  @Test
  void testVisitClassConstant7() {
    // Arrange
    ShortestUsageMarker usageMarker = new ShortestUsageMarker();
    AnnotationUsageMarker annotationUsageMarker = new AnnotationUsageMarker(
        new ShortestClassUsageMarker(usageMarker, "Just cause"));
    DoubleConstant doubleConstant = new DoubleConstant(10.0d);
    ProgramClass clazz = new ProgramClass(5, 3, new Constant[]{doubleConstant}, 5, 5, 5);

    ClassConstant classConstant = new ClassConstant();

    // Act
    annotationUsageMarker.visitClassConstant(clazz, classConstant);

    // Assert
    Constant[] constantArray = clazz.constantPool;
    assertEquals(1, constantArray.length);
    assertSame(doubleConstant, constantArray[0]);
    ShortestUsageMark expectedProcessingInfo = usageMarker.currentUsageMark;
    assertSame(expectedProcessingInfo, classConstant.getProcessingInfo());
  }

  /**
   * Method under test:
   * {@link AnnotationUsageMarker#visitClassConstant(Clazz, ClassConstant)}
   */
  @Test
  void testVisitClassConstant8() {
    // Arrange
    ShortestUsageMarker usageMarker = new ShortestUsageMarker();
    AnnotationUsageMarker annotationUsageMarker = new AnnotationUsageMarker(
        new ShortestClassUsageMarker(usageMarker, "Just cause"));
    DynamicConstant dynamicConstant = new DynamicConstant();
    ProgramClass clazz = new ProgramClass(5, 3, new Constant[]{dynamicConstant}, 5, 5, 5);

    ClassConstant classConstant = new ClassConstant();

    // Act
    annotationUsageMarker.visitClassConstant(clazz, classConstant);

    // Assert
    Constant[] constantArray = clazz.constantPool;
    assertEquals(1, constantArray.length);
    assertSame(dynamicConstant, constantArray[0]);
    ShortestUsageMark expectedProcessingInfo = usageMarker.currentUsageMark;
    assertSame(expectedProcessingInfo, classConstant.getProcessingInfo());
  }

  /**
   * Method under test:
   * {@link AnnotationUsageMarker#visitClassConstant(Clazz, ClassConstant)}
   */
  @Test
  void testVisitClassConstant9() {
    // Arrange
    ShortestUsageMarker usageMarker = new ShortestUsageMarker();
    AnnotationUsageMarker annotationUsageMarker = new AnnotationUsageMarker(
        new ShortestClassUsageMarker(usageMarker, "Just cause"));
    FieldrefConstant fieldrefConstant = new FieldrefConstant();
    ProgramClass clazz = new ProgramClass(5, 3, new Constant[]{fieldrefConstant}, 5, 5, 5);

    ClassConstant classConstant = new ClassConstant();

    // Act
    annotationUsageMarker.visitClassConstant(clazz, classConstant);

    // Assert
    Constant[] constantArray = clazz.constantPool;
    assertEquals(1, constantArray.length);
    assertSame(fieldrefConstant, constantArray[0]);
    ShortestUsageMark expectedProcessingInfo = usageMarker.currentUsageMark;
    assertSame(expectedProcessingInfo, classConstant.getProcessingInfo());
  }

  /**
   * Method under test:
   * {@link AnnotationUsageMarker#visitClassConstant(Clazz, ClassConstant)}
   */
  @Test
  void testVisitClassConstant10() {
    // Arrange
    ShortestUsageMarker usageMarker = new ShortestUsageMarker();
    AnnotationUsageMarker annotationUsageMarker = new AnnotationUsageMarker(
        new ShortestClassUsageMarker(usageMarker, "Just cause"));
    FloatConstant floatConstant = new FloatConstant(10.0f);
    ProgramClass clazz = new ProgramClass(5, 3, new Constant[]{floatConstant}, 5, 5, 5);

    ClassConstant classConstant = new ClassConstant();

    // Act
    annotationUsageMarker.visitClassConstant(clazz, classConstant);

    // Assert
    Constant[] constantArray = clazz.constantPool;
    assertEquals(1, constantArray.length);
    assertSame(floatConstant, constantArray[0]);
    ShortestUsageMark expectedProcessingInfo = usageMarker.currentUsageMark;
    assertSame(expectedProcessingInfo, classConstant.getProcessingInfo());
  }

  /**
   * Method under test: {@link AnnotationUsageMarker#visitAnyClass(Clazz)}
   */
  @Test
  void testVisitAnyClass() {
    // Arrange
    AnnotationUsageMarker annotationUsageMarker = new AnnotationUsageMarker(new ClassUsageMarker());

    // Act and Assert
    assertThrows(UnsupportedOperationException.class, () -> annotationUsageMarker.visitAnyClass(new LibraryClass()));
  }

  /**
   * Method under test:
   * {@link AnnotationUsageMarker#visitProgramClass(ProgramClass)}
   */
  @Test
  void testVisitProgramClass() {
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
   * Method under test:
   * {@link AnnotationUsageMarker#visitProgramClass(ProgramClass)}
   */
  @Test
  void testVisitProgramClass2() {
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
   * Method under test:
   * {@link AnnotationUsageMarker#visitProgramClass(ProgramClass)}
   */
  @Test
  void testVisitProgramClass3() {
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
   * Method under test:
   * {@link AnnotationUsageMarker#visitLibraryClass(LibraryClass)}
   */
  @Test
  void testVisitLibraryClass() {
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
   * Method under test:
   * {@link AnnotationUsageMarker#visitProgramMethod(ProgramClass, ProgramMethod)}
   */
  @Test
  void testVisitProgramMethod() {
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
