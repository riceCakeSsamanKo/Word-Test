package project.word.test.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import project.word.test.domain.Difficulty;
import project.word.test.domain.Word;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class WordRepository {

    private final EntityManager em;

    public void save(Word word){
        em.persist(word);
    }
    public Word find(Long wordId) {
        return em.find(Word.class,wordId);
    }
    public List<Word> findAll(){
        return em.createQuery("select w from Word w", Word.class)
                .getResultList();
    }
    public List<Word> findByDifficulty(Difficulty difficulty) {
        return em.createQuery("select w from Word w where w.difficulty = :difficulty", Word.class)
                .setParameter("difficulty", difficulty)
                .getResultList();
    }
}
