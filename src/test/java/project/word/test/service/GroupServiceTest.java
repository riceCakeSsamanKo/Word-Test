package project.word.test.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import project.word.test.domain.Group;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class GroupServiceTest {
    @Autowired
    GroupService groupService;

    @Test
    void 이름_중복_그룹_등록() throws Exception{
        //given
        Group group1 = new Group("group");
        Group group2 = new Group("group");

        //when
        groupService.join(group1);

        //then
        Assertions.assertThrows(IllegalStateException.class, ()->groupService.join(group2));
    }
}