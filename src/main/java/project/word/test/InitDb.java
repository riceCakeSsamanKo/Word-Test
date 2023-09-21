package project.word.test;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import project.word.test.domain.AccountType;
import project.word.test.domain.Group;
import project.word.test.domain.User;
import project.word.test.service.GroupService;
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
//        initService.userInit();
        initService.groupInit();
    }

    @Component
    @RequiredArgsConstructor
    static class InitService {

        private final UserService userService;
        private final GroupService groupService;
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
    }
}
