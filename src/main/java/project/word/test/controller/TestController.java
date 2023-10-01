package project.word.test.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import project.word.test.domain.Test;
import project.word.test.service.TestService;
import project.word.test.service.WordListService;
import project.word.test.service.WordService;

import java.util.List;
import java.util.Optional;

@Controller
@Slf4j
@RequiredArgsConstructor
public class TestController {

    private final WordService wordService;
    private final WordListService wordListService;
    private final TestService testService;

    @RequestMapping("/test")
    public String testMain() {
        return "test/total";
    }

    @GetMapping("/test/select")
    public String selectTest(Model model) {
        List<Test> tests = testService.findtests();
        model.addAttribute("selectTest", new String());
        model.addAttribute("tests", tests);
        return "test/selectTest";
    }

    @GetMapping("/test/select/{name}/korean")
    public String koreanTest(@PathVariable String name,Model model) {
        Optional<Test> findtest = testService.findtest(name);
        if (findtest.isEmpty()) {
            log.error("error! there is no test which has those name");
        }
        Test test = findtest.get();
        model.addAttribute("test", test);

        return "test/koreanTest";
    }

}
