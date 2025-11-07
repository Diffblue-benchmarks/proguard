package proguard.io;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.io.IOException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import proguard.Configuration;
import proguard.classfile.ClassPool;
import proguard.configuration.InitialStateInfo;

class ClassMapDataEntryReplacerDiffblueTest {
  /**
   * Test {@link ClassMapDataEntryReplacer#read(DataEntry)}.
   * <ul>
   *   <li>Given {@link DataEntryFilter} {@link DataEntryFilter#accepts(DataEntry)} return {@code false}.</li>
   *   <li>Then calls {@link DataEntryFilter#accepts(DataEntry)}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ClassMapDataEntryReplacer#read(DataEntry)}
   */
  @Test
  @DisplayName("Test read(DataEntry); given DataEntryFilter accepts(DataEntry) return 'false'; then calls accepts(DataEntry)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void ClassMapDataEntryReplacer.read(DataEntry)"})
  void testRead_givenDataEntryFilterAcceptsReturnFalse_thenCallsAccepts() throws IOException {
    // Arrange
    DataEntryFilter dataEntryFilter = mock(DataEntryFilter.class);
    when(dataEntryFilter.accepts(Mockito.<DataEntry>any())).thenReturn(false);
    UniqueDataEntryWriter dataEntryWriter = new UniqueDataEntryWriter(new FilteredDataEntryWriter(dataEntryFilter,
        new UniqueDataEntryWriter(new DirectoryWriter(Configuration.STD_OUT))));
    ClassPool programClassPool = new ClassPool();
    ClassMapDataEntryReplacer classMapDataEntryReplacer = new ClassMapDataEntryReplacer(programClassPool,
        new InitialStateInfo(new ClassPool()), dataEntryWriter);

    // Act
    classMapDataEntryReplacer.read(new ClassPathDataEntry("Name"));

    // Assert
    verify(dataEntryFilter).accepts(isA(DataEntry.class));
  }

  /**
   * Test {@link ClassMapDataEntryReplacer#isKept(int)}.
   * <ul>
   *   <li>When {@code 4194304}.</li>
   *   <li>Then return {@code false}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ClassMapDataEntryReplacer#isKept(int)}
   */
  @Test
  @DisplayName("Test isKept(int); when '4194304'; then return 'false'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean ClassMapDataEntryReplacer.isKept(int)"})
  void testIsKept_when4194304_thenReturnFalse() {
    // Arrange, Act and Assert
    assertFalse(ClassMapDataEntryReplacer.isKept(4194304));
  }

  /**
   * Test {@link ClassMapDataEntryReplacer#isKept(int)}.
   * <ul>
   *   <li>When minus one.</li>
   *   <li>Then return {@code true}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ClassMapDataEntryReplacer#isKept(int)}
   */
  @Test
  @DisplayName("Test isKept(int); when minus one; then return 'true'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean ClassMapDataEntryReplacer.isKept(int)"})
  void testIsKept_whenMinusOne_thenReturnTrue() {
    // Arrange, Act and Assert
    assertTrue(ClassMapDataEntryReplacer.isKept(-1));
  }

  /**
   * Test {@link ClassMapDataEntryReplacer#isKept(int)}.
   * <ul>
   *   <li>When one.</li>
   *   <li>Then return {@code false}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ClassMapDataEntryReplacer#isKept(int)}
   */
  @Test
  @DisplayName("Test isKept(int); when one; then return 'false'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean ClassMapDataEntryReplacer.isKept(int)"})
  void testIsKept_whenOne_thenReturnFalse() {
    // Arrange, Act and Assert
    assertFalse(ClassMapDataEntryReplacer.isKept(1));
  }
}
