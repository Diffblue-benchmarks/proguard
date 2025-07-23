package proguard;

import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import proguard.classfile.ClassPool;
import proguard.classfile.LibraryClass;
import proguard.classfile.util.WarningPrinter;
import proguard.classfile.visitor.ClassVisitor;

class FullyQualifiedClassNameCheckerDiffblueTest {
  /**
   * Test {@link FullyQualifiedClassNameChecker#checkClassSpecifications(List)}.
   *
   * <p>Method under test: {@link FullyQualifiedClassNameChecker#checkClassSpecifications(List)}
   */
  @Test
  @DisplayName("Test checkClassSpecifications(List)")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void FullyQualifiedClassNameChecker.checkClassSpecifications(List)"})
  void testCheckClassSpecifications() {
    // Arrange
    ClassPool programClassPool = mock(ClassPool.class);
    when(programClassPool.getClass(Mockito.<String>any())).thenReturn(new LibraryClass());
    ClassPool libraryClassPool = new ClassPool();
    FullyQualifiedClassNameChecker fullyQualifiedClassNameChecker =
        new FullyQualifiedClassNameChecker(
            programClassPool,
            libraryClassPool,
            new WarningPrinter(new PrintWriter(new StringWriter())));

    ArrayList<Object> classSpecifications = new ArrayList<>();
    classSpecifications.add(
        new ClassSpecification(
            "Comments", 1, 1, null, "Class Name", "Extends Annotation Type", "Extends Class Name"));
    classSpecifications.add(
        new ClassSpecification(
            "Comments",
            1,
            1,
            "Annotation Type",
            "Class Name",
            "Extends Annotation Type",
            "Extends Class Name"));
    classSpecifications.add(
        new ClassSpecification(
            "Comments",
            1,
            1,
            "Annotation Type",
            "Class Name",
            "Extends Annotation Type",
            "Extends Class Name"));
    classSpecifications.add(
        new ClassSpecification(
            "Comments",
            1,
            1,
            "Annotation Type",
            "Class Name",
            "Extends Annotation Type",
            "Extends Class Name"));
    classSpecifications.add(
        new ClassSpecification(
            "Comments",
            1,
            1,
            "Annotation Type",
            "Class Name",
            "Extends Annotation Type",
            "Extends Class Name"));
    classSpecifications.add(
        new ClassSpecification(
            "Comments",
            1,
            1,
            "Annotation Type",
            "Class Name",
            "Extends Annotation Type",
            "Extends Class Name"));
    classSpecifications.add(
        new ClassSpecification(
            "Comments",
            1,
            1,
            "Annotation Type",
            "Class Name",
            "Extends Annotation Type",
            "Extends Class Name"));
    classSpecifications.add(
        new ClassSpecification(
            "Comments",
            1,
            1,
            "Annotation Type",
            "Class Name",
            "Extends Annotation Type",
            "Extends Class Name"));
    classSpecifications.add(
        new ClassSpecification(
            "Comments",
            1,
            1,
            "Annotation Type",
            "Class Name",
            "Extends Annotation Type",
            "Extends Class Name"));
    classSpecifications.add(
        new ClassSpecification(
            "Comments",
            1,
            1,
            "Annotation Type",
            "Class Name",
            "Extends Annotation Type",
            "Extends Class Name"));
    classSpecifications.add(
        new ClassSpecification(
            "Comments",
            1,
            1,
            "Annotation Type",
            "Class Name",
            "Extends Annotation Type",
            "Extends Class Name"));
    classSpecifications.add(
        new ClassSpecification(
            "Comments",
            1,
            1,
            "Annotation Type",
            "Class Name",
            "Extends Annotation Type",
            "Extends Class Name"));
    classSpecifications.add(
        new ClassSpecification(
            "Comments",
            1,
            1,
            "Annotation Type",
            "Class Name",
            "Extends Annotation Type",
            "Extends Class Name"));
    classSpecifications.add(
        new ClassSpecification(
            "Comments",
            1,
            1,
            "Annotation Type",
            "Class Name",
            "Extends Annotation Type",
            "Extends Class Name"));
    classSpecifications.add(
        new ClassSpecification(
            "Comments",
            1,
            1,
            "Annotation Type",
            "Class Name",
            "Extends Annotation Type",
            "Extends Class Name"));
    classSpecifications.add(
        new ClassSpecification(
            "Comments",
            1,
            1,
            "Annotation Type",
            "Class Name",
            "Extends Annotation Type",
            "Extends Class Name"));
    classSpecifications.add(
        new ClassSpecification(
            "Comments",
            1,
            1,
            "Annotation Type",
            "Class Name",
            "Extends Annotation Type",
            "Extends Class Name"));
    classSpecifications.add(
        new ClassSpecification(
            "Comments",
            1,
            1,
            "Annotation Type",
            "Class Name",
            "Extends Annotation Type",
            "Extends Class Name"));

    // Act
    fullyQualifiedClassNameChecker.checkClassSpecifications(classSpecifications);

    // Assert
    verify(programClassPool, atLeast(1)).getClass(Mockito.<String>any());
  }

  /**
   * Test {@link FullyQualifiedClassNameChecker#checkClassSpecifications(List)}.
   *
   * <p>Method under test: {@link FullyQualifiedClassNameChecker#checkClassSpecifications(List)}
   */
  @Test
  @DisplayName("Test checkClassSpecifications(List)")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void FullyQualifiedClassNameChecker.checkClassSpecifications(List)"})
  void testCheckClassSpecifications2() {
    // Arrange
    ClassPool programClassPool = mock(ClassPool.class);
    when(programClassPool.getClass(Mockito.<String>any())).thenReturn(new LibraryClass());
    ClassPool libraryClassPool = new ClassPool();
    FullyQualifiedClassNameChecker fullyQualifiedClassNameChecker =
        new FullyQualifiedClassNameChecker(
            programClassPool,
            libraryClassPool,
            new WarningPrinter(new PrintWriter(new StringWriter())));

    ArrayList<Object> classSpecifications = new ArrayList<>();
    classSpecifications.add(
        new ClassSpecification(
            "Comments", 1, 1, "", "Class Name", "Extends Annotation Type", "Extends Class Name"));
    classSpecifications.add(
        new ClassSpecification(
            "Comments",
            1,
            1,
            "Annotation Type",
            "Class Name",
            "Extends Annotation Type",
            "Extends Class Name"));
    classSpecifications.add(
        new ClassSpecification(
            "Comments",
            1,
            1,
            "Annotation Type",
            "Class Name",
            "Extends Annotation Type",
            "Extends Class Name"));
    classSpecifications.add(
        new ClassSpecification(
            "Comments",
            1,
            1,
            "Annotation Type",
            "Class Name",
            "Extends Annotation Type",
            "Extends Class Name"));
    classSpecifications.add(
        new ClassSpecification(
            "Comments",
            1,
            1,
            "Annotation Type",
            "Class Name",
            "Extends Annotation Type",
            "Extends Class Name"));
    classSpecifications.add(
        new ClassSpecification(
            "Comments",
            1,
            1,
            "Annotation Type",
            "Class Name",
            "Extends Annotation Type",
            "Extends Class Name"));
    classSpecifications.add(
        new ClassSpecification(
            "Comments",
            1,
            1,
            "Annotation Type",
            "Class Name",
            "Extends Annotation Type",
            "Extends Class Name"));
    classSpecifications.add(
        new ClassSpecification(
            "Comments",
            1,
            1,
            "Annotation Type",
            "Class Name",
            "Extends Annotation Type",
            "Extends Class Name"));
    classSpecifications.add(
        new ClassSpecification(
            "Comments",
            1,
            1,
            "Annotation Type",
            "Class Name",
            "Extends Annotation Type",
            "Extends Class Name"));
    classSpecifications.add(
        new ClassSpecification(
            "Comments",
            1,
            1,
            "Annotation Type",
            "Class Name",
            "Extends Annotation Type",
            "Extends Class Name"));
    classSpecifications.add(
        new ClassSpecification(
            "Comments",
            1,
            1,
            "Annotation Type",
            "Class Name",
            "Extends Annotation Type",
            "Extends Class Name"));
    classSpecifications.add(
        new ClassSpecification(
            "Comments",
            1,
            1,
            "Annotation Type",
            "Class Name",
            "Extends Annotation Type",
            "Extends Class Name"));
    classSpecifications.add(
        new ClassSpecification(
            "Comments",
            1,
            1,
            "Annotation Type",
            "Class Name",
            "Extends Annotation Type",
            "Extends Class Name"));
    classSpecifications.add(
        new ClassSpecification(
            "Comments",
            1,
            1,
            "Annotation Type",
            "Class Name",
            "Extends Annotation Type",
            "Extends Class Name"));
    classSpecifications.add(
        new ClassSpecification(
            "Comments",
            1,
            1,
            "Annotation Type",
            "Class Name",
            "Extends Annotation Type",
            "Extends Class Name"));
    classSpecifications.add(
        new ClassSpecification(
            "Comments",
            1,
            1,
            "Annotation Type",
            "Class Name",
            "Extends Annotation Type",
            "Extends Class Name"));
    classSpecifications.add(
        new ClassSpecification(
            "Comments",
            1,
            1,
            "Annotation Type",
            "Class Name",
            "Extends Annotation Type",
            "Extends Class Name"));
    classSpecifications.add(
        new ClassSpecification(
            "Comments",
            1,
            1,
            "Annotation Type",
            "Class Name",
            "Extends Annotation Type",
            "Extends Class Name"));

    // Act
    fullyQualifiedClassNameChecker.checkClassSpecifications(classSpecifications);

    // Assert
    verify(programClassPool, atLeast(1)).getClass(Mockito.<String>any());
  }

  /**
   * Test {@link FullyQualifiedClassNameChecker#checkClassSpecifications(List)}.
   *
   * <p>Method under test: {@link FullyQualifiedClassNameChecker#checkClassSpecifications(List)}
   */
  @Test
  @DisplayName("Test checkClassSpecifications(List)")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void FullyQualifiedClassNameChecker.checkClassSpecifications(List)"})
  void testCheckClassSpecifications3() {
    // Arrange
    ClassPool programClassPool = mock(ClassPool.class);
    when(programClassPool.getClass(Mockito.<String>any())).thenReturn(new LibraryClass());
    ClassPool libraryClassPool = new ClassPool();
    FullyQualifiedClassNameChecker fullyQualifiedClassNameChecker =
        new FullyQualifiedClassNameChecker(
            programClassPool,
            libraryClassPool,
            new WarningPrinter(new PrintWriter(new StringWriter())));

    ArrayList<Object> classSpecifications = new ArrayList<>();
    classSpecifications.add(
        new ClassSpecification(
            "Comments",
            1,
            1,
            "Annotation Type",
            "///",
            "Extends Annotation Type",
            "Extends Class Name"));
    classSpecifications.add(
        new ClassSpecification(
            "Comments",
            1,
            1,
            "Annotation Type",
            "Class Name",
            "Extends Annotation Type",
            "Extends Class Name"));
    classSpecifications.add(
        new ClassSpecification(
            "Comments",
            1,
            1,
            "Annotation Type",
            "Class Name",
            "Extends Annotation Type",
            "Extends Class Name"));
    classSpecifications.add(
        new ClassSpecification(
            "Comments",
            1,
            1,
            "Annotation Type",
            "Class Name",
            "Extends Annotation Type",
            "Extends Class Name"));
    classSpecifications.add(
        new ClassSpecification(
            "Comments",
            1,
            1,
            "Annotation Type",
            "Class Name",
            "Extends Annotation Type",
            "Extends Class Name"));
    classSpecifications.add(
        new ClassSpecification(
            "Comments",
            1,
            1,
            "Annotation Type",
            "Class Name",
            "Extends Annotation Type",
            "Extends Class Name"));
    classSpecifications.add(
        new ClassSpecification(
            "Comments",
            1,
            1,
            "Annotation Type",
            "Class Name",
            "Extends Annotation Type",
            "Extends Class Name"));
    classSpecifications.add(
        new ClassSpecification(
            "Comments",
            1,
            1,
            "Annotation Type",
            "Class Name",
            "Extends Annotation Type",
            "Extends Class Name"));
    classSpecifications.add(
        new ClassSpecification(
            "Comments",
            1,
            1,
            "Annotation Type",
            "Class Name",
            "Extends Annotation Type",
            "Extends Class Name"));
    classSpecifications.add(
        new ClassSpecification(
            "Comments",
            1,
            1,
            "Annotation Type",
            "Class Name",
            "Extends Annotation Type",
            "Extends Class Name"));
    classSpecifications.add(
        new ClassSpecification(
            "Comments",
            1,
            1,
            "Annotation Type",
            "Class Name",
            "Extends Annotation Type",
            "Extends Class Name"));
    classSpecifications.add(
        new ClassSpecification(
            "Comments",
            1,
            1,
            "Annotation Type",
            "Class Name",
            "Extends Annotation Type",
            "Extends Class Name"));
    classSpecifications.add(
        new ClassSpecification(
            "Comments",
            1,
            1,
            "Annotation Type",
            "Class Name",
            "Extends Annotation Type",
            "Extends Class Name"));
    classSpecifications.add(
        new ClassSpecification(
            "Comments",
            1,
            1,
            "Annotation Type",
            "Class Name",
            "Extends Annotation Type",
            "Extends Class Name"));
    classSpecifications.add(
        new ClassSpecification(
            "Comments",
            1,
            1,
            "Annotation Type",
            "Class Name",
            "Extends Annotation Type",
            "Extends Class Name"));
    classSpecifications.add(
        new ClassSpecification(
            "Comments",
            1,
            1,
            "Annotation Type",
            "Class Name",
            "Extends Annotation Type",
            "Extends Class Name"));
    classSpecifications.add(
        new ClassSpecification(
            "Comments",
            1,
            1,
            "Annotation Type",
            "Class Name",
            "Extends Annotation Type",
            "Extends Class Name"));
    classSpecifications.add(
        new ClassSpecification(
            "Comments",
            1,
            1,
            "Annotation Type",
            "Class Name",
            "Extends Annotation Type",
            "Extends Class Name"));

    // Act
    fullyQualifiedClassNameChecker.checkClassSpecifications(classSpecifications);

    // Assert
    verify(programClassPool, atLeast(1)).getClass(Mockito.<String>any());
  }

  /**
   * Test {@link FullyQualifiedClassNameChecker#checkClassSpecifications(List)}.
   *
   * <p>Method under test: {@link FullyQualifiedClassNameChecker#checkClassSpecifications(List)}
   */
  @Test
  @DisplayName("Test checkClassSpecifications(List)")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void FullyQualifiedClassNameChecker.checkClassSpecifications(List)"})
  void testCheckClassSpecifications4() {
    // Arrange
    ClassPool programClassPool = mock(ClassPool.class);
    when(programClassPool.getClass(Mockito.<String>any())).thenReturn(new LibraryClass());
    ClassPool libraryClassPool = new ClassPool();
    FullyQualifiedClassNameChecker fullyQualifiedClassNameChecker =
        new FullyQualifiedClassNameChecker(
            programClassPool,
            libraryClassPool,
            new WarningPrinter(new PrintWriter(new StringWriter())));

    ArrayList<Object> classSpecifications = new ArrayList<>();
    ArrayList<Object> fieldSpecifications = new ArrayList<>();
    classSpecifications.add(
        new ClassSpecification(
            "///", 18, 18, "///", "///", "///", "///", fieldSpecifications, new ArrayList<>()));
    classSpecifications.add(
        new ClassSpecification(
            "Comments",
            1,
            1,
            "Annotation Type",
            "Class Name",
            "Extends Annotation Type",
            "Extends Class Name"));
    classSpecifications.add(
        new ClassSpecification(
            "Comments",
            1,
            1,
            "Annotation Type",
            "Class Name",
            "Extends Annotation Type",
            "Extends Class Name"));
    classSpecifications.add(
        new ClassSpecification(
            "Comments",
            1,
            1,
            "Annotation Type",
            "Class Name",
            "Extends Annotation Type",
            "Extends Class Name"));
    classSpecifications.add(
        new ClassSpecification(
            "Comments",
            1,
            1,
            "Annotation Type",
            "Class Name",
            "Extends Annotation Type",
            "Extends Class Name"));
    classSpecifications.add(
        new ClassSpecification(
            "Comments",
            1,
            1,
            "Annotation Type",
            "Class Name",
            "Extends Annotation Type",
            "Extends Class Name"));
    classSpecifications.add(
        new ClassSpecification(
            "Comments",
            1,
            1,
            "Annotation Type",
            "Class Name",
            "Extends Annotation Type",
            "Extends Class Name"));
    classSpecifications.add(
        new ClassSpecification(
            "Comments",
            1,
            1,
            "Annotation Type",
            "Class Name",
            "Extends Annotation Type",
            "Extends Class Name"));
    classSpecifications.add(
        new ClassSpecification(
            "Comments",
            1,
            1,
            "Annotation Type",
            "Class Name",
            "Extends Annotation Type",
            "Extends Class Name"));
    classSpecifications.add(
        new ClassSpecification(
            "Comments",
            1,
            1,
            "Annotation Type",
            "Class Name",
            "Extends Annotation Type",
            "Extends Class Name"));
    classSpecifications.add(
        new ClassSpecification(
            "Comments",
            1,
            1,
            "Annotation Type",
            "Class Name",
            "Extends Annotation Type",
            "Extends Class Name"));
    classSpecifications.add(
        new ClassSpecification(
            "Comments",
            1,
            1,
            "Annotation Type",
            "Class Name",
            "Extends Annotation Type",
            "Extends Class Name"));
    classSpecifications.add(
        new ClassSpecification(
            "Comments",
            1,
            1,
            "Annotation Type",
            "Class Name",
            "Extends Annotation Type",
            "Extends Class Name"));
    classSpecifications.add(
        new ClassSpecification(
            "Comments",
            1,
            1,
            "Annotation Type",
            "Class Name",
            "Extends Annotation Type",
            "Extends Class Name"));
    classSpecifications.add(
        new ClassSpecification(
            "Comments",
            1,
            1,
            "Annotation Type",
            "Class Name",
            "Extends Annotation Type",
            "Extends Class Name"));
    classSpecifications.add(
        new ClassSpecification(
            "Comments",
            1,
            1,
            "Annotation Type",
            "Class Name",
            "Extends Annotation Type",
            "Extends Class Name"));
    classSpecifications.add(
        new ClassSpecification(
            "Comments",
            1,
            1,
            "Annotation Type",
            "Class Name",
            "Extends Annotation Type",
            "Extends Class Name"));
    classSpecifications.add(
        new ClassSpecification(
            "Comments",
            1,
            1,
            "Annotation Type",
            "Class Name",
            "Extends Annotation Type",
            "Extends Class Name"));

    // Act
    fullyQualifiedClassNameChecker.checkClassSpecifications(classSpecifications);

    // Assert
    verify(programClassPool, atLeast(1)).getClass(Mockito.<String>any());
  }

  /**
   * Test {@link FullyQualifiedClassNameChecker#checkClassSpecifications(List)}.
   *
   * <p>Method under test: {@link FullyQualifiedClassNameChecker#checkClassSpecifications(List)}
   */
  @Test
  @DisplayName("Test checkClassSpecifications(List)")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void FullyQualifiedClassNameChecker.checkClassSpecifications(List)"})
  void testCheckClassSpecifications5() {
    // Arrange
    ClassPool programClassPool = mock(ClassPool.class);
    when(programClassPool.getClass(Mockito.<String>any())).thenReturn(new LibraryClass());
    ClassPool libraryClassPool = new ClassPool();
    FullyQualifiedClassNameChecker fullyQualifiedClassNameChecker =
        new FullyQualifiedClassNameChecker(
            programClassPool,
            libraryClassPool,
            new WarningPrinter(new PrintWriter(new StringWriter())));

    ClassSpecification classSpecification =
        new ClassSpecification(
            "Comments",
            1,
            1,
            "Annotation Type",
            "Class Name",
            "Extends Annotation Type",
            "Extends Class Name");
    classSpecification.addField(new MemberSpecification(18, 18, "///", "///", "///"));

    ArrayList<Object> classSpecifications = new ArrayList<>();
    classSpecifications.add(classSpecification);
    classSpecifications.add(
        new ClassSpecification(
            "Comments",
            1,
            1,
            "Annotation Type",
            "Class Name",
            "Extends Annotation Type",
            "Extends Class Name"));
    classSpecifications.add(
        new ClassSpecification(
            "Comments",
            1,
            1,
            "Annotation Type",
            "Class Name",
            "Extends Annotation Type",
            "Extends Class Name"));
    classSpecifications.add(
        new ClassSpecification(
            "Comments",
            1,
            1,
            "Annotation Type",
            "Class Name",
            "Extends Annotation Type",
            "Extends Class Name"));
    classSpecifications.add(
        new ClassSpecification(
            "Comments",
            1,
            1,
            "Annotation Type",
            "Class Name",
            "Extends Annotation Type",
            "Extends Class Name"));
    classSpecifications.add(
        new ClassSpecification(
            "Comments",
            1,
            1,
            "Annotation Type",
            "Class Name",
            "Extends Annotation Type",
            "Extends Class Name"));
    classSpecifications.add(
        new ClassSpecification(
            "Comments",
            1,
            1,
            "Annotation Type",
            "Class Name",
            "Extends Annotation Type",
            "Extends Class Name"));
    classSpecifications.add(
        new ClassSpecification(
            "Comments",
            1,
            1,
            "Annotation Type",
            "Class Name",
            "Extends Annotation Type",
            "Extends Class Name"));
    classSpecifications.add(
        new ClassSpecification(
            "Comments",
            1,
            1,
            "Annotation Type",
            "Class Name",
            "Extends Annotation Type",
            "Extends Class Name"));
    classSpecifications.add(
        new ClassSpecification(
            "Comments",
            1,
            1,
            "Annotation Type",
            "Class Name",
            "Extends Annotation Type",
            "Extends Class Name"));
    classSpecifications.add(
        new ClassSpecification(
            "Comments",
            1,
            1,
            "Annotation Type",
            "Class Name",
            "Extends Annotation Type",
            "Extends Class Name"));
    classSpecifications.add(
        new ClassSpecification(
            "Comments",
            1,
            1,
            "Annotation Type",
            "Class Name",
            "Extends Annotation Type",
            "Extends Class Name"));
    classSpecifications.add(
        new ClassSpecification(
            "Comments",
            1,
            1,
            "Annotation Type",
            "Class Name",
            "Extends Annotation Type",
            "Extends Class Name"));
    classSpecifications.add(
        new ClassSpecification(
            "Comments",
            1,
            1,
            "Annotation Type",
            "Class Name",
            "Extends Annotation Type",
            "Extends Class Name"));
    classSpecifications.add(
        new ClassSpecification(
            "Comments",
            1,
            1,
            "Annotation Type",
            "Class Name",
            "Extends Annotation Type",
            "Extends Class Name"));
    classSpecifications.add(
        new ClassSpecification(
            "Comments",
            1,
            1,
            "Annotation Type",
            "Class Name",
            "Extends Annotation Type",
            "Extends Class Name"));
    classSpecifications.add(
        new ClassSpecification(
            "Comments",
            1,
            1,
            "Annotation Type",
            "Class Name",
            "Extends Annotation Type",
            "Extends Class Name"));
    classSpecifications.add(
        new ClassSpecification(
            "Comments",
            1,
            1,
            "Annotation Type",
            "Class Name",
            "Extends Annotation Type",
            "Extends Class Name"));

    // Act
    fullyQualifiedClassNameChecker.checkClassSpecifications(classSpecifications);

    // Assert
    verify(programClassPool, atLeast(1)).getClass(Mockito.<String>any());
  }

  /**
   * Test {@link FullyQualifiedClassNameChecker#checkClassSpecifications(List)}.
   *
   * <ul>
   *   <li>Then calls {@link ClassPool#classesAccept(ClassVisitor)}.
   * </ul>
   *
   * <p>Method under test: {@link FullyQualifiedClassNameChecker#checkClassSpecifications(List)}
   */
  @Test
  @DisplayName("Test checkClassSpecifications(List); then calls classesAccept(ClassVisitor)")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void FullyQualifiedClassNameChecker.checkClassSpecifications(List)"})
  void testCheckClassSpecifications_thenCallsClassesAccept() {
    // Arrange
    ClassPool programClassPool = mock(ClassPool.class);
    when(programClassPool.getClass(Mockito.<String>any())).thenReturn(null);
    doNothing().when(programClassPool).classesAccept(Mockito.<ClassVisitor>any());
    ClassPool libraryClassPool = new ClassPool();
    FullyQualifiedClassNameChecker fullyQualifiedClassNameChecker =
        new FullyQualifiedClassNameChecker(
            programClassPool,
            libraryClassPool,
            new WarningPrinter(new PrintWriter(new StringWriter())));

    ArrayList<Object> classSpecifications = new ArrayList<>();
    classSpecifications.add(
        new ClassSpecification(
            "Comments",
            1,
            1,
            "Annotation Type",
            "Class Name",
            "Extends Annotation Type",
            "Extends Class Name"));
    classSpecifications.add(
        new ClassSpecification(
            "Comments",
            1,
            1,
            "Annotation Type",
            "Class Name",
            "Extends Annotation Type",
            "Extends Class Name"));
    classSpecifications.add(
        new ClassSpecification(
            "Comments",
            1,
            1,
            "Annotation Type",
            "Class Name",
            "Extends Annotation Type",
            "Extends Class Name"));
    classSpecifications.add(
        new ClassSpecification(
            "Comments",
            1,
            1,
            "Annotation Type",
            "Class Name",
            "Extends Annotation Type",
            "Extends Class Name"));
    classSpecifications.add(
        new ClassSpecification(
            "Comments",
            1,
            1,
            "Annotation Type",
            "Class Name",
            "Extends Annotation Type",
            "Extends Class Name"));
    classSpecifications.add(
        new ClassSpecification(
            "Comments",
            1,
            1,
            "Annotation Type",
            "Class Name",
            "Extends Annotation Type",
            "Extends Class Name"));
    classSpecifications.add(
        new ClassSpecification(
            "Comments",
            1,
            1,
            "Annotation Type",
            "Class Name",
            "Extends Annotation Type",
            "Extends Class Name"));
    classSpecifications.add(
        new ClassSpecification(
            "Comments",
            1,
            1,
            "Annotation Type",
            "Class Name",
            "Extends Annotation Type",
            "Extends Class Name"));
    classSpecifications.add(
        new ClassSpecification(
            "Comments",
            1,
            1,
            "Annotation Type",
            "Class Name",
            "Extends Annotation Type",
            "Extends Class Name"));
    classSpecifications.add(
        new ClassSpecification(
            "Comments",
            1,
            1,
            "Annotation Type",
            "Class Name",
            "Extends Annotation Type",
            "Extends Class Name"));
    classSpecifications.add(
        new ClassSpecification(
            "Comments",
            1,
            1,
            "Annotation Type",
            "Class Name",
            "Extends Annotation Type",
            "Extends Class Name"));
    classSpecifications.add(
        new ClassSpecification(
            "Comments",
            1,
            1,
            "Annotation Type",
            "Class Name",
            "Extends Annotation Type",
            "Extends Class Name"));
    classSpecifications.add(
        new ClassSpecification(
            "Comments",
            1,
            1,
            "Annotation Type",
            "Class Name",
            "Extends Annotation Type",
            "Extends Class Name"));
    classSpecifications.add(
        new ClassSpecification(
            "Comments",
            1,
            1,
            "Annotation Type",
            "Class Name",
            "Extends Annotation Type",
            "Extends Class Name"));
    classSpecifications.add(
        new ClassSpecification(
            "Comments",
            1,
            1,
            "Annotation Type",
            "Class Name",
            "Extends Annotation Type",
            "Extends Class Name"));
    classSpecifications.add(
        new ClassSpecification(
            "Comments",
            1,
            1,
            "Annotation Type",
            "Class Name",
            "Extends Annotation Type",
            "Extends Class Name"));
    classSpecifications.add(
        new ClassSpecification(
            "Comments",
            1,
            1,
            "Annotation Type",
            "Class Name",
            "Extends Annotation Type",
            "Extends Class Name"));
    classSpecifications.add(
        new ClassSpecification(
            "Comments",
            1,
            1,
            "Annotation Type",
            "Class Name",
            "Extends Annotation Type",
            "Extends Class Name"));

    // Act
    fullyQualifiedClassNameChecker.checkClassSpecifications(classSpecifications);

    // Assert
    verify(programClassPool, atLeast(1)).classesAccept(Mockito.<ClassVisitor>any());
    verify(programClassPool, atLeast(1)).getClass(Mockito.<String>any());
  }

  /**
   * Test {@link FullyQualifiedClassNameChecker#checkClassSpecifications(List)}.
   *
   * <ul>
   *   <li>Then calls {@link ClassPool#getClass(String)}.
   * </ul>
   *
   * <p>Method under test: {@link FullyQualifiedClassNameChecker#checkClassSpecifications(List)}
   */
  @Test
  @DisplayName("Test checkClassSpecifications(List); then calls getClass(String)")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void FullyQualifiedClassNameChecker.checkClassSpecifications(List)"})
  void testCheckClassSpecifications_thenCallsGetClass() {
    // Arrange
    ClassPool programClassPool = mock(ClassPool.class);
    when(programClassPool.getClass(Mockito.<String>any())).thenReturn(new LibraryClass());
    ClassPool libraryClassPool = new ClassPool();
    FullyQualifiedClassNameChecker fullyQualifiedClassNameChecker =
        new FullyQualifiedClassNameChecker(
            programClassPool,
            libraryClassPool,
            new WarningPrinter(new PrintWriter(new StringWriter())));

    ArrayList<Object> classSpecifications = new ArrayList<>();
    classSpecifications.add(
        new ClassSpecification(
            "Comments",
            1,
            1,
            "Annotation Type",
            "Class Name",
            "Extends Annotation Type",
            "Extends Class Name"));
    classSpecifications.add(
        new ClassSpecification(
            "Comments",
            1,
            1,
            "Annotation Type",
            "Class Name",
            "Extends Annotation Type",
            "Extends Class Name"));
    classSpecifications.add(
        new ClassSpecification(
            "Comments",
            1,
            1,
            "Annotation Type",
            "Class Name",
            "Extends Annotation Type",
            "Extends Class Name"));
    classSpecifications.add(
        new ClassSpecification(
            "Comments",
            1,
            1,
            "Annotation Type",
            "Class Name",
            "Extends Annotation Type",
            "Extends Class Name"));
    classSpecifications.add(
        new ClassSpecification(
            "Comments",
            1,
            1,
            "Annotation Type",
            "Class Name",
            "Extends Annotation Type",
            "Extends Class Name"));
    classSpecifications.add(
        new ClassSpecification(
            "Comments",
            1,
            1,
            "Annotation Type",
            "Class Name",
            "Extends Annotation Type",
            "Extends Class Name"));
    classSpecifications.add(
        new ClassSpecification(
            "Comments",
            1,
            1,
            "Annotation Type",
            "Class Name",
            "Extends Annotation Type",
            "Extends Class Name"));
    classSpecifications.add(
        new ClassSpecification(
            "Comments",
            1,
            1,
            "Annotation Type",
            "Class Name",
            "Extends Annotation Type",
            "Extends Class Name"));
    classSpecifications.add(
        new ClassSpecification(
            "Comments",
            1,
            1,
            "Annotation Type",
            "Class Name",
            "Extends Annotation Type",
            "Extends Class Name"));
    classSpecifications.add(
        new ClassSpecification(
            "Comments",
            1,
            1,
            "Annotation Type",
            "Class Name",
            "Extends Annotation Type",
            "Extends Class Name"));
    classSpecifications.add(
        new ClassSpecification(
            "Comments",
            1,
            1,
            "Annotation Type",
            "Class Name",
            "Extends Annotation Type",
            "Extends Class Name"));
    classSpecifications.add(
        new ClassSpecification(
            "Comments",
            1,
            1,
            "Annotation Type",
            "Class Name",
            "Extends Annotation Type",
            "Extends Class Name"));
    classSpecifications.add(
        new ClassSpecification(
            "Comments",
            1,
            1,
            "Annotation Type",
            "Class Name",
            "Extends Annotation Type",
            "Extends Class Name"));
    classSpecifications.add(
        new ClassSpecification(
            "Comments",
            1,
            1,
            "Annotation Type",
            "Class Name",
            "Extends Annotation Type",
            "Extends Class Name"));
    classSpecifications.add(
        new ClassSpecification(
            "Comments",
            1,
            1,
            "Annotation Type",
            "Class Name",
            "Extends Annotation Type",
            "Extends Class Name"));
    classSpecifications.add(
        new ClassSpecification(
            "Comments",
            1,
            1,
            "Annotation Type",
            "Class Name",
            "Extends Annotation Type",
            "Extends Class Name"));
    classSpecifications.add(
        new ClassSpecification(
            "Comments",
            1,
            1,
            "Annotation Type",
            "Class Name",
            "Extends Annotation Type",
            "Extends Class Name"));
    classSpecifications.add(
        new ClassSpecification(
            "Comments",
            1,
            1,
            "Annotation Type",
            "Class Name",
            "Extends Annotation Type",
            "Extends Class Name"));

    // Act
    fullyQualifiedClassNameChecker.checkClassSpecifications(classSpecifications);

    // Assert
    verify(programClassPool, atLeast(1)).getClass(Mockito.<String>any());
  }
}
