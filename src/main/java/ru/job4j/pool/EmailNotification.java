package ru.job4j.pool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class EmailNotification {
    private final ExecutorService pool;

    public EmailNotification(int pools) {
        this.pool = Executors.newFixedThreadPool(pools);
    }

    public void emailTo(User user) {
        String subject = "Notification "
                + user.getUsername()
                + "to email "
                + user.getEmail();
        String body = "Add a new event to "
                + user.getUsername();
        pool.submit(() -> send(subject, body, user.getEmail()));
        close();
    }

    private void send(String subject, String body, String email) {

    }

    public void close() {
        pool.shutdown();
    }

}
