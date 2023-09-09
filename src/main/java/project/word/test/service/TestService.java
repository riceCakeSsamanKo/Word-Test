package project.word.test.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import project.word.test.domain.Test;
import project.word.test.repository.TestRepository;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class TestService {
    
    private final TestRepository testRepository;

    public Long join(Test test) {
        testRepository.save(test);
        return test.getId();
    }
    @Transactional(readOnly = true)
    public Test findtest(Long testId) {
        return testRepository.find(testId);
    }
    @Transactional(readOnly = true)
    public Optional<Test> findtest(String name) {
        return testRepository.findByName(name);
    }
    @Transactional(readOnly = true)
    public List<Test> findtests() {
        return testRepository.findAll();
    }
}
