package hello.hello_sping;

import hello.hello_sping.repository.*;
import hello.hello_sping.service.MemberService;
import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

// 수동 스프링 빈 등록
/**
 * 상황에 따라 구현 클래스를 변경해야 하는 경우 수동 빈 등록
 * @Autowired를 통한 DI는 스프링 빈으로 등록된 것만 동작
 */
@Configuration
public class SpringConfig {

    private final MemberRepository memberRepository;

    @Autowired
    public SpringConfig(MemberRepository memberRepository){
        this.memberRepository = memberRepository;
    }

    @Bean
    public MemberService memberService(){
        return new MemberService(memberRepository);
    }

//    @Bean
//    public MemberRepository memberRepository(){
//        return new MemoryMemberRepository();
//        return new JdbcMemberRepository(datasource);
//        return new JdbcTemplateMemberRepository(datasource);
//        return new JpaMemberRepository(em);
//    }
}
