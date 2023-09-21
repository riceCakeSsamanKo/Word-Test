package project.word.test.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import project.word.test.domain.AccountType;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
public class UserForm {
    // pk
    private Long id;

    // login
    private String login_id;
    @NotEmpty(message = "비밀번호는 필수입니다")
    private String login_pw;

    // user field
    @NotEmpty(message = "이름은 필수입니다")
    private String name;
    private int age;

    //group
    private Long groupId;
}
