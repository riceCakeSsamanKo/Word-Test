package project.word.test.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import project.word.test.domain.WordTest;

import javax.persistence.EntityManager;
import javax.swing.text.html.parser.Entity;

@Repository
@Transactional
@RequiredArgsConstructor
public class WordTestRepository {
    private final EntityManager em;

    public WordTest findOne(Long wordTestId) {
        return em.find(WordTest.class, wordTestId);
    }

    public void removeWordTest(WordTest wordTest) {
        em.remove(wordTest);
    }
}
