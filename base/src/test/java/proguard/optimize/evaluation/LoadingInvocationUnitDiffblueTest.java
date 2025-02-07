package proguard.optimize.evaluation;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import proguard.classfile.ClassPool;
import proguard.classfile.Clazz;
import proguard.classfile.LibraryClass;
import proguard.classfile.LibraryField;
import proguard.classfile.constant.AnyMethodrefConstant;
import proguard.classfile.constant.FieldrefConstant;
import proguard.classfile.constant.InterfaceMethodrefConstant;
import proguard.evaluation.ParticularReferenceValueFactory;
import proguard.evaluation.value.BasicValueFactory;
import proguard.evaluation.value.IdentifiedReferenceValue;
import proguard.evaluation.value.MultiTypedReferenceValue;
import proguard.evaluation.value.MultiTypedReferenceValueFactory;
import proguard.evaluation.value.PrimitiveTypedReferenceValueFactory;
import proguard.evaluation.value.TopValue;
import proguard.evaluation.value.TypedReferenceValue;
import proguard.evaluation.value.UnknownReferenceValue;
import proguard.evaluation.value.Value;
import proguard.evaluation.value.ValueFactory;
import proguard.evaluation.value.object.AnalyzedObject;
import proguard.optimize.info.FieldOptimizationInfo;
import proguard.optimize.info.ProgramFieldOptimizationInfo;

class LoadingInvocationUnitDiffblueTest {
  /**
   * Test {@link LoadingInvocationUnit#LoadingInvocationUnit(ValueFactory)}.
   * <p>
   * Method under test: {@link LoadingInvocationUnit#LoadingInvocationUnit(ValueFactory)}
   */
  @Test
  @DisplayName("Test new LoadingInvocationUnit(ValueFactory)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "void proguard.optimize.evaluation.LoadingInvocationUnit.<init>(proguard.evaluation.value.ValueFactory)"})
  void testNewLoadingInvocationUnit() {
    // Arrange, Act and Assert
    Value exceptionValue = (new LoadingInvocationUnit(new ParticularReferenceValueFactory())).getExceptionValue(null,
        null);
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
   * Test {@link LoadingInvocationUnit#LoadingInvocationUnit(ValueFactory, boolean, boolean, boolean)}.
   * <p>
   * Method under test: {@link LoadingInvocationUnit#LoadingInvocationUnit(ValueFactory, boolean, boolean, boolean)}
   */
  @Test
  @DisplayName("Test new LoadingInvocationUnit(ValueFactory, boolean, boolean, boolean)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "void proguard.optimize.evaluation.LoadingInvocationUnit.<init>(proguard.evaluation.value.ValueFactory, boolean, boolean, boolean)"})
  void testNewLoadingInvocationUnit2() {
    // Arrange, Act and Assert
    Value exceptionValue = (new LoadingInvocationUnit(new ParticularReferenceValueFactory(), true, true, true))
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
   * <p>
   * Method under test: {@link LoadingInvocationUnit#getFieldClassValue(Clazz, FieldrefConstant, String)}
   */
  @Test
  @DisplayName("Test getFieldClassValue(Clazz, FieldrefConstant, String)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "proguard.evaluation.value.Value proguard.optimize.evaluation.LoadingInvocationUnit.getFieldClassValue(proguard.classfile.Clazz, proguard.classfile.constant.FieldrefConstant, java.lang.String)"})
  void testGetFieldClassValue() {
    // Arrange
    PrimitiveTypedReferenceValueFactory valueFactory = new PrimitiveTypedReferenceValueFactory();
    LoadingInvocationUnit loadingInvocationUnit = new LoadingInvocationUnit(valueFactory);
    LibraryClass clazz = new LibraryClass();

    // Act and Assert
    assertSame(valueFactory.REFERENCE_VALUE,
        loadingInvocationUnit.getFieldClassValue(clazz, new FieldrefConstant(), "Type"));
  }

  /**
   * Test {@link LoadingInvocationUnit#getFieldClassValue(Clazz, FieldrefConstant, String)}.
   * <p>
   * Method under test: {@link LoadingInvocationUnit#getFieldClassValue(Clazz, FieldrefConstant, String)}
   */
  @Test
  @DisplayName("Test getFieldClassValue(Clazz, FieldrefConstant, String)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "proguard.evaluation.value.Value proguard.optimize.evaluation.LoadingInvocationUnit.getFieldClassValue(proguard.classfile.Clazz, proguard.classfile.constant.FieldrefConstant, java.lang.String)"})
  void testGetFieldClassValue2() {
    // Arrange
    LoadingInvocationUnit loadingInvocationUnit = new LoadingInvocationUnit(new ParticularReferenceValueFactory());
    LibraryClass clazz = new LibraryClass();
    LibraryClass clazz2 = new LibraryClass();

    ProgramFieldOptimizationInfo programFieldOptimizationInfo = new ProgramFieldOptimizationInfo(clazz2,
        new LibraryField(1, "Name", "Descriptor"), true);
    programFieldOptimizationInfo.generalizeReferencedClass(null);

    LibraryField libraryField = new LibraryField(1, "Name", "Descriptor");
    libraryField.setProcessingInfo(programFieldOptimizationInfo);
    FieldrefConstant refConstant = new FieldrefConstant();
    refConstant.referencedField = libraryField;

    // Act
    Value actualFieldClassValue = loadingInvocationUnit.getFieldClassValue(clazz, refConstant, "Type");

    // Assert
    assertTrue(loadingInvocationUnit.getExceptionValue(null, null) instanceof IdentifiedReferenceValue);
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
   * <ul>
   *   <li>Then return {@link MultiTypedReferenceValue}.</li>
   * </ul>
   * <p>
   * Method under test: {@link LoadingInvocationUnit#getFieldClassValue(Clazz, FieldrefConstant, String)}
   */
  @Test
  @DisplayName("Test getFieldClassValue(Clazz, FieldrefConstant, String); then return MultiTypedReferenceValue")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "proguard.evaluation.value.Value proguard.optimize.evaluation.LoadingInvocationUnit.getFieldClassValue(proguard.classfile.Clazz, proguard.classfile.constant.FieldrefConstant, java.lang.String)"})
  void testGetFieldClassValue_thenReturnMultiTypedReferenceValue() {
    // Arrange
    ClassPool programClassPool = new ClassPool();
    LoadingInvocationUnit loadingInvocationUnit = new LoadingInvocationUnit(
        new MultiTypedReferenceValueFactory(true, programClassPool, new ClassPool()));
    LibraryClass clazz = new LibraryClass();

    // Act
    Value actualFieldClassValue = loadingInvocationUnit.getFieldClassValue(clazz, new FieldrefConstant(), "Type");

    // Assert
    assertTrue(actualFieldClassValue instanceof MultiTypedReferenceValue);
    assertEquals("Type", ((MultiTypedReferenceValue) actualFieldClassValue).getType());
    TypedReferenceValue generalizedType = ((MultiTypedReferenceValue) actualFieldClassValue).getGeneralizedType();
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
   * <ul>
   *   <li>Then return {@link UnknownReferenceValue} (default constructor).</li>
   * </ul>
   * <p>
   * Method under test: {@link LoadingInvocationUnit#getFieldClassValue(Clazz, FieldrefConstant, String)}
   */
  @Test
  @DisplayName("Test getFieldClassValue(Clazz, FieldrefConstant, String); then return UnknownReferenceValue (default constructor)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "proguard.evaluation.value.Value proguard.optimize.evaluation.LoadingInvocationUnit.getFieldClassValue(proguard.classfile.Clazz, proguard.classfile.constant.FieldrefConstant, java.lang.String)"})
  void testGetFieldClassValue_thenReturnUnknownReferenceValue() {
    // Arrange
    LoadingInvocationUnit loadingInvocationUnit = new LoadingInvocationUnit(new ParticularReferenceValueFactory());
    LibraryClass clazz = new LibraryClass();
    LibraryClass clazz2 = new LibraryClass();

    ProgramFieldOptimizationInfo programFieldOptimizationInfo = new ProgramFieldOptimizationInfo(clazz2,
        new LibraryField(1, "Name", "Descriptor"), true);
    UnknownReferenceValue referencedClass = new UnknownReferenceValue();
    programFieldOptimizationInfo.generalizeReferencedClass(referencedClass);

    LibraryField libraryField = new LibraryField(1, "Name", "Descriptor");
    libraryField.setProcessingInfo(programFieldOptimizationInfo);
    FieldrefConstant refConstant = new FieldrefConstant();
    refConstant.referencedField = libraryField;

    // Act
    Value actualFieldClassValue = loadingInvocationUnit.getFieldClassValue(clazz, refConstant, "Type");

    // Assert
    assertTrue(loadingInvocationUnit.getExceptionValue(null, null) instanceof IdentifiedReferenceValue);
    assertSame(referencedClass, actualFieldClassValue);
  }

  /**
   * Test {@link LoadingInvocationUnit#getFieldClassValue(Clazz, FieldrefConstant, String)}.
   * <ul>
   *   <li>When {@link FieldrefConstant#FieldrefConstant()}.</li>
   *   <li>Then return {@link IdentifiedReferenceValue}.</li>
   * </ul>
   * <p>
   * Method under test: {@link LoadingInvocationUnit#getFieldClassValue(Clazz, FieldrefConstant, String)}
   */
  @Test
  @DisplayName("Test getFieldClassValue(Clazz, FieldrefConstant, String); when FieldrefConstant(); then return IdentifiedReferenceValue")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "proguard.evaluation.value.Value proguard.optimize.evaluation.LoadingInvocationUnit.getFieldClassValue(proguard.classfile.Clazz, proguard.classfile.constant.FieldrefConstant, java.lang.String)"})
  void testGetFieldClassValue_whenFieldrefConstant_thenReturnIdentifiedReferenceValue() {
    // Arrange
    LoadingInvocationUnit loadingInvocationUnit = new LoadingInvocationUnit(new ParticularReferenceValueFactory());
    LibraryClass clazz = new LibraryClass();

    // Act
    Value actualFieldClassValue = loadingInvocationUnit.getFieldClassValue(clazz, new FieldrefConstant(), "Type");

    // Assert
    assertTrue(loadingInvocationUnit.getExceptionValue(null, null) instanceof IdentifiedReferenceValue);
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
   * <ul>
   *   <li>Given {@link FieldOptimizationInfo} (default constructor) Value is {@code null}.</li>
   * </ul>
   * <p>
   * Method under test: {@link LoadingInvocationUnit#getFieldValue(Clazz, FieldrefConstant, String)}
   */
  @Test
  @DisplayName("Test getFieldValue(Clazz, FieldrefConstant, String); given FieldOptimizationInfo (default constructor) Value is 'null'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "proguard.evaluation.value.Value proguard.optimize.evaluation.LoadingInvocationUnit.getFieldValue(proguard.classfile.Clazz, proguard.classfile.constant.FieldrefConstant, java.lang.String)"})
  void testGetFieldValue_givenFieldOptimizationInfoValueIsNull() {
    // Arrange
    LoadingInvocationUnit loadingInvocationUnit = new LoadingInvocationUnit(new ParticularReferenceValueFactory());
    LibraryClass clazz = new LibraryClass();

    FieldOptimizationInfo fieldOptimizationInfo = new FieldOptimizationInfo();
    fieldOptimizationInfo.setValue(null);

    LibraryField libraryField = new LibraryField(1, "Name", "Descriptor");
    libraryField.setProcessingInfo(fieldOptimizationInfo);
    FieldrefConstant refConstant = new FieldrefConstant();
    refConstant.referencedField = libraryField;

    // Act
    Value actualFieldValue = loadingInvocationUnit.getFieldValue(clazz, refConstant, "Type");

    // Assert
    assertTrue(loadingInvocationUnit.getExceptionValue(null, null) instanceof IdentifiedReferenceValue);
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
   * <ul>
   *   <li>Given {@link FieldOptimizationInfo} (default constructor) Value is {@link TopValue} (default constructor).</li>
   *   <li>Then return {@link TopValue} (default constructor).</li>
   * </ul>
   * <p>
   * Method under test: {@link LoadingInvocationUnit#getFieldValue(Clazz, FieldrefConstant, String)}
   */
  @Test
  @DisplayName("Test getFieldValue(Clazz, FieldrefConstant, String); given FieldOptimizationInfo (default constructor) Value is TopValue (default constructor); then return TopValue (default constructor)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "proguard.evaluation.value.Value proguard.optimize.evaluation.LoadingInvocationUnit.getFieldValue(proguard.classfile.Clazz, proguard.classfile.constant.FieldrefConstant, java.lang.String)"})
  void testGetFieldValue_givenFieldOptimizationInfoValueIsTopValue_thenReturnTopValue() {
    // Arrange
    LoadingInvocationUnit loadingInvocationUnit = new LoadingInvocationUnit(new ParticularReferenceValueFactory());
    LibraryClass clazz = new LibraryClass();

    FieldOptimizationInfo fieldOptimizationInfo = new FieldOptimizationInfo();
    TopValue value = new TopValue();
    fieldOptimizationInfo.setValue(value);

    LibraryField libraryField = new LibraryField(1, "Name", "Descriptor");
    libraryField.setProcessingInfo(fieldOptimizationInfo);
    FieldrefConstant refConstant = new FieldrefConstant();
    refConstant.referencedField = libraryField;

    // Act
    Value actualFieldValue = loadingInvocationUnit.getFieldValue(clazz, refConstant, "Type");

    // Assert
    assertTrue(loadingInvocationUnit.getExceptionValue(null, null) instanceof IdentifiedReferenceValue);
    assertSame(value, actualFieldValue);
  }

  /**
   * Test {@link LoadingInvocationUnit#getFieldValue(Clazz, FieldrefConstant, String)}.
   * <ul>
   *   <li>Then return {@link MultiTypedReferenceValue}.</li>
   * </ul>
   * <p>
   * Method under test: {@link LoadingInvocationUnit#getFieldValue(Clazz, FieldrefConstant, String)}
   */
  @Test
  @DisplayName("Test getFieldValue(Clazz, FieldrefConstant, String); then return MultiTypedReferenceValue")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "proguard.evaluation.value.Value proguard.optimize.evaluation.LoadingInvocationUnit.getFieldValue(proguard.classfile.Clazz, proguard.classfile.constant.FieldrefConstant, java.lang.String)"})
  void testGetFieldValue_thenReturnMultiTypedReferenceValue() {
    // Arrange
    ClassPool programClassPool = new ClassPool();
    LoadingInvocationUnit loadingInvocationUnit = new LoadingInvocationUnit(
        new MultiTypedReferenceValueFactory(true, programClassPool, new ClassPool()));
    LibraryClass clazz = new LibraryClass();

    // Act
    Value actualFieldValue = loadingInvocationUnit.getFieldValue(clazz, new FieldrefConstant(), "Type");

    // Assert
    assertTrue(actualFieldValue instanceof MultiTypedReferenceValue);
    assertEquals("Type", ((MultiTypedReferenceValue) actualFieldValue).getType());
    TypedReferenceValue generalizedType = ((MultiTypedReferenceValue) actualFieldValue).getGeneralizedType();
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
   * <ul>
   *   <li>Then return {@link PrimitiveTypedReferenceValueFactory} (default constructor) {@link BasicValueFactory#REFERENCE_VALUE}.</li>
   * </ul>
   * <p>
   * Method under test: {@link LoadingInvocationUnit#getFieldValue(Clazz, FieldrefConstant, String)}
   */
  @Test
  @DisplayName("Test getFieldValue(Clazz, FieldrefConstant, String); then return PrimitiveTypedReferenceValueFactory (default constructor) REFERENCE_VALUE")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "proguard.evaluation.value.Value proguard.optimize.evaluation.LoadingInvocationUnit.getFieldValue(proguard.classfile.Clazz, proguard.classfile.constant.FieldrefConstant, java.lang.String)"})
  void testGetFieldValue_thenReturnPrimitiveTypedReferenceValueFactoryReference_value() {
    // Arrange
    PrimitiveTypedReferenceValueFactory valueFactory = new PrimitiveTypedReferenceValueFactory();
    LoadingInvocationUnit loadingInvocationUnit = new LoadingInvocationUnit(valueFactory);
    LibraryClass clazz = new LibraryClass();

    // Act and Assert
    assertSame(valueFactory.REFERENCE_VALUE,
        loadingInvocationUnit.getFieldValue(clazz, new FieldrefConstant(), "Type"));
  }

  /**
   * Test {@link LoadingInvocationUnit#getFieldValue(Clazz, FieldrefConstant, String)}.
   * <ul>
   *   <li>When {@link FieldrefConstant#FieldrefConstant()}.</li>
   *   <li>Then return {@link IdentifiedReferenceValue}.</li>
   * </ul>
   * <p>
   * Method under test: {@link LoadingInvocationUnit#getFieldValue(Clazz, FieldrefConstant, String)}
   */
  @Test
  @DisplayName("Test getFieldValue(Clazz, FieldrefConstant, String); when FieldrefConstant(); then return IdentifiedReferenceValue")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "proguard.evaluation.value.Value proguard.optimize.evaluation.LoadingInvocationUnit.getFieldValue(proguard.classfile.Clazz, proguard.classfile.constant.FieldrefConstant, java.lang.String)"})
  void testGetFieldValue_whenFieldrefConstant_thenReturnIdentifiedReferenceValue() {
    // Arrange
    LoadingInvocationUnit loadingInvocationUnit = new LoadingInvocationUnit(new ParticularReferenceValueFactory());
    LibraryClass clazz = new LibraryClass();

    // Act
    Value actualFieldValue = loadingInvocationUnit.getFieldValue(clazz, new FieldrefConstant(), "Type");

    // Assert
    assertTrue(loadingInvocationUnit.getExceptionValue(null, null) instanceof IdentifiedReferenceValue);
    assertTrue(actualFieldValue instanceof IdentifiedReferenceValue);
    assertEquals("Type", ((IdentifiedReferenceValue) actualFieldValue).getType());
    assertNull(((IdentifiedReferenceValue) actualFieldValue).getReferencedClass());
    assertEquals(0, ((IdentifiedReferenceValue) actualFieldValue).isNull());
    assertFalse(actualFieldValue.isParticular());
    assertTrue(((IdentifiedReferenceValue) actualFieldValue).mayBeExtension());
    assertTrue(actualFieldValue.isSpecific());
  }

  /**
   * Test {@link LoadingInvocationUnit#getMethodReturnValue(Clazz, AnyMethodrefConstant, String)} with {@code clazz}, {@code refConstant}, {@code type}.
   * <p>
   * Method under test: {@link LoadingInvocationUnit#getMethodReturnValue(Clazz, AnyMethodrefConstant, String)}
   */
  @Test
  @DisplayName("Test getMethodReturnValue(Clazz, AnyMethodrefConstant, String) with 'clazz', 'refConstant', 'type'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "proguard.evaluation.value.Value proguard.optimize.evaluation.LoadingInvocationUnit.getMethodReturnValue(proguard.classfile.Clazz, proguard.classfile.constant.AnyMethodrefConstant, java.lang.String)"})
  void testGetMethodReturnValueWithClazzRefConstantType() {
    // Arrange
    LoadingInvocationUnit loadingInvocationUnit = new LoadingInvocationUnit(new ParticularReferenceValueFactory());
    LibraryClass clazz = new LibraryClass();

    // Act
    Value actualMethodReturnValue = loadingInvocationUnit.getMethodReturnValue(clazz, new InterfaceMethodrefConstant(),
        "Type");

    // Assert
    assertTrue(loadingInvocationUnit.getExceptionValue(null, null) instanceof IdentifiedReferenceValue);
    assertTrue(actualMethodReturnValue instanceof IdentifiedReferenceValue);
    assertEquals("Type", ((IdentifiedReferenceValue) actualMethodReturnValue).getType());
    assertNull(((IdentifiedReferenceValue) actualMethodReturnValue).getReferencedClass());
    assertEquals(0, ((IdentifiedReferenceValue) actualMethodReturnValue).isNull());
    assertFalse(actualMethodReturnValue.isParticular());
    assertTrue(((IdentifiedReferenceValue) actualMethodReturnValue).mayBeExtension());
    assertTrue(actualMethodReturnValue.isSpecific());
  }

  /**
   * Test {@link LoadingInvocationUnit#getMethodReturnValue(Clazz, AnyMethodrefConstant, String)} with {@code clazz}, {@code refConstant}, {@code type}.
   * <p>
   * Method under test: {@link LoadingInvocationUnit#getMethodReturnValue(Clazz, AnyMethodrefConstant, String)}
   */
  @Test
  @DisplayName("Test getMethodReturnValue(Clazz, AnyMethodrefConstant, String) with 'clazz', 'refConstant', 'type'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "proguard.evaluation.value.Value proguard.optimize.evaluation.LoadingInvocationUnit.getMethodReturnValue(proguard.classfile.Clazz, proguard.classfile.constant.AnyMethodrefConstant, java.lang.String)"})
  void testGetMethodReturnValueWithClazzRefConstantType2() {
    // Arrange
    ClassPool programClassPool = new ClassPool();
    LoadingInvocationUnit loadingInvocationUnit = new LoadingInvocationUnit(
        new MultiTypedReferenceValueFactory(true, programClassPool, new ClassPool()));
    LibraryClass clazz = new LibraryClass();

    // Act
    Value actualMethodReturnValue = loadingInvocationUnit.getMethodReturnValue(clazz, new InterfaceMethodrefConstant(),
        "Type");

    // Assert
    assertTrue(actualMethodReturnValue instanceof MultiTypedReferenceValue);
    assertEquals("Type", ((MultiTypedReferenceValue) actualMethodReturnValue).getType());
    TypedReferenceValue generalizedType = ((MultiTypedReferenceValue) actualMethodReturnValue).getGeneralizedType();
    assertEquals("Type", generalizedType.getType());
    assertNull(((MultiTypedReferenceValue) actualMethodReturnValue).getReferencedClass());
    assertNull(generalizedType.getReferencedClass());
    assertEquals(0, generalizedType.isNotNull());
    assertEquals(0, generalizedType.isNull());
    assertEquals(1, ((MultiTypedReferenceValue) actualMethodReturnValue).getPotentialTypes().size());
    assertFalse(generalizedType.isCategory2());
    assertFalse(generalizedType.isParticular());
    assertFalse(actualMethodReturnValue.isParticular());
    assertFalse(generalizedType.isSpecific());
    assertFalse(((MultiTypedReferenceValue) actualMethodReturnValue).mayBeUnknown);
    assertTrue(generalizedType.mayBeExtension());
  }

  /**
   * Test {@link LoadingInvocationUnit#getMethodReturnValue(Clazz, AnyMethodrefConstant, String)} with {@code clazz}, {@code refConstant}, {@code type}.
   * <p>
   * Method under test: {@link LoadingInvocationUnit#getMethodReturnValue(Clazz, AnyMethodrefConstant, String)}
   */
  @Test
  @DisplayName("Test getMethodReturnValue(Clazz, AnyMethodrefConstant, String) with 'clazz', 'refConstant', 'type'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "proguard.evaluation.value.Value proguard.optimize.evaluation.LoadingInvocationUnit.getMethodReturnValue(proguard.classfile.Clazz, proguard.classfile.constant.AnyMethodrefConstant, java.lang.String)"})
  void testGetMethodReturnValueWithClazzRefConstantType3() {
    // Arrange
    PrimitiveTypedReferenceValueFactory valueFactory = new PrimitiveTypedReferenceValueFactory();
    LoadingInvocationUnit loadingInvocationUnit = new LoadingInvocationUnit(valueFactory);
    LibraryClass clazz = new LibraryClass();

    // Act and Assert
    assertSame(valueFactory.REFERENCE_VALUE,
        loadingInvocationUnit.getMethodReturnValue(clazz, new InterfaceMethodrefConstant(), "Type"));
  }
}
