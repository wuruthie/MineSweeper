package hu.ait.android.minesweeperfinal.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

/**
 * Created by ruthwu on 3/1/16.
 */
public class MineSweeperGrid {

    private static MineSweeperGrid instance = null;
    private MineSweeperGrid() {
        resetGrid();
    }

    public static MineSweeperGrid getInstance() {
        if (instance == null) {
            instance = new MineSweeperGrid();
        }
        return instance;
    }

    private MineSweeperCell[][] grid = new MineSweeperCell[5][5];

    public MineSweeperCell getFieldContent(int x, int y) {
        return grid[x][y];
    }

    public MineSweeperCell setFieldContent(int x, int y, MineSweeperCell content) {
        return grid[x][y] = content;
    }

    public void placeNumbers(){
        for(int i = 0; i < 5; i++){
            for(int j = 0; j< 5; j++){
                if(getFieldContent(i, j).bombStatus() == false){
                    int numAdjacent = getTryNumber(i, j);
                    MineSweeperCell numUpdatedCell = new MineSweeperCell(numAdjacent, false, true, true, false);
                    setFieldContent(i, j, numUpdatedCell);
                }
            }
        }
    }

    public int getTryNumber(int x, int y) {
        int numberOfMines = 0;

        int xStart = x - 1;
        if (xStart < 0) {
            xStart = x;
        }
        int xEnd = x + 1;
        if (xEnd > 4) {
            xEnd = x;
        }

        int yStart = y - 1;
        if (yStart < 0) {
            yStart = y;
        }

        int yEnd = y + 1;
        if (yEnd > 4) {
            yEnd = y;
        }

        for (int i = xStart; i < xEnd + 1; i++) {
            for (int j = yStart; j < yEnd + 1; j++) {
                if (getFieldContent(i,j).bombStatus() == true) {
                    numberOfMines++;
                }
            }
        }
        return numberOfMines;
    }

    public void placeMines() {
        int x;
        int y;
        List<Integer> solution = new ArrayList<>();
        for (int i = 0; i < 5; i++)
        {
            solution.add(i);
        }

        Random rand = new Random();

        for (int i = 0; i < 3; i++) {
            Collections.shuffle(solution);
            x = solution.get(i);
            y = rand.nextInt(5);
            //creating new bomb to add
            MineSweeperCell newCell = new MineSweeperCell(0, false, false, true, true);
            setFieldContent(x, y, newCell);
        }
    }

    public void resetGrid() {
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                grid[i][j] = new MineSweeperCell(0, false, false, true, false);
            }
        }
    }
}
