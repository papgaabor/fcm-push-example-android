package com.wanari.fcmexample;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;

import com.google.firebase.iid.FirebaseInstanceId;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "Main activity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FirebaseIDService.addTokenRefreshListener(new TokenRefreshListener() {
            @Override
            public void onTokenRefresh(final String newToken) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        EditText editText = (EditText) findViewById(R.id.editText);
                        editText.setText(newToken);
                    }
                });
            }
        });

        String token = FirebaseInstanceId.getInstance().getToken();
        if (token != null) {
            EditText editText = (EditText) findViewById(R.id.editText);
            editText.setText(token);
        }
    }
}
