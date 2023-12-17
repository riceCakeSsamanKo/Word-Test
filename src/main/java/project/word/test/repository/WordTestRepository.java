package project.word.test.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import project.word.test.domain.WordTest;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

@Repository
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class WordTestRepository {
    EntityManager em;

    @Transactional(readOnly = false)
    public void save(WordTest wordTest) {
        em.persist(wordTest);
    }

    public WordTest findOne(Long wordTestId) {
        return em.find(WordTest.class, wordTestId);
    }

    public List<WordTest> findAll() {
        return em.createQuery("select wt from WordTest wt " +
                        "join fetch wt.test t", WordTest.class)
                .getResultList();
    }
}
