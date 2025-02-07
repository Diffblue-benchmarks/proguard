package proguard.optimize.peephole;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import com.diffblue.cover.annotations.MethodsUnderTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import proguard.AppView;
import proguard.classfile.ClassPool;
import proguard.classfile.Clazz;
import proguard.classfile.LibraryClass;
import proguard.classfile.LibraryMethod;
import proguard.classfile.Method;
import proguard.classfile.ProgramClass;
import proguard.classfile.attribute.Attribute;
import proguard.classfile.attribute.BootstrapMethodsAttribute;
import proguard.classfile.attribute.CodeAttribute;
import proguard.classfile.attribute.LineNumberTableAttribute;
import proguard.classfile.attribute.visitor.LineNumberInfoVisitor;
import proguard.classfile.visitor.ClassVisitor;
import proguard.classfile.visitor.MemberVisitor;

class LineNumberLinearizerDiffblueTest {
  /**
   * Test {@link LineNumberLinearizer#execute(AppView)}.
   * <ul>
   *   <li>Given {@link LibraryClass} {@link LibraryClass#accept(ClassVisitor)} does nothing.</li>
   *   <li>Then calls {@link LibraryClass#accept(ClassVisitor)}.</li>
   * </ul>
   * <p>
   * Method under test: {@link LineNumberLinearizer#execute(AppView)}
   */
  @Test
  @DisplayName("Test execute(AppView); given LibraryClass accept(ClassVisitor) does nothing; then calls accept(ClassVisitor)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void proguard.optimize.peephole.LineNumberLinearizer.execute(proguard.AppView)"})
  void testExecute_givenLibraryClassAcceptDoesNothing_thenCallsAccept() {
    // Arrange
    LineNumberLinearizer lineNumberLinearizer = new LineNumberLinearizer();
    LibraryClass clazz = mock(LibraryClass.class);
    doNothing().when(clazz).accept(Mockito.<ClassVisitor>any());

    ClassPool programClassPool = new ClassPool();
    programClassPool.addClass("Name", clazz);

    // Act
    lineNumberLinearizer.execute(new AppView(programClassPool, new ClassPool()));

    // Assert
    verify(clazz).accept(isA(ClassVisitor.class));
  }

  /**
   * Test {@link LineNumberLinearizer#execute(AppView)}.
   * <ul>
   *   <li>When {@link ClassPool} {@link ClassPool#classesAccept(ClassVisitor)} does nothing.</li>
   *   <li>Then calls {@link ClassPool#classesAccept(ClassVisitor)}.</li>
   * </ul>
   * <p>
   * Method under test: {@link LineNumberLinearizer#execute(AppView)}
   */
  @Test
  @DisplayName("Test execute(AppView); when ClassPool classesAccept(ClassVisitor) does nothing; then calls classesAccept(ClassVisitor)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void proguard.optimize.peephole.LineNumberLinearizer.execute(proguard.AppView)"})
  void testExecute_whenClassPoolClassesAcceptDoesNothing_thenCallsClassesAccept() {
    // Arrange
    LineNumberLinearizer lineNumberLinearizer = new LineNumberLinearizer();
    ClassPool programClassPool = mock(ClassPool.class);
    doNothing().when(programClassPool).classesAccept(Mockito.<ClassVisitor>any());

    // Act
    lineNumberLinearizer.execute(new AppView(programClassPool, new ClassPool()));

    // Assert
    verify(programClassPool).classesAccept(isA(ClassVisitor.class));
  }

  /**
   * Test {@link LineNumberLinearizer#visitProgramClass(ProgramClass)}.
   * <ul>
   *   <li>Then calls {@link ProgramClass#methodsAccept(MemberVisitor)}.</li>
   * </ul>
   * <p>
   * Method under test: {@link LineNumberLinearizer#visitProgramClass(ProgramClass)}
   */
  @Test
  @DisplayName("Test visitProgramClass(ProgramClass); then calls methodsAccept(MemberVisitor)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "void proguard.optimize.peephole.LineNumberLinearizer.visitProgramClass(proguard.classfile.ProgramClass)"})
  void testVisitProgramClass_thenCallsMethodsAccept() {
    // Arrange
    LineNumberLinearizer lineNumberLinearizer = new LineNumberLinearizer();
    ProgramClass programClass = mock(ProgramClass.class);
    doNothing().when(programClass).methodsAccept(Mockito.<MemberVisitor>any());

    // Act
    lineNumberLinearizer.visitProgramClass(programClass);

    // Assert
    verify(programClass).methodsAccept(isA(MemberVisitor.class));
  }

  /**
   * Test {@link LineNumberLinearizer#visitLineNumberTableAttribute(Clazz, Method, CodeAttribute, LineNumberTableAttribute)}.
   * <ul>
   *   <li>Then calls {@link LineNumberTableAttribute#lineNumbersAccept(Clazz, Method, CodeAttribute, LineNumberInfoVisitor)}.</li>
   * </ul>
   * <p>
   * Method under test: {@link LineNumberLinearizer#visitLineNumberTableAttribute(Clazz, Method, CodeAttribute, LineNumberTableAttribute)}
   */
  @Test
  @DisplayName("Test visitLineNumberTableAttribute(Clazz, Method, CodeAttribute, LineNumberTableAttribute); then calls lineNumbersAccept(Clazz, Method, CodeAttribute, LineNumberInfoVisitor)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "void proguard.optimize.peephole.LineNumberLinearizer.visitLineNumberTableAttribute(proguard.classfile.Clazz, proguard.classfile.Method, proguard.classfile.attribute.CodeAttribute, proguard.classfile.attribute.LineNumberTableAttribute)"})
  void testVisitLineNumberTableAttribute_thenCallsLineNumbersAccept() {
    // Arrange
    LineNumberLinearizer lineNumberLinearizer = new LineNumberLinearizer();
    LibraryClass clazz = new LibraryClass();
    LibraryMethod method = new LibraryMethod(1, "Name", "Descriptor");

    CodeAttribute codeAttribute = new CodeAttribute(1);
    LineNumberTableAttribute lineNumberTableAttribute = mock(LineNumberTableAttribute.class);
    doNothing().when(lineNumberTableAttribute)
        .lineNumbersAccept(Mockito.<Clazz>any(), Mockito.<Method>any(), Mockito.<CodeAttribute>any(),
            Mockito.<LineNumberInfoVisitor>any());

    // Act
    lineNumberLinearizer.visitLineNumberTableAttribute(clazz, method, codeAttribute, lineNumberTableAttribute);

    // Assert
    verify(lineNumberTableAttribute).lineNumbersAccept(isA(Clazz.class), isA(Method.class), isA(CodeAttribute.class),
        isA(LineNumberInfoVisitor.class));
  }

  /**
   * Test getters and setters.
   * <p>
   * Methods under test:
   * <ul>
   *   <li>default or parameterless constructor of {@link LineNumberLinearizer}
   *   <li>{@link LineNumberLinearizer#visitAnyAttribute(Clazz, Attribute)}
   *   <li>{@link LineNumberLinearizer#visitAnyClass(Clazz)}
   * </ul>
   */
  @Test
  @DisplayName("Test getters and setters")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void proguard.optimize.peephole.LineNumberLinearizer.<init>()",
      "void proguard.optimize.peephole.LineNumberLinearizer.visitAnyAttribute(proguard.classfile.Clazz, proguard.classfile.attribute.Attribute)",
      "void proguard.optimize.peephole.LineNumberLinearizer.visitAnyClass(proguard.classfile.Clazz)"})
  void testGettersAndSetters() {
    // Arrange and Act
    LineNumberLinearizer actualLineNumberLinearizer = new LineNumberLinearizer();
    LibraryClass clazz = new LibraryClass();
    actualLineNumberLinearizer.visitAnyAttribute(clazz, new BootstrapMethodsAttribute());
    actualLineNumberLinearizer.visitAnyClass(new LibraryClass());

    // Assert
    assertEquals("proguard.optimize.peephole.LineNumberLinearizer", actualLineNumberLinearizer.getName());
  }
}
