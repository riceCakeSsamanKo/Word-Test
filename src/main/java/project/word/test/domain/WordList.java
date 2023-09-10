package project.word.test.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

import java.util.ArrayList;
import java.util.List;

import static javax.persistence.CascadeType.*;
import static javax.persistence.FetchType.*;
import static lombok.AccessLevel.*;

@Entity
@Getter @Setter
@NoArgsConstructor(access = PROTECTED)
public class WordList {
    @Id
    @GeneratedValue
    @Column(name = "word_list_id")
    private Long id;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "test_id")
    private Test test;

    @OneToMany(cascade = ALL, mappedBy = "wordList")
    private List<Word> words = new ArrayList<>();

    // 생성 메서드
    public static WordList createWordList(Word... words){
        WordList wordList = new WordList();
        for (Word word : words) {
            wordList.addWord(word);
        }

        return wordList;
    }

    // setter
    public void setTest(Test test) {
        this.test = test;
    }

    // 연관 관계 편의 메서드
    public void addWord(Word word) {
        words.add(word);
        word.setWordList(this);
    }
}
