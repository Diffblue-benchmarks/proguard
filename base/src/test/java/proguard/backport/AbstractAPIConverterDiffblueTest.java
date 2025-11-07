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
   * Test MethodReplacement {@link MethodReplacement#matches(Clazz, AnyMethodrefConstant)}.
   * <p>
   * Method under test: {@link MethodReplacement#matches(Clazz, AnyMethodrefConstant)}
   */
  @Test
  @DisplayName("Test MethodReplacement matches(Clazz, AnyMethodrefConstant)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean MethodReplacement.matches(Clazz, AnyMethodrefConstant)"})
  void testMethodReplacementMatches() {
    // Arrange
    ClassPool programClassPool = new ClassPool();
    ClassPool libraryClassPool = new ClassPool();
    WarningPrinter warningPrinter = new WarningPrinter(new PrintWriter(new StringWriter()));
    ClassVisitor modifiedClassVisitor = mock(ClassVisitor.class);
    MethodReplacement methodReplacement = (new AbstractAPIConverter(programClassPool, libraryClassPool, warningPrinter,
        modifiedClassVisitor, new DuplicateInitializerInvocationFixer())).new MethodReplacement("<default>",
            "Method Name", "Method Desc", "Replacement Class Name", "Replacement Method Name",
            "Replacement Method Desc");
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
   * Test MethodReplacement {@link MethodReplacement#matches(Clazz, AnyMethodrefConstant)}.
   * <p>
   * Method under test: {@link MethodReplacement#matches(Clazz, AnyMethodrefConstant)}
   */
  @Test
  @DisplayName("Test MethodReplacement matches(Clazz, AnyMethodrefConstant)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean MethodReplacement.matches(Clazz, AnyMethodrefConstant)"})
  void testMethodReplacementMatches2() {
    // Arrange
    ClassPool programClassPool = new ClassPool();
    ClassPool libraryClassPool = new ClassPool();
    WarningPrinter warningPrinter = new WarningPrinter(new PrintWriter(new StringWriter()));
    ClassVisitor modifiedClassVisitor = mock(ClassVisitor.class);
    MethodReplacement methodReplacement = (new AbstractAPIConverter(programClassPool, libraryClassPool, warningPrinter,
        modifiedClassVisitor, new DuplicateInitializerInvocationFixer())).new MethodReplacement("Class Name",
            "<default>", "Method Desc", "Replacement Class Name", "Replacement Method Name", "Replacement Method Desc");
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
   * Test MethodReplacement {@link MethodReplacement#matches(Clazz, AnyMethodrefConstant)}.
   * <p>
   * Method under test: {@link MethodReplacement#matches(Clazz, AnyMethodrefConstant)}
   */
  @Test
  @DisplayName("Test MethodReplacement matches(Clazz, AnyMethodrefConstant)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean MethodReplacement.matches(Clazz, AnyMethodrefConstant)"})
  void testMethodReplacementMatches3() {
    // Arrange
    ClassPool programClassPool = new ClassPool();
    ClassPool libraryClassPool = new ClassPool();
    WarningPrinter warningPrinter = new WarningPrinter(new PrintWriter(new StringWriter()));
    ClassVisitor modifiedClassVisitor = mock(ClassVisitor.class);
    MethodReplacement methodReplacement = (new AbstractAPIConverter(programClassPool, libraryClassPool, warningPrinter,
        modifiedClassVisitor, new DuplicateInitializerInvocationFixer())).new MethodReplacement("Class Name",
            "<static>", "Method Desc", "Replacement Class Name", "Replacement Method Name", "Replacement Method Desc");
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
   * Test MethodReplacement {@link MethodReplacement#matches(Clazz, AnyMethodrefConstant)}.
   * <p>
   * Method under test: {@link MethodReplacement#matches(Clazz, AnyMethodrefConstant)}
   */
  @Test
  @DisplayName("Test MethodReplacement matches(Clazz, AnyMethodrefConstant)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean MethodReplacement.matches(Clazz, AnyMethodrefConstant)"})
  void testMethodReplacementMatches4() {
    // Arrange
    ClassPool programClassPool = new ClassPool();
    ClassPool libraryClassPool = new ClassPool();
    WarningPrinter warningPrinter = new WarningPrinter(new PrintWriter(new StringWriter()));
    ClassVisitor modifiedClassVisitor = mock(ClassVisitor.class);
    MethodReplacement methodReplacement = (new AbstractAPIConverter(programClassPool, libraryClassPool, warningPrinter,
        modifiedClassVisitor, new DuplicateInitializerInvocationFixer())).new MethodReplacement("Class Name", "*",
            "Method Desc", "Replacement Class Name", "Replacement Method Name", "Replacement Method Desc");
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
   * Test MethodReplacement {@link MethodReplacement#matches(Clazz, AnyMethodrefConstant)}.
   * <p>
   * Method under test: {@link MethodReplacement#matches(Clazz, AnyMethodrefConstant)}
   */
  @Test
  @DisplayName("Test MethodReplacement matches(Clazz, AnyMethodrefConstant)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean MethodReplacement.matches(Clazz, AnyMethodrefConstant)"})
  void testMethodReplacementMatches5() {
    // Arrange
    ClassPool programClassPool = new ClassPool();
    ClassPool libraryClassPool = new ClassPool();
    WarningPrinter warningPrinter = new WarningPrinter(new PrintWriter(new StringWriter()));
    ClassVisitor modifiedClassVisitor = mock(ClassVisitor.class);
    MethodReplacement methodReplacement = (new AbstractAPIConverter(programClassPool, libraryClassPool, warningPrinter,
        modifiedClassVisitor, new DuplicateInitializerInvocationFixer())).new MethodReplacement("Class Name",
            "Method Name", "Method Desc", "Replacement Class Name", "Replacement Method Name",
            "Replacement Method Desc");
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
   * Test MethodReplacement {@link MethodReplacement#matches(Clazz, AnyMethodrefConstant)}.
   * <ul>
   *   <li>Then return {@code false}.</li>
   * </ul>
   * <p>
   * Method under test: {@link MethodReplacement#matches(Clazz, AnyMethodrefConstant)}
   */
  @Test
  @DisplayName("Test MethodReplacement matches(Clazz, AnyMethodrefConstant); then return 'false'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean MethodReplacement.matches(Clazz, AnyMethodrefConstant)"})
  void testMethodReplacementMatches_thenReturnFalse() {
    // Arrange
    ClassPool programClassPool = new ClassPool();
    ClassPool libraryClassPool = new ClassPool();
    WarningPrinter warningPrinter = new WarningPrinter(new PrintWriter(new StringWriter()));
    ClassVisitor modifiedClassVisitor = mock(ClassVisitor.class);
    MethodReplacement methodReplacement = (new AbstractAPIConverter(programClassPool, libraryClassPool, warningPrinter,
        modifiedClassVisitor, new DuplicateInitializerInvocationFixer())).new MethodReplacement("Class Name",
            "Method Name", "Method Desc", "Replacement Class Name", "Replacement Method Name",
            "Replacement Method Desc");
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
   * Test MethodReplacement {@link MethodReplacement#matches(Clazz, AnyMethodrefConstant)}.
   * <ul>
   *   <li>When {@link InterfaceMethodrefConstant#InterfaceMethodrefConstant()}.</li>
   *   <li>Then return {@code false}.</li>
   * </ul>
   * <p>
   * Method under test: {@link MethodReplacement#matches(Clazz, AnyMethodrefConstant)}
   */
  @Test
  @DisplayName("Test MethodReplacement matches(Clazz, AnyMethodrefConstant); when InterfaceMethodrefConstant(); then return 'false'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean MethodReplacement.matches(Clazz, AnyMethodrefConstant)"})
  void testMethodReplacementMatches_whenInterfaceMethodrefConstant_thenReturnFalse() {
    // Arrange
    ClassPool programClassPool = new ClassPool();
    ClassPool libraryClassPool = new ClassPool();
    WarningPrinter warningPrinter = new WarningPrinter(new PrintWriter(new StringWriter()));
    ClassVisitor modifiedClassVisitor = mock(ClassVisitor.class);
    MethodReplacement methodReplacement = (new AbstractAPIConverter(programClassPool, libraryClassPool, warningPrinter,
        modifiedClassVisitor, new DuplicateInitializerInvocationFixer())).new MethodReplacement("Class Name",
            "Method Name", "Method Desc", "Replacement Class Name", "Replacement Method Name",
            "Replacement Method Desc");
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
   * Test MethodReplacement {@link MethodReplacement#MethodReplacement(AbstractAPIConverter, String, String, String, String, String, String)}.
   * <p>
   * Method under test: {@link MethodReplacement#MethodReplacement(AbstractAPIConverter, String, String, String, String, String, String)}
   */
  @Test
  @DisplayName("Test MethodReplacement new MethodReplacement(AbstractAPIConverter, String, String, String, String, String, String)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "void MethodReplacement.<init>(AbstractAPIConverter, String, String, String, String, String, String)"})
  void testMethodReplacementNewMethodReplacement() {
    // Arrange
    ClassPool programClassPool = new ClassPool();
    ClassPool libraryClassPool = new ClassPool();
    WarningPrinter warningPrinter = new WarningPrinter(new PrintWriter(new StringWriter()));
    ClassVisitor modifiedClassVisitor = mock(ClassVisitor.class);

    // Act
    MethodReplacement actualMethodReplacement = (new AbstractAPIConverter(programClassPool, libraryClassPool,
        warningPrinter, modifiedClassVisitor, new DuplicateInitializerInvocationFixer())).new MethodReplacement(
            "Class Name", "Method Name", "Method Desc", "Replacement Class Name", "Replacement Method Name",
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
   * Test MethodReplacement {@link MethodReplacement#MethodReplacement(AbstractAPIConverter, String, String, String, String, String, String)}.
   * <p>
   * Method under test: {@link MethodReplacement#MethodReplacement(AbstractAPIConverter, String, String, String, String, String, String)}
   */
  @Test
  @DisplayName("Test MethodReplacement new MethodReplacement(AbstractAPIConverter, String, String, String, String, String, String)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "void MethodReplacement.<init>(AbstractAPIConverter, String, String, String, String, String, String)"})
  void testMethodReplacementNewMethodReplacement2() {
    // Arrange
    ClassPool programClassPool = new ClassPool();
    ClassPool libraryClassPool = new ClassPool();
    WarningPrinter warningPrinter = new WarningPrinter(new PrintWriter(new StringWriter()));
    ClassVisitor modifiedClassVisitor = mock(ClassVisitor.class);

    // Act
    MethodReplacement actualMethodReplacement = (new AbstractAPIConverter(programClassPool, libraryClassPool,
        warningPrinter, modifiedClassVisitor, new DuplicateInitializerInvocationFixer())).new MethodReplacement("**",
            "Method Name", "Method Desc", "Replacement Class Name", "Replacement Method Name",
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
   * Test MethodReplacement {@link MethodReplacement#MethodReplacement(AbstractAPIConverter, String, String, String, String, String, String)}.
   * <p>
   * Method under test: {@link MethodReplacement#MethodReplacement(AbstractAPIConverter, String, String, String, String, String, String)}
   */
  @Test
  @DisplayName("Test MethodReplacement new MethodReplacement(AbstractAPIConverter, String, String, String, String, String, String)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "void MethodReplacement.<init>(AbstractAPIConverter, String, String, String, String, String, String)"})
  void testMethodReplacementNewMethodReplacement3() {
    // Arrange
    ClassPool programClassPool = new ClassPool();
    ClassPool libraryClassPool = new ClassPool();
    WarningPrinter warningPrinter = new WarningPrinter(new PrintWriter(new StringWriter()));
    ClassVisitor modifiedClassVisitor = mock(ClassVisitor.class);

    // Act
    MethodReplacement actualMethodReplacement = (new AbstractAPIConverter(programClassPool, libraryClassPool,
        warningPrinter, modifiedClassVisitor, new DuplicateInitializerInvocationFixer())).new MethodReplacement(
            "Class Name", "**", "Method Desc", "Replacement Class Name", "Replacement Method Name",
            "Replacement Method Desc");

    // Assert
    assertTrue(actualMethodReplacement.classNameMatcher instanceof FixedStringMatcher);
    assertTrue(actualMethodReplacement.methodNameMatcher instanceof VariableStringMatcher);
    assertEquals("**", actualMethodReplacement.matchingMethodName);
    assertEquals("Class Name", actualMethodReplacement.matchingClassName);
  }

  /**
   * Test MethodReplacement {@link MethodReplacement#MethodReplacement(AbstractAPIConverter, String, String, String, String, String, String)}.
   * <ul>
   *   <li>Then {@link MethodReplacement#descMatcher} return {@link ConstantMatcher}.</li>
   * </ul>
   * <p>
   * Method under test: {@link MethodReplacement#MethodReplacement(AbstractAPIConverter, String, String, String, String, String, String)}
   */
  @Test
  @DisplayName("Test MethodReplacement new MethodReplacement(AbstractAPIConverter, String, String, String, String, String, String); then descMatcher return ConstantMatcher")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "void MethodReplacement.<init>(AbstractAPIConverter, String, String, String, String, String, String)"})
  void testMethodReplacementNewMethodReplacement_thenDescMatcherReturnConstantMatcher() {
    // Arrange
    ClassPool programClassPool = new ClassPool();
    ClassPool libraryClassPool = new ClassPool();
    WarningPrinter warningPrinter = new WarningPrinter(new PrintWriter(new StringWriter()));
    ClassVisitor modifiedClassVisitor = mock(ClassVisitor.class);

    // Act
    MethodReplacement actualMethodReplacement = (new AbstractAPIConverter(programClassPool, libraryClassPool,
        warningPrinter, modifiedClassVisitor, new DuplicateInitializerInvocationFixer())).new MethodReplacement(
            "Class Name", "Method Name", "**", "Replacement Class Name", "Replacement Method Name",
            "Replacement Method Desc");

    // Assert
    assertTrue(actualMethodReplacement.descMatcher instanceof ConstantMatcher);
    assertTrue(actualMethodReplacement.classNameMatcher instanceof FixedStringMatcher);
    assertEquals("**", actualMethodReplacement.matchingMethodDesc);
    assertEquals("Class Name", actualMethodReplacement.matchingClassName);
  }

  /**
   * Test MethodReplacement {@link MethodReplacement#MethodReplacement(AbstractAPIConverter, String, String, String, String, String, String)}.
   * <ul>
   *   <li>Then return {@link MethodReplacement#matchingClassName} is {@code *}.</li>
   * </ul>
   * <p>
   * Method under test: {@link MethodReplacement#MethodReplacement(AbstractAPIConverter, String, String, String, String, String, String)}
   */
  @Test
  @DisplayName("Test MethodReplacement new MethodReplacement(AbstractAPIConverter, String, String, String, String, String, String); then return matchingClassName is '*'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "void MethodReplacement.<init>(AbstractAPIConverter, String, String, String, String, String, String)"})
  void testMethodReplacementNewMethodReplacement_thenReturnMatchingClassNameIsAsterisk() {
    // Arrange
    ClassPool programClassPool = new ClassPool();
    ClassPool libraryClassPool = new ClassPool();
    WarningPrinter warningPrinter = new WarningPrinter(new PrintWriter(new StringWriter()));
    ClassVisitor modifiedClassVisitor = mock(ClassVisitor.class);

    // Act
    MethodReplacement actualMethodReplacement = (new AbstractAPIConverter(programClassPool, libraryClassPool,
        warningPrinter, modifiedClassVisitor, new DuplicateInitializerInvocationFixer())).new MethodReplacement("*",
            "Method Name", "Method Desc", "Replacement Class Name", "Replacement Method Name",
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
   * Test MethodReplacement {@link MethodReplacement#replaceInstruction(int, Clazz, Method, AnyMethodrefConstant)}.
   * <ul>
   *   <li>Then calls {@link LibraryClass#getClassName(int)}.</li>
   * </ul>
   * <p>
   * Method under test: {@link MethodReplacement#replaceInstruction(int, Clazz, Method, AnyMethodrefConstant)}
   */
  @Test
  @DisplayName("Test MethodReplacement replaceInstruction(int, Clazz, Method, AnyMethodrefConstant); then calls getClassName(int)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void MethodReplacement.replaceInstruction(int, Clazz, Method, AnyMethodrefConstant)"})
  void testMethodReplacementReplaceInstruction_thenCallsGetClassName() {
    // Arrange
    ClassPool programClassPool = new ClassPool();
    ClassPool libraryClassPool = new ClassPool();
    WarningPrinter warningPrinter = new WarningPrinter(new PrintWriter(new StringWriter()));
    ClassVisitor modifiedClassVisitor = mock(ClassVisitor.class);
    MethodReplacement methodReplacement = (new AbstractAPIConverter(programClassPool, libraryClassPool, warningPrinter,
        modifiedClassVisitor, new DuplicateInitializerInvocationFixer())).new MethodReplacement("Class Name",
            "Method Name", "Method Desc", "Replacement Class Name", "Replacement Method Name",
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
   * Test {@link AbstractAPIConverter#AbstractAPIConverter(ClassPool, ClassPool, WarningPrinter, ClassVisitor, InstructionVisitor)}.
   * <p>
   * Method under test: {@link AbstractAPIConverter#AbstractAPIConverter(ClassPool, ClassPool, WarningPrinter, ClassVisitor, InstructionVisitor)}
   */
  @Test
  @DisplayName("Test new AbstractAPIConverter(ClassPool, ClassPool, WarningPrinter, ClassVisitor, InstructionVisitor)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "void AbstractAPIConverter.<init>(ClassPool, ClassPool, WarningPrinter, ClassVisitor, InstructionVisitor)"})
  void testNewAbstractAPIConverter() {
    // Arrange
    ClassPool programClassPool = new ClassPool();
    ClassPool libraryClassPool = new ClassPool();
    WarningPrinter warningPrinter = new WarningPrinter(new PrintWriter(new StringWriter()));
    ClassVisitor modifiedClassVisitor = mock(ClassVisitor.class);

    // Act and Assert
    TypeReplacement missingResult = (new AbstractAPIConverter(programClassPool, libraryClassPool, warningPrinter,
        modifiedClassVisitor, new DuplicateInitializerInvocationFixer())).missing("Class Name");
    assertTrue(missingResult.classNameMatcher instanceof FixedStringMatcher);
    assertEquals("Class Name", missingResult.matchingClassName);
    assertNull(missingResult.replacementClassName);
  }

  /**
   * Test {@link AbstractAPIConverter#replace(String, String, String, String, String, String)} with {@code className}, {@code methodName}, {@code methodDesc}, {@code replacementClassName}, {@code replacementMethodName}, {@code replacementMethodDesc}.
   * <p>
   * Method under test: {@link AbstractAPIConverter#replace(String, String, String, String, String, String)}
   */
  @Test
  @DisplayName("Test replace(String, String, String, String, String, String) with 'className', 'methodName', 'methodDesc', 'replacementClassName', 'replacementMethodName', 'replacementMethodDesc'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"MethodReplacement AbstractAPIConverter.replace(String, String, String, String, String, String)"})
  void testReplaceWithClassNameMethodNameMethodDescReplacementClassNameReplacementMethodNameReplacementMethodDesc() {
    // Arrange
    ClassPool programClassPool = new ClassPool();
    ClassPool libraryClassPool = new ClassPool();
    WarningPrinter warningPrinter = new WarningPrinter(new PrintWriter(new StringWriter()));
    ClassVisitor modifiedClassVisitor = mock(ClassVisitor.class);

    // Act
    MethodReplacement actualReplaceResult = (new AbstractAPIConverter(programClassPool, libraryClassPool,
        warningPrinter, modifiedClassVisitor, new DuplicateInitializerInvocationFixer())).replace("Class Name",
            "Method Name", "Method Desc", "Replacement Class Name", "Replacement Method Name",
            "Replacement Method Desc");

    // Assert
    assertTrue(actualReplaceResult.classNameMatcher instanceof FixedStringMatcher);
    assertEquals("Class Name", actualReplaceResult.matchingClassName);
    assertNull(actualReplaceResult.replacementClassName);
    assertNull(actualReplaceResult.replacementMethodDesc);
    assertNull(actualReplaceResult.replacementMethodName);
  }

  /**
   * Test {@link AbstractAPIConverter#replace(String, String, String, String, String, String)} with {@code className}, {@code methodName}, {@code methodDesc}, {@code replacementClassName}, {@code replacementMethodName}, {@code replacementMethodDesc}.
   * <p>
   * Method under test: {@link AbstractAPIConverter#replace(String, String, String, String, String, String)}
   */
  @Test
  @DisplayName("Test replace(String, String, String, String, String, String) with 'className', 'methodName', 'methodDesc', 'replacementClassName', 'replacementMethodName', 'replacementMethodDesc'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"MethodReplacement AbstractAPIConverter.replace(String, String, String, String, String, String)"})
  void testReplaceWithClassNameMethodNameMethodDescReplacementClassNameReplacementMethodNameReplacementMethodDesc2() {
    // Arrange
    ClassPool programClassPool = new ClassPool();
    ClassPool libraryClassPool = new ClassPool();
    WarningPrinter warningPrinter = new WarningPrinter(new PrintWriter(new StringWriter()));
    ClassVisitor modifiedClassVisitor = mock(ClassVisitor.class);

    // Act
    MethodReplacement actualReplaceResult = (new AbstractAPIConverter(programClassPool, libraryClassPool,
        warningPrinter, modifiedClassVisitor, new DuplicateInitializerInvocationFixer())).replace("**", "Method Name",
            "Method Desc", "Replacement Class Name", "Replacement Method Name", "Replacement Method Desc");

    // Assert
    assertTrue(actualReplaceResult.classNameMatcher instanceof VariableStringMatcher);
    assertEquals("**", actualReplaceResult.matchingClassName);
    assertNull(actualReplaceResult.replacementClassName);
    assertNull(actualReplaceResult.replacementMethodDesc);
    assertNull(actualReplaceResult.replacementMethodName);
  }

  /**
   * Test {@link AbstractAPIConverter#replace(String, String, String, String, String, String)} with {@code className}, {@code methodName}, {@code methodDesc}, {@code replacementClassName}, {@code replacementMethodName}, {@code replacementMethodDesc}.
   * <p>
   * Method under test: {@link AbstractAPIConverter#replace(String, String, String, String, String, String)}
   */
  @Test
  @DisplayName("Test replace(String, String, String, String, String, String) with 'className', 'methodName', 'methodDesc', 'replacementClassName', 'replacementMethodName', 'replacementMethodDesc'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"MethodReplacement AbstractAPIConverter.replace(String, String, String, String, String, String)"})
  void testReplaceWithClassNameMethodNameMethodDescReplacementClassNameReplacementMethodNameReplacementMethodDesc3() {
    // Arrange
    ClassPool programClassPool = new ClassPool();
    ClassPool libraryClassPool = new ClassPool();
    WarningPrinter warningPrinter = new WarningPrinter(new PrintWriter(new StringWriter()));
    ClassVisitor modifiedClassVisitor = mock(ClassVisitor.class);

    // Act
    MethodReplacement actualReplaceResult = (new AbstractAPIConverter(programClassPool, libraryClassPool,
        warningPrinter, modifiedClassVisitor, new DuplicateInitializerInvocationFixer())).replace("*", "Method Name",
            "Method Desc", "Replacement Class Name", "Replacement Method Name", "Replacement Method Desc");

    // Assert
    assertTrue(actualReplaceResult.classNameMatcher instanceof VariableStringMatcher);
    assertEquals("*", actualReplaceResult.matchingClassName);
    assertNull(actualReplaceResult.replacementClassName);
    assertNull(actualReplaceResult.replacementMethodDesc);
    assertNull(actualReplaceResult.replacementMethodName);
  }

  /**
   * Test {@link AbstractAPIConverter#replace(String, String, String, String, String, String)} with {@code className}, {@code methodName}, {@code methodDesc}, {@code replacementClassName}, {@code replacementMethodName}, {@code replacementMethodDesc}.
   * <p>
   * Method under test: {@link AbstractAPIConverter#replace(String, String, String, String, String, String)}
   */
  @Test
  @DisplayName("Test replace(String, String, String, String, String, String) with 'className', 'methodName', 'methodDesc', 'replacementClassName', 'replacementMethodName', 'replacementMethodDesc'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"MethodReplacement AbstractAPIConverter.replace(String, String, String, String, String, String)"})
  void testReplaceWithClassNameMethodNameMethodDescReplacementClassNameReplacementMethodNameReplacementMethodDesc4() {
    // Arrange
    ClassPool programClassPool = new ClassPool();
    ClassPool libraryClassPool = new ClassPool();
    WarningPrinter warningPrinter = new WarningPrinter(new PrintWriter(new StringWriter()));
    ClassVisitor modifiedClassVisitor = mock(ClassVisitor.class);

    // Act
    MethodReplacement actualReplaceResult = (new AbstractAPIConverter(programClassPool, libraryClassPool,
        warningPrinter, modifiedClassVisitor, new DuplicateInitializerInvocationFixer())).replace("Class Name", "**",
            "Method Desc", "Replacement Class Name", "Replacement Method Name", "Replacement Method Desc");

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
   * Test {@link AbstractAPIConverter#replace(String, String, String, String, String, String)} with {@code className}, {@code methodName}, {@code methodDesc}, {@code replacementClassName}, {@code replacementMethodName}, {@code replacementMethodDesc}.
   * <p>
   * Method under test: {@link AbstractAPIConverter#replace(String, String, String, String, String, String)}
   */
  @Test
  @DisplayName("Test replace(String, String, String, String, String, String) with 'className', 'methodName', 'methodDesc', 'replacementClassName', 'replacementMethodName', 'replacementMethodDesc'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"MethodReplacement AbstractAPIConverter.replace(String, String, String, String, String, String)"})
  void testReplaceWithClassNameMethodNameMethodDescReplacementClassNameReplacementMethodNameReplacementMethodDesc5() {
    // Arrange
    ClassPool programClassPool = new ClassPool();
    ClassPool libraryClassPool = new ClassPool();
    WarningPrinter warningPrinter = new WarningPrinter(new PrintWriter(new StringWriter()));
    ClassVisitor modifiedClassVisitor = mock(ClassVisitor.class);

    // Act
    MethodReplacement actualReplaceResult = (new AbstractAPIConverter(programClassPool, libraryClassPool,
        warningPrinter, modifiedClassVisitor, new DuplicateInitializerInvocationFixer())).replace("Class Name",
            "Method Name", "**", "Replacement Class Name", "Replacement Method Name", "Replacement Method Desc");

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
   * Test {@link AbstractAPIConverter#replace(String, String, String, String, String, String)} with {@code className}, {@code methodName}, {@code methodDesc}, {@code replacementClassName}, {@code replacementMethodName}, {@code replacementMethodDesc}.
   * <p>
   * Method under test: {@link AbstractAPIConverter#replace(String, String, String, String, String, String)}
   */
  @Test
  @DisplayName("Test replace(String, String, String, String, String, String) with 'className', 'methodName', 'methodDesc', 'replacementClassName', 'replacementMethodName', 'replacementMethodDesc'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"MethodReplacement AbstractAPIConverter.replace(String, String, String, String, String, String)"})
  void testReplaceWithClassNameMethodNameMethodDescReplacementClassNameReplacementMethodNameReplacementMethodDesc6() {
    // Arrange
    ClassPool programClassPool = new ClassPool();
    ClassPool libraryClassPool = new ClassPool();
    WarningPrinter warningPrinter = new WarningPrinter(new PrintWriter(new StringWriter()));
    ClassVisitor modifiedClassVisitor = mock(ClassVisitor.class);

    // Act
    MethodReplacement actualReplaceResult = (new AbstractAPIConverter(programClassPool, libraryClassPool,
        warningPrinter, modifiedClassVisitor, new DuplicateInitializerInvocationFixer())).replace("Class Name",
            "Method Name", "Method Desc", "**", "Replacement Method Name", "Replacement Method Desc");

    // Assert
    assertTrue(actualReplaceResult.classNameMatcher instanceof FixedStringMatcher);
    assertEquals("**", actualReplaceResult.replacementClassName);
    assertEquals("Class Name", actualReplaceResult.matchingClassName);
    assertEquals("Replacement Method Desc", actualReplaceResult.replacementMethodDesc);
    assertEquals("Replacement Method Name", actualReplaceResult.replacementMethodName);
  }

  /**
   * Test {@link AbstractAPIConverter#replace(String, String, String, String, String, String)} with {@code className}, {@code methodName}, {@code methodDesc}, {@code replacementClassName}, {@code replacementMethodName}, {@code replacementMethodDesc}.
   * <p>
   * Method under test: {@link AbstractAPIConverter#replace(String, String, String, String, String, String)}
   */
  @Test
  @DisplayName("Test replace(String, String, String, String, String, String) with 'className', 'methodName', 'methodDesc', 'replacementClassName', 'replacementMethodName', 'replacementMethodDesc'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"MethodReplacement AbstractAPIConverter.replace(String, String, String, String, String, String)"})
  void testReplaceWithClassNameMethodNameMethodDescReplacementClassNameReplacementMethodNameReplacementMethodDesc7() {
    // Arrange
    ClassPool programClassPool = new ClassPool();
    ClassPool libraryClassPool = new ClassPool();
    WarningPrinter warningPrinter = new WarningPrinter(new PrintWriter(new StringWriter()));
    ClassVisitor modifiedClassVisitor = mock(ClassVisitor.class);

    // Act
    MethodReplacement actualReplaceResult = (new AbstractAPIConverter(programClassPool, libraryClassPool,
        warningPrinter, modifiedClassVisitor, new DuplicateInitializerInvocationFixer())).replace("Class Name",
            "Method Name", "Method Desc", "<1>", "Replacement Method Name", "Replacement Method Desc");

    // Assert
    assertTrue(actualReplaceResult.classNameMatcher instanceof FixedStringMatcher);
    assertEquals("<1>", actualReplaceResult.replacementClassName);
    assertEquals("Class Name", actualReplaceResult.matchingClassName);
    assertEquals("Replacement Method Desc", actualReplaceResult.replacementMethodDesc);
    assertEquals("Replacement Method Name", actualReplaceResult.replacementMethodName);
  }

  /**
   * Test {@link AbstractAPIConverter#replace(String, String)} with {@code className}, {@code replacementClassName}.
   * <p>
   * Method under test: {@link AbstractAPIConverter#replace(String, String)}
   */
  @Test
  @DisplayName("Test replace(String, String) with 'className', 'replacementClassName'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"TypeReplacement AbstractAPIConverter.replace(String, String)"})
  void testReplaceWithClassNameReplacementClassName() {
    // Arrange
    ClassPool programClassPool = new ClassPool();
    ClassPool libraryClassPool = new ClassPool();
    WarningPrinter warningPrinter = new WarningPrinter(new PrintWriter(new StringWriter()));
    ClassVisitor modifiedClassVisitor = mock(ClassVisitor.class);

    // Act
    TypeReplacement actualReplaceResult = (new AbstractAPIConverter(programClassPool, libraryClassPool, warningPrinter,
        modifiedClassVisitor, new DuplicateInitializerInvocationFixer())).replace("Class Name",
            "Replacement Class Name");

    // Assert
    assertTrue(actualReplaceResult.classNameMatcher instanceof FixedStringMatcher);
    assertEquals("Class Name", actualReplaceResult.matchingClassName);
    assertNull(actualReplaceResult.replacementClassName);
  }

  /**
   * Test {@link AbstractAPIConverter#replace(String, String)} with {@code className}, {@code replacementClassName}.
   * <p>
   * Method under test: {@link AbstractAPIConverter#replace(String, String)}
   */
  @Test
  @DisplayName("Test replace(String, String) with 'className', 'replacementClassName'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"TypeReplacement AbstractAPIConverter.replace(String, String)"})
  void testReplaceWithClassNameReplacementClassName2() {
    // Arrange
    ClassPool programClassPool = new ClassPool();
    ClassPool libraryClassPool = new ClassPool();
    WarningPrinter warningPrinter = new WarningPrinter(new PrintWriter(new StringWriter()));
    ClassVisitor modifiedClassVisitor = mock(ClassVisitor.class);

    // Act
    TypeReplacement actualReplaceResult = (new AbstractAPIConverter(programClassPool, libraryClassPool, warningPrinter,
        modifiedClassVisitor, new DuplicateInitializerInvocationFixer())).replace("*", "Replacement Class Name");

    // Assert
    assertTrue(actualReplaceResult.classNameMatcher instanceof VariableStringMatcher);
    assertEquals("*", actualReplaceResult.matchingClassName);
    assertNull(actualReplaceResult.replacementClassName);
  }

  /**
   * Test {@link AbstractAPIConverter#replace(String, String)} with {@code className}, {@code replacementClassName}.
   * <p>
   * Method under test: {@link AbstractAPIConverter#replace(String, String)}
   */
  @Test
  @DisplayName("Test replace(String, String) with 'className', 'replacementClassName'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"TypeReplacement AbstractAPIConverter.replace(String, String)"})
  void testReplaceWithClassNameReplacementClassName3() {
    // Arrange
    ClassPool programClassPool = new ClassPool();
    ClassPool libraryClassPool = new ClassPool();
    WarningPrinter warningPrinter = new WarningPrinter(new PrintWriter(new StringWriter()));
    ClassVisitor modifiedClassVisitor = mock(ClassVisitor.class);

    // Act
    TypeReplacement actualReplaceResult = (new AbstractAPIConverter(programClassPool, libraryClassPool, warningPrinter,
        modifiedClassVisitor, new DuplicateInitializerInvocationFixer())).replace("Class Name", "*");

    // Assert
    assertTrue(actualReplaceResult.classNameMatcher instanceof FixedStringMatcher);
    assertEquals("*", actualReplaceResult.replacementClassName);
    assertEquals("Class Name", actualReplaceResult.matchingClassName);
  }

  /**
   * Test {@link AbstractAPIConverter#replace(String, String)} with {@code className}, {@code replacementClassName}.
   * <ul>
   *   <li>Then return {@link TypeReplacement#replacementClassName} is {@code <1>}.</li>
   * </ul>
   * <p>
   * Method under test: {@link AbstractAPIConverter#replace(String, String)}
   */
  @Test
  @DisplayName("Test replace(String, String) with 'className', 'replacementClassName'; then return replacementClassName is '<1>'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"TypeReplacement AbstractAPIConverter.replace(String, String)"})
  void testReplaceWithClassNameReplacementClassName_thenReturnReplacementClassNameIs1() {
    // Arrange
    ClassPool programClassPool = new ClassPool();
    ClassPool libraryClassPool = new ClassPool();
    WarningPrinter warningPrinter = new WarningPrinter(new PrintWriter(new StringWriter()));
    ClassVisitor modifiedClassVisitor = mock(ClassVisitor.class);

    // Act
    TypeReplacement actualReplaceResult = (new AbstractAPIConverter(programClassPool, libraryClassPool, warningPrinter,
        modifiedClassVisitor, new DuplicateInitializerInvocationFixer())).replace("Class Name", "<1>");

    // Assert
    assertTrue(actualReplaceResult.classNameMatcher instanceof FixedStringMatcher);
    assertEquals("<1>", actualReplaceResult.replacementClassName);
    assertEquals("Class Name", actualReplaceResult.matchingClassName);
  }

  /**
   * Test {@link AbstractAPIConverter#missing(String, String, String)} with {@code className}, {@code methodName}, {@code methodDesc}.
   * <p>
   * Method under test: {@link AbstractAPIConverter#missing(String, String, String)}
   */
  @Test
  @DisplayName("Test missing(String, String, String) with 'className', 'methodName', 'methodDesc'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"MethodReplacement AbstractAPIConverter.missing(String, String, String)"})
  void testMissingWithClassNameMethodNameMethodDesc() {
    // Arrange
    ClassPool programClassPool = new ClassPool();
    ClassPool libraryClassPool = new ClassPool();
    WarningPrinter warningPrinter = new WarningPrinter(new PrintWriter(new StringWriter()));
    ClassVisitor modifiedClassVisitor = mock(ClassVisitor.class);

    // Act
    MethodReplacement actualMissingResult = (new AbstractAPIConverter(programClassPool, libraryClassPool,
        warningPrinter, modifiedClassVisitor, new DuplicateInitializerInvocationFixer())).missing("Class Name",
            "Method Name", "Method Desc");

    // Assert
    assertTrue(actualMissingResult.classNameMatcher instanceof FixedStringMatcher);
    assertTrue(actualMissingResult.descMatcher instanceof FixedStringMatcher);
    assertTrue(actualMissingResult.methodNameMatcher instanceof FixedStringMatcher);
    assertEquals("Class Name", actualMissingResult.matchingClassName);
    assertEquals("Method Desc", actualMissingResult.matchingMethodDesc);
    assertEquals("Method Name", actualMissingResult.matchingMethodName);
  }

  /**
   * Test {@link AbstractAPIConverter#missing(String, String, String)} with {@code className}, {@code methodName}, {@code methodDesc}.
   * <p>
   * Method under test: {@link AbstractAPIConverter#missing(String, String, String)}
   */
  @Test
  @DisplayName("Test missing(String, String, String) with 'className', 'methodName', 'methodDesc'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"MethodReplacement AbstractAPIConverter.missing(String, String, String)"})
  void testMissingWithClassNameMethodNameMethodDesc2() {
    // Arrange
    ClassPool programClassPool = new ClassPool();
    ClassPool libraryClassPool = new ClassPool();
    WarningPrinter warningPrinter = new WarningPrinter(new PrintWriter(new StringWriter()));
    ClassVisitor modifiedClassVisitor = mock(ClassVisitor.class);

    // Act
    MethodReplacement actualMissingResult = (new AbstractAPIConverter(programClassPool, libraryClassPool,
        warningPrinter, modifiedClassVisitor, new DuplicateInitializerInvocationFixer())).missing("**", "Method Name",
            "Method Desc");

    // Assert
    assertTrue(actualMissingResult.descMatcher instanceof FixedStringMatcher);
    assertTrue(actualMissingResult.methodNameMatcher instanceof FixedStringMatcher);
    assertTrue(actualMissingResult.classNameMatcher instanceof VariableStringMatcher);
    assertEquals("**", actualMissingResult.matchingClassName);
    assertEquals("Method Desc", actualMissingResult.matchingMethodDesc);
    assertEquals("Method Name", actualMissingResult.matchingMethodName);
  }

  /**
   * Test {@link AbstractAPIConverter#missing(String, String, String)} with {@code className}, {@code methodName}, {@code methodDesc}.
   * <p>
   * Method under test: {@link AbstractAPIConverter#missing(String, String, String)}
   */
  @Test
  @DisplayName("Test missing(String, String, String) with 'className', 'methodName', 'methodDesc'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"MethodReplacement AbstractAPIConverter.missing(String, String, String)"})
  void testMissingWithClassNameMethodNameMethodDesc3() {
    // Arrange
    ClassPool programClassPool = new ClassPool();
    ClassPool libraryClassPool = new ClassPool();
    WarningPrinter warningPrinter = new WarningPrinter(new PrintWriter(new StringWriter()));
    ClassVisitor modifiedClassVisitor = mock(ClassVisitor.class);

    // Act
    MethodReplacement actualMissingResult = (new AbstractAPIConverter(programClassPool, libraryClassPool,
        warningPrinter, modifiedClassVisitor, new DuplicateInitializerInvocationFixer())).missing("Class Name", "**",
            "Method Desc");

    // Assert
    assertTrue(actualMissingResult.classNameMatcher instanceof FixedStringMatcher);
    assertTrue(actualMissingResult.descMatcher instanceof FixedStringMatcher);
    assertTrue(actualMissingResult.methodNameMatcher instanceof VariableStringMatcher);
    assertEquals("**", actualMissingResult.matchingMethodName);
    assertEquals("Class Name", actualMissingResult.matchingClassName);
    assertEquals("Method Desc", actualMissingResult.matchingMethodDesc);
  }

  /**
   * Test {@link AbstractAPIConverter#missing(String, String, String)} with {@code className}, {@code methodName}, {@code methodDesc}.
   * <p>
   * Method under test: {@link AbstractAPIConverter#missing(String, String, String)}
   */
  @Test
  @DisplayName("Test missing(String, String, String) with 'className', 'methodName', 'methodDesc'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"MethodReplacement AbstractAPIConverter.missing(String, String, String)"})
  void testMissingWithClassNameMethodNameMethodDesc4() {
    // Arrange
    ClassPool programClassPool = new ClassPool();
    ClassPool libraryClassPool = new ClassPool();
    WarningPrinter warningPrinter = new WarningPrinter(new PrintWriter(new StringWriter()));
    ClassVisitor modifiedClassVisitor = mock(ClassVisitor.class);

    // Act
    MethodReplacement actualMissingResult = (new AbstractAPIConverter(programClassPool, libraryClassPool,
        warningPrinter, modifiedClassVisitor, new DuplicateInitializerInvocationFixer())).missing("Class Name",
            "Method Name", "**");

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
   * <ul>
   *   <li>Then {@link TypeReplacement#classNameMatcher} return {@link EmptyStringMatcher}.</li>
   * </ul>
   * <p>
   * Method under test: {@link AbstractAPIConverter#missing(String)}
   */
  @Test
  @DisplayName("Test missing(String) with 'className'; then classNameMatcher return EmptyStringMatcher")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"TypeReplacement AbstractAPIConverter.missing(String)"})
  void testMissingWithClassName_thenClassNameMatcherReturnEmptyStringMatcher() {
    // Arrange
    ClassPool programClassPool = new ClassPool();
    ClassPool libraryClassPool = new ClassPool();
    WarningPrinter warningPrinter = new WarningPrinter(new PrintWriter(new StringWriter()));
    ClassVisitor modifiedClassVisitor = mock(ClassVisitor.class);

    // Act
    TypeReplacement actualMissingResult = (new AbstractAPIConverter(programClassPool, libraryClassPool, warningPrinter,
        modifiedClassVisitor, new DuplicateInitializerInvocationFixer())).missing("");

    // Assert
    assertTrue(actualMissingResult.classNameMatcher instanceof EmptyStringMatcher);
    assertEquals("", actualMissingResult.matchingClassName);
    assertNull(actualMissingResult.replacementClassName);
  }

  /**
   * Test {@link AbstractAPIConverter#missing(String)} with {@code className}.
   * <ul>
   *   <li>Then {@link TypeReplacement#classNameMatcher} return {@link FixedStringMatcher}.</li>
   * </ul>
   * <p>
   * Method under test: {@link AbstractAPIConverter#missing(String)}
   */
  @Test
  @DisplayName("Test missing(String) with 'className'; then classNameMatcher return FixedStringMatcher")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"TypeReplacement AbstractAPIConverter.missing(String)"})
  void testMissingWithClassName_thenClassNameMatcherReturnFixedStringMatcher() {
    // Arrange
    ClassPool programClassPool = new ClassPool();
    ClassPool libraryClassPool = new ClassPool();
    WarningPrinter warningPrinter = new WarningPrinter(new PrintWriter(new StringWriter()));
    ClassVisitor modifiedClassVisitor = mock(ClassVisitor.class);

    // Act
    TypeReplacement actualMissingResult = (new AbstractAPIConverter(programClassPool, libraryClassPool, warningPrinter,
        modifiedClassVisitor, new DuplicateInitializerInvocationFixer())).missing("Class Name");

    // Assert
    assertTrue(actualMissingResult.classNameMatcher instanceof FixedStringMatcher);
    assertEquals("Class Name", actualMissingResult.matchingClassName);
    assertNull(actualMissingResult.replacementClassName);
  }

  /**
   * Test TypeReplacement {@link TypeReplacement#isValid()}.
   * <p>
   * Method under test: {@link TypeReplacement#isValid()}
   */
  @Test
  @DisplayName("Test TypeReplacement isValid()")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean TypeReplacement.isValid()"})
  void testTypeReplacementIsValid() {
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
   * Test TypeReplacement {@link TypeReplacement#isValid()}.
   * <p>
   * Method under test: {@link TypeReplacement#isValid()}
   */
  @Test
  @DisplayName("Test TypeReplacement isValid()")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean TypeReplacement.isValid()"})
  void testTypeReplacementIsValid2() {
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
   * Test TypeReplacement {@link TypeReplacement#isValid()}.
   * <ul>
   *   <li>Then return {@code false}.</li>
   * </ul>
   * <p>
   * Method under test: {@link TypeReplacement#isValid()}
   */
  @Test
  @DisplayName("Test TypeReplacement isValid(); then return 'false'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean TypeReplacement.isValid()"})
  void testTypeReplacementIsValid_thenReturnFalse() {
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
   * Test TypeReplacement {@link TypeReplacement#matchesClassName(String)}.
   * <p>
   * Method under test: {@link TypeReplacement#matchesClassName(String)}
   */
  @Test
  @DisplayName("Test TypeReplacement matchesClassName(String)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean TypeReplacement.matchesClassName(String)"})
  void testTypeReplacementMatchesClassName() {
    // Arrange
    ClassPool programClassPool = new ClassPool();
    ClassPool libraryClassPool = new ClassPool();
    WarningPrinter warningPrinter = new WarningPrinter(new PrintWriter(new StringWriter()));
    ClassVisitor modifiedClassVisitor = mock(ClassVisitor.class);
    TypeReplacement typeReplacement = (new AbstractAPIConverter(programClassPool, libraryClassPool, warningPrinter,
        modifiedClassVisitor, new DuplicateInitializerInvocationFixer())).new TypeReplacement("*",
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
   * Test TypeReplacement {@link TypeReplacement#matchesClassName(String)}.
   * <ul>
   *   <li>Then return {@code false}.</li>
   * </ul>
   * <p>
   * Method under test: {@link TypeReplacement#matchesClassName(String)}
   */
  @Test
  @DisplayName("Test TypeReplacement matchesClassName(String); then return 'false'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean TypeReplacement.matchesClassName(String)"})
  void testTypeReplacementMatchesClassName_thenReturnFalse() {
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
   * Test TypeReplacement {@link TypeReplacement#TypeReplacement(AbstractAPIConverter, String, String)}.
   * <p>
   * Method under test: {@link TypeReplacement#TypeReplacement(AbstractAPIConverter, String, String)}
   */
  @Test
  @DisplayName("Test TypeReplacement new TypeReplacement(AbstractAPIConverter, String, String)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void TypeReplacement.<init>(AbstractAPIConverter, String, String)"})
  void testTypeReplacementNewTypeReplacement() {
    // Arrange
    ClassPool programClassPool = new ClassPool();
    ClassPool libraryClassPool = new ClassPool();
    WarningPrinter warningPrinter = new WarningPrinter(new PrintWriter(new StringWriter()));
    ClassVisitor modifiedClassVisitor = mock(ClassVisitor.class);

    // Act
    TypeReplacement actualTypeReplacement = (new AbstractAPIConverter(programClassPool, libraryClassPool,
        warningPrinter, modifiedClassVisitor, new DuplicateInitializerInvocationFixer())).new TypeReplacement(
            "Matching Class Name", "Replacement Class Name");

    // Assert
    assertTrue(actualTypeReplacement.classNameMatcher instanceof FixedStringMatcher);
    assertEquals("Matching Class Name", actualTypeReplacement.matchingClassName);
    assertEquals("Replacement Class Name", actualTypeReplacement.replacementClassName);
  }

  /**
   * Test TypeReplacement {@link TypeReplacement#TypeReplacement(AbstractAPIConverter, String, String)}.
   * <p>
   * Method under test: {@link TypeReplacement#TypeReplacement(AbstractAPIConverter, String, String)}
   */
  @Test
  @DisplayName("Test TypeReplacement new TypeReplacement(AbstractAPIConverter, String, String)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void TypeReplacement.<init>(AbstractAPIConverter, String, String)"})
  void testTypeReplacementNewTypeReplacement2() {
    // Arrange
    ClassPool programClassPool = new ClassPool();
    ClassPool libraryClassPool = new ClassPool();
    WarningPrinter warningPrinter = new WarningPrinter(new PrintWriter(new StringWriter()));
    ClassVisitor modifiedClassVisitor = mock(ClassVisitor.class);

    // Act
    TypeReplacement actualTypeReplacement = (new AbstractAPIConverter(programClassPool, libraryClassPool,
        warningPrinter, modifiedClassVisitor, new DuplicateInitializerInvocationFixer())).new TypeReplacement("*",
            "Replacement Class Name");

    // Assert
    assertTrue(actualTypeReplacement.classNameMatcher instanceof VariableStringMatcher);
    assertEquals("*", actualTypeReplacement.matchingClassName);
    assertEquals("Replacement Class Name", actualTypeReplacement.replacementClassName);
  }

  /**
   * Test TypeReplacement {@link TypeReplacement#replaceClassName(Clazz, String)}.
   * <p>
   * Method under test: {@link TypeReplacement#replaceClassName(Clazz, String)}
   */
  @Test
  @DisplayName("Test TypeReplacement replaceClassName(Clazz, String)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"String TypeReplacement.replaceClassName(Clazz, String)"})
  void testTypeReplacementReplaceClassName() {
    // Arrange
    ClassPool programClassPool = new ClassPool();
    ClassPool libraryClassPool = new ClassPool();
    WarningPrinter warningPrinter = new WarningPrinter(new PrintWriter(new StringWriter()));
    ClassVisitor modifiedClassVisitor = mock(ClassVisitor.class);
    TypeReplacement typeReplacement = (new AbstractAPIConverter(programClassPool, libraryClassPool, warningPrinter,
        modifiedClassVisitor, new DuplicateInitializerInvocationFixer())).new TypeReplacement("Matching Class Name",
            "Replacement Class Name");

    // Act and Assert
    assertEquals("Replacement Class Name", typeReplacement.replaceClassName(new LibraryClass(), "Class Name"));
  }

  /**
   * Test {@link AbstractAPIConverter#visitProgramClass(ProgramClass)}.
   * <ul>
   *   <li>Then calls {@link ProgramClass#attributesAccept(AttributeVisitor)}.</li>
   * </ul>
   * <p>
   * Method under test: {@link AbstractAPIConverter#visitProgramClass(ProgramClass)}
   */
  @Test
  @DisplayName("Test visitProgramClass(ProgramClass); then calls attributesAccept(AttributeVisitor)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void AbstractAPIConverter.visitProgramClass(ProgramClass)"})
  void testVisitProgramClass_thenCallsAttributesAccept() {
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
   * Test {@link AbstractAPIConverter#visitProgramField(ProgramClass, ProgramField)}.
   * <p>
   * Method under test: {@link AbstractAPIConverter#visitProgramField(ProgramClass, ProgramField)}
   */
  @Test
  @DisplayName("Test visitProgramField(ProgramClass, ProgramField)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void AbstractAPIConverter.visitProgramField(ProgramClass, ProgramField)"})
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
    abstractAPIConverter.visitProgramField(programClass, new NamedField("String", "String"));

    // Assert
    verify(programClass).getString(eq(0));
  }

  /**
   * Test {@link AbstractAPIConverter#visitProgramField(ProgramClass, ProgramField)}.
   * <ul>
   *   <li>Given {@code String}.</li>
   *   <li>When {@link ProgramField#ProgramField()}.</li>
   *   <li>Then calls {@link ProgramClass#getString(int)}.</li>
   * </ul>
   * <p>
   * Method under test: {@link AbstractAPIConverter#visitProgramField(ProgramClass, ProgramField)}
   */
  @Test
  @DisplayName("Test visitProgramField(ProgramClass, ProgramField); given 'String'; when ProgramField(); then calls getString(int)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void AbstractAPIConverter.visitProgramField(ProgramClass, ProgramField)"})
  void testVisitProgramField_givenString_whenProgramField_thenCallsGetString() {
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
   * Test {@link AbstractAPIConverter#visitProgramMethod(ProgramClass, ProgramMethod)}.
   * <p>
   * Method under test: {@link AbstractAPIConverter#visitProgramMethod(ProgramClass, ProgramMethod)}
   */
  @Test
  @DisplayName("Test visitProgramMethod(ProgramClass, ProgramMethod)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void AbstractAPIConverter.visitProgramMethod(ProgramClass, ProgramMethod)"})
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
    abstractAPIConverter.visitProgramMethod(programClass, new NamedMember("String", "String"));

    // Assert
    verify(programClass).getString(eq(0));
  }

  /**
   * Test {@link AbstractAPIConverter#visitProgramMethod(ProgramClass, ProgramMethod)}.
   * <ul>
   *   <li>Given {@code String}.</li>
   *   <li>When {@link ProgramMethod#ProgramMethod()}.</li>
   *   <li>Then calls {@link ProgramClass#getString(int)}.</li>
   * </ul>
   * <p>
   * Method under test: {@link AbstractAPIConverter#visitProgramMethod(ProgramClass, ProgramMethod)}
   */
  @Test
  @DisplayName("Test visitProgramMethod(ProgramClass, ProgramMethod); given 'String'; when ProgramMethod(); then calls getString(int)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void AbstractAPIConverter.visitProgramMethod(ProgramClass, ProgramMethod)"})
  void testVisitProgramMethod_givenString_whenProgramMethod_thenCallsGetString() {
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
   * Test {@link AbstractAPIConverter#visitCodeAttribute(Clazz, Method, CodeAttribute)}.
   * <ul>
   *   <li>Then calls {@link CodeAttribute#attributesAccept(Clazz, Method, AttributeVisitor)}.</li>
   * </ul>
   * <p>
   * Method under test: {@link AbstractAPIConverter#visitCodeAttribute(Clazz, Method, CodeAttribute)}
   */
  @Test
  @DisplayName("Test visitCodeAttribute(Clazz, Method, CodeAttribute); then calls attributesAccept(Clazz, Method, AttributeVisitor)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void AbstractAPIConverter.visitCodeAttribute(Clazz, Method, CodeAttribute)"})
  void testVisitCodeAttribute_thenCallsAttributesAccept() {
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
   * Test {@link AbstractAPIConverter#visitSignatureAttribute(Clazz, SignatureAttribute)} with {@code clazz}, {@code signatureAttribute}.
   * <ul>
   *   <li>Then calls {@link LibraryClass#getString(int)}.</li>
   * </ul>
   * <p>
   * Method under test: {@link AbstractAPIConverter#visitSignatureAttribute(Clazz, SignatureAttribute)}
   */
  @Test
  @DisplayName("Test visitSignatureAttribute(Clazz, SignatureAttribute) with 'clazz', 'signatureAttribute'; then calls getString(int)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void AbstractAPIConverter.visitSignatureAttribute(Clazz, SignatureAttribute)"})
  void testVisitSignatureAttributeWithClazzSignatureAttribute_thenCallsGetString() {
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
   * Test {@link AbstractAPIConverter#visitAnnotationDefaultAttribute(Clazz, Method, AnnotationDefaultAttribute)}.
   * <ul>
   *   <li>Then calls {@link AnnotationDefaultAttribute#defaultValueAccept(Clazz, ElementValueVisitor)}.</li>
   * </ul>
   * <p>
   * Method under test: {@link AbstractAPIConverter#visitAnnotationDefaultAttribute(Clazz, Method, AnnotationDefaultAttribute)}
   */
  @Test
  @DisplayName("Test visitAnnotationDefaultAttribute(Clazz, Method, AnnotationDefaultAttribute); then calls defaultValueAccept(Clazz, ElementValueVisitor)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "void AbstractAPIConverter.visitAnnotationDefaultAttribute(Clazz, Method, AnnotationDefaultAttribute)"})
  void testVisitAnnotationDefaultAttribute_thenCallsDefaultValueAccept() {
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
   * Test {@link AbstractAPIConverter#visitLocalVariableInfo(Clazz, Method, CodeAttribute, LocalVariableInfo)}.
   * <ul>
   *   <li>Given {@code String}.</li>
   *   <li>Then calls {@link LibraryClass#getString(int)}.</li>
   * </ul>
   * <p>
   * Method under test: {@link AbstractAPIConverter#visitLocalVariableInfo(Clazz, Method, CodeAttribute, LocalVariableInfo)}
   */
  @Test
  @DisplayName("Test visitLocalVariableInfo(Clazz, Method, CodeAttribute, LocalVariableInfo); given 'String'; then calls getString(int)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "void AbstractAPIConverter.visitLocalVariableInfo(Clazz, Method, CodeAttribute, LocalVariableInfo)"})
  void testVisitLocalVariableInfo_givenString_thenCallsGetString() {
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
   * Test {@link AbstractAPIConverter#visitLocalVariableTypeInfo(Clazz, Method, CodeAttribute, LocalVariableTypeInfo)}.
   * <ul>
   *   <li>Given {@code String}.</li>
   *   <li>Then calls {@link LibraryClass#getString(int)}.</li>
   * </ul>
   * <p>
   * Method under test: {@link AbstractAPIConverter#visitLocalVariableTypeInfo(Clazz, Method, CodeAttribute, LocalVariableTypeInfo)}
   */
  @Test
  @DisplayName("Test visitLocalVariableTypeInfo(Clazz, Method, CodeAttribute, LocalVariableTypeInfo); given 'String'; then calls getString(int)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "void AbstractAPIConverter.visitLocalVariableTypeInfo(Clazz, Method, CodeAttribute, LocalVariableTypeInfo)"})
  void testVisitLocalVariableTypeInfo_givenString_thenCallsGetString() {
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
   * Test {@link AbstractAPIConverter#visitAnnotation(Clazz, Annotation)} with {@code clazz}, {@code annotation}.
   * <ul>
   *   <li>Given {@code String}.</li>
   *   <li>Then calls {@link LibraryClass#getString(int)}.</li>
   * </ul>
   * <p>
   * Method under test: {@link AbstractAPIConverter#visitAnnotation(Clazz, Annotation)}
   */
  @Test
  @DisplayName("Test visitAnnotation(Clazz, Annotation) with 'clazz', 'annotation'; given 'String'; then calls getString(int)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void AbstractAPIConverter.visitAnnotation(Clazz, Annotation)"})
  void testVisitAnnotationWithClazzAnnotation_givenString_thenCallsGetString() {
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
   * Test {@link AbstractAPIConverter#visitAnnotation(Clazz, Annotation)} with {@code clazz}, {@code annotation}.
   * <ul>
   *   <li>Then calls {@link Annotation#elementValuesAccept(Clazz, ElementValueVisitor)}.</li>
   * </ul>
   * <p>
   * Method under test: {@link AbstractAPIConverter#visitAnnotation(Clazz, Annotation)}
   */
  @Test
  @DisplayName("Test visitAnnotation(Clazz, Annotation) with 'clazz', 'annotation'; then calls elementValuesAccept(Clazz, ElementValueVisitor)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void AbstractAPIConverter.visitAnnotation(Clazz, Annotation)"})
  void testVisitAnnotationWithClazzAnnotation_thenCallsElementValuesAccept() {
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
   * Test {@link AbstractAPIConverter#visitEnumConstantElementValue(Clazz, Annotation, EnumConstantElementValue)}.
   * <ul>
   *   <li>Given {@code String}.</li>
   *   <li>Then calls {@link LibraryClass#getString(int)}.</li>
   * </ul>
   * <p>
   * Method under test: {@link AbstractAPIConverter#visitEnumConstantElementValue(Clazz, Annotation, EnumConstantElementValue)}
   */
  @Test
  @DisplayName("Test visitEnumConstantElementValue(Clazz, Annotation, EnumConstantElementValue); given 'String'; then calls getString(int)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "void AbstractAPIConverter.visitEnumConstantElementValue(Clazz, Annotation, EnumConstantElementValue)"})
  void testVisitEnumConstantElementValue_givenString_thenCallsGetString() {
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
   * Test {@link AbstractAPIConverter#visitClassElementValue(Clazz, Annotation, ClassElementValue)}.
   * <ul>
   *   <li>Then calls {@link LibraryClass#getString(int)}.</li>
   * </ul>
   * <p>
   * Method under test: {@link AbstractAPIConverter#visitClassElementValue(Clazz, Annotation, ClassElementValue)}
   */
  @Test
  @DisplayName("Test visitClassElementValue(Clazz, Annotation, ClassElementValue); then calls getString(int)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void AbstractAPIConverter.visitClassElementValue(Clazz, Annotation, ClassElementValue)"})
  void testVisitClassElementValue_thenCallsGetString() {
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
   * Test {@link AbstractAPIConverter#visitAnnotationElementValue(Clazz, Annotation, AnnotationElementValue)}.
   * <ul>
   *   <li>Then calls {@link AnnotationElementValue#annotationAccept(Clazz, AnnotationVisitor)}.</li>
   * </ul>
   * <p>
   * Method under test: {@link AbstractAPIConverter#visitAnnotationElementValue(Clazz, Annotation, AnnotationElementValue)}
   */
  @Test
  @DisplayName("Test visitAnnotationElementValue(Clazz, Annotation, AnnotationElementValue); then calls annotationAccept(Clazz, AnnotationVisitor)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "void AbstractAPIConverter.visitAnnotationElementValue(Clazz, Annotation, AnnotationElementValue)"})
  void testVisitAnnotationElementValue_thenCallsAnnotationAccept() {
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
   * Test {@link AbstractAPIConverter#visitClassConstant(Clazz, ClassConstant)}.
   * <ul>
   *   <li>Then calls {@link LibraryClass#getString(int)}.</li>
   * </ul>
   * <p>
   * Method under test: {@link AbstractAPIConverter#visitClassConstant(Clazz, ClassConstant)}
   */
  @Test
  @DisplayName("Test visitClassConstant(Clazz, ClassConstant); then calls getString(int)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void AbstractAPIConverter.visitClassConstant(Clazz, ClassConstant)"})
  void testVisitClassConstant_thenCallsGetString() {
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
   * Test {@link AbstractAPIConverter#visitFieldrefConstant(Clazz, FieldrefConstant)}.
   * <ul>
   *   <li>Given {@code foo}.</li>
   *   <li>Then calls {@link LibraryClass#getName(int)}.</li>
   * </ul>
   * <p>
   * Method under test: {@link AbstractAPIConverter#visitFieldrefConstant(Clazz, FieldrefConstant)}
   */
  @Test
  @DisplayName("Test visitFieldrefConstant(Clazz, FieldrefConstant); given 'foo'; then calls getName(int)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void AbstractAPIConverter.visitFieldrefConstant(Clazz, FieldrefConstant)"})
  void testVisitFieldrefConstant_givenFoo_thenCallsGetName() {
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
   * Test {@link AbstractAPIConverter#visitAnyMethodrefConstant(Clazz, AnyMethodrefConstant)}.
   * <ul>
   *   <li>Given {@code foo}.</li>
   *   <li>Then calls {@link LibraryClass#getClassName(int)}.</li>
   * </ul>
   * <p>
   * Method under test: {@link AbstractAPIConverter#visitAnyMethodrefConstant(Clazz, AnyMethodrefConstant)}
   */
  @Test
  @DisplayName("Test visitAnyMethodrefConstant(Clazz, AnyMethodrefConstant); given 'foo'; then calls getClassName(int)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void AbstractAPIConverter.visitAnyMethodrefConstant(Clazz, AnyMethodrefConstant)"})
  void testVisitAnyMethodrefConstant_givenFoo_thenCallsGetClassName() {
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
}
