package project.word.test.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Embeddable;

import static lombok.AccessLevel.*;

@Embeddable
@NoArgsConstructor(access = PROTECTED)
@Getter
public class LogInInformation {
    private String id;
    private String password;

    public LogInInformation(String id, String password){
        this.id = id;
        this.password = password;
    }

    public void setPassword(String password){
        this.password = password;
    }
}
