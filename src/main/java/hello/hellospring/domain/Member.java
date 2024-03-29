package hello.hellospring.domain;

import jakarta.persistence.*;

@Entity // JPA가 관리하는 Entity이다.
public class Member {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) // DB가 ID를 자동으로 생성해줌.
    private Long id;

    @Column(name = "username") // DB의 username Column과 연결
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
