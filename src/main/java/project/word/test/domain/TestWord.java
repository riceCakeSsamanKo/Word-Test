package project.word.test.domain;

import lombok.Getter;

import javax.persistence.*;

import static javax.persistence.FetchType.*;

@Entity
@Getter
public class TestWord {
    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne(fetch = LAZY)
    private Test test;

    @ManyToOne(fetch = LAZY)
    private Word word;
}
