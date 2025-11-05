package proguard;

import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.function.BiFunction;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import proguard.classfile.ClassPool;
import proguard.classfile.Clazz;
import proguard.classfile.LibraryClass;
import proguard.classfile.util.WarningPrinter;
import proguard.classfile.visitor.ClassVisitor;
import proguard.io.ClassPath;
import proguard.io.ClassPathEntry;
import proguard.io.DataEntryReader;
import proguard.io.util.IOUtil;

class FullyQualifiedClassNameCheckerDiffblueTest {
  /**
   * Method under test:
   * {@link FullyQualifiedClassNameChecker#checkClassSpecifications(List)}
   */
  @Test
  void testCheckClassSpecifications() throws IOException {
    // Arrange
    BiFunction<DataEntryReader, ClassVisitor, DataEntryReader> extraDataEntryReader = mock(BiFunction.class);
    when(extraDataEntryReader.apply(Mockito.<DataEntryReader>any(), Mockito.<ClassVisitor>any()))
        .thenReturn(mock(DataEntryReader.class));
    ClassPool programClassPool = IOUtil.read(new ClassPath(new ClassPathEntry(Configuration.STD_OUT, true)),
        "Class Name Filter", true, true, true, true, true, true, extraDataEntryReader);
    ClassPool libraryClassPool = new ClassPool();
    FullyQualifiedClassNameChecker fullyQualifiedClassNameChecker = new FullyQualifiedClassNameChecker(programClassPool,
        libraryClassPool, new WarningPrinter(new PrintWriter(new StringWriter())));

    // Act
    fullyQualifiedClassNameChecker.checkClassSpecifications(new ArrayList<>());

    // Assert that nothing has changed
    verify(extraDataEntryReader).apply(isA(DataEntryReader.class), isA(ClassVisitor.class));
  }

  /**
   * Method under test:
   * {@link FullyQualifiedClassNameChecker#visitAnyClass(Clazz)}
   */
  @Test
  void testVisitAnyClass() throws IOException {
    // Arrange
    BiFunction<DataEntryReader, ClassVisitor, DataEntryReader> extraDataEntryReader = mock(BiFunction.class);
    when(extraDataEntryReader.apply(Mockito.<DataEntryReader>any(), Mockito.<ClassVisitor>any()))
        .thenReturn(mock(DataEntryReader.class));
    ClassPool programClassPool = IOUtil.read(new ClassPath(new ClassPathEntry(Configuration.STD_OUT, true)),
        "      Maybe you meant the fully qualified name '{}'?", true, true, true, true, true, true,
        extraDataEntryReader);
    ClassPool libraryClassPool = new ClassPool();
    FullyQualifiedClassNameChecker fullyQualifiedClassNameChecker = new FullyQualifiedClassNameChecker(programClassPool,
        libraryClassPool, new WarningPrinter(new PrintWriter(new StringWriter())));

    // Act
    fullyQualifiedClassNameChecker
        .visitAnyClass(new LibraryClass(1, "      Maybe you meant the fully qualified name '{}'?",
            "      Maybe you meant the fully qualified name '{}'?"));

    // Assert that nothing has changed
    verify(extraDataEntryReader).apply(isA(DataEntryReader.class), isA(ClassVisitor.class));
  }
}
