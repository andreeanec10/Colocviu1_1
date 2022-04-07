package ro.pub.cs.systems.eim.colocviu1_1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class Colocviu1_1MainActivity extends AppCompatActivity {

    Button north;
    Button east;
    Button west;
    Button south;
    TextView press;
    Button navigate_button;
    Integer number = 0;
    private int serviceStatus = 0;
    IntentFilter intentFilter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_colocviu1_1_main);



        north =findViewById(R.id.north);
        east =findViewById(R.id.east);
        west =findViewById(R.id.west);
        south =findViewById(R.id.south);
        navigate_button =findViewById(R.id.navigate_button);
        press =findViewById(R.id.press);
        press.setText("");

        north.setOnClickListener(v -> {
            number++;
            String message = press.getText().toString();
            if (message == "")
                press.setText("North");
            else
                press.setText(message + ", North");
        });
        east.setOnClickListener(v-> {
            number++;

            String message = press.getText().toString();
            if (message == "")
                press.setText("East");
            else
                press.setText(message + ", East");
        });

        west.setOnClickListener(v-> {
            number++;

            String message = press.getText().toString();
            if (message == "")
                press.setText("West");
            else
                press.setText(message + ", West");
        });
        south.setOnClickListener(v-> {
            number++;

            String message = press.getText().toString();
            if (message == "")
                press.setText("South");
            else
                press.setText(message + ", South");
        });

        navigate_button.setOnClickListener(v->{
            Intent intent = new Intent(getApplicationContext(), Colocviu1_1SecondaryActivity.class);
            intent.putExtra("message", press.getText().toString());
            startActivityForResult(intent, 10);
        });

        if (number == 4 && serviceStatus == 0) {
            Intent intent = new Intent(getApplicationContext(), Colocviu1_1Service.class);
            getApplicationContext().startService(intent);
            serviceStatus = 1;
        }

    }

    private MessageBroadcastReceiver messageBroadcastReceiver = new MessageBroadcastReceiver();
    private class MessageBroadcastReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            Log.d("[BROADCAST]", intent.getStringExtra("message_b"));
        }
    }

    @Override
    protected void onResume() {
        super.onResume();

        registerReceiver(messageBroadcastReceiver, intentFilter);
    }

    @Override
    protected void onPause() {
        unregisterReceiver(messageBroadcastReceiver);
        super.onPause();
    }

    @Override
    protected void onDestroy() {
        Intent intent = new Intent(this, Colocviu1_1Service.class);
        stopService(intent);
        super.onDestroy();
    }

    @SuppressLint("MissingSuperCall")
    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        outState.putInt("number", number);
    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        if (savedInstanceState.containsKey("number")) {
            number = savedInstanceState.getInt("number");
        } else {
            number = 0;
        }
        Log.d("[number]", "" + number);
    }

    @SuppressLint("MissingSuperCall")
    public void onActivityResult(int requestCode, int resultCode, Intent intent) {
        if (requestCode == 10)
            if (resultCode == -1)
                Toast.makeText(this, "Register", Toast.LENGTH_LONG).show();
            else
                Toast.makeText(this, "Cancel", Toast.LENGTH_LONG).show();

    }

}