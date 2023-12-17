package project.word.test.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import project.word.test.domain.AccountType;
import project.word.test.domain.LogIn;
import project.word.test.domain.User;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import java.util.List;
import java.util.Optional;

import static java.util.Optional.*;

@Repository
@RequiredArgsConstructor
public class UserRepository {
    private final EntityManager em;

    // 서비스 계층에서 user 등록하는 로직에 동일한 아이디 가지는 user는 등록이 안되도록 구현해야 한다.
    public void save(User user) {
        em.persist(user);
    }

    public User findOne(Long userId) {
        return em.find(User.class, userId);
    }

    public Optional<User> findByLogIn(LogIn logIn) {
        List<User> findUser = em.createQuery("select u from User u " +
                        "join fetch u.group g " +
                        "where u.login.id = :id " +
                        "and u.login.pw = :pw", User.class)
                .setParameter("id", logIn.getId())
                .setParameter("pw", logIn.getPw())
                .getResultList();
        return findUser.stream().findAny();
    }

    public Optional<User> findByLogInId(LogIn logIn) {
        List<User> findUser = em.createQuery("select u from User u " +
                        "left join fetch u.group g " +
                        "where u.login.id = :id ", User.class)
                .setParameter("id", logIn.getId())
                .getResultList();
        return findUser.stream().findAny();
    }

    public List<User> findAll() {
        return em.createQuery("select u from User u " +
                        "left join fetch u.group g", User.class)
                .getResultList();
    }

    public List<User> findByName(String name) {
        return em.createQuery("select u from User u " +
                        "left join fetch u.group g " +
                        "where u.name = :name", User.class)
                .setParameter("name", name)
                .getResultList();
    }

    public List<User> findByAccountType(AccountType accountType) {
        return em.createQuery("select u from User u " +
                        "left join fetch u.group g " +
                        "where u.accountType = :accountType", User.class)
                .setParameter("accountType", accountType)
                .getResultList();
    }
}
