package proguard;

import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import java.security.KeyStore;
import org.junit.jupiter.api.Test;
import proguard.classfile.ClassPool;
import proguard.io.DataEntryWriter;
import proguard.io.DirectoryWriter;
import proguard.io.NameFilteredDataEntryWriter;
import proguard.io.UniqueDataEntryWriter;
import proguard.resources.file.ResourceFilePool;
import proguard.util.EmptyStringMatcher;
import proguard.util.StringFunction;
import proguard.util.StringMatcher;
import proguard.util.TransformedStringMatcher;

class DataEntryWriterFactoryDiffblueTest {
  /**
   * Method under test:
   * {@link DataEntryWriterFactory#createDataEntryWriter(ClassPath, int, int, DataEntryWriter)}
   */
  @Test
  void testCreateDataEntryWriter() {
    // Arrange
    ClassPool programClassPool = new ClassPool();
    ResourceFilePool resourceFilePool = new ResourceFilePool();
    StringFunction stringFunction = mock(StringFunction.class);
    DataEntryWriterFactory dataEntryWriterFactory = new DataEntryWriterFactory(programClassPool, resourceFilePool, 1,
        new TransformedStringMatcher(stringFunction, new EmptyStringMatcher()), 1, true, true,
        new KeyStore.PrivateKeyEntry[]{null});
    ClassPath classPath = new ClassPath();

    // Act and Assert
    assertNull(dataEntryWriterFactory.createDataEntryWriter(classPath, 1, 1,
        new UniqueDataEntryWriter(new DirectoryWriter(Configuration.STD_OUT))));
  }

  /**
   * Method under test:
   * {@link DataEntryWriterFactory#createDataEntryWriter(ClassPath, int, int, DataEntryWriter)}
   */
  @Test
  void testCreateDataEntryWriter2() {
    // Arrange
    ClassPool programClassPool = new ClassPool();
    ResourceFilePool resourceFilePool = new ResourceFilePool();
    StringFunction stringFunction = mock(StringFunction.class);
    DataEntryWriterFactory dataEntryWriterFactory = new DataEntryWriterFactory(programClassPool, resourceFilePool, 1,
        new TransformedStringMatcher(stringFunction, new EmptyStringMatcher()), 1, true, true, null);

    ClassPath classPath = new ClassPath();
    classPath.add(0, new ClassPathEntry(Configuration.STD_OUT, true));

    // Act and Assert
    assertTrue(dataEntryWriterFactory.createDataEntryWriter(classPath, 0, 1,
        new UniqueDataEntryWriter(new DirectoryWriter(Configuration.STD_OUT))) instanceof NameFilteredDataEntryWriter);
  }

  /**
   * Method under test:
   * {@link DataEntryWriterFactory#createDataEntryWriter(ClassPath, int, int, DataEntryWriter)}
   */
  @Test
  void testCreateDataEntryWriter3() {
    // Arrange
    ClassPool programClassPool = new ClassPool();
    ResourceFilePool resourceFilePool = new ResourceFilePool();
    StringFunction stringFunction = mock(StringFunction.class);
    DataEntryWriterFactory dataEntryWriterFactory = new DataEntryWriterFactory(programClassPool, resourceFilePool, 1,
        new TransformedStringMatcher(stringFunction, new EmptyStringMatcher()), 1, true, true, null);

    ClassPath classPath = new ClassPath();
    classPath.add(0, new ClassPathEntry(Configuration.STD_OUT, false));
    classPath.add(0, new ClassPathEntry(Configuration.STD_OUT, true));

    // Act and Assert
    assertTrue(dataEntryWriterFactory.createDataEntryWriter(classPath, 0, 1,
        new UniqueDataEntryWriter(new DirectoryWriter(Configuration.STD_OUT))) instanceof NameFilteredDataEntryWriter);
  }

  /**
   * Method under test:
   * {@link DataEntryWriterFactory#DataEntryWriterFactory(ClassPool, ResourceFilePool, int, StringMatcher, int, boolean, boolean, KeyStore.PrivateKeyEntry[])}
   */
  @Test
  void testNewDataEntryWriterFactory() {
    // Arrange
    ClassPool programClassPool = new ClassPool();
    ResourceFilePool resourceFilePool = new ResourceFilePool();
    StringFunction stringFunction = mock(StringFunction.class);

    // Act and Assert
    assertNull((new DataEntryWriterFactory(programClassPool, resourceFilePool, 1,
        new TransformedStringMatcher(stringFunction, new EmptyStringMatcher()), 1, true, true,
        new KeyStore.PrivateKeyEntry[]{null})).createDataEntryWriter(null, 1, 1, null));
  }
}
