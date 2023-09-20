package project.word.test.repository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;
import project.word.test.domain.AccountType;
import project.word.test.domain.Group;
import project.word.test.domain.User;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class UserRepository {

    private final EntityManager em;

    public void save(User user) {
        em.persist(user);
    }

    public User find(Long userId) {
        return em.find(User.class, userId);
    }

    public List<User> findAll() {
        return em.createQuery("select u from User u " +
                        "join fetch u.group", User.class)
                .getResultList();
    }

    public List<User> findByName(String name) {
        return em.createQuery("select u from User u " +
                        "join fetch u.group where u.name = :name", User.class)
                .setParameter("name", name)
                .getResultList();
    }

    public Optional<User> findByLogInId(String login_id) {
        try {
            return Optional.ofNullable(em.createQuery("select u from User u " +
                            "join fetch u.group " +
                            "where u.logIn.login_id = :login_id", User.class)
                    .setParameter("login_id", login_id)
                    .getSingleResult());
        } catch (NoResultException e) {
            return Optional.empty();
        }
    }

    public Optional<User> findByLogInInfo(String id, String password) {
        try {
            return Optional.ofNullable(em.createQuery("select u from User u " +
                            "join fetch u.group " +
                            "where u.logIn.login_id = :id and u.logIn.login_password = :password", User.class)
                    .setParameter("id", id)
                    .setParameter("password", password)
                    .getSingleResult());
        } catch (NoResultException e) {
            return Optional.empty();
        }
    }

    public List<User> findByAccountType(AccountType accountType) {
        return em.createQuery("select u from User u " +
                        "join fetch u.group g where u.accountType = :accountType", User.class)
                .setParameter("accountType", accountType)
                .getResultList();
    }

    public List<User> findByGroup(Group group) {
        return em.createQuery("select u from User u " +
                        "join fetch u.group g where g.id = :groupId", User.class)
                .setParameter("groupId", group.getId())
                .getResultList();
    }

    public void remove(User user) {
        em.remove(user);
    }
}
