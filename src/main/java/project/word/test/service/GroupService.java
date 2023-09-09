package project.word.test.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import project.word.test.domain.Group;
import project.word.test.domain.User;
import project.word.test.repository.GroupRepository;
import project.word.test.repository.UserRepository;

import java.util.List;
import java.util.Optional;


@Service
@RequiredArgsConstructor
@Transactional
public class GroupService {
    private final GroupRepository groupRepository;

    public Long join(Group group) {
        groupRepository.save(group);
        return group.getId();
    }

    @Transactional(readOnly = true)
    public Group findGroup(Long groupId) {
        return groupRepository.find(groupId);
    }

    @Transactional(readOnly = true)
    public Optional<Group> findGroup(String name) {
        return groupRepository.findByName(name);
    }

    @Transactional(readOnly = true)
    public List<Group> findGroups() {
        return groupRepository.findAll();
    }
}
