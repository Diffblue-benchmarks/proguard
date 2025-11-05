package proguard.ant;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import java.util.ArrayList;
import java.util.List;
import org.apache.tools.ant.BuildException;
import org.apache.tools.ant.Location;
import org.junit.jupiter.api.Test;
import proguard.MemberSpecification;

class MemberSpecificationElementDiffblueTest {
  /**
   * Method under test:
   * {@link MemberSpecificationElement#appendTo(List, boolean, boolean)}
   */
  @Test
  void testAppendTo() {
    // Arrange
    MemberSpecificationElement memberSpecificationElement = new MemberSpecificationElement();
    ArrayList<Object> memberSpecifications = new ArrayList<>();

    // Act
    memberSpecificationElement.appendTo(memberSpecifications, true, true);

    // Assert
    assertEquals(1, memberSpecifications.size());
    Object getResult = memberSpecifications.get(0);
    assertTrue(getResult instanceof MemberSpecification);
    assertEquals("<init>", ((MemberSpecification) getResult).name);
    assertNull(((MemberSpecification) getResult).annotationType);
    assertNull(((MemberSpecification) getResult).descriptor);
    assertNull(((MemberSpecification) getResult).attributeNames);
    assertEquals(0, ((MemberSpecification) getResult).requiredSetAccessFlags);
    assertEquals(0, ((MemberSpecification) getResult).requiredUnsetAccessFlags);
  }

  /**
   * Method under test:
   * {@link MemberSpecificationElement#appendTo(List, boolean, boolean)}
   */
  @Test
  void testAppendTo2() {
    // Arrange
    MemberSpecificationElement memberSpecificationElement = new MemberSpecificationElement();

    ArrayList<Object> memberSpecifications = new ArrayList<>();
    memberSpecifications.add("42");

    // Act
    memberSpecificationElement.appendTo(memberSpecifications, true, true);

    // Assert
    assertEquals(2, memberSpecifications.size());
    Object getResult = memberSpecifications.get(1);
    assertTrue(getResult instanceof MemberSpecification);
    assertEquals("42", memberSpecifications.get(0));
    assertEquals("<init>", ((MemberSpecification) getResult).name);
    assertNull(((MemberSpecification) getResult).annotationType);
    assertNull(((MemberSpecification) getResult).descriptor);
    assertNull(((MemberSpecification) getResult).attributeNames);
    assertEquals(0, ((MemberSpecification) getResult).requiredSetAccessFlags);
    assertEquals(0, ((MemberSpecification) getResult).requiredUnsetAccessFlags);
  }

  /**
   * Method under test:
   * {@link MemberSpecificationElement#appendTo(List, boolean, boolean)}
   */
  @Test
  void testAppendTo3() {
    // Arrange
    MemberSpecificationElement memberSpecificationElement = new MemberSpecificationElement();
    ArrayList<Object> memberSpecifications = new ArrayList<>();

    // Act
    memberSpecificationElement.appendTo(memberSpecifications, false, true);

    // Assert
    assertEquals(1, memberSpecifications.size());
    Object getResult = memberSpecifications.get(0);
    assertTrue(getResult instanceof MemberSpecification);
    assertNull(((MemberSpecification) getResult).annotationType);
    assertNull(((MemberSpecification) getResult).descriptor);
    assertNull(((MemberSpecification) getResult).name);
    assertNull(((MemberSpecification) getResult).attributeNames);
    assertEquals(0, ((MemberSpecification) getResult).requiredSetAccessFlags);
    assertEquals(0, ((MemberSpecification) getResult).requiredUnsetAccessFlags);
  }

  /**
   * Method under test:
   * {@link MemberSpecificationElement#appendTo(List, boolean, boolean)}
   */
  @Test
  void testAppendTo4() {
    // Arrange
    MemberSpecificationElement memberSpecificationElement = new MemberSpecificationElement();
    ArrayList<Object> memberSpecifications = new ArrayList<>();

    // Act
    memberSpecificationElement.appendTo(memberSpecifications, true, false);

    // Assert
    assertEquals(1, memberSpecifications.size());
    Object getResult = memberSpecifications.get(0);
    assertTrue(getResult instanceof MemberSpecification);
    assertNull(((MemberSpecification) getResult).annotationType);
    assertNull(((MemberSpecification) getResult).descriptor);
    assertNull(((MemberSpecification) getResult).name);
    assertNull(((MemberSpecification) getResult).attributeNames);
    assertEquals(0, ((MemberSpecification) getResult).requiredSetAccessFlags);
    assertEquals(0, ((MemberSpecification) getResult).requiredUnsetAccessFlags);
  }

  /**
   * Method under test:
   * {@link MemberSpecificationElement#appendTo(List, boolean, boolean)}
   */
  @Test
  void testAppendTo5() {
    // Arrange
    MemberSpecificationElement memberSpecificationElement = new MemberSpecificationElement();
    memberSpecificationElement.setAccess("<init>");

    // Act and Assert
    assertThrows(BuildException.class, () -> memberSpecificationElement.appendTo(new ArrayList<>(), true, true));
  }

  /**
   * Method under test:
   * {@link MemberSpecificationElement#appendTo(List, boolean, boolean)}
   */
  @Test
  void testAppendTo6() {
    // Arrange
    MemberSpecificationElement memberSpecificationElement = new MemberSpecificationElement();
    memberSpecificationElement.setAnnotation("<init>");
    ArrayList<Object> memberSpecifications = new ArrayList<>();

    // Act
    memberSpecificationElement.appendTo(memberSpecifications, true, true);

    // Assert
    assertEquals(1, memberSpecifications.size());
    Object getResult = memberSpecifications.get(0);
    assertTrue(getResult instanceof MemberSpecification);
    assertEquals("<init>", ((MemberSpecification) getResult).name);
    assertEquals("L<init>;", ((MemberSpecification) getResult).annotationType);
    assertNull(((MemberSpecification) getResult).descriptor);
    assertNull(((MemberSpecification) getResult).attributeNames);
    assertEquals(0, ((MemberSpecification) getResult).requiredSetAccessFlags);
    assertEquals(0, ((MemberSpecification) getResult).requiredUnsetAccessFlags);
  }

  /**
   * Method under test:
   * {@link MemberSpecificationElement#appendTo(List, boolean, boolean)}
   */
  @Test
  void testAppendTo7() {
    // Arrange
    MemberSpecificationElement memberSpecificationElement = new MemberSpecificationElement();
    memberSpecificationElement.setType("<init>");

    // Act and Assert
    assertThrows(BuildException.class, () -> memberSpecificationElement.appendTo(new ArrayList<>(), true, true));
  }

  /**
   * Method under test:
   * {@link MemberSpecificationElement#appendTo(List, boolean, boolean)}
   */
  @Test
  void testAppendTo8() {
    // Arrange
    MemberSpecificationElement memberSpecificationElement = new MemberSpecificationElement();
    memberSpecificationElement.setParameters("<init>");
    ArrayList<Object> memberSpecifications = new ArrayList<>();

    // Act
    memberSpecificationElement.appendTo(memberSpecifications, true, true);

    // Assert
    assertEquals(1, memberSpecifications.size());
    Object getResult = memberSpecifications.get(0);
    assertTrue(getResult instanceof MemberSpecification);
    assertEquals("(L<init>;)V", ((MemberSpecification) getResult).descriptor);
    assertEquals("<init>", ((MemberSpecification) getResult).name);
    assertNull(((MemberSpecification) getResult).annotationType);
    assertNull(((MemberSpecification) getResult).attributeNames);
    assertEquals(0, ((MemberSpecification) getResult).requiredSetAccessFlags);
    assertEquals(0, ((MemberSpecification) getResult).requiredUnsetAccessFlags);
  }

  /**
   * Method under test:
   * {@link MemberSpecificationElement#appendTo(List, boolean, boolean)}
   */
  @Test
  void testAppendTo9() {
    // Arrange
    MemberSpecificationElement memberSpecificationElement = new MemberSpecificationElement();
    memberSpecificationElement.setValues("42");

    // Act and Assert
    assertThrows(BuildException.class, () -> memberSpecificationElement.appendTo(new ArrayList<>(), true, true));
  }

  /**
   * Method under test:
   * {@link MemberSpecificationElement#appendTo(List, boolean, boolean)}
   */
  @Test
  void testAppendTo10() {
    // Arrange
    MemberSpecificationElement memberSpecificationElement = new MemberSpecificationElement();
    memberSpecificationElement.setAccess(" ,");
    ArrayList<Object> memberSpecifications = new ArrayList<>();

    // Act
    memberSpecificationElement.appendTo(memberSpecifications, true, true);

    // Assert
    assertEquals(1, memberSpecifications.size());
    Object getResult = memberSpecifications.get(0);
    assertTrue(getResult instanceof MemberSpecification);
    assertEquals("<init>", ((MemberSpecification) getResult).name);
    assertNull(((MemberSpecification) getResult).annotationType);
    assertNull(((MemberSpecification) getResult).descriptor);
    assertNull(((MemberSpecification) getResult).attributeNames);
    assertEquals(0, ((MemberSpecification) getResult).requiredSetAccessFlags);
    assertEquals(0, ((MemberSpecification) getResult).requiredUnsetAccessFlags);
  }

  /**
   * Method under test:
   * {@link MemberSpecificationElement#appendTo(List, boolean, boolean)}
   */
  @Test
  void testAppendTo11() {
    // Arrange
    MemberSpecificationElement memberSpecificationElement = new MemberSpecificationElement();
    memberSpecificationElement.setAccess("!");

    // Act and Assert
    assertThrows(BuildException.class, () -> memberSpecificationElement.appendTo(new ArrayList<>(), true, true));
  }

  /**
   * Method under test:
   * {@link MemberSpecificationElement#appendTo(List, boolean, boolean)}
   */
  @Test
  void testAppendTo12() {
    // Arrange
    MemberSpecificationElement memberSpecificationElement = new MemberSpecificationElement();
    memberSpecificationElement.setAccess("public");
    ArrayList<Object> memberSpecifications = new ArrayList<>();

    // Act
    memberSpecificationElement.appendTo(memberSpecifications, true, true);

    // Assert
    assertEquals(1, memberSpecifications.size());
    Object getResult = memberSpecifications.get(0);
    assertTrue(getResult instanceof MemberSpecification);
    assertEquals("<init>", ((MemberSpecification) getResult).name);
    assertNull(((MemberSpecification) getResult).annotationType);
    assertNull(((MemberSpecification) getResult).descriptor);
    assertNull(((MemberSpecification) getResult).attributeNames);
    assertEquals(0, ((MemberSpecification) getResult).requiredUnsetAccessFlags);
    assertEquals(1, ((MemberSpecification) getResult).requiredSetAccessFlags);
  }

  /**
   * Method under test:
   * {@link MemberSpecificationElement#appendTo(List, boolean, boolean)}
   */
  @Test
  void testAppendTo13() {
    // Arrange
    MemberSpecificationElement memberSpecificationElement = new MemberSpecificationElement();
    memberSpecificationElement.setAccess("private");
    ArrayList<Object> memberSpecifications = new ArrayList<>();

    // Act
    memberSpecificationElement.appendTo(memberSpecifications, true, true);

    // Assert
    assertEquals(1, memberSpecifications.size());
    Object getResult = memberSpecifications.get(0);
    assertTrue(getResult instanceof MemberSpecification);
    assertEquals("<init>", ((MemberSpecification) getResult).name);
    assertNull(((MemberSpecification) getResult).annotationType);
    assertNull(((MemberSpecification) getResult).descriptor);
    assertNull(((MemberSpecification) getResult).attributeNames);
    assertEquals(0, ((MemberSpecification) getResult).requiredUnsetAccessFlags);
    assertEquals(2, ((MemberSpecification) getResult).requiredSetAccessFlags);
  }

  /**
   * Method under test:
   * {@link MemberSpecificationElement#appendTo(List, boolean, boolean)}
   */
  @Test
  void testAppendTo14() {
    // Arrange
    MemberSpecificationElement memberSpecificationElement = new MemberSpecificationElement();
    memberSpecificationElement.setAccess("protected");
    ArrayList<Object> memberSpecifications = new ArrayList<>();

    // Act
    memberSpecificationElement.appendTo(memberSpecifications, true, true);

    // Assert
    assertEquals(1, memberSpecifications.size());
    Object getResult = memberSpecifications.get(0);
    assertTrue(getResult instanceof MemberSpecification);
    assertEquals("<init>", ((MemberSpecification) getResult).name);
    assertNull(((MemberSpecification) getResult).annotationType);
    assertNull(((MemberSpecification) getResult).descriptor);
    assertNull(((MemberSpecification) getResult).attributeNames);
    assertEquals(0, ((MemberSpecification) getResult).requiredUnsetAccessFlags);
    assertEquals(4, ((MemberSpecification) getResult).requiredSetAccessFlags);
  }

  /**
   * Method under test:
   * {@link MemberSpecificationElement#appendTo(List, boolean, boolean)}
   */
  @Test
  void testAppendTo15() {
    // Arrange
    MemberSpecificationElement memberSpecificationElement = new MemberSpecificationElement();
    memberSpecificationElement.setAccess("static");
    ArrayList<Object> memberSpecifications = new ArrayList<>();

    // Act
    memberSpecificationElement.appendTo(memberSpecifications, true, true);

    // Assert
    assertEquals(1, memberSpecifications.size());
    Object getResult = memberSpecifications.get(0);
    assertTrue(getResult instanceof MemberSpecification);
    assertEquals("<init>", ((MemberSpecification) getResult).name);
    assertNull(((MemberSpecification) getResult).annotationType);
    assertNull(((MemberSpecification) getResult).descriptor);
    assertNull(((MemberSpecification) getResult).attributeNames);
    assertEquals(0, ((MemberSpecification) getResult).requiredUnsetAccessFlags);
    assertEquals(8, ((MemberSpecification) getResult).requiredSetAccessFlags);
  }

  /**
   * Method under test:
   * {@link MemberSpecificationElement#appendTo(List, boolean, boolean)}
   */
  @Test
  void testAppendTo16() {
    // Arrange
    MemberSpecificationElement memberSpecificationElement = new MemberSpecificationElement();
    memberSpecificationElement.setAccess("final");
    ArrayList<Object> memberSpecifications = new ArrayList<>();

    // Act
    memberSpecificationElement.appendTo(memberSpecifications, true, true);

    // Assert
    assertEquals(1, memberSpecifications.size());
    Object getResult = memberSpecifications.get(0);
    assertTrue(getResult instanceof MemberSpecification);
    assertEquals("<init>", ((MemberSpecification) getResult).name);
    assertNull(((MemberSpecification) getResult).annotationType);
    assertNull(((MemberSpecification) getResult).descriptor);
    assertNull(((MemberSpecification) getResult).attributeNames);
    assertEquals(0, ((MemberSpecification) getResult).requiredUnsetAccessFlags);
    assertEquals(Short.SIZE, ((MemberSpecification) getResult).requiredSetAccessFlags);
  }

  /**
   * Method under test:
   * {@link MemberSpecificationElement#appendTo(List, boolean, boolean)}
   */
  @Test
  void testAppendTo17() {
    // Arrange
    MemberSpecificationElement memberSpecificationElement = new MemberSpecificationElement();
    memberSpecificationElement.setAccess("synchronized");
    ArrayList<Object> memberSpecifications = new ArrayList<>();

    // Act
    memberSpecificationElement.appendTo(memberSpecifications, true, true);

    // Assert
    assertEquals(1, memberSpecifications.size());
    Object getResult = memberSpecifications.get(0);
    assertTrue(getResult instanceof MemberSpecification);
    assertEquals("<init>", ((MemberSpecification) getResult).name);
    assertNull(((MemberSpecification) getResult).annotationType);
    assertNull(((MemberSpecification) getResult).descriptor);
    assertNull(((MemberSpecification) getResult).attributeNames);
    assertEquals(0, ((MemberSpecification) getResult).requiredUnsetAccessFlags);
    assertEquals(Integer.SIZE, ((MemberSpecification) getResult).requiredSetAccessFlags);
  }

  /**
   * Method under test:
   * {@link MemberSpecificationElement#appendTo(List, boolean, boolean)}
   */
  @Test
  void testAppendTo18() {
    // Arrange
    MemberSpecificationElement memberSpecificationElement = new MemberSpecificationElement();
    memberSpecificationElement.setType("<init>");
    ArrayList<Object> memberSpecifications = new ArrayList<>();

    // Act
    memberSpecificationElement.appendTo(memberSpecifications, false, true);

    // Assert
    assertEquals(1, memberSpecifications.size());
    Object getResult = memberSpecifications.get(0);
    assertTrue(getResult instanceof MemberSpecification);
    assertEquals("L<init>;", ((MemberSpecification) getResult).descriptor);
    assertNull(((MemberSpecification) getResult).annotationType);
    assertNull(((MemberSpecification) getResult).name);
    assertNull(((MemberSpecification) getResult).attributeNames);
    assertEquals(0, ((MemberSpecification) getResult).requiredSetAccessFlags);
    assertEquals(0, ((MemberSpecification) getResult).requiredUnsetAccessFlags);
  }

  /**
   * Method under test:
   * {@link MemberSpecificationElement#appendTo(List, boolean, boolean)}
   */
  @Test
  void testAppendTo19() {
    // Arrange
    MemberSpecificationElement memberSpecificationElement = new MemberSpecificationElement();
    memberSpecificationElement.setType("<init>");

    // Act and Assert
    assertThrows(BuildException.class, () -> memberSpecificationElement.appendTo(new ArrayList<>(), true, false));
  }

  /**
   * Method under test:
   * {@link MemberSpecificationElement#appendTo(List, boolean, boolean)}
   */
  @Test
  void testAppendTo20() {
    // Arrange
    MemberSpecificationElement memberSpecificationElement = new MemberSpecificationElement();
    memberSpecificationElement.setParameters("<init>");

    // Act and Assert
    assertThrows(BuildException.class, () -> memberSpecificationElement.appendTo(new ArrayList<>(), false, true));
  }

  /**
   * Method under test:
   * {@link MemberSpecificationElement#appendTo(List, boolean, boolean)}
   */
  @Test
  void testAppendTo21() {
    // Arrange
    MemberSpecificationElement memberSpecificationElement = new MemberSpecificationElement();
    memberSpecificationElement.setParameters("<init>");

    // Act and Assert
    assertThrows(BuildException.class, () -> memberSpecificationElement.appendTo(new ArrayList<>(), true, false));
  }

  /**
   * Method under test:
   * {@link MemberSpecificationElement#appendTo(List, boolean, boolean)}
   */
  @Test
  void testAppendTo22() {
    // Arrange
    MemberSpecificationElement memberSpecificationElement = new MemberSpecificationElement();
    memberSpecificationElement.setValues("42");

    // Act and Assert
    assertThrows(BuildException.class, () -> memberSpecificationElement.appendTo(new ArrayList<>(), false, true));
  }

  /**
   * Method under test:
   * {@link MemberSpecificationElement#appendTo(List, boolean, boolean)}
   */
  @Test
  void testAppendTo23() {
    // Arrange
    MemberSpecificationElement memberSpecificationElement = new MemberSpecificationElement();
    memberSpecificationElement.setAccess("volatile");
    ArrayList<Object> memberSpecifications = new ArrayList<>();

    // Act
    memberSpecificationElement.appendTo(memberSpecifications, true, true);

    // Assert
    assertEquals(1, memberSpecifications.size());
    Object getResult = memberSpecifications.get(0);
    assertTrue(getResult instanceof MemberSpecification);
    assertEquals("<init>", ((MemberSpecification) getResult).name);
    assertNull(((MemberSpecification) getResult).annotationType);
    assertNull(((MemberSpecification) getResult).descriptor);
    assertNull(((MemberSpecification) getResult).attributeNames);
    assertEquals(0, ((MemberSpecification) getResult).requiredUnsetAccessFlags);
    assertEquals(Double.SIZE, ((MemberSpecification) getResult).requiredSetAccessFlags);
  }

  /**
   * Method under test:
   * {@link MemberSpecificationElement#appendTo(List, boolean, boolean)}
   */
  @Test
  void testAppendTo24() {
    // Arrange
    MemberSpecificationElement memberSpecificationElement = new MemberSpecificationElement();
    memberSpecificationElement.setAccess("abstract");
    ArrayList<Object> memberSpecifications = new ArrayList<>();

    // Act
    memberSpecificationElement.appendTo(memberSpecifications, true, true);

    // Assert
    assertEquals(1, memberSpecifications.size());
    Object getResult = memberSpecifications.get(0);
    assertTrue(getResult instanceof MemberSpecification);
    assertEquals("<init>", ((MemberSpecification) getResult).name);
    assertNull(((MemberSpecification) getResult).annotationType);
    assertNull(((MemberSpecification) getResult).descriptor);
    assertNull(((MemberSpecification) getResult).attributeNames);
    assertEquals(0, ((MemberSpecification) getResult).requiredUnsetAccessFlags);
    assertEquals(1024, ((MemberSpecification) getResult).requiredSetAccessFlags);
  }

  /**
   * Method under test: default or parameterless constructor of
   * {@link MemberSpecificationElement}
   */
  @Test
  void testNewMemberSpecificationElement() {
    // Arrange and Act
    MemberSpecificationElement actualMemberSpecificationElement = new MemberSpecificationElement();

    // Assert
    Location location = actualMemberSpecificationElement.getLocation();
    assertNull(location.getFileName());
    assertNull(actualMemberSpecificationElement.getDescription());
    assertNull(actualMemberSpecificationElement.getProject());
    assertNull(actualMemberSpecificationElement.getRefid());
    assertEquals(0, location.getColumnNumber());
    assertEquals(0, location.getLineNumber());
    assertFalse(actualMemberSpecificationElement.isReference());
  }
}
