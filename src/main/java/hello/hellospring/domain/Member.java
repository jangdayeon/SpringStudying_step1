package hello.hellospring.domain;

import jakarta.persistence.*;

//ORM : object related mapping ( 오브젝트를 관계형으로 매핑해보기 )
@Entity //이제 자바에서 관리해주는 객체라는 뜻
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    //이 IDENTITY는 이전에 db에서 번호순서대로 알아서 생성해주는 것
    private Long id;

    private String name;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
