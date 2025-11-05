package proguard.optimize.evaluation;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;
import proguard.classfile.ClassPool;
import proguard.classfile.Clazz;
import proguard.classfile.LibraryClass;
import proguard.classfile.LibraryField;
import proguard.classfile.constant.AnyMethodrefConstant;
import proguard.classfile.constant.FieldrefConstant;
import proguard.classfile.constant.InterfaceMethodrefConstant;
import proguard.evaluation.ParticularReferenceValueFactory;
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
   * Method under test:
   * {@link LoadingInvocationUnit#getFieldClassValue(Clazz, FieldrefConstant, String)}
   */
  @Test
  void testGetFieldClassValue() {
    // Arrange
    LoadingInvocationUnit loadingInvocationUnit = new LoadingInvocationUnit(new ParticularReferenceValueFactory());
    LibraryClass clazz = new LibraryClass();

    // Act
    Value actualFieldClassValue = loadingInvocationUnit.getFieldClassValue(clazz, new FieldrefConstant(), "Type");

    // Assert
    assertTrue(actualFieldClassValue instanceof IdentifiedReferenceValue);
    assertEquals("Type", ((IdentifiedReferenceValue) actualFieldClassValue).getType());
    AnalyzedObject value = ((IdentifiedReferenceValue) actualFieldClassValue).getValue();
    assertNull(value.getPreciseValue());
    assertNull(((IdentifiedReferenceValue) actualFieldClassValue).getReferencedClass());
    assertNull(value.getModeledOrNullValue());
    assertEquals(0, ((IdentifiedReferenceValue) actualFieldClassValue).isNotNull());
    assertEquals(0, ((IdentifiedReferenceValue) actualFieldClassValue).isNull());
    assertFalse(actualFieldClassValue.isCategory2());
    assertFalse(actualFieldClassValue.isParticular());
    assertTrue(((IdentifiedReferenceValue) actualFieldClassValue).mayBeExtension());
    assertTrue(actualFieldClassValue.isSpecific());
  }

  /**
   * Method under test:
   * {@link LoadingInvocationUnit#getFieldClassValue(Clazz, FieldrefConstant, String)}
   */
  @Test
  void testGetFieldClassValue2() {
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
    AnalyzedObject value = ((MultiTypedReferenceValue) actualFieldClassValue).getValue();
    assertNull(value.getPreciseValue());
    assertNull(((MultiTypedReferenceValue) actualFieldClassValue).getReferencedClass());
    assertNull(generalizedType.getReferencedClass());
    assertNull(value.getModeledOrNullValue());
    assertEquals(0, generalizedType.isNotNull());
    assertEquals(0, ((MultiTypedReferenceValue) actualFieldClassValue).isNotNull());
    assertEquals(0, generalizedType.isNull());
    assertEquals(1, ((MultiTypedReferenceValue) actualFieldClassValue).getPotentialTypes().size());
    assertFalse(generalizedType.isCategory2());
    assertFalse(generalizedType.isParticular());
    assertFalse(actualFieldClassValue.isCategory2());
    assertFalse(actualFieldClassValue.isParticular());
    assertFalse(generalizedType.isSpecific());
    assertFalse(actualFieldClassValue.isSpecific());
    assertFalse(((MultiTypedReferenceValue) actualFieldClassValue).mayBeUnknown);
    assertTrue(generalizedType.mayBeExtension());
    assertSame(value, generalizedType.getValue());
  }

  /**
   * Method under test:
   * {@link LoadingInvocationUnit#getFieldClassValue(Clazz, FieldrefConstant, String)}
   */
  @Test
  void testGetFieldClassValue3() {
    // Arrange
    PrimitiveTypedReferenceValueFactory valueFactory = new PrimitiveTypedReferenceValueFactory();
    LoadingInvocationUnit loadingInvocationUnit = new LoadingInvocationUnit(valueFactory);
    LibraryClass clazz = new LibraryClass();

    // Act and Assert
    assertSame(valueFactory.REFERENCE_VALUE,
        loadingInvocationUnit.getFieldClassValue(clazz, new FieldrefConstant(), "Type"));
  }

  /**
   * Method under test:
   * {@link LoadingInvocationUnit#getFieldClassValue(Clazz, FieldrefConstant, String)}
   */
  @Test
  void testGetFieldClassValue4() {
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
    assertTrue(actualFieldClassValue instanceof IdentifiedReferenceValue);
    assertEquals("Type", ((IdentifiedReferenceValue) actualFieldClassValue).getType());
    AnalyzedObject value = ((IdentifiedReferenceValue) actualFieldClassValue).getValue();
    assertNull(value.getPreciseValue());
    assertNull(((IdentifiedReferenceValue) actualFieldClassValue).getReferencedClass());
    assertNull(value.getModeledOrNullValue());
    assertEquals(0, ((IdentifiedReferenceValue) actualFieldClassValue).isNotNull());
    assertEquals(0, ((IdentifiedReferenceValue) actualFieldClassValue).isNull());
    assertFalse(actualFieldClassValue.isCategory2());
    assertFalse(actualFieldClassValue.isParticular());
    assertTrue(((IdentifiedReferenceValue) actualFieldClassValue).mayBeExtension());
    assertTrue(actualFieldClassValue.isSpecific());
  }

  /**
   * Method under test:
   * {@link LoadingInvocationUnit#getFieldClassValue(Clazz, FieldrefConstant, String)}
   */
  @Test
  void testGetFieldClassValue5() {
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

    // Act and Assert
    assertSame(referencedClass, loadingInvocationUnit.getFieldClassValue(clazz, refConstant, "Type"));
  }

  /**
   * Method under test:
   * {@link LoadingInvocationUnit#getFieldValue(Clazz, FieldrefConstant, String)}
   */
  @Test
  void testGetFieldValue() {
    // Arrange
    LoadingInvocationUnit loadingInvocationUnit = new LoadingInvocationUnit(new ParticularReferenceValueFactory());
    LibraryClass clazz = new LibraryClass();

    // Act
    Value actualFieldValue = loadingInvocationUnit.getFieldValue(clazz, new FieldrefConstant(), "Type");

    // Assert
    assertTrue(actualFieldValue instanceof IdentifiedReferenceValue);
    assertEquals("Type", ((IdentifiedReferenceValue) actualFieldValue).getType());
    AnalyzedObject value = ((IdentifiedReferenceValue) actualFieldValue).getValue();
    assertNull(value.getPreciseValue());
    assertNull(((IdentifiedReferenceValue) actualFieldValue).getReferencedClass());
    assertNull(value.getModeledOrNullValue());
    assertEquals(0, ((IdentifiedReferenceValue) actualFieldValue).isNotNull());
    assertEquals(0, ((IdentifiedReferenceValue) actualFieldValue).isNull());
    assertFalse(actualFieldValue.isCategory2());
    assertFalse(actualFieldValue.isParticular());
    assertTrue(((IdentifiedReferenceValue) actualFieldValue).mayBeExtension());
    assertTrue(actualFieldValue.isSpecific());
  }

  /**
   * Method under test:
   * {@link LoadingInvocationUnit#getFieldValue(Clazz, FieldrefConstant, String)}
   */
  @Test
  void testGetFieldValue2() {
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
    AnalyzedObject value = ((MultiTypedReferenceValue) actualFieldValue).getValue();
    assertNull(value.getPreciseValue());
    assertNull(((MultiTypedReferenceValue) actualFieldValue).getReferencedClass());
    assertNull(generalizedType.getReferencedClass());
    assertNull(value.getModeledOrNullValue());
    assertEquals(0, generalizedType.isNotNull());
    assertEquals(0, ((MultiTypedReferenceValue) actualFieldValue).isNotNull());
    assertEquals(0, generalizedType.isNull());
    assertEquals(1, ((MultiTypedReferenceValue) actualFieldValue).getPotentialTypes().size());
    assertFalse(generalizedType.isCategory2());
    assertFalse(generalizedType.isParticular());
    assertFalse(actualFieldValue.isCategory2());
    assertFalse(actualFieldValue.isParticular());
    assertFalse(generalizedType.isSpecific());
    assertFalse(actualFieldValue.isSpecific());
    assertFalse(((MultiTypedReferenceValue) actualFieldValue).mayBeUnknown);
    assertTrue(generalizedType.mayBeExtension());
    assertSame(value, generalizedType.getValue());
  }

  /**
   * Method under test:
   * {@link LoadingInvocationUnit#getFieldValue(Clazz, FieldrefConstant, String)}
   */
  @Test
  void testGetFieldValue3() {
    // Arrange
    PrimitiveTypedReferenceValueFactory valueFactory = new PrimitiveTypedReferenceValueFactory();
    LoadingInvocationUnit loadingInvocationUnit = new LoadingInvocationUnit(valueFactory);
    LibraryClass clazz = new LibraryClass();

    // Act and Assert
    assertSame(valueFactory.REFERENCE_VALUE,
        loadingInvocationUnit.getFieldValue(clazz, new FieldrefConstant(), "Type"));
  }

  /**
   * Method under test:
   * {@link LoadingInvocationUnit#getFieldValue(Clazz, FieldrefConstant, String)}
   */
  @Test
  void testGetFieldValue4() {
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
    assertTrue(actualFieldValue instanceof IdentifiedReferenceValue);
    assertEquals("Type", ((IdentifiedReferenceValue) actualFieldValue).getType());
    AnalyzedObject value = ((IdentifiedReferenceValue) actualFieldValue).getValue();
    assertNull(value.getPreciseValue());
    assertNull(((IdentifiedReferenceValue) actualFieldValue).getReferencedClass());
    assertNull(value.getModeledOrNullValue());
    assertEquals(0, ((IdentifiedReferenceValue) actualFieldValue).isNotNull());
    assertEquals(0, ((IdentifiedReferenceValue) actualFieldValue).isNull());
    assertFalse(actualFieldValue.isCategory2());
    assertFalse(actualFieldValue.isParticular());
    assertTrue(((IdentifiedReferenceValue) actualFieldValue).mayBeExtension());
    assertTrue(actualFieldValue.isSpecific());
  }

  /**
   * Method under test:
   * {@link LoadingInvocationUnit#getFieldValue(Clazz, FieldrefConstant, String)}
   */
  @Test
  void testGetFieldValue5() {
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

    // Act and Assert
    assertSame(value, loadingInvocationUnit.getFieldValue(clazz, refConstant, "Type"));
  }

  /**
   * Method under test:
   * {@link LoadingInvocationUnit#getMethodReturnValue(Clazz, AnyMethodrefConstant, String)}
   */
  @Test
  void testGetMethodReturnValue() {
    // Arrange
    LoadingInvocationUnit loadingInvocationUnit = new LoadingInvocationUnit(new ParticularReferenceValueFactory());
    LibraryClass clazz = new LibraryClass();

    // Act
    Value actualMethodReturnValue = loadingInvocationUnit.getMethodReturnValue(clazz, new InterfaceMethodrefConstant(),
        "Type");

    // Assert
    assertTrue(actualMethodReturnValue instanceof IdentifiedReferenceValue);
    assertEquals("Type", ((IdentifiedReferenceValue) actualMethodReturnValue).getType());
    AnalyzedObject value = ((IdentifiedReferenceValue) actualMethodReturnValue).getValue();
    assertNull(value.getPreciseValue());
    assertNull(((IdentifiedReferenceValue) actualMethodReturnValue).getReferencedClass());
    assertNull(value.getModeledOrNullValue());
    assertEquals(0, ((IdentifiedReferenceValue) actualMethodReturnValue).isNotNull());
    assertEquals(0, ((IdentifiedReferenceValue) actualMethodReturnValue).isNull());
    assertFalse(actualMethodReturnValue.isCategory2());
    assertFalse(actualMethodReturnValue.isParticular());
    assertTrue(((IdentifiedReferenceValue) actualMethodReturnValue).mayBeExtension());
    assertTrue(actualMethodReturnValue.isSpecific());
  }

  /**
   * Method under test:
   * {@link LoadingInvocationUnit#getMethodReturnValue(Clazz, AnyMethodrefConstant, String)}
   */
  @Test
  void testGetMethodReturnValue2() {
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
    AnalyzedObject value = ((MultiTypedReferenceValue) actualMethodReturnValue).getValue();
    assertNull(value.getPreciseValue());
    assertNull(((MultiTypedReferenceValue) actualMethodReturnValue).getReferencedClass());
    assertNull(generalizedType.getReferencedClass());
    assertNull(value.getModeledOrNullValue());
    assertEquals(0, generalizedType.isNotNull());
    assertEquals(0, ((MultiTypedReferenceValue) actualMethodReturnValue).isNotNull());
    assertEquals(0, generalizedType.isNull());
    assertEquals(1, ((MultiTypedReferenceValue) actualMethodReturnValue).getPotentialTypes().size());
    assertFalse(generalizedType.isCategory2());
    assertFalse(generalizedType.isParticular());
    assertFalse(actualMethodReturnValue.isCategory2());
    assertFalse(actualMethodReturnValue.isParticular());
    assertFalse(generalizedType.isSpecific());
    assertFalse(actualMethodReturnValue.isSpecific());
    assertFalse(((MultiTypedReferenceValue) actualMethodReturnValue).mayBeUnknown);
    assertTrue(generalizedType.mayBeExtension());
    assertSame(value, generalizedType.getValue());
  }

  /**
   * Method under test:
   * {@link LoadingInvocationUnit#getMethodReturnValue(Clazz, AnyMethodrefConstant, String)}
   */
  @Test
  void testGetMethodReturnValue3() {
    // Arrange
    PrimitiveTypedReferenceValueFactory valueFactory = new PrimitiveTypedReferenceValueFactory();
    LoadingInvocationUnit loadingInvocationUnit = new LoadingInvocationUnit(valueFactory);
    LibraryClass clazz = new LibraryClass();

    // Act and Assert
    assertSame(valueFactory.REFERENCE_VALUE,
        loadingInvocationUnit.getMethodReturnValue(clazz, new InterfaceMethodrefConstant(), "Type"));
  }

  /**
   * Method under test:
   * {@link LoadingInvocationUnit#LoadingInvocationUnit(ValueFactory)}
   */
  @Test
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
   * Method under test:
   * {@link LoadingInvocationUnit#LoadingInvocationUnit(ValueFactory, boolean, boolean, boolean)}
   */
  @Test
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
}
