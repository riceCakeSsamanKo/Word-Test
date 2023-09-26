package project.word.test.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UserEditDto {

    //login
    private String login_pw;
    //data

    private String name;
    private int age;

    //group
    private Long group_id;
}
