package project.word.test.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import project.word.test.domain.*;
import project.word.test.repository.UserRepository;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    @Transactional(readOnly = false)
    public void join(User user) {
        Optional<User> findUser = userRepository.findByLogInId(user.getLogin());
        if (findUser.isPresent()) {
            throw new IllegalStateException("error\n" +
                    "내용: 이미 가입된 아이디입니다\n" +
                    "발생지점: UserService.join()");
        }
        userRepository.save(user);
    }

    public User findUser(Long userId) {
        return userRepository.findOne(userId);
    }

    public User findUser(LogIn login) {
        Optional<User> userFindByName = userRepository.findByLogIn(login);
        if (userFindByName.isEmpty()) {
            throw new IllegalStateException("error\n" +
                    "내용: 존재하지 않는 아이디입니다.\n" +
                    "발생지점: UserService.findUser(LogIn login)");
        }

        return userFindByName.get();
    }

    public User findUser(String logIn_id) {
        Optional<User> userFindByLogIn_id = userRepository.findByLogIn(logIn_id);
        if (userFindByLogIn_id.isEmpty()) {
            throw new IllegalStateException("error\n" +
                    "내용: 존재하지 않는 아이디입니다.\n" +
                    "발생지점: UserService.findUser(String logIn_id)");
        }

        return userFindByLogIn_id.get();
    }
    public User findUser(String id, String pw) {
        Optional<User> userFindByName = userRepository.findByLogIn(id, pw);
        if (userFindByName.isEmpty()) {
            throw new IllegalStateException("error\n" +
                    "내용: 존재하지 않는 아이디입니다.\n" +
                    "발생지점: UserService.findUser(String id, String pw)");
        }

        return userFindByName.get();
    }

    public List<User> findUsers() {
        return userRepository.findAll();
    }

    public List<User> findUsers(String userName) {
        return userRepository.findByName(userName);
    }

    public List<User> findUsers(AccountType accountType) {
        return userRepository.findByAccountType(accountType);
    }

    /**
     * 업데이트 로직 |
     * 유저 정보 업데이트
     */
    @Transactional(readOnly = false)
    public void updateUserInfo(Long userId, String name, int age, Gender gender) {
        User findUser = userRepository.findOne(userId);
        if (findUser == null) {
            throw new IllegalStateException("error\n" +
                    "내용: 존재하지 않는 유저입니다.\n" +
                    "발생지점: UserService.updateUserInfo()");
        }
        findUser.changeInfo(name, age, gender);
    }

    @Transactional(readOnly = false)
    public void updateUserPassword(Long userId, String password) {
        User findUser = userRepository.findOne(userId);
        if (findUser == null) {
            throw new IllegalStateException("error\n" +
                    "내용: 존재하지 않는 유저입니다. (잘못된 pk로 조회함)\n" +
                    "발생지점: UserService.updateUserPassword()");
        }
        findUser.changePassword(password);
    }

    @Transactional(readOnly = false)
    public void updateUserGroup(Long userId, Group group) {
        User findUser = userRepository.findOne(userId);
        if (findUser == null) {
            throw new IllegalStateException("error\n" +
                    "내용: 존재하지 않는 유저입니다. (잘못된 pk로 조회함)\n" +
                    "발생지점: UserService.updateUserGroup()");
        }
        findUser.changeGroup(group);
    }
}
