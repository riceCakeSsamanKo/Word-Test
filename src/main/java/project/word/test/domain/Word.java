package project.word.test.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import static javax.persistence.EnumType.*;
import static javax.persistence.FetchType.*;
import static lombok.AccessLevel.*;

@Entity
@Getter
@NoArgsConstructor(access = PROTECTED)
public class Word {

    @Id
    @GeneratedValue
    private Long id;

    private String foreign;
    private String korean;
    
    @Enumerated(STRING)
    private Difficulty difficulty;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "word_list_id")
    private WordList wordList;

    public Word(String foreign, String korean, Difficulty difficulty) {
        setForeign(foreign);
        setKorean(korean);
        setDifficulty(difficulty);
    }
    // setter
    private void setForeign(String foreign) {
        this.foreign = foreign;
    }
    private void setKorean(String korean) {
        this.korean = korean;
    }
    private void setDifficulty(Difficulty difficulty) {this.difficulty = difficulty;}
    void setWordList(WordList wordList) {
        this.wordList = wordList;
    } // default 선언

    public void changeWord(String foreign, String korean) {
        setForeign(foreign);
        setKorean(korean);
    }
    public void changeWord(String foreign, String korean,Difficulty difficulty) {
        setForeign(foreign);
        setKorean(korean);
        setDifficulty(difficulty);
    }
}
