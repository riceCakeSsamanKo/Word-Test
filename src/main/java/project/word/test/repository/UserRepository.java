package project.word.test.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import project.word.test.domain.AccountType;
import project.word.test.domain.LogIn;
import project.word.test.domain.User;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class UserRepository {
    private final EntityManager em;

    public void save(User user) {
        em.persist(user);
    }

    public User findOne(Long userId) {
        return em.find(User.class, userId);
    }

    public List<User> findAll() {
        return em.createQuery("select u from User u " +
                        "join fetch u.group g", User.class)
                .getResultList();
    }

    public List<User> findByName(String name) {
        return em.createQuery("select u from User u " +
                        "join fetch u.group g " +
                        "where u.name = :name",User.class)
                .setParameter("name", name)
                .getResultList();
    }

    public List<User> findByAccountType(AccountType accountType) {
        return em.createQuery("select u from User u " +
                        "join fetch u.group g " +
                        "where u.accountType = :accountType",User.class)
                .setParameter("accountType", accountType)
                .getResultList();
    }

    public List<User> findByLogIn(LogIn logIn) {
        return em.createQuery("select u from User u " +
                        "join fetch u.group g " +
                        "where u.login.id = :id " +
                        "and u.login.pw = :pw", User.class)
                .setParameter("id", logIn.getId())
                .setParameter("pw",logIn.getPw())
                .getResultList();
    }
}
