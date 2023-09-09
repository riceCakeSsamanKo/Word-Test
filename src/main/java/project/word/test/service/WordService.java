package project.word.test.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import project.word.test.domain.Difficulty;
import project.word.test.domain.Word;
import project.word.test.repository.WordRepository;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class WordService {

    private final WordRepository wordRepository;

    public void join(Word word){
        wordRepository.save(word);
    }
    @Transactional(readOnly = true)
    public Word findword(Long wordId) {
        return wordRepository.find(wordId);
    }
    @Transactional(readOnly = true)
    public List<Word> findwords(Difficulty difficulty) {
        return wordRepository.findByDifficulty(difficulty);
    }
    @Transactional(readOnly = true)
    public List<Word> findwords() {
        return wordRepository.findAll();
    }

}
