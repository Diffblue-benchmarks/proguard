package proguard.optimize.evaluation;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.junit.jupiter.api.Test;
import proguard.classfile.Clazz;
import proguard.classfile.Field;
import proguard.classfile.LibraryClass;
import proguard.classfile.LibraryField;
import proguard.classfile.LibraryMethod;
import proguard.classfile.Method;
import proguard.classfile.constant.AnyMethodrefConstant;
import proguard.classfile.constant.FieldrefConstant;
import proguard.classfile.constant.InterfaceMethodrefConstant;
import proguard.evaluation.ParticularReferenceValueFactory;
import proguard.evaluation.value.IdentifiedReferenceValue;
import proguard.evaluation.value.ParticularReferenceValue;
import proguard.evaluation.value.ReferenceValue;
import proguard.evaluation.value.TopValue;
import proguard.evaluation.value.Value;
import proguard.evaluation.value.ValueFactory;
import proguard.evaluation.value.object.AnalyzedObject;
import proguard.optimize.info.FieldOptimizationInfo;
import proguard.optimize.info.MethodOptimizationInfo;
import proguard.optimize.info.ProgramFieldOptimizationInfo;

class StoringInvocationUnitDiffblueTest {
  /**
   * Method under test:
   * {@link StoringInvocationUnit#setFieldClassValue(Clazz, FieldrefConstant, ReferenceValue)}
   */
  @Test
  void testSetFieldClassValue() {
    // Arrange
    StoringInvocationUnit storingInvocationUnit = new StoringInvocationUnit(new ParticularReferenceValueFactory());
    LibraryClass clazz = new LibraryClass();
    FieldrefConstant refConstant = new FieldrefConstant();
    AnalyzedObject value = mock(AnalyzedObject.class);
    when(value.getType()).thenReturn("Type");
    LibraryClass referencedClass = new LibraryClass();

    // Act
    storingInvocationUnit.setFieldClassValue(clazz, refConstant,
        new ParticularReferenceValue(referencedClass, new ParticularReferenceValueFactory(), "Reference ID", value));

    // Assert that nothing has changed
    verify(value, atLeast(1)).getType();
  }

  /**
   * Method under test:
   * {@link StoringInvocationUnit#setFieldValue(Clazz, FieldrefConstant, Value)}
   */
  @Test
  void testSetFieldValue() {
    // Arrange
    StoringInvocationUnit storingInvocationUnit = new StoringInvocationUnit(new ParticularReferenceValueFactory());
    LibraryClass clazz = new LibraryClass();
    Field referencedField = mock(Field.class);
    when(referencedField.getProcessingInfo()).thenReturn(new FieldOptimizationInfo());
    FieldrefConstant refConstant = new FieldrefConstant(1, 1, new LibraryClass(), referencedField);

    // Act
    storingInvocationUnit.setFieldValue(clazz, refConstant, new TopValue());

    // Assert that nothing has changed
    verify(referencedField).getProcessingInfo();
  }

  /**
   * Method under test:
   * {@link StoringInvocationUnit#setFieldValue(Clazz, FieldrefConstant, Value)}
   */
  @Test
  void testSetFieldValue2() {
    // Arrange
    StoringInvocationUnit storingInvocationUnit = new StoringInvocationUnit(new ParticularReferenceValueFactory());
    LibraryClass clazz = new LibraryClass();
    FieldOptimizationInfo fieldOptimizationInfo = mock(FieldOptimizationInfo.class);
    when(fieldOptimizationInfo.isKept()).thenReturn(true);
    Field referencedField = mock(Field.class);
    when(referencedField.getProcessingInfo()).thenReturn(fieldOptimizationInfo);
    FieldrefConstant refConstant = new FieldrefConstant(1, 1, new LibraryClass(), referencedField);

    // Act
    storingInvocationUnit.setFieldValue(clazz, refConstant, new TopValue());

    // Assert
    verify(fieldOptimizationInfo).isKept();
    verify(referencedField).getProcessingInfo();
  }

  /**
   * Method under test:
   * {@link StoringInvocationUnit#setMethodParameterValue(Clazz, AnyMethodrefConstant, int, Value)}
   */
  @Test
  void testSetMethodParameterValue() {
    // Arrange
    StoringInvocationUnit storingInvocationUnit = new StoringInvocationUnit(new ParticularReferenceValueFactory());
    LibraryClass clazz = new LibraryClass();
    Method referencedMethod = mock(Method.class);
    when(referencedMethod.getProcessingInfo()).thenReturn(new MethodOptimizationInfo());
    InterfaceMethodrefConstant refConstant = new InterfaceMethodrefConstant(1, 1, new LibraryClass(), referencedMethod);

    // Act
    storingInvocationUnit.setMethodParameterValue(clazz, refConstant, 1, new TopValue());

    // Assert that nothing has changed
    verify(referencedMethod, atLeast(1)).getProcessingInfo();
  }

  /**
   * Method under test:
   * {@link StoringInvocationUnit#setMethodParameterValue(Clazz, AnyMethodrefConstant, int, Value)}
   */
  @Test
  void testSetMethodParameterValue2() {
    // Arrange
    StoringInvocationUnit storingInvocationUnit = new StoringInvocationUnit(new ParticularReferenceValueFactory());
    LibraryClass clazz = new LibraryClass();
    MethodOptimizationInfo methodOptimizationInfo = mock(MethodOptimizationInfo.class);
    when(methodOptimizationInfo.isKept()).thenReturn(true);
    Method referencedMethod = mock(Method.class);
    when(referencedMethod.getProcessingInfo()).thenReturn(methodOptimizationInfo);
    InterfaceMethodrefConstant refConstant = new InterfaceMethodrefConstant(1, 1, new LibraryClass(), referencedMethod);

    // Act
    storingInvocationUnit.setMethodParameterValue(clazz, refConstant, 1, new TopValue());

    // Assert
    verify(methodOptimizationInfo).isKept();
    verify(referencedMethod, atLeast(1)).getProcessingInfo();
  }

  /**
   * Method under test:
   * {@link StoringInvocationUnit#setMethodReturnValue(Clazz, Method, Value)}
   */
  @Test
  void testSetMethodReturnValue() {
    // Arrange
    StoringInvocationUnit storingInvocationUnit = new StoringInvocationUnit(new ParticularReferenceValueFactory());
    LibraryClass clazz = new LibraryClass();
    Method method = mock(Method.class);
    when(method.getProcessingInfo()).thenReturn(new MethodOptimizationInfo());

    // Act
    storingInvocationUnit.setMethodReturnValue(clazz, method, new TopValue());

    // Assert that nothing has changed
    verify(method, atLeast(1)).getProcessingInfo();
  }

  /**
   * Method under test:
   * {@link StoringInvocationUnit#setMethodReturnValue(Clazz, Method, Value)}
   */
  @Test
  void testSetMethodReturnValue2() {
    // Arrange
    StoringInvocationUnit storingInvocationUnit = new StoringInvocationUnit(new ParticularReferenceValueFactory());
    LibraryClass clazz = new LibraryClass();
    MethodOptimizationInfo methodOptimizationInfo = mock(MethodOptimizationInfo.class);
    when(methodOptimizationInfo.isKept()).thenReturn(true);
    Method method = mock(Method.class);
    when(method.getProcessingInfo()).thenReturn(methodOptimizationInfo);

    // Act
    storingInvocationUnit.setMethodReturnValue(clazz, method, new TopValue());

    // Assert
    verify(methodOptimizationInfo).isKept();
    verify(method, atLeast(1)).getProcessingInfo();
  }

  /**
   * Method under test: {@link StoringInvocationUnit#getFieldClassValue(Field)}
   */
  @Test
  void testGetFieldClassValue() {
    // Arrange
    LibraryField field = new LibraryField(1, "Name", "Descriptor");
    field.setProcessingInfo(new FieldOptimizationInfo());

    // Act and Assert
    assertNull(StoringInvocationUnit.getFieldClassValue(field));
  }

  /**
   * Method under test: {@link StoringInvocationUnit#getFieldClassValue(Field)}
   */
  @Test
  void testGetFieldClassValue2() {
    // Arrange
    LibraryField field = new LibraryField(1, "Name", "Descriptor");
    LibraryClass clazz = new LibraryClass();
    field.setProcessingInfo(new ProgramFieldOptimizationInfo(clazz, new LibraryField(1, "Name", "Descriptor"), true));

    // Act and Assert
    assertNull(StoringInvocationUnit.getFieldClassValue(field));
  }

  /**
   * Method under test: {@link StoringInvocationUnit#getFieldValue(Field)}
   */
  @Test
  void testGetFieldValue() {
    // Arrange
    LibraryField field = new LibraryField(1, "Name", "Descriptor");
    field.setProcessingInfo(new FieldOptimizationInfo());

    // Act and Assert
    assertNull(StoringInvocationUnit.getFieldValue(field));
  }

  /**
   * Method under test:
   * {@link StoringInvocationUnit#getMethodParameterValue(Method, int)}
   */
  @Test
  void testGetMethodParameterValue() {
    // Arrange
    LibraryMethod method = new LibraryMethod(1, "Name", "Descriptor");
    method.setProcessingInfo(new MethodOptimizationInfo());

    // Act and Assert
    assertNull(StoringInvocationUnit.getMethodParameterValue(method, 1));
  }

  /**
   * Method under test: {@link StoringInvocationUnit#getMethodReturnValue(Method)}
   */
  @Test
  void testGetMethodReturnValue() {
    // Arrange
    LibraryMethod method = new LibraryMethod(1, "Name", "Descriptor");
    method.setProcessingInfo(new MethodOptimizationInfo());

    // Act and Assert
    assertNull(StoringInvocationUnit.getMethodReturnValue(method));
  }

  /**
   * Method under test:
   * {@link StoringInvocationUnit#StoringInvocationUnit(ValueFactory)}
   */
  @Test
  void testNewStoringInvocationUnit() {
    // Arrange, Act and Assert
    Value exceptionValue = (new StoringInvocationUnit(new ParticularReferenceValueFactory())).getExceptionValue(null,
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
   * {@link StoringInvocationUnit#StoringInvocationUnit(ValueFactory, boolean, boolean, boolean)}
   */
  @Test
  void testNewStoringInvocationUnit2() {
    // Arrange, Act and Assert
    Value exceptionValue = (new StoringInvocationUnit(new ParticularReferenceValueFactory(), true, true, true))
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
