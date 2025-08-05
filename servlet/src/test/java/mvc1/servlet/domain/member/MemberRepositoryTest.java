package mvc1.servlet.domain.member;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.List;

class MemberRepositoryTest {

    MemberRepository memberRepository = MemberRepository.getInstance();

    @AfterEach
    void afterEach(){
        memberRepository.clearStore();
    }

    @Test
    void save() {
        //given
        Member member1 = new Member("kim", 20);
        //when
        Member savedMember = memberRepository.save(member1);
        //then
        Member findMember = memberRepository.findById(savedMember.getId());
        Assertions.assertThat(findMember).isEqualTo(savedMember);
    }

    @Test
    void findById() {
        //given
        Member member1 = new Member("kim", 20);
        Member member2 = new Member("choi", 16);
        //when
        Member savedMember1 = memberRepository.save(member1);
        Member savedMember2 = memberRepository.save(member2);
        //then
        Member findMember1 = memberRepository.findById(savedMember1.getId());
        Member findMember2 = memberRepository.findById(savedMember2.getId());
        Assertions.assertThat(findMember1).isEqualTo(savedMember1);
        Assertions.assertThat(findMember2).isEqualTo(savedMember2);
    }

    @Test
    void findAll() {
        //given
        Member member1 = new Member("kim", 20);
        Member member2 = new Member("choi", 16);
        //when
        memberRepository.save(member1);
        memberRepository.save(member2);
        //then
        List<Member> members = memberRepository.findAll();
        Assertions.assertThat(members.size()).isEqualTo(2);
        Assertions.assertThat(members).contains(member1, member2);
    }

    @Test
    void clearStore() {

    }
}