package hu.ait.android.minesweeperfinal.model;

/**
 * Created by ruthwu on 3/1/16.
 */
public class MineSweeperCell {

    int numberAdjacent;
    boolean isFlagged;
    boolean isAdjacent;
    boolean isBomb;

    MineSweeperCell(int numberAdjacent, boolean isFlagged, boolean isAdjacent, boolean isBomb){
        this.numberAdjacent = numberAdjacent;
        this.isFlagged = isFlagged;
        this.isAdjacent = isAdjacent;
        this.isBomb = isBomb;
    }
}
