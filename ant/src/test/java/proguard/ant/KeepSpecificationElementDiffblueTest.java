package proguard.ant;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import com.diffblue.cover.annotations.MaintainedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.util.ArrayList;
import java.util.List;
import org.apache.tools.ant.Location;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import proguard.KeepClassSpecification;
import proguard.MemberSpecification;

public class KeepSpecificationElementDiffblueTest {
  /**
   * Test {@link KeepSpecificationElement#appendTo(List, boolean, boolean, boolean)} with {@code keepSpecifications}, {@code markClasses}, {@code markClassMembers}, {@code markConditionally}.
   * <p>
   * Method under test: {@link KeepSpecificationElement#appendTo(List, boolean, boolean, boolean)}
   */
  @Test
  @Category(MaintainedByDiffblue.class)
  @MethodsUnderTest({"void proguard.ant.KeepSpecificationElement.appendTo(java.util.List, boolean, boolean, boolean)"})
  public void testAppendToWithKeepSpecificationsMarkClassesMarkClassMembersMarkConditionally() {
    // Arrange
    KeepSpecificationElement keepSpecificationElement = new KeepSpecificationElement();
    ArrayList<Object> keepSpecifications = new ArrayList<>();

    // Act
    keepSpecificationElement.appendTo(keepSpecifications, true, true, true);

    // Assert
    assertEquals(1, keepSpecifications.size());
    Object getResult = keepSpecifications.get(0);
    assertTrue(getResult instanceof KeepClassSpecification);
    assertNull(((KeepClassSpecification) getResult).fieldSpecifications);
    assertNull(((KeepClassSpecification) getResult).methodSpecifications);
    assertEquals(0, ((KeepClassSpecification) getResult).requiredSetAccessFlags);
    assertEquals(0, ((KeepClassSpecification) getResult).requiredUnsetAccessFlags);
  }

  /**
   * Test {@link KeepSpecificationElement#appendTo(List, boolean, boolean, boolean)} with {@code keepSpecifications}, {@code markClasses}, {@code markClassMembers}, {@code markConditionally}.
   * <p>
   * Method under test: {@link KeepSpecificationElement#appendTo(List, boolean, boolean, boolean)}
   */
  @Test
  @Category(MaintainedByDiffblue.class)
  @MethodsUnderTest({"void proguard.ant.KeepSpecificationElement.appendTo(java.util.List, boolean, boolean, boolean)"})
  public void testAppendToWithKeepSpecificationsMarkClassesMarkClassMembersMarkConditionally2() {
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
    List<MemberSpecification> memberSpecificationList = ((KeepClassSpecification) getResult).fieldSpecifications;
    assertEquals(1, memberSpecificationList.size());
    MemberSpecification getResult2 = memberSpecificationList.get(0);
    assertNull(getResult2.annotationType);
    assertNull(getResult2.descriptor);
    assertNull(getResult2.name);
    assertNull(getResult2.attributeNames);
    assertEquals(0, ((KeepClassSpecification) getResult).requiredSetAccessFlags);
    assertEquals(0, getResult2.requiredSetAccessFlags);
    assertEquals(0, getResult2.requiredUnsetAccessFlags);
  }

  /**
   * Test {@link KeepSpecificationElement#appendTo(List, boolean, boolean, boolean)} with {@code keepSpecifications}, {@code markClasses}, {@code markClassMembers}, {@code markConditionally}.
   * <p>
   * Method under test: {@link KeepSpecificationElement#appendTo(List, boolean, boolean, boolean)}
   */
  @Test
  @Category(MaintainedByDiffblue.class)
  @MethodsUnderTest({"void proguard.ant.KeepSpecificationElement.appendTo(java.util.List, boolean, boolean, boolean)"})
  public void testAppendToWithKeepSpecificationsMarkClassesMarkClassMembersMarkConditionally3() {
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
    List<MemberSpecification> memberSpecificationList = ((KeepClassSpecification) getResult).methodSpecifications;
    assertEquals(1, memberSpecificationList.size());
    MemberSpecification getResult2 = memberSpecificationList.get(0);
    assertNull(getResult2.annotationType);
    assertNull(getResult2.descriptor);
    assertNull(getResult2.name);
    assertNull(getResult2.attributeNames);
    assertEquals(0, getResult2.requiredSetAccessFlags);
    assertEquals(0, getResult2.requiredUnsetAccessFlags);
  }

  /**
   * Test {@link KeepSpecificationElement#appendTo(List, boolean, boolean, boolean)} with {@code keepSpecifications}, {@code markClasses}, {@code markClassMembers}, {@code markConditionally}.
   * <p>
   * Method under test: {@link KeepSpecificationElement#appendTo(List, boolean, boolean, boolean)}
   */
  @Test
  @Category(MaintainedByDiffblue.class)
  @MethodsUnderTest({"void proguard.ant.KeepSpecificationElement.appendTo(java.util.List, boolean, boolean, boolean)"})
  public void testAppendToWithKeepSpecificationsMarkClassesMarkClassMembersMarkConditionally4() {
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
    List<MemberSpecification> memberSpecificationList = ((KeepClassSpecification) getResult).fieldSpecifications;
    assertEquals(2, memberSpecificationList.size());
    MemberSpecification getResult2 = memberSpecificationList.get(0);
    assertNull(getResult2.annotationType);
    assertNull(getResult2.descriptor);
    assertNull(getResult2.name);
    assertNull(getResult2.attributeNames);
    assertEquals(0, ((KeepClassSpecification) getResult).requiredSetAccessFlags);
    assertEquals(0, getResult2.requiredSetAccessFlags);
    assertEquals(0, getResult2.requiredUnsetAccessFlags);
  }

  /**
   * Test {@link KeepSpecificationElement#appendTo(List, boolean, boolean, boolean)} with {@code keepSpecifications}, {@code markClasses}, {@code markClassMembers}, {@code markConditionally}.
   * <p>
   * Method under test: {@link KeepSpecificationElement#appendTo(List, boolean, boolean, boolean)}
   */
  @Test
  @Category(MaintainedByDiffblue.class)
  @MethodsUnderTest({"void proguard.ant.KeepSpecificationElement.appendTo(java.util.List, boolean, boolean, boolean)"})
  public void testAppendToWithKeepSpecificationsMarkClassesMarkClassMembersMarkConditionally5() {
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
    List<MemberSpecification> memberSpecificationList = ((KeepClassSpecification) getResult).methodSpecifications;
    assertEquals(2, memberSpecificationList.size());
    MemberSpecification getResult2 = memberSpecificationList.get(0);
    assertNull(getResult2.annotationType);
    assertNull(getResult2.descriptor);
    assertNull(getResult2.name);
    assertNull(getResult2.attributeNames);
    assertEquals(0, getResult2.requiredSetAccessFlags);
    assertEquals(0, getResult2.requiredUnsetAccessFlags);
  }

  /**
   * Test {@link KeepSpecificationElement#appendTo(List, boolean, boolean, boolean)} with {@code keepSpecifications}, {@code markClasses}, {@code markClassMembers}, {@code markConditionally}.
   * <p>
   * Method under test: {@link KeepSpecificationElement#appendTo(List, boolean, boolean, boolean)}
   */
  @Test
  @Category(MaintainedByDiffblue.class)
  @MethodsUnderTest({"void proguard.ant.KeepSpecificationElement.appendTo(java.util.List, boolean, boolean, boolean)"})
  public void testAppendToWithKeepSpecificationsMarkClassesMarkClassMembersMarkConditionally6() {
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
    List<MemberSpecification> memberSpecificationList = ((KeepClassSpecification) getResult).fieldSpecifications;
    assertEquals(1, memberSpecificationList.size());
    MemberSpecification getResult2 = memberSpecificationList.get(0);
    assertNull(getResult2.annotationType);
    assertNull(getResult2.descriptor);
    assertNull(getResult2.name);
    assertNull(getResult2.attributeNames);
    assertEquals(0, ((KeepClassSpecification) getResult).requiredSetAccessFlags);
    assertEquals(0, getResult2.requiredSetAccessFlags);
    assertEquals(0, getResult2.requiredUnsetAccessFlags);
  }

  /**
   * Test {@link KeepSpecificationElement#appendTo(List, boolean, boolean, boolean)} with {@code keepSpecifications}, {@code markClasses}, {@code markClassMembers}, {@code markConditionally}.
   * <p>
   * Method under test: {@link KeepSpecificationElement#appendTo(List, boolean, boolean, boolean)}
   */
  @Test
  @Category(MaintainedByDiffblue.class)
  @MethodsUnderTest({"void proguard.ant.KeepSpecificationElement.appendTo(java.util.List, boolean, boolean, boolean)"})
  public void testAppendToWithKeepSpecificationsMarkClassesMarkClassMembersMarkConditionally7() {
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
    List<MemberSpecification> memberSpecificationList = ((KeepClassSpecification) getResult).fieldSpecifications;
    assertEquals(1, memberSpecificationList.size());
    MemberSpecification getResult2 = memberSpecificationList.get(0);
    assertNull(getResult2.annotationType);
    assertNull(getResult2.descriptor);
    assertNull(getResult2.name);
    assertNull(getResult2.attributeNames);
    assertEquals(0, ((KeepClassSpecification) getResult).requiredSetAccessFlags);
    assertEquals(0, getResult2.requiredSetAccessFlags);
    assertEquals(0, getResult2.requiredUnsetAccessFlags);
  }

  /**
   * Test {@link KeepSpecificationElement#appendTo(List, boolean, boolean, boolean)} with {@code keepSpecifications}, {@code markClasses}, {@code markClassMembers}, {@code markConditionally}.
   * <p>
   * Method under test: {@link KeepSpecificationElement#appendTo(List, boolean, boolean, boolean)}
   */
  @Test
  @Category(MaintainedByDiffblue.class)
  @MethodsUnderTest({"void proguard.ant.KeepSpecificationElement.appendTo(java.util.List, boolean, boolean, boolean)"})
  public void testAppendToWithKeepSpecificationsMarkClassesMarkClassMembersMarkConditionally8() {
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
    List<MemberSpecification> memberSpecificationList = ((KeepClassSpecification) getResult).fieldSpecifications;
    assertEquals(1, memberSpecificationList.size());
    MemberSpecification getResult2 = memberSpecificationList.get(0);
    assertNull(getResult2.annotationType);
    assertNull(getResult2.descriptor);
    assertNull(getResult2.name);
    assertNull(getResult2.attributeNames);
    assertEquals(0, ((KeepClassSpecification) getResult).requiredSetAccessFlags);
    assertEquals(0, getResult2.requiredSetAccessFlags);
    assertEquals(0, getResult2.requiredUnsetAccessFlags);
  }

  /**
   * Test {@link KeepSpecificationElement#appendTo(List, boolean, boolean, boolean)} with {@code keepSpecifications}, {@code markClasses}, {@code markClassMembers}, {@code markConditionally}.
   * <p>
   * Method under test: {@link KeepSpecificationElement#appendTo(List, boolean, boolean, boolean)}
   */
  @Test
  @Category(MaintainedByDiffblue.class)
  @MethodsUnderTest({"void proguard.ant.KeepSpecificationElement.appendTo(java.util.List, boolean, boolean, boolean)"})
  public void testAppendToWithKeepSpecificationsMarkClassesMarkClassMembersMarkConditionally9() {
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
    List<MemberSpecification> memberSpecificationList = ((KeepClassSpecification) getResult).fieldSpecifications;
    assertEquals(1, memberSpecificationList.size());
    MemberSpecification getResult2 = memberSpecificationList.get(0);
    assertNull(getResult2.annotationType);
    assertNull(getResult2.descriptor);
    assertNull(getResult2.name);
    assertNull(getResult2.attributeNames);
    assertEquals(0, ((KeepClassSpecification) getResult).requiredSetAccessFlags);
    assertEquals(0, getResult2.requiredSetAccessFlags);
    assertEquals(0, getResult2.requiredUnsetAccessFlags);
  }

  /**
   * Test {@link KeepSpecificationElement#appendTo(List, boolean, boolean, boolean)} with {@code keepSpecifications}, {@code markClasses}, {@code markClassMembers}, {@code markConditionally}.
   * <p>
   * Method under test: {@link KeepSpecificationElement#appendTo(List, boolean, boolean, boolean)}
   */
  @Test
  @Category(MaintainedByDiffblue.class)
  @MethodsUnderTest({"void proguard.ant.KeepSpecificationElement.appendTo(java.util.List, boolean, boolean, boolean)"})
  public void testAppendToWithKeepSpecificationsMarkClassesMarkClassMembersMarkConditionally10() {
    // Arrange
    KeepSpecificationElement keepSpecificationElement = new KeepSpecificationElement();

    ArrayList<Object> keepSpecifications = new ArrayList<>();
    keepSpecifications.add("42");

    // Act
    keepSpecificationElement.appendTo(keepSpecifications, true, true, true);

    // Assert
    assertEquals(2, keepSpecifications.size());
    assertTrue(keepSpecifications.get(1) instanceof KeepClassSpecification);
    assertEquals("42", keepSpecifications.get(0));
  }

  /**
   * Test {@link KeepSpecificationElement#appendTo(List, boolean, boolean, boolean)} with {@code keepSpecifications}, {@code markClasses}, {@code markClassMembers}, {@code markConditionally}.
   * <p>
   * Method under test: {@link KeepSpecificationElement#appendTo(List, boolean, boolean, boolean)}
   */
  @Test
  @Category(MaintainedByDiffblue.class)
  @MethodsUnderTest({"void proguard.ant.KeepSpecificationElement.appendTo(java.util.List, boolean, boolean, boolean)"})
  public void testAppendToWithKeepSpecificationsMarkClassesMarkClassMembersMarkConditionally11() {
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
    List<MemberSpecification> memberSpecificationList = ((KeepClassSpecification) getResult).fieldSpecifications;
    assertEquals(1, memberSpecificationList.size());
    MemberSpecification getResult2 = memberSpecificationList.get(0);
    assertNull(getResult2.annotationType);
    assertNull(getResult2.descriptor);
    assertNull(getResult2.name);
    assertNull(getResult2.attributeNames);
    assertEquals(0, ((KeepClassSpecification) getResult).requiredSetAccessFlags);
    assertEquals(0, getResult2.requiredSetAccessFlags);
    assertEquals(0, getResult2.requiredUnsetAccessFlags);
  }

  /**
   * Test {@link KeepSpecificationElement#appendTo(List, boolean, boolean, boolean)} with {@code keepSpecifications}, {@code markClasses}, {@code markClassMembers}, {@code markConditionally}.
   * <p>
   * Method under test: {@link KeepSpecificationElement#appendTo(List, boolean, boolean, boolean)}
   */
  @Test
  @Category(MaintainedByDiffblue.class)
  @MethodsUnderTest({"void proguard.ant.KeepSpecificationElement.appendTo(java.util.List, boolean, boolean, boolean)"})
  public void testAppendToWithKeepSpecificationsMarkClassesMarkClassMembersMarkConditionally12() {
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
    List<MemberSpecification> memberSpecificationList = ((KeepClassSpecification) getResult).fieldSpecifications;
    assertEquals(1, memberSpecificationList.size());
    MemberSpecification getResult2 = memberSpecificationList.get(0);
    assertNull(getResult2.annotationType);
    assertNull(getResult2.descriptor);
    assertNull(getResult2.name);
    assertNull(getResult2.attributeNames);
    assertEquals(0, getResult2.requiredSetAccessFlags);
    assertEquals(0, getResult2.requiredUnsetAccessFlags);
    assertEquals(1, ((KeepClassSpecification) getResult).requiredSetAccessFlags);
  }

  /**
   * Test {@link KeepSpecificationElement#appendTo(List, boolean, boolean, boolean)} with {@code keepSpecifications}, {@code markClasses}, {@code markClassMembers}, {@code markConditionally}.
   * <p>
   * Method under test: {@link KeepSpecificationElement#appendTo(List, boolean, boolean, boolean)}
   */
  @Test
  @Category(MaintainedByDiffblue.class)
  @MethodsUnderTest({"void proguard.ant.KeepSpecificationElement.appendTo(java.util.List, boolean, boolean, boolean)"})
  public void testAppendToWithKeepSpecificationsMarkClassesMarkClassMembersMarkConditionally13() {
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
    List<MemberSpecification> memberSpecificationList = ((KeepClassSpecification) getResult).fieldSpecifications;
    assertEquals(1, memberSpecificationList.size());
    MemberSpecification getResult2 = memberSpecificationList.get(0);
    assertNull(getResult2.annotationType);
    assertNull(getResult2.descriptor);
    assertNull(getResult2.name);
    assertNull(getResult2.attributeNames);
    assertEquals(0, getResult2.requiredSetAccessFlags);
    assertEquals(0, getResult2.requiredUnsetAccessFlags);
    assertEquals(Short.SIZE, ((KeepClassSpecification) getResult).requiredSetAccessFlags);
  }

  /**
   * Test {@link KeepSpecificationElement#appendTo(List, boolean, boolean, boolean)} with {@code keepSpecifications}, {@code markClasses}, {@code markClassMembers}, {@code markConditionally}.
   * <p>
   * Method under test: {@link KeepSpecificationElement#appendTo(List, boolean, boolean, boolean)}
   */
  @Test
  @Category(MaintainedByDiffblue.class)
  @MethodsUnderTest({"void proguard.ant.KeepSpecificationElement.appendTo(java.util.List, boolean, boolean, boolean)"})
  public void testAppendToWithKeepSpecificationsMarkClassesMarkClassMembersMarkConditionally14() {
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
    List<MemberSpecification> memberSpecificationList = ((KeepClassSpecification) getResult).fieldSpecifications;
    assertEquals(1, memberSpecificationList.size());
    MemberSpecification getResult2 = memberSpecificationList.get(0);
    assertNull(getResult2.annotationType);
    assertNull(getResult2.descriptor);
    assertNull(getResult2.name);
    assertNull(getResult2.attributeNames);
    assertEquals(0, getResult2.requiredSetAccessFlags);
    assertEquals(0, getResult2.requiredUnsetAccessFlags);
    assertEquals(1024, ((KeepClassSpecification) getResult).requiredSetAccessFlags);
  }

  /**
   * Test {@link KeepSpecificationElement#appendTo(List, boolean, boolean, boolean)} with {@code keepSpecifications}, {@code markClasses}, {@code markClassMembers}, {@code markConditionally}.
   * <p>
   * Method under test: {@link KeepSpecificationElement#appendTo(List, boolean, boolean, boolean)}
   */
  @Test
  @Category(MaintainedByDiffblue.class)
  @MethodsUnderTest({"void proguard.ant.KeepSpecificationElement.appendTo(java.util.List, boolean, boolean, boolean)"})
  public void testAppendToWithKeepSpecificationsMarkClassesMarkClassMembersMarkConditionally15() {
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
    List<MemberSpecification> memberSpecificationList = ((KeepClassSpecification) getResult).fieldSpecifications;
    assertEquals(1, memberSpecificationList.size());
    MemberSpecification getResult2 = memberSpecificationList.get(0);
    assertNull(getResult2.annotationType);
    assertNull(getResult2.descriptor);
    assertNull(getResult2.name);
    assertNull(getResult2.attributeNames);
    assertEquals(0, getResult2.requiredSetAccessFlags);
    assertEquals(0, getResult2.requiredUnsetAccessFlags);
    assertEquals(4096, ((KeepClassSpecification) getResult).requiredSetAccessFlags);
  }

  /**
   * Test {@link KeepSpecificationElement#appendTo(List, boolean, boolean, boolean)} with {@code keepSpecifications}, {@code markClasses}, {@code markClassMembers}, {@code markConditionally}.
   * <p>
   * Method under test: {@link KeepSpecificationElement#appendTo(List, boolean, boolean, boolean)}
   */
  @Test
  @Category(MaintainedByDiffblue.class)
  @MethodsUnderTest({"void proguard.ant.KeepSpecificationElement.appendTo(java.util.List, boolean, boolean, boolean)"})
  public void testAppendToWithKeepSpecificationsMarkClassesMarkClassMembersMarkConditionally16() {
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
    List<MemberSpecification> memberSpecificationList = ((KeepClassSpecification) getResult).fieldSpecifications;
    assertEquals(1, memberSpecificationList.size());
    MemberSpecification getResult2 = memberSpecificationList.get(0);
    assertNull(getResult2.annotationType);
    assertNull(getResult2.descriptor);
    assertNull(getResult2.name);
    assertNull(getResult2.attributeNames);
    assertEquals(0, getResult2.requiredSetAccessFlags);
    assertEquals(0, getResult2.requiredUnsetAccessFlags);
    assertEquals(8192, ((KeepClassSpecification) getResult).requiredSetAccessFlags);
  }

  /**
   * Test {@link KeepSpecificationElement#appendTo(List, boolean, boolean, boolean)} with {@code keepSpecifications}, {@code markClasses}, {@code markClassMembers}, {@code markConditionally}.
   * <p>
   * Method under test: {@link KeepSpecificationElement#appendTo(List, boolean, boolean, boolean)}
   */
  @Test
  @Category(MaintainedByDiffblue.class)
  @MethodsUnderTest({"void proguard.ant.KeepSpecificationElement.appendTo(java.util.List, boolean, boolean, boolean)"})
  public void testAppendToWithKeepSpecificationsMarkClassesMarkClassMembersMarkConditionally17() {
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
    List<MemberSpecification> memberSpecificationList = ((KeepClassSpecification) getResult).fieldSpecifications;
    assertEquals(1, memberSpecificationList.size());
    MemberSpecification getResult2 = memberSpecificationList.get(0);
    assertNull(getResult2.annotationType);
    assertNull(getResult2.descriptor);
    assertNull(getResult2.name);
    assertNull(getResult2.attributeNames);
    assertEquals(0, ((KeepClassSpecification) getResult).requiredSetAccessFlags);
    assertEquals(0, getResult2.requiredSetAccessFlags);
    assertEquals(0, getResult2.requiredUnsetAccessFlags);
  }

  /**
   * Test {@link KeepSpecificationElement#appendTo(List, boolean, boolean, boolean)} with {@code keepSpecifications}, {@code markClasses}, {@code markClassMembers}, {@code markConditionally}.
   * <p>
   * Method under test: {@link KeepSpecificationElement#appendTo(List, boolean, boolean, boolean)}
   */
  @Test
  @Category(MaintainedByDiffblue.class)
  @MethodsUnderTest({"void proguard.ant.KeepSpecificationElement.appendTo(java.util.List, boolean, boolean, boolean)"})
  public void testAppendToWithKeepSpecificationsMarkClassesMarkClassMembersMarkConditionally18() {
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
    List<MemberSpecification> memberSpecificationList = ((KeepClassSpecification) getResult).fieldSpecifications;
    assertEquals(1, memberSpecificationList.size());
    MemberSpecification getResult2 = memberSpecificationList.get(0);
    assertNull(getResult2.annotationType);
    assertNull(getResult2.descriptor);
    assertNull(getResult2.name);
    assertNull(getResult2.attributeNames);
    assertEquals(0, getResult2.requiredSetAccessFlags);
    assertEquals(0, getResult2.requiredUnsetAccessFlags);
    assertEquals(512, ((KeepClassSpecification) getResult).requiredSetAccessFlags);
  }

  /**
   * Test {@link KeepSpecificationElement#appendTo(List, boolean, boolean, boolean)} with {@code keepSpecifications}, {@code markClasses}, {@code markClassMembers}, {@code markConditionally}.
   * <p>
   * Method under test: {@link KeepSpecificationElement#appendTo(List, boolean, boolean, boolean)}
   */
  @Test
  @Category(MaintainedByDiffblue.class)
  @MethodsUnderTest({"void proguard.ant.KeepSpecificationElement.appendTo(java.util.List, boolean, boolean, boolean)"})
  public void testAppendToWithKeepSpecificationsMarkClassesMarkClassMembersMarkConditionally19() {
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
    List<MemberSpecification> memberSpecificationList = ((KeepClassSpecification) getResult).fieldSpecifications;
    assertEquals(1, memberSpecificationList.size());
    MemberSpecification getResult2 = memberSpecificationList.get(0);
    assertNull(getResult2.annotationType);
    assertNull(getResult2.descriptor);
    assertNull(getResult2.name);
    assertNull(getResult2.attributeNames);
    assertEquals(0, ((KeepClassSpecification) getResult).requiredSetAccessFlags);
    assertEquals(0, getResult2.requiredSetAccessFlags);
    assertEquals(0, getResult2.requiredUnsetAccessFlags);
    assertEquals(512, ((KeepClassSpecification) getResult).requiredUnsetAccessFlags);
  }

  /**
   * Test {@link KeepSpecificationElement#appendTo(List, boolean, boolean, boolean)} with {@code keepSpecifications}, {@code markClasses}, {@code markClassMembers}, {@code markConditionally}.
   * <p>
   * Method under test: {@link KeepSpecificationElement#appendTo(List, boolean, boolean, boolean)}
   */
  @Test
  @Category(MaintainedByDiffblue.class)
  @MethodsUnderTest({"void proguard.ant.KeepSpecificationElement.appendTo(java.util.List, boolean, boolean, boolean)"})
  public void testAppendToWithKeepSpecificationsMarkClassesMarkClassMembersMarkConditionally20() {
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
    List<MemberSpecification> memberSpecificationList = ((KeepClassSpecification) getResult).fieldSpecifications;
    assertEquals(1, memberSpecificationList.size());
    MemberSpecification getResult2 = memberSpecificationList.get(0);
    assertNull(getResult2.annotationType);
    assertNull(getResult2.descriptor);
    assertNull(getResult2.name);
    assertNull(getResult2.attributeNames);
    assertEquals(0, getResult2.requiredSetAccessFlags);
    assertEquals(0, getResult2.requiredUnsetAccessFlags);
    assertEquals(16384, ((KeepClassSpecification) getResult).requiredSetAccessFlags);
  }

  /**
   * Test {@link KeepSpecificationElement#appendTo(List, boolean, boolean, boolean)} with {@code keepSpecifications}, {@code markClasses}, {@code markClassMembers}, {@code markConditionally}.
   * <p>
   * Method under test: {@link KeepSpecificationElement#appendTo(List, boolean, boolean, boolean)}
   */
  @Test
  @Category(MaintainedByDiffblue.class)
  @MethodsUnderTest({"void proguard.ant.KeepSpecificationElement.appendTo(java.util.List, boolean, boolean, boolean)"})
  public void testAppendToWithKeepSpecificationsMarkClassesMarkClassMembersMarkConditionally21() {
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
    List<MemberSpecification> memberSpecificationList = ((KeepClassSpecification) getResult).fieldSpecifications;
    assertEquals(1, memberSpecificationList.size());
    MemberSpecification getResult2 = memberSpecificationList.get(0);
    assertNull(getResult2.annotationType);
    assertNull(getResult2.descriptor);
    assertNull(getResult2.name);
    assertNull(getResult2.attributeNames);
    assertEquals(0, ((KeepClassSpecification) getResult).requiredSetAccessFlags);
    assertEquals(0, getResult2.requiredSetAccessFlags);
    assertEquals(0, getResult2.requiredUnsetAccessFlags);
    assertEquals(16384, ((KeepClassSpecification) getResult).requiredUnsetAccessFlags);
  }

  /**
   * Test {@link KeepSpecificationElement#appendTo(List, boolean, boolean, boolean)} with {@code keepSpecifications}, {@code markClasses}, {@code markClassMembers}, {@code markConditionally}.
   * <p>
   * Method under test: {@link KeepSpecificationElement#appendTo(List, boolean, boolean, boolean)}
   */
  @Test
  @Category(MaintainedByDiffblue.class)
  @MethodsUnderTest({"void proguard.ant.KeepSpecificationElement.appendTo(java.util.List, boolean, boolean, boolean)"})
  public void testAppendToWithKeepSpecificationsMarkClassesMarkClassMembersMarkConditionally22() {
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
    List<MemberSpecification> memberSpecificationList = ((KeepClassSpecification) getResult).fieldSpecifications;
    assertEquals(1, memberSpecificationList.size());
    MemberSpecification getResult2 = memberSpecificationList.get(0);
    assertNull(getResult2.annotationType);
    assertNull(getResult2.descriptor);
    assertNull(getResult2.name);
    assertNull(getResult2.attributeNames);
    assertEquals(0, ((KeepClassSpecification) getResult).requiredSetAccessFlags);
    assertEquals(0, getResult2.requiredSetAccessFlags);
    assertEquals(0, getResult2.requiredUnsetAccessFlags);
  }

  /**
   * Test new {@link KeepSpecificationElement} (default constructor).
   * <p>
   * Method under test: default or parameterless constructor of {@link KeepSpecificationElement}
   */
  @Test
  @Category(MaintainedByDiffblue.class)
  @MethodsUnderTest({"void proguard.ant.KeepSpecificationElement.<init>()"})
  public void testNewKeepSpecificationElement() {
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
