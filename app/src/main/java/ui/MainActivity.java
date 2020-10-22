package ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.projetointegrador.ItensList;
import com.example.projetointegrador.R;

public class MainActivity extends AppCompatActivity {

    private Button buttonNextActivity;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        buttonClick();
    }

    private void buttonClick(){
        buttonNextActivity = findViewById(R.id.buttonNextActivity);
        buttonNextActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ItensList.class);
                startActivity(intent);
            }
        });
    }
}