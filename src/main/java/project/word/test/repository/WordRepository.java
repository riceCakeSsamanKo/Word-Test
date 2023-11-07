package project.word.test.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import project.word.test.domain.Word;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

import static java.util.Optional.*;

@Repository
@RequiredArgsConstructor
public class WordRepository {
    private final EntityManager em;

    public void save(Word word) {
        em.persist(word);
    }

    public Optional<Word> findOne(Long wordId) {
        try {
            Word findWord = em.find(Word.class, wordId);
            return ofNullable(findWord);
        } catch (IllegalArgumentException e) {
            return empty();
        }
    }

    public List<Word> findAll() {
        return em.createQuery("select w from Word w " +
                        "join fetch w.wordList wl ", Word.class)
                .getResultList();
    }

    public List<Word> findByKorean(String korean) {
        return em.createQuery("select w from Word w " +
                        "join fetch w.wordList wl " +
                        "where w.korean = :korean", Word.class)
                .setParameter("korean", korean)
                .getResultList();
    }

    public List<Word> findByEnglish(String english) {
        return em.createQuery("select w from Word w " +
                        "join fetch w.wordList wl " +
                        "where w.korean = :english", Word.class)
                .setParameter("english", english)
                .getResultList();
    }
}
