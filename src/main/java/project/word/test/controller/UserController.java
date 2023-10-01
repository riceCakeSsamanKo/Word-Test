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
import java.util.List;

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
    public String userInformation(HttpSession session,Model model) {
        User loggedInUser = (User) session.getAttribute("loggedInUser");
        model.addAttribute("user",loggedInUser);
        log.info("USER INFORMATION");

        return "user/info";
    }

    // 09-24 edit는 아직 미구현
    @GetMapping("/user/edit")
    public String editUserInfo(HttpSession session, Model model) {
        User loggedInUser = (User) session.getAttribute("loggedInUser");
        List<Group> groups = groupService.findGroups();
        model.addAttribute("user", loggedInUser);
        model.addAttribute("groups", groups);
        model.addAttribute("form", new UserEditDto());
        log.info("USER EDIT");

        return "user/edit";
    }

    @PostMapping("/user/edit")
    public String editUserInfoPost(HttpSession session,UserEditDto dto) {
        Long id = ((User) session.getAttribute("loggedInUser")).getId();
        User user = userService.findUser(id);
        Group group = groupService.findGroup(user.getGroup().getId());

        // group의 users 리스트에서 user 제거
        groupService.deleteUser(group,user);

        Group newGroup = groupService.findGroup(dto.getGroup_id());
        newGroup.addUser(user); //user의 group에 대한 연관관계 변경

        userService.updateUser(id,dto.getLogin_pw(),dto.getName(),dto.getAge());

        return "user/info";
    }
    @RequestMapping("/user/withdraw")
    public String userWithDraw() {

        log.info("USER WITHDRAW");

        return "user/withdraw";
    }

    @DeleteMapping("/user/withdraw")
    public String userWithDraw(HttpSession session){

        User loggedInUser = (User) session.getAttribute("loggedInUser");
        User user = userService.findUser(loggedInUser.getId());
        userService.delete(user);
        session.removeAttribute("loggedInUser");

        return "redirect:/";
    }
}
