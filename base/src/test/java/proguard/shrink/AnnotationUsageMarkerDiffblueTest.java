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
import com.diffblue.cover.annotations.ManagedByDiffblue;
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

class AnnotationUsageMarkerDiffblueTest {
  /**
   * Test {@link AnnotationUsageMarker#visitAnyAnnotationsAttribute(Clazz, AnnotationsAttribute)}.
   *
   * <p>Method under test: {@link AnnotationUsageMarker#visitAnyAnnotationsAttribute(Clazz,
   * AnnotationsAttribute)}
   */
  @Test
  @DisplayName("Test visitAnyAnnotationsAttribute(Clazz, AnnotationsAttribute)")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void AnnotationUsageMarker.visitAnyAnnotationsAttribute(Clazz, AnnotationsAttribute)"
  })
  void testVisitAnyAnnotationsAttribute() {
    // Arrange
    AnnotationUsageMarker annotationUsageMarker = new AnnotationUsageMarker(new ClassUsageMarker());
    LibraryClass clazz = new LibraryClass();
    RuntimeInvisibleAnnotationsAttribute annotationsAttribute =
        new RuntimeInvisibleAnnotationsAttribute();

    // Act
    annotationUsageMarker.visitAnyAnnotationsAttribute(clazz, annotationsAttribute);

    // Assert that nothing has changed
    assertNull(annotationsAttribute.getProcessingInfo());
  }

  /**
   * Test {@link AnnotationUsageMarker#visitAnyAnnotationsAttribute(Clazz, AnnotationsAttribute)}.
   *
   * <p>Method under test: {@link AnnotationUsageMarker#visitAnyAnnotationsAttribute(Clazz,
   * AnnotationsAttribute)}
   */
  @Test
  @DisplayName("Test visitAnyAnnotationsAttribute(Clazz, AnnotationsAttribute)")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void AnnotationUsageMarker.visitAnyAnnotationsAttribute(Clazz, AnnotationsAttribute)"
  })
  void testVisitAnyAnnotationsAttribute2() {
    // Arrange
    AnnotationUsageMarker annotationUsageMarker = new AnnotationUsageMarker(new ClassUsageMarker());
    LibraryClass clazz = new LibraryClass();
    RuntimeInvisibleAnnotationsAttribute annotationsAttribute =
        new RuntimeInvisibleAnnotationsAttribute(0, 1, new Annotation[] {new Annotation()});

    // Act
    annotationUsageMarker.visitAnyAnnotationsAttribute(clazz, annotationsAttribute);

    // Assert
    assertEquals(1, annotationsAttribute.annotations.length);
  }

  /**
   * Test {@link AnnotationUsageMarker#visitAnyAnnotationsAttribute(Clazz, AnnotationsAttribute)}.
   *
   * <p>Method under test: {@link AnnotationUsageMarker#visitAnyAnnotationsAttribute(Clazz,
   * AnnotationsAttribute)}
   */
  @Test
  @DisplayName("Test visitAnyAnnotationsAttribute(Clazz, AnnotationsAttribute)")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void AnnotationUsageMarker.visitAnyAnnotationsAttribute(Clazz, AnnotationsAttribute)"
  })
  void testVisitAnyAnnotationsAttribute3() {
    // Arrange
    AnnotationUsageMarker annotationUsageMarker = new AnnotationUsageMarker(new ClassUsageMarker());
    LibraryClass clazz = new LibraryClass();
    RuntimeInvisibleAnnotationsAttribute annotationsAttribute =
        new RuntimeInvisibleAnnotationsAttribute(1, 1, new Annotation[] {new Annotation()});

    // Act
    annotationUsageMarker.visitAnyAnnotationsAttribute(clazz, annotationsAttribute);

    // Assert
    assertEquals(1, annotationsAttribute.annotations.length);
  }

  /**
   * Test {@link AnnotationUsageMarker#visitAnyAnnotationsAttribute(Clazz, AnnotationsAttribute)}.
   *
   * <p>Method under test: {@link AnnotationUsageMarker#visitAnyAnnotationsAttribute(Clazz,
   * AnnotationsAttribute)}
   */
  @Test
  @DisplayName("Test visitAnyAnnotationsAttribute(Clazz, AnnotationsAttribute)")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void AnnotationUsageMarker.visitAnyAnnotationsAttribute(Clazz, AnnotationsAttribute)"
  })
  void testVisitAnyAnnotationsAttribute4() {
    // Arrange
    ShortestUsageMarker usageMarker = new ShortestUsageMarker();
    AnnotationUsageMarker annotationUsageMarker =
        new AnnotationUsageMarker(new ShortestClassUsageMarker(usageMarker, "Just cause"));
    LibraryClass clazz = new LibraryClass();
    RuntimeInvisibleAnnotationsAttribute annotationsAttribute =
        new RuntimeInvisibleAnnotationsAttribute(0, 1, new Annotation[] {new Annotation()});

    // Act
    annotationUsageMarker.visitAnyAnnotationsAttribute(clazz, annotationsAttribute);

    // Assert
    Annotation[] annotationArray = annotationsAttribute.annotations;
    assertEquals(1, annotationArray.length);
    ShortestUsageMark shortestUsageMark = usageMarker.currentUsageMark;
    assertSame(shortestUsageMark, annotationsAttribute.getProcessingInfo());
    assertSame(shortestUsageMark, (annotationArray[0]).getProcessingInfo());
  }

  /**
   * Test {@link AnnotationUsageMarker#visitAnyAnnotationsAttribute(Clazz, AnnotationsAttribute)}.
   *
   * <ul>
   *   <li>Then first element {@link AnnotationElementValue}.
   * </ul>
   *
   * <p>Method under test: {@link AnnotationUsageMarker#visitAnyAnnotationsAttribute(Clazz,
   * AnnotationsAttribute)}
   */
  @Test
  @DisplayName(
      "Test visitAnyAnnotationsAttribute(Clazz, AnnotationsAttribute); then first element AnnotationElementValue")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void AnnotationUsageMarker.visitAnyAnnotationsAttribute(Clazz, AnnotationsAttribute)"
  })
  void testVisitAnyAnnotationsAttribute_thenFirstElementAnnotationElementValue() {
    // Arrange
    AnnotationUsageMarker annotationUsageMarker = new AnnotationUsageMarker(new ClassUsageMarker());
    LibraryClass clazz = new LibraryClass();
    RuntimeInvisibleAnnotationsAttribute annotationsAttribute =
        new RuntimeInvisibleAnnotationsAttribute(
            0,
            1,
            new Annotation[] {
              new Annotation(
                  1, 1, new ElementValue[] {new AnnotationElementValue(1, new Annotation())})
            });

    // Act
    annotationUsageMarker.visitAnyAnnotationsAttribute(clazz, annotationsAttribute);

    // Assert
    Annotation[] annotationArray = annotationsAttribute.annotations;
    ElementValue[] elementValueArray = (annotationArray[0]).elementValues;
    assertTrue(elementValueArray[0] instanceof AnnotationElementValue);
    assertEquals(1, elementValueArray.length);
    assertEquals(1, annotationArray.length);
  }

  /**
   * Test {@link AnnotationUsageMarker#visitAnyAnnotationsAttribute(Clazz, AnnotationsAttribute)}.
   *
   * <ul>
   *   <li>Then first element {@link ArrayElementValue}.
   * </ul>
   *
   * <p>Method under test: {@link AnnotationUsageMarker#visitAnyAnnotationsAttribute(Clazz,
   * AnnotationsAttribute)}
   */
  @Test
  @DisplayName(
      "Test visitAnyAnnotationsAttribute(Clazz, AnnotationsAttribute); then first element ArrayElementValue")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void AnnotationUsageMarker.visitAnyAnnotationsAttribute(Clazz, AnnotationsAttribute)"
  })
  void testVisitAnyAnnotationsAttribute_thenFirstElementArrayElementValue() {
    // Arrange
    AnnotationUsageMarker annotationUsageMarker = new AnnotationUsageMarker(new ClassUsageMarker());
    LibraryClass clazz = new LibraryClass();
    RuntimeInvisibleAnnotationsAttribute annotationsAttribute =
        new RuntimeInvisibleAnnotationsAttribute(
            0,
            1,
            new Annotation[] {new Annotation(1, 1, new ElementValue[] {new ArrayElementValue()})});

    // Act
    annotationUsageMarker.visitAnyAnnotationsAttribute(clazz, annotationsAttribute);

    // Assert
    Annotation[] annotationArray = annotationsAttribute.annotations;
    ElementValue[] elementValueArray = (annotationArray[0]).elementValues;
    assertTrue(elementValueArray[0] instanceof ArrayElementValue);
    assertEquals(1, elementValueArray.length);
    assertEquals(1, annotationArray.length);
  }

  /**
   * Test {@link AnnotationUsageMarker#visitAnyAnnotationsAttribute(Clazz, AnnotationsAttribute)}.
   *
   * <ul>
   *   <li>Then first element {@link ClassElementValue}.
   * </ul>
   *
   * <p>Method under test: {@link AnnotationUsageMarker#visitAnyAnnotationsAttribute(Clazz,
   * AnnotationsAttribute)}
   */
  @Test
  @DisplayName(
      "Test visitAnyAnnotationsAttribute(Clazz, AnnotationsAttribute); then first element ClassElementValue")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void AnnotationUsageMarker.visitAnyAnnotationsAttribute(Clazz, AnnotationsAttribute)"
  })
  void testVisitAnyAnnotationsAttribute_thenFirstElementClassElementValue() {
    // Arrange
    AnnotationUsageMarker annotationUsageMarker = new AnnotationUsageMarker(new ClassUsageMarker());
    LibraryClass clazz = new LibraryClass();
    RuntimeInvisibleAnnotationsAttribute annotationsAttribute =
        new RuntimeInvisibleAnnotationsAttribute(
            0,
            1,
            new Annotation[] {
              new Annotation(1, 1, new ElementValue[] {new ClassElementValue(1, 1)})
            });

    // Act
    annotationUsageMarker.visitAnyAnnotationsAttribute(clazz, annotationsAttribute);

    // Assert
    Annotation[] annotationArray = annotationsAttribute.annotations;
    ElementValue[] elementValueArray = (annotationArray[0]).elementValues;
    assertTrue(elementValueArray[0] instanceof ClassElementValue);
    assertEquals(1, elementValueArray.length);
    assertEquals(1, annotationArray.length);
  }

  /**
   * Test {@link AnnotationUsageMarker#visitAnyAnnotationsAttribute(Clazz, AnnotationsAttribute)}.
   *
   * <ul>
   *   <li>Then first element {@link ConstantElementValue}.
   * </ul>
   *
   * <p>Method under test: {@link AnnotationUsageMarker#visitAnyAnnotationsAttribute(Clazz,
   * AnnotationsAttribute)}
   */
  @Test
  @DisplayName(
      "Test visitAnyAnnotationsAttribute(Clazz, AnnotationsAttribute); then first element ConstantElementValue")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void AnnotationUsageMarker.visitAnyAnnotationsAttribute(Clazz, AnnotationsAttribute)"
  })
  void testVisitAnyAnnotationsAttribute_thenFirstElementConstantElementValue() {
    // Arrange
    AnnotationUsageMarker annotationUsageMarker = new AnnotationUsageMarker(new ClassUsageMarker());
    LibraryClass clazz = new LibraryClass();
    RuntimeInvisibleAnnotationsAttribute annotationsAttribute =
        new RuntimeInvisibleAnnotationsAttribute(
            0,
            1,
            new Annotation[] {
              new Annotation(1, 1, new ElementValue[] {new ConstantElementValue('\u0001')})
            });

    // Act
    annotationUsageMarker.visitAnyAnnotationsAttribute(clazz, annotationsAttribute);

    // Assert
    Annotation[] annotationArray = annotationsAttribute.annotations;
    ElementValue[] elementValueArray = (annotationArray[0]).elementValues;
    assertTrue(elementValueArray[0] instanceof ConstantElementValue);
    assertEquals(1, elementValueArray.length);
    assertEquals(1, annotationArray.length);
  }

  /**
   * Test {@link AnnotationUsageMarker#visitAnyAnnotationsAttribute(Clazz, AnnotationsAttribute)}.
   *
   * <ul>
   *   <li>Then first element {@link EnumConstantElementValue}.
   * </ul>
   *
   * <p>Method under test: {@link AnnotationUsageMarker#visitAnyAnnotationsAttribute(Clazz,
   * AnnotationsAttribute)}
   */
  @Test
  @DisplayName(
      "Test visitAnyAnnotationsAttribute(Clazz, AnnotationsAttribute); then first element EnumConstantElementValue")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void AnnotationUsageMarker.visitAnyAnnotationsAttribute(Clazz, AnnotationsAttribute)"
  })
  void testVisitAnyAnnotationsAttribute_thenFirstElementEnumConstantElementValue() {
    // Arrange
    AnnotationUsageMarker annotationUsageMarker = new AnnotationUsageMarker(new ClassUsageMarker());
    LibraryClass clazz = new LibraryClass();
    RuntimeInvisibleAnnotationsAttribute annotationsAttribute =
        new RuntimeInvisibleAnnotationsAttribute(
            0,
            1,
            new Annotation[] {
              new Annotation(1, 1, new ElementValue[] {new EnumConstantElementValue(1, 1, 1)})
            });

    // Act
    annotationUsageMarker.visitAnyAnnotationsAttribute(clazz, annotationsAttribute);

    // Assert
    Annotation[] annotationArray = annotationsAttribute.annotations;
    ElementValue[] elementValueArray = (annotationArray[0]).elementValues;
    assertTrue(elementValueArray[0] instanceof EnumConstantElementValue);
    assertEquals(1, elementValueArray.length);
    assertEquals(1, annotationArray.length);
  }

  /**
   * Test {@link AnnotationUsageMarker#visitAnyParameterAnnotationsAttribute(Clazz, Method,
   * ParameterAnnotationsAttribute)}.
   *
   * <p>Method under test: {@link AnnotationUsageMarker#visitAnyParameterAnnotationsAttribute(Clazz,
   * Method, ParameterAnnotationsAttribute)}
   */
  @Test
  @DisplayName(
      "Test visitAnyParameterAnnotationsAttribute(Clazz, Method, ParameterAnnotationsAttribute)")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void AnnotationUsageMarker.visitAnyParameterAnnotationsAttribute(Clazz, Method, ParameterAnnotationsAttribute)"
  })
  void testVisitAnyParameterAnnotationsAttribute() {
    // Arrange
    AnnotationUsageMarker annotationUsageMarker = new AnnotationUsageMarker(new ClassUsageMarker());
    LibraryClass clazz = new LibraryClass();
    LibraryMethod method = new LibraryMethod(1, "Name", "Descriptor");

    RuntimeInvisibleParameterAnnotationsAttribute parameterAnnotationsAttribute =
        new RuntimeInvisibleParameterAnnotationsAttribute();

    // Act
    annotationUsageMarker.visitAnyParameterAnnotationsAttribute(
        clazz, method, parameterAnnotationsAttribute);

    // Assert that nothing has changed
    assertNull(parameterAnnotationsAttribute.getProcessingInfo());
  }

  /**
   * Test {@link AnnotationUsageMarker#visitAnyParameterAnnotationsAttribute(Clazz, Method,
   * ParameterAnnotationsAttribute)}.
   *
   * <p>Method under test: {@link AnnotationUsageMarker#visitAnyParameterAnnotationsAttribute(Clazz,
   * Method, ParameterAnnotationsAttribute)}
   */
  @Test
  @DisplayName(
      "Test visitAnyParameterAnnotationsAttribute(Clazz, Method, ParameterAnnotationsAttribute)")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void AnnotationUsageMarker.visitAnyParameterAnnotationsAttribute(Clazz, Method, ParameterAnnotationsAttribute)"
  })
  void testVisitAnyParameterAnnotationsAttribute2() {
    // Arrange
    AnnotationUsageMarker annotationUsageMarker = new AnnotationUsageMarker(new ClassUsageMarker());
    LibraryClass clazz = new LibraryClass();
    LibraryMethod method = new LibraryMethod(1, "Name", "Descriptor");

    RuntimeInvisibleParameterAnnotationsAttribute parameterAnnotationsAttribute =
        new RuntimeInvisibleParameterAnnotationsAttribute(
            0, 1, new int[] {1, 1, 3, 1}, new Annotation[][] {new Annotation[] {new Annotation()}});

    // Act
    annotationUsageMarker.visitAnyParameterAnnotationsAttribute(
        clazz, method, parameterAnnotationsAttribute);

    // Assert
    Annotation[][] annotationArray = parameterAnnotationsAttribute.parameterAnnotations;
    assertEquals(1, (annotationArray[0]).length);
    assertEquals(1, annotationArray.length);
  }

  /**
   * Test {@link AnnotationUsageMarker#visitAnyParameterAnnotationsAttribute(Clazz, Method,
   * ParameterAnnotationsAttribute)}.
   *
   * <p>Method under test: {@link AnnotationUsageMarker#visitAnyParameterAnnotationsAttribute(Clazz,
   * Method, ParameterAnnotationsAttribute)}
   */
  @Test
  @DisplayName(
      "Test visitAnyParameterAnnotationsAttribute(Clazz, Method, ParameterAnnotationsAttribute)")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void AnnotationUsageMarker.visitAnyParameterAnnotationsAttribute(Clazz, Method, ParameterAnnotationsAttribute)"
  })
  void testVisitAnyParameterAnnotationsAttribute3() {
    // Arrange
    ShortestUsageMarker usageMarker = new ShortestUsageMarker();
    AnnotationUsageMarker annotationUsageMarker =
        new AnnotationUsageMarker(new ShortestClassUsageMarker(usageMarker, "Just cause"));
    LibraryClass clazz = new LibraryClass();
    LibraryMethod method = new LibraryMethod(1, "Name", "Descriptor");

    RuntimeInvisibleParameterAnnotationsAttribute parameterAnnotationsAttribute =
        new RuntimeInvisibleParameterAnnotationsAttribute(
            0, 1, new int[] {1, 1, 3, 1}, new Annotation[][] {new Annotation[] {new Annotation()}});

    // Act
    annotationUsageMarker.visitAnyParameterAnnotationsAttribute(
        clazz, method, parameterAnnotationsAttribute);

    // Assert
    Annotation[][] annotationArray = parameterAnnotationsAttribute.parameterAnnotations;
    Annotation[] annotationArray2 = annotationArray[0];
    assertEquals(1, annotationArray2.length);
    assertEquals(1, annotationArray.length);
    ShortestUsageMark shortestUsageMark = usageMarker.currentUsageMark;
    assertSame(shortestUsageMark, parameterAnnotationsAttribute.getProcessingInfo());
    assertSame(shortestUsageMark, (annotationArray2[0]).getProcessingInfo());
  }

  /**
   * Test {@link AnnotationUsageMarker#visitAnyParameterAnnotationsAttribute(Clazz, Method,
   * ParameterAnnotationsAttribute)}.
   *
   * <p>Method under test: {@link AnnotationUsageMarker#visitAnyParameterAnnotationsAttribute(Clazz,
   * Method, ParameterAnnotationsAttribute)}
   */
  @Test
  @DisplayName(
      "Test visitAnyParameterAnnotationsAttribute(Clazz, Method, ParameterAnnotationsAttribute)")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void AnnotationUsageMarker.visitAnyParameterAnnotationsAttribute(Clazz, Method, ParameterAnnotationsAttribute)"
  })
  void testVisitAnyParameterAnnotationsAttribute4() {
    // Arrange
    AnnotationUsageMarker annotationUsageMarker = new AnnotationUsageMarker(new ClassUsageMarker());
    LibraryClass clazz = new LibraryClass();
    LibraryMethod method = new LibraryMethod(1, "Name", "Descriptor");

    RuntimeInvisibleParameterAnnotationsAttribute parameterAnnotationsAttribute =
        new RuntimeInvisibleParameterAnnotationsAttribute(
            1, 1, new int[] {1, 1, 3, 1}, new Annotation[][] {new Annotation[] {new Annotation()}});

    // Act
    annotationUsageMarker.visitAnyParameterAnnotationsAttribute(
        clazz, method, parameterAnnotationsAttribute);

    // Assert
    Annotation[][] annotationArray = parameterAnnotationsAttribute.parameterAnnotations;
    assertEquals(1, (annotationArray[0]).length);
    assertEquals(1, annotationArray.length);
  }

  /**
   * Test {@link AnnotationUsageMarker#visitAnnotationDefaultAttribute(Clazz, Method,
   * AnnotationDefaultAttribute)}.
   *
   * <p>Method under test: {@link AnnotationUsageMarker#visitAnnotationDefaultAttribute(Clazz,
   * Method, AnnotationDefaultAttribute)}
   */
  @Test
  @DisplayName("Test visitAnnotationDefaultAttribute(Clazz, Method, AnnotationDefaultAttribute)")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void AnnotationUsageMarker.visitAnnotationDefaultAttribute(Clazz, Method, AnnotationDefaultAttribute)"
  })
  void testVisitAnnotationDefaultAttribute() {
    // Arrange
    AnnotationUsageMarker annotationUsageMarker = new AnnotationUsageMarker(new ClassUsageMarker());
    LibraryClass clazz = new LibraryClass();
    LibraryMethod method = new LibraryMethod(1, "Name", "Descriptor");

    AnnotationDefaultAttribute annotationDefaultAttribute =
        new AnnotationDefaultAttribute(0, new AnnotationElementValue(1, new Annotation()));

    // Act
    annotationUsageMarker.visitAnnotationDefaultAttribute(
        clazz, method, annotationDefaultAttribute);

    // Assert
    assertTrue(annotationDefaultAttribute.defaultValue instanceof AnnotationElementValue);
  }

  /**
   * Test {@link AnnotationUsageMarker#visitAnnotationDefaultAttribute(Clazz, Method,
   * AnnotationDefaultAttribute)}.
   *
   * <p>Method under test: {@link AnnotationUsageMarker#visitAnnotationDefaultAttribute(Clazz,
   * Method, AnnotationDefaultAttribute)}
   */
  @Test
  @DisplayName("Test visitAnnotationDefaultAttribute(Clazz, Method, AnnotationDefaultAttribute)")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void AnnotationUsageMarker.visitAnnotationDefaultAttribute(Clazz, Method, AnnotationDefaultAttribute)"
  })
  void testVisitAnnotationDefaultAttribute2() {
    // Arrange
    AnnotationUsageMarker annotationUsageMarker = new AnnotationUsageMarker(new ClassUsageMarker());
    LibraryClass clazz = new LibraryClass();
    LibraryMethod method = new LibraryMethod(1, "Name", "Descriptor");

    AnnotationDefaultAttribute annotationDefaultAttribute =
        new AnnotationDefaultAttribute(0, new ArrayElementValue());

    // Act
    annotationUsageMarker.visitAnnotationDefaultAttribute(
        clazz, method, annotationDefaultAttribute);

    // Assert
    assertTrue(annotationDefaultAttribute.defaultValue instanceof ArrayElementValue);
  }

  /**
   * Test {@link AnnotationUsageMarker#visitAnnotationDefaultAttribute(Clazz, Method,
   * AnnotationDefaultAttribute)}.
   *
   * <p>Method under test: {@link AnnotationUsageMarker#visitAnnotationDefaultAttribute(Clazz,
   * Method, AnnotationDefaultAttribute)}
   */
  @Test
  @DisplayName("Test visitAnnotationDefaultAttribute(Clazz, Method, AnnotationDefaultAttribute)")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void AnnotationUsageMarker.visitAnnotationDefaultAttribute(Clazz, Method, AnnotationDefaultAttribute)"
  })
  void testVisitAnnotationDefaultAttribute3() {
    // Arrange
    AnnotationUsageMarker annotationUsageMarker = new AnnotationUsageMarker(new ClassUsageMarker());
    LibraryClass clazz = new LibraryClass();
    LibraryMethod method = new LibraryMethod(1, "Name", "Descriptor");

    AnnotationDefaultAttribute annotationDefaultAttribute =
        new AnnotationDefaultAttribute(0, new ClassElementValue(1, 1));

    // Act
    annotationUsageMarker.visitAnnotationDefaultAttribute(
        clazz, method, annotationDefaultAttribute);

    // Assert
    assertTrue(annotationDefaultAttribute.defaultValue instanceof ClassElementValue);
  }

  /**
   * Test {@link AnnotationUsageMarker#visitAnnotationDefaultAttribute(Clazz, Method,
   * AnnotationDefaultAttribute)}.
   *
   * <p>Method under test: {@link AnnotationUsageMarker#visitAnnotationDefaultAttribute(Clazz,
   * Method, AnnotationDefaultAttribute)}
   */
  @Test
  @DisplayName("Test visitAnnotationDefaultAttribute(Clazz, Method, AnnotationDefaultAttribute)")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void AnnotationUsageMarker.visitAnnotationDefaultAttribute(Clazz, Method, AnnotationDefaultAttribute)"
  })
  void testVisitAnnotationDefaultAttribute4() {
    // Arrange
    AnnotationUsageMarker annotationUsageMarker = new AnnotationUsageMarker(new ClassUsageMarker());
    LibraryClass clazz = new LibraryClass();
    LibraryMethod method = new LibraryMethod(1, "Name", "Descriptor");

    AnnotationDefaultAttribute annotationDefaultAttribute =
        new AnnotationDefaultAttribute(0, new ConstantElementValue('\u0001'));

    // Act
    annotationUsageMarker.visitAnnotationDefaultAttribute(
        clazz, method, annotationDefaultAttribute);

    // Assert
    assertTrue(annotationDefaultAttribute.defaultValue instanceof ConstantElementValue);
  }

  /**
   * Test {@link AnnotationUsageMarker#visitAnnotationDefaultAttribute(Clazz, Method,
   * AnnotationDefaultAttribute)}.
   *
   * <p>Method under test: {@link AnnotationUsageMarker#visitAnnotationDefaultAttribute(Clazz,
   * Method, AnnotationDefaultAttribute)}
   */
  @Test
  @DisplayName("Test visitAnnotationDefaultAttribute(Clazz, Method, AnnotationDefaultAttribute)")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void AnnotationUsageMarker.visitAnnotationDefaultAttribute(Clazz, Method, AnnotationDefaultAttribute)"
  })
  void testVisitAnnotationDefaultAttribute5() {
    // Arrange
    AnnotationUsageMarker annotationUsageMarker = new AnnotationUsageMarker(new ClassUsageMarker());
    LibraryClass clazz = new LibraryClass();
    LibraryMethod method = new LibraryMethod(1, "Name", "Descriptor");

    AnnotationDefaultAttribute annotationDefaultAttribute =
        new AnnotationDefaultAttribute(0, new EnumConstantElementValue(1, 1, 1));

    // Act
    annotationUsageMarker.visitAnnotationDefaultAttribute(
        clazz, method, annotationDefaultAttribute);

    // Assert
    assertTrue(annotationDefaultAttribute.defaultValue instanceof EnumConstantElementValue);
  }

  /**
   * Test {@link AnnotationUsageMarker#visitAnnotationDefaultAttribute(Clazz, Method,
   * AnnotationDefaultAttribute)}.
   *
   * <p>Method under test: {@link AnnotationUsageMarker#visitAnnotationDefaultAttribute(Clazz,
   * Method, AnnotationDefaultAttribute)}
   */
  @Test
  @DisplayName("Test visitAnnotationDefaultAttribute(Clazz, Method, AnnotationDefaultAttribute)")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void AnnotationUsageMarker.visitAnnotationDefaultAttribute(Clazz, Method, AnnotationDefaultAttribute)"
  })
  void testVisitAnnotationDefaultAttribute6() {
    // Arrange
    ShortestUsageMarker usageMarker = new ShortestUsageMarker();
    AnnotationUsageMarker annotationUsageMarker =
        new AnnotationUsageMarker(new ShortestClassUsageMarker(usageMarker, "Just cause"));
    LibraryClass clazz = new LibraryClass();
    LibraryMethod method = new LibraryMethod(1, "Name", "Descriptor");

    AnnotationDefaultAttribute annotationDefaultAttribute =
        new AnnotationDefaultAttribute(0, new AnnotationElementValue(1, new Annotation()));

    // Act
    annotationUsageMarker.visitAnnotationDefaultAttribute(
        clazz, method, annotationDefaultAttribute);

    // Assert
    ElementValue elementValue = annotationDefaultAttribute.defaultValue;
    assertTrue(elementValue instanceof AnnotationElementValue);
    ShortestUsageMark shortestUsageMark = usageMarker.currentUsageMark;
    assertSame(shortestUsageMark, annotationDefaultAttribute.getProcessingInfo());
    assertSame(shortestUsageMark, elementValue.getProcessingInfo());
    assertSame(
        shortestUsageMark,
        ((AnnotationElementValue) elementValue).annotationValue.getProcessingInfo());
  }

  /**
   * Test {@link AnnotationUsageMarker#visitAnnotationDefaultAttribute(Clazz, Method,
   * AnnotationDefaultAttribute)}.
   *
   * <ul>
   *   <li>Then first element {@link AnnotationElementValue}.
   * </ul>
   *
   * <p>Method under test: {@link AnnotationUsageMarker#visitAnnotationDefaultAttribute(Clazz,
   * Method, AnnotationDefaultAttribute)}
   */
  @Test
  @DisplayName(
      "Test visitAnnotationDefaultAttribute(Clazz, Method, AnnotationDefaultAttribute); then first element AnnotationElementValue")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void AnnotationUsageMarker.visitAnnotationDefaultAttribute(Clazz, Method, AnnotationDefaultAttribute)"
  })
  void testVisitAnnotationDefaultAttribute_thenFirstElementAnnotationElementValue() {
    // Arrange
    AnnotationUsageMarker annotationUsageMarker = new AnnotationUsageMarker(new ClassUsageMarker());
    LibraryClass clazz = new LibraryClass();
    LibraryMethod method = new LibraryMethod(1, "Name", "Descriptor");

    AnnotationDefaultAttribute annotationDefaultAttribute =
        new AnnotationDefaultAttribute(
            0,
            new ArrayElementValue(
                1, 1, new ElementValue[] {new AnnotationElementValue(1, new Annotation())}));

    // Act
    annotationUsageMarker.visitAnnotationDefaultAttribute(
        clazz, method, annotationDefaultAttribute);

    // Assert
    ElementValue elementValue = annotationDefaultAttribute.defaultValue;
    ElementValue[] elementValueArray = ((ArrayElementValue) elementValue).elementValues;
    assertTrue(elementValueArray[0] instanceof AnnotationElementValue);
    assertTrue(elementValue instanceof ArrayElementValue);
    assertEquals(1, elementValueArray.length);
  }

  /**
   * Test {@link AnnotationUsageMarker#visitAnnotationDefaultAttribute(Clazz, Method,
   * AnnotationDefaultAttribute)}.
   *
   * <ul>
   *   <li>Then first element {@link ArrayElementValue}.
   * </ul>
   *
   * <p>Method under test: {@link AnnotationUsageMarker#visitAnnotationDefaultAttribute(Clazz,
   * Method, AnnotationDefaultAttribute)}
   */
  @Test
  @DisplayName(
      "Test visitAnnotationDefaultAttribute(Clazz, Method, AnnotationDefaultAttribute); then first element ArrayElementValue")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void AnnotationUsageMarker.visitAnnotationDefaultAttribute(Clazz, Method, AnnotationDefaultAttribute)"
  })
  void testVisitAnnotationDefaultAttribute_thenFirstElementArrayElementValue() {
    // Arrange
    AnnotationUsageMarker annotationUsageMarker = new AnnotationUsageMarker(new ClassUsageMarker());
    LibraryClass clazz = new LibraryClass();
    LibraryMethod method = new LibraryMethod(1, "Name", "Descriptor");

    AnnotationDefaultAttribute annotationDefaultAttribute =
        new AnnotationDefaultAttribute(
            0, new ArrayElementValue(1, 1, new ElementValue[] {new ArrayElementValue()}));

    // Act
    annotationUsageMarker.visitAnnotationDefaultAttribute(
        clazz, method, annotationDefaultAttribute);

    // Assert
    ElementValue elementValue = annotationDefaultAttribute.defaultValue;
    ElementValue[] elementValueArray = ((ArrayElementValue) elementValue).elementValues;
    assertTrue(elementValueArray[0] instanceof ArrayElementValue);
    assertTrue(elementValue instanceof ArrayElementValue);
    assertEquals(1, elementValueArray.length);
  }

  /**
   * Test {@link AnnotationUsageMarker#visitAnnotationDefaultAttribute(Clazz, Method,
   * AnnotationDefaultAttribute)}.
   *
   * <ul>
   *   <li>Then first element {@link ClassElementValue}.
   * </ul>
   *
   * <p>Method under test: {@link AnnotationUsageMarker#visitAnnotationDefaultAttribute(Clazz,
   * Method, AnnotationDefaultAttribute)}
   */
  @Test
  @DisplayName(
      "Test visitAnnotationDefaultAttribute(Clazz, Method, AnnotationDefaultAttribute); then first element ClassElementValue")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void AnnotationUsageMarker.visitAnnotationDefaultAttribute(Clazz, Method, AnnotationDefaultAttribute)"
  })
  void testVisitAnnotationDefaultAttribute_thenFirstElementClassElementValue() {
    // Arrange
    AnnotationUsageMarker annotationUsageMarker = new AnnotationUsageMarker(new ClassUsageMarker());
    LibraryClass clazz = new LibraryClass();
    LibraryMethod method = new LibraryMethod(1, "Name", "Descriptor");

    AnnotationDefaultAttribute annotationDefaultAttribute =
        new AnnotationDefaultAttribute(
            0, new ArrayElementValue(1, 1, new ElementValue[] {new ClassElementValue(1, 1)}));

    // Act
    annotationUsageMarker.visitAnnotationDefaultAttribute(
        clazz, method, annotationDefaultAttribute);

    // Assert
    ElementValue elementValue = annotationDefaultAttribute.defaultValue;
    assertTrue(elementValue instanceof ArrayElementValue);
    ElementValue[] elementValueArray = ((ArrayElementValue) elementValue).elementValues;
    assertTrue(elementValueArray[0] instanceof ClassElementValue);
    assertEquals(1, elementValueArray.length);
  }

  /**
   * Test {@link AnnotationUsageMarker#visitAnnotation(Clazz, Annotation)} with {@code clazz},
   * {@code annotation}.
   *
   * <p>Method under test: {@link AnnotationUsageMarker#visitAnnotation(Clazz, Annotation)}
   */
  @Test
  @DisplayName("Test visitAnnotation(Clazz, Annotation) with 'clazz', 'annotation'")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void AnnotationUsageMarker.visitAnnotation(Clazz, Annotation)"})
  void testVisitAnnotationWithClazzAnnotation() {
    // Arrange
    AnnotationUsageMarker annotationUsageMarker = new AnnotationUsageMarker(new ClassUsageMarker());
    LibraryClass clazz = new LibraryClass();
    Annotation annotation = new Annotation(1, 0, new ElementValue[] {new AnnotationElementValue()});

    annotation.referencedClasses = null;

    // Act
    annotationUsageMarker.visitAnnotation(clazz, annotation);

    // Assert that nothing has changed
    ElementValue[] elementValueArray = annotation.elementValues;
    assertTrue(elementValueArray[0] instanceof AnnotationElementValue);
    assertEquals(1, elementValueArray.length);
  }

  /**
   * Test {@link AnnotationUsageMarker#visitAnnotation(Clazz, Annotation)} with {@code clazz},
   * {@code annotation}.
   *
   * <p>Method under test: {@link AnnotationUsageMarker#visitAnnotation(Clazz, Annotation)}
   */
  @Test
  @DisplayName("Test visitAnnotation(Clazz, Annotation) with 'clazz', 'annotation'")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void AnnotationUsageMarker.visitAnnotation(Clazz, Annotation)"})
  void testVisitAnnotationWithClazzAnnotation2() {
    // Arrange
    ShortestUsageMarker usageMarker = new ShortestUsageMarker();
    AnnotationUsageMarker annotationUsageMarker =
        new AnnotationUsageMarker(new ShortestClassUsageMarker(usageMarker, "Just cause"));
    LibraryClass clazz = new LibraryClass();
    Annotation annotation = new Annotation();

    // Act
    annotationUsageMarker.visitAnnotation(clazz, annotation);

    // Assert
    ShortestUsageMark expectedProcessingInfo = usageMarker.currentUsageMark;
    assertSame(expectedProcessingInfo, annotation.getProcessingInfo());
  }

  /**
   * Test {@link AnnotationUsageMarker#visitAnnotation(Clazz, Annotation)} with {@code clazz},
   * {@code annotation}.
   *
   * <p>Method under test: {@link AnnotationUsageMarker#visitAnnotation(Clazz, Annotation)}
   */
  @Test
  @DisplayName("Test visitAnnotation(Clazz, Annotation) with 'clazz', 'annotation'")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void AnnotationUsageMarker.visitAnnotation(Clazz, Annotation)"})
  void testVisitAnnotationWithClazzAnnotation3() {
    // Arrange
    AnnotationUsageMarker annotationUsageMarker = new AnnotationUsageMarker(new ClassUsageMarker());
    LibraryClass clazz = new LibraryClass();
    Annotation annotation = new Annotation(0, 0, new ElementValue[] {new AnnotationElementValue()});

    annotation.referencedClasses =
        new Clazz[] {new LibraryClass(1, "kotlin/Metadata", "kotlin/Metadata")};

    // Act
    annotationUsageMarker.visitAnnotation(clazz, annotation);

    // Assert that nothing has changed
    ElementValue[] elementValueArray = annotation.elementValues;
    assertTrue(elementValueArray[0] instanceof AnnotationElementValue);
    assertEquals(1, elementValueArray.length);
  }

  /**
   * Test {@link AnnotationUsageMarker#visitAnnotation(Clazz, Annotation)} with {@code clazz},
   * {@code annotation}.
   *
   * <p>Method under test: {@link AnnotationUsageMarker#visitAnnotation(Clazz, Annotation)}
   */
  @Test
  @DisplayName("Test visitAnnotation(Clazz, Annotation) with 'clazz', 'annotation'")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void AnnotationUsageMarker.visitAnnotation(Clazz, Annotation)"})
  void testVisitAnnotationWithClazzAnnotation4() {
    // Arrange
    AnnotationUsageMarker annotationUsageMarker = new AnnotationUsageMarker(new ClassUsageMarker());
    LibraryClass clazz = new LibraryClass();
    Annotation annotation = new Annotation(0, 0, new ElementValue[] {new AnnotationElementValue()});

    annotation.referencedClasses = new Clazz[] {new NamedClass("kotlin/Metadata")};

    // Act
    annotationUsageMarker.visitAnnotation(clazz, annotation);

    // Assert that nothing has changed
    ElementValue[] elementValueArray = annotation.elementValues;
    assertTrue(elementValueArray[0] instanceof AnnotationElementValue);
    assertEquals(1, elementValueArray.length);
  }

  /**
   * Test {@link AnnotationUsageMarker#visitAnnotation(Clazz, Annotation)} with {@code clazz},
   * {@code annotation}.
   *
   * <p>Method under test: {@link AnnotationUsageMarker#visitAnnotation(Clazz, Annotation)}
   */
  @Test
  @DisplayName("Test visitAnnotation(Clazz, Annotation) with 'clazz', 'annotation'")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void AnnotationUsageMarker.visitAnnotation(Clazz, Annotation)"})
  void testVisitAnnotationWithClazzAnnotation5() {
    // Arrange
    AnnotationUsageMarker annotationUsageMarker = new AnnotationUsageMarker(new ClassUsageMarker());
    LibraryClass clazz = new LibraryClass();
    Annotation annotation = new Annotation(0, 0, new ElementValue[] {new AnnotationElementValue()});

    annotation.referencedClasses =
        new Clazz[] {new LibraryClass(1, "This Class Name", "kotlin/Metadata")};

    // Act
    annotationUsageMarker.visitAnnotation(clazz, annotation);

    // Assert that nothing has changed
    ElementValue[] elementValueArray = annotation.elementValues;
    assertTrue(elementValueArray[0] instanceof AnnotationElementValue);
    assertEquals(1, elementValueArray.length);
  }

  /**
   * Test {@link AnnotationUsageMarker#visitAnnotation(Clazz, Annotation)} with {@code clazz},
   * {@code annotation}.
   *
   * <p>Method under test: {@link AnnotationUsageMarker#visitAnnotation(Clazz, Annotation)}
   */
  @Test
  @DisplayName("Test visitAnnotation(Clazz, Annotation) with 'clazz', 'annotation'")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void AnnotationUsageMarker.visitAnnotation(Clazz, Annotation)"})
  void testVisitAnnotationWithClazzAnnotation6() {
    // Arrange
    AnnotationUsageMarker annotationUsageMarker = new AnnotationUsageMarker(new ClassUsageMarker());
    LibraryClass clazz = new LibraryClass();

    LibraryClass libraryClass = new LibraryClass(1, "kotlin/Metadata", "kotlin/Metadata");
    libraryClass.addProcessingFlags(-1, 1, 2, 1);
    Annotation annotation = new Annotation(0, 0, new ElementValue[] {new AnnotationElementValue()});

    annotation.referencedClasses = new Clazz[] {libraryClass};

    // Act
    annotationUsageMarker.visitAnnotation(clazz, annotation);

    // Assert that nothing has changed
    ElementValue[] elementValueArray = annotation.elementValues;
    assertTrue(elementValueArray[0] instanceof AnnotationElementValue);
    assertEquals(1, elementValueArray.length);
  }

  /**
   * Test {@link AnnotationUsageMarker#visitAnnotation(Clazz, Annotation)} with {@code clazz},
   * {@code annotation}.
   *
   * <p>Method under test: {@link AnnotationUsageMarker#visitAnnotation(Clazz, Annotation)}
   */
  @Test
  @DisplayName("Test visitAnnotation(Clazz, Annotation) with 'clazz', 'annotation'")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void AnnotationUsageMarker.visitAnnotation(Clazz, Annotation)"})
  void testVisitAnnotationWithClazzAnnotation7() {
    // Arrange
    AnnotationUsageMarker annotationUsageMarker =
        new AnnotationUsageMarker(
            new ShortestClassUsageMarker(new ShortestUsageMarker(), "Just cause"));
    LibraryClass clazz = new LibraryClass();
    Annotation annotation = new Annotation(0, 0, new ElementValue[] {new AnnotationElementValue()});

    annotation.referencedClasses = new Clazz[] {new NamedClass("kotlin/Metadata")};

    // Act
    annotationUsageMarker.visitAnnotation(clazz, annotation);

    // Assert that nothing has changed
    ElementValue[] elementValueArray = annotation.elementValues;
    assertTrue(elementValueArray[0] instanceof AnnotationElementValue);
    assertEquals(1, elementValueArray.length);
  }

  /**
   * Test {@link AnnotationUsageMarker#visitAnnotation(Clazz, Annotation)} with {@code clazz},
   * {@code annotation}.
   *
   * <p>Method under test: {@link AnnotationUsageMarker#visitAnnotation(Clazz, Annotation)}
   */
  @Test
  @DisplayName("Test visitAnnotation(Clazz, Annotation) with 'clazz', 'annotation'")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void AnnotationUsageMarker.visitAnnotation(Clazz, Annotation)"})
  void testVisitAnnotationWithClazzAnnotation8() {
    // Arrange
    AnnotationUsageMarker annotationUsageMarker = new AnnotationUsageMarker(new ClassUsageMarker());
    LibraryClass clazz = new LibraryClass();
    Annotation annotation = new Annotation(0, 0, new ElementValue[] {new AnnotationElementValue()});

    annotation.referencedClasses =
        new Clazz[] {new NamedClass("kotlin/coroutines/jvm/internal/DebugMetadata")};

    // Act
    annotationUsageMarker.visitAnnotation(clazz, annotation);

    // Assert that nothing has changed
    ElementValue[] elementValueArray = annotation.elementValues;
    assertTrue(elementValueArray[0] instanceof AnnotationElementValue);
    assertEquals(1, elementValueArray.length);
  }

  /**
   * Test {@link AnnotationUsageMarker#visitAnnotation(Clazz, Annotation)} with {@code clazz},
   * {@code annotation}.
   *
   * <p>Method under test: {@link AnnotationUsageMarker#visitAnnotation(Clazz, Annotation)}
   */
  @Test
  @DisplayName("Test visitAnnotation(Clazz, Annotation) with 'clazz', 'annotation'")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void AnnotationUsageMarker.visitAnnotation(Clazz, Annotation)"})
  void testVisitAnnotationWithClazzAnnotation9() {
    // Arrange
    AnnotationUsageMarker annotationUsageMarker = new AnnotationUsageMarker(new ClassUsageMarker());
    LibraryClass clazz = new LibraryClass();
    Annotation annotation =
        new Annotation(0, 1, new ElementValue[] {new AnnotationElementValue(1, new Annotation())});

    annotation.referencedClasses = null;

    // Act
    annotationUsageMarker.visitAnnotation(clazz, annotation);

    // Assert
    ElementValue[] elementValueArray = annotation.elementValues;
    assertTrue(elementValueArray[0] instanceof AnnotationElementValue);
    assertEquals(1, elementValueArray.length);
  }

  /**
   * Test {@link AnnotationUsageMarker#visitAnnotation(Clazz, Annotation)} with {@code clazz},
   * {@code annotation}.
   *
   * <ul>
   *   <li>Given array of {@link Clazz} with {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link AnnotationUsageMarker#visitAnnotation(Clazz, Annotation)}
   */
  @Test
  @DisplayName(
      "Test visitAnnotation(Clazz, Annotation) with 'clazz', 'annotation'; given array of Clazz with 'null'")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void AnnotationUsageMarker.visitAnnotation(Clazz, Annotation)"})
  void testVisitAnnotationWithClazzAnnotation_givenArrayOfClazzWithNull() {
    // Arrange
    AnnotationUsageMarker annotationUsageMarker = new AnnotationUsageMarker(new ClassUsageMarker());
    LibraryClass clazz = new LibraryClass();
    Annotation annotation = new Annotation(0, 0, new ElementValue[] {new AnnotationElementValue()});

    annotation.referencedClasses = new Clazz[] {null};

    // Act
    annotationUsageMarker.visitAnnotation(clazz, annotation);

    // Assert that nothing has changed
    ElementValue[] elementValueArray = annotation.elementValues;
    assertTrue(elementValueArray[0] instanceof AnnotationElementValue);
    assertEquals(1, elementValueArray.length);
  }

  /**
   * Test {@link AnnotationUsageMarker#visitAnnotation(Clazz, Annotation)} with {@code clazz},
   * {@code annotation}.
   *
   * <ul>
   *   <li>Then first element {@link ArrayElementValue}.
   * </ul>
   *
   * <p>Method under test: {@link AnnotationUsageMarker#visitAnnotation(Clazz, Annotation)}
   */
  @Test
  @DisplayName(
      "Test visitAnnotation(Clazz, Annotation) with 'clazz', 'annotation'; then first element ArrayElementValue")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void AnnotationUsageMarker.visitAnnotation(Clazz, Annotation)"})
  void testVisitAnnotationWithClazzAnnotation_thenFirstElementArrayElementValue() {
    // Arrange
    AnnotationUsageMarker annotationUsageMarker = new AnnotationUsageMarker(new ClassUsageMarker());
    LibraryClass clazz = new LibraryClass();
    Annotation annotation = new Annotation(0, 1, new ElementValue[] {new ArrayElementValue()});

    annotation.referencedClasses = null;

    // Act
    annotationUsageMarker.visitAnnotation(clazz, annotation);

    // Assert
    ElementValue[] elementValueArray = annotation.elementValues;
    assertTrue(elementValueArray[0] instanceof ArrayElementValue);
    assertEquals(1, elementValueArray.length);
  }

  /**
   * Test {@link AnnotationUsageMarker#visitAnnotation(Clazz, Annotation)} with {@code clazz},
   * {@code annotation}.
   *
   * <ul>
   *   <li>Then first element {@link ClassElementValue}.
   * </ul>
   *
   * <p>Method under test: {@link AnnotationUsageMarker#visitAnnotation(Clazz, Annotation)}
   */
  @Test
  @DisplayName(
      "Test visitAnnotation(Clazz, Annotation) with 'clazz', 'annotation'; then first element ClassElementValue")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void AnnotationUsageMarker.visitAnnotation(Clazz, Annotation)"})
  void testVisitAnnotationWithClazzAnnotation_thenFirstElementClassElementValue() {
    // Arrange
    AnnotationUsageMarker annotationUsageMarker = new AnnotationUsageMarker(new ClassUsageMarker());
    LibraryClass clazz = new LibraryClass();
    Annotation annotation = new Annotation(0, 1, new ElementValue[] {new ClassElementValue(1, 1)});

    annotation.referencedClasses = null;

    // Act
    annotationUsageMarker.visitAnnotation(clazz, annotation);

    // Assert
    ElementValue[] elementValueArray = annotation.elementValues;
    assertTrue(elementValueArray[0] instanceof ClassElementValue);
    assertEquals(1, elementValueArray.length);
  }

  /**
   * Test {@link AnnotationUsageMarker#visitAnnotation(Clazz, Annotation)} with {@code clazz},
   * {@code annotation}.
   *
   * <ul>
   *   <li>Then first element {@link ConstantElementValue}.
   * </ul>
   *
   * <p>Method under test: {@link AnnotationUsageMarker#visitAnnotation(Clazz, Annotation)}
   */
  @Test
  @DisplayName(
      "Test visitAnnotation(Clazz, Annotation) with 'clazz', 'annotation'; then first element ConstantElementValue")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void AnnotationUsageMarker.visitAnnotation(Clazz, Annotation)"})
  void testVisitAnnotationWithClazzAnnotation_thenFirstElementConstantElementValue() {
    // Arrange
    AnnotationUsageMarker annotationUsageMarker = new AnnotationUsageMarker(new ClassUsageMarker());
    LibraryClass clazz = new LibraryClass();
    Annotation annotation =
        new Annotation(0, 1, new ElementValue[] {new ConstantElementValue('\u0001')});

    annotation.referencedClasses = null;

    // Act
    annotationUsageMarker.visitAnnotation(clazz, annotation);

    // Assert
    ElementValue[] elementValueArray = annotation.elementValues;
    assertTrue(elementValueArray[0] instanceof ConstantElementValue);
    assertEquals(1, elementValueArray.length);
  }

  /**
   * Test {@link AnnotationUsageMarker#visitAnnotation(Clazz, Annotation)} with {@code clazz},
   * {@code annotation}.
   *
   * <ul>
   *   <li>Then first element {@link EnumConstantElementValue}.
   * </ul>
   *
   * <p>Method under test: {@link AnnotationUsageMarker#visitAnnotation(Clazz, Annotation)}
   */
  @Test
  @DisplayName(
      "Test visitAnnotation(Clazz, Annotation) with 'clazz', 'annotation'; then first element EnumConstantElementValue")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void AnnotationUsageMarker.visitAnnotation(Clazz, Annotation)"})
  void testVisitAnnotationWithClazzAnnotation_thenFirstElementEnumConstantElementValue() {
    // Arrange
    AnnotationUsageMarker annotationUsageMarker = new AnnotationUsageMarker(new ClassUsageMarker());
    LibraryClass clazz = new LibraryClass();
    Annotation annotation =
        new Annotation(0, 1, new ElementValue[] {new EnumConstantElementValue(1, 1, 1)});

    annotation.referencedClasses = null;

    // Act
    annotationUsageMarker.visitAnnotation(clazz, annotation);

    // Assert
    ElementValue[] elementValueArray = annotation.elementValues;
    assertTrue(elementValueArray[0] instanceof EnumConstantElementValue);
    assertEquals(1, elementValueArray.length);
  }

  /**
   * Test {@link AnnotationUsageMarker#visitConstantElementValue(Clazz, Annotation,
   * ConstantElementValue)}.
   *
   * <p>Method under test: {@link AnnotationUsageMarker#visitConstantElementValue(Clazz, Annotation,
   * ConstantElementValue)}
   */
  @Test
  @DisplayName("Test visitConstantElementValue(Clazz, Annotation, ConstantElementValue)")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void AnnotationUsageMarker.visitConstantElementValue(Clazz, Annotation, ConstantElementValue)"
  })
  void testVisitConstantElementValue() {
    // Arrange
    ShortestUsageMarker usageMarker = new ShortestUsageMarker();
    AnnotationUsageMarker annotationUsageMarker =
        new AnnotationUsageMarker(new ShortestClassUsageMarker(usageMarker, "Just cause"));
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
   * Test {@link AnnotationUsageMarker#visitConstantElementValue(Clazz, Annotation,
   * ConstantElementValue)}.
   *
   * <p>Method under test: {@link AnnotationUsageMarker#visitConstantElementValue(Clazz, Annotation,
   * ConstantElementValue)}
   */
  @Test
  @DisplayName("Test visitConstantElementValue(Clazz, Annotation, ConstantElementValue)")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void AnnotationUsageMarker.visitConstantElementValue(Clazz, Annotation, ConstantElementValue)"
  })
  void testVisitConstantElementValue2() {
    // Arrange
    AnnotationUsageMarker annotationUsageMarker = new AnnotationUsageMarker(new ClassUsageMarker());
    LibraryClass clazz = new LibraryClass();
    Annotation annotation = new Annotation();
    ConstantElementValue constantElementValue = new ConstantElementValue('A');
    constantElementValue.referencedMethod = new ProgramMethod();

    // Act
    annotationUsageMarker.visitConstantElementValue(clazz, annotation, constantElementValue);

    // Assert that nothing has changed
    assertNull(constantElementValue.getProcessingInfo());
  }

  /**
   * Test {@link AnnotationUsageMarker#visitConstantElementValue(Clazz, Annotation,
   * ConstantElementValue)}.
   *
   * <p>Method under test: {@link AnnotationUsageMarker#visitConstantElementValue(Clazz, Annotation,
   * ConstantElementValue)}
   */
  @Test
  @DisplayName("Test visitConstantElementValue(Clazz, Annotation, ConstantElementValue)")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void AnnotationUsageMarker.visitConstantElementValue(Clazz, Annotation, ConstantElementValue)"
  })
  void testVisitConstantElementValue3() {
    // Arrange
    AnnotationUsageMarker annotationUsageMarker =
        new AnnotationUsageMarker(
            new ShortestClassUsageMarker(new ShortestUsageMarker(), "Just cause"));
    LibraryClass clazz = new LibraryClass();
    Annotation annotation = new Annotation();
    ConstantElementValue constantElementValue = new ConstantElementValue('A');
    constantElementValue.referencedMethod = new ProgramMethod();

    // Act
    annotationUsageMarker.visitConstantElementValue(clazz, annotation, constantElementValue);

    // Assert that nothing has changed
    assertNull(constantElementValue.getProcessingInfo());
  }

  /**
   * Test {@link AnnotationUsageMarker#visitConstantElementValue(Clazz, Annotation,
   * ConstantElementValue)}.
   *
   * <p>Method under test: {@link AnnotationUsageMarker#visitConstantElementValue(Clazz, Annotation,
   * ConstantElementValue)}
   */
  @Test
  @DisplayName("Test visitConstantElementValue(Clazz, Annotation, ConstantElementValue)")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void AnnotationUsageMarker.visitConstantElementValue(Clazz, Annotation, ConstantElementValue)"
  })
  void testVisitConstantElementValue4() {
    // Arrange
    AnnotationUsageMarker annotationUsageMarker =
        new AnnotationUsageMarker(new ClassUsageMarker(new ShortestUsageMarker()));
    LibraryClass clazz = new LibraryClass();
    Annotation annotation = new Annotation();
    ConstantElementValue constantElementValue = new ConstantElementValue('A');
    constantElementValue.referencedMethod = new ProgramMethod();

    // Act
    annotationUsageMarker.visitConstantElementValue(clazz, annotation, constantElementValue);

    // Assert that nothing has changed
    assertNull(constantElementValue.getProcessingInfo());
  }

  /**
   * Test {@link AnnotationUsageMarker#visitEnumConstantElementValue(Clazz, Annotation,
   * EnumConstantElementValue)}.
   *
   * <p>Method under test: {@link AnnotationUsageMarker#visitEnumConstantElementValue(Clazz,
   * Annotation, EnumConstantElementValue)}
   */
  @Test
  @DisplayName("Test visitEnumConstantElementValue(Clazz, Annotation, EnumConstantElementValue)")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void AnnotationUsageMarker.visitEnumConstantElementValue(Clazz, Annotation, EnumConstantElementValue)"
  })
  void testVisitEnumConstantElementValue() {
    // Arrange
    ShortestUsageMarker usageMarker = new ShortestUsageMarker();
    AnnotationUsageMarker annotationUsageMarker =
        new AnnotationUsageMarker(new ShortestClassUsageMarker(usageMarker, "Just cause"));
    LibraryClass clazz = new LibraryClass();
    Annotation annotation = new Annotation();
    EnumConstantElementValue enumConstantElementValue = new EnumConstantElementValue(1, 1, 1);

    // Act
    annotationUsageMarker.visitEnumConstantElementValue(
        clazz, annotation, enumConstantElementValue);

    // Assert
    ShortestUsageMark expectedProcessingInfo = usageMarker.currentUsageMark;
    assertSame(expectedProcessingInfo, enumConstantElementValue.getProcessingInfo());
  }

  /**
   * Test {@link AnnotationUsageMarker#visitEnumConstantElementValue(Clazz, Annotation,
   * EnumConstantElementValue)}.
   *
   * <p>Method under test: {@link AnnotationUsageMarker#visitEnumConstantElementValue(Clazz,
   * Annotation, EnumConstantElementValue)}
   */
  @Test
  @DisplayName("Test visitEnumConstantElementValue(Clazz, Annotation, EnumConstantElementValue)")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void AnnotationUsageMarker.visitEnumConstantElementValue(Clazz, Annotation, EnumConstantElementValue)"
  })
  void testVisitEnumConstantElementValue2() {
    // Arrange
    AnnotationUsageMarker annotationUsageMarker = new AnnotationUsageMarker(new ClassUsageMarker());
    LibraryClass clazz = new LibraryClass();
    Annotation annotation = new Annotation();
    EnumConstantElementValue enumConstantElementValue = new EnumConstantElementValue(0, 0, 0);

    enumConstantElementValue.referencedClasses =
        new Clazz[] {new LibraryClass(1, "kotlin/Metadata", "kotlin/Metadata")};

    // Act
    annotationUsageMarker.visitEnumConstantElementValue(
        clazz, annotation, enumConstantElementValue);

    // Assert that nothing has changed
    assertNull(enumConstantElementValue.getProcessingInfo());
  }

  /**
   * Test {@link AnnotationUsageMarker#visitEnumConstantElementValue(Clazz, Annotation,
   * EnumConstantElementValue)}.
   *
   * <p>Method under test: {@link AnnotationUsageMarker#visitEnumConstantElementValue(Clazz,
   * Annotation, EnumConstantElementValue)}
   */
  @Test
  @DisplayName("Test visitEnumConstantElementValue(Clazz, Annotation, EnumConstantElementValue)")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void AnnotationUsageMarker.visitEnumConstantElementValue(Clazz, Annotation, EnumConstantElementValue)"
  })
  void testVisitEnumConstantElementValue3() {
    // Arrange
    AnnotationUsageMarker annotationUsageMarker = new AnnotationUsageMarker(new ClassUsageMarker());
    LibraryClass clazz = new LibraryClass();
    Annotation annotation = new Annotation();
    EnumConstantElementValue enumConstantElementValue = new EnumConstantElementValue(0, 0, 0);

    enumConstantElementValue.referencedClasses = new Clazz[] {new NamedClass("kotlin/Metadata")};

    // Act
    annotationUsageMarker.visitEnumConstantElementValue(
        clazz, annotation, enumConstantElementValue);

    // Assert that nothing has changed
    assertNull(enumConstantElementValue.getProcessingInfo());
  }

  /**
   * Test {@link AnnotationUsageMarker#visitEnumConstantElementValue(Clazz, Annotation,
   * EnumConstantElementValue)}.
   *
   * <p>Method under test: {@link AnnotationUsageMarker#visitEnumConstantElementValue(Clazz,
   * Annotation, EnumConstantElementValue)}
   */
  @Test
  @DisplayName("Test visitEnumConstantElementValue(Clazz, Annotation, EnumConstantElementValue)")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void AnnotationUsageMarker.visitEnumConstantElementValue(Clazz, Annotation, EnumConstantElementValue)"
  })
  void testVisitEnumConstantElementValue4() {
    // Arrange
    AnnotationUsageMarker annotationUsageMarker =
        new AnnotationUsageMarker(
            new ShortestClassUsageMarker(new ShortestUsageMarker(), "Just cause"));
    LibraryClass clazz = new LibraryClass();
    Annotation annotation = new Annotation();
    EnumConstantElementValue enumConstantElementValue = new EnumConstantElementValue(0, 0, 0);

    enumConstantElementValue.referencedClasses = new Clazz[] {new NamedClass("kotlin/Metadata")};

    // Act
    annotationUsageMarker.visitEnumConstantElementValue(
        clazz, annotation, enumConstantElementValue);

    // Assert that nothing has changed
    assertNull(enumConstantElementValue.getProcessingInfo());
  }

  /**
   * Test {@link AnnotationUsageMarker#visitEnumConstantElementValue(Clazz, Annotation,
   * EnumConstantElementValue)}.
   *
   * <p>Method under test: {@link AnnotationUsageMarker#visitEnumConstantElementValue(Clazz,
   * Annotation, EnumConstantElementValue)}
   */
  @Test
  @DisplayName("Test visitEnumConstantElementValue(Clazz, Annotation, EnumConstantElementValue)")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void AnnotationUsageMarker.visitEnumConstantElementValue(Clazz, Annotation, EnumConstantElementValue)"
  })
  void testVisitEnumConstantElementValue5() {
    // Arrange
    AnnotationUsageMarker annotationUsageMarker = new AnnotationUsageMarker(new ClassUsageMarker());
    LibraryClass clazz = new LibraryClass();
    Annotation annotation = new Annotation();
    EnumConstantElementValue enumConstantElementValue = new EnumConstantElementValue(0, 0, 0);

    enumConstantElementValue.referencedClasses =
        new Clazz[] {new NamedClass("kotlin/coroutines/jvm/internal/DebugMetadata")};

    // Act
    annotationUsageMarker.visitEnumConstantElementValue(
        clazz, annotation, enumConstantElementValue);

    // Assert that nothing has changed
    assertNull(enumConstantElementValue.getProcessingInfo());
  }

  /**
   * Test {@link AnnotationUsageMarker#visitClassElementValue(Clazz, Annotation,
   * ClassElementValue)}.
   *
   * <p>Method under test: {@link AnnotationUsageMarker#visitClassElementValue(Clazz, Annotation,
   * ClassElementValue)}
   */
  @Test
  @DisplayName("Test visitClassElementValue(Clazz, Annotation, ClassElementValue)")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void AnnotationUsageMarker.visitClassElementValue(Clazz, Annotation, ClassElementValue)"
  })
  void testVisitClassElementValue() {
    // Arrange
    ShortestUsageMarker usageMarker = new ShortestUsageMarker();
    AnnotationUsageMarker annotationUsageMarker =
        new AnnotationUsageMarker(new ShortestClassUsageMarker(usageMarker, "Just cause"));
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
   * Test {@link AnnotationUsageMarker#visitClassElementValue(Clazz, Annotation,
   * ClassElementValue)}.
   *
   * <p>Method under test: {@link AnnotationUsageMarker#visitClassElementValue(Clazz, Annotation,
   * ClassElementValue)}
   */
  @Test
  @DisplayName("Test visitClassElementValue(Clazz, Annotation, ClassElementValue)")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void AnnotationUsageMarker.visitClassElementValue(Clazz, Annotation, ClassElementValue)"
  })
  void testVisitClassElementValue2() {
    // Arrange
    ShortestUsageMarker usageMarker = new ShortestUsageMarker();
    AnnotationUsageMarker annotationUsageMarker =
        new AnnotationUsageMarker(new ShortestClassUsageMarker(usageMarker, "Just cause"));
    LibraryClass clazz = new LibraryClass();
    Annotation annotation = new Annotation();
    ClassElementValue classElementValue = new ClassElementValue(0, 0);

    classElementValue.referencedClasses =
        new Clazz[] {new LibraryClass(1, "This Class Name", "Super Class Name")};

    // Act
    annotationUsageMarker.visitClassElementValue(clazz, annotation, classElementValue);

    // Assert
    Clazz[] clazzArray = classElementValue.referencedClasses;
    Clazz clazz2 = clazzArray[0];
    assertTrue(clazz2 instanceof LibraryClass);
    assertEquals(1, clazzArray.length);
    ShortestUsageMark shortestUsageMark = usageMarker.currentUsageMark;
    assertSame(shortestUsageMark, clazz2.getProcessingInfo());
    assertSame(shortestUsageMark, classElementValue.getProcessingInfo());
  }

  /**
   * Test {@link AnnotationUsageMarker#visitClassElementValue(Clazz, Annotation,
   * ClassElementValue)}.
   *
   * <p>Method under test: {@link AnnotationUsageMarker#visitClassElementValue(Clazz, Annotation,
   * ClassElementValue)}
   */
  @Test
  @DisplayName("Test visitClassElementValue(Clazz, Annotation, ClassElementValue)")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void AnnotationUsageMarker.visitClassElementValue(Clazz, Annotation, ClassElementValue)"
  })
  void testVisitClassElementValue3() {
    // Arrange
    AnnotationUsageMarker annotationUsageMarker = new AnnotationUsageMarker(new ClassUsageMarker());
    LibraryClass clazz = new LibraryClass();
    Annotation annotation = new Annotation();
    ClassElementValue classElementValue = new ClassElementValue(1, 1);

    classElementValue.referencedMethod = new ProgramMethod();

    // Act
    annotationUsageMarker.visitClassElementValue(clazz, annotation, classElementValue);

    // Assert that nothing has changed
    assertNull(classElementValue.getProcessingInfo());
  }

  /**
   * Test {@link AnnotationUsageMarker#visitClassElementValue(Clazz, Annotation,
   * ClassElementValue)}.
   *
   * <p>Method under test: {@link AnnotationUsageMarker#visitClassElementValue(Clazz, Annotation,
   * ClassElementValue)}
   */
  @Test
  @DisplayName("Test visitClassElementValue(Clazz, Annotation, ClassElementValue)")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void AnnotationUsageMarker.visitClassElementValue(Clazz, Annotation, ClassElementValue)"
  })
  void testVisitClassElementValue4() {
    // Arrange
    AnnotationUsageMarker annotationUsageMarker =
        new AnnotationUsageMarker(
            new ShortestClassUsageMarker(new ShortestUsageMarker(), "Just cause"));
    LibraryClass clazz = new LibraryClass();
    Annotation annotation = new Annotation();
    ClassElementValue classElementValue = new ClassElementValue(1, 1);

    classElementValue.referencedMethod = new ProgramMethod();

    // Act
    annotationUsageMarker.visitClassElementValue(clazz, annotation, classElementValue);

    // Assert that nothing has changed
    assertNull(classElementValue.getProcessingInfo());
  }

  /**
   * Test {@link AnnotationUsageMarker#visitClassElementValue(Clazz, Annotation,
   * ClassElementValue)}.
   *
   * <ul>
   *   <li>Then first element {@link LibraryClass}.
   * </ul>
   *
   * <p>Method under test: {@link AnnotationUsageMarker#visitClassElementValue(Clazz, Annotation,
   * ClassElementValue)}
   */
  @Test
  @DisplayName(
      "Test visitClassElementValue(Clazz, Annotation, ClassElementValue); then first element LibraryClass")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void AnnotationUsageMarker.visitClassElementValue(Clazz, Annotation, ClassElementValue)"
  })
  void testVisitClassElementValue_thenFirstElementLibraryClass() {
    // Arrange
    AnnotationUsageMarker annotationUsageMarker = new AnnotationUsageMarker(new ClassUsageMarker());
    LibraryClass clazz = new LibraryClass();
    Annotation annotation = new Annotation();
    ClassElementValue classElementValue = new ClassElementValue(0, 0);

    classElementValue.referencedClasses =
        new Clazz[] {new LibraryClass(1, "This Class Name", "Super Class Name")};

    // Act
    annotationUsageMarker.visitClassElementValue(clazz, annotation, classElementValue);

    // Assert
    Clazz[] clazzArray = classElementValue.referencedClasses;
    assertTrue(clazzArray[0] instanceof LibraryClass);
    assertEquals(1, clazzArray.length);
  }

  /**
   * Test {@link AnnotationUsageMarker#visitAnnotationElementValue(Clazz, Annotation,
   * AnnotationElementValue)}.
   *
   * <p>Method under test: {@link AnnotationUsageMarker#visitAnnotationElementValue(Clazz,
   * Annotation, AnnotationElementValue)}
   */
  @Test
  @DisplayName("Test visitAnnotationElementValue(Clazz, Annotation, AnnotationElementValue)")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void AnnotationUsageMarker.visitAnnotationElementValue(Clazz, Annotation, AnnotationElementValue)"
  })
  void testVisitAnnotationElementValue() {
    // Arrange
    ShortestUsageMarker usageMarker = new ShortestUsageMarker();
    AnnotationUsageMarker annotationUsageMarker =
        new AnnotationUsageMarker(new ShortestClassUsageMarker(usageMarker, "Just cause"));
    LibraryClass clazz = new LibraryClass();
    Annotation annotation = new Annotation();
    AnnotationElementValue annotationElementValue = new AnnotationElementValue(1, new Annotation());

    annotationElementValue.referencedMethod = null;

    // Act
    annotationUsageMarker.visitAnnotationElementValue(clazz, annotation, annotationElementValue);

    // Assert
    ShortestUsageMark shortestUsageMark = usageMarker.currentUsageMark;
    assertSame(shortestUsageMark, annotationElementValue.getProcessingInfo());
    assertSame(shortestUsageMark, annotationElementValue.annotationValue.getProcessingInfo());
  }

  /**
   * Test {@link AnnotationUsageMarker#visitAnnotationElementValue(Clazz, Annotation,
   * AnnotationElementValue)}.
   *
   * <p>Method under test: {@link AnnotationUsageMarker#visitAnnotationElementValue(Clazz,
   * Annotation, AnnotationElementValue)}
   */
  @Test
  @DisplayName("Test visitAnnotationElementValue(Clazz, Annotation, AnnotationElementValue)")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void AnnotationUsageMarker.visitAnnotationElementValue(Clazz, Annotation, AnnotationElementValue)"
  })
  void testVisitAnnotationElementValue2() {
    // Arrange
    AnnotationUsageMarker annotationUsageMarker = new AnnotationUsageMarker(new ClassUsageMarker());
    LibraryClass clazz = new LibraryClass();
    Annotation annotation = new Annotation();
    AnnotationElementValue annotationElementValue = new AnnotationElementValue(1, new Annotation());

    annotationElementValue.referencedMethod = new ProgramMethod();

    // Act
    annotationUsageMarker.visitAnnotationElementValue(clazz, annotation, annotationElementValue);

    // Assert that nothing has changed
    assertNull(annotationElementValue.getProcessingInfo());
    assertNull(annotationElementValue.annotationValue.getProcessingInfo());
  }

  /**
   * Test {@link AnnotationUsageMarker#visitAnnotationElementValue(Clazz, Annotation,
   * AnnotationElementValue)}.
   *
   * <p>Method under test: {@link AnnotationUsageMarker#visitAnnotationElementValue(Clazz,
   * Annotation, AnnotationElementValue)}
   */
  @Test
  @DisplayName("Test visitAnnotationElementValue(Clazz, Annotation, AnnotationElementValue)")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void AnnotationUsageMarker.visitAnnotationElementValue(Clazz, Annotation, AnnotationElementValue)"
  })
  void testVisitAnnotationElementValue3() {
    // Arrange
    AnnotationUsageMarker annotationUsageMarker =
        new AnnotationUsageMarker(
            new ShortestClassUsageMarker(new ShortestUsageMarker(), "Just cause"));
    LibraryClass clazz = new LibraryClass();
    Annotation annotation = new Annotation();
    AnnotationElementValue annotationElementValue = new AnnotationElementValue(1, new Annotation());

    annotationElementValue.referencedMethod = new ProgramMethod();

    // Act
    annotationUsageMarker.visitAnnotationElementValue(clazz, annotation, annotationElementValue);

    // Assert that nothing has changed
    assertNull(annotationElementValue.getProcessingInfo());
    assertNull(annotationElementValue.annotationValue.getProcessingInfo());
  }

  /**
   * Test {@link AnnotationUsageMarker#visitAnnotationElementValue(Clazz, Annotation,
   * AnnotationElementValue)}.
   *
   * <ul>
   *   <li>Then first element {@link AnnotationElementValue}.
   * </ul>
   *
   * <p>Method under test: {@link AnnotationUsageMarker#visitAnnotationElementValue(Clazz,
   * Annotation, AnnotationElementValue)}
   */
  @Test
  @DisplayName(
      "Test visitAnnotationElementValue(Clazz, Annotation, AnnotationElementValue); then first element AnnotationElementValue")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void AnnotationUsageMarker.visitAnnotationElementValue(Clazz, Annotation, AnnotationElementValue)"
  })
  void testVisitAnnotationElementValue_thenFirstElementAnnotationElementValue() {
    // Arrange
    AnnotationUsageMarker annotationUsageMarker = new AnnotationUsageMarker(new ClassUsageMarker());
    LibraryClass clazz = new LibraryClass();
    Annotation annotation = new Annotation();
    AnnotationElementValue annotationElementValue =
        new AnnotationElementValue(
            1,
            new Annotation(
                1, 1, new ElementValue[] {new AnnotationElementValue(1, new Annotation())}));

    annotationElementValue.referencedMethod = null;

    // Act
    annotationUsageMarker.visitAnnotationElementValue(clazz, annotation, annotationElementValue);

    // Assert
    ElementValue[] elementValueArray = annotationElementValue.annotationValue.elementValues;
    assertTrue(elementValueArray[0] instanceof AnnotationElementValue);
    assertEquals(1, elementValueArray.length);
  }

  /**
   * Test {@link AnnotationUsageMarker#visitAnnotationElementValue(Clazz, Annotation,
   * AnnotationElementValue)}.
   *
   * <ul>
   *   <li>Then first element {@link ArrayElementValue}.
   * </ul>
   *
   * <p>Method under test: {@link AnnotationUsageMarker#visitAnnotationElementValue(Clazz,
   * Annotation, AnnotationElementValue)}
   */
  @Test
  @DisplayName(
      "Test visitAnnotationElementValue(Clazz, Annotation, AnnotationElementValue); then first element ArrayElementValue")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void AnnotationUsageMarker.visitAnnotationElementValue(Clazz, Annotation, AnnotationElementValue)"
  })
  void testVisitAnnotationElementValue_thenFirstElementArrayElementValue() {
    // Arrange
    AnnotationUsageMarker annotationUsageMarker = new AnnotationUsageMarker(new ClassUsageMarker());
    LibraryClass clazz = new LibraryClass();
    Annotation annotation = new Annotation();
    AnnotationElementValue annotationElementValue =
        new AnnotationElementValue(
            1, new Annotation(1, 1, new ElementValue[] {new ArrayElementValue()}));

    annotationElementValue.referencedMethod = null;

    // Act
    annotationUsageMarker.visitAnnotationElementValue(clazz, annotation, annotationElementValue);

    // Assert
    ElementValue[] elementValueArray = annotationElementValue.annotationValue.elementValues;
    assertTrue(elementValueArray[0] instanceof ArrayElementValue);
    assertEquals(1, elementValueArray.length);
  }

  /**
   * Test {@link AnnotationUsageMarker#visitAnnotationElementValue(Clazz, Annotation,
   * AnnotationElementValue)}.
   *
   * <ul>
   *   <li>Then first element {@link ClassElementValue}.
   * </ul>
   *
   * <p>Method under test: {@link AnnotationUsageMarker#visitAnnotationElementValue(Clazz,
   * Annotation, AnnotationElementValue)}
   */
  @Test
  @DisplayName(
      "Test visitAnnotationElementValue(Clazz, Annotation, AnnotationElementValue); then first element ClassElementValue")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void AnnotationUsageMarker.visitAnnotationElementValue(Clazz, Annotation, AnnotationElementValue)"
  })
  void testVisitAnnotationElementValue_thenFirstElementClassElementValue() {
    // Arrange
    AnnotationUsageMarker annotationUsageMarker = new AnnotationUsageMarker(new ClassUsageMarker());
    LibraryClass clazz = new LibraryClass();
    Annotation annotation = new Annotation();
    AnnotationElementValue annotationElementValue =
        new AnnotationElementValue(
            1, new Annotation(1, 1, new ElementValue[] {new ClassElementValue(1, 1)}));

    annotationElementValue.referencedMethod = null;

    // Act
    annotationUsageMarker.visitAnnotationElementValue(clazz, annotation, annotationElementValue);

    // Assert
    ElementValue[] elementValueArray = annotationElementValue.annotationValue.elementValues;
    assertTrue(elementValueArray[0] instanceof ClassElementValue);
    assertEquals(1, elementValueArray.length);
  }

  /**
   * Test {@link AnnotationUsageMarker#visitAnnotationElementValue(Clazz, Annotation,
   * AnnotationElementValue)}.
   *
   * <ul>
   *   <li>Then first element {@link ConstantElementValue}.
   * </ul>
   *
   * <p>Method under test: {@link AnnotationUsageMarker#visitAnnotationElementValue(Clazz,
   * Annotation, AnnotationElementValue)}
   */
  @Test
  @DisplayName(
      "Test visitAnnotationElementValue(Clazz, Annotation, AnnotationElementValue); then first element ConstantElementValue")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void AnnotationUsageMarker.visitAnnotationElementValue(Clazz, Annotation, AnnotationElementValue)"
  })
  void testVisitAnnotationElementValue_thenFirstElementConstantElementValue() {
    // Arrange
    AnnotationUsageMarker annotationUsageMarker = new AnnotationUsageMarker(new ClassUsageMarker());
    LibraryClass clazz = new LibraryClass();
    Annotation annotation = new Annotation();
    AnnotationElementValue annotationElementValue =
        new AnnotationElementValue(
            1, new Annotation(1, 1, new ElementValue[] {new ConstantElementValue('\u0001')}));

    annotationElementValue.referencedMethod = null;

    // Act
    annotationUsageMarker.visitAnnotationElementValue(clazz, annotation, annotationElementValue);

    // Assert
    ElementValue[] elementValueArray = annotationElementValue.annotationValue.elementValues;
    assertTrue(elementValueArray[0] instanceof ConstantElementValue);
    assertEquals(1, elementValueArray.length);
  }

  /**
   * Test {@link AnnotationUsageMarker#visitAnnotationElementValue(Clazz, Annotation,
   * AnnotationElementValue)}.
   *
   * <ul>
   *   <li>Then first element {@link EnumConstantElementValue}.
   * </ul>
   *
   * <p>Method under test: {@link AnnotationUsageMarker#visitAnnotationElementValue(Clazz,
   * Annotation, AnnotationElementValue)}
   */
  @Test
  @DisplayName(
      "Test visitAnnotationElementValue(Clazz, Annotation, AnnotationElementValue); then first element EnumConstantElementValue")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void AnnotationUsageMarker.visitAnnotationElementValue(Clazz, Annotation, AnnotationElementValue)"
  })
  void testVisitAnnotationElementValue_thenFirstElementEnumConstantElementValue() {
    // Arrange
    AnnotationUsageMarker annotationUsageMarker = new AnnotationUsageMarker(new ClassUsageMarker());
    LibraryClass clazz = new LibraryClass();
    Annotation annotation = new Annotation();
    AnnotationElementValue annotationElementValue =
        new AnnotationElementValue(
            1, new Annotation(1, 1, new ElementValue[] {new EnumConstantElementValue(1, 1, 1)}));

    annotationElementValue.referencedMethod = null;

    // Act
    annotationUsageMarker.visitAnnotationElementValue(clazz, annotation, annotationElementValue);

    // Assert
    ElementValue[] elementValueArray = annotationElementValue.annotationValue.elementValues;
    assertTrue(elementValueArray[0] instanceof EnumConstantElementValue);
    assertEquals(1, elementValueArray.length);
  }

  /**
   * Test {@link AnnotationUsageMarker#visitArrayElementValue(Clazz, Annotation,
   * ArrayElementValue)}.
   *
   * <p>Method under test: {@link AnnotationUsageMarker#visitArrayElementValue(Clazz, Annotation,
   * ArrayElementValue)}
   */
  @Test
  @DisplayName("Test visitArrayElementValue(Clazz, Annotation, ArrayElementValue)")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void AnnotationUsageMarker.visitArrayElementValue(Clazz, Annotation, ArrayElementValue)"
  })
  void testVisitArrayElementValue() {
    // Arrange
    AnnotationUsageMarker annotationUsageMarker = new AnnotationUsageMarker(new ClassUsageMarker());
    LibraryClass clazz = new LibraryClass();
    Annotation annotation = new Annotation();
    ArrayElementValue arrayElementValue =
        new ArrayElementValue(1, 0, new ElementValue[] {new AnnotationElementValue()});

    // Act
    annotationUsageMarker.visitArrayElementValue(clazz, annotation, arrayElementValue);

    // Assert that nothing has changed
    ElementValue[] elementValueArray = arrayElementValue.elementValues;
    assertTrue(elementValueArray[0] instanceof AnnotationElementValue);
    assertEquals(1, elementValueArray.length);
  }

  /**
   * Test {@link AnnotationUsageMarker#visitArrayElementValue(Clazz, Annotation,
   * ArrayElementValue)}.
   *
   * <p>Method under test: {@link AnnotationUsageMarker#visitArrayElementValue(Clazz, Annotation,
   * ArrayElementValue)}
   */
  @Test
  @DisplayName("Test visitArrayElementValue(Clazz, Annotation, ArrayElementValue)")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void AnnotationUsageMarker.visitArrayElementValue(Clazz, Annotation, ArrayElementValue)"
  })
  void testVisitArrayElementValue2() {
    // Arrange
    ShortestUsageMarker usageMarker = new ShortestUsageMarker();
    AnnotationUsageMarker annotationUsageMarker =
        new AnnotationUsageMarker(new ShortestClassUsageMarker(usageMarker, "Just cause"));
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
   * Test {@link AnnotationUsageMarker#visitArrayElementValue(Clazz, Annotation,
   * ArrayElementValue)}.
   *
   * <p>Method under test: {@link AnnotationUsageMarker#visitArrayElementValue(Clazz, Annotation,
   * ArrayElementValue)}
   */
  @Test
  @DisplayName("Test visitArrayElementValue(Clazz, Annotation, ArrayElementValue)")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void AnnotationUsageMarker.visitArrayElementValue(Clazz, Annotation, ArrayElementValue)"
  })
  void testVisitArrayElementValue3() {
    // Arrange
    AnnotationUsageMarker annotationUsageMarker = new AnnotationUsageMarker(new ClassUsageMarker());
    LibraryClass clazz = new LibraryClass();
    Annotation annotation = new Annotation();
    ArrayElementValue arrayElementValue =
        new ArrayElementValue(
            0,
            1,
            new ElementValue[] {
              new AnnotationElementValue(
                  1,
                  new Annotation(
                      1, 1, new ElementValue[] {new AnnotationElementValue(1, new Annotation())}))
            });

    // Act
    annotationUsageMarker.visitArrayElementValue(clazz, annotation, arrayElementValue);

    // Assert
    ElementValue[] elementValueArray = arrayElementValue.elementValues;
    ElementValue elementValue = elementValueArray[0];
    ElementValue[] elementValueArray2 =
        ((AnnotationElementValue) elementValue).annotationValue.elementValues;
    assertTrue(elementValueArray2[0] instanceof AnnotationElementValue);
    assertTrue(elementValue instanceof AnnotationElementValue);
    assertEquals(1, elementValueArray2.length);
    assertEquals(1, elementValueArray.length);
  }

  /**
   * Test {@link AnnotationUsageMarker#visitArrayElementValue(Clazz, Annotation,
   * ArrayElementValue)}.
   *
   * <ul>
   *   <li>Then first element {@link AnnotationElementValue}.
   * </ul>
   *
   * <p>Method under test: {@link AnnotationUsageMarker#visitArrayElementValue(Clazz, Annotation,
   * ArrayElementValue)}
   */
  @Test
  @DisplayName(
      "Test visitArrayElementValue(Clazz, Annotation, ArrayElementValue); then first element AnnotationElementValue")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void AnnotationUsageMarker.visitArrayElementValue(Clazz, Annotation, ArrayElementValue)"
  })
  void testVisitArrayElementValue_thenFirstElementAnnotationElementValue() {
    // Arrange
    AnnotationUsageMarker annotationUsageMarker = new AnnotationUsageMarker(new ClassUsageMarker());
    LibraryClass clazz = new LibraryClass();
    Annotation annotation = new Annotation();
    ArrayElementValue arrayElementValue =
        new ArrayElementValue(
            0, 1, new ElementValue[] {new AnnotationElementValue(1, new Annotation())});

    // Act
    annotationUsageMarker.visitArrayElementValue(clazz, annotation, arrayElementValue);

    // Assert
    ElementValue[] elementValueArray = arrayElementValue.elementValues;
    assertTrue(elementValueArray[0] instanceof AnnotationElementValue);
    assertEquals(1, elementValueArray.length);
  }

  /**
   * Test {@link AnnotationUsageMarker#visitArrayElementValue(Clazz, Annotation,
   * ArrayElementValue)}.
   *
   * <ul>
   *   <li>Then first element {@link ArrayElementValue}.
   * </ul>
   *
   * <p>Method under test: {@link AnnotationUsageMarker#visitArrayElementValue(Clazz, Annotation,
   * ArrayElementValue)}
   */
  @Test
  @DisplayName(
      "Test visitArrayElementValue(Clazz, Annotation, ArrayElementValue); then first element ArrayElementValue")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void AnnotationUsageMarker.visitArrayElementValue(Clazz, Annotation, ArrayElementValue)"
  })
  void testVisitArrayElementValue_thenFirstElementArrayElementValue() {
    // Arrange
    AnnotationUsageMarker annotationUsageMarker = new AnnotationUsageMarker(new ClassUsageMarker());
    LibraryClass clazz = new LibraryClass();
    Annotation annotation = new Annotation();
    ArrayElementValue arrayElementValue =
        new ArrayElementValue(0, 1, new ElementValue[] {new ArrayElementValue()});

    // Act
    annotationUsageMarker.visitArrayElementValue(clazz, annotation, arrayElementValue);

    // Assert
    ElementValue[] elementValueArray = arrayElementValue.elementValues;
    assertTrue(elementValueArray[0] instanceof ArrayElementValue);
    assertEquals(1, elementValueArray.length);
  }

  /**
   * Test {@link AnnotationUsageMarker#visitArrayElementValue(Clazz, Annotation,
   * ArrayElementValue)}.
   *
   * <ul>
   *   <li>Then first element {@link ClassElementValue}.
   * </ul>
   *
   * <p>Method under test: {@link AnnotationUsageMarker#visitArrayElementValue(Clazz, Annotation,
   * ArrayElementValue)}
   */
  @Test
  @DisplayName(
      "Test visitArrayElementValue(Clazz, Annotation, ArrayElementValue); then first element ClassElementValue")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void AnnotationUsageMarker.visitArrayElementValue(Clazz, Annotation, ArrayElementValue)"
  })
  void testVisitArrayElementValue_thenFirstElementClassElementValue() {
    // Arrange
    AnnotationUsageMarker annotationUsageMarker = new AnnotationUsageMarker(new ClassUsageMarker());
    LibraryClass clazz = new LibraryClass();
    Annotation annotation = new Annotation();
    ArrayElementValue arrayElementValue =
        new ArrayElementValue(0, 1, new ElementValue[] {new ClassElementValue(1, 1)});

    // Act
    annotationUsageMarker.visitArrayElementValue(clazz, annotation, arrayElementValue);

    // Assert
    ElementValue[] elementValueArray = arrayElementValue.elementValues;
    assertTrue(elementValueArray[0] instanceof ClassElementValue);
    assertEquals(1, elementValueArray.length);
  }

  /**
   * Test {@link AnnotationUsageMarker#visitArrayElementValue(Clazz, Annotation,
   * ArrayElementValue)}.
   *
   * <ul>
   *   <li>Then first element {@link ConstantElementValue}.
   * </ul>
   *
   * <p>Method under test: {@link AnnotationUsageMarker#visitArrayElementValue(Clazz, Annotation,
   * ArrayElementValue)}
   */
  @Test
  @DisplayName(
      "Test visitArrayElementValue(Clazz, Annotation, ArrayElementValue); then first element ConstantElementValue")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void AnnotationUsageMarker.visitArrayElementValue(Clazz, Annotation, ArrayElementValue)"
  })
  void testVisitArrayElementValue_thenFirstElementConstantElementValue() {
    // Arrange
    AnnotationUsageMarker annotationUsageMarker = new AnnotationUsageMarker(new ClassUsageMarker());
    LibraryClass clazz = new LibraryClass();
    Annotation annotation = new Annotation();
    ArrayElementValue arrayElementValue =
        new ArrayElementValue(0, 1, new ElementValue[] {new ConstantElementValue('\u0001')});

    // Act
    annotationUsageMarker.visitArrayElementValue(clazz, annotation, arrayElementValue);

    // Assert
    ElementValue[] elementValueArray = arrayElementValue.elementValues;
    assertTrue(elementValueArray[0] instanceof ConstantElementValue);
    assertEquals(1, elementValueArray.length);
  }

  /**
   * Test {@link AnnotationUsageMarker#visitArrayElementValue(Clazz, Annotation,
   * ArrayElementValue)}.
   *
   * <ul>
   *   <li>Then first element {@link EnumConstantElementValue}.
   * </ul>
   *
   * <p>Method under test: {@link AnnotationUsageMarker#visitArrayElementValue(Clazz, Annotation,
   * ArrayElementValue)}
   */
  @Test
  @DisplayName(
      "Test visitArrayElementValue(Clazz, Annotation, ArrayElementValue); then first element EnumConstantElementValue")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void AnnotationUsageMarker.visitArrayElementValue(Clazz, Annotation, ArrayElementValue)"
  })
  void testVisitArrayElementValue_thenFirstElementEnumConstantElementValue() {
    // Arrange
    AnnotationUsageMarker annotationUsageMarker = new AnnotationUsageMarker(new ClassUsageMarker());
    LibraryClass clazz = new LibraryClass();
    Annotation annotation = new Annotation();
    ArrayElementValue arrayElementValue =
        new ArrayElementValue(0, 1, new ElementValue[] {new EnumConstantElementValue(1, 1, 1)});

    // Act
    annotationUsageMarker.visitArrayElementValue(clazz, annotation, arrayElementValue);

    // Assert
    ElementValue[] elementValueArray = arrayElementValue.elementValues;
    assertTrue(elementValueArray[0] instanceof EnumConstantElementValue);
    assertEquals(1, elementValueArray.length);
  }

  /**
   * Test {@link AnnotationUsageMarker#visitAnyConstant(Clazz, Constant)}.
   *
   * <p>Method under test: {@link AnnotationUsageMarker#visitAnyConstant(Clazz, Constant)}
   */
  @Test
  @DisplayName("Test visitAnyConstant(Clazz, Constant)")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void AnnotationUsageMarker.visitAnyConstant(Clazz, Constant)"})
  void testVisitAnyConstant() {
    // Arrange
    ShortestUsageMarker usageMarker = new ShortestUsageMarker();
    AnnotationUsageMarker annotationUsageMarker =
        new AnnotationUsageMarker(new ShortestClassUsageMarker(usageMarker, "Just cause"));
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
   *
   * <p>Method under test: {@link AnnotationUsageMarker#visitAnyConstant(Clazz, Constant)}
   */
  @Test
  @DisplayName("Test visitAnyConstant(Clazz, Constant)")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void AnnotationUsageMarker.visitAnyConstant(Clazz, Constant)"})
  void testVisitAnyConstant2() {
    // Arrange
    ShortestUsageMarker usageMarker = new ShortestUsageMarker();
    AnnotationUsageMarker annotationUsageMarker =
        new AnnotationUsageMarker(new ShortestClassUsageMarker(usageMarker, "Just cause"));
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
   *
   * <p>Method under test: {@link AnnotationUsageMarker#visitAnyConstant(Clazz, Constant)}
   */
  @Test
  @DisplayName("Test visitAnyConstant(Clazz, Constant)")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void AnnotationUsageMarker.visitAnyConstant(Clazz, Constant)"})
  void testVisitAnyConstant3() {
    // Arrange
    ShortestUsageMarker usageMarker = new ShortestUsageMarker();
    AnnotationUsageMarker annotationUsageMarker =
        new AnnotationUsageMarker(new ShortestClassUsageMarker(usageMarker, "Just cause"));
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
   *
   * <p>Method under test: {@link AnnotationUsageMarker#visitAnyConstant(Clazz, Constant)}
   */
  @Test
  @DisplayName("Test visitAnyConstant(Clazz, Constant)")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void AnnotationUsageMarker.visitAnyConstant(Clazz, Constant)"})
  void testVisitAnyConstant4() {
    // Arrange
    AnnotationUsageMarker annotationUsageMarker = new AnnotationUsageMarker(new ClassUsageMarker());
    LibraryClass clazz = new LibraryClass();
    ClassConstant constant =
        new ClassConstant(1, new LibraryClass(5, "This Class Name", "Super Class Name"));

    // Act
    annotationUsageMarker.visitAnyConstant(clazz, constant);

    // Assert
    assertTrue(constant.referencedClass instanceof LibraryClass);
  }

  /**
   * Test {@link AnnotationUsageMarker#visitAnyConstant(Clazz, Constant)}.
   *
   * <p>Method under test: {@link AnnotationUsageMarker#visitAnyConstant(Clazz, Constant)}
   */
  @Test
  @DisplayName("Test visitAnyConstant(Clazz, Constant)")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void AnnotationUsageMarker.visitAnyConstant(Clazz, Constant)"})
  void testVisitAnyConstant5() {
    // Arrange
    AnnotationUsageMarker annotationUsageMarker = new AnnotationUsageMarker(new ClassUsageMarker());
    LibraryClass clazz = new LibraryClass();
    LibraryClass referencedClass = new LibraryClass(5, "This Class Name", "Super Class Name");

    FieldrefConstant constant =
        new FieldrefConstant(1, 1, referencedClass, new LibraryField(5, "Name", "Descriptor"));

    // Act
    annotationUsageMarker.visitAnyConstant(clazz, constant);

    // Assert
    assertTrue(constant.referencedClass instanceof LibraryClass);
  }

  /**
   * Test {@link AnnotationUsageMarker#visitAnyConstant(Clazz, Constant)}.
   *
   * <p>Method under test: {@link AnnotationUsageMarker#visitAnyConstant(Clazz, Constant)}
   */
  @Test
  @DisplayName("Test visitAnyConstant(Clazz, Constant)")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void AnnotationUsageMarker.visitAnyConstant(Clazz, Constant)"})
  void testVisitAnyConstant6() {
    // Arrange
    ShortestUsageMarker usageMarker = new ShortestUsageMarker();
    AnnotationUsageMarker annotationUsageMarker =
        new AnnotationUsageMarker(new ShortestClassUsageMarker(usageMarker, "Just cause"));
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
   *
   * <p>Method under test: {@link AnnotationUsageMarker#visitAnyConstant(Clazz, Constant)}
   */
  @Test
  @DisplayName("Test visitAnyConstant(Clazz, Constant)")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void AnnotationUsageMarker.visitAnyConstant(Clazz, Constant)"})
  void testVisitAnyConstant7() {
    // Arrange
    ShortestUsageMarker usageMarker = new ShortestUsageMarker();
    AnnotationUsageMarker annotationUsageMarker =
        new AnnotationUsageMarker(new ShortestClassUsageMarker(usageMarker, "Just cause"));
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
   *
   * <p>Method under test: {@link AnnotationUsageMarker#visitClassConstant(Clazz, ClassConstant)}
   */
  @Test
  @DisplayName("Test visitClassConstant(Clazz, ClassConstant)")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void AnnotationUsageMarker.visitClassConstant(Clazz, ClassConstant)"})
  void testVisitClassConstant() {
    // Arrange
    ShortestUsageMarker usageMarker = new ShortestUsageMarker();
    AnnotationUsageMarker annotationUsageMarker =
        new AnnotationUsageMarker(new ShortestClassUsageMarker(usageMarker, "Just cause"));
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
   *
   * <ul>
   *   <li>Then first element {@link DoubleConstant}.
   * </ul>
   *
   * <p>Method under test: {@link AnnotationUsageMarker#visitClassConstant(Clazz, ClassConstant)}
   */
  @Test
  @DisplayName("Test visitClassConstant(Clazz, ClassConstant); then first element DoubleConstant")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void AnnotationUsageMarker.visitClassConstant(Clazz, ClassConstant)"})
  void testVisitClassConstant_thenFirstElementDoubleConstant() {
    // Arrange
    ShortestUsageMarker usageMarker = new ShortestUsageMarker();
    AnnotationUsageMarker annotationUsageMarker =
        new AnnotationUsageMarker(new ShortestClassUsageMarker(usageMarker, "Just cause"));
    ProgramClass clazz =
        new ProgramClass(5, 3, new Constant[] {new DoubleConstant(10.0d)}, 5, 5, 5);

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
   *
   * <ul>
   *   <li>Then first element {@link DynamicConstant}.
   * </ul>
   *
   * <p>Method under test: {@link AnnotationUsageMarker#visitClassConstant(Clazz, ClassConstant)}
   */
  @Test
  @DisplayName("Test visitClassConstant(Clazz, ClassConstant); then first element DynamicConstant")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void AnnotationUsageMarker.visitClassConstant(Clazz, ClassConstant)"})
  void testVisitClassConstant_thenFirstElementDynamicConstant() {
    // Arrange
    ShortestUsageMarker usageMarker = new ShortestUsageMarker();
    AnnotationUsageMarker annotationUsageMarker =
        new AnnotationUsageMarker(new ShortestClassUsageMarker(usageMarker, "Just cause"));
    ProgramClass clazz = new ProgramClass(5, 3, new Constant[] {new DynamicConstant()}, 5, 5, 5);

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
   *
   * <ul>
   *   <li>Then first element {@link FieldrefConstant}.
   * </ul>
   *
   * <p>Method under test: {@link AnnotationUsageMarker#visitClassConstant(Clazz, ClassConstant)}
   */
  @Test
  @DisplayName("Test visitClassConstant(Clazz, ClassConstant); then first element FieldrefConstant")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void AnnotationUsageMarker.visitClassConstant(Clazz, ClassConstant)"})
  void testVisitClassConstant_thenFirstElementFieldrefConstant() {
    // Arrange
    ShortestUsageMarker usageMarker = new ShortestUsageMarker();
    AnnotationUsageMarker annotationUsageMarker =
        new AnnotationUsageMarker(new ShortestClassUsageMarker(usageMarker, "Just cause"));
    ProgramClass clazz = new ProgramClass(5, 3, new Constant[] {new FieldrefConstant()}, 5, 5, 5);

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
   *
   * <ul>
   *   <li>Then first element {@link FloatConstant}.
   * </ul>
   *
   * <p>Method under test: {@link AnnotationUsageMarker#visitClassConstant(Clazz, ClassConstant)}
   */
  @Test
  @DisplayName("Test visitClassConstant(Clazz, ClassConstant); then first element FloatConstant")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void AnnotationUsageMarker.visitClassConstant(Clazz, ClassConstant)"})
  void testVisitClassConstant_thenFirstElementFloatConstant() {
    // Arrange
    ShortestUsageMarker usageMarker = new ShortestUsageMarker();
    AnnotationUsageMarker annotationUsageMarker =
        new AnnotationUsageMarker(new ShortestClassUsageMarker(usageMarker, "Just cause"));
    ProgramClass clazz = new ProgramClass(5, 3, new Constant[] {new FloatConstant(10.0f)}, 5, 5, 5);

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
   * Test {@link AnnotationUsageMarker#visitClassConstant(Clazz, ClassConstant)}.
   *
   * <ul>
   *   <li>Then first element {@link IntegerConstant}.
   * </ul>
   *
   * <p>Method under test: {@link AnnotationUsageMarker#visitClassConstant(Clazz, ClassConstant)}
   */
  @Test
  @DisplayName("Test visitClassConstant(Clazz, ClassConstant); then first element IntegerConstant")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void AnnotationUsageMarker.visitClassConstant(Clazz, ClassConstant)"})
  void testVisitClassConstant_thenFirstElementIntegerConstant() {
    // Arrange
    ShortestUsageMarker usageMarker = new ShortestUsageMarker();
    AnnotationUsageMarker annotationUsageMarker =
        new AnnotationUsageMarker(new ShortestClassUsageMarker(usageMarker, "Just cause"));
    ProgramClass clazz = new ProgramClass(5, 3, new Constant[] {new IntegerConstant(42)}, 5, 5, 5);

    ClassConstant classConstant = new ClassConstant();

    // Act
    annotationUsageMarker.visitClassConstant(clazz, classConstant);

    // Assert
    Constant[] constantArray = clazz.constantPool;
    Constant constant = constantArray[0];
    assertTrue(constant instanceof IntegerConstant);
    assertEquals(1, constantArray.length);
    Object processingInfo = constant.getProcessingInfo();
    assertSame(processingInfo, classConstant.getProcessingInfo());
    assertSame(usageMarker.currentUsageMark, processingInfo);
  }

  /**
   * Test {@link AnnotationUsageMarker#visitAnyClass(Clazz)}.
   *
   * <ul>
   *   <li>When {@link LibraryClass#LibraryClass()}.
   *   <li>Then throw {@link UnsupportedOperationException}.
   * </ul>
   *
   * <p>Method under test: {@link AnnotationUsageMarker#visitAnyClass(Clazz)}
   */
  @Test
  @DisplayName(
      "Test visitAnyClass(Clazz); when LibraryClass(); then throw UnsupportedOperationException")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void AnnotationUsageMarker.visitAnyClass(Clazz)"})
  void testVisitAnyClass_whenLibraryClass_thenThrowUnsupportedOperationException() {
    // Arrange
    AnnotationUsageMarker annotationUsageMarker = new AnnotationUsageMarker(new ClassUsageMarker());

    // Act and Assert
    assertThrows(
        UnsupportedOperationException.class,
        () -> annotationUsageMarker.visitAnyClass(new LibraryClass()));
  }

  /**
   * Test {@link AnnotationUsageMarker#visitProgramClass(ProgramClass)}.
   *
   * <p>Method under test: {@link AnnotationUsageMarker#visitProgramClass(ProgramClass)}
   */
  @Test
  @DisplayName("Test visitProgramClass(ProgramClass)")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void AnnotationUsageMarker.visitProgramClass(ProgramClass)"})
  void testVisitProgramClass() {
    // Arrange
    ClassUsageMarker classUsageMarker = mock(ClassUsageMarker.class);
    when(classUsageMarker.isUsed(Mockito.<Processable>any())).thenReturn(true);
    AnnotationUsageMarker annotationUsageMarker = new AnnotationUsageMarker(classUsageMarker);

    // Act
    annotationUsageMarker.visitProgramClass(
        new NamedClass("kotlin/coroutines/jvm/internal/DebugMetadata"));

    // Assert
    verify(classUsageMarker).isUsed(isA(Processable.class));
  }

  /**
   * Test {@link AnnotationUsageMarker#visitProgramClass(ProgramClass)}.
   *
   * <ul>
   *   <li>Given {@code Name}.
   *   <li>When {@link ProgramClass} {@link ProgramClass#getName()} return {@code Name}.
   * </ul>
   *
   * <p>Method under test: {@link AnnotationUsageMarker#visitProgramClass(ProgramClass)}
   */
  @Test
  @DisplayName(
      "Test visitProgramClass(ProgramClass); given 'Name'; when ProgramClass getName() return 'Name'")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
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
   * Test {@link AnnotationUsageMarker#visitLibraryClass(LibraryClass)}.
   *
   * <ul>
   *   <li>Given minus one.
   * </ul>
   *
   * <p>Method under test: {@link AnnotationUsageMarker#visitLibraryClass(LibraryClass)}
   */
  @Test
  @DisplayName("Test visitLibraryClass(LibraryClass); given minus one")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void AnnotationUsageMarker.visitLibraryClass(LibraryClass)"})
  void testVisitLibraryClass_givenMinusOne() {
    // Arrange
    AnnotationUsageMarker annotationUsageMarker = new AnnotationUsageMarker(new ClassUsageMarker());
    LibraryClass libraryClass = mock(LibraryClass.class);
    when(libraryClass.getProcessingFlags()).thenReturn(-1);
    when(libraryClass.getName()).thenReturn("kotlin/Metadata");

    // Act
    annotationUsageMarker.visitLibraryClass(libraryClass);

    // Assert
    verify(libraryClass).getName();
    verify(libraryClass).getProcessingFlags();
  }

  /**
   * Test {@link AnnotationUsageMarker#visitLibraryClass(LibraryClass)}.
   *
   * <ul>
   *   <li>Given {@code Name}.
   *   <li>When {@link LibraryClass} {@link LibraryClass#getName()} return {@code Name}.
   * </ul>
   *
   * <p>Method under test: {@link AnnotationUsageMarker#visitLibraryClass(LibraryClass)}
   */
  @Test
  @DisplayName(
      "Test visitLibraryClass(LibraryClass); given 'Name'; when LibraryClass getName() return 'Name'")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
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
   *
   * <ul>
   *   <li>Given {@link ClassUsageMarker} {@link ClassUsageMarker#isUsed(Processable)} return {@code
   *       true}.
   *   <li>Then calls {@link ClassUsageMarker#isUsed(Processable)}.
   * </ul>
   *
   * <p>Method under test: {@link AnnotationUsageMarker#visitProgramMethod(ProgramClass,
   * ProgramMethod)}
   */
  @Test
  @DisplayName(
      "Test visitProgramMethod(ProgramClass, ProgramMethod); given ClassUsageMarker isUsed(Processable) return 'true'; then calls isUsed(Processable)")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
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
