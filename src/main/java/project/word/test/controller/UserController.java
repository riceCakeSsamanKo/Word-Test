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
import project.word.test.dto.loginDto;
import project.word.test.service.UserService;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@Controller
@RequiredArgsConstructor
@Slf4j
public class UserController {
    private final UserService userService;

    @GetMapping("/user/join")
    public String joinUser(Model model, HttpSession session) {
        log.info("join page");

        User loggedInUser = (User) session.getAttribute("loggedInUser");
        if (loggedInUser != null) {
            throw new IllegalStateException("error\n" +
                    "내용: 이미 로그인되어 있습니다\n" +
                    "발생지점: UserController.joinUser()");
        }

        model.addAttribute("form", new UserForm());

        return "user/joinUser";
    }

    @PostMapping("/user/join")
    public String registerUserToDB(@Valid UserForm form, Model model) {
        try {
            User findUser = userService.findUser(form.getLogin_id());
        } catch (IllegalStateException e) {
            User user =
                    new User(form.getName(), form.getAge(), form.getGender(), form.getLogin_id(), form.getLogin_pw(), null);
            userService.join(user);
            return "redirect:/";
        }

        //이미 동일한 아이디로 가입된 경우
        model.addAttribute("form", new UserForm());
        model.addAttribute("isIdAlreadyExist", true);
        return "user/joinUser";
    }

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
    public String getUser(@Valid loginDto form, HttpSession session, Model model, BindingResult bindingResult) {
        try {
            User user = userService.findUser(form.getLogin_id(), form.getLogin_pw());
            session.setAttribute("loggedInUser", user);
        } catch (IllegalStateException e) {
            System.out.println("error = " + e);
            model.addAttribute("form", new loginDto());
            model.addAttribute("doesNotExist", true);
            return "user/login";
        }
        return "redirect:/";
    }
}
