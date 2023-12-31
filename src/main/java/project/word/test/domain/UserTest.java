package project.word.test.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

import static javax.persistence.EnumType.*;
import static javax.persistence.FetchType.*;
import static lombok.AccessLevel.*;
import static project.word.test.domain.Pass.*;

@Entity
@Getter @Setter
@NoArgsConstructor(access = PROTECTED)
public class UserTest {
    @Id @GeneratedValue
    @Column(name = "user_test_id")
    private Long id;

    private int score;

    @Enumerated(value = STRING)
    private Pass pass;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "test_id")
    private Test test;


    // 생성 메서드
    public static UserTest createUserTest(User user, Test test) {
        UserTest userTest = new UserTest();
        userTest.user = user;
        userTest.test = test;
        userTest.pass = FAIL;
        return userTest;
    }

    // 비즈니스 로직
    public void changePass(Pass pass) {
        this.pass = pass;
    }

    public void setScore(int score) {
        this.score = score;
    }
}
