package project.word.test.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import project.word.test.domain.Group;
import project.word.test.domain.User;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class GroupRepository {

    private final EntityManager em;

    public void save(Group group) {
        em.persist(group);
    }

    public Group find(Long groupId) {
        return em.find(Group.class, groupId);
    }

    public List<Group> findAll() {
        return em.createQuery("select g from Group g", Group.class)
                .getResultList();
    }

    public Optional<Group> findByName(String name) {
        try {
            return Optional.ofNullable(em.createQuery("select g from Group g " +
                            "where g.name = :name", Group.class)
                    .setParameter("name", name)
                    .getSingleResult());
        } catch (NoResultException e) {
            return Optional.empty();
        }
    }

    public void remove(Group group){
        List<User> users = group.getUsers();
        for (User user : users) {
            user.setGroup(null);
        }
        em.remove(group);
    }

    public void removeUser(Group group, User user) {
        group.removeUser(user);
    }
}
