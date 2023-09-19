package project.word.test.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.HttpSessionRequiredException;
import org.springframework.web.bind.annotation.RequestMapping;
import project.word.test.domain.AccountType;
import project.word.test.domain.User;

import javax.servlet.http.HttpSession;

import static project.word.test.domain.AccountType.*;

@Controller
@RequiredArgsConstructor
@Slf4j
public class HomeController {
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
        return "home";
    }
}
