package it.xpug.kata.birthday_greetings.infrastructure;

/**
 * Created by podisto on 15/04/2022.
 */
public class MailProperties {
    private final String sender;
    private final String host;
    private final int port;

    public MailProperties(String sender, String host, int port) {
        this.sender = sender;
        this.host = host;
        this.port = port;
    }

    public String getSender() {
        return sender;
    }

    public String getHost() {
        return host;
    }

    public int getPort() {
        return port;
    }
}
