package proguard.optimize;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.anyInt;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import com.diffblue.cover.annotations.MethodsUnderTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import proguard.classfile.Clazz;
import proguard.classfile.LibraryClass;
import proguard.classfile.LibraryMethod;
import proguard.classfile.Method;
import proguard.classfile.ProgramClass;
import proguard.classfile.ProgramMethod;
import proguard.classfile.attribute.annotation.ParameterAnnotationsAttribute;
import proguard.classfile.attribute.annotation.RuntimeInvisibleParameterAnnotationsAttribute;

class DuplicateInitializerFixerDiffblueTest {
  /**
   * Test {@link DuplicateInitializerFixer#visitProgramMethod(ProgramClass, ProgramMethod)}.
   * <ul>
   *   <li>Given {@code String}.</li>
   *   <li>Then calls {@link ProgramClass#getString(int)}.</li>
   * </ul>
   * <p>
   * Method under test: {@link DuplicateInitializerFixer#visitProgramMethod(ProgramClass, ProgramMethod)}
   */
  @Test
  @DisplayName("Test visitProgramMethod(ProgramClass, ProgramMethod); given 'String'; then calls getString(int)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void DuplicateInitializerFixer.visitProgramMethod(ProgramClass, ProgramMethod)"})
  void testVisitProgramMethod_givenString_thenCallsGetString() {
    // Arrange
    DuplicateInitializerFixer duplicateInitializerFixer = new DuplicateInitializerFixer();
    ProgramClass programClass = mock(ProgramClass.class);
    when(programClass.getString(anyInt())).thenReturn("String");

    // Act
    duplicateInitializerFixer.visitProgramMethod(programClass, new ProgramMethod());

    // Assert
    verify(programClass).getString(eq(0));
  }

  /**
   * Test {@link DuplicateInitializerFixer#visitAnyParameterAnnotationsAttribute(Clazz, Method, ParameterAnnotationsAttribute)}.
   * <p>
   * Method under test: {@link DuplicateInitializerFixer#visitAnyParameterAnnotationsAttribute(Clazz, Method, ParameterAnnotationsAttribute)}
   */
  @Test
  @DisplayName("Test visitAnyParameterAnnotationsAttribute(Clazz, Method, ParameterAnnotationsAttribute)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "void DuplicateInitializerFixer.visitAnyParameterAnnotationsAttribute(Clazz, Method, ParameterAnnotationsAttribute)"})
  void testVisitAnyParameterAnnotationsAttribute() {
    // Arrange
    DuplicateInitializerFixer duplicateInitializerFixer = new DuplicateInitializerFixer();
    LibraryClass clazz = new LibraryClass();
    LibraryMethod method = new LibraryMethod(1, "Name", "Descriptor");

    RuntimeInvisibleParameterAnnotationsAttribute parameterAnnotationsAttribute = new RuntimeInvisibleParameterAnnotationsAttribute();
    parameterAnnotationsAttribute.u2parameterAnnotationsCount = new int[]{1, 0, 1, 0};

    // Act
    duplicateInitializerFixer.visitAnyParameterAnnotationsAttribute(clazz, method, parameterAnnotationsAttribute);

    // Assert
    assertEquals(1, parameterAnnotationsAttribute.u1parametersCount);
  }
}
