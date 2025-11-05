package proguard.optimize.kotlin;

import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import proguard.classfile.Clazz;
import proguard.classfile.LibraryClass;
import proguard.classfile.kotlin.KotlinClassKindMetadata;
import proguard.classfile.kotlin.visitor.KotlinConstructorVisitor;

class KotlinContextReceiverUsageMarkerDiffblueTest {
  /**
   * Method under test:
   * {@link KotlinContextReceiverUsageMarker#visitKotlinClassMetadata(Clazz, KotlinClassKindMetadata)}
   */
  @Test
  void testVisitKotlinClassMetadata() {
    // Arrange
    KotlinContextReceiverUsageMarker kotlinContextReceiverUsageMarker = new KotlinContextReceiverUsageMarker();
    LibraryClass clazz = new LibraryClass();
    KotlinClassKindMetadata kotlinClassKindMetadata = mock(KotlinClassKindMetadata.class);
    doNothing().when(kotlinClassKindMetadata)
        .constructorsAccept(Mockito.<Clazz>any(), Mockito.<KotlinConstructorVisitor>any());

    // Act
    kotlinContextReceiverUsageMarker.visitKotlinClassMetadata(clazz, kotlinClassKindMetadata);

    // Assert
    verify(kotlinClassKindMetadata).constructorsAccept(isA(Clazz.class), isA(KotlinConstructorVisitor.class));
  }
}
