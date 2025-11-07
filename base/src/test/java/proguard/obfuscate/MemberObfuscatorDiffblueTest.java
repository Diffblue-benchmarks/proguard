package proguard.obfuscate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.util.HashMap;
import java.util.Map;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import proguard.classfile.Clazz;
import proguard.classfile.LibraryClass;
import proguard.classfile.LibraryField;
import proguard.classfile.Member;
import proguard.classfile.ProgramField;

class MemberObfuscatorDiffblueTest {
  /**
   * Test {@link MemberObfuscator#visitAnyMember(Clazz, Member)}.
   * <p>
   * Method under test: {@link MemberObfuscator#visitAnyMember(Clazz, Member)}
   */
  @Test
  @DisplayName("Test visitAnyMember(Clazz, Member)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void MemberObfuscator.visitAnyMember(Clazz, Member)"})
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
   * Test {@link MemberObfuscator#visitAnyMember(Clazz, Member)}.
   * <p>
   * Method under test: {@link MemberObfuscator#visitAnyMember(Clazz, Member)}
   */
  @Test
  @DisplayName("Test visitAnyMember(Clazz, Member)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void MemberObfuscator.visitAnyMember(Clazz, Member)"})
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
   * Test {@link MemberObfuscator#retrieveNameMap(Map, String)}.
   * <p>
   * Method under test: {@link MemberObfuscator#retrieveNameMap(Map, String)}
   */
  @Test
  @DisplayName("Test retrieveNameMap(Map, String)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"Map MemberObfuscator.retrieveNameMap(Map, String)"})
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
   * Test {@link MemberObfuscator#setFixedNewMemberName(Member, String)}.
   * <p>
   * Method under test: {@link MemberObfuscator#setFixedNewMemberName(Member, String)}
   */
  @Test
  @DisplayName("Test setFixedNewMemberName(Member, String)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void MemberObfuscator.setFixedNewMemberName(Member, String)"})
  void testSetFixedNewMemberName() {
    // Arrange
    LibraryField member = new LibraryField(1, "Name", "Descriptor");

    // Act
    MemberObfuscator.setFixedNewMemberName(member, "Name");

    // Assert
    assertEquals("Name", member.getProcessingInfo());
  }

  /**
   * Test {@link MemberObfuscator#setFixedNewMemberName(Member, String)}.
   * <p>
   * Method under test: {@link MemberObfuscator#setFixedNewMemberName(Member, String)}
   */
  @Test
  @DisplayName("Test setFixedNewMemberName(Member, String)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void MemberObfuscator.setFixedNewMemberName(Member, String)"})
  void testSetFixedNewMemberName2() {
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
   * Test {@link MemberObfuscator#setFixedNewMemberName(Member, String)}.
   * <ul>
   *   <li>Given {@code Member}.</li>
   * </ul>
   * <p>
   * Method under test: {@link MemberObfuscator#setFixedNewMemberName(Member, String)}
   */
  @Test
  @DisplayName("Test setFixedNewMemberName(Member, String); given 'Member'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void MemberObfuscator.setFixedNewMemberName(Member, String)"})
  void testSetFixedNewMemberName_givenMember() {
    // Arrange
    LibraryField member = new LibraryField(1, "Name", "Descriptor");
    member.setProcessingInfo("Member");

    // Act
    MemberObfuscator.setFixedNewMemberName(member, "Name");

    // Assert
    assertEquals("Name", member.getProcessingInfo());
  }

  /**
   * Test {@link MemberObfuscator#setNewMemberName(Member, String)}.
   * <p>
   * Method under test: {@link MemberObfuscator#setNewMemberName(Member, String)}
   */
  @Test
  @DisplayName("Test setNewMemberName(Member, String)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void MemberObfuscator.setNewMemberName(Member, String)"})
  void testSetNewMemberName() {
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
   * Test {@link MemberObfuscator#setNewMemberName(Member, String)}.
   * <ul>
   *   <li>Given {@code Member}.</li>
   * </ul>
   * <p>
   * Method under test: {@link MemberObfuscator#setNewMemberName(Member, String)}
   */
  @Test
  @DisplayName("Test setNewMemberName(Member, String); given 'Member'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void MemberObfuscator.setNewMemberName(Member, String)"})
  void testSetNewMemberName_givenMember() {
    // Arrange
    LibraryField member = new LibraryField(1, "Name", "Descriptor");
    member.setProcessingInfo("Member");

    // Act
    MemberObfuscator.setNewMemberName(member, "Name");

    // Assert
    assertEquals("Name", member.getProcessingInfo());
  }

  /**
   * Test {@link MemberObfuscator#setNewMemberName(Member, String)}.
   * <ul>
   *   <li>When {@link LibraryField#LibraryField(int, String, String)} with u2accessFlags is one and {@code Name} and {@code Descriptor}.</li>
   * </ul>
   * <p>
   * Method under test: {@link MemberObfuscator#setNewMemberName(Member, String)}
   */
  @Test
  @DisplayName("Test setNewMemberName(Member, String); when LibraryField(int, String, String) with u2accessFlags is one and 'Name' and 'Descriptor'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void MemberObfuscator.setNewMemberName(Member, String)"})
  void testSetNewMemberName_whenLibraryFieldWithU2accessFlagsIsOneAndNameAndDescriptor() {
    // Arrange
    LibraryField member = new LibraryField(1, "Name", "Descriptor");

    // Act
    MemberObfuscator.setNewMemberName(member, "Name");

    // Assert
    assertEquals("Name", member.getProcessingInfo());
  }

  /**
   * Test {@link MemberObfuscator#hasFixedNewMemberName(Member)}.
   * <p>
   * Method under test: {@link MemberObfuscator#hasFixedNewMemberName(Member)}
   */
  @Test
  @DisplayName("Test hasFixedNewMemberName(Member)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean MemberObfuscator.hasFixedNewMemberName(Member)"})
  void testHasFixedNewMemberName() {
    // Arrange, Act and Assert
    assertTrue(MemberObfuscator.hasFixedNewMemberName(new LibraryField(1, "Name", "Descriptor")));
  }

  /**
   * Test {@link MemberObfuscator#hasFixedNewMemberName(Member)}.
   * <p>
   * Method under test: {@link MemberObfuscator#hasFixedNewMemberName(Member)}
   */
  @Test
  @DisplayName("Test hasFixedNewMemberName(Member)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean MemberObfuscator.hasFixedNewMemberName(Member)"})
  void testHasFixedNewMemberName2() {
    // Arrange
    LibraryField libraryField = new LibraryField(1, "Name", "Descriptor");
    libraryField.setProcessingInfo(null);

    LibraryField member = new LibraryField(1, "Name", "Descriptor");
    member.setProcessingInfo(libraryField);

    // Act and Assert
    assertTrue(MemberObfuscator.hasFixedNewMemberName(member));
  }

  /**
   * Test {@link MemberObfuscator#hasFixedNewMemberName(Member)}.
   * <ul>
   *   <li>Given {@code Member}.</li>
   * </ul>
   * <p>
   * Method under test: {@link MemberObfuscator#hasFixedNewMemberName(Member)}
   */
  @Test
  @DisplayName("Test hasFixedNewMemberName(Member); given 'Member'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean MemberObfuscator.hasFixedNewMemberName(Member)"})
  void testHasFixedNewMemberName_givenMember() {
    // Arrange
    LibraryField member = new LibraryField(1, "Name", "Descriptor");
    member.setProcessingInfo("Member");

    // Act and Assert
    assertTrue(MemberObfuscator.hasFixedNewMemberName(member));
  }

  /**
   * Test {@link MemberObfuscator#hasFixedNewMemberName(Member)}.
   * <ul>
   *   <li>When {@link ProgramField#ProgramField()}.</li>
   *   <li>Then return {@code false}.</li>
   * </ul>
   * <p>
   * Method under test: {@link MemberObfuscator#hasFixedNewMemberName(Member)}
   */
  @Test
  @DisplayName("Test hasFixedNewMemberName(Member); when ProgramField(); then return 'false'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean MemberObfuscator.hasFixedNewMemberName(Member)"})
  void testHasFixedNewMemberName_whenProgramField_thenReturnFalse() {
    // Arrange, Act and Assert
    assertFalse(MemberObfuscator.hasFixedNewMemberName(new ProgramField()));
  }

  /**
   * Test {@link MemberObfuscator#newMemberName(Member)}.
   * <p>
   * Method under test: {@link MemberObfuscator#newMemberName(Member)}
   */
  @Test
  @DisplayName("Test newMemberName(Member)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"String MemberObfuscator.newMemberName(Member)"})
  void testNewMemberName() {
    // Arrange
    LibraryField libraryField = new LibraryField(1, "Name", "Descriptor");
    libraryField.setProcessingInfo(null);

    LibraryField member = new LibraryField(1, "Name", "Descriptor");
    member.setProcessingInfo(libraryField);

    // Act and Assert
    assertNull(MemberObfuscator.newMemberName(member));
  }

  /**
   * Test {@link MemberObfuscator#newMemberName(Member)}.
   * <ul>
   *   <li>Given {@code Member}.</li>
   *   <li>Then return {@code Member}.</li>
   * </ul>
   * <p>
   * Method under test: {@link MemberObfuscator#newMemberName(Member)}
   */
  @Test
  @DisplayName("Test newMemberName(Member); given 'Member'; then return 'Member'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"String MemberObfuscator.newMemberName(Member)"})
  void testNewMemberName_givenMember_thenReturnMember() {
    // Arrange
    LibraryField member = new LibraryField(1, "Name", "Descriptor");
    member.setProcessingInfo("Member");

    // Act and Assert
    assertEquals("Member", MemberObfuscator.newMemberName(member));
  }

  /**
   * Test {@link MemberObfuscator#newMemberName(Member)}.
   * <ul>
   *   <li>When {@link LibraryField#LibraryField(int, String, String)} with u2accessFlags is one and {@code Name} and {@code Descriptor}.</li>
   * </ul>
   * <p>
   * Method under test: {@link MemberObfuscator#newMemberName(Member)}
   */
  @Test
  @DisplayName("Test newMemberName(Member); when LibraryField(int, String, String) with u2accessFlags is one and 'Name' and 'Descriptor'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"String MemberObfuscator.newMemberName(Member)"})
  void testNewMemberName_whenLibraryFieldWithU2accessFlagsIsOneAndNameAndDescriptor() {
    // Arrange, Act and Assert
    assertNull(MemberObfuscator.newMemberName(new LibraryField(1, "Name", "Descriptor")));
  }
}
