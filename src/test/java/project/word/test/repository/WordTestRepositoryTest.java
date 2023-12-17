package project.word.test.repository;

import org.junit.jupiter.api.Assertions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import project.word.test.domain.Difficulty;
import project.word.test.domain.Test;
import project.word.test.domain.WordList;
import project.word.test.domain.WordTest;

import javax.persistence.EntityManager;

import static org.junit.jupiter.api.Assertions.*;
import static project.word.test.domain.Difficulty.*;

@SpringBootTest
@Transactional
class WordTestRepositoryTest {
    @Autowired
    WordTestRepository wordTestRepository;
    @Autowired
    TestRepository testRepository;
    @Autowired
    WordListRepository wordListRepository;
    @Autowired
    EntityManager em;

    @org.junit.jupiter.api.Test
    public void 제거() throws Exception{
        //given
        WordList wordList = new WordList();
        WordTest wordTest = WordTest.createWordTest("wt", wordList);

        Test test = new Test("test", EASY, 80);
        test.addWordTest(wordTest);
        testRepository.save(test);

        //when
        wordTestRepository.removeWordTest(wordTest);
        
        //then
        WordTest find = wordTestRepository.findOne(wordTest.getId());
        assertEquals(find,null);
    }
}