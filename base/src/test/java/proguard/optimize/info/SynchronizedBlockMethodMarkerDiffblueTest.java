package proguard.optimize.info;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.junit.jupiter.api.Test;
import proguard.classfile.Clazz;
import proguard.classfile.LibraryClass;
import proguard.classfile.LibraryMethod;
import proguard.classfile.Method;
import proguard.classfile.attribute.CodeAttribute;
import proguard.classfile.instruction.SimpleInstruction;

class SynchronizedBlockMethodMarkerDiffblueTest {
  /**
   * Method under test:
   * {@link SynchronizedBlockMethodMarker#visitSimpleInstruction(Clazz, Method, CodeAttribute, int, SimpleInstruction)}
   */
  @Test
  void testVisitSimpleInstruction() {
    // Arrange
    SynchronizedBlockMethodMarker synchronizedBlockMethodMarker = new SynchronizedBlockMethodMarker();
    LibraryClass clazz = new LibraryClass();
    ProgramMethodOptimizationInfo programMethodOptimizationInfo = mock(ProgramMethodOptimizationInfo.class);
    doNothing().when(programMethodOptimizationInfo).setHasSynchronizedBlock();
    Method method = mock(Method.class);
    when(method.getProcessingInfo()).thenReturn(programMethodOptimizationInfo);
    CodeAttribute codeAttribute = new CodeAttribute(1);

    // Act
    synchronizedBlockMethodMarker.visitSimpleInstruction(clazz, method, codeAttribute, 2,
        new SimpleInstruction((byte) -62));

    // Assert
    verify(programMethodOptimizationInfo).setHasSynchronizedBlock();
    verify(method, atLeast(1)).getProcessingInfo();
  }

  /**
   * Method under test:
   * {@link SynchronizedBlockMethodMarker#hasSynchronizedBlock(Method)}
   */
  @Test
  void testHasSynchronizedBlock() {
    // Arrange
    LibraryMethod method = new LibraryMethod(1, "Name", "Descriptor");
    method.setProcessingInfo(new MethodOptimizationInfo());

    // Act and Assert
    assertTrue(SynchronizedBlockMethodMarker.hasSynchronizedBlock(method));
  }
}
