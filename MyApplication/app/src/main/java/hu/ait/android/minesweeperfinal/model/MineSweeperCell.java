package hu.ait.android.minesweeperfinal.model;

/**
 * Created by ruthwu on 3/1/16.
 */
public class MineSweeperCell {

    int numberAdjacent;
    boolean isFlagged;
    boolean isAdjacent;
    boolean isBomb;
    boolean isCovered;

    public MineSweeperCell(int numberAdjacent, boolean isFlagged, boolean isAdjacent, boolean isCovered, boolean isBomb) {
        this.numberAdjacent = numberAdjacent;
        this.isFlagged = isFlagged;
        this.isAdjacent = isAdjacent;
        this.isCovered = isCovered;
        this.isBomb = isBomb;
    }

    public boolean flagStatus() {
        return this.isFlagged;
    }

    public boolean adjacentStatus() {
        return this.isAdjacent;
    }

    public boolean bombStatus() {

        return this.isBomb;
    }

    public boolean coveredStatus() {
        return this.isCovered;
    }

    public int adjacentNum() {
        return this.numberAdjacent;
    }
}
