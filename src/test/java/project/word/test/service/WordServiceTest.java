package project.word.test.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import project.word.test.domain.Word;

import java.util.List;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class WordServiceTest {

    @Autowired
    WordService wordService;

    @Test
    public void  동일_영단어_등록() throws Exception {
        //given
        Word word1 = new Word("때리다", "hit");
        Word word2 = new Word("때리다", "punch");
        Word word3 = new Word("치다", "hit");

        //when
        wordService.register(word1);

        //then
        assertThrows(IllegalStateException.class, () -> wordService.register(word3));
    }

}