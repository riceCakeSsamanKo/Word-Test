package project.word.test.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import project.word.test.domain.Group;
import project.word.test.repository.GroupRepository;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class GroupService {
    private final GroupRepository groupRepository;

    @Transactional(readOnly = false)
    public void join(Group group) {
        Optional<Group> groupFindByName = groupRepository.findByName(group.getName());

        // 동일한 이름의 그룹은 존재할 수 없다
        if (groupFindByName.isPresent()) {
            throw new IllegalStateException("error\n" +
                    "내용: 이미 존재하는 그룹명입니다." +
                    "발생지점: GroupService.join()");
        }
        groupRepository.save(group);
    }

    public Group findGroup(Long groupId) {
        return groupRepository.findOne(groupId);
    }

    public Group findGroup(String name) {
        Optional<Group> groupFindByName = groupRepository.findByName(name);

        if (groupFindByName.isEmpty()) {
            throw new IllegalStateException("error\n" +
                    "내용: 존재하지 않는 그룹입니다." +
                    "발생지점: GroupService.findGroup(String name)");
        }
        return groupFindByName.get();
    }

    public List<Group> findGroups() {
        return groupRepository.findAll();
    }

    /**
     * 업데이트 로직 |
     * 그룹 정보 업데이트
     */

    @Transactional(readOnly = false)
    public void updateGroupName(Long groupId, String name) {
        Group group = groupRepository.findOne(groupId);
        group.changeName(name);
    }
}
