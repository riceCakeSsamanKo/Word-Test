package project.word.test.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import project.word.test.domain.WordList;
import project.word.test.repository.WordListRepository;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class WordListService {
    private final WordListRepository wordListRepository;

    public void register(WordList wordList) {
        Optional<WordList> wordListFindByName =
                wordListRepository.findByName(wordList.getName());
        if (wordListFindByName.isPresent()) {
            throw new IllegalStateException("error\n" +
                    "내용: 이미 등록된 단어장 이름입니다\n" +
                    "발생지점: WordListService.register()");
        }
        wordListRepository.save(wordList);
    }

    public WordList findWordList(Long wordListId) {
        return wordListRepository.findOne(wordListId);
    }

    public WordList findWordList(String name) {
        Optional<WordList> wordListFindByName =
                wordListRepository.findByName(name);
        if (wordListFindByName.isEmpty()) {
            throw new IllegalStateException("error\n" +
                    "내용: 존재하지 않는 단어장입니다\n" +
                    "발생지점: WordListService.findWordList(String name)");
        }

        return wordListFindByName.get();
    }
    public List<WordList> findWordLists() {
        return wordListRepository.findAll();
    }

}
