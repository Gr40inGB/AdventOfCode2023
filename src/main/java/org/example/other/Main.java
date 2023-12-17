package org.example.other;

import javax.print.DocFlavor;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Main {

    static final char STONE = 'O';
    static final char BLOCK = '#';
    static final char EMPTY = '.';

    static char[][] arr = null;

    static int HEIGHT = 0;
    static int WIGHT = 0;

    static int TEST_CYCLES = 1_000;


    static {
        try {
            arr = getArrayFromFile();
            HEIGHT = arr.length;
            WIGHT = arr[0].length;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    public static void main(String[] args) throws IOException {

//        printArray(arr);

//        int[] loadsOfTest = new int[TEST_CYCLES];
        for (int i = 0; i < 1000_000_000; i++) {
            cycleRotation();

//            loadsOfTest[i] = getLoad();
        }
        System.out.println(getLoad());
//
//        int maxRepeated = 0;
//        List<Integer> maxRepeatedNumbers = new ArrayList<>();
//
//        HashMap<Integer, Integer> allLoadsInTest = new HashMap<>();
//        for (int i : loadsOfTest) {
//            System.out.println(i);
//            if (allLoadsInTest.containsKey(i)) allLoadsInTest.put(i, allLoadsInTest.get(i) + 1);
//            else allLoadsInTest.put(i, 1);
//        }
//
//        for (var entry : allLoadsInTest.entrySet()) {
//            if (entry.getValue() > maxRepeated) maxRepeated = entry.getValue();
//        }
//
//        for (var entry : allLoadsInTest.entrySet()) {
//            if (entry.getValue() == maxRepeated) maxRepeatedNumbers.add(entry.getKey());
//        }
//
//        for (int n : maxRepeatedNumbers) System.out.println(n);
//
//        int repeatLineCount = 0;
//        int repeatStartIndex = 1;
//        for (int i = 1; i <= loadsOfTest.length; i++) {
//            if (maxRepeatedNumbers.contains(loadsOfTest[i])) repeatLineCount++;
//            else repeatLineCount = 0;
//            if (repeatLineCount == maxRepeatedNumbers.size()) {
//                repeatStartIndex = i - maxRepeatedNumbers.size() ;
//                System.out.println(loadsOfTest[repeatStartIndex]);
//                break;
//            }
//        }
//        System.out.println(repeatStartIndex);
//
//
//        int countCycles = ((1_000_000_000 - repeatStartIndex) % maxRepeatedNumbers.size()) + repeatStartIndex;
//        System.out.println(countCycles);
//
//        arr = getArrayFromFile();
//
//        for (int i = 1; i <= countCycles-1; i++) {
//            cycleRotation();
////            loadsOfTest[i] = getLoad();
//        }
//        System.out.println(getLoad());
//
////        System.out.println("*************************************");
////        printArray(arr);

    }


    public static int getLoad() {
        int result = 0;
        for (int y = 0; y < HEIGHT; y++) {
            for (int x = 0; x < WIGHT; x++) {
                if (arr[y][x] == STONE) result += HEIGHT - y;
            }
        }
        return result;
    }

    public static void cycleRotation() {
        //up
        for (int y = 0; y < HEIGHT; y++) {
            for (int x = 0; x < WIGHT; x++) {
                if (arr[y][x] == STONE) move(y, x, -1, 0);
            }
        }
        //left
        for (int y = 0; y < HEIGHT; y++) {
            for (int x = 0; x < WIGHT; x++) {
                if (arr[y][x] == STONE) move(y, x, 0, -1);
            }
        }
        //down
        for (int y = HEIGHT - 1; y > -1; y--) {
            for (int x = 0; x < WIGHT; x++) {
                if (arr[y][x] == STONE) move(y, x, 1, 0);
            }
        }
        //right
        for (int y = 0; y < HEIGHT; y++) {
            for (int x = WIGHT - 1; x > -1; x--) {
                if (arr[y][x] == STONE) move(y, x, 0, 1);
            }
        }
    }

    public static void move(int y, int x, int yD, int xD) {
        int newY = y + yD;
        int newX = x + xD;
        if (inDish(newY, newX) && arr[newY][newX] == EMPTY) {
            arr[y][x] = EMPTY;
            arr[newY][newX] = STONE;
            move(newY, newX, yD, xD);
        } else return;
    }

    public static boolean inDish(int y, int x) {
        return y > -1 && y < HEIGHT && x > -1 && x < WIGHT;
    }

    public static char[][] getArrayFromFile() throws IOException {
        List<String> list = Files.readAllLines(Path.of("src/main/resources/dish.txt"));
        char[][] arr = new char[list.size()][list.getFirst().length()];
        for (int y = 0; y < arr.length; y++) {
            for (int x = 0; x < arr[0].length; x++) {
                arr[y][x] = list.get(y).charAt(x);
            }
        }
        return arr;
    }

    public static void printArray(char[][] arr) {
        for (int y = 0; y < HEIGHT; y++) {
            for (int x = 0; x < WIGHT; x++) {
                System.out.print(arr[y][x] + " ");
            }
            System.out.println();
        }
    }
}