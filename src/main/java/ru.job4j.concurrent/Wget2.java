package ru.job4j.concurrent;

import java.io.BufferedInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.util.Date;

public class Wget2 implements Runnable {
    private final String url;
    private final int speed;
    private final String output;


    public Wget2(String url, int speed, String output) {
        this.url = url;
        this.speed = speed;
        this.output = output;
    }

    @Override
    public void run() {
        try (BufferedInputStream in = new BufferedInputStream(new URL(url).openStream());
             FileOutputStream fileOutputStream = new FileOutputStream(output)) {
            byte[] dataBuffer = new byte[1024];
            int bytesRead;
            int downloadData = 0;
            Date start = new Date();
            while ((bytesRead = in.read(dataBuffer, 0, 1024)) != -1) {
                downloadData = downloadData + bytesRead;
                if (downloadData >= speed) {
                    Date pause = new Date();
                    int numSeconds = (int) ((pause.getTime() - start.getTime()) / 1000);
                    if (numSeconds < 1) {
                        Thread.sleep(1000 - numSeconds);
                    } else {
                        Thread.sleep(1000);
                    }
                    downloadData = 0;
                    start.setTime(0);
                }
                fileOutputStream.write(dataBuffer, 0, bytesRead);
            }
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        String url = "https://proof.ovh.net/files/10Mb.dat";
        int speed = 1048576;
        String output = "tested.txt";
        Thread wget = new Thread(new Wget2(url, speed, output));
        wget.start();
        wget.join();
    }
}
