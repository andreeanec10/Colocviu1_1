package ro.pub.cs.systems.eim.colocviu1_1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;

public class Colocviu1_1SecondaryActivity extends AppCompatActivity {

    TextView text_send;
    Button register;
    Button cancel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_colocviu11_secondary);

        text_send = findViewById(R.id.test_send);
        register = findViewById(R.id.register);
        cancel = findViewById(R.id.cancel);


        Intent intent = getIntent();
        String string = intent.getStringExtra("message");
        text_send.setText(string);

        register.setOnClickListener(v->{
            setResult(RESULT_OK, null);
            finish();
        });
        cancel.setOnClickListener(v->{
            setResult(RESULT_CANCELED, null);
            finish();
        });

    }
}