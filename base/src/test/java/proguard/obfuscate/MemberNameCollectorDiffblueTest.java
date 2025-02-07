package proguard.obfuscate;

import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.util.HashMap;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import proguard.classfile.Clazz;
import proguard.classfile.LibraryClass;
import proguard.classfile.Member;

class MemberNameCollectorDiffblueTest {
  /**
   * Test {@link MemberNameCollector#visitAnyMember(Clazz, Member)}.
   * <p>
   * Method under test: {@link MemberNameCollector#visitAnyMember(Clazz, Member)}
   */
  @Test
  @DisplayName("Test visitAnyMember(Clazz, Member)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "void proguard.obfuscate.MemberNameCollector.visitAnyMember(proguard.classfile.Clazz, proguard.classfile.Member)"})
  void testVisitAnyMember() {
    // Arrange
    MemberNameCollector memberNameCollector = new MemberNameCollector(false, new HashMap<>());
    LibraryClass clazz = new LibraryClass();
    Member member = mock(Member.class);
    when(member.getDescriptor(Mockito.<Clazz>any())).thenReturn("Descriptor");
    when(member.getProcessingInfo()).thenReturn("Processing Info");
    when(member.getName(Mockito.<Clazz>any())).thenReturn("Name");

    // Act
    memberNameCollector.visitAnyMember(clazz, member);

    // Assert
    verify(member).getDescriptor(isA(Clazz.class));
    verify(member).getName(isA(Clazz.class));
    verify(member, atLeast(1)).getProcessingInfo();
  }

  /**
   * Test {@link MemberNameCollector#visitAnyMember(Clazz, Member)}.
   * <ul>
   *   <li>Given {@code Descriptor}.</li>
   *   <li>Then calls {@link Member#getDescriptor(Clazz)}.</li>
   * </ul>
   * <p>
   * Method under test: {@link MemberNameCollector#visitAnyMember(Clazz, Member)}
   */
  @Test
  @DisplayName("Test visitAnyMember(Clazz, Member); given 'Descriptor'; then calls getDescriptor(Clazz)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "void proguard.obfuscate.MemberNameCollector.visitAnyMember(proguard.classfile.Clazz, proguard.classfile.Member)"})
  void testVisitAnyMember_givenDescriptor_thenCallsGetDescriptor() {
    // Arrange
    MemberNameCollector memberNameCollector = new MemberNameCollector(true, new HashMap<>());
    LibraryClass clazz = new LibraryClass();
    Member member = mock(Member.class);
    when(member.getDescriptor(Mockito.<Clazz>any())).thenReturn("Descriptor");
    when(member.getProcessingInfo()).thenReturn("Processing Info");
    when(member.getName(Mockito.<Clazz>any())).thenReturn("Name");

    // Act
    memberNameCollector.visitAnyMember(clazz, member);

    // Assert
    verify(member).getDescriptor(isA(Clazz.class));
    verify(member).getName(isA(Clazz.class));
    verify(member, atLeast(1)).getProcessingInfo();
  }
}
