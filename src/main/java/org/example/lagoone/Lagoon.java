package org.example.lagoone;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;

public class Lagoon {
    static boolean[][] array;
    static int HEIGHT = 0;
    static int WIGHT = 0;


    public static void main(String[] args) throws IOException {
        int verticalSizePlus = 0;
        int verticalSizeMinus = 0;
        int horizontalSizePlus = 0;
        int horizontalSizeMinus = 0;

        List<Coordinate> allCoordinates = new ArrayList<>();

        Coordinate start = new Coordinate(0, 0);
        allCoordinates.add(start);

        List<DiggerCommand> diggerCommands = getCommandList();
        for (var command : diggerCommands) {
            for (int i = 0; i < command.stepCounts; i++) {
                start = new Coordinate(start.y + command.vector.yDirection, start.x + command.vector.xDirection);
                allCoordinates.add(start);
                if (start.x > 0 && horizontalSizePlus < start.x) horizontalSizePlus = start.x;
                else if (start.x < 0 && horizontalSizeMinus > start.x) horizontalSizeMinus = start.x;
                if (start.y > 0 && verticalSizePlus < start.y) verticalSizePlus = start.y;
                else if (start.y < 0 && verticalSizeMinus > start.y) verticalSizeMinus = start.y;
            }
        }
//        for (var c : allCoordinates) System.out.println(c);

        System.out.println(verticalSizePlus + "\t" + verticalSizeMinus);
        System.out.println(horizontalSizePlus + "\t" + horizontalSizeMinus);

        HEIGHT = verticalSizePlus - verticalSizeMinus + (verticalSizeMinus <= 0 ? 1 : 0);
        System.out.println("ver size = " + HEIGHT);
        WIGHT = horizontalSizePlus - horizontalSizeMinus + (horizontalSizeMinus <= 0 ? 1 : 0);
        System.out.println("hor size = " + WIGHT);

        array = new boolean[HEIGHT][WIGHT];
        for (var coordinate : allCoordinates) {
            array[verticalSizePlus - coordinate.y][-(horizontalSizeMinus - coordinate.x)] = true;
        }
//        printArray(array);

        int innerY = -1;
        int innerX = -1;

        for (int y = 0; y < HEIGHT && innerX < 0; y++) {
            for (int x = 0; x < WIGHT && innerX < 0; x++) {
                if (!array[y][x] && iAmIn(y, x)) {
                    innerX = x;
                    innerY = y;
                }
            }
        }

        System.out.println("inner " + innerY + "\t" + innerX);

//        boolean[][] arrayResult = Arrays.copyOf(array);
//        for (int y = 0; y < HEIGHT; y++) {
//            for (int x = 0; x < WIGHT; x++) {
//                if (iAmIn(y,x))
//            }
//
//        }

        fillArray(innerY, innerX);
//        printArray(array);

        int finalRes = 0;
        for (int y = 0; y < HEIGHT; y++) {
            for (int x = 0; x < WIGHT; x++) {
                if (array[y][x]) finalRes++;
            }

        }

        System.out.println("RESULT IS" + finalRes);


    }

    public static void fillArray(int y, int x) {
//        if (y < 0 || y >= HEIGHT || x < 0 || x >= WIGHT) return;
        Deque<Cell> queue = new ArrayDeque<>();
        boolean[][] inQueue = new boolean[HEIGHT][WIGHT];
        queue.add(new Cell(y, x));
        while (!queue.isEmpty()) {
            Cell currentCell = queue.pop();
            array[currentCell.y][currentCell.x] = true;
            for (Cell c : getFreeNeighbors(currentCell)) {
                if (!inQueue[c.y][c.x]) {
                    queue.add(c);
                    inQueue[c.y][c.x] = true;
                }
            }
        }


    }

    public static List<Cell> getFreeNeighbors(Cell innerCell) {
        List<Cell> result = new ArrayList<>();
        for (Cell currentCell : innerCell.getCellNeighbors()) {
            if (inField(currentCell) && !array[currentCell.y][currentCell.x]) result.add(currentCell);
        }
        return result;
    }

    public static boolean inField(Cell cell) {
        return cell.y >= 0 && cell.y < HEIGHT && cell.x >= 0 && cell.x < WIGHT;
    }

    public class FillInTread extends Thread {
        int y;
        int yD;
        int x;
        int xD;


        public FillInTread(int y, int yD, int x, int xD) {
            this.y = y;
            this.yD = yD;
            this.x = x;
            this.xD = xD;
            start();
        }

        @Override
        public void run() {
            if (y < 0 && y >= HEIGHT && x < 0 && x >= WIGHT) return;
            if (array[y][x]) return;
            else {
                array[y][x] = true;
//                new FillInTread(y, x )
                fillArray(y + 1, x);
                fillArray(y - 1, x);
                fillArray(y, x + 1);
                fillArray(y, x - 1);
            }
        }
    }

    public static boolean iAmIn(int y, int x) {
        boolean leftOk = thisSideOk(y, 0, x, -1);
        boolean rightOk = thisSideOk(y, 0, x, 1);
        boolean upOk = thisSideOk(y, 1, x, 0);
        boolean downOk = thisSideOk(y, -1, x, 0);
        return leftOk && rightOk && upOk && downOk;
    }

    public static boolean thisSideOk(int y, int yD, int x, int xD) {
        while (y > 0 && y < HEIGHT && x > 0 && x < WIGHT) {
            y = y + yD;
            x = x + xD;
            if (array[y][x]) return true;
        }
        return false;
    }


    public static List<DiggerCommand> getCommandList() throws IOException {
        List<String> list = Files.readAllLines(Path.of("src/main/resources/lag"));
        List<DiggerCommand> commandList = new ArrayList<>();
        for (String s : list) {
            String[] tempAtt = s.split(" ");

            commandList.add(new DiggerCommand(getVectorBySymbol(tempAtt[0]), Integer.parseInt(tempAtt[1])));
        }
        return commandList;
    }

    public static class Coordinate {
        public int y;
        public int x;

        public Coordinate(int y, int x) {
            this.y = y;
            this.x = x;
        }

        @Override
        public String toString() {
            return "Coordinate{" +
                    "y=" + y +
                    ", x=" + x +
                    '}';
        }
    }

    public static class DiggerCommand {
        DiggerVector vector;
        int stepCounts;

        public DiggerCommand(DiggerVector vector, int stepCounts) {
            this.vector = vector;
            this.stepCounts = stepCounts;
        }

        @Override
        public String toString() {
            return "DiggerCommand{" +
                    "vector=" + vector +
                    ", stepCounts=" + stepCounts +
                    '}';
        }
    }

    public static DiggerVector getVectorBySymbol(String s) {
        return switch (s) {
            case "R" -> new DiggerVector(0, 1);
            case "L" -> new DiggerVector(0, -1);
            case "U" -> new DiggerVector(1, 0);
            case "D" -> new DiggerVector(-1, 0);
            default -> null;
        };

    }

    public static void printArray(boolean[][] arr) {
        System.out.println("---------------------------------------------------");
        for (boolean[] booleans : arr) {
            for (int x = 0; x < arr[0].length; x++) {
                if (booleans[x]) System.out.print("#");
                else System.out.print(" ");
            }
            System.out.println();
        }
    }
}
