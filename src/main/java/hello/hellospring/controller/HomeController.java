package hello.hellospring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
    @GetMapping("/") // 슬레시는 그냥 첫번째 도메인
    public String home(){
        return "home";
    }
}
//이 HomeController가 없었으면, hello.html이 실행됐을테지만, 항상 controller을 먼저 확인하고 파일을 확인하기 때문에
// hello.html을 무시하고 home.html이 실행된다.