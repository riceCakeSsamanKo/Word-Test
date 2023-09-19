package project.word.test.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import project.word.test.domain.Group;
import project.word.test.domain.LogInInformation;
import project.word.test.domain.User;
import project.word.test.dto.UserForm;
import project.word.test.service.GroupService;
import project.word.test.service.UserService;

import java.util.List;

@Controller
@RequiredArgsConstructor
@Slf4j
public class UserContoller {

    private final UserService userService;
    private final GroupService groupService;

    @GetMapping("/new")
    public String createUserForm(Model model) {
        model.addAttribute("form", new UserForm());
        List<Group> groups = groupService.findGroups();
        model.addAttribute("groups", groups);

        return "createUserForm";
    }

    @PostMapping("/new")
    public String createUser(UserForm form) {
        User user = new User(form.getName(), form.getAge(), form.getLogin_id(), form.getLogin_pw());
        userService.join(user);

        return "redirect:/";
    }

    @GetMapping("/login")
    public String login() {

        return null;
    }

    @GetMapping("/user")
    public String userInfo() {
        return null;
    }
}
