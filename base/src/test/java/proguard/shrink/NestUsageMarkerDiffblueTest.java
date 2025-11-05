package proguard.shrink;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
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
import proguard.classfile.LibraryClass;
import proguard.classfile.ProgramClass;
import proguard.classfile.attribute.NestHostAttribute;
import proguard.classfile.attribute.NestMembersAttribute;
import proguard.classfile.attribute.PermittedSubclassesAttribute;
import proguard.classfile.constant.ClassConstant;
import proguard.classfile.constant.Constant;
import proguard.classfile.constant.Utf8Constant;
import proguard.classfile.constant.visitor.ConstantVisitor;
import proguard.util.Processable;

class NestUsageMarkerDiffblueTest {
  /**
   * Method under test:
   * {@link NestUsageMarker#visitNestHostAttribute(Clazz, NestHostAttribute)}
   */
  @Test
  void testVisitNestHostAttribute() {
    // Arrange
    NestUsageMarker nestUsageMarker = new NestUsageMarker(new ClassUsageMarker());
    LibraryClass clazz = mock(LibraryClass.class);
    doNothing().when(clazz).constantPoolEntryAccept(anyInt(), Mockito.<ConstantVisitor>any());

    // Act
    nestUsageMarker.visitNestHostAttribute(clazz, new NestHostAttribute(1, 1));

    // Assert
    verify(clazz).constantPoolEntryAccept(eq(1), isA(ConstantVisitor.class));
  }

  /**
   * Method under test:
   * {@link NestUsageMarker#visitNestMembersAttribute(Clazz, NestMembersAttribute)}
   */
  @Test
  void testVisitNestMembersAttribute() {
    // Arrange
    NestUsageMarker nestUsageMarker = new NestUsageMarker(new ClassUsageMarker());
    LibraryClass clazz = new LibraryClass();
    NestMembersAttribute nestMembersAttribute = mock(NestMembersAttribute.class);
    doNothing().when(nestMembersAttribute)
        .memberClassConstantsAccept(Mockito.<Clazz>any(), Mockito.<ConstantVisitor>any());

    // Act
    nestUsageMarker.visitNestMembersAttribute(clazz, nestMembersAttribute);

    // Assert
    verify(nestMembersAttribute).memberClassConstantsAccept(isA(Clazz.class), isA(ConstantVisitor.class));
  }

  /**
   * Method under test:
   * {@link NestUsageMarker#visitPermittedSubclassesAttribute(Clazz, PermittedSubclassesAttribute)}
   */
  @Test
  void testVisitPermittedSubclassesAttribute() {
    // Arrange
    NestUsageMarker nestUsageMarker = new NestUsageMarker(new ClassUsageMarker());
    LibraryClass clazz = new LibraryClass();
    PermittedSubclassesAttribute permittedSubclassesAttribute = mock(PermittedSubclassesAttribute.class);
    doNothing().when(permittedSubclassesAttribute)
        .permittedSubclassConstantsAccept(Mockito.<Clazz>any(), Mockito.<ConstantVisitor>any());

    // Act
    nestUsageMarker.visitPermittedSubclassesAttribute(clazz, permittedSubclassesAttribute);

    // Assert
    verify(permittedSubclassesAttribute).permittedSubclassConstantsAccept(isA(Clazz.class), isA(ConstantVisitor.class));
  }

  /**
   * Method under test:
   * {@link NestUsageMarker#visitClassConstant(Clazz, ClassConstant)}
   */
  @Test
  void testVisitClassConstant() {
    // Arrange
    ShortestUsageMarker usageMarker = new ShortestUsageMarker();
    NestUsageMarker nestUsageMarker = new NestUsageMarker(new ShortestClassUsageMarker(usageMarker, "Just cause"));
    LibraryClass clazz = new LibraryClass();
    ClassConstant classConstant = new ClassConstant();

    // Act
    nestUsageMarker.visitClassConstant(clazz, classConstant);

    // Assert
    ShortestUsageMark expectedProcessingInfo = usageMarker.currentUsageMark;
    assertSame(expectedProcessingInfo, classConstant.getProcessingInfo());
  }

  /**
   * Method under test:
   * {@link NestUsageMarker#visitClassConstant(Clazz, ClassConstant)}
   */
  @Test
  void testVisitClassConstant2() {
    // Arrange
    NestUsageMarker nestUsageMarker = new NestUsageMarker(new ClassUsageMarker());
    ClassConstant classConstant = new ClassConstant();
    ProgramClass clazz = new ProgramClass(5, 3, new Constant[]{classConstant}, 5, 5, 5);

    ClassConstant classConstant2 = new ClassConstant();

    // Act
    nestUsageMarker.visitClassConstant(clazz, classConstant2);

    // Assert
    assertEquals(classConstant, classConstant2);
  }

  /**
   * Method under test:
   * {@link NestUsageMarker#visitUtf8Constant(Clazz, Utf8Constant)}
   */
  @Test
  void testVisitUtf8Constant() {
    // Arrange
    ShortestUsageMarker usageMarker = new ShortestUsageMarker();
    NestUsageMarker nestUsageMarker = new NestUsageMarker(new ShortestClassUsageMarker(usageMarker, "Just cause"));
    LibraryClass clazz = new LibraryClass();
    Utf8Constant utf8Constant = new Utf8Constant("String");

    // Act
    nestUsageMarker.visitUtf8Constant(clazz, utf8Constant);

    // Assert
    ShortestUsageMark expectedProcessingInfo = usageMarker.currentUsageMark;
    assertSame(expectedProcessingInfo, utf8Constant.getProcessingInfo());
  }

  /**
   * Method under test: {@link NestUsageMarker#visitAnyClass(Clazz)}
   */
  @Test
  void testVisitAnyClass() {
    // Arrange
    NestUsageMarker nestUsageMarker = new NestUsageMarker(new ClassUsageMarker());

    // Act and Assert
    assertThrows(UnsupportedOperationException.class, () -> nestUsageMarker.visitAnyClass(new LibraryClass()));
  }

  /**
   * Method under test: {@link NestUsageMarker#visitProgramClass(ProgramClass)}
   */
  @Test
  void testVisitProgramClass() {
    // Arrange
    ClassUsageMarker classUsageMarker = mock(ClassUsageMarker.class);
    when(classUsageMarker.isUsed(Mockito.<Processable>any())).thenReturn(true);
    NestUsageMarker nestUsageMarker = new NestUsageMarker(classUsageMarker);

    // Act
    nestUsageMarker.visitProgramClass(new ProgramClass());

    // Assert
    verify(classUsageMarker).isUsed(isA(Processable.class));
  }
}
