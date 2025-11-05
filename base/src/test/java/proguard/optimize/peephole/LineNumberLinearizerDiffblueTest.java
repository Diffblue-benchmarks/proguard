package proguard.optimize.peephole;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
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
import proguard.classfile.attribute.visitor.AttributeVisitor;
import proguard.classfile.attribute.visitor.LineNumberInfoVisitor;
import proguard.classfile.visitor.ClassVisitor;
import proguard.classfile.visitor.MemberVisitor;

class LineNumberLinearizerDiffblueTest {
  /**
   * Method under test: {@link LineNumberLinearizer#execute(AppView)}
   */
  @Test
  void testExecute() {
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
   * Method under test: {@link LineNumberLinearizer#execute(AppView)}
   */
  @Test
  void testExecute2() {
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
   * Method under test:
   * {@link LineNumberLinearizer#visitProgramClass(ProgramClass)}
   */
  @Test
  void testVisitProgramClass() {
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
   * Method under test:
   * {@link LineNumberLinearizer#visitCodeAttribute(Clazz, Method, CodeAttribute)}
   */
  @Test
  void testVisitCodeAttribute() {
    // Arrange
    LineNumberLinearizer lineNumberLinearizer = new LineNumberLinearizer();
    LibraryClass clazz = new LibraryClass();
    LibraryMethod method = new LibraryMethod(1, "Name", "Descriptor");

    CodeAttribute codeAttribute = mock(CodeAttribute.class);
    doNothing().when(codeAttribute)
        .attributesAccept(Mockito.<Clazz>any(), Mockito.<Method>any(), Mockito.<AttributeVisitor>any());

    // Act
    lineNumberLinearizer.visitCodeAttribute(clazz, method, codeAttribute);

    // Assert
    verify(codeAttribute).attributesAccept(isA(Clazz.class), isA(Method.class), isA(AttributeVisitor.class));
  }

  /**
   * Method under test:
   * {@link LineNumberLinearizer#visitLineNumberTableAttribute(Clazz, Method, CodeAttribute, LineNumberTableAttribute)}
   */
  @Test
  void testVisitLineNumberTableAttribute() {
    // Arrange
    LineNumberLinearizer lineNumberLinearizer = new LineNumberLinearizer();
    LibraryClass clazz = mock(LibraryClass.class);
    when(clazz.getName()).thenReturn("Name");
    LibraryMethod method = new LibraryMethod(1, "Name", "Descriptor");

    CodeAttribute codeAttribute = new CodeAttribute(1);

    // Act
    lineNumberLinearizer.visitLineNumberTableAttribute(clazz, method, codeAttribute, new LineNumberTableAttribute());

    // Assert
    verify(clazz).getName();
  }

  /**
   * Method under test:
   * {@link LineNumberLinearizer#visitLineNumberTableAttribute(Clazz, Method, CodeAttribute, LineNumberTableAttribute)}
   */
  @Test
  void testVisitLineNumberTableAttribute2() {
    // Arrange
    LineNumberLinearizer lineNumberLinearizer = new LineNumberLinearizer();
    LibraryClass clazz = mock(LibraryClass.class);
    when(clazz.getName()).thenReturn("Name");
    LibraryMethod method = new LibraryMethod(1, "Name", "Descriptor");

    CodeAttribute codeAttribute = new CodeAttribute(1);
    LineNumberTableAttribute lineNumberTableAttribute = mock(LineNumberTableAttribute.class);
    doNothing().when(lineNumberTableAttribute)
        .lineNumbersAccept(Mockito.<Clazz>any(), Mockito.<Method>any(), Mockito.<CodeAttribute>any(),
            Mockito.<LineNumberInfoVisitor>any());

    // Act
    lineNumberLinearizer.visitLineNumberTableAttribute(clazz, method, codeAttribute, lineNumberTableAttribute);

    // Assert
    verify(clazz).getName();
    verify(lineNumberTableAttribute).lineNumbersAccept(isA(Clazz.class), isA(Method.class), isA(CodeAttribute.class),
        isA(LineNumberInfoVisitor.class));
  }

  /**
   * Methods under test:
   * <ul>
   *   <li>default or parameterless constructor of {@link LineNumberLinearizer}
   *   <li>{@link LineNumberLinearizer#visitAnyAttribute(Clazz, Attribute)}
   *   <li>{@link LineNumberLinearizer#visitAnyClass(Clazz)}
   * </ul>
   */
  @Test
  void testGettersAndSetters() {
    // Arrange and Act
    LineNumberLinearizer actualLineNumberLinearizer = new LineNumberLinearizer();
    LibraryClass clazz = new LibraryClass();
    actualLineNumberLinearizer.visitAnyAttribute(clazz, new BootstrapMethodsAttribute());
    actualLineNumberLinearizer.visitAnyClass(new LibraryClass());

    // Assert that nothing has changed
    assertEquals("proguard.optimize.peephole.LineNumberLinearizer", actualLineNumberLinearizer.getName());
  }
}
