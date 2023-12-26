package project.word.test;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import project.word.test.domain.*;
import project.word.test.service.GroupService;
import project.word.test.service.UserService;
import project.word.test.service.WordService;

import javax.annotation.PostConstruct;

import static project.word.test.domain.AccountType.ADMIN;
import static project.word.test.domain.AccountType.USER;
import static project.word.test.domain.Gender.*;

@Component //component scan의 대상이 되어 bean으로 등록됨
@RequiredArgsConstructor
public class InitDB {
    private final InitService initService;

    @PostConstruct // 스프링 빈으로 등록시 자동으로 실행됨
    public void init() {
        initService.initUser();
        initService.initGroup();
        initService.initWord();
    }

    @Component
    @Transactional
    @RequiredArgsConstructor
    static class InitService {

        private final UserService userService;
        private final GroupService groupService;
        private final WordService wordService;

        void initUser() {
            User user1 = new User("user1", 20, MALE, new LogIn("1", "1"), USER);
            User user2 = new User("user2", 30, FEMALE, new LogIn("2", "2"), USER);
            User user3 = new User("admin", 24, MALE, new LogIn("3", "3"), ADMIN);

            userService.join(user1);
            userService.join(user2);
            userService.join(user3);
        }

        void initGroup() {
            Group group1 = new Group("group1");
            Group group2 = new Group("group2");

            groupService.join(group1);
            groupService.join(group2);
        }

        void initWord() {
            Word word1 = new Word("apple", "사과");
            Word word2 = new Word("run", "뛰다");
            Word word3 = new Word("jump", "뛰다");
            Word word4 = new Word("jump", "뛰다");

            wordService.register(word1);
            wordService.register(word2);
            wordService.register(word3);
        }
    }
}
