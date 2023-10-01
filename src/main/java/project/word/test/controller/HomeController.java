package project.word.test.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import project.word.test.domain.Group;
import project.word.test.domain.User;
import project.word.test.dto.UserForm;
import project.word.test.dto.UserLogInDto;
import project.word.test.service.GroupService;
import project.word.test.service.UserService;

import javax.servlet.http.HttpSession;

import java.util.List;
import java.util.Optional;

import static project.word.test.domain.AccountType.*;

@Controller
@RequiredArgsConstructor
@Slf4j
public class HomeController {

    private final UserService userService;
    private final GroupService groupService;

    @RequestMapping("/")
    public String home(HttpSession session, Model model) {

        boolean loggedIn = session.getAttribute("loggedInUser") != null;
        model.addAttribute("loggedIn", loggedIn);

        if (loggedIn) {
            User loggedInUser = (User) (session.getAttribute("loggedInUser"));
            boolean isAdmin = loggedInUser.getAccountType() == ADMIN;
            model.addAttribute("isAdmin", isAdmin);
        }

        log.info("HOME");
        return "main/home";
    }

    @GetMapping("/new")
    public String createUserForm(Model model) {
        model.addAttribute("form", new UserForm());
        List<Group> groups = groupService.findGroups();
        model.addAttribute("groups", groups);

        log.info("JOIN MEMBERSHIP");
        return "main/createUserForm";
    }

    @PostMapping("/new")
    public String createUser(UserForm form, Model model) {
        // 조회한 그룹
        List<Group> groups = groupService.findGroups();

        // 입력되지 않은 값이 있는지 체크
        if (form.getName().isEmpty() || form.getLogin_id().isEmpty() || form.getLogin_pw().isEmpty()) {
            model.addAttribute("form", new UserForm());
            model.addAttribute("groups", groups);
            model.addAttribute("isBlankedValueExist", true);

            return "main/createUserForm";
        }
        // 동일한 아이디로 회원 가입 된 유저인 경우, 가입 불가
        Optional<User> findUser = userService.findUserByLoginId(form.getLogin_id());
        if (findUser.isPresent()) {
            model.addAttribute("form", new UserForm());
            model.addAttribute("groups", groups);
            model.addAttribute("isAlreadyExist", true);

            return "main/createUserForm";
        }

        User user = new User(form.getName(), form.getAge(), form.getLogin_id(), form.getLogin_pw());
        Group group = groupService.findGroup(form.getGroupId());
        group.addUser(user);

        userService.join(user);

        return "redirect:/";
    }

    @GetMapping("/login")
    public String login(Model model) {
        model.addAttribute("form", new UserLogInDto());
        log.info("LOGIN");

        return "main/loginPage";
    }

    @PostMapping("/login")
    public String login(Model model, UserLogInDto logInDto, HttpSession session, BindingResult bindingResult) {
        // id와 pw로 로그인한 유저 조회
        Optional<User> loggedInUserFindById =
                userService.findUser(logInDto.getLogin_id(), logInDto.getLogin_pw());

        boolean noMemberExist = loggedInUserFindById.isEmpty();
        // 해당 id와 pw로 가입된 유저가 없는 경우

        if (logInDto.getLogin_id().isEmpty() || logInDto.getLogin_pw().isEmpty()) {
            model.addAttribute("form", new UserLogInDto());
            model.addAttribute("isBlankedValueExist", true);
            return "main/loginPage";
        } else if (noMemberExist) {
            model.addAttribute("form", new UserLogInDto());
            model.addAttribute("noMemberExist", true);
            // 경고 발생과 함께 다시 로그인 페이지로 리다이렉트
            return "main/loginPage";
        } else { // 가입된 유저가 존재하는 경우
            User loggedInUser = loggedInUserFindById.get();
            session.setAttribute("loggedInUser", loggedInUser);  // 로그인한 유저 정보 저장

            if (loggedInUser.getAccountType() == ADMIN) {
                return "redirect:/admin";
            }
            return "redirect:/";
        }
    }

    @RequestMapping("/logout")
    public String logout(HttpSession session) {
        session.removeAttribute("loggedInUser");
        log.info("LOGOUT");

        return "redirect:/";
    }

    @RequestMapping("/admin")
    public String adminPage() {
        return "admin/home";
    }
}
