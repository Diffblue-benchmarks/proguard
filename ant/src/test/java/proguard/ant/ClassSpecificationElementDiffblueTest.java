package proguard.ant;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.anyBoolean;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import java.util.ArrayList;
import java.util.List;
import org.apache.tools.ant.BuildException;
import org.apache.tools.ant.Location;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import proguard.ClassSpecification;
import proguard.MemberSpecification;

class ClassSpecificationElementDiffblueTest {
  /**
   * Method under test: {@link ClassSpecificationElement#appendTo(List)}
   */
  @Test
  void testAppendTo() {
    // Arrange
    ClassSpecificationElement classSpecificationElement = new ClassSpecificationElement();
    ArrayList<Object> classSpecifications = new ArrayList<>();

    // Act
    classSpecificationElement.appendTo(classSpecifications);

    // Assert
    assertEquals(1, classSpecifications.size());
    Object getResult = classSpecifications.get(0);
    assertTrue(getResult instanceof ClassSpecification);
    assertNull(((ClassSpecification) getResult).annotationType);
    assertNull(((ClassSpecification) getResult).className);
    assertNull(((ClassSpecification) getResult).comments);
    assertNull(((ClassSpecification) getResult).extendsAnnotationType);
    assertNull(((ClassSpecification) getResult).extendsClassName);
    assertNull(((ClassSpecification) getResult).memberComments);
    assertNull(((ClassSpecification) getResult).attributeNames);
    assertNull(((ClassSpecification) getResult).fieldSpecifications);
    assertNull(((ClassSpecification) getResult).methodSpecifications);
    assertEquals(0, ((ClassSpecification) getResult).requiredSetAccessFlags);
    assertEquals(0, ((ClassSpecification) getResult).requiredUnsetAccessFlags);
  }

  /**
   * Method under test: {@link ClassSpecificationElement#appendTo(List)}
   */
  @Test
  void testAppendTo2() {
    // Arrange
    ClassSpecificationElement classSpecificationElement = new ClassSpecificationElement();
    classSpecificationElement.addConfiguredField(new MemberSpecificationElement());
    ArrayList<Object> classSpecifications = new ArrayList<>();

    // Act
    classSpecificationElement.appendTo(classSpecifications);

    // Assert
    assertEquals(1, classSpecifications.size());
    Object getResult = classSpecifications.get(0);
    assertTrue(getResult instanceof ClassSpecification);
    assertNull(((ClassSpecification) getResult).annotationType);
    assertNull(((ClassSpecification) getResult).className);
    assertNull(((ClassSpecification) getResult).comments);
    assertNull(((ClassSpecification) getResult).extendsAnnotationType);
    assertNull(((ClassSpecification) getResult).extendsClassName);
    assertNull(((ClassSpecification) getResult).memberComments);
    List<MemberSpecification> memberSpecificationList = ((ClassSpecification) getResult).fieldSpecifications;
    assertEquals(1, memberSpecificationList.size());
    MemberSpecification getResult2 = memberSpecificationList.get(0);
    assertNull(getResult2.annotationType);
    assertNull(getResult2.descriptor);
    assertNull(getResult2.name);
    assertNull(getResult2.attributeNames);
    assertNull(((ClassSpecification) getResult).attributeNames);
    assertNull(((ClassSpecification) getResult).methodSpecifications);
    assertEquals(0, ((ClassSpecification) getResult).requiredSetAccessFlags);
    assertEquals(0, ((ClassSpecification) getResult).requiredUnsetAccessFlags);
    assertEquals(0, getResult2.requiredSetAccessFlags);
    assertEquals(0, getResult2.requiredUnsetAccessFlags);
  }

  /**
   * Method under test: {@link ClassSpecificationElement#appendTo(List)}
   */
  @Test
  void testAppendTo3() {
    // Arrange
    ClassSpecificationElement classSpecificationElement = new ClassSpecificationElement();
    classSpecificationElement.addConfiguredMethod(new MemberSpecificationElement());
    ArrayList<Object> classSpecifications = new ArrayList<>();

    // Act
    classSpecificationElement.appendTo(classSpecifications);

    // Assert
    assertEquals(1, classSpecifications.size());
    Object getResult = classSpecifications.get(0);
    assertTrue(getResult instanceof ClassSpecification);
    assertNull(((ClassSpecification) getResult).annotationType);
    assertNull(((ClassSpecification) getResult).className);
    assertNull(((ClassSpecification) getResult).comments);
    assertNull(((ClassSpecification) getResult).extendsAnnotationType);
    assertNull(((ClassSpecification) getResult).extendsClassName);
    assertNull(((ClassSpecification) getResult).memberComments);
    List<MemberSpecification> memberSpecificationList = ((ClassSpecification) getResult).methodSpecifications;
    assertEquals(1, memberSpecificationList.size());
    MemberSpecification getResult2 = memberSpecificationList.get(0);
    assertNull(getResult2.annotationType);
    assertNull(getResult2.descriptor);
    assertNull(getResult2.name);
    assertNull(getResult2.attributeNames);
    assertNull(((ClassSpecification) getResult).attributeNames);
    assertNull(((ClassSpecification) getResult).fieldSpecifications);
    assertEquals(0, ((ClassSpecification) getResult).requiredSetAccessFlags);
    assertEquals(0, ((ClassSpecification) getResult).requiredUnsetAccessFlags);
    assertEquals(0, getResult2.requiredSetAccessFlags);
    assertEquals(0, getResult2.requiredUnsetAccessFlags);
  }

  /**
   * Method under test: {@link ClassSpecificationElement#appendTo(List)}
   */
  @Test
  void testAppendTo4() {
    // Arrange
    ClassSpecificationElement classSpecificationElement = new ClassSpecificationElement();
    classSpecificationElement.setAccess("Access");
    classSpecificationElement.addConfiguredField(new MemberSpecificationElement());

    // Act and Assert
    assertThrows(BuildException.class, () -> classSpecificationElement.appendTo(new ArrayList<>()));
  }

  /**
   * Method under test: {@link ClassSpecificationElement#appendTo(List)}
   */
  @Test
  void testAppendTo5() {
    // Arrange
    ClassSpecificationElement classSpecificationElement = new ClassSpecificationElement();
    classSpecificationElement.setAnnotation("Annotation");
    classSpecificationElement.addConfiguredField(new MemberSpecificationElement());
    ArrayList<Object> classSpecifications = new ArrayList<>();

    // Act
    classSpecificationElement.appendTo(classSpecifications);

    // Assert
    assertEquals(1, classSpecifications.size());
    Object getResult = classSpecifications.get(0);
    assertTrue(getResult instanceof ClassSpecification);
    assertEquals("LAnnotation;", ((ClassSpecification) getResult).annotationType);
    assertNull(((ClassSpecification) getResult).className);
    assertNull(((ClassSpecification) getResult).comments);
    assertNull(((ClassSpecification) getResult).extendsAnnotationType);
    assertNull(((ClassSpecification) getResult).extendsClassName);
    assertNull(((ClassSpecification) getResult).memberComments);
    List<MemberSpecification> memberSpecificationList = ((ClassSpecification) getResult).fieldSpecifications;
    assertEquals(1, memberSpecificationList.size());
    MemberSpecification getResult2 = memberSpecificationList.get(0);
    assertNull(getResult2.annotationType);
    assertNull(getResult2.descriptor);
    assertNull(getResult2.name);
    assertNull(getResult2.attributeNames);
    assertNull(((ClassSpecification) getResult).attributeNames);
    assertNull(((ClassSpecification) getResult).methodSpecifications);
    assertEquals(0, ((ClassSpecification) getResult).requiredSetAccessFlags);
    assertEquals(0, ((ClassSpecification) getResult).requiredUnsetAccessFlags);
    assertEquals(0, getResult2.requiredSetAccessFlags);
    assertEquals(0, getResult2.requiredUnsetAccessFlags);
  }

  /**
   * Method under test: {@link ClassSpecificationElement#appendTo(List)}
   */
  @Test
  void testAppendTo6() {
    // Arrange
    ClassSpecificationElement classSpecificationElement = new ClassSpecificationElement();
    classSpecificationElement.setType("Type");
    classSpecificationElement.addConfiguredField(new MemberSpecificationElement());

    // Act and Assert
    assertThrows(BuildException.class, () -> classSpecificationElement.appendTo(new ArrayList<>()));
  }

  /**
   * Method under test: {@link ClassSpecificationElement#appendTo(List)}
   */
  @Test
  void testAppendTo7() {
    // Arrange
    ClassSpecificationElement classSpecificationElement = new ClassSpecificationElement();
    classSpecificationElement.setName("Name");
    classSpecificationElement.addConfiguredField(new MemberSpecificationElement());
    ArrayList<Object> classSpecifications = new ArrayList<>();

    // Act
    classSpecificationElement.appendTo(classSpecifications);

    // Assert
    assertEquals(1, classSpecifications.size());
    Object getResult = classSpecifications.get(0);
    assertTrue(getResult instanceof ClassSpecification);
    assertEquals("Name", ((ClassSpecification) getResult).className);
    assertNull(((ClassSpecification) getResult).annotationType);
    assertNull(((ClassSpecification) getResult).comments);
    assertNull(((ClassSpecification) getResult).extendsAnnotationType);
    assertNull(((ClassSpecification) getResult).extendsClassName);
    assertNull(((ClassSpecification) getResult).memberComments);
    List<MemberSpecification> memberSpecificationList = ((ClassSpecification) getResult).fieldSpecifications;
    assertEquals(1, memberSpecificationList.size());
    MemberSpecification getResult2 = memberSpecificationList.get(0);
    assertNull(getResult2.annotationType);
    assertNull(getResult2.descriptor);
    assertNull(getResult2.name);
    assertNull(getResult2.attributeNames);
    assertNull(((ClassSpecification) getResult).attributeNames);
    assertNull(((ClassSpecification) getResult).methodSpecifications);
    assertEquals(0, ((ClassSpecification) getResult).requiredSetAccessFlags);
    assertEquals(0, ((ClassSpecification) getResult).requiredUnsetAccessFlags);
    assertEquals(0, getResult2.requiredSetAccessFlags);
    assertEquals(0, getResult2.requiredUnsetAccessFlags);
  }

  /**
   * Method under test: {@link ClassSpecificationElement#appendTo(List)}
   */
  @Test
  void testAppendTo8() {
    // Arrange
    ClassSpecificationElement classSpecificationElement = new ClassSpecificationElement();
    classSpecificationElement.setExtendsannotation("Extends Annotation");
    classSpecificationElement.addConfiguredField(new MemberSpecificationElement());
    ArrayList<Object> classSpecifications = new ArrayList<>();

    // Act
    classSpecificationElement.appendTo(classSpecifications);

    // Assert
    assertEquals(1, classSpecifications.size());
    Object getResult = classSpecifications.get(0);
    assertTrue(getResult instanceof ClassSpecification);
    assertEquals("LExtends Annotation;", ((ClassSpecification) getResult).extendsAnnotationType);
    assertNull(((ClassSpecification) getResult).annotationType);
    assertNull(((ClassSpecification) getResult).className);
    assertNull(((ClassSpecification) getResult).comments);
    assertNull(((ClassSpecification) getResult).extendsClassName);
    assertNull(((ClassSpecification) getResult).memberComments);
    List<MemberSpecification> memberSpecificationList = ((ClassSpecification) getResult).fieldSpecifications;
    assertEquals(1, memberSpecificationList.size());
    MemberSpecification getResult2 = memberSpecificationList.get(0);
    assertNull(getResult2.annotationType);
    assertNull(getResult2.descriptor);
    assertNull(getResult2.name);
    assertNull(getResult2.attributeNames);
    assertNull(((ClassSpecification) getResult).attributeNames);
    assertNull(((ClassSpecification) getResult).methodSpecifications);
    assertEquals(0, ((ClassSpecification) getResult).requiredSetAccessFlags);
    assertEquals(0, ((ClassSpecification) getResult).requiredUnsetAccessFlags);
    assertEquals(0, getResult2.requiredSetAccessFlags);
    assertEquals(0, getResult2.requiredUnsetAccessFlags);
  }

  /**
   * Method under test: {@link ClassSpecificationElement#appendTo(List)}
   */
  @Test
  void testAppendTo9() {
    // Arrange
    ClassSpecificationElement classSpecificationElement = new ClassSpecificationElement();
    classSpecificationElement.setExtends("Extends ");
    classSpecificationElement.addConfiguredField(new MemberSpecificationElement());
    ArrayList<Object> classSpecifications = new ArrayList<>();

    // Act
    classSpecificationElement.appendTo(classSpecifications);

    // Assert
    assertEquals(1, classSpecifications.size());
    Object getResult = classSpecifications.get(0);
    assertTrue(getResult instanceof ClassSpecification);
    assertEquals("Extends ", ((ClassSpecification) getResult).extendsClassName);
    assertNull(((ClassSpecification) getResult).annotationType);
    assertNull(((ClassSpecification) getResult).className);
    assertNull(((ClassSpecification) getResult).comments);
    assertNull(((ClassSpecification) getResult).extendsAnnotationType);
    assertNull(((ClassSpecification) getResult).memberComments);
    List<MemberSpecification> memberSpecificationList = ((ClassSpecification) getResult).fieldSpecifications;
    assertEquals(1, memberSpecificationList.size());
    MemberSpecification getResult2 = memberSpecificationList.get(0);
    assertNull(getResult2.annotationType);
    assertNull(getResult2.descriptor);
    assertNull(getResult2.name);
    assertNull(getResult2.attributeNames);
    assertNull(((ClassSpecification) getResult).attributeNames);
    assertNull(((ClassSpecification) getResult).methodSpecifications);
    assertEquals(0, ((ClassSpecification) getResult).requiredSetAccessFlags);
    assertEquals(0, ((ClassSpecification) getResult).requiredUnsetAccessFlags);
    assertEquals(0, getResult2.requiredSetAccessFlags);
    assertEquals(0, getResult2.requiredUnsetAccessFlags);
  }

  /**
   * Method under test: {@link ClassSpecificationElement#appendTo(List)}
   */
  @Test
  void testAppendTo10() {
    // Arrange
    ClassSpecificationElement classSpecificationElement = new ClassSpecificationElement();

    ArrayList<Object> classSpecifications = new ArrayList<>();
    classSpecifications.add("42");

    // Act
    classSpecificationElement.appendTo(classSpecifications);

    // Assert
    assertEquals(2, classSpecifications.size());
    Object getResult = classSpecifications.get(1);
    assertTrue(getResult instanceof ClassSpecification);
    assertEquals("42", classSpecifications.get(0));
    assertNull(((ClassSpecification) getResult).annotationType);
    assertNull(((ClassSpecification) getResult).className);
    assertNull(((ClassSpecification) getResult).comments);
    assertNull(((ClassSpecification) getResult).extendsAnnotationType);
    assertNull(((ClassSpecification) getResult).extendsClassName);
    assertNull(((ClassSpecification) getResult).memberComments);
    assertNull(((ClassSpecification) getResult).attributeNames);
    assertNull(((ClassSpecification) getResult).fieldSpecifications);
    assertNull(((ClassSpecification) getResult).methodSpecifications);
    assertEquals(0, ((ClassSpecification) getResult).requiredSetAccessFlags);
    assertEquals(0, ((ClassSpecification) getResult).requiredUnsetAccessFlags);
  }

  /**
   * Method under test: {@link ClassSpecificationElement#appendTo(List)}
   */
  @Test
  void testAppendTo11() {
    // Arrange
    ClassSpecificationElement classSpecificationElement = new ClassSpecificationElement();
    classSpecificationElement.setAccess(" ,");
    classSpecificationElement.addConfiguredField(new MemberSpecificationElement());
    ArrayList<Object> classSpecifications = new ArrayList<>();

    // Act
    classSpecificationElement.appendTo(classSpecifications);

    // Assert
    assertEquals(1, classSpecifications.size());
    Object getResult = classSpecifications.get(0);
    assertTrue(getResult instanceof ClassSpecification);
    assertNull(((ClassSpecification) getResult).annotationType);
    assertNull(((ClassSpecification) getResult).className);
    assertNull(((ClassSpecification) getResult).comments);
    assertNull(((ClassSpecification) getResult).extendsAnnotationType);
    assertNull(((ClassSpecification) getResult).extendsClassName);
    assertNull(((ClassSpecification) getResult).memberComments);
    List<MemberSpecification> memberSpecificationList = ((ClassSpecification) getResult).fieldSpecifications;
    assertEquals(1, memberSpecificationList.size());
    MemberSpecification getResult2 = memberSpecificationList.get(0);
    assertNull(getResult2.annotationType);
    assertNull(getResult2.descriptor);
    assertNull(getResult2.name);
    assertNull(getResult2.attributeNames);
    assertNull(((ClassSpecification) getResult).attributeNames);
    assertNull(((ClassSpecification) getResult).methodSpecifications);
    assertEquals(0, ((ClassSpecification) getResult).requiredSetAccessFlags);
    assertEquals(0, ((ClassSpecification) getResult).requiredUnsetAccessFlags);
    assertEquals(0, getResult2.requiredSetAccessFlags);
    assertEquals(0, getResult2.requiredUnsetAccessFlags);
  }

  /**
   * Method under test: {@link ClassSpecificationElement#appendTo(List)}
   */
  @Test
  void testAppendTo12() {
    // Arrange
    ClassSpecificationElement classSpecificationElement = new ClassSpecificationElement();
    classSpecificationElement.setAccess("!");
    classSpecificationElement.addConfiguredField(new MemberSpecificationElement());

    // Act and Assert
    assertThrows(BuildException.class, () -> classSpecificationElement.appendTo(new ArrayList<>()));
  }

  /**
   * Method under test: {@link ClassSpecificationElement#appendTo(List)}
   */
  @Test
  void testAppendTo13() {
    // Arrange
    ClassSpecificationElement classSpecificationElement = new ClassSpecificationElement();
    classSpecificationElement.setAccess("public");
    classSpecificationElement.addConfiguredField(new MemberSpecificationElement());
    ArrayList<Object> classSpecifications = new ArrayList<>();

    // Act
    classSpecificationElement.appendTo(classSpecifications);

    // Assert
    assertEquals(1, classSpecifications.size());
    Object getResult = classSpecifications.get(0);
    assertTrue(getResult instanceof ClassSpecification);
    assertNull(((ClassSpecification) getResult).annotationType);
    assertNull(((ClassSpecification) getResult).className);
    assertNull(((ClassSpecification) getResult).comments);
    assertNull(((ClassSpecification) getResult).extendsAnnotationType);
    assertNull(((ClassSpecification) getResult).extendsClassName);
    assertNull(((ClassSpecification) getResult).memberComments);
    List<MemberSpecification> memberSpecificationList = ((ClassSpecification) getResult).fieldSpecifications;
    assertEquals(1, memberSpecificationList.size());
    MemberSpecification getResult2 = memberSpecificationList.get(0);
    assertNull(getResult2.annotationType);
    assertNull(getResult2.descriptor);
    assertNull(getResult2.name);
    assertNull(getResult2.attributeNames);
    assertNull(((ClassSpecification) getResult).attributeNames);
    assertNull(((ClassSpecification) getResult).methodSpecifications);
    assertEquals(0, ((ClassSpecification) getResult).requiredUnsetAccessFlags);
    assertEquals(0, getResult2.requiredSetAccessFlags);
    assertEquals(0, getResult2.requiredUnsetAccessFlags);
    assertEquals(1, ((ClassSpecification) getResult).requiredSetAccessFlags);
  }

  /**
   * Method under test: {@link ClassSpecificationElement#appendTo(List)}
   */
  @Test
  void testAppendTo14() {
    // Arrange
    ClassSpecificationElement classSpecificationElement = new ClassSpecificationElement();
    classSpecificationElement.setAccess("final");
    classSpecificationElement.addConfiguredField(new MemberSpecificationElement());
    ArrayList<Object> classSpecifications = new ArrayList<>();

    // Act
    classSpecificationElement.appendTo(classSpecifications);

    // Assert
    assertEquals(1, classSpecifications.size());
    Object getResult = classSpecifications.get(0);
    assertTrue(getResult instanceof ClassSpecification);
    assertNull(((ClassSpecification) getResult).annotationType);
    assertNull(((ClassSpecification) getResult).className);
    assertNull(((ClassSpecification) getResult).comments);
    assertNull(((ClassSpecification) getResult).extendsAnnotationType);
    assertNull(((ClassSpecification) getResult).extendsClassName);
    assertNull(((ClassSpecification) getResult).memberComments);
    List<MemberSpecification> memberSpecificationList = ((ClassSpecification) getResult).fieldSpecifications;
    assertEquals(1, memberSpecificationList.size());
    MemberSpecification getResult2 = memberSpecificationList.get(0);
    assertNull(getResult2.annotationType);
    assertNull(getResult2.descriptor);
    assertNull(getResult2.name);
    assertNull(getResult2.attributeNames);
    assertNull(((ClassSpecification) getResult).attributeNames);
    assertNull(((ClassSpecification) getResult).methodSpecifications);
    assertEquals(0, ((ClassSpecification) getResult).requiredUnsetAccessFlags);
    assertEquals(0, getResult2.requiredSetAccessFlags);
    assertEquals(0, getResult2.requiredUnsetAccessFlags);
    assertEquals(Short.SIZE, ((ClassSpecification) getResult).requiredSetAccessFlags);
  }

  /**
   * Method under test: {@link ClassSpecificationElement#appendTo(List)}
   */
  @Test
  void testAppendTo15() {
    // Arrange
    ClassSpecificationElement classSpecificationElement = new ClassSpecificationElement();
    classSpecificationElement.setAccess("abstract");
    classSpecificationElement.addConfiguredField(new MemberSpecificationElement());
    ArrayList<Object> classSpecifications = new ArrayList<>();

    // Act
    classSpecificationElement.appendTo(classSpecifications);

    // Assert
    assertEquals(1, classSpecifications.size());
    Object getResult = classSpecifications.get(0);
    assertTrue(getResult instanceof ClassSpecification);
    assertNull(((ClassSpecification) getResult).annotationType);
    assertNull(((ClassSpecification) getResult).className);
    assertNull(((ClassSpecification) getResult).comments);
    assertNull(((ClassSpecification) getResult).extendsAnnotationType);
    assertNull(((ClassSpecification) getResult).extendsClassName);
    assertNull(((ClassSpecification) getResult).memberComments);
    List<MemberSpecification> memberSpecificationList = ((ClassSpecification) getResult).fieldSpecifications;
    assertEquals(1, memberSpecificationList.size());
    MemberSpecification getResult2 = memberSpecificationList.get(0);
    assertNull(getResult2.annotationType);
    assertNull(getResult2.descriptor);
    assertNull(getResult2.name);
    assertNull(getResult2.attributeNames);
    assertNull(((ClassSpecification) getResult).attributeNames);
    assertNull(((ClassSpecification) getResult).methodSpecifications);
    assertEquals(0, ((ClassSpecification) getResult).requiredUnsetAccessFlags);
    assertEquals(0, getResult2.requiredSetAccessFlags);
    assertEquals(0, getResult2.requiredUnsetAccessFlags);
    assertEquals(1024, ((ClassSpecification) getResult).requiredSetAccessFlags);
  }

  /**
   * Method under test: {@link ClassSpecificationElement#appendTo(List)}
   */
  @Test
  void testAppendTo16() {
    // Arrange
    ClassSpecificationElement classSpecificationElement = new ClassSpecificationElement();
    classSpecificationElement.setAccess("synthetic");
    classSpecificationElement.addConfiguredField(new MemberSpecificationElement());
    ArrayList<Object> classSpecifications = new ArrayList<>();

    // Act
    classSpecificationElement.appendTo(classSpecifications);

    // Assert
    assertEquals(1, classSpecifications.size());
    Object getResult = classSpecifications.get(0);
    assertTrue(getResult instanceof ClassSpecification);
    assertNull(((ClassSpecification) getResult).annotationType);
    assertNull(((ClassSpecification) getResult).className);
    assertNull(((ClassSpecification) getResult).comments);
    assertNull(((ClassSpecification) getResult).extendsAnnotationType);
    assertNull(((ClassSpecification) getResult).extendsClassName);
    assertNull(((ClassSpecification) getResult).memberComments);
    List<MemberSpecification> memberSpecificationList = ((ClassSpecification) getResult).fieldSpecifications;
    assertEquals(1, memberSpecificationList.size());
    MemberSpecification getResult2 = memberSpecificationList.get(0);
    assertNull(getResult2.annotationType);
    assertNull(getResult2.descriptor);
    assertNull(getResult2.name);
    assertNull(getResult2.attributeNames);
    assertNull(((ClassSpecification) getResult).attributeNames);
    assertNull(((ClassSpecification) getResult).methodSpecifications);
    assertEquals(0, ((ClassSpecification) getResult).requiredUnsetAccessFlags);
    assertEquals(0, getResult2.requiredSetAccessFlags);
    assertEquals(0, getResult2.requiredUnsetAccessFlags);
    assertEquals(4096, ((ClassSpecification) getResult).requiredSetAccessFlags);
  }

  /**
   * Method under test: {@link ClassSpecificationElement#appendTo(List)}
   */
  @Test
  void testAppendTo17() {
    // Arrange
    ClassSpecificationElement classSpecificationElement = new ClassSpecificationElement();
    classSpecificationElement.setAccess("@");
    classSpecificationElement.addConfiguredField(new MemberSpecificationElement());
    ArrayList<Object> classSpecifications = new ArrayList<>();

    // Act
    classSpecificationElement.appendTo(classSpecifications);

    // Assert
    assertEquals(1, classSpecifications.size());
    Object getResult = classSpecifications.get(0);
    assertTrue(getResult instanceof ClassSpecification);
    assertNull(((ClassSpecification) getResult).annotationType);
    assertNull(((ClassSpecification) getResult).className);
    assertNull(((ClassSpecification) getResult).comments);
    assertNull(((ClassSpecification) getResult).extendsAnnotationType);
    assertNull(((ClassSpecification) getResult).extendsClassName);
    assertNull(((ClassSpecification) getResult).memberComments);
    List<MemberSpecification> memberSpecificationList = ((ClassSpecification) getResult).fieldSpecifications;
    assertEquals(1, memberSpecificationList.size());
    MemberSpecification getResult2 = memberSpecificationList.get(0);
    assertNull(getResult2.annotationType);
    assertNull(getResult2.descriptor);
    assertNull(getResult2.name);
    assertNull(getResult2.attributeNames);
    assertNull(((ClassSpecification) getResult).attributeNames);
    assertNull(((ClassSpecification) getResult).methodSpecifications);
    assertEquals(0, ((ClassSpecification) getResult).requiredUnsetAccessFlags);
    assertEquals(0, getResult2.requiredSetAccessFlags);
    assertEquals(0, getResult2.requiredUnsetAccessFlags);
    assertEquals(8192, ((ClassSpecification) getResult).requiredSetAccessFlags);
  }

  /**
   * Method under test: {@link ClassSpecificationElement#appendTo(List)}
   */
  @Test
  void testAppendTo18() {
    // Arrange
    ClassSpecificationElement classSpecificationElement = new ClassSpecificationElement();
    classSpecificationElement.setType("!");
    classSpecificationElement.addConfiguredField(new MemberSpecificationElement());

    // Act and Assert
    assertThrows(BuildException.class, () -> classSpecificationElement.appendTo(new ArrayList<>()));
  }

  /**
   * Method under test: {@link ClassSpecificationElement#appendTo(List)}
   */
  @Test
  void testAppendTo19() {
    // Arrange
    ClassSpecificationElement classSpecificationElement = new ClassSpecificationElement();
    classSpecificationElement.setType("class");
    classSpecificationElement.addConfiguredField(new MemberSpecificationElement());
    ArrayList<Object> classSpecifications = new ArrayList<>();

    // Act
    classSpecificationElement.appendTo(classSpecifications);

    // Assert
    assertEquals(1, classSpecifications.size());
    Object getResult = classSpecifications.get(0);
    assertTrue(getResult instanceof ClassSpecification);
    assertNull(((ClassSpecification) getResult).annotationType);
    assertNull(((ClassSpecification) getResult).className);
    assertNull(((ClassSpecification) getResult).comments);
    assertNull(((ClassSpecification) getResult).extendsAnnotationType);
    assertNull(((ClassSpecification) getResult).extendsClassName);
    assertNull(((ClassSpecification) getResult).memberComments);
    List<MemberSpecification> memberSpecificationList = ((ClassSpecification) getResult).fieldSpecifications;
    assertEquals(1, memberSpecificationList.size());
    MemberSpecification getResult2 = memberSpecificationList.get(0);
    assertNull(getResult2.annotationType);
    assertNull(getResult2.descriptor);
    assertNull(getResult2.name);
    assertNull(getResult2.attributeNames);
    assertNull(((ClassSpecification) getResult).attributeNames);
    assertNull(((ClassSpecification) getResult).methodSpecifications);
    assertEquals(0, ((ClassSpecification) getResult).requiredSetAccessFlags);
    assertEquals(0, ((ClassSpecification) getResult).requiredUnsetAccessFlags);
    assertEquals(0, getResult2.requiredSetAccessFlags);
    assertEquals(0, getResult2.requiredUnsetAccessFlags);
  }

  /**
   * Method under test: {@link ClassSpecificationElement#appendTo(List)}
   */
  @Test
  void testAppendTo20() {
    // Arrange
    ClassSpecificationElement classSpecificationElement = new ClassSpecificationElement();
    classSpecificationElement.setType("interface");
    classSpecificationElement.addConfiguredField(new MemberSpecificationElement());
    ArrayList<Object> classSpecifications = new ArrayList<>();

    // Act
    classSpecificationElement.appendTo(classSpecifications);

    // Assert
    assertEquals(1, classSpecifications.size());
    Object getResult = classSpecifications.get(0);
    assertTrue(getResult instanceof ClassSpecification);
    assertNull(((ClassSpecification) getResult).annotationType);
    assertNull(((ClassSpecification) getResult).className);
    assertNull(((ClassSpecification) getResult).comments);
    assertNull(((ClassSpecification) getResult).extendsAnnotationType);
    assertNull(((ClassSpecification) getResult).extendsClassName);
    assertNull(((ClassSpecification) getResult).memberComments);
    List<MemberSpecification> memberSpecificationList = ((ClassSpecification) getResult).fieldSpecifications;
    assertEquals(1, memberSpecificationList.size());
    MemberSpecification getResult2 = memberSpecificationList.get(0);
    assertNull(getResult2.annotationType);
    assertNull(getResult2.descriptor);
    assertNull(getResult2.name);
    assertNull(getResult2.attributeNames);
    assertNull(((ClassSpecification) getResult).attributeNames);
    assertNull(((ClassSpecification) getResult).methodSpecifications);
    assertEquals(0, ((ClassSpecification) getResult).requiredUnsetAccessFlags);
    assertEquals(0, getResult2.requiredSetAccessFlags);
    assertEquals(0, getResult2.requiredUnsetAccessFlags);
    assertEquals(512, ((ClassSpecification) getResult).requiredSetAccessFlags);
  }

  /**
   * Method under test: {@link ClassSpecificationElement#appendTo(List)}
   */
  @Test
  void testAppendTo21() {
    // Arrange
    ClassSpecificationElement classSpecificationElement = new ClassSpecificationElement();
    classSpecificationElement.setType("!interface");
    classSpecificationElement.addConfiguredField(new MemberSpecificationElement());
    ArrayList<Object> classSpecifications = new ArrayList<>();

    // Act
    classSpecificationElement.appendTo(classSpecifications);

    // Assert
    assertEquals(1, classSpecifications.size());
    Object getResult = classSpecifications.get(0);
    assertTrue(getResult instanceof ClassSpecification);
    assertNull(((ClassSpecification) getResult).annotationType);
    assertNull(((ClassSpecification) getResult).className);
    assertNull(((ClassSpecification) getResult).comments);
    assertNull(((ClassSpecification) getResult).extendsAnnotationType);
    assertNull(((ClassSpecification) getResult).extendsClassName);
    assertNull(((ClassSpecification) getResult).memberComments);
    List<MemberSpecification> memberSpecificationList = ((ClassSpecification) getResult).fieldSpecifications;
    assertEquals(1, memberSpecificationList.size());
    MemberSpecification getResult2 = memberSpecificationList.get(0);
    assertNull(getResult2.annotationType);
    assertNull(getResult2.descriptor);
    assertNull(getResult2.name);
    assertNull(getResult2.attributeNames);
    assertNull(((ClassSpecification) getResult).attributeNames);
    assertNull(((ClassSpecification) getResult).methodSpecifications);
    assertEquals(0, ((ClassSpecification) getResult).requiredSetAccessFlags);
    assertEquals(0, getResult2.requiredSetAccessFlags);
    assertEquals(0, getResult2.requiredUnsetAccessFlags);
    assertEquals(512, ((ClassSpecification) getResult).requiredUnsetAccessFlags);
  }

  /**
   * Method under test: {@link ClassSpecificationElement#appendTo(List)}
   */
  @Test
  void testAppendTo22() {
    // Arrange
    ClassSpecificationElement classSpecificationElement = new ClassSpecificationElement();
    classSpecificationElement.setType("enum");
    classSpecificationElement.addConfiguredField(new MemberSpecificationElement());
    ArrayList<Object> classSpecifications = new ArrayList<>();

    // Act
    classSpecificationElement.appendTo(classSpecifications);

    // Assert
    assertEquals(1, classSpecifications.size());
    Object getResult = classSpecifications.get(0);
    assertTrue(getResult instanceof ClassSpecification);
    assertNull(((ClassSpecification) getResult).annotationType);
    assertNull(((ClassSpecification) getResult).className);
    assertNull(((ClassSpecification) getResult).comments);
    assertNull(((ClassSpecification) getResult).extendsAnnotationType);
    assertNull(((ClassSpecification) getResult).extendsClassName);
    assertNull(((ClassSpecification) getResult).memberComments);
    List<MemberSpecification> memberSpecificationList = ((ClassSpecification) getResult).fieldSpecifications;
    assertEquals(1, memberSpecificationList.size());
    MemberSpecification getResult2 = memberSpecificationList.get(0);
    assertNull(getResult2.annotationType);
    assertNull(getResult2.descriptor);
    assertNull(getResult2.name);
    assertNull(getResult2.attributeNames);
    assertNull(((ClassSpecification) getResult).attributeNames);
    assertNull(((ClassSpecification) getResult).methodSpecifications);
    assertEquals(0, ((ClassSpecification) getResult).requiredUnsetAccessFlags);
    assertEquals(0, getResult2.requiredSetAccessFlags);
    assertEquals(0, getResult2.requiredUnsetAccessFlags);
    assertEquals(16384, ((ClassSpecification) getResult).requiredSetAccessFlags);
  }

  /**
   * Method under test: {@link ClassSpecificationElement#appendTo(List)}
   */
  @Test
  void testAppendTo23() {
    // Arrange
    ClassSpecificationElement classSpecificationElement = new ClassSpecificationElement();
    classSpecificationElement.setType("!enum");
    classSpecificationElement.addConfiguredField(new MemberSpecificationElement());
    ArrayList<Object> classSpecifications = new ArrayList<>();

    // Act
    classSpecificationElement.appendTo(classSpecifications);

    // Assert
    assertEquals(1, classSpecifications.size());
    Object getResult = classSpecifications.get(0);
    assertTrue(getResult instanceof ClassSpecification);
    assertNull(((ClassSpecification) getResult).annotationType);
    assertNull(((ClassSpecification) getResult).className);
    assertNull(((ClassSpecification) getResult).comments);
    assertNull(((ClassSpecification) getResult).extendsAnnotationType);
    assertNull(((ClassSpecification) getResult).extendsClassName);
    assertNull(((ClassSpecification) getResult).memberComments);
    List<MemberSpecification> memberSpecificationList = ((ClassSpecification) getResult).fieldSpecifications;
    assertEquals(1, memberSpecificationList.size());
    MemberSpecification getResult2 = memberSpecificationList.get(0);
    assertNull(getResult2.annotationType);
    assertNull(getResult2.descriptor);
    assertNull(getResult2.name);
    assertNull(getResult2.attributeNames);
    assertNull(((ClassSpecification) getResult).attributeNames);
    assertNull(((ClassSpecification) getResult).methodSpecifications);
    assertEquals(0, ((ClassSpecification) getResult).requiredSetAccessFlags);
    assertEquals(0, getResult2.requiredSetAccessFlags);
    assertEquals(0, getResult2.requiredUnsetAccessFlags);
    assertEquals(16384, ((ClassSpecification) getResult).requiredUnsetAccessFlags);
  }

  /**
   * Method under test: {@link ClassSpecificationElement#appendTo(List)}
   */
  @Test
  void testAppendTo24() {
    // Arrange
    ClassSpecificationElement classSpecificationElement = new ClassSpecificationElement();
    classSpecificationElement.setName("*");
    classSpecificationElement.addConfiguredField(new MemberSpecificationElement());
    ArrayList<Object> classSpecifications = new ArrayList<>();

    // Act
    classSpecificationElement.appendTo(classSpecifications);

    // Assert
    assertEquals(1, classSpecifications.size());
    Object getResult = classSpecifications.get(0);
    assertTrue(getResult instanceof ClassSpecification);
    assertNull(((ClassSpecification) getResult).annotationType);
    assertNull(((ClassSpecification) getResult).className);
    assertNull(((ClassSpecification) getResult).comments);
    assertNull(((ClassSpecification) getResult).extendsAnnotationType);
    assertNull(((ClassSpecification) getResult).extendsClassName);
    assertNull(((ClassSpecification) getResult).memberComments);
    List<MemberSpecification> memberSpecificationList = ((ClassSpecification) getResult).fieldSpecifications;
    assertEquals(1, memberSpecificationList.size());
    MemberSpecification getResult2 = memberSpecificationList.get(0);
    assertNull(getResult2.annotationType);
    assertNull(getResult2.descriptor);
    assertNull(getResult2.name);
    assertNull(getResult2.attributeNames);
    assertNull(((ClassSpecification) getResult).attributeNames);
    assertNull(((ClassSpecification) getResult).methodSpecifications);
    assertEquals(0, ((ClassSpecification) getResult).requiredSetAccessFlags);
    assertEquals(0, ((ClassSpecification) getResult).requiredUnsetAccessFlags);
    assertEquals(0, getResult2.requiredSetAccessFlags);
    assertEquals(0, getResult2.requiredUnsetAccessFlags);
  }

  /**
   * Method under test:
   * {@link ClassSpecificationElement#createClassSpecification(ClassSpecificationElement)}
   */
  @Test
  void testCreateClassSpecification() {
    // Arrange
    ClassSpecificationElement classSpecificationElement = new ClassSpecificationElement();

    // Act
    ClassSpecification actualCreateClassSpecificationResult = classSpecificationElement
        .createClassSpecification(new ClassSpecificationElement());

    // Assert
    assertNull(actualCreateClassSpecificationResult.annotationType);
    assertNull(actualCreateClassSpecificationResult.className);
    assertNull(actualCreateClassSpecificationResult.comments);
    assertNull(actualCreateClassSpecificationResult.extendsAnnotationType);
    assertNull(actualCreateClassSpecificationResult.extendsClassName);
    assertNull(actualCreateClassSpecificationResult.memberComments);
    assertNull(actualCreateClassSpecificationResult.attributeNames);
    assertNull(actualCreateClassSpecificationResult.fieldSpecifications);
    assertNull(actualCreateClassSpecificationResult.methodSpecifications);
    assertEquals(0, actualCreateClassSpecificationResult.requiredSetAccessFlags);
    assertEquals(0, actualCreateClassSpecificationResult.requiredUnsetAccessFlags);
  }

  /**
   * Method under test:
   * {@link ClassSpecificationElement#createClassSpecification(ClassSpecificationElement)}
   */
  @Test
  void testCreateClassSpecification2() {
    // Arrange
    ClassSpecificationElement classSpecificationElement = new ClassSpecificationElement();
    classSpecificationElement.addConfiguredField(new MemberSpecificationElement());

    // Act
    ClassSpecification actualCreateClassSpecificationResult = classSpecificationElement
        .createClassSpecification(new ClassSpecificationElement());

    // Assert
    assertNull(actualCreateClassSpecificationResult.annotationType);
    assertNull(actualCreateClassSpecificationResult.className);
    assertNull(actualCreateClassSpecificationResult.comments);
    assertNull(actualCreateClassSpecificationResult.extendsAnnotationType);
    assertNull(actualCreateClassSpecificationResult.extendsClassName);
    assertNull(actualCreateClassSpecificationResult.memberComments);
    List<MemberSpecification> memberSpecificationList = actualCreateClassSpecificationResult.fieldSpecifications;
    assertEquals(1, memberSpecificationList.size());
    MemberSpecification getResult = memberSpecificationList.get(0);
    assertNull(getResult.annotationType);
    assertNull(getResult.descriptor);
    assertNull(getResult.name);
    assertNull(getResult.attributeNames);
    assertNull(actualCreateClassSpecificationResult.attributeNames);
    assertNull(actualCreateClassSpecificationResult.methodSpecifications);
    assertEquals(0, actualCreateClassSpecificationResult.requiredSetAccessFlags);
    assertEquals(0, actualCreateClassSpecificationResult.requiredUnsetAccessFlags);
    assertEquals(0, getResult.requiredSetAccessFlags);
    assertEquals(0, getResult.requiredUnsetAccessFlags);
  }

  /**
   * Method under test:
   * {@link ClassSpecificationElement#createClassSpecification(ClassSpecificationElement)}
   */
  @Test
  void testCreateClassSpecification3() {
    // Arrange
    ClassSpecificationElement classSpecificationElement = new ClassSpecificationElement();
    classSpecificationElement.addConfiguredMethod(new MemberSpecificationElement());

    // Act
    ClassSpecification actualCreateClassSpecificationResult = classSpecificationElement
        .createClassSpecification(new ClassSpecificationElement());

    // Assert
    assertNull(actualCreateClassSpecificationResult.annotationType);
    assertNull(actualCreateClassSpecificationResult.className);
    assertNull(actualCreateClassSpecificationResult.comments);
    assertNull(actualCreateClassSpecificationResult.extendsAnnotationType);
    assertNull(actualCreateClassSpecificationResult.extendsClassName);
    assertNull(actualCreateClassSpecificationResult.memberComments);
    List<MemberSpecification> memberSpecificationList = actualCreateClassSpecificationResult.methodSpecifications;
    assertEquals(1, memberSpecificationList.size());
    MemberSpecification getResult = memberSpecificationList.get(0);
    assertNull(getResult.annotationType);
    assertNull(getResult.descriptor);
    assertNull(getResult.name);
    assertNull(getResult.attributeNames);
    assertNull(actualCreateClassSpecificationResult.attributeNames);
    assertNull(actualCreateClassSpecificationResult.fieldSpecifications);
    assertEquals(0, actualCreateClassSpecificationResult.requiredSetAccessFlags);
    assertEquals(0, actualCreateClassSpecificationResult.requiredUnsetAccessFlags);
    assertEquals(0, getResult.requiredSetAccessFlags);
    assertEquals(0, getResult.requiredUnsetAccessFlags);
  }

  /**
   * Method under test:
   * {@link ClassSpecificationElement#createClassSpecification(ClassSpecificationElement)}
   */
  @Test
  void testCreateClassSpecification4() {
    // Arrange
    ClassSpecificationElement classSpecificationElement = new ClassSpecificationElement();

    ClassSpecificationElement classSpecificationElement2 = new ClassSpecificationElement();
    classSpecificationElement2.setAccess("Access");

    // Act and Assert
    assertThrows(BuildException.class,
        () -> classSpecificationElement.createClassSpecification(classSpecificationElement2));
  }

  /**
   * Method under test:
   * {@link ClassSpecificationElement#createClassSpecification(ClassSpecificationElement)}
   */
  @Test
  void testCreateClassSpecification5() {
    // Arrange
    ClassSpecificationElement classSpecificationElement = new ClassSpecificationElement();

    ClassSpecificationElement classSpecificationElement2 = new ClassSpecificationElement();
    classSpecificationElement2.setAnnotation("Annotation");

    // Act
    ClassSpecification actualCreateClassSpecificationResult = classSpecificationElement
        .createClassSpecification(classSpecificationElement2);

    // Assert
    assertEquals("LAnnotation;", actualCreateClassSpecificationResult.annotationType);
    assertNull(actualCreateClassSpecificationResult.className);
    assertNull(actualCreateClassSpecificationResult.comments);
    assertNull(actualCreateClassSpecificationResult.extendsAnnotationType);
    assertNull(actualCreateClassSpecificationResult.extendsClassName);
    assertNull(actualCreateClassSpecificationResult.memberComments);
    assertNull(actualCreateClassSpecificationResult.attributeNames);
    assertNull(actualCreateClassSpecificationResult.fieldSpecifications);
    assertNull(actualCreateClassSpecificationResult.methodSpecifications);
    assertEquals(0, actualCreateClassSpecificationResult.requiredSetAccessFlags);
    assertEquals(0, actualCreateClassSpecificationResult.requiredUnsetAccessFlags);
  }

  /**
   * Method under test:
   * {@link ClassSpecificationElement#createClassSpecification(ClassSpecificationElement)}
   */
  @Test
  void testCreateClassSpecification6() {
    // Arrange
    ClassSpecificationElement classSpecificationElement = new ClassSpecificationElement();

    ClassSpecificationElement classSpecificationElement2 = new ClassSpecificationElement();
    classSpecificationElement2.setType("Type");

    // Act and Assert
    assertThrows(BuildException.class,
        () -> classSpecificationElement.createClassSpecification(classSpecificationElement2));
  }

  /**
   * Method under test:
   * {@link ClassSpecificationElement#createClassSpecification(ClassSpecificationElement)}
   */
  @Test
  void testCreateClassSpecification7() {
    // Arrange
    ClassSpecificationElement classSpecificationElement = new ClassSpecificationElement();

    ClassSpecificationElement classSpecificationElement2 = new ClassSpecificationElement();
    classSpecificationElement2.setName("Name");

    // Act
    ClassSpecification actualCreateClassSpecificationResult = classSpecificationElement
        .createClassSpecification(classSpecificationElement2);

    // Assert
    assertEquals("Name", actualCreateClassSpecificationResult.className);
    assertNull(actualCreateClassSpecificationResult.annotationType);
    assertNull(actualCreateClassSpecificationResult.comments);
    assertNull(actualCreateClassSpecificationResult.extendsAnnotationType);
    assertNull(actualCreateClassSpecificationResult.extendsClassName);
    assertNull(actualCreateClassSpecificationResult.memberComments);
    assertNull(actualCreateClassSpecificationResult.attributeNames);
    assertNull(actualCreateClassSpecificationResult.fieldSpecifications);
    assertNull(actualCreateClassSpecificationResult.methodSpecifications);
    assertEquals(0, actualCreateClassSpecificationResult.requiredSetAccessFlags);
    assertEquals(0, actualCreateClassSpecificationResult.requiredUnsetAccessFlags);
  }

  /**
   * Method under test:
   * {@link ClassSpecificationElement#createClassSpecification(ClassSpecificationElement)}
   */
  @Test
  void testCreateClassSpecification8() {
    // Arrange
    ClassSpecificationElement classSpecificationElement = new ClassSpecificationElement();

    ClassSpecificationElement classSpecificationElement2 = new ClassSpecificationElement();
    classSpecificationElement2.setExtendsannotation("Extends Annotation");

    // Act
    ClassSpecification actualCreateClassSpecificationResult = classSpecificationElement
        .createClassSpecification(classSpecificationElement2);

    // Assert
    assertEquals("LExtends Annotation;", actualCreateClassSpecificationResult.extendsAnnotationType);
    assertNull(actualCreateClassSpecificationResult.annotationType);
    assertNull(actualCreateClassSpecificationResult.className);
    assertNull(actualCreateClassSpecificationResult.comments);
    assertNull(actualCreateClassSpecificationResult.extendsClassName);
    assertNull(actualCreateClassSpecificationResult.memberComments);
    assertNull(actualCreateClassSpecificationResult.attributeNames);
    assertNull(actualCreateClassSpecificationResult.fieldSpecifications);
    assertNull(actualCreateClassSpecificationResult.methodSpecifications);
    assertEquals(0, actualCreateClassSpecificationResult.requiredSetAccessFlags);
    assertEquals(0, actualCreateClassSpecificationResult.requiredUnsetAccessFlags);
  }

  /**
   * Method under test:
   * {@link ClassSpecificationElement#createClassSpecification(ClassSpecificationElement)}
   */
  @Test
  void testCreateClassSpecification9() {
    // Arrange
    ClassSpecificationElement classSpecificationElement = new ClassSpecificationElement();

    ClassSpecificationElement classSpecificationElement2 = new ClassSpecificationElement();
    classSpecificationElement2.setExtends("Extends ");

    // Act
    ClassSpecification actualCreateClassSpecificationResult = classSpecificationElement
        .createClassSpecification(classSpecificationElement2);

    // Assert
    assertEquals("Extends ", actualCreateClassSpecificationResult.extendsClassName);
    assertNull(actualCreateClassSpecificationResult.annotationType);
    assertNull(actualCreateClassSpecificationResult.className);
    assertNull(actualCreateClassSpecificationResult.comments);
    assertNull(actualCreateClassSpecificationResult.extendsAnnotationType);
    assertNull(actualCreateClassSpecificationResult.memberComments);
    assertNull(actualCreateClassSpecificationResult.attributeNames);
    assertNull(actualCreateClassSpecificationResult.fieldSpecifications);
    assertNull(actualCreateClassSpecificationResult.methodSpecifications);
    assertEquals(0, actualCreateClassSpecificationResult.requiredSetAccessFlags);
    assertEquals(0, actualCreateClassSpecificationResult.requiredUnsetAccessFlags);
  }

  /**
   * Method under test:
   * {@link ClassSpecificationElement#createClassSpecification(ClassSpecificationElement)}
   */
  @Test
  void testCreateClassSpecification10() {
    // Arrange
    ClassSpecificationElement classSpecificationElement = new ClassSpecificationElement();

    ClassSpecificationElement classSpecificationElement2 = new ClassSpecificationElement();
    classSpecificationElement2.setAccess(" ,");

    // Act
    ClassSpecification actualCreateClassSpecificationResult = classSpecificationElement
        .createClassSpecification(classSpecificationElement2);

    // Assert
    assertNull(actualCreateClassSpecificationResult.annotationType);
    assertNull(actualCreateClassSpecificationResult.className);
    assertNull(actualCreateClassSpecificationResult.comments);
    assertNull(actualCreateClassSpecificationResult.extendsAnnotationType);
    assertNull(actualCreateClassSpecificationResult.extendsClassName);
    assertNull(actualCreateClassSpecificationResult.memberComments);
    assertNull(actualCreateClassSpecificationResult.attributeNames);
    assertNull(actualCreateClassSpecificationResult.fieldSpecifications);
    assertNull(actualCreateClassSpecificationResult.methodSpecifications);
    assertEquals(0, actualCreateClassSpecificationResult.requiredSetAccessFlags);
    assertEquals(0, actualCreateClassSpecificationResult.requiredUnsetAccessFlags);
  }

  /**
   * Method under test:
   * {@link ClassSpecificationElement#createClassSpecification(ClassSpecificationElement)}
   */
  @Test
  void testCreateClassSpecification11() {
    // Arrange
    ClassSpecificationElement classSpecificationElement = new ClassSpecificationElement();

    ClassSpecificationElement classSpecificationElement2 = new ClassSpecificationElement();
    classSpecificationElement2.setAccess("!");

    // Act and Assert
    assertThrows(BuildException.class,
        () -> classSpecificationElement.createClassSpecification(classSpecificationElement2));
  }

  /**
   * Method under test:
   * {@link ClassSpecificationElement#createClassSpecification(ClassSpecificationElement)}
   */
  @Test
  void testCreateClassSpecification12() {
    // Arrange
    ClassSpecificationElement classSpecificationElement = new ClassSpecificationElement();

    ClassSpecificationElement classSpecificationElement2 = new ClassSpecificationElement();
    classSpecificationElement2.setAccess("public");

    // Act
    ClassSpecification actualCreateClassSpecificationResult = classSpecificationElement
        .createClassSpecification(classSpecificationElement2);

    // Assert
    assertNull(actualCreateClassSpecificationResult.annotationType);
    assertNull(actualCreateClassSpecificationResult.className);
    assertNull(actualCreateClassSpecificationResult.comments);
    assertNull(actualCreateClassSpecificationResult.extendsAnnotationType);
    assertNull(actualCreateClassSpecificationResult.extendsClassName);
    assertNull(actualCreateClassSpecificationResult.memberComments);
    assertNull(actualCreateClassSpecificationResult.attributeNames);
    assertNull(actualCreateClassSpecificationResult.fieldSpecifications);
    assertNull(actualCreateClassSpecificationResult.methodSpecifications);
    assertEquals(0, actualCreateClassSpecificationResult.requiredUnsetAccessFlags);
    assertEquals(1, actualCreateClassSpecificationResult.requiredSetAccessFlags);
  }

  /**
   * Method under test:
   * {@link ClassSpecificationElement#createClassSpecification(ClassSpecificationElement)}
   */
  @Test
  void testCreateClassSpecification13() {
    // Arrange
    ClassSpecificationElement classSpecificationElement = new ClassSpecificationElement();

    ClassSpecificationElement classSpecificationElement2 = new ClassSpecificationElement();
    classSpecificationElement2.setAccess("final");

    // Act
    ClassSpecification actualCreateClassSpecificationResult = classSpecificationElement
        .createClassSpecification(classSpecificationElement2);

    // Assert
    assertNull(actualCreateClassSpecificationResult.annotationType);
    assertNull(actualCreateClassSpecificationResult.className);
    assertNull(actualCreateClassSpecificationResult.comments);
    assertNull(actualCreateClassSpecificationResult.extendsAnnotationType);
    assertNull(actualCreateClassSpecificationResult.extendsClassName);
    assertNull(actualCreateClassSpecificationResult.memberComments);
    assertNull(actualCreateClassSpecificationResult.attributeNames);
    assertNull(actualCreateClassSpecificationResult.fieldSpecifications);
    assertNull(actualCreateClassSpecificationResult.methodSpecifications);
    assertEquals(0, actualCreateClassSpecificationResult.requiredUnsetAccessFlags);
    assertEquals(Short.SIZE, actualCreateClassSpecificationResult.requiredSetAccessFlags);
  }

  /**
   * Method under test:
   * {@link ClassSpecificationElement#createClassSpecification(ClassSpecificationElement)}
   */
  @Test
  void testCreateClassSpecification14() {
    // Arrange
    ClassSpecificationElement classSpecificationElement = new ClassSpecificationElement();

    ClassSpecificationElement classSpecificationElement2 = new ClassSpecificationElement();
    classSpecificationElement2.setAccess("abstract");

    // Act
    ClassSpecification actualCreateClassSpecificationResult = classSpecificationElement
        .createClassSpecification(classSpecificationElement2);

    // Assert
    assertNull(actualCreateClassSpecificationResult.annotationType);
    assertNull(actualCreateClassSpecificationResult.className);
    assertNull(actualCreateClassSpecificationResult.comments);
    assertNull(actualCreateClassSpecificationResult.extendsAnnotationType);
    assertNull(actualCreateClassSpecificationResult.extendsClassName);
    assertNull(actualCreateClassSpecificationResult.memberComments);
    assertNull(actualCreateClassSpecificationResult.attributeNames);
    assertNull(actualCreateClassSpecificationResult.fieldSpecifications);
    assertNull(actualCreateClassSpecificationResult.methodSpecifications);
    assertEquals(0, actualCreateClassSpecificationResult.requiredUnsetAccessFlags);
    assertEquals(1024, actualCreateClassSpecificationResult.requiredSetAccessFlags);
  }

  /**
   * Method under test:
   * {@link ClassSpecificationElement#createClassSpecification(ClassSpecificationElement)}
   */
  @Test
  void testCreateClassSpecification15() {
    // Arrange
    ClassSpecificationElement classSpecificationElement = new ClassSpecificationElement();

    ClassSpecificationElement classSpecificationElement2 = new ClassSpecificationElement();
    classSpecificationElement2.setAccess("synthetic");

    // Act
    ClassSpecification actualCreateClassSpecificationResult = classSpecificationElement
        .createClassSpecification(classSpecificationElement2);

    // Assert
    assertNull(actualCreateClassSpecificationResult.annotationType);
    assertNull(actualCreateClassSpecificationResult.className);
    assertNull(actualCreateClassSpecificationResult.comments);
    assertNull(actualCreateClassSpecificationResult.extendsAnnotationType);
    assertNull(actualCreateClassSpecificationResult.extendsClassName);
    assertNull(actualCreateClassSpecificationResult.memberComments);
    assertNull(actualCreateClassSpecificationResult.attributeNames);
    assertNull(actualCreateClassSpecificationResult.fieldSpecifications);
    assertNull(actualCreateClassSpecificationResult.methodSpecifications);
    assertEquals(0, actualCreateClassSpecificationResult.requiredUnsetAccessFlags);
    assertEquals(4096, actualCreateClassSpecificationResult.requiredSetAccessFlags);
  }

  /**
   * Method under test:
   * {@link ClassSpecificationElement#createClassSpecification(ClassSpecificationElement)}
   */
  @Test
  void testCreateClassSpecification16() {
    // Arrange
    ClassSpecificationElement classSpecificationElement = new ClassSpecificationElement();

    ClassSpecificationElement classSpecificationElement2 = new ClassSpecificationElement();
    classSpecificationElement2.setAccess("@");

    // Act
    ClassSpecification actualCreateClassSpecificationResult = classSpecificationElement
        .createClassSpecification(classSpecificationElement2);

    // Assert
    assertNull(actualCreateClassSpecificationResult.annotationType);
    assertNull(actualCreateClassSpecificationResult.className);
    assertNull(actualCreateClassSpecificationResult.comments);
    assertNull(actualCreateClassSpecificationResult.extendsAnnotationType);
    assertNull(actualCreateClassSpecificationResult.extendsClassName);
    assertNull(actualCreateClassSpecificationResult.memberComments);
    assertNull(actualCreateClassSpecificationResult.attributeNames);
    assertNull(actualCreateClassSpecificationResult.fieldSpecifications);
    assertNull(actualCreateClassSpecificationResult.methodSpecifications);
    assertEquals(0, actualCreateClassSpecificationResult.requiredUnsetAccessFlags);
    assertEquals(8192, actualCreateClassSpecificationResult.requiredSetAccessFlags);
  }

  /**
   * Method under test:
   * {@link ClassSpecificationElement#createClassSpecification(ClassSpecificationElement)}
   */
  @Test
  void testCreateClassSpecification17() {
    // Arrange
    ClassSpecificationElement classSpecificationElement = new ClassSpecificationElement();

    ClassSpecificationElement classSpecificationElement2 = new ClassSpecificationElement();
    classSpecificationElement2.setType("!");

    // Act and Assert
    assertThrows(BuildException.class,
        () -> classSpecificationElement.createClassSpecification(classSpecificationElement2));
  }

  /**
   * Method under test:
   * {@link ClassSpecificationElement#createClassSpecification(ClassSpecificationElement)}
   */
  @Test
  void testCreateClassSpecification18() {
    // Arrange
    ClassSpecificationElement classSpecificationElement = new ClassSpecificationElement();

    ClassSpecificationElement classSpecificationElement2 = new ClassSpecificationElement();
    classSpecificationElement2.setType("class");

    // Act
    ClassSpecification actualCreateClassSpecificationResult = classSpecificationElement
        .createClassSpecification(classSpecificationElement2);

    // Assert
    assertNull(actualCreateClassSpecificationResult.annotationType);
    assertNull(actualCreateClassSpecificationResult.className);
    assertNull(actualCreateClassSpecificationResult.comments);
    assertNull(actualCreateClassSpecificationResult.extendsAnnotationType);
    assertNull(actualCreateClassSpecificationResult.extendsClassName);
    assertNull(actualCreateClassSpecificationResult.memberComments);
    assertNull(actualCreateClassSpecificationResult.attributeNames);
    assertNull(actualCreateClassSpecificationResult.fieldSpecifications);
    assertNull(actualCreateClassSpecificationResult.methodSpecifications);
    assertEquals(0, actualCreateClassSpecificationResult.requiredSetAccessFlags);
    assertEquals(0, actualCreateClassSpecificationResult.requiredUnsetAccessFlags);
  }

  /**
   * Method under test:
   * {@link ClassSpecificationElement#createClassSpecification(ClassSpecificationElement)}
   */
  @Test
  void testCreateClassSpecification19() {
    // Arrange
    ClassSpecificationElement classSpecificationElement = new ClassSpecificationElement();

    ClassSpecificationElement classSpecificationElement2 = new ClassSpecificationElement();
    classSpecificationElement2.setType("interface");

    // Act
    ClassSpecification actualCreateClassSpecificationResult = classSpecificationElement
        .createClassSpecification(classSpecificationElement2);

    // Assert
    assertNull(actualCreateClassSpecificationResult.annotationType);
    assertNull(actualCreateClassSpecificationResult.className);
    assertNull(actualCreateClassSpecificationResult.comments);
    assertNull(actualCreateClassSpecificationResult.extendsAnnotationType);
    assertNull(actualCreateClassSpecificationResult.extendsClassName);
    assertNull(actualCreateClassSpecificationResult.memberComments);
    assertNull(actualCreateClassSpecificationResult.attributeNames);
    assertNull(actualCreateClassSpecificationResult.fieldSpecifications);
    assertNull(actualCreateClassSpecificationResult.methodSpecifications);
    assertEquals(0, actualCreateClassSpecificationResult.requiredUnsetAccessFlags);
    assertEquals(512, actualCreateClassSpecificationResult.requiredSetAccessFlags);
  }

  /**
   * Method under test:
   * {@link ClassSpecificationElement#createClassSpecification(ClassSpecificationElement)}
   */
  @Test
  void testCreateClassSpecification20() {
    // Arrange
    ClassSpecificationElement classSpecificationElement = new ClassSpecificationElement();

    ClassSpecificationElement classSpecificationElement2 = new ClassSpecificationElement();
    classSpecificationElement2.setType("!interface");

    // Act
    ClassSpecification actualCreateClassSpecificationResult = classSpecificationElement
        .createClassSpecification(classSpecificationElement2);

    // Assert
    assertNull(actualCreateClassSpecificationResult.annotationType);
    assertNull(actualCreateClassSpecificationResult.className);
    assertNull(actualCreateClassSpecificationResult.comments);
    assertNull(actualCreateClassSpecificationResult.extendsAnnotationType);
    assertNull(actualCreateClassSpecificationResult.extendsClassName);
    assertNull(actualCreateClassSpecificationResult.memberComments);
    assertNull(actualCreateClassSpecificationResult.attributeNames);
    assertNull(actualCreateClassSpecificationResult.fieldSpecifications);
    assertNull(actualCreateClassSpecificationResult.methodSpecifications);
    assertEquals(0, actualCreateClassSpecificationResult.requiredSetAccessFlags);
    assertEquals(512, actualCreateClassSpecificationResult.requiredUnsetAccessFlags);
  }

  /**
   * Method under test:
   * {@link ClassSpecificationElement#createClassSpecification(ClassSpecificationElement)}
   */
  @Test
  void testCreateClassSpecification21() {
    // Arrange
    ClassSpecificationElement classSpecificationElement = new ClassSpecificationElement();

    ClassSpecificationElement classSpecificationElement2 = new ClassSpecificationElement();
    classSpecificationElement2.setType("enum");

    // Act
    ClassSpecification actualCreateClassSpecificationResult = classSpecificationElement
        .createClassSpecification(classSpecificationElement2);

    // Assert
    assertNull(actualCreateClassSpecificationResult.annotationType);
    assertNull(actualCreateClassSpecificationResult.className);
    assertNull(actualCreateClassSpecificationResult.comments);
    assertNull(actualCreateClassSpecificationResult.extendsAnnotationType);
    assertNull(actualCreateClassSpecificationResult.extendsClassName);
    assertNull(actualCreateClassSpecificationResult.memberComments);
    assertNull(actualCreateClassSpecificationResult.attributeNames);
    assertNull(actualCreateClassSpecificationResult.fieldSpecifications);
    assertNull(actualCreateClassSpecificationResult.methodSpecifications);
    assertEquals(0, actualCreateClassSpecificationResult.requiredUnsetAccessFlags);
    assertEquals(16384, actualCreateClassSpecificationResult.requiredSetAccessFlags);
  }

  /**
   * Method under test:
   * {@link ClassSpecificationElement#createClassSpecification(ClassSpecificationElement)}
   */
  @Test
  void testCreateClassSpecification22() {
    // Arrange
    ClassSpecificationElement classSpecificationElement = new ClassSpecificationElement();

    ClassSpecificationElement classSpecificationElement2 = new ClassSpecificationElement();
    classSpecificationElement2.setType("!enum");

    // Act
    ClassSpecification actualCreateClassSpecificationResult = classSpecificationElement
        .createClassSpecification(classSpecificationElement2);

    // Assert
    assertNull(actualCreateClassSpecificationResult.annotationType);
    assertNull(actualCreateClassSpecificationResult.className);
    assertNull(actualCreateClassSpecificationResult.comments);
    assertNull(actualCreateClassSpecificationResult.extendsAnnotationType);
    assertNull(actualCreateClassSpecificationResult.extendsClassName);
    assertNull(actualCreateClassSpecificationResult.memberComments);
    assertNull(actualCreateClassSpecificationResult.attributeNames);
    assertNull(actualCreateClassSpecificationResult.fieldSpecifications);
    assertNull(actualCreateClassSpecificationResult.methodSpecifications);
    assertEquals(0, actualCreateClassSpecificationResult.requiredSetAccessFlags);
    assertEquals(16384, actualCreateClassSpecificationResult.requiredUnsetAccessFlags);
  }

  /**
   * Method under test:
   * {@link ClassSpecificationElement#createClassSpecification(ClassSpecificationElement)}
   */
  @Test
  void testCreateClassSpecification23() {
    // Arrange
    ClassSpecificationElement classSpecificationElement = new ClassSpecificationElement();

    ClassSpecificationElement classSpecificationElement2 = new ClassSpecificationElement();
    classSpecificationElement2.setName("*");

    // Act
    ClassSpecification actualCreateClassSpecificationResult = classSpecificationElement
        .createClassSpecification(classSpecificationElement2);

    // Assert
    assertNull(actualCreateClassSpecificationResult.annotationType);
    assertNull(actualCreateClassSpecificationResult.className);
    assertNull(actualCreateClassSpecificationResult.comments);
    assertNull(actualCreateClassSpecificationResult.extendsAnnotationType);
    assertNull(actualCreateClassSpecificationResult.extendsClassName);
    assertNull(actualCreateClassSpecificationResult.memberComments);
    assertNull(actualCreateClassSpecificationResult.attributeNames);
    assertNull(actualCreateClassSpecificationResult.fieldSpecifications);
    assertNull(actualCreateClassSpecificationResult.methodSpecifications);
    assertEquals(0, actualCreateClassSpecificationResult.requiredSetAccessFlags);
    assertEquals(0, actualCreateClassSpecificationResult.requiredUnsetAccessFlags);
  }

  /**
   * Method under test:
   * {@link ClassSpecificationElement#addConfiguredField(MemberSpecificationElement)}
   */
  @Test
  void testAddConfiguredField() {
    // Arrange
    ClassSpecificationElement classSpecificationElement = new ClassSpecificationElement();
    MemberSpecificationElement memberSpecificationElement = mock(MemberSpecificationElement.class);
    doNothing().when(memberSpecificationElement).appendTo(Mockito.<List<Object>>any(), anyBoolean(), anyBoolean());

    // Act
    classSpecificationElement.addConfiguredField(memberSpecificationElement);

    // Assert
    verify(memberSpecificationElement).appendTo(isA(List.class), eq(false), eq(false));
  }

  /**
   * Method under test:
   * {@link ClassSpecificationElement#addConfiguredMethod(MemberSpecificationElement)}
   */
  @Test
  void testAddConfiguredMethod() {
    // Arrange
    ClassSpecificationElement classSpecificationElement = new ClassSpecificationElement();
    MemberSpecificationElement memberSpecificationElement = mock(MemberSpecificationElement.class);
    doNothing().when(memberSpecificationElement).appendTo(Mockito.<List<Object>>any(), anyBoolean(), anyBoolean());

    // Act
    classSpecificationElement.addConfiguredMethod(memberSpecificationElement);

    // Assert
    verify(memberSpecificationElement).appendTo(isA(List.class), eq(true), eq(false));
  }

  /**
   * Method under test:
   * {@link ClassSpecificationElement#addConfiguredConstructor(MemberSpecificationElement)}
   */
  @Test
  void testAddConfiguredConstructor() {
    // Arrange
    ClassSpecificationElement classSpecificationElement = new ClassSpecificationElement();
    MemberSpecificationElement memberSpecificationElement = mock(MemberSpecificationElement.class);
    doNothing().when(memberSpecificationElement).appendTo(Mockito.<List<Object>>any(), anyBoolean(), anyBoolean());

    // Act
    classSpecificationElement.addConfiguredConstructor(memberSpecificationElement);

    // Assert
    verify(memberSpecificationElement).appendTo(isA(List.class), eq(true), eq(true));
  }

  /**
   * Method under test: default or parameterless constructor of
   * {@link ClassSpecificationElement}
   */
  @Test
  void testNewClassSpecificationElement() {
    // Arrange and Act
    ClassSpecificationElement actualClassSpecificationElement = new ClassSpecificationElement();

    // Assert
    Location location = actualClassSpecificationElement.getLocation();
    assertNull(location.getFileName());
    assertNull(actualClassSpecificationElement.getDescription());
    assertNull(actualClassSpecificationElement.getProject());
    assertNull(actualClassSpecificationElement.getRefid());
    assertEquals(0, location.getColumnNumber());
    assertEquals(0, location.getLineNumber());
    assertFalse(actualClassSpecificationElement.isReference());
  }
}
