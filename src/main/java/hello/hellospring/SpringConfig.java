package hello.hellospring;

import hello.hellospring.repository.JdbcMemberRepository;
import hello.hellospring.repository.MemberRepository;
import hello.hellospring.repository.MemoryMemberRepository;
import hello.hellospring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
public class SpringConfig {
    //Spring에서 db연결을 자동으로 해줌 !!/////////////////////////////
    private DataSource dataSource;

    @Autowired
    public SpringConfig(DataSource dataSource){
        this.dataSource=dataSource;
    }
    ///////////////////////////////////////////////////////////////

    @Bean
    public MemberService memberService(){
        return new MemberService(memberRepository());
    }

    //이게 나중에 db를 생성할 것을 고려해서 만들었던 것임!!!
    //db 연결을 후에 했어도, 아래처럼 리턴문을 바꾸기만 하면 됨!!!
    @Bean
    public MemberRepository memberRepository(){

//        return new MemoryMemberRepository();
        return new JdbcMemberRepository(dataSource);
    }
}
