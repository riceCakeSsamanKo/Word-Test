package project.word.test.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import project.word.test.domain.Test;
import project.word.test.domain.Word;
import project.word.test.domain.WordList;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class WordListRepository {

    private final EntityManager em;

    public void save(WordList wordList) {
        em.persist(wordList);
    }

    public WordList find(Long wordListId) {
        return em.find(WordList.class, wordListId);
    }

    public List<WordList> findAll() {
        return em.createQuery("select w from WordList w " +
                        "join fetch w.test", WordList.class)
                .getResultList();
    }

    public List<WordList> findByTest(Test test) {
        return em.createQuery("select w from WordList w " +
                        "join fetch w.test t " +
                        "where t.id =: testId", WordList.class)
                .setParameter("testId", test.getId())
                .getResultList();
    }

    public List<WordList> findByWord(Word word) {
        return em.createQuery("select distinct wl from WordList wl " +
                        "join fetch wl.test t " +
                        "join fetch wl.words w " +
                        "where w in :word", WordList.class)
                .setParameter("word", word)
                .getResultList();
    }
}
