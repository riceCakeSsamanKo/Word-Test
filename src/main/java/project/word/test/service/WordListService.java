package project.word.test.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import project.word.test.repository.WordListRepository;

import javax.transaction.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class WordListService {

    private final WordListRepository wordListRepository;

    
}
