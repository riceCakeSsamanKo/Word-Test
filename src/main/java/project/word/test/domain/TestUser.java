package project.word.test.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

import static javax.persistence.FetchType.*;

@Entity
@Getter
@Setter
public class TestUser {
    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "test_id")
    private Test test;

    // 생성 메서드
    public static TestUser createTestUser(User user){
        TestUser testUser = new TestUser();
        testUser.setUser(user);
        return testUser;
    }

    // setter
    public void setUser(User user) {
        this.user = user;
    }
    public void setTest(Test test) {
        this.test = test;
    }
}
