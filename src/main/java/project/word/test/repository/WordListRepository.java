package project.word.test.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import project.word.test.domain.WordList;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import java.util.Optional;


@Repository
@RequiredArgsConstructor
public class WordListRepository {
    private final EntityManager em;

    public void save(WordList wordList) {
        em.persist(wordList);
    }

    public WordList findOne(Long wordListId) {
        return em.find(WordList.class, wordListId);
    }
}
