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
    private String login_id;
    private String login_password;

    public LogInInformation(String id, String password){
        this.login_id = id;
        this.login_password = password;
    }

    public void setPassword(String password){
        this.login_password = password;
    }
}

