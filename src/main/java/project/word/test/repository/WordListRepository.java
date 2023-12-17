package project.word.test.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import project.word.test.domain.WordList;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import java.util.List;
import java.util.Optional;


@Repository
@RequiredArgsConstructor
public class WordListRepository {
    private final EntityManager em;

    public void save(WordList wordList) {
        em.persist(wordList);
    }

    public WordList findOne(Long wordListId) {
        return em.find(WordList.class, wordListId);
    }

    public List<WordList> findAll() {
        return em.createQuery("select wl from WordList wl", WordList.class).getResultList();
    }

    // 동일한 이름의 단어장은 한개만 존재
    public Optional<WordList> findByName(String name) {
        List<WordList> findWordList = em.createQuery("select wl from WordList wl " +
                        "where wl.name = :name", WordList.class)
                .setParameter("name", name)
                .getResultList();
        return findWordList.stream().findAny();
    }
}
