package hu.ait.android.minesweeperfinal;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import hu.ait.android.minesweeperfinal.view.MineSweeperView;
public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        setContentView(R.layout.activity_main);

        final MineSweeperView gameField =
                (MineSweeperView) findViewById(R.id.gameField);
        Button btnRestart = (Button) findViewById(R.id.btnRestart);
        btnRestart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // clear the game field
            }
        });
    }
}

