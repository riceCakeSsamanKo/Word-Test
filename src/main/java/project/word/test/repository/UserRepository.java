package project.word.test.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import project.word.test.domain.Group;
import project.word.test.domain.User;

import javax.persistence.EntityManager;
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
    public List<User> findByGroup(Group group){
        return em.createQuery("select u from User u " +
                        "join fetch u.group g where g.id = :groupId", User.class)
                .setParameter("groupId", group.getId())
                .getResultList();
    }
}
