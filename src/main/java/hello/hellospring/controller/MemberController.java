package hello.hellospring.controller;

import hello.hellospring.domain.Member;
import hello.hellospring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class MemberController {
    private final MemberService memberService;

    @Autowired
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    } //생성자 generated
    //autowired는 DI(의존성 주입)을 스프링이 하도록 하는 것
    //but, memberService를 찾을 수 없다고 에러가 뜬다.
    //이유는 스프링이 하도록하려면 스프링이 알아볼 수 있도록 @Controller, @repository, @service를
    //각각의 파일에 적어놔야지 인식하고 주입시킬 수 있기 때문이다!!
    //참고로 이전에 했던 HelloController은 스프링이 제공하는 컨트롤러여서 스프링 빈으로 자동 등록된다.

    @GetMapping("/members/new")
    public String createForm(){
        return "members/createMemberForm";
    }

    @PostMapping("/members/new")
    public String create(MemberForm form){
        Member member = new Member();
        member.setName(form.getName());

        memberService.join(member);

        return "redirect:/";
    }

    //GetMapping과 PostMapping의 차이
    //Get은 데이터를 가져와서 가공할 때, Post는 데이터를 내보낼 때 이용
}
