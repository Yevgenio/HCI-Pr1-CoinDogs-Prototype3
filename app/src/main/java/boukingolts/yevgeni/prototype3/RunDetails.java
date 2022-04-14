package boukingolts.yevgeni.prototype3;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ScrollView;

public class RunDetails extends AppCompatActivity {

    private Button option1;
    private ScrollView option2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_run_details);
        getSupportActionBar().hide();

        option1 = (Button) findViewById(R.id.option1);
        option1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openOption1();
            }
        });

        option2 = (ScrollView) findViewById(R.id.scrollView);
        option2.getChildAt(0).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openOption2();
            }
        });

    }

    public void openOption1() {
        Intent intent = new Intent(this, RunSummary.class);
        startActivity(intent);
    }

    public void openOption2() {
        finish();
//        Intent intent = new Intent(this, Navigator.class);
//        startActivity(intent);
    }
}