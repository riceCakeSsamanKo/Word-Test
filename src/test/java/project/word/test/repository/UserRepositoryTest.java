package project.word.test.repository;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import project.word.test.domain.AccountType;
import project.word.test.domain.Gender;
import project.word.test.domain.LogIn;
import project.word.test.domain.User;

import javax.persistence.EntityManager;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static project.word.test.domain.AccountType.USER;
import static project.word.test.domain.Gender.*;

@SpringBootTest
@Transactional
class UserRepositoryTest {

    @Autowired
    UserRepository userRepository;

    @Test
    public void 아이디로_유저_조회() throws Exception{
        //given
        LogIn logIn = new LogIn("123", "123");
        User user1 = new User("user1", 10, MALE, logIn,USER);
        userRepository.save(user1);

        //when
        Optional<User> findUser = userRepository.findByLogInId(user1.getLogin());
        User user = findUser.get();

        //then
        assertEquals(user1.getId(), user.getId());
    }

    @Test
    public void 아이디로_유저_조회2() throws Exception{
        //given
        LogIn logIn = new LogIn("123", "123");
        LogIn logIn2 = new LogIn("12", "123");

        User user1 = new User("user1", 10, MALE, logIn, USER);
        userRepository.save(user1);

        //when
        Optional<User> findUser = userRepository.findByLogInId(logIn2);

        //then
        assertEquals(findUser.get(),Optional.empty());
    }
}