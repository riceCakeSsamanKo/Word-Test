package project.word.test.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import static javax.persistence.EnumType.*;
import static javax.persistence.FetchType.*;

@Entity
@Getter
@NoArgsConstructor
@Table(name = "USERS")
public class User {

    @Id @GeneratedValue
    @Column(name = "user_id")
    private Long id;

    private String name;
    private int age;

    @Enumerated(value = STRING)
    private AccountType accountType;

    @Embedded
    private LogInInformation logInInformation;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "group_id")
    private Group group;

    public User(String name, int age, AccountType accountType) {
        this.name = name;
        this.age = age;
        this.accountType = accountType;
    }

    public void setGroup(Group group) {
        this.group = group;
    }
    public void setAccountType(AccountType accountType){
        this.accountType = accountType;
    }
    public void changeUserInfo(String name, int age, String password) {
        this.name = name;
        this.age = age;
        this.logInInformation.setPassword(password);
    }

}