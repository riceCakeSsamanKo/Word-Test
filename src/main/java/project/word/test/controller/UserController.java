package project.word.test.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import project.word.test.domain.AccountType;
import project.word.test.domain.Gender;
import project.word.test.domain.Group;
import project.word.test.domain.User;
import project.word.test.dto.EditUserDto;
import project.word.test.dto.UserForm;
import project.word.test.dto.loginDto;
import project.word.test.exception.AlreadyLogInException;
import project.word.test.exception.RequiredLogInException;
import project.word.test.service.GroupService;
import project.word.test.service.UserService;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import static project.word.test.domain.AccountType.*;
import static project.word.test.domain.Gender.*;

@Controller
@RequiredArgsConstructor
@Slf4j
public class UserController {
    private final UserService userService;
    private final GroupService groupService;

    @GetMapping("/user/join")
    public String joinUser(Model model, HttpSession session) throws AlreadyLogInException {
        log.info("join page");

        // 이미 로그인이 된 경우 예외 발생(회원 가입 불가)
        checkIsAlreadyLoggedIn(session, "UserController.joinUser()");

        model.addAttribute("form", new UserForm());

        return "user/join";
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
        return "/user/join";
    }

    @GetMapping("/user/login")
    public String logIn(Model model, HttpSession session) throws AlreadyLogInException {
        log.info("login page");

        // 이미 로그인되어 있는 경우 로그인 불가
        checkIsAlreadyLoggedIn(session, "UserController.logIn()");

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

    @RequestMapping("/user/logout")
    public String logout(HttpSession session) {
        log.info("log out");

        session.removeAttribute("loggedInUser");
        return "redirect:/";
    }

    @RequestMapping("/user")
    public String userPage(HttpSession session) throws RequiredLogInException {
        log.info("user page");

        // 로그인이 필요하지 체크
        checkIsLogInRequired(session, "UserController.userPage()");


        return "user/user";
    }

    @RequestMapping("/user/information")
    public String getUserInfo(Model model, HttpSession session) throws RequiredLogInException {
        log.info("user information");

        // 로그인이 필요하지 체크
        checkIsLogInRequired(session, "UserController.changeUserInfo()");

        User loggedInUser = (User) session.getAttribute("loggedInUser");
        User user = userService.findUser(loggedInUser.getId());

        model.addAttribute("user", user);
        return "user/information";
    }

    @GetMapping("/user/information/edit")
    public String changeUserInfo(Model model, HttpSession session) throws RequiredLogInException {
        log.info("edit user information");

        // 로그인이 필요하지 체크
        checkIsLogInRequired(session, "UserController.changeUserInfo()");

        model.addAttribute("form", new EditUserDto());
        return "user/editInformation";
    }

    @PostMapping("/user/information/edit")
    public String udpateUserInfoToDB(@Valid EditUserDto editUserDto, HttpSession session) {
        String pw = editUserDto.getLogin_pw();
        String name = editUserDto.getName();
        int age = editUserDto.getAge();
        Gender gender = editUserDto.getGender();
        Long groupId = editUserDto.getGroup_id();
        Group group = groupService.findGroup(groupId);

        User loggedInUser = (User) session.getAttribute("loggedInUser");

        userService.updateUserInfo(loggedInUser.getId(), pw, name, age, gender, group);

        // 세션 상에서 현재 로그인된 유저 정보 업데이트(DB는 위에서 이미 업데이트 됨)
        session.setAttribute("loggedInUser",(User)userService.findUser(loggedInUser.getId()));

        return "redirect:/user/information";
    }

    /**
     * 로그인 여부 확인 로직
     */
    // 로그인 확인 로직
    protected void checkIsLogInRequired(HttpSession session, String location) throws RequiredLogInException {

        User loggedInUser = (User) session.getAttribute("loggedInUser");

        // 로그인하지 않은 경우 로직 사용 불가능
        if (loggedInUser == null) {
            throw new RequiredLogInException("error\n" +
                    "내용: 로그인이 필요합니다\n" +
                    "발생지점:" + location);
        }
    }

    // 이미 로그인되어 있는지 확인하는 로직
    protected void checkIsAlreadyLoggedIn(HttpSession session, String location) throws AlreadyLogInException {
        User loggedInUser = (User) session.getAttribute("loggedInUser");

        // 로그인하지 않은 경우 로직 사용 불가능
        if (loggedInUser != null) {
            throw new AlreadyLogInException("error\n" +
                    "내용: 이미 로그인되어 있습니다\n" +
                    "발생지점:" + location);
        }
    }

}
