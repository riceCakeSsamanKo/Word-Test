package project.word.test.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

import static javax.persistence.FetchType.*;

@Entity
@Getter @Setter
public class TestWord {
    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "test_id")
    private Test test;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "word_id")
    private Word word;

    // setter
    public void setTest(Test test) {
        this.test = test;
    }
    public void setWord(Word word) {
        this.word = word;
    }
}
