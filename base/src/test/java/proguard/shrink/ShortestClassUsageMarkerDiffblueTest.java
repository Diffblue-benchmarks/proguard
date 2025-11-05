package proguard.shrink;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.anyBoolean;
import static org.mockito.Mockito.anyInt;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import proguard.classfile.Clazz;
import proguard.classfile.LibraryClass;
import proguard.classfile.LibraryMethod;
import proguard.classfile.Method;
import proguard.classfile.ProgramClass;
import proguard.classfile.ProgramField;
import proguard.classfile.ProgramMember;
import proguard.classfile.attribute.visitor.AttributeVisitor;
import proguard.classfile.constant.ClassConstant;
import proguard.classfile.constant.Constant;
import proguard.classfile.constant.visitor.ConstantVisitor;
import proguard.classfile.visitor.ClassVisitor;
import proguard.classfile.visitor.MemberVisitor;
import proguard.fixer.kotlin.KotlinAnnotationCounter;
import proguard.obfuscate.ClassRenamer;
import proguard.util.Processable;

class ShortestClassUsageMarkerDiffblueTest {
  /**
   * Method under test: {@link ShortestClassUsageMarker#getUsageMarker()}
   */
  @Test
  void testGetUsageMarker() {
    // Arrange
    ShortestUsageMarker usageMarker = new ShortestUsageMarker();

    // Act and Assert
    assertSame(usageMarker, (new ShortestClassUsageMarker(usageMarker, "Just cause")).getUsageMarker());
  }

  /**
   * Method under test: {@link ShortestClassUsageMarker#getUsageMarker()}
   */
  @Test
  void testGetUsageMarker2() {
    // Arrange
    ShortestUsageMarker usageMarker = new ShortestUsageMarker();

    ShortestClassUsageMarker shortestClassUsageMarker = new ShortestClassUsageMarker(usageMarker, "Just cause");
    ClassVisitor extraClassVisitor = mock(ClassVisitor.class);
    shortestClassUsageMarker
        .setExtraConstantVisitor(new ClassRenamer(extraClassVisitor, new KotlinAnnotationCounter()));

    // Act and Assert
    assertSame(usageMarker, shortestClassUsageMarker.getUsageMarker());
  }

  /**
   * Method under test:
   * {@link ShortestClassUsageMarker#markProgramClassBody(ProgramClass)}
   */
  @Test
  void testMarkProgramClassBody() {
    // Arrange
    ShortestUsageMarker usageMarker = mock(ShortestUsageMarker.class);
    when(usageMarker.getShortestUsageMark(Mockito.<Processable>any())).thenReturn(new ShortestUsageMark("Just cause"));
    doNothing().when(usageMarker).setCurrentUsageMark(Mockito.<ShortestUsageMark>any());
    ShortestClassUsageMarker shortestClassUsageMarker = new ShortestClassUsageMarker(usageMarker, "Just cause");
    ProgramClass programClass = mock(ProgramClass.class);
    doNothing().when(programClass).attributesAccept(Mockito.<AttributeVisitor>any());
    doNothing().when(programClass).constantPoolEntryAccept(anyInt(), Mockito.<ConstantVisitor>any());
    doNothing().when(programClass).fieldsAccept(Mockito.<MemberVisitor>any());
    doNothing().when(programClass)
        .hierarchyAccept(anyBoolean(), anyBoolean(), anyBoolean(), anyBoolean(), Mockito.<ClassVisitor>any());
    doNothing().when(programClass)
        .methodAccept(Mockito.<String>any(), Mockito.<String>any(), Mockito.<MemberVisitor>any());
    doNothing().when(programClass).methodsAccept(Mockito.<MemberVisitor>any());

    // Act
    shortestClassUsageMarker.markProgramClassBody(programClass);

    // Assert
    verify(programClass).attributesAccept(isA(AttributeVisitor.class));
    verify(programClass).constantPoolEntryAccept(eq(0), isA(ConstantVisitor.class));
    verify(programClass).fieldsAccept(isA(MemberVisitor.class));
    verify(programClass).hierarchyAccept(eq(false), eq(false), eq(true), eq(false), isA(ClassVisitor.class));
    verify(programClass).methodAccept(eq("<clinit>"), eq("()V"), isA(MemberVisitor.class));
    verify(programClass).methodsAccept(isA(MemberVisitor.class));
    verify(usageMarker).getShortestUsageMark(isA(Processable.class));
    verify(usageMarker, atLeast(1)).setCurrentUsageMark(Mockito.<ShortestUsageMark>any());
  }

  /**
   * Method under test:
   * {@link ShortestClassUsageMarker#markProgramFieldBody(ProgramClass, ProgramField)}
   */
  @Test
  void testMarkProgramFieldBody() {
    // Arrange
    ShortestUsageMarker usageMarker = mock(ShortestUsageMarker.class);
    when(usageMarker.shouldBeMarkedAsUsed(Mockito.<Processable>any())).thenReturn(false);
    when(usageMarker.getShortestUsageMark(Mockito.<Processable>any())).thenReturn(new ShortestUsageMark("Just cause"));
    doNothing().when(usageMarker).setCurrentUsageMark(Mockito.<ShortestUsageMark>any());
    ShortestClassUsageMarker shortestClassUsageMarker = new ShortestClassUsageMarker(usageMarker, "Just cause");
    ProgramClass programClass = new ProgramClass(1, 3, new Constant[]{new ClassConstant()}, 1, 1, 1);

    // Act
    shortestClassUsageMarker.markProgramFieldBody(programClass, new ProgramField());

    // Assert
    verify(usageMarker).getShortestUsageMark(isA(Processable.class));
    verify(usageMarker, atLeast(1)).setCurrentUsageMark(Mockito.<ShortestUsageMark>any());
    verify(usageMarker, atLeast(1)).shouldBeMarkedAsUsed(isA(Processable.class));
  }

  /**
   * Method under test:
   * {@link ShortestClassUsageMarker#markMethodHierarchy(Clazz, Method)}
   */
  @Test
  void testMarkMethodHierarchy() {
    // Arrange
    ShortestUsageMarker usageMarker = mock(ShortestUsageMarker.class);
    when(usageMarker.getShortestUsageMark(Mockito.<Processable>any())).thenReturn(new ShortestUsageMark("Just cause"));
    doNothing().when(usageMarker).setCurrentUsageMark(Mockito.<ShortestUsageMark>any());
    ShortestClassUsageMarker shortestClassUsageMarker = new ShortestClassUsageMarker(usageMarker, "Just cause");
    LibraryClass clazz = new LibraryClass(1, "implements       ", "implements       ");

    // Act
    shortestClassUsageMarker.markMethodHierarchy(clazz, new LibraryMethod(1, "Name", "Descriptor"));

    // Assert
    verify(usageMarker).getShortestUsageMark(isA(Processable.class));
    verify(usageMarker, atLeast(1)).setCurrentUsageMark(Mockito.<ShortestUsageMark>any());
  }

  /**
   * Method under test:
   * {@link ShortestClassUsageMarker#markMethodHierarchy(Clazz, Method)}
   */
  @Test
  void testMarkMethodHierarchy2() {
    // Arrange
    ShortestUsageMarker usageMarker = mock(ShortestUsageMarker.class);
    when(usageMarker.getShortestUsageMark(Mockito.<Processable>any())).thenReturn(new ShortestUsageMark("Just cause"));
    doNothing().when(usageMarker).setCurrentUsageMark(Mockito.<ShortestUsageMark>any());
    ShortestClassUsageMarker shortestClassUsageMarker = new ShortestClassUsageMarker(usageMarker, "Just cause");
    ProgramClass clazz = new ProgramClass();

    // Act
    shortestClassUsageMarker.markMethodHierarchy(clazz, new LibraryMethod(1, "Name", "Descriptor"));

    // Assert
    verify(usageMarker).getShortestUsageMark(isA(Processable.class));
    verify(usageMarker, atLeast(1)).setCurrentUsageMark(Mockito.<ShortestUsageMark>any());
  }

  /**
   * Method under test:
   * {@link ShortestClassUsageMarker#markMethodHierarchy(Clazz, Method)}
   */
  @Test
  void testMarkMethodHierarchy3() {
    // Arrange
    ShortestUsageMarker usageMarker = mock(ShortestUsageMarker.class);
    when(usageMarker.getShortestUsageMark(Mockito.<Processable>any())).thenReturn(new ShortestUsageMark("Just cause"));
    doNothing().when(usageMarker).setCurrentUsageMark(Mockito.<ShortestUsageMark>any());
    ShortestClassUsageMarker shortestClassUsageMarker = new ShortestClassUsageMarker(usageMarker, "Just cause");
    Clazz clazz = mock(Clazz.class);
    doNothing().when(clazz).accept(Mockito.<ClassVisitor>any());

    // Act
    shortestClassUsageMarker.markMethodHierarchy(clazz, new LibraryMethod(1, "Name", "Descriptor"));

    // Assert
    verify(clazz, atLeast(1)).accept(Mockito.<ClassVisitor>any());
    verify(usageMarker).getShortestUsageMark(isA(Processable.class));
    verify(usageMarker, atLeast(1)).setCurrentUsageMark(Mockito.<ShortestUsageMark>any());
  }

  /**
   * Method under test:
   * {@link ShortestClassUsageMarker#markMethodHierarchy(Clazz, Method)}
   */
  @Test
  void testMarkMethodHierarchy4() {
    // Arrange
    ShortestUsageMarker usageMarker = mock(ShortestUsageMarker.class);
    when(usageMarker.getShortestUsageMark(Mockito.<Processable>any())).thenReturn(new ShortestUsageMark("Just cause"));
    doNothing().when(usageMarker).setCurrentUsageMark(Mockito.<ShortestUsageMark>any());
    ShortestClassUsageMarker shortestClassUsageMarker = new ShortestClassUsageMarker(usageMarker, "Just cause");
    Clazz clazz = mock(Clazz.class);
    doNothing().when(clazz).accept(Mockito.<ClassVisitor>any());

    // Act
    shortestClassUsageMarker.markMethodHierarchy(clazz, new LibraryMethod(100, "Name", "Descriptor"));

    // Assert
    verify(clazz, atLeast(1)).accept(Mockito.<ClassVisitor>any());
    verify(usageMarker).getShortestUsageMark(isA(Processable.class));
    verify(usageMarker, atLeast(1)).setCurrentUsageMark(Mockito.<ShortestUsageMark>any());
  }

  /**
   * Method under test:
   * {@link ShortestClassUsageMarker#markMethodHierarchy(Clazz, Method)}
   */
  @Test
  void testMarkMethodHierarchy5() {
    // Arrange
    ShortestUsageMarker usageMarker = mock(ShortestUsageMarker.class);
    when(usageMarker.getShortestUsageMark(Mockito.<Processable>any())).thenReturn(new ShortestUsageMark("Just cause"));
    doNothing().when(usageMarker).setCurrentUsageMark(Mockito.<ShortestUsageMark>any());
    ShortestClassUsageMarker shortestClassUsageMarker = new ShortestClassUsageMarker(usageMarker, "Just cause");
    Clazz clazz = mock(Clazz.class);

    // Act
    shortestClassUsageMarker.markMethodHierarchy(clazz, new LibraryMethod(10, "Name", "Descriptor"));

    // Assert
    verify(usageMarker).getShortestUsageMark(isA(Processable.class));
    verify(usageMarker, atLeast(1)).setCurrentUsageMark(Mockito.<ShortestUsageMark>any());
  }

  /**
   * Method under test:
   * {@link ShortestClassUsageMarker#shouldBeMarkedAsUsed(ProgramClass)}
   */
  @Test
  void testShouldBeMarkedAsUsed() {
    // Arrange
    ShortestClassUsageMarker shortestClassUsageMarker = new ShortestClassUsageMarker(new ShortestUsageMarker(),
        "Just cause");

    // Act and Assert
    assertTrue(shortestClassUsageMarker.shouldBeMarkedAsUsed(new ProgramClass()));
  }

  /**
   * Method under test:
   * {@link ShortestClassUsageMarker#shouldBeMarkedAsUsed(ProgramClass)}
   */
  @Test
  void testShouldBeMarkedAsUsed2() {
    // Arrange
    ShortestClassUsageMarker shortestClassUsageMarker = new ShortestClassUsageMarker(new ShortestUsageMarker(),
        "Just cause");

    // Act and Assert
    assertTrue(shortestClassUsageMarker.shouldBeMarkedAsUsed(
        new ProgramClass(1, 3, new Constant[]{new ClassConstant()}, 1, 1, 1, "Feature Name", 1, "Processing Info")));
  }

  /**
   * Method under test:
   * {@link ShortestClassUsageMarker#shouldBeMarkedAsUsed(ProgramClass)}
   */
  @Test
  void testShouldBeMarkedAsUsed3() {
    // Arrange
    ShortestClassUsageMarker shortestClassUsageMarker = new ShortestClassUsageMarker(new ShortestUsageMarker(),
        "Just cause");

    // Act and Assert
    assertFalse(shortestClassUsageMarker.shouldBeMarkedAsUsed(new ProgramClass(1, 3,
        new Constant[]{new ClassConstant()}, 1, 1, 1, "Feature Name", 1, new ShortestUsageMark("Just cause"))));
  }

  /**
   * Method under test:
   * {@link ShortestClassUsageMarker#shouldBeMarkedAsUsed(ProgramClass)}
   */
  @Test
  void testShouldBeMarkedAsUsed4() {
    // Arrange
    ShortestClassUsageMarker shortestClassUsageMarker = new ShortestClassUsageMarker(new ShortestUsageMarker(),
        "Just cause");
    ShortestUsageMark previousUsageMark = new ShortestUsageMark("Just cause");

    // Act and Assert
    assertTrue(shortestClassUsageMarker.shouldBeMarkedAsUsed(new ProgramClass(1, 3, new Constant[]{new ClassConstant()},
        1, 1, 1, "Feature Name", 1, new ShortestUsageMark(previousUsageMark, "Just cause", 1, new LibraryClass()))));
  }

  /**
   * Method under test:
   * {@link ShortestClassUsageMarker#shouldBeMarkedAsUsed(ProgramClass, ProgramMember)}
   */
  @Test
  void testShouldBeMarkedAsUsed5() {
    // Arrange
    ShortestClassUsageMarker shortestClassUsageMarker = new ShortestClassUsageMarker(new ShortestUsageMarker(),
        "Just cause");
    ProgramClass programClass = new ProgramClass();

    // Act and Assert
    assertTrue(shortestClassUsageMarker.shouldBeMarkedAsUsed(programClass, new ProgramField()));
  }

  /**
   * Method under test:
   * {@link ShortestClassUsageMarker#shouldBeMarkedAsUsed(ProgramClass, ProgramMember)}
   */
  @Test
  void testShouldBeMarkedAsUsed6() {
    // Arrange
    ShortestClassUsageMarker shortestClassUsageMarker = new ShortestClassUsageMarker(new ShortestUsageMarker(),
        "Just cause");
    ProgramClass programClass = new ProgramClass();

    ProgramField programMember = new ProgramField();
    programMember.setProcessingInfo("Processing Info");

    // Act and Assert
    assertTrue(shortestClassUsageMarker.shouldBeMarkedAsUsed(programClass, programMember));
  }

  /**
   * Method under test:
   * {@link ShortestClassUsageMarker#shouldBeMarkedAsUsed(ProgramClass, ProgramMember)}
   */
  @Test
  void testShouldBeMarkedAsUsed7() {
    // Arrange
    ShortestClassUsageMarker shortestClassUsageMarker = new ShortestClassUsageMarker(new ShortestUsageMarker(),
        "Just cause");
    ProgramClass programClass = new ProgramClass();

    ProgramField programMember = new ProgramField();
    programMember.setProcessingInfo(new ShortestUsageMark("Just cause"));

    // Act and Assert
    assertFalse(shortestClassUsageMarker.shouldBeMarkedAsUsed(programClass, programMember));
  }

  /**
   * Method under test:
   * {@link ShortestClassUsageMarker#shouldBeMarkedAsUsed(ProgramClass, ProgramMember)}
   */
  @Test
  void testShouldBeMarkedAsUsed8() {
    // Arrange
    ShortestClassUsageMarker shortestClassUsageMarker = new ShortestClassUsageMarker(new ShortestUsageMarker(),
        "Just cause");
    ProgramClass programClass = new ProgramClass();

    ProgramField programMember = new ProgramField();
    ShortestUsageMark previousUsageMark = new ShortestUsageMark("Just cause");
    programMember.setProcessingInfo(new ShortestUsageMark(previousUsageMark, "Just cause", 1, new LibraryClass()));

    // Act and Assert
    assertTrue(shortestClassUsageMarker.shouldBeMarkedAsUsed(programClass, programMember));
  }

  /**
   * Method under test:
   * {@link ShortestClassUsageMarker#shouldBeMarkedAsUsed(Processable)}
   */
  @Test
  void testShouldBeMarkedAsUsed9() {
    // Arrange
    ShortestClassUsageMarker shortestClassUsageMarker = new ShortestClassUsageMarker(new ShortestUsageMarker(),
        "Just cause");

    // Act and Assert
    assertTrue(shortestClassUsageMarker.shouldBeMarkedAsUsed(new LibraryClass()));
  }

  /**
   * Method under test:
   * {@link ShortestClassUsageMarker#shouldBeMarkedAsUsed(Processable)}
   */
  @Test
  void testShouldBeMarkedAsUsed10() {
    // Arrange
    ShortestClassUsageMarker shortestClassUsageMarker = new ShortestClassUsageMarker(new ShortestUsageMarker(),
        "Just cause");
    Processable processable = mock(Processable.class);
    when(processable.getProcessingInfo()).thenReturn("Processing Info");

    // Act
    boolean actualShouldBeMarkedAsUsedResult = shortestClassUsageMarker.shouldBeMarkedAsUsed(processable);

    // Assert
    verify(processable).getProcessingInfo();
    assertTrue(actualShouldBeMarkedAsUsedResult);
  }

  /**
   * Method under test:
   * {@link ShortestClassUsageMarker#shouldBeMarkedAsUsed(Processable)}
   */
  @Test
  void testShouldBeMarkedAsUsed11() {
    // Arrange
    ShortestClassUsageMarker shortestClassUsageMarker = new ShortestClassUsageMarker(new ShortestUsageMarker(),
        "Just cause");
    Processable processable = mock(Processable.class);
    when(processable.getProcessingInfo()).thenReturn(new ShortestUsageMark("Just cause"));

    // Act
    boolean actualShouldBeMarkedAsUsedResult = shortestClassUsageMarker.shouldBeMarkedAsUsed(processable);

    // Assert
    verify(processable).getProcessingInfo();
    assertFalse(actualShouldBeMarkedAsUsedResult);
  }

  /**
   * Method under test:
   * {@link ShortestClassUsageMarker#shouldBeMarkedAsUsed(Processable)}
   */
  @Test
  void testShouldBeMarkedAsUsed12() {
    // Arrange
    ShortestClassUsageMarker shortestClassUsageMarker = new ShortestClassUsageMarker(new ShortestUsageMarker(),
        "Just cause");
    Processable processable = mock(Processable.class);
    ShortestUsageMark previousUsageMark = new ShortestUsageMark("Just cause");
    when(processable.getProcessingInfo())
        .thenReturn(new ShortestUsageMark(previousUsageMark, "Just cause", 1, new LibraryClass()));

    // Act
    boolean actualShouldBeMarkedAsUsedResult = shortestClassUsageMarker.shouldBeMarkedAsUsed(processable);

    // Assert
    verify(processable).getProcessingInfo();
    assertTrue(actualShouldBeMarkedAsUsedResult);
  }

  /**
   * Method under test:
   * {@link ShortestClassUsageMarker#shouldBeMarkedAsUsed(Processable)}
   */
  @Test
  void testShouldBeMarkedAsUsed13() {
    // Arrange
    ShortestClassUsageMarker shortestClassUsageMarker = new ShortestClassUsageMarker(new ShortestUsageMarker(),
        "Just cause");
    Processable processable = mock(Processable.class);
    when(processable.getProcessingInfo()).thenReturn(new ShortestUsageMark(new ShortestUsageMark("Just cause"), false));

    // Act
    boolean actualShouldBeMarkedAsUsedResult = shortestClassUsageMarker.shouldBeMarkedAsUsed(processable);

    // Assert
    verify(processable).getProcessingInfo();
    assertTrue(actualShouldBeMarkedAsUsedResult);
  }

  /**
   * Method under test: {@link ShortestClassUsageMarker#isUsed(Processable)}
   */
  @Test
  void testIsUsed() {
    // Arrange
    ShortestClassUsageMarker shortestClassUsageMarker = new ShortestClassUsageMarker(new ShortestUsageMarker(),
        "Just cause");

    // Act and Assert
    assertFalse(shortestClassUsageMarker.isUsed(new LibraryClass()));
  }

  /**
   * Method under test: {@link ShortestClassUsageMarker#isUsed(Processable)}
   */
  @Test
  void testIsUsed2() {
    // Arrange
    ShortestClassUsageMarker shortestClassUsageMarker = new ShortestClassUsageMarker(new ShortestUsageMarker(),
        "Just cause");
    Processable processable = mock(Processable.class);
    when(processable.getProcessingInfo()).thenReturn("Processing Info");

    // Act
    boolean actualIsUsedResult = shortestClassUsageMarker.isUsed(processable);

    // Assert
    verify(processable).getProcessingInfo();
    assertFalse(actualIsUsedResult);
  }

  /**
   * Method under test: {@link ShortestClassUsageMarker#isUsed(Processable)}
   */
  @Test
  void testIsUsed3() {
    // Arrange
    ShortestClassUsageMarker shortestClassUsageMarker = new ShortestClassUsageMarker(new ShortestUsageMarker(),
        "Just cause");
    Processable processable = mock(Processable.class);
    when(processable.getProcessingInfo()).thenReturn(new ShortestUsageMark("Just cause"));

    // Act
    boolean actualIsUsedResult = shortestClassUsageMarker.isUsed(processable);

    // Assert
    verify(processable).getProcessingInfo();
    assertTrue(actualIsUsedResult);
  }

  /**
   * Method under test: {@link ShortestClassUsageMarker#isUsed(Processable)}
   */
  @Test
  void testIsUsed4() {
    // Arrange
    ShortestClassUsageMarker shortestClassUsageMarker = new ShortestClassUsageMarker(new ShortestUsageMarker(),
        "Just cause");
    Processable processable = mock(Processable.class);
    when(processable.getProcessingInfo()).thenReturn(new ShortestUsageMark(new ShortestUsageMark("Just cause"), false));

    // Act
    boolean actualIsUsedResult = shortestClassUsageMarker.isUsed(processable);

    // Assert
    verify(processable).getProcessingInfo();
    assertFalse(actualIsUsedResult);
  }

  /**
   * Method under test:
   * {@link ShortestClassUsageMarker#markAsPossiblyUsed(Processable)}
   */
  @Test
  void testMarkAsPossiblyUsed() {
    // Arrange
    ShortestClassUsageMarker shortestClassUsageMarker = new ShortestClassUsageMarker(new ShortestUsageMarker(),
        "Just cause");
    LibraryClass processable = new LibraryClass();

    // Act
    shortestClassUsageMarker.markAsPossiblyUsed(processable);

    // Assert
    Object processingInfo = processable.getProcessingInfo();
    assertTrue(processingInfo instanceof ShortestUsageMark);
    assertEquals("Just cause", ((ShortestUsageMark) processingInfo).getReason());
    assertFalse(((ShortestUsageMark) processingInfo).isCertain());
  }

  /**
   * Method under test:
   * {@link ShortestClassUsageMarker#markAsPossiblyUsed(Processable)}
   */
  @Test
  void testMarkAsPossiblyUsed2() {
    // Arrange
    ShortestClassUsageMarker shortestClassUsageMarker = new ShortestClassUsageMarker(new ShortestUsageMarker(),
        "Just cause");
    Processable processable = mock(Processable.class);
    doNothing().when(processable).setProcessingInfo(Mockito.<Object>any());

    // Act
    shortestClassUsageMarker.markAsPossiblyUsed(processable);

    // Assert
    verify(processable).setProcessingInfo(isA(Object.class));
  }

  /**
   * Method under test:
   * {@link ShortestClassUsageMarker#shouldBeMarkedAsPossiblyUsed(ProgramClass, ProgramMember)}
   */
  @Test
  void testShouldBeMarkedAsPossiblyUsed() {
    // Arrange
    ShortestClassUsageMarker shortestClassUsageMarker = new ShortestClassUsageMarker(new ShortestUsageMarker(),
        "Just cause");
    ProgramClass programClass = new ProgramClass();

    // Act and Assert
    assertTrue(shortestClassUsageMarker.shouldBeMarkedAsPossiblyUsed(programClass, new ProgramField()));
  }

  /**
   * Method under test:
   * {@link ShortestClassUsageMarker#shouldBeMarkedAsPossiblyUsed(ProgramClass, ProgramMember)}
   */
  @Test
  void testShouldBeMarkedAsPossiblyUsed2() {
    // Arrange
    ShortestClassUsageMarker shortestClassUsageMarker = new ShortestClassUsageMarker(new ShortestUsageMarker(),
        "Just cause");
    ProgramClass programClass = new ProgramClass();

    ProgramField programMember = new ProgramField();
    programMember.setProcessingInfo("Processing Info");

    // Act and Assert
    assertTrue(shortestClassUsageMarker.shouldBeMarkedAsPossiblyUsed(programClass, programMember));
  }

  /**
   * Method under test:
   * {@link ShortestClassUsageMarker#shouldBeMarkedAsPossiblyUsed(ProgramClass, ProgramMember)}
   */
  @Test
  void testShouldBeMarkedAsPossiblyUsed3() {
    // Arrange
    ShortestClassUsageMarker shortestClassUsageMarker = new ShortestClassUsageMarker(new ShortestUsageMarker(),
        "Just cause");
    ProgramClass programClass = new ProgramClass();

    ProgramField programMember = new ProgramField();
    programMember.setProcessingInfo(new ShortestUsageMark("Just cause"));

    // Act and Assert
    assertFalse(shortestClassUsageMarker.shouldBeMarkedAsPossiblyUsed(programClass, programMember));
  }

  /**
   * Method under test:
   * {@link ShortestClassUsageMarker#shouldBeMarkedAsPossiblyUsed(ProgramClass, ProgramMember)}
   */
  @Test
  void testShouldBeMarkedAsPossiblyUsed4() {
    // Arrange
    ShortestClassUsageMarker shortestClassUsageMarker = new ShortestClassUsageMarker(new ShortestUsageMarker(),
        "Just cause");
    ProgramClass programClass = new ProgramClass();

    ProgramField programMember = new ProgramField();
    ShortestUsageMark previousUsageMark = new ShortestUsageMark("Just cause");
    programMember.setProcessingInfo(new ShortestUsageMark(previousUsageMark, "Just cause", 1, new LibraryClass()));

    // Act and Assert
    assertFalse(shortestClassUsageMarker.shouldBeMarkedAsPossiblyUsed(programClass, programMember));
  }

  /**
   * Method under test:
   * {@link ShortestClassUsageMarker#shouldBeMarkedAsPossiblyUsed(Processable)}
   */
  @Test
  void testShouldBeMarkedAsPossiblyUsed5() {
    // Arrange
    ShortestClassUsageMarker shortestClassUsageMarker = new ShortestClassUsageMarker(new ShortestUsageMarker(),
        "Just cause");

    // Act and Assert
    assertTrue(shortestClassUsageMarker.shouldBeMarkedAsPossiblyUsed(new LibraryClass()));
  }

  /**
   * Method under test:
   * {@link ShortestClassUsageMarker#shouldBeMarkedAsPossiblyUsed(Processable)}
   */
  @Test
  void testShouldBeMarkedAsPossiblyUsed6() {
    // Arrange
    ShortestClassUsageMarker shortestClassUsageMarker = new ShortestClassUsageMarker(new ShortestUsageMarker(),
        "Just cause");
    Processable processable = mock(Processable.class);
    when(processable.getProcessingInfo()).thenReturn("Processing Info");

    // Act
    boolean actualShouldBeMarkedAsPossiblyUsedResult = shortestClassUsageMarker
        .shouldBeMarkedAsPossiblyUsed(processable);

    // Assert
    verify(processable).getProcessingInfo();
    assertTrue(actualShouldBeMarkedAsPossiblyUsedResult);
  }

  /**
   * Method under test:
   * {@link ShortestClassUsageMarker#shouldBeMarkedAsPossiblyUsed(Processable)}
   */
  @Test
  void testShouldBeMarkedAsPossiblyUsed7() {
    // Arrange
    ShortestClassUsageMarker shortestClassUsageMarker = new ShortestClassUsageMarker(new ShortestUsageMarker(),
        "Just cause");
    Processable processable = mock(Processable.class);
    when(processable.getProcessingInfo()).thenReturn(new ShortestUsageMark("Just cause"));

    // Act
    boolean actualShouldBeMarkedAsPossiblyUsedResult = shortestClassUsageMarker
        .shouldBeMarkedAsPossiblyUsed(processable);

    // Assert
    verify(processable).getProcessingInfo();
    assertFalse(actualShouldBeMarkedAsPossiblyUsedResult);
  }

  /**
   * Method under test:
   * {@link ShortestClassUsageMarker#shouldBeMarkedAsPossiblyUsed(Processable)}
   */
  @Test
  void testShouldBeMarkedAsPossiblyUsed8() {
    // Arrange
    ShortestClassUsageMarker shortestClassUsageMarker = new ShortestClassUsageMarker(new ShortestUsageMarker(),
        "Just cause");
    Processable processable = mock(Processable.class);
    ShortestUsageMark previousUsageMark = new ShortestUsageMark("Just cause");
    when(processable.getProcessingInfo())
        .thenReturn(new ShortestUsageMark(previousUsageMark, "Just cause", 1, new LibraryClass()));

    // Act
    boolean actualShouldBeMarkedAsPossiblyUsedResult = shortestClassUsageMarker
        .shouldBeMarkedAsPossiblyUsed(processable);

    // Assert
    verify(processable).getProcessingInfo();
    assertFalse(actualShouldBeMarkedAsPossiblyUsedResult);
  }

  /**
   * Method under test:
   * {@link ShortestClassUsageMarker#isPossiblyUsed(Processable)}
   */
  @Test
  void testIsPossiblyUsed() {
    // Arrange
    ShortestClassUsageMarker shortestClassUsageMarker = new ShortestClassUsageMarker(new ShortestUsageMarker(),
        "Just cause");

    // Act and Assert
    assertFalse(shortestClassUsageMarker.isPossiblyUsed(new LibraryClass()));
  }

  /**
   * Method under test:
   * {@link ShortestClassUsageMarker#isPossiblyUsed(Processable)}
   */
  @Test
  void testIsPossiblyUsed2() {
    // Arrange
    ShortestClassUsageMarker shortestClassUsageMarker = new ShortestClassUsageMarker(new ShortestUsageMarker(),
        "Just cause");
    Processable processable = mock(Processable.class);
    when(processable.getProcessingInfo()).thenReturn("Processing Info");

    // Act
    boolean actualIsPossiblyUsedResult = shortestClassUsageMarker.isPossiblyUsed(processable);

    // Assert
    verify(processable).getProcessingInfo();
    assertFalse(actualIsPossiblyUsedResult);
  }

  /**
   * Method under test:
   * {@link ShortestClassUsageMarker#isPossiblyUsed(Processable)}
   */
  @Test
  void testIsPossiblyUsed3() {
    // Arrange
    ShortestClassUsageMarker shortestClassUsageMarker = new ShortestClassUsageMarker(new ShortestUsageMarker(),
        "Just cause");
    Processable processable = mock(Processable.class);
    when(processable.getProcessingInfo()).thenReturn(new ShortestUsageMark("Just cause"));

    // Act
    boolean actualIsPossiblyUsedResult = shortestClassUsageMarker.isPossiblyUsed(processable);

    // Assert
    verify(processable).getProcessingInfo();
    assertFalse(actualIsPossiblyUsedResult);
  }

  /**
   * Method under test:
   * {@link ShortestClassUsageMarker#isPossiblyUsed(Processable)}
   */
  @Test
  void testIsPossiblyUsed4() {
    // Arrange
    ShortestClassUsageMarker shortestClassUsageMarker = new ShortestClassUsageMarker(new ShortestUsageMarker(),
        "Just cause");
    Processable processable = mock(Processable.class);
    when(processable.getProcessingInfo()).thenReturn(new ShortestUsageMark(new ShortestUsageMark("Just cause"), false));

    // Act
    boolean actualIsPossiblyUsedResult = shortestClassUsageMarker.isPossiblyUsed(processable);

    // Assert
    verify(processable).getProcessingInfo();
    assertTrue(actualIsPossiblyUsedResult);
  }

  /**
   * Method under test:
   * {@link ShortestClassUsageMarker#getShortestUsageMark(Processable)}
   */
  @Test
  void testGetShortestUsageMark() {
    // Arrange
    ShortestClassUsageMarker shortestClassUsageMarker = new ShortestClassUsageMarker(new ShortestUsageMarker(),
        "Just cause");

    // Act and Assert
    assertNull(shortestClassUsageMarker.getShortestUsageMark(new LibraryClass()));
  }

  /**
   * Method under test:
   * {@link ShortestClassUsageMarker#getShortestUsageMark(Processable)}
   */
  @Test
  void testGetShortestUsageMark2() {
    // Arrange
    ShortestClassUsageMarker shortestClassUsageMarker = new ShortestClassUsageMarker(new ShortestUsageMarker(),
        "Just cause");
    Processable processable = mock(Processable.class);
    ShortestUsageMark shortestUsageMark = new ShortestUsageMark("Just cause");
    when(processable.getProcessingInfo()).thenReturn(shortestUsageMark);

    // Act
    ShortestUsageMark actualShortestUsageMark = shortestClassUsageMarker.getShortestUsageMark(processable);

    // Assert
    verify(processable).getProcessingInfo();
    assertSame(shortestUsageMark, actualShortestUsageMark);
  }

  /**
   * Method under test:
   * {@link ShortestClassUsageMarker#ShortestClassUsageMarker(ShortestUsageMarker, String)}
   */
  @Test
  void testNewShortestClassUsageMarker() {
    // Arrange
    ShortestUsageMarker usageMarker = new ShortestUsageMarker();

    // Act
    ShortestClassUsageMarker actualShortestClassUsageMarker = new ShortestClassUsageMarker(usageMarker, "Just cause");

    // Assert
    ShortestUsageMark shortestUsageMark = usageMarker.currentUsageMark;
    assertEquals("Just cause", shortestUsageMark.getReason());
    assertNull(actualShortestClassUsageMarker.getExtraConstantVisitor());
    assertTrue(shortestUsageMark.isCertain());
    assertSame(usageMarker, actualShortestClassUsageMarker.getUsageMarker());
  }
}
