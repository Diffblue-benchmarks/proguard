package proguard;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import java.io.IOException;
import java.util.function.BiFunction;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import proguard.classfile.ClassPool;
import proguard.classfile.LibraryClass;
import proguard.classfile.visitor.ClassVisitor;
import proguard.io.ClassPath;
import proguard.io.ClassPathEntry;
import proguard.io.DataEntryReader;
import proguard.io.util.IOUtil;

class KotlinMetadataAdapterDiffblueTest {
  /**
   * Method under test: {@link KotlinMetadataAdapter#execute(AppView)}
   */
  @Test
  void testExecute() throws IOException {
    // Arrange
    KotlinMetadataAdapter kotlinMetadataAdapter = new KotlinMetadataAdapter();
    BiFunction<DataEntryReader, ClassVisitor, DataEntryReader> extraDataEntryReader = mock(BiFunction.class);
    when(extraDataEntryReader.apply(Mockito.<DataEntryReader>any(), Mockito.<ClassVisitor>any()))
        .thenReturn(mock(DataEntryReader.class));
    ClassPool programClassPool = IOUtil.read(new ClassPath(new ClassPathEntry(Configuration.STD_OUT, true)),
        "Adapting Kotlin metadata...", true, true, true, true, true, true, extraDataEntryReader);

    // Act
    kotlinMetadataAdapter.execute(new AppView(programClassPool, new ClassPool()));

    // Assert
    verify(extraDataEntryReader).apply(isA(DataEntryReader.class), isA(ClassVisitor.class));
  }

  /**
   * Method under test: {@link KotlinMetadataAdapter#execute(AppView)}
   */
  @Test
  void testExecute2() throws IOException {
    // Arrange
    KotlinMetadataAdapter kotlinMetadataAdapter = new KotlinMetadataAdapter();
    BiFunction<DataEntryReader, ClassVisitor, DataEntryReader> extraDataEntryReader = mock(BiFunction.class);
    when(extraDataEntryReader.apply(Mockito.<DataEntryReader>any(), Mockito.<ClassVisitor>any()))
        .thenReturn(mock(DataEntryReader.class));
    ClassPool programClassPool = IOUtil.read(new ClassPath(new ClassPathEntry(Configuration.STD_OUT, true)),
        "Adapting Kotlin metadata...", true, true, true, true, true, true, extraDataEntryReader);
    programClassPool.addClass("  Number of Kotlin classes adapted:              ", new LibraryClass());

    // Act
    kotlinMetadataAdapter.execute(new AppView(programClassPool, new ClassPool()));

    // Assert
    verify(extraDataEntryReader).apply(isA(DataEntryReader.class), isA(ClassVisitor.class));
  }

  /**
   * Method under test: default or parameterless constructor of
   * {@link KotlinMetadataAdapter}
   */
  @Test
  void testNewKotlinMetadataAdapter() {
    // Arrange, Act and Assert
    assertEquals("proguard.KotlinMetadataAdapter", (new KotlinMetadataAdapter()).getName());
  }
}
