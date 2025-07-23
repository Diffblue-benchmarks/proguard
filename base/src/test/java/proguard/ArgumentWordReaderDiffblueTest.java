package proguard;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.io.File;
import java.io.IOException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

class ArgumentWordReaderDiffblueTest {
  /**
   * Test {@link ArgumentWordReader#ArgumentWordReader(String[], File)}.
   *
   * <p>Method under test: {@link ArgumentWordReader#ArgumentWordReader(String[], File)}
   */
  @Test
  @DisplayName("Test new ArgumentWordReader(String[], File)")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void ArgumentWordReader.<init>(String[], File)"})
  void testNewArgumentWordReader() {
    // Arrange
    File baseDir = Configuration.STD_OUT;

    // Act
    ArgumentWordReader actualArgumentWordReader =
        new ArgumentWordReader(new String[] {"Arguments"}, baseDir);

    // Assert
    assertNull(actualArgumentWordReader.getBaseURL());
    assertSame(baseDir, actualArgumentWordReader.getBaseDir());
  }

  /**
   * Test {@link ArgumentWordReader#nextLine()}.
   *
   * <ul>
   *   <li>Then return {@code Arguments}.
   * </ul>
   *
   * <p>Method under test: {@link ArgumentWordReader#nextLine()}
   */
  @Test
  @DisplayName("Test nextLine(); then return 'Arguments'")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"String ArgumentWordReader.nextLine()"})
  void testNextLine_thenReturnArguments() throws IOException {
    // Arrange, Act and Assert
    assertEquals(
        "Arguments",
        new ArgumentWordReader(new String[] {"Arguments"}, Configuration.STD_OUT).nextLine());
  }

  /**
   * Test {@link ArgumentWordReader#nextLine()}.
   *
   * <ul>
   *   <li>Then return {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link ArgumentWordReader#nextLine()}
   */
  @Test
  @DisplayName("Test nextLine(); then return 'null'")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"String ArgumentWordReader.nextLine()"})
  void testNextLine_thenReturnNull() throws IOException {
    // Arrange, Act and Assert
    assertNull(new ArgumentWordReader(new String[] {}, Configuration.STD_OUT).nextLine());
  }

  /**
   * Test {@link ArgumentWordReader#lineLocationDescription()}.
   *
   * <p>Method under test: {@link ArgumentWordReader#lineLocationDescription()}
   */
  @Test
  @DisplayName("Test lineLocationDescription()")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"String ArgumentWordReader.lineLocationDescription()"})
  void testLineLocationDescription() {
    // Arrange, Act and Assert
    assertEquals(
        "argument number 0",
        new ArgumentWordReader(new String[] {"Arguments"}, Configuration.STD_OUT)
            .lineLocationDescription());
  }
}
