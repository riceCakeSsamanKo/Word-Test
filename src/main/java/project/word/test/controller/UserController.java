package project.word.test.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import project.word.test.domain.Group;
import project.word.test.domain.User;
import project.word.test.dto.UserEditDto;
import project.word.test.dto.UserForm;
import project.word.test.service.GroupService;
import project.word.test.service.UserService;

import javax.servlet.http.HttpSession;
import java.util.Objects;

@Controller
@RequiredArgsConstructor
@Slf4j
public class UserController {

    private final UserService userService;
    private final GroupService groupService;

    @RequestMapping("/user")
    public String userTotal(HttpSession session) {
        if (session.getAttribute("loggedInUser") == null) {
            return "redirect:/login";
        }
        log.info("USER PAGE");
        return "user/total";
    }

    @RequestMapping("/user/info")
    public String userInformation(HttpSession session, Model model) {
        User loggedInUser = (User) session.getAttribute("loggedInUser");
        User user = userService.findUser(loggedInUser.getId());
        model.addAttribute("user", user);
        log.info("USER INFORMATION");

        return "user/info";
    }

    @GetMapping("/user/info/edit")
    public String editUserInfo(HttpSession session, Model model) {
        User loggedInUser = (User) session.getAttribute("loggedInUser");
        model.addAttribute("user", loggedInUser);
        model.addAttribute("form", new UserEditDto());
        model.addAttribute("groups", groupService.findGroups());
        log.info("USER EDIT");

        return "user/edit";
    }

    @PostMapping("/user/info/edit")
    public String editUserInfoPost(HttpSession session, UserEditDto dto) {
        Long id = ((User) session.getAttribute("loggedInUser")).getId();
        User user = userService.findUser(id);
        // 기존 유저의 그룹
        Group group = groupService.findGroup(user.getGroup().getId());  // group이 null인 경우 처리 필요(NullPointerException 발생)

        // dto로 받아온 그룹
        Group newGroup = groupService.findGroup(dto.getGroup_id());
        // group의 users 리스트에서 user 제거
        groupService.deleteUser(group, user);
        newGroup.addUser(user); //user의 group에 대한 연관관계 변경

        // 입력이 된 값만 변경함. 입력되지 않은 값의 경우 기존의 값을 유지함(문자열 비교는 equals 메서드를 이용해야 한다.)
        String newPassword = (!Objects.equals(dto.getLogin_pw(), "")) ? dto.getLogin_pw() : user.getLogIn().getLogin_password();
        String newName = (!Objects.equals(dto.getName(), "")) ? dto.getName() : user.getName();
        int newAge = (dto.getAge() != 0) ? dto.getAge() : user.getAge();

        userService.updateUser(id, newPassword, newName, newAge);

        return "redirect:/user/info";
    }

    @RequestMapping("/user/withdraw")
    public String userWithDraw() {

        log.info("USER WITHDRAW");

        return "user/withdraw";
    }

    @DeleteMapping("/user/withdraw")
    public String userWithDraw(HttpSession session) {

        User loggedInUser = (User) session.getAttribute("loggedInUser");
        User user = userService.findUser(loggedInUser.getId());
        userService.delete(user);
        session.removeAttribute("loggedInUser");

        return "redirect:/";
    }
}
