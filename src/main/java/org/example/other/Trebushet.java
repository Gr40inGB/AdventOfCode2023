package org.example.other;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class Trebushet {
    public static void main(String[] args) throws IOException {
        List<String> list = Files.readAllLines(Path.of("src/main/resources/treb.txt"));
        int sum = 0;
        int first = 0;
        int second = 0;
        for (String s : list) {
            char[] chars = s.toCharArray();
            for (int i = 0; i < chars.length; i++) {
                if (Character.isDigit(chars[i])) {
                    first = Character.digit(chars[i], 10);
//                    System.out.println(first);
                    break;
                }
            }
            for (int i = chars.length - 1; i > -1; i--) {
                if (Character.isDigit(chars[i])) {
                    second = Character.digit(chars[i], 10);
                    break;
                }
            }
            sum += (first * 10) + second;
        }
        System.out.println(sum);
    }
}
