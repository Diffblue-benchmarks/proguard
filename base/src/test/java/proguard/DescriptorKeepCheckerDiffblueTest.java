package proguard;

import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import proguard.classfile.ClassPool;
import proguard.classfile.util.WarningPrinter;
import proguard.classfile.visitor.ClassPoolVisitor;
import proguard.classfile.visitor.ClassVisitor;

class DescriptorKeepCheckerDiffblueTest {
  /**
   * Test {@link DescriptorKeepChecker#checkClassSpecifications(List)}.
   *
   * <p>Method under test: {@link DescriptorKeepChecker#checkClassSpecifications(List)}
   */
  @Test
  @DisplayName("Test checkClassSpecifications(List)")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void DescriptorKeepChecker.checkClassSpecifications(List)"})
  void testCheckClassSpecifications() {
    // Arrange
    ClassPool programClassPool = mock(ClassPool.class);
    doNothing().when(programClassPool).accept(Mockito.<ClassPoolVisitor>any());
    doNothing().when(programClassPool).classesAccept(Mockito.<ClassVisitor>any());
    ClassPool libraryClassPool = new ClassPool();
    WarningPrinter notePrinter = new WarningPrinter(new PrintWriter(new StringWriter()));

    DescriptorKeepChecker descriptorKeepChecker =
        new DescriptorKeepChecker(programClassPool, libraryClassPool, notePrinter);

    // Act
    descriptorKeepChecker.checkClassSpecifications(new ArrayList<>());

    // Assert
    verify(programClassPool).accept(isA(ClassPoolVisitor.class));
    verify(programClassPool, atLeast(1)).classesAccept(Mockito.<ClassVisitor>any());
  }

  /**
   * Test {@link DescriptorKeepChecker#checkClassSpecifications(List)}.
   *
   * <p>Method under test: {@link DescriptorKeepChecker#checkClassSpecifications(List)}
   */
  @Test
  @DisplayName("Test checkClassSpecifications(List)")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void DescriptorKeepChecker.checkClassSpecifications(List)"})
  void testCheckClassSpecifications2() {
    // Arrange
    ClassPool programClassPool = mock(ClassPool.class);
    doNothing().when(programClassPool).accept(Mockito.<ClassPoolVisitor>any());
    doNothing().when(programClassPool).classesAccept(Mockito.<ClassVisitor>any());

    ClassPool libraryClassPool = mock(ClassPool.class);
    doNothing().when(libraryClassPool).accept(Mockito.<ClassPoolVisitor>any());
    doNothing().when(libraryClassPool).classesAccept(Mockito.<ClassVisitor>any());
    WarningPrinter notePrinter = new WarningPrinter(new PrintWriter(new StringWriter()));

    DescriptorKeepChecker descriptorKeepChecker =
        new DescriptorKeepChecker(programClassPool, libraryClassPool, notePrinter);

    // Act
    descriptorKeepChecker.checkClassSpecifications(new ArrayList<>());

    // Assert
    verify(programClassPool).accept(isA(ClassPoolVisitor.class));
    verify(libraryClassPool).accept(isA(ClassPoolVisitor.class));
    verify(libraryClassPool).classesAccept(isA(ClassVisitor.class));
    verify(programClassPool, atLeast(1)).classesAccept(Mockito.<ClassVisitor>any());
  }
}
