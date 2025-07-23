package proguard.gradle.plugin.android;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import com.android.build.api.variant.VariantInfo;
import com.android.build.gradle.BaseExtension;
import com.android.build.gradle.LibraryExtension;
import com.android.build.gradle.internal.dsl.AaptOptions;
import com.android.build.gradle.internal.pipeline.VariantInfoImpl;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import com.google.common.collect.ImmutableList;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import proguard.gradle.plugin.android.dsl.VariantConfiguration;

class AndroidPluginKtDiffblueTest {
  /**
   * Test {@link AndroidPluginKt#findVariantConfiguration(Iterable, VariantInfo)} with {@code
   * $this$findVariantConfiguration}, {@code variant}.
   *
   * <p>Method under test: {@link AndroidPluginKt#findVariantConfiguration(Iterable, VariantInfo)}
   */
  @Test
  @DisplayName(
      "Test findVariantConfiguration(Iterable, VariantInfo) with '$this$findVariantConfiguration', 'variant'")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({
    "VariantConfiguration AndroidPluginKt.findVariantConfiguration(Iterable, VariantInfo)"
  })
  void testFindVariantConfigurationWithThisFindVariantConfigurationVariant() {
    // Arrange
    LinkedHashSet<VariantConfiguration> $this$findVariantConfiguration = new LinkedHashSet<>();
    $this$findVariantConfiguration.add(new VariantConfiguration("<this>"));

    // Act
    VariantConfiguration actualFindVariantConfigurationResult =
        AndroidPluginKt.findVariantConfiguration(
            $this$findVariantConfiguration,
            new VariantInfoImpl(true, "<this>", "<this>", mock(ImmutableList.class), true));

    // Assert
    assertEquals("<this>", actualFindVariantConfigurationResult.getName());
    assertTrue(actualFindVariantConfigurationResult.getConfigurations().isEmpty());
    assertTrue(actualFindVariantConfigurationResult.getConsumerRuleFilter().isEmpty());
  }

  /**
   * Test {@link AndroidPluginKt#findVariantConfiguration(Iterable, VariantInfo)} with {@code
   * $this$findVariantConfiguration}, {@code variant}.
   *
   * <p>Method under test: {@link AndroidPluginKt#findVariantConfiguration(Iterable, VariantInfo)}
   */
  @Test
  @DisplayName(
      "Test findVariantConfiguration(Iterable, VariantInfo) with '$this$findVariantConfiguration', 'variant'")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({
    "VariantConfiguration AndroidPluginKt.findVariantConfiguration(Iterable, VariantInfo)"
  })
  void testFindVariantConfigurationWithThisFindVariantConfigurationVariant2() {
    // Arrange
    LinkedHashSet<VariantConfiguration> $this$findVariantConfiguration = new LinkedHashSet<>();
    $this$findVariantConfiguration.add(new VariantConfiguration("variant"));

    // Act and Assert
    assertNull(
        AndroidPluginKt.findVariantConfiguration(
            $this$findVariantConfiguration,
            new VariantInfoImpl(true, "<this>", "<this>", mock(ImmutableList.class), true)));
  }

  /**
   * Test {@link AndroidPluginKt#findVariantConfiguration(Iterable, VariantInfo)} with {@code
   * $this$findVariantConfiguration}, {@code variant}.
   *
   * <p>Method under test: {@link AndroidPluginKt#findVariantConfiguration(Iterable, VariantInfo)}
   */
  @Test
  @DisplayName(
      "Test findVariantConfiguration(Iterable, VariantInfo) with '$this$findVariantConfiguration', 'variant'")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({
    "VariantConfiguration AndroidPluginKt.findVariantConfiguration(Iterable, VariantInfo)"
  })
  void testFindVariantConfigurationWithThisFindVariantConfigurationVariant3() {
    // Arrange
    LinkedHashSet<VariantConfiguration> $this$findVariantConfiguration = new LinkedHashSet<>();
    $this$findVariantConfiguration.add(new VariantConfiguration("<this>"));

    // Act
    VariantConfiguration actualFindVariantConfigurationResult =
        AndroidPluginKt.findVariantConfiguration(
            $this$findVariantConfiguration,
            new VariantInfoImpl(true, "variant", "<this>", mock(ImmutableList.class), true));

    // Assert
    assertEquals("<this>", actualFindVariantConfigurationResult.getName());
    assertTrue(actualFindVariantConfigurationResult.getConfigurations().isEmpty());
    assertTrue(actualFindVariantConfigurationResult.getConsumerRuleFilter().isEmpty());
  }

  /**
   * Test {@link AndroidPluginKt#findVariantConfiguration(Iterable, String)} with {@code
   * $this$findVariantConfiguration}, {@code variantName}.
   *
   * <p>Method under test: {@link AndroidPluginKt#findVariantConfiguration(Iterable, String)}
   */
  @Test
  @DisplayName(
      "Test findVariantConfiguration(Iterable, String) with '$this$findVariantConfiguration', 'variantName'")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({
    "VariantConfiguration AndroidPluginKt.findVariantConfiguration(Iterable, String)"
  })
  void testFindVariantConfigurationWithThisFindVariantConfigurationVariantName() {
    // Arrange, Act and Assert
    assertNull(AndroidPluginKt.findVariantConfiguration(new ArrayList<>(), "Variant Name"));
  }

  /**
   * Test {@link AndroidPluginKt#findVariantConfiguration(Iterable, String)} with {@code
   * $this$findVariantConfiguration}, {@code variantName}.
   *
   * <p>Method under test: {@link AndroidPluginKt#findVariantConfiguration(Iterable, String)}
   */
  @Test
  @DisplayName(
      "Test findVariantConfiguration(Iterable, String) with '$this$findVariantConfiguration', 'variantName'")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({
    "VariantConfiguration AndroidPluginKt.findVariantConfiguration(Iterable, String)"
  })
  void testFindVariantConfigurationWithThisFindVariantConfigurationVariantName2() {
    // Arrange
    LinkedHashSet<VariantConfiguration> $this$findVariantConfiguration = new LinkedHashSet<>();
    $this$findVariantConfiguration.add(new VariantConfiguration("Name"));

    // Act
    VariantConfiguration actualFindVariantConfigurationResult =
        AndroidPluginKt.findVariantConfiguration($this$findVariantConfiguration, "Variant Name");

    // Assert
    assertEquals("Name", actualFindVariantConfigurationResult.getName());
    assertTrue(actualFindVariantConfigurationResult.getConfigurations().isEmpty());
    assertTrue(actualFindVariantConfigurationResult.getConsumerRuleFilter().isEmpty());
  }

  /**
   * Test {@link AndroidPluginKt#findVariantConfiguration(Iterable, String)} with {@code
   * $this$findVariantConfiguration}, {@code variantName}.
   *
   * <p>Method under test: {@link AndroidPluginKt#findVariantConfiguration(Iterable, String)}
   */
  @Test
  @DisplayName(
      "Test findVariantConfiguration(Iterable, String) with '$this$findVariantConfiguration', 'variantName'")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({
    "VariantConfiguration AndroidPluginKt.findVariantConfiguration(Iterable, String)"
  })
  void testFindVariantConfigurationWithThisFindVariantConfigurationVariantName3() {
    // Arrange
    ArrayList<VariantConfiguration> $this$findVariantConfiguration = new ArrayList<>();
    $this$findVariantConfiguration.add(new VariantConfiguration("<this>"));

    // Act and Assert
    assertNull(
        AndroidPluginKt.findVariantConfiguration($this$findVariantConfiguration, "Variant Name"));
  }

  /**
   * Test {@link AndroidPluginKt#findVariantConfiguration(Iterable, String)} with {@code
   * $this$findVariantConfiguration}, {@code variantName}.
   *
   * <p>Method under test: {@link AndroidPluginKt#findVariantConfiguration(Iterable, String)}
   */
  @Test
  @DisplayName(
      "Test findVariantConfiguration(Iterable, String) with '$this$findVariantConfiguration', 'variantName'")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({
    "VariantConfiguration AndroidPluginKt.findVariantConfiguration(Iterable, String)"
  })
  void testFindVariantConfigurationWithThisFindVariantConfigurationVariantName4() {
    // Arrange
    ArrayList<VariantConfiguration> $this$findVariantConfiguration = new ArrayList<>();
    $this$findVariantConfiguration.add(new VariantConfiguration("variantName"));

    // Act and Assert
    assertNull(
        AndroidPluginKt.findVariantConfiguration($this$findVariantConfiguration, "Variant Name"));
  }

  /**
   * Test {@link AndroidPluginKt#findVariantConfiguration(Iterable, String)} with {@code
   * $this$findVariantConfiguration}, {@code variantName}.
   *
   * <p>Method under test: {@link AndroidPluginKt#findVariantConfiguration(Iterable, String)}
   */
  @Test
  @DisplayName(
      "Test findVariantConfiguration(Iterable, String) with '$this$findVariantConfiguration', 'variantName'")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({
    "VariantConfiguration AndroidPluginKt.findVariantConfiguration(Iterable, String)"
  })
  void testFindVariantConfigurationWithThisFindVariantConfigurationVariantName5() {
    // Arrange
    ArrayList<VariantConfiguration> $this$findVariantConfiguration = new ArrayList<>();
    $this$findVariantConfiguration.add(new VariantConfiguration("<this>"));

    // Act
    VariantConfiguration actualFindVariantConfigurationResult =
        AndroidPluginKt.findVariantConfiguration($this$findVariantConfiguration, "<this>");

    // Assert
    assertEquals("<this>", actualFindVariantConfigurationResult.getName());
    assertTrue(actualFindVariantConfigurationResult.getConfigurations().isEmpty());
    assertTrue(actualFindVariantConfigurationResult.getConsumerRuleFilter().isEmpty());
  }

  /**
   * Test {@link AndroidPluginKt#getAaptAdditionalParameters(BaseExtension)}.
   *
   * <ul>
   *   <li>Then return {@link List}.
   * </ul>
   *
   * <p>Method under test: {@link AndroidPluginKt#getAaptAdditionalParameters(BaseExtension)}
   */
  @Test
  @DisplayName("Test getAaptAdditionalParameters(BaseExtension); then return List")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"Collection AndroidPluginKt.getAaptAdditionalParameters(BaseExtension)"})
  void testGetAaptAdditionalParameters_thenReturnList() {
    // Arrange
    LibraryExtension $this$aaptAdditionalParameters = mock(LibraryExtension.class);
    when($this$aaptAdditionalParameters.getAaptOptions()).thenReturn(new AaptOptions(true));

    // Act
    Collection<String> actualAaptAdditionalParameters =
        AndroidPluginKt.getAaptAdditionalParameters($this$aaptAdditionalParameters);

    // Assert
    verify($this$aaptAdditionalParameters).getAaptOptions();
    assertTrue(actualAaptAdditionalParameters instanceof List);
    assertTrue(actualAaptAdditionalParameters.isEmpty());
  }

  /**
   * Test {@link AndroidPluginKt#hasVariantConfiguration(Iterable, String)}.
   *
   * <ul>
   *   <li>Given {@link VariantConfiguration#VariantConfiguration(String)} with {@code Name}.
   * </ul>
   *
   * <p>Method under test: {@link AndroidPluginKt#hasVariantConfiguration(Iterable, String)}
   */
  @Test
  @DisplayName(
      "Test hasVariantConfiguration(Iterable, String); given VariantConfiguration(String) with 'Name'")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean AndroidPluginKt.hasVariantConfiguration(Iterable, String)"})
  void testHasVariantConfiguration_givenVariantConfigurationWithName() {
    // Arrange
    LinkedHashSet<VariantConfiguration> $this$hasVariantConfiguration = new LinkedHashSet<>();
    $this$hasVariantConfiguration.add(new VariantConfiguration("Name"));

    // Act and Assert
    assertTrue(
        AndroidPluginKt.hasVariantConfiguration($this$hasVariantConfiguration, "Variant Name"));
  }

  /**
   * Test {@link AndroidPluginKt#hasVariantConfiguration(Iterable, String)}.
   *
   * <ul>
   *   <li>Given {@link VariantConfiguration#VariantConfiguration(String)} with name is {@code
   *       <this>}.
   * </ul>
   *
   * <p>Method under test: {@link AndroidPluginKt#hasVariantConfiguration(Iterable, String)}
   */
  @Test
  @DisplayName(
      "Test hasVariantConfiguration(Iterable, String); given VariantConfiguration(String) with name is '<this>'")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean AndroidPluginKt.hasVariantConfiguration(Iterable, String)"})
  void testHasVariantConfiguration_givenVariantConfigurationWithNameIsThis() {
    // Arrange
    ArrayList<VariantConfiguration> $this$hasVariantConfiguration = new ArrayList<>();
    $this$hasVariantConfiguration.add(new VariantConfiguration("<this>"));

    // Act and Assert
    assertFalse(
        AndroidPluginKt.hasVariantConfiguration($this$hasVariantConfiguration, "Variant Name"));
  }

  /**
   * Test {@link AndroidPluginKt#hasVariantConfiguration(Iterable, String)}.
   *
   * <ul>
   *   <li>Given {@link VariantConfiguration#VariantConfiguration(String)} with name is {@code
   *       variantName}.
   * </ul>
   *
   * <p>Method under test: {@link AndroidPluginKt#hasVariantConfiguration(Iterable, String)}
   */
  @Test
  @DisplayName(
      "Test hasVariantConfiguration(Iterable, String); given VariantConfiguration(String) with name is 'variantName'")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean AndroidPluginKt.hasVariantConfiguration(Iterable, String)"})
  void testHasVariantConfiguration_givenVariantConfigurationWithNameIsVariantName() {
    // Arrange
    ArrayList<VariantConfiguration> $this$hasVariantConfiguration = new ArrayList<>();
    $this$hasVariantConfiguration.add(new VariantConfiguration("variantName"));

    // Act and Assert
    assertFalse(
        AndroidPluginKt.hasVariantConfiguration($this$hasVariantConfiguration, "Variant Name"));
  }

  /**
   * Test {@link AndroidPluginKt#hasVariantConfiguration(Iterable, String)}.
   *
   * <ul>
   *   <li>When {@link ArrayList#ArrayList()}.
   *   <li>Then return {@code false}.
   * </ul>
   *
   * <p>Method under test: {@link AndroidPluginKt#hasVariantConfiguration(Iterable, String)}
   */
  @Test
  @DisplayName(
      "Test hasVariantConfiguration(Iterable, String); when ArrayList(); then return 'false'")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean AndroidPluginKt.hasVariantConfiguration(Iterable, String)"})
  void testHasVariantConfiguration_whenArrayList_thenReturnFalse() {
    // Arrange, Act and Assert
    assertFalse(AndroidPluginKt.hasVariantConfiguration(new ArrayList<>(), "Variant Name"));
  }

  /**
   * Test {@link AndroidPluginKt#hasVariantConfiguration(Iterable, String)}.
   *
   * <ul>
   *   <li>When {@code <this>}.
   *   <li>Then return {@code true}.
   * </ul>
   *
   * <p>Method under test: {@link AndroidPluginKt#hasVariantConfiguration(Iterable, String)}
   */
  @Test
  @DisplayName("Test hasVariantConfiguration(Iterable, String); when '<this>'; then return 'true'")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean AndroidPluginKt.hasVariantConfiguration(Iterable, String)"})
  void testHasVariantConfiguration_whenThis_thenReturnTrue() {
    // Arrange
    ArrayList<VariantConfiguration> $this$hasVariantConfiguration = new ArrayList<>();
    $this$hasVariantConfiguration.add(new VariantConfiguration("<this>"));

    // Act and Assert
    assertTrue(AndroidPluginKt.hasVariantConfiguration($this$hasVariantConfiguration, "<this>"));
  }
}
