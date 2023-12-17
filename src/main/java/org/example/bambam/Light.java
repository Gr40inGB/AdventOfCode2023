package org.example.bambam;

import org.example.bambam.obstacle.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Vector;

public class Light {

    static char[][] arr = null;
    static Obstacle[][] contraption;

    static int HEIGHT = 0;
    static int WIGHT = 0;

    static char dark = '.';
    static char light = '#';
    static LightVector[][] lightFill;


    static {
        try {
            arr = getArrayFromFile();
            HEIGHT = arr.length;
            WIGHT = arr[0].length;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        lightFill = new LightVector[HEIGHT][WIGHT];
        contraption = fillContraption(arr);
    }


    public static void main(String[] args) throws IOException {

        printArray(contraption);
//        bambam(0, 0, new LightVector(0, 1));
        System.out.println("///****************************///");
//        printArray(lightFill);

        findWarmest();


    }

    public static void bambam(int y, int x, LightVector vector) {
        if (!inContraption(y, x)) return;
        if (lightFill[y][x] != null && lightFill[y][x].equals(vector)) return;
        lightFill[y][x] = vector;
        for (LightVector v : contraption[y][x].encounter(vector)) {
            bambam(y + v.yDirection, x + v.xDirection, v);
        }

    }

    public static void findWarmest() {
        int count = 0;
        for (int y = 0; y < HEIGHT; y++) {
            int tempCount = initSearch(y, 0, new LightVector(0, 1));
            if (count < tempCount) count = tempCount;
            tempCount = initSearch(y, WIGHT - 1, new LightVector(0, -1));
            if (count < tempCount) count = tempCount;
        }
        for (int x = 0; x < WIGHT; x++) {
            int tempCount = initSearch(0, x, new LightVector(1, 0));
            if (count < tempCount) count = tempCount;
            tempCount = initSearch(HEIGHT - 1, x, new LightVector(-1, 0));
            if (count < tempCount) count = tempCount;
        }

        System.out.println("best is " + count);

    }

    public static int initSearch(int y, int x, LightVector vector) {
        lightFill = new LightVector[HEIGHT][WIGHT];
        bambam(y, x, vector);
        return countLight();
    }

    public static int countLight() {
        int count = 0;
        for (int y = 0; y < HEIGHT; y++) {
            for (int x = 0; x < WIGHT; x++) {
                if (lightFill[y][x] != null) count++;
            }
        }
        return count;
    }

    public static boolean inContraption(int y, int x) {
        return y > -1 && y < HEIGHT && x > -1 && x < WIGHT;
    }

    public static Obstacle getObstacleByChar(char c) {
        if (c == '.') return new Empty();
        else if (c == '-') return new HorizontalSplitter();
        else if (c == '|') return new VerticalSplitter();
        else if (c == '/') return new RightMirror();
        else return new LeftMirror();// if c == '\'
    }

    public static Obstacle[][] fillContraption(char[][] charArray) {
        Obstacle[][] contraption = new Obstacle[HEIGHT][WIGHT];
        for (int y = 0; y < HEIGHT; y++) {
            for (int x = 0; x < WIGHT; x++) {
                contraption[y][x] = getObstacleByChar(charArray[y][x]);
            }
        }
        return contraption;
    }

    public static char[][] getArrayFromFile() throws IOException {
        List<String> list = Files.readAllLines(Path.of("src/main/resources/fild.txt"));
        char[][] arr = new char[list.size()][list.getFirst().length()];
        for (int y = 0; y < arr.length; y++) {
            for (int x = 0; x < arr[0].length; x++) {
                arr[y][x] = list.get(y).charAt(x);
            }
        }
        return arr;
    }

    public static void printArray(Obstacle[][] arr) {
        for (int y = 0; y < HEIGHT; y++) {
            for (int x = 0; x < WIGHT; x++) {
                System.out.print(arr[y][x] + " ");
            }
            System.out.println();
        }
    }

    public static void printArray(LightVector[][] arr) {
        for (int y = 0; y < HEIGHT; y++) {
            for (int x = 0; x < WIGHT; x++) {
                if (arr[y][x] != null) System.out.print(light + " ");
                else System.out.print(dark + " ");
            }
            System.out.println();
        }
    }


}
