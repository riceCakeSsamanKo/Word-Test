package project.word.test.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import project.word.test.domain.AccountType;
import project.word.test.domain.Group;
import project.word.test.domain.LogInInformation;
import project.word.test.domain.User;
import project.word.test.dto.UserForm;
import project.word.test.service.GroupService;
import project.word.test.service.UserService;

import javax.servlet.http.HttpSession;
import java.util.List;

import static project.word.test.domain.AccountType.*;

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
    public String login(Model model) {
        model.addAttribute("form", new UserForm());
        return "loginPage";
    }

    @PostMapping("/login")
    public String login(Model model, UserForm userForm, HttpSession session) {
        User loggedInUser = userService.findUser(userForm.getId());
        if (loggedInUser != null) {
            session.setAttribute("loggedInUser",loggedInUser);
            model.addAttribute("loggedIn", true);
            if(loggedInUser.getAccountType() == ADMIN){
                model.addAttribute("isAdmin", true);
            }
            return "home";
        } else{
            // 없는 아이디이거나 비밀번호입니다 경고 발생
            model.addAttribute("noMemberExist", true);
            return "loginPage";
        }
    }

    @RequestMapping("/user")
    public String userInfo(HttpSession session,Model model) {
        User loggedInUser = (User) session.getAttribute("loggedInUser");
        model.addAttribute("user", loggedInUser);

        return "userInfo";
    }

    @GetMapping("/user/edit")
    public String editUserInfo(HttpSession session, Model model) {
        User loggedInUser = (User) session.getAttribute("loggedInUser");
        model.addAttribute("user", loggedInUser);
        model.addAttribute("form", new UserForm());

        return "editUserInfo";
    }
}
