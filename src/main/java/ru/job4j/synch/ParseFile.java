package ru.job4j.synch;

import java.io.*;
import java.util.function.Predicate;

public final class ParseFile {
    private final File file;

    public ParseFile(File file) {
        this.file = file;
    }

    public synchronized String getContent(Predicate<Integer> filter) {
        String output = "";
        try (BufferedInputStream in = new BufferedInputStream(new FileInputStream(file))) {
            byte[] buff = new byte[in.available()];
            int data;
            while ((data = in.read(buff, 0, 1)) != 1) {
                if (filter.test(data)) {
                    output += (char) data;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return output;
    }
}
