package proguard.obfuscate;

import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import java.util.HashMap;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import proguard.classfile.Clazz;
import proguard.classfile.LibraryClass;
import proguard.classfile.Member;

class MemberNameCollectorDiffblueTest {
  /**
   * Method under test: {@link MemberNameCollector#visitAnyMember(Clazz, Member)}
   */
  @Test
  void testVisitAnyMember() {
    // Arrange
    MemberNameCollector memberNameCollector = new MemberNameCollector(true, new HashMap<>());
    LibraryClass clazz = new LibraryClass(1, "This Class Name", "Super Class Name");

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
   * Method under test: {@link MemberNameCollector#visitAnyMember(Clazz, Member)}
   */
  @Test
  void testVisitAnyMember2() {
    // Arrange
    MemberNameCollector memberNameCollector = new MemberNameCollector(true, new HashMap<>());
    Clazz clazz = mock(Clazz.class);
    when(clazz.extendsOrImplements(Mockito.<String>any())).thenReturn(true);
    Member member = mock(Member.class);
    when(member.getDescriptor(Mockito.<Clazz>any())).thenReturn("Descriptor");
    when(member.getProcessingInfo()).thenReturn("Processing Info");
    when(member.getName(Mockito.<Clazz>any())).thenReturn("Name");

    // Act
    memberNameCollector.visitAnyMember(clazz, member);

    // Assert
    verify(clazz).extendsOrImplements(eq("java/lang/annotation/Annotation"));
    verify(member).getDescriptor(isA(Clazz.class));
    verify(member).getName(isA(Clazz.class));
    verify(member, atLeast(1)).getProcessingInfo();
  }

  /**
   * Method under test: {@link MemberNameCollector#visitAnyMember(Clazz, Member)}
   */
  @Test
  void testVisitAnyMember3() {
    // Arrange
    MemberNameCollector memberNameCollector = new MemberNameCollector(false, new HashMap<>());
    Clazz clazz = mock(Clazz.class);
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
