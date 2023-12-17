package project.word.test.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import project.word.test.domain.Group;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

import static java.util.Optional.empty;
import static java.util.Optional.ofNullable;

@Repository
@RequiredArgsConstructor
public class GroupRepository {
    private final EntityManager em;

    public void save(Group group) {
        em.persist(group);
    }

    public Group findOne(Long groupId) {
            return em.find(Group.class, groupId);
    }

    public Optional<Group> findByName(String name) {
        List<Group> findGroup = em.createQuery("select g from Group g " +
                        "where g.name =: name", Group.class)
                .setParameter("name", name)
                .getResultList();
        return findGroup.stream().findAny();
    }

    public List<Group> findAll() {
        return em.createQuery("select g from Group g", Group.class)
                .getResultList();
    }
}
