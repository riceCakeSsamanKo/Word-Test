package project.word.test;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import project.word.test.domain.Word;
import project.word.test.domain.WordList;

public class WordListTest {
    @Test
    public void addWord() throws Exception{
        WordList wordList = new WordList();
        wordList.addWord(new Word("닭", "Chicken"));
        wordList.addWord(new Word("닭", "Chicken"));
        System.out.println("NumOfWords = " + wordList.getNumOfWords());
        Assertions.assertEquals(wordList.getNumOfWords(),1);

        wordList.addWord(new Word("고기", "meat"));
        Assertions.assertEquals(wordList.getNumOfWords(),2);
        System.out.println("NumOfWords = " + wordList.getNumOfWords());
    }
}
