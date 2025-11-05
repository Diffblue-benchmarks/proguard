package proguard.ant;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import java.util.ArrayList;
import java.util.List;
import org.apache.tools.ant.Location;
import org.junit.jupiter.api.Test;
import proguard.KeepClassSpecification;
import proguard.MemberSpecification;

class KeepSpecificationElementDiffblueTest {
  /**
   * Method under test:
   * {@link KeepSpecificationElement#appendTo(List, boolean, boolean, boolean)}
   */
  @Test
  void testAppendTo() {
    // Arrange
    KeepSpecificationElement keepSpecificationElement = new KeepSpecificationElement();
    ArrayList<Object> keepSpecifications = new ArrayList<>();

    // Act
    keepSpecificationElement.appendTo(keepSpecifications, true, true, true);

    // Assert
    assertEquals(1, keepSpecifications.size());
    Object getResult = keepSpecifications.get(0);
    assertTrue(getResult instanceof KeepClassSpecification);
    assertNull(((KeepClassSpecification) getResult).annotationType);
    assertNull(((KeepClassSpecification) getResult).className);
    assertNull(((KeepClassSpecification) getResult).comments);
    assertNull(((KeepClassSpecification) getResult).extendsAnnotationType);
    assertNull(((KeepClassSpecification) getResult).extendsClassName);
    assertNull(((KeepClassSpecification) getResult).memberComments);
    assertNull(((KeepClassSpecification) getResult).attributeNames);
    assertNull(((KeepClassSpecification) getResult).fieldSpecifications);
    assertNull(((KeepClassSpecification) getResult).methodSpecifications);
    assertNull(((KeepClassSpecification) getResult).condition);
    assertEquals(0, ((KeepClassSpecification) getResult).requiredSetAccessFlags);
    assertEquals(0, ((KeepClassSpecification) getResult).requiredUnsetAccessFlags);
    assertFalse(((KeepClassSpecification) getResult).allowObfuscation);
    assertFalse(((KeepClassSpecification) getResult).allowOptimization);
    assertFalse(((KeepClassSpecification) getResult).allowShrinking);
    assertFalse(((KeepClassSpecification) getResult).markCodeAttributes);
    assertFalse(((KeepClassSpecification) getResult).markDescriptorClasses);
    assertTrue(((KeepClassSpecification) getResult).markClassMembers);
    assertTrue(((KeepClassSpecification) getResult).markClasses);
    assertTrue(((KeepClassSpecification) getResult).markConditionally);
  }

  /**
   * Method under test:
   * {@link KeepSpecificationElement#appendTo(List, boolean, boolean, boolean)}
   */
  @Test
  void testAppendTo2() {
    // Arrange
    KeepSpecificationElement keepSpecificationElement = new KeepSpecificationElement();
    keepSpecificationElement.addConfiguredField(new MemberSpecificationElement());
    ArrayList<Object> keepSpecifications = new ArrayList<>();

    // Act
    keepSpecificationElement.appendTo(keepSpecifications, true, true, true);

    // Assert
    assertEquals(1, keepSpecifications.size());
    Object getResult = keepSpecifications.get(0);
    assertTrue(getResult instanceof KeepClassSpecification);
    assertNull(((KeepClassSpecification) getResult).annotationType);
    assertNull(((KeepClassSpecification) getResult).className);
    assertNull(((KeepClassSpecification) getResult).comments);
    assertNull(((KeepClassSpecification) getResult).extendsAnnotationType);
    assertNull(((KeepClassSpecification) getResult).extendsClassName);
    assertNull(((KeepClassSpecification) getResult).memberComments);
    List<MemberSpecification> memberSpecificationList = ((KeepClassSpecification) getResult).fieldSpecifications;
    assertEquals(1, memberSpecificationList.size());
    MemberSpecification getResult2 = memberSpecificationList.get(0);
    assertNull(getResult2.annotationType);
    assertNull(getResult2.descriptor);
    assertNull(getResult2.name);
    assertNull(getResult2.attributeNames);
    assertNull(((KeepClassSpecification) getResult).attributeNames);
    assertNull(((KeepClassSpecification) getResult).methodSpecifications);
    assertNull(((KeepClassSpecification) getResult).condition);
    assertEquals(0, ((KeepClassSpecification) getResult).requiredSetAccessFlags);
    assertEquals(0, ((KeepClassSpecification) getResult).requiredUnsetAccessFlags);
    assertEquals(0, getResult2.requiredSetAccessFlags);
    assertEquals(0, getResult2.requiredUnsetAccessFlags);
    assertFalse(((KeepClassSpecification) getResult).allowObfuscation);
    assertFalse(((KeepClassSpecification) getResult).allowOptimization);
    assertFalse(((KeepClassSpecification) getResult).allowShrinking);
    assertFalse(((KeepClassSpecification) getResult).markCodeAttributes);
    assertFalse(((KeepClassSpecification) getResult).markDescriptorClasses);
    assertTrue(((KeepClassSpecification) getResult).markClassMembers);
    assertTrue(((KeepClassSpecification) getResult).markClasses);
    assertTrue(((KeepClassSpecification) getResult).markConditionally);
  }

  /**
   * Method under test:
   * {@link KeepSpecificationElement#appendTo(List, boolean, boolean, boolean)}
   */
  @Test
  void testAppendTo3() {
    // Arrange
    KeepSpecificationElement keepSpecificationElement = new KeepSpecificationElement();
    keepSpecificationElement.addConfiguredMethod(new MemberSpecificationElement());
    ArrayList<Object> keepSpecifications = new ArrayList<>();

    // Act
    keepSpecificationElement.appendTo(keepSpecifications, true, true, true);

    // Assert
    assertEquals(1, keepSpecifications.size());
    Object getResult = keepSpecifications.get(0);
    assertTrue(getResult instanceof KeepClassSpecification);
    assertNull(((KeepClassSpecification) getResult).annotationType);
    assertNull(((KeepClassSpecification) getResult).className);
    assertNull(((KeepClassSpecification) getResult).comments);
    assertNull(((KeepClassSpecification) getResult).extendsAnnotationType);
    assertNull(((KeepClassSpecification) getResult).extendsClassName);
    assertNull(((KeepClassSpecification) getResult).memberComments);
    List<MemberSpecification> memberSpecificationList = ((KeepClassSpecification) getResult).methodSpecifications;
    assertEquals(1, memberSpecificationList.size());
    MemberSpecification getResult2 = memberSpecificationList.get(0);
    assertNull(getResult2.annotationType);
    assertNull(getResult2.descriptor);
    assertNull(getResult2.name);
    assertNull(getResult2.attributeNames);
    assertNull(((KeepClassSpecification) getResult).attributeNames);
    assertNull(((KeepClassSpecification) getResult).fieldSpecifications);
    assertNull(((KeepClassSpecification) getResult).condition);
    assertEquals(0, ((KeepClassSpecification) getResult).requiredSetAccessFlags);
    assertEquals(0, ((KeepClassSpecification) getResult).requiredUnsetAccessFlags);
    assertEquals(0, getResult2.requiredSetAccessFlags);
    assertEquals(0, getResult2.requiredUnsetAccessFlags);
    assertFalse(((KeepClassSpecification) getResult).allowObfuscation);
    assertFalse(((KeepClassSpecification) getResult).allowOptimization);
    assertFalse(((KeepClassSpecification) getResult).allowShrinking);
    assertFalse(((KeepClassSpecification) getResult).markCodeAttributes);
    assertFalse(((KeepClassSpecification) getResult).markDescriptorClasses);
    assertTrue(((KeepClassSpecification) getResult).markClassMembers);
    assertTrue(((KeepClassSpecification) getResult).markClasses);
    assertTrue(((KeepClassSpecification) getResult).markConditionally);
  }

  /**
   * Method under test:
   * {@link KeepSpecificationElement#appendTo(List, boolean, boolean, boolean)}
   */
  @Test
  void testAppendTo4() {
    // Arrange
    KeepSpecificationElement keepSpecificationElement = new KeepSpecificationElement();
    keepSpecificationElement.addConfiguredField(new MemberSpecificationElement());
    keepSpecificationElement.addConfiguredField(new MemberSpecificationElement());
    ArrayList<Object> keepSpecifications = new ArrayList<>();

    // Act
    keepSpecificationElement.appendTo(keepSpecifications, true, true, true);

    // Assert
    assertEquals(1, keepSpecifications.size());
    Object getResult = keepSpecifications.get(0);
    assertTrue(getResult instanceof KeepClassSpecification);
    assertNull(((KeepClassSpecification) getResult).annotationType);
    assertNull(((KeepClassSpecification) getResult).className);
    assertNull(((KeepClassSpecification) getResult).comments);
    assertNull(((KeepClassSpecification) getResult).extendsAnnotationType);
    assertNull(((KeepClassSpecification) getResult).extendsClassName);
    assertNull(((KeepClassSpecification) getResult).memberComments);
    List<MemberSpecification> memberSpecificationList = ((KeepClassSpecification) getResult).fieldSpecifications;
    assertEquals(2, memberSpecificationList.size());
    MemberSpecification getResult2 = memberSpecificationList.get(0);
    assertNull(getResult2.annotationType);
    assertNull(getResult2.descriptor);
    assertNull(getResult2.name);
    assertNull(getResult2.attributeNames);
    assertNull(((KeepClassSpecification) getResult).attributeNames);
    assertNull(((KeepClassSpecification) getResult).methodSpecifications);
    assertNull(((KeepClassSpecification) getResult).condition);
    assertEquals(0, ((KeepClassSpecification) getResult).requiredSetAccessFlags);
    assertEquals(0, ((KeepClassSpecification) getResult).requiredUnsetAccessFlags);
    assertEquals(0, getResult2.requiredSetAccessFlags);
    assertEquals(0, getResult2.requiredUnsetAccessFlags);
    assertFalse(((KeepClassSpecification) getResult).allowObfuscation);
    assertFalse(((KeepClassSpecification) getResult).allowOptimization);
    assertFalse(((KeepClassSpecification) getResult).allowShrinking);
    assertFalse(((KeepClassSpecification) getResult).markCodeAttributes);
    assertFalse(((KeepClassSpecification) getResult).markDescriptorClasses);
    assertTrue(((KeepClassSpecification) getResult).markClassMembers);
    assertTrue(((KeepClassSpecification) getResult).markClasses);
    assertTrue(((KeepClassSpecification) getResult).markConditionally);
  }

  /**
   * Method under test:
   * {@link KeepSpecificationElement#appendTo(List, boolean, boolean, boolean)}
   */
  @Test
  void testAppendTo5() {
    // Arrange
    KeepSpecificationElement keepSpecificationElement = new KeepSpecificationElement();
    keepSpecificationElement.addConfiguredMethod(new MemberSpecificationElement());
    keepSpecificationElement.addConfiguredMethod(new MemberSpecificationElement());
    ArrayList<Object> keepSpecifications = new ArrayList<>();

    // Act
    keepSpecificationElement.appendTo(keepSpecifications, true, true, true);

    // Assert
    assertEquals(1, keepSpecifications.size());
    Object getResult = keepSpecifications.get(0);
    assertTrue(getResult instanceof KeepClassSpecification);
    assertNull(((KeepClassSpecification) getResult).annotationType);
    assertNull(((KeepClassSpecification) getResult).className);
    assertNull(((KeepClassSpecification) getResult).comments);
    assertNull(((KeepClassSpecification) getResult).extendsAnnotationType);
    assertNull(((KeepClassSpecification) getResult).extendsClassName);
    assertNull(((KeepClassSpecification) getResult).memberComments);
    List<MemberSpecification> memberSpecificationList = ((KeepClassSpecification) getResult).methodSpecifications;
    assertEquals(2, memberSpecificationList.size());
    MemberSpecification getResult2 = memberSpecificationList.get(0);
    assertNull(getResult2.annotationType);
    assertNull(getResult2.descriptor);
    assertNull(getResult2.name);
    assertNull(getResult2.attributeNames);
    assertNull(((KeepClassSpecification) getResult).attributeNames);
    assertNull(((KeepClassSpecification) getResult).fieldSpecifications);
    assertNull(((KeepClassSpecification) getResult).condition);
    assertEquals(0, ((KeepClassSpecification) getResult).requiredSetAccessFlags);
    assertEquals(0, ((KeepClassSpecification) getResult).requiredUnsetAccessFlags);
    assertEquals(0, getResult2.requiredSetAccessFlags);
    assertEquals(0, getResult2.requiredUnsetAccessFlags);
    assertFalse(((KeepClassSpecification) getResult).allowObfuscation);
    assertFalse(((KeepClassSpecification) getResult).allowOptimization);
    assertFalse(((KeepClassSpecification) getResult).allowShrinking);
    assertFalse(((KeepClassSpecification) getResult).markCodeAttributes);
    assertFalse(((KeepClassSpecification) getResult).markDescriptorClasses);
    assertTrue(((KeepClassSpecification) getResult).markClassMembers);
    assertTrue(((KeepClassSpecification) getResult).markClasses);
    assertTrue(((KeepClassSpecification) getResult).markConditionally);
  }

  /**
   * Method under test:
   * {@link KeepSpecificationElement#appendTo(List, boolean, boolean, boolean)}
   */
  @Test
  void testAppendTo6() {
    // Arrange
    KeepSpecificationElement keepSpecificationElement = new KeepSpecificationElement();
    keepSpecificationElement.setAnnotation("Annotation");
    keepSpecificationElement.addConfiguredField(new MemberSpecificationElement());
    ArrayList<Object> keepSpecifications = new ArrayList<>();

    // Act
    keepSpecificationElement.appendTo(keepSpecifications, true, true, true);

    // Assert
    assertEquals(1, keepSpecifications.size());
    Object getResult = keepSpecifications.get(0);
    assertTrue(getResult instanceof KeepClassSpecification);
    assertEquals("LAnnotation;", ((KeepClassSpecification) getResult).annotationType);
    assertNull(((KeepClassSpecification) getResult).className);
    assertNull(((KeepClassSpecification) getResult).comments);
    assertNull(((KeepClassSpecification) getResult).extendsAnnotationType);
    assertNull(((KeepClassSpecification) getResult).extendsClassName);
    assertNull(((KeepClassSpecification) getResult).memberComments);
    List<MemberSpecification> memberSpecificationList = ((KeepClassSpecification) getResult).fieldSpecifications;
    assertEquals(1, memberSpecificationList.size());
    MemberSpecification getResult2 = memberSpecificationList.get(0);
    assertNull(getResult2.annotationType);
    assertNull(getResult2.descriptor);
    assertNull(getResult2.name);
    assertNull(getResult2.attributeNames);
    assertNull(((KeepClassSpecification) getResult).attributeNames);
    assertNull(((KeepClassSpecification) getResult).methodSpecifications);
    assertNull(((KeepClassSpecification) getResult).condition);
    assertEquals(0, ((KeepClassSpecification) getResult).requiredSetAccessFlags);
    assertEquals(0, ((KeepClassSpecification) getResult).requiredUnsetAccessFlags);
    assertEquals(0, getResult2.requiredSetAccessFlags);
    assertEquals(0, getResult2.requiredUnsetAccessFlags);
    assertFalse(((KeepClassSpecification) getResult).allowObfuscation);
    assertFalse(((KeepClassSpecification) getResult).allowOptimization);
    assertFalse(((KeepClassSpecification) getResult).allowShrinking);
    assertFalse(((KeepClassSpecification) getResult).markCodeAttributes);
    assertFalse(((KeepClassSpecification) getResult).markDescriptorClasses);
    assertTrue(((KeepClassSpecification) getResult).markClassMembers);
    assertTrue(((KeepClassSpecification) getResult).markClasses);
    assertTrue(((KeepClassSpecification) getResult).markConditionally);
  }

  /**
   * Method under test:
   * {@link KeepSpecificationElement#appendTo(List, boolean, boolean, boolean)}
   */
  @Test
  void testAppendTo7() {
    // Arrange
    KeepSpecificationElement keepSpecificationElement = new KeepSpecificationElement();
    keepSpecificationElement.setName("Name");
    keepSpecificationElement.addConfiguredField(new MemberSpecificationElement());
    ArrayList<Object> keepSpecifications = new ArrayList<>();

    // Act
    keepSpecificationElement.appendTo(keepSpecifications, true, true, true);

    // Assert
    assertEquals(1, keepSpecifications.size());
    Object getResult = keepSpecifications.get(0);
    assertTrue(getResult instanceof KeepClassSpecification);
    assertEquals("Name", ((KeepClassSpecification) getResult).className);
    assertNull(((KeepClassSpecification) getResult).annotationType);
    assertNull(((KeepClassSpecification) getResult).comments);
    assertNull(((KeepClassSpecification) getResult).extendsAnnotationType);
    assertNull(((KeepClassSpecification) getResult).extendsClassName);
    assertNull(((KeepClassSpecification) getResult).memberComments);
    List<MemberSpecification> memberSpecificationList = ((KeepClassSpecification) getResult).fieldSpecifications;
    assertEquals(1, memberSpecificationList.size());
    MemberSpecification getResult2 = memberSpecificationList.get(0);
    assertNull(getResult2.annotationType);
    assertNull(getResult2.descriptor);
    assertNull(getResult2.name);
    assertNull(getResult2.attributeNames);
    assertNull(((KeepClassSpecification) getResult).attributeNames);
    assertNull(((KeepClassSpecification) getResult).methodSpecifications);
    assertNull(((KeepClassSpecification) getResult).condition);
    assertEquals(0, ((KeepClassSpecification) getResult).requiredSetAccessFlags);
    assertEquals(0, ((KeepClassSpecification) getResult).requiredUnsetAccessFlags);
    assertEquals(0, getResult2.requiredSetAccessFlags);
    assertEquals(0, getResult2.requiredUnsetAccessFlags);
    assertFalse(((KeepClassSpecification) getResult).allowObfuscation);
    assertFalse(((KeepClassSpecification) getResult).allowOptimization);
    assertFalse(((KeepClassSpecification) getResult).allowShrinking);
    assertFalse(((KeepClassSpecification) getResult).markCodeAttributes);
    assertFalse(((KeepClassSpecification) getResult).markDescriptorClasses);
    assertTrue(((KeepClassSpecification) getResult).markClassMembers);
    assertTrue(((KeepClassSpecification) getResult).markClasses);
    assertTrue(((KeepClassSpecification) getResult).markConditionally);
  }

  /**
   * Method under test:
   * {@link KeepSpecificationElement#appendTo(List, boolean, boolean, boolean)}
   */
  @Test
  void testAppendTo8() {
    // Arrange
    KeepSpecificationElement keepSpecificationElement = new KeepSpecificationElement();
    keepSpecificationElement.setExtendsannotation("Extends Annotation");
    keepSpecificationElement.addConfiguredField(new MemberSpecificationElement());
    ArrayList<Object> keepSpecifications = new ArrayList<>();

    // Act
    keepSpecificationElement.appendTo(keepSpecifications, true, true, true);

    // Assert
    assertEquals(1, keepSpecifications.size());
    Object getResult = keepSpecifications.get(0);
    assertTrue(getResult instanceof KeepClassSpecification);
    assertEquals("LExtends Annotation;", ((KeepClassSpecification) getResult).extendsAnnotationType);
    assertNull(((KeepClassSpecification) getResult).annotationType);
    assertNull(((KeepClassSpecification) getResult).className);
    assertNull(((KeepClassSpecification) getResult).comments);
    assertNull(((KeepClassSpecification) getResult).extendsClassName);
    assertNull(((KeepClassSpecification) getResult).memberComments);
    List<MemberSpecification> memberSpecificationList = ((KeepClassSpecification) getResult).fieldSpecifications;
    assertEquals(1, memberSpecificationList.size());
    MemberSpecification getResult2 = memberSpecificationList.get(0);
    assertNull(getResult2.annotationType);
    assertNull(getResult2.descriptor);
    assertNull(getResult2.name);
    assertNull(getResult2.attributeNames);
    assertNull(((KeepClassSpecification) getResult).attributeNames);
    assertNull(((KeepClassSpecification) getResult).methodSpecifications);
    assertNull(((KeepClassSpecification) getResult).condition);
    assertEquals(0, ((KeepClassSpecification) getResult).requiredSetAccessFlags);
    assertEquals(0, ((KeepClassSpecification) getResult).requiredUnsetAccessFlags);
    assertEquals(0, getResult2.requiredSetAccessFlags);
    assertEquals(0, getResult2.requiredUnsetAccessFlags);
    assertFalse(((KeepClassSpecification) getResult).allowObfuscation);
    assertFalse(((KeepClassSpecification) getResult).allowOptimization);
    assertFalse(((KeepClassSpecification) getResult).allowShrinking);
    assertFalse(((KeepClassSpecification) getResult).markCodeAttributes);
    assertFalse(((KeepClassSpecification) getResult).markDescriptorClasses);
    assertTrue(((KeepClassSpecification) getResult).markClassMembers);
    assertTrue(((KeepClassSpecification) getResult).markClasses);
    assertTrue(((KeepClassSpecification) getResult).markConditionally);
  }

  /**
   * Method under test:
   * {@link KeepSpecificationElement#appendTo(List, boolean, boolean, boolean)}
   */
  @Test
  void testAppendTo9() {
    // Arrange
    KeepSpecificationElement keepSpecificationElement = new KeepSpecificationElement();
    keepSpecificationElement.setExtends("Extends ");
    keepSpecificationElement.addConfiguredField(new MemberSpecificationElement());
    ArrayList<Object> keepSpecifications = new ArrayList<>();

    // Act
    keepSpecificationElement.appendTo(keepSpecifications, true, true, true);

    // Assert
    assertEquals(1, keepSpecifications.size());
    Object getResult = keepSpecifications.get(0);
    assertTrue(getResult instanceof KeepClassSpecification);
    assertEquals("Extends ", ((KeepClassSpecification) getResult).extendsClassName);
    assertNull(((KeepClassSpecification) getResult).annotationType);
    assertNull(((KeepClassSpecification) getResult).className);
    assertNull(((KeepClassSpecification) getResult).comments);
    assertNull(((KeepClassSpecification) getResult).extendsAnnotationType);
    assertNull(((KeepClassSpecification) getResult).memberComments);
    List<MemberSpecification> memberSpecificationList = ((KeepClassSpecification) getResult).fieldSpecifications;
    assertEquals(1, memberSpecificationList.size());
    MemberSpecification getResult2 = memberSpecificationList.get(0);
    assertNull(getResult2.annotationType);
    assertNull(getResult2.descriptor);
    assertNull(getResult2.name);
    assertNull(getResult2.attributeNames);
    assertNull(((KeepClassSpecification) getResult).attributeNames);
    assertNull(((KeepClassSpecification) getResult).methodSpecifications);
    assertNull(((KeepClassSpecification) getResult).condition);
    assertEquals(0, ((KeepClassSpecification) getResult).requiredSetAccessFlags);
    assertEquals(0, ((KeepClassSpecification) getResult).requiredUnsetAccessFlags);
    assertEquals(0, getResult2.requiredSetAccessFlags);
    assertEquals(0, getResult2.requiredUnsetAccessFlags);
    assertFalse(((KeepClassSpecification) getResult).allowObfuscation);
    assertFalse(((KeepClassSpecification) getResult).allowOptimization);
    assertFalse(((KeepClassSpecification) getResult).allowShrinking);
    assertFalse(((KeepClassSpecification) getResult).markCodeAttributes);
    assertFalse(((KeepClassSpecification) getResult).markDescriptorClasses);
    assertTrue(((KeepClassSpecification) getResult).markClassMembers);
    assertTrue(((KeepClassSpecification) getResult).markClasses);
    assertTrue(((KeepClassSpecification) getResult).markConditionally);
  }

  /**
   * Method under test:
   * {@link KeepSpecificationElement#appendTo(List, boolean, boolean, boolean)}
   */
  @Test
  void testAppendTo10() {
    // Arrange
    KeepSpecificationElement keepSpecificationElement = new KeepSpecificationElement();

    ArrayList<Object> keepSpecifications = new ArrayList<>();
    keepSpecifications.add("42");

    // Act
    keepSpecificationElement.appendTo(keepSpecifications, true, true, true);

    // Assert
    assertEquals(2, keepSpecifications.size());
    Object getResult = keepSpecifications.get(1);
    assertTrue(getResult instanceof KeepClassSpecification);
    assertEquals("42", keepSpecifications.get(0));
    assertNull(((KeepClassSpecification) getResult).annotationType);
    assertNull(((KeepClassSpecification) getResult).className);
    assertNull(((KeepClassSpecification) getResult).comments);
    assertNull(((KeepClassSpecification) getResult).extendsAnnotationType);
    assertNull(((KeepClassSpecification) getResult).extendsClassName);
    assertNull(((KeepClassSpecification) getResult).memberComments);
    assertNull(((KeepClassSpecification) getResult).attributeNames);
    assertNull(((KeepClassSpecification) getResult).fieldSpecifications);
    assertNull(((KeepClassSpecification) getResult).methodSpecifications);
    assertNull(((KeepClassSpecification) getResult).condition);
    assertEquals(0, ((KeepClassSpecification) getResult).requiredSetAccessFlags);
    assertEquals(0, ((KeepClassSpecification) getResult).requiredUnsetAccessFlags);
    assertFalse(((KeepClassSpecification) getResult).allowObfuscation);
    assertFalse(((KeepClassSpecification) getResult).allowOptimization);
    assertFalse(((KeepClassSpecification) getResult).allowShrinking);
    assertFalse(((KeepClassSpecification) getResult).markCodeAttributes);
    assertFalse(((KeepClassSpecification) getResult).markDescriptorClasses);
    assertTrue(((KeepClassSpecification) getResult).markClassMembers);
    assertTrue(((KeepClassSpecification) getResult).markClasses);
    assertTrue(((KeepClassSpecification) getResult).markConditionally);
  }

  /**
   * Method under test:
   * {@link KeepSpecificationElement#appendTo(List, boolean, boolean, boolean)}
   */
  @Test
  void testAppendTo11() {
    // Arrange
    KeepSpecificationElement keepSpecificationElement = new KeepSpecificationElement();
    keepSpecificationElement.setAccess(" ,");
    keepSpecificationElement.addConfiguredField(new MemberSpecificationElement());
    ArrayList<Object> keepSpecifications = new ArrayList<>();

    // Act
    keepSpecificationElement.appendTo(keepSpecifications, true, true, true);

    // Assert
    assertEquals(1, keepSpecifications.size());
    Object getResult = keepSpecifications.get(0);
    assertTrue(getResult instanceof KeepClassSpecification);
    assertNull(((KeepClassSpecification) getResult).annotationType);
    assertNull(((KeepClassSpecification) getResult).className);
    assertNull(((KeepClassSpecification) getResult).comments);
    assertNull(((KeepClassSpecification) getResult).extendsAnnotationType);
    assertNull(((KeepClassSpecification) getResult).extendsClassName);
    assertNull(((KeepClassSpecification) getResult).memberComments);
    List<MemberSpecification> memberSpecificationList = ((KeepClassSpecification) getResult).fieldSpecifications;
    assertEquals(1, memberSpecificationList.size());
    MemberSpecification getResult2 = memberSpecificationList.get(0);
    assertNull(getResult2.annotationType);
    assertNull(getResult2.descriptor);
    assertNull(getResult2.name);
    assertNull(getResult2.attributeNames);
    assertNull(((KeepClassSpecification) getResult).attributeNames);
    assertNull(((KeepClassSpecification) getResult).methodSpecifications);
    assertNull(((KeepClassSpecification) getResult).condition);
    assertEquals(0, ((KeepClassSpecification) getResult).requiredSetAccessFlags);
    assertEquals(0, ((KeepClassSpecification) getResult).requiredUnsetAccessFlags);
    assertEquals(0, getResult2.requiredSetAccessFlags);
    assertEquals(0, getResult2.requiredUnsetAccessFlags);
    assertFalse(((KeepClassSpecification) getResult).allowObfuscation);
    assertFalse(((KeepClassSpecification) getResult).allowOptimization);
    assertFalse(((KeepClassSpecification) getResult).allowShrinking);
    assertFalse(((KeepClassSpecification) getResult).markCodeAttributes);
    assertFalse(((KeepClassSpecification) getResult).markDescriptorClasses);
    assertTrue(((KeepClassSpecification) getResult).markClassMembers);
    assertTrue(((KeepClassSpecification) getResult).markClasses);
    assertTrue(((KeepClassSpecification) getResult).markConditionally);
  }

  /**
   * Method under test:
   * {@link KeepSpecificationElement#appendTo(List, boolean, boolean, boolean)}
   */
  @Test
  void testAppendTo12() {
    // Arrange
    KeepSpecificationElement keepSpecificationElement = new KeepSpecificationElement();
    keepSpecificationElement.setAccess("public");
    keepSpecificationElement.addConfiguredField(new MemberSpecificationElement());
    ArrayList<Object> keepSpecifications = new ArrayList<>();

    // Act
    keepSpecificationElement.appendTo(keepSpecifications, true, true, true);

    // Assert
    assertEquals(1, keepSpecifications.size());
    Object getResult = keepSpecifications.get(0);
    assertTrue(getResult instanceof KeepClassSpecification);
    assertNull(((KeepClassSpecification) getResult).annotationType);
    assertNull(((KeepClassSpecification) getResult).className);
    assertNull(((KeepClassSpecification) getResult).comments);
    assertNull(((KeepClassSpecification) getResult).extendsAnnotationType);
    assertNull(((KeepClassSpecification) getResult).extendsClassName);
    assertNull(((KeepClassSpecification) getResult).memberComments);
    List<MemberSpecification> memberSpecificationList = ((KeepClassSpecification) getResult).fieldSpecifications;
    assertEquals(1, memberSpecificationList.size());
    MemberSpecification getResult2 = memberSpecificationList.get(0);
    assertNull(getResult2.annotationType);
    assertNull(getResult2.descriptor);
    assertNull(getResult2.name);
    assertNull(getResult2.attributeNames);
    assertNull(((KeepClassSpecification) getResult).attributeNames);
    assertNull(((KeepClassSpecification) getResult).methodSpecifications);
    assertNull(((KeepClassSpecification) getResult).condition);
    assertEquals(0, ((KeepClassSpecification) getResult).requiredUnsetAccessFlags);
    assertEquals(0, getResult2.requiredSetAccessFlags);
    assertEquals(0, getResult2.requiredUnsetAccessFlags);
    assertEquals(1, ((KeepClassSpecification) getResult).requiredSetAccessFlags);
    assertFalse(((KeepClassSpecification) getResult).allowObfuscation);
    assertFalse(((KeepClassSpecification) getResult).allowOptimization);
    assertFalse(((KeepClassSpecification) getResult).allowShrinking);
    assertFalse(((KeepClassSpecification) getResult).markCodeAttributes);
    assertFalse(((KeepClassSpecification) getResult).markDescriptorClasses);
    assertTrue(((KeepClassSpecification) getResult).markClassMembers);
    assertTrue(((KeepClassSpecification) getResult).markClasses);
    assertTrue(((KeepClassSpecification) getResult).markConditionally);
  }

  /**
   * Method under test:
   * {@link KeepSpecificationElement#appendTo(List, boolean, boolean, boolean)}
   */
  @Test
  void testAppendTo13() {
    // Arrange
    KeepSpecificationElement keepSpecificationElement = new KeepSpecificationElement();
    keepSpecificationElement.setAccess("final");
    keepSpecificationElement.addConfiguredField(new MemberSpecificationElement());
    ArrayList<Object> keepSpecifications = new ArrayList<>();

    // Act
    keepSpecificationElement.appendTo(keepSpecifications, true, true, true);

    // Assert
    assertEquals(1, keepSpecifications.size());
    Object getResult = keepSpecifications.get(0);
    assertTrue(getResult instanceof KeepClassSpecification);
    assertNull(((KeepClassSpecification) getResult).annotationType);
    assertNull(((KeepClassSpecification) getResult).className);
    assertNull(((KeepClassSpecification) getResult).comments);
    assertNull(((KeepClassSpecification) getResult).extendsAnnotationType);
    assertNull(((KeepClassSpecification) getResult).extendsClassName);
    assertNull(((KeepClassSpecification) getResult).memberComments);
    List<MemberSpecification> memberSpecificationList = ((KeepClassSpecification) getResult).fieldSpecifications;
    assertEquals(1, memberSpecificationList.size());
    MemberSpecification getResult2 = memberSpecificationList.get(0);
    assertNull(getResult2.annotationType);
    assertNull(getResult2.descriptor);
    assertNull(getResult2.name);
    assertNull(getResult2.attributeNames);
    assertNull(((KeepClassSpecification) getResult).attributeNames);
    assertNull(((KeepClassSpecification) getResult).methodSpecifications);
    assertNull(((KeepClassSpecification) getResult).condition);
    assertEquals(0, ((KeepClassSpecification) getResult).requiredUnsetAccessFlags);
    assertEquals(0, getResult2.requiredSetAccessFlags);
    assertEquals(0, getResult2.requiredUnsetAccessFlags);
    assertFalse(((KeepClassSpecification) getResult).allowObfuscation);
    assertFalse(((KeepClassSpecification) getResult).allowOptimization);
    assertFalse(((KeepClassSpecification) getResult).allowShrinking);
    assertFalse(((KeepClassSpecification) getResult).markCodeAttributes);
    assertFalse(((KeepClassSpecification) getResult).markDescriptorClasses);
    assertTrue(((KeepClassSpecification) getResult).markClassMembers);
    assertTrue(((KeepClassSpecification) getResult).markClasses);
    assertTrue(((KeepClassSpecification) getResult).markConditionally);
    assertEquals(Short.SIZE, ((KeepClassSpecification) getResult).requiredSetAccessFlags);
  }

  /**
   * Method under test:
   * {@link KeepSpecificationElement#appendTo(List, boolean, boolean, boolean)}
   */
  @Test
  void testAppendTo14() {
    // Arrange
    KeepSpecificationElement keepSpecificationElement = new KeepSpecificationElement();
    keepSpecificationElement.setAccess("abstract");
    keepSpecificationElement.addConfiguredField(new MemberSpecificationElement());
    ArrayList<Object> keepSpecifications = new ArrayList<>();

    // Act
    keepSpecificationElement.appendTo(keepSpecifications, true, true, true);

    // Assert
    assertEquals(1, keepSpecifications.size());
    Object getResult = keepSpecifications.get(0);
    assertTrue(getResult instanceof KeepClassSpecification);
    assertNull(((KeepClassSpecification) getResult).annotationType);
    assertNull(((KeepClassSpecification) getResult).className);
    assertNull(((KeepClassSpecification) getResult).comments);
    assertNull(((KeepClassSpecification) getResult).extendsAnnotationType);
    assertNull(((KeepClassSpecification) getResult).extendsClassName);
    assertNull(((KeepClassSpecification) getResult).memberComments);
    List<MemberSpecification> memberSpecificationList = ((KeepClassSpecification) getResult).fieldSpecifications;
    assertEquals(1, memberSpecificationList.size());
    MemberSpecification getResult2 = memberSpecificationList.get(0);
    assertNull(getResult2.annotationType);
    assertNull(getResult2.descriptor);
    assertNull(getResult2.name);
    assertNull(getResult2.attributeNames);
    assertNull(((KeepClassSpecification) getResult).attributeNames);
    assertNull(((KeepClassSpecification) getResult).methodSpecifications);
    assertNull(((KeepClassSpecification) getResult).condition);
    assertEquals(0, ((KeepClassSpecification) getResult).requiredUnsetAccessFlags);
    assertEquals(0, getResult2.requiredSetAccessFlags);
    assertEquals(0, getResult2.requiredUnsetAccessFlags);
    assertEquals(1024, ((KeepClassSpecification) getResult).requiredSetAccessFlags);
    assertFalse(((KeepClassSpecification) getResult).allowObfuscation);
    assertFalse(((KeepClassSpecification) getResult).allowOptimization);
    assertFalse(((KeepClassSpecification) getResult).allowShrinking);
    assertFalse(((KeepClassSpecification) getResult).markCodeAttributes);
    assertFalse(((KeepClassSpecification) getResult).markDescriptorClasses);
    assertTrue(((KeepClassSpecification) getResult).markClassMembers);
    assertTrue(((KeepClassSpecification) getResult).markClasses);
    assertTrue(((KeepClassSpecification) getResult).markConditionally);
  }

  /**
   * Method under test:
   * {@link KeepSpecificationElement#appendTo(List, boolean, boolean, boolean)}
   */
  @Test
  void testAppendTo15() {
    // Arrange
    KeepSpecificationElement keepSpecificationElement = new KeepSpecificationElement();
    keepSpecificationElement.setAccess("synthetic");
    keepSpecificationElement.addConfiguredField(new MemberSpecificationElement());
    ArrayList<Object> keepSpecifications = new ArrayList<>();

    // Act
    keepSpecificationElement.appendTo(keepSpecifications, true, true, true);

    // Assert
    assertEquals(1, keepSpecifications.size());
    Object getResult = keepSpecifications.get(0);
    assertTrue(getResult instanceof KeepClassSpecification);
    assertNull(((KeepClassSpecification) getResult).annotationType);
    assertNull(((KeepClassSpecification) getResult).className);
    assertNull(((KeepClassSpecification) getResult).comments);
    assertNull(((KeepClassSpecification) getResult).extendsAnnotationType);
    assertNull(((KeepClassSpecification) getResult).extendsClassName);
    assertNull(((KeepClassSpecification) getResult).memberComments);
    List<MemberSpecification> memberSpecificationList = ((KeepClassSpecification) getResult).fieldSpecifications;
    assertEquals(1, memberSpecificationList.size());
    MemberSpecification getResult2 = memberSpecificationList.get(0);
    assertNull(getResult2.annotationType);
    assertNull(getResult2.descriptor);
    assertNull(getResult2.name);
    assertNull(getResult2.attributeNames);
    assertNull(((KeepClassSpecification) getResult).attributeNames);
    assertNull(((KeepClassSpecification) getResult).methodSpecifications);
    assertNull(((KeepClassSpecification) getResult).condition);
    assertEquals(0, ((KeepClassSpecification) getResult).requiredUnsetAccessFlags);
    assertEquals(0, getResult2.requiredSetAccessFlags);
    assertEquals(0, getResult2.requiredUnsetAccessFlags);
    assertEquals(4096, ((KeepClassSpecification) getResult).requiredSetAccessFlags);
    assertFalse(((KeepClassSpecification) getResult).allowObfuscation);
    assertFalse(((KeepClassSpecification) getResult).allowOptimization);
    assertFalse(((KeepClassSpecification) getResult).allowShrinking);
    assertFalse(((KeepClassSpecification) getResult).markCodeAttributes);
    assertFalse(((KeepClassSpecification) getResult).markDescriptorClasses);
    assertTrue(((KeepClassSpecification) getResult).markClassMembers);
    assertTrue(((KeepClassSpecification) getResult).markClasses);
    assertTrue(((KeepClassSpecification) getResult).markConditionally);
  }

  /**
   * Method under test:
   * {@link KeepSpecificationElement#appendTo(List, boolean, boolean, boolean)}
   */
  @Test
  void testAppendTo16() {
    // Arrange
    KeepSpecificationElement keepSpecificationElement = new KeepSpecificationElement();
    keepSpecificationElement.setAccess("@");
    keepSpecificationElement.addConfiguredField(new MemberSpecificationElement());
    ArrayList<Object> keepSpecifications = new ArrayList<>();

    // Act
    keepSpecificationElement.appendTo(keepSpecifications, true, true, true);

    // Assert
    assertEquals(1, keepSpecifications.size());
    Object getResult = keepSpecifications.get(0);
    assertTrue(getResult instanceof KeepClassSpecification);
    assertNull(((KeepClassSpecification) getResult).annotationType);
    assertNull(((KeepClassSpecification) getResult).className);
    assertNull(((KeepClassSpecification) getResult).comments);
    assertNull(((KeepClassSpecification) getResult).extendsAnnotationType);
    assertNull(((KeepClassSpecification) getResult).extendsClassName);
    assertNull(((KeepClassSpecification) getResult).memberComments);
    List<MemberSpecification> memberSpecificationList = ((KeepClassSpecification) getResult).fieldSpecifications;
    assertEquals(1, memberSpecificationList.size());
    MemberSpecification getResult2 = memberSpecificationList.get(0);
    assertNull(getResult2.annotationType);
    assertNull(getResult2.descriptor);
    assertNull(getResult2.name);
    assertNull(getResult2.attributeNames);
    assertNull(((KeepClassSpecification) getResult).attributeNames);
    assertNull(((KeepClassSpecification) getResult).methodSpecifications);
    assertNull(((KeepClassSpecification) getResult).condition);
    assertEquals(0, ((KeepClassSpecification) getResult).requiredUnsetAccessFlags);
    assertEquals(0, getResult2.requiredSetAccessFlags);
    assertEquals(0, getResult2.requiredUnsetAccessFlags);
    assertEquals(8192, ((KeepClassSpecification) getResult).requiredSetAccessFlags);
    assertFalse(((KeepClassSpecification) getResult).allowObfuscation);
    assertFalse(((KeepClassSpecification) getResult).allowOptimization);
    assertFalse(((KeepClassSpecification) getResult).allowShrinking);
    assertFalse(((KeepClassSpecification) getResult).markCodeAttributes);
    assertFalse(((KeepClassSpecification) getResult).markDescriptorClasses);
    assertTrue(((KeepClassSpecification) getResult).markClassMembers);
    assertTrue(((KeepClassSpecification) getResult).markClasses);
    assertTrue(((KeepClassSpecification) getResult).markConditionally);
  }

  /**
   * Method under test:
   * {@link KeepSpecificationElement#appendTo(List, boolean, boolean, boolean)}
   */
  @Test
  void testAppendTo17() {
    // Arrange
    KeepSpecificationElement keepSpecificationElement = new KeepSpecificationElement();
    keepSpecificationElement.setType("class");
    keepSpecificationElement.addConfiguredField(new MemberSpecificationElement());
    ArrayList<Object> keepSpecifications = new ArrayList<>();

    // Act
    keepSpecificationElement.appendTo(keepSpecifications, true, true, true);

    // Assert
    assertEquals(1, keepSpecifications.size());
    Object getResult = keepSpecifications.get(0);
    assertTrue(getResult instanceof KeepClassSpecification);
    assertNull(((KeepClassSpecification) getResult).annotationType);
    assertNull(((KeepClassSpecification) getResult).className);
    assertNull(((KeepClassSpecification) getResult).comments);
    assertNull(((KeepClassSpecification) getResult).extendsAnnotationType);
    assertNull(((KeepClassSpecification) getResult).extendsClassName);
    assertNull(((KeepClassSpecification) getResult).memberComments);
    List<MemberSpecification> memberSpecificationList = ((KeepClassSpecification) getResult).fieldSpecifications;
    assertEquals(1, memberSpecificationList.size());
    MemberSpecification getResult2 = memberSpecificationList.get(0);
    assertNull(getResult2.annotationType);
    assertNull(getResult2.descriptor);
    assertNull(getResult2.name);
    assertNull(getResult2.attributeNames);
    assertNull(((KeepClassSpecification) getResult).attributeNames);
    assertNull(((KeepClassSpecification) getResult).methodSpecifications);
    assertNull(((KeepClassSpecification) getResult).condition);
    assertEquals(0, ((KeepClassSpecification) getResult).requiredSetAccessFlags);
    assertEquals(0, ((KeepClassSpecification) getResult).requiredUnsetAccessFlags);
    assertEquals(0, getResult2.requiredSetAccessFlags);
    assertEquals(0, getResult2.requiredUnsetAccessFlags);
    assertFalse(((KeepClassSpecification) getResult).allowObfuscation);
    assertFalse(((KeepClassSpecification) getResult).allowOptimization);
    assertFalse(((KeepClassSpecification) getResult).allowShrinking);
    assertFalse(((KeepClassSpecification) getResult).markCodeAttributes);
    assertFalse(((KeepClassSpecification) getResult).markDescriptorClasses);
    assertTrue(((KeepClassSpecification) getResult).markClassMembers);
    assertTrue(((KeepClassSpecification) getResult).markClasses);
    assertTrue(((KeepClassSpecification) getResult).markConditionally);
  }

  /**
   * Method under test:
   * {@link KeepSpecificationElement#appendTo(List, boolean, boolean, boolean)}
   */
  @Test
  void testAppendTo18() {
    // Arrange
    KeepSpecificationElement keepSpecificationElement = new KeepSpecificationElement();
    keepSpecificationElement.setType("interface");
    keepSpecificationElement.addConfiguredField(new MemberSpecificationElement());
    ArrayList<Object> keepSpecifications = new ArrayList<>();

    // Act
    keepSpecificationElement.appendTo(keepSpecifications, true, true, true);

    // Assert
    assertEquals(1, keepSpecifications.size());
    Object getResult = keepSpecifications.get(0);
    assertTrue(getResult instanceof KeepClassSpecification);
    assertNull(((KeepClassSpecification) getResult).annotationType);
    assertNull(((KeepClassSpecification) getResult).className);
    assertNull(((KeepClassSpecification) getResult).comments);
    assertNull(((KeepClassSpecification) getResult).extendsAnnotationType);
    assertNull(((KeepClassSpecification) getResult).extendsClassName);
    assertNull(((KeepClassSpecification) getResult).memberComments);
    List<MemberSpecification> memberSpecificationList = ((KeepClassSpecification) getResult).fieldSpecifications;
    assertEquals(1, memberSpecificationList.size());
    MemberSpecification getResult2 = memberSpecificationList.get(0);
    assertNull(getResult2.annotationType);
    assertNull(getResult2.descriptor);
    assertNull(getResult2.name);
    assertNull(getResult2.attributeNames);
    assertNull(((KeepClassSpecification) getResult).attributeNames);
    assertNull(((KeepClassSpecification) getResult).methodSpecifications);
    assertNull(((KeepClassSpecification) getResult).condition);
    assertEquals(0, ((KeepClassSpecification) getResult).requiredUnsetAccessFlags);
    assertEquals(0, getResult2.requiredSetAccessFlags);
    assertEquals(0, getResult2.requiredUnsetAccessFlags);
    assertEquals(512, ((KeepClassSpecification) getResult).requiredSetAccessFlags);
    assertFalse(((KeepClassSpecification) getResult).allowObfuscation);
    assertFalse(((KeepClassSpecification) getResult).allowOptimization);
    assertFalse(((KeepClassSpecification) getResult).allowShrinking);
    assertFalse(((KeepClassSpecification) getResult).markCodeAttributes);
    assertFalse(((KeepClassSpecification) getResult).markDescriptorClasses);
    assertTrue(((KeepClassSpecification) getResult).markClassMembers);
    assertTrue(((KeepClassSpecification) getResult).markClasses);
    assertTrue(((KeepClassSpecification) getResult).markConditionally);
  }

  /**
   * Method under test:
   * {@link KeepSpecificationElement#appendTo(List, boolean, boolean, boolean)}
   */
  @Test
  void testAppendTo19() {
    // Arrange
    KeepSpecificationElement keepSpecificationElement = new KeepSpecificationElement();
    keepSpecificationElement.setType("!interface");
    keepSpecificationElement.addConfiguredField(new MemberSpecificationElement());
    ArrayList<Object> keepSpecifications = new ArrayList<>();

    // Act
    keepSpecificationElement.appendTo(keepSpecifications, true, true, true);

    // Assert
    assertEquals(1, keepSpecifications.size());
    Object getResult = keepSpecifications.get(0);
    assertTrue(getResult instanceof KeepClassSpecification);
    assertNull(((KeepClassSpecification) getResult).annotationType);
    assertNull(((KeepClassSpecification) getResult).className);
    assertNull(((KeepClassSpecification) getResult).comments);
    assertNull(((KeepClassSpecification) getResult).extendsAnnotationType);
    assertNull(((KeepClassSpecification) getResult).extendsClassName);
    assertNull(((KeepClassSpecification) getResult).memberComments);
    List<MemberSpecification> memberSpecificationList = ((KeepClassSpecification) getResult).fieldSpecifications;
    assertEquals(1, memberSpecificationList.size());
    MemberSpecification getResult2 = memberSpecificationList.get(0);
    assertNull(getResult2.annotationType);
    assertNull(getResult2.descriptor);
    assertNull(getResult2.name);
    assertNull(getResult2.attributeNames);
    assertNull(((KeepClassSpecification) getResult).attributeNames);
    assertNull(((KeepClassSpecification) getResult).methodSpecifications);
    assertNull(((KeepClassSpecification) getResult).condition);
    assertEquals(0, ((KeepClassSpecification) getResult).requiredSetAccessFlags);
    assertEquals(0, getResult2.requiredSetAccessFlags);
    assertEquals(0, getResult2.requiredUnsetAccessFlags);
    assertEquals(512, ((KeepClassSpecification) getResult).requiredUnsetAccessFlags);
    assertFalse(((KeepClassSpecification) getResult).allowObfuscation);
    assertFalse(((KeepClassSpecification) getResult).allowOptimization);
    assertFalse(((KeepClassSpecification) getResult).allowShrinking);
    assertFalse(((KeepClassSpecification) getResult).markCodeAttributes);
    assertFalse(((KeepClassSpecification) getResult).markDescriptorClasses);
    assertTrue(((KeepClassSpecification) getResult).markClassMembers);
    assertTrue(((KeepClassSpecification) getResult).markClasses);
    assertTrue(((KeepClassSpecification) getResult).markConditionally);
  }

  /**
   * Method under test:
   * {@link KeepSpecificationElement#appendTo(List, boolean, boolean, boolean)}
   */
  @Test
  void testAppendTo20() {
    // Arrange
    KeepSpecificationElement keepSpecificationElement = new KeepSpecificationElement();
    keepSpecificationElement.setType("enum");
    keepSpecificationElement.addConfiguredField(new MemberSpecificationElement());
    ArrayList<Object> keepSpecifications = new ArrayList<>();

    // Act
    keepSpecificationElement.appendTo(keepSpecifications, true, true, true);

    // Assert
    assertEquals(1, keepSpecifications.size());
    Object getResult = keepSpecifications.get(0);
    assertTrue(getResult instanceof KeepClassSpecification);
    assertNull(((KeepClassSpecification) getResult).annotationType);
    assertNull(((KeepClassSpecification) getResult).className);
    assertNull(((KeepClassSpecification) getResult).comments);
    assertNull(((KeepClassSpecification) getResult).extendsAnnotationType);
    assertNull(((KeepClassSpecification) getResult).extendsClassName);
    assertNull(((KeepClassSpecification) getResult).memberComments);
    List<MemberSpecification> memberSpecificationList = ((KeepClassSpecification) getResult).fieldSpecifications;
    assertEquals(1, memberSpecificationList.size());
    MemberSpecification getResult2 = memberSpecificationList.get(0);
    assertNull(getResult2.annotationType);
    assertNull(getResult2.descriptor);
    assertNull(getResult2.name);
    assertNull(getResult2.attributeNames);
    assertNull(((KeepClassSpecification) getResult).attributeNames);
    assertNull(((KeepClassSpecification) getResult).methodSpecifications);
    assertNull(((KeepClassSpecification) getResult).condition);
    assertEquals(0, ((KeepClassSpecification) getResult).requiredUnsetAccessFlags);
    assertEquals(0, getResult2.requiredSetAccessFlags);
    assertEquals(0, getResult2.requiredUnsetAccessFlags);
    assertEquals(16384, ((KeepClassSpecification) getResult).requiredSetAccessFlags);
    assertFalse(((KeepClassSpecification) getResult).allowObfuscation);
    assertFalse(((KeepClassSpecification) getResult).allowOptimization);
    assertFalse(((KeepClassSpecification) getResult).allowShrinking);
    assertFalse(((KeepClassSpecification) getResult).markCodeAttributes);
    assertFalse(((KeepClassSpecification) getResult).markDescriptorClasses);
    assertTrue(((KeepClassSpecification) getResult).markClassMembers);
    assertTrue(((KeepClassSpecification) getResult).markClasses);
    assertTrue(((KeepClassSpecification) getResult).markConditionally);
  }

  /**
   * Method under test:
   * {@link KeepSpecificationElement#appendTo(List, boolean, boolean, boolean)}
   */
  @Test
  void testAppendTo21() {
    // Arrange
    KeepSpecificationElement keepSpecificationElement = new KeepSpecificationElement();
    keepSpecificationElement.setType("!enum");
    keepSpecificationElement.addConfiguredField(new MemberSpecificationElement());
    ArrayList<Object> keepSpecifications = new ArrayList<>();

    // Act
    keepSpecificationElement.appendTo(keepSpecifications, true, true, true);

    // Assert
    assertEquals(1, keepSpecifications.size());
    Object getResult = keepSpecifications.get(0);
    assertTrue(getResult instanceof KeepClassSpecification);
    assertNull(((KeepClassSpecification) getResult).annotationType);
    assertNull(((KeepClassSpecification) getResult).className);
    assertNull(((KeepClassSpecification) getResult).comments);
    assertNull(((KeepClassSpecification) getResult).extendsAnnotationType);
    assertNull(((KeepClassSpecification) getResult).extendsClassName);
    assertNull(((KeepClassSpecification) getResult).memberComments);
    List<MemberSpecification> memberSpecificationList = ((KeepClassSpecification) getResult).fieldSpecifications;
    assertEquals(1, memberSpecificationList.size());
    MemberSpecification getResult2 = memberSpecificationList.get(0);
    assertNull(getResult2.annotationType);
    assertNull(getResult2.descriptor);
    assertNull(getResult2.name);
    assertNull(getResult2.attributeNames);
    assertNull(((KeepClassSpecification) getResult).attributeNames);
    assertNull(((KeepClassSpecification) getResult).methodSpecifications);
    assertNull(((KeepClassSpecification) getResult).condition);
    assertEquals(0, ((KeepClassSpecification) getResult).requiredSetAccessFlags);
    assertEquals(0, getResult2.requiredSetAccessFlags);
    assertEquals(0, getResult2.requiredUnsetAccessFlags);
    assertEquals(16384, ((KeepClassSpecification) getResult).requiredUnsetAccessFlags);
    assertFalse(((KeepClassSpecification) getResult).allowObfuscation);
    assertFalse(((KeepClassSpecification) getResult).allowOptimization);
    assertFalse(((KeepClassSpecification) getResult).allowShrinking);
    assertFalse(((KeepClassSpecification) getResult).markCodeAttributes);
    assertFalse(((KeepClassSpecification) getResult).markDescriptorClasses);
    assertTrue(((KeepClassSpecification) getResult).markClassMembers);
    assertTrue(((KeepClassSpecification) getResult).markClasses);
    assertTrue(((KeepClassSpecification) getResult).markConditionally);
  }

  /**
   * Method under test:
   * {@link KeepSpecificationElement#appendTo(List, boolean, boolean, boolean)}
   */
  @Test
  void testAppendTo22() {
    // Arrange
    KeepSpecificationElement keepSpecificationElement = new KeepSpecificationElement();
    keepSpecificationElement.setName("*");
    keepSpecificationElement.addConfiguredField(new MemberSpecificationElement());
    ArrayList<Object> keepSpecifications = new ArrayList<>();

    // Act
    keepSpecificationElement.appendTo(keepSpecifications, true, true, true);

    // Assert
    assertEquals(1, keepSpecifications.size());
    Object getResult = keepSpecifications.get(0);
    assertTrue(getResult instanceof KeepClassSpecification);
    assertNull(((KeepClassSpecification) getResult).annotationType);
    assertNull(((KeepClassSpecification) getResult).className);
    assertNull(((KeepClassSpecification) getResult).comments);
    assertNull(((KeepClassSpecification) getResult).extendsAnnotationType);
    assertNull(((KeepClassSpecification) getResult).extendsClassName);
    assertNull(((KeepClassSpecification) getResult).memberComments);
    List<MemberSpecification> memberSpecificationList = ((KeepClassSpecification) getResult).fieldSpecifications;
    assertEquals(1, memberSpecificationList.size());
    MemberSpecification getResult2 = memberSpecificationList.get(0);
    assertNull(getResult2.annotationType);
    assertNull(getResult2.descriptor);
    assertNull(getResult2.name);
    assertNull(getResult2.attributeNames);
    assertNull(((KeepClassSpecification) getResult).attributeNames);
    assertNull(((KeepClassSpecification) getResult).methodSpecifications);
    assertNull(((KeepClassSpecification) getResult).condition);
    assertEquals(0, ((KeepClassSpecification) getResult).requiredSetAccessFlags);
    assertEquals(0, ((KeepClassSpecification) getResult).requiredUnsetAccessFlags);
    assertEquals(0, getResult2.requiredSetAccessFlags);
    assertEquals(0, getResult2.requiredUnsetAccessFlags);
    assertFalse(((KeepClassSpecification) getResult).allowObfuscation);
    assertFalse(((KeepClassSpecification) getResult).allowOptimization);
    assertFalse(((KeepClassSpecification) getResult).allowShrinking);
    assertFalse(((KeepClassSpecification) getResult).markCodeAttributes);
    assertFalse(((KeepClassSpecification) getResult).markDescriptorClasses);
    assertTrue(((KeepClassSpecification) getResult).markClassMembers);
    assertTrue(((KeepClassSpecification) getResult).markClasses);
    assertTrue(((KeepClassSpecification) getResult).markConditionally);
  }

  /**
   * Method under test: default or parameterless constructor of
   * {@link KeepSpecificationElement}
   */
  @Test
  void testNewKeepSpecificationElement() {
    // Arrange and Act
    KeepSpecificationElement actualKeepSpecificationElement = new KeepSpecificationElement();

    // Assert
    Location location = actualKeepSpecificationElement.getLocation();
    assertNull(location.getFileName());
    assertNull(actualKeepSpecificationElement.getDescription());
    assertNull(actualKeepSpecificationElement.getProject());
    assertNull(actualKeepSpecificationElement.getRefid());
    assertEquals(0, location.getColumnNumber());
    assertEquals(0, location.getLineNumber());
    assertFalse(actualKeepSpecificationElement.isReference());
  }
}
