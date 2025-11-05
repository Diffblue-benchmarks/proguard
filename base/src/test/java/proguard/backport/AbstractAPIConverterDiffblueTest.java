package proguard.backport;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.anyInt;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import java.io.PrintWriter;
import java.io.StringWriter;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import proguard.classfile.ClassPool;
import proguard.classfile.Clazz;
import proguard.classfile.LibraryClass;
import proguard.classfile.LibraryMethod;
import proguard.classfile.Method;
import proguard.classfile.ProgramClass;
import proguard.classfile.ProgramField;
import proguard.classfile.ProgramMethod;
import proguard.classfile.attribute.CodeAttribute;
import proguard.classfile.attribute.LocalVariableInfo;
import proguard.classfile.attribute.LocalVariableTypeInfo;
import proguard.classfile.attribute.SignatureAttribute;
import proguard.classfile.attribute.annotation.Annotation;
import proguard.classfile.attribute.annotation.AnnotationDefaultAttribute;
import proguard.classfile.attribute.annotation.AnnotationElementValue;
import proguard.classfile.attribute.annotation.ClassElementValue;
import proguard.classfile.attribute.annotation.EnumConstantElementValue;
import proguard.classfile.attribute.annotation.visitor.AnnotationVisitor;
import proguard.classfile.attribute.annotation.visitor.ElementValueVisitor;
import proguard.classfile.attribute.visitor.AttributeVisitor;
import proguard.classfile.constant.AnyMethodrefConstant;
import proguard.classfile.constant.ClassConstant;
import proguard.classfile.constant.FieldrefConstant;
import proguard.classfile.constant.InterfaceMethodrefConstant;
import proguard.classfile.constant.visitor.ConstantVisitor;
import proguard.classfile.instruction.visitor.InstructionVisitor;
import proguard.classfile.util.WarningPrinter;
import proguard.classfile.visitor.ClassVisitor;
import proguard.classfile.visitor.MemberVisitor;
import proguard.optimize.DuplicateInitializerInvocationFixer;
import proguard.testutils.cpa.NamedField;
import proguard.testutils.cpa.NamedMember;
import proguard.util.ConstantMatcher;
import proguard.util.EmptyStringMatcher;
import proguard.util.FixedStringMatcher;
import proguard.util.StringMatcher;
import proguard.util.VariableStringMatcher;

class AbstractAPIConverterDiffblueTest {
  /**
   * Method under test:
   * {@link AbstractAPIConverter.MethodReplacement#matches(Clazz, AnyMethodrefConstant)}
   */
  @Test
  void testMethodReplacementMatches() {
    // Arrange
    ClassPool programClassPool = new ClassPool();
    ClassPool libraryClassPool = new ClassPool();
    WarningPrinter warningPrinter = new WarningPrinter(new PrintWriter(new StringWriter()));
    ClassVisitor modifiedClassVisitor = mock(ClassVisitor.class);
    AbstractAPIConverter.MethodReplacement methodReplacement = (new AbstractAPIConverter(programClassPool,
        libraryClassPool, warningPrinter, modifiedClassVisitor,
        new DuplicateInitializerInvocationFixer())).new MethodReplacement("Class Name", "Method Name", "Method Desc",
            "Replacement Class Name", "Replacement Method Name", "Replacement Method Desc");
    LibraryClass clazz = mock(LibraryClass.class);
    when(clazz.getClassName(anyInt())).thenReturn("Class Name");
    when(clazz.getName(anyInt())).thenReturn("Name");
    when(clazz.getType(anyInt())).thenReturn("Type");

    // Act
    boolean actualMatchesResult = methodReplacement.matches(clazz, new InterfaceMethodrefConstant());

    // Assert
    verify(clazz).getClassName(eq(0));
    verify(clazz).getName(eq(0));
    verify(clazz).getType(eq(0));
    assertFalse(actualMatchesResult);
  }

  /**
   * Method under test:
   * {@link AbstractAPIConverter.MethodReplacement#matches(Clazz, AnyMethodrefConstant)}
   */
  @Test
  void testMethodReplacementMatches2() {
    // Arrange
    ClassPool programClassPool = new ClassPool();
    ClassPool libraryClassPool = new ClassPool();
    WarningPrinter warningPrinter = new WarningPrinter(new PrintWriter(new StringWriter()));
    ClassVisitor modifiedClassVisitor = mock(ClassVisitor.class);
    AbstractAPIConverter.MethodReplacement methodReplacement = (new AbstractAPIConverter(programClassPool,
        libraryClassPool, warningPrinter, modifiedClassVisitor,
        new DuplicateInitializerInvocationFixer())).new MethodReplacement("Class Name", "Method Name", "Method Desc",
            "Replacement Class Name", "Replacement Method Name", "Replacement Method Desc");
    LibraryClass clazz = mock(LibraryClass.class);
    when(clazz.getClassName(anyInt())).thenReturn("Class Name");
    when(clazz.getName(anyInt())).thenReturn("Name");
    when(clazz.getType(anyInt())).thenReturn("Type");
    LibraryClass referencedClass = new LibraryClass();

    // Act
    boolean actualMatchesResult = methodReplacement.matches(clazz,
        new InterfaceMethodrefConstant(1, 1, referencedClass, new LibraryMethod(1, "Name", "Descriptor")));

    // Assert
    verify(clazz).getClassName(eq(1));
    verify(clazz).getName(eq(1));
    verify(clazz).getType(eq(1));
    assertFalse(actualMatchesResult);
  }

  /**
   * Method under test:
   * {@link AbstractAPIConverter.MethodReplacement#matches(Clazz, AnyMethodrefConstant)}
   */
  @Test
  void testMethodReplacementMatches3() {
    // Arrange
    ClassPool programClassPool = new ClassPool();
    ClassPool libraryClassPool = new ClassPool();
    WarningPrinter warningPrinter = new WarningPrinter(new PrintWriter(new StringWriter()));
    ClassVisitor modifiedClassVisitor = mock(ClassVisitor.class);
    AbstractAPIConverter.MethodReplacement methodReplacement = (new AbstractAPIConverter(programClassPool,
        libraryClassPool, warningPrinter, modifiedClassVisitor,
        new DuplicateInitializerInvocationFixer())).new MethodReplacement("<default>", "Method Name", "Method Desc",
            "Replacement Class Name", "Replacement Method Name", "Replacement Method Desc");
    LibraryClass clazz = mock(LibraryClass.class);
    when(clazz.getClassName(anyInt())).thenReturn("Class Name");
    when(clazz.getName(anyInt())).thenReturn("Name");
    when(clazz.getType(anyInt())).thenReturn("Type");
    LibraryClass referencedClass = new LibraryClass();

    // Act
    boolean actualMatchesResult = methodReplacement.matches(clazz,
        new InterfaceMethodrefConstant(1, 1, referencedClass, new LibraryMethod(1, "Name", "Descriptor")));

    // Assert
    verify(clazz).getClassName(eq(1));
    verify(clazz).getName(eq(1));
    verify(clazz).getType(eq(1));
    assertFalse(actualMatchesResult);
  }

  /**
   * Method under test:
   * {@link AbstractAPIConverter.MethodReplacement#matches(Clazz, AnyMethodrefConstant)}
   */
  @Test
  void testMethodReplacementMatches4() {
    // Arrange
    ClassPool programClassPool = new ClassPool();
    ClassPool libraryClassPool = new ClassPool();
    WarningPrinter warningPrinter = new WarningPrinter(new PrintWriter(new StringWriter()));
    ClassVisitor modifiedClassVisitor = mock(ClassVisitor.class);
    AbstractAPIConverter.MethodReplacement methodReplacement = (new AbstractAPIConverter(programClassPool,
        libraryClassPool, warningPrinter, modifiedClassVisitor,
        new DuplicateInitializerInvocationFixer())).new MethodReplacement("Class Name", "<default>", "Method Desc",
            "Replacement Class Name", "Replacement Method Name", "Replacement Method Desc");
    LibraryClass clazz = mock(LibraryClass.class);
    when(clazz.getClassName(anyInt())).thenReturn("Class Name");
    when(clazz.getName(anyInt())).thenReturn("Name");
    when(clazz.getType(anyInt())).thenReturn("Type");
    LibraryClass referencedClass = new LibraryClass();

    // Act
    boolean actualMatchesResult = methodReplacement.matches(clazz,
        new InterfaceMethodrefConstant(1, 1, referencedClass, new LibraryMethod(1, "Name", "Descriptor")));

    // Assert
    verify(clazz).getClassName(eq(1));
    verify(clazz).getName(eq(1));
    verify(clazz).getType(eq(1));
    assertFalse(actualMatchesResult);
  }

  /**
   * Method under test:
   * {@link AbstractAPIConverter.MethodReplacement#matches(Clazz, AnyMethodrefConstant)}
   */
  @Test
  void testMethodReplacementMatches5() {
    // Arrange
    ClassPool programClassPool = new ClassPool();
    ClassPool libraryClassPool = new ClassPool();
    WarningPrinter warningPrinter = new WarningPrinter(new PrintWriter(new StringWriter()));
    ClassVisitor modifiedClassVisitor = mock(ClassVisitor.class);
    AbstractAPIConverter.MethodReplacement methodReplacement = (new AbstractAPIConverter(programClassPool,
        libraryClassPool, warningPrinter, modifiedClassVisitor,
        new DuplicateInitializerInvocationFixer())).new MethodReplacement("Class Name", "<static>", "Method Desc",
            "Replacement Class Name", "Replacement Method Name", "Replacement Method Desc");
    LibraryClass clazz = mock(LibraryClass.class);
    when(clazz.getClassName(anyInt())).thenReturn("Class Name");
    when(clazz.getName(anyInt())).thenReturn("Name");
    when(clazz.getType(anyInt())).thenReturn("Type");
    LibraryClass referencedClass = new LibraryClass();

    // Act
    boolean actualMatchesResult = methodReplacement.matches(clazz,
        new InterfaceMethodrefConstant(1, 1, referencedClass, new LibraryMethod(1, "Name", "Descriptor")));

    // Assert
    verify(clazz).getClassName(eq(1));
    verify(clazz).getName(eq(1));
    verify(clazz).getType(eq(1));
    assertFalse(actualMatchesResult);
  }

  /**
   * Method under test:
   * {@link AbstractAPIConverter.MethodReplacement#matches(Clazz, AnyMethodrefConstant)}
   */
  @Test
  void testMethodReplacementMatches6() {
    // Arrange
    ClassPool programClassPool = new ClassPool();
    ClassPool libraryClassPool = new ClassPool();
    WarningPrinter warningPrinter = new WarningPrinter(new PrintWriter(new StringWriter()));
    ClassVisitor modifiedClassVisitor = mock(ClassVisitor.class);
    AbstractAPIConverter.MethodReplacement methodReplacement = (new AbstractAPIConverter(programClassPool,
        libraryClassPool, warningPrinter, modifiedClassVisitor,
        new DuplicateInitializerInvocationFixer())).new MethodReplacement("Class Name", "*", "Method Desc",
            "Replacement Class Name", "Replacement Method Name", "Replacement Method Desc");
    LibraryClass clazz = mock(LibraryClass.class);
    when(clazz.getClassName(anyInt())).thenReturn("Class Name");
    when(clazz.getName(anyInt())).thenReturn("Name");
    when(clazz.getType(anyInt())).thenReturn("Type");
    LibraryClass referencedClass = new LibraryClass();

    // Act
    boolean actualMatchesResult = methodReplacement.matches(clazz,
        new InterfaceMethodrefConstant(1, 1, referencedClass, new LibraryMethod(1, "Name", "Descriptor")));

    // Assert
    verify(clazz).getClassName(eq(1));
    verify(clazz).getName(eq(1));
    verify(clazz).getType(eq(1));
    StringMatcher stringMatcher = methodReplacement.methodNameMatcher;
    assertTrue(stringMatcher instanceof VariableStringMatcher);
    assertEquals("Name", ((VariableStringMatcher) stringMatcher).getMatchingString());
    assertFalse(actualMatchesResult);
  }

  /**
   * Method under test:
   * {@link AbstractAPIConverter.MethodReplacement#matches(Clazz, AnyMethodrefConstant)}
   */
  @Test
  void testMethodReplacementMatches7() {
    // Arrange
    ClassPool programClassPool = new ClassPool();
    ClassPool libraryClassPool = new ClassPool();
    WarningPrinter warningPrinter = new WarningPrinter(new PrintWriter(new StringWriter()));
    ClassVisitor modifiedClassVisitor = mock(ClassVisitor.class);
    AbstractAPIConverter.MethodReplacement methodReplacement = (new AbstractAPIConverter(programClassPool,
        libraryClassPool, warningPrinter, modifiedClassVisitor,
        new DuplicateInitializerInvocationFixer())).new MethodReplacement("Class Name", "Method Name", "Method Desc",
            "Replacement Class Name", "Replacement Method Name", "Replacement Method Desc");
    LibraryClass clazz = mock(LibraryClass.class);
    when(clazz.getClassName(anyInt())).thenReturn("Class Name");
    when(clazz.getName(anyInt())).thenReturn("Name");
    when(clazz.getType(anyInt())).thenReturn("Type");

    // Act
    boolean actualMatchesResult = methodReplacement.matches(clazz,
        new InterfaceMethodrefConstant(1, 1, new LibraryClass(), null));

    // Assert
    verify(clazz).getClassName(eq(1));
    verify(clazz).getName(eq(1));
    verify(clazz).getType(eq(1));
    assertFalse(actualMatchesResult);
  }

  /**
   * Method under test:
   * {@link AbstractAPIConverter.MethodReplacement#MethodReplacement(AbstractAPIConverter, String, String, String, String, String, String)}
   */
  @Test
  void testMethodReplacementNewMethodReplacement() {
    // Arrange
    ClassPool programClassPool = new ClassPool();
    ClassPool libraryClassPool = new ClassPool();
    WarningPrinter warningPrinter = new WarningPrinter(new PrintWriter(new StringWriter()));
    ClassVisitor modifiedClassVisitor = mock(ClassVisitor.class);

    // Act
    AbstractAPIConverter.MethodReplacement actualMethodReplacement = (new AbstractAPIConverter(programClassPool,
        libraryClassPool, warningPrinter, modifiedClassVisitor,
        new DuplicateInitializerInvocationFixer())).new MethodReplacement("Class Name", "Method Name", "Method Desc",
            "Replacement Class Name", "Replacement Method Name", "Replacement Method Desc");

    // Assert
    assertTrue(actualMethodReplacement.classNameMatcher instanceof FixedStringMatcher);
    assertTrue(actualMethodReplacement.descMatcher instanceof FixedStringMatcher);
    assertTrue(actualMethodReplacement.methodNameMatcher instanceof FixedStringMatcher);
    assertEquals("Class Name", actualMethodReplacement.matchingClassName);
    assertEquals("Method Desc", actualMethodReplacement.matchingMethodDesc);
    assertEquals("Method Name", actualMethodReplacement.matchingMethodName);
    assertEquals("Replacement Class Name", actualMethodReplacement.replacementClassName);
    assertEquals("Replacement Method Desc", actualMethodReplacement.replacementMethodDesc);
    assertEquals("Replacement Method Name", actualMethodReplacement.replacementMethodName);
  }

  /**
   * Method under test:
   * {@link AbstractAPIConverter.MethodReplacement#MethodReplacement(AbstractAPIConverter, String, String, String, String, String, String)}
   */
  @Test
  void testMethodReplacementNewMethodReplacement2() {
    // Arrange
    ClassPool programClassPool = new ClassPool();
    ClassPool libraryClassPool = new ClassPool();
    WarningPrinter warningPrinter = new WarningPrinter(new PrintWriter(new StringWriter()));
    ClassVisitor modifiedClassVisitor = mock(ClassVisitor.class);

    // Act
    AbstractAPIConverter.MethodReplacement actualMethodReplacement = (new AbstractAPIConverter(programClassPool,
        libraryClassPool, warningPrinter, modifiedClassVisitor,
        new DuplicateInitializerInvocationFixer())).new MethodReplacement("**", "Method Name", "Method Desc",
            "Replacement Class Name", "Replacement Method Name", "Replacement Method Desc");

    // Assert
    assertTrue(actualMethodReplacement.descMatcher instanceof FixedStringMatcher);
    assertTrue(actualMethodReplacement.methodNameMatcher instanceof FixedStringMatcher);
    assertTrue(actualMethodReplacement.classNameMatcher instanceof VariableStringMatcher);
    assertEquals("**", actualMethodReplacement.matchingClassName);
    assertEquals("Method Desc", actualMethodReplacement.matchingMethodDesc);
    assertEquals("Method Name", actualMethodReplacement.matchingMethodName);
    assertEquals("Replacement Class Name", actualMethodReplacement.replacementClassName);
    assertEquals("Replacement Method Desc", actualMethodReplacement.replacementMethodDesc);
    assertEquals("Replacement Method Name", actualMethodReplacement.replacementMethodName);
  }

  /**
   * Method under test:
   * {@link AbstractAPIConverter.MethodReplacement#MethodReplacement(AbstractAPIConverter, String, String, String, String, String, String)}
   */
  @Test
  void testMethodReplacementNewMethodReplacement3() {
    // Arrange
    ClassPool programClassPool = new ClassPool();
    ClassPool libraryClassPool = new ClassPool();
    WarningPrinter warningPrinter = new WarningPrinter(new PrintWriter(new StringWriter()));
    ClassVisitor modifiedClassVisitor = mock(ClassVisitor.class);

    // Act
    AbstractAPIConverter.MethodReplacement actualMethodReplacement = (new AbstractAPIConverter(programClassPool,
        libraryClassPool, warningPrinter, modifiedClassVisitor,
        new DuplicateInitializerInvocationFixer())).new MethodReplacement("*", "Method Name", "Method Desc",
            "Replacement Class Name", "Replacement Method Name", "Replacement Method Desc");

    // Assert
    assertTrue(actualMethodReplacement.descMatcher instanceof FixedStringMatcher);
    assertTrue(actualMethodReplacement.methodNameMatcher instanceof FixedStringMatcher);
    assertTrue(actualMethodReplacement.classNameMatcher instanceof VariableStringMatcher);
    assertEquals("*", actualMethodReplacement.matchingClassName);
    assertEquals("Method Desc", actualMethodReplacement.matchingMethodDesc);
    assertEquals("Method Name", actualMethodReplacement.matchingMethodName);
    assertEquals("Replacement Class Name", actualMethodReplacement.replacementClassName);
    assertEquals("Replacement Method Desc", actualMethodReplacement.replacementMethodDesc);
    assertEquals("Replacement Method Name", actualMethodReplacement.replacementMethodName);
  }

  /**
   * Method under test:
   * {@link AbstractAPIConverter.MethodReplacement#MethodReplacement(AbstractAPIConverter, String, String, String, String, String, String)}
   */
  @Test
  void testMethodReplacementNewMethodReplacement4() {
    // Arrange
    ClassPool programClassPool = new ClassPool();
    ClassPool libraryClassPool = new ClassPool();
    WarningPrinter warningPrinter = new WarningPrinter(new PrintWriter(new StringWriter()));
    ClassVisitor modifiedClassVisitor = mock(ClassVisitor.class);

    // Act
    AbstractAPIConverter.MethodReplacement actualMethodReplacement = (new AbstractAPIConverter(programClassPool,
        libraryClassPool, warningPrinter, modifiedClassVisitor,
        new DuplicateInitializerInvocationFixer())).new MethodReplacement("Class Name", "**", "Method Desc",
            "Replacement Class Name", "Replacement Method Name", "Replacement Method Desc");

    // Assert
    assertTrue(actualMethodReplacement.classNameMatcher instanceof FixedStringMatcher);
    assertTrue(actualMethodReplacement.descMatcher instanceof FixedStringMatcher);
    assertTrue(actualMethodReplacement.methodNameMatcher instanceof VariableStringMatcher);
    assertEquals("**", actualMethodReplacement.matchingMethodName);
    assertEquals("Class Name", actualMethodReplacement.matchingClassName);
    assertEquals("Method Desc", actualMethodReplacement.matchingMethodDesc);
    assertEquals("Replacement Class Name", actualMethodReplacement.replacementClassName);
    assertEquals("Replacement Method Desc", actualMethodReplacement.replacementMethodDesc);
    assertEquals("Replacement Method Name", actualMethodReplacement.replacementMethodName);
  }

  /**
   * Method under test:
   * {@link AbstractAPIConverter.MethodReplacement#MethodReplacement(AbstractAPIConverter, String, String, String, String, String, String)}
   */
  @Test
  void testMethodReplacementNewMethodReplacement5() {
    // Arrange
    ClassPool programClassPool = new ClassPool();
    ClassPool libraryClassPool = new ClassPool();
    WarningPrinter warningPrinter = new WarningPrinter(new PrintWriter(new StringWriter()));
    ClassVisitor modifiedClassVisitor = mock(ClassVisitor.class);

    // Act
    AbstractAPIConverter.MethodReplacement actualMethodReplacement = (new AbstractAPIConverter(programClassPool,
        libraryClassPool, warningPrinter, modifiedClassVisitor,
        new DuplicateInitializerInvocationFixer())).new MethodReplacement("Class Name", "Method Name", "**",
            "Replacement Class Name", "Replacement Method Name", "Replacement Method Desc");

    // Assert
    assertTrue(actualMethodReplacement.descMatcher instanceof ConstantMatcher);
    assertTrue(actualMethodReplacement.classNameMatcher instanceof FixedStringMatcher);
    assertTrue(actualMethodReplacement.methodNameMatcher instanceof FixedStringMatcher);
    assertEquals("**", actualMethodReplacement.matchingMethodDesc);
    assertEquals("Class Name", actualMethodReplacement.matchingClassName);
    assertEquals("Method Name", actualMethodReplacement.matchingMethodName);
    assertEquals("Replacement Class Name", actualMethodReplacement.replacementClassName);
    assertEquals("Replacement Method Desc", actualMethodReplacement.replacementMethodDesc);
    assertEquals("Replacement Method Name", actualMethodReplacement.replacementMethodName);
  }

  /**
   * Method under test:
   * {@link AbstractAPIConverter.MethodReplacement#replaceInstruction(int, Clazz, Method, AnyMethodrefConstant)}
   */
  @Test
  void testMethodReplacementReplaceInstruction() {
    // Arrange
    ClassPool programClassPool = new ClassPool();
    ClassPool libraryClassPool = new ClassPool();
    WarningPrinter warningPrinter = new WarningPrinter(new PrintWriter(new StringWriter()));
    ClassVisitor modifiedClassVisitor = mock(ClassVisitor.class);
    AbstractAPIConverter.MethodReplacement methodReplacement = (new AbstractAPIConverter(programClassPool,
        libraryClassPool, warningPrinter, modifiedClassVisitor,
        new DuplicateInitializerInvocationFixer())).new MethodReplacement("Class Name", "Method Name", "Method Desc",
            "Replacement Class Name", "Replacement Method Name", "Replacement Method Desc");
    LibraryClass clazz = mock(LibraryClass.class);
    when(clazz.getClassName(anyInt())).thenReturn("Class Name");
    when(clazz.getName(anyInt())).thenReturn("Name");
    when(clazz.getType(anyInt())).thenReturn("Type");
    LibraryMethod method = new LibraryMethod(1, "Name", "Descriptor");

    // Act
    methodReplacement.replaceInstruction(2, clazz, method, new InterfaceMethodrefConstant());

    // Assert
    verify(clazz).getClassName(eq(0));
    verify(clazz).getName(eq(0));
    verify(clazz).getType(eq(0));
  }

  /**
   * Method under test: {@link AbstractAPIConverter#replace(String, String)}
   */
  @Test
  void testReplace() {
    // Arrange
    ClassPool programClassPool = new ClassPool();
    ClassPool libraryClassPool = new ClassPool();
    WarningPrinter warningPrinter = new WarningPrinter(new PrintWriter(new StringWriter()));
    ClassVisitor modifiedClassVisitor = mock(ClassVisitor.class);

    // Act
    AbstractAPIConverter.TypeReplacement actualReplaceResult = (new AbstractAPIConverter(programClassPool,
        libraryClassPool, warningPrinter, modifiedClassVisitor, new DuplicateInitializerInvocationFixer()))
            .replace("Class Name", "Replacement Class Name");

    // Assert
    assertTrue(actualReplaceResult.classNameMatcher instanceof FixedStringMatcher);
    assertEquals("Class Name", actualReplaceResult.matchingClassName);
    assertNull(actualReplaceResult.replacementClassName);
  }

  /**
   * Method under test: {@link AbstractAPIConverter#replace(String, String)}
   */
  @Test
  void testReplace2() {
    // Arrange
    ClassPool programClassPool = new ClassPool();
    ClassPool libraryClassPool = new ClassPool();
    WarningPrinter warningPrinter = new WarningPrinter(new PrintWriter(new StringWriter()));
    ClassVisitor modifiedClassVisitor = mock(ClassVisitor.class);

    // Act
    AbstractAPIConverter.TypeReplacement actualReplaceResult = (new AbstractAPIConverter(programClassPool,
        libraryClassPool, warningPrinter, modifiedClassVisitor, new DuplicateInitializerInvocationFixer())).replace("*",
            "Replacement Class Name");

    // Assert
    assertTrue(actualReplaceResult.classNameMatcher instanceof VariableStringMatcher);
    assertEquals("*", actualReplaceResult.matchingClassName);
    assertNull(actualReplaceResult.replacementClassName);
  }

  /**
   * Method under test: {@link AbstractAPIConverter#replace(String, String)}
   */
  @Test
  void testReplace3() {
    // Arrange
    ClassPool programClassPool = new ClassPool();
    ClassPool libraryClassPool = new ClassPool();
    WarningPrinter warningPrinter = new WarningPrinter(new PrintWriter(new StringWriter()));
    ClassVisitor modifiedClassVisitor = mock(ClassVisitor.class);

    // Act
    AbstractAPIConverter.TypeReplacement actualReplaceResult = (new AbstractAPIConverter(programClassPool,
        libraryClassPool, warningPrinter, modifiedClassVisitor, new DuplicateInitializerInvocationFixer()))
            .replace("Class Name", "*");

    // Assert
    assertTrue(actualReplaceResult.classNameMatcher instanceof FixedStringMatcher);
    assertEquals("*", actualReplaceResult.replacementClassName);
    assertEquals("Class Name", actualReplaceResult.matchingClassName);
  }

  /**
   * Method under test: {@link AbstractAPIConverter#replace(String, String)}
   */
  @Test
  void testReplace4() {
    // Arrange
    ClassPool programClassPool = new ClassPool();
    ClassPool libraryClassPool = new ClassPool();
    WarningPrinter warningPrinter = new WarningPrinter(new PrintWriter(new StringWriter()));
    ClassVisitor modifiedClassVisitor = mock(ClassVisitor.class);

    // Act
    AbstractAPIConverter.TypeReplacement actualReplaceResult = (new AbstractAPIConverter(programClassPool,
        libraryClassPool, warningPrinter, modifiedClassVisitor, new DuplicateInitializerInvocationFixer()))
            .replace("Class Name", "<1>");

    // Assert
    assertTrue(actualReplaceResult.classNameMatcher instanceof FixedStringMatcher);
    assertEquals("<1>", actualReplaceResult.replacementClassName);
    assertEquals("Class Name", actualReplaceResult.matchingClassName);
  }

  /**
   * Method under test:
   * {@link AbstractAPIConverter#replace(String, String, String, String, String, String)}
   */
  @Test
  void testReplace5() {
    // Arrange
    ClassPool programClassPool = new ClassPool();
    ClassPool libraryClassPool = new ClassPool();
    WarningPrinter warningPrinter = new WarningPrinter(new PrintWriter(new StringWriter()));
    ClassVisitor modifiedClassVisitor = mock(ClassVisitor.class);

    // Act
    AbstractAPIConverter.MethodReplacement actualReplaceResult = (new AbstractAPIConverter(programClassPool,
        libraryClassPool, warningPrinter, modifiedClassVisitor, new DuplicateInitializerInvocationFixer())).replace(
            "Class Name", "Method Name", "Method Desc", "Replacement Class Name", "Replacement Method Name",
            "Replacement Method Desc");

    // Assert
    assertTrue(actualReplaceResult.classNameMatcher instanceof FixedStringMatcher);
    assertTrue(actualReplaceResult.descMatcher instanceof FixedStringMatcher);
    assertTrue(actualReplaceResult.methodNameMatcher instanceof FixedStringMatcher);
    assertEquals("Class Name", actualReplaceResult.matchingClassName);
    assertEquals("Method Desc", actualReplaceResult.matchingMethodDesc);
    assertEquals("Method Name", actualReplaceResult.matchingMethodName);
    assertNull(actualReplaceResult.replacementClassName);
    assertNull(actualReplaceResult.replacementMethodDesc);
    assertNull(actualReplaceResult.replacementMethodName);
  }

  /**
   * Method under test:
   * {@link AbstractAPIConverter#replace(String, String, String, String, String, String)}
   */
  @Test
  void testReplace6() {
    // Arrange
    ClassPool programClassPool = new ClassPool();
    ClassPool libraryClassPool = new ClassPool();
    WarningPrinter warningPrinter = new WarningPrinter(new PrintWriter(new StringWriter()));
    ClassVisitor modifiedClassVisitor = mock(ClassVisitor.class);

    // Act
    AbstractAPIConverter.MethodReplacement actualReplaceResult = (new AbstractAPIConverter(programClassPool,
        libraryClassPool, warningPrinter, modifiedClassVisitor, new DuplicateInitializerInvocationFixer())).replace(
            "**", "Method Name", "Method Desc", "Replacement Class Name", "Replacement Method Name",
            "Replacement Method Desc");

    // Assert
    assertTrue(actualReplaceResult.descMatcher instanceof FixedStringMatcher);
    assertTrue(actualReplaceResult.methodNameMatcher instanceof FixedStringMatcher);
    assertTrue(actualReplaceResult.classNameMatcher instanceof VariableStringMatcher);
    assertEquals("**", actualReplaceResult.matchingClassName);
    assertEquals("Method Desc", actualReplaceResult.matchingMethodDesc);
    assertEquals("Method Name", actualReplaceResult.matchingMethodName);
    assertNull(actualReplaceResult.replacementClassName);
    assertNull(actualReplaceResult.replacementMethodDesc);
    assertNull(actualReplaceResult.replacementMethodName);
  }

  /**
   * Method under test:
   * {@link AbstractAPIConverter#replace(String, String, String, String, String, String)}
   */
  @Test
  void testReplace7() {
    // Arrange
    ClassPool programClassPool = new ClassPool();
    ClassPool libraryClassPool = new ClassPool();
    WarningPrinter warningPrinter = new WarningPrinter(new PrintWriter(new StringWriter()));
    ClassVisitor modifiedClassVisitor = mock(ClassVisitor.class);

    // Act
    AbstractAPIConverter.MethodReplacement actualReplaceResult = (new AbstractAPIConverter(programClassPool,
        libraryClassPool, warningPrinter, modifiedClassVisitor, new DuplicateInitializerInvocationFixer())).replace("*",
            "Method Name", "Method Desc", "Replacement Class Name", "Replacement Method Name",
            "Replacement Method Desc");

    // Assert
    assertTrue(actualReplaceResult.descMatcher instanceof FixedStringMatcher);
    assertTrue(actualReplaceResult.methodNameMatcher instanceof FixedStringMatcher);
    assertTrue(actualReplaceResult.classNameMatcher instanceof VariableStringMatcher);
    assertEquals("*", actualReplaceResult.matchingClassName);
    assertEquals("Method Desc", actualReplaceResult.matchingMethodDesc);
    assertEquals("Method Name", actualReplaceResult.matchingMethodName);
    assertNull(actualReplaceResult.replacementClassName);
    assertNull(actualReplaceResult.replacementMethodDesc);
    assertNull(actualReplaceResult.replacementMethodName);
  }

  /**
   * Method under test:
   * {@link AbstractAPIConverter#replace(String, String, String, String, String, String)}
   */
  @Test
  void testReplace8() {
    // Arrange
    ClassPool programClassPool = new ClassPool();
    ClassPool libraryClassPool = new ClassPool();
    WarningPrinter warningPrinter = new WarningPrinter(new PrintWriter(new StringWriter()));
    ClassVisitor modifiedClassVisitor = mock(ClassVisitor.class);

    // Act
    AbstractAPIConverter.MethodReplacement actualReplaceResult = (new AbstractAPIConverter(programClassPool,
        libraryClassPool, warningPrinter, modifiedClassVisitor, new DuplicateInitializerInvocationFixer())).replace(
            "Class Name", "**", "Method Desc", "Replacement Class Name", "Replacement Method Name",
            "Replacement Method Desc");

    // Assert
    assertTrue(actualReplaceResult.classNameMatcher instanceof FixedStringMatcher);
    assertTrue(actualReplaceResult.descMatcher instanceof FixedStringMatcher);
    assertTrue(actualReplaceResult.methodNameMatcher instanceof VariableStringMatcher);
    assertEquals("**", actualReplaceResult.matchingMethodName);
    assertEquals("Class Name", actualReplaceResult.matchingClassName);
    assertEquals("Method Desc", actualReplaceResult.matchingMethodDesc);
    assertNull(actualReplaceResult.replacementClassName);
    assertNull(actualReplaceResult.replacementMethodDesc);
    assertNull(actualReplaceResult.replacementMethodName);
  }

  /**
   * Method under test:
   * {@link AbstractAPIConverter#replace(String, String, String, String, String, String)}
   */
  @Test
  void testReplace9() {
    // Arrange
    ClassPool programClassPool = new ClassPool();
    ClassPool libraryClassPool = new ClassPool();
    WarningPrinter warningPrinter = new WarningPrinter(new PrintWriter(new StringWriter()));
    ClassVisitor modifiedClassVisitor = mock(ClassVisitor.class);

    // Act
    AbstractAPIConverter.MethodReplacement actualReplaceResult = (new AbstractAPIConverter(programClassPool,
        libraryClassPool, warningPrinter, modifiedClassVisitor, new DuplicateInitializerInvocationFixer())).replace(
            "Class Name", "Method Name", "**", "Replacement Class Name", "Replacement Method Name",
            "Replacement Method Desc");

    // Assert
    assertTrue(actualReplaceResult.descMatcher instanceof ConstantMatcher);
    assertTrue(actualReplaceResult.classNameMatcher instanceof FixedStringMatcher);
    assertTrue(actualReplaceResult.methodNameMatcher instanceof FixedStringMatcher);
    assertEquals("**", actualReplaceResult.matchingMethodDesc);
    assertEquals("Class Name", actualReplaceResult.matchingClassName);
    assertEquals("Method Name", actualReplaceResult.matchingMethodName);
    assertNull(actualReplaceResult.replacementClassName);
    assertNull(actualReplaceResult.replacementMethodDesc);
    assertNull(actualReplaceResult.replacementMethodName);
  }

  /**
   * Method under test:
   * {@link AbstractAPIConverter#replace(String, String, String, String, String, String)}
   */
  @Test
  void testReplace10() {
    // Arrange
    ClassPool programClassPool = new ClassPool();
    ClassPool libraryClassPool = new ClassPool();
    WarningPrinter warningPrinter = new WarningPrinter(new PrintWriter(new StringWriter()));
    ClassVisitor modifiedClassVisitor = mock(ClassVisitor.class);

    // Act
    AbstractAPIConverter.MethodReplacement actualReplaceResult = (new AbstractAPIConverter(programClassPool,
        libraryClassPool, warningPrinter, modifiedClassVisitor, new DuplicateInitializerInvocationFixer())).replace(
            "Class Name", "Method Name", "Method Desc", "**", "Replacement Method Name", "Replacement Method Desc");

    // Assert
    assertTrue(actualReplaceResult.classNameMatcher instanceof FixedStringMatcher);
    assertTrue(actualReplaceResult.descMatcher instanceof FixedStringMatcher);
    assertTrue(actualReplaceResult.methodNameMatcher instanceof FixedStringMatcher);
    assertEquals("**", actualReplaceResult.replacementClassName);
    assertEquals("Class Name", actualReplaceResult.matchingClassName);
    assertEquals("Method Desc", actualReplaceResult.matchingMethodDesc);
    assertEquals("Method Name", actualReplaceResult.matchingMethodName);
    assertEquals("Replacement Method Desc", actualReplaceResult.replacementMethodDesc);
    assertEquals("Replacement Method Name", actualReplaceResult.replacementMethodName);
  }

  /**
   * Method under test:
   * {@link AbstractAPIConverter#replace(String, String, String, String, String, String)}
   */
  @Test
  void testReplace11() {
    // Arrange
    ClassPool programClassPool = new ClassPool();
    ClassPool libraryClassPool = new ClassPool();
    WarningPrinter warningPrinter = new WarningPrinter(new PrintWriter(new StringWriter()));
    ClassVisitor modifiedClassVisitor = mock(ClassVisitor.class);

    // Act
    AbstractAPIConverter.MethodReplacement actualReplaceResult = (new AbstractAPIConverter(programClassPool,
        libraryClassPool, warningPrinter, modifiedClassVisitor, new DuplicateInitializerInvocationFixer())).replace(
            "Class Name", "Method Name", "Method Desc", "<1>", "Replacement Method Name", "Replacement Method Desc");

    // Assert
    assertTrue(actualReplaceResult.classNameMatcher instanceof FixedStringMatcher);
    assertTrue(actualReplaceResult.descMatcher instanceof FixedStringMatcher);
    assertTrue(actualReplaceResult.methodNameMatcher instanceof FixedStringMatcher);
    assertEquals("<1>", actualReplaceResult.replacementClassName);
    assertEquals("Class Name", actualReplaceResult.matchingClassName);
    assertEquals("Method Desc", actualReplaceResult.matchingMethodDesc);
    assertEquals("Method Name", actualReplaceResult.matchingMethodName);
    assertEquals("Replacement Method Desc", actualReplaceResult.replacementMethodDesc);
    assertEquals("Replacement Method Name", actualReplaceResult.replacementMethodName);
  }

  /**
   * Method under test: {@link AbstractAPIConverter#missing(String)}
   */
  @Test
  void testMissing() {
    // Arrange
    ClassPool programClassPool = new ClassPool();
    ClassPool libraryClassPool = new ClassPool();
    WarningPrinter warningPrinter = new WarningPrinter(new PrintWriter(new StringWriter()));
    ClassVisitor modifiedClassVisitor = mock(ClassVisitor.class);

    // Act
    AbstractAPIConverter.TypeReplacement actualMissingResult = (new AbstractAPIConverter(programClassPool,
        libraryClassPool, warningPrinter, modifiedClassVisitor, new DuplicateInitializerInvocationFixer()))
            .missing("Class Name");

    // Assert
    assertTrue(actualMissingResult.classNameMatcher instanceof FixedStringMatcher);
    assertEquals("Class Name", actualMissingResult.matchingClassName);
    assertNull(actualMissingResult.replacementClassName);
  }

  /**
   * Method under test: {@link AbstractAPIConverter#missing(String)}
   */
  @Test
  void testMissing2() {
    // Arrange
    ClassPool programClassPool = new ClassPool();
    ClassPool libraryClassPool = new ClassPool();
    WarningPrinter warningPrinter = new WarningPrinter(new PrintWriter(new StringWriter()));
    ClassVisitor modifiedClassVisitor = mock(ClassVisitor.class);

    // Act
    AbstractAPIConverter.TypeReplacement actualMissingResult = (new AbstractAPIConverter(programClassPool,
        libraryClassPool, warningPrinter, modifiedClassVisitor, new DuplicateInitializerInvocationFixer())).missing("");

    // Assert
    assertTrue(actualMissingResult.classNameMatcher instanceof EmptyStringMatcher);
    assertEquals("", actualMissingResult.matchingClassName);
    assertNull(actualMissingResult.replacementClassName);
  }

  /**
   * Method under test:
   * {@link AbstractAPIConverter#missing(String, String, String)}
   */
  @Test
  void testMissing3() {
    // Arrange
    ClassPool programClassPool = new ClassPool();
    ClassPool libraryClassPool = new ClassPool();
    WarningPrinter warningPrinter = new WarningPrinter(new PrintWriter(new StringWriter()));
    ClassVisitor modifiedClassVisitor = mock(ClassVisitor.class);

    // Act
    AbstractAPIConverter.MethodReplacement actualMissingResult = (new AbstractAPIConverter(programClassPool,
        libraryClassPool, warningPrinter, modifiedClassVisitor, new DuplicateInitializerInvocationFixer()))
            .missing("Class Name", "Method Name", "Method Desc");

    // Assert
    assertTrue(actualMissingResult.classNameMatcher instanceof FixedStringMatcher);
    assertTrue(actualMissingResult.descMatcher instanceof FixedStringMatcher);
    assertTrue(actualMissingResult.methodNameMatcher instanceof FixedStringMatcher);
    assertEquals("Class Name", actualMissingResult.matchingClassName);
    assertEquals("Method Desc", actualMissingResult.matchingMethodDesc);
    assertEquals("Method Name", actualMissingResult.matchingMethodName);
    assertNull(actualMissingResult.replacementClassName);
    assertNull(actualMissingResult.replacementMethodDesc);
    assertNull(actualMissingResult.replacementMethodName);
  }

  /**
   * Method under test:
   * {@link AbstractAPIConverter#missing(String, String, String)}
   */
  @Test
  void testMissing4() {
    // Arrange
    ClassPool programClassPool = new ClassPool();
    ClassPool libraryClassPool = new ClassPool();
    WarningPrinter warningPrinter = new WarningPrinter(new PrintWriter(new StringWriter()));
    ClassVisitor modifiedClassVisitor = mock(ClassVisitor.class);

    // Act
    AbstractAPIConverter.MethodReplacement actualMissingResult = (new AbstractAPIConverter(programClassPool,
        libraryClassPool, warningPrinter, modifiedClassVisitor, new DuplicateInitializerInvocationFixer()))
            .missing("**", "Method Name", "Method Desc");

    // Assert
    assertTrue(actualMissingResult.descMatcher instanceof FixedStringMatcher);
    assertTrue(actualMissingResult.methodNameMatcher instanceof FixedStringMatcher);
    assertTrue(actualMissingResult.classNameMatcher instanceof VariableStringMatcher);
    assertEquals("**", actualMissingResult.matchingClassName);
    assertEquals("Method Desc", actualMissingResult.matchingMethodDesc);
    assertEquals("Method Name", actualMissingResult.matchingMethodName);
    assertNull(actualMissingResult.replacementClassName);
    assertNull(actualMissingResult.replacementMethodDesc);
    assertNull(actualMissingResult.replacementMethodName);
  }

  /**
   * Method under test:
   * {@link AbstractAPIConverter#missing(String, String, String)}
   */
  @Test
  void testMissing5() {
    // Arrange
    ClassPool programClassPool = new ClassPool();
    ClassPool libraryClassPool = new ClassPool();
    WarningPrinter warningPrinter = new WarningPrinter(new PrintWriter(new StringWriter()));
    ClassVisitor modifiedClassVisitor = mock(ClassVisitor.class);

    // Act
    AbstractAPIConverter.MethodReplacement actualMissingResult = (new AbstractAPIConverter(programClassPool,
        libraryClassPool, warningPrinter, modifiedClassVisitor, new DuplicateInitializerInvocationFixer()))
            .missing("Class Name", "**", "Method Desc");

    // Assert
    assertTrue(actualMissingResult.classNameMatcher instanceof FixedStringMatcher);
    assertTrue(actualMissingResult.descMatcher instanceof FixedStringMatcher);
    assertTrue(actualMissingResult.methodNameMatcher instanceof VariableStringMatcher);
    assertEquals("**", actualMissingResult.matchingMethodName);
    assertEquals("Class Name", actualMissingResult.matchingClassName);
    assertEquals("Method Desc", actualMissingResult.matchingMethodDesc);
    assertNull(actualMissingResult.replacementClassName);
    assertNull(actualMissingResult.replacementMethodDesc);
    assertNull(actualMissingResult.replacementMethodName);
  }

  /**
   * Method under test:
   * {@link AbstractAPIConverter#missing(String, String, String)}
   */
  @Test
  void testMissing6() {
    // Arrange
    ClassPool programClassPool = new ClassPool();
    ClassPool libraryClassPool = new ClassPool();
    WarningPrinter warningPrinter = new WarningPrinter(new PrintWriter(new StringWriter()));
    ClassVisitor modifiedClassVisitor = mock(ClassVisitor.class);

    // Act
    AbstractAPIConverter.MethodReplacement actualMissingResult = (new AbstractAPIConverter(programClassPool,
        libraryClassPool, warningPrinter, modifiedClassVisitor, new DuplicateInitializerInvocationFixer()))
            .missing("Class Name", "Method Name", "**");

    // Assert
    assertTrue(actualMissingResult.descMatcher instanceof ConstantMatcher);
    assertTrue(actualMissingResult.classNameMatcher instanceof FixedStringMatcher);
    assertTrue(actualMissingResult.methodNameMatcher instanceof FixedStringMatcher);
    assertEquals("**", actualMissingResult.matchingMethodDesc);
    assertEquals("Class Name", actualMissingResult.matchingClassName);
    assertEquals("Method Name", actualMissingResult.matchingMethodName);
    assertNull(actualMissingResult.replacementClassName);
    assertNull(actualMissingResult.replacementMethodDesc);
    assertNull(actualMissingResult.replacementMethodName);
  }

  /**
   * Method under test: {@link AbstractAPIConverter.TypeReplacement#isValid()}
   */
  @Test
  void testTypeReplacementIsValid() {
    // Arrange
    ClassPool programClassPool = new ClassPool();
    ClassPool libraryClassPool = new ClassPool();
    WarningPrinter warningPrinter = new WarningPrinter(new PrintWriter(new StringWriter()));
    ClassVisitor modifiedClassVisitor = mock(ClassVisitor.class);

    // Act and Assert
    assertFalse(((new AbstractAPIConverter(programClassPool, libraryClassPool, warningPrinter, modifiedClassVisitor,
        new DuplicateInitializerInvocationFixer())).new TypeReplacement("Matching Class Name",
            "Replacement Class Name")).isValid());
  }

  /**
   * Method under test: {@link AbstractAPIConverter.TypeReplacement#isValid()}
   */
  @Test
  void testTypeReplacementIsValid2() {
    // Arrange
    ClassPool programClassPool = new ClassPool();
    ClassPool libraryClassPool = new ClassPool();
    WarningPrinter warningPrinter = new WarningPrinter(new PrintWriter(new StringWriter()));
    ClassVisitor modifiedClassVisitor = mock(ClassVisitor.class);

    // Act and Assert
    assertTrue(((new AbstractAPIConverter(programClassPool, libraryClassPool, warningPrinter, modifiedClassVisitor,
        new DuplicateInitializerInvocationFixer())).new TypeReplacement("Matching Class Name", "*")).isValid());
  }

  /**
   * Method under test: {@link AbstractAPIConverter.TypeReplacement#isValid()}
   */
  @Test
  void testTypeReplacementIsValid3() {
    // Arrange
    ClassPool programClassPool = new ClassPool();
    ClassPool libraryClassPool = new ClassPool();
    WarningPrinter warningPrinter = new WarningPrinter(new PrintWriter(new StringWriter()));
    ClassVisitor modifiedClassVisitor = mock(ClassVisitor.class);

    // Act and Assert
    assertTrue(((new AbstractAPIConverter(programClassPool, libraryClassPool, warningPrinter, modifiedClassVisitor,
        new DuplicateInitializerInvocationFixer())).new TypeReplacement("Matching Class Name", "<1>")).isValid());
  }

  /**
   * Method under test:
   * {@link AbstractAPIConverter.TypeReplacement#matchesClassName(String)}
   */
  @Test
  void testTypeReplacementMatchesClassName() {
    // Arrange
    ClassPool programClassPool = new ClassPool();
    ClassPool libraryClassPool = new ClassPool();
    WarningPrinter warningPrinter = new WarningPrinter(new PrintWriter(new StringWriter()));
    ClassVisitor modifiedClassVisitor = mock(ClassVisitor.class);

    // Act and Assert
    assertFalse(((new AbstractAPIConverter(programClassPool, libraryClassPool, warningPrinter, modifiedClassVisitor,
        new DuplicateInitializerInvocationFixer())).new TypeReplacement("Matching Class Name",
            "Replacement Class Name")).matchesClassName("Class Name"));
  }

  /**
   * Method under test:
   * {@link AbstractAPIConverter.TypeReplacement#matchesClassName(String)}
   */
  @Test
  void testTypeReplacementMatchesClassName2() {
    // Arrange
    ClassPool programClassPool = new ClassPool();
    ClassPool libraryClassPool = new ClassPool();
    WarningPrinter warningPrinter = new WarningPrinter(new PrintWriter(new StringWriter()));
    ClassVisitor modifiedClassVisitor = mock(ClassVisitor.class);
    AbstractAPIConverter.TypeReplacement typeReplacement = (new AbstractAPIConverter(programClassPool, libraryClassPool,
        warningPrinter, modifiedClassVisitor, new DuplicateInitializerInvocationFixer())).new TypeReplacement("*",
            "Replacement Class Name");

    // Act
    boolean actualMatchesClassNameResult = typeReplacement.matchesClassName("Class Name");

    // Assert
    StringMatcher stringMatcher = typeReplacement.classNameMatcher;
    assertTrue(stringMatcher instanceof VariableStringMatcher);
    assertEquals("Class Name", ((VariableStringMatcher) stringMatcher).getMatchingString());
    assertTrue(actualMatchesClassNameResult);
  }

  /**
   * Method under test:
   * {@link AbstractAPIConverter.TypeReplacement#TypeReplacement(AbstractAPIConverter, String, String)}
   */
  @Test
  void testTypeReplacementNewTypeReplacement() {
    // Arrange
    ClassPool programClassPool = new ClassPool();
    ClassPool libraryClassPool = new ClassPool();
    WarningPrinter warningPrinter = new WarningPrinter(new PrintWriter(new StringWriter()));
    ClassVisitor modifiedClassVisitor = mock(ClassVisitor.class);

    // Act
    AbstractAPIConverter.TypeReplacement actualTypeReplacement = (new AbstractAPIConverter(programClassPool,
        libraryClassPool, warningPrinter, modifiedClassVisitor,
        new DuplicateInitializerInvocationFixer())).new TypeReplacement("Matching Class Name",
            "Replacement Class Name");

    // Assert
    assertTrue(actualTypeReplacement.classNameMatcher instanceof FixedStringMatcher);
    assertEquals("Matching Class Name", actualTypeReplacement.matchingClassName);
    assertEquals("Replacement Class Name", actualTypeReplacement.replacementClassName);
  }

  /**
   * Method under test:
   * {@link AbstractAPIConverter.TypeReplacement#TypeReplacement(AbstractAPIConverter, String, String)}
   */
  @Test
  void testTypeReplacementNewTypeReplacement2() {
    // Arrange
    ClassPool programClassPool = new ClassPool();
    ClassPool libraryClassPool = new ClassPool();
    WarningPrinter warningPrinter = new WarningPrinter(new PrintWriter(new StringWriter()));
    ClassVisitor modifiedClassVisitor = mock(ClassVisitor.class);

    // Act
    AbstractAPIConverter.TypeReplacement actualTypeReplacement = (new AbstractAPIConverter(programClassPool,
        libraryClassPool, warningPrinter, modifiedClassVisitor,
        new DuplicateInitializerInvocationFixer())).new TypeReplacement("*", "Replacement Class Name");

    // Assert
    assertTrue(actualTypeReplacement.classNameMatcher instanceof VariableStringMatcher);
    assertEquals("*", actualTypeReplacement.matchingClassName);
    assertEquals("Replacement Class Name", actualTypeReplacement.replacementClassName);
  }

  /**
   * Method under test:
   * {@link AbstractAPIConverter.TypeReplacement#replaceClassName(Clazz, String)}
   */
  @Test
  void testTypeReplacementReplaceClassName() {
    // Arrange
    ClassPool programClassPool = new ClassPool();
    ClassPool libraryClassPool = new ClassPool();
    WarningPrinter warningPrinter = new WarningPrinter(new PrintWriter(new StringWriter()));
    ClassVisitor modifiedClassVisitor = mock(ClassVisitor.class);
    AbstractAPIConverter.TypeReplacement typeReplacement = (new AbstractAPIConverter(programClassPool, libraryClassPool,
        warningPrinter, modifiedClassVisitor, new DuplicateInitializerInvocationFixer())).new TypeReplacement(
            "Matching Class Name", "Replacement Class Name");

    // Act and Assert
    assertEquals("Replacement Class Name", typeReplacement.replaceClassName(new LibraryClass(), "Class Name"));
  }

  /**
   * Method under test:
   * {@link AbstractAPIConverter#visitProgramClass(ProgramClass)}
   */
  @Test
  void testVisitProgramClass() {
    // Arrange
    ClassPool programClassPool = new ClassPool();
    ClassPool libraryClassPool = new ClassPool();
    WarningPrinter warningPrinter = new WarningPrinter(new PrintWriter(new StringWriter()));
    ClassVisitor modifiedClassVisitor = mock(ClassVisitor.class);
    AbstractAPIConverter abstractAPIConverter = new AbstractAPIConverter(programClassPool, libraryClassPool,
        warningPrinter, modifiedClassVisitor, new DuplicateInitializerInvocationFixer());
    ProgramClass programClass = mock(ProgramClass.class);
    doNothing().when(programClass).attributesAccept(Mockito.<AttributeVisitor>any());
    doNothing().when(programClass).constantPoolEntriesAccept(Mockito.<ConstantVisitor>any());
    doNothing().when(programClass).fieldsAccept(Mockito.<MemberVisitor>any());
    doNothing().when(programClass).methodsAccept(Mockito.<MemberVisitor>any());

    // Act
    abstractAPIConverter.visitProgramClass(programClass);

    // Assert
    verify(programClass).attributesAccept(isA(AttributeVisitor.class));
    verify(programClass).constantPoolEntriesAccept(isA(ConstantVisitor.class));
    verify(programClass).fieldsAccept(isA(MemberVisitor.class));
    verify(programClass, atLeast(1)).methodsAccept(Mockito.<MemberVisitor>any());
  }

  /**
   * Method under test:
   * {@link AbstractAPIConverter#visitProgramField(ProgramClass, ProgramField)}
   */
  @Test
  void testVisitProgramField() {
    // Arrange
    ClassPool programClassPool = new ClassPool();
    ClassPool libraryClassPool = new ClassPool();
    WarningPrinter warningPrinter = new WarningPrinter(new PrintWriter(new StringWriter()));
    ClassVisitor modifiedClassVisitor = mock(ClassVisitor.class);
    AbstractAPIConverter abstractAPIConverter = new AbstractAPIConverter(programClassPool, libraryClassPool,
        warningPrinter, modifiedClassVisitor, new DuplicateInitializerInvocationFixer());
    ProgramClass programClass = mock(ProgramClass.class);
    when(programClass.getString(anyInt())).thenReturn("String");

    // Act
    abstractAPIConverter.visitProgramField(programClass, new ProgramField());

    // Assert
    verify(programClass).getString(eq(0));
  }

  /**
   * Method under test:
   * {@link AbstractAPIConverter#visitProgramField(ProgramClass, ProgramField)}
   */
  @Test
  void testVisitProgramField2() {
    // Arrange
    ClassPool programClassPool = new ClassPool();
    ClassPool libraryClassPool = new ClassPool();
    WarningPrinter warningPrinter = new WarningPrinter(new PrintWriter(new StringWriter()));
    ClassVisitor modifiedClassVisitor = mock(ClassVisitor.class);
    AbstractAPIConverter abstractAPIConverter = new AbstractAPIConverter(programClassPool, libraryClassPool,
        warningPrinter, modifiedClassVisitor, new DuplicateInitializerInvocationFixer());
    ProgramClass programClass = mock(ProgramClass.class);
    when(programClass.getString(anyInt())).thenReturn("String");

    // Act
    abstractAPIConverter.visitProgramField(programClass, new NamedField("String", "String"));

    // Assert
    verify(programClass).getString(eq(0));
  }

  /**
   * Method under test:
   * {@link AbstractAPIConverter#visitProgramMethod(ProgramClass, ProgramMethod)}
   */
  @Test
  void testVisitProgramMethod() {
    // Arrange
    ClassPool programClassPool = new ClassPool();
    ClassPool libraryClassPool = new ClassPool();
    WarningPrinter warningPrinter = new WarningPrinter(new PrintWriter(new StringWriter()));
    ClassVisitor modifiedClassVisitor = mock(ClassVisitor.class);
    AbstractAPIConverter abstractAPIConverter = new AbstractAPIConverter(programClassPool, libraryClassPool,
        warningPrinter, modifiedClassVisitor, new DuplicateInitializerInvocationFixer());
    ProgramClass programClass = mock(ProgramClass.class);
    when(programClass.getString(anyInt())).thenReturn("String");

    // Act
    abstractAPIConverter.visitProgramMethod(programClass, new ProgramMethod());

    // Assert
    verify(programClass).getString(eq(0));
  }

  /**
   * Method under test:
   * {@link AbstractAPIConverter#visitProgramMethod(ProgramClass, ProgramMethod)}
   */
  @Test
  void testVisitProgramMethod2() {
    // Arrange
    ClassPool programClassPool = new ClassPool();
    ClassPool libraryClassPool = new ClassPool();
    WarningPrinter warningPrinter = new WarningPrinter(new PrintWriter(new StringWriter()));
    ClassVisitor modifiedClassVisitor = mock(ClassVisitor.class);
    AbstractAPIConverter abstractAPIConverter = new AbstractAPIConverter(programClassPool, libraryClassPool,
        warningPrinter, modifiedClassVisitor, new DuplicateInitializerInvocationFixer());
    ProgramClass programClass = mock(ProgramClass.class);
    when(programClass.getString(anyInt())).thenReturn("String");

    // Act
    abstractAPIConverter.visitProgramMethod(programClass, new NamedMember("String", "String"));

    // Assert
    verify(programClass).getString(eq(0));
  }

  /**
   * Method under test:
   * {@link AbstractAPIConverter#visitCodeAttribute(Clazz, Method, CodeAttribute)}
   */
  @Test
  void testVisitCodeAttribute() {
    // Arrange
    ClassPool programClassPool = new ClassPool();
    ClassPool libraryClassPool = new ClassPool();
    WarningPrinter warningPrinter = new WarningPrinter(new PrintWriter(new StringWriter()));
    ClassVisitor modifiedClassVisitor = mock(ClassVisitor.class);
    AbstractAPIConverter abstractAPIConverter = new AbstractAPIConverter(programClassPool, libraryClassPool,
        warningPrinter, modifiedClassVisitor, new DuplicateInitializerInvocationFixer());
    LibraryClass clazz = new LibraryClass();
    LibraryMethod method = new LibraryMethod(1, "Name", "Descriptor");

    CodeAttribute codeAttribute = mock(CodeAttribute.class);
    doNothing().when(codeAttribute)
        .attributesAccept(Mockito.<Clazz>any(), Mockito.<Method>any(), Mockito.<AttributeVisitor>any());
    doNothing().when(codeAttribute)
        .instructionsAccept(Mockito.<Clazz>any(), Mockito.<Method>any(), Mockito.<InstructionVisitor>any());

    // Act
    abstractAPIConverter.visitCodeAttribute(clazz, method, codeAttribute);

    // Assert
    verify(codeAttribute).attributesAccept(isA(Clazz.class), isA(Method.class), isA(AttributeVisitor.class));
    verify(codeAttribute).instructionsAccept(isA(Clazz.class), isA(Method.class), isA(InstructionVisitor.class));
  }

  /**
   * Method under test:
   * {@link AbstractAPIConverter#visitSignatureAttribute(Clazz, SignatureAttribute)}
   */
  @Test
  void testVisitSignatureAttribute() {
    // Arrange
    ClassPool programClassPool = new ClassPool();
    ClassPool libraryClassPool = new ClassPool();
    WarningPrinter warningPrinter = new WarningPrinter(new PrintWriter(new StringWriter()));
    ClassVisitor modifiedClassVisitor = mock(ClassVisitor.class);
    AbstractAPIConverter abstractAPIConverter = new AbstractAPIConverter(programClassPool, libraryClassPool,
        warningPrinter, modifiedClassVisitor, new DuplicateInitializerInvocationFixer());
    LibraryClass clazz = mock(LibraryClass.class);
    when(clazz.getString(anyInt())).thenReturn("String");

    // Act
    abstractAPIConverter.visitSignatureAttribute(clazz, new SignatureAttribute(1, 1));

    // Assert
    verify(clazz).getString(eq(1));
  }

  /**
   * Method under test:
   * {@link AbstractAPIConverter#visitAnnotationDefaultAttribute(Clazz, Method, AnnotationDefaultAttribute)}
   */
  @Test
  void testVisitAnnotationDefaultAttribute() {
    // Arrange
    ClassPool programClassPool = new ClassPool();
    ClassPool libraryClassPool = new ClassPool();
    WarningPrinter warningPrinter = new WarningPrinter(new PrintWriter(new StringWriter()));
    ClassVisitor modifiedClassVisitor = mock(ClassVisitor.class);
    AbstractAPIConverter abstractAPIConverter = new AbstractAPIConverter(programClassPool, libraryClassPool,
        warningPrinter, modifiedClassVisitor, new DuplicateInitializerInvocationFixer());
    LibraryClass clazz = new LibraryClass();
    LibraryMethod method = new LibraryMethod(1, "Name", "Descriptor");

    AnnotationDefaultAttribute annotationDefaultAttribute = mock(AnnotationDefaultAttribute.class);
    doNothing().when(annotationDefaultAttribute)
        .defaultValueAccept(Mockito.<Clazz>any(), Mockito.<ElementValueVisitor>any());

    // Act
    abstractAPIConverter.visitAnnotationDefaultAttribute(clazz, method, annotationDefaultAttribute);

    // Assert
    verify(annotationDefaultAttribute).defaultValueAccept(isA(Clazz.class), isA(ElementValueVisitor.class));
  }

  /**
   * Method under test:
   * {@link AbstractAPIConverter#visitLocalVariableInfo(Clazz, Method, CodeAttribute, LocalVariableInfo)}
   */
  @Test
  void testVisitLocalVariableInfo() {
    // Arrange
    ClassPool programClassPool = new ClassPool();
    ClassPool libraryClassPool = new ClassPool();
    WarningPrinter warningPrinter = new WarningPrinter(new PrintWriter(new StringWriter()));
    ClassVisitor modifiedClassVisitor = mock(ClassVisitor.class);
    AbstractAPIConverter abstractAPIConverter = new AbstractAPIConverter(programClassPool, libraryClassPool,
        warningPrinter, modifiedClassVisitor, new DuplicateInitializerInvocationFixer());
    LibraryClass clazz = mock(LibraryClass.class);
    when(clazz.getString(anyInt())).thenReturn("String");
    LibraryMethod method = new LibraryMethod(1, "Name", "Descriptor");

    CodeAttribute codeAttribute = new CodeAttribute(1);

    // Act
    abstractAPIConverter.visitLocalVariableInfo(clazz, method, codeAttribute, new LocalVariableInfo(1, 3, 1, 1, 1));

    // Assert
    verify(clazz).getString(eq(1));
  }

  /**
   * Method under test:
   * {@link AbstractAPIConverter#visitLocalVariableTypeInfo(Clazz, Method, CodeAttribute, LocalVariableTypeInfo)}
   */
  @Test
  void testVisitLocalVariableTypeInfo() {
    // Arrange
    ClassPool programClassPool = new ClassPool();
    ClassPool libraryClassPool = new ClassPool();
    WarningPrinter warningPrinter = new WarningPrinter(new PrintWriter(new StringWriter()));
    ClassVisitor modifiedClassVisitor = mock(ClassVisitor.class);
    AbstractAPIConverter abstractAPIConverter = new AbstractAPIConverter(programClassPool, libraryClassPool,
        warningPrinter, modifiedClassVisitor, new DuplicateInitializerInvocationFixer());
    LibraryClass clazz = mock(LibraryClass.class);
    when(clazz.getString(anyInt())).thenReturn("String");
    LibraryMethod method = new LibraryMethod(1, "Name", "Descriptor");

    CodeAttribute codeAttribute = new CodeAttribute(1);

    // Act
    abstractAPIConverter.visitLocalVariableTypeInfo(clazz, method, codeAttribute,
        new LocalVariableTypeInfo(1, 3, 1, 1, 1));

    // Assert
    verify(clazz).getString(eq(1));
  }

  /**
   * Method under test:
   * {@link AbstractAPIConverter#visitAnnotation(Clazz, Annotation)}
   */
  @Test
  void testVisitAnnotation() {
    // Arrange
    ClassPool programClassPool = new ClassPool();
    ClassPool libraryClassPool = new ClassPool();
    WarningPrinter warningPrinter = new WarningPrinter(new PrintWriter(new StringWriter()));
    ClassVisitor modifiedClassVisitor = mock(ClassVisitor.class);
    AbstractAPIConverter abstractAPIConverter = new AbstractAPIConverter(programClassPool, libraryClassPool,
        warningPrinter, modifiedClassVisitor, new DuplicateInitializerInvocationFixer());
    LibraryClass clazz = mock(LibraryClass.class);
    when(clazz.getString(anyInt())).thenReturn("String");

    // Act
    abstractAPIConverter.visitAnnotation(clazz, new Annotation());

    // Assert
    verify(clazz).getString(eq(0));
  }

  /**
   * Method under test:
   * {@link AbstractAPIConverter#visitAnnotation(Clazz, Annotation)}
   */
  @Test
  void testVisitAnnotation2() {
    // Arrange
    ClassPool programClassPool = new ClassPool();
    ClassPool libraryClassPool = new ClassPool();
    WarningPrinter warningPrinter = new WarningPrinter(new PrintWriter(new StringWriter()));
    ClassVisitor modifiedClassVisitor = mock(ClassVisitor.class);
    AbstractAPIConverter abstractAPIConverter = new AbstractAPIConverter(programClassPool, libraryClassPool,
        warningPrinter, modifiedClassVisitor, new DuplicateInitializerInvocationFixer());
    LibraryClass clazz = mock(LibraryClass.class);
    when(clazz.getString(anyInt())).thenReturn("String");
    Annotation annotation = mock(Annotation.class);
    doNothing().when(annotation).elementValuesAccept(Mockito.<Clazz>any(), Mockito.<ElementValueVisitor>any());

    // Act
    abstractAPIConverter.visitAnnotation(clazz, annotation);

    // Assert
    verify(clazz).getString(eq(0));
    verify(annotation).elementValuesAccept(isA(Clazz.class), isA(ElementValueVisitor.class));
  }

  /**
   * Method under test:
   * {@link AbstractAPIConverter#visitEnumConstantElementValue(Clazz, Annotation, EnumConstantElementValue)}
   */
  @Test
  void testVisitEnumConstantElementValue() {
    // Arrange
    ClassPool programClassPool = new ClassPool();
    ClassPool libraryClassPool = new ClassPool();
    WarningPrinter warningPrinter = new WarningPrinter(new PrintWriter(new StringWriter()));
    ClassVisitor modifiedClassVisitor = mock(ClassVisitor.class);
    AbstractAPIConverter abstractAPIConverter = new AbstractAPIConverter(programClassPool, libraryClassPool,
        warningPrinter, modifiedClassVisitor, new DuplicateInitializerInvocationFixer());
    LibraryClass clazz = mock(LibraryClass.class);
    when(clazz.getString(anyInt())).thenReturn("String");
    Annotation annotation = new Annotation();

    // Act
    abstractAPIConverter.visitEnumConstantElementValue(clazz, annotation, new EnumConstantElementValue(1, 1, 1));

    // Assert
    verify(clazz).getString(eq(1));
  }

  /**
   * Method under test:
   * {@link AbstractAPIConverter#visitClassElementValue(Clazz, Annotation, ClassElementValue)}
   */
  @Test
  void testVisitClassElementValue() {
    // Arrange
    ClassPool programClassPool = new ClassPool();
    ClassPool libraryClassPool = new ClassPool();
    WarningPrinter warningPrinter = new WarningPrinter(new PrintWriter(new StringWriter()));
    ClassVisitor modifiedClassVisitor = mock(ClassVisitor.class);
    JSR310Converter jsr310Converter = new JSR310Converter(programClassPool, libraryClassPool, warningPrinter,
        modifiedClassVisitor, new DuplicateInitializerInvocationFixer());
    LibraryClass libraryClass = mock(LibraryClass.class);
    when(libraryClass.getString(anyInt())).thenReturn("String");
    Annotation annotation = new Annotation();

    // Act
    jsr310Converter.visitClassElementValue(libraryClass, annotation, new ClassElementValue(1, 1));

    // Assert
    verify(libraryClass).getString(eq(1));
  }

  /**
   * Method under test:
   * {@link AbstractAPIConverter#visitAnnotationElementValue(Clazz, Annotation, AnnotationElementValue)}
   */
  @Test
  void testVisitAnnotationElementValue() {
    // Arrange
    ClassPool programClassPool = new ClassPool();
    ClassPool libraryClassPool = new ClassPool();
    WarningPrinter warningPrinter = new WarningPrinter(new PrintWriter(new StringWriter()));
    ClassVisitor modifiedClassVisitor = mock(ClassVisitor.class);
    AbstractAPIConverter abstractAPIConverter = new AbstractAPIConverter(programClassPool, libraryClassPool,
        warningPrinter, modifiedClassVisitor, new DuplicateInitializerInvocationFixer());
    LibraryClass clazz = new LibraryClass();
    Annotation annotation = new Annotation();
    AnnotationElementValue annotationElementValue = mock(AnnotationElementValue.class);
    doNothing().when(annotationElementValue).annotationAccept(Mockito.<Clazz>any(), Mockito.<AnnotationVisitor>any());

    // Act
    abstractAPIConverter.visitAnnotationElementValue(clazz, annotation, annotationElementValue);

    // Assert
    verify(annotationElementValue).annotationAccept(isA(Clazz.class), isA(AnnotationVisitor.class));
  }

  /**
   * Method under test:
   * {@link AbstractAPIConverter#visitClassConstant(Clazz, ClassConstant)}
   */
  @Test
  void testVisitClassConstant() {
    // Arrange
    ClassPool programClassPool = new ClassPool();
    ClassPool libraryClassPool = new ClassPool();
    WarningPrinter warningPrinter = new WarningPrinter(new PrintWriter(new StringWriter()));
    ClassVisitor modifiedClassVisitor = mock(ClassVisitor.class);
    JSR310Converter jsr310Converter = new JSR310Converter(programClassPool, libraryClassPool, warningPrinter,
        modifiedClassVisitor, new DuplicateInitializerInvocationFixer());
    LibraryClass libraryClass = mock(LibraryClass.class);
    when(libraryClass.getString(anyInt())).thenReturn("String");

    // Act
    jsr310Converter.visitClassConstant(libraryClass, new ClassConstant());

    // Assert
    verify(libraryClass).getString(eq(0));
  }

  /**
   * Method under test:
   * {@link AbstractAPIConverter#visitFieldrefConstant(Clazz, FieldrefConstant)}
   */
  @Test
  void testVisitFieldrefConstant() {
    // Arrange
    ClassPool programClassPool = new ClassPool();
    ClassPool libraryClassPool = new ClassPool();
    WarningPrinter warningPrinter = new WarningPrinter(new PrintWriter(new StringWriter()));
    ClassVisitor modifiedClassVisitor = mock(ClassVisitor.class);
    AbstractAPIConverter abstractAPIConverter = new AbstractAPIConverter(programClassPool, libraryClassPool,
        warningPrinter, modifiedClassVisitor, new DuplicateInitializerInvocationFixer());
    LibraryClass clazz = mock(LibraryClass.class);
    when(clazz.getName(anyInt())).thenReturn("Name");
    when(clazz.getType(anyInt())).thenReturn("foo");

    // Act
    abstractAPIConverter.visitFieldrefConstant(clazz, new FieldrefConstant());

    // Assert
    verify(clazz).getName(eq(0));
    verify(clazz).getType(eq(0));
  }

  /**
   * Method under test:
   * {@link AbstractAPIConverter#visitAnyMethodrefConstant(Clazz, AnyMethodrefConstant)}
   */
  @Test
  void testVisitAnyMethodrefConstant() {
    // Arrange
    ClassPool programClassPool = new ClassPool();
    ClassPool libraryClassPool = new ClassPool();
    WarningPrinter warningPrinter = new WarningPrinter(new PrintWriter(new StringWriter()));
    ClassVisitor modifiedClassVisitor = mock(ClassVisitor.class);
    JSR310Converter jsr310Converter = new JSR310Converter(programClassPool, libraryClassPool, warningPrinter,
        modifiedClassVisitor, new DuplicateInitializerInvocationFixer());
    LibraryClass libraryClass = mock(LibraryClass.class);
    when(libraryClass.getClassName(anyInt())).thenReturn("Class Name");
    when(libraryClass.getName(anyInt())).thenReturn("Name");
    when(libraryClass.getType(anyInt())).thenReturn("foo");

    // Act
    jsr310Converter.visitAnyMethodrefConstant(libraryClass, new InterfaceMethodrefConstant());

    // Assert
    verify(libraryClass).getClassName(eq(0));
    verify(libraryClass, atLeast(1)).getName(eq(0));
    verify(libraryClass, atLeast(1)).getType(eq(0));
  }

  /**
   * Method under test:
   * {@link AbstractAPIConverter#AbstractAPIConverter(ClassPool, ClassPool, WarningPrinter, ClassVisitor, InstructionVisitor)}
   */
  @Test
  void testNewAbstractAPIConverter() {
    // Arrange
    ClassPool programClassPool = new ClassPool();
    ClassPool libraryClassPool = new ClassPool();
    WarningPrinter warningPrinter = new WarningPrinter(new PrintWriter(new StringWriter()));
    ClassVisitor modifiedClassVisitor = mock(ClassVisitor.class);

    // Act and Assert
    AbstractAPIConverter.TypeReplacement missingResult = (new AbstractAPIConverter(programClassPool, libraryClassPool,
        warningPrinter, modifiedClassVisitor, new DuplicateInitializerInvocationFixer())).missing("Class Name");
    assertTrue(missingResult.classNameMatcher instanceof FixedStringMatcher);
    assertEquals("Class Name", missingResult.matchingClassName);
    assertNull(missingResult.replacementClassName);
  }
}
