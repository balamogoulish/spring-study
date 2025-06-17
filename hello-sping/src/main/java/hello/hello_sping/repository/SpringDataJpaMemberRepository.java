package hello.hello_sping.repository;

import hello.hello_sping.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

//JpaRepository<Entity, PK의 타입>
//JpaRepository가 알아서 구현체를 생성하고 빈 등록
public interface SpringDataJpaMemberRepository extends JpaRepository<Member, Long>, MemberRepository {

    //findByCOLNAME => select m from Member m where m.COLNAME=?
    @Override
    Optional<Member> findByName(String name);
}
