package proguard.io;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import java.io.IOException;
import java.io.OutputStream;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import proguard.Configuration;
import proguard.classfile.ClassPool;
import proguard.classfile.visitor.ClassVisitor;

class UniqueDataEntryWriterDiffblueTest {
  /**
   * Method under test: {@link UniqueDataEntryWriter#createDirectory(DataEntry)}
   */
  @Test
  void testCreateDirectory() throws IOException {
    // Arrange
    DataEntryFilter dataEntryFilter = mock(DataEntryFilter.class);
    when(dataEntryFilter.accepts(Mockito.<DataEntry>any())).thenReturn(false);
    UniqueDataEntryWriter uniqueDataEntryWriter = new UniqueDataEntryWriter(new FilteredDataEntryWriter(dataEntryFilter,
        new UniqueDataEntryWriter(new DirectoryWriter(Configuration.STD_OUT))));

    // Act
    boolean actualCreateDirectoryResult = uniqueDataEntryWriter.createDirectory(new ClassPathDataEntry("Name"));

    // Assert
    verify(dataEntryFilter).accepts(isA(DataEntry.class));
    assertFalse(actualCreateDirectoryResult);
  }

  /**
   * Method under test: {@link UniqueDataEntryWriter#createDirectory(DataEntry)}
   */
  @Test
  void testCreateDirectory2() throws IOException {
    // Arrange
    DataEntryFilter dataEntryFilter = mock(DataEntryFilter.class);
    when(dataEntryFilter.accepts(Mockito.<DataEntry>any())).thenReturn(true);
    DataEntryFilter dataEntryFilter2 = mock(DataEntryFilter.class);
    when(dataEntryFilter2.accepts(Mockito.<DataEntry>any())).thenReturn(false);
    UniqueDataEntryWriter uniqueDataEntryWriter = new UniqueDataEntryWriter(new FilteredDataEntryWriter(dataEntryFilter,
        new UniqueDataEntryWriter(new FilteredDataEntryWriter(dataEntryFilter2,
            new UniqueDataEntryWriter(new DirectoryWriter(Configuration.STD_OUT))))));

    // Act
    boolean actualCreateDirectoryResult = uniqueDataEntryWriter.createDirectory(new ClassPathDataEntry("Name"));

    // Assert
    verify(dataEntryFilter).accepts(isA(DataEntry.class));
    verify(dataEntryFilter2).accepts(isA(DataEntry.class));
    assertFalse(actualCreateDirectoryResult);
  }

  /**
   * Method under test: {@link UniqueDataEntryWriter#createDirectory(DataEntry)}
   */
  @Test
  void testCreateDirectory3() throws IOException {
    // Arrange
    DataEntryFilter dataEntryFilter = mock(DataEntryFilter.class);
    when(dataEntryFilter.accepts(Mockito.<DataEntry>any())).thenReturn(true);
    DataEntryFilter dataEntryFilter2 = mock(DataEntryFilter.class);
    when(dataEntryFilter2.accepts(Mockito.<DataEntry>any())).thenReturn(true);
    UniqueDataEntryWriter uniqueDataEntryWriter = new UniqueDataEntryWriter(new FilteredDataEntryWriter(dataEntryFilter,
        new UniqueDataEntryWriter(new FilteredDataEntryWriter(dataEntryFilter2,
            new UniqueDataEntryWriter(new DirectoryWriter(Configuration.STD_OUT))))));

    // Act
    boolean actualCreateDirectoryResult = uniqueDataEntryWriter.createDirectory(new ClassPathDataEntry(""));

    // Assert
    verify(dataEntryFilter).accepts(isA(DataEntry.class));
    verify(dataEntryFilter2).accepts(isA(DataEntry.class));
    assertTrue(actualCreateDirectoryResult);
  }

  /**
   * Method under test:
   * {@link UniqueDataEntryWriter#sameOutputStream(DataEntry, DataEntry)}
   */
  @Test
  void testSameOutputStream() throws IOException {
    // Arrange
    UniqueDataEntryWriter uniqueDataEntryWriter = new UniqueDataEntryWriter(new DirectoryWriter(Configuration.STD_OUT));
    ClassPathDataEntry dataEntry1 = new ClassPathDataEntry("Name");

    // Act and Assert
    assertTrue(uniqueDataEntryWriter.sameOutputStream(dataEntry1, new ClassPathDataEntry("Name")));
  }

  /**
   * Method under test:
   * {@link UniqueDataEntryWriter#sameOutputStream(DataEntry, DataEntry)}
   */
  @Test
  void testSameOutputStream2() throws IOException {
    // Arrange
    UniqueDataEntryWriter dataEntryWriter1 = new UniqueDataEntryWriter(new DirectoryWriter(Configuration.STD_OUT));
    UniqueDataEntryWriter uniqueDataEntryWriter = new UniqueDataEntryWriter(new CascadingDataEntryWriter(
        dataEntryWriter1, new UniqueDataEntryWriter(new DirectoryWriter(Configuration.STD_OUT))));
    ClassPathDataEntry dataEntry1 = new ClassPathDataEntry("Name");

    // Act and Assert
    assertTrue(uniqueDataEntryWriter.sameOutputStream(dataEntry1, new ClassPathDataEntry("Name")));
  }

  /**
   * Method under test:
   * {@link UniqueDataEntryWriter#sameOutputStream(DataEntry, DataEntry)}
   */
  @Test
  void testSameOutputStream3() throws IOException {
    // Arrange
    ClassPool classPool = new ClassPool();
    UniqueDataEntryWriter uniqueDataEntryWriter = new UniqueDataEntryWriter(new ClassDataEntryWriter(classPool,
        new UniqueDataEntryWriter(new DirectoryWriter(Configuration.STD_OUT)), mock(ClassVisitor.class)));
    ClassPathDataEntry dataEntry1 = new ClassPathDataEntry("Name");

    // Act and Assert
    assertTrue(uniqueDataEntryWriter.sameOutputStream(dataEntry1, new ClassPathDataEntry("Name")));
  }

  /**
   * Method under test:
   * {@link UniqueDataEntryWriter#sameOutputStream(DataEntry, DataEntry)}
   */
  @Test
  void testSameOutputStream4() throws IOException {
    // Arrange
    UniqueDataEntryWriter uniqueDataEntryWriter = new UniqueDataEntryWriter(new DirectoryWriter(Configuration.STD_OUT));
    ClassPathDataEntry dataEntry1 = new ClassPathDataEntry("42");

    // Act and Assert
    assertFalse(uniqueDataEntryWriter.sameOutputStream(dataEntry1, new ClassPathDataEntry("Name")));
  }

  /**
   * Method under test:
   * {@link UniqueDataEntryWriter#sameOutputStream(DataEntry, DataEntry)}
   */
  @Test
  void testSameOutputStream5() throws IOException {
    // Arrange
    UniqueDataEntryWriter dataEntryWriter1 = new UniqueDataEntryWriter(new DirectoryWriter(Configuration.STD_OUT));
    UniqueDataEntryWriter uniqueDataEntryWriter = new UniqueDataEntryWriter(new CascadingDataEntryWriter(
        dataEntryWriter1, new UniqueDataEntryWriter(new DirectoryWriter(Configuration.STD_OUT))));
    ClassPathDataEntry dataEntry1 = new ClassPathDataEntry("42");

    // Act and Assert
    assertFalse(uniqueDataEntryWriter.sameOutputStream(dataEntry1, new ClassPathDataEntry("Name")));
  }

  /**
   * Method under test:
   * {@link UniqueDataEntryWriter#createOutputStream(DataEntry)}
   */
  @Test
  void testCreateOutputStream() throws IOException {
    // Arrange
    DataEntryFilter dataEntryFilter = mock(DataEntryFilter.class);
    when(dataEntryFilter.accepts(Mockito.<DataEntry>any())).thenReturn(false);
    UniqueDataEntryWriter uniqueDataEntryWriter = new UniqueDataEntryWriter(new FilteredDataEntryWriter(dataEntryFilter,
        new UniqueDataEntryWriter(new DirectoryWriter(Configuration.STD_OUT))));

    // Act
    OutputStream actualCreateOutputStreamResult = uniqueDataEntryWriter
        .createOutputStream(new ClassPathDataEntry("Name"));

    // Assert
    verify(dataEntryFilter).accepts(isA(DataEntry.class));
    assertNull(actualCreateOutputStreamResult);
  }

  /**
   * Method under test:
   * {@link UniqueDataEntryWriter#createOutputStream(DataEntry)}
   */
  @Test
  void testCreateOutputStream2() throws IOException {
    // Arrange
    DataEntryFilter dataEntryFilter = mock(DataEntryFilter.class);
    when(dataEntryFilter.accepts(Mockito.<DataEntry>any())).thenReturn(true);
    DataEntryFilter dataEntryFilter2 = mock(DataEntryFilter.class);
    when(dataEntryFilter2.accepts(Mockito.<DataEntry>any())).thenReturn(false);
    UniqueDataEntryWriter uniqueDataEntryWriter = new UniqueDataEntryWriter(new FilteredDataEntryWriter(dataEntryFilter,
        new UniqueDataEntryWriter(new FilteredDataEntryWriter(dataEntryFilter2,
            new UniqueDataEntryWriter(new DirectoryWriter(Configuration.STD_OUT))))));

    // Act
    OutputStream actualCreateOutputStreamResult = uniqueDataEntryWriter
        .createOutputStream(new ClassPathDataEntry("Name"));

    // Assert
    verify(dataEntryFilter).accepts(isA(DataEntry.class));
    verify(dataEntryFilter2).accepts(isA(DataEntry.class));
    assertNull(actualCreateOutputStreamResult);
  }
}
