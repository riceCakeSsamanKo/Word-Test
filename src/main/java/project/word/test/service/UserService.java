package project.word.test.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import project.word.test.domain.AccountType;
import project.word.test.domain.User;
import project.word.test.repository.UserRepository;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public Long join(User user) {
        userRepository.save(user);
        return user.getId();
    }
    @Transactional(readOnly = true)
    public User findUser(Long userId) {
        return userRepository.find(userId);
    }
    @Transactional(readOnly = true)
    public Optional<User> findUser(String id, String password) {
        return userRepository.findByLogInInfo(id,password);
    }
    @Transactional(readOnly = true)
    public List<User> findUsers() {
        return userRepository.findAll();
    }

    // 업데이트 로직
    public void updateUser(Long userId, String password, String name, int age) {
        User user = userRepository.find(userId);
        user.changeUserInfo(name,age,password);
    }
    public void updateUser(Long userId, String password, String name, int age, AccountType accountType) {
        User user = userRepository.find(userId);
        user.changeUserInfo(name,age,password);
        user.setAccountType(accountType);
    }
}
