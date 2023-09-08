package project.word.test.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

import static javax.persistence.CascadeType.*;

@Entity
@Getter
@NoArgsConstructor
public class Group {

    @Id @GeneratedValue
    private Long id;

    private String name;

    @OneToMany(cascade = ALL)
    private List<User> users;

    // 연관관계 편의 메서드
    public void addUser(User user) {
        user.setGroup(this);
        users.add(user);
    }
}
