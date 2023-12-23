package project.word.test.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
@Builder
@NoArgsConstructor @AllArgsConstructor
public class UserForm {
    @NotNull
    @NotEmpty
    String login_id;

    @NotNull
    @NotEmpty
    String login_pw;
}

