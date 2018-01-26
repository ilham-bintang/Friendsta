package io.github.nullphantom.friendsta.activity;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import io.github.nullphantom.friendsta.R;

public class Login extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }

    public void login(View v) {
        keHalaman(getApplicationContext(), MainActivity.class);
    }

    public void keHalaman(Context c, Class cls) {
        Intent i = new Intent(c,cls);
        startActivity(i);
    }
}
