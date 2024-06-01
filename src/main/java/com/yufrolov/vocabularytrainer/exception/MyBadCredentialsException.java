package com.yufrolov.vocabularytrainer.exception;

public class MyBadCredentialsException extends RuntimeException{
    public MyBadCredentialsException(String message) {
        super(message);
    }
}
