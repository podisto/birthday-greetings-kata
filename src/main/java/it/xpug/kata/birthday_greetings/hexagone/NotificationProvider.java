package it.xpug.kata.birthday_greetings.hexagone;

/**
 * Created by podisto on 15/04/2022.
 */
public interface NotificationProvider {

    void send(String recipient, String subject, String body);
}
