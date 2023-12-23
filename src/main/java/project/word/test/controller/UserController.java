package project.word.test.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import project.word.test.domain.User;
import project.word.test.dto.UserForm;
import project.word.test.service.UserService;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@Controller
@RequiredArgsConstructor
@Slf4j
public class UserController {
    private final UserService userService;

    @GetMapping("/user/login")
    public String logIn(Model model, HttpSession session) {
        log.info("login page");

        User loggedInUser = (User) session.getAttribute("loggedInUser");
        if (loggedInUser != null) {
            throw new IllegalStateException("error\n" +
                    "내용: 이미 로그인되어 있습니다\n" +
                    "발생지점: UserController.login()");
        }
        model.addAttribute("form", new UserForm());

        return "user/login";
    }

    @PostMapping("/user/login")
    public String getUser(@Valid UserForm userForm, HttpSession session, Model model, BindingResult bindingResult) {
        User user = userService.findUser(userForm.getLogin_id(), userForm.getLogin_pw());
        session.setAttribute("loggedInUser",user);
        // 아이디 없을 때 로직 추가 필요
        return "redirect:/";
    }
}
