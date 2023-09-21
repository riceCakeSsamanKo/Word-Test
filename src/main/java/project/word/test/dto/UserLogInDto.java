package project.word.test.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;

@Data
@NoArgsConstructor
// login 전용 DTO
public class UserLogInDto {
    @NotEmpty(message = "아이디는 필수입니다")
    private String login_id;
    @NotEmpty(message = "비밀번호는 필수입니다")
    private String login_pw;
}
