package project.word.test.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import project.word.test.domain.Difficulty;
import project.word.test.domain.Word;
import project.word.test.repository.WordRepository;

import javax.persistence.TransactionRequiredException;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class WordService {

    private final WordRepository wordRepository;

    public void addWord(Word word) {
        wordRepository.save(word);
    }

    @Transactional(readOnly = true)
    public Word findWord(Long wordId) {
        return wordRepository.find(wordId);
    }

    @Transactional(readOnly = true)
    public List<Word> findWords() {
        return wordRepository.findAll();
    }

    @Transactional(readOnly = true)
    public List<Word> findWords(Difficulty difficulty) {
        return wordRepository.findByDifficulty(difficulty);
    }

    public boolean deleteWord(Word word) {
        try {
            wordRepository.remove(word);
        } catch (IllegalArgumentException e) {
            log.info("IllegalArgumentException!" +
                    "where: wordService.deleteWord" +
                    "why: there is no entity or detached");
            return false;
        } catch (TransactionRequiredException e) {
            log.info("TransactionRequiredException!" +
                    "where: wordService.deleteWord" +
                    "why: there is no transaction");
            return true;
        }
        return true;
    }
}
