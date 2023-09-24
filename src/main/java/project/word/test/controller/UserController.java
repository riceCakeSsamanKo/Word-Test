package project.word.test.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import project.word.test.domain.User;
import project.word.test.dto.UserForm;
import project.word.test.service.GroupService;
import project.word.test.service.UserService;

import javax.servlet.http.HttpSession;

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
        model.addAttribute("user", loggedInUser);
        model.addAttribute("form", new UserForm());
        log.info("USER EDIT");

        return "user/infofasd";
    }

    @RequestMapping("/user/withdraw")
    public String userWithDraw(){
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
