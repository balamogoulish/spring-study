package hello.hello_sping.service;

import hello.hello_sping.domain.Member;
import hello.hello_sping.repository.MemoryMemberRepository;

import java.util.List;
import java.util.Optional;

public class MemberService {
    private final MemoryMemberRepository memberRepository;

    public MemberService(MemoryMemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    //회원가입 => 같은 이름이 있는 중복 회원은 X
    public Long join(Member member){
        validateDuplicateMember(member); //중복 회원 검증

        memberRepository.save(member);
        return member.getId();
    }

    //전체 회원 조회
    public List<Member> findMembers(){
        return memberRepository.findAll();
    }

    //아이디로 회원 검색
    public Optional<Member> findOne(Long id){
        return memberRepository.findById(id);
    }

    private void validateDuplicateMember(Member member) {
        memberRepository.findByName(member.getName())
                .ifPresent(m->{
                    throw new IllegalStateException("이미 존재하는 회원입니다.");
                });
    }

    
}
