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
import proguard.classfile.LibraryField;
import proguard.classfile.LibraryMethod;
import proguard.classfile.Method;
import proguard.classfile.constant.AnyMethodrefConstant;
import proguard.classfile.constant.FieldrefConstant;
import proguard.classfile.constant.InterfaceMethodrefConstant;
import proguard.evaluation.ParticularReferenceValueFactory;
import proguard.evaluation.value.BasicValueFactory;
import proguard.evaluation.value.IdentifiedReferenceValue;
import proguard.evaluation.value.MultiTypedReferenceValue;
import proguard.evaluation.value.MultiTypedReferenceValueFactory;
import proguard.evaluation.value.PrimitiveTypedReferenceValueFactory;
import proguard.evaluation.value.TypedReferenceValue;
import proguard.evaluation.value.UnknownReferenceValue;
import proguard.evaluation.value.Value;
import proguard.evaluation.value.ValueFactory;
import proguard.evaluation.value.object.AnalyzedObject;
import proguard.optimize.info.ProgramFieldOptimizationInfo;

class LoadingInvocationUnitDiffblueTest {
  /**
   * Test {@link LoadingInvocationUnit#LoadingInvocationUnit(ValueFactory)}.
   *
   * <p>Method under test: {@link LoadingInvocationUnit#LoadingInvocationUnit(ValueFactory)}
   */
  @Test
  @DisplayName("Test new LoadingInvocationUnit(ValueFactory)")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void LoadingInvocationUnit.<init>(ValueFactory)"})
  void testNewLoadingInvocationUnit() {
    // Arrange, Act and Assert
    Value exceptionValue =
        new LoadingInvocationUnit(new ParticularReferenceValueFactory())
            .getExceptionValue(null, null);
    assertTrue(exceptionValue instanceof IdentifiedReferenceValue);
    assertEquals("Ljava/lang/Throwable;", ((IdentifiedReferenceValue) exceptionValue).getType());
    AnalyzedObject value = ((IdentifiedReferenceValue) exceptionValue).getValue();
    assertNull(value.getPreciseValue());
    assertNull(((IdentifiedReferenceValue) exceptionValue).getReferencedClass());
    assertNull(value.getModeledOrNullValue());
    assertEquals(-1, ((IdentifiedReferenceValue) exceptionValue).isNull());
    assertEquals(1, ((IdentifiedReferenceValue) exceptionValue).isNotNull());
    assertFalse(exceptionValue.isCategory2());
    assertFalse(exceptionValue.isParticular());
    assertTrue(((IdentifiedReferenceValue) exceptionValue).mayBeExtension());
    assertTrue(exceptionValue.isSpecific());
  }

  /**
   * Test {@link LoadingInvocationUnit#LoadingInvocationUnit(ValueFactory, boolean, boolean,
   * boolean)}.
   *
   * <p>Method under test: {@link LoadingInvocationUnit#LoadingInvocationUnit(ValueFactory, boolean,
   * boolean, boolean)}
   */
  @Test
  @DisplayName("Test new LoadingInvocationUnit(ValueFactory, boolean, boolean, boolean)")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void LoadingInvocationUnit.<init>(ValueFactory, boolean, boolean, boolean)"})
  void testNewLoadingInvocationUnit2() {
    // Arrange, Act and Assert
    Value exceptionValue =
        new LoadingInvocationUnit(new ParticularReferenceValueFactory(), true, true, true)
            .getExceptionValue(null, null);
    assertTrue(exceptionValue instanceof IdentifiedReferenceValue);
    assertEquals("Ljava/lang/Throwable;", ((IdentifiedReferenceValue) exceptionValue).getType());
    AnalyzedObject value = ((IdentifiedReferenceValue) exceptionValue).getValue();
    assertNull(value.getPreciseValue());
    assertNull(((IdentifiedReferenceValue) exceptionValue).getReferencedClass());
    assertNull(value.getModeledOrNullValue());
    assertEquals(-1, ((IdentifiedReferenceValue) exceptionValue).isNull());
    assertEquals(1, ((IdentifiedReferenceValue) exceptionValue).isNotNull());
    assertFalse(exceptionValue.isCategory2());
    assertFalse(exceptionValue.isParticular());
    assertTrue(((IdentifiedReferenceValue) exceptionValue).mayBeExtension());
    assertTrue(exceptionValue.isSpecific());
  }

  /**
   * Test {@link LoadingInvocationUnit#getFieldClassValue(Clazz, FieldrefConstant, String)}.
   *
   * <p>Method under test: {@link LoadingInvocationUnit#getFieldClassValue(Clazz, FieldrefConstant,
   * String)}
   */
  @Test
  @DisplayName("Test getFieldClassValue(Clazz, FieldrefConstant, String)")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({
    "Value LoadingInvocationUnit.getFieldClassValue(Clazz, FieldrefConstant, String)"
  })
  void testGetFieldClassValue() {
    // Arrange
    LoadingInvocationUnit loadingInvocationUnit =
        new LoadingInvocationUnit(new ParticularReferenceValueFactory());
    LibraryClass clazz = new LibraryClass();
    LibraryClass clazz2 = new LibraryClass();

    ProgramFieldOptimizationInfo programFieldOptimizationInfo =
        new ProgramFieldOptimizationInfo(
            new ProgramFieldOptimizationInfo(
                clazz2, new LibraryField(1, "Name", "Descriptor"), true));
    programFieldOptimizationInfo.generalizeReferencedClass(null);

    LibraryField referencedField = new LibraryField(1, "Name", "Descriptor");
    referencedField.setProcessingInfo(programFieldOptimizationInfo);

    // Act
    Value actualFieldClassValue =
        loadingInvocationUnit.getFieldClassValue(
            clazz, new FieldrefConstant(1, 1, new LibraryClass(), referencedField), "Type");

    // Assert
    assertTrue(
        loadingInvocationUnit.getExceptionValue(null, null) instanceof IdentifiedReferenceValue);
    assertTrue(actualFieldClassValue instanceof IdentifiedReferenceValue);
    assertEquals("Type", ((IdentifiedReferenceValue) actualFieldClassValue).getType());
    assertNull(((IdentifiedReferenceValue) actualFieldClassValue).getReferencedClass());
    assertEquals(0, ((IdentifiedReferenceValue) actualFieldClassValue).isNull());
    assertFalse(actualFieldClassValue.isParticular());
    assertTrue(((IdentifiedReferenceValue) actualFieldClassValue).mayBeExtension());
    assertTrue(actualFieldClassValue.isSpecific());
  }

  /**
   * Test {@link LoadingInvocationUnit#getFieldClassValue(Clazz, FieldrefConstant, String)}.
   *
   * <p>Method under test: {@link LoadingInvocationUnit#getFieldClassValue(Clazz, FieldrefConstant,
   * String)}
   */
  @Test
  @DisplayName("Test getFieldClassValue(Clazz, FieldrefConstant, String)")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({
    "Value LoadingInvocationUnit.getFieldClassValue(Clazz, FieldrefConstant, String)"
  })
  void testGetFieldClassValue2() {
    // Arrange
    PrimitiveTypedReferenceValueFactory valueFactory = new PrimitiveTypedReferenceValueFactory();
    LoadingInvocationUnit loadingInvocationUnit = new LoadingInvocationUnit(valueFactory);
    LibraryClass clazz = new LibraryClass();

    // Act and Assert
    assertSame(
        valueFactory.REFERENCE_VALUE,
        loadingInvocationUnit.getFieldClassValue(clazz, new FieldrefConstant(), "Type"));
  }

  /**
   * Test {@link LoadingInvocationUnit#getFieldClassValue(Clazz, FieldrefConstant, String)}.
   *
   * <ul>
   *   <li>Then return {@link MultiTypedReferenceValue}.
   * </ul>
   *
   * <p>Method under test: {@link LoadingInvocationUnit#getFieldClassValue(Clazz, FieldrefConstant,
   * String)}
   */
  @Test
  @DisplayName(
      "Test getFieldClassValue(Clazz, FieldrefConstant, String); then return MultiTypedReferenceValue")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({
    "Value LoadingInvocationUnit.getFieldClassValue(Clazz, FieldrefConstant, String)"
  })
  void testGetFieldClassValue_thenReturnMultiTypedReferenceValue() {
    // Arrange
    ClassPool programClassPool = new ClassPool();
    LoadingInvocationUnit loadingInvocationUnit =
        new LoadingInvocationUnit(
            new MultiTypedReferenceValueFactory(true, programClassPool, new ClassPool()));
    LibraryClass clazz = new LibraryClass();

    // Act
    Value actualFieldClassValue =
        loadingInvocationUnit.getFieldClassValue(clazz, new FieldrefConstant(), "Type");

    // Assert
    assertTrue(actualFieldClassValue instanceof MultiTypedReferenceValue);
    assertEquals("Type", ((MultiTypedReferenceValue) actualFieldClassValue).getType());
    TypedReferenceValue generalizedType =
        ((MultiTypedReferenceValue) actualFieldClassValue).getGeneralizedType();
    assertEquals("Type", generalizedType.getType());
    assertNull(((MultiTypedReferenceValue) actualFieldClassValue).getReferencedClass());
    assertNull(generalizedType.getReferencedClass());
    assertEquals(0, generalizedType.isNotNull());
    assertEquals(0, generalizedType.isNull());
    assertEquals(1, ((MultiTypedReferenceValue) actualFieldClassValue).getPotentialTypes().size());
    assertFalse(generalizedType.isCategory2());
    assertFalse(generalizedType.isParticular());
    assertFalse(actualFieldClassValue.isParticular());
    assertFalse(generalizedType.isSpecific());
    assertFalse(((MultiTypedReferenceValue) actualFieldClassValue).mayBeUnknown);
    assertTrue(generalizedType.mayBeExtension());
  }

  /**
   * Test {@link LoadingInvocationUnit#getFieldClassValue(Clazz, FieldrefConstant, String)}.
   *
   * <ul>
   *   <li>Then return {@link UnknownReferenceValue} (default constructor).
   * </ul>
   *
   * <p>Method under test: {@link LoadingInvocationUnit#getFieldClassValue(Clazz, FieldrefConstant,
   * String)}
   */
  @Test
  @DisplayName(
      "Test getFieldClassValue(Clazz, FieldrefConstant, String); then return UnknownReferenceValue (default constructor)")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({
    "Value LoadingInvocationUnit.getFieldClassValue(Clazz, FieldrefConstant, String)"
  })
  void testGetFieldClassValue_thenReturnUnknownReferenceValue() {
    // Arrange
    LoadingInvocationUnit loadingInvocationUnit =
        new LoadingInvocationUnit(new ParticularReferenceValueFactory());
    LibraryClass clazz = new LibraryClass();
    LibraryClass clazz2 = new LibraryClass();

    ProgramFieldOptimizationInfo programFieldOptimizationInfo =
        new ProgramFieldOptimizationInfo(
            new ProgramFieldOptimizationInfo(
                clazz2, new LibraryField(1, "Name", "Descriptor"), true));
    UnknownReferenceValue referencedClass = new UnknownReferenceValue();
    programFieldOptimizationInfo.generalizeReferencedClass(referencedClass);

    LibraryField referencedField = new LibraryField(1, "Name", "Descriptor");
    referencedField.setProcessingInfo(programFieldOptimizationInfo);

    // Act
    Value actualFieldClassValue =
        loadingInvocationUnit.getFieldClassValue(
            clazz, new FieldrefConstant(1, 1, new LibraryClass(), referencedField), "Type");

    // Assert
    assertTrue(
        loadingInvocationUnit.getExceptionValue(null, null) instanceof IdentifiedReferenceValue);
    assertSame(referencedClass, actualFieldClassValue);
  }

  /**
   * Test {@link LoadingInvocationUnit#getFieldClassValue(Clazz, FieldrefConstant, String)}.
   *
   * <ul>
   *   <li>When {@link FieldrefConstant#FieldrefConstant()}.
   *   <li>Then return {@link IdentifiedReferenceValue}.
   * </ul>
   *
   * <p>Method under test: {@link LoadingInvocationUnit#getFieldClassValue(Clazz, FieldrefConstant,
   * String)}
   */
  @Test
  @DisplayName(
      "Test getFieldClassValue(Clazz, FieldrefConstant, String); when FieldrefConstant(); then return IdentifiedReferenceValue")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({
    "Value LoadingInvocationUnit.getFieldClassValue(Clazz, FieldrefConstant, String)"
  })
  void testGetFieldClassValue_whenFieldrefConstant_thenReturnIdentifiedReferenceValue() {
    // Arrange
    LoadingInvocationUnit loadingInvocationUnit =
        new LoadingInvocationUnit(new ParticularReferenceValueFactory());
    LibraryClass clazz = new LibraryClass();

    // Act
    Value actualFieldClassValue =
        loadingInvocationUnit.getFieldClassValue(clazz, new FieldrefConstant(), "Type");

    // Assert
    assertTrue(
        loadingInvocationUnit.getExceptionValue(null, null) instanceof IdentifiedReferenceValue);
    assertTrue(actualFieldClassValue instanceof IdentifiedReferenceValue);
    assertEquals("Type", ((IdentifiedReferenceValue) actualFieldClassValue).getType());
    assertNull(((IdentifiedReferenceValue) actualFieldClassValue).getReferencedClass());
    assertEquals(0, ((IdentifiedReferenceValue) actualFieldClassValue).isNull());
    assertFalse(actualFieldClassValue.isParticular());
    assertTrue(((IdentifiedReferenceValue) actualFieldClassValue).mayBeExtension());
    assertTrue(actualFieldClassValue.isSpecific());
  }

  /**
   * Test {@link LoadingInvocationUnit#getFieldValue(Clazz, FieldrefConstant, String)}.
   *
   * <p>Method under test: {@link LoadingInvocationUnit#getFieldValue(Clazz, FieldrefConstant,
   * String)}
   */
  @Test
  @DisplayName("Test getFieldValue(Clazz, FieldrefConstant, String)")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"Value LoadingInvocationUnit.getFieldValue(Clazz, FieldrefConstant, String)"})
  void testGetFieldValue() {
    // Arrange
    LoadingInvocationUnit loadingInvocationUnit =
        new LoadingInvocationUnit(new ParticularReferenceValueFactory());
    LibraryClass clazz = new LibraryClass();

    // Act
    Value actualFieldValue =
        loadingInvocationUnit.getFieldValue(clazz, new FieldrefConstant(), "Type");

    // Assert
    assertTrue(
        loadingInvocationUnit.getExceptionValue(null, null) instanceof IdentifiedReferenceValue);
    assertTrue(actualFieldValue instanceof IdentifiedReferenceValue);
    assertEquals("Type", ((IdentifiedReferenceValue) actualFieldValue).getType());
    assertNull(((IdentifiedReferenceValue) actualFieldValue).getReferencedClass());
    assertEquals(0, ((IdentifiedReferenceValue) actualFieldValue).isNull());
    assertFalse(actualFieldValue.isParticular());
    assertTrue(((IdentifiedReferenceValue) actualFieldValue).mayBeExtension());
    assertTrue(actualFieldValue.isSpecific());
  }

  /**
   * Test {@link LoadingInvocationUnit#getFieldValue(Clazz, FieldrefConstant, String)}.
   *
   * <ul>
   *   <li>Then return {@link MultiTypedReferenceValue}.
   * </ul>
   *
   * <p>Method under test: {@link LoadingInvocationUnit#getFieldValue(Clazz, FieldrefConstant,
   * String)}
   */
  @Test
  @DisplayName(
      "Test getFieldValue(Clazz, FieldrefConstant, String); then return MultiTypedReferenceValue")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"Value LoadingInvocationUnit.getFieldValue(Clazz, FieldrefConstant, String)"})
  void testGetFieldValue_thenReturnMultiTypedReferenceValue() {
    // Arrange
    ClassPool programClassPool = new ClassPool();
    LoadingInvocationUnit loadingInvocationUnit =
        new LoadingInvocationUnit(
            new MultiTypedReferenceValueFactory(true, programClassPool, new ClassPool()));
    LibraryClass clazz = new LibraryClass();

    // Act
    Value actualFieldValue =
        loadingInvocationUnit.getFieldValue(clazz, new FieldrefConstant(), "Type");

    // Assert
    assertTrue(actualFieldValue instanceof MultiTypedReferenceValue);
    assertEquals("Type", ((MultiTypedReferenceValue) actualFieldValue).getType());
    TypedReferenceValue generalizedType =
        ((MultiTypedReferenceValue) actualFieldValue).getGeneralizedType();
    assertEquals("Type", generalizedType.getType());
    assertNull(((MultiTypedReferenceValue) actualFieldValue).getReferencedClass());
    assertNull(generalizedType.getReferencedClass());
    assertEquals(0, generalizedType.isNotNull());
    assertEquals(0, generalizedType.isNull());
    assertEquals(1, ((MultiTypedReferenceValue) actualFieldValue).getPotentialTypes().size());
    assertFalse(generalizedType.isCategory2());
    assertFalse(generalizedType.isParticular());
    assertFalse(actualFieldValue.isParticular());
    assertFalse(generalizedType.isSpecific());
    assertFalse(((MultiTypedReferenceValue) actualFieldValue).mayBeUnknown);
    assertTrue(generalizedType.mayBeExtension());
  }

  /**
   * Test {@link LoadingInvocationUnit#getFieldValue(Clazz, FieldrefConstant, String)}.
   *
   * <ul>
   *   <li>Then return {@link PrimitiveTypedReferenceValueFactory} (default constructor) {@link
   *       BasicValueFactory#REFERENCE_VALUE}.
   * </ul>
   *
   * <p>Method under test: {@link LoadingInvocationUnit#getFieldValue(Clazz, FieldrefConstant,
   * String)}
   */
  @Test
  @DisplayName(
      "Test getFieldValue(Clazz, FieldrefConstant, String); then return PrimitiveTypedReferenceValueFactory (default constructor) REFERENCE_VALUE")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"Value LoadingInvocationUnit.getFieldValue(Clazz, FieldrefConstant, String)"})
  void testGetFieldValue_thenReturnPrimitiveTypedReferenceValueFactoryReference_value() {
    // Arrange
    PrimitiveTypedReferenceValueFactory valueFactory = new PrimitiveTypedReferenceValueFactory();
    LoadingInvocationUnit loadingInvocationUnit = new LoadingInvocationUnit(valueFactory);
    LibraryClass clazz = new LibraryClass();

    // Act and Assert
    assertSame(
        valueFactory.REFERENCE_VALUE,
        loadingInvocationUnit.getFieldValue(clazz, new FieldrefConstant(), "Type"));
  }

  /**
   * Test {@link LoadingInvocationUnit#getMethodParameterValue(Clazz, Method, int, String, Clazz)}.
   *
   * <p>Method under test: {@link LoadingInvocationUnit#getMethodParameterValue(Clazz, Method, int,
   * String, Clazz)}
   */
  @Test
  @DisplayName("Test getMethodParameterValue(Clazz, Method, int, String, Clazz)")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({
    "Value LoadingInvocationUnit.getMethodParameterValue(Clazz, Method, int, String, Clazz)"
  })
  void testGetMethodParameterValue() {
    // Arrange
    PrimitiveTypedReferenceValueFactory valueFactory = new PrimitiveTypedReferenceValueFactory();
    LoadingInvocationUnit loadingInvocationUnit =
        new LoadingInvocationUnit(valueFactory, true, false, true);
    LibraryClass clazz = new LibraryClass();
    LibraryMethod method = new LibraryMethod(1, "Name", "Descriptor");

    // Act and Assert
    assertSame(
        valueFactory.REFERENCE_VALUE,
        loadingInvocationUnit.getMethodParameterValue(
            clazz, method, 1, "Type", new LibraryClass()));
  }

  /**
   * Test {@link LoadingInvocationUnit#getMethodParameterValue(Clazz, Method, int, String, Clazz)}.
   *
   * <p>Method under test: {@link LoadingInvocationUnit#getMethodParameterValue(Clazz, Method, int,
   * String, Clazz)}
   */
  @Test
  @DisplayName("Test getMethodParameterValue(Clazz, Method, int, String, Clazz)")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({
    "Value LoadingInvocationUnit.getMethodParameterValue(Clazz, Method, int, String, Clazz)"
  })
  void testGetMethodParameterValue2() {
    // Arrange
    LoadingInvocationUnit loadingInvocationUnit =
        new LoadingInvocationUnit(new ParticularReferenceValueFactory(), true, false, true);
    LibraryClass clazz = new LibraryClass();
    LibraryMethod method = new LibraryMethod(-1, "Name", "Descriptor");

    LibraryClass referencedClass = new LibraryClass();

    // Act
    Value actualMethodParameterValue =
        loadingInvocationUnit.getMethodParameterValue(clazz, method, 0, "Type", referencedClass);

    // Assert
    Clazz referencedClass2 =
        ((IdentifiedReferenceValue) actualMethodParameterValue).getReferencedClass();
    assertTrue(referencedClass2 instanceof LibraryClass);
    assertTrue(actualMethodParameterValue instanceof IdentifiedReferenceValue);
    assertEquals(0, ((IdentifiedReferenceValue) actualMethodParameterValue).isNull());
    assertSame(referencedClass, referencedClass2);
  }

  /**
   * Test {@link LoadingInvocationUnit#getMethodParameterValue(Clazz, Method, int, String, Clazz)}.
   *
   * <ul>
   *   <li>Then return {@link MultiTypedReferenceValue}.
   * </ul>
   *
   * <p>Method under test: {@link LoadingInvocationUnit#getMethodParameterValue(Clazz, Method, int,
   * String, Clazz)}
   */
  @Test
  @DisplayName(
      "Test getMethodParameterValue(Clazz, Method, int, String, Clazz); then return MultiTypedReferenceValue")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({
    "Value LoadingInvocationUnit.getMethodParameterValue(Clazz, Method, int, String, Clazz)"
  })
  void testGetMethodParameterValue_thenReturnMultiTypedReferenceValue() {
    // Arrange
    ClassPool programClassPool = new ClassPool();
    LoadingInvocationUnit loadingInvocationUnit =
        new LoadingInvocationUnit(
            new MultiTypedReferenceValueFactory(true, programClassPool, new ClassPool()),
            true,
            false,
            true);
    LibraryClass clazz = new LibraryClass();
    LibraryMethod method = new LibraryMethod(1, "Name", "Descriptor");

    LibraryClass referencedClass = new LibraryClass();

    // Act
    Value actualMethodParameterValue =
        loadingInvocationUnit.getMethodParameterValue(clazz, method, 1, "Type", referencedClass);

    // Assert
    Clazz referencedClass2 =
        ((MultiTypedReferenceValue) actualMethodParameterValue).getReferencedClass();
    assertTrue(referencedClass2 instanceof LibraryClass);
    assertTrue(actualMethodParameterValue instanceof MultiTypedReferenceValue);
    assertEquals("Type", ((MultiTypedReferenceValue) actualMethodParameterValue).getType());
    assertEquals(
        1, ((MultiTypedReferenceValue) actualMethodParameterValue).getPotentialTypes().size());
    assertFalse(actualMethodParameterValue.isParticular());
    assertFalse(((MultiTypedReferenceValue) actualMethodParameterValue).mayBeUnknown);
    assertSame(referencedClass, referencedClass2);
  }

  /**
   * Test {@link LoadingInvocationUnit#getMethodParameterValue(Clazz, Method, int, String, Clazz)}.
   *
   * <ul>
   *   <li>Then return Null is zero.
   * </ul>
   *
   * <p>Method under test: {@link LoadingInvocationUnit#getMethodParameterValue(Clazz, Method, int,
   * String, Clazz)}
   */
  @Test
  @DisplayName(
      "Test getMethodParameterValue(Clazz, Method, int, String, Clazz); then return Null is zero")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({
    "Value LoadingInvocationUnit.getMethodParameterValue(Clazz, Method, int, String, Clazz)"
  })
  void testGetMethodParameterValue_thenReturnNullIsZero() {
    // Arrange
    LoadingInvocationUnit loadingInvocationUnit =
        new LoadingInvocationUnit(new ParticularReferenceValueFactory(), true, false, true);
    LibraryClass clazz = new LibraryClass();
    LibraryMethod method = new LibraryMethod(1, "Name", "Descriptor");

    LibraryClass referencedClass = new LibraryClass();

    // Act
    Value actualMethodParameterValue =
        loadingInvocationUnit.getMethodParameterValue(clazz, method, 1, "Type", referencedClass);

    // Assert
    Clazz referencedClass2 =
        ((IdentifiedReferenceValue) actualMethodParameterValue).getReferencedClass();
    assertTrue(referencedClass2 instanceof LibraryClass);
    assertTrue(actualMethodParameterValue instanceof IdentifiedReferenceValue);
    assertEquals(0, ((IdentifiedReferenceValue) actualMethodParameterValue).isNull());
    assertSame(referencedClass, referencedClass2);
  }

  /**
   * Test {@link LoadingInvocationUnit#getMethodParameterValue(Clazz, Method, int, String, Clazz)}.
   *
   * <ul>
   *   <li>When {@code null}.
   *   <li>Then return ReferencedClass is {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link LoadingInvocationUnit#getMethodParameterValue(Clazz, Method, int,
   * String, Clazz)}
   */
  @Test
  @DisplayName(
      "Test getMethodParameterValue(Clazz, Method, int, String, Clazz); when 'null'; then return ReferencedClass is 'null'")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({
    "Value LoadingInvocationUnit.getMethodParameterValue(Clazz, Method, int, String, Clazz)"
  })
  void testGetMethodParameterValue_whenNull_thenReturnReferencedClassIsNull() {
    // Arrange
    LoadingInvocationUnit loadingInvocationUnit =
        new LoadingInvocationUnit(new ParticularReferenceValueFactory(), true, false, true);
    LibraryClass clazz = new LibraryClass();

    // Act
    Value actualMethodParameterValue =
        loadingInvocationUnit.getMethodParameterValue(
            clazz, new LibraryMethod(1, "Name", "Descriptor"), 1, "Type", null);

    // Assert
    assertTrue(
        loadingInvocationUnit.getExceptionValue(null, null) instanceof IdentifiedReferenceValue);
    assertTrue(actualMethodParameterValue instanceof IdentifiedReferenceValue);
    assertEquals("Type", ((IdentifiedReferenceValue) actualMethodParameterValue).getType());
    assertNull(((IdentifiedReferenceValue) actualMethodParameterValue).getReferencedClass());
    assertFalse(actualMethodParameterValue.isParticular());
    assertTrue(((IdentifiedReferenceValue) actualMethodParameterValue).mayBeExtension());
    assertTrue(actualMethodParameterValue.isSpecific());
  }

  /**
   * Test {@link LoadingInvocationUnit#getMethodParameterValue(Clazz, Method, int, String, Clazz)}.
   *
   * <ul>
   *   <li>When zero.
   *   <li>Then return Null is minus one.
   * </ul>
   *
   * <p>Method under test: {@link LoadingInvocationUnit#getMethodParameterValue(Clazz, Method, int,
   * String, Clazz)}
   */
  @Test
  @DisplayName(
      "Test getMethodParameterValue(Clazz, Method, int, String, Clazz); when zero; then return Null is minus one")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({
    "Value LoadingInvocationUnit.getMethodParameterValue(Clazz, Method, int, String, Clazz)"
  })
  void testGetMethodParameterValue_whenZero_thenReturnNullIsMinusOne() {
    // Arrange
    LoadingInvocationUnit loadingInvocationUnit =
        new LoadingInvocationUnit(new ParticularReferenceValueFactory(), true, false, true);
    LibraryClass clazz = new LibraryClass();
    LibraryMethod method = new LibraryMethod(1, "Name", "Descriptor");

    // Act
    Value actualMethodParameterValue =
        loadingInvocationUnit.getMethodParameterValue(clazz, method, 0, "Type", new LibraryClass());

    // Assert
    assertTrue(
        ((IdentifiedReferenceValue) actualMethodParameterValue).getReferencedClass()
            instanceof LibraryClass);
    assertTrue(
        loadingInvocationUnit.getExceptionValue(null, null) instanceof IdentifiedReferenceValue);
    assertTrue(actualMethodParameterValue instanceof IdentifiedReferenceValue);
    assertEquals(-1, ((IdentifiedReferenceValue) actualMethodParameterValue).isNull());
    assertEquals(1, ((IdentifiedReferenceValue) actualMethodParameterValue).isNotNull());
  }

  /**
   * Test {@link LoadingInvocationUnit#getMethodReturnValue(Clazz, AnyMethodrefConstant, String)}
   * with {@code clazz}, {@code refConstant}, {@code type}.
   *
   * <p>Method under test: {@link LoadingInvocationUnit#getMethodReturnValue(Clazz,
   * AnyMethodrefConstant, String)}
   */
  @Test
  @DisplayName(
      "Test getMethodReturnValue(Clazz, AnyMethodrefConstant, String) with 'clazz', 'refConstant', 'type'")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({
    "Value LoadingInvocationUnit.getMethodReturnValue(Clazz, AnyMethodrefConstant, String)"
  })
  void testGetMethodReturnValueWithClazzRefConstantType() {
    // Arrange
    LoadingInvocationUnit loadingInvocationUnit =
        new LoadingInvocationUnit(new ParticularReferenceValueFactory());
    LibraryClass clazz = new LibraryClass();

    // Act
    Value actualMethodReturnValue =
        loadingInvocationUnit.getMethodReturnValue(clazz, new InterfaceMethodrefConstant(), "Type");

    // Assert
    assertTrue(
        loadingInvocationUnit.getExceptionValue(null, null) instanceof IdentifiedReferenceValue);
    assertTrue(actualMethodReturnValue instanceof IdentifiedReferenceValue);
    assertEquals("Type", ((IdentifiedReferenceValue) actualMethodReturnValue).getType());
    assertNull(((IdentifiedReferenceValue) actualMethodReturnValue).getReferencedClass());
    assertEquals(0, ((IdentifiedReferenceValue) actualMethodReturnValue).isNull());
    assertFalse(actualMethodReturnValue.isParticular());
    assertTrue(((IdentifiedReferenceValue) actualMethodReturnValue).mayBeExtension());
    assertTrue(actualMethodReturnValue.isSpecific());
  }

  /**
   * Test {@link LoadingInvocationUnit#getMethodReturnValue(Clazz, AnyMethodrefConstant, String)}
   * with {@code clazz}, {@code refConstant}, {@code type}.
   *
   * <p>Method under test: {@link LoadingInvocationUnit#getMethodReturnValue(Clazz,
   * AnyMethodrefConstant, String)}
   */
  @Test
  @DisplayName(
      "Test getMethodReturnValue(Clazz, AnyMethodrefConstant, String) with 'clazz', 'refConstant', 'type'")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({
    "Value LoadingInvocationUnit.getMethodReturnValue(Clazz, AnyMethodrefConstant, String)"
  })
  void testGetMethodReturnValueWithClazzRefConstantType2() {
    // Arrange
    ClassPool programClassPool = new ClassPool();
    LoadingInvocationUnit loadingInvocationUnit =
        new LoadingInvocationUnit(
            new MultiTypedReferenceValueFactory(true, programClassPool, new ClassPool()));
    LibraryClass clazz = new LibraryClass();

    // Act
    Value actualMethodReturnValue =
        loadingInvocationUnit.getMethodReturnValue(clazz, new InterfaceMethodrefConstant(), "Type");

    // Assert
    assertTrue(actualMethodReturnValue instanceof MultiTypedReferenceValue);
    assertEquals("Type", ((MultiTypedReferenceValue) actualMethodReturnValue).getType());
    TypedReferenceValue generalizedType =
        ((MultiTypedReferenceValue) actualMethodReturnValue).getGeneralizedType();
    assertEquals("Type", generalizedType.getType());
    assertNull(((MultiTypedReferenceValue) actualMethodReturnValue).getReferencedClass());
    assertNull(generalizedType.getReferencedClass());
    assertEquals(0, generalizedType.isNotNull());
    assertEquals(0, generalizedType.isNull());
    assertEquals(
        1, ((MultiTypedReferenceValue) actualMethodReturnValue).getPotentialTypes().size());
    assertFalse(generalizedType.isCategory2());
    assertFalse(generalizedType.isParticular());
    assertFalse(actualMethodReturnValue.isParticular());
    assertFalse(generalizedType.isSpecific());
    assertFalse(((MultiTypedReferenceValue) actualMethodReturnValue).mayBeUnknown);
    assertTrue(generalizedType.mayBeExtension());
  }

  /**
   * Test {@link LoadingInvocationUnit#getMethodReturnValue(Clazz, AnyMethodrefConstant, String)}
   * with {@code clazz}, {@code refConstant}, {@code type}.
   *
   * <p>Method under test: {@link LoadingInvocationUnit#getMethodReturnValue(Clazz,
   * AnyMethodrefConstant, String)}
   */
  @Test
  @DisplayName(
      "Test getMethodReturnValue(Clazz, AnyMethodrefConstant, String) with 'clazz', 'refConstant', 'type'")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({
    "Value LoadingInvocationUnit.getMethodReturnValue(Clazz, AnyMethodrefConstant, String)"
  })
  void testGetMethodReturnValueWithClazzRefConstantType3() {
    // Arrange
    PrimitiveTypedReferenceValueFactory valueFactory = new PrimitiveTypedReferenceValueFactory();
    LoadingInvocationUnit loadingInvocationUnit = new LoadingInvocationUnit(valueFactory);
    LibraryClass clazz = new LibraryClass();

    // Act and Assert
    assertSame(
        valueFactory.REFERENCE_VALUE,
        loadingInvocationUnit.getMethodReturnValue(
            clazz, new InterfaceMethodrefConstant(), "Type"));
  }
}
