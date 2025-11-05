package proguard.optimize.evaluation;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.anyInt;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import proguard.classfile.ClassPool;
import proguard.classfile.Clazz;
import proguard.classfile.LibraryClass;
import proguard.classfile.constant.AnyMethodrefConstant;
import proguard.classfile.constant.InterfaceMethodrefConstant;
import proguard.evaluation.BasicInvocationUnit;
import proguard.evaluation.ParticularReferenceValueFactory;
import proguard.evaluation.ReferenceTracingInvocationUnit;
import proguard.evaluation.SimplifiedInvocationUnit;
import proguard.evaluation.value.BasicValueFactory;
import proguard.evaluation.value.IdentifiedReferenceValue;
import proguard.evaluation.value.InstructionOffsetValue;
import proguard.evaluation.value.MultiTypedReferenceValue;
import proguard.evaluation.value.MultiTypedReferenceValueFactory;
import proguard.evaluation.value.PrimitiveTypedReferenceValueFactory;
import proguard.evaluation.value.ReferenceValue;
import proguard.evaluation.value.TopValue;
import proguard.evaluation.value.TracedReferenceValue;
import proguard.evaluation.value.TypedReferenceValue;
import proguard.evaluation.value.Value;
import proguard.evaluation.value.object.AnalyzedObject;

class ParameterTracingInvocationUnitDiffblueTest {
  /**
   * Method under test:
   * {@link ParameterTracingInvocationUnit#setMethodParameterValue(Clazz, AnyMethodrefConstant, int, Value)}
   */
  @Test
  void testSetMethodParameterValue() {
    // Arrange
    BasicInvocationUnit invocationUnit = mock(BasicInvocationUnit.class);
    doNothing().when(invocationUnit)
        .setMethodParameterValue(Mockito.<Clazz>any(), Mockito.<AnyMethodrefConstant>any(), anyInt(),
            Mockito.<Value>any());
    ParameterTracingInvocationUnit parameterTracingInvocationUnit = new ParameterTracingInvocationUnit(invocationUnit);
    LibraryClass clazz = new LibraryClass();
    InterfaceMethodrefConstant refConstant = new InterfaceMethodrefConstant();

    // Act
    parameterTracingInvocationUnit.setMethodParameterValue(clazz, refConstant, 1, new TopValue());

    // Assert
    verify(invocationUnit).setMethodParameterValue(isA(Clazz.class), isA(AnyMethodrefConstant.class), eq(1),
        isA(Value.class));
  }

  /**
   * Method under test:
   * {@link ParameterTracingInvocationUnit#getMethodReturnValue(Clazz, AnyMethodrefConstant, String)}
   */
  @Test
  void testGetMethodReturnValue() {
    // Arrange
    ParameterTracingInvocationUnit parameterTracingInvocationUnit = new ParameterTracingInvocationUnit(
        new LoadingInvocationUnit(new ParticularReferenceValueFactory()));
    LibraryClass clazz = new LibraryClass();

    // Act
    Value actualMethodReturnValue = parameterTracingInvocationUnit.getMethodReturnValue(clazz,
        new InterfaceMethodrefConstant(), "Type");

    // Assert
    ReferenceValue referenceValue = ((TracedReferenceValue) actualMethodReturnValue).getReferenceValue();
    assertTrue(referenceValue instanceof IdentifiedReferenceValue);
    Value traceValue = ((TracedReferenceValue) actualMethodReturnValue).getTraceValue();
    assertTrue(traceValue instanceof InstructionOffsetValue);
    assertTrue(actualMethodReturnValue instanceof TracedReferenceValue);
    assertEquals("Type", referenceValue.getType());
    assertEquals("Type", ((TracedReferenceValue) actualMethodReturnValue).getType());
    AnalyzedObject value = ((TracedReferenceValue) actualMethodReturnValue).getValue();
    assertNull(value.getPreciseValue());
    assertNull(referenceValue.getReferencedClass());
    assertNull(((TracedReferenceValue) actualMethodReturnValue).getReferencedClass());
    assertNull(value.getModeledOrNullValue());
    assertEquals(0, referenceValue.isNotNull());
    assertEquals(0, ((TracedReferenceValue) actualMethodReturnValue).isNotNull());
    assertEquals(0, referenceValue.isNull());
    assertEquals(0, ((TracedReferenceValue) actualMethodReturnValue).isNull());
    assertFalse(referenceValue.isCategory2());
    assertFalse(traceValue.isCategory2());
    assertFalse(actualMethodReturnValue.isCategory2());
    assertFalse(referenceValue.isParticular());
    assertFalse(actualMethodReturnValue.isParticular());
    assertTrue(referenceValue.mayBeExtension());
    assertTrue(traceValue.isParticular());
    assertTrue(referenceValue.isSpecific());
    assertTrue(traceValue.isSpecific());
    assertTrue(actualMethodReturnValue.isSpecific());
    assertSame(value, referenceValue.getValue());
  }

  /**
   * Method under test:
   * {@link ParameterTracingInvocationUnit#getMethodReturnValue(Clazz, AnyMethodrefConstant, String)}
   */
  @Test
  void testGetMethodReturnValue2() {
    // Arrange
    BasicValueFactory valueFactory = new BasicValueFactory();
    ParameterTracingInvocationUnit parameterTracingInvocationUnit = new ParameterTracingInvocationUnit(
        new LoadingInvocationUnit(valueFactory));
    LibraryClass clazz = new LibraryClass();

    // Act
    Value actualMethodReturnValue = parameterTracingInvocationUnit.getMethodReturnValue(clazz,
        new InterfaceMethodrefConstant(), "Type");

    // Assert
    Value traceValue = ((TracedReferenceValue) actualMethodReturnValue).getTraceValue();
    assertTrue(traceValue instanceof InstructionOffsetValue);
    assertTrue(actualMethodReturnValue instanceof TracedReferenceValue);
    assertEquals("Ljava/lang/Object;", ((TracedReferenceValue) actualMethodReturnValue).getType());
    AnalyzedObject value = ((TracedReferenceValue) actualMethodReturnValue).getValue();
    assertNull(value.getPreciseValue());
    assertNull(((TracedReferenceValue) actualMethodReturnValue).getReferencedClass());
    assertNull(value.getModeledOrNullValue());
    assertEquals(0, ((TracedReferenceValue) actualMethodReturnValue).isNotNull());
    assertEquals(0, ((TracedReferenceValue) actualMethodReturnValue).isNull());
    assertFalse(traceValue.isCategory2());
    assertFalse(actualMethodReturnValue.isCategory2());
    assertFalse(actualMethodReturnValue.isParticular());
    assertFalse(actualMethodReturnValue.isSpecific());
    assertTrue(traceValue.isParticular());
    assertTrue(traceValue.isSpecific());
    ReferenceValue expectedReferenceValue = valueFactory.REFERENCE_VALUE;
    assertSame(expectedReferenceValue, ((TracedReferenceValue) actualMethodReturnValue).getReferenceValue());
  }

  /**
   * Method under test:
   * {@link ParameterTracingInvocationUnit#getMethodReturnValue(Clazz, AnyMethodrefConstant, String)}
   */
  @Test
  void testGetMethodReturnValue3() {
    // Arrange
    ParameterTracingInvocationUnit parameterTracingInvocationUnit = new ParameterTracingInvocationUnit(
        new LoadingInvocationUnit(new MultiTypedReferenceValueFactory()));
    LibraryClass clazz = new LibraryClass();

    // Act
    Value actualMethodReturnValue = parameterTracingInvocationUnit.getMethodReturnValue(clazz,
        new InterfaceMethodrefConstant(), "Type");

    // Assert
    Value traceValue = ((TracedReferenceValue) actualMethodReturnValue).getTraceValue();
    assertTrue(traceValue instanceof InstructionOffsetValue);
    ReferenceValue referenceValue = ((TracedReferenceValue) actualMethodReturnValue).getReferenceValue();
    assertTrue(referenceValue instanceof MultiTypedReferenceValue);
    assertTrue(actualMethodReturnValue instanceof TracedReferenceValue);
    assertEquals("Type", referenceValue.getType());
    assertEquals("Type", ((TracedReferenceValue) actualMethodReturnValue).getType());
    TypedReferenceValue generalizedType = ((MultiTypedReferenceValue) referenceValue).getGeneralizedType();
    assertEquals("Type", generalizedType.getType());
    AnalyzedObject value = ((TracedReferenceValue) actualMethodReturnValue).getValue();
    assertNull(value.getPreciseValue());
    assertNull(referenceValue.getReferencedClass());
    assertNull(((TracedReferenceValue) actualMethodReturnValue).getReferencedClass());
    assertNull(generalizedType.getReferencedClass());
    assertNull(value.getModeledOrNullValue());
    assertEquals(0, generalizedType.isNotNull());
    assertEquals(0, referenceValue.isNotNull());
    assertEquals(0, ((TracedReferenceValue) actualMethodReturnValue).isNotNull());
    assertEquals(0, ((TracedReferenceValue) actualMethodReturnValue).isNull());
    assertEquals(0, generalizedType.isNull());
    assertEquals(1, ((MultiTypedReferenceValue) referenceValue).getPotentialTypes().size());
    assertFalse(generalizedType.isCategory2());
    assertFalse(referenceValue.isCategory2());
    assertFalse(generalizedType.isParticular());
    assertFalse(traceValue.isCategory2());
    assertFalse(actualMethodReturnValue.isCategory2());
    assertFalse(referenceValue.isParticular());
    assertFalse(actualMethodReturnValue.isParticular());
    assertFalse(generalizedType.isSpecific());
    assertFalse(referenceValue.isSpecific());
    assertFalse(actualMethodReturnValue.isSpecific());
    assertFalse(((MultiTypedReferenceValue) referenceValue).mayBeUnknown);
    assertTrue(generalizedType.mayBeExtension());
    assertTrue(traceValue.isParticular());
    assertTrue(traceValue.isSpecific());
    assertSame(value, generalizedType.getValue());
    assertSame(value, referenceValue.getValue());
  }

  /**
   * Method under test:
   * {@link ParameterTracingInvocationUnit#getMethodReturnValue(Clazz, AnyMethodrefConstant, String)}
   */
  @Test
  void testGetMethodReturnValue4() {
    // Arrange
    ClassPool programClassPool = new ClassPool();
    ParameterTracingInvocationUnit parameterTracingInvocationUnit = new ParameterTracingInvocationUnit(
        new LoadingInvocationUnit(new MultiTypedReferenceValueFactory(true, programClassPool, new ClassPool())));
    LibraryClass clazz = new LibraryClass();

    // Act
    Value actualMethodReturnValue = parameterTracingInvocationUnit.getMethodReturnValue(clazz,
        new InterfaceMethodrefConstant(), "Type");

    // Assert
    Value traceValue = ((TracedReferenceValue) actualMethodReturnValue).getTraceValue();
    assertTrue(traceValue instanceof InstructionOffsetValue);
    ReferenceValue referenceValue = ((TracedReferenceValue) actualMethodReturnValue).getReferenceValue();
    assertTrue(referenceValue instanceof MultiTypedReferenceValue);
    assertTrue(actualMethodReturnValue instanceof TracedReferenceValue);
    assertEquals("Type", referenceValue.getType());
    assertEquals("Type", ((TracedReferenceValue) actualMethodReturnValue).getType());
    TypedReferenceValue generalizedType = ((MultiTypedReferenceValue) referenceValue).getGeneralizedType();
    assertEquals("Type", generalizedType.getType());
    AnalyzedObject value = ((TracedReferenceValue) actualMethodReturnValue).getValue();
    assertNull(value.getPreciseValue());
    assertNull(referenceValue.getReferencedClass());
    assertNull(((TracedReferenceValue) actualMethodReturnValue).getReferencedClass());
    assertNull(generalizedType.getReferencedClass());
    assertNull(value.getModeledOrNullValue());
    assertEquals(0, generalizedType.isNotNull());
    assertEquals(0, referenceValue.isNotNull());
    assertEquals(0, ((TracedReferenceValue) actualMethodReturnValue).isNotNull());
    assertEquals(0, ((TracedReferenceValue) actualMethodReturnValue).isNull());
    assertEquals(0, generalizedType.isNull());
    assertEquals(1, ((MultiTypedReferenceValue) referenceValue).getPotentialTypes().size());
    assertFalse(generalizedType.isCategory2());
    assertFalse(referenceValue.isCategory2());
    assertFalse(generalizedType.isParticular());
    assertFalse(traceValue.isCategory2());
    assertFalse(actualMethodReturnValue.isCategory2());
    assertFalse(referenceValue.isParticular());
    assertFalse(actualMethodReturnValue.isParticular());
    assertFalse(generalizedType.isSpecific());
    assertFalse(referenceValue.isSpecific());
    assertFalse(actualMethodReturnValue.isSpecific());
    assertFalse(((MultiTypedReferenceValue) referenceValue).mayBeUnknown);
    assertTrue(generalizedType.mayBeExtension());
    assertTrue(traceValue.isParticular());
    assertTrue(traceValue.isSpecific());
    assertSame(value, generalizedType.getValue());
    assertSame(value, referenceValue.getValue());
  }

  /**
   * Method under test:
   * {@link ParameterTracingInvocationUnit#getMethodReturnValue(Clazz, AnyMethodrefConstant, String)}
   */
  @Test
  void testGetMethodReturnValue5() {
    // Arrange
    ParameterTracingInvocationUnit parameterTracingInvocationUnit = new ParameterTracingInvocationUnit(
        new ReferenceTracingInvocationUnit(new LoadingInvocationUnit(new ParticularReferenceValueFactory())));
    LibraryClass clazz = new LibraryClass();

    // Act
    Value actualMethodReturnValue = parameterTracingInvocationUnit.getMethodReturnValue(clazz,
        new InterfaceMethodrefConstant(), "Type");

    // Assert
    ReferenceValue referenceValue = ((TracedReferenceValue) actualMethodReturnValue).getReferenceValue();
    assertTrue(referenceValue instanceof IdentifiedReferenceValue);
    Value traceValue = ((TracedReferenceValue) actualMethodReturnValue).getTraceValue();
    assertTrue(traceValue instanceof InstructionOffsetValue);
    assertTrue(actualMethodReturnValue instanceof TracedReferenceValue);
    assertEquals("Type", referenceValue.getType());
    assertEquals("Type", ((TracedReferenceValue) actualMethodReturnValue).getType());
    AnalyzedObject value = ((TracedReferenceValue) actualMethodReturnValue).getValue();
    assertNull(value.getPreciseValue());
    assertNull(referenceValue.getReferencedClass());
    assertNull(((TracedReferenceValue) actualMethodReturnValue).getReferencedClass());
    assertNull(value.getModeledOrNullValue());
    assertEquals(0, referenceValue.isNotNull());
    assertEquals(0, ((TracedReferenceValue) actualMethodReturnValue).isNotNull());
    assertEquals(0, referenceValue.isNull());
    assertEquals(0, ((TracedReferenceValue) actualMethodReturnValue).isNull());
    assertFalse(referenceValue.isCategory2());
    assertFalse(traceValue.isCategory2());
    assertFalse(actualMethodReturnValue.isCategory2());
    assertFalse(referenceValue.isParticular());
    assertFalse(actualMethodReturnValue.isParticular());
    assertTrue(referenceValue.mayBeExtension());
    assertTrue(traceValue.isParticular());
    assertTrue(referenceValue.isSpecific());
    assertTrue(traceValue.isSpecific());
    assertTrue(actualMethodReturnValue.isSpecific());
    assertSame(value, referenceValue.getValue());
  }

  /**
   * Method under test:
   * {@link ParameterTracingInvocationUnit#getMethodReturnValue(Clazz, AnyMethodrefConstant, String)}
   */
  @Test
  void testGetMethodReturnValue6() {
    // Arrange
    ParameterTracingInvocationUnit parameterTracingInvocationUnit = new ParameterTracingInvocationUnit(
        new ParameterTracingInvocationUnit(new LoadingInvocationUnit(new ParticularReferenceValueFactory())));
    LibraryClass clazz = new LibraryClass();

    // Act
    Value actualMethodReturnValue = parameterTracingInvocationUnit.getMethodReturnValue(clazz,
        new InterfaceMethodrefConstant(), "Type");

    // Assert
    ReferenceValue referenceValue = ((TracedReferenceValue) actualMethodReturnValue).getReferenceValue();
    assertTrue(referenceValue instanceof IdentifiedReferenceValue);
    Value traceValue = ((TracedReferenceValue) actualMethodReturnValue).getTraceValue();
    assertTrue(traceValue instanceof InstructionOffsetValue);
    assertTrue(actualMethodReturnValue instanceof TracedReferenceValue);
    assertEquals("Type", referenceValue.getType());
    assertEquals("Type", ((TracedReferenceValue) actualMethodReturnValue).getType());
    AnalyzedObject value = ((TracedReferenceValue) actualMethodReturnValue).getValue();
    assertNull(value.getPreciseValue());
    assertNull(referenceValue.getReferencedClass());
    assertNull(((TracedReferenceValue) actualMethodReturnValue).getReferencedClass());
    assertNull(value.getModeledOrNullValue());
    assertEquals(0, referenceValue.isNotNull());
    assertEquals(0, ((TracedReferenceValue) actualMethodReturnValue).isNotNull());
    assertEquals(0, referenceValue.isNull());
    assertEquals(0, ((TracedReferenceValue) actualMethodReturnValue).isNull());
    assertFalse(referenceValue.isCategory2());
    assertFalse(traceValue.isCategory2());
    assertFalse(actualMethodReturnValue.isCategory2());
    assertFalse(referenceValue.isParticular());
    assertFalse(actualMethodReturnValue.isParticular());
    assertTrue(referenceValue.mayBeExtension());
    assertTrue(traceValue.isParticular());
    assertTrue(referenceValue.isSpecific());
    assertTrue(traceValue.isSpecific());
    assertTrue(actualMethodReturnValue.isSpecific());
    assertSame(value, referenceValue.getValue());
  }

  /**
   * Method under test:
   * {@link ParameterTracingInvocationUnit#getMethodReturnValue(Clazz, AnyMethodrefConstant, String)}
   */
  @Test
  void testGetMethodReturnValue7() {
    // Arrange
    PrimitiveTypedReferenceValueFactory valueFactory = new PrimitiveTypedReferenceValueFactory();
    ParameterTracingInvocationUnit parameterTracingInvocationUnit = new ParameterTracingInvocationUnit(
        new LoadingInvocationUnit(valueFactory));
    LibraryClass clazz = new LibraryClass();

    // Act
    Value actualMethodReturnValue = parameterTracingInvocationUnit.getMethodReturnValue(clazz,
        new InterfaceMethodrefConstant(), "Type");

    // Assert
    Value traceValue = ((TracedReferenceValue) actualMethodReturnValue).getTraceValue();
    assertTrue(traceValue instanceof InstructionOffsetValue);
    assertTrue(actualMethodReturnValue instanceof TracedReferenceValue);
    assertEquals("Ljava/lang/Object;", ((TracedReferenceValue) actualMethodReturnValue).getType());
    AnalyzedObject value = ((TracedReferenceValue) actualMethodReturnValue).getValue();
    assertNull(value.getPreciseValue());
    assertNull(((TracedReferenceValue) actualMethodReturnValue).getReferencedClass());
    assertNull(value.getModeledOrNullValue());
    assertEquals(0, ((TracedReferenceValue) actualMethodReturnValue).isNotNull());
    assertEquals(0, ((TracedReferenceValue) actualMethodReturnValue).isNull());
    assertFalse(traceValue.isCategory2());
    assertFalse(actualMethodReturnValue.isCategory2());
    assertFalse(actualMethodReturnValue.isParticular());
    assertFalse(actualMethodReturnValue.isSpecific());
    assertTrue(traceValue.isParticular());
    assertTrue(traceValue.isSpecific());
    ReferenceValue expectedReferenceValue = valueFactory.REFERENCE_VALUE;
    assertSame(expectedReferenceValue, ((TracedReferenceValue) actualMethodReturnValue).getReferenceValue());
  }

  /**
   * Method under test:
   * {@link ParameterTracingInvocationUnit#ParameterTracingInvocationUnit(SimplifiedInvocationUnit)}
   */
  @Test
  void testNewParameterTracingInvocationUnit() {
    // Arrange, Act and Assert
    Value exceptionValue = (new ParameterTracingInvocationUnit(
        new LoadingInvocationUnit(new ParticularReferenceValueFactory()))).getExceptionValue(null, null);
    ReferenceValue referenceValue = ((TracedReferenceValue) exceptionValue).getReferenceValue();
    assertTrue(referenceValue instanceof IdentifiedReferenceValue);
    Value traceValue = ((TracedReferenceValue) exceptionValue).getTraceValue();
    assertTrue(traceValue instanceof InstructionOffsetValue);
    assertTrue(exceptionValue instanceof TracedReferenceValue);
    assertEquals("Ljava/lang/Throwable;", referenceValue.getType());
    assertEquals("Ljava/lang/Throwable;", ((TracedReferenceValue) exceptionValue).getType());
    AnalyzedObject value = ((TracedReferenceValue) exceptionValue).getValue();
    assertNull(value.getPreciseValue());
    assertNull(referenceValue.getReferencedClass());
    assertNull(((TracedReferenceValue) exceptionValue).getReferencedClass());
    assertNull(value.getModeledOrNullValue());
    assertEquals(-1, referenceValue.isNull());
    assertEquals(-1, ((TracedReferenceValue) exceptionValue).isNull());
    assertEquals(1, referenceValue.isNotNull());
    assertEquals(1, ((TracedReferenceValue) exceptionValue).isNotNull());
    assertFalse(referenceValue.isCategory2());
    assertFalse(exceptionValue.isCategory2());
    assertFalse(traceValue.isCategory2());
    assertFalse(exceptionValue.isParticular());
    assertFalse(referenceValue.isParticular());
    assertTrue(referenceValue.mayBeExtension());
    assertTrue(traceValue.isParticular());
    assertTrue(exceptionValue.isSpecific());
    assertTrue(referenceValue.isSpecific());
    assertTrue(traceValue.isSpecific());
    assertSame(value, referenceValue.getValue());
  }
}
