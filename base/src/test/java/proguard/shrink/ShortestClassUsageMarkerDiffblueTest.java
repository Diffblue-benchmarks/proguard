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
import com.diffblue.cover.annotations.MethodsUnderTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
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
import proguard.util.Processable;

class ShortestClassUsageMarkerDiffblueTest {
  /**
   * Test {@link ShortestClassUsageMarker#ShortestClassUsageMarker(ShortestUsageMarker, String)}.
   * <p>
   * Method under test: {@link ShortestClassUsageMarker#ShortestClassUsageMarker(ShortestUsageMarker, String)}
   */
  @Test
  @DisplayName("Test new ShortestClassUsageMarker(ShortestUsageMarker, String)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void ShortestClassUsageMarker.<init>(ShortestUsageMarker, String)"})
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

  /**
   * Test {@link ShortestClassUsageMarker#getUsageMarker()}.
   * <p>
   * Method under test: {@link ShortestClassUsageMarker#getUsageMarker()}
   */
  @Test
  @DisplayName("Test getUsageMarker()")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"ShortestUsageMarker ShortestClassUsageMarker.getUsageMarker()"})
  void testGetUsageMarker() {
    // Arrange
    ShortestUsageMarker usageMarker = new ShortestUsageMarker();

    // Act and Assert
    assertSame(usageMarker, (new ShortestClassUsageMarker(usageMarker, "Just cause")).getUsageMarker());
  }

  /**
   * Test {@link ShortestClassUsageMarker#markProgramClassBody(ProgramClass)}.
   * <ul>
   *   <li>Then calls {@link ProgramClass#attributesAccept(AttributeVisitor)}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ShortestClassUsageMarker#markProgramClassBody(ProgramClass)}
   */
  @Test
  @DisplayName("Test markProgramClassBody(ProgramClass); then calls attributesAccept(AttributeVisitor)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void ShortestClassUsageMarker.markProgramClassBody(ProgramClass)"})
  void testMarkProgramClassBody_thenCallsAttributesAccept() {
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
   * Test {@link ShortestClassUsageMarker#markProgramFieldBody(ProgramClass, ProgramField)}.
   * <ul>
   *   <li>Then calls {@link ShortestUsageMarker#getShortestUsageMark(Processable)}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ShortestClassUsageMarker#markProgramFieldBody(ProgramClass, ProgramField)}
   */
  @Test
  @DisplayName("Test markProgramFieldBody(ProgramClass, ProgramField); then calls getShortestUsageMark(Processable)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void ShortestClassUsageMarker.markProgramFieldBody(ProgramClass, ProgramField)"})
  void testMarkProgramFieldBody_thenCallsGetShortestUsageMark() {
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
   * Test {@link ShortestClassUsageMarker#markMethodHierarchy(Clazz, Method)}.
   * <p>
   * Method under test: {@link ShortestClassUsageMarker#markMethodHierarchy(Clazz, Method)}
   */
  @Test
  @DisplayName("Test markMethodHierarchy(Clazz, Method)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void ShortestClassUsageMarker.markMethodHierarchy(Clazz, Method)"})
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
   * Test {@link ShortestClassUsageMarker#markMethodHierarchy(Clazz, Method)}.
   * <p>
   * Method under test: {@link ShortestClassUsageMarker#markMethodHierarchy(Clazz, Method)}
   */
  @Test
  @DisplayName("Test markMethodHierarchy(Clazz, Method)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void ShortestClassUsageMarker.markMethodHierarchy(Clazz, Method)"})
  void testMarkMethodHierarchy2() {
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
   * Test {@link ShortestClassUsageMarker#markMethodHierarchy(Clazz, Method)}.
   * <ul>
   *   <li>When {@link Clazz} {@link Clazz#accept(ClassVisitor)} does nothing.</li>
   *   <li>Then calls {@link Clazz#accept(ClassVisitor)}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ShortestClassUsageMarker#markMethodHierarchy(Clazz, Method)}
   */
  @Test
  @DisplayName("Test markMethodHierarchy(Clazz, Method); when Clazz accept(ClassVisitor) does nothing; then calls accept(ClassVisitor)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void ShortestClassUsageMarker.markMethodHierarchy(Clazz, Method)"})
  void testMarkMethodHierarchy_whenClazzAcceptDoesNothing_thenCallsAccept() {
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
   * Test {@link ShortestClassUsageMarker#markMethodHierarchy(Clazz, Method)}.
   * <ul>
   *   <li>When {@link Clazz}.</li>
   *   <li>Then calls {@link ShortestUsageMarker#getShortestUsageMark(Processable)}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ShortestClassUsageMarker#markMethodHierarchy(Clazz, Method)}
   */
  @Test
  @DisplayName("Test markMethodHierarchy(Clazz, Method); when Clazz; then calls getShortestUsageMark(Processable)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void ShortestClassUsageMarker.markMethodHierarchy(Clazz, Method)"})
  void testMarkMethodHierarchy_whenClazz_thenCallsGetShortestUsageMark() {
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
   * Test {@link ShortestClassUsageMarker#markMethodHierarchy(Clazz, Method)}.
   * <ul>
   *   <li>When {@link ProgramClass#ProgramClass()}.</li>
   *   <li>Then calls {@link ShortestUsageMarker#getShortestUsageMark(Processable)}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ShortestClassUsageMarker#markMethodHierarchy(Clazz, Method)}
   */
  @Test
  @DisplayName("Test markMethodHierarchy(Clazz, Method); when ProgramClass(); then calls getShortestUsageMark(Processable)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void ShortestClassUsageMarker.markMethodHierarchy(Clazz, Method)"})
  void testMarkMethodHierarchy_whenProgramClass_thenCallsGetShortestUsageMark() {
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
   * Test {@link ShortestClassUsageMarker#shouldBeMarkedAsUsed(Processable)} with {@code processable}.
   * <p>
   * Method under test: {@link ShortestClassUsageMarker#shouldBeMarkedAsUsed(Processable)}
   */
  @Test
  @DisplayName("Test shouldBeMarkedAsUsed(Processable) with 'processable'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean ShortestClassUsageMarker.shouldBeMarkedAsUsed(Processable)"})
  void testShouldBeMarkedAsUsedWithProcessable() {
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
   * Test {@link ShortestClassUsageMarker#shouldBeMarkedAsUsed(Processable)} with {@code processable}.
   * <p>
   * Method under test: {@link ShortestClassUsageMarker#shouldBeMarkedAsUsed(Processable)}
   */
  @Test
  @DisplayName("Test shouldBeMarkedAsUsed(Processable) with 'processable'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean ShortestClassUsageMarker.shouldBeMarkedAsUsed(Processable)"})
  void testShouldBeMarkedAsUsedWithProcessable2() {
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
   * Test {@link ShortestClassUsageMarker#shouldBeMarkedAsUsed(Processable)} with {@code processable}.
   * <ul>
   *   <li>Given {@code Processing Info}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ShortestClassUsageMarker#shouldBeMarkedAsUsed(Processable)}
   */
  @Test
  @DisplayName("Test shouldBeMarkedAsUsed(Processable) with 'processable'; given 'Processing Info'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean ShortestClassUsageMarker.shouldBeMarkedAsUsed(Processable)"})
  void testShouldBeMarkedAsUsedWithProcessable_givenProcessingInfo() {
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
   * Test {@link ShortestClassUsageMarker#shouldBeMarkedAsUsed(Processable)} with {@code processable}.
   * <ul>
   *   <li>Then return {@code false}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ShortestClassUsageMarker#shouldBeMarkedAsUsed(Processable)}
   */
  @Test
  @DisplayName("Test shouldBeMarkedAsUsed(Processable) with 'processable'; then return 'false'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean ShortestClassUsageMarker.shouldBeMarkedAsUsed(Processable)"})
  void testShouldBeMarkedAsUsedWithProcessable_thenReturnFalse() {
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
   * Test {@link ShortestClassUsageMarker#shouldBeMarkedAsUsed(Processable)} with {@code processable}.
   * <ul>
   *   <li>When {@link LibraryClass#LibraryClass()}.</li>
   *   <li>Then return {@code true}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ShortestClassUsageMarker#shouldBeMarkedAsUsed(Processable)}
   */
  @Test
  @DisplayName("Test shouldBeMarkedAsUsed(Processable) with 'processable'; when LibraryClass(); then return 'true'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean ShortestClassUsageMarker.shouldBeMarkedAsUsed(Processable)"})
  void testShouldBeMarkedAsUsedWithProcessable_whenLibraryClass_thenReturnTrue() {
    // Arrange
    ShortestClassUsageMarker shortestClassUsageMarker = new ShortestClassUsageMarker(new ShortestUsageMarker(),
        "Just cause");

    // Act and Assert
    assertTrue(shortestClassUsageMarker.shouldBeMarkedAsUsed(new LibraryClass()));
  }

  /**
   * Test {@link ShortestClassUsageMarker#shouldBeMarkedAsUsed(ProgramClass)} with {@code programClass}.
   * <p>
   * Method under test: {@link ShortestClassUsageMarker#shouldBeMarkedAsUsed(ProgramClass)}
   */
  @Test
  @DisplayName("Test shouldBeMarkedAsUsed(ProgramClass) with 'programClass'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean ShortestClassUsageMarker.shouldBeMarkedAsUsed(ProgramClass)"})
  void testShouldBeMarkedAsUsedWithProgramClass() {
    // Arrange
    ShortestClassUsageMarker shortestClassUsageMarker = new ShortestClassUsageMarker(new ShortestUsageMarker(),
        "Just cause");

    // Act and Assert
    assertTrue(shortestClassUsageMarker.shouldBeMarkedAsUsed(
        new ProgramClass(1, 3, new Constant[]{new ClassConstant()}, 1, 1, 1, "Feature Name", 1, "Processing Info")));
  }

  /**
   * Test {@link ShortestClassUsageMarker#shouldBeMarkedAsUsed(ProgramClass)} with {@code programClass}.
   * <p>
   * Method under test: {@link ShortestClassUsageMarker#shouldBeMarkedAsUsed(ProgramClass)}
   */
  @Test
  @DisplayName("Test shouldBeMarkedAsUsed(ProgramClass) with 'programClass'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean ShortestClassUsageMarker.shouldBeMarkedAsUsed(ProgramClass)"})
  void testShouldBeMarkedAsUsedWithProgramClass2() {
    // Arrange
    ShortestClassUsageMarker shortestClassUsageMarker = new ShortestClassUsageMarker(new ShortestUsageMarker(),
        "Just cause");
    ShortestUsageMark previousUsageMark = new ShortestUsageMark("Just cause");

    // Act and Assert
    assertTrue(shortestClassUsageMarker.shouldBeMarkedAsUsed(new ProgramClass(1, 3, new Constant[]{new ClassConstant()},
        1, 1, 1, "Feature Name", 1, new ShortestUsageMark(previousUsageMark, "Just cause", 1, new LibraryClass()))));
  }

  /**
   * Test {@link ShortestClassUsageMarker#shouldBeMarkedAsUsed(ProgramClass, ProgramMember)} with {@code programClass}, {@code programMember}.
   * <p>
   * Method under test: {@link ShortestClassUsageMarker#shouldBeMarkedAsUsed(ProgramClass, ProgramMember)}
   */
  @Test
  @DisplayName("Test shouldBeMarkedAsUsed(ProgramClass, ProgramMember) with 'programClass', 'programMember'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean ShortestClassUsageMarker.shouldBeMarkedAsUsed(ProgramClass, ProgramMember)"})
  void testShouldBeMarkedAsUsedWithProgramClassProgramMember() {
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
   * Test {@link ShortestClassUsageMarker#shouldBeMarkedAsUsed(ProgramClass, ProgramMember)} with {@code programClass}, {@code programMember}.
   * <ul>
   *   <li>Given {@code Processing Info}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ShortestClassUsageMarker#shouldBeMarkedAsUsed(ProgramClass, ProgramMember)}
   */
  @Test
  @DisplayName("Test shouldBeMarkedAsUsed(ProgramClass, ProgramMember) with 'programClass', 'programMember'; given 'Processing Info'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean ShortestClassUsageMarker.shouldBeMarkedAsUsed(ProgramClass, ProgramMember)"})
  void testShouldBeMarkedAsUsedWithProgramClassProgramMember_givenProcessingInfo() {
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
   * Test {@link ShortestClassUsageMarker#shouldBeMarkedAsUsed(ProgramClass, ProgramMember)} with {@code programClass}, {@code programMember}.
   * <ul>
   *   <li>Then return {@code false}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ShortestClassUsageMarker#shouldBeMarkedAsUsed(ProgramClass, ProgramMember)}
   */
  @Test
  @DisplayName("Test shouldBeMarkedAsUsed(ProgramClass, ProgramMember) with 'programClass', 'programMember'; then return 'false'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean ShortestClassUsageMarker.shouldBeMarkedAsUsed(ProgramClass, ProgramMember)"})
  void testShouldBeMarkedAsUsedWithProgramClassProgramMember_thenReturnFalse() {
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
   * Test {@link ShortestClassUsageMarker#shouldBeMarkedAsUsed(ProgramClass, ProgramMember)} with {@code programClass}, {@code programMember}.
   * <ul>
   *   <li>When {@link ProgramField#ProgramField()}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ShortestClassUsageMarker#shouldBeMarkedAsUsed(ProgramClass, ProgramMember)}
   */
  @Test
  @DisplayName("Test shouldBeMarkedAsUsed(ProgramClass, ProgramMember) with 'programClass', 'programMember'; when ProgramField()")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean ShortestClassUsageMarker.shouldBeMarkedAsUsed(ProgramClass, ProgramMember)"})
  void testShouldBeMarkedAsUsedWithProgramClassProgramMember_whenProgramField() {
    // Arrange
    ShortestClassUsageMarker shortestClassUsageMarker = new ShortestClassUsageMarker(new ShortestUsageMarker(),
        "Just cause");
    ProgramClass programClass = new ProgramClass();

    // Act and Assert
    assertTrue(shortestClassUsageMarker.shouldBeMarkedAsUsed(programClass, new ProgramField()));
  }

  /**
   * Test {@link ShortestClassUsageMarker#shouldBeMarkedAsUsed(ProgramClass)} with {@code programClass}.
   * <ul>
   *   <li>Then return {@code false}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ShortestClassUsageMarker#shouldBeMarkedAsUsed(ProgramClass)}
   */
  @Test
  @DisplayName("Test shouldBeMarkedAsUsed(ProgramClass) with 'programClass'; then return 'false'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean ShortestClassUsageMarker.shouldBeMarkedAsUsed(ProgramClass)"})
  void testShouldBeMarkedAsUsedWithProgramClass_thenReturnFalse() {
    // Arrange
    ShortestClassUsageMarker shortestClassUsageMarker = new ShortestClassUsageMarker(new ShortestUsageMarker(),
        "Just cause");

    // Act and Assert
    assertFalse(shortestClassUsageMarker.shouldBeMarkedAsUsed(new ProgramClass(1, 3,
        new Constant[]{new ClassConstant()}, 1, 1, 1, "Feature Name", 1, new ShortestUsageMark("Just cause"))));
  }

  /**
   * Test {@link ShortestClassUsageMarker#shouldBeMarkedAsUsed(ProgramClass)} with {@code programClass}.
   * <ul>
   *   <li>When {@link ProgramClass#ProgramClass()}.</li>
   *   <li>Then return {@code true}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ShortestClassUsageMarker#shouldBeMarkedAsUsed(ProgramClass)}
   */
  @Test
  @DisplayName("Test shouldBeMarkedAsUsed(ProgramClass) with 'programClass'; when ProgramClass(); then return 'true'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean ShortestClassUsageMarker.shouldBeMarkedAsUsed(ProgramClass)"})
  void testShouldBeMarkedAsUsedWithProgramClass_whenProgramClass_thenReturnTrue() {
    // Arrange
    ShortestClassUsageMarker shortestClassUsageMarker = new ShortestClassUsageMarker(new ShortestUsageMarker(),
        "Just cause");

    // Act and Assert
    assertTrue(shortestClassUsageMarker.shouldBeMarkedAsUsed(new ProgramClass()));
  }

  /**
   * Test {@link ShortestClassUsageMarker#isUsed(Processable)}.
   * <p>
   * Method under test: {@link ShortestClassUsageMarker#isUsed(Processable)}
   */
  @Test
  @DisplayName("Test isUsed(Processable)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean ShortestClassUsageMarker.isUsed(Processable)"})
  void testIsUsed() {
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
   * Test {@link ShortestClassUsageMarker#isUsed(Processable)}.
   * <ul>
   *   <li>Given {@code Processing Info}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ShortestClassUsageMarker#isUsed(Processable)}
   */
  @Test
  @DisplayName("Test isUsed(Processable); given 'Processing Info'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean ShortestClassUsageMarker.isUsed(Processable)"})
  void testIsUsed_givenProcessingInfo() {
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
   * Test {@link ShortestClassUsageMarker#isUsed(Processable)}.
   * <ul>
   *   <li>Then return {@code true}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ShortestClassUsageMarker#isUsed(Processable)}
   */
  @Test
  @DisplayName("Test isUsed(Processable); then return 'true'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean ShortestClassUsageMarker.isUsed(Processable)"})
  void testIsUsed_thenReturnTrue() {
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
   * Test {@link ShortestClassUsageMarker#isUsed(Processable)}.
   * <ul>
   *   <li>When {@link LibraryClass#LibraryClass()}.</li>
   *   <li>Then return {@code false}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ShortestClassUsageMarker#isUsed(Processable)}
   */
  @Test
  @DisplayName("Test isUsed(Processable); when LibraryClass(); then return 'false'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean ShortestClassUsageMarker.isUsed(Processable)"})
  void testIsUsed_whenLibraryClass_thenReturnFalse() {
    // Arrange
    ShortestClassUsageMarker shortestClassUsageMarker = new ShortestClassUsageMarker(new ShortestUsageMarker(),
        "Just cause");

    // Act and Assert
    assertFalse(shortestClassUsageMarker.isUsed(new LibraryClass()));
  }

  /**
   * Test {@link ShortestClassUsageMarker#markAsPossiblyUsed(Processable)}.
   * <ul>
   *   <li>Then {@link LibraryClass#LibraryClass()} ProcessingInfo {@link ShortestUsageMark}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ShortestClassUsageMarker#markAsPossiblyUsed(Processable)}
   */
  @Test
  @DisplayName("Test markAsPossiblyUsed(Processable); then LibraryClass() ProcessingInfo ShortestUsageMark")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void ShortestClassUsageMarker.markAsPossiblyUsed(Processable)"})
  void testMarkAsPossiblyUsed_thenLibraryClassProcessingInfoShortestUsageMark() {
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
   * Test {@link ShortestClassUsageMarker#shouldBeMarkedAsPossiblyUsed(Processable)} with {@code processable}.
   * <p>
   * Method under test: {@link ShortestClassUsageMarker#shouldBeMarkedAsPossiblyUsed(Processable)}
   */
  @Test
  @DisplayName("Test shouldBeMarkedAsPossiblyUsed(Processable) with 'processable'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean ShortestClassUsageMarker.shouldBeMarkedAsPossiblyUsed(Processable)"})
  void testShouldBeMarkedAsPossiblyUsedWithProcessable() {
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
   * Test {@link ShortestClassUsageMarker#shouldBeMarkedAsPossiblyUsed(Processable)} with {@code processable}.
   * <p>
   * Method under test: {@link ShortestClassUsageMarker#shouldBeMarkedAsPossiblyUsed(Processable)}
   */
  @Test
  @DisplayName("Test shouldBeMarkedAsPossiblyUsed(Processable) with 'processable'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean ShortestClassUsageMarker.shouldBeMarkedAsPossiblyUsed(Processable)"})
  void testShouldBeMarkedAsPossiblyUsedWithProcessable2() {
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
   * Test {@link ShortestClassUsageMarker#shouldBeMarkedAsPossiblyUsed(Processable)} with {@code processable}.
   * <ul>
   *   <li>Given {@code Processing Info}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ShortestClassUsageMarker#shouldBeMarkedAsPossiblyUsed(Processable)}
   */
  @Test
  @DisplayName("Test shouldBeMarkedAsPossiblyUsed(Processable) with 'processable'; given 'Processing Info'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean ShortestClassUsageMarker.shouldBeMarkedAsPossiblyUsed(Processable)"})
  void testShouldBeMarkedAsPossiblyUsedWithProcessable_givenProcessingInfo() {
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
   * Test {@link ShortestClassUsageMarker#shouldBeMarkedAsPossiblyUsed(Processable)} with {@code processable}.
   * <ul>
   *   <li>When {@link LibraryClass#LibraryClass()}.</li>
   *   <li>Then return {@code true}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ShortestClassUsageMarker#shouldBeMarkedAsPossiblyUsed(Processable)}
   */
  @Test
  @DisplayName("Test shouldBeMarkedAsPossiblyUsed(Processable) with 'processable'; when LibraryClass(); then return 'true'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean ShortestClassUsageMarker.shouldBeMarkedAsPossiblyUsed(Processable)"})
  void testShouldBeMarkedAsPossiblyUsedWithProcessable_whenLibraryClass_thenReturnTrue() {
    // Arrange
    ShortestClassUsageMarker shortestClassUsageMarker = new ShortestClassUsageMarker(new ShortestUsageMarker(),
        "Just cause");

    // Act and Assert
    assertTrue(shortestClassUsageMarker.shouldBeMarkedAsPossiblyUsed(new LibraryClass()));
  }

  /**
   * Test {@link ShortestClassUsageMarker#shouldBeMarkedAsPossiblyUsed(ProgramClass, ProgramMember)} with {@code programClass}, {@code programMember}.
   * <p>
   * Method under test: {@link ShortestClassUsageMarker#shouldBeMarkedAsPossiblyUsed(ProgramClass, ProgramMember)}
   */
  @Test
  @DisplayName("Test shouldBeMarkedAsPossiblyUsed(ProgramClass, ProgramMember) with 'programClass', 'programMember'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean ShortestClassUsageMarker.shouldBeMarkedAsPossiblyUsed(ProgramClass, ProgramMember)"})
  void testShouldBeMarkedAsPossiblyUsedWithProgramClassProgramMember() {
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
   * Test {@link ShortestClassUsageMarker#shouldBeMarkedAsPossiblyUsed(ProgramClass, ProgramMember)} with {@code programClass}, {@code programMember}.
   * <p>
   * Method under test: {@link ShortestClassUsageMarker#shouldBeMarkedAsPossiblyUsed(ProgramClass, ProgramMember)}
   */
  @Test
  @DisplayName("Test shouldBeMarkedAsPossiblyUsed(ProgramClass, ProgramMember) with 'programClass', 'programMember'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean ShortestClassUsageMarker.shouldBeMarkedAsPossiblyUsed(ProgramClass, ProgramMember)"})
  void testShouldBeMarkedAsPossiblyUsedWithProgramClassProgramMember2() {
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
   * Test {@link ShortestClassUsageMarker#shouldBeMarkedAsPossiblyUsed(ProgramClass, ProgramMember)} with {@code programClass}, {@code programMember}.
   * <p>
   * Method under test: {@link ShortestClassUsageMarker#shouldBeMarkedAsPossiblyUsed(ProgramClass, ProgramMember)}
   */
  @Test
  @DisplayName("Test shouldBeMarkedAsPossiblyUsed(ProgramClass, ProgramMember) with 'programClass', 'programMember'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean ShortestClassUsageMarker.shouldBeMarkedAsPossiblyUsed(ProgramClass, ProgramMember)"})
  void testShouldBeMarkedAsPossiblyUsedWithProgramClassProgramMember3() {
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
   * Test {@link ShortestClassUsageMarker#shouldBeMarkedAsPossiblyUsed(ProgramClass, ProgramMember)} with {@code programClass}, {@code programMember}.
   * <ul>
   *   <li>When {@link ProgramField#ProgramField()}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ShortestClassUsageMarker#shouldBeMarkedAsPossiblyUsed(ProgramClass, ProgramMember)}
   */
  @Test
  @DisplayName("Test shouldBeMarkedAsPossiblyUsed(ProgramClass, ProgramMember) with 'programClass', 'programMember'; when ProgramField()")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean ShortestClassUsageMarker.shouldBeMarkedAsPossiblyUsed(ProgramClass, ProgramMember)"})
  void testShouldBeMarkedAsPossiblyUsedWithProgramClassProgramMember_whenProgramField() {
    // Arrange
    ShortestClassUsageMarker shortestClassUsageMarker = new ShortestClassUsageMarker(new ShortestUsageMarker(),
        "Just cause");
    ProgramClass programClass = new ProgramClass();

    // Act and Assert
    assertTrue(shortestClassUsageMarker.shouldBeMarkedAsPossiblyUsed(programClass, new ProgramField()));
  }

  /**
   * Test {@link ShortestClassUsageMarker#isPossiblyUsed(Processable)}.
   * <p>
   * Method under test: {@link ShortestClassUsageMarker#isPossiblyUsed(Processable)}
   */
  @Test
  @DisplayName("Test isPossiblyUsed(Processable)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean ShortestClassUsageMarker.isPossiblyUsed(Processable)"})
  void testIsPossiblyUsed() {
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
   * Test {@link ShortestClassUsageMarker#isPossiblyUsed(Processable)}.
   * <ul>
   *   <li>Given {@code Processing Info}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ShortestClassUsageMarker#isPossiblyUsed(Processable)}
   */
  @Test
  @DisplayName("Test isPossiblyUsed(Processable); given 'Processing Info'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean ShortestClassUsageMarker.isPossiblyUsed(Processable)"})
  void testIsPossiblyUsed_givenProcessingInfo() {
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
   * Test {@link ShortestClassUsageMarker#isPossiblyUsed(Processable)}.
   * <ul>
   *   <li>Then return {@code true}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ShortestClassUsageMarker#isPossiblyUsed(Processable)}
   */
  @Test
  @DisplayName("Test isPossiblyUsed(Processable); then return 'true'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean ShortestClassUsageMarker.isPossiblyUsed(Processable)"})
  void testIsPossiblyUsed_thenReturnTrue() {
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
   * Test {@link ShortestClassUsageMarker#isPossiblyUsed(Processable)}.
   * <ul>
   *   <li>When {@link LibraryClass#LibraryClass()}.</li>
   *   <li>Then return {@code false}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ShortestClassUsageMarker#isPossiblyUsed(Processable)}
   */
  @Test
  @DisplayName("Test isPossiblyUsed(Processable); when LibraryClass(); then return 'false'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean ShortestClassUsageMarker.isPossiblyUsed(Processable)"})
  void testIsPossiblyUsed_whenLibraryClass_thenReturnFalse() {
    // Arrange
    ShortestClassUsageMarker shortestClassUsageMarker = new ShortestClassUsageMarker(new ShortestUsageMarker(),
        "Just cause");

    // Act and Assert
    assertFalse(shortestClassUsageMarker.isPossiblyUsed(new LibraryClass()));
  }

  /**
   * Test {@link ShortestClassUsageMarker#getShortestUsageMark(Processable)}.
   * <p>
   * Method under test: {@link ShortestClassUsageMarker#getShortestUsageMark(Processable)}
   */
  @Test
  @DisplayName("Test getShortestUsageMark(Processable)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"ShortestUsageMark ShortestClassUsageMarker.getShortestUsageMark(Processable)"})
  void testGetShortestUsageMark() {
    // Arrange
    ShortestClassUsageMarker shortestClassUsageMarker = new ShortestClassUsageMarker(new ShortestUsageMarker(),
        "Just cause");

    // Act and Assert
    assertNull(shortestClassUsageMarker.getShortestUsageMark(new LibraryClass()));
  }
}
