package project.word.test.exception;

public class RequiredLogInException extends Exception{
    public RequiredLogInException() {}
    public RequiredLogInException(String msg) {
        super(msg);
    }
}
