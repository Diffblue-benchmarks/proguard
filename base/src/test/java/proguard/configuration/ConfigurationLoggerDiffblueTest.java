package proguard.configuration;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import proguard.configuration.ConfigurationLogger.ClassInfo;
import proguard.configuration.ConfigurationLogger.MemberInfo;

class ConfigurationLoggerDiffblueTest {
  /**
   * Test {@link ConfigurationLogger#checkGetDeclaredConstructor(Class, Class[], String)}.
   *
   * <ul>
   *   <li>When {@link Object}.
   *   <li>Then array length is one.
   * </ul>
   *
   * <p>Method under test: {@link ConfigurationLogger#checkGetDeclaredConstructor(Class, Class[],
   * String)}
   */
  @Test
  @DisplayName(
      "Test checkGetDeclaredConstructor(Class, Class[], String); when Object; then array length is one")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void ConfigurationLogger.checkGetDeclaredConstructor(Class, Class[], String)"
  })
  void testCheckGetDeclaredConstructor_whenObject_thenArrayLengthIsOne() {
    // Arrange
    Class<Object> reflectedClass = Object.class;
    Class<Object> forNameResult = Object.class;
    Class[] constructorParameters = new Class[] {forNameResult};

    // Act
    ConfigurationLogger.checkGetDeclaredConstructor(
        reflectedClass, constructorParameters, "Calling Class Name");

    // Assert
    assertEquals(1, constructorParameters.length);
    Class<Object> expectedResultClass = Object.class;
    assertEquals(expectedResultClass, constructorParameters[0]);
  }

  /**
   * Test {@link ConfigurationLogger#checkGetConstructor(Class, Class[], String)} with {@code
   * reflectedClass}, {@code constructorParameters}, {@code callingClassName}.
   *
   * <p>Method under test: {@link ConfigurationLogger#checkGetConstructor(Class, Class[], String)}
   */
  @Test
  @DisplayName(
      "Test checkGetConstructor(Class, Class[], String) with 'reflectedClass', 'constructorParameters', 'callingClassName'")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void ConfigurationLogger.checkGetConstructor(Class, Class[], String)"})
  void testCheckGetConstructorWithReflectedClassConstructorParametersCallingClassName() {
    // Arrange
    Class<Object> reflectedClass = Object.class;
    Class<Object> forNameResult = Object.class;
    Class[] constructorParameters = new Class[] {forNameResult};

    // Act
    ConfigurationLogger.checkGetConstructor(
        reflectedClass, constructorParameters, "Calling Class Name");

    // Assert
    assertEquals(1, constructorParameters.length);
    Class<Object> expectedResultClass = Object.class;
    assertEquals(expectedResultClass, constructorParameters[0]);
  }

  /**
   * Test {@link ConfigurationLogger#checkGetConstructor(String, Class, Class[], String)} with
   * {@code reflectionMethodName}, {@code reflectedClass}, {@code constructorParameters}, {@code
   * callingClassName}.
   *
   * <p>Method under test: {@link ConfigurationLogger#checkGetConstructor(String, Class, Class[],
   * String)}
   */
  @Test
  @DisplayName(
      "Test checkGetConstructor(String, Class, Class[], String) with 'reflectionMethodName', 'reflectedClass', 'constructorParameters', 'callingClassName'")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void ConfigurationLogger.checkGetConstructor(String, Class, Class[], String)"
  })
  void
      testCheckGetConstructorWithReflectionMethodNameReflectedClassConstructorParametersCallingClassName() {
    // Arrange
    Class<Object> reflectedClass = Object.class;
    Class<Object> forNameResult = Object.class;
    Class[] constructorParameters = new Class[] {forNameResult};

    // Act
    ConfigurationLogger.checkGetConstructor(
        "Reflection Method Name", reflectedClass, constructorParameters, "Calling Class Name");

    // Assert
    assertEquals(1, constructorParameters.length);
    Class<Object> expectedResultClass = Object.class;
    assertEquals(expectedResultClass, constructorParameters[0]);
  }

  /**
   * Test {@link ConfigurationLogger#checkGetDeclaredMethod(Class, String, Class[], String)}.
   *
   * <ul>
   *   <li>When {@link Object}.
   *   <li>Then array length is one.
   * </ul>
   *
   * <p>Method under test: {@link ConfigurationLogger#checkGetDeclaredMethod(Class, String, Class[],
   * String)}
   */
  @Test
  @DisplayName(
      "Test checkGetDeclaredMethod(Class, String, Class[], String); when Object; then array length is one")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void ConfigurationLogger.checkGetDeclaredMethod(Class, String, Class[], String)"
  })
  void testCheckGetDeclaredMethod_whenObject_thenArrayLengthIsOne() {
    // Arrange
    Class<Object> reflectedClass = Object.class;
    Class<Object> forNameResult = Object.class;
    Class[] reflectedMethodParameters = new Class[] {forNameResult};

    // Act
    ConfigurationLogger.checkGetDeclaredMethod(
        reflectedClass, "Reflected Method Name", reflectedMethodParameters, "Calling Class Name");

    // Assert
    assertEquals(1, reflectedMethodParameters.length);
    Class<Object> expectedResultClass = Object.class;
    assertEquals(expectedResultClass, reflectedMethodParameters[0]);
  }

  /**
   * Test {@link ConfigurationLogger#checkGetMethod(Class, String, Class[], String)} with {@code
   * reflectedClass}, {@code reflectedMethodName}, {@code reflectedMethodParameters}, {@code
   * callingClassName}.
   *
   * <p>Method under test: {@link ConfigurationLogger#checkGetMethod(Class, String, Class[],
   * String)}
   */
  @Test
  @DisplayName(
      "Test checkGetMethod(Class, String, Class[], String) with 'reflectedClass', 'reflectedMethodName', 'reflectedMethodParameters', 'callingClassName'")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void ConfigurationLogger.checkGetMethod(Class, String, Class[], String)"})
  void
      testCheckGetMethodWithReflectedClassReflectedMethodNameReflectedMethodParametersCallingClassName() {
    // Arrange
    Class<Object> reflectedClass = Object.class;
    Class<Object> forNameResult = Object.class;
    Class[] reflectedMethodParameters = new Class[] {forNameResult};

    // Act
    ConfigurationLogger.checkGetMethod(
        reflectedClass, "Reflected Method Name", reflectedMethodParameters, "Calling Class Name");

    // Assert
    assertEquals(1, reflectedMethodParameters.length);
    Class<Object> expectedResultClass = Object.class;
    assertEquals(expectedResultClass, reflectedMethodParameters[0]);
  }

  /**
   * Test ClassInfo {@link ClassInfo#ClassInfo(String, String, short, int[], byte[], int[],
   * byte[])}.
   *
   * <p>Method under test: {@link ClassInfo#ClassInfo(String, String, short, int[], byte[], int[],
   * byte[])}
   */
  @Test
  @DisplayName("Test ClassInfo new ClassInfo(String, String, short, int[], byte[], int[], byte[])")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void ClassInfo.<init>(String, String, short, int[], byte[], int[], byte[])"})
  void testClassInfoNewClassInfo() throws UnsupportedEncodingException {
    // Arrange
    byte[] fieldFlags = "AXAXAXAX".getBytes("UTF-8");

    // Act
    ClassInfo actualClassInfo =
        new ClassInfo(
            "Original Class Name",
            "Super Class Name",
            (short) 1,
            new int[] {19088743, 1, 19088743, 1},
            fieldFlags,
            new int[] {19088743, 1, 19088743, 1},
            "AXAXAXAX".getBytes("UTF-8"));

    // Assert
    assertEquals("Original Class Name", actualClassInfo.originalClassName);
    assertEquals("Super Class Name", actualClassInfo.superClassName);
    assertEquals((short) 1, actualClassInfo.flags);
    assertArrayEquals("AXAXAXAX".getBytes("UTF-8"), actualClassInfo.fieldFlags);
    assertArrayEquals("AXAXAXAX".getBytes("UTF-8"), actualClassInfo.methodFlags);
    assertArrayEquals(new int[] {19088743, 1, 19088743, 1}, actualClassInfo.fieldHashes);
    assertArrayEquals(new int[] {19088743, 1, 19088743, 1}, actualClassInfo.methodHashes);
  }

  /**
   * Test ClassInfo {@link ClassInfo#toString()}.
   *
   * <p>Method under test: {@link ClassInfo#toString()}
   */
  @Test
  @DisplayName("Test ClassInfo toString()")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"String ClassInfo.toString()"})
  void testClassInfoToString() throws UnsupportedEncodingException {
    // Arrange
    byte[] fieldFlags = "AXAXAXAX".getBytes("UTF-8");

    // Act and Assert
    assertEquals(
        "Original Class Name extends Super Class Name (kept, not shrunk) 4 fields, 4 methods",
        new ClassInfo(
                "Original Class Name",
                "Super Class Name",
                (short) 1,
                new int[] {19088743, 1, 19088743, 1},
                fieldFlags,
                new int[] {19088743, 1, 19088743, 1},
                "AXAXAXAX".getBytes("UTF-8"))
            .toString());
  }

  /**
   * Test ClassInfo {@link ClassInfo#toString()}.
   *
   * <p>Method under test: {@link ClassInfo#toString()}
   */
  @Test
  @DisplayName("Test ClassInfo toString()")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"String ClassInfo.toString()"})
  void testClassInfoToString2() throws UnsupportedEncodingException {
    // Arrange
    byte[] fieldFlags = "AXAXAXAX".getBytes("UTF-8");

    // Act and Assert
    assertEquals(
        "Original Class Name extends Super Class Name (not kept, shrunk) 4 fields, 4 methods",
        new ClassInfo(
                "Original Class Name",
                "Super Class Name",
                (short) 128,
                new int[] {19088743, 1, 19088743, 1},
                fieldFlags,
                new int[] {19088743, 1, 19088743, 1},
                "AXAXAXAX".getBytes("UTF-8"))
            .toString());
  }

  /**
   * Test ClassInfo {@link ClassInfo#toString()}.
   *
   * <ul>
   *   <li>Then return {@code Original Class Name (kept, not shrunk) 4 fields, 4 methods}.
   * </ul>
   *
   * <p>Method under test: {@link ClassInfo#toString()}
   */
  @Test
  @DisplayName(
      "Test ClassInfo toString(); then return 'Original Class Name (kept, not shrunk) 4 fields, 4 methods'")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"String ClassInfo.toString()"})
  void testClassInfoToString_thenReturnOriginalClassNameKeptNotShrunk4Fields4Methods()
      throws UnsupportedEncodingException {
    // Arrange
    byte[] fieldFlags = "AXAXAXAX".getBytes("UTF-8");

    // Act and Assert
    assertEquals(
        "Original Class Name (kept, not shrunk) 4 fields, 4 methods",
        new ClassInfo(
                "Original Class Name",
                "",
                (short) 1,
                new int[] {19088743, 1, 19088743, 1},
                fieldFlags,
                new int[] {19088743, 1, 19088743, 1},
                "AXAXAXAX".getBytes("UTF-8"))
            .toString());
  }

  /**
   * Test {@link ConfigurationLogger#loadClassMap(InputStream, Map)}.
   *
   * <p>Method under test: {@link ConfigurationLogger#loadClassMap(InputStream, Map)}
   */
  @Test
  @DisplayName("Test loadClassMap(InputStream, Map)")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void ConfigurationLogger.loadClassMap(InputStream, Map)"})
  void testLoadClassMap() throws IOException {
    // Arrange
    ByteArrayInputStream inputStream =
        new ByteArrayInputStream(new byte[] {-1, 'X', 'A', 'X', 'A', 'X', 'A', 'X'});

    // Act
    ConfigurationLogger.loadClassMap(inputStream, new HashMap<>());

    // Assert
    assertEquals(-1, inputStream.read(new byte[] {}));
  }

  /**
   * Test {@link ConfigurationLogger#loadClassMap(InputStream, Map)}.
   *
   * <ul>
   *   <li>When {@code null}.
   *   <li>Then {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link ConfigurationLogger#loadClassMap(InputStream, Map)}
   */
  @Test
  @DisplayName("Test loadClassMap(InputStream, Map); when 'null'; then 'null'")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void ConfigurationLogger.loadClassMap(InputStream, Map)"})
  void testLoadClassMap_whenNull_thenNull() throws IOException {
    // Arrange and Act
    ConfigurationLogger.loadClassMap(null, new HashMap<>());

    // Assert that nothing has changed
    assertNull(null);
  }

  /**
   * Test MemberInfo {@link MemberInfo#MemberInfo(String, byte)}.
   *
   * <p>Method under test: {@link MemberInfo#MemberInfo(String, byte)}
   */
  @Test
  @DisplayName("Test MemberInfo new MemberInfo(String, byte)")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void MemberInfo.<init>(String, byte)"})
  void testMemberInfoNewMemberInfo() {
    // Arrange and Act
    MemberInfo actualMemberInfo = new MemberInfo("Declaring Class Name", (byte) 'A');

    // Assert
    assertEquals("Declaring Class Name", actualMemberInfo.declaringClassName);
    assertEquals('A', actualMemberInfo.flags);
  }

  /**
   * Test MemberInfo {@link MemberInfo#toString()}.
   *
   * <ul>
   *   <li>Then return {@code Declaring Class Name (kept, not shrunk)}.
   * </ul>
   *
   * <p>Method under test: {@link MemberInfo#toString()}
   */
  @Test
  @DisplayName("Test MemberInfo toString(); then return 'Declaring Class Name (kept, not shrunk)'")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"String MemberInfo.toString()"})
  void testMemberInfoToString_thenReturnDeclaringClassNameKeptNotShrunk() {
    // Arrange, Act and Assert
    assertEquals(
        "Declaring Class Name (kept, not shrunk)",
        new MemberInfo("Declaring Class Name", (byte) 'A').toString());
  }

  /**
   * Test MemberInfo {@link MemberInfo#toString()}.
   *
   * <ul>
   *   <li>Then return {@code Declaring Class Name (not kept, shrunk)}.
   * </ul>
   *
   * <p>Method under test: {@link MemberInfo#toString()}
   */
  @Test
  @DisplayName("Test MemberInfo toString(); then return 'Declaring Class Name (not kept, shrunk)'")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"String MemberInfo.toString()"})
  void testMemberInfoToString_thenReturnDeclaringClassNameNotKeptShrunk() {
    // Arrange, Act and Assert
    assertEquals(
        "Declaring Class Name (not kept, shrunk)",
        new MemberInfo("Declaring Class Name", (byte) 2).toString());
  }
}
