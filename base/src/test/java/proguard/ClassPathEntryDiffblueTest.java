package proguard;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import com.google.gson.internal.NonNullElementWrapperList;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Test;

class ClassPathEntryDiffblueTest {
  /**
   * Method under test: {@link ClassPathEntry#getName()}
   */
  @Test
  void testGetName() {
    // Arrange, Act and Assert
    assertEquals(System.getProperty("user.dir"), (new ClassPathEntry(Configuration.STD_OUT, true)).getName());
  }

  /**
   * Method under test: {@link ClassPathEntry#isDex()}
   */
  @Test
  void testIsDex() {
    // Arrange, Act and Assert
    assertFalse((new ClassPathEntry(Configuration.STD_OUT, true)).isDex());
  }

  /**
   * Method under test: {@link ClassPathEntry#isApk()}
   */
  @Test
  void testIsApk() {
    // Arrange, Act and Assert
    assertFalse((new ClassPathEntry(Configuration.STD_OUT, true)).isApk());
  }

  /**
   * Method under test: {@link ClassPathEntry#isAab()}
   */
  @Test
  void testIsAab() {
    // Arrange, Act and Assert
    assertFalse((new ClassPathEntry(Configuration.STD_OUT, true)).isAab());
  }

  /**
   * Method under test: {@link ClassPathEntry#isJar()}
   */
  @Test
  void testIsJar() {
    // Arrange, Act and Assert
    assertFalse((new ClassPathEntry(Configuration.STD_OUT, true)).isJar());
  }

  /**
   * Method under test: {@link ClassPathEntry#isAar()}
   */
  @Test
  void testIsAar() {
    // Arrange, Act and Assert
    assertFalse((new ClassPathEntry(Configuration.STD_OUT, true)).isAar());
  }

  /**
   * Method under test: {@link ClassPathEntry#isWar()}
   */
  @Test
  void testIsWar() {
    // Arrange, Act and Assert
    assertFalse((new ClassPathEntry(Configuration.STD_OUT, true)).isWar());
  }

  /**
   * Method under test: {@link ClassPathEntry#isEar()}
   */
  @Test
  void testIsEar() {
    // Arrange, Act and Assert
    assertFalse((new ClassPathEntry(Configuration.STD_OUT, true)).isEar());
  }

  /**
   * Method under test: {@link ClassPathEntry#isJmod()}
   */
  @Test
  void testIsJmod() {
    // Arrange, Act and Assert
    assertFalse((new ClassPathEntry(Configuration.STD_OUT, true)).isJmod());
  }

  /**
   * Method under test: {@link ClassPathEntry#isZip()}
   */
  @Test
  void testIsZip() {
    // Arrange, Act and Assert
    assertFalse((new ClassPathEntry(Configuration.STD_OUT, true)).isZip());
  }

  /**
   * Method under test: {@link ClassPathEntry#isFiltered()}
   */
  @Test
  void testIsFiltered() {
    // Arrange, Act and Assert
    assertFalse((new ClassPathEntry(Configuration.STD_OUT, true)).isFiltered());
  }

  /**
   * Method under test: {@link ClassPathEntry#isFiltered()}
   */
  @Test
  void testIsFiltered2() {
    // Arrange
    NonNullElementWrapperList<String> filter = mock(NonNullElementWrapperList.class);
    when(filter.size()).thenReturn(3);

    ClassPathEntry classPathEntry = new ClassPathEntry(Configuration.STD_OUT, true);
    classPathEntry.setFilter(filter);
    classPathEntry.setApkFilter(null);
    classPathEntry.setAabFilter(null);
    classPathEntry.setJarFilter(null);
    classPathEntry.setAarFilter(null);
    classPathEntry.setWarFilter(null);
    classPathEntry.setEarFilter(null);
    classPathEntry.setJmodFilter(null);
    classPathEntry.setZipFilter(null);

    // Act
    boolean actualIsFilteredResult = classPathEntry.isFiltered();

    // Assert
    verify(filter).size();
    assertTrue(actualIsFilteredResult);
  }

  /**
   * Method under test: {@link ClassPathEntry#setFilter(List)}
   */
  @Test
  void testSetFilter() {
    // Arrange
    ClassPathEntry classPathEntry = new ClassPathEntry(Configuration.STD_OUT, true);

    // Act
    classPathEntry.setFilter(new ArrayList<>());

    // Assert
    assertNull(classPathEntry.getFilter());
    assertFalse(classPathEntry.isFiltered());
  }

  /**
   * Method under test: {@link ClassPathEntry#setFilter(List)}
   */
  @Test
  void testSetFilter2() {
    // Arrange
    ClassPathEntry classPathEntry = new ClassPathEntry(Configuration.STD_OUT, true);

    // Act
    classPathEntry.setFilter(null);

    // Assert
    assertNull(classPathEntry.getFilter());
    assertFalse(classPathEntry.isFiltered());
  }

  /**
   * Method under test: {@link ClassPathEntry#setFilter(List)}
   */
  @Test
  void testSetFilter3() {
    // Arrange
    ClassPathEntry classPathEntry = new ClassPathEntry(Configuration.STD_OUT, true);

    ArrayList<String> filter = new ArrayList<>();
    filter.add("foo");

    // Act
    classPathEntry.setFilter(filter);

    // Assert
    assertTrue(classPathEntry.isFiltered());
    assertSame(filter, classPathEntry.getFilter());
  }

  /**
   * Method under test: {@link ClassPathEntry#setFilter(List)}
   */
  @Test
  void testSetFilter4() {
    // Arrange
    ClassPathEntry classPathEntry = new ClassPathEntry(Configuration.STD_OUT, true);

    ArrayList<String> filter = new ArrayList<>();
    filter.add("42");
    filter.add("foo");

    // Act
    classPathEntry.setFilter(filter);

    // Assert
    assertTrue(classPathEntry.isFiltered());
    assertSame(filter, classPathEntry.getFilter());
  }

  /**
   * Method under test: {@link ClassPathEntry#setFilter(List)}
   */
  @Test
  void testSetFilter5() {
    // Arrange
    ClassPathEntry classPathEntry = new ClassPathEntry(Configuration.STD_OUT, true);
    NonNullElementWrapperList<String> filter = mock(NonNullElementWrapperList.class);
    when(filter.size()).thenReturn(3);

    // Act
    classPathEntry.setFilter(filter);

    // Assert
    verify(filter).size();
    assertTrue(classPathEntry.isFiltered());
    assertSame(filter, classPathEntry.getFilter());
  }

  /**
   * Method under test: {@link ClassPathEntry#setApkFilter(List)}
   */
  @Test
  void testSetApkFilter() {
    // Arrange
    ClassPathEntry classPathEntry = new ClassPathEntry(Configuration.STD_OUT, true);

    // Act
    classPathEntry.setApkFilter(new ArrayList<>());

    // Assert
    assertNull(classPathEntry.getApkFilter());
    assertFalse(classPathEntry.isFiltered());
  }

  /**
   * Method under test: {@link ClassPathEntry#setApkFilter(List)}
   */
  @Test
  void testSetApkFilter2() {
    // Arrange
    ClassPathEntry classPathEntry = new ClassPathEntry(Configuration.STD_OUT, true);

    // Act
    classPathEntry.setApkFilter(null);

    // Assert
    assertNull(classPathEntry.getApkFilter());
    assertFalse(classPathEntry.isFiltered());
  }

  /**
   * Method under test: {@link ClassPathEntry#setApkFilter(List)}
   */
  @Test
  void testSetApkFilter3() {
    // Arrange
    ClassPathEntry classPathEntry = new ClassPathEntry(Configuration.STD_OUT, true);

    ArrayList<String> filter = new ArrayList<>();
    filter.add("foo");

    // Act
    classPathEntry.setApkFilter(filter);

    // Assert
    assertTrue(classPathEntry.isFiltered());
    assertSame(filter, classPathEntry.getApkFilter());
  }

  /**
   * Method under test: {@link ClassPathEntry#setApkFilter(List)}
   */
  @Test
  void testSetApkFilter4() {
    // Arrange
    ClassPathEntry classPathEntry = new ClassPathEntry(Configuration.STD_OUT, true);

    ArrayList<String> filter = new ArrayList<>();
    filter.add("42");
    filter.add("foo");

    // Act
    classPathEntry.setApkFilter(filter);

    // Assert
    assertTrue(classPathEntry.isFiltered());
    assertSame(filter, classPathEntry.getApkFilter());
  }

  /**
   * Method under test: {@link ClassPathEntry#setApkFilter(List)}
   */
  @Test
  void testSetApkFilter5() {
    // Arrange
    ClassPathEntry classPathEntry = new ClassPathEntry(Configuration.STD_OUT, true);
    NonNullElementWrapperList<String> filter = mock(NonNullElementWrapperList.class);
    when(filter.size()).thenReturn(3);

    // Act
    classPathEntry.setApkFilter(filter);

    // Assert
    verify(filter).size();
    assertTrue(classPathEntry.isFiltered());
    assertSame(filter, classPathEntry.getApkFilter());
  }

  /**
   * Method under test: {@link ClassPathEntry#setAabFilter(List)}
   */
  @Test
  void testSetAabFilter() {
    // Arrange
    ClassPathEntry classPathEntry = new ClassPathEntry(Configuration.STD_OUT, true);

    // Act
    classPathEntry.setAabFilter(new ArrayList<>());

    // Assert
    assertNull(classPathEntry.getAabFilter());
    assertFalse(classPathEntry.isFiltered());
  }

  /**
   * Method under test: {@link ClassPathEntry#setAabFilter(List)}
   */
  @Test
  void testSetAabFilter2() {
    // Arrange
    ClassPathEntry classPathEntry = new ClassPathEntry(Configuration.STD_OUT, true);

    // Act
    classPathEntry.setAabFilter(null);

    // Assert
    assertNull(classPathEntry.getAabFilter());
    assertFalse(classPathEntry.isFiltered());
  }

  /**
   * Method under test: {@link ClassPathEntry#setAabFilter(List)}
   */
  @Test
  void testSetAabFilter3() {
    // Arrange
    ClassPathEntry classPathEntry = new ClassPathEntry(Configuration.STD_OUT, true);

    ArrayList<String> filter = new ArrayList<>();
    filter.add("foo");

    // Act
    classPathEntry.setAabFilter(filter);

    // Assert
    assertTrue(classPathEntry.isFiltered());
    assertSame(filter, classPathEntry.getAabFilter());
  }

  /**
   * Method under test: {@link ClassPathEntry#setAabFilter(List)}
   */
  @Test
  void testSetAabFilter4() {
    // Arrange
    ClassPathEntry classPathEntry = new ClassPathEntry(Configuration.STD_OUT, true);

    ArrayList<String> filter = new ArrayList<>();
    filter.add("42");
    filter.add("foo");

    // Act
    classPathEntry.setAabFilter(filter);

    // Assert
    assertTrue(classPathEntry.isFiltered());
    assertSame(filter, classPathEntry.getAabFilter());
  }

  /**
   * Method under test: {@link ClassPathEntry#setAabFilter(List)}
   */
  @Test
  void testSetAabFilter5() {
    // Arrange
    ClassPathEntry classPathEntry = new ClassPathEntry(Configuration.STD_OUT, true);
    NonNullElementWrapperList<String> filter = mock(NonNullElementWrapperList.class);
    when(filter.size()).thenReturn(3);

    // Act
    classPathEntry.setAabFilter(filter);

    // Assert
    verify(filter).size();
    assertTrue(classPathEntry.isFiltered());
    assertSame(filter, classPathEntry.getAabFilter());
  }

  /**
   * Method under test: {@link ClassPathEntry#setJarFilter(List)}
   */
  @Test
  void testSetJarFilter() {
    // Arrange
    ClassPathEntry classPathEntry = new ClassPathEntry(Configuration.STD_OUT, true);

    // Act
    classPathEntry.setJarFilter(new ArrayList<>());

    // Assert
    assertNull(classPathEntry.getJarFilter());
    assertFalse(classPathEntry.isFiltered());
  }

  /**
   * Method under test: {@link ClassPathEntry#setJarFilter(List)}
   */
  @Test
  void testSetJarFilter2() {
    // Arrange
    ClassPathEntry classPathEntry = new ClassPathEntry(Configuration.STD_OUT, true);

    // Act
    classPathEntry.setJarFilter(null);

    // Assert
    assertNull(classPathEntry.getJarFilter());
    assertFalse(classPathEntry.isFiltered());
  }

  /**
   * Method under test: {@link ClassPathEntry#setJarFilter(List)}
   */
  @Test
  void testSetJarFilter3() {
    // Arrange
    ClassPathEntry classPathEntry = new ClassPathEntry(Configuration.STD_OUT, true);

    ArrayList<String> filter = new ArrayList<>();
    filter.add("foo");

    // Act
    classPathEntry.setJarFilter(filter);

    // Assert
    assertTrue(classPathEntry.isFiltered());
    assertSame(filter, classPathEntry.getJarFilter());
  }

  /**
   * Method under test: {@link ClassPathEntry#setJarFilter(List)}
   */
  @Test
  void testSetJarFilter4() {
    // Arrange
    ClassPathEntry classPathEntry = new ClassPathEntry(Configuration.STD_OUT, true);

    ArrayList<String> filter = new ArrayList<>();
    filter.add("42");
    filter.add("foo");

    // Act
    classPathEntry.setJarFilter(filter);

    // Assert
    assertTrue(classPathEntry.isFiltered());
    assertSame(filter, classPathEntry.getJarFilter());
  }

  /**
   * Method under test: {@link ClassPathEntry#setJarFilter(List)}
   */
  @Test
  void testSetJarFilter5() {
    // Arrange
    ClassPathEntry classPathEntry = new ClassPathEntry(Configuration.STD_OUT, true);
    NonNullElementWrapperList<String> filter = mock(NonNullElementWrapperList.class);
    when(filter.size()).thenReturn(3);

    // Act
    classPathEntry.setJarFilter(filter);

    // Assert
    verify(filter).size();
    assertTrue(classPathEntry.isFiltered());
    assertSame(filter, classPathEntry.getJarFilter());
  }

  /**
   * Method under test: {@link ClassPathEntry#setAarFilter(List)}
   */
  @Test
  void testSetAarFilter() {
    // Arrange
    ClassPathEntry classPathEntry = new ClassPathEntry(Configuration.STD_OUT, true);

    // Act
    classPathEntry.setAarFilter(new ArrayList<>());

    // Assert
    assertNull(classPathEntry.getAarFilter());
    assertFalse(classPathEntry.isFiltered());
  }

  /**
   * Method under test: {@link ClassPathEntry#setAarFilter(List)}
   */
  @Test
  void testSetAarFilter2() {
    // Arrange
    ClassPathEntry classPathEntry = new ClassPathEntry(Configuration.STD_OUT, true);

    // Act
    classPathEntry.setAarFilter(null);

    // Assert
    assertNull(classPathEntry.getAarFilter());
    assertFalse(classPathEntry.isFiltered());
  }

  /**
   * Method under test: {@link ClassPathEntry#setAarFilter(List)}
   */
  @Test
  void testSetAarFilter3() {
    // Arrange
    ClassPathEntry classPathEntry = new ClassPathEntry(Configuration.STD_OUT, true);

    ArrayList<String> filter = new ArrayList<>();
    filter.add("foo");

    // Act
    classPathEntry.setAarFilter(filter);

    // Assert
    assertTrue(classPathEntry.isFiltered());
    assertSame(filter, classPathEntry.getAarFilter());
  }

  /**
   * Method under test: {@link ClassPathEntry#setAarFilter(List)}
   */
  @Test
  void testSetAarFilter4() {
    // Arrange
    ClassPathEntry classPathEntry = new ClassPathEntry(Configuration.STD_OUT, true);

    ArrayList<String> filter = new ArrayList<>();
    filter.add("42");
    filter.add("foo");

    // Act
    classPathEntry.setAarFilter(filter);

    // Assert
    assertTrue(classPathEntry.isFiltered());
    assertSame(filter, classPathEntry.getAarFilter());
  }

  /**
   * Method under test: {@link ClassPathEntry#setAarFilter(List)}
   */
  @Test
  void testSetAarFilter5() {
    // Arrange
    ClassPathEntry classPathEntry = new ClassPathEntry(Configuration.STD_OUT, true);
    NonNullElementWrapperList<String> filter = mock(NonNullElementWrapperList.class);
    when(filter.size()).thenReturn(3);

    // Act
    classPathEntry.setAarFilter(filter);

    // Assert
    verify(filter).size();
    assertTrue(classPathEntry.isFiltered());
    assertSame(filter, classPathEntry.getAarFilter());
  }

  /**
   * Method under test: {@link ClassPathEntry#setWarFilter(List)}
   */
  @Test
  void testSetWarFilter() {
    // Arrange
    ClassPathEntry classPathEntry = new ClassPathEntry(Configuration.STD_OUT, true);

    // Act
    classPathEntry.setWarFilter(new ArrayList<>());

    // Assert
    assertNull(classPathEntry.getWarFilter());
    assertFalse(classPathEntry.isFiltered());
  }

  /**
   * Method under test: {@link ClassPathEntry#setWarFilter(List)}
   */
  @Test
  void testSetWarFilter2() {
    // Arrange
    ClassPathEntry classPathEntry = new ClassPathEntry(Configuration.STD_OUT, true);

    // Act
    classPathEntry.setWarFilter(null);

    // Assert
    assertNull(classPathEntry.getWarFilter());
    assertFalse(classPathEntry.isFiltered());
  }

  /**
   * Method under test: {@link ClassPathEntry#setWarFilter(List)}
   */
  @Test
  void testSetWarFilter3() {
    // Arrange
    ClassPathEntry classPathEntry = new ClassPathEntry(Configuration.STD_OUT, true);

    ArrayList<String> filter = new ArrayList<>();
    filter.add("foo");

    // Act
    classPathEntry.setWarFilter(filter);

    // Assert
    assertTrue(classPathEntry.isFiltered());
    assertSame(filter, classPathEntry.getWarFilter());
  }

  /**
   * Method under test: {@link ClassPathEntry#setWarFilter(List)}
   */
  @Test
  void testSetWarFilter4() {
    // Arrange
    ClassPathEntry classPathEntry = new ClassPathEntry(Configuration.STD_OUT, true);

    ArrayList<String> filter = new ArrayList<>();
    filter.add("42");
    filter.add("foo");

    // Act
    classPathEntry.setWarFilter(filter);

    // Assert
    assertTrue(classPathEntry.isFiltered());
    assertSame(filter, classPathEntry.getWarFilter());
  }

  /**
   * Method under test: {@link ClassPathEntry#setWarFilter(List)}
   */
  @Test
  void testSetWarFilter5() {
    // Arrange
    ClassPathEntry classPathEntry = new ClassPathEntry(Configuration.STD_OUT, true);
    NonNullElementWrapperList<String> filter = mock(NonNullElementWrapperList.class);
    when(filter.size()).thenReturn(3);

    // Act
    classPathEntry.setWarFilter(filter);

    // Assert
    verify(filter).size();
    assertTrue(classPathEntry.isFiltered());
    assertSame(filter, classPathEntry.getWarFilter());
  }

  /**
   * Method under test: {@link ClassPathEntry#setEarFilter(List)}
   */
  @Test
  void testSetEarFilter() {
    // Arrange
    ClassPathEntry classPathEntry = new ClassPathEntry(Configuration.STD_OUT, true);

    // Act
    classPathEntry.setEarFilter(new ArrayList<>());

    // Assert
    assertNull(classPathEntry.getEarFilter());
    assertFalse(classPathEntry.isFiltered());
  }

  /**
   * Method under test: {@link ClassPathEntry#setEarFilter(List)}
   */
  @Test
  void testSetEarFilter2() {
    // Arrange
    ClassPathEntry classPathEntry = new ClassPathEntry(Configuration.STD_OUT, true);

    // Act
    classPathEntry.setEarFilter(null);

    // Assert
    assertNull(classPathEntry.getEarFilter());
    assertFalse(classPathEntry.isFiltered());
  }

  /**
   * Method under test: {@link ClassPathEntry#setEarFilter(List)}
   */
  @Test
  void testSetEarFilter3() {
    // Arrange
    ClassPathEntry classPathEntry = new ClassPathEntry(Configuration.STD_OUT, true);

    ArrayList<String> filter = new ArrayList<>();
    filter.add("foo");

    // Act
    classPathEntry.setEarFilter(filter);

    // Assert
    assertTrue(classPathEntry.isFiltered());
    assertSame(filter, classPathEntry.getEarFilter());
  }

  /**
   * Method under test: {@link ClassPathEntry#setEarFilter(List)}
   */
  @Test
  void testSetEarFilter4() {
    // Arrange
    ClassPathEntry classPathEntry = new ClassPathEntry(Configuration.STD_OUT, true);

    ArrayList<String> filter = new ArrayList<>();
    filter.add("42");
    filter.add("foo");

    // Act
    classPathEntry.setEarFilter(filter);

    // Assert
    assertTrue(classPathEntry.isFiltered());
    assertSame(filter, classPathEntry.getEarFilter());
  }

  /**
   * Method under test: {@link ClassPathEntry#setEarFilter(List)}
   */
  @Test
  void testSetEarFilter5() {
    // Arrange
    ClassPathEntry classPathEntry = new ClassPathEntry(Configuration.STD_OUT, true);
    NonNullElementWrapperList<String> filter = mock(NonNullElementWrapperList.class);
    when(filter.size()).thenReturn(3);

    // Act
    classPathEntry.setEarFilter(filter);

    // Assert
    verify(filter).size();
    assertTrue(classPathEntry.isFiltered());
    assertSame(filter, classPathEntry.getEarFilter());
  }

  /**
   * Method under test: {@link ClassPathEntry#setJmodFilter(List)}
   */
  @Test
  void testSetJmodFilter() {
    // Arrange
    ClassPathEntry classPathEntry = new ClassPathEntry(Configuration.STD_OUT, true);

    // Act
    classPathEntry.setJmodFilter(new ArrayList<>());

    // Assert
    assertNull(classPathEntry.getJmodFilter());
    assertFalse(classPathEntry.isFiltered());
  }

  /**
   * Method under test: {@link ClassPathEntry#setJmodFilter(List)}
   */
  @Test
  void testSetJmodFilter2() {
    // Arrange
    ClassPathEntry classPathEntry = new ClassPathEntry(Configuration.STD_OUT, true);

    // Act
    classPathEntry.setJmodFilter(null);

    // Assert
    assertNull(classPathEntry.getJmodFilter());
    assertFalse(classPathEntry.isFiltered());
  }

  /**
   * Method under test: {@link ClassPathEntry#setJmodFilter(List)}
   */
  @Test
  void testSetJmodFilter3() {
    // Arrange
    ClassPathEntry classPathEntry = new ClassPathEntry(Configuration.STD_OUT, true);

    ArrayList<String> filter = new ArrayList<>();
    filter.add("foo");

    // Act
    classPathEntry.setJmodFilter(filter);

    // Assert
    assertTrue(classPathEntry.isFiltered());
    assertSame(filter, classPathEntry.getJmodFilter());
  }

  /**
   * Method under test: {@link ClassPathEntry#setJmodFilter(List)}
   */
  @Test
  void testSetJmodFilter4() {
    // Arrange
    ClassPathEntry classPathEntry = new ClassPathEntry(Configuration.STD_OUT, true);

    ArrayList<String> filter = new ArrayList<>();
    filter.add("42");
    filter.add("foo");

    // Act
    classPathEntry.setJmodFilter(filter);

    // Assert
    assertTrue(classPathEntry.isFiltered());
    assertSame(filter, classPathEntry.getJmodFilter());
  }

  /**
   * Method under test: {@link ClassPathEntry#setJmodFilter(List)}
   */
  @Test
  void testSetJmodFilter5() {
    // Arrange
    ClassPathEntry classPathEntry = new ClassPathEntry(Configuration.STD_OUT, true);
    NonNullElementWrapperList<String> filter = mock(NonNullElementWrapperList.class);
    when(filter.size()).thenReturn(3);

    // Act
    classPathEntry.setJmodFilter(filter);

    // Assert
    verify(filter).size();
    assertTrue(classPathEntry.isFiltered());
    assertSame(filter, classPathEntry.getJmodFilter());
  }

  /**
   * Method under test: {@link ClassPathEntry#setZipFilter(List)}
   */
  @Test
  void testSetZipFilter() {
    // Arrange
    ClassPathEntry classPathEntry = new ClassPathEntry(Configuration.STD_OUT, true);

    // Act
    classPathEntry.setZipFilter(new ArrayList<>());

    // Assert
    assertNull(classPathEntry.getZipFilter());
    assertFalse(classPathEntry.isFiltered());
  }

  /**
   * Method under test: {@link ClassPathEntry#setZipFilter(List)}
   */
  @Test
  void testSetZipFilter2() {
    // Arrange
    ClassPathEntry classPathEntry = new ClassPathEntry(Configuration.STD_OUT, true);

    // Act
    classPathEntry.setZipFilter(null);

    // Assert
    assertNull(classPathEntry.getZipFilter());
    assertFalse(classPathEntry.isFiltered());
  }

  /**
   * Method under test: {@link ClassPathEntry#setZipFilter(List)}
   */
  @Test
  void testSetZipFilter3() {
    // Arrange
    ClassPathEntry classPathEntry = new ClassPathEntry(Configuration.STD_OUT, true);

    ArrayList<String> filter = new ArrayList<>();
    filter.add("foo");

    // Act
    classPathEntry.setZipFilter(filter);

    // Assert
    assertTrue(classPathEntry.isFiltered());
    assertSame(filter, classPathEntry.getZipFilter());
  }

  /**
   * Method under test: {@link ClassPathEntry#setZipFilter(List)}
   */
  @Test
  void testSetZipFilter4() {
    // Arrange
    ClassPathEntry classPathEntry = new ClassPathEntry(Configuration.STD_OUT, true);

    ArrayList<String> filter = new ArrayList<>();
    filter.add("42");
    filter.add("foo");

    // Act
    classPathEntry.setZipFilter(filter);

    // Assert
    assertTrue(classPathEntry.isFiltered());
    assertSame(filter, classPathEntry.getZipFilter());
  }

  /**
   * Method under test: {@link ClassPathEntry#setZipFilter(List)}
   */
  @Test
  void testSetZipFilter5() {
    // Arrange
    ClassPathEntry classPathEntry = new ClassPathEntry(Configuration.STD_OUT, true);
    NonNullElementWrapperList<String> filter = mock(NonNullElementWrapperList.class);
    when(filter.size()).thenReturn(3);

    // Act
    classPathEntry.setZipFilter(filter);

    // Assert
    verify(filter).size();
    assertTrue(classPathEntry.isFiltered());
    assertSame(filter, classPathEntry.getZipFilter());
  }

  /**
   * Method under test: {@link ClassPathEntry#toString()}
   */
  @Test
  void testToString() {
    // Arrange, Act and Assert
    assertEquals(System.getProperty("user.dir"), (new ClassPathEntry(Configuration.STD_OUT, true)).toString());
  }

  /**
   * Method under test: {@link ClassPathEntry#toString()}
   */
  @Test
  void testToString2() {
    // Arrange
    ArrayList<String> filter = new ArrayList<>();
    filter.add("foo");

    ClassPathEntry classPathEntry = new ClassPathEntry(Configuration.STD_OUT, true);
    classPathEntry.setFilter(filter);

    // Act
    String actualToStringResult = classPathEntry.toString();

    // Assert
    assertEquals(String.join("", System.getProperty("user.dir"), "(;;;;;;;;foo)"), actualToStringResult);
  }

  /**
   * Method under test: {@link ClassPathEntry#toString()}
   */
  @Test
  void testToString3() {
    // Arrange
    ArrayList<String> filter = new ArrayList<>();
    filter.add(ConfigurationConstants.SEPARATOR_KEYWORD);
    filter.add("foo");

    ClassPathEntry classPathEntry = new ClassPathEntry(Configuration.STD_OUT, true);
    classPathEntry.setFilter(filter);

    // Act
    String actualToStringResult = classPathEntry.toString();

    // Assert
    assertEquals(String.join("", System.getProperty("user.dir"), "(;;;;;;;;';',foo)"), actualToStringResult);
  }

  /**
   * Method under test: {@link ClassPathEntry#toString()}
   */
  @Test
  void testToString4() {
    // Arrange
    ArrayList<String> filter = new ArrayList<>();
    filter.add(ConfigurationConstants.OPEN_ARGUMENTS_KEYWORD);

    ClassPathEntry classPathEntry = new ClassPathEntry(Configuration.STD_OUT, true);
    classPathEntry.setFilter(filter);

    // Act
    String actualToStringResult = classPathEntry.toString();

    // Assert
    assertEquals(String.join("", System.getProperty("user.dir"), "(;;;;;;;;'(')"), actualToStringResult);
  }

  /**
   * Method under test: {@link ClassPathEntry#toString()}
   */
  @Test
  void testToString5() {
    // Arrange
    ArrayList<String> filter = new ArrayList<>();
    filter.add(ConfigurationConstants.CLOSE_ARGUMENTS_KEYWORD);

    ClassPathEntry classPathEntry = new ClassPathEntry(Configuration.STD_OUT, true);
    classPathEntry.setFilter(filter);

    // Act
    String actualToStringResult = classPathEntry.toString();

    // Assert
    assertEquals(String.join("", System.getProperty("user.dir"), "(;;;;;;;;')')"), actualToStringResult);
  }

  /**
   * Method under test: {@link ClassPathEntry#toString()}
   */
  @Test
  void testToString6() {
    // Arrange
    ArrayList<String> filter = new ArrayList<>();
    filter.add("");

    ClassPathEntry classPathEntry = new ClassPathEntry(Configuration.STD_OUT, true);
    classPathEntry.setFilter(filter);

    // Act
    String actualToStringResult = classPathEntry.toString();

    // Assert
    assertEquals(String.join("", System.getProperty("user.dir"), "(;;;;;;;;'')"), actualToStringResult);
  }

  /**
   * Method under test: {@link ClassPathEntry#toString()}
   */
  @Test
  void testToString7() {
    // Arrange
    ArrayList<String> filter = new ArrayList<>();
    filter.add("foo");

    ClassPathEntry classPathEntry = new ClassPathEntry(Configuration.STD_OUT, true);
    classPathEntry.setApkFilter(filter);

    // Act
    String actualToStringResult = classPathEntry.toString();

    // Assert
    assertEquals(String.join("", System.getProperty("user.dir"), "(;;foo;;;;;;)"), actualToStringResult);
  }

  /**
   * Method under test: {@link ClassPathEntry#toString()}
   */
  @Test
  void testToString8() {
    // Arrange
    ArrayList<String> filter = new ArrayList<>();
    filter.add("foo");

    ClassPathEntry classPathEntry = new ClassPathEntry(Configuration.STD_OUT, true);
    classPathEntry.setAabFilter(filter);

    // Act
    String actualToStringResult = classPathEntry.toString();

    // Assert
    assertEquals(String.join("", System.getProperty("user.dir"), "(;foo;;;;;;;)"), actualToStringResult);
  }

  /**
   * Method under test: {@link ClassPathEntry#toString()}
   */
  @Test
  void testToString9() {
    // Arrange
    ArrayList<String> filter = new ArrayList<>();
    filter.add("foo");

    ClassPathEntry classPathEntry = new ClassPathEntry(Configuration.STD_OUT, true);
    classPathEntry.setJarFilter(filter);

    // Act
    String actualToStringResult = classPathEntry.toString();

    // Assert
    assertEquals(String.join("", System.getProperty("user.dir"), "(;;;;;;;foo;)"), actualToStringResult);
  }

  /**
   * Method under test: {@link ClassPathEntry#toString()}
   */
  @Test
  void testToString10() {
    // Arrange
    ArrayList<String> filter = new ArrayList<>();
    filter.add("foo");

    ClassPathEntry classPathEntry = new ClassPathEntry(Configuration.STD_OUT, true);
    classPathEntry.setAarFilter(filter);

    // Act
    String actualToStringResult = classPathEntry.toString();

    // Assert
    assertEquals(String.join("", System.getProperty("user.dir"), "(foo;;;;;;;;)"), actualToStringResult);
  }

  /**
   * Method under test: {@link ClassPathEntry#toString()}
   */
  @Test
  void testToString11() {
    // Arrange
    ArrayList<String> filter = new ArrayList<>();
    filter.add("foo");

    ClassPathEntry classPathEntry = new ClassPathEntry(Configuration.STD_OUT, true);
    classPathEntry.setWarFilter(filter);

    // Act
    String actualToStringResult = classPathEntry.toString();

    // Assert
    assertEquals(String.join("", System.getProperty("user.dir"), "(;;;;;;foo;;)"), actualToStringResult);
  }

  /**
   * Method under test: {@link ClassPathEntry#toString()}
   */
  @Test
  void testToString12() {
    // Arrange
    ArrayList<String> filter = new ArrayList<>();
    filter.add("foo");

    ClassPathEntry classPathEntry = new ClassPathEntry(Configuration.STD_OUT, true);
    classPathEntry.setEarFilter(filter);

    // Act
    String actualToStringResult = classPathEntry.toString();

    // Assert
    assertEquals(String.join("", System.getProperty("user.dir"), "(;;;;;foo;;;)"), actualToStringResult);
  }

  /**
   * Methods under test:
   * <ul>
   *   <li>{@link ClassPathEntry#ClassPathEntry(File, boolean)}
   *   <li>{@link ClassPathEntry#setFeatureName(String)}
   *   <li>{@link ClassPathEntry#setOutput(boolean)}
   *   <li>{@link ClassPathEntry#getAabFilter()}
   *   <li>{@link ClassPathEntry#getAarFilter()}
   *   <li>{@link ClassPathEntry#getApkFilter()}
   *   <li>{@link ClassPathEntry#getEarFilter()}
   *   <li>{@link ClassPathEntry#getFeatureName()}
   *   <li>{@link ClassPathEntry#getFile()}
   *   <li>{@link ClassPathEntry#getFilter()}
   *   <li>{@link ClassPathEntry#getJarFilter()}
   *   <li>{@link ClassPathEntry#getJmodFilter()}
   *   <li>{@link ClassPathEntry#getWarFilter()}
   *   <li>{@link ClassPathEntry#getZipFilter()}
   *   <li>{@link ClassPathEntry#isOutput()}
   * </ul>
   */
  @Test
  void testGettersAndSetters() {
    // Arrange
    File file = Configuration.STD_OUT;

    // Act
    ClassPathEntry actualClassPathEntry = new ClassPathEntry(file, true);
    actualClassPathEntry.setFeatureName("Feature Name");
    actualClassPathEntry.setOutput(true);
    actualClassPathEntry.getAabFilter();
    actualClassPathEntry.getAarFilter();
    actualClassPathEntry.getApkFilter();
    actualClassPathEntry.getEarFilter();
    String actualFeatureName = actualClassPathEntry.getFeatureName();
    File actualFile = actualClassPathEntry.getFile();
    actualClassPathEntry.getFilter();
    actualClassPathEntry.getJarFilter();
    actualClassPathEntry.getJmodFilter();
    actualClassPathEntry.getWarFilter();
    actualClassPathEntry.getZipFilter();

    // Assert that nothing has changed
    assertEquals("Feature Name", actualFeatureName);
    assertTrue(actualClassPathEntry.isOutput());
    assertSame(file, actualFile);
  }

  /**
   * Methods under test:
   * <ul>
   *   <li>{@link ClassPathEntry#ClassPathEntry(File, boolean, String)}
   *   <li>{@link ClassPathEntry#setFeatureName(String)}
   *   <li>{@link ClassPathEntry#setOutput(boolean)}
   *   <li>{@link ClassPathEntry#getAabFilter()}
   *   <li>{@link ClassPathEntry#getAarFilter()}
   *   <li>{@link ClassPathEntry#getApkFilter()}
   *   <li>{@link ClassPathEntry#getEarFilter()}
   *   <li>{@link ClassPathEntry#getFeatureName()}
   *   <li>{@link ClassPathEntry#getFile()}
   *   <li>{@link ClassPathEntry#getFilter()}
   *   <li>{@link ClassPathEntry#getJarFilter()}
   *   <li>{@link ClassPathEntry#getJmodFilter()}
   *   <li>{@link ClassPathEntry#getWarFilter()}
   *   <li>{@link ClassPathEntry#getZipFilter()}
   *   <li>{@link ClassPathEntry#isOutput()}
   * </ul>
   */
  @Test
  void testGettersAndSetters2() {
    // Arrange
    File file = Configuration.STD_OUT;

    // Act
    ClassPathEntry actualClassPathEntry = new ClassPathEntry(file, true, "Feature Name");
    actualClassPathEntry.setFeatureName("Feature Name");
    actualClassPathEntry.setOutput(true);
    actualClassPathEntry.getAabFilter();
    actualClassPathEntry.getAarFilter();
    actualClassPathEntry.getApkFilter();
    actualClassPathEntry.getEarFilter();
    String actualFeatureName = actualClassPathEntry.getFeatureName();
    File actualFile = actualClassPathEntry.getFile();
    actualClassPathEntry.getFilter();
    actualClassPathEntry.getJarFilter();
    actualClassPathEntry.getJmodFilter();
    actualClassPathEntry.getWarFilter();
    actualClassPathEntry.getZipFilter();

    // Assert that nothing has changed
    assertEquals("Feature Name", actualFeatureName);
    assertTrue(actualClassPathEntry.isOutput());
    assertSame(file, actualFile);
  }
}
