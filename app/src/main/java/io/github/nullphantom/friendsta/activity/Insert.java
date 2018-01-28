package io.github.nullphantom.friendsta.activity;

import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.channels.FileChannel;

import io.github.nullphantom.friendsta.Database.FriendsOperation;
import io.github.nullphantom.friendsta.R;
import io.github.nullphantom.friendsta.friends.Friends;

public class Insert extends AppCompatActivity {

    private FriendsOperation friendData;
    RadioGroup jk;
    RadioButton kelamin, laki, perempuan;
    String imageName = "icon";
    EditText nama, email, no_hp;
    public static final int SELECT_IMAGE = 1;
    String mSelectedImagePath;
    boolean insert = false;
    Friends f;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setTitle("Insert Friends");

        nama = findViewById(R.id.nama);
        email = findViewById(R.id.email);
        no_hp = findViewById(R.id.no_hp);
        jk = findViewById(R.id.jenis_kelamin);

        laki = findViewById(R.id.laki_laki);
        perempuan = findViewById(R.id.perempuan);

        friendData = new FriendsOperation(this);

        if( getIntent().getExtras() != null)
        {
            insert=true;
            Intent id = getIntent();
            Long id_friend = id.getLongExtra("id",0);
            Log.e("Halo : ","halo jakarta");
            friendData.open();
            f = friendData.getFriend(id_friend);
            friendData.close();

            f.setFriendsId(id_friend);
            nama.setText(f.getNama());
            email.setText(f.getEmail());
            no_hp.setText(f.getNo_hp());
        }
    }

    public void pilihGambar(View v) {}

    public void simpan(View v) {
        String snama = nama.getText().toString();
        String semail = email.getText().toString();
        String snohp= no_hp.getText().toString();

        friendData.open();
        if (insert) {
            Log.e("di insert : ",snama);
            f.setNama(snama);
            f.setEmail(semail);
            f.setNo_hp(snohp);
            f.setGender("L");
            f.setGambar("friends_icon");
            Log.e("di insert 2 : ",String.valueOf(f.getFriendsId()));
            friendData.updateFriend(f);
        }else{
            Friends friend = new Friends(snama,"friends_icon",semail,snohp,"L");
            Log.e("Name",friend.getNama());
            friendData.addFriend(friend);
        }
        friendData.close();

        Intent i = new Intent(this,MainActivity.class);
        startActivity(i);
    }

    @Override
    public void onBackPressed() {
        Intent i = new Intent(this,MainActivity.class);
        startActivity(i);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish(); // close this activity and return to preview activity (if there is any)
        }
        return super.onOptionsItemSelected(item);
    }
}