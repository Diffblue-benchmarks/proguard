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
   * Methods under test:
   * <ul>
   *   <li>default or parameterless constructor of {@link TypeParameterClassChecker}
   *   <li>{@link TypeParameterClassChecker#visitAnyAttribute(Clazz, Attribute)}
   *   <li>{@link TypeParameterClassChecker#visitAnyClass(Clazz)}
   * </ul>
   */
  @Test
  void testGettersAndSetters() {
    // Arrange and Act
    TypeParameterClassChecker actualTypeParameterClassChecker = new TypeParameterClassChecker();
    LibraryClass clazz = new LibraryClass();
    actualTypeParameterClassChecker.visitAnyAttribute(clazz, new BootstrapMethodsAttribute());
    actualTypeParameterClassChecker.visitAnyClass(new LibraryClass());

    // Assert that nothing has changed
    assertFalse(actualTypeParameterClassChecker.hasFieldWithTypeParameter);
  }

  /**
   * Method under test:
   * {@link TypeParameterClassChecker#visitProgramClass(ProgramClass)}
   */
  @Test
  void testVisitProgramClass() {
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
   * Method under test:
   * {@link TypeParameterClassChecker#visitSignatureAttribute(Clazz, Field, SignatureAttribute)}
   */
  @Test
  void testVisitSignatureAttribute() {
    // Arrange
    TypeParameterClassChecker typeParameterClassChecker = new TypeParameterClassChecker();
    LibraryClass clazz = mock(LibraryClass.class);
    when(clazz.getString(anyInt())).thenReturn("String");
    LibraryField field = new LibraryField(1, "Name", "Descriptor");

    // Act
    typeParameterClassChecker.visitSignatureAttribute(clazz, (Field) field, new SignatureAttribute(1, 1));

    // Assert
    verify(clazz).getString(eq(1));
    assertFalse(typeParameterClassChecker.hasFieldWithTypeParameter);
  }

  /**
   * Method under test:
   * {@link TypeParameterClassChecker#visitSignatureAttribute(Clazz, Field, SignatureAttribute)}
   */
  @Test
  void testVisitSignatureAttribute2() {
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
   * Method under test:
   * {@link TypeParameterClassChecker#visitSignatureAttribute(Clazz, Field, SignatureAttribute)}
   */
  @Test
  void testVisitSignatureAttribute3() {
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
   * Method under test:
   * {@link TypeParameterClassChecker#visitSignatureAttribute(Clazz, Field, SignatureAttribute)}
   */
  @Test
  void testVisitSignatureAttribute4() {
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
   * Method under test:
   * {@link TypeParameterClassChecker#visitSignatureAttribute(Clazz, Field, SignatureAttribute)}
   */
  @Test
  void testVisitSignatureAttribute5() {
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
   * Method under test:
   * {@link TypeParameterClassChecker#visitSignatureAttribute(Clazz, Field, SignatureAttribute)}
   */
  @Test
  void testVisitSignatureAttribute6() {
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
   * Method under test:
   * {@link TypeParameterClassChecker#visitSignatureAttribute(Clazz, Field, SignatureAttribute)}
   */
  @Test
  void testVisitSignatureAttribute7() {
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
   * Method under test:
   * {@link TypeParameterClassChecker#visitSignatureAttribute(Clazz, Field, SignatureAttribute)}
   */
  @Test
  void testVisitSignatureAttribute8() {
    // Arrange
    TypeParameterClassChecker typeParameterClassChecker = new TypeParameterClassChecker();
    LibraryClass clazz = mock(LibraryClass.class);
    LibraryField field = new LibraryField(1, "Name", "Descriptor");

    SignatureAttribute signatureAttribute = mock(SignatureAttribute.class);
    when(signatureAttribute.getSignature(Mockito.<Clazz>any())).thenReturn("Signature");

    // Act
    typeParameterClassChecker.visitSignatureAttribute(clazz, (Field) field, signatureAttribute);

    // Assert
    verify(signatureAttribute).getSignature(isA(Clazz.class));
    assertFalse(typeParameterClassChecker.hasFieldWithTypeParameter);
  }
}
