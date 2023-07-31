package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemberRepository;
import hello.hellospring.repository.MemoryMemberRepository;
import hello.hellospring.repository.MemoryMemberRepositoryTest;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional

class MemberServiceIntegrationTest {
    //아래 코드를 DI 가능하도록 바꾸자
//    MemberService memberService = new MemberService();
//    MemoryMemberRepository memberRepository = new MemoryMemberRepository();

    //테스트할 때 그냥 끌어와서 사용 후 끝이므로 그냥 아래의
    //코드와 같이 Autowired한다.
    @Autowired MemberService memberService;
    @Autowired MemberRepository memberRepository;


    @Test
        //테스트는 한글로 바꿔서 작성해도 됨
    void 회원가입() {
        //given -> when -> then
        //이런 게 주어줬을 때 ~상황에서 ~이런 결과가 나와야 해
        //이런 주석이 도움이 많이 됨!


        //given
        Member member = new Member();
        member.setName("spring"); //spring이 중복되서 매번 테스트할 때마다
        //@AfterEach를 해야하는 문제가 생김
        //이걸 해결하기 위해 @Transactional이 사용!!!!
        //이것은 test하는 동안 db를 수정했던 모든 내역들을 테스트가 끝나면
        //전부 롤백(원래대로 되돌려줌)한다!!
        //혁신 그잡채,,


        //when
        Long saveId = memberService.join(member);
        //then
        Member findMember = memberService.findOne(saveId).get();
        assertThat(member.getName()).isEqualTo(findMember.getName());
    }

    @Test
    public void 중복_회원_예외(){
        //given
        Member member1 = new Member();
        member1.setName("spring");

        Member member2 = new Member();
        member2.setName("spring");
        //when
        memberService.join(member1);

//        try {
//            memberService.join(member2);
//            fail();
//        } catch(IllegalStateException e){
//            assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");
//        }
        //then

        //tryCatch를 안쓰고 간단하게 쓰는 방법
        IllegalStateException e = assertThrows(IllegalStateException.class, () -> memberService.join(member2));

        assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");
    }

}