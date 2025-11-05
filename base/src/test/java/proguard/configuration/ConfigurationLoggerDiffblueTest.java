package proguard.configuration;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;
import org.junit.jupiter.api.Test;

class ConfigurationLoggerDiffblueTest {
  /**
   * Method under test:
   * {@link ConfigurationLogger#checkGetDeclaredConstructor(Class, Class[], String)}
   */
  @Test
  void testCheckGetDeclaredConstructor() {
    // Arrange
    Class<Object> reflectedClass = Object.class;
    Class<Object> forNameResult = Object.class;
    Class[] constructorParameters = new Class[]{forNameResult};

    // Act
    ConfigurationLogger.checkGetDeclaredConstructor(reflectedClass, constructorParameters, "Calling Class Name");

    // Assert that nothing has changed
    assertEquals(1, constructorParameters.length);
    Class<Object> expectedResultClass = Object.class;
    Class resultClass = constructorParameters[0];
    assertEquals(expectedResultClass, resultClass);
    assertSame(forNameResult, resultClass);
  }

  /**
   * Method under test:
   * {@link ConfigurationLogger#checkGetConstructor(Class, Class[], String)}
   */
  @Test
  void testCheckGetConstructor() {
    // Arrange
    Class<Object> reflectedClass = Object.class;
    Class<Object> forNameResult = Object.class;
    Class[] constructorParameters = new Class[]{forNameResult};

    // Act
    ConfigurationLogger.checkGetConstructor(reflectedClass, constructorParameters, "Calling Class Name");

    // Assert that nothing has changed
    assertEquals(1, constructorParameters.length);
    Class<Object> expectedResultClass = Object.class;
    Class resultClass = constructorParameters[0];
    assertEquals(expectedResultClass, resultClass);
    assertSame(forNameResult, resultClass);
  }

  /**
   * Method under test:
   * {@link ConfigurationLogger#checkGetConstructor(String, Class, Class[], String)}
   */
  @Test
  void testCheckGetConstructor2() {
    // Arrange
    Class<Object> reflectedClass = Object.class;
    Class<Object> forNameResult = Object.class;
    Class[] constructorParameters = new Class[]{forNameResult};

    // Act
    ConfigurationLogger.checkGetConstructor("Reflection Method Name", reflectedClass, constructorParameters,
        "Calling Class Name");

    // Assert that nothing has changed
    assertEquals(1, constructorParameters.length);
    Class<Object> expectedResultClass = Object.class;
    Class resultClass = constructorParameters[0];
    assertEquals(expectedResultClass, resultClass);
    assertSame(forNameResult, resultClass);
  }

  /**
   * Method under test:
   * {@link ConfigurationLogger#checkGetDeclaredMethod(Class, String, Class[], String)}
   */
  @Test
  void testCheckGetDeclaredMethod() {
    // Arrange
    Class<Object> reflectedClass = Object.class;
    Class<Object> forNameResult = Object.class;
    Class[] reflectedMethodParameters = new Class[]{forNameResult};

    // Act
    ConfigurationLogger.checkGetDeclaredMethod(reflectedClass, "Reflected Method Name", reflectedMethodParameters,
        "Calling Class Name");

    // Assert that nothing has changed
    assertEquals(1, reflectedMethodParameters.length);
    Class<Object> expectedResultClass = Object.class;
    Class resultClass = reflectedMethodParameters[0];
    assertEquals(expectedResultClass, resultClass);
    assertSame(forNameResult, resultClass);
  }

  /**
   * Method under test:
   * {@link ConfigurationLogger#checkGetMethod(Class, String, Class[], String)}
   */
  @Test
  void testCheckGetMethod() {
    // Arrange
    Class<Object> reflectedClass = Object.class;
    Class<Object> forNameResult = Object.class;
    Class[] reflectedMethodParameters = new Class[]{forNameResult};

    // Act
    ConfigurationLogger.checkGetMethod(reflectedClass, "Reflected Method Name", reflectedMethodParameters,
        "Calling Class Name");

    // Assert that nothing has changed
    assertEquals(1, reflectedMethodParameters.length);
    Class<Object> expectedResultClass = Object.class;
    Class resultClass = reflectedMethodParameters[0];
    assertEquals(expectedResultClass, resultClass);
    assertSame(forNameResult, resultClass);
  }

  /**
   * Method under test:
   * {@link ConfigurationLogger.ClassInfo#ClassInfo(String, String, short, int[], byte[], int[], byte[])}
   */
  @Test
  void testClassInfoNewClassInfo() throws UnsupportedEncodingException {
    // Arrange
    byte[] fieldFlags = "AXAXAXAX".getBytes("UTF-8");

    // Act
    ConfigurationLogger.ClassInfo actualClassInfo = new ConfigurationLogger.ClassInfo("Original Class Name",
        "Super Class Name", (short) 1, new int[]{19088743, 1, 19088743, 1}, fieldFlags,
        new int[]{19088743, 1, 19088743, 1}, "AXAXAXAX".getBytes("UTF-8"));

    // Assert
    assertEquals("Original Class Name", actualClassInfo.originalClassName);
    assertEquals("Super Class Name", actualClassInfo.superClassName);
    assertEquals((short) 1, actualClassInfo.flags);
    assertArrayEquals("AXAXAXAX".getBytes("UTF-8"), actualClassInfo.fieldFlags);
    assertArrayEquals("AXAXAXAX".getBytes("UTF-8"), actualClassInfo.methodFlags);
    assertArrayEquals(new int[]{19088743, 1, 19088743, 1}, actualClassInfo.fieldHashes);
    assertArrayEquals(new int[]{19088743, 1, 19088743, 1}, actualClassInfo.methodHashes);
  }

  /**
   * Method under test: {@link ConfigurationLogger.ClassInfo#toString()}
   */
  @Test
  void testClassInfoToString() throws UnsupportedEncodingException {
    // Arrange
    byte[] fieldFlags = "AXAXAXAX".getBytes("UTF-8");

    // Act and Assert
    assertEquals("Original Class Name extends Super Class Name (kept, not shrunk) 4 fields, 4 methods",
        (new ConfigurationLogger.ClassInfo("Original Class Name", "Super Class Name", (short) 1,
            new int[]{19088743, 1, 19088743, 1}, fieldFlags, new int[]{19088743, 1, 19088743, 1},
            "AXAXAXAX".getBytes("UTF-8"))).toString());
  }

  /**
   * Method under test: {@link ConfigurationLogger.ClassInfo#toString()}
   */
  @Test
  void testClassInfoToString2() throws UnsupportedEncodingException {
    // Arrange
    byte[] fieldFlags = "AXAXAXAX".getBytes("UTF-8");

    // Act and Assert
    assertEquals("Original Class Name (kept, not shrunk) 4 fields, 4 methods",
        (new ConfigurationLogger.ClassInfo("Original Class Name", "", (short) 1, new int[]{19088743, 1, 19088743, 1},
            fieldFlags, new int[]{19088743, 1, 19088743, 1}, "AXAXAXAX".getBytes("UTF-8"))).toString());
  }

  /**
   * Method under test: {@link ConfigurationLogger.ClassInfo#toString()}
   */
  @Test
  void testClassInfoToString3() throws UnsupportedEncodingException {
    // Arrange
    byte[] fieldFlags = "AXAXAXAX".getBytes("UTF-8");

    // Act and Assert
    assertEquals("Original Class Name extends Super Class Name (not kept, shrunk) 4 fields, 4 methods",
        (new ConfigurationLogger.ClassInfo("Original Class Name", "Super Class Name", (short) 128,
            new int[]{19088743, 1, 19088743, 1}, fieldFlags, new int[]{19088743, 1, 19088743, 1},
            "AXAXAXAX".getBytes("UTF-8"))).toString());
  }

  /**
   * Method under test: {@link ConfigurationLogger#loadClassMap(InputStream, Map)}
   */
  @Test
  void testLoadClassMap() throws IOException {
    // Arrange
    ByteArrayInputStream inputStream = new ByteArrayInputStream(new byte[]{-1, 'X', 'A', 'X', 'A', 'X', 'A', 'X'});

    // Act
    ConfigurationLogger.loadClassMap(inputStream, new HashMap<>());

    // Assert that nothing has changed
    assertEquals(-1, inputStream.read(new byte[]{}));
  }

  /**
   * Method under test:
   * {@link ConfigurationLogger.MemberInfo#MemberInfo(String, byte)}
   */
  @Test
  void testMemberInfoNewMemberInfo() {
    // Arrange and Act
    ConfigurationLogger.MemberInfo actualMemberInfo = new ConfigurationLogger.MemberInfo("Declaring Class Name",
        (byte) 'A');

    // Assert
    assertEquals("Declaring Class Name", actualMemberInfo.declaringClassName);
    assertEquals('A', actualMemberInfo.flags);
  }

  /**
   * Method under test: {@link ConfigurationLogger.MemberInfo#toString()}
   */
  @Test
  void testMemberInfoToString() {
    // Arrange, Act and Assert
    assertEquals("Declaring Class Name (kept, not shrunk)",
        (new ConfigurationLogger.MemberInfo("Declaring Class Name", (byte) 'A')).toString());
    assertEquals("Declaring Class Name (not kept, shrunk)",
        (new ConfigurationLogger.MemberInfo("Declaring Class Name", (byte) 2)).toString());
  }
}
