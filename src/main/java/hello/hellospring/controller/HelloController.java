package hello.hellospring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

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

    @GetMapping("hello-string")
    @ResponseBody // HttpMessageConverter에게 넘김
    public String helloString(@RequestParam("name") String name){
        return "hello " + name; //이건 html의 body에 return된 코드가 그대로 들어가 출력됨
        // String으로 그냥 넘김
    }

    @GetMapping("hello-api") //JSON방식으로 출력됨
    @ResponseBody
    public Hello helloApi(@RequestParam("name") String name){
        Hello hello = new Hello();
        hello.setName(name);
        return hello;
        //객체니깐 Json으로 넘김
    }

    static class Hello {
        private String name;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}
