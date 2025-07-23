package proguard.optimize.evaluation;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import proguard.classfile.ClassPool;
import proguard.classfile.Clazz;
import proguard.classfile.LibraryClass;
import proguard.classfile.constant.AnyMethodrefConstant;
import proguard.classfile.constant.InterfaceMethodrefConstant;
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
import proguard.evaluation.value.Value;

class ParameterTracingInvocationUnitDiffblueTest {
  /**
   * Test {@link
   * ParameterTracingInvocationUnit#ParameterTracingInvocationUnit(SimplifiedInvocationUnit)}.
   *
   * <p>Method under test: {@link
   * ParameterTracingInvocationUnit#ParameterTracingInvocationUnit(SimplifiedInvocationUnit)}
   */
  @Test
  @DisplayName("Test new ParameterTracingInvocationUnit(SimplifiedInvocationUnit)")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void ParameterTracingInvocationUnit.<init>(SimplifiedInvocationUnit)"})
  void testNewParameterTracingInvocationUnit() {
    // Arrange
    LoadingInvocationUnit invocationUnit =
        new LoadingInvocationUnit(new ParticularReferenceValueFactory());

    // Act
    ParameterTracingInvocationUnit actualParameterTracingInvocationUnit =
        new ParameterTracingInvocationUnit(invocationUnit);

    // Assert
    assertTrue(invocationUnit.getExceptionValue(null, null) instanceof IdentifiedReferenceValue);
    Value exceptionValue = actualParameterTracingInvocationUnit.getExceptionValue(null, null);
    assertTrue(
        ((TracedReferenceValue) exceptionValue).getReferenceValue()
            instanceof IdentifiedReferenceValue);
    assertTrue(
        ((TracedReferenceValue) exceptionValue).getTraceValue() instanceof InstructionOffsetValue);
    assertTrue(exceptionValue instanceof TracedReferenceValue);
    assertEquals("Ljava/lang/Throwable;", ((TracedReferenceValue) exceptionValue).getType());
    assertNull(((TracedReferenceValue) exceptionValue).getReferencedClass());
    assertEquals(-1, ((TracedReferenceValue) exceptionValue).isNull());
    assertEquals(1, ((TracedReferenceValue) exceptionValue).isNotNull());
    assertFalse(exceptionValue.isCategory2());
    assertFalse(exceptionValue.isParticular());
    assertTrue(exceptionValue.isSpecific());
  }

  /**
   * Test {@link ParameterTracingInvocationUnit#setMethodParameterValue(Clazz, AnyMethodrefConstant,
   * int, Value)}.
   *
   * <p>Method under test: {@link ParameterTracingInvocationUnit#setMethodParameterValue(Clazz,
   * AnyMethodrefConstant, int, Value)}
   */
  @Test
  @DisplayName("Test setMethodParameterValue(Clazz, AnyMethodrefConstant, int, Value)")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void ParameterTracingInvocationUnit.setMethodParameterValue(Clazz, AnyMethodrefConstant, int, Value)"
  })
  void testSetMethodParameterValue() {
    // Arrange
    ParameterTracingInvocationUnit parameterTracingInvocationUnit =
        new ParameterTracingInvocationUnit(
            new LoadingInvocationUnit(new ParticularReferenceValueFactory()));
    LibraryClass clazz = new LibraryClass();
    InterfaceMethodrefConstant refConstant = new InterfaceMethodrefConstant();

    // Act
    parameterTracingInvocationUnit.setMethodParameterValue(clazz, refConstant, 1, new TopValue());

    // Assert
    Value exceptionValue = parameterTracingInvocationUnit.getExceptionValue(null, null);
    assertTrue(
        ((TracedReferenceValue) exceptionValue).getReferenceValue()
            instanceof IdentifiedReferenceValue);
    assertTrue(exceptionValue instanceof TracedReferenceValue);
  }

  /**
   * Test {@link ParameterTracingInvocationUnit#setMethodParameterValue(Clazz, AnyMethodrefConstant,
   * int, Value)}.
   *
   * <p>Method under test: {@link ParameterTracingInvocationUnit#setMethodParameterValue(Clazz,
   * AnyMethodrefConstant, int, Value)}
   */
  @Test
  @DisplayName("Test setMethodParameterValue(Clazz, AnyMethodrefConstant, int, Value)")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void ParameterTracingInvocationUnit.setMethodParameterValue(Clazz, AnyMethodrefConstant, int, Value)"
  })
  void testSetMethodParameterValue2() {
    // Arrange
    ParameterTracingInvocationUnit parameterTracingInvocationUnit =
        new ParameterTracingInvocationUnit(
            new ReferenceTracingInvocationUnit(
                new LoadingInvocationUnit(new ParticularReferenceValueFactory())));
    LibraryClass clazz = new LibraryClass();
    InterfaceMethodrefConstant refConstant = new InterfaceMethodrefConstant();

    // Act
    parameterTracingInvocationUnit.setMethodParameterValue(clazz, refConstant, 1, new TopValue());

    // Assert
    Value exceptionValue = parameterTracingInvocationUnit.getExceptionValue(null, null);
    assertTrue(
        ((TracedReferenceValue) exceptionValue).getReferenceValue()
            instanceof IdentifiedReferenceValue);
    assertTrue(exceptionValue instanceof TracedReferenceValue);
  }

  /**
   * Test {@link ParameterTracingInvocationUnit#setMethodParameterValue(Clazz, AnyMethodrefConstant,
   * int, Value)}.
   *
   * <p>Method under test: {@link ParameterTracingInvocationUnit#setMethodParameterValue(Clazz,
   * AnyMethodrefConstant, int, Value)}
   */
  @Test
  @DisplayName("Test setMethodParameterValue(Clazz, AnyMethodrefConstant, int, Value)")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void ParameterTracingInvocationUnit.setMethodParameterValue(Clazz, AnyMethodrefConstant, int, Value)"
  })
  void testSetMethodParameterValue3() {
    // Arrange
    ParameterTracingInvocationUnit parameterTracingInvocationUnit =
        new ParameterTracingInvocationUnit(
            new ParameterTracingInvocationUnit(
                new LoadingInvocationUnit(new ParticularReferenceValueFactory())));
    LibraryClass clazz = new LibraryClass();
    InterfaceMethodrefConstant refConstant = new InterfaceMethodrefConstant();

    // Act
    parameterTracingInvocationUnit.setMethodParameterValue(clazz, refConstant, 1, new TopValue());

    // Assert
    Value exceptionValue = parameterTracingInvocationUnit.getExceptionValue(null, null);
    assertTrue(
        ((TracedReferenceValue) exceptionValue).getReferenceValue()
            instanceof IdentifiedReferenceValue);
    assertTrue(exceptionValue instanceof TracedReferenceValue);
  }

  /**
   * Test {@link ParameterTracingInvocationUnit#setMethodParameterValue(Clazz, AnyMethodrefConstant,
   * int, Value)}.
   *
   * <p>Method under test: {@link ParameterTracingInvocationUnit#setMethodParameterValue(Clazz,
   * AnyMethodrefConstant, int, Value)}
   */
  @Test
  @DisplayName("Test setMethodParameterValue(Clazz, AnyMethodrefConstant, int, Value)")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void ParameterTracingInvocationUnit.setMethodParameterValue(Clazz, AnyMethodrefConstant, int, Value)"
  })
  void testSetMethodParameterValue4() {
    // Arrange
    ParameterTracingInvocationUnit parameterTracingInvocationUnit =
        new ParameterTracingInvocationUnit(
            new StoringInvocationUnit(new ParticularReferenceValueFactory()));
    LibraryClass clazz = new LibraryClass();
    InterfaceMethodrefConstant refConstant = new InterfaceMethodrefConstant();

    // Act
    parameterTracingInvocationUnit.setMethodParameterValue(clazz, refConstant, 1, new TopValue());

    // Assert
    Value exceptionValue = parameterTracingInvocationUnit.getExceptionValue(null, null);
    assertTrue(
        ((TracedReferenceValue) exceptionValue).getReferenceValue()
            instanceof IdentifiedReferenceValue);
    assertTrue(exceptionValue instanceof TracedReferenceValue);
  }

  /**
   * Test {@link ParameterTracingInvocationUnit#getMethodReturnValue(Clazz, AnyMethodrefConstant,
   * String)} with {@code clazz}, {@code refConstant}, {@code type}.
   *
   * <p>Method under test: {@link ParameterTracingInvocationUnit#getMethodReturnValue(Clazz,
   * AnyMethodrefConstant, String)}
   */
  @Test
  @DisplayName(
      "Test getMethodReturnValue(Clazz, AnyMethodrefConstant, String) with 'clazz', 'refConstant', 'type'")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({
    "Value ParameterTracingInvocationUnit.getMethodReturnValue(Clazz, AnyMethodrefConstant, String)"
  })
  void testGetMethodReturnValueWithClazzRefConstantType() {
    // Arrange
    ParameterTracingInvocationUnit parameterTracingInvocationUnit =
        new ParameterTracingInvocationUnit(
            new LoadingInvocationUnit(new ParticularReferenceValueFactory()));
    LibraryClass clazz = new LibraryClass();

    // Act
    Value actualMethodReturnValue =
        parameterTracingInvocationUnit.getMethodReturnValue(
            clazz, new InterfaceMethodrefConstant(), "Type");

    // Assert
    ReferenceValue referenceValue =
        ((TracedReferenceValue) actualMethodReturnValue).getReferenceValue();
    assertTrue(referenceValue instanceof IdentifiedReferenceValue);
    assertTrue(actualMethodReturnValue instanceof TracedReferenceValue);
    assertEquals("Type", referenceValue.getType());
    assertNull(referenceValue.getReferencedClass());
    assertEquals(0, referenceValue.isNull());
    assertFalse(referenceValue.isParticular());
    assertTrue(referenceValue.mayBeExtension());
    assertTrue(referenceValue.isSpecific());
    assertTrue(actualMethodReturnValue.isSpecific());
  }

  /**
   * Test {@link ParameterTracingInvocationUnit#getMethodReturnValue(Clazz, AnyMethodrefConstant,
   * String)} with {@code clazz}, {@code refConstant}, {@code type}.
   *
   * <p>Method under test: {@link ParameterTracingInvocationUnit#getMethodReturnValue(Clazz,
   * AnyMethodrefConstant, String)}
   */
  @Test
  @DisplayName(
      "Test getMethodReturnValue(Clazz, AnyMethodrefConstant, String) with 'clazz', 'refConstant', 'type'")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({
    "Value ParameterTracingInvocationUnit.getMethodReturnValue(Clazz, AnyMethodrefConstant, String)"
  })
  void testGetMethodReturnValueWithClazzRefConstantType2() {
    // Arrange
    BasicValueFactory valueFactory = new BasicValueFactory();
    ParameterTracingInvocationUnit parameterTracingInvocationUnit =
        new ParameterTracingInvocationUnit(new LoadingInvocationUnit(valueFactory));
    LibraryClass clazz = new LibraryClass();

    // Act
    Value actualMethodReturnValue =
        parameterTracingInvocationUnit.getMethodReturnValue(
            clazz, new InterfaceMethodrefConstant(), "Type");

    // Assert
    assertTrue(actualMethodReturnValue instanceof TracedReferenceValue);
    assertEquals("Ljava/lang/Object;", ((TracedReferenceValue) actualMethodReturnValue).getType());
    ReferenceValue expectedReferenceValue = valueFactory.REFERENCE_VALUE;
    assertSame(
        expectedReferenceValue,
        ((TracedReferenceValue) actualMethodReturnValue).getReferenceValue());
  }

  /**
   * Test {@link ParameterTracingInvocationUnit#getMethodReturnValue(Clazz, AnyMethodrefConstant,
   * String)} with {@code clazz}, {@code refConstant}, {@code type}.
   *
   * <p>Method under test: {@link ParameterTracingInvocationUnit#getMethodReturnValue(Clazz,
   * AnyMethodrefConstant, String)}
   */
  @Test
  @DisplayName(
      "Test getMethodReturnValue(Clazz, AnyMethodrefConstant, String) with 'clazz', 'refConstant', 'type'")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({
    "Value ParameterTracingInvocationUnit.getMethodReturnValue(Clazz, AnyMethodrefConstant, String)"
  })
  void testGetMethodReturnValueWithClazzRefConstantType3() {
    // Arrange
    ParameterTracingInvocationUnit parameterTracingInvocationUnit =
        new ParameterTracingInvocationUnit(
            new LoadingInvocationUnit(new MultiTypedReferenceValueFactory()));
    LibraryClass clazz = new LibraryClass();

    // Act
    Value actualMethodReturnValue =
        parameterTracingInvocationUnit.getMethodReturnValue(
            clazz, new InterfaceMethodrefConstant(), "Type");

    // Assert
    ReferenceValue referenceValue =
        ((TracedReferenceValue) actualMethodReturnValue).getReferenceValue();
    assertTrue(referenceValue instanceof MultiTypedReferenceValue);
    assertTrue(actualMethodReturnValue instanceof TracedReferenceValue);
    assertEquals("Type", referenceValue.getType());
    assertNull(referenceValue.getReferencedClass());
    assertEquals(1, ((MultiTypedReferenceValue) referenceValue).getPotentialTypes().size());
    assertFalse(referenceValue.isParticular());
    assertFalse(((MultiTypedReferenceValue) referenceValue).mayBeUnknown);
  }

  /**
   * Test {@link ParameterTracingInvocationUnit#getMethodReturnValue(Clazz, AnyMethodrefConstant,
   * String)} with {@code clazz}, {@code refConstant}, {@code type}.
   *
   * <p>Method under test: {@link ParameterTracingInvocationUnit#getMethodReturnValue(Clazz,
   * AnyMethodrefConstant, String)}
   */
  @Test
  @DisplayName(
      "Test getMethodReturnValue(Clazz, AnyMethodrefConstant, String) with 'clazz', 'refConstant', 'type'")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({
    "Value ParameterTracingInvocationUnit.getMethodReturnValue(Clazz, AnyMethodrefConstant, String)"
  })
  void testGetMethodReturnValueWithClazzRefConstantType4() {
    // Arrange
    ClassPool programClassPool = new ClassPool();
    ParameterTracingInvocationUnit parameterTracingInvocationUnit =
        new ParameterTracingInvocationUnit(
            new LoadingInvocationUnit(
                new MultiTypedReferenceValueFactory(true, programClassPool, new ClassPool())));
    LibraryClass clazz = new LibraryClass();

    // Act
    Value actualMethodReturnValue =
        parameterTracingInvocationUnit.getMethodReturnValue(
            clazz, new InterfaceMethodrefConstant(), "Type");

    // Assert
    ReferenceValue referenceValue =
        ((TracedReferenceValue) actualMethodReturnValue).getReferenceValue();
    assertTrue(referenceValue instanceof MultiTypedReferenceValue);
    assertTrue(actualMethodReturnValue instanceof TracedReferenceValue);
    assertEquals("Type", referenceValue.getType());
    assertNull(referenceValue.getReferencedClass());
    assertEquals(1, ((MultiTypedReferenceValue) referenceValue).getPotentialTypes().size());
    assertFalse(referenceValue.isParticular());
    assertFalse(((MultiTypedReferenceValue) referenceValue).mayBeUnknown);
  }

  /**
   * Test {@link ParameterTracingInvocationUnit#getMethodReturnValue(Clazz, AnyMethodrefConstant,
   * String)} with {@code clazz}, {@code refConstant}, {@code type}.
   *
   * <p>Method under test: {@link ParameterTracingInvocationUnit#getMethodReturnValue(Clazz,
   * AnyMethodrefConstant, String)}
   */
  @Test
  @DisplayName(
      "Test getMethodReturnValue(Clazz, AnyMethodrefConstant, String) with 'clazz', 'refConstant', 'type'")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({
    "Value ParameterTracingInvocationUnit.getMethodReturnValue(Clazz, AnyMethodrefConstant, String)"
  })
  void testGetMethodReturnValueWithClazzRefConstantType5() {
    // Arrange
    ParameterTracingInvocationUnit parameterTracingInvocationUnit =
        new ParameterTracingInvocationUnit(
            new ReferenceTracingInvocationUnit(
                new LoadingInvocationUnit(new ParticularReferenceValueFactory())));
    LibraryClass clazz = new LibraryClass();

    // Act
    Value actualMethodReturnValue =
        parameterTracingInvocationUnit.getMethodReturnValue(
            clazz, new InterfaceMethodrefConstant(), "Type");

    // Assert
    ReferenceValue referenceValue =
        ((TracedReferenceValue) actualMethodReturnValue).getReferenceValue();
    assertTrue(referenceValue instanceof IdentifiedReferenceValue);
    assertTrue(actualMethodReturnValue instanceof TracedReferenceValue);
    assertEquals("Type", referenceValue.getType());
    assertNull(referenceValue.getReferencedClass());
    assertEquals(0, referenceValue.isNull());
    assertFalse(referenceValue.isParticular());
    assertTrue(referenceValue.mayBeExtension());
    assertTrue(referenceValue.isSpecific());
    assertTrue(actualMethodReturnValue.isSpecific());
  }

  /**
   * Test {@link ParameterTracingInvocationUnit#getMethodReturnValue(Clazz, AnyMethodrefConstant,
   * String)} with {@code clazz}, {@code refConstant}, {@code type}.
   *
   * <p>Method under test: {@link ParameterTracingInvocationUnit#getMethodReturnValue(Clazz,
   * AnyMethodrefConstant, String)}
   */
  @Test
  @DisplayName(
      "Test getMethodReturnValue(Clazz, AnyMethodrefConstant, String) with 'clazz', 'refConstant', 'type'")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({
    "Value ParameterTracingInvocationUnit.getMethodReturnValue(Clazz, AnyMethodrefConstant, String)"
  })
  void testGetMethodReturnValueWithClazzRefConstantType6() {
    // Arrange
    ParameterTracingInvocationUnit parameterTracingInvocationUnit =
        new ParameterTracingInvocationUnit(
            new ParameterTracingInvocationUnit(
                new LoadingInvocationUnit(new ParticularReferenceValueFactory())));
    LibraryClass clazz = new LibraryClass();

    // Act
    Value actualMethodReturnValue =
        parameterTracingInvocationUnit.getMethodReturnValue(
            clazz, new InterfaceMethodrefConstant(), "Type");

    // Assert
    ReferenceValue referenceValue =
        ((TracedReferenceValue) actualMethodReturnValue).getReferenceValue();
    assertTrue(referenceValue instanceof IdentifiedReferenceValue);
    assertTrue(actualMethodReturnValue instanceof TracedReferenceValue);
    assertEquals("Type", referenceValue.getType());
    assertNull(referenceValue.getReferencedClass());
    assertEquals(0, referenceValue.isNull());
    assertFalse(referenceValue.isParticular());
    assertTrue(referenceValue.mayBeExtension());
    assertTrue(referenceValue.isSpecific());
    assertTrue(actualMethodReturnValue.isSpecific());
  }

  /**
   * Test {@link ParameterTracingInvocationUnit#getMethodReturnValue(Clazz, AnyMethodrefConstant,
   * String)} with {@code clazz}, {@code refConstant}, {@code type}.
   *
   * <p>Method under test: {@link ParameterTracingInvocationUnit#getMethodReturnValue(Clazz,
   * AnyMethodrefConstant, String)}
   */
  @Test
  @DisplayName(
      "Test getMethodReturnValue(Clazz, AnyMethodrefConstant, String) with 'clazz', 'refConstant', 'type'")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({
    "Value ParameterTracingInvocationUnit.getMethodReturnValue(Clazz, AnyMethodrefConstant, String)"
  })
  void testGetMethodReturnValueWithClazzRefConstantType7() {
    // Arrange
    PrimitiveTypedReferenceValueFactory valueFactory = new PrimitiveTypedReferenceValueFactory();
    ParameterTracingInvocationUnit parameterTracingInvocationUnit =
        new ParameterTracingInvocationUnit(new LoadingInvocationUnit(valueFactory));
    LibraryClass clazz = new LibraryClass();

    // Act
    Value actualMethodReturnValue =
        parameterTracingInvocationUnit.getMethodReturnValue(
            clazz, new InterfaceMethodrefConstant(), "Type");

    // Assert
    assertTrue(actualMethodReturnValue instanceof TracedReferenceValue);
    assertEquals("Ljava/lang/Object;", ((TracedReferenceValue) actualMethodReturnValue).getType());
    ReferenceValue expectedReferenceValue = valueFactory.REFERENCE_VALUE;
    assertSame(
        expectedReferenceValue,
        ((TracedReferenceValue) actualMethodReturnValue).getReferenceValue());
  }
}
