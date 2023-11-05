package project.word.test.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;

import static lombok.AccessLevel.*;

@Embeddable
@Getter
@NoArgsConstructor(access = PROTECTED)
public class LogIn {
    private String id;
    private String pw;

    public LogIn(String id, String pw){
        this.id = id;
        this.pw = pw;
    }

    public void changePassword(String pw){
        this.pw = pw;
    }
}
