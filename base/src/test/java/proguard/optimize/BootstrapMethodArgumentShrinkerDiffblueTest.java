package proguard.optimize;

import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.anyInt;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import proguard.classfile.Clazz;
import proguard.classfile.LibraryClass;
import proguard.classfile.ProgramClass;
import proguard.classfile.ProgramMethod;
import proguard.classfile.attribute.BootstrapMethodInfo;
import proguard.classfile.constant.MethodHandleConstant;
import proguard.classfile.constant.visitor.ConstantVisitor;
import proguard.optimize.info.MethodOptimizationInfo;

class BootstrapMethodArgumentShrinkerDiffblueTest {
  /**
   * Method under test:
   * {@link BootstrapMethodArgumentShrinker#visitBootstrapMethodInfo(Clazz, BootstrapMethodInfo)}
   */
  @Test
  void testVisitBootstrapMethodInfo() {
    // Arrange
    BootstrapMethodArgumentShrinker bootstrapMethodArgumentShrinker = new BootstrapMethodArgumentShrinker();
    LibraryClass clazz = mock(LibraryClass.class);
    doNothing().when(clazz).constantPoolEntryAccept(anyInt(), Mockito.<ConstantVisitor>any());

    // Act
    bootstrapMethodArgumentShrinker.visitBootstrapMethodInfo(clazz, new BootstrapMethodInfo());

    // Assert
    verify(clazz).constantPoolEntryAccept(eq(0), isA(ConstantVisitor.class));
  }

  /**
   * Method under test:
   * {@link BootstrapMethodArgumentShrinker#visitBootstrapMethodInfo(Clazz, BootstrapMethodInfo)}
   */
  @Test
  void testVisitBootstrapMethodInfo2() {
    // Arrange
    BootstrapMethodArgumentShrinker bootstrapMethodArgumentShrinker = new BootstrapMethodArgumentShrinker();
    LibraryClass clazz = mock(LibraryClass.class);
    doNothing().when(clazz).constantPoolEntryAccept(anyInt(), Mockito.<ConstantVisitor>any());

    // Act
    bootstrapMethodArgumentShrinker.visitBootstrapMethodInfo(clazz,
        new BootstrapMethodInfo(1, 3, new int[]{-1, 1, -1, 1}));

    // Assert
    verify(clazz).constantPoolEntryAccept(eq(1), isA(ConstantVisitor.class));
  }

  /**
   * Method under test:
   * {@link BootstrapMethodArgumentShrinker#visitMethodHandleConstant(Clazz, MethodHandleConstant)}
   */
  @Test
  void testVisitMethodHandleConstant() {
    // Arrange
    BootstrapMethodArgumentShrinker bootstrapMethodArgumentShrinker = new BootstrapMethodArgumentShrinker();
    LibraryClass clazz = mock(LibraryClass.class);
    doNothing().when(clazz).constantPoolEntryAccept(anyInt(), Mockito.<ConstantVisitor>any());

    // Act
    bootstrapMethodArgumentShrinker.visitMethodHandleConstant(clazz, new MethodHandleConstant(1, 1));

    // Assert
    verify(clazz).constantPoolEntryAccept(eq(1), isA(ConstantVisitor.class));
  }

  /**
   * Method under test:
   * {@link BootstrapMethodArgumentShrinker#visitProgramMethod(ProgramClass, ProgramMethod)}
   */
  @Test
  void testVisitProgramMethod() {
    // Arrange
    BootstrapMethodArgumentShrinker bootstrapMethodArgumentShrinker = new BootstrapMethodArgumentShrinker();
    ProgramClass programClass = new ProgramClass();
    ProgramMethod programMethod = mock(ProgramMethod.class);
    when(programMethod.getProcessingInfo()).thenReturn(new MethodOptimizationInfo());

    // Act
    bootstrapMethodArgumentShrinker.visitProgramMethod(programClass, programMethod);

    // Assert
    verify(programMethod, atLeast(1)).getProcessingInfo();
  }

  /**
   * Method under test:
   * {@link BootstrapMethodArgumentShrinker#visitProgramMethod(ProgramClass, ProgramMethod)}
   */
  @Test
  void testVisitProgramMethod2() {
    // Arrange
    BootstrapMethodArgumentShrinker bootstrapMethodArgumentShrinker = new BootstrapMethodArgumentShrinker();
    ProgramClass programClass = new ProgramClass();
    MethodOptimizationInfo methodOptimizationInfo = mock(MethodOptimizationInfo.class);
    when(methodOptimizationInfo.getUsedParameters()).thenReturn(1L);
    ProgramMethod programMethod = mock(ProgramMethod.class);
    when(programMethod.getProcessingInfo()).thenReturn(methodOptimizationInfo);

    // Act
    bootstrapMethodArgumentShrinker.visitProgramMethod(programClass, programMethod);

    // Assert
    verify(methodOptimizationInfo).getUsedParameters();
    verify(programMethod, atLeast(1)).getProcessingInfo();
  }
}
