package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

//jpa를 extends를 하면 스프링이 자동으로 구현체를 만들어서 등록을 해줌
//라이브러리를 이용하기 때문에 나머지 함수는 override를 안해도 되는 것
public interface SpringDataJpaMemberRepository extends JpaRepository<Member,Long>, MemberRepository {
    @Override
    Optional<Member> findByName(String name);
}
