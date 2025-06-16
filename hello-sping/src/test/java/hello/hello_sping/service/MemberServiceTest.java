package hello.hello_sping.service;

import hello.hello_sping.domain.Member;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertThrows;

import hello.hello_sping.repository.MemoryMemberRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

class MemberServiceTest {
    MemberService memberService;
    MemoryMemberRepository memberRepository;

    @BeforeEach
    public void beforeEach() {
        memberRepository = new MemoryMemberRepository();
        memberService = new MemberService(memberRepository);
    }

    @AfterEach
    public void afterEach(){
        memberRepository.clearStore();
    }

    @Test
    void join() {
        //given: 주어진 조건
        Member member = new Member();
        member.setName("member1");

        //when: 실행
        Long saveId = memberService.join(member);

        //then: 그러면
        Member result = memberRepository.findById(saveId).get();
        assertThat(member).isEqualTo(result);
    }

    @Test
    void join_duplicated() {
        //given: 주어진 조건
        Member member1 = new Member();
        member1.setName("member1");
        Member member2 = new Member();
        member2.setName("member1");

        //when: 실행
        Long member1Id = memberService.join(member1);
        //then: 그러면
//        try {
//            Long member2Id = memberService.join(member2);
//            fail("중복 회원가입으로 예외가 발생해야 합니다.");
//        } catch (IllegalStateException e){
//            assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");
//        }
        
        //예외가 정상적으로 발생하는 지 확인함
        IllegalStateException e = assertThrows(IllegalStateException.class, ()->memberService.join(member2));
        assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");

    }

    @Test
    void findMembers() {
        //given
        Member member1 = new Member();
        member1.setName("member1");
        Member member2 = new Member();
        member2.setName("member2");

        memberRepository.save(member1);
        memberRepository.save(member2);

        //when
        List<Member> result = memberService.findMembers();

        //then
        assertThat(2).isEqualTo(result.size());
    }

    @Test
    void findOne() {
        //given
        Member member1 = new Member();
        member1.setName("member1");
        Member member2 = new Member();
        member2.setName("member2");

        Long saveId1 = memberService.join(member1);
        Long saveId2 = memberService.join(member2);

        //when
        Optional<Member> result1 = memberService.findOne(saveId1);
        Optional<Member> result2 = memberService.findOne(saveId2);
        Optional<Member> result3 = memberService.findOne(3L);

        if(result1.isEmpty()){
            fail("member1을 찾을 수 없습니다.");
        }
        result1.ifPresent(m->{assertThat(saveId1).isEqualTo(m.getId());});

        if(result2.isEmpty()){
            fail("member2을 찾을 수 없습니다.");
        }
        result2.ifPresent(m->{assertThat(saveId2).isEqualTo(m.getId());});

        result3.ifPresent(m->fail("등록하지 않은 member3가 존재합니다."));

    }
}