package project.word.test.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import project.word.test.repository.GroupRepository;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

import static javax.persistence.CascadeType.*;
import static lombok.AccessLevel.*;

@Entity
@Getter
@NoArgsConstructor(access = PROTECTED)
@Table(name = "GROUPS")
public class Group {

    @Id @GeneratedValue
    private Long id;

    private String name;

    @OneToMany(cascade = ALL, mappedBy = "group")
    private List<User> users = new ArrayList<>();

    public Group(String name){
        this.name = name;
    }

    // setter
    public void setName(String name) {
        this.name = name;
    }

    // 연관관계 편의 메서드
    public void addUser(User user) {
        users.add(user);
        user.setGroup(this);
    }

    public void deleteUser(User user) {
        users.remove(user);
    }
}
