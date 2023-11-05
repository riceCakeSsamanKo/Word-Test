package project.word.test.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import java.util.ArrayList;
import java.util.List;

import static javax.persistence.CascadeType.*;
import static javax.persistence.EnumType.*;
import static javax.persistence.FetchType.*;
import static lombok.AccessLevel.*;

@Entity
@Getter
@NoArgsConstructor(access = PROTECTED)
public class User {
    @Id
    @GeneratedValue
    @Column(name = "user_id")
    private Long id;

    private String name;
    private int age;

    @Enumerated(STRING)
    private Gender gender;

    @Embedded
    private LogIn login;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "group_id")
    private Group group;

    @OneToMany(mappedBy = "user", cascade = PERSIST)
    private List<UserTest> userTests = new ArrayList<>();

    public User(String name, int age, Gender gender, LogIn login, Group group, UserTest... userTests) {
        this.name = name;
        this.age = age;
        this.gender = gender;
        this.login = login;
        this.group = group;
        for (UserTest userTest : userTests) {
            addUserTest(userTest);
        }
    }

    // 비즈니스 로직
    public void changeInfo(String name, int age, Gender gender) {
        this.name = name;
        this.age = age;
        this.gender = gender;
    }
    public void changePassword(String pw) {
        String id = this.login.getId();
        this.login = new LogIn(id, pw);
    }
    public void changeGroup(Group group) {
        this.group = group;
    }

    //*연관 관계 편의 메서드*//
    public void addUserTest(UserTest userTest) {
        this.userTests.add(userTest);
        userTest.setUser(this);
    }
}
