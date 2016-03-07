package hu.ait.android.minesweeperfinal;

import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import hu.ait.android.minesweeperfinal.view.MineSweeperView;

public class MainActivity extends AppCompatActivity {
    private boolean toggle = false;

    private MineSweeperView gameField;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        gameField = (MineSweeperView) findViewById(R.id.gameField);

        Button btnRestart = (Button) findViewById(R.id.btnRestart);
        btnRestart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // clear the game field
                gameField.clearGameField();
            }
        });

        Button btnFlag = (Button) findViewById(R.id.btnFlag);
        btnFlag.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (toggle) {
                    toggle = false;
                    gameField.setFlagMode(toggle);
                } else {
                    toggle = true;
                    gameField.setFlagMode(toggle);
                }
            }
        });
    }

    public void showSnacbarMessage(String msg){
        Snackbar.make(gameField, msg, Snackbar.LENGTH_LONG).setAction(
                R.string.action_cancel, new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        //do some code here
                        gameField.clearGameField();

                    }
                }
        ).show();
    }
}


