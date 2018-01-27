package io.github.nullphantom.friendsta.activity;

import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import java.io.File;

import io.github.nullphantom.friendsta.Database.FriendsOperation;
import io.github.nullphantom.friendsta.R;
import io.github.nullphantom.friendsta.friends.Friends;

public class Insert extends AppCompatActivity {

    private FriendsOperation friendData;
    RadioGroup jk;
    RadioButton kelamin;
    String imageName;
    EditText nama, email, no_hp;
    public static final int PICK_IMAGE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setTitle("Insert Friends");

        friendData = new FriendsOperation(this);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish(); // close this activity and return to preview activity (if there is any)
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        Intent i = new Intent(this,MainActivity.class);
        startActivity(i);
    }

    public void pilihGambar(View v) {
//        Intent intent = new Intent();
//        intent.setType("image/*");
//        intent.setAction(Intent.ACTION_GET_CONTENT);
//        startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE);
    }

//    @Override
//    public void onActivityResult(int requestCode, int resultCode, Intent data)
//    {
//        if (requestCode == PICK_IMAGE) {
//            Uri selectedImage = data.getData();
//            String[] filePathColumn = { MediaStore.Images.Media.DATA };
//            Cursor cursor = getContentResolver().query(selectedImage,
//                    filePathColumn, null, null, null);
//            cursor.moveToFirst();
//            int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
//            String picturePath = cursor.getString(columnIndex);
//            cursor.close();
//
//            File f = new File(picturePath);
//
//            imageName = f.getName();
//        }
//    }

    public void simpan(View v) {
        nama = findViewById(R.id.nama);
        email = findViewById(R.id.email);
        no_hp = findViewById(R.id.no_hp);

        jk = findViewById(R.id.jenis_kelamin);

        int jenis = jk.getCheckedRadioButtonId();
        kelamin = findViewById(jenis);
        String jenkel = kelamin.getText().toString();

        Friends friend = new Friends(nama.getText().toString(),"friends_icon",email.getText().toString(),no_hp.getText().toString(),jenkel);

        friendData.open();
        friendData.addFriend(friend);
        friendData.close();

        Intent i = new Intent(this,MainActivity.class);
        startActivity(i);
    }
}
