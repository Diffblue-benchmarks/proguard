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
import proguard.classfile.ProgramMethod;
import proguard.classfile.attribute.visitor.AttributeVisitor;
import proguard.classfile.constant.ClassConstant;
import proguard.classfile.constant.Constant;
import proguard.classfile.constant.DoubleConstant;
import proguard.classfile.constant.visitor.ConstantVisitor;
import proguard.classfile.kotlin.visitor.KotlinMetadataVisitor;
import proguard.classfile.visitor.ClassVisitor;
import proguard.classfile.visitor.MemberVisitor;
import proguard.fixer.kotlin.KotlinAnnotationCounter;
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
  @MethodsUnderTest({
      "void proguard.shrink.ShortestClassUsageMarker.<init>(proguard.shrink.ShortestUsageMarker, java.lang.String)"})
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
  @MethodsUnderTest({"proguard.shrink.ShortestUsageMarker proguard.shrink.ShortestClassUsageMarker.getUsageMarker()"})
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
  @MethodsUnderTest({
      "void proguard.shrink.ShortestClassUsageMarker.markProgramClassBody(proguard.classfile.ProgramClass)"})
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
   *   <li>Given {@link ShortestUsageMarker} {@link ShortestUsageMarker#shouldBeMarkedAsUsed(Processable)} return {@code false}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ShortestClassUsageMarker#markProgramFieldBody(ProgramClass, ProgramField)}
   */
  @Test
  @DisplayName("Test markProgramFieldBody(ProgramClass, ProgramField); given ShortestUsageMarker shouldBeMarkedAsUsed(Processable) return 'false'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "void proguard.shrink.ShortestClassUsageMarker.markProgramFieldBody(proguard.classfile.ProgramClass, proguard.classfile.ProgramField)"})
  void testMarkProgramFieldBody_givenShortestUsageMarkerShouldBeMarkedAsUsedReturnFalse() {
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
   * Test {@link ShortestClassUsageMarker#markProgramFieldBody(ProgramClass, ProgramField)}.
   * <ul>
   *   <li>Then calls {@link ProgramClass#kotlinMetadataAccept(KotlinMetadataVisitor)}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ShortestClassUsageMarker#markProgramFieldBody(ProgramClass, ProgramField)}
   */
  @Test
  @DisplayName("Test markProgramFieldBody(ProgramClass, ProgramField); then calls kotlinMetadataAccept(KotlinMetadataVisitor)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "void proguard.shrink.ShortestClassUsageMarker.markProgramFieldBody(proguard.classfile.ProgramClass, proguard.classfile.ProgramField)"})
  void testMarkProgramFieldBody_thenCallsKotlinMetadataAccept() {
    // Arrange
    ShortestUsageMarker usageMarker = mock(ShortestUsageMarker.class);
    when(usageMarker.getShortestUsageMark(Mockito.<Processable>any())).thenReturn(new ShortestUsageMark("Just cause"));
    doNothing().when(usageMarker).setCurrentUsageMark(Mockito.<ShortestUsageMark>any());
    ShortestClassUsageMarker shortestClassUsageMarker = new ShortestClassUsageMarker(usageMarker, "Just cause");
    ProgramClass programClass = mock(ProgramClass.class);
    doNothing().when(programClass).constantPoolEntryAccept(anyInt(), Mockito.<ConstantVisitor>any());
    doNothing().when(programClass).kotlinMetadataAccept(Mockito.<KotlinMetadataVisitor>any());

    // Act
    shortestClassUsageMarker.markProgramFieldBody(programClass, new ProgramField());

    // Assert
    verify(programClass, atLeast(1)).constantPoolEntryAccept(eq(0), isA(ConstantVisitor.class));
    verify(programClass).kotlinMetadataAccept(isA(KotlinMetadataVisitor.class));
    verify(usageMarker).getShortestUsageMark(isA(Processable.class));
    verify(usageMarker, atLeast(1)).setCurrentUsageMark(Mockito.<ShortestUsageMark>any());
  }

  /**
   * Test {@link ShortestClassUsageMarker#markProgramFieldBody(ProgramClass, ProgramField)}.
   * <ul>
   *   <li>Then calls {@link ShortestUsageMarker#markAsUsed(Processable)}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ShortestClassUsageMarker#markProgramFieldBody(ProgramClass, ProgramField)}
   */
  @Test
  @DisplayName("Test markProgramFieldBody(ProgramClass, ProgramField); then calls markAsUsed(Processable)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "void proguard.shrink.ShortestClassUsageMarker.markProgramFieldBody(proguard.classfile.ProgramClass, proguard.classfile.ProgramField)"})
  void testMarkProgramFieldBody_thenCallsMarkAsUsed() {
    // Arrange
    ShortestUsageMarker usageMarker = mock(ShortestUsageMarker.class);
    doNothing().when(usageMarker).markAsUsed(Mockito.<Processable>any());
    when(usageMarker.shouldBeMarkedAsUsed(Mockito.<Processable>any())).thenReturn(true);
    when(usageMarker.getShortestUsageMark(Mockito.<Processable>any())).thenReturn(new ShortestUsageMark("Just cause"));
    doNothing().when(usageMarker).setCurrentUsageMark(Mockito.<ShortestUsageMark>any());
    ShortestClassUsageMarker shortestClassUsageMarker = new ShortestClassUsageMarker(usageMarker, "Just cause");
    ProgramClass programClass = new ProgramClass(1, 3, new Constant[]{new DoubleConstant()}, 1, 1, 1);

    // Act
    shortestClassUsageMarker.markProgramFieldBody(programClass, new ProgramField());

    // Assert
    verify(usageMarker).getShortestUsageMark(isA(Processable.class));
    verify(usageMarker, atLeast(1)).markAsUsed(isA(Processable.class));
    verify(usageMarker, atLeast(1)).setCurrentUsageMark(Mockito.<ShortestUsageMark>any());
    verify(usageMarker, atLeast(1)).shouldBeMarkedAsUsed(isA(Processable.class));
  }

  /**
   * Test {@link ShortestClassUsageMarker#markProgramFieldBody(ProgramClass, ProgramField)}.
   * <ul>
   *   <li>When {@link Constant} {@link Constant#accept(Clazz, ConstantVisitor)} does nothing.</li>
   *   <li>Then calls {@link Constant#accept(Clazz, ConstantVisitor)}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ShortestClassUsageMarker#markProgramFieldBody(ProgramClass, ProgramField)}
   */
  @Test
  @DisplayName("Test markProgramFieldBody(ProgramClass, ProgramField); when Constant accept(Clazz, ConstantVisitor) does nothing; then calls accept(Clazz, ConstantVisitor)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "void proguard.shrink.ShortestClassUsageMarker.markProgramFieldBody(proguard.classfile.ProgramClass, proguard.classfile.ProgramField)"})
  void testMarkProgramFieldBody_whenConstantAcceptDoesNothing_thenCallsAccept() {
    // Arrange
    ShortestUsageMarker usageMarker = mock(ShortestUsageMarker.class);
    when(usageMarker.getShortestUsageMark(Mockito.<Processable>any())).thenReturn(new ShortestUsageMark("Just cause"));
    doNothing().when(usageMarker).setCurrentUsageMark(Mockito.<ShortestUsageMark>any());
    ShortestClassUsageMarker shortestClassUsageMarker = new ShortestClassUsageMarker(usageMarker, "Just cause");
    Constant constant = mock(Constant.class);
    doNothing().when(constant).accept(Mockito.<Clazz>any(), Mockito.<ConstantVisitor>any());
    ProgramClass programClass = new ProgramClass(1, 3, new Constant[]{constant}, 1, 1, 1);

    // Act
    shortestClassUsageMarker.markProgramFieldBody(programClass, new ProgramField());

    // Assert
    verify(constant, atLeast(1)).accept(isA(Clazz.class), isA(ConstantVisitor.class));
    verify(usageMarker).getShortestUsageMark(isA(Processable.class));
    verify(usageMarker, atLeast(1)).setCurrentUsageMark(Mockito.<ShortestUsageMark>any());
  }

  /**
   * Test {@link ShortestClassUsageMarker#markProgramFieldBody(ProgramClass, ProgramField)}.
   * <ul>
   *   <li>When {@link ProgramField} {@link ProgramField#accept(ProgramClass, MemberVisitor)} does nothing.</li>
   *   <li>Then calls {@link ProgramField#accept(ProgramClass, MemberVisitor)}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ShortestClassUsageMarker#markProgramFieldBody(ProgramClass, ProgramField)}
   */
  @Test
  @DisplayName("Test markProgramFieldBody(ProgramClass, ProgramField); when ProgramField accept(ProgramClass, MemberVisitor) does nothing; then calls accept(ProgramClass, MemberVisitor)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "void proguard.shrink.ShortestClassUsageMarker.markProgramFieldBody(proguard.classfile.ProgramClass, proguard.classfile.ProgramField)"})
  void testMarkProgramFieldBody_whenProgramFieldAcceptDoesNothing_thenCallsAccept() {
    // Arrange
    ShortestUsageMarker usageMarker = mock(ShortestUsageMarker.class);
    when(usageMarker.getShortestUsageMark(Mockito.<Processable>any())).thenReturn(new ShortestUsageMark("Just cause"));
    doNothing().when(usageMarker).setCurrentUsageMark(Mockito.<ShortestUsageMark>any());
    ShortestClassUsageMarker shortestClassUsageMarker = new ShortestClassUsageMarker(usageMarker, "Just cause");
    ProgramClass programClass = mock(ProgramClass.class);
    doNothing().when(programClass).constantPoolEntryAccept(anyInt(), Mockito.<ConstantVisitor>any());
    ProgramField programField = mock(ProgramField.class);
    doNothing().when(programField).accept(Mockito.<ProgramClass>any(), Mockito.<MemberVisitor>any());
    doNothing().when(programField).attributesAccept(Mockito.<ProgramClass>any(), Mockito.<AttributeVisitor>any());
    doNothing().when(programField).referencedClassesAccept(Mockito.<ClassVisitor>any());

    // Act
    shortestClassUsageMarker.markProgramFieldBody(programClass, programField);

    // Assert
    verify(programClass, atLeast(1)).constantPoolEntryAccept(eq(0), isA(ConstantVisitor.class));
    verify(programField).accept(isA(ProgramClass.class), isA(MemberVisitor.class));
    verify(programField).attributesAccept(isA(ProgramClass.class), isA(AttributeVisitor.class));
    verify(programField).referencedClassesAccept(isA(ClassVisitor.class));
    verify(usageMarker).getShortestUsageMark(isA(Processable.class));
    verify(usageMarker, atLeast(1)).setCurrentUsageMark(Mockito.<ShortestUsageMark>any());
  }

  /**
   * Test {@link ShortestClassUsageMarker#markProgramMethodBody(ProgramClass, ProgramMethod)}.
   * <ul>
   *   <li>Given {@code <init>}.</li>
   *   <li>When {@link ProgramMethod} {@link ProgramMember#getName(Clazz)} return {@code <init>}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ShortestClassUsageMarker#markProgramMethodBody(ProgramClass, ProgramMethod)}
   */
  @Test
  @DisplayName("Test markProgramMethodBody(ProgramClass, ProgramMethod); given '<init>'; when ProgramMethod getName(Clazz) return '<init>'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "void proguard.shrink.ShortestClassUsageMarker.markProgramMethodBody(proguard.classfile.ProgramClass, proguard.classfile.ProgramMethod)"})
  void testMarkProgramMethodBody_givenInit_whenProgramMethodGetNameReturnInit() {
    // Arrange
    ShortestUsageMarker usageMarker = mock(ShortestUsageMarker.class);
    when(usageMarker.getShortestUsageMark(Mockito.<Processable>any())).thenReturn(new ShortestUsageMark("Just cause"));
    doNothing().when(usageMarker).setCurrentUsageMark(Mockito.<ShortestUsageMark>any());
    ShortestClassUsageMarker shortestClassUsageMarker = new ShortestClassUsageMarker(usageMarker, "Just cause");
    ProgramClass programClass = mock(ProgramClass.class);
    doNothing().when(programClass).constantPoolEntryAccept(anyInt(), Mockito.<ConstantVisitor>any());
    ProgramMethod programMethod = mock(ProgramMethod.class);
    doNothing().when(programMethod).accept(Mockito.<Clazz>any(), Mockito.<MemberVisitor>any());
    when(programMethod.getName(Mockito.<Clazz>any())).thenReturn("<init>");
    doNothing().when(programMethod).attributesAccept(Mockito.<ProgramClass>any(), Mockito.<AttributeVisitor>any());
    doNothing().when(programMethod).referencedClassesAccept(Mockito.<ClassVisitor>any());

    // Act
    shortestClassUsageMarker.markProgramMethodBody(programClass, programMethod);

    // Assert
    verify(programClass, atLeast(1)).constantPoolEntryAccept(eq(0), isA(ConstantVisitor.class));
    verify(programMethod).accept(isA(Clazz.class), isA(MemberVisitor.class));
    verify(programMethod).getName(isA(Clazz.class));
    verify(programMethod).attributesAccept(isA(ProgramClass.class), isA(AttributeVisitor.class));
    verify(programMethod).referencedClassesAccept(isA(ClassVisitor.class));
    verify(usageMarker).getShortestUsageMark(isA(Processable.class));
    verify(usageMarker, atLeast(1)).setCurrentUsageMark(Mockito.<ShortestUsageMark>any());
  }

  /**
   * Test {@link ShortestClassUsageMarker#markProgramMethodBody(ProgramClass, ProgramMethod)}.
   * <ul>
   *   <li>Given {@code Name}.</li>
   *   <li>When {@link ProgramMethod} {@link ProgramMember#getName(Clazz)} return {@code Name}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ShortestClassUsageMarker#markProgramMethodBody(ProgramClass, ProgramMethod)}
   */
  @Test
  @DisplayName("Test markProgramMethodBody(ProgramClass, ProgramMethod); given 'Name'; when ProgramMethod getName(Clazz) return 'Name'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "void proguard.shrink.ShortestClassUsageMarker.markProgramMethodBody(proguard.classfile.ProgramClass, proguard.classfile.ProgramMethod)"})
  void testMarkProgramMethodBody_givenName_whenProgramMethodGetNameReturnName() {
    // Arrange
    ShortestUsageMarker usageMarker = mock(ShortestUsageMarker.class);
    when(usageMarker.getShortestUsageMark(Mockito.<Processable>any())).thenReturn(new ShortestUsageMark("Just cause"));
    doNothing().when(usageMarker).setCurrentUsageMark(Mockito.<ShortestUsageMark>any());
    ShortestClassUsageMarker shortestClassUsageMarker = new ShortestClassUsageMarker(usageMarker, "Just cause");
    ProgramClass programClass = mock(ProgramClass.class);
    doNothing().when(programClass).constantPoolEntryAccept(anyInt(), Mockito.<ConstantVisitor>any());
    ProgramMethod programMethod = mock(ProgramMethod.class);
    doNothing().when(programMethod).accept(Mockito.<Clazz>any(), Mockito.<MemberVisitor>any());
    when(programMethod.getName(Mockito.<Clazz>any())).thenReturn("Name");
    doNothing().when(programMethod).attributesAccept(Mockito.<ProgramClass>any(), Mockito.<AttributeVisitor>any());
    doNothing().when(programMethod).referencedClassesAccept(Mockito.<ClassVisitor>any());

    // Act
    shortestClassUsageMarker.markProgramMethodBody(programClass, programMethod);

    // Assert
    verify(programClass, atLeast(1)).constantPoolEntryAccept(eq(0), isA(ConstantVisitor.class));
    verify(programMethod).accept(isA(Clazz.class), isA(MemberVisitor.class));
    verify(programMethod).getName(isA(Clazz.class));
    verify(programMethod).attributesAccept(isA(ProgramClass.class), isA(AttributeVisitor.class));
    verify(programMethod).referencedClassesAccept(isA(ClassVisitor.class));
    verify(usageMarker).getShortestUsageMark(isA(Processable.class));
    verify(usageMarker, atLeast(1)).setCurrentUsageMark(Mockito.<ShortestUsageMark>any());
  }

  /**
   * Test {@link ShortestClassUsageMarker#markProgramMethodBody(ProgramClass, ProgramMethod)}.
   * <ul>
   *   <li>Given {@code String}.</li>
   *   <li>Then calls {@link ProgramClass#getString(int)}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ShortestClassUsageMarker#markProgramMethodBody(ProgramClass, ProgramMethod)}
   */
  @Test
  @DisplayName("Test markProgramMethodBody(ProgramClass, ProgramMethod); given 'String'; then calls getString(int)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "void proguard.shrink.ShortestClassUsageMarker.markProgramMethodBody(proguard.classfile.ProgramClass, proguard.classfile.ProgramMethod)"})
  void testMarkProgramMethodBody_givenString_thenCallsGetString() {
    // Arrange
    ShortestUsageMarker usageMarker = mock(ShortestUsageMarker.class);
    when(usageMarker.getShortestUsageMark(Mockito.<Processable>any())).thenReturn(new ShortestUsageMark("Just cause"));
    doNothing().when(usageMarker).setCurrentUsageMark(Mockito.<ShortestUsageMark>any());
    ShortestClassUsageMarker shortestClassUsageMarker = new ShortestClassUsageMarker(usageMarker, "Just cause");
    ProgramClass programClass = mock(ProgramClass.class);
    doNothing().when(programClass).kotlinMetadataAccept(Mockito.<KotlinMetadataVisitor>any());
    when(programClass.getString(anyInt())).thenReturn("String");
    doNothing().when(programClass).constantPoolEntryAccept(anyInt(), Mockito.<ConstantVisitor>any());

    // Act
    shortestClassUsageMarker.markProgramMethodBody(programClass, new ProgramMethod());

    // Assert
    verify(programClass, atLeast(1)).constantPoolEntryAccept(eq(0), isA(ConstantVisitor.class));
    verify(programClass).getString(eq(0));
    verify(programClass, atLeast(1)).kotlinMetadataAccept(Mockito.<KotlinMetadataVisitor>any());
    verify(usageMarker).getShortestUsageMark(isA(Processable.class));
    verify(usageMarker, atLeast(1)).setCurrentUsageMark(Mockito.<ShortestUsageMark>any());
  }

  /**
   * Test {@link ShortestClassUsageMarker#markProgramMethodBody(ProgramClass, ProgramMethod)}.
   * <ul>
   *   <li>Then calls {@link ProgramMethod#accept(ProgramClass, MemberVisitor)}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ShortestClassUsageMarker#markProgramMethodBody(ProgramClass, ProgramMethod)}
   */
  @Test
  @DisplayName("Test markProgramMethodBody(ProgramClass, ProgramMethod); then calls accept(ProgramClass, MemberVisitor)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "void proguard.shrink.ShortestClassUsageMarker.markProgramMethodBody(proguard.classfile.ProgramClass, proguard.classfile.ProgramMethod)"})
  void testMarkProgramMethodBody_thenCallsAccept() {
    // Arrange
    ShortestUsageMarker usageMarker = mock(ShortestUsageMarker.class);
    when(usageMarker.getShortestUsageMark(Mockito.<Processable>any())).thenReturn(new ShortestUsageMark("Just cause"));
    doNothing().when(usageMarker).setCurrentUsageMark(Mockito.<ShortestUsageMark>any());

    ShortestClassUsageMarker shortestClassUsageMarker = new ShortestClassUsageMarker(usageMarker, "Just cause");
    shortestClassUsageMarker.setExtraMethodVisitor(new KotlinAnnotationCounter());
    ProgramClass programClass = mock(ProgramClass.class);
    doNothing().when(programClass).constantPoolEntryAccept(anyInt(), Mockito.<ConstantVisitor>any());
    ProgramMethod programMethod = mock(ProgramMethod.class);
    doNothing().when(programMethod).accept(Mockito.<ProgramClass>any(), Mockito.<MemberVisitor>any());
    doNothing().when(programMethod).accept(Mockito.<Clazz>any(), Mockito.<MemberVisitor>any());
    when(programMethod.getName(Mockito.<Clazz>any())).thenReturn("Name");
    doNothing().when(programMethod).attributesAccept(Mockito.<ProgramClass>any(), Mockito.<AttributeVisitor>any());
    doNothing().when(programMethod).referencedClassesAccept(Mockito.<ClassVisitor>any());

    // Act
    shortestClassUsageMarker.markProgramMethodBody(programClass, programMethod);

    // Assert
    verify(programClass, atLeast(1)).constantPoolEntryAccept(eq(0), isA(ConstantVisitor.class));
    verify(programMethod).accept(isA(Clazz.class), isA(MemberVisitor.class));
    verify(programMethod).getName(isA(Clazz.class));
    verify(programMethod).accept(isA(ProgramClass.class), isA(MemberVisitor.class));
    verify(programMethod).attributesAccept(isA(ProgramClass.class), isA(AttributeVisitor.class));
    verify(programMethod).referencedClassesAccept(isA(ClassVisitor.class));
    verify(usageMarker).getShortestUsageMark(isA(Processable.class));
    verify(usageMarker, atLeast(1)).setCurrentUsageMark(Mockito.<ShortestUsageMark>any());
  }

  /**
   * Test {@link ShortestClassUsageMarker#markProgramMethodBody(ProgramClass, ProgramMethod)}.
   * <ul>
   *   <li>When {@link ProgramClass} {@link ProgramClass#getString(int)} return {@code <init>}.</li>
   *   <li>Then calls {@link ProgramClass#getString(int)}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ShortestClassUsageMarker#markProgramMethodBody(ProgramClass, ProgramMethod)}
   */
  @Test
  @DisplayName("Test markProgramMethodBody(ProgramClass, ProgramMethod); when ProgramClass getString(int) return '<init>'; then calls getString(int)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "void proguard.shrink.ShortestClassUsageMarker.markProgramMethodBody(proguard.classfile.ProgramClass, proguard.classfile.ProgramMethod)"})
  void testMarkProgramMethodBody_whenProgramClassGetStringReturnInit_thenCallsGetString() {
    // Arrange
    ShortestUsageMarker usageMarker = mock(ShortestUsageMarker.class);
    when(usageMarker.getShortestUsageMark(Mockito.<Processable>any())).thenReturn(new ShortestUsageMark("Just cause"));
    doNothing().when(usageMarker).setCurrentUsageMark(Mockito.<ShortestUsageMark>any());
    ShortestClassUsageMarker shortestClassUsageMarker = new ShortestClassUsageMarker(usageMarker, "Just cause");
    ProgramClass programClass = mock(ProgramClass.class);
    doNothing().when(programClass).kotlinMetadataAccept(Mockito.<KotlinMetadataVisitor>any());
    when(programClass.getString(anyInt())).thenReturn("<init>");
    doNothing().when(programClass).constantPoolEntryAccept(anyInt(), Mockito.<ConstantVisitor>any());

    // Act
    shortestClassUsageMarker.markProgramMethodBody(programClass, new ProgramMethod());

    // Assert
    verify(programClass, atLeast(1)).constantPoolEntryAccept(eq(0), isA(ConstantVisitor.class));
    verify(programClass).getString(eq(0));
    verify(programClass).kotlinMetadataAccept(isA(KotlinMetadataVisitor.class));
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
  @MethodsUnderTest({
      "void proguard.shrink.ShortestClassUsageMarker.markMethodHierarchy(proguard.classfile.Clazz, proguard.classfile.Method)"})
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
  @MethodsUnderTest({
      "void proguard.shrink.ShortestClassUsageMarker.markMethodHierarchy(proguard.classfile.Clazz, proguard.classfile.Method)"})
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
  @MethodsUnderTest({
      "void proguard.shrink.ShortestClassUsageMarker.markMethodHierarchy(proguard.classfile.Clazz, proguard.classfile.Method)"})
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
  @MethodsUnderTest({
      "void proguard.shrink.ShortestClassUsageMarker.markMethodHierarchy(proguard.classfile.Clazz, proguard.classfile.Method)"})
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
  @MethodsUnderTest({
      "void proguard.shrink.ShortestClassUsageMarker.markMethodHierarchy(proguard.classfile.Clazz, proguard.classfile.Method)"})
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
  @MethodsUnderTest({
      "boolean proguard.shrink.ShortestClassUsageMarker.shouldBeMarkedAsUsed(proguard.util.Processable)"})
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
  @MethodsUnderTest({
      "boolean proguard.shrink.ShortestClassUsageMarker.shouldBeMarkedAsUsed(proguard.util.Processable)"})
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
  @MethodsUnderTest({
      "boolean proguard.shrink.ShortestClassUsageMarker.shouldBeMarkedAsUsed(proguard.util.Processable)"})
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
  @MethodsUnderTest({
      "boolean proguard.shrink.ShortestClassUsageMarker.shouldBeMarkedAsUsed(proguard.util.Processable)"})
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
  @MethodsUnderTest({
      "boolean proguard.shrink.ShortestClassUsageMarker.shouldBeMarkedAsUsed(proguard.util.Processable)"})
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
  @MethodsUnderTest({
      "boolean proguard.shrink.ShortestClassUsageMarker.shouldBeMarkedAsUsed(proguard.classfile.ProgramClass)"})
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
  @MethodsUnderTest({
      "boolean proguard.shrink.ShortestClassUsageMarker.shouldBeMarkedAsUsed(proguard.classfile.ProgramClass)"})
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
  @MethodsUnderTest({
      "boolean proguard.shrink.ShortestClassUsageMarker.shouldBeMarkedAsUsed(proguard.classfile.ProgramClass, proguard.classfile.ProgramMember)"})
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
  @MethodsUnderTest({
      "boolean proguard.shrink.ShortestClassUsageMarker.shouldBeMarkedAsUsed(proguard.classfile.ProgramClass, proguard.classfile.ProgramMember)"})
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
  @MethodsUnderTest({
      "boolean proguard.shrink.ShortestClassUsageMarker.shouldBeMarkedAsUsed(proguard.classfile.ProgramClass, proguard.classfile.ProgramMember)"})
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
  @MethodsUnderTest({
      "boolean proguard.shrink.ShortestClassUsageMarker.shouldBeMarkedAsUsed(proguard.classfile.ProgramClass, proguard.classfile.ProgramMember)"})
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
  @MethodsUnderTest({
      "boolean proguard.shrink.ShortestClassUsageMarker.shouldBeMarkedAsUsed(proguard.classfile.ProgramClass)"})
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
  @MethodsUnderTest({
      "boolean proguard.shrink.ShortestClassUsageMarker.shouldBeMarkedAsUsed(proguard.classfile.ProgramClass)"})
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
  @MethodsUnderTest({"boolean proguard.shrink.ShortestClassUsageMarker.isUsed(proguard.util.Processable)"})
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
  @MethodsUnderTest({"boolean proguard.shrink.ShortestClassUsageMarker.isUsed(proguard.util.Processable)"})
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
  @MethodsUnderTest({"boolean proguard.shrink.ShortestClassUsageMarker.isUsed(proguard.util.Processable)"})
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
  @MethodsUnderTest({"boolean proguard.shrink.ShortestClassUsageMarker.isUsed(proguard.util.Processable)"})
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
  @MethodsUnderTest({"void proguard.shrink.ShortestClassUsageMarker.markAsPossiblyUsed(proguard.util.Processable)"})
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
  @MethodsUnderTest({
      "boolean proguard.shrink.ShortestClassUsageMarker.shouldBeMarkedAsPossiblyUsed(proguard.util.Processable)"})
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
  @MethodsUnderTest({
      "boolean proguard.shrink.ShortestClassUsageMarker.shouldBeMarkedAsPossiblyUsed(proguard.util.Processable)"})
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
  @MethodsUnderTest({
      "boolean proguard.shrink.ShortestClassUsageMarker.shouldBeMarkedAsPossiblyUsed(proguard.util.Processable)"})
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
  @MethodsUnderTest({
      "boolean proguard.shrink.ShortestClassUsageMarker.shouldBeMarkedAsPossiblyUsed(proguard.util.Processable)"})
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
  @MethodsUnderTest({
      "boolean proguard.shrink.ShortestClassUsageMarker.shouldBeMarkedAsPossiblyUsed(proguard.classfile.ProgramClass, proguard.classfile.ProgramMember)"})
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
  @MethodsUnderTest({
      "boolean proguard.shrink.ShortestClassUsageMarker.shouldBeMarkedAsPossiblyUsed(proguard.classfile.ProgramClass, proguard.classfile.ProgramMember)"})
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
  @MethodsUnderTest({
      "boolean proguard.shrink.ShortestClassUsageMarker.shouldBeMarkedAsPossiblyUsed(proguard.classfile.ProgramClass, proguard.classfile.ProgramMember)"})
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
  @MethodsUnderTest({
      "boolean proguard.shrink.ShortestClassUsageMarker.shouldBeMarkedAsPossiblyUsed(proguard.classfile.ProgramClass, proguard.classfile.ProgramMember)"})
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
  @MethodsUnderTest({"boolean proguard.shrink.ShortestClassUsageMarker.isPossiblyUsed(proguard.util.Processable)"})
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
  @MethodsUnderTest({"boolean proguard.shrink.ShortestClassUsageMarker.isPossiblyUsed(proguard.util.Processable)"})
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
  @MethodsUnderTest({"boolean proguard.shrink.ShortestClassUsageMarker.isPossiblyUsed(proguard.util.Processable)"})
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
  @MethodsUnderTest({"boolean proguard.shrink.ShortestClassUsageMarker.isPossiblyUsed(proguard.util.Processable)"})
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
  @MethodsUnderTest({
      "proguard.shrink.ShortestUsageMark proguard.shrink.ShortestClassUsageMarker.getShortestUsageMark(proguard.util.Processable)"})
  void testGetShortestUsageMark() {
    // Arrange
    ShortestClassUsageMarker shortestClassUsageMarker = new ShortestClassUsageMarker(new ShortestUsageMarker(),
        "Just cause");

    // Act and Assert
    assertNull(shortestClassUsageMarker.getShortestUsageMark(new LibraryClass()));
  }
}
