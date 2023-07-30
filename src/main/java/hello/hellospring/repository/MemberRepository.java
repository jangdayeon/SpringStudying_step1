package hello.hellospring.repository;


import hello.hellospring.domain.Member;
import java.util.List;
import java.util.Optional;

public interface MemberRepository {
    Member save(Member member);
    Optional<Member> findById(Long id); //Optional : null값이 들어올 것에 대한 대비칙
    Optional<Member> findByName(String name);
    List<Member> findAll();
}
