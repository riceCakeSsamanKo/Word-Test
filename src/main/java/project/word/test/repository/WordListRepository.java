package project.word.test.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import project.word.test.domain.WordList;

import javax.persistence.EntityManager;
import java.util.Optional;


@Repository
@RequiredArgsConstructor
public class WordListRepository {
    private final EntityManager em;

    public void save(WordList wordList) {
        em.persist(wordList);
    }

    public Optional<WordList> findOne(Long id) {
        
    }
}
