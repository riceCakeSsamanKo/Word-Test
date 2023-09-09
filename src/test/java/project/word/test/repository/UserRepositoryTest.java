package project.word.test.repository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import project.word.test.domain.AccountType;
import project.word.test.domain.User;
import project.word.test.service.UserService;

import javax.persistence.EntityManager;

import static org.junit.jupiter.api.Assertions.*;
import static project.word.test.domain.AccountType.*;

@SpringBootTest
@Transactional
class UserRepositoryTest {

    @Autowired
    EntityManager em;
    @Autowired
    UserRepository userRepository;

    User user1 = new User("유저1", 50, STUDENT);
    User user2 = new User("유저2", 30, STUDENT);
    User user3 = new User("유저3", 20, ADMIN);

    @BeforeEach
    public void each() {
        userRepository.save(user1);
        userRepository.save(user3);
    }

    @Test
    void remove() {
        User findUser1 = userRepository.find(user1.getId());
        userRepository.remove(findUser1);

        userRepository.remove(user2);
    }
}