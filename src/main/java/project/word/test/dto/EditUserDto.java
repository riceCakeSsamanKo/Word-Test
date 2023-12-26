package project.word.test.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import project.word.test.domain.Gender;
import project.word.test.domain.Group;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EditUserDto {
    String login_pw;
    String name;
    int age;
    Gender gender;
    Long group_id; // group fk
}
