package proguard.backport;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.anyInt;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.io.PrintWriter;
import java.io.StringWriter;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import proguard.backport.AbstractAPIConverter.MethodReplacement;
import proguard.backport.AbstractAPIConverter.TypeReplacement;
import proguard.classfile.ClassPool;
import proguard.classfile.Clazz;
import proguard.classfile.LibraryClass;
import proguard.classfile.LibraryMethod;
import proguard.classfile.Method;
import proguard.classfile.ProgramClass;
import proguard.classfile.ProgramMethod;
import proguard.classfile.attribute.Attribute;
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
import proguard.classfile.instruction.visitor.InstructionVisitor;
import proguard.classfile.util.WarningPrinter;
import proguard.classfile.visitor.ClassVisitor;
import proguard.optimize.DuplicateInitializerInvocationFixer;
import proguard.testutils.cpa.NamedMember;
import proguard.util.ConstantMatcher;
import proguard.util.EmptyStringMatcher;
import proguard.util.FixedStringMatcher;
import proguard.util.StringMatcher;
import proguard.util.VariableStringMatcher;

class AbstractAPIConverterDiffblueTest {
  /**
   * Test MethodReplacement {@link MethodReplacement#MethodReplacement(AbstractAPIConverter, String,
   * String, String, String, String, String)}.
   *
   * <p>Method under test: {@link MethodReplacement#MethodReplacement(AbstractAPIConverter, String,
   * String, String, String, String, String)}
   */
  @Test
  @DisplayName(
      "Test MethodReplacement new MethodReplacement(AbstractAPIConverter, String, String, String, String, String, String)")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void MethodReplacement.<init>(AbstractAPIConverter, String, String, String, String, String, String)"
  })
  void testMethodReplacementNewMethodReplacement() {
    // Arrange
    ClassPool programClassPool = new ClassPool();
    ClassPool libraryClassPool = new ClassPool();
    WarningPrinter warningPrinter = new WarningPrinter(new PrintWriter(new StringWriter()));
    ClassVisitor modifiedClassVisitor = mock(ClassVisitor.class);

    // Act
    MethodReplacement actualMethodReplacement =
        new AbstractAPIConverter(
            programClassPool,
            libraryClassPool,
            warningPrinter,
            modifiedClassVisitor,
            new DuplicateInitializerInvocationFixer())
        .new MethodReplacement(
            "Class Name",
            "Method Name",
            "Method Desc",
            "Replacement Class Name",
            "Replacement Method Name",
            "Replacement Method Desc");

    // Assert
    assertTrue(actualMethodReplacement.classNameMatcher instanceof FixedStringMatcher);
    assertTrue(actualMethodReplacement.descMatcher instanceof FixedStringMatcher);
    assertTrue(actualMethodReplacement.methodNameMatcher instanceof FixedStringMatcher);
    assertEquals("Class Name", actualMethodReplacement.matchingClassName);
    assertEquals("Method Desc", actualMethodReplacement.matchingMethodDesc);
    assertEquals("Method Name", actualMethodReplacement.matchingMethodName);
  }

  /**
   * Test MethodReplacement {@link MethodReplacement#MethodReplacement(AbstractAPIConverter, String,
   * String, String, String, String, String)}.
   *
   * <p>Method under test: {@link MethodReplacement#MethodReplacement(AbstractAPIConverter, String,
   * String, String, String, String, String)}
   */
  @Test
  @DisplayName(
      "Test MethodReplacement new MethodReplacement(AbstractAPIConverter, String, String, String, String, String, String)")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void MethodReplacement.<init>(AbstractAPIConverter, String, String, String, String, String, String)"
  })
  void testMethodReplacementNewMethodReplacement2() {
    // Arrange
    ClassPool programClassPool = new ClassPool();
    ClassPool libraryClassPool = new ClassPool();
    WarningPrinter warningPrinter = new WarningPrinter(new PrintWriter(new StringWriter()));
    ClassVisitor modifiedClassVisitor = mock(ClassVisitor.class);

    // Act
    MethodReplacement actualMethodReplacement =
        new AbstractAPIConverter(
            programClassPool,
            libraryClassPool,
            warningPrinter,
            modifiedClassVisitor,
            new DuplicateInitializerInvocationFixer())
        .new MethodReplacement(
            "**",
            "Method Name",
            "Method Desc",
            "Replacement Class Name",
            "Replacement Method Name",
            "Replacement Method Desc");

    // Assert
    assertTrue(actualMethodReplacement.descMatcher instanceof FixedStringMatcher);
    assertTrue(actualMethodReplacement.methodNameMatcher instanceof FixedStringMatcher);
    assertTrue(actualMethodReplacement.classNameMatcher instanceof VariableStringMatcher);
    assertEquals("**", actualMethodReplacement.matchingClassName);
    assertEquals("Method Desc", actualMethodReplacement.matchingMethodDesc);
    assertEquals("Method Name", actualMethodReplacement.matchingMethodName);
  }

  /**
   * Test MethodReplacement {@link MethodReplacement#MethodReplacement(AbstractAPIConverter, String,
   * String, String, String, String, String)}.
   *
   * <p>Method under test: {@link MethodReplacement#MethodReplacement(AbstractAPIConverter, String,
   * String, String, String, String, String)}
   */
  @Test
  @DisplayName(
      "Test MethodReplacement new MethodReplacement(AbstractAPIConverter, String, String, String, String, String, String)")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void MethodReplacement.<init>(AbstractAPIConverter, String, String, String, String, String, String)"
  })
  void testMethodReplacementNewMethodReplacement3() {
    // Arrange
    ClassPool programClassPool = new ClassPool();
    ClassPool libraryClassPool = new ClassPool();
    WarningPrinter warningPrinter = new WarningPrinter(new PrintWriter(new StringWriter()));
    ClassVisitor modifiedClassVisitor = mock(ClassVisitor.class);

    // Act
    MethodReplacement actualMethodReplacement =
        new AbstractAPIConverter(
            programClassPool,
            libraryClassPool,
            warningPrinter,
            modifiedClassVisitor,
            new DuplicateInitializerInvocationFixer())
        .new MethodReplacement(
            "Class Name",
            "**",
            "Method Desc",
            "Replacement Class Name",
            "Replacement Method Name",
            "Replacement Method Desc");

    // Assert
    assertTrue(actualMethodReplacement.classNameMatcher instanceof FixedStringMatcher);
    assertTrue(actualMethodReplacement.methodNameMatcher instanceof VariableStringMatcher);
    assertEquals("**", actualMethodReplacement.matchingMethodName);
    assertEquals("Class Name", actualMethodReplacement.matchingClassName);
  }

  /**
   * Test MethodReplacement {@link MethodReplacement#MethodReplacement(AbstractAPIConverter, String,
   * String, String, String, String, String)}.
   *
   * <ul>
   *   <li>Then {@link MethodReplacement#descMatcher} return {@link ConstantMatcher}.
   * </ul>
   *
   * <p>Method under test: {@link MethodReplacement#MethodReplacement(AbstractAPIConverter, String,
   * String, String, String, String, String)}
   */
  @Test
  @DisplayName(
      "Test MethodReplacement new MethodReplacement(AbstractAPIConverter, String, String, String, String, String, String); then descMatcher return ConstantMatcher")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void MethodReplacement.<init>(AbstractAPIConverter, String, String, String, String, String, String)"
  })
  void testMethodReplacementNewMethodReplacement_thenDescMatcherReturnConstantMatcher() {
    // Arrange
    ClassPool programClassPool = new ClassPool();
    ClassPool libraryClassPool = new ClassPool();
    WarningPrinter warningPrinter = new WarningPrinter(new PrintWriter(new StringWriter()));
    ClassVisitor modifiedClassVisitor = mock(ClassVisitor.class);

    // Act
    MethodReplacement actualMethodReplacement =
        new AbstractAPIConverter(
            programClassPool,
            libraryClassPool,
            warningPrinter,
            modifiedClassVisitor,
            new DuplicateInitializerInvocationFixer())
        .new MethodReplacement(
            "Class Name",
            "Method Name",
            "**",
            "Replacement Class Name",
            "Replacement Method Name",
            "Replacement Method Desc");

    // Assert
    assertTrue(actualMethodReplacement.descMatcher instanceof ConstantMatcher);
    assertTrue(actualMethodReplacement.classNameMatcher instanceof FixedStringMatcher);
    assertEquals("**", actualMethodReplacement.matchingMethodDesc);
    assertEquals("Class Name", actualMethodReplacement.matchingClassName);
  }

  /**
   * Test MethodReplacement {@link MethodReplacement#MethodReplacement(AbstractAPIConverter, String,
   * String, String, String, String, String)}.
   *
   * <ul>
   *   <li>Then return {@link MethodReplacement#matchingClassName} is {@code *}.
   * </ul>
   *
   * <p>Method under test: {@link MethodReplacement#MethodReplacement(AbstractAPIConverter, String,
   * String, String, String, String, String)}
   */
  @Test
  @DisplayName(
      "Test MethodReplacement new MethodReplacement(AbstractAPIConverter, String, String, String, String, String, String); then return matchingClassName is '*'")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void MethodReplacement.<init>(AbstractAPIConverter, String, String, String, String, String, String)"
  })
  void testMethodReplacementNewMethodReplacement_thenReturnMatchingClassNameIsAsterisk() {
    // Arrange
    ClassPool programClassPool = new ClassPool();
    ClassPool libraryClassPool = new ClassPool();
    WarningPrinter warningPrinter = new WarningPrinter(new PrintWriter(new StringWriter()));
    ClassVisitor modifiedClassVisitor = mock(ClassVisitor.class);

    // Act
    MethodReplacement actualMethodReplacement =
        new AbstractAPIConverter(
            programClassPool,
            libraryClassPool,
            warningPrinter,
            modifiedClassVisitor,
            new DuplicateInitializerInvocationFixer())
        .new MethodReplacement(
            "*",
            "Method Name",
            "Method Desc",
            "Replacement Class Name",
            "Replacement Method Name",
            "Replacement Method Desc");

    // Assert
    assertTrue(actualMethodReplacement.descMatcher instanceof FixedStringMatcher);
    assertTrue(actualMethodReplacement.methodNameMatcher instanceof FixedStringMatcher);
    assertTrue(actualMethodReplacement.classNameMatcher instanceof VariableStringMatcher);
    assertEquals("*", actualMethodReplacement.matchingClassName);
    assertEquals("Method Desc", actualMethodReplacement.matchingMethodDesc);
    assertEquals("Method Name", actualMethodReplacement.matchingMethodName);
  }

  /**
   * Test MethodReplacement {@link MethodReplacement#replaceInstruction(int, Clazz, Method,
   * AnyMethodrefConstant)}.
   *
   * <ul>
   *   <li>Then calls {@link LibraryClass#getClassName(int)}.
   * </ul>
   *
   * <p>Method under test: {@link MethodReplacement#replaceInstruction(int, Clazz, Method,
   * AnyMethodrefConstant)}
   */
  @Test
  @DisplayName(
      "Test MethodReplacement replaceInstruction(int, Clazz, Method, AnyMethodrefConstant); then calls getClassName(int)")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void MethodReplacement.replaceInstruction(int, Clazz, Method, AnyMethodrefConstant)"
  })
  void testMethodReplacementReplaceInstruction_thenCallsGetClassName() {
    // Arrange
    ClassPool programClassPool = new ClassPool();
    ClassPool libraryClassPool = new ClassPool();
    WarningPrinter warningPrinter = new WarningPrinter(new PrintWriter(new StringWriter()));
    ClassVisitor modifiedClassVisitor = mock(ClassVisitor.class);
    MethodReplacement methodReplacement =
        new AbstractAPIConverter(
            programClassPool,
            libraryClassPool,
            warningPrinter,
            modifiedClassVisitor,
            new DuplicateInitializerInvocationFixer())
        .new MethodReplacement(
            "Class Name",
            "Method Name",
            "Method Desc",
            "Replacement Class Name",
            "Replacement Method Name",
            "Replacement Method Desc");
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
   * Test {@link AbstractAPIConverter#AbstractAPIConverter(ClassPool, ClassPool, WarningPrinter,
   * ClassVisitor, InstructionVisitor)}.
   *
   * <p>Method under test: {@link AbstractAPIConverter#AbstractAPIConverter(ClassPool, ClassPool,
   * WarningPrinter, ClassVisitor, InstructionVisitor)}
   */
  @Test
  @DisplayName(
      "Test new AbstractAPIConverter(ClassPool, ClassPool, WarningPrinter, ClassVisitor, InstructionVisitor)")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void AbstractAPIConverter.<init>(ClassPool, ClassPool, WarningPrinter, ClassVisitor, InstructionVisitor)"
  })
  void testNewAbstractAPIConverter() {
    // Arrange
    ClassPool programClassPool = new ClassPool();
    ClassPool libraryClassPool = new ClassPool();
    WarningPrinter warningPrinter = new WarningPrinter(new PrintWriter(new StringWriter()));
    ClassVisitor modifiedClassVisitor = mock(ClassVisitor.class);

    // Act and Assert
    TypeReplacement missingResult =
        new AbstractAPIConverter(
                programClassPool,
                libraryClassPool,
                warningPrinter,
                modifiedClassVisitor,
                new DuplicateInitializerInvocationFixer())
            .missing("Class Name");
    assertTrue(missingResult.classNameMatcher instanceof FixedStringMatcher);
    assertEquals("Class Name", missingResult.matchingClassName);
    assertNull(missingResult.replacementClassName);
  }

  /**
   * Test {@link AbstractAPIConverter#replace(String, String, String, String, String, String)} with
   * {@code className}, {@code methodName}, {@code methodDesc}, {@code replacementClassName}, {@code
   * replacementMethodName}, {@code replacementMethodDesc}.
   *
   * <p>Method under test: {@link AbstractAPIConverter#replace(String, String, String, String,
   * String, String)}
   */
  @Test
  @DisplayName(
      "Test replace(String, String, String, String, String, String) with 'className', 'methodName', 'methodDesc', 'replacementClassName', 'replacementMethodName', 'replacementMethodDesc'")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({
    "MethodReplacement AbstractAPIConverter.replace(String, String, String, String, String, String)"
  })
  void
      testReplaceWithClassNameMethodNameMethodDescReplacementClassNameReplacementMethodNameReplacementMethodDesc() {
    // Arrange
    ClassPool programClassPool = new ClassPool();
    ClassPool libraryClassPool = new ClassPool();
    WarningPrinter warningPrinter = new WarningPrinter(new PrintWriter(new StringWriter()));
    ClassVisitor modifiedClassVisitor = mock(ClassVisitor.class);

    // Act
    MethodReplacement actualReplaceResult =
        new AbstractAPIConverter(
                programClassPool,
                libraryClassPool,
                warningPrinter,
                modifiedClassVisitor,
                new DuplicateInitializerInvocationFixer())
            .replace(
                "Class Name",
                "Method Name",
                "Method Desc",
                "Replacement Class Name",
                "Replacement Method Name",
                "Replacement Method Desc");

    // Assert
    assertTrue(actualReplaceResult.classNameMatcher instanceof FixedStringMatcher);
    assertEquals("Class Name", actualReplaceResult.matchingClassName);
    assertNull(actualReplaceResult.replacementClassName);
    assertNull(actualReplaceResult.replacementMethodDesc);
    assertNull(actualReplaceResult.replacementMethodName);
  }

  /**
   * Test {@link AbstractAPIConverter#replace(String, String, String, String, String, String)} with
   * {@code className}, {@code methodName}, {@code methodDesc}, {@code replacementClassName}, {@code
   * replacementMethodName}, {@code replacementMethodDesc}.
   *
   * <p>Method under test: {@link AbstractAPIConverter#replace(String, String, String, String,
   * String, String)}
   */
  @Test
  @DisplayName(
      "Test replace(String, String, String, String, String, String) with 'className', 'methodName', 'methodDesc', 'replacementClassName', 'replacementMethodName', 'replacementMethodDesc'")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({
    "MethodReplacement AbstractAPIConverter.replace(String, String, String, String, String, String)"
  })
  void
      testReplaceWithClassNameMethodNameMethodDescReplacementClassNameReplacementMethodNameReplacementMethodDesc2() {
    // Arrange
    ClassPool programClassPool = new ClassPool();
    ClassPool libraryClassPool = new ClassPool();
    WarningPrinter warningPrinter = new WarningPrinter(new PrintWriter(new StringWriter()));
    ClassVisitor modifiedClassVisitor = mock(ClassVisitor.class);

    // Act
    MethodReplacement actualReplaceResult =
        new AbstractAPIConverter(
                programClassPool,
                libraryClassPool,
                warningPrinter,
                modifiedClassVisitor,
                new DuplicateInitializerInvocationFixer())
            .replace(
                "**",
                "Method Name",
                "Method Desc",
                "Replacement Class Name",
                "Replacement Method Name",
                "Replacement Method Desc");

    // Assert
    assertTrue(actualReplaceResult.classNameMatcher instanceof VariableStringMatcher);
    assertEquals("**", actualReplaceResult.matchingClassName);
    assertNull(actualReplaceResult.replacementClassName);
    assertNull(actualReplaceResult.replacementMethodDesc);
    assertNull(actualReplaceResult.replacementMethodName);
  }

  /**
   * Test {@link AbstractAPIConverter#replace(String, String, String, String, String, String)} with
   * {@code className}, {@code methodName}, {@code methodDesc}, {@code replacementClassName}, {@code
   * replacementMethodName}, {@code replacementMethodDesc}.
   *
   * <p>Method under test: {@link AbstractAPIConverter#replace(String, String, String, String,
   * String, String)}
   */
  @Test
  @DisplayName(
      "Test replace(String, String, String, String, String, String) with 'className', 'methodName', 'methodDesc', 'replacementClassName', 'replacementMethodName', 'replacementMethodDesc'")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({
    "MethodReplacement AbstractAPIConverter.replace(String, String, String, String, String, String)"
  })
  void
      testReplaceWithClassNameMethodNameMethodDescReplacementClassNameReplacementMethodNameReplacementMethodDesc3() {
    // Arrange
    ClassPool programClassPool = new ClassPool();
    ClassPool libraryClassPool = new ClassPool();
    WarningPrinter warningPrinter = new WarningPrinter(new PrintWriter(new StringWriter()));
    ClassVisitor modifiedClassVisitor = mock(ClassVisitor.class);

    // Act
    MethodReplacement actualReplaceResult =
        new AbstractAPIConverter(
                programClassPool,
                libraryClassPool,
                warningPrinter,
                modifiedClassVisitor,
                new DuplicateInitializerInvocationFixer())
            .replace(
                "*",
                "Method Name",
                "Method Desc",
                "Replacement Class Name",
                "Replacement Method Name",
                "Replacement Method Desc");

    // Assert
    assertTrue(actualReplaceResult.classNameMatcher instanceof VariableStringMatcher);
    assertEquals("*", actualReplaceResult.matchingClassName);
    assertNull(actualReplaceResult.replacementClassName);
    assertNull(actualReplaceResult.replacementMethodDesc);
    assertNull(actualReplaceResult.replacementMethodName);
  }

  /**
   * Test {@link AbstractAPIConverter#replace(String, String, String, String, String, String)} with
   * {@code className}, {@code methodName}, {@code methodDesc}, {@code replacementClassName}, {@code
   * replacementMethodName}, {@code replacementMethodDesc}.
   *
   * <p>Method under test: {@link AbstractAPIConverter#replace(String, String, String, String,
   * String, String)}
   */
  @Test
  @DisplayName(
      "Test replace(String, String, String, String, String, String) with 'className', 'methodName', 'methodDesc', 'replacementClassName', 'replacementMethodName', 'replacementMethodDesc'")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({
    "MethodReplacement AbstractAPIConverter.replace(String, String, String, String, String, String)"
  })
  void
      testReplaceWithClassNameMethodNameMethodDescReplacementClassNameReplacementMethodNameReplacementMethodDesc4() {
    // Arrange
    ClassPool programClassPool = new ClassPool();
    ClassPool libraryClassPool = new ClassPool();
    WarningPrinter warningPrinter = new WarningPrinter(new PrintWriter(new StringWriter()));
    ClassVisitor modifiedClassVisitor = mock(ClassVisitor.class);

    // Act
    MethodReplacement actualReplaceResult =
        new AbstractAPIConverter(
                programClassPool,
                libraryClassPool,
                warningPrinter,
                modifiedClassVisitor,
                new DuplicateInitializerInvocationFixer())
            .replace(
                "Class Name",
                "**",
                "Method Desc",
                "Replacement Class Name",
                "Replacement Method Name",
                "Replacement Method Desc");

    // Assert
    assertTrue(actualReplaceResult.classNameMatcher instanceof FixedStringMatcher);
    assertTrue(actualReplaceResult.methodNameMatcher instanceof VariableStringMatcher);
    assertEquals("**", actualReplaceResult.matchingMethodName);
    assertEquals("Class Name", actualReplaceResult.matchingClassName);
    assertNull(actualReplaceResult.replacementClassName);
    assertNull(actualReplaceResult.replacementMethodDesc);
    assertNull(actualReplaceResult.replacementMethodName);
  }

  /**
   * Test {@link AbstractAPIConverter#replace(String, String, String, String, String, String)} with
   * {@code className}, {@code methodName}, {@code methodDesc}, {@code replacementClassName}, {@code
   * replacementMethodName}, {@code replacementMethodDesc}.
   *
   * <p>Method under test: {@link AbstractAPIConverter#replace(String, String, String, String,
   * String, String)}
   */
  @Test
  @DisplayName(
      "Test replace(String, String, String, String, String, String) with 'className', 'methodName', 'methodDesc', 'replacementClassName', 'replacementMethodName', 'replacementMethodDesc'")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({
    "MethodReplacement AbstractAPIConverter.replace(String, String, String, String, String, String)"
  })
  void
      testReplaceWithClassNameMethodNameMethodDescReplacementClassNameReplacementMethodNameReplacementMethodDesc5() {
    // Arrange
    ClassPool programClassPool = new ClassPool();
    ClassPool libraryClassPool = new ClassPool();
    WarningPrinter warningPrinter = new WarningPrinter(new PrintWriter(new StringWriter()));
    ClassVisitor modifiedClassVisitor = mock(ClassVisitor.class);

    // Act
    MethodReplacement actualReplaceResult =
        new AbstractAPIConverter(
                programClassPool,
                libraryClassPool,
                warningPrinter,
                modifiedClassVisitor,
                new DuplicateInitializerInvocationFixer())
            .replace(
                "Class Name",
                "Method Name",
                "**",
                "Replacement Class Name",
                "Replacement Method Name",
                "Replacement Method Desc");

    // Assert
    assertTrue(actualReplaceResult.descMatcher instanceof ConstantMatcher);
    assertTrue(actualReplaceResult.classNameMatcher instanceof FixedStringMatcher);
    assertEquals("**", actualReplaceResult.matchingMethodDesc);
    assertEquals("Class Name", actualReplaceResult.matchingClassName);
    assertNull(actualReplaceResult.replacementClassName);
    assertNull(actualReplaceResult.replacementMethodDesc);
    assertNull(actualReplaceResult.replacementMethodName);
  }

  /**
   * Test {@link AbstractAPIConverter#replace(String, String, String, String, String, String)} with
   * {@code className}, {@code methodName}, {@code methodDesc}, {@code replacementClassName}, {@code
   * replacementMethodName}, {@code replacementMethodDesc}.
   *
   * <p>Method under test: {@link AbstractAPIConverter#replace(String, String, String, String,
   * String, String)}
   */
  @Test
  @DisplayName(
      "Test replace(String, String, String, String, String, String) with 'className', 'methodName', 'methodDesc', 'replacementClassName', 'replacementMethodName', 'replacementMethodDesc'")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({
    "MethodReplacement AbstractAPIConverter.replace(String, String, String, String, String, String)"
  })
  void
      testReplaceWithClassNameMethodNameMethodDescReplacementClassNameReplacementMethodNameReplacementMethodDesc6() {
    // Arrange
    ClassPool programClassPool = new ClassPool();
    ClassPool libraryClassPool = new ClassPool();
    WarningPrinter warningPrinter = new WarningPrinter(new PrintWriter(new StringWriter()));
    ClassVisitor modifiedClassVisitor = mock(ClassVisitor.class);

    // Act
    MethodReplacement actualReplaceResult =
        new AbstractAPIConverter(
                programClassPool,
                libraryClassPool,
                warningPrinter,
                modifiedClassVisitor,
                new DuplicateInitializerInvocationFixer())
            .replace(
                "Class Name",
                "Method Name",
                "Method Desc",
                "**",
                "Replacement Method Name",
                "Replacement Method Desc");

    // Assert
    assertTrue(actualReplaceResult.classNameMatcher instanceof FixedStringMatcher);
    assertEquals("**", actualReplaceResult.replacementClassName);
    assertEquals("Class Name", actualReplaceResult.matchingClassName);
    assertEquals("Replacement Method Desc", actualReplaceResult.replacementMethodDesc);
    assertEquals("Replacement Method Name", actualReplaceResult.replacementMethodName);
  }

  /**
   * Test {@link AbstractAPIConverter#replace(String, String, String, String, String, String)} with
   * {@code className}, {@code methodName}, {@code methodDesc}, {@code replacementClassName}, {@code
   * replacementMethodName}, {@code replacementMethodDesc}.
   *
   * <p>Method under test: {@link AbstractAPIConverter#replace(String, String, String, String,
   * String, String)}
   */
  @Test
  @DisplayName(
      "Test replace(String, String, String, String, String, String) with 'className', 'methodName', 'methodDesc', 'replacementClassName', 'replacementMethodName', 'replacementMethodDesc'")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({
    "MethodReplacement AbstractAPIConverter.replace(String, String, String, String, String, String)"
  })
  void
      testReplaceWithClassNameMethodNameMethodDescReplacementClassNameReplacementMethodNameReplacementMethodDesc7() {
    // Arrange
    ClassPool programClassPool = new ClassPool();
    ClassPool libraryClassPool = new ClassPool();
    WarningPrinter warningPrinter = new WarningPrinter(new PrintWriter(new StringWriter()));
    ClassVisitor modifiedClassVisitor = mock(ClassVisitor.class);

    // Act
    MethodReplacement actualReplaceResult =
        new AbstractAPIConverter(
                programClassPool,
                libraryClassPool,
                warningPrinter,
                modifiedClassVisitor,
                new DuplicateInitializerInvocationFixer())
            .replace(
                "Class Name",
                "Method Name",
                "Method Desc",
                "<1>",
                "Replacement Method Name",
                "Replacement Method Desc");

    // Assert
    assertTrue(actualReplaceResult.classNameMatcher instanceof FixedStringMatcher);
    assertEquals("<1>", actualReplaceResult.replacementClassName);
    assertEquals("Class Name", actualReplaceResult.matchingClassName);
    assertEquals("Replacement Method Desc", actualReplaceResult.replacementMethodDesc);
    assertEquals("Replacement Method Name", actualReplaceResult.replacementMethodName);
  }

  /**
   * Test {@link AbstractAPIConverter#replace(String, String)} with {@code className}, {@code
   * replacementClassName}.
   *
   * <p>Method under test: {@link AbstractAPIConverter#replace(String, String)}
   */
  @Test
  @DisplayName("Test replace(String, String) with 'className', 'replacementClassName'")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"TypeReplacement AbstractAPIConverter.replace(String, String)"})
  void testReplaceWithClassNameReplacementClassName() {
    // Arrange
    ClassPool programClassPool = new ClassPool();
    ClassPool libraryClassPool = new ClassPool();
    WarningPrinter warningPrinter = new WarningPrinter(new PrintWriter(new StringWriter()));
    ClassVisitor modifiedClassVisitor = mock(ClassVisitor.class);

    // Act
    TypeReplacement actualReplaceResult =
        new AbstractAPIConverter(
                programClassPool,
                libraryClassPool,
                warningPrinter,
                modifiedClassVisitor,
                new DuplicateInitializerInvocationFixer())
            .replace("Class Name", "Replacement Class Name");

    // Assert
    assertTrue(actualReplaceResult.classNameMatcher instanceof FixedStringMatcher);
    assertEquals("Class Name", actualReplaceResult.matchingClassName);
    assertNull(actualReplaceResult.replacementClassName);
  }

  /**
   * Test {@link AbstractAPIConverter#replace(String, String)} with {@code className}, {@code
   * replacementClassName}.
   *
   * <p>Method under test: {@link AbstractAPIConverter#replace(String, String)}
   */
  @Test
  @DisplayName("Test replace(String, String) with 'className', 'replacementClassName'")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"TypeReplacement AbstractAPIConverter.replace(String, String)"})
  void testReplaceWithClassNameReplacementClassName2() {
    // Arrange
    ClassPool programClassPool = new ClassPool();
    ClassPool libraryClassPool = new ClassPool();
    WarningPrinter warningPrinter = new WarningPrinter(new PrintWriter(new StringWriter()));
    ClassVisitor modifiedClassVisitor = mock(ClassVisitor.class);

    // Act
    TypeReplacement actualReplaceResult =
        new AbstractAPIConverter(
                programClassPool,
                libraryClassPool,
                warningPrinter,
                modifiedClassVisitor,
                new DuplicateInitializerInvocationFixer())
            .replace("*", "Replacement Class Name");

    // Assert
    assertTrue(actualReplaceResult.classNameMatcher instanceof VariableStringMatcher);
    assertEquals("*", actualReplaceResult.matchingClassName);
    assertNull(actualReplaceResult.replacementClassName);
  }

  /**
   * Test {@link AbstractAPIConverter#replace(String, String)} with {@code className}, {@code
   * replacementClassName}.
   *
   * <p>Method under test: {@link AbstractAPIConverter#replace(String, String)}
   */
  @Test
  @DisplayName("Test replace(String, String) with 'className', 'replacementClassName'")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"TypeReplacement AbstractAPIConverter.replace(String, String)"})
  void testReplaceWithClassNameReplacementClassName3() {
    // Arrange
    ClassPool programClassPool = new ClassPool();
    ClassPool libraryClassPool = new ClassPool();
    WarningPrinter warningPrinter = new WarningPrinter(new PrintWriter(new StringWriter()));
    ClassVisitor modifiedClassVisitor = mock(ClassVisitor.class);

    // Act
    TypeReplacement actualReplaceResult =
        new AbstractAPIConverter(
                programClassPool,
                libraryClassPool,
                warningPrinter,
                modifiedClassVisitor,
                new DuplicateInitializerInvocationFixer())
            .replace("Class Name", "*");

    // Assert
    assertTrue(actualReplaceResult.classNameMatcher instanceof FixedStringMatcher);
    assertEquals("*", actualReplaceResult.replacementClassName);
    assertEquals("Class Name", actualReplaceResult.matchingClassName);
  }

  /**
   * Test {@link AbstractAPIConverter#replace(String, String)} with {@code className}, {@code
   * replacementClassName}.
   *
   * <ul>
   *   <li>Then return {@link TypeReplacement#replacementClassName} is {@code <1>}.
   * </ul>
   *
   * <p>Method under test: {@link AbstractAPIConverter#replace(String, String)}
   */
  @Test
  @DisplayName(
      "Test replace(String, String) with 'className', 'replacementClassName'; then return replacementClassName is '<1>'")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"TypeReplacement AbstractAPIConverter.replace(String, String)"})
  void testReplaceWithClassNameReplacementClassName_thenReturnReplacementClassNameIs1() {
    // Arrange
    ClassPool programClassPool = new ClassPool();
    ClassPool libraryClassPool = new ClassPool();
    WarningPrinter warningPrinter = new WarningPrinter(new PrintWriter(new StringWriter()));
    ClassVisitor modifiedClassVisitor = mock(ClassVisitor.class);

    // Act
    TypeReplacement actualReplaceResult =
        new AbstractAPIConverter(
                programClassPool,
                libraryClassPool,
                warningPrinter,
                modifiedClassVisitor,
                new DuplicateInitializerInvocationFixer())
            .replace("Class Name", "<1>");

    // Assert
    assertTrue(actualReplaceResult.classNameMatcher instanceof FixedStringMatcher);
    assertEquals("<1>", actualReplaceResult.replacementClassName);
    assertEquals("Class Name", actualReplaceResult.matchingClassName);
  }

  /**
   * Test {@link AbstractAPIConverter#missing(String, String, String)} with {@code className},
   * {@code methodName}, {@code methodDesc}.
   *
   * <p>Method under test: {@link AbstractAPIConverter#missing(String, String, String)}
   */
  @Test
  @DisplayName("Test missing(String, String, String) with 'className', 'methodName', 'methodDesc'")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"MethodReplacement AbstractAPIConverter.missing(String, String, String)"})
  void testMissingWithClassNameMethodNameMethodDesc() {
    // Arrange
    ClassPool programClassPool = new ClassPool();
    ClassPool libraryClassPool = new ClassPool();
    WarningPrinter warningPrinter = new WarningPrinter(new PrintWriter(new StringWriter()));
    ClassVisitor modifiedClassVisitor = mock(ClassVisitor.class);

    // Act
    MethodReplacement actualMissingResult =
        new AbstractAPIConverter(
                programClassPool,
                libraryClassPool,
                warningPrinter,
                modifiedClassVisitor,
                new DuplicateInitializerInvocationFixer())
            .missing("Class Name", "Method Name", "Method Desc");

    // Assert
    assertTrue(actualMissingResult.classNameMatcher instanceof FixedStringMatcher);
    assertTrue(actualMissingResult.descMatcher instanceof FixedStringMatcher);
    assertTrue(actualMissingResult.methodNameMatcher instanceof FixedStringMatcher);
    assertEquals("Class Name", actualMissingResult.matchingClassName);
    assertEquals("Method Desc", actualMissingResult.matchingMethodDesc);
    assertEquals("Method Name", actualMissingResult.matchingMethodName);
  }

  /**
   * Test {@link AbstractAPIConverter#missing(String, String, String)} with {@code className},
   * {@code methodName}, {@code methodDesc}.
   *
   * <p>Method under test: {@link AbstractAPIConverter#missing(String, String, String)}
   */
  @Test
  @DisplayName("Test missing(String, String, String) with 'className', 'methodName', 'methodDesc'")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"MethodReplacement AbstractAPIConverter.missing(String, String, String)"})
  void testMissingWithClassNameMethodNameMethodDesc2() {
    // Arrange
    ClassPool programClassPool = new ClassPool();
    ClassPool libraryClassPool = new ClassPool();
    WarningPrinter warningPrinter = new WarningPrinter(new PrintWriter(new StringWriter()));
    ClassVisitor modifiedClassVisitor = mock(ClassVisitor.class);

    // Act
    MethodReplacement actualMissingResult =
        new AbstractAPIConverter(
                programClassPool,
                libraryClassPool,
                warningPrinter,
                modifiedClassVisitor,
                new DuplicateInitializerInvocationFixer())
            .missing("**", "Method Name", "Method Desc");

    // Assert
    assertTrue(actualMissingResult.descMatcher instanceof FixedStringMatcher);
    assertTrue(actualMissingResult.methodNameMatcher instanceof FixedStringMatcher);
    assertTrue(actualMissingResult.classNameMatcher instanceof VariableStringMatcher);
    assertEquals("**", actualMissingResult.matchingClassName);
    assertEquals("Method Desc", actualMissingResult.matchingMethodDesc);
    assertEquals("Method Name", actualMissingResult.matchingMethodName);
  }

  /**
   * Test {@link AbstractAPIConverter#missing(String, String, String)} with {@code className},
   * {@code methodName}, {@code methodDesc}.
   *
   * <p>Method under test: {@link AbstractAPIConverter#missing(String, String, String)}
   */
  @Test
  @DisplayName("Test missing(String, String, String) with 'className', 'methodName', 'methodDesc'")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"MethodReplacement AbstractAPIConverter.missing(String, String, String)"})
  void testMissingWithClassNameMethodNameMethodDesc3() {
    // Arrange
    ClassPool programClassPool = new ClassPool();
    ClassPool libraryClassPool = new ClassPool();
    WarningPrinter warningPrinter = new WarningPrinter(new PrintWriter(new StringWriter()));
    ClassVisitor modifiedClassVisitor = mock(ClassVisitor.class);

    // Act
    MethodReplacement actualMissingResult =
        new AbstractAPIConverter(
                programClassPool,
                libraryClassPool,
                warningPrinter,
                modifiedClassVisitor,
                new DuplicateInitializerInvocationFixer())
            .missing("Class Name", "**", "Method Desc");

    // Assert
    assertTrue(actualMissingResult.classNameMatcher instanceof FixedStringMatcher);
    assertTrue(actualMissingResult.descMatcher instanceof FixedStringMatcher);
    assertTrue(actualMissingResult.methodNameMatcher instanceof VariableStringMatcher);
    assertEquals("**", actualMissingResult.matchingMethodName);
    assertEquals("Class Name", actualMissingResult.matchingClassName);
    assertEquals("Method Desc", actualMissingResult.matchingMethodDesc);
  }

  /**
   * Test {@link AbstractAPIConverter#missing(String, String, String)} with {@code className},
   * {@code methodName}, {@code methodDesc}.
   *
   * <p>Method under test: {@link AbstractAPIConverter#missing(String, String, String)}
   */
  @Test
  @DisplayName("Test missing(String, String, String) with 'className', 'methodName', 'methodDesc'")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"MethodReplacement AbstractAPIConverter.missing(String, String, String)"})
  void testMissingWithClassNameMethodNameMethodDesc4() {
    // Arrange
    ClassPool programClassPool = new ClassPool();
    ClassPool libraryClassPool = new ClassPool();
    WarningPrinter warningPrinter = new WarningPrinter(new PrintWriter(new StringWriter()));
    ClassVisitor modifiedClassVisitor = mock(ClassVisitor.class);

    // Act
    MethodReplacement actualMissingResult =
        new AbstractAPIConverter(
                programClassPool,
                libraryClassPool,
                warningPrinter,
                modifiedClassVisitor,
                new DuplicateInitializerInvocationFixer())
            .missing("Class Name", "Method Name", "**");

    // Assert
    assertTrue(actualMissingResult.descMatcher instanceof ConstantMatcher);
    assertTrue(actualMissingResult.classNameMatcher instanceof FixedStringMatcher);
    assertTrue(actualMissingResult.methodNameMatcher instanceof FixedStringMatcher);
    assertEquals("**", actualMissingResult.matchingMethodDesc);
    assertEquals("Class Name", actualMissingResult.matchingClassName);
    assertEquals("Method Name", actualMissingResult.matchingMethodName);
  }

  /**
   * Test {@link AbstractAPIConverter#missing(String)} with {@code className}.
   *
   * <ul>
   *   <li>Then {@link TypeReplacement#classNameMatcher} return {@link EmptyStringMatcher}.
   * </ul>
   *
   * <p>Method under test: {@link AbstractAPIConverter#missing(String)}
   */
  @Test
  @DisplayName(
      "Test missing(String) with 'className'; then classNameMatcher return EmptyStringMatcher")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"TypeReplacement AbstractAPIConverter.missing(String)"})
  void testMissingWithClassName_thenClassNameMatcherReturnEmptyStringMatcher() {
    // Arrange
    ClassPool programClassPool = new ClassPool();
    ClassPool libraryClassPool = new ClassPool();
    WarningPrinter warningPrinter = new WarningPrinter(new PrintWriter(new StringWriter()));
    ClassVisitor modifiedClassVisitor = mock(ClassVisitor.class);

    // Act
    TypeReplacement actualMissingResult =
        new AbstractAPIConverter(
                programClassPool,
                libraryClassPool,
                warningPrinter,
                modifiedClassVisitor,
                new DuplicateInitializerInvocationFixer())
            .missing("");

    // Assert
    assertTrue(actualMissingResult.classNameMatcher instanceof EmptyStringMatcher);
    assertEquals("", actualMissingResult.matchingClassName);
    assertNull(actualMissingResult.replacementClassName);
  }

  /**
   * Test {@link AbstractAPIConverter#missing(String)} with {@code className}.
   *
   * <ul>
   *   <li>Then {@link TypeReplacement#classNameMatcher} return {@link FixedStringMatcher}.
   * </ul>
   *
   * <p>Method under test: {@link AbstractAPIConverter#missing(String)}
   */
  @Test
  @DisplayName(
      "Test missing(String) with 'className'; then classNameMatcher return FixedStringMatcher")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"TypeReplacement AbstractAPIConverter.missing(String)"})
  void testMissingWithClassName_thenClassNameMatcherReturnFixedStringMatcher() {
    // Arrange
    ClassPool programClassPool = new ClassPool();
    ClassPool libraryClassPool = new ClassPool();
    WarningPrinter warningPrinter = new WarningPrinter(new PrintWriter(new StringWriter()));
    ClassVisitor modifiedClassVisitor = mock(ClassVisitor.class);

    // Act
    TypeReplacement actualMissingResult =
        new AbstractAPIConverter(
                programClassPool,
                libraryClassPool,
                warningPrinter,
                modifiedClassVisitor,
                new DuplicateInitializerInvocationFixer())
            .missing("Class Name");

    // Assert
    assertTrue(actualMissingResult.classNameMatcher instanceof FixedStringMatcher);
    assertEquals("Class Name", actualMissingResult.matchingClassName);
    assertNull(actualMissingResult.replacementClassName);
  }

  /**
   * Test TypeReplacement {@link TypeReplacement#isValid()}.
   *
   * <p>Method under test: {@link TypeReplacement#isValid()}
   */
  @Test
  @DisplayName("Test TypeReplacement isValid()")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean TypeReplacement.isValid()"})
  void testTypeReplacementIsValid() {
    // Arrange
    ClassPool programClassPool = new ClassPool();
    ClassPool libraryClassPool = new ClassPool();
    WarningPrinter warningPrinter = new WarningPrinter(new PrintWriter(new StringWriter()));
    ClassVisitor modifiedClassVisitor = mock(ClassVisitor.class);

    // Act and Assert
    assertTrue(
        new AbstractAPIConverter(
                programClassPool,
                libraryClassPool,
                warningPrinter,
                modifiedClassVisitor,
                new DuplicateInitializerInvocationFixer())
            .new TypeReplacement("Matching Class Name", "*")
            .isValid());
  }

  /**
   * Test TypeReplacement {@link TypeReplacement#isValid()}.
   *
   * <p>Method under test: {@link TypeReplacement#isValid()}
   */
  @Test
  @DisplayName("Test TypeReplacement isValid()")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean TypeReplacement.isValid()"})
  void testTypeReplacementIsValid2() {
    // Arrange
    ClassPool programClassPool = new ClassPool();
    ClassPool libraryClassPool = new ClassPool();
    WarningPrinter warningPrinter = new WarningPrinter(new PrintWriter(new StringWriter()));
    ClassVisitor modifiedClassVisitor = mock(ClassVisitor.class);

    // Act and Assert
    assertTrue(
        new AbstractAPIConverter(
                programClassPool,
                libraryClassPool,
                warningPrinter,
                modifiedClassVisitor,
                new DuplicateInitializerInvocationFixer())
            .new TypeReplacement("Matching Class Name", "<1>")
            .isValid());
  }

  /**
   * Test TypeReplacement {@link TypeReplacement#isValid()}.
   *
   * <ul>
   *   <li>Then return {@code false}.
   * </ul>
   *
   * <p>Method under test: {@link TypeReplacement#isValid()}
   */
  @Test
  @DisplayName("Test TypeReplacement isValid(); then return 'false'")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean TypeReplacement.isValid()"})
  void testTypeReplacementIsValid_thenReturnFalse() {
    // Arrange
    ClassPool programClassPool = new ClassPool();
    ClassPool libraryClassPool = new ClassPool();
    WarningPrinter warningPrinter = new WarningPrinter(new PrintWriter(new StringWriter()));
    ClassVisitor modifiedClassVisitor = mock(ClassVisitor.class);

    // Act and Assert
    assertFalse(
        new AbstractAPIConverter(
                programClassPool,
                libraryClassPool,
                warningPrinter,
                modifiedClassVisitor,
                new DuplicateInitializerInvocationFixer())
            .new TypeReplacement("Matching Class Name", "Replacement Class Name")
            .isValid());
  }

  /**
   * Test TypeReplacement {@link TypeReplacement#matchesClassName(String)}.
   *
   * <p>Method under test: {@link TypeReplacement#matchesClassName(String)}
   */
  @Test
  @DisplayName("Test TypeReplacement matchesClassName(String)")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean TypeReplacement.matchesClassName(String)"})
  void testTypeReplacementMatchesClassName() {
    // Arrange
    ClassPool programClassPool = new ClassPool();
    ClassPool libraryClassPool = new ClassPool();
    WarningPrinter warningPrinter = new WarningPrinter(new PrintWriter(new StringWriter()));
    ClassVisitor modifiedClassVisitor = mock(ClassVisitor.class);
    TypeReplacement typeReplacement =
        new AbstractAPIConverter(
            programClassPool,
            libraryClassPool,
            warningPrinter,
            modifiedClassVisitor,
            new DuplicateInitializerInvocationFixer())
        .new TypeReplacement("*", "Replacement Class Name");

    // Act
    boolean actualMatchesClassNameResult = typeReplacement.matchesClassName("Class Name");

    // Assert
    StringMatcher stringMatcher = typeReplacement.classNameMatcher;
    assertTrue(stringMatcher instanceof VariableStringMatcher);
    assertEquals("Class Name", ((VariableStringMatcher) stringMatcher).getMatchingString());
    assertTrue(actualMatchesClassNameResult);
  }

  /**
   * Test TypeReplacement {@link TypeReplacement#matchesClassName(String)}.
   *
   * <ul>
   *   <li>Then return {@code false}.
   * </ul>
   *
   * <p>Method under test: {@link TypeReplacement#matchesClassName(String)}
   */
  @Test
  @DisplayName("Test TypeReplacement matchesClassName(String); then return 'false'")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean TypeReplacement.matchesClassName(String)"})
  void testTypeReplacementMatchesClassName_thenReturnFalse() {
    // Arrange
    ClassPool programClassPool = new ClassPool();
    ClassPool libraryClassPool = new ClassPool();
    WarningPrinter warningPrinter = new WarningPrinter(new PrintWriter(new StringWriter()));
    ClassVisitor modifiedClassVisitor = mock(ClassVisitor.class);

    // Act and Assert
    assertFalse(
        new AbstractAPIConverter(
                programClassPool,
                libraryClassPool,
                warningPrinter,
                modifiedClassVisitor,
                new DuplicateInitializerInvocationFixer())
            .new TypeReplacement("Matching Class Name", "Replacement Class Name")
            .matchesClassName("Class Name"));
  }

  /**
   * Test TypeReplacement {@link TypeReplacement#TypeReplacement(AbstractAPIConverter, String,
   * String)}.
   *
   * <p>Method under test: {@link TypeReplacement#TypeReplacement(AbstractAPIConverter, String,
   * String)}
   */
  @Test
  @DisplayName("Test TypeReplacement new TypeReplacement(AbstractAPIConverter, String, String)")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void TypeReplacement.<init>(AbstractAPIConverter, String, String)"})
  void testTypeReplacementNewTypeReplacement() {
    // Arrange
    ClassPool programClassPool = new ClassPool();
    ClassPool libraryClassPool = new ClassPool();
    WarningPrinter warningPrinter = new WarningPrinter(new PrintWriter(new StringWriter()));
    ClassVisitor modifiedClassVisitor = mock(ClassVisitor.class);

    // Act
    TypeReplacement actualTypeReplacement =
        new AbstractAPIConverter(
            programClassPool,
            libraryClassPool,
            warningPrinter,
            modifiedClassVisitor,
            new DuplicateInitializerInvocationFixer())
        .new TypeReplacement("Matching Class Name", "Replacement Class Name");

    // Assert
    assertTrue(actualTypeReplacement.classNameMatcher instanceof FixedStringMatcher);
    assertEquals("Matching Class Name", actualTypeReplacement.matchingClassName);
    assertEquals("Replacement Class Name", actualTypeReplacement.replacementClassName);
  }

  /**
   * Test TypeReplacement {@link TypeReplacement#TypeReplacement(AbstractAPIConverter, String,
   * String)}.
   *
   * <p>Method under test: {@link TypeReplacement#TypeReplacement(AbstractAPIConverter, String,
   * String)}
   */
  @Test
  @DisplayName("Test TypeReplacement new TypeReplacement(AbstractAPIConverter, String, String)")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void TypeReplacement.<init>(AbstractAPIConverter, String, String)"})
  void testTypeReplacementNewTypeReplacement2() {
    // Arrange
    ClassPool programClassPool = new ClassPool();
    ClassPool libraryClassPool = new ClassPool();
    WarningPrinter warningPrinter = new WarningPrinter(new PrintWriter(new StringWriter()));
    ClassVisitor modifiedClassVisitor = mock(ClassVisitor.class);

    // Act
    TypeReplacement actualTypeReplacement =
        new AbstractAPIConverter(
            programClassPool,
            libraryClassPool,
            warningPrinter,
            modifiedClassVisitor,
            new DuplicateInitializerInvocationFixer())
        .new TypeReplacement("*", "Replacement Class Name");

    // Assert
    assertTrue(actualTypeReplacement.classNameMatcher instanceof VariableStringMatcher);
    assertEquals("*", actualTypeReplacement.matchingClassName);
    assertEquals("Replacement Class Name", actualTypeReplacement.replacementClassName);
  }

  /**
   * Test TypeReplacement {@link TypeReplacement#replaceClassName(Clazz, String)}.
   *
   * <p>Method under test: {@link TypeReplacement#replaceClassName(Clazz, String)}
   */
  @Test
  @DisplayName("Test TypeReplacement replaceClassName(Clazz, String)")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"String TypeReplacement.replaceClassName(Clazz, String)"})
  void testTypeReplacementReplaceClassName() {
    // Arrange
    ClassPool programClassPool = new ClassPool();
    ClassPool libraryClassPool = new ClassPool();
    WarningPrinter warningPrinter = new WarningPrinter(new PrintWriter(new StringWriter()));
    ClassVisitor modifiedClassVisitor = mock(ClassVisitor.class);
    TypeReplacement typeReplacement =
        new AbstractAPIConverter(
            programClassPool,
            libraryClassPool,
            warningPrinter,
            modifiedClassVisitor,
            new DuplicateInitializerInvocationFixer())
        .new TypeReplacement("Matching Class Name", "Replacement Class Name");

    // Act and Assert
    assertEquals(
        "Replacement Class Name",
        typeReplacement.replaceClassName(new LibraryClass(), "Class Name"));
  }

  /**
   * Test {@link AbstractAPIConverter#visitProgramMethod(ProgramClass, ProgramMethod)}.
   *
   * <p>Method under test: {@link AbstractAPIConverter#visitProgramMethod(ProgramClass,
   * ProgramMethod)}
   */
  @Test
  @DisplayName("Test visitProgramMethod(ProgramClass, ProgramMethod)")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void AbstractAPIConverter.visitProgramMethod(ProgramClass, ProgramMethod)"})
  void testVisitProgramMethod() {
    // Arrange
    ClassPool programClassPool = new ClassPool();
    ClassPool libraryClassPool = new ClassPool();
    WarningPrinter warningPrinter = new WarningPrinter(new PrintWriter(new StringWriter()));
    ClassVisitor modifiedClassVisitor = mock(ClassVisitor.class);
    AbstractAPIConverter abstractAPIConverter =
        new AbstractAPIConverter(
            programClassPool,
            libraryClassPool,
            warningPrinter,
            modifiedClassVisitor,
            new DuplicateInitializerInvocationFixer());
    ProgramClass programClass = mock(ProgramClass.class);
    when(programClass.getString(anyInt())).thenReturn("String");

    // Act
    abstractAPIConverter.visitProgramMethod(programClass, new NamedMember("String", "String"));

    // Assert
    verify(programClass).getString(eq(0));
  }

  /**
   * Test {@link AbstractAPIConverter#visitProgramMethod(ProgramClass, ProgramMethod)}.
   *
   * <ul>
   *   <li>Given {@code String}.
   *   <li>When {@link ProgramMethod#ProgramMethod()}.
   *   <li>Then calls {@link ProgramClass#getString(int)}.
   * </ul>
   *
   * <p>Method under test: {@link AbstractAPIConverter#visitProgramMethod(ProgramClass,
   * ProgramMethod)}
   */
  @Test
  @DisplayName(
      "Test visitProgramMethod(ProgramClass, ProgramMethod); given 'String'; when ProgramMethod(); then calls getString(int)")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void AbstractAPIConverter.visitProgramMethod(ProgramClass, ProgramMethod)"})
  void testVisitProgramMethod_givenString_whenProgramMethod_thenCallsGetString() {
    // Arrange
    ClassPool programClassPool = new ClassPool();
    ClassPool libraryClassPool = new ClassPool();
    WarningPrinter warningPrinter = new WarningPrinter(new PrintWriter(new StringWriter()));
    ClassVisitor modifiedClassVisitor = mock(ClassVisitor.class);
    AbstractAPIConverter abstractAPIConverter =
        new AbstractAPIConverter(
            programClassPool,
            libraryClassPool,
            warningPrinter,
            modifiedClassVisitor,
            new DuplicateInitializerInvocationFixer());
    ProgramClass programClass = mock(ProgramClass.class);
    when(programClass.getString(anyInt())).thenReturn("String");

    // Act
    abstractAPIConverter.visitProgramMethod(programClass, new ProgramMethod());

    // Assert
    verify(programClass).getString(eq(0));
  }

  /**
   * Test {@link AbstractAPIConverter#visitProgramMethod(ProgramClass, ProgramMethod)}.
   *
   * <ul>
   *   <li>Then calls {@link AnnotationDefaultAttribute#accept(Clazz, Method, AttributeVisitor)}.
   * </ul>
   *
   * <p>Method under test: {@link AbstractAPIConverter#visitProgramMethod(ProgramClass,
   * ProgramMethod)}
   */
  @Test
  @DisplayName(
      "Test visitProgramMethod(ProgramClass, ProgramMethod); then calls accept(Clazz, Method, AttributeVisitor)")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void AbstractAPIConverter.visitProgramMethod(ProgramClass, ProgramMethod)"})
  void testVisitProgramMethod_thenCallsAccept() {
    // Arrange
    ClassPool programClassPool = new ClassPool();
    ClassPool libraryClassPool = new ClassPool();
    WarningPrinter warningPrinter = new WarningPrinter(new PrintWriter(new StringWriter()));
    ClassVisitor modifiedClassVisitor = mock(ClassVisitor.class);
    AbstractAPIConverter abstractAPIConverter =
        new AbstractAPIConverter(
            programClassPool,
            libraryClassPool,
            warningPrinter,
            modifiedClassVisitor,
            new DuplicateInitializerInvocationFixer());
    ProgramClass programClass = mock(ProgramClass.class);
    when(programClass.getString(anyInt())).thenReturn("String");
    AnnotationDefaultAttribute annotationDefaultAttribute = mock(AnnotationDefaultAttribute.class);
    doNothing()
        .when(annotationDefaultAttribute)
        .accept(Mockito.<Clazz>any(), Mockito.<Method>any(), Mockito.<AttributeVisitor>any());

    // Act
    abstractAPIConverter.visitProgramMethod(
        programClass,
        new ProgramMethod(
            1,
            1,
            1,
            1,
            new Attribute[] {annotationDefaultAttribute},
            new Clazz[] {new LibraryClass()}));

    // Assert
    verify(programClass).getString(eq(1));
    verify(annotationDefaultAttribute)
        .accept(isA(Clazz.class), isA(Method.class), isA(AttributeVisitor.class));
  }

  /**
   * Test {@link AbstractAPIConverter#visitSignatureAttribute(Clazz, SignatureAttribute)} with
   * {@code clazz}, {@code signatureAttribute}.
   *
   * <ul>
   *   <li>Then calls {@link LibraryClass#getString(int)}.
   * </ul>
   *
   * <p>Method under test: {@link AbstractAPIConverter#visitSignatureAttribute(Clazz,
   * SignatureAttribute)}
   */
  @Test
  @DisplayName(
      "Test visitSignatureAttribute(Clazz, SignatureAttribute) with 'clazz', 'signatureAttribute'; then calls getString(int)")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void AbstractAPIConverter.visitSignatureAttribute(Clazz, SignatureAttribute)"
  })
  void testVisitSignatureAttributeWithClazzSignatureAttribute_thenCallsGetString() {
    // Arrange
    ClassPool programClassPool = new ClassPool();
    ClassPool libraryClassPool = new ClassPool();
    WarningPrinter warningPrinter = new WarningPrinter(new PrintWriter(new StringWriter()));
    ClassVisitor modifiedClassVisitor = mock(ClassVisitor.class);
    AbstractAPIConverter abstractAPIConverter =
        new AbstractAPIConverter(
            programClassPool,
            libraryClassPool,
            warningPrinter,
            modifiedClassVisitor,
            new DuplicateInitializerInvocationFixer());
    LibraryClass clazz = mock(LibraryClass.class);
    when(clazz.getString(anyInt())).thenReturn("String");

    // Act
    abstractAPIConverter.visitSignatureAttribute(clazz, new SignatureAttribute(1, 1));

    // Assert
    verify(clazz).getString(eq(1));
  }

  /**
   * Test {@link AbstractAPIConverter#visitAnnotationDefaultAttribute(Clazz, Method,
   * AnnotationDefaultAttribute)}.
   *
   * <ul>
   *   <li>Then calls {@link AnnotationDefaultAttribute#defaultValueAccept(Clazz,
   *       ElementValueVisitor)}.
   * </ul>
   *
   * <p>Method under test: {@link AbstractAPIConverter#visitAnnotationDefaultAttribute(Clazz,
   * Method, AnnotationDefaultAttribute)}
   */
  @Test
  @DisplayName(
      "Test visitAnnotationDefaultAttribute(Clazz, Method, AnnotationDefaultAttribute); then calls defaultValueAccept(Clazz, ElementValueVisitor)")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void AbstractAPIConverter.visitAnnotationDefaultAttribute(Clazz, Method, AnnotationDefaultAttribute)"
  })
  void testVisitAnnotationDefaultAttribute_thenCallsDefaultValueAccept() {
    // Arrange
    ClassPool programClassPool = new ClassPool();
    ClassPool libraryClassPool = new ClassPool();
    WarningPrinter warningPrinter = new WarningPrinter(new PrintWriter(new StringWriter()));
    ClassVisitor modifiedClassVisitor = mock(ClassVisitor.class);
    AbstractAPIConverter abstractAPIConverter =
        new AbstractAPIConverter(
            programClassPool,
            libraryClassPool,
            warningPrinter,
            modifiedClassVisitor,
            new DuplicateInitializerInvocationFixer());
    LibraryClass clazz = new LibraryClass();
    LibraryMethod method = new LibraryMethod(1, "Name", "Descriptor");

    AnnotationDefaultAttribute annotationDefaultAttribute = mock(AnnotationDefaultAttribute.class);
    doNothing()
        .when(annotationDefaultAttribute)
        .defaultValueAccept(Mockito.<Clazz>any(), Mockito.<ElementValueVisitor>any());

    // Act
    abstractAPIConverter.visitAnnotationDefaultAttribute(clazz, method, annotationDefaultAttribute);

    // Assert
    verify(annotationDefaultAttribute)
        .defaultValueAccept(isA(Clazz.class), isA(ElementValueVisitor.class));
  }

  /**
   * Test {@link AbstractAPIConverter#visitLocalVariableInfo(Clazz, Method, CodeAttribute,
   * LocalVariableInfo)}.
   *
   * <ul>
   *   <li>Given {@code String}.
   *   <li>Then calls {@link LibraryClass#getString(int)}.
   * </ul>
   *
   * <p>Method under test: {@link AbstractAPIConverter#visitLocalVariableInfo(Clazz, Method,
   * CodeAttribute, LocalVariableInfo)}
   */
  @Test
  @DisplayName(
      "Test visitLocalVariableInfo(Clazz, Method, CodeAttribute, LocalVariableInfo); given 'String'; then calls getString(int)")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void AbstractAPIConverter.visitLocalVariableInfo(Clazz, Method, CodeAttribute, LocalVariableInfo)"
  })
  void testVisitLocalVariableInfo_givenString_thenCallsGetString() {
    // Arrange
    ClassPool programClassPool = new ClassPool();
    ClassPool libraryClassPool = new ClassPool();
    WarningPrinter warningPrinter = new WarningPrinter(new PrintWriter(new StringWriter()));
    ClassVisitor modifiedClassVisitor = mock(ClassVisitor.class);
    AbstractAPIConverter abstractAPIConverter =
        new AbstractAPIConverter(
            programClassPool,
            libraryClassPool,
            warningPrinter,
            modifiedClassVisitor,
            new DuplicateInitializerInvocationFixer());
    LibraryClass clazz = mock(LibraryClass.class);
    when(clazz.getString(anyInt())).thenReturn("String");
    LibraryMethod method = new LibraryMethod(1, "Name", "Descriptor");

    CodeAttribute codeAttribute = new CodeAttribute(1);

    // Act
    abstractAPIConverter.visitLocalVariableInfo(
        clazz, method, codeAttribute, new LocalVariableInfo(1, 3, 1, 1, 1));

    // Assert
    verify(clazz).getString(eq(1));
  }

  /**
   * Test {@link AbstractAPIConverter#visitLocalVariableTypeInfo(Clazz, Method, CodeAttribute,
   * LocalVariableTypeInfo)}.
   *
   * <ul>
   *   <li>Given {@code String}.
   *   <li>Then calls {@link LibraryClass#getString(int)}.
   * </ul>
   *
   * <p>Method under test: {@link AbstractAPIConverter#visitLocalVariableTypeInfo(Clazz, Method,
   * CodeAttribute, LocalVariableTypeInfo)}
   */
  @Test
  @DisplayName(
      "Test visitLocalVariableTypeInfo(Clazz, Method, CodeAttribute, LocalVariableTypeInfo); given 'String'; then calls getString(int)")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void AbstractAPIConverter.visitLocalVariableTypeInfo(Clazz, Method, CodeAttribute, LocalVariableTypeInfo)"
  })
  void testVisitLocalVariableTypeInfo_givenString_thenCallsGetString() {
    // Arrange
    ClassPool programClassPool = new ClassPool();
    ClassPool libraryClassPool = new ClassPool();
    WarningPrinter warningPrinter = new WarningPrinter(new PrintWriter(new StringWriter()));
    ClassVisitor modifiedClassVisitor = mock(ClassVisitor.class);
    AbstractAPIConverter abstractAPIConverter =
        new AbstractAPIConverter(
            programClassPool,
            libraryClassPool,
            warningPrinter,
            modifiedClassVisitor,
            new DuplicateInitializerInvocationFixer());
    LibraryClass clazz = mock(LibraryClass.class);
    when(clazz.getString(anyInt())).thenReturn("String");
    LibraryMethod method = new LibraryMethod(1, "Name", "Descriptor");

    CodeAttribute codeAttribute = new CodeAttribute(1);

    // Act
    abstractAPIConverter.visitLocalVariableTypeInfo(
        clazz, method, codeAttribute, new LocalVariableTypeInfo(1, 3, 1, 1, 1));

    // Assert
    verify(clazz).getString(eq(1));
  }

  /**
   * Test {@link AbstractAPIConverter#visitEnumConstantElementValue(Clazz, Annotation,
   * EnumConstantElementValue)}.
   *
   * <ul>
   *   <li>Given {@code String}.
   *   <li>Then calls {@link LibraryClass#getString(int)}.
   * </ul>
   *
   * <p>Method under test: {@link AbstractAPIConverter#visitEnumConstantElementValue(Clazz,
   * Annotation, EnumConstantElementValue)}
   */
  @Test
  @DisplayName(
      "Test visitEnumConstantElementValue(Clazz, Annotation, EnumConstantElementValue); given 'String'; then calls getString(int)")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void AbstractAPIConverter.visitEnumConstantElementValue(Clazz, Annotation, EnumConstantElementValue)"
  })
  void testVisitEnumConstantElementValue_givenString_thenCallsGetString() {
    // Arrange
    ClassPool programClassPool = new ClassPool();
    ClassPool libraryClassPool = new ClassPool();
    WarningPrinter warningPrinter = new WarningPrinter(new PrintWriter(new StringWriter()));
    ClassVisitor modifiedClassVisitor = mock(ClassVisitor.class);
    AbstractAPIConverter abstractAPIConverter =
        new AbstractAPIConverter(
            programClassPool,
            libraryClassPool,
            warningPrinter,
            modifiedClassVisitor,
            new DuplicateInitializerInvocationFixer());
    LibraryClass clazz = mock(LibraryClass.class);
    when(clazz.getString(anyInt())).thenReturn("String");
    Annotation annotation = new Annotation();

    // Act
    abstractAPIConverter.visitEnumConstantElementValue(
        clazz, annotation, new EnumConstantElementValue(1, 1, 1));

    // Assert
    verify(clazz).getString(eq(1));
  }

  /**
   * Test {@link AbstractAPIConverter#visitClassElementValue(Clazz, Annotation, ClassElementValue)}.
   *
   * <p>Method under test: {@link AbstractAPIConverter#visitClassElementValue(Clazz, Annotation,
   * ClassElementValue)}
   */
  @Test
  @DisplayName("Test visitClassElementValue(Clazz, Annotation, ClassElementValue)")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void AbstractAPIConverter.visitClassElementValue(Clazz, Annotation, ClassElementValue)"
  })
  void testVisitClassElementValue() {
    // Arrange
    ClassPool programClassPool = new ClassPool();
    ClassPool libraryClassPool = new ClassPool();
    WarningPrinter warningPrinter = new WarningPrinter(new PrintWriter(new StringWriter()));
    ClassVisitor modifiedClassVisitor = mock(ClassVisitor.class);

    JSR310Converter jsr310Converter =
        new JSR310Converter(
            programClassPool,
            libraryClassPool,
            warningPrinter,
            modifiedClassVisitor,
            new DuplicateInitializerInvocationFixer());
    ClassPool programClassPool2 = new ClassPool();
    ClassPool libraryClassPool2 = new ClassPool();
    WarningPrinter warningPrinter2 = new WarningPrinter(null);
    ClassVisitor modifiedClassVisitor2 = mock(ClassVisitor.class);
    jsr310Converter.setTypeReplacements(
        new TypeReplacement[] {
          new AbstractAPIConverter(
              programClassPool2,
              libraryClassPool2,
              warningPrinter2,
              modifiedClassVisitor2,
              new DuplicateInitializerInvocationFixer())
          .new TypeReplacement("Class Name", "Class Name")
        });
    LibraryClass libraryClass = mock(LibraryClass.class);
    Annotation annotation = new Annotation();
    ClassElementValue classElementValue = mock(ClassElementValue.class);
    when(classElementValue.getClassName(Mockito.<Clazz>any())).thenReturn("Class Name");

    // Act
    jsr310Converter.visitClassElementValue(libraryClass, annotation, classElementValue);

    // Assert
    verify(classElementValue).getClassName(isA(Clazz.class));
  }

  /**
   * Test {@link AbstractAPIConverter#visitClassElementValue(Clazz, Annotation, ClassElementValue)}.
   *
   * <ul>
   *   <li>Then calls {@link ClassElementValue#getClassName(Clazz)}.
   * </ul>
   *
   * <p>Method under test: {@link AbstractAPIConverter#visitClassElementValue(Clazz, Annotation,
   * ClassElementValue)}
   */
  @Test
  @DisplayName(
      "Test visitClassElementValue(Clazz, Annotation, ClassElementValue); then calls getClassName(Clazz)")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void AbstractAPIConverter.visitClassElementValue(Clazz, Annotation, ClassElementValue)"
  })
  void testVisitClassElementValue_thenCallsGetClassName() {
    // Arrange
    ClassPool programClassPool = new ClassPool();
    ClassPool libraryClassPool = new ClassPool();
    WarningPrinter warningPrinter = new WarningPrinter(new PrintWriter(new StringWriter()));
    ClassVisitor modifiedClassVisitor = mock(ClassVisitor.class);
    JSR310Converter jsr310Converter =
        new JSR310Converter(
            programClassPool,
            libraryClassPool,
            warningPrinter,
            modifiedClassVisitor,
            new DuplicateInitializerInvocationFixer());
    LibraryClass libraryClass = mock(LibraryClass.class);
    Annotation annotation = new Annotation();
    ClassElementValue classElementValue = mock(ClassElementValue.class);
    when(classElementValue.getClassName(Mockito.<Clazz>any())).thenReturn("Class Name");

    // Act
    jsr310Converter.visitClassElementValue(libraryClass, annotation, classElementValue);

    // Assert
    verify(classElementValue).getClassName(isA(Clazz.class));
  }

  /**
   * Test {@link AbstractAPIConverter#visitClassElementValue(Clazz, Annotation, ClassElementValue)}.
   *
   * <ul>
   *   <li>Then calls {@link LibraryClass#getString(int)}.
   * </ul>
   *
   * <p>Method under test: {@link AbstractAPIConverter#visitClassElementValue(Clazz, Annotation,
   * ClassElementValue)}
   */
  @Test
  @DisplayName(
      "Test visitClassElementValue(Clazz, Annotation, ClassElementValue); then calls getString(int)")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void AbstractAPIConverter.visitClassElementValue(Clazz, Annotation, ClassElementValue)"
  })
  void testVisitClassElementValue_thenCallsGetString() {
    // Arrange
    ClassPool programClassPool = new ClassPool();
    ClassPool libraryClassPool = new ClassPool();
    WarningPrinter warningPrinter = new WarningPrinter(new PrintWriter(new StringWriter()));
    ClassVisitor modifiedClassVisitor = mock(ClassVisitor.class);
    JSR310Converter jsr310Converter =
        new JSR310Converter(
            programClassPool,
            libraryClassPool,
            warningPrinter,
            modifiedClassVisitor,
            new DuplicateInitializerInvocationFixer());
    LibraryClass libraryClass = mock(LibraryClass.class);
    when(libraryClass.getString(anyInt())).thenReturn("String");
    Annotation annotation = new Annotation();

    // Act
    jsr310Converter.visitClassElementValue(libraryClass, annotation, new ClassElementValue(1, 1));

    // Assert
    verify(libraryClass).getString(eq(1));
  }

  /**
   * Test {@link AbstractAPIConverter#visitClassElementValue(Clazz, Annotation, ClassElementValue)}.
   *
   * <ul>
   *   <li>Then calls {@link TypeReplacement#matchesClassName(String)}.
   * </ul>
   *
   * <p>Method under test: {@link AbstractAPIConverter#visitClassElementValue(Clazz, Annotation,
   * ClassElementValue)}
   */
  @Test
  @DisplayName(
      "Test visitClassElementValue(Clazz, Annotation, ClassElementValue); then calls matchesClassName(String)")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void AbstractAPIConverter.visitClassElementValue(Clazz, Annotation, ClassElementValue)"
  })
  void testVisitClassElementValue_thenCallsMatchesClassName() {
    // Arrange
    TypeReplacement typeReplacement = mock(TypeReplacement.class);
    when(typeReplacement.replaceClassName(Mockito.<Clazz>any(), Mockito.<String>any()))
        .thenReturn("Class Name");
    when(typeReplacement.matchesClassName(Mockito.<String>any())).thenReturn(true);
    ClassPool programClassPool = new ClassPool();
    ClassPool libraryClassPool = new ClassPool();
    WarningPrinter warningPrinter = new WarningPrinter(new PrintWriter(new StringWriter()));
    ClassVisitor modifiedClassVisitor = mock(ClassVisitor.class);

    JSR310Converter jsr310Converter =
        new JSR310Converter(
            programClassPool,
            libraryClassPool,
            warningPrinter,
            modifiedClassVisitor,
            new DuplicateInitializerInvocationFixer());
    jsr310Converter.setTypeReplacements(new TypeReplacement[] {typeReplacement});
    LibraryClass libraryClass = mock(LibraryClass.class);
    Annotation annotation = new Annotation();
    ClassElementValue classElementValue = mock(ClassElementValue.class);
    when(classElementValue.getClassName(Mockito.<Clazz>any())).thenReturn("Class Name");

    // Act
    jsr310Converter.visitClassElementValue(libraryClass, annotation, classElementValue);

    // Assert
    verify(typeReplacement).matchesClassName(eq("Class Name"));
    verify(typeReplacement).replaceClassName(isA(Clazz.class), eq("Class Name"));
    verify(classElementValue).getClassName(isA(Clazz.class));
  }

  /**
   * Test {@link AbstractAPIConverter#visitAnnotationElementValue(Clazz, Annotation,
   * AnnotationElementValue)}.
   *
   * <ul>
   *   <li>Then calls {@link AnnotationElementValue#annotationAccept(Clazz, AnnotationVisitor)}.
   * </ul>
   *
   * <p>Method under test: {@link AbstractAPIConverter#visitAnnotationElementValue(Clazz,
   * Annotation, AnnotationElementValue)}
   */
  @Test
  @DisplayName(
      "Test visitAnnotationElementValue(Clazz, Annotation, AnnotationElementValue); then calls annotationAccept(Clazz, AnnotationVisitor)")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void AbstractAPIConverter.visitAnnotationElementValue(Clazz, Annotation, AnnotationElementValue)"
  })
  void testVisitAnnotationElementValue_thenCallsAnnotationAccept() {
    // Arrange
    ClassPool programClassPool = new ClassPool();
    ClassPool libraryClassPool = new ClassPool();
    WarningPrinter warningPrinter = new WarningPrinter(new PrintWriter(new StringWriter()));
    ClassVisitor modifiedClassVisitor = mock(ClassVisitor.class);
    AbstractAPIConverter abstractAPIConverter =
        new AbstractAPIConverter(
            programClassPool,
            libraryClassPool,
            warningPrinter,
            modifiedClassVisitor,
            new DuplicateInitializerInvocationFixer());
    LibraryClass clazz = new LibraryClass();
    Annotation annotation = new Annotation();
    AnnotationElementValue annotationElementValue = mock(AnnotationElementValue.class);
    doNothing()
        .when(annotationElementValue)
        .annotationAccept(Mockito.<Clazz>any(), Mockito.<AnnotationVisitor>any());

    // Act
    abstractAPIConverter.visitAnnotationElementValue(clazz, annotation, annotationElementValue);

    // Assert
    verify(annotationElementValue).annotationAccept(isA(Clazz.class), isA(AnnotationVisitor.class));
  }

  /**
   * Test {@link AbstractAPIConverter#visitClassConstant(Clazz, ClassConstant)}.
   *
   * <p>Method under test: {@link AbstractAPIConverter#visitClassConstant(Clazz, ClassConstant)}
   */
  @Test
  @DisplayName("Test visitClassConstant(Clazz, ClassConstant)")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void AbstractAPIConverter.visitClassConstant(Clazz, ClassConstant)"})
  void testVisitClassConstant() {
    // Arrange
    ClassPool programClassPool = new ClassPool();
    ClassPool libraryClassPool = new ClassPool();
    WarningPrinter warningPrinter = new WarningPrinter(new PrintWriter(new StringWriter()));
    ClassVisitor modifiedClassVisitor = mock(ClassVisitor.class);

    JSR310Converter jsr310Converter =
        new JSR310Converter(
            programClassPool,
            libraryClassPool,
            warningPrinter,
            modifiedClassVisitor,
            new DuplicateInitializerInvocationFixer());
    ClassPool programClassPool2 = new ClassPool();
    ClassPool libraryClassPool2 = new ClassPool();
    WarningPrinter warningPrinter2 = new WarningPrinter(null);
    ClassVisitor modifiedClassVisitor2 = mock(ClassVisitor.class);
    jsr310Converter.setTypeReplacements(
        new TypeReplacement[] {
          new AbstractAPIConverter(
              programClassPool2,
              libraryClassPool2,
              warningPrinter2,
              modifiedClassVisitor2,
              new DuplicateInitializerInvocationFixer())
          .new TypeReplacement("Name", "Name")
        });
    LibraryClass libraryClass = mock(LibraryClass.class);
    ClassConstant classConstant = mock(ClassConstant.class);
    when(classConstant.getName(Mockito.<Clazz>any())).thenReturn("Name");

    // Act
    jsr310Converter.visitClassConstant(libraryClass, classConstant);

    // Assert
    verify(classConstant).getName(isA(Clazz.class));
  }

  /**
   * Test {@link AbstractAPIConverter#visitClassConstant(Clazz, ClassConstant)}.
   *
   * <ul>
   *   <li>Then calls {@link ClassConstant#getName(Clazz)}.
   * </ul>
   *
   * <p>Method under test: {@link AbstractAPIConverter#visitClassConstant(Clazz, ClassConstant)}
   */
  @Test
  @DisplayName("Test visitClassConstant(Clazz, ClassConstant); then calls getName(Clazz)")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void AbstractAPIConverter.visitClassConstant(Clazz, ClassConstant)"})
  void testVisitClassConstant_thenCallsGetName() {
    // Arrange
    ClassPool programClassPool = new ClassPool();
    ClassPool libraryClassPool = new ClassPool();
    WarningPrinter warningPrinter = new WarningPrinter(new PrintWriter(new StringWriter()));
    ClassVisitor modifiedClassVisitor = mock(ClassVisitor.class);
    JSR310Converter jsr310Converter =
        new JSR310Converter(
            programClassPool,
            libraryClassPool,
            warningPrinter,
            modifiedClassVisitor,
            new DuplicateInitializerInvocationFixer());
    LibraryClass libraryClass = mock(LibraryClass.class);
    ClassConstant classConstant = mock(ClassConstant.class);
    when(classConstant.getName(Mockito.<Clazz>any())).thenReturn("Name");

    // Act
    jsr310Converter.visitClassConstant(libraryClass, classConstant);

    // Assert
    verify(classConstant).getName(isA(Clazz.class));
  }

  /**
   * Test {@link AbstractAPIConverter#visitClassConstant(Clazz, ClassConstant)}.
   *
   * <ul>
   *   <li>Then calls {@link LibraryClass#getString(int)}.
   * </ul>
   *
   * <p>Method under test: {@link AbstractAPIConverter#visitClassConstant(Clazz, ClassConstant)}
   */
  @Test
  @DisplayName("Test visitClassConstant(Clazz, ClassConstant); then calls getString(int)")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void AbstractAPIConverter.visitClassConstant(Clazz, ClassConstant)"})
  void testVisitClassConstant_thenCallsGetString() {
    // Arrange
    ClassPool programClassPool = new ClassPool();
    ClassPool libraryClassPool = new ClassPool();
    WarningPrinter warningPrinter = new WarningPrinter(new PrintWriter(new StringWriter()));
    ClassVisitor modifiedClassVisitor = mock(ClassVisitor.class);
    JSR310Converter jsr310Converter =
        new JSR310Converter(
            programClassPool,
            libraryClassPool,
            warningPrinter,
            modifiedClassVisitor,
            new DuplicateInitializerInvocationFixer());
    LibraryClass libraryClass = mock(LibraryClass.class);
    when(libraryClass.getString(anyInt())).thenReturn("String");

    // Act
    jsr310Converter.visitClassConstant(libraryClass, new ClassConstant());

    // Assert
    verify(libraryClass).getString(eq(0));
  }

  /**
   * Test {@link AbstractAPIConverter#visitClassConstant(Clazz, ClassConstant)}.
   *
   * <ul>
   *   <li>Then calls {@link TypeReplacement#matchesClassName(String)}.
   * </ul>
   *
   * <p>Method under test: {@link AbstractAPIConverter#visitClassConstant(Clazz, ClassConstant)}
   */
  @Test
  @DisplayName("Test visitClassConstant(Clazz, ClassConstant); then calls matchesClassName(String)")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void AbstractAPIConverter.visitClassConstant(Clazz, ClassConstant)"})
  void testVisitClassConstant_thenCallsMatchesClassName() {
    // Arrange
    TypeReplacement typeReplacement = mock(TypeReplacement.class);
    when(typeReplacement.replaceClassName(Mockito.<Clazz>any(), Mockito.<String>any()))
        .thenReturn("Name");
    when(typeReplacement.matchesClassName(Mockito.<String>any())).thenReturn(true);
    ClassPool programClassPool = new ClassPool();
    ClassPool libraryClassPool = new ClassPool();
    WarningPrinter warningPrinter = new WarningPrinter(new PrintWriter(new StringWriter()));
    ClassVisitor modifiedClassVisitor = mock(ClassVisitor.class);

    JSR310Converter jsr310Converter =
        new JSR310Converter(
            programClassPool,
            libraryClassPool,
            warningPrinter,
            modifiedClassVisitor,
            new DuplicateInitializerInvocationFixer());
    jsr310Converter.setTypeReplacements(new TypeReplacement[] {typeReplacement});
    LibraryClass libraryClass = mock(LibraryClass.class);
    ClassConstant classConstant = mock(ClassConstant.class);
    when(classConstant.getName(Mockito.<Clazz>any())).thenReturn("Name");

    // Act
    jsr310Converter.visitClassConstant(libraryClass, classConstant);

    // Assert
    verify(typeReplacement).matchesClassName(eq("Name"));
    verify(typeReplacement).replaceClassName(isA(Clazz.class), eq("Name"));
    verify(classConstant).getName(isA(Clazz.class));
  }

  /**
   * Test {@link AbstractAPIConverter#visitFieldrefConstant(Clazz, FieldrefConstant)}.
   *
   * <ul>
   *   <li>Given {@code foo}.
   *   <li>When {@link LibraryClass}.
   *   <li>Then calls {@link FieldrefConstant#getName(Clazz)}.
   * </ul>
   *
   * <p>Method under test: {@link AbstractAPIConverter#visitFieldrefConstant(Clazz,
   * FieldrefConstant)}
   */
  @Test
  @DisplayName(
      "Test visitFieldrefConstant(Clazz, FieldrefConstant); given 'foo'; when LibraryClass; then calls getName(Clazz)")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void AbstractAPIConverter.visitFieldrefConstant(Clazz, FieldrefConstant)"})
  void testVisitFieldrefConstant_givenFoo_whenLibraryClass_thenCallsGetName() {
    // Arrange
    ClassPool programClassPool = new ClassPool();
    ClassPool libraryClassPool = new ClassPool();
    WarningPrinter warningPrinter = new WarningPrinter(new PrintWriter(new StringWriter()));
    ClassVisitor modifiedClassVisitor = mock(ClassVisitor.class);
    AbstractAPIConverter abstractAPIConverter =
        new AbstractAPIConverter(
            programClassPool,
            libraryClassPool,
            warningPrinter,
            modifiedClassVisitor,
            new DuplicateInitializerInvocationFixer());
    LibraryClass clazz = mock(LibraryClass.class);
    FieldrefConstant fieldrefConstant = mock(FieldrefConstant.class);
    when(fieldrefConstant.getName(Mockito.<Clazz>any())).thenReturn("Name");
    when(fieldrefConstant.getType(Mockito.<Clazz>any())).thenReturn("foo");

    // Act
    abstractAPIConverter.visitFieldrefConstant(clazz, fieldrefConstant);

    // Assert
    verify(fieldrefConstant).getName(isA(Clazz.class));
    verify(fieldrefConstant).getType(isA(Clazz.class));
  }

  /**
   * Test {@link AbstractAPIConverter#visitFieldrefConstant(Clazz, FieldrefConstant)}.
   *
   * <ul>
   *   <li>When {@link LibraryClass} {@link LibraryClass#getType(int)} return {@code foo}.
   *   <li>Then calls {@link LibraryClass#getName(int)}.
   * </ul>
   *
   * <p>Method under test: {@link AbstractAPIConverter#visitFieldrefConstant(Clazz,
   * FieldrefConstant)}
   */
  @Test
  @DisplayName(
      "Test visitFieldrefConstant(Clazz, FieldrefConstant); when LibraryClass getType(int) return 'foo'; then calls getName(int)")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void AbstractAPIConverter.visitFieldrefConstant(Clazz, FieldrefConstant)"})
  void testVisitFieldrefConstant_whenLibraryClassGetTypeReturnFoo_thenCallsGetName() {
    // Arrange
    ClassPool programClassPool = new ClassPool();
    ClassPool libraryClassPool = new ClassPool();
    WarningPrinter warningPrinter = new WarningPrinter(new PrintWriter(new StringWriter()));
    ClassVisitor modifiedClassVisitor = mock(ClassVisitor.class);
    AbstractAPIConverter abstractAPIConverter =
        new AbstractAPIConverter(
            programClassPool,
            libraryClassPool,
            warningPrinter,
            modifiedClassVisitor,
            new DuplicateInitializerInvocationFixer());
    LibraryClass clazz = mock(LibraryClass.class);
    when(clazz.getName(anyInt())).thenReturn("Name");
    when(clazz.getType(anyInt())).thenReturn("foo");

    // Act
    abstractAPIConverter.visitFieldrefConstant(clazz, new FieldrefConstant());

    // Assert
    verify(clazz).getName(eq(0));
    verify(clazz).getType(eq(0));
  }
}
