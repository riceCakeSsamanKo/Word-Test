package project.word.test.repository;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import project.word.test.domain.Group;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class GroupRepositoryTest {
    @Autowired
    GroupRepository groupRepository;

    @Test
    public void find() throws Exception{
        //given
        Group group = new Group("a");
        groupRepository.save(group);
        //when
        Group one = groupRepository.findOne(11231242123L);

        //then
        try {
            one.getId();
        } catch (NullPointerException e) {
            System.out.println(e);
        }
    }
}