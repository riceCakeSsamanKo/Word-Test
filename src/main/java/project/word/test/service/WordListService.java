package project.word.test.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import project.word.test.domain.Test;
import project.word.test.domain.Word;
import project.word.test.domain.WordList;
import project.word.test.repository.WordListRepository;

import java.util.List;


@Service
@Transactional
@RequiredArgsConstructor
public class WordListService {

    private final WordListRepository wordListRepository;

    public void addWordList(WordList wordList) {
        wordListRepository.save(wordList);
    }

    @Transactional(readOnly = true)
    public WordList findWordList(Long wordListId) {
        return wordListRepository.find(wordListId);
    }

    @Transactional(readOnly = true)
    public List<WordList> findWordLists() {
        return wordListRepository.findAll();
    }

    @Transactional(readOnly = true)
    public List<WordList> findWordLists(Word word) {
        return wordListRepository.findByWord(word);
    }
}
