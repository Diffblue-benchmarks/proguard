package proguard.optimize.info;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.junit.jupiter.api.Test;
import proguard.classfile.ProgramClass;
import proguard.classfile.ProgramField;
import proguard.classfile.ProgramMethod;
import proguard.evaluation.value.ParticularFloatValue;
import proguard.evaluation.value.Value;
import proguard.testutils.cpa.NamedField;

class ProgramMemberOptimizationInfoSetterDiffblueTest {
  /**
   * Method under test:
   * {@link ProgramMemberOptimizationInfoSetter#visitProgramField(ProgramClass, ProgramField)}
   */
  @Test
  void testVisitProgramField() {
    // Arrange
    ProgramMemberOptimizationInfoSetter programMemberOptimizationInfoSetter = new ProgramMemberOptimizationInfoSetter(
        true);
    ProgramClass programClass = new ProgramClass();
    NamedField programField = new NamedField("Field Name", "Field Descriptor");

    // Act
    programMemberOptimizationInfoSetter.visitProgramField(programClass, programField);

    // Assert
    Object processingInfo = programField.getProcessingInfo();
    Value value = ((ProgramFieldOptimizationInfo) processingInfo).getValue();
    assertTrue(value instanceof ParticularFloatValue);
    assertTrue(processingInfo instanceof ProgramFieldOptimizationInfo);
    assertNull(((ProgramFieldOptimizationInfo) processingInfo).getReferencedClass());
    assertEquals(0.0f, ((ParticularFloatValue) value).value());
    assertFalse(value.isCategory2());
    assertFalse(((ProgramFieldOptimizationInfo) processingInfo).isKept());
    assertFalse(((ProgramFieldOptimizationInfo) processingInfo).isRead());
    assertFalse(((ProgramFieldOptimizationInfo) processingInfo).isWritten());
    assertTrue(value.isParticular());
    assertTrue(value.isSpecific());
    assertTrue(((ProgramFieldOptimizationInfo) processingInfo).canBeMadePrivate());
  }

  /**
   * Method under test:
   * {@link ProgramMemberOptimizationInfoSetter#visitProgramMethod(ProgramClass, ProgramMethod)}
   */
  @Test
  void testVisitProgramMethod() {
    // Arrange
    ProgramMemberOptimizationInfoSetter programMemberOptimizationInfoSetter = new ProgramMemberOptimizationInfoSetter(
        false);
    ProgramClass programClass = mock(ProgramClass.class);
    ProgramMethod programMethod = mock(ProgramMethod.class);
    when(programMethod.getProcessingInfo()).thenReturn("Processing Info");

    // Act
    programMemberOptimizationInfoSetter.visitProgramMethod(programClass, programMethod);

    // Assert
    verify(programMethod, atLeast(1)).getProcessingInfo();
  }
}
