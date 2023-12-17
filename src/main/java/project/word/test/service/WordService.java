package project.word.test.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import project.word.test.domain.Word;
import project.word.test.repository.WordRepository;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class WordService {
    private final WordRepository wordRepository;

    @Transactional(readOnly = false)
    public void register(Word word) {
        Optional<Word> wordFindByEnglish = wordRepository.findByEnglish(word.getEnglish());
        if (wordFindByEnglish.isPresent()) {
            throw new IllegalStateException("error\n" +
                    "내용: 이미 등록된 영단어입니다\n" +
                    "발생지점: WordService.register()");
        }
        wordRepository.save(word);
    }

    public Word findWord(Long wordId) {
        return wordRepository.findOne(wordId);
    }

    public Word findWord(String english) {
        Optional<Word> wordFindByEnglish = wordRepository.findByEnglish(english);
        if (wordFindByEnglish.isEmpty()) {
            throw new IllegalStateException("error\n" +
                    "내용: 해당 영단어는 존재하지 않습니다\n" +
                    "발생지점: WordService.findWord()");
        }
        return wordFindByEnglish.get();
    }

    public List<Word> findWords() {
        return wordRepository.findAll();
    }

    public List<Word> findWords(String korean) {
        return wordRepository.findByKorean(korean);
    }
}
