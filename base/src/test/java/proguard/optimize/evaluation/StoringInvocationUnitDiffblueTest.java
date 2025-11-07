package proguard.optimize.evaluation;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import com.diffblue.cover.annotations.MethodsUnderTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
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
import proguard.evaluation.value.ReferenceValue;
import proguard.evaluation.value.TopValue;
import proguard.evaluation.value.UnknownReferenceValue;
import proguard.evaluation.value.Value;
import proguard.evaluation.value.ValueFactory;
import proguard.evaluation.value.object.AnalyzedObject;
import proguard.optimize.info.FieldOptimizationInfo;
import proguard.optimize.info.MethodOptimizationInfo;
import proguard.optimize.info.ProgramFieldOptimizationInfo;

class StoringInvocationUnitDiffblueTest {
  /**
   * Test {@link StoringInvocationUnit#StoringInvocationUnit(ValueFactory)}.
   * <p>
   * Method under test: {@link StoringInvocationUnit#StoringInvocationUnit(ValueFactory)}
   */
  @Test
  @DisplayName("Test new StoringInvocationUnit(ValueFactory)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void StoringInvocationUnit.<init>(ValueFactory)"})
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
   * Test {@link StoringInvocationUnit#StoringInvocationUnit(ValueFactory, boolean, boolean, boolean)}.
   * <p>
   * Method under test: {@link StoringInvocationUnit#StoringInvocationUnit(ValueFactory, boolean, boolean, boolean)}
   */
  @Test
  @DisplayName("Test new StoringInvocationUnit(ValueFactory, boolean, boolean, boolean)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void StoringInvocationUnit.<init>(ValueFactory, boolean, boolean, boolean)"})
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

  /**
   * Test {@link StoringInvocationUnit#setFieldClassValue(Clazz, FieldrefConstant, ReferenceValue)}.
   * <p>
   * Method under test: {@link StoringInvocationUnit#setFieldClassValue(Clazz, FieldrefConstant, ReferenceValue)}
   */
  @Test
  @DisplayName("Test setFieldClassValue(Clazz, FieldrefConstant, ReferenceValue)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void StoringInvocationUnit.setFieldClassValue(Clazz, FieldrefConstant, ReferenceValue)"})
  void testSetFieldClassValue() {
    // Arrange
    StoringInvocationUnit storingInvocationUnit = new StoringInvocationUnit(new ParticularReferenceValueFactory());
    LibraryClass clazz = new LibraryClass();
    FieldrefConstant refConstant = new FieldrefConstant();

    // Act
    storingInvocationUnit.setFieldClassValue(clazz, refConstant, new UnknownReferenceValue());

    // Assert
    assertTrue(storingInvocationUnit.getExceptionValue(null, null) instanceof IdentifiedReferenceValue);
  }

  /**
   * Test {@link StoringInvocationUnit#setFieldValue(Clazz, FieldrefConstant, Value)}.
   * <p>
   * Method under test: {@link StoringInvocationUnit#setFieldValue(Clazz, FieldrefConstant, Value)}
   */
  @Test
  @DisplayName("Test setFieldValue(Clazz, FieldrefConstant, Value)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void StoringInvocationUnit.setFieldValue(Clazz, FieldrefConstant, Value)"})
  void testSetFieldValue() {
    // Arrange
    StoringInvocationUnit storingInvocationUnit = new StoringInvocationUnit(new ParticularReferenceValueFactory());
    LibraryClass clazz = new LibraryClass();
    FieldrefConstant refConstant = new FieldrefConstant();

    // Act
    storingInvocationUnit.setFieldValue(clazz, refConstant, new TopValue());

    // Assert
    assertTrue(storingInvocationUnit.getExceptionValue(null, null) instanceof IdentifiedReferenceValue);
  }

  /**
   * Test {@link StoringInvocationUnit#setMethodParameterValue(Clazz, AnyMethodrefConstant, int, Value)}.
   * <p>
   * Method under test: {@link StoringInvocationUnit#setMethodParameterValue(Clazz, AnyMethodrefConstant, int, Value)}
   */
  @Test
  @DisplayName("Test setMethodParameterValue(Clazz, AnyMethodrefConstant, int, Value)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void StoringInvocationUnit.setMethodParameterValue(Clazz, AnyMethodrefConstant, int, Value)"})
  void testSetMethodParameterValue() {
    // Arrange
    StoringInvocationUnit storingInvocationUnit = new StoringInvocationUnit(new ParticularReferenceValueFactory());
    LibraryClass clazz = new LibraryClass();
    InterfaceMethodrefConstant refConstant = new InterfaceMethodrefConstant();

    // Act
    storingInvocationUnit.setMethodParameterValue(clazz, refConstant, 1, new TopValue());

    // Assert
    assertTrue(storingInvocationUnit.getExceptionValue(null, null) instanceof IdentifiedReferenceValue);
  }

  /**
   * Test {@link StoringInvocationUnit#setMethodReturnValue(Clazz, Method, Value)}.
   * <ul>
   *   <li>Given {@link MethodOptimizationInfo} (default constructor).</li>
   * </ul>
   * <p>
   * Method under test: {@link StoringInvocationUnit#setMethodReturnValue(Clazz, Method, Value)}
   */
  @Test
  @DisplayName("Test setMethodReturnValue(Clazz, Method, Value); given MethodOptimizationInfo (default constructor)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void StoringInvocationUnit.setMethodReturnValue(Clazz, Method, Value)"})
  void testSetMethodReturnValue_givenMethodOptimizationInfo() {
    // Arrange
    StoringInvocationUnit storingInvocationUnit = new StoringInvocationUnit(new ParticularReferenceValueFactory());
    LibraryClass clazz = new LibraryClass();
    Method method = mock(Method.class);
    when(method.getProcessingInfo()).thenReturn(new MethodOptimizationInfo());

    // Act
    storingInvocationUnit.setMethodReturnValue(clazz, method, new TopValue());

    // Assert
    verify(method, atLeast(1)).getProcessingInfo();
    assertTrue(storingInvocationUnit.getExceptionValue(null, null) instanceof IdentifiedReferenceValue);
  }

  /**
   * Test {@link StoringInvocationUnit#setMethodReturnValue(Clazz, Method, Value)}.
   * <ul>
   *   <li>Then calls {@link MethodOptimizationInfo#isKept()}.</li>
   * </ul>
   * <p>
   * Method under test: {@link StoringInvocationUnit#setMethodReturnValue(Clazz, Method, Value)}
   */
  @Test
  @DisplayName("Test setMethodReturnValue(Clazz, Method, Value); then calls isKept()")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void StoringInvocationUnit.setMethodReturnValue(Clazz, Method, Value)"})
  void testSetMethodReturnValue_thenCallsIsKept() {
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
    assertTrue(storingInvocationUnit.getExceptionValue(null, null) instanceof IdentifiedReferenceValue);
  }

  /**
   * Test {@link StoringInvocationUnit#getFieldClassValue(Field)} with {@code field}.
   * <p>
   * Method under test: {@link StoringInvocationUnit#getFieldClassValue(Field)}
   */
  @Test
  @DisplayName("Test getFieldClassValue(Field) with 'field'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"ReferenceValue StoringInvocationUnit.getFieldClassValue(Field)"})
  void testGetFieldClassValueWithField() {
    // Arrange
    LibraryField field = new LibraryField(1, "Name", "Descriptor");
    LibraryClass clazz = new LibraryClass();
    field.setProcessingInfo(new ProgramFieldOptimizationInfo(clazz, new LibraryField(1, "Name", "Descriptor"), true));

    // Act and Assert
    assertNull(StoringInvocationUnit.getFieldClassValue(field));
  }

  /**
   * Test {@link StoringInvocationUnit#getFieldClassValue(Field)} with {@code field}.
   * <ul>
   *   <li>Given {@link FieldOptimizationInfo} (default constructor).</li>
   * </ul>
   * <p>
   * Method under test: {@link StoringInvocationUnit#getFieldClassValue(Field)}
   */
  @Test
  @DisplayName("Test getFieldClassValue(Field) with 'field'; given FieldOptimizationInfo (default constructor)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"ReferenceValue StoringInvocationUnit.getFieldClassValue(Field)"})
  void testGetFieldClassValueWithField_givenFieldOptimizationInfo() {
    // Arrange
    LibraryField field = new LibraryField(1, "Name", "Descriptor");
    field.setProcessingInfo(new FieldOptimizationInfo());

    // Act and Assert
    assertNull(StoringInvocationUnit.getFieldClassValue(field));
  }

  /**
   * Test {@link StoringInvocationUnit#getFieldValue(Field)} with {@code field}.
   * <ul>
   *   <li>Given {@link FieldOptimizationInfo} (default constructor).</li>
   *   <li>Then return {@code null}.</li>
   * </ul>
   * <p>
   * Method under test: {@link StoringInvocationUnit#getFieldValue(Field)}
   */
  @Test
  @DisplayName("Test getFieldValue(Field) with 'field'; given FieldOptimizationInfo (default constructor); then return 'null'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"Value StoringInvocationUnit.getFieldValue(Field)"})
  void testGetFieldValueWithField_givenFieldOptimizationInfo_thenReturnNull() {
    // Arrange
    LibraryField field = new LibraryField(1, "Name", "Descriptor");
    field.setProcessingInfo(new FieldOptimizationInfo());

    // Act and Assert
    assertNull(StoringInvocationUnit.getFieldValue(field));
  }

  /**
   * Test {@link StoringInvocationUnit#getMethodParameterValue(Method, int)} with {@code method}, {@code parameterIndex}.
   * <ul>
   *   <li>Then return {@code null}.</li>
   * </ul>
   * <p>
   * Method under test: {@link StoringInvocationUnit#getMethodParameterValue(Method, int)}
   */
  @Test
  @DisplayName("Test getMethodParameterValue(Method, int) with 'method', 'parameterIndex'; then return 'null'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"Value StoringInvocationUnit.getMethodParameterValue(Method, int)"})
  void testGetMethodParameterValueWithMethodParameterIndex_thenReturnNull() {
    // Arrange
    LibraryMethod method = new LibraryMethod(1, "Name", "Descriptor");
    method.setProcessingInfo(new MethodOptimizationInfo());

    // Act and Assert
    assertNull(StoringInvocationUnit.getMethodParameterValue(method, 1));
  }

  /**
   * Test {@link StoringInvocationUnit#getMethodReturnValue(Method)} with {@code method}.
   * <ul>
   *   <li>Given {@link MethodOptimizationInfo} (default constructor).</li>
   *   <li>Then return {@code null}.</li>
   * </ul>
   * <p>
   * Method under test: {@link StoringInvocationUnit#getMethodReturnValue(Method)}
   */
  @Test
  @DisplayName("Test getMethodReturnValue(Method) with 'method'; given MethodOptimizationInfo (default constructor); then return 'null'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"Value StoringInvocationUnit.getMethodReturnValue(Method)"})
  void testGetMethodReturnValueWithMethod_givenMethodOptimizationInfo_thenReturnNull() {
    // Arrange
    LibraryMethod method = new LibraryMethod(1, "Name", "Descriptor");
    method.setProcessingInfo(new MethodOptimizationInfo());

    // Act and Assert
    assertNull(StoringInvocationUnit.getMethodReturnValue(method));
  }
}
