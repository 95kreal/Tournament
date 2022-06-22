package ru.netology.exeption;

public class NotRegisteredException extends RuntimeException {
    public NotRegisteredException(String msg) {
        super(msg);
    }
}