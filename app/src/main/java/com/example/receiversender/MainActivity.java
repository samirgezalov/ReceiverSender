package com.example.receiversender;

import androidx.appcompat.app.AppCompatActivity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.widget.EditText;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity {

    EditText et;
    TextView tv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        et = findViewById(R.id.edittext);
        tv = findViewById(R.id.text);

        registerReceiver(br, new IntentFilter("SAMmessage"));
        et.addTextChangedListener(new TextWatcher() {

            public void afterTextChanged(Editable s) {
            }

            public void beforeTextChanged(CharSequence s, int start,
                                          int count, int after) {
            }

            public void onTextChanged(CharSequence s, int start,
                                      int before, int count) {
                Intent intent = new Intent("SAMmessage");

                intent.putExtra("updatedTV", et.getText().toString());

                sendBroadcast(intent);
                Log.i("Testing", "message sent");

//                tv.setText(et.getText());
            }
        });

    }









    private BroadcastReceiver br = new BroadcastReceiver() {

        @Override
        public void onReceive(Context context, Intent intent) {
            Log.i("Testing", "Msg Received");

            tv.setText(intent.getStringExtra("updatedTV"));
        }

    };
    public void onDestroy() {
        super.onDestroy();
        unregisterReceiver(br);
    }
}
