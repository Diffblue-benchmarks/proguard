package proguard.shrink;

import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import proguard.resources.kotlinmodule.KotlinModule;
import proguard.resources.kotlinmodule.visitor.KotlinModulePackageVisitor;

class KotlinModuleShrinkerDiffblueTest {
  /**
   * Method under test:
   * {@link KotlinModuleShrinker#visitKotlinModule(KotlinModule)}
   */
  @Test
  void testVisitKotlinModule() {
    // Arrange
    KotlinModuleShrinker kotlinModuleShrinker = new KotlinModuleShrinker(mock(ShortestUsageMarker.class));
    KotlinModule kotlinModule = mock(KotlinModule.class);
    doNothing().when(kotlinModule).modulePackagesAccept(Mockito.<KotlinModulePackageVisitor>any());

    // Act
    kotlinModuleShrinker.visitKotlinModule(kotlinModule);

    // Assert
    verify(kotlinModule).modulePackagesAccept(isA(KotlinModulePackageVisitor.class));
  }
}
