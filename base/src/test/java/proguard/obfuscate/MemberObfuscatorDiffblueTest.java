package proguard.obfuscate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import java.util.HashMap;
import java.util.Map;
import java.util.function.BiFunction;
import org.junit.jupiter.api.Test;
import proguard.classfile.Clazz;
import proguard.classfile.LibraryClass;
import proguard.classfile.LibraryField;
import proguard.classfile.Member;
import proguard.classfile.ProgramField;

class MemberObfuscatorDiffblueTest {
  /**
   * Method under test: {@link MemberObfuscator#visitAnyMember(Clazz, Member)}
   */
  @Test
  void testVisitAnyMember() {
    // Arrange
    NumericNameFactory nameFactory = new NumericNameFactory();
    MemberObfuscator memberObfuscator = new MemberObfuscator(true, nameFactory, new HashMap<>());
    LibraryClass clazz = new LibraryClass(1, "java/lang/annotation/Annotation", "java/lang/annotation/Annotation");

    LibraryField member = new LibraryField(1, "Name", "Descriptor");

    // Act
    memberObfuscator.visitAnyMember(clazz, member);

    // Assert
    assertEquals("1", member.getProcessingInfo());
  }

  /**
   * Method under test: {@link MemberObfuscator#visitAnyMember(Clazz, Member)}
   */
  @Test
  void testVisitAnyMember2() {
    // Arrange
    NumericNameFactory nameFactory = new NumericNameFactory();
    MemberObfuscator memberObfuscator = new MemberObfuscator(false, nameFactory, new HashMap<>());
    LibraryClass clazz = new LibraryClass(1, "java/lang/annotation/Annotation", "java/lang/annotation/Annotation");

    LibraryField member = new LibraryField(1, "Name", "Descriptor");

    // Act
    memberObfuscator.visitAnyMember(clazz, member);

    // Assert
    assertEquals("1", member.getProcessingInfo());
  }

  /**
   * Method under test: {@link MemberObfuscator#retrieveNameMap(Map, String)}
   */
  @Test
  void testRetrieveNameMap() {
    // Arrange
    HashMap<Object, Object> descriptorMap = new HashMap<>();

    // Act
    Map actualRetrieveNameMapResult = MemberObfuscator.retrieveNameMap(descriptorMap, "Descriptor");

    // Assert
    assertEquals(1, descriptorMap.size());
    assertTrue(descriptorMap.containsKey("Descriptor"));
    assertTrue(actualRetrieveNameMapResult.isEmpty());
  }

  /**
   * Method under test: {@link MemberObfuscator#retrieveNameMap(Map, String)}
   */
  @Test
  void testRetrieveNameMap2() {
    // Arrange
    HashMap<Object, Object> descriptorMap = new HashMap<>();
    descriptorMap.computeIfPresent("42", mock(BiFunction.class));

    // Act
    Map actualRetrieveNameMapResult = MemberObfuscator.retrieveNameMap(descriptorMap, "Descriptor");

    // Assert
    assertEquals(1, descriptorMap.size());
    assertTrue(descriptorMap.containsKey("Descriptor"));
    assertTrue(actualRetrieveNameMapResult.isEmpty());
  }

  /**
   * Method under test:
   * {@link MemberObfuscator#setFixedNewMemberName(Member, String)}
   */
  @Test
  void testSetFixedNewMemberName() {
    // Arrange
    LibraryField member = new LibraryField(1, "Name", "Descriptor");

    // Act
    MemberObfuscator.setFixedNewMemberName(member, "Name");

    // Assert
    assertEquals("Name", member.getProcessingInfo());
  }

  /**
   * Method under test:
   * {@link MemberObfuscator#setFixedNewMemberName(Member, String)}
   */
  @Test
  void testSetFixedNewMemberName2() {
    // Arrange
    LibraryField member = new LibraryField(1, "Name", "Descriptor");
    member.setProcessingInfo("Member");

    // Act
    MemberObfuscator.setFixedNewMemberName(member, "Name");

    // Assert
    assertEquals("Name", member.getProcessingInfo());
  }

  /**
   * Method under test:
   * {@link MemberObfuscator#setFixedNewMemberName(Member, String)}
   */
  @Test
  void testSetFixedNewMemberName3() {
    // Arrange
    LibraryField libraryField = new LibraryField(1, "Name", "Descriptor");
    libraryField.setProcessingInfo(null);

    LibraryField member = new LibraryField(1, "Name", "Descriptor");
    member.setProcessingInfo(libraryField);

    // Act
    MemberObfuscator.setFixedNewMemberName(member, "Name");

    // Assert
    Object processingInfo = member.getProcessingInfo();
    assertTrue(processingInfo instanceof LibraryField);
    assertEquals("Name", ((LibraryField) processingInfo).getProcessingInfo());
    assertSame(libraryField, processingInfo);
  }

  /**
   * Method under test: {@link MemberObfuscator#setNewMemberName(Member, String)}
   */
  @Test
  void testSetNewMemberName() {
    // Arrange
    LibraryField member = new LibraryField(1, "Name", "Descriptor");

    // Act
    MemberObfuscator.setNewMemberName(member, "Name");

    // Assert
    assertEquals("Name", member.getProcessingInfo());
  }

  /**
   * Method under test: {@link MemberObfuscator#setNewMemberName(Member, String)}
   */
  @Test
  void testSetNewMemberName2() {
    // Arrange
    LibraryField member = new LibraryField(1, "Name", "Descriptor");
    member.setProcessingInfo("Member");

    // Act
    MemberObfuscator.setNewMemberName(member, "Name");

    // Assert
    assertEquals("Name", member.getProcessingInfo());
  }

  /**
   * Method under test: {@link MemberObfuscator#setNewMemberName(Member, String)}
   */
  @Test
  void testSetNewMemberName3() {
    // Arrange
    LibraryField libraryField = new LibraryField(1, "Name", "Descriptor");
    libraryField.setProcessingInfo(null);

    LibraryField member = new LibraryField(1, "Name", "Descriptor");
    member.setProcessingInfo(libraryField);

    // Act
    MemberObfuscator.setNewMemberName(member, "Name");

    // Assert
    Object processingInfo = member.getProcessingInfo();
    assertTrue(processingInfo instanceof LibraryField);
    assertEquals("Name", ((LibraryField) processingInfo).getProcessingInfo());
    assertSame(libraryField, processingInfo);
  }

  /**
   * Method under test: {@link MemberObfuscator#hasFixedNewMemberName(Member)}
   */
  @Test
  void testHasFixedNewMemberName() {
    // Arrange, Act and Assert
    assertTrue(MemberObfuscator.hasFixedNewMemberName(new LibraryField(1, "Name", "Descriptor")));
    assertFalse(MemberObfuscator.hasFixedNewMemberName(new ProgramField()));
  }

  /**
   * Method under test: {@link MemberObfuscator#hasFixedNewMemberName(Member)}
   */
  @Test
  void testHasFixedNewMemberName2() {
    // Arrange
    LibraryField member = new LibraryField(1, "Name", "Descriptor");
    member.setProcessingInfo("Member");

    // Act and Assert
    assertTrue(MemberObfuscator.hasFixedNewMemberName(member));
  }

  /**
   * Method under test: {@link MemberObfuscator#hasFixedNewMemberName(Member)}
   */
  @Test
  void testHasFixedNewMemberName3() {
    // Arrange
    LibraryField libraryField = new LibraryField(1, "Name", "Descriptor");
    libraryField.setProcessingInfo(null);

    LibraryField member = new LibraryField(1, "Name", "Descriptor");
    member.setProcessingInfo(libraryField);

    // Act and Assert
    assertTrue(MemberObfuscator.hasFixedNewMemberName(member));
  }

  /**
   * Method under test: {@link MemberObfuscator#newMemberName(Member)}
   */
  @Test
  void testNewMemberName() {
    // Arrange, Act and Assert
    assertNull(MemberObfuscator.newMemberName(new LibraryField(1, "Name", "Descriptor")));
  }

  /**
   * Method under test: {@link MemberObfuscator#newMemberName(Member)}
   */
  @Test
  void testNewMemberName2() {
    // Arrange
    LibraryField member = new LibraryField(1, "Name", "Descriptor");
    member.setProcessingInfo("Member");

    // Act and Assert
    assertEquals("Member", MemberObfuscator.newMemberName(member));
  }

  /**
   * Method under test: {@link MemberObfuscator#newMemberName(Member)}
   */
  @Test
  void testNewMemberName3() {
    // Arrange
    LibraryField libraryField = new LibraryField(1, "Name", "Descriptor");
    libraryField.setProcessingInfo(null);

    LibraryField member = new LibraryField(1, "Name", "Descriptor");
    member.setProcessingInfo(libraryField);

    // Act and Assert
    assertNull(MemberObfuscator.newMemberName(member));
  }
}
