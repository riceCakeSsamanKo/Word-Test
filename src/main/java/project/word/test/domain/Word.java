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
}
