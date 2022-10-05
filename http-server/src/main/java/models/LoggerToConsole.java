package models;

import interfaces.Logger;

/**
 * Этот класс логирует в консоль, так же при необходимости можно написать клаа для логирование в файйл или еще куда-нибудь.
 * **/
public class LoggerToConsole implements Logger {

    @Override
    public void writeMessage(String message) {
        System.out.println(message);
    }
}
