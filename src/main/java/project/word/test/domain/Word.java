package project.word.test.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import static javax.persistence.EnumType.*;

@Entity
@Getter
@NoArgsConstructor
public class Word {

    @Id
    @GeneratedValue
    private Long id;

    private String foreign;
    private String korean;
    
    @Enumerated(STRING)
    private Difficulty difficulty;

    // setter
    private void setForeign(String foreign) {
        this.foreign = foreign;
    }
    private void setKorean(String korean) {
        this.korean = korean;
    }
    private void setDifficulty(Difficulty difficulty) {this.difficulty = difficulty;}

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
