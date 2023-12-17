package project.word.test.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

import static javax.persistence.CascadeType.*;
import static javax.persistence.FetchType.*;
import static lombok.AccessLevel.*;

@Entity
@Getter
@NoArgsConstructor(access = PROTECTED)
public class WordTest {
    @Id
    @GeneratedValue
    @Column(name = "word_test_id")
    private Long id;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "test_id")
    private Test test;

    @ManyToOne(fetch = LAZY, cascade = PERSIST)
    @JoinColumn(name = "word_list_id")
    private WordList wordList;

    public static WordTest createWordTest(String name, WordList wordList) {
        WordTest wordTest = new WordTest();
        wordTest.wordList = wordList;
        return wordTest;
    }

    protected void setTest(Test test) {
        this.test = test;
    }

    protected void setWordList(WordList wordList) {
        this.wordList = wordList;
    }
}
