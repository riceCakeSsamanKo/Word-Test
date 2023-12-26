package project.word.test.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import project.word.test.domain.Gender;
import project.word.test.domain.Group;
import project.word.test.domain.LogIn;
import project.word.test.domain.User;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;

import static org.junit.jupiter.api.Assertions.*;
import static project.word.test.domain.AccountType.USER;
import static project.word.test.domain.Gender.*;

@SpringBootTest
@Transactional
class UserServiceTest {
    @Autowired
    UserService userService;
    @Autowired
    EntityManager em;

    @BeforeEach
    public void each() {

    }

    @Test
    public void 중복_아이디_회원가입() throws Exception {
        //given
        LogIn logIn = new LogIn("123", "123");
        Group group = new Group("group");

        User user1 = new User("user1", 10, MALE, logIn, group,USER);

        //when
        userService.join(user1);

        //then
        User user2 = new User("user2", 10, MALE, logIn, group,USER);
        assertThrows(IllegalStateException.class, ()->userService.join(user2));
    }
}