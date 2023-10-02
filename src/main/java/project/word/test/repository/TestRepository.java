package project.word.test.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import project.word.test.domain.Test;
import project.word.test.domain.TestStatus;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class TestRepository {
    private final EntityManager em;

    public void save(Test test){
        em.persist(test);
    }
    public Test find(Long testId) {
        return em.find(Test.class, testId);
    }
    public List<Test> findAll() {
        return em.createQuery("select t from Test t " +
                        "left join fetch t.wordList", Test.class)
                .getResultList();
    }
    public Optional<Test> findByName(String name) {
        try {
            Test test = em.createQuery("select t from Test t " +
                            "left join fetch t.wordList " +
                            "where t.name = :name", Test.class)
                    .setParameter("name", name)
                    .getSingleResult();

            return Optional.ofNullable(test);
        } catch (NoResultException e) {
            return Optional.empty();
        }
    }
    public List<Test> findByStatus(TestStatus testStatus) {
        return em.createQuery("select t from Test t " +
                        "left join fetch t.wordList " +
                        "where t.testStatus= :testStatus", Test.class)
                .setParameter("testStatus", testStatus)
                .getResultList();
    }

}
