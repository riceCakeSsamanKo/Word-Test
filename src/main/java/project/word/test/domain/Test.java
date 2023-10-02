package project.word.test.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static javax.persistence.CascadeType.*;
import static javax.persistence.EnumType.*;
import static javax.persistence.FetchType.*;
import static lombok.AccessLevel.*;
import static project.word.test.domain.TestStatus.*;

@Entity
@Getter
@NoArgsConstructor(access = PROTECTED)
public class Test {
    @Id
    @GeneratedValue
    private Long id;

    private String name;
    private int score;
    private LocalDateTime registrationTime;

    public Test(String name) {
        this.name = name;
        score = 0;
        testStatus = PRE;
        registrationTime = LocalDateTime.now();
    }

    @Enumerated(STRING)
    private TestStatus testStatus;

    @OneToMany(cascade = ALL, mappedBy = "test")
    private List<TestUser> testUsers = new ArrayList<>();

    @OneToMany(cascade = ALL, mappedBy = "test")
    private List<TestGroup> testGroups = new ArrayList<>();

    @OneToOne(fetch = LAZY, cascade = ALL)
    @JoinColumn(name = "word_list_id")
    private WordList wordList;

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
    public void addTestGroup(TestGroup testGroup) {
        testGroup.setTest(this);
        testGroups.add(testGroup);
    }

    public void addWordList(WordList wordList) {
        this.wordList = wordList;
    }
}
