package hello.hellospring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class HelloController {

    @GetMapping("hello")
    public String hello(Model model){
        model.addAttribute("data","hello!!");
        return "hello";
    }

    @GetMapping("hello-mvc")
//    왜 error가 뜨는가?
//    @RequestParam에 ^+P를 해서 커멘드 확인해보면, name값이 전달이 안되어서 그런 걸 확인 할 수 있다.
//    name값을 주기 위해서는 주소창의 주소 맨 뒤에 ?name=spring!!!!! 과같이 붙고 다시 실행하면 올바르게 결과가 나온다.
    public String helloMvc(@RequestParam(value = "name") String name, Model model){
        model.addAttribute("name",name);
        return "hello-template";
    }
}
