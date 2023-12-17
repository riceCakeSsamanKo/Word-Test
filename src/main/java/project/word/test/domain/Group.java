package project.word.test.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

import static lombok.AccessLevel.*;

@Entity
@Getter
@Table(name = "GROUPS")
@NoArgsConstructor(access = PROTECTED)
public class Group {
    @Id @GeneratedValue
    @Column(name = "group_id")
    private Long id;

    private String name;

    public Group(String name){
        this.name = name;
    }

    // 비즈니스 로직
    public void changeName(String name) {
        this.name = name;
    }
}
