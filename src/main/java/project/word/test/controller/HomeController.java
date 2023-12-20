package project.word.test.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import project.word.test.domain.AccountType;
import project.word.test.domain.User;

import javax.servlet.http.HttpSession;

import static project.word.test.domain.AccountType.*;

@Controller
@Slf4j
public class HomeController {
    @GetMapping("/")
    public String home(Model model, HttpSession session) {
        log.info("home page");

        User loggedInUser = (User) session.getAttribute("loggedInUser");
        boolean logIn = (loggedInUser != null);
        if (logIn) {
            boolean isAdmin = loggedInUser.getAccountType() == ADMIN;
            model.addAttribute("isAdmin", isAdmin);
        }

        model.addAttribute("logIn", logIn);
        model.addAttribute("user", loggedInUser);

        return "home/home";
    }
}
