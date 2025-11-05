package proguard.backport;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.anyBoolean;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import proguard.classfile.Clazz;
import proguard.classfile.Method;
import proguard.classfile.ProgramClass;
import proguard.classfile.ProgramMethod;
import proguard.classfile.attribute.CodeAttribute;
import proguard.classfile.visitor.ClassVisitor;
import proguard.fixer.kotlin.KotlinAnnotationCounter;
import proguard.testutils.cpa.NamedMember;

class DefaultInterfaceMethodConverterDiffblueTest {
  /**
   * Method under test:
   * {@link DefaultInterfaceMethodConverter#visitProgramClass(ProgramClass)}
   */
  @Test
  void testVisitProgramClass() {
    // Arrange
    ClassVisitor modifiedClassVisitor = mock(ClassVisitor.class);
    DefaultInterfaceMethodConverter defaultInterfaceMethodConverter = new DefaultInterfaceMethodConverter(
        modifiedClassVisitor, new KotlinAnnotationCounter());
    ProgramClass programClass = mock(ProgramClass.class);
    doNothing().when(programClass).accept(Mockito.<ClassVisitor>any());
    doNothing().when(programClass)
        .hierarchyAccept(anyBoolean(), anyBoolean(), anyBoolean(), anyBoolean(), Mockito.<ClassVisitor>any());

    // Act
    defaultInterfaceMethodConverter.visitProgramClass(programClass);

    // Assert
    verify(programClass).accept(isA(ClassVisitor.class));
    verify(programClass).hierarchyAccept(eq(false), eq(false), eq(false), eq(true), isA(ClassVisitor.class));
  }

  /**
   * Method under test:
   * {@link DefaultInterfaceMethodConverter#visitCodeAttribute(Clazz, Method, CodeAttribute)}
   */
  @Test
  void testVisitCodeAttribute() {
    // Arrange
    ClassVisitor modifiedClassVisitor = mock(ClassVisitor.class);
    DefaultInterfaceMethodConverter defaultInterfaceMethodConverter = new DefaultInterfaceMethodConverter(
        modifiedClassVisitor, new KotlinAnnotationCounter());
    ProgramClass clazz = mock(ProgramClass.class);
    doNothing().when(clazz).addExtraFeatureName(Mockito.<String>any());
    clazz.addExtraFeatureName("Feature Name");
    ProgramMethod method = new ProgramMethod();

    // Act
    defaultInterfaceMethodConverter.visitCodeAttribute(clazz, method, new CodeAttribute(1));

    // Assert
    verify(clazz).addExtraFeatureName(eq("Feature Name"));
    assertEquals(1024, method.getAccessFlags());
  }

  /**
   * Method under test:
   * {@link DefaultInterfaceMethodConverter#visitCodeAttribute(Clazz, Method, CodeAttribute)}
   */
  @Test
  void testVisitCodeAttribute2() {
    // Arrange
    ClassVisitor modifiedClassVisitor = mock(ClassVisitor.class);
    DefaultInterfaceMethodConverter defaultInterfaceMethodConverter = new DefaultInterfaceMethodConverter(
        modifiedClassVisitor, new KotlinAnnotationCounter());
    ProgramClass clazz = mock(ProgramClass.class);
    doNothing().when(clazz).addExtraFeatureName(Mockito.<String>any());
    clazz.addExtraFeatureName("Feature Name");
    NamedMember method = new NamedMember("Member Name", "Descriptor");

    // Act
    defaultInterfaceMethodConverter.visitCodeAttribute(clazz, method, new CodeAttribute(1));

    // Assert
    verify(clazz).addExtraFeatureName(eq("Feature Name"));
  }
}
