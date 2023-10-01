package project.word.test.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import project.word.test.domain.AccountType;
import project.word.test.domain.Group;
import project.word.test.domain.LogInInformation;
import project.word.test.domain.User;

import static org.junit.jupiter.api.Assertions.*;
import static project.word.test.domain.AccountType.*;

@SpringBootTest
@Transactional
class GroupRepositoryTest {
    @Autowired
    GroupRepository groupRepository;

    @Test
    public void remove() {

        Group group = new Group("group");
        User user1 = new User("유저1", 1, "abc", "pw");
        User user2 = new User("유저2", 2, "abc", "pw");
        User user3 = new User("유저3", 3, ADMIN, "abc", "pw");

        group.addUser(user1);
        group.addUser(user2);
        group.addUser(user3);
        System.out.println("name=" + user1.getGroup().getName());

        groupRepository.save(group);

        Group findGroup = groupRepository.find(group.getId());
        groupRepository.remove(findGroup);
    }
}