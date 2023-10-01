package project.word.test.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import project.word.test.service.WordService;

@Controller
@Slf4j
@RequiredArgsConstructor
public class WordController {

    private final WordService wordService;

}
