package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

public class MemoryMemberRepositoryTest {
    MemoryMemberRepository repository = new MemoryMemberRepository();

    @AfterEach //하나의 메소드가 끝날 때마다 실행됨
    public void afterEach(){
        repository.clearStore();
    }

    @Test
    public void save(){
        Member member = new Member();
        member.setName("spring");

        repository.save(member);

        Member result = repository.findById(member.getId()).get();
        Assertions.assertEquals(member,result);
        //result와 member가 같은지 확인
        org.assertj.core.api.Assertions.assertThat(member).isEqualTo(result);
        //위 assertions과 아래 Assertions은 다른 것임. 아래께 더 직관점임
    }

    @Test
    public void findByName(){
        Member member1 = new Member();
        member1.setName("spring1");
        repository.save(member1);

        //shift + f6 이면 rename이 가능
        Member member2 = new Member();
        member2.setName("spring2");
        repository.save(member2);

        Member result = repository.findByName("spring1").get();

        org.assertj.core.api.Assertions.assertThat(result).isEqualTo(member1);
    }

    @Test
    public void findAll(){
        Member member1 = new Member();
        member1.setName("spring1");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("spring2");
        repository.save(member2);

        List<Member> result = repository.findAll();

        org.assertj.core.api.Assertions.assertThat(result.size()).isEqualTo(2);
    }
    //함수 하나하나를 실행하면 오류가 발생하지 않는데,
    //왜 이 파일 전체를 실행했을 때 오류가 나는가?
    //-> 함수가 끝났을 때 clear을 안해서 다른 함수에서 실행한 member1이 실행됐기 때문
}
