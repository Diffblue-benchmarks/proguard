package proguard.io;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.io.IOException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

class ExtraDataEntryReaderDiffblueTest {
  /**
   * Test {@link ExtraDataEntryReader#ExtraDataEntryReader(String, DataEntryReader)}.
   *
   * <p>Method under test: {@link ExtraDataEntryReader#ExtraDataEntryReader(String,
   * DataEntryReader)}
   */
  @Test
  @DisplayName("Test new ExtraDataEntryReader(String, DataEntryReader)")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void ExtraDataEntryReader.<init>(String, DataEntryReader)"})
  void testNewExtraDataEntryReader() throws IOException {
    // Arrange
    DataEntryReader dataEntryReader = mock(DataEntryReader.class);
    doNothing().when(dataEntryReader).read(Mockito.<DataEntry>any());

    // Act
    ExtraDataEntryReader actualExtraDataEntryReader =
        new ExtraDataEntryReader("java.lang.String", dataEntryReader);
    ClassPathDataEntry parent = new ClassPathDataEntry("Name");
    DummyDataEntry dataEntry = new DummyDataEntry(parent, "Name", 3L, true);

    actualExtraDataEntryReader.read(dataEntry);

    // Assert that nothing has changed
    verify(dataEntryReader, atLeast(1)).read(Mockito.<DataEntry>any());
    assertEquals("Name", dataEntry.getName());
    assertEquals("Name", dataEntry.getOriginalName());
    assertEquals(3L, dataEntry.getSize());
    assertTrue(dataEntry.isDirectory());
    assertSame(parent, dataEntry.getParent());
  }

  /**
   * Test {@link ExtraDataEntryReader#ExtraDataEntryReader(String, DataEntryReader,
   * DataEntryReader)}.
   *
   * <p>Method under test: {@link ExtraDataEntryReader#ExtraDataEntryReader(String, DataEntryReader,
   * DataEntryReader)}
   */
  @Test
  @DisplayName("Test new ExtraDataEntryReader(String, DataEntryReader, DataEntryReader)")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void ExtraDataEntryReader.<init>(String, DataEntryReader, DataEntryReader)"})
  void testNewExtraDataEntryReader2() throws IOException {
    // Arrange
    DataEntryReader dataEntryReader = mock(DataEntryReader.class);
    doNothing().when(dataEntryReader).read(Mockito.<DataEntry>any());
    DataEntryReader extraDataEntryReader = mock(DataEntryReader.class);
    doNothing().when(extraDataEntryReader).read(Mockito.<DataEntry>any());

    // Act
    ExtraDataEntryReader actualExtraDataEntryReader =
        new ExtraDataEntryReader("java.lang.String", dataEntryReader, extraDataEntryReader);
    ClassPathDataEntry parent = new ClassPathDataEntry("Name");
    DummyDataEntry dataEntry = new DummyDataEntry(parent, "Name", 3L, true);

    actualExtraDataEntryReader.read(dataEntry);

    // Assert that nothing has changed
    verify(dataEntryReader).read(isA(DataEntry.class));
    verify(extraDataEntryReader).read(isA(DataEntry.class));
    assertEquals("Name", dataEntry.getName());
    assertEquals("Name", dataEntry.getOriginalName());
    assertEquals(3L, dataEntry.getSize());
    assertTrue(dataEntry.isDirectory());
    assertSame(parent, dataEntry.getParent());
  }

  /**
   * Test {@link ExtraDataEntryReader#ExtraDataEntryReader(String, DataEntryReader)}.
   *
   * <ul>
   *   <li>Then {@link ClassPathDataEntry#ClassPathDataEntry(String)} with {@code Name} Name is
   *       {@code Name}.
   * </ul>
   *
   * <p>Method under test: {@link ExtraDataEntryReader#ExtraDataEntryReader(String,
   * DataEntryReader)}
   */
  @Test
  @DisplayName(
      "Test new ExtraDataEntryReader(String, DataEntryReader); then ClassPathDataEntry(String) with 'Name' Name is 'Name'")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void ExtraDataEntryReader.<init>(String, DataEntryReader)"})
  void testNewExtraDataEntryReader_thenClassPathDataEntryWithNameNameIsName() throws IOException {
    // Arrange
    DataEntryReader dataEntryReader = mock(DataEntryReader.class);
    doNothing().when(dataEntryReader).read(Mockito.<DataEntry>any());

    // Act
    ExtraDataEntryReader actualExtraDataEntryReader =
        new ExtraDataEntryReader("Extra Entry Name", dataEntryReader);
    ClassPathDataEntry dataEntry = new ClassPathDataEntry("Name");
    actualExtraDataEntryReader.read(dataEntry);

    // Assert that nothing has changed
    verify(dataEntryReader, atLeast(1)).read(Mockito.<DataEntry>any());
    assertEquals("Name", dataEntry.getName());
    assertEquals("Name", dataEntry.getOriginalName());
    assertEquals(-1L, dataEntry.getSize());
    assertFalse(dataEntry.isDirectory());
  }

  /**
   * Test {@link ExtraDataEntryReader#ExtraDataEntryReader(String, DataEntryReader,
   * DataEntryReader)}.
   *
   * <ul>
   *   <li>Then {@link ClassPathDataEntry#ClassPathDataEntry(String)} with {@code Name} Name is
   *       {@code Name}.
   * </ul>
   *
   * <p>Method under test: {@link ExtraDataEntryReader#ExtraDataEntryReader(String, DataEntryReader,
   * DataEntryReader)}
   */
  @Test
  @DisplayName(
      "Test new ExtraDataEntryReader(String, DataEntryReader, DataEntryReader); then ClassPathDataEntry(String) with 'Name' Name is 'Name'")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void ExtraDataEntryReader.<init>(String, DataEntryReader, DataEntryReader)"})
  void testNewExtraDataEntryReader_thenClassPathDataEntryWithNameNameIsName2() throws IOException {
    // Arrange
    DataEntryReader dataEntryReader = mock(DataEntryReader.class);
    doNothing().when(dataEntryReader).read(Mockito.<DataEntry>any());
    DataEntryReader extraDataEntryReader = mock(DataEntryReader.class);
    doNothing().when(extraDataEntryReader).read(Mockito.<DataEntry>any());

    // Act
    ExtraDataEntryReader actualExtraDataEntryReader =
        new ExtraDataEntryReader("Extra Entry Name", dataEntryReader, extraDataEntryReader);
    ClassPathDataEntry dataEntry = new ClassPathDataEntry("Name");
    actualExtraDataEntryReader.read(dataEntry);

    // Assert that nothing has changed
    verify(dataEntryReader).read(isA(DataEntry.class));
    verify(extraDataEntryReader).read(isA(DataEntry.class));
    assertEquals("Name", dataEntry.getName());
    assertEquals("Name", dataEntry.getOriginalName());
    assertEquals(-1L, dataEntry.getSize());
    assertFalse(dataEntry.isDirectory());
  }

  /**
   * Test {@link ExtraDataEntryReader#ExtraDataEntryReader(String, DataEntryReader)}.
   *
   * <ul>
   *   <li>When {@code null}.
   *   <li>Then {@link ClassPathDataEntry#ClassPathDataEntry(String)} with {@code Name} Name is
   *       {@code Name}.
   * </ul>
   *
   * <p>Method under test: {@link ExtraDataEntryReader#ExtraDataEntryReader(String,
   * DataEntryReader)}
   */
  @Test
  @DisplayName(
      "Test new ExtraDataEntryReader(String, DataEntryReader); when 'null'; then ClassPathDataEntry(String) with 'Name' Name is 'Name'")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void ExtraDataEntryReader.<init>(String, DataEntryReader)"})
  void testNewExtraDataEntryReader_whenNull_thenClassPathDataEntryWithNameNameIsName()
      throws IOException {
    // Arrange
    DataEntryReader dataEntryReader = mock(DataEntryReader.class);
    doNothing().when(dataEntryReader).read(Mockito.<DataEntry>any());

    // Act
    ExtraDataEntryReader actualExtraDataEntryReader =
        new ExtraDataEntryReader((String) null, dataEntryReader);
    ClassPathDataEntry dataEntry = new ClassPathDataEntry("Name");
    actualExtraDataEntryReader.read(dataEntry);

    // Assert that nothing has changed
    verify(dataEntryReader, atLeast(1)).read(Mockito.<DataEntry>any());
    assertEquals("Name", dataEntry.getName());
    assertEquals("Name", dataEntry.getOriginalName());
    assertEquals(-1L, dataEntry.getSize());
    assertFalse(dataEntry.isDirectory());
  }

  /**
   * Test {@link ExtraDataEntryReader#ExtraDataEntryReader(String, DataEntryReader,
   * DataEntryReader)}.
   *
   * <ul>
   *   <li>When {@code null}.
   *   <li>Then {@link ClassPathDataEntry#ClassPathDataEntry(String)} with {@code Name} Name is
   *       {@code Name}.
   * </ul>
   *
   * <p>Method under test: {@link ExtraDataEntryReader#ExtraDataEntryReader(String, DataEntryReader,
   * DataEntryReader)}
   */
  @Test
  @DisplayName(
      "Test new ExtraDataEntryReader(String, DataEntryReader, DataEntryReader); when 'null'; then ClassPathDataEntry(String) with 'Name' Name is 'Name'")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void ExtraDataEntryReader.<init>(String, DataEntryReader, DataEntryReader)"})
  void testNewExtraDataEntryReader_whenNull_thenClassPathDataEntryWithNameNameIsName2()
      throws IOException {
    // Arrange
    DataEntryReader dataEntryReader = mock(DataEntryReader.class);
    doNothing().when(dataEntryReader).read(Mockito.<DataEntry>any());
    DataEntryReader extraDataEntryReader = mock(DataEntryReader.class);
    doNothing().when(extraDataEntryReader).read(Mockito.<DataEntry>any());

    // Act
    ExtraDataEntryReader actualExtraDataEntryReader =
        new ExtraDataEntryReader((String) null, dataEntryReader, extraDataEntryReader);
    ClassPathDataEntry dataEntry = new ClassPathDataEntry("Name");
    actualExtraDataEntryReader.read(dataEntry);

    // Assert that nothing has changed
    verify(dataEntryReader).read(isA(DataEntry.class));
    verify(extraDataEntryReader).read(isA(DataEntry.class));
    assertEquals("Name", dataEntry.getName());
    assertEquals("Name", dataEntry.getOriginalName());
    assertEquals(-1L, dataEntry.getSize());
    assertFalse(dataEntry.isDirectory());
  }

  /**
   * Test {@link ExtraDataEntryReader#read(DataEntry)}.
   *
   * <p>Method under test: {@link ExtraDataEntryReader#read(DataEntry)}
   */
  @Test
  @DisplayName("Test read(DataEntry)")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void ExtraDataEntryReader.read(DataEntry)"})
  void testRead() throws IOException {
    // Arrange
    DataEntryReader dataEntryReader = mock(DataEntryReader.class);
    doThrow(new IOException("foo")).when(dataEntryReader).read(Mockito.<DataEntry>any());
    ExtraDataEntryReader extraDataEntryReader =
        new ExtraDataEntryReader(new ExtraDataEntryNameMap(), dataEntryReader);

    // Act and Assert
    assertThrows(
        IOException.class,
        () ->
            extraDataEntryReader.read(
                new DummyDataEntry(new ClassPathDataEntry("Name"), "Name", 3L, true)));
    verify(dataEntryReader).read(isA(DataEntry.class));
  }

  /**
   * Test {@link ExtraDataEntryReader#read(DataEntry)}.
   *
   * <ul>
   *   <li>Given {@link DataEntryReader} {@link DataEntryReader#read(DataEntry)} does nothing.
   *   <li>Then calls {@link DataEntryReader#read(DataEntry)}.
   * </ul>
   *
   * <p>Method under test: {@link ExtraDataEntryReader#read(DataEntry)}
   */
  @Test
  @DisplayName(
      "Test read(DataEntry); given DataEntryReader read(DataEntry) does nothing; then calls read(DataEntry)")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void ExtraDataEntryReader.read(DataEntry)"})
  void testRead_givenDataEntryReaderReadDoesNothing_thenCallsRead() throws IOException {
    // Arrange
    DataEntryReader dataEntryReader = mock(DataEntryReader.class);
    doNothing().when(dataEntryReader).read(Mockito.<DataEntry>any());
    ExtraDataEntryReader extraDataEntryReader =
        new ExtraDataEntryReader("Extra Entry Name", dataEntryReader);

    // Act
    extraDataEntryReader.read(new ClassPathDataEntry("Name"));

    // Assert
    verify(dataEntryReader, atLeast(1)).read(Mockito.<DataEntry>any());
  }

  /**
   * Test {@link ExtraDataEntryReader#read(DataEntry)}.
   *
   * <ul>
   *   <li>Given {@link ExtraDataEntryReader#ExtraDataEntryReader(String, DataEntryReader)} with
   *       {@code Extra Entry Name} and {@link DataEntryReader}.
   * </ul>
   *
   * <p>Method under test: {@link ExtraDataEntryReader#read(DataEntry)}
   */
  @Test
  @DisplayName(
      "Test read(DataEntry); given ExtraDataEntryReader(String, DataEntryReader) with 'Extra Entry Name' and DataEntryReader")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void ExtraDataEntryReader.read(DataEntry)"})
  void testRead_givenExtraDataEntryReaderWithExtraEntryNameAndDataEntryReader() throws IOException {
    // Arrange
    DataEntryReader dataEntryReader = mock(DataEntryReader.class);
    doThrow(new IOException("foo")).when(dataEntryReader).read(Mockito.<DataEntry>any());
    ExtraDataEntryReader extraDataEntryReader =
        new ExtraDataEntryReader("Extra Entry Name", dataEntryReader);

    // Act and Assert
    assertThrows(
        IOException.class, () -> extraDataEntryReader.read(new ClassPathDataEntry("Name")));
    verify(dataEntryReader).read(isA(DataEntry.class));
  }

  /**
   * Test {@link ExtraDataEntryReader#read(DataEntry)}.
   *
   * <ul>
   *   <li>Given {@link ExtraDataEntryReader#ExtraDataEntryReader(String, DataEntryReader)} with
   *       extraEntryName is {@code null} and {@link DataEntryReader}.
   * </ul>
   *
   * <p>Method under test: {@link ExtraDataEntryReader#read(DataEntry)}
   */
  @Test
  @DisplayName(
      "Test read(DataEntry); given ExtraDataEntryReader(String, DataEntryReader) with extraEntryName is 'null' and DataEntryReader")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void ExtraDataEntryReader.read(DataEntry)"})
  void testRead_givenExtraDataEntryReaderWithExtraEntryNameIsNullAndDataEntryReader()
      throws IOException {
    // Arrange
    DataEntryReader dataEntryReader = mock(DataEntryReader.class);
    doNothing().when(dataEntryReader).read(Mockito.<DataEntry>any());
    ExtraDataEntryReader extraDataEntryReader =
        new ExtraDataEntryReader((String) null, dataEntryReader);

    // Act
    extraDataEntryReader.read(new ClassPathDataEntry("Name"));

    // Assert
    verify(dataEntryReader, atLeast(1)).read(Mockito.<DataEntry>any());
  }

  /**
   * Test {@link ExtraDataEntryReader#read(DataEntry)}.
   *
   * <ul>
   *   <li>Then throw {@link IOException}.
   * </ul>
   *
   * <p>Method under test: {@link ExtraDataEntryReader#read(DataEntry)}
   */
  @Test
  @DisplayName("Test read(DataEntry); then throw IOException")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void ExtraDataEntryReader.read(DataEntry)"})
  void testRead_thenThrowIOException() throws IOException {
    // Arrange
    DataEntryReader dataEntryReader = mock(DataEntryReader.class);
    doThrow(new IOException("foo")).when(dataEntryReader).read(Mockito.<DataEntry>any());
    ExtraDataEntryReader extraDataEntryReader =
        new ExtraDataEntryReader(new ExtraDataEntryNameMap(), dataEntryReader);

    // Act and Assert
    assertThrows(
        IOException.class, () -> extraDataEntryReader.read(new ClassPathDataEntry("Name")));
    verify(dataEntryReader).read(isA(DataEntry.class));
  }
}
