package project.word.test.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import java.util.ArrayList;
import java.util.List;

import static javax.persistence.CascadeType.*;
import static javax.persistence.EnumType.*;
import static lombok.AccessLevel.*;
import static project.word.test.domain.TestStatus.*;

@Entity
@Getter
@NoArgsConstructor(access = PROTECTED)
public class Test {
    @Id
    @GeneratedValue
    @Column(name = "test_id")
    private Long id;

    private String name;
    private int cutLine;

    @Enumerated(STRING)
    private TestStatus status;

    @Enumerated(STRING)
    private Difficulty difficulty;

    @Embedded
    private ExecutionDate executionDate;

    @OneToMany(mappedBy = "test", cascade = ALL)
    private List<WordTest> wordTests = new ArrayList<>();

    public Test(String name, Difficulty difficulty, int cutLine) {
        this.name = name;
        this.status = BEFORE_EXECUTION;
        this.difficulty = difficulty;
        this.cutLine = cutLine;
    }
    public Test(String name, TestStatus status, Difficulty difficulty, int cutLine) {
        this.name = name;
        this.status = status;
        this.difficulty = difficulty;
        this.cutLine = cutLine;
    }

    // 비즈니스 로직
    public void changeName(String name) {
        this.name = name;
    }
    public void changeCutLine(int cutLine) {
        this.cutLine = cutLine;
    }
    public void changeStatus(TestStatus status) {
        this.status = status;
    }
    public void changeDifficulty(Difficulty difficulty) {
        this.difficulty = difficulty;
    }

    public void setExecutionDate(ExecutionDate executionDate) {
        this.executionDate = executionDate;
    }
    public void setExecutionDate(int year, int month, int date) {
        this.executionDate = new ExecutionDate(year,month,date);
    }

    //*연관관계 편의 메서드*//
    public void addWordTest(WordTest wordTest) {
        wordTests.add(wordTest);
        wordTest.setTest(this);
    }
}
