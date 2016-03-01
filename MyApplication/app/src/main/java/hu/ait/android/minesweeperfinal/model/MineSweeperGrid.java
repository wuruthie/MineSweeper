package hu.ait.android.minesweeperfinal.model;

/**
 * Created by ruthwu on 3/1/16.
 */
public class MineSweeperGrid {

    private static MineSweeperGrid instance = null;

    private MineSweeperGrid() {
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
}
