package project.word.test.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.yaml.snakeyaml.extensions.compactnotation.PackageCompactConstructor;

import javax.persistence.*;

import java.util.ArrayList;
import java.util.List;

import static javax.persistence.CascadeType.*;
import static javax.persistence.EnumType.*;
import static javax.persistence.FetchType.*;
import static lombok.AccessLevel.*;
import static project.word.test.domain.AccountType.ADMIN;
import static project.word.test.domain.AccountType.USER;
import static project.word.test.domain.Gender.MALE;

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

    @Setter
    @Enumerated(STRING)
    private AccountType accountType;

    @Embedded
    private LogIn login;

    @Setter
    @ManyToOne(fetch = LAZY, cascade = PERSIST)
    @JoinColumn(name = "group_id")
    private Group group;

    @OneToMany(mappedBy = "user", cascade = PERSIST)
    private List<UserTest> userTests = new ArrayList<>();

    public User(String name, int age, Gender gender, LogIn login, AccountType accountType) {

        this.name = name;
        this.age = age;
        this.gender = gender;
        this.login = login;
        this.accountType = accountType;
    }

    public User(String name, int age, Gender gender, String logIn_id, String logIn_pw, AccountType accountType) {

        this.name = name;
        this.age = age;
        this.gender = gender;
        this.login = new LogIn(logIn_id, logIn_pw);
        if (accountType == null) {
            accountType = USER;
        }
        this.accountType = accountType;
    }

    public User(String name, int age, Gender gender, LogIn login, Group group, AccountType accountType) {
        this.name = name;
        this.age = age;
        this.gender = gender;
        this.login = login;
        this.group = group;
        this.accountType = accountType;
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
