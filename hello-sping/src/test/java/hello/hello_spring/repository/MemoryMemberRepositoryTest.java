package hello.hello_spring.repository;

import hello.hello_spring.domain.Member;
import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.List;

class MemoryMemberRepositoryTest {
    MemoryMemberRepository repository = new MemoryMemberRepository();

    @AfterEach //각 테스트가 끝날 때마다 repository store 비움
    public void afterEach(){
        // 테스트는 실행 순서 상관 없이 독립적으로 동작해야 함 -> 매 테스트가 끝날 때마다 repository를 비움
        repository.clearStore();
    }

    @Test
    public void save(){
        Member member = new Member();
        member.setName("testName");

        repository.save(member);

        Member result = repository.findById(member.getId()).get();
        assertThat(member).isEqualTo(result);
    }

    @Test
    public void findByName(){
        Member member1 = new Member();
        member1.setName("member1Name");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("member2Name");
        repository.save(member2);

        Member result = repository.findByName("member2Name").get();
        assertThat(member2).isEqualTo(result);
    }

    @Test
    public void findAll(){
        Member member1 = new Member();
        member1.setName("member1Name");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("member2Name");
        repository.save(member2);

        List<Member> result = repository.findAll();
        assertThat(result.size()).isEqualTo(2);

    }
}
