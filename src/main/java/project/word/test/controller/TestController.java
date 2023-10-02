package project.word.test.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import project.word.test.domain.Test;
import project.word.test.dto.SelectTestDto;
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

    @RequestMapping("/test/select")
    public String selectTest() {
        return "test/selectTest";
    }

    @GetMapping("/test/select/korean")
    public String selectTest(Model model) {
        List<Test> tests = testService.findtests();
        for (Test test : tests) {
            System.out.println("test = " + test.getName());
        }
        model.addAttribute("selectTest", new SelectTestDto());
        model.addAttribute("tests", tests);
        return "test/selectKoreanTest";
    }

    @PostMapping("/test/select/korean")
    public String selectTestPost(SelectTestDto selectTestDto, Model model) {
        Long testId = selectTestDto.getTestId();
        Test findtest = testService.findtest(testId);
        model.addAttribute("test", findtest);

        return "test/koreanTest";
    }

    /*@GetMapping("/test/select/korean")
    public String koreanTest(@PathVariable String name,Model model) {
        Optional<Test> findtest = testService.findtest(name);
        if (findtest.isEmpty()) {
            log.error("error! there is no test which has those name");
        }
        Test test = findtest.get();
        model.addAttribute("test", test);

        return "test/koreanTest";
    }*/

}
