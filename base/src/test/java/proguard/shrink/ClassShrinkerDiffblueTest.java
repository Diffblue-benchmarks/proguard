package proguard.shrink;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import proguard.classfile.Clazz;
import proguard.classfile.LibraryClass;
import proguard.classfile.LibraryMethod;
import proguard.classfile.Method;
import proguard.classfile.ProgramClass;
import proguard.classfile.ProgramField;
import proguard.classfile.ProgramMember;
import proguard.classfile.attribute.Attribute;
import proguard.classfile.attribute.BootstrapMethodInfo;
import proguard.classfile.attribute.BootstrapMethodsAttribute;
import proguard.classfile.attribute.CodeAttribute;
import proguard.classfile.attribute.EnclosingMethodAttribute;
import proguard.classfile.attribute.InnerClassesAttribute;
import proguard.classfile.attribute.InnerClassesInfo;
import proguard.classfile.attribute.RecordAttribute;
import proguard.classfile.attribute.RecordComponentInfo;
import proguard.classfile.attribute.annotation.Annotation;
import proguard.classfile.attribute.annotation.AnnotationElementValue;
import proguard.classfile.attribute.annotation.AnnotationsAttribute;
import proguard.classfile.attribute.annotation.ArrayElementValue;
import proguard.classfile.attribute.annotation.ElementValue;
import proguard.classfile.attribute.annotation.ParameterAnnotationsAttribute;
import proguard.classfile.attribute.annotation.RuntimeInvisibleAnnotationsAttribute;
import proguard.classfile.attribute.annotation.RuntimeInvisibleParameterAnnotationsAttribute;
import proguard.classfile.attribute.annotation.visitor.AnnotationVisitor;
import proguard.classfile.attribute.visitor.AttributeVisitor;
import proguard.classfile.constant.ClassConstant;
import proguard.classfile.constant.Constant;

class ClassShrinkerDiffblueTest {
  /**
   * Method under test: {@link ClassShrinker#visitAnyClass(Clazz)}
   */
  @Test
  void testVisitAnyClass() {
    // Arrange
    ClassShrinker classShrinker = new ClassShrinker(new SimpleUsageMarker());

    // Act and Assert
    assertThrows(UnsupportedOperationException.class, () -> classShrinker.visitAnyClass(new LibraryClass()));
  }

  /**
   * Method under test: {@link ClassShrinker#visitProgramClass(ProgramClass)}
   */
  @Test
  void testVisitProgramClass() {
    // Arrange
    ClassShrinker classShrinker = new ClassShrinker(new SimpleUsageMarker());
    ProgramClass programClass = new ProgramClass(1, 1, new Constant[]{new ClassConstant()}, 1, 1, 1);

    // Act
    classShrinker.visitProgramClass(programClass);

    // Assert
    assertEquals(0, programClass.subClasses.length);
    assertEquals(0, programClass.subClassCount);
  }

  /**
   * Method under test: {@link ClassShrinker#visitProgramClass(ProgramClass)}
   */
  @Test
  void testVisitProgramClass2() {
    // Arrange
    ClassShrinker classShrinker = new ClassShrinker(new SimpleUsageMarker());

    ProgramClass programClass = new ProgramClass(1, 1, new Constant[]{new ClassConstant()}, 1, 1, 1);
    programClass.addSubClass(new LibraryClass());

    // Act
    classShrinker.visitProgramClass(programClass);

    // Assert
    Clazz[] clazzArray = programClass.subClasses;
    assertNull(clazzArray[0]);
    assertEquals(0, programClass.subClassCount);
    assertEquals(1, clazzArray.length);
  }

  /**
   * Method under test: {@link ClassShrinker#visitProgramClass(ProgramClass)}
   */
  @Test
  void testVisitProgramClass3() {
    // Arrange
    ClassShrinker classShrinker = new ClassShrinker(new ShortestUsageMarker());

    ProgramClass programClass = new ProgramClass(1, 1, new Constant[]{new ClassConstant()}, 1, 1, 1);
    programClass.addSubClass(new LibraryClass());

    // Act
    classShrinker.visitProgramClass(programClass);

    // Assert
    Clazz[] clazzArray = programClass.subClasses;
    assertNull(clazzArray[0]);
    assertEquals(0, programClass.subClassCount);
    assertEquals(1, clazzArray.length);
  }

  /**
   * Method under test: {@link ClassShrinker#visitLibraryClass(LibraryClass)}
   */
  @Test
  void testVisitLibraryClass() {
    // Arrange
    ClassShrinker classShrinker = new ClassShrinker(new SimpleUsageMarker());
    LibraryClass libraryClass = new LibraryClass();

    // Act
    classShrinker.visitLibraryClass(libraryClass);

    // Assert
    assertEquals(0, libraryClass.subClasses.length);
    assertEquals(0, libraryClass.subClassCount);
  }

  /**
   * Method under test: {@link ClassShrinker#visitLibraryClass(LibraryClass)}
   */
  @Test
  void testVisitLibraryClass2() {
    // Arrange
    ClassShrinker classShrinker = new ClassShrinker(new SimpleUsageMarker());

    LibraryClass libraryClass = new LibraryClass();
    libraryClass.addSubClass(new LibraryClass());

    // Act
    classShrinker.visitLibraryClass(libraryClass);

    // Assert
    Clazz[] clazzArray = libraryClass.subClasses;
    assertNull(clazzArray[0]);
    assertEquals(0, libraryClass.subClassCount);
    assertEquals(1, clazzArray.length);
  }

  /**
   * Method under test: {@link ClassShrinker#visitLibraryClass(LibraryClass)}
   */
  @Test
  void testVisitLibraryClass3() {
    // Arrange
    ClassShrinker classShrinker = new ClassShrinker(new ShortestUsageMarker());

    LibraryClass libraryClass = new LibraryClass();
    libraryClass.addSubClass(new LibraryClass());

    // Act
    classShrinker.visitLibraryClass(libraryClass);

    // Assert
    Clazz[] clazzArray = libraryClass.subClasses;
    assertNull(clazzArray[0]);
    assertEquals(0, libraryClass.subClassCount);
    assertEquals(1, clazzArray.length);
  }

  /**
   * Method under test:
   * {@link ClassShrinker#visitProgramMember(ProgramClass, ProgramMember)}
   */
  @Test
  void testVisitProgramMember() {
    // Arrange
    ClassShrinker classShrinker = new ClassShrinker(new SimpleUsageMarker());
    ProgramClass programClass = new ProgramClass();
    ProgramField programMember = new ProgramField(1, 1, 1, 1, new Attribute[]{new BootstrapMethodsAttribute()},
        new LibraryClass());

    // Act
    classShrinker.visitProgramMember(programClass, programMember);

    // Assert
    Attribute[] attributeArray = programMember.attributes;
    assertNull(attributeArray[0]);
    assertEquals(0, programMember.u2attributesCount);
    assertEquals(1, attributeArray.length);
  }

  /**
   * Method under test:
   * {@link ClassShrinker#visitBootstrapMethodsAttribute(Clazz, BootstrapMethodsAttribute)}
   */
  @Test
  void testVisitBootstrapMethodsAttribute() {
    // Arrange
    ClassShrinker classShrinker = new ClassShrinker(new SimpleUsageMarker());
    LibraryClass clazz = new LibraryClass();
    BootstrapMethodsAttribute bootstrapMethodsAttribute = new BootstrapMethodsAttribute(1, 1,
        new BootstrapMethodInfo[]{new BootstrapMethodInfo()});

    // Act
    classShrinker.visitBootstrapMethodsAttribute(clazz, bootstrapMethodsAttribute);

    // Assert
    BootstrapMethodInfo[] bootstrapMethodInfoArray = bootstrapMethodsAttribute.bootstrapMethods;
    assertNull(bootstrapMethodInfoArray[0]);
    assertEquals(0, bootstrapMethodsAttribute.u2bootstrapMethodsCount);
    assertEquals(1, bootstrapMethodInfoArray.length);
  }

  /**
   * Method under test:
   * {@link ClassShrinker#visitBootstrapMethodsAttribute(Clazz, BootstrapMethodsAttribute)}
   */
  @Test
  void testVisitBootstrapMethodsAttribute2() {
    // Arrange
    ClassShrinker classShrinker = new ClassShrinker(new SimpleUsageMarker());
    ProgramClass clazz = new ProgramClass();
    BootstrapMethodsAttribute bootstrapMethodsAttribute = new BootstrapMethodsAttribute(1, 1,
        new BootstrapMethodInfo[]{new BootstrapMethodInfo()});

    // Act
    classShrinker.visitBootstrapMethodsAttribute(clazz, bootstrapMethodsAttribute);

    // Assert
    BootstrapMethodInfo[] bootstrapMethodInfoArray = bootstrapMethodsAttribute.bootstrapMethods;
    assertNull(bootstrapMethodInfoArray[0]);
    assertEquals(0, bootstrapMethodsAttribute.u2bootstrapMethodsCount);
    assertEquals(1, bootstrapMethodInfoArray.length);
  }

  /**
   * Method under test:
   * {@link ClassShrinker#visitRecordAttribute(Clazz, RecordAttribute)}
   */
  @Test
  void testVisitRecordAttribute() {
    // Arrange
    ClassShrinker classShrinker = new ClassShrinker(new SimpleUsageMarker());
    LibraryClass clazz = new LibraryClass();
    RecordAttribute recordAttribute = new RecordAttribute(1, 1, new RecordComponentInfo[]{new RecordComponentInfo()});

    // Act
    classShrinker.visitRecordAttribute(clazz, recordAttribute);

    // Assert
    RecordComponentInfo[] recordComponentInfoArray = recordAttribute.components;
    assertNull(recordComponentInfoArray[0]);
    assertEquals(0, recordAttribute.u2componentsCount);
    assertEquals(1, recordComponentInfoArray.length);
  }

  /**
   * Method under test:
   * {@link ClassShrinker#visitInnerClassesAttribute(Clazz, InnerClassesAttribute)}
   */
  @Test
  void testVisitInnerClassesAttribute() {
    // Arrange
    ClassShrinker classShrinker = new ClassShrinker(new SimpleUsageMarker());
    LibraryClass clazz = new LibraryClass();
    InnerClassesAttribute innerClassesAttribute = new InnerClassesAttribute(1, 1,
        new InnerClassesInfo[]{new InnerClassesInfo(1, 1, 1, 1)});

    // Act
    classShrinker.visitInnerClassesAttribute(clazz, innerClassesAttribute);

    // Assert
    InnerClassesInfo[] innerClassesInfoArray = innerClassesAttribute.classes;
    assertNull(innerClassesInfoArray[0]);
    assertEquals(0, innerClassesAttribute.u2classesCount);
    assertEquals(1, innerClassesInfoArray.length);
  }

  /**
   * Method under test:
   * {@link ClassShrinker#visitEnclosingMethodAttribute(Clazz, EnclosingMethodAttribute)}
   */
  @Test
  void testVisitEnclosingMethodAttribute() {
    // Arrange
    ClassShrinker classShrinker = new ClassShrinker(new SimpleUsageMarker());
    LibraryClass clazz = new LibraryClass();
    EnclosingMethodAttribute enclosingMethodAttribute = new EnclosingMethodAttribute(1, 1, 1);

    // Act
    classShrinker.visitEnclosingMethodAttribute(clazz, enclosingMethodAttribute);

    // Assert that nothing has changed
    assertEquals(1, enclosingMethodAttribute.u2nameAndTypeIndex);
  }

  /**
   * Method under test:
   * {@link ClassShrinker#visitEnclosingMethodAttribute(Clazz, EnclosingMethodAttribute)}
   */
  @Test
  void testVisitEnclosingMethodAttribute2() {
    // Arrange
    ClassShrinker classShrinker = new ClassShrinker(new SimpleUsageMarker());
    LibraryClass clazz = new LibraryClass();
    EnclosingMethodAttribute enclosingMethodAttribute = new EnclosingMethodAttribute(1, 1, 1);

    enclosingMethodAttribute.referencedMethod = new LibraryMethod(1, "Name", "Descriptor");

    // Act
    classShrinker.visitEnclosingMethodAttribute(clazz, enclosingMethodAttribute);

    // Assert
    assertNull(enclosingMethodAttribute.referencedMethod);
    assertEquals(0, enclosingMethodAttribute.u2nameAndTypeIndex);
  }

  /**
   * Method under test:
   * {@link ClassShrinker#visitEnclosingMethodAttribute(Clazz, EnclosingMethodAttribute)}
   */
  @Test
  void testVisitEnclosingMethodAttribute3() {
    // Arrange
    ClassShrinker classShrinker = new ClassShrinker(new ShortestUsageMarker());
    LibraryClass clazz = new LibraryClass();
    EnclosingMethodAttribute enclosingMethodAttribute = new EnclosingMethodAttribute(1, 1, 1);

    enclosingMethodAttribute.referencedMethod = new LibraryMethod(1, "Name", "Descriptor");

    // Act
    classShrinker.visitEnclosingMethodAttribute(clazz, enclosingMethodAttribute);

    // Assert
    assertNull(enclosingMethodAttribute.referencedMethod);
    assertEquals(0, enclosingMethodAttribute.u2nameAndTypeIndex);
  }

  /**
   * Method under test:
   * {@link ClassShrinker#visitCodeAttribute(Clazz, Method, CodeAttribute)}
   */
  @Test
  void testVisitCodeAttribute() {
    // Arrange
    ClassShrinker classShrinker = new ClassShrinker(new SimpleUsageMarker());
    LibraryClass clazz = new LibraryClass();
    LibraryMethod method = new LibraryMethod(1, "Name", "Descriptor");

    CodeAttribute codeAttribute = mock(CodeAttribute.class);
    doNothing().when(codeAttribute)
        .attributesAccept(Mockito.<Clazz>any(), Mockito.<Method>any(), Mockito.<AttributeVisitor>any());

    // Act
    classShrinker.visitCodeAttribute(clazz, method, codeAttribute);

    // Assert
    verify(codeAttribute).attributesAccept(isA(Clazz.class), isA(Method.class), isA(AttributeVisitor.class));
  }

  /**
   * Method under test:
   * {@link ClassShrinker#visitCodeAttribute(Clazz, Method, CodeAttribute)}
   */
  @Test
  void testVisitCodeAttribute2() {
    // Arrange
    ClassShrinker classShrinker = new ClassShrinker(new SimpleUsageMarker());
    LibraryClass clazz = new LibraryClass();
    LibraryMethod method = new LibraryMethod(1, "Name", "Descriptor");

    CodeAttribute codeAttribute = mock(CodeAttribute.class);
    doThrow(new UnsupportedOperationException("foo")).when(codeAttribute)
        .attributesAccept(Mockito.<Clazz>any(), Mockito.<Method>any(), Mockito.<AttributeVisitor>any());

    // Act and Assert
    assertThrows(UnsupportedOperationException.class,
        () -> classShrinker.visitCodeAttribute(clazz, method, codeAttribute));
    verify(codeAttribute).attributesAccept(isA(Clazz.class), isA(Method.class), isA(AttributeVisitor.class));
  }

  /**
   * Method under test:
   * {@link ClassShrinker#visitAnyAnnotationsAttribute(Clazz, AnnotationsAttribute)}
   */
  @Test
  void testVisitAnyAnnotationsAttribute() {
    // Arrange
    ClassShrinker classShrinker = new ClassShrinker(new SimpleUsageMarker());
    LibraryClass clazz = new LibraryClass();
    RuntimeInvisibleAnnotationsAttribute annotationsAttribute = new RuntimeInvisibleAnnotationsAttribute(1, 1,
        new Annotation[]{new Annotation()});

    // Act
    classShrinker.visitAnyAnnotationsAttribute(clazz, annotationsAttribute);

    // Assert
    Annotation[] annotationArray = annotationsAttribute.annotations;
    assertNull(annotationArray[0]);
    assertEquals(0, annotationsAttribute.u2annotationsCount);
    assertEquals(1, annotationArray.length);
  }

  /**
   * Method under test:
   * {@link ClassShrinker#visitAnyParameterAnnotationsAttribute(Clazz, Method, ParameterAnnotationsAttribute)}
   */
  @Test
  void testVisitAnyParameterAnnotationsAttribute() {
    // Arrange
    ClassShrinker classShrinker = new ClassShrinker(new SimpleUsageMarker());
    LibraryClass clazz = new LibraryClass();
    LibraryMethod method = new LibraryMethod(1, "Name", "Descriptor");

    RuntimeInvisibleParameterAnnotationsAttribute parameterAnnotationsAttribute = mock(
        RuntimeInvisibleParameterAnnotationsAttribute.class);
    doNothing().when(parameterAnnotationsAttribute)
        .annotationsAccept(Mockito.<Clazz>any(), Mockito.<Method>any(), Mockito.<AnnotationVisitor>any());

    // Act
    classShrinker.visitAnyParameterAnnotationsAttribute(clazz, method, parameterAnnotationsAttribute);

    // Assert
    verify(parameterAnnotationsAttribute).annotationsAccept(isA(Clazz.class), isA(Method.class),
        isA(AnnotationVisitor.class));
  }

  /**
   * Method under test:
   * {@link ClassShrinker#visitAnyParameterAnnotationsAttribute(Clazz, Method, ParameterAnnotationsAttribute)}
   */
  @Test
  void testVisitAnyParameterAnnotationsAttribute2() {
    // Arrange
    ClassShrinker classShrinker = new ClassShrinker(new SimpleUsageMarker());
    LibraryClass clazz = new LibraryClass();
    LibraryMethod method = new LibraryMethod(1, "Name", "Descriptor");

    RuntimeInvisibleParameterAnnotationsAttribute parameterAnnotationsAttribute = mock(
        RuntimeInvisibleParameterAnnotationsAttribute.class);
    doThrow(new UnsupportedOperationException("foo")).when(parameterAnnotationsAttribute)
        .annotationsAccept(Mockito.<Clazz>any(), Mockito.<Method>any(), Mockito.<AnnotationVisitor>any());

    // Act and Assert
    assertThrows(UnsupportedOperationException.class,
        () -> classShrinker.visitAnyParameterAnnotationsAttribute(clazz, method, parameterAnnotationsAttribute));
    verify(parameterAnnotationsAttribute).annotationsAccept(isA(Clazz.class), isA(Method.class),
        isA(AnnotationVisitor.class));
  }

  /**
   * Method under test:
   * {@link ClassShrinker#visitRecordComponentInfo(Clazz, RecordComponentInfo)}
   */
  @Test
  void testVisitRecordComponentInfo() {
    // Arrange
    ClassShrinker classShrinker = new ClassShrinker(new SimpleUsageMarker());
    LibraryClass clazz = new LibraryClass();
    RecordComponentInfo recordComponentInfo = new RecordComponentInfo(1, 1, 1,
        new Attribute[]{new BootstrapMethodsAttribute()});

    // Act
    classShrinker.visitRecordComponentInfo(clazz, recordComponentInfo);

    // Assert
    Attribute[] attributeArray = recordComponentInfo.attributes;
    assertNull(attributeArray[0]);
    assertEquals(0, recordComponentInfo.u2attributesCount);
    assertEquals(1, attributeArray.length);
  }

  /**
   * Method under test: {@link ClassShrinker#visitAnnotation(Clazz, Annotation)}
   */
  @Test
  void testVisitAnnotation() {
    // Arrange
    ClassShrinker classShrinker = new ClassShrinker(new SimpleUsageMarker());
    LibraryClass clazz = new LibraryClass();
    Annotation annotation = new Annotation(1, 1, new ElementValue[]{new AnnotationElementValue()});

    // Act
    classShrinker.visitAnnotation(clazz, annotation);

    // Assert
    ElementValue[] elementValueArray = annotation.elementValues;
    assertNull(elementValueArray[0]);
    assertEquals(0, annotation.u2elementValuesCount);
    assertEquals(1, elementValueArray.length);
  }

  /**
   * Method under test:
   * {@link ClassShrinker#visitAnnotationElementValue(Clazz, Annotation, AnnotationElementValue)}
   */
  @Test
  void testVisitAnnotationElementValue() {
    // Arrange
    ClassShrinker classShrinker = new ClassShrinker(new SimpleUsageMarker());
    LibraryClass clazz = new LibraryClass();
    Annotation annotation = new Annotation();
    Annotation annotationValue = new Annotation(1, 1, new ElementValue[]{new AnnotationElementValue()});

    AnnotationElementValue annotationElementValue = new AnnotationElementValue(1, annotationValue);

    // Act
    classShrinker.visitAnnotationElementValue(clazz, annotation, annotationElementValue);

    // Assert
    Annotation annotation2 = annotationElementValue.annotationValue;
    ElementValue[] elementValueArray = annotation2.elementValues;
    assertNull(elementValueArray[0]);
    assertEquals(0, annotation2.u2elementValuesCount);
    assertEquals(1, elementValueArray.length);
    assertSame(annotationValue.elementValues, elementValueArray);
  }

  /**
   * Method under test:
   * {@link ClassShrinker#visitArrayElementValue(Clazz, Annotation, ArrayElementValue)}
   */
  @Test
  void testVisitArrayElementValue() {
    // Arrange
    ClassShrinker classShrinker = new ClassShrinker(new SimpleUsageMarker());
    LibraryClass clazz = new LibraryClass();
    Annotation annotation = new Annotation();
    ArrayElementValue arrayElementValue = new ArrayElementValue(1, 1, new ElementValue[]{new AnnotationElementValue()});

    // Act
    classShrinker.visitArrayElementValue(clazz, annotation, arrayElementValue);

    // Assert
    ElementValue[] elementValueArray = arrayElementValue.elementValues;
    assertNull(elementValueArray[0]);
    assertEquals(0, arrayElementValue.u2elementValuesCount);
    assertEquals(1, elementValueArray.length);
  }
}
