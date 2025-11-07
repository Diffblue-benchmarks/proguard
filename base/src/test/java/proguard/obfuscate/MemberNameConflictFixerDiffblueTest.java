package proguard.obfuscate;

import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.anyInt;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.HashMap;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import proguard.classfile.ProgramClass;
import proguard.classfile.ProgramField;
import proguard.classfile.ProgramMethod;
import proguard.classfile.util.WarningPrinter;

class MemberNameConflictFixerDiffblueTest {
  /**
   * Test {@link MemberNameConflictFixer#visitProgramField(ProgramClass, ProgramField)}.
   * <p>
   * Method under test: {@link MemberNameConflictFixer#visitProgramField(ProgramClass, ProgramField)}
   */
  @Test
  @DisplayName("Test visitProgramField(ProgramClass, ProgramField)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void MemberNameConflictFixer.visitProgramField(ProgramClass, ProgramField)"})
  void testVisitProgramField() {
    // Arrange
    HashMap<Object, Object> descriptorMap = new HashMap<>();
    WarningPrinter warningPrinter = new WarningPrinter(new PrintWriter(new StringWriter()));
    NumericNameFactory nameFactory = new NumericNameFactory();
    MemberNameConflictFixer memberNameConflictFixer = new MemberNameConflictFixer(false, descriptorMap, warningPrinter,
        new MemberObfuscator(true, nameFactory, new HashMap<>()));
    ProgramClass programClass = mock(ProgramClass.class);
    when(programClass.getString(anyInt())).thenReturn("String");

    // Act
    memberNameConflictFixer.visitProgramField(programClass, new ProgramField());

    // Assert
    verify(programClass, atLeast(1)).getString(eq(0));
  }

  /**
   * Test {@link MemberNameConflictFixer#visitProgramField(ProgramClass, ProgramField)}.
   * <ul>
   *   <li>Given {@code false}.</li>
   *   <li>When {@link ProgramClass} {@link ProgramClass#extendsOrImplements(String)} return {@code false}.</li>
   * </ul>
   * <p>
   * Method under test: {@link MemberNameConflictFixer#visitProgramField(ProgramClass, ProgramField)}
   */
  @Test
  @DisplayName("Test visitProgramField(ProgramClass, ProgramField); given 'false'; when ProgramClass extendsOrImplements(String) return 'false'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void MemberNameConflictFixer.visitProgramField(ProgramClass, ProgramField)"})
  void testVisitProgramField_givenFalse_whenProgramClassExtendsOrImplementsReturnFalse() {
    // Arrange
    HashMap<Object, Object> descriptorMap = new HashMap<>();
    WarningPrinter warningPrinter = new WarningPrinter(new PrintWriter(new StringWriter()));
    NumericNameFactory nameFactory = new NumericNameFactory();
    MemberNameConflictFixer memberNameConflictFixer = new MemberNameConflictFixer(true, descriptorMap, warningPrinter,
        new MemberObfuscator(true, nameFactory, new HashMap<>()));
    ProgramClass programClass = mock(ProgramClass.class);
    when(programClass.extendsOrImplements(Mockito.<String>any())).thenReturn(false);
    when(programClass.getString(anyInt())).thenReturn("String");

    // Act
    memberNameConflictFixer.visitProgramField(programClass, new ProgramField());

    // Assert
    verify(programClass).extendsOrImplements(eq("java/lang/annotation/Annotation"));
    verify(programClass, atLeast(1)).getString(eq(0));
  }

  /**
   * Test {@link MemberNameConflictFixer#visitProgramField(ProgramClass, ProgramField)}.
   * <ul>
   *   <li>Given {@code Processing Info}.</li>
   * </ul>
   * <p>
   * Method under test: {@link MemberNameConflictFixer#visitProgramField(ProgramClass, ProgramField)}
   */
  @Test
  @DisplayName("Test visitProgramField(ProgramClass, ProgramField); given 'Processing Info'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void MemberNameConflictFixer.visitProgramField(ProgramClass, ProgramField)"})
  void testVisitProgramField_givenProcessingInfo() {
    // Arrange
    HashMap<Object, Object> descriptorMap = new HashMap<>();
    WarningPrinter warningPrinter = new WarningPrinter(new PrintWriter(new StringWriter()));
    NumericNameFactory nameFactory = new NumericNameFactory();
    MemberNameConflictFixer memberNameConflictFixer = new MemberNameConflictFixer(true, descriptorMap, warningPrinter,
        new MemberObfuscator(true, nameFactory, new HashMap<>()));
    ProgramClass programClass = mock(ProgramClass.class);
    when(programClass.extendsOrImplements(Mockito.<String>any())).thenReturn(true);
    when(programClass.getString(anyInt())).thenReturn("String");

    ProgramField programField = new ProgramField();
    programField.setProcessingInfo("Processing Info");

    // Act
    memberNameConflictFixer.visitProgramField(programClass, programField);

    // Assert
    verify(programClass).extendsOrImplements(eq("java/lang/annotation/Annotation"));
    verify(programClass, atLeast(1)).getString(eq(0));
  }

  /**
   * Test {@link MemberNameConflictFixer#visitProgramField(ProgramClass, ProgramField)}.
   * <ul>
   *   <li>Given {@code true}.</li>
   *   <li>Then calls {@link ProgramClass#extendsOrImplements(String)}.</li>
   * </ul>
   * <p>
   * Method under test: {@link MemberNameConflictFixer#visitProgramField(ProgramClass, ProgramField)}
   */
  @Test
  @DisplayName("Test visitProgramField(ProgramClass, ProgramField); given 'true'; then calls extendsOrImplements(String)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void MemberNameConflictFixer.visitProgramField(ProgramClass, ProgramField)"})
  void testVisitProgramField_givenTrue_thenCallsExtendsOrImplements() {
    // Arrange
    HashMap<Object, Object> descriptorMap = new HashMap<>();
    WarningPrinter warningPrinter = new WarningPrinter(new PrintWriter(new StringWriter()));
    NumericNameFactory nameFactory = new NumericNameFactory();
    MemberNameConflictFixer memberNameConflictFixer = new MemberNameConflictFixer(true, descriptorMap, warningPrinter,
        new MemberObfuscator(true, nameFactory, new HashMap<>()));
    ProgramClass programClass = mock(ProgramClass.class);
    when(programClass.extendsOrImplements(Mockito.<String>any())).thenReturn(true);
    when(programClass.getString(anyInt())).thenReturn("String");

    // Act
    memberNameConflictFixer.visitProgramField(programClass, new ProgramField());

    // Assert
    verify(programClass).extendsOrImplements(eq("java/lang/annotation/Annotation"));
    verify(programClass, atLeast(1)).getString(eq(0));
  }

  /**
   * Test {@link MemberNameConflictFixer#visitProgramMethod(ProgramClass, ProgramMethod)}.
   * <p>
   * Method under test: {@link MemberNameConflictFixer#visitProgramMethod(ProgramClass, ProgramMethod)}
   */
  @Test
  @DisplayName("Test visitProgramMethod(ProgramClass, ProgramMethod)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void MemberNameConflictFixer.visitProgramMethod(ProgramClass, ProgramMethod)"})
  void testVisitProgramMethod() {
    // Arrange
    HashMap<Object, Object> descriptorMap = new HashMap<>();
    WarningPrinter warningPrinter = new WarningPrinter(new PrintWriter(new StringWriter()));
    NumericNameFactory nameFactory = new NumericNameFactory();
    MemberNameConflictFixer memberNameConflictFixer = new MemberNameConflictFixer(false, descriptorMap, warningPrinter,
        new MemberObfuscator(true, nameFactory, new HashMap<>()));
    ProgramClass programClass = mock(ProgramClass.class);
    when(programClass.getString(anyInt())).thenReturn("String");

    // Act
    memberNameConflictFixer.visitProgramMethod(programClass, new ProgramMethod());

    // Assert
    verify(programClass, atLeast(1)).getString(eq(0));
  }

  /**
   * Test {@link MemberNameConflictFixer#visitProgramMethod(ProgramClass, ProgramMethod)}.
   * <ul>
   *   <li>Given {@code false}.</li>
   *   <li>When {@link ProgramClass} {@link ProgramClass#extendsOrImplements(String)} return {@code false}.</li>
   * </ul>
   * <p>
   * Method under test: {@link MemberNameConflictFixer#visitProgramMethod(ProgramClass, ProgramMethod)}
   */
  @Test
  @DisplayName("Test visitProgramMethod(ProgramClass, ProgramMethod); given 'false'; when ProgramClass extendsOrImplements(String) return 'false'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void MemberNameConflictFixer.visitProgramMethod(ProgramClass, ProgramMethod)"})
  void testVisitProgramMethod_givenFalse_whenProgramClassExtendsOrImplementsReturnFalse() {
    // Arrange
    HashMap<Object, Object> descriptorMap = new HashMap<>();
    WarningPrinter warningPrinter = new WarningPrinter(new PrintWriter(new StringWriter()));
    NumericNameFactory nameFactory = new NumericNameFactory();
    MemberNameConflictFixer memberNameConflictFixer = new MemberNameConflictFixer(true, descriptorMap, warningPrinter,
        new MemberObfuscator(true, nameFactory, new HashMap<>()));
    ProgramClass programClass = mock(ProgramClass.class);
    when(programClass.extendsOrImplements(Mockito.<String>any())).thenReturn(false);
    when(programClass.getString(anyInt())).thenReturn("String");

    // Act
    memberNameConflictFixer.visitProgramMethod(programClass, new ProgramMethod());

    // Assert
    verify(programClass).extendsOrImplements(eq("java/lang/annotation/Annotation"));
    verify(programClass, atLeast(1)).getString(eq(0));
  }

  /**
   * Test {@link MemberNameConflictFixer#visitProgramMethod(ProgramClass, ProgramMethod)}.
   * <ul>
   *   <li>Given {@code Processing Info}.</li>
   * </ul>
   * <p>
   * Method under test: {@link MemberNameConflictFixer#visitProgramMethod(ProgramClass, ProgramMethod)}
   */
  @Test
  @DisplayName("Test visitProgramMethod(ProgramClass, ProgramMethod); given 'Processing Info'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void MemberNameConflictFixer.visitProgramMethod(ProgramClass, ProgramMethod)"})
  void testVisitProgramMethod_givenProcessingInfo() {
    // Arrange
    HashMap<Object, Object> descriptorMap = new HashMap<>();
    WarningPrinter warningPrinter = new WarningPrinter(new PrintWriter(new StringWriter()));
    NumericNameFactory nameFactory = new NumericNameFactory();
    MemberNameConflictFixer memberNameConflictFixer = new MemberNameConflictFixer(true, descriptorMap, warningPrinter,
        new MemberObfuscator(true, nameFactory, new HashMap<>()));
    ProgramClass programClass = mock(ProgramClass.class);
    when(programClass.extendsOrImplements(Mockito.<String>any())).thenReturn(true);
    when(programClass.getString(anyInt())).thenReturn("String");

    ProgramMethod programMethod = new ProgramMethod();
    programMethod.setProcessingInfo("Processing Info");

    // Act
    memberNameConflictFixer.visitProgramMethod(programClass, programMethod);

    // Assert
    verify(programClass).extendsOrImplements(eq("java/lang/annotation/Annotation"));
    verify(programClass, atLeast(1)).getString(eq(0));
  }

  /**
   * Test {@link MemberNameConflictFixer#visitProgramMethod(ProgramClass, ProgramMethod)}.
   * <ul>
   *   <li>Given {@code true}.</li>
   *   <li>Then calls {@link ProgramClass#extendsOrImplements(String)}.</li>
   * </ul>
   * <p>
   * Method under test: {@link MemberNameConflictFixer#visitProgramMethod(ProgramClass, ProgramMethod)}
   */
  @Test
  @DisplayName("Test visitProgramMethod(ProgramClass, ProgramMethod); given 'true'; then calls extendsOrImplements(String)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void MemberNameConflictFixer.visitProgramMethod(ProgramClass, ProgramMethod)"})
  void testVisitProgramMethod_givenTrue_thenCallsExtendsOrImplements() {
    // Arrange
    HashMap<Object, Object> descriptorMap = new HashMap<>();
    WarningPrinter warningPrinter = new WarningPrinter(new PrintWriter(new StringWriter()));
    NumericNameFactory nameFactory = new NumericNameFactory();
    MemberNameConflictFixer memberNameConflictFixer = new MemberNameConflictFixer(true, descriptorMap, warningPrinter,
        new MemberObfuscator(true, nameFactory, new HashMap<>()));
    ProgramClass programClass = mock(ProgramClass.class);
    when(programClass.extendsOrImplements(Mockito.<String>any())).thenReturn(true);
    when(programClass.getString(anyInt())).thenReturn("String");

    // Act
    memberNameConflictFixer.visitProgramMethod(programClass, new ProgramMethod());

    // Assert
    verify(programClass).extendsOrImplements(eq("java/lang/annotation/Annotation"));
    verify(programClass, atLeast(1)).getString(eq(0));
  }
}
