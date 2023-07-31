package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import jakarta.persistence.EntityManager;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Transactional //데이터를 저장하거나 변경할 때 항상 필요함!
public class JpaMemberRepository implements MemberRepository{

    //JPA라이브러리를 받았으면 스프링부트에서 자동으로 EntityManager을 만들어줌
    //이걸 이용해서 db를 연결하면 됨
    private final EntityManager em;

    public JpaMemberRepository(EntityManager em) {
        this.em = em;
    }

    @Override
    public Member save(Member member) {
        em.persist(member); //이렇게만해도 다 insert문을 자동으로 만들어줌
        return member;
    }

    @Override
    public Optional<Member> findById(Long id) {
        Member member = em.find(Member.class, id);
        return Optional.ofNullable(member);
    }

    //하나의 데이터를 찾을 땐 위처럼 간단하게 적으면 되지만,
    //리스트로 데이터를 받아와야 하는 경우는 아래처럼 jpql을 작성해줘야 한다.
    @Override
    public Optional<Member> findByName(String name) {
        List<Member> result = em.createQuery("select m from Member m where m.name = :name", Member.class)
                .setParameter("name",name)
                .getResultList();

        return result.stream().findAny();
    }

    @Override
    public List<Member> findAll() {

        //코드 합칠 수 있는 거 합치는 단축어
        //Shift+Ctrl+Alt+T 하고 inline 누르면 됨
        return em.createQuery("select m from Member m", Member.class)
                .getResultList();
        //name이나 id가 아니라 객체 자체를 select하면 jpa가 알아서 처리해줌
    }
}
