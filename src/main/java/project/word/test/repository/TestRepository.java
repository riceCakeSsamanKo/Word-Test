package project.word.test.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import project.word.test.domain.Difficulty;
import project.word.test.domain.Test;
import project.word.test.domain.TestStatus;
import project.word.test.domain.User;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

import static java.util.Optional.empty;
import static java.util.Optional.ofNullable;

@Repository
@RequiredArgsConstructor
public class TestRepository {
    private final EntityManager em;

    public void save(Test test) {
        em.persist(test);
    }

    public Test findOne(Long testId) {
        return em.find(Test.class, testId);
    }

    // 동일한 이름의 테스트는 한개만 존재
    public Optional<Test> findByName(String name) {
        List<Test> findTest = em.createQuery("select t from Test t " +
                        "where t.name = :name", Test.class)
                .setParameter("name", name)
                .getResultList();
        return findTest.stream().findAny();
    }

    public List<Test> findAll() {
        return em.createQuery("select t from Test t ", Test.class)
                .getResultList();
    }

    public List<Test> findByStatus(TestStatus status) {
        return em.createQuery("select t from Test t " +
                        "where t.status = :status", Test.class)
                .setParameter("status", status)
                .getResultList();
    }

    public List<Test> findByDifficulty(Difficulty difficulty) {
        return em.createQuery("select t from Test t " +
                        "where t.difficulty = :difficulty", Test.class)
                .setParameter("difficulty", difficulty)
                .getResultList();
    }
}
