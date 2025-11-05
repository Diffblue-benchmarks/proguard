package proguard.io;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import java.io.IOException;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

class ExtraDataEntryReaderDiffblueTest {
  /**
   * Method under test:
   * {@link ExtraDataEntryReader#ExtraDataEntryReader(String, DataEntryReader)}
   */
  @Test
  void testNewExtraDataEntryReader() throws IOException {
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
   * Method under test:
   * {@link ExtraDataEntryReader#ExtraDataEntryReader(String, DataEntryReader)}
   */
  @Test
  void testNewExtraDataEntryReader2() throws IOException {
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
   * Method under test:
   * {@link ExtraDataEntryReader#ExtraDataEntryReader(String, DataEntryReader)}
   */
  @Test
  void testNewExtraDataEntryReader3() throws IOException {
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
   * Method under test:
   * {@link ExtraDataEntryReader#ExtraDataEntryReader(String, DataEntryReader, DataEntryReader)}
   */
  @Test
  void testNewExtraDataEntryReader4() throws IOException {
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
   * Method under test:
   * {@link ExtraDataEntryReader#ExtraDataEntryReader(String, DataEntryReader, DataEntryReader)}
   */
  @Test
  void testNewExtraDataEntryReader5() throws IOException {
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
   * Method under test:
   * {@link ExtraDataEntryReader#ExtraDataEntryReader(String, DataEntryReader, DataEntryReader)}
   */
  @Test
  void testNewExtraDataEntryReader6() throws IOException {
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
   * Method under test: {@link ExtraDataEntryReader#read(DataEntry)}
   */
  @Test
  void testRead() throws IOException {
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
   * Method under test: {@link ExtraDataEntryReader#read(DataEntry)}
   */
  @Test
  void testRead2() throws IOException {
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
   * Method under test: {@link ExtraDataEntryReader#read(DataEntry)}
   */
  @Test
  void testRead3() throws IOException {
    // Arrange
    DataEntryReader dataEntryReader = mock(DataEntryReader.class);
    doThrow(new IOException("foo")).when(dataEntryReader).read(Mockito.<DataEntry>any());
    ExtraDataEntryReader extraDataEntryReader = new ExtraDataEntryReader(new ExtraDataEntryNameMap(), dataEntryReader);

    // Act and Assert
    assertThrows(IOException.class, () -> extraDataEntryReader.read(new ClassPathDataEntry("Name")));
    verify(dataEntryReader).read(isA(DataEntry.class));
  }

  /**
   * Method under test: {@link ExtraDataEntryReader#read(DataEntry)}
   */
  @Test
  void testRead4() throws IOException {
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
   * Method under test: {@link ExtraDataEntryReader#read(DataEntry)}
   */
  @Test
  void testRead5() throws IOException {
    // Arrange
    DataEntryReader dataEntryReader = mock(DataEntryReader.class);
    doThrow(new IOException("foo")).when(dataEntryReader).read(Mockito.<DataEntry>any());
    ExtraDataEntryReader extraDataEntryReader = new ExtraDataEntryReader(new ExtraDataEntryNameMap(), dataEntryReader);

    // Act and Assert
    assertThrows(IOException.class,
        () -> extraDataEntryReader.read(new DummyDataEntry(new ClassPathDataEntry("Name"), "Name", 3L, true)));
    verify(dataEntryReader).read(isA(DataEntry.class));
  }
}
