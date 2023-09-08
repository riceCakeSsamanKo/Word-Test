package project.word.test.domain;

import lombok.Getter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

import static javax.persistence.CascadeType.*;
import static javax.persistence.EnumType.*;

@Entity
@Getter
public class Test {
    @Id
    @GeneratedValue
    private Long id;

    private String name;
    private int score;
    private LocalDateTime registrationTime;

    @Enumerated(STRING)
    private TestStatus testStatus;

    @OneToMany(cascade = ALL, mappedBy = "test")
    private List<TestUser> testUsers;

    @OneToMany(cascade = ALL, mappedBy = "test")
    private List<TestWord> testWords;

    // setter
    public void setName(String name) {
        this.name = name;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public void setRegistrationTime(LocalDateTime registrationTime) {
        this.registrationTime = registrationTime;
    }

    public void setTestStatus(TestStatus testStatus) {
        this.testStatus = testStatus;
    }

    // 연관관계 편의 메서드
    public void addTestUser(TestUser testUser) {
        testUser.setTest(this);
        testUsers.add(testUser);
    }

    public void addTestWord(TestWord testWord) {
        testWord.setTest(this);
        testWords.add(testWord);
    }
}
