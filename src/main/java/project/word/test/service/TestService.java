package project.word.test.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import project.word.test.domain.*;
import project.word.test.repository.TestRepository;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TestService {
    private final TestRepository testRepository;

    public void register(Test test) {
        Optional<Test> testFindByName = testRepository.findByName(test.getName());

        // 동일이름의 테스트는 존재할 수 없다
        if (testFindByName.isPresent()) {
            throw new IllegalStateException("error\n" +
                    "내용: 이미 존재하는 테스트명입니다." +
                    "발생지점: testService.register()");
        }
        testRepository.save(test);
    }

    @Transactional(readOnly = true)
    public Test findTest(Long testId) {
        return testRepository.findOne(testId);
    }

    public Test findTest(String name) {
        Optional<Test> testFindByName = testRepository.findByName(name);
        if (testFindByName.isEmpty()) {
            throw new IllegalStateException("error\n" +
                    "내용: 존재하지 않는 테스트입니다." +
                    "발생지점: TestService.findTest(String name)");
        }

        return testFindByName.get();
    }

    @Transactional(readOnly = true)
    public List<Test> findTests() {
        return testRepository.findAll();
    }

    @Transactional(readOnly = true)
    public List<Test> findTests(TestStatus status) {
        return testRepository.findByStatus(status);
    }

    @Transactional(readOnly = true)
    public List<Test> findTests(Difficulty difficulty) {
        return testRepository.findByDifficulty(difficulty);
    }

    /**
     * 업데이트 로직 |
     * 테스트 정보 업데이트
     */
    public void updateTest(Long testId, String name) {
        Test test = testRepository.findOne(testId);
        test.changeName(name);
    }

    public void updateTest(Long testId, int cutLine) {
        Test test = testRepository.findOne(testId);
        test.changeCutLine(cutLine);
    }

    public void updateTest(Long testId, TestStatus status) {
        Test test = testRepository.findOne(testId);
        test.changeStatus(status);
    }

    public void updateTest(Long testId, Difficulty difficulty) {
        Test test = testRepository.findOne(testId);
        test.changeDifficulty(difficulty);
    }

    public void updateTest(Long testId, int year, int month, int date) {
        Test test = testRepository.findOne(testId);
        test.setExecutionDate(year, month, date);
    }

    public void addWordTest(Long testId, WordTest wordTest) {
        Test test = testRepository.findOne(testId);
        test.addWordTest(wordTest);
    }

    public void deleteWordTest(Long testId, WordTest wordTest) {
        Test test = testRepository.findOne(testId);
        test.deleteWordTest(wordTest);
    }
}