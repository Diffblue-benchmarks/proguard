package proguard.io;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.io.IOException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

class ExtraDataEntryReaderDiffblueTest {
  /**
   * Test {@link ExtraDataEntryReader#ExtraDataEntryReader(String, DataEntryReader)}.
   * <ul>
   *   <li>When {@code Extra Entry Name}.</li>
   *   <li>Then calls {@link DataEntryReader#read(DataEntry)}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ExtraDataEntryReader#ExtraDataEntryReader(String, DataEntryReader)}
   */
  @Test
  @DisplayName("Test new ExtraDataEntryReader(String, DataEntryReader); when 'Extra Entry Name'; then calls read(DataEntry)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void ExtraDataEntryReader.<init>(String, DataEntryReader)"})
  void testNewExtraDataEntryReader_whenExtraEntryName_thenCallsRead() throws IOException {
    // Arrange
    DataEntryReader dataEntryReader = mock(DataEntryReader.class);
    doNothing().when(dataEntryReader).read(Mockito.<DataEntry>any());

    // Act
    ExtraDataEntryReader actualExtraDataEntryReader = new ExtraDataEntryReader("Extra Entry Name", dataEntryReader);
    actualExtraDataEntryReader.read(new ClassPathDataEntry("Name"));

    // Assert
    verify(dataEntryReader, atLeast(1)).read(Mockito.<DataEntry>any());
  }

  /**
   * Test {@link ExtraDataEntryReader#ExtraDataEntryReader(String, DataEntryReader, DataEntryReader)}.
   * <ul>
   *   <li>When {@code Extra Entry Name}.</li>
   *   <li>Then calls {@link DataEntryReader#read(DataEntry)}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ExtraDataEntryReader#ExtraDataEntryReader(String, DataEntryReader, DataEntryReader)}
   */
  @Test
  @DisplayName("Test new ExtraDataEntryReader(String, DataEntryReader, DataEntryReader); when 'Extra Entry Name'; then calls read(DataEntry)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void ExtraDataEntryReader.<init>(String, DataEntryReader, DataEntryReader)"})
  void testNewExtraDataEntryReader_whenExtraEntryName_thenCallsRead2() throws IOException {
    // Arrange
    DataEntryReader dataEntryReader = mock(DataEntryReader.class);
    doNothing().when(dataEntryReader).read(Mockito.<DataEntry>any());
    DataEntryReader extraDataEntryReader = mock(DataEntryReader.class);
    doNothing().when(extraDataEntryReader).read(Mockito.<DataEntry>any());

    // Act
    ExtraDataEntryReader actualExtraDataEntryReader = new ExtraDataEntryReader("Extra Entry Name", dataEntryReader,
        extraDataEntryReader);
    actualExtraDataEntryReader.read(new ClassPathDataEntry("Name"));

    // Assert
    verify(dataEntryReader).read(isA(DataEntry.class));
    verify(extraDataEntryReader).read(isA(DataEntry.class));
  }

  /**
   * Test {@link ExtraDataEntryReader#ExtraDataEntryReader(String, DataEntryReader)}.
   * <ul>
   *   <li>When {@code String}.</li>
   *   <li>Then calls {@link DataEntryReader#read(DataEntry)}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ExtraDataEntryReader#ExtraDataEntryReader(String, DataEntryReader)}
   */
  @Test
  @DisplayName("Test new ExtraDataEntryReader(String, DataEntryReader); when 'java.lang.String'; then calls read(DataEntry)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void ExtraDataEntryReader.<init>(String, DataEntryReader)"})
  void testNewExtraDataEntryReader_whenJavaLangString_thenCallsRead() throws IOException {
    // Arrange
    DataEntryReader dataEntryReader = mock(DataEntryReader.class);
    doNothing().when(dataEntryReader).read(Mockito.<DataEntry>any());

    // Act
    ExtraDataEntryReader actualExtraDataEntryReader = new ExtraDataEntryReader("java.lang.String", dataEntryReader);
    actualExtraDataEntryReader.read(new DummyDataEntry(new ClassPathDataEntry("Name"), "Name", 3L, true));

    // Assert
    verify(dataEntryReader, atLeast(1)).read(Mockito.<DataEntry>any());
  }

  /**
   * Test {@link ExtraDataEntryReader#ExtraDataEntryReader(String, DataEntryReader, DataEntryReader)}.
   * <ul>
   *   <li>When {@code String}.</li>
   *   <li>Then calls {@link DataEntryReader#read(DataEntry)}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ExtraDataEntryReader#ExtraDataEntryReader(String, DataEntryReader, DataEntryReader)}
   */
  @Test
  @DisplayName("Test new ExtraDataEntryReader(String, DataEntryReader, DataEntryReader); when 'java.lang.String'; then calls read(DataEntry)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void ExtraDataEntryReader.<init>(String, DataEntryReader, DataEntryReader)"})
  void testNewExtraDataEntryReader_whenJavaLangString_thenCallsRead2() throws IOException {
    // Arrange
    DataEntryReader dataEntryReader = mock(DataEntryReader.class);
    doNothing().when(dataEntryReader).read(Mockito.<DataEntry>any());
    DataEntryReader extraDataEntryReader = mock(DataEntryReader.class);
    doNothing().when(extraDataEntryReader).read(Mockito.<DataEntry>any());

    // Act
    ExtraDataEntryReader actualExtraDataEntryReader = new ExtraDataEntryReader("java.lang.String", dataEntryReader,
        extraDataEntryReader);
    actualExtraDataEntryReader.read(new DummyDataEntry(new ClassPathDataEntry("Name"), "Name", 3L, true));

    // Assert
    verify(dataEntryReader).read(isA(DataEntry.class));
    verify(extraDataEntryReader).read(isA(DataEntry.class));
  }

  /**
   * Test {@link ExtraDataEntryReader#ExtraDataEntryReader(String, DataEntryReader)}.
   * <ul>
   *   <li>When {@code null}.</li>
   *   <li>Then calls {@link DataEntryReader#read(DataEntry)}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ExtraDataEntryReader#ExtraDataEntryReader(String, DataEntryReader)}
   */
  @Test
  @DisplayName("Test new ExtraDataEntryReader(String, DataEntryReader); when 'null'; then calls read(DataEntry)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void ExtraDataEntryReader.<init>(String, DataEntryReader)"})
  void testNewExtraDataEntryReader_whenNull_thenCallsRead() throws IOException {
    // Arrange
    DataEntryReader dataEntryReader = mock(DataEntryReader.class);
    doNothing().when(dataEntryReader).read(Mockito.<DataEntry>any());

    // Act
    ExtraDataEntryReader actualExtraDataEntryReader = new ExtraDataEntryReader((String) null, dataEntryReader);
    actualExtraDataEntryReader.read(new ClassPathDataEntry("Name"));

    // Assert
    verify(dataEntryReader, atLeast(1)).read(Mockito.<DataEntry>any());
  }

  /**
   * Test {@link ExtraDataEntryReader#ExtraDataEntryReader(String, DataEntryReader, DataEntryReader)}.
   * <ul>
   *   <li>When {@code null}.</li>
   *   <li>Then calls {@link DataEntryReader#read(DataEntry)}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ExtraDataEntryReader#ExtraDataEntryReader(String, DataEntryReader, DataEntryReader)}
   */
  @Test
  @DisplayName("Test new ExtraDataEntryReader(String, DataEntryReader, DataEntryReader); when 'null'; then calls read(DataEntry)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void ExtraDataEntryReader.<init>(String, DataEntryReader, DataEntryReader)"})
  void testNewExtraDataEntryReader_whenNull_thenCallsRead2() throws IOException {
    // Arrange
    DataEntryReader dataEntryReader = mock(DataEntryReader.class);
    doNothing().when(dataEntryReader).read(Mockito.<DataEntry>any());
    DataEntryReader extraDataEntryReader = mock(DataEntryReader.class);
    doNothing().when(extraDataEntryReader).read(Mockito.<DataEntry>any());

    // Act
    ExtraDataEntryReader actualExtraDataEntryReader = new ExtraDataEntryReader((String) null, dataEntryReader,
        extraDataEntryReader);
    actualExtraDataEntryReader.read(new ClassPathDataEntry("Name"));

    // Assert
    verify(dataEntryReader).read(isA(DataEntry.class));
    verify(extraDataEntryReader).read(isA(DataEntry.class));
  }

  /**
   * Test {@link ExtraDataEntryReader#read(DataEntry)}.
   * <p>
   * Method under test: {@link ExtraDataEntryReader#read(DataEntry)}
   */
  @Test
  @DisplayName("Test read(DataEntry)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void ExtraDataEntryReader.read(DataEntry)"})
  void testRead() throws IOException {
    // Arrange
    DataEntryReader dataEntryReader = mock(DataEntryReader.class);
    doThrow(new IOException("foo")).when(dataEntryReader).read(Mockito.<DataEntry>any());
    ExtraDataEntryReader extraDataEntryReader = new ExtraDataEntryReader(new ExtraDataEntryNameMap(), dataEntryReader);

    // Act and Assert
    assertThrows(IOException.class,
        () -> extraDataEntryReader.read(new DummyDataEntry(new ClassPathDataEntry("Name"), "Name", 3L, true)));
    verify(dataEntryReader).read(isA(DataEntry.class));
  }

  /**
   * Test {@link ExtraDataEntryReader#read(DataEntry)}.
   * <ul>
   *   <li>Given {@link ExtraDataEntryNameMap} (default constructor) addExtraDataEntry {@code Extra Data Entry Name}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ExtraDataEntryReader#read(DataEntry)}
   */
  @Test
  @DisplayName("Test read(DataEntry); given ExtraDataEntryNameMap (default constructor) addExtraDataEntry 'Extra Data Entry Name'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void ExtraDataEntryReader.read(DataEntry)"})
  void testRead_givenExtraDataEntryNameMapAddExtraDataEntryExtraDataEntryName() throws IOException {
    // Arrange
    ExtraDataEntryNameMap extraEntryNameMap = new ExtraDataEntryNameMap();
    extraEntryNameMap.addExtraDataEntry("Extra Data Entry Name");
    DataEntryReader dataEntryReader = mock(DataEntryReader.class);
    doThrow(new IOException("foo")).when(dataEntryReader).read(Mockito.<DataEntry>any());
    ExtraDataEntryReader extraDataEntryReader = new ExtraDataEntryReader(extraEntryNameMap, dataEntryReader);

    // Act and Assert
    assertThrows(IOException.class, () -> extraDataEntryReader.read(new ClassPathDataEntry("Name")));
    verify(dataEntryReader).read(isA(DataEntry.class));
  }

  /**
   * Test {@link ExtraDataEntryReader#read(DataEntry)}.
   * <ul>
   *   <li>Given {@link ExtraDataEntryReader#ExtraDataEntryReader(String, DataEntryReader)} with {@code Extra Entry Name} and {@link DataEntryReader}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ExtraDataEntryReader#read(DataEntry)}
   */
  @Test
  @DisplayName("Test read(DataEntry); given ExtraDataEntryReader(String, DataEntryReader) with 'Extra Entry Name' and DataEntryReader")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void ExtraDataEntryReader.read(DataEntry)"})
  void testRead_givenExtraDataEntryReaderWithExtraEntryNameAndDataEntryReader() throws IOException {
    // Arrange
    DataEntryReader dataEntryReader = mock(DataEntryReader.class);
    doNothing().when(dataEntryReader).read(Mockito.<DataEntry>any());
    ExtraDataEntryReader extraDataEntryReader = new ExtraDataEntryReader("Extra Entry Name", dataEntryReader);

    // Act
    extraDataEntryReader.read(new ClassPathDataEntry("Name"));

    // Assert
    verify(dataEntryReader, atLeast(1)).read(Mockito.<DataEntry>any());
  }

  /**
   * Test {@link ExtraDataEntryReader#read(DataEntry)}.
   * <ul>
   *   <li>Given {@link ExtraDataEntryReader#ExtraDataEntryReader(String, DataEntryReader)} with extraEntryName is {@code null} and {@link DataEntryReader}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ExtraDataEntryReader#read(DataEntry)}
   */
  @Test
  @DisplayName("Test read(DataEntry); given ExtraDataEntryReader(String, DataEntryReader) with extraEntryName is 'null' and DataEntryReader")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void ExtraDataEntryReader.read(DataEntry)"})
  void testRead_givenExtraDataEntryReaderWithExtraEntryNameIsNullAndDataEntryReader() throws IOException {
    // Arrange
    DataEntryReader dataEntryReader = mock(DataEntryReader.class);
    doNothing().when(dataEntryReader).read(Mockito.<DataEntry>any());
    ExtraDataEntryReader extraDataEntryReader = new ExtraDataEntryReader((String) null, dataEntryReader);

    // Act
    extraDataEntryReader.read(new ClassPathDataEntry("Name"));

    // Assert
    verify(dataEntryReader, atLeast(1)).read(Mockito.<DataEntry>any());
  }

  /**
   * Test {@link ExtraDataEntryReader#read(DataEntry)}.
   * <ul>
   *   <li>Then throw {@link IOException}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ExtraDataEntryReader#read(DataEntry)}
   */
  @Test
  @DisplayName("Test read(DataEntry); then throw IOException")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void ExtraDataEntryReader.read(DataEntry)"})
  void testRead_thenThrowIOException() throws IOException {
    // Arrange
    DataEntryReader dataEntryReader = mock(DataEntryReader.class);
    doThrow(new IOException("foo")).when(dataEntryReader).read(Mockito.<DataEntry>any());
    ExtraDataEntryReader extraDataEntryReader = new ExtraDataEntryReader(new ExtraDataEntryNameMap(), dataEntryReader);

    // Act and Assert
    assertThrows(IOException.class, () -> extraDataEntryReader.read(new ClassPathDataEntry("Name")));
    verify(dataEntryReader).read(isA(DataEntry.class));
  }
}
