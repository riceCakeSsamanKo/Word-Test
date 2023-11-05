package project.word.test.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Embeddable;

import static lombok.AccessLevel.*;

@Embeddable
@Getter @Setter
@NoArgsConstructor(access = PROTECTED)
public class ExecutionDate {
    int year;
    int month;
    int date;

    public ExecutionDate(int year, int month, int date) {
        this.year = year;
        this.month = month;
        this.date = date;
    }
}
