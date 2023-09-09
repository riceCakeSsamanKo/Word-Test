package project.word.test.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import project.word.test.domain.Group;

import javax.persistence.EntityManager;
import java.util.List;

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

    public List<Group> findByName(String name) {
        return em.createQuery("select g from Group g " +
                        "where g.name = :name", Group.class)
                .setParameter("name", name)
                .getResultList();
    }

}
