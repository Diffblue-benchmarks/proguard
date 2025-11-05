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
import proguard.classfile.ProgramClass;
import proguard.classfile.ProgramMethod;
import proguard.classfile.util.WarningPrinter;
import proguard.classfile.visitor.ClassVisitor;
import proguard.io.ClassPath;
import proguard.io.ClassPathEntry;
import proguard.io.DataEntryReader;
import proguard.io.util.IOUtil;

class DescriptorKeepCheckerDiffblueTest {
  /**
   * Method under test:
   * {@link DescriptorKeepChecker#checkClassSpecifications(List)}
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
    DescriptorKeepChecker descriptorKeepChecker = new DescriptorKeepChecker(programClassPool, libraryClassPool,
        new WarningPrinter(new PrintWriter(new StringWriter())));

    // Act
    descriptorKeepChecker.checkClassSpecifications(new ArrayList<>());

    // Assert
    verify(extraDataEntryReader).apply(isA(DataEntryReader.class), isA(ClassVisitor.class));
  }

  /**
   * Method under test:
   * {@link DescriptorKeepChecker#visitProgramMethod(ProgramClass, ProgramMethod)}
   */
  @Test
  void testVisitProgramMethod() throws IOException {
    // Arrange
    BiFunction<DataEntryReader, ClassVisitor, DataEntryReader> extraDataEntryReader = mock(BiFunction.class);
    when(extraDataEntryReader.apply(Mockito.<DataEntryReader>any(), Mockito.<ClassVisitor>any()))
        .thenReturn(mock(DataEntryReader.class));
    ClassPool programClassPool = IOUtil.read(new ClassPath(new ClassPathEntry(Configuration.STD_OUT, true)),
        "Class Name Filter", true, true, true, true, true, true, extraDataEntryReader);
    ClassPool libraryClassPool = new ClassPool();
    DescriptorKeepChecker descriptorKeepChecker = new DescriptorKeepChecker(programClassPool, libraryClassPool,
        new WarningPrinter(new PrintWriter(new StringWriter())));
    ProgramClass programClass = new ProgramClass();

    // Act
    descriptorKeepChecker.visitProgramMethod(programClass, new ProgramMethod());

    // Assert
    verify(extraDataEntryReader).apply(isA(DataEntryReader.class), isA(ClassVisitor.class));
  }
}
