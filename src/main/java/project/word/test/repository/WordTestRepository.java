package project.word.test.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import project.word.test.domain.WordTest;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class WordTestRepository {
    EntityManager em;

    public void save(WordTest wordTest) {
        em.persist(wordTest);
    }

    public WordTest findOne(Long wordTestId) {
        return em.find(WordTest.class, wordTestId);
    }

    public Optional<WordTest> findByName(String name) {
        List<WordTest> findWordTest =
                em.createQuery("select wt from WordTest wt " +
                                "left join fetch wt.test t " +
                                "where wt.", WordTest.class)
                .getResultList();

        return findWordTest.stream().findAny();
    }
}
