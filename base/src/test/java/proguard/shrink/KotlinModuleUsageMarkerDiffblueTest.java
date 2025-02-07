package proguard.shrink;

import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.ArgumentMatchers.isNull;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.util.ArrayList;
import java.util.HashMap;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import proguard.resources.kotlinmodule.KotlinModule;
import proguard.resources.kotlinmodule.KotlinModulePackage;
import proguard.resources.kotlinmodule.visitor.KotlinModulePackageVisitor;
import proguard.util.Processable;

class KotlinModuleUsageMarkerDiffblueTest {
  /**
   * Test {@link KotlinModuleUsageMarker#visitKotlinModule(KotlinModule)}.
   * <ul>
   *   <li>Then calls {@link KotlinModule#modulePackagesAccept(KotlinModulePackageVisitor)}.</li>
   * </ul>
   * <p>
   * Method under test: {@link KotlinModuleUsageMarker#visitKotlinModule(KotlinModule)}
   */
  @Test
  @DisplayName("Test visitKotlinModule(KotlinModule); then calls modulePackagesAccept(KotlinModulePackageVisitor)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "void proguard.shrink.KotlinModuleUsageMarker.visitKotlinModule(proguard.resources.kotlinmodule.KotlinModule)"})
  void testVisitKotlinModule_thenCallsModulePackagesAccept() {
    // Arrange
    KotlinModuleUsageMarker kotlinModuleUsageMarker = new KotlinModuleUsageMarker(new SimpleUsageMarker());
    KotlinModule kotlinModule = mock(KotlinModule.class);
    doNothing().when(kotlinModule).modulePackagesAccept(Mockito.<KotlinModulePackageVisitor>any());

    // Act
    kotlinModuleUsageMarker.visitKotlinModule(kotlinModule);

    // Assert
    verify(kotlinModule).modulePackagesAccept(isA(KotlinModulePackageVisitor.class));
  }

  /**
   * Test {@link KotlinModuleUsageMarker#visitKotlinModulePackage(KotlinModule, KotlinModulePackage)}.
   * <ul>
   *   <li>Then calls {@link ShortestUsageMarker#isUsed(Processable)}.</li>
   * </ul>
   * <p>
   * Method under test: {@link KotlinModuleUsageMarker#visitKotlinModulePackage(KotlinModule, KotlinModulePackage)}
   */
  @Test
  @DisplayName("Test visitKotlinModulePackage(KotlinModule, KotlinModulePackage); then calls isUsed(Processable)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "void proguard.shrink.KotlinModuleUsageMarker.visitKotlinModulePackage(proguard.resources.kotlinmodule.KotlinModule, proguard.resources.kotlinmodule.KotlinModulePackage)"})
  void testVisitKotlinModulePackage_thenCallsIsUsed() {
    // Arrange
    ShortestUsageMarker usageMarker = mock(ShortestUsageMarker.class);
    when(usageMarker.isUsed(Mockito.<Processable>any())).thenReturn(true);
    KotlinModuleUsageMarker kotlinModuleUsageMarker = new KotlinModuleUsageMarker(usageMarker);

    ArrayList<String> fileFacadeNames = new ArrayList<>();
    fileFacadeNames.add("42");

    // Act
    kotlinModuleUsageMarker.visitKotlinModulePackage(null,
        new KotlinModulePackage("Fq Name", fileFacadeNames, new HashMap<>()));

    // Assert
    verify(usageMarker).isUsed((Processable) isNull());
  }
}
