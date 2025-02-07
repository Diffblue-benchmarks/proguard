package proguard.optimize.gson;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.anyInt;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import com.diffblue.cover.annotations.MethodsUnderTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import proguard.classfile.Clazz;
import proguard.classfile.Field;
import proguard.classfile.LibraryClass;
import proguard.classfile.LibraryField;
import proguard.classfile.ProgramClass;
import proguard.classfile.attribute.Attribute;
import proguard.classfile.attribute.BootstrapMethodsAttribute;
import proguard.classfile.attribute.SignatureAttribute;
import proguard.classfile.visitor.MemberVisitor;

class TypeParameterClassCheckerDiffblueTest {
  /**
   * Test {@link TypeParameterClassChecker#visitProgramClass(ProgramClass)}.
   * <ul>
   *   <li>Then calls {@link ProgramClass#fieldsAccept(MemberVisitor)}.</li>
   * </ul>
   * <p>
   * Method under test: {@link TypeParameterClassChecker#visitProgramClass(ProgramClass)}
   */
  @Test
  @DisplayName("Test visitProgramClass(ProgramClass); then calls fieldsAccept(MemberVisitor)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "void proguard.optimize.gson.TypeParameterClassChecker.visitProgramClass(proguard.classfile.ProgramClass)"})
  void testVisitProgramClass_thenCallsFieldsAccept() {
    // Arrange
    TypeParameterClassChecker typeParameterClassChecker = new TypeParameterClassChecker();
    ProgramClass programClass = mock(ProgramClass.class);
    doNothing().when(programClass).fieldsAccept(Mockito.<MemberVisitor>any());

    // Act
    typeParameterClassChecker.visitProgramClass(programClass);

    // Assert
    verify(programClass).fieldsAccept(isA(MemberVisitor.class));
  }

  /**
   * Test {@link TypeParameterClassChecker#visitSignatureAttribute(Clazz, Field, SignatureAttribute)} with {@code clazz}, {@code field}, {@code signatureAttribute}.
   * <p>
   * Method under test: {@link TypeParameterClassChecker#visitSignatureAttribute(Clazz, Field, SignatureAttribute)}
   */
  @Test
  @DisplayName("Test visitSignatureAttribute(Clazz, Field, SignatureAttribute) with 'clazz', 'field', 'signatureAttribute'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "void proguard.optimize.gson.TypeParameterClassChecker.visitSignatureAttribute(proguard.classfile.Clazz, proguard.classfile.Field, proguard.classfile.attribute.SignatureAttribute)"})
  void testVisitSignatureAttributeWithClazzFieldSignatureAttribute() {
    // Arrange
    TypeParameterClassChecker typeParameterClassChecker = new TypeParameterClassChecker();
    LibraryClass clazz = mock(LibraryClass.class);
    LibraryField field = new LibraryField(1, "Name", "Descriptor");

    SignatureAttribute signatureAttribute = mock(SignatureAttribute.class);
    when(signatureAttribute.getSignature(Mockito.<Clazz>any())).thenReturn("Signature");

    // Act
    typeParameterClassChecker.visitSignatureAttribute(clazz, (Field) field, signatureAttribute);

    // Assert that nothing has changed
    verify(signatureAttribute).getSignature(isA(Clazz.class));
    assertFalse(typeParameterClassChecker.hasFieldWithTypeParameter);
  }

  /**
   * Test {@link TypeParameterClassChecker#visitSignatureAttribute(Clazz, Field, SignatureAttribute)} with {@code clazz}, {@code field}, {@code signatureAttribute}.
   * <ul>
   *   <li>Given {@code +L}.</li>
   * </ul>
   * <p>
   * Method under test: {@link TypeParameterClassChecker#visitSignatureAttribute(Clazz, Field, SignatureAttribute)}
   */
  @Test
  @DisplayName("Test visitSignatureAttribute(Clazz, Field, SignatureAttribute) with 'clazz', 'field', 'signatureAttribute'; given '+L'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "void proguard.optimize.gson.TypeParameterClassChecker.visitSignatureAttribute(proguard.classfile.Clazz, proguard.classfile.Field, proguard.classfile.attribute.SignatureAttribute)"})
  void testVisitSignatureAttributeWithClazzFieldSignatureAttribute_givenL() {
    // Arrange
    TypeParameterClassChecker typeParameterClassChecker = new TypeParameterClassChecker();
    LibraryClass clazz = mock(LibraryClass.class);
    when(clazz.getString(anyInt())).thenReturn("+L");
    LibraryField field = new LibraryField(1, "Name", "Descriptor");

    // Act
    typeParameterClassChecker.visitSignatureAttribute(clazz, (Field) field, new SignatureAttribute(1, 1));

    // Assert
    verify(clazz).getString(eq(1));
    assertTrue(typeParameterClassChecker.hasFieldWithTypeParameter);
  }

  /**
   * Test {@link TypeParameterClassChecker#visitSignatureAttribute(Clazz, Field, SignatureAttribute)} with {@code clazz}, {@code field}, {@code signatureAttribute}.
   * <ul>
   *   <li>Given {@code String}.</li>
   * </ul>
   * <p>
   * Method under test: {@link TypeParameterClassChecker#visitSignatureAttribute(Clazz, Field, SignatureAttribute)}
   */
  @Test
  @DisplayName("Test visitSignatureAttribute(Clazz, Field, SignatureAttribute) with 'clazz', 'field', 'signatureAttribute'; given 'String'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "void proguard.optimize.gson.TypeParameterClassChecker.visitSignatureAttribute(proguard.classfile.Clazz, proguard.classfile.Field, proguard.classfile.attribute.SignatureAttribute)"})
  void testVisitSignatureAttributeWithClazzFieldSignatureAttribute_givenString() {
    // Arrange
    TypeParameterClassChecker typeParameterClassChecker = new TypeParameterClassChecker();
    LibraryClass clazz = mock(LibraryClass.class);
    when(clazz.getString(anyInt())).thenReturn("String");
    LibraryField field = new LibraryField(1, "Name", "Descriptor");

    // Act
    typeParameterClassChecker.visitSignatureAttribute(clazz, (Field) field, new SignatureAttribute(1, 1));

    // Assert that nothing has changed
    verify(clazz).getString(eq(1));
    assertFalse(typeParameterClassChecker.hasFieldWithTypeParameter);
  }

  /**
   * Test {@link TypeParameterClassChecker#visitSignatureAttribute(Clazz, Field, SignatureAttribute)} with {@code clazz}, {@code field}, {@code signatureAttribute}.
   * <ul>
   *   <li>Given {@code T}.</li>
   * </ul>
   * <p>
   * Method under test: {@link TypeParameterClassChecker#visitSignatureAttribute(Clazz, Field, SignatureAttribute)}
   */
  @Test
  @DisplayName("Test visitSignatureAttribute(Clazz, Field, SignatureAttribute) with 'clazz', 'field', 'signatureAttribute'; given 'T'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "void proguard.optimize.gson.TypeParameterClassChecker.visitSignatureAttribute(proguard.classfile.Clazz, proguard.classfile.Field, proguard.classfile.attribute.SignatureAttribute)"})
  void testVisitSignatureAttributeWithClazzFieldSignatureAttribute_givenT() {
    // Arrange
    TypeParameterClassChecker typeParameterClassChecker = new TypeParameterClassChecker();
    LibraryClass clazz = mock(LibraryClass.class);
    when(clazz.getString(anyInt())).thenReturn("T");
    LibraryField field = new LibraryField(1, "Name", "Descriptor");

    // Act
    typeParameterClassChecker.visitSignatureAttribute(clazz, (Field) field, new SignatureAttribute(1, 1));

    // Assert
    verify(clazz).getString(eq(1));
    assertTrue(typeParameterClassChecker.hasFieldWithTypeParameter);
  }

  /**
   * Test {@link TypeParameterClassChecker#visitSignatureAttribute(Clazz, Field, SignatureAttribute)} with {@code clazz}, {@code field}, {@code signatureAttribute}.
   * <ul>
   *   <li>Given {@code <T}.</li>
   * </ul>
   * <p>
   * Method under test: {@link TypeParameterClassChecker#visitSignatureAttribute(Clazz, Field, SignatureAttribute)}
   */
  @Test
  @DisplayName("Test visitSignatureAttribute(Clazz, Field, SignatureAttribute) with 'clazz', 'field', 'signatureAttribute'; given '<T'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "void proguard.optimize.gson.TypeParameterClassChecker.visitSignatureAttribute(proguard.classfile.Clazz, proguard.classfile.Field, proguard.classfile.attribute.SignatureAttribute)"})
  void testVisitSignatureAttributeWithClazzFieldSignatureAttribute_givenT2() {
    // Arrange
    TypeParameterClassChecker typeParameterClassChecker = new TypeParameterClassChecker();
    LibraryClass clazz = mock(LibraryClass.class);
    when(clazz.getString(anyInt())).thenReturn("<T");
    LibraryField field = new LibraryField(1, "Name", "Descriptor");

    // Act
    typeParameterClassChecker.visitSignatureAttribute(clazz, (Field) field, new SignatureAttribute(1, 1));

    // Assert
    verify(clazz).getString(eq(1));
    assertTrue(typeParameterClassChecker.hasFieldWithTypeParameter);
  }

  /**
   * Test {@link TypeParameterClassChecker#visitSignatureAttribute(Clazz, Field, SignatureAttribute)} with {@code clazz}, {@code field}, {@code signatureAttribute}.
   * <ul>
   *   <li>Given {@code ;T}.</li>
   * </ul>
   * <p>
   * Method under test: {@link TypeParameterClassChecker#visitSignatureAttribute(Clazz, Field, SignatureAttribute)}
   */
  @Test
  @DisplayName("Test visitSignatureAttribute(Clazz, Field, SignatureAttribute) with 'clazz', 'field', 'signatureAttribute'; given ';T'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "void proguard.optimize.gson.TypeParameterClassChecker.visitSignatureAttribute(proguard.classfile.Clazz, proguard.classfile.Field, proguard.classfile.attribute.SignatureAttribute)"})
  void testVisitSignatureAttributeWithClazzFieldSignatureAttribute_givenT3() {
    // Arrange
    TypeParameterClassChecker typeParameterClassChecker = new TypeParameterClassChecker();
    LibraryClass clazz = mock(LibraryClass.class);
    when(clazz.getString(anyInt())).thenReturn(";T");
    LibraryField field = new LibraryField(1, "Name", "Descriptor");

    // Act
    typeParameterClassChecker.visitSignatureAttribute(clazz, (Field) field, new SignatureAttribute(1, 1));

    // Assert
    verify(clazz).getString(eq(1));
    assertTrue(typeParameterClassChecker.hasFieldWithTypeParameter);
  }

  /**
   * Test {@link TypeParameterClassChecker#visitSignatureAttribute(Clazz, Field, SignatureAttribute)} with {@code clazz}, {@code field}, {@code signatureAttribute}.
   * <ul>
   *   <li>Given {@code +T}.</li>
   * </ul>
   * <p>
   * Method under test: {@link TypeParameterClassChecker#visitSignatureAttribute(Clazz, Field, SignatureAttribute)}
   */
  @Test
  @DisplayName("Test visitSignatureAttribute(Clazz, Field, SignatureAttribute) with 'clazz', 'field', 'signatureAttribute'; given '+T'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "void proguard.optimize.gson.TypeParameterClassChecker.visitSignatureAttribute(proguard.classfile.Clazz, proguard.classfile.Field, proguard.classfile.attribute.SignatureAttribute)"})
  void testVisitSignatureAttributeWithClazzFieldSignatureAttribute_givenT4() {
    // Arrange
    TypeParameterClassChecker typeParameterClassChecker = new TypeParameterClassChecker();
    LibraryClass clazz = mock(LibraryClass.class);
    when(clazz.getString(anyInt())).thenReturn("+T");
    LibraryField field = new LibraryField(1, "Name", "Descriptor");

    // Act
    typeParameterClassChecker.visitSignatureAttribute(clazz, (Field) field, new SignatureAttribute(1, 1));

    // Assert
    verify(clazz).getString(eq(1));
    assertTrue(typeParameterClassChecker.hasFieldWithTypeParameter);
  }

  /**
   * Test {@link TypeParameterClassChecker#visitSignatureAttribute(Clazz, Field, SignatureAttribute)} with {@code clazz}, {@code field}, {@code signatureAttribute}.
   * <ul>
   *   <li>Given {@code [T}.</li>
   * </ul>
   * <p>
   * Method under test: {@link TypeParameterClassChecker#visitSignatureAttribute(Clazz, Field, SignatureAttribute)}
   */
  @Test
  @DisplayName("Test visitSignatureAttribute(Clazz, Field, SignatureAttribute) with 'clazz', 'field', 'signatureAttribute'; given '[T'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "void proguard.optimize.gson.TypeParameterClassChecker.visitSignatureAttribute(proguard.classfile.Clazz, proguard.classfile.Field, proguard.classfile.attribute.SignatureAttribute)"})
  void testVisitSignatureAttributeWithClazzFieldSignatureAttribute_givenT5() {
    // Arrange
    TypeParameterClassChecker typeParameterClassChecker = new TypeParameterClassChecker();
    LibraryClass clazz = mock(LibraryClass.class);
    when(clazz.getString(anyInt())).thenReturn("[T");
    LibraryField field = new LibraryField(1, "Name", "Descriptor");

    // Act
    typeParameterClassChecker.visitSignatureAttribute(clazz, (Field) field, new SignatureAttribute(1, 1));

    // Assert
    verify(clazz).getString(eq(1));
    assertTrue(typeParameterClassChecker.hasFieldWithTypeParameter);
  }

  /**
   * Test getters and setters.
   * <p>
   * Methods under test:
   * <ul>
   *   <li>default or parameterless constructor of {@link TypeParameterClassChecker}
   *   <li>{@link TypeParameterClassChecker#visitAnyAttribute(Clazz, Attribute)}
   *   <li>{@link TypeParameterClassChecker#visitAnyClass(Clazz)}
   * </ul>
   */
  @Test
  @DisplayName("Test getters and setters")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void proguard.optimize.gson.TypeParameterClassChecker.<init>()",
      "void proguard.optimize.gson.TypeParameterClassChecker.visitAnyAttribute(proguard.classfile.Clazz, proguard.classfile.attribute.Attribute)",
      "void proguard.optimize.gson.TypeParameterClassChecker.visitAnyClass(proguard.classfile.Clazz)"})
  void testGettersAndSetters() {
    // Arrange and Act
    TypeParameterClassChecker actualTypeParameterClassChecker = new TypeParameterClassChecker();
    LibraryClass clazz = new LibraryClass();
    actualTypeParameterClassChecker.visitAnyAttribute(clazz, new BootstrapMethodsAttribute());
    actualTypeParameterClassChecker.visitAnyClass(new LibraryClass());

    // Assert
    assertFalse(actualTypeParameterClassChecker.hasFieldWithTypeParameter);
  }
}
