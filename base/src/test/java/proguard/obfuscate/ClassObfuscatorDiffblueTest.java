package proguard.obfuscate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import com.diffblue.cover.annotations.MethodsUnderTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import proguard.classfile.Clazz;
import proguard.classfile.LibraryClass;

class ClassObfuscatorDiffblueTest {
  /**
   * Test {@link ClassObfuscator#setNewClassName(Clazz, String)}.
   * <ul>
   *   <li>When {@link LibraryClass#LibraryClass()}.</li>
   *   <li>Then {@link LibraryClass#LibraryClass()} ProcessingInfo is {@code Name}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ClassObfuscator#setNewClassName(Clazz, String)}
   */
  @Test
  @DisplayName("Test setNewClassName(Clazz, String); when LibraryClass(); then LibraryClass() ProcessingInfo is 'Name'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "void proguard.obfuscate.ClassObfuscator.setNewClassName(proguard.classfile.Clazz, java.lang.String)"})
  void testSetNewClassName_whenLibraryClass_thenLibraryClassProcessingInfoIsName() {
    // Arrange
    LibraryClass clazz = new LibraryClass();

    // Act
    ClassObfuscator.setNewClassName(clazz, "Name");

    // Assert
    assertEquals("Name", clazz.getProcessingInfo());
  }

  /**
   * Test {@link ClassObfuscator#hasOriginalClassName(Clazz)}.
   * <ul>
   *   <li>Then return {@code false}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ClassObfuscator#hasOriginalClassName(Clazz)}
   */
  @Test
  @DisplayName("Test hasOriginalClassName(Clazz); then return 'false'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean proguard.obfuscate.ClassObfuscator.hasOriginalClassName(proguard.classfile.Clazz)"})
  void testHasOriginalClassName_thenReturnFalse() {
    // Arrange, Act and Assert
    assertFalse(ClassObfuscator.hasOriginalClassName(new LibraryClass(1, "This Class Name", "Super Class Name")));
  }

  /**
   * Test {@link ClassObfuscator#newClassName(Clazz)}.
   * <ul>
   *   <li>Given {@code Clazz}.</li>
   *   <li>Then return {@code Clazz}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ClassObfuscator#newClassName(Clazz)}
   */
  @Test
  @DisplayName("Test newClassName(Clazz); given 'Clazz'; then return 'Clazz'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"java.lang.String proguard.obfuscate.ClassObfuscator.newClassName(proguard.classfile.Clazz)"})
  void testNewClassName_givenClazz_thenReturnClazz() {
    // Arrange
    LibraryClass clazz = new LibraryClass();
    clazz.setProcessingInfo("Clazz");

    // Act and Assert
    assertEquals("Clazz", ClassObfuscator.newClassName(clazz));
  }

  /**
   * Test {@link ClassObfuscator#newClassName(Clazz)}.
   * <ul>
   *   <li>When {@link LibraryClass#LibraryClass()}.</li>
   *   <li>Then return {@code null}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ClassObfuscator#newClassName(Clazz)}
   */
  @Test
  @DisplayName("Test newClassName(Clazz); when LibraryClass(); then return 'null'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"java.lang.String proguard.obfuscate.ClassObfuscator.newClassName(proguard.classfile.Clazz)"})
  void testNewClassName_whenLibraryClass_thenReturnNull() {
    // Arrange, Act and Assert
    assertNull(ClassObfuscator.newClassName(new LibraryClass()));
  }
}
