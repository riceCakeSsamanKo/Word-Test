package project.word.test.domain;

import lombok.Getter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

import static javax.persistence.CascadeType.*;
import static javax.persistence.EnumType.*;

@Entity
@Getter
public class Test {
    @Id
    @GeneratedValue
    private Long id;

    private int score;
    private LocalDateTime registrationTime;
    private String name;

    @Enumerated(STRING)
    private TestStatus testStatus;

    @OneToMany(cascade = ALL)
    private List<TestUser> testUsers;

    @OneToMany(cascade = ALL)
    private List<TestWord> testWords;
}
