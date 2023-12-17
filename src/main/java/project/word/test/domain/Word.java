package project.word.test.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import java.util.Objects;

import static javax.persistence.FetchType.*;
import static lombok.AccessLevel.*;

@Entity
@Getter
@NoArgsConstructor(access = PROTECTED)
public class Word {
    @Id @GeneratedValue
    @Column(name = "word_id")
    private Long id;

    private String english;
    private String korean;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "word_list_id")
    private WordList wordList;

    public Word(String korean, String english) {
        this.korean = korean;
        this.english = english;
    }

    public void changeInfo(String korean, String english) {
        this.korean = korean;
        this.english = english;
    }

    protected void setWordList(WordList wordList) {
        this.wordList = wordList;
    }

    //비즈니스 로직
    public boolean compareKorean(String korean) {
        return Objects.equals(this.korean, korean);
    }
    public boolean compareEnglish(String english) {
        return Objects.equals(this.english, english);
    }
}
