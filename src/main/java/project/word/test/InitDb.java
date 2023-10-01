package project.word.test;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import project.word.test.domain.AccountType;
import project.word.test.domain.Group;
import project.word.test.domain.Test;
import project.word.test.domain.User;
import project.word.test.service.GroupService;
import project.word.test.service.TestService;
import project.word.test.service.UserService;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;

import static project.word.test.domain.AccountType.*;

@Component
@RequiredArgsConstructor
public class InitDb {

    private final InitService initService;

    @PostConstruct  // 빈으로 등록될 시 자동으로 실행됨
    public void init() {
        initService.userInit();
        initService.groupInit();
        initService.userGroupJoinInit();
        initService.testInit();
    }

    @Component
    @RequiredArgsConstructor
    static class InitService {

        private final UserService userService;
        private final GroupService groupService;
        private final TestService testService;
        void userInit() {

            User user1 = new User("user1", 30, "1", "1");
            User user2 = new User("user2", 40, "2", "2");
            User user3 = new User("user3", 50, "3", "3");

            User admin1 = new User("admin1", 20, ADMIN, "a1", "a1");

            userService.join(user1);
            userService.join(user2);
            userService.join(user3);
            userService.join(admin1);
        }


        void groupInit() {

            Group group1 = new Group("group1");
            Group group2 = new Group("group2");
            Group group3 = new Group("group3");

            groupService.join(group1);
            groupService.join(group2);
            groupService.join(group3);
        }

        void userGroupJoinInit() {
            User userA = new User("userA",20,"11","11");
            User userB = new User("userB",20,"22","22");

            Group groupA = new Group("groupA");
            Group groupB = new Group("groupB");

            groupA.addUser(userA);
            groupB.addUser(userB);

            groupService.join(groupA);
            groupService.join(groupB);
        }

        void testInit() {
            Test test1 = new Test("Test1");
            Test test2 = new Test("Test2");
            testService.join(test1);
            testService.join(test2);
        }
    }
}
