package proguard.io;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import java.io.IOException;
import java.util.function.BiFunction;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import proguard.Configuration;
import proguard.classfile.ClassPool;
import proguard.classfile.visitor.ClassVisitor;
import proguard.configuration.InitialStateInfo;
import proguard.io.util.IOUtil;

class ClassMapDataEntryReplacerDiffblueTest {
  /**
   * Method under test: {@link ClassMapDataEntryReplacer#read(DataEntry)}
   */
  @Test
  void testRead() throws IOException {
    // Arrange
    BiFunction<DataEntryReader, ClassVisitor, DataEntryReader> extraDataEntryReader = mock(BiFunction.class);
    when(extraDataEntryReader.apply(Mockito.<DataEntryReader>any(), Mockito.<ClassVisitor>any()))
        .thenReturn(mock(DataEntryReader.class));
    ClassPool programClassPool = IOUtil.read(new ClassPath(new ClassPathEntry(Configuration.STD_OUT, true)),
        "Class Name Filter", true, true, true, true, true, true, extraDataEntryReader);
    DataEntryFilter dataEntryFilter = mock(DataEntryFilter.class);
    when(dataEntryFilter.accepts(Mockito.<DataEntry>any())).thenReturn(false);
    UniqueDataEntryWriter dataEntryWriter = new UniqueDataEntryWriter(new FilteredDataEntryWriter(dataEntryFilter,
        new UniqueDataEntryWriter(new DirectoryWriter(Configuration.STD_OUT))));
    ClassMapDataEntryReplacer classMapDataEntryReplacer = new ClassMapDataEntryReplacer(programClassPool,
        new InitialStateInfo(new ClassPool()), dataEntryWriter);

    // Act
    classMapDataEntryReplacer.read(new ClassPathDataEntry("Name"));

    // Assert
    verify(extraDataEntryReader).apply(isA(DataEntryReader.class), isA(ClassVisitor.class));
    verify(dataEntryFilter).accepts(isA(DataEntry.class));
  }

  /**
   * Method under test: {@link ClassMapDataEntryReplacer#isKept(int)}
   */
  @Test
  void testIsKept() {
    // Arrange, Act and Assert
    assertFalse(ClassMapDataEntryReplacer.isKept(1));
    assertFalse(ClassMapDataEntryReplacer.isKept(4194304));
    assertTrue(ClassMapDataEntryReplacer.isKept(-1));
  }
}
