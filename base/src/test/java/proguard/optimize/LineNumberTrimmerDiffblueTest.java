package proguard.optimize;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import proguard.AppView;
import proguard.classfile.ClassPool;
import proguard.classfile.visitor.ClassVisitor;

class LineNumberTrimmerDiffblueTest {
  /**
   * Method under test: {@link LineNumberTrimmer#execute(AppView)}
   */
  @Test
  void testExecute() {
    // Arrange
    LineNumberTrimmer lineNumberTrimmer = new LineNumberTrimmer();
    ClassPool programClassPool = mock(ClassPool.class);
    doNothing().when(programClassPool).classesAccept(Mockito.<ClassVisitor>any());

    // Act
    lineNumberTrimmer.execute(new AppView(programClassPool, new ClassPool()));

    // Assert
    verify(programClassPool).classesAccept(isA(ClassVisitor.class));
  }

  /**
   * Method under test: default or parameterless constructor of
   * {@link LineNumberTrimmer}
   */
  @Test
  void testNewLineNumberTrimmer() {
    // Arrange, Act and Assert
    assertEquals("proguard.optimize.LineNumberTrimmer", (new LineNumberTrimmer()).getName());
  }
}
