package proguard.optimize.info;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import proguard.classfile.Clazz;
import proguard.classfile.Field;
import proguard.classfile.LibraryClass;
import proguard.classfile.LibraryField;
import proguard.classfile.attribute.Attribute;
import proguard.classfile.attribute.BootstrapMethodsAttribute;
import proguard.classfile.attribute.ConstantValueAttribute;
import proguard.evaluation.ParticularReferenceValueFactory;
import proguard.evaluation.value.ArrayReferenceValue;
import proguard.evaluation.value.CompositeDoubleValue;
import proguard.evaluation.value.IdentifiedArrayReferenceValue;
import proguard.evaluation.value.IdentifiedReferenceValue;
import proguard.evaluation.value.MultiTypedReferenceValue;
import proguard.evaluation.value.ParticularDoubleValue;
import proguard.evaluation.value.ParticularFloatValue;
import proguard.evaluation.value.ParticularReferenceValue;
import proguard.evaluation.value.ReferenceValue;
import proguard.evaluation.value.TopValue;
import proguard.evaluation.value.TracedReferenceValue;
import proguard.evaluation.value.TypedReferenceValue;
import proguard.evaluation.value.UnknownDoubleValue;
import proguard.evaluation.value.UnknownIntegerValue;
import proguard.evaluation.value.UnknownReferenceValue;
import proguard.evaluation.value.Value;
import proguard.evaluation.value.object.AnalyzedObject;
import proguard.testutils.cpa.NamedField;

class ProgramFieldOptimizationInfoDiffblueTest {
  /**
   * Test {@link ProgramFieldOptimizationInfo#ProgramFieldOptimizationInfo(Clazz, Field, boolean)}.
   *
   * <p>Method under test: {@link ProgramFieldOptimizationInfo#ProgramFieldOptimizationInfo(Clazz,
   * Field, boolean)}
   */
  @Test
  @DisplayName("Test new ProgramFieldOptimizationInfo(Clazz, Field, boolean)")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void ProgramFieldOptimizationInfo.<init>(Clazz, Field, boolean)"})
  void testNewProgramFieldOptimizationInfo() {
    // Arrange
    LibraryClass clazz = new LibraryClass();
    LibraryField field = new LibraryField(1, "Name", "Descriptor");

    // Act
    ProgramFieldOptimizationInfo actualProgramFieldOptimizationInfo =
        new ProgramFieldOptimizationInfo(clazz, field, true);

    // Assert
    Value value = actualProgramFieldOptimizationInfo.getValue();
    assertTrue(value instanceof ParticularDoubleValue);
    assertEquals(0.0d, ((ParticularDoubleValue) value).value());
    assertTrue(value.isCategory2());
    assertTrue(value.isParticular());
    assertTrue(value.isSpecific());
  }

  /**
   * Test {@link ProgramFieldOptimizationInfo#ProgramFieldOptimizationInfo(Clazz, Field, boolean)}.
   *
   * <p>Method under test: {@link ProgramFieldOptimizationInfo#ProgramFieldOptimizationInfo(Clazz,
   * Field, boolean)}
   */
  @Test
  @DisplayName("Test new ProgramFieldOptimizationInfo(Clazz, Field, boolean)")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void ProgramFieldOptimizationInfo.<init>(Clazz, Field, boolean)"})
  void testNewProgramFieldOptimizationInfo2() {
    // Arrange
    LibraryClass clazz = new LibraryClass();
    LibraryField field = new LibraryField(8, "Name", "Descriptor");

    // Act
    ProgramFieldOptimizationInfo actualProgramFieldOptimizationInfo =
        new ProgramFieldOptimizationInfo(clazz, field, true);

    // Assert
    Value value = actualProgramFieldOptimizationInfo.getValue();
    assertTrue(value instanceof ParticularDoubleValue);
    assertEquals(0.0d, ((ParticularDoubleValue) value).value());
    assertTrue(value.isCategory2());
    assertTrue(value.isParticular());
    assertTrue(value.isSpecific());
  }

  /**
   * Test {@link ProgramFieldOptimizationInfo#ProgramFieldOptimizationInfo(Clazz, Field, boolean)}.
   *
   * <p>Method under test: {@link ProgramFieldOptimizationInfo#ProgramFieldOptimizationInfo(Clazz,
   * Field, boolean)}
   */
  @Test
  @DisplayName("Test new ProgramFieldOptimizationInfo(Clazz, Field, boolean)")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void ProgramFieldOptimizationInfo.<init>(Clazz, Field, boolean)"})
  void testNewProgramFieldOptimizationInfo3() {
    // Arrange
    LibraryClass clazz = new LibraryClass();
    LibraryField field = new LibraryField(1, "Name", "Descriptor");

    // Act
    ProgramFieldOptimizationInfo actualProgramFieldOptimizationInfo =
        new ProgramFieldOptimizationInfo(clazz, field, false);

    // Assert
    Value value = actualProgramFieldOptimizationInfo.getValue();
    assertTrue(value instanceof ParticularDoubleValue);
    assertEquals(0.0d, ((ParticularDoubleValue) value).value());
    assertTrue(value.isCategory2());
    assertTrue(value.isParticular());
    assertTrue(value.isSpecific());
  }

  /**
   * Test {@link ProgramFieldOptimizationInfo#ProgramFieldOptimizationInfo(Clazz, Field, boolean)}.
   *
   * <ul>
   *   <li>Then return Read.
   * </ul>
   *
   * <p>Method under test: {@link ProgramFieldOptimizationInfo#ProgramFieldOptimizationInfo(Clazz,
   * Field, boolean)}
   */
  @Test
  @DisplayName("Test new ProgramFieldOptimizationInfo(Clazz, Field, boolean); then return Read")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void ProgramFieldOptimizationInfo.<init>(Clazz, Field, boolean)"})
  void testNewProgramFieldOptimizationInfo_thenReturnRead() {
    // Arrange
    LibraryClass clazz = new LibraryClass();
    LibraryField field = new LibraryField(Double.SIZE, "Name", "Descriptor");

    // Act
    ProgramFieldOptimizationInfo actualProgramFieldOptimizationInfo =
        new ProgramFieldOptimizationInfo(clazz, field, true);

    // Assert
    Value value = actualProgramFieldOptimizationInfo.getValue();
    assertTrue(value instanceof ParticularDoubleValue);
    assertEquals(0.0d, ((ParticularDoubleValue) value).value());
    assertTrue(value.isCategory2());
    assertTrue(value.isParticular());
    assertTrue(value.isSpecific());
    assertTrue(actualProgramFieldOptimizationInfo.isRead());
    assertTrue(actualProgramFieldOptimizationInfo.isWritten());
  }

  /**
   * Test {@link ProgramFieldOptimizationInfo#ProgramFieldOptimizationInfo(Clazz, Field, boolean)}.
   *
   * <ul>
   *   <li>Then return ReferencedClass is {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link ProgramFieldOptimizationInfo#ProgramFieldOptimizationInfo(Clazz,
   * Field, boolean)}
   */
  @Test
  @DisplayName(
      "Test new ProgramFieldOptimizationInfo(Clazz, Field, boolean); then return ReferencedClass is 'null'")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void ProgramFieldOptimizationInfo.<init>(Clazz, Field, boolean)"})
  void testNewProgramFieldOptimizationInfo_thenReturnReferencedClassIsNull() {
    // Arrange
    LibraryClass clazz = new LibraryClass();
    LibraryField field = new LibraryField(Short.SIZE, "Name", "Descriptor");

    // Act
    ProgramFieldOptimizationInfo actualProgramFieldOptimizationInfo =
        new ProgramFieldOptimizationInfo(clazz, field, false);

    // Assert
    assertNull(actualProgramFieldOptimizationInfo.getReferencedClass());
    assertNull(actualProgramFieldOptimizationInfo.getValue());
    assertFalse(actualProgramFieldOptimizationInfo.isKept());
    assertFalse(actualProgramFieldOptimizationInfo.isRead());
    assertFalse(actualProgramFieldOptimizationInfo.isWritten());
    assertTrue(actualProgramFieldOptimizationInfo.canBeMadePrivate());
  }

  /**
   * Test {@link
   * ProgramFieldOptimizationInfo#ProgramFieldOptimizationInfo(ProgramFieldOptimizationInfo)}.
   *
   * <ul>
   *   <li>Then Value return {@link ParticularDoubleValue}.
   * </ul>
   *
   * <p>Method under test: {@link
   * ProgramFieldOptimizationInfo#ProgramFieldOptimizationInfo(ProgramFieldOptimizationInfo)}
   */
  @Test
  @DisplayName(
      "Test new ProgramFieldOptimizationInfo(ProgramFieldOptimizationInfo); then Value return ParticularDoubleValue")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void ProgramFieldOptimizationInfo.<init>(ProgramFieldOptimizationInfo)"})
  void testNewProgramFieldOptimizationInfo_thenValueReturnParticularDoubleValue() {
    // Arrange
    LibraryClass clazz = new LibraryClass();
    LibraryField field = new LibraryField(1, "Name", "Descriptor");

    ProgramFieldOptimizationInfo programFieldOptimizationInfo =
        new ProgramFieldOptimizationInfo(clazz, field, true);

    // Act
    ProgramFieldOptimizationInfo actualProgramFieldOptimizationInfo =
        new ProgramFieldOptimizationInfo(programFieldOptimizationInfo);

    // Assert
    Value value = actualProgramFieldOptimizationInfo.getValue();
    assertTrue(value instanceof ParticularDoubleValue);
    assertNull(actualProgramFieldOptimizationInfo.getReferencedClass());
    assertEquals(0.0d, ((ParticularDoubleValue) value).value());
    assertFalse(actualProgramFieldOptimizationInfo.isKept());
    assertFalse(actualProgramFieldOptimizationInfo.isRead());
    assertFalse(actualProgramFieldOptimizationInfo.isWritten());
    assertTrue(value.isCategory2());
    assertTrue(value.isParticular());
    assertTrue(value.isSpecific());
    assertTrue(actualProgramFieldOptimizationInfo.canBeMadePrivate());
  }

  /**
   * Test {@link ProgramFieldOptimizationInfo#ProgramFieldOptimizationInfo(Clazz, Field, boolean)}.
   *
   * <ul>
   *   <li>Then Value return {@link ParticularFloatValue}.
   * </ul>
   *
   * <p>Method under test: {@link ProgramFieldOptimizationInfo#ProgramFieldOptimizationInfo(Clazz,
   * Field, boolean)}
   */
  @Test
  @DisplayName(
      "Test new ProgramFieldOptimizationInfo(Clazz, Field, boolean); then Value return ParticularFloatValue")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void ProgramFieldOptimizationInfo.<init>(Clazz, Field, boolean)"})
  void testNewProgramFieldOptimizationInfo_thenValueReturnParticularFloatValue() {
    // Arrange
    LibraryClass clazz = new LibraryClass();

    // Act
    ProgramFieldOptimizationInfo actualProgramFieldOptimizationInfo =
        new ProgramFieldOptimizationInfo(
            clazz, new NamedField("Field Name", "Field Descriptor"), true);

    // Assert
    Value value = actualProgramFieldOptimizationInfo.getValue();
    assertTrue(value instanceof ParticularFloatValue);
    assertEquals(0.0f, ((ParticularFloatValue) value).value());
    assertFalse(value.isCategory2());
    assertTrue(value.isParticular());
    assertTrue(value.isSpecific());
  }

  /**
   * Test {@link ProgramFieldOptimizationInfo#ProgramFieldOptimizationInfo(Clazz, Field, boolean)}.
   *
   * <ul>
   *   <li>Then Value return {@link ParticularFloatValue}.
   * </ul>
   *
   * <p>Method under test: {@link ProgramFieldOptimizationInfo#ProgramFieldOptimizationInfo(Clazz,
   * Field, boolean)}
   */
  @Test
  @DisplayName(
      "Test new ProgramFieldOptimizationInfo(Clazz, Field, boolean); then Value return ParticularFloatValue")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void ProgramFieldOptimizationInfo.<init>(Clazz, Field, boolean)"})
  void testNewProgramFieldOptimizationInfo_thenValueReturnParticularFloatValue2() {
    // Arrange
    LibraryClass clazz = new LibraryClass();

    // Act
    ProgramFieldOptimizationInfo actualProgramFieldOptimizationInfo =
        new ProgramFieldOptimizationInfo(
            clazz, new NamedField("Field Name", "Field Descriptor"), false);

    // Assert
    Value value = actualProgramFieldOptimizationInfo.getValue();
    assertTrue(value instanceof ParticularFloatValue);
    assertEquals(0.0f, ((ParticularFloatValue) value).value());
    assertFalse(value.isCategory2());
    assertTrue(value.isParticular());
    assertTrue(value.isSpecific());
  }

  /**
   * Test getters and setters.
   *
   * <p>Methods under test:
   *
   * <ul>
   *   <li>{@link ProgramFieldOptimizationInfo#visitAnyAttribute(Clazz, Attribute)}
   *   <li>{@link ProgramFieldOptimizationInfo#setCanNotBeMadePrivate()}
   *   <li>{@link ProgramFieldOptimizationInfo#setRead()}
   *   <li>{@link ProgramFieldOptimizationInfo#setWritten()}
   *   <li>{@link ProgramFieldOptimizationInfo#canBeMadePrivate()}
   *   <li>{@link ProgramFieldOptimizationInfo#getReferencedClass()}
   *   <li>{@link ProgramFieldOptimizationInfo#isKept()}
   *   <li>{@link ProgramFieldOptimizationInfo#isRead()}
   *   <li>{@link ProgramFieldOptimizationInfo#isWritten()}
   * </ul>
   */
  @Test
  @DisplayName("Test getters and setters")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({
    "boolean ProgramFieldOptimizationInfo.canBeMadePrivate()",
    "ReferenceValue ProgramFieldOptimizationInfo.getReferencedClass()",
    "boolean ProgramFieldOptimizationInfo.isKept()",
    "boolean ProgramFieldOptimizationInfo.isRead()",
    "boolean ProgramFieldOptimizationInfo.isWritten()",
    "void ProgramFieldOptimizationInfo.setCanNotBeMadePrivate()",
    "void ProgramFieldOptimizationInfo.setRead()",
    "void ProgramFieldOptimizationInfo.setWritten()",
    "void ProgramFieldOptimizationInfo.visitAnyAttribute(Clazz, Attribute)"
  })
  void testGettersAndSetters() {
    // Arrange
    LibraryClass clazz = new LibraryClass();
    LibraryField field = new LibraryField(1, "Name", "Descriptor");

    ProgramFieldOptimizationInfo programFieldOptimizationInfo =
        new ProgramFieldOptimizationInfo(clazz, field, true);
    LibraryClass clazz2 = new LibraryClass();

    // Act
    programFieldOptimizationInfo.visitAnyAttribute(clazz2, new BootstrapMethodsAttribute());
    programFieldOptimizationInfo.setCanNotBeMadePrivate();
    programFieldOptimizationInfo.setRead();
    programFieldOptimizationInfo.setWritten();
    boolean actualCanBeMadePrivateResult = programFieldOptimizationInfo.canBeMadePrivate();
    ReferenceValue actualReferencedClass = programFieldOptimizationInfo.getReferencedClass();
    boolean actualIsKeptResult = programFieldOptimizationInfo.isKept();
    boolean actualIsReadResult = programFieldOptimizationInfo.isRead();

    // Assert
    assertNull(actualReferencedClass);
    assertFalse(actualCanBeMadePrivateResult);
    assertFalse(actualIsKeptResult);
    assertTrue(actualIsReadResult);
    assertTrue(programFieldOptimizationInfo.isWritten());
  }

  /**
   * Test {@link ProgramFieldOptimizationInfo#generalizeReferencedClass(ReferenceValue)}.
   *
   * <p>Method under test: {@link
   * ProgramFieldOptimizationInfo#generalizeReferencedClass(ReferenceValue)}
   */
  @Test
  @DisplayName("Test generalizeReferencedClass(ReferenceValue)")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void ProgramFieldOptimizationInfo.generalizeReferencedClass(ReferenceValue)"})
  void testGeneralizeReferencedClass() {
    // Arrange
    LibraryClass clazz = new LibraryClass();
    LibraryField field = new LibraryField(1, "Name", "Descriptor");

    ProgramFieldOptimizationInfo programFieldOptimizationInfo =
        new ProgramFieldOptimizationInfo(clazz, field, true);
    UnknownReferenceValue referencedClass = new UnknownReferenceValue();

    // Act
    programFieldOptimizationInfo.generalizeReferencedClass(referencedClass);

    // Assert
    assertSame(referencedClass, programFieldOptimizationInfo.getReferencedClass());
  }

  /**
   * Test {@link ProgramFieldOptimizationInfo#generalizeReferencedClass(ReferenceValue)}.
   *
   * <p>Method under test: {@link
   * ProgramFieldOptimizationInfo#generalizeReferencedClass(ReferenceValue)}
   */
  @Test
  @DisplayName("Test generalizeReferencedClass(ReferenceValue)")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void ProgramFieldOptimizationInfo.generalizeReferencedClass(ReferenceValue)"})
  void testGeneralizeReferencedClass2() {
    // Arrange
    LibraryClass clazz = new LibraryClass();
    LibraryField field = new LibraryField(1, "Name", "Descriptor");

    ProgramFieldOptimizationInfo programFieldOptimizationInfo =
        new ProgramFieldOptimizationInfo(clazz, field, true);

    ProgramFieldOptimizationInfo programFieldOptimizationInfo2 =
        new ProgramFieldOptimizationInfo(programFieldOptimizationInfo);
    UnknownReferenceValue referencedClass = new UnknownReferenceValue();
    programFieldOptimizationInfo2.generalizeReferencedClass(referencedClass);

    // Act
    programFieldOptimizationInfo2.generalizeReferencedClass(new UnknownReferenceValue());

    // Assert that nothing has changed
    ReferenceValue referencedClass2 = programFieldOptimizationInfo2.getReferencedClass();
    assertTrue(referencedClass2 instanceof UnknownReferenceValue);
    assertSame(referencedClass, referencedClass2);
  }

  /**
   * Test {@link ProgramFieldOptimizationInfo#generalizeReferencedClass(ReferenceValue)}.
   *
   * <p>Method under test: {@link
   * ProgramFieldOptimizationInfo#generalizeReferencedClass(ReferenceValue)}
   */
  @Test
  @DisplayName("Test generalizeReferencedClass(ReferenceValue)")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void ProgramFieldOptimizationInfo.generalizeReferencedClass(ReferenceValue)"})
  void testGeneralizeReferencedClass3() {
    // Arrange
    LibraryClass clazz = new LibraryClass();
    LibraryField field = new LibraryField(1, "Name", "Descriptor");

    ProgramFieldOptimizationInfo programFieldOptimizationInfo =
        new ProgramFieldOptimizationInfo(clazz, field, true);

    ProgramFieldOptimizationInfo programFieldOptimizationInfo2 =
        new ProgramFieldOptimizationInfo(programFieldOptimizationInfo);
    LibraryClass referencedClass = new LibraryClass();
    ArrayReferenceValue referencedClass2 =
        new ArrayReferenceValue("Type", referencedClass, true, new UnknownIntegerValue());
    programFieldOptimizationInfo2.generalizeReferencedClass(referencedClass2);
    UnknownReferenceValue referencedClass3 = new UnknownReferenceValue();

    // Act
    programFieldOptimizationInfo2.generalizeReferencedClass(referencedClass3);

    // Assert
    assertSame(referencedClass3, programFieldOptimizationInfo2.getReferencedClass());
  }

  /**
   * Test {@link ProgramFieldOptimizationInfo#generalizeReferencedClass(ReferenceValue)}.
   *
   * <p>Method under test: {@link
   * ProgramFieldOptimizationInfo#generalizeReferencedClass(ReferenceValue)}
   */
  @Test
  @DisplayName("Test generalizeReferencedClass(ReferenceValue)")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void ProgramFieldOptimizationInfo.generalizeReferencedClass(ReferenceValue)"})
  void testGeneralizeReferencedClass4() {
    // Arrange
    LibraryClass clazz = new LibraryClass();
    LibraryField field = new LibraryField(1, "Name", "Descriptor");

    ProgramFieldOptimizationInfo programFieldOptimizationInfo =
        new ProgramFieldOptimizationInfo(clazz, field, true);

    ProgramFieldOptimizationInfo programFieldOptimizationInfo2 =
        new ProgramFieldOptimizationInfo(programFieldOptimizationInfo);
    LibraryClass referencedClass = new LibraryClass();
    UnknownIntegerValue arrayLength = new UnknownIntegerValue();

    IdentifiedArrayReferenceValue referencedClass2 =
        new IdentifiedArrayReferenceValue(
            "Type", referencedClass, true, arrayLength, new ParticularReferenceValueFactory(), 1);
    programFieldOptimizationInfo2.generalizeReferencedClass(referencedClass2);
    UnknownReferenceValue referencedClass3 = new UnknownReferenceValue();

    // Act
    programFieldOptimizationInfo2.generalizeReferencedClass(referencedClass3);

    // Assert
    assertSame(referencedClass3, programFieldOptimizationInfo2.getReferencedClass());
  }

  /**
   * Test {@link ProgramFieldOptimizationInfo#generalizeReferencedClass(ReferenceValue)}.
   *
   * <p>Method under test: {@link
   * ProgramFieldOptimizationInfo#generalizeReferencedClass(ReferenceValue)}
   */
  @Test
  @DisplayName("Test generalizeReferencedClass(ReferenceValue)")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void ProgramFieldOptimizationInfo.generalizeReferencedClass(ReferenceValue)"})
  void testGeneralizeReferencedClass5() {
    // Arrange
    LibraryClass clazz = new LibraryClass();
    LibraryField field = new LibraryField(1, "Name", "Descriptor");

    ProgramFieldOptimizationInfo programFieldOptimizationInfo =
        new ProgramFieldOptimizationInfo(clazz, field, true);

    ProgramFieldOptimizationInfo programFieldOptimizationInfo2 =
        new ProgramFieldOptimizationInfo(programFieldOptimizationInfo);
    LibraryClass referencedClass = new LibraryClass();
    IdentifiedReferenceValue referencedClass2 =
        new IdentifiedReferenceValue(
            "Type", referencedClass, true, true, new ParticularReferenceValueFactory(), "Id");
    programFieldOptimizationInfo2.generalizeReferencedClass(referencedClass2);
    UnknownReferenceValue referencedClass3 = new UnknownReferenceValue();

    // Act
    programFieldOptimizationInfo2.generalizeReferencedClass(referencedClass3);

    // Assert
    assertSame(referencedClass3, programFieldOptimizationInfo2.getReferencedClass());
  }

  /**
   * Test {@link ProgramFieldOptimizationInfo#generalizeReferencedClass(ReferenceValue)}.
   *
   * <p>Method under test: {@link
   * ProgramFieldOptimizationInfo#generalizeReferencedClass(ReferenceValue)}
   */
  @Test
  @DisplayName("Test generalizeReferencedClass(ReferenceValue)")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void ProgramFieldOptimizationInfo.generalizeReferencedClass(ReferenceValue)"})
  void testGeneralizeReferencedClass6() {
    // Arrange
    LibraryClass clazz = new LibraryClass();
    LibraryField field = new LibraryField(1, "Name", "Descriptor");

    ProgramFieldOptimizationInfo programFieldOptimizationInfo =
        new ProgramFieldOptimizationInfo(clazz, field, true);

    ProgramFieldOptimizationInfo programFieldOptimizationInfo2 =
        new ProgramFieldOptimizationInfo(programFieldOptimizationInfo);
    TypedReferenceValue type = new TypedReferenceValue("Type", new LibraryClass(), true, true);
    MultiTypedReferenceValue referencedClass = new MultiTypedReferenceValue(type, true);
    programFieldOptimizationInfo2.generalizeReferencedClass(referencedClass);

    // Act
    programFieldOptimizationInfo2.generalizeReferencedClass(new UnknownReferenceValue());

    // Assert
    ReferenceValue referencedClass2 = programFieldOptimizationInfo2.getReferencedClass();
    assertTrue(referencedClass2 instanceof MultiTypedReferenceValue);
    assertEquals(referencedClass, referencedClass2);
  }

  /**
   * Test {@link ProgramFieldOptimizationInfo#generalizeReferencedClass(ReferenceValue)}.
   *
   * <p>Method under test: {@link
   * ProgramFieldOptimizationInfo#generalizeReferencedClass(ReferenceValue)}
   */
  @Test
  @DisplayName("Test generalizeReferencedClass(ReferenceValue)")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void ProgramFieldOptimizationInfo.generalizeReferencedClass(ReferenceValue)"})
  void testGeneralizeReferencedClass7() {
    // Arrange
    LibraryClass clazz = new LibraryClass();
    LibraryField field = new LibraryField(1, "Name", "Descriptor");

    ProgramFieldOptimizationInfo programFieldOptimizationInfo =
        new ProgramFieldOptimizationInfo(clazz, field, true);

    ProgramFieldOptimizationInfo programFieldOptimizationInfo2 =
        new ProgramFieldOptimizationInfo(programFieldOptimizationInfo);
    UnknownReferenceValue referenceValue = new UnknownReferenceValue();
    TracedReferenceValue referencedClass = new TracedReferenceValue(referenceValue, new TopValue());
    programFieldOptimizationInfo2.generalizeReferencedClass(referencedClass);
    UnknownReferenceValue referencedClass2 = new UnknownReferenceValue();

    // Act
    programFieldOptimizationInfo2.generalizeReferencedClass(referencedClass2);

    // Assert
    ReferenceValue referencedClass3 = programFieldOptimizationInfo2.getReferencedClass();
    assertTrue(referencedClass3 instanceof TracedReferenceValue);
    assertSame(referencedClass2, ((TracedReferenceValue) referencedClass3).getReferenceValue());
  }

  /**
   * Test {@link ProgramFieldOptimizationInfo#generalizeReferencedClass(ReferenceValue)}.
   *
   * <p>Method under test: {@link
   * ProgramFieldOptimizationInfo#generalizeReferencedClass(ReferenceValue)}
   */
  @Test
  @DisplayName("Test generalizeReferencedClass(ReferenceValue)")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void ProgramFieldOptimizationInfo.generalizeReferencedClass(ReferenceValue)"})
  void testGeneralizeReferencedClass8() {
    // Arrange
    LibraryClass clazz = new LibraryClass();
    LibraryField field = new LibraryField(1, "Name", "Descriptor");

    ProgramFieldOptimizationInfo programFieldOptimizationInfo =
        new ProgramFieldOptimizationInfo(clazz, field, true);

    ProgramFieldOptimizationInfo programFieldOptimizationInfo2 =
        new ProgramFieldOptimizationInfo(programFieldOptimizationInfo);
    TypedReferenceValue referencedClass =
        new TypedReferenceValue("Type", new LibraryClass(), true, true);
    programFieldOptimizationInfo2.generalizeReferencedClass(referencedClass);
    UnknownReferenceValue referencedClass2 = new UnknownReferenceValue();

    // Act
    programFieldOptimizationInfo2.generalizeReferencedClass(referencedClass2);

    // Assert
    assertSame(referencedClass2, programFieldOptimizationInfo2.getReferencedClass());
  }

  /**
   * Test {@link ProgramFieldOptimizationInfo#generalizeReferencedClass(ReferenceValue)}.
   *
   * <p>Method under test: {@link
   * ProgramFieldOptimizationInfo#generalizeReferencedClass(ReferenceValue)}
   */
  @Test
  @DisplayName("Test generalizeReferencedClass(ReferenceValue)")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void ProgramFieldOptimizationInfo.generalizeReferencedClass(ReferenceValue)"})
  void testGeneralizeReferencedClass9() {
    // Arrange
    LibraryClass clazz = new LibraryClass();
    LibraryField field = new LibraryField(1, "Name", "Descriptor");

    ProgramFieldOptimizationInfo programFieldOptimizationInfo =
        new ProgramFieldOptimizationInfo(clazz, field, true);

    ProgramFieldOptimizationInfo programFieldOptimizationInfo2 =
        new ProgramFieldOptimizationInfo(programFieldOptimizationInfo);
    TypedReferenceValue type = new TypedReferenceValue("Type", new LibraryClass(), true, true);
    MultiTypedReferenceValue referenceValue = new MultiTypedReferenceValue(type, true);
    TracedReferenceValue referencedClass = new TracedReferenceValue(referenceValue, new TopValue());
    programFieldOptimizationInfo2.generalizeReferencedClass(referencedClass);

    // Act
    programFieldOptimizationInfo2.generalizeReferencedClass(new UnknownReferenceValue());

    // Assert
    ReferenceValue referencedClass2 = programFieldOptimizationInfo2.getReferencedClass();
    ReferenceValue referenceValue2 = ((TracedReferenceValue) referencedClass2).getReferenceValue();
    assertTrue(referenceValue2 instanceof MultiTypedReferenceValue);
    assertTrue(referencedClass2 instanceof TracedReferenceValue);
    assertEquals(referenceValue, referenceValue2);
  }

  /**
   * Test {@link ProgramFieldOptimizationInfo#generalizeReferencedClass(ReferenceValue)}.
   *
   * <ul>
   *   <li>Then calls {@link AnalyzedObject#getType()}.
   * </ul>
   *
   * <p>Method under test: {@link
   * ProgramFieldOptimizationInfo#generalizeReferencedClass(ReferenceValue)}
   */
  @Test
  @DisplayName("Test generalizeReferencedClass(ReferenceValue); then calls getType()")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void ProgramFieldOptimizationInfo.generalizeReferencedClass(ReferenceValue)"})
  void testGeneralizeReferencedClass_thenCallsGetType() {
    // Arrange
    AnalyzedObject value = mock(AnalyzedObject.class);
    when(value.getType()).thenReturn("Type");
    LibraryClass referencedClass = new LibraryClass();

    ParticularReferenceValue referencedClass2 =
        new ParticularReferenceValue(
            referencedClass, new ParticularReferenceValueFactory(), "Reference ID", value);
    LibraryClass clazz = new LibraryClass();
    LibraryField field = new LibraryField(1, "Name", "Descriptor");

    ProgramFieldOptimizationInfo programFieldOptimizationInfo =
        new ProgramFieldOptimizationInfo(clazz, field, true);

    ProgramFieldOptimizationInfo programFieldOptimizationInfo2 =
        new ProgramFieldOptimizationInfo(programFieldOptimizationInfo);
    programFieldOptimizationInfo2.generalizeReferencedClass(referencedClass2);
    UnknownReferenceValue referencedClass3 = new UnknownReferenceValue();

    // Act
    programFieldOptimizationInfo2.generalizeReferencedClass(referencedClass3);

    // Assert
    verify(value, atLeast(1)).getType();
    assertSame(referencedClass3, programFieldOptimizationInfo2.getReferencedClass());
  }

  /**
   * Test {@link ProgramFieldOptimizationInfo#resetValue(Clazz, Field)}.
   *
   * <p>Method under test: {@link ProgramFieldOptimizationInfo#resetValue(Clazz, Field)}
   */
  @Test
  @DisplayName("Test resetValue(Clazz, Field)")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void ProgramFieldOptimizationInfo.resetValue(Clazz, Field)"})
  void testResetValue() {
    // Arrange
    LibraryClass clazz = new LibraryClass();
    LibraryField field = new LibraryField(1, "Name", "Descriptor");

    ProgramFieldOptimizationInfo programFieldOptimizationInfo =
        new ProgramFieldOptimizationInfo(clazz, field, true);
    LibraryClass clazz2 = new LibraryClass();
    LibraryField field2 = new LibraryField(1, "Name", "Descriptor");

    // Act
    programFieldOptimizationInfo.resetValue(clazz2, field2);

    // Assert that nothing has changed
    assertTrue(programFieldOptimizationInfo.getValue() instanceof ParticularDoubleValue);
  }

  /**
   * Test {@link ProgramFieldOptimizationInfo#resetValue(Clazz, Field)}.
   *
   * <p>Method under test: {@link ProgramFieldOptimizationInfo#resetValue(Clazz, Field)}
   */
  @Test
  @DisplayName("Test resetValue(Clazz, Field)")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void ProgramFieldOptimizationInfo.resetValue(Clazz, Field)"})
  void testResetValue2() {
    // Arrange
    LibraryClass clazz = new LibraryClass();
    LibraryField field = new LibraryField(1, "Name", "Descriptor");

    ProgramFieldOptimizationInfo programFieldOptimizationInfo =
        new ProgramFieldOptimizationInfo(clazz, field, false);
    LibraryClass clazz2 = new LibraryClass();
    LibraryField field2 = new LibraryField(1, "Name", "Descriptor");

    // Act
    programFieldOptimizationInfo.resetValue(clazz2, field2);

    // Assert that nothing has changed
    assertTrue(programFieldOptimizationInfo.getValue() instanceof ParticularDoubleValue);
  }

  /**
   * Test {@link ProgramFieldOptimizationInfo#resetValue(Clazz, Field)}.
   *
   * <p>Method under test: {@link ProgramFieldOptimizationInfo#resetValue(Clazz, Field)}
   */
  @Test
  @DisplayName("Test resetValue(Clazz, Field)")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void ProgramFieldOptimizationInfo.resetValue(Clazz, Field)"})
  void testResetValue3() {
    // Arrange
    LibraryClass clazz = new LibraryClass();
    LibraryField field = new LibraryField(1, "Name", "Descriptor");

    ProgramFieldOptimizationInfo programFieldOptimizationInfo =
        new ProgramFieldOptimizationInfo(clazz, field, true);
    LibraryClass clazz2 = new LibraryClass();

    // Act
    programFieldOptimizationInfo.resetValue(
        clazz2, new NamedField("Field Name", "Field Descriptor"));

    // Assert
    Value value = programFieldOptimizationInfo.getValue();
    assertTrue(value instanceof ParticularFloatValue);
    assertEquals(0.0f, ((ParticularFloatValue) value).value());
    assertFalse(value.isCategory2());
    assertTrue(value.isParticular());
    assertTrue(value.isSpecific());
  }

  /**
   * Test {@link ProgramFieldOptimizationInfo#resetValue(Clazz, Field)}.
   *
   * <ul>
   *   <li>When {@link LibraryField#LibraryField(int, String, String)} with u2accessFlags is eight
   *       and {@code Name} and {@code Descriptor}.
   * </ul>
   *
   * <p>Method under test: {@link ProgramFieldOptimizationInfo#resetValue(Clazz, Field)}
   */
  @Test
  @DisplayName(
      "Test resetValue(Clazz, Field); when LibraryField(int, String, String) with u2accessFlags is eight and 'Name' and 'Descriptor'")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void ProgramFieldOptimizationInfo.resetValue(Clazz, Field)"})
  void testResetValue_whenLibraryFieldWithU2accessFlagsIsEightAndNameAndDescriptor() {
    // Arrange
    LibraryClass clazz = new LibraryClass();
    LibraryField field = new LibraryField(1, "Name", "Descriptor");

    ProgramFieldOptimizationInfo programFieldOptimizationInfo =
        new ProgramFieldOptimizationInfo(clazz, field, true);
    LibraryClass clazz2 = new LibraryClass();
    LibraryField field2 = new LibraryField(8, "Name", "Descriptor");

    // Act
    programFieldOptimizationInfo.resetValue(clazz2, field2);

    // Assert that nothing has changed
    assertTrue(programFieldOptimizationInfo.getValue() instanceof ParticularDoubleValue);
  }

  /**
   * Test {@link ProgramFieldOptimizationInfo#generalizeValue(Value)}.
   *
   * <p>Method under test: {@link ProgramFieldOptimizationInfo#generalizeValue(Value)}
   */
  @Test
  @DisplayName("Test generalizeValue(Value)")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void ProgramFieldOptimizationInfo.generalizeValue(Value)"})
  void testGeneralizeValue() {
    // Arrange
    LibraryClass clazz = new LibraryClass();
    LibraryField field = new LibraryField(1, "Name", "Descriptor");

    ProgramFieldOptimizationInfo programFieldOptimizationInfo =
        new ProgramFieldOptimizationInfo(clazz, field, true);
    UnknownDoubleValue doubleValue1 = new UnknownDoubleValue();

    // Act
    programFieldOptimizationInfo.generalizeValue(
        new CompositeDoubleValue(doubleValue1, (byte) 'A', new UnknownDoubleValue()));

    // Assert
    Value value = programFieldOptimizationInfo.getValue();
    assertTrue(value instanceof UnknownDoubleValue);
    assertEquals(doubleValue1, value);
  }

  /**
   * Test {@link ProgramFieldOptimizationInfo#generalizeValue(Value)}.
   *
   * <p>Method under test: {@link ProgramFieldOptimizationInfo#generalizeValue(Value)}
   */
  @Test
  @DisplayName("Test generalizeValue(Value)")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void ProgramFieldOptimizationInfo.generalizeValue(Value)"})
  void testGeneralizeValue2() {
    // Arrange
    LibraryClass clazz = new LibraryClass();
    LibraryField field = new LibraryField(1, "Name", "Descriptor");

    ProgramFieldOptimizationInfo programFieldOptimizationInfo =
        new ProgramFieldOptimizationInfo(clazz, field, true);
    LibraryClass clazz2 = new LibraryClass();
    LibraryField field2 = new LibraryField(1, "Name", "Descriptor");

    programFieldOptimizationInfo.visitConstantValueAttribute(
        clazz2, field2, new ConstantValueAttribute(1, 1));
    TopValue value = new TopValue();

    // Act
    programFieldOptimizationInfo.generalizeValue(value);

    // Assert
    assertSame(value, programFieldOptimizationInfo.getValue());
  }

  /**
   * Test {@link ProgramFieldOptimizationInfo#visitConstantValueAttribute(Clazz, Field,
   * ConstantValueAttribute)}.
   *
   * <p>Method under test: {@link ProgramFieldOptimizationInfo#visitConstantValueAttribute(Clazz,
   * Field, ConstantValueAttribute)}
   */
  @Test
  @DisplayName("Test visitConstantValueAttribute(Clazz, Field, ConstantValueAttribute)")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void ProgramFieldOptimizationInfo.visitConstantValueAttribute(Clazz, Field, ConstantValueAttribute)"
  })
  void testVisitConstantValueAttribute() {
    // Arrange
    LibraryClass clazz = new LibraryClass();
    LibraryField field = new LibraryField(1, "Name", "Descriptor");

    ProgramFieldOptimizationInfo programFieldOptimizationInfo =
        new ProgramFieldOptimizationInfo(clazz, field, true);
    LibraryClass clazz2 = new LibraryClass();
    LibraryField field2 = new LibraryField(1, "Name", "Descriptor");

    // Act
    programFieldOptimizationInfo.visitConstantValueAttribute(
        clazz2, field2, new ConstantValueAttribute(1, 1));

    // Assert
    assertNull(programFieldOptimizationInfo.getValue());
  }

  /**
   * Test {@link ProgramFieldOptimizationInfo#setProgramFieldOptimizationInfo(Clazz, Field,
   * boolean)}.
   *
   * <p>Method under test: {@link
   * ProgramFieldOptimizationInfo#setProgramFieldOptimizationInfo(Clazz, Field, boolean)}
   */
  @Test
  @DisplayName("Test setProgramFieldOptimizationInfo(Clazz, Field, boolean)")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void ProgramFieldOptimizationInfo.setProgramFieldOptimizationInfo(Clazz, Field, boolean)"
  })
  void testSetProgramFieldOptimizationInfo() {
    // Arrange
    LibraryClass clazz = new LibraryClass();
    LibraryField field = new LibraryField(1, "Name", "Descriptor");

    // Act
    ProgramFieldOptimizationInfo.setProgramFieldOptimizationInfo(clazz, field, true);

    // Assert
    Object processingInfo = field.getProcessingInfo();
    Value value = ((ProgramFieldOptimizationInfo) processingInfo).getValue();
    assertTrue(value instanceof ParticularDoubleValue);
    assertTrue(processingInfo instanceof ProgramFieldOptimizationInfo);
    assertEquals(0.0d, ((ParticularDoubleValue) value).value());
    assertTrue(value.isCategory2());
    assertTrue(value.isParticular());
    assertTrue(value.isSpecific());
  }

  /**
   * Test {@link ProgramFieldOptimizationInfo#setProgramFieldOptimizationInfo(Clazz, Field,
   * boolean)}.
   *
   * <p>Method under test: {@link
   * ProgramFieldOptimizationInfo#setProgramFieldOptimizationInfo(Clazz, Field, boolean)}
   */
  @Test
  @DisplayName("Test setProgramFieldOptimizationInfo(Clazz, Field, boolean)")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void ProgramFieldOptimizationInfo.setProgramFieldOptimizationInfo(Clazz, Field, boolean)"
  })
  void testSetProgramFieldOptimizationInfo2() {
    // Arrange
    LibraryClass clazz = new LibraryClass();
    LibraryField field = new LibraryField(Double.SIZE, "Name", "Descriptor");

    // Act
    ProgramFieldOptimizationInfo.setProgramFieldOptimizationInfo(clazz, field, true);

    // Assert
    Object processingInfo = field.getProcessingInfo();
    Value value = ((ProgramFieldOptimizationInfo) processingInfo).getValue();
    assertTrue(value instanceof ParticularDoubleValue);
    assertTrue(processingInfo instanceof ProgramFieldOptimizationInfo);
    assertEquals(0.0d, ((ParticularDoubleValue) value).value());
    assertTrue(value.isCategory2());
    assertTrue(value.isParticular());
    assertTrue(value.isSpecific());
    assertTrue(((ProgramFieldOptimizationInfo) processingInfo).isRead());
    assertTrue(((ProgramFieldOptimizationInfo) processingInfo).isWritten());
  }

  /**
   * Test {@link ProgramFieldOptimizationInfo#setProgramFieldOptimizationInfo(Clazz, Field,
   * boolean)}.
   *
   * <p>Method under test: {@link
   * ProgramFieldOptimizationInfo#setProgramFieldOptimizationInfo(Clazz, Field, boolean)}
   */
  @Test
  @DisplayName("Test setProgramFieldOptimizationInfo(Clazz, Field, boolean)")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void ProgramFieldOptimizationInfo.setProgramFieldOptimizationInfo(Clazz, Field, boolean)"
  })
  void testSetProgramFieldOptimizationInfo3() {
    // Arrange
    LibraryClass clazz = new LibraryClass();
    LibraryField field = new LibraryField(8, "Name", "Descriptor");

    // Act
    ProgramFieldOptimizationInfo.setProgramFieldOptimizationInfo(clazz, field, true);

    // Assert
    Object processingInfo = field.getProcessingInfo();
    Value value = ((ProgramFieldOptimizationInfo) processingInfo).getValue();
    assertTrue(value instanceof ParticularDoubleValue);
    assertTrue(processingInfo instanceof ProgramFieldOptimizationInfo);
    assertEquals(0.0d, ((ParticularDoubleValue) value).value());
    assertTrue(value.isCategory2());
    assertTrue(value.isParticular());
    assertTrue(value.isSpecific());
  }

  /**
   * Test {@link ProgramFieldOptimizationInfo#setProgramFieldOptimizationInfo(Clazz, Field,
   * boolean)}.
   *
   * <p>Method under test: {@link
   * ProgramFieldOptimizationInfo#setProgramFieldOptimizationInfo(Clazz, Field, boolean)}
   */
  @Test
  @DisplayName("Test setProgramFieldOptimizationInfo(Clazz, Field, boolean)")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void ProgramFieldOptimizationInfo.setProgramFieldOptimizationInfo(Clazz, Field, boolean)"
  })
  void testSetProgramFieldOptimizationInfo4() {
    // Arrange
    LibraryClass clazz = new LibraryClass();
    NamedField field = new NamedField("Field Name", "Field Descriptor");

    // Act
    ProgramFieldOptimizationInfo.setProgramFieldOptimizationInfo(clazz, field, true);

    // Assert
    Object processingInfo = field.getProcessingInfo();
    Value value = ((ProgramFieldOptimizationInfo) processingInfo).getValue();
    assertTrue(value instanceof ParticularFloatValue);
    assertTrue(processingInfo instanceof ProgramFieldOptimizationInfo);
    assertEquals(0.0f, ((ParticularFloatValue) value).value());
    assertFalse(value.isCategory2());
    assertTrue(value.isParticular());
    assertTrue(value.isSpecific());
  }

  /**
   * Test {@link ProgramFieldOptimizationInfo#setProgramFieldOptimizationInfo(Clazz, Field,
   * boolean)}.
   *
   * <p>Method under test: {@link
   * ProgramFieldOptimizationInfo#setProgramFieldOptimizationInfo(Clazz, Field, boolean)}
   */
  @Test
  @DisplayName("Test setProgramFieldOptimizationInfo(Clazz, Field, boolean)")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void ProgramFieldOptimizationInfo.setProgramFieldOptimizationInfo(Clazz, Field, boolean)"
  })
  void testSetProgramFieldOptimizationInfo5() {
    // Arrange
    LibraryClass clazz = new LibraryClass();
    LibraryField field = new LibraryField(1, "Name", "Descriptor");

    // Act
    ProgramFieldOptimizationInfo.setProgramFieldOptimizationInfo(clazz, field, false);

    // Assert
    Object processingInfo = field.getProcessingInfo();
    Value value = ((ProgramFieldOptimizationInfo) processingInfo).getValue();
    assertTrue(value instanceof ParticularDoubleValue);
    assertTrue(processingInfo instanceof ProgramFieldOptimizationInfo);
    assertEquals(0.0d, ((ParticularDoubleValue) value).value());
    assertTrue(value.isCategory2());
    assertTrue(value.isParticular());
    assertTrue(value.isSpecific());
  }

  /**
   * Test {@link ProgramFieldOptimizationInfo#setProgramFieldOptimizationInfo(Clazz, Field,
   * boolean)}.
   *
   * <p>Method under test: {@link
   * ProgramFieldOptimizationInfo#setProgramFieldOptimizationInfo(Clazz, Field, boolean)}
   */
  @Test
  @DisplayName("Test setProgramFieldOptimizationInfo(Clazz, Field, boolean)")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void ProgramFieldOptimizationInfo.setProgramFieldOptimizationInfo(Clazz, Field, boolean)"
  })
  void testSetProgramFieldOptimizationInfo6() {
    // Arrange
    LibraryClass clazz = new LibraryClass();
    LibraryField field = new LibraryField(Short.SIZE, "Name", "Descriptor");

    // Act
    ProgramFieldOptimizationInfo.setProgramFieldOptimizationInfo(clazz, field, false);

    // Assert
    Object processingInfo = field.getProcessingInfo();
    assertTrue(processingInfo instanceof ProgramFieldOptimizationInfo);
    assertNull(((ProgramFieldOptimizationInfo) processingInfo).getReferencedClass());
    assertNull(((ProgramFieldOptimizationInfo) processingInfo).getValue());
    assertFalse(((ProgramFieldOptimizationInfo) processingInfo).isKept());
    assertFalse(((ProgramFieldOptimizationInfo) processingInfo).isRead());
    assertFalse(((ProgramFieldOptimizationInfo) processingInfo).isWritten());
    assertTrue(((ProgramFieldOptimizationInfo) processingInfo).canBeMadePrivate());
  }

  /**
   * Test {@link ProgramFieldOptimizationInfo#setProgramFieldOptimizationInfo(Clazz, Field,
   * boolean)}.
   *
   * <p>Method under test: {@link
   * ProgramFieldOptimizationInfo#setProgramFieldOptimizationInfo(Clazz, Field, boolean)}
   */
  @Test
  @DisplayName("Test setProgramFieldOptimizationInfo(Clazz, Field, boolean)")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void ProgramFieldOptimizationInfo.setProgramFieldOptimizationInfo(Clazz, Field, boolean)"
  })
  void testSetProgramFieldOptimizationInfo7() {
    // Arrange
    LibraryClass clazz = new LibraryClass();
    NamedField field = new NamedField("Field Name", "Field Descriptor");

    // Act
    ProgramFieldOptimizationInfo.setProgramFieldOptimizationInfo(clazz, field, false);

    // Assert
    Object processingInfo = field.getProcessingInfo();
    Value value = ((ProgramFieldOptimizationInfo) processingInfo).getValue();
    assertTrue(value instanceof ParticularFloatValue);
    assertTrue(processingInfo instanceof ProgramFieldOptimizationInfo);
    assertEquals(0.0f, ((ParticularFloatValue) value).value());
    assertFalse(value.isCategory2());
    assertTrue(value.isParticular());
    assertTrue(value.isSpecific());
  }

  /**
   * Test {@link ProgramFieldOptimizationInfo#getProgramFieldOptimizationInfo(Field)}.
   *
   * <ul>
   *   <li>Then return {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link
   * ProgramFieldOptimizationInfo#getProgramFieldOptimizationInfo(Field)}
   */
  @Test
  @DisplayName("Test getProgramFieldOptimizationInfo(Field); then return 'null'")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({
    "ProgramFieldOptimizationInfo ProgramFieldOptimizationInfo.getProgramFieldOptimizationInfo(Field)"
  })
  void testGetProgramFieldOptimizationInfo_thenReturnNull() {
    // Arrange
    LibraryField field = new LibraryField(1, "Name", "Descriptor");

    // Act and Assert
    assertNull(ProgramFieldOptimizationInfo.getProgramFieldOptimizationInfo(field));
  }
}
