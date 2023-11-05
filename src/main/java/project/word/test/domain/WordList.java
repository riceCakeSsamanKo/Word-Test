package project.word.test.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Entity
@Getter
@NoArgsConstructor
public class WordList {
    @Id
    @GeneratedValue
    @Column(name = "word_list_id")
    private Long id;

    private int numOfWords = 0;

    @OneToMany(mappedBy = "wordList", cascade = CascadeType.ALL)
    private List<Word> words = new ArrayList<>();

    //*연관 관계 편의 메서드*//
    public void addWord(Word word) {
        // 검사 로직
        Optional<Word> existingWord =
                words.stream()
                        .filter(w -> w.compareEnglish(word.getEnglish()))
                        .findFirst();

        // 동일한 word가 words 내부에 없는 경우 add
        if (existingWord.isEmpty()) {
            words.add(word);
            word.setWordList(this);
            numOfWords += 1;
        }
    }
}
