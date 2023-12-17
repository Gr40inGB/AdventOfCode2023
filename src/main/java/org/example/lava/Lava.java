package org.example.lava;

import org.example.bambam.LightVector;
import org.example.bambam.obstacle.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class Lava {

    static int[][] arr = null;

    static int HEIGHT = 0;
    static int WIGHT = 0;

    static char dark = '.';
    static char light = '#';
    static LavaVector[][] LavaWay;

    static int MAX_STEPS;
    static int MinHeatLost;


    static {
        try {
            arr = getArrayFromFile();
            HEIGHT = arr.length;
            WIGHT = arr[0].length;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        MAX_STEPS = (HEIGHT + WIGHT) * 2;
        MinHeatLost = getDefaultHeatLost();

    }


    public static void main(String[] args) throws IOException {
        printArray(arr);
        System.out.println(MinHeatLost);
    }

    public static void lavaWay(int y, int x, LavaVector vector, int oneDirectionStepCount, int stepCount, int lostHeat) {
        if (!inCity(y, x)) return;
        lostHeat += arr[y][x];
        if (lostHeat > MinHeatLost && stepCount > MAX_STEPS) return;

        if (y == HEIGHT - 1 && x == WIGHT - 1) {
            System.out.println(" we found some way - and lost only " + lostHeat + " heat");
            return;
        }
//        lavaWay();



    }

    public static LavaVector[] getAllActualWays(LavaVector vector){
        return new LavaVector[3];
    }

    public static boolean inCity(int y, int x) {
        return y > -1 && y < HEIGHT && x > -1 && x < WIGHT;
    }


    public static int[][] getArrayFromFile() throws IOException {
        List<String> list = Files.readAllLines(Path.of("src/main/resources/lava.txt"));
        int[][] arr = new int[list.size()][list.getFirst().length()];
        for (int y = 0; y < arr.length; y++) {
            String[] temp = list.get(y).split("");
            for (int x = 0; x < arr[0].length; x++) {
                arr[y][x] = Integer.parseInt(temp[x]);
            }
        }
        return arr;
    }

    public static int getDefaultHeatLost() {
        int sum = 0;
        for (int y = 0; y < HEIGHT; y++) {
            sum += arr[y][y];
            if ((y + 1) < HEIGHT) sum += arr[y + 1][y];
        }
        return sum - arr[0][0];
    }


    public static void printArray(int[][] arr) {
        for (int y = 0; y < HEIGHT; y++) {
            for (int x = 0; x < WIGHT; x++) {
                System.out.print(arr[y][x] + " ");
            }
            System.out.println();
        }
    }


}
