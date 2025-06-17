package hello.hello_sping.service;

import hello.hello_sping.domain.Member;
import hello.hello_sping.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

//@Service
@Transactional
public class MemberService {
    private final MemberRepository memberRepository;

//    @Autowired
    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    //회원가입 => 같은 이름이 있는 중복 회원은 X
    public Long join(Member member){
        long start = System.currentTimeMillis();
        try{
            validateDuplicateMember(member); //중복 회원 검증
            memberRepository.save(member);
            return member.getId();
        } finally {
            long finish = System.currentTimeMillis();
            long timeMs = finish-start;
            System.out.println("join = " + timeMs + "ms");
        }

    }

    //전체 회원 조회
    public List<Member> findMembers(){
        long start = System.currentTimeMillis();
        try{
            return memberRepository.findAll();
        } finally {
            long finish = System.currentTimeMillis();
            long timeMs = finish-start;
            System.out.println("findMembers = " + timeMs + "ms");
        }

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
