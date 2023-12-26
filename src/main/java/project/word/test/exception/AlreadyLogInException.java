package project.word.test.exception;

public class AlreadyLogInException extends Exception{
    public AlreadyLogInException() {}
    public AlreadyLogInException(String msg) {
        super(msg);
    }
}

