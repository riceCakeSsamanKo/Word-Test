package project.word.test.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import project.word.test.domain.AccountType;

@Data
@NoArgsConstructor
public class UserForm {
    // pk
    private Long id;
    // user field
    private String name;
    private int age;
    // login
    private String login_id;
    private String login_pw;
    //group
    private Long groupId;
}
