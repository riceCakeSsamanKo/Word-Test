package project.word.test.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

import static javax.persistence.FetchType.*;
import static lombok.AccessLevel.*;

@Entity
@Getter @Setter(value = PROTECTED)
@NoArgsConstructor(access = PROTECTED)
public class TestGroup {

    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "test_id")
    private Test test;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "group_id")
    private Group group;

    // 생성 메서드
    public static TestGroup createTestGroup(Group group){
        TestGroup testGroup = new TestGroup();
        testGroup.setGroup(group);
        return testGroup;
    }
}
