//package org.example.lava;
//
//import org.example.bambam.LightVector;
//import org.example.bambam.obstacle.*;
//
//import java.io.IOException;
//import java.nio.file.Files;
//import java.nio.file.Path;
//import java.util.ArrayList;
//import java.util.Arrays;
//import java.util.List;
//
//public class Lava {
//
//    static int[][] arr = null;
//
//    static int HEIGHT = 0;
//    static int WIGHT = 0;
//
//    static char dark = '.';
//    static char light = '#';
//    static LavaVector[][] LavaWay;
//    static int[][] minPoints;
//
//    static int MAX_STEPS;
//    static int minHeatLost;
//
//    static BestCell[][] bestCells;
//
//
//    static {
//        try {
//            arr = getArrayFromFile();
//            HEIGHT = arr.length;
//            WIGHT = arr[0].length;
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
//
//        bestCells = new BestCell[HEIGHT][WIGHT];
//        MAX_STEPS = (HEIGHT + WIGHT) + HEIGHT + WIGHT;
//        minHeatLost = getDefaultHeatLost();
//        minPoints = new int[HEIGHT][WIGHT];
//
//    }
//
//
//    public static void main(String[] args) throws IOException {
//        printArray(arr);
////        System.out.println(MinHeatLost);
//
//
////        lavaWaySmart(0, 0, new LavaVector(1, 0), 0, 0);
////        printArray(bestCells);
////        System.out.println(bestCells[HEIGHT - 1][WIGHT - 1].bestValue);
//        lavaWaySmart(0, 0, new LavaVector(0, 1), 0, 0);
//        printArray(bestCells);
//        System.out.println(bestCells[HEIGHT - 1][WIGHT - 1].bestValue);
//    }
//
//    public static void lavaWaySmart(int y, int x, LavaVector vector, int oneDirectionStepCount, int lostHeat) {
//        if (!inCity(y, x)) return;
//        lostHeat += arr[y][x];
//        if (bestCells[y][x] == null )
//            bestCells[y][x] = new BestCell(y, x, vector, oneDirectionStepCount, lostHeat);
//        else if (bestCells[y][x].bestValue > lostHeat){
//            bestCells[y][x] = new BestCell(y, x, vector, oneDirectionStepCount, lostHeat);
//        }
////        else if (bestCells[y][x].bestValue == lostHeat){
//////            bestCells[y][x] = new BestCell(y, x, vector, lostHeat);
////        }
//        else return;
//        if (bestCells[y][x].bestValue > minHeatLost) return;
//
//        if (y == HEIGHT - 1 && x == WIGHT - 1) {
////            System.out.println(" we found some way - and lost only " + lostHeat + " heat");
//            return;
//        }
//
//        for (LavaVector newLavaVector : getAllActualWays(vector, oneDirectionStepCount)) {
//            lavaWaySmart(y + newLavaVector.yDirection, x + newLavaVector.xDirection, newLavaVector,
//                    newLavaVector.equals(vector) ? oneDirectionStepCount + 1 : 1, lostHeat);
//        }
//
//
//    }
//
//    public static void lavaWay(int y, int x, LavaVector vector, int oneDirectionStepCount, int stepCount, int lostHeat, boolean[][] alreadyPassed) {
//        if (!inCity(y, x)) return;
//        boolean[][] matrix = copyArr(alreadyPassed);
//        if (matrix[y][x]) return;
//        matrix[y][x] = true;
//        if (oneDirectionStepCount > 3) return;
//        lostHeat += arr[y][x];
//        if (lostHeat > MinHeatLost && stepCount > MAX_STEPS) return;
//
//        if (y == HEIGHT - 1 && x == WIGHT - 1) {
//            if (lostHeat < MinHeatLost) {
//                System.out.println(" we found some way - and lost only " + lostHeat + " heat");
//            }
//            return;
//        }
//        for (LavaVector newLavaVector : getAllActualWays(vector, oneDirectionStepCount)) {
//            lavaWay(y + newLavaVector.yDirection, x + newLavaVector.xDirection, newLavaVector,
//                    newLavaVector.equals(vector) ? oneDirectionStepCount + 1 : 1, stepCount++, lostHeat, matrix);
//        }
//
//
//    }
//
//    public static boolean[][] copyArr(boolean[][] array) {
//        boolean[][] tempLink = new boolean[HEIGHT][WIGHT];
//        for (int y = 0; y < HEIGHT; y++) {
//            for (int x = 0; x < WIGHT; x++) {
//                tempLink[y][x] = array[y][x];
//            }
//
//        }
//        return tempLink;
//    }
//
//    public static List<LavaVector> getAllActualWays(LavaVector vector, int count) {
//        List<LavaVector> tempList = new ArrayList<>();
//        if (count < 3) tempList.add(new LavaVector(vector.yDirection, vector.xDirection));
//        if (vector.xDirection == 0) {
//            tempList.add(new LavaVector(0, -1));
//            tempList.add(new LavaVector(0, 1));
//        }
//        if (vector.yDirection == 0) {
//            tempList.add(new LavaVector(-1, 0));
//            tempList.add(new LavaVector(1, 0));
//        }
//        return tempList;
//    }
//
//    public static boolean inCity(int y, int x) {
//        return y > -1 && y < HEIGHT && x > -1 && x < WIGHT;
//    }
//
//
//    public static int[][] getArrayFromFile() throws IOException {
//        List<String> list = Files.readAllLines(Path.of("src/main/resources/lava.txt"));
//        int[][] arr = new int[list.size()][list.getFirst().length()];
//        for (int y = 0; y < arr.length; y++) {
//            String[] temp = list.get(y).split("");
//            for (int x = 0; x < arr[0].length; x++) {
//                arr[y][x] = Integer.parseInt(temp[x]);
//            }
//        }
//        return arr;
//    }
//
//    public static int getDefaultHeatLost() {
//        int sum = 0;
//        for (int y = 0; y < HEIGHT; y++) {
//            sum += arr[y][y];
//            if ((y + 1) < HEIGHT) sum += arr[y + 1][y];
//        }
//        return sum - arr[0][0];
//    }
//
//
//    public static void printArray(int[][] arr) {
//        for (int y = 0; y < HEIGHT; y++) {
//            for (int x = 0; x < WIGHT; x++) {
//                System.out.print(arr[y][x] + " ");
//            }
//            System.out.println();
//        }
//    }
//
//
//}
