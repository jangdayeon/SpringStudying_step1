package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemberRepository;
import hello.hellospring.repository.MemoryMemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MemberService {
    //쉽게 테스트 코드 만드는 법 : ctrl+shift+T

    //기존에는 회원 서비스가 메모리 회원 리포지토리를 직접 생성하게 했다.
//    private final MemberRepository memberRepository = new MemoryMemberRepository();
    //위 코드를 아래의 코드로 회원 서비스 코드를 DI 가능하게 변경한다.
    private final MemberRepository memberRepository;

    @Autowired
    public MemberService(MemberRepository memberRepository){
        this.memberRepository = memberRepository;
    }

    /**
     * 회원 가입
     */
    public Long join(Member member){
        // 같은 이름이 있는 중복 회원 X
        validateDuplicateMember(member); //중복 회원 검증

        memberRepository.save(member);
        return member.getId();
    }

    private void validateDuplicateMember(Member member) {
//        //이 아래는 memberRepository.findByName(member.getName());를 치고 Ctrl+Alt+V를 치면 자동으로 선언해줌
//        Optional<Member> result = memberRepository.findByName(member.getName());
//        result.ifPresent(m -> {
//            throw new IllegalStateException("이미 존재하는 회원입니다.");
//        }); //이건 Optional을 사용해서 쓸 수 있는 코드임
//        //원래 같았으면 if else문을 이용했겠지만, Optional은 null값이l값을 반환하지 않기 때문에 이처럼 깔끔하게 쓸 수 있음.

        //위의 코드를 아래와 같이 간단하게 표현할 수 있다.
        memberRepository.findByName(member.getName())
                        .ifPresent(m -> {
                            throw new IllegalStateException("이미 존재하는 회원입니다.");
                        });
    }

    /**
     * 전체 회원 조회
     */
    public List<Member> findMembers(){
        return memberRepository.findAll();
    }

    public Optional<Member> findOne(Long memberId){
        return memberRepository.findById(memberId);
    }
}
