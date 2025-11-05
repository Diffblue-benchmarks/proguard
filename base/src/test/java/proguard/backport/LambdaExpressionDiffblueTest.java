package proguard.backport;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;
import proguard.classfile.Clazz;
import proguard.classfile.LibraryClass;
import proguard.classfile.LibraryMethod;
import proguard.classfile.Method;
import proguard.classfile.ProgramClass;
import proguard.classfile.attribute.BootstrapMethodInfo;
import proguard.testutils.cpa.NamedClass;

class LambdaExpressionDiffblueTest {
  /**
   * Method under test: {@link LambdaExpression#getLambdaClassName()}
   */
  @Test
  void testGetLambdaClassName() {
    // Arrange
    NamedClass referencedClass = new NamedClass("%s$$Lambda$%d");
    BootstrapMethodInfo bootstrapMethodInfo = new BootstrapMethodInfo();
    LibraryClass referencedInvokedClass = new LibraryClass();

    // Act and Assert
    assertEquals("%s$$Lambda$%d$$Lambda$1",
        (new LambdaExpression(referencedClass, 1, bootstrapMethodInfo, "Factory Method Descriptor",
            new String[]{"Interfaces"}, new String[]{"Bridge Method Descriptors"}, "Interface Method",
            "Interface Method Descriptor", 1, "Invoked Class Name", "Invoked Method Name", "Invoked Method Desc",
            referencedInvokedClass, new LibraryMethod(1, "Name", "Descriptor"))).getLambdaClassName());
  }

  /**
   * Method under test: {@link LambdaExpression#isSerializable()}
   */
  @Test
  void testIsSerializable() {
    // Arrange
    ProgramClass referencedClass = new ProgramClass();
    BootstrapMethodInfo bootstrapMethodInfo = new BootstrapMethodInfo();
    LibraryClass referencedInvokedClass = new LibraryClass();

    // Act and Assert
    assertFalse((new LambdaExpression(referencedClass, 1, bootstrapMethodInfo, "Factory Method Descriptor",
        new String[]{"Interfaces"}, new String[]{"Bridge Method Descriptors"}, "Interface Method",
        "Interface Method Descriptor", 1, "Invoked Class Name", "Invoked Method Name", "Invoked Method Desc",
        referencedInvokedClass, new LibraryMethod(1, "Name", "Descriptor"))).isSerializable());
  }

  /**
   * Method under test: {@link LambdaExpression#isSerializable()}
   */
  @Test
  void testIsSerializable2() {
    // Arrange
    ProgramClass referencedClass = new ProgramClass();
    BootstrapMethodInfo bootstrapMethodInfo = new BootstrapMethodInfo();
    LibraryClass referencedInvokedClass = new LibraryClass();

    // Act and Assert
    assertTrue((new LambdaExpression(referencedClass, 1, bootstrapMethodInfo, "Factory Method Descriptor",
        new String[]{"java/io/Serializable"}, new String[]{"Bridge Method Descriptors"}, "Interface Method",
        "Interface Method Descriptor", 1, "Invoked Class Name", "Invoked Method Name", "Invoked Method Desc",
        referencedInvokedClass, new LibraryMethod(1, "Name", "Descriptor"))).isSerializable());
  }

  /**
   * Method under test: {@link LambdaExpression#isMethodReference()}
   */
  @Test
  void testIsMethodReference() {
    // Arrange
    ProgramClass referencedClass = new ProgramClass();
    BootstrapMethodInfo bootstrapMethodInfo = new BootstrapMethodInfo();
    LibraryClass referencedInvokedClass = new LibraryClass();

    // Act and Assert
    assertTrue((new LambdaExpression(referencedClass, 1, bootstrapMethodInfo, "Factory Method Descriptor",
        new String[]{"Interfaces"}, new String[]{"Bridge Method Descriptors"}, "Interface Method",
        "Interface Method Descriptor", 1, "Invoked Class Name", "Invoked Method Name", "Invoked Method Desc",
        referencedInvokedClass, new LibraryMethod(1, "Name", "Descriptor"))).isMethodReference());
  }

  /**
   * Method under test: {@link LambdaExpression#isMethodReference()}
   */
  @Test
  void testIsMethodReference2() {
    // Arrange
    ProgramClass referencedClass = new ProgramClass();
    BootstrapMethodInfo bootstrapMethodInfo = new BootstrapMethodInfo();
    LibraryClass referencedInvokedClass = new LibraryClass();
    LambdaExpression lambdaExpression = new LambdaExpression(referencedClass, 1, bootstrapMethodInfo,
        "Factory Method Descriptor", new String[]{"Interfaces"}, new String[]{"Bridge Method Descriptors"},
        "Interface Method", "Interface Method Descriptor", 1, "Invoked Class Name", "Invoked Method Name",
        "Invoked Method Desc", referencedInvokedClass, new LibraryMethod(1, "Name", "Descriptor"));
    lambdaExpression.invokedMethodName = "lambda$";

    // Act and Assert
    assertFalse(lambdaExpression.isMethodReference());
  }

  /**
   * Method under test: {@link LambdaExpression#invokesStaticInterfaceMethod()}
   */
  @Test
  void testInvokesStaticInterfaceMethod() {
    // Arrange
    ProgramClass referencedClass = new ProgramClass();
    BootstrapMethodInfo bootstrapMethodInfo = new BootstrapMethodInfo();
    LibraryClass referencedInvokedClass = new LibraryClass();

    // Act and Assert
    assertFalse((new LambdaExpression(referencedClass, 1, bootstrapMethodInfo, "Factory Method Descriptor",
        new String[]{"Interfaces"}, new String[]{"Bridge Method Descriptors"}, "Interface Method",
        "Interface Method Descriptor", 1, "Invoked Class Name", "Invoked Method Name", "Invoked Method Desc",
        referencedInvokedClass, new LibraryMethod(1, "Name", "Descriptor"))).invokesStaticInterfaceMethod());
  }

  /**
   * Method under test: {@link LambdaExpression#invokesStaticInterfaceMethod()}
   */
  @Test
  void testInvokesStaticInterfaceMethod2() {
    // Arrange
    ProgramClass referencedClass = new ProgramClass();
    BootstrapMethodInfo bootstrapMethodInfo = new BootstrapMethodInfo();
    LibraryClass referencedInvokedClass = new LibraryClass();
    LambdaExpression lambdaExpression = new LambdaExpression(referencedClass, 1, bootstrapMethodInfo,
        "Factory Method Descriptor", new String[]{"Interfaces"}, new String[]{"Bridge Method Descriptors"},
        "Interface Method", "Interface Method Descriptor", 1, "Invoked Class Name", "Invoked Method Name",
        "Invoked Method Desc", referencedInvokedClass, new LibraryMethod(1, "Name", "Descriptor"));
    lambdaExpression.invokedReferenceKind = 6;
    lambdaExpression.referencedInvokedClass = null;

    // Act and Assert
    assertFalse(lambdaExpression.invokesStaticInterfaceMethod());
  }

  /**
   * Method under test: {@link LambdaExpression#invokesStaticInterfaceMethod()}
   */
  @Test
  void testInvokesStaticInterfaceMethod3() {
    // Arrange
    ProgramClass referencedClass = new ProgramClass();
    BootstrapMethodInfo bootstrapMethodInfo = new BootstrapMethodInfo();
    LibraryClass referencedInvokedClass = new LibraryClass();
    LambdaExpression lambdaExpression = new LambdaExpression(referencedClass, 1, bootstrapMethodInfo,
        "Factory Method Descriptor", new String[]{"Interfaces"}, new String[]{"Bridge Method Descriptors"},
        "Interface Method", "Interface Method Descriptor", 1, "Invoked Class Name", "Invoked Method Name",
        "Invoked Method Desc", referencedInvokedClass, new LibraryMethod(1, "Name", "Descriptor"));
    lambdaExpression.invokedReferenceKind = 6;
    lambdaExpression.referencedInvokedClass = new LibraryClass();

    // Act and Assert
    assertFalse(lambdaExpression.invokesStaticInterfaceMethod());
  }

  /**
   * Method under test:
   * {@link LambdaExpression#referencesPrivateSyntheticInterfaceMethod()}
   */
  @Test
  void testReferencesPrivateSyntheticInterfaceMethod() {
    // Arrange
    ProgramClass referencedClass = new ProgramClass();
    BootstrapMethodInfo bootstrapMethodInfo = new BootstrapMethodInfo();
    LibraryClass referencedInvokedClass = new LibraryClass();

    // Act and Assert
    assertFalse((new LambdaExpression(referencedClass, 1, bootstrapMethodInfo, "Factory Method Descriptor",
        new String[]{"Interfaces"}, new String[]{"Bridge Method Descriptors"}, "Interface Method",
        "Interface Method Descriptor", 1, "Invoked Class Name", "Invoked Method Name", "Invoked Method Desc",
        referencedInvokedClass, new LibraryMethod(1, "Name", "Descriptor")))
            .referencesPrivateSyntheticInterfaceMethod());
  }

  /**
   * Method under test:
   * {@link LambdaExpression#referencesPrivateSyntheticInterfaceMethod()}
   */
  @Test
  void testReferencesPrivateSyntheticInterfaceMethod2() {
    // Arrange
    ProgramClass referencedClass = new ProgramClass();
    BootstrapMethodInfo bootstrapMethodInfo = new BootstrapMethodInfo();
    LibraryClass referencedInvokedClass = new LibraryClass(512, "This Class Name", "Super Class Name");

    // Act and Assert
    assertFalse((new LambdaExpression(referencedClass, 1, bootstrapMethodInfo, "Factory Method Descriptor",
        new String[]{"Interfaces"}, new String[]{"Bridge Method Descriptors"}, "Interface Method",
        "Interface Method Descriptor", 1, "Invoked Class Name", "Invoked Method Name", "Invoked Method Desc",
        referencedInvokedClass, new LibraryMethod(1, "Name", "Descriptor")))
            .referencesPrivateSyntheticInterfaceMethod());
  }

  /**
   * Method under test:
   * {@link LambdaExpression#referencesPrivateSyntheticInterfaceMethod()}
   */
  @Test
  void testReferencesPrivateSyntheticInterfaceMethod3() {
    // Arrange
    ProgramClass referencedClass = new ProgramClass();
    BootstrapMethodInfo bootstrapMethodInfo = new BootstrapMethodInfo();
    LibraryClass referencedInvokedClass = new LibraryClass(512, "This Class Name", "Super Class Name");

    // Act and Assert
    assertTrue((new LambdaExpression(referencedClass, 1, bootstrapMethodInfo, "Factory Method Descriptor",
        new String[]{"Interfaces"}, new String[]{"Bridge Method Descriptors"}, "Interface Method",
        "Interface Method Descriptor", 1, "Invoked Class Name", "Invoked Method Name", "Invoked Method Desc",
        referencedInvokedClass, new LibraryMethod(4098, "Name", "Descriptor")))
            .referencesPrivateSyntheticInterfaceMethod());
  }

  /**
   * Method under test: {@link LambdaExpression#needsAccessorMethod()}
   */
  @Test
  void testNeedsAccessorMethod() {
    // Arrange
    ProgramClass referencedClass = new ProgramClass();
    BootstrapMethodInfo bootstrapMethodInfo = new BootstrapMethodInfo();

    // Act and Assert
    assertFalse((new LambdaExpression(referencedClass, 1, bootstrapMethodInfo, "Factory Method Descriptor",
        new String[]{"Interfaces"}, new String[]{"Bridge Method Descriptors"}, "Interface Method",
        "Interface Method Descriptor", 1, "Invoked Class Name", "Invoked Method Name", "Invoked Method Desc", null,
        new LibraryMethod(1, "Name", "Descriptor"))).needsAccessorMethod());
  }

  /**
   * Method under test: {@link LambdaExpression#needsAccessorMethod()}
   */
  @Test
  void testNeedsAccessorMethod2() {
    // Arrange
    ProgramClass referencedClass = new ProgramClass();
    BootstrapMethodInfo bootstrapMethodInfo = new BootstrapMethodInfo();
    LibraryClass referencedInvokedClass = new LibraryClass(1, "This Class Name", "Super Class Name");

    // Act and Assert
    assertTrue((new LambdaExpression(referencedClass, 1, bootstrapMethodInfo, "Factory Method Descriptor",
        new String[]{"Interfaces"}, new String[]{"Bridge Method Descriptors"}, "Interface Method",
        "Interface Method Descriptor", 1, "Invoked Class Name", "Invoked Method Name", "Invoked Method Desc",
        referencedInvokedClass, new LibraryMethod(1, "Name", "Descriptor"))).needsAccessorMethod());
  }

  /**
   * Method under test: {@link LambdaExpression#needsAccessorMethod()}
   */
  @Test
  void testNeedsAccessorMethod3() {
    // Arrange
    ProgramClass referencedClass = new ProgramClass();
    BootstrapMethodInfo bootstrapMethodInfo = new BootstrapMethodInfo();
    LibraryClass referencedInvokedClass = new LibraryClass(1, "This Class Name", "Super Class Name");

    // Act and Assert
    assertTrue((new LambdaExpression(referencedClass, 1, bootstrapMethodInfo, "Factory Method Descriptor",
        new String[]{"Interfaces"}, new String[]{"Bridge Method Descriptors"}, "Interface Method",
        "Interface Method Descriptor", 1, "Invoked Class Name", null, "Invoked Method Desc", referencedInvokedClass,
        new LibraryMethod(1, "Name", "Descriptor"))).needsAccessorMethod());
  }

  /**
   * Method under test: {@link LambdaExpression#needsAccessorMethod()}
   */
  @Test
  void testNeedsAccessorMethod4() {
    // Arrange
    ProgramClass referencedClass = new ProgramClass();
    BootstrapMethodInfo bootstrapMethodInfo = new BootstrapMethodInfo();
    LibraryClass referencedInvokedClass = new LibraryClass(1, "This Class Name", "Super Class Name");

    // Act and Assert
    assertTrue((new LambdaExpression(referencedClass, 1, bootstrapMethodInfo, "Factory Method Descriptor",
        new String[]{"Interfaces"}, new String[]{"Bridge Method Descriptors"}, "Interface Method",
        "Interface Method Descriptor", 1, "Invoked Class Name", "Invoked Method Name", null, referencedInvokedClass,
        new LibraryMethod(1, "Name", "Descriptor"))).needsAccessorMethod());
  }

  /**
   * Method under test: {@link LambdaExpression#referencesPrivateConstructor()}
   */
  @Test
  void testReferencesPrivateConstructor() {
    // Arrange
    ProgramClass referencedClass = new ProgramClass();
    BootstrapMethodInfo bootstrapMethodInfo = new BootstrapMethodInfo();
    LibraryClass referencedInvokedClass = new LibraryClass();

    // Act and Assert
    assertFalse((new LambdaExpression(referencedClass, 1, bootstrapMethodInfo, "Factory Method Descriptor",
        new String[]{"Interfaces"}, new String[]{"Bridge Method Descriptors"}, "Interface Method",
        "Interface Method Descriptor", 1, "Invoked Class Name", "Invoked Method Name", "Invoked Method Desc",
        referencedInvokedClass, new LibraryMethod(1, "Name", "Descriptor"))).referencesPrivateConstructor());
  }

  /**
   * Method under test: {@link LambdaExpression#referencesPrivateConstructor()}
   */
  @Test
  void testReferencesPrivateConstructor2() {
    // Arrange
    ProgramClass referencedClass = new ProgramClass();
    BootstrapMethodInfo bootstrapMethodInfo = new BootstrapMethodInfo();
    LibraryClass referencedInvokedClass = new LibraryClass();
    LambdaExpression lambdaExpression = new LambdaExpression(referencedClass, 1, bootstrapMethodInfo,
        "Factory Method Descriptor", new String[]{"Interfaces"}, new String[]{"Bridge Method Descriptors"},
        "Interface Method", "Interface Method Descriptor", 1, "Invoked Class Name", "Invoked Method Name",
        "Invoked Method Desc", referencedInvokedClass, new LibraryMethod(1, "Name", "Descriptor"));
    lambdaExpression.invokedReferenceKind = 8;
    lambdaExpression.invokedMethodName = "<init>";

    // Act and Assert
    assertFalse(lambdaExpression.referencesPrivateConstructor());
  }

  /**
   * Method under test: {@link LambdaExpression#referencesPrivateConstructor()}
   */
  @Test
  void testReferencesPrivateConstructor3() {
    // Arrange
    ProgramClass referencedClass = new ProgramClass();
    BootstrapMethodInfo bootstrapMethodInfo = new BootstrapMethodInfo();
    LibraryClass referencedInvokedClass = new LibraryClass();

    // Act and Assert
    assertFalse((new LambdaExpression(referencedClass, 1, bootstrapMethodInfo, "Factory Method Descriptor",
        new String[]{"Interfaces"}, new String[]{"Bridge Method Descriptors"}, "Interface Method",
        "Interface Method Descriptor", 8, "Invoked Class Name", "Invoked Method Name", "Invoked Method Desc",
        referencedInvokedClass, new LibraryMethod(1, "Name", "Descriptor"))).referencesPrivateConstructor());
  }

  /**
   * Method under test:
   * {@link LambdaExpression#LambdaExpression(ProgramClass, int, BootstrapMethodInfo, String, String[], String[], String, String, int, String, String, String, Clazz, Method)}
   */
  @Test
  void testNewLambdaExpression() {
    // Arrange
    ProgramClass referencedClass = new ProgramClass();
    BootstrapMethodInfo bootstrapMethodInfo = new BootstrapMethodInfo();
    LibraryClass referencedInvokedClass = new LibraryClass();

    // Act
    LambdaExpression actualLambdaExpression = new LambdaExpression(referencedClass, 1, bootstrapMethodInfo,
        "Factory Method Descriptor", new String[]{"Interfaces"}, new String[]{"Bridge Method Descriptors"},
        "Interface Method", "Interface Method Descriptor", 1, "Invoked Class Name", "Invoked Method Name",
        "Invoked Method Desc", referencedInvokedClass, new LibraryMethod(1, "Name", "Descriptor"));

    // Assert
    BootstrapMethodInfo bootstrapMethodInfo2 = actualLambdaExpression.bootstrapMethodInfo;
    assertNull(bootstrapMethodInfo2.getProcessingInfo());
    ProgramClass programClass = actualLambdaExpression.referencedClass;
    assertNull(programClass.getProcessingInfo());
    assertNull(programClass.getSuperName());
    assertNull(programClass.getFeatureName());
    assertNull(programClass.getSuperClass());
    assertEquals(0, programClass.getAccessFlags());
    assertEquals(0, programClass.getInterfaceCount());
    assertEquals(0, bootstrapMethodInfo2.getProcessingFlags());
    assertEquals(0, programClass.getProcessingFlags());
    assertTrue(programClass.getExtraFeatureNames().isEmpty());
  }
}
