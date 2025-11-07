package proguard.backport;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.anyBoolean;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import com.diffblue.cover.annotations.MethodsUnderTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
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
   * Test {@link DefaultInterfaceMethodConverter#visitProgramClass(ProgramClass)}.
   * <ul>
   *   <li>When {@link ProgramClass} {@link ProgramClass#accept(ClassVisitor)} does nothing.</li>
   *   <li>Then calls {@link ProgramClass#accept(ClassVisitor)}.</li>
   * </ul>
   * <p>
   * Method under test: {@link DefaultInterfaceMethodConverter#visitProgramClass(ProgramClass)}
   */
  @Test
  @DisplayName("Test visitProgramClass(ProgramClass); when ProgramClass accept(ClassVisitor) does nothing; then calls accept(ClassVisitor)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void DefaultInterfaceMethodConverter.visitProgramClass(ProgramClass)"})
  void testVisitProgramClass_whenProgramClassAcceptDoesNothing_thenCallsAccept() {
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
   * Test {@link DefaultInterfaceMethodConverter#visitCodeAttribute(Clazz, Method, CodeAttribute)}.
   * <p>
   * Method under test: {@link DefaultInterfaceMethodConverter#visitCodeAttribute(Clazz, Method, CodeAttribute)}
   */
  @Test
  @DisplayName("Test visitCodeAttribute(Clazz, Method, CodeAttribute)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void DefaultInterfaceMethodConverter.visitCodeAttribute(Clazz, Method, CodeAttribute)"})
  void testVisitCodeAttribute() {
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

    // Assert that nothing has changed
    verify(clazz).addExtraFeatureName(eq("Feature Name"));
    assertEquals(0, method.getAccessFlags());
  }

  /**
   * Test {@link DefaultInterfaceMethodConverter#visitCodeAttribute(Clazz, Method, CodeAttribute)}.
   * <ul>
   *   <li>When {@link ProgramMethod#ProgramMethod()}.</li>
   *   <li>Then {@link ProgramMethod#ProgramMethod()} AccessFlags is {@code 1024}.</li>
   * </ul>
   * <p>
   * Method under test: {@link DefaultInterfaceMethodConverter#visitCodeAttribute(Clazz, Method, CodeAttribute)}
   */
  @Test
  @DisplayName("Test visitCodeAttribute(Clazz, Method, CodeAttribute); when ProgramMethod(); then ProgramMethod() AccessFlags is '1024'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void DefaultInterfaceMethodConverter.visitCodeAttribute(Clazz, Method, CodeAttribute)"})
  void testVisitCodeAttribute_whenProgramMethod_thenProgramMethodAccessFlagsIs1024() {
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
}
