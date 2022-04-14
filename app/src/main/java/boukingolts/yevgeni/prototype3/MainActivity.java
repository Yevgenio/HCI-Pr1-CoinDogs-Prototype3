package boukingolts.yevgeni.prototype3;


import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    private Button option1;
    private Button option2;
    private Button option3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();

        option1 = (Button) findViewById(R.id.option1);
        option1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openOption1();
            }
        });

        option2 = (Button) findViewById(R.id.option2);
        option2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openOption2();
            }
        });

        option3 = (Button) findViewById(R.id.option3);
        option3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openOption3();
            }
        });
    }


    public void openOption1() {
        Intent intent = new Intent(this, StartRunning.class);
        startActivity(intent);
    }

    public void openOption2() {
        Intent intent = new Intent(this, Table.class);
        startActivity(intent);
    }

    public void openOption3() {
        Intent intent = new Intent(this, Settings.class);
        startActivity(intent);
    }
}