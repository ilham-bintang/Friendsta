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
    RadioButton kelamin;
    String imageName;
    EditText nama, email, no_hp;
    public static final int SELECT_IMAGE = 1;
    String mSelectedImagePath;

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
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent,"Select Picture"), SELECT_IMAGE);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            switch(requestCode) {
                case SELECT_IMAGE:
                    mSelectedImagePath = getPath(data.getData());
                    System.out.println("mSelectedImagePath : " + mSelectedImagePath);
                    try {
                        File sd = Environment.getExternalStorageDirectory();
                        if (sd.canWrite()) {
                            System.out.println("(sd.canWrite()) = " + (sd.canWrite()));
                            String destinationImagePath= "/file.jpg";   // this is the destination image path.
                            File source = new File(mSelectedImagePath );
                            imageName = source.getName();
                            File destination= new File(sd, destinationImagePath);
                            if (source.exists()) {
                                FileChannel src = new FileInputStream(source).getChannel();
                                FileChannel dst = new FileOutputStream(destination).getChannel();
                                dst.transferFrom(src, 0, src.size());       // copy the first file to second.....
                                src.close();
                                dst.close();
                                Toast.makeText(getApplicationContext(), "Check the copy of the image in the same path as the gallery image. File is name file.jpg", Toast.LENGTH_LONG).show();
                            }
                        }else{
                            Toast.makeText(getApplicationContext(), "SDCARD Not writable.", Toast.LENGTH_LONG).show();
                        }
                    }catch (Exception e) {
                        System.out.println("Error :" + e.getMessage());
                    }
                    break;
            }
        }
    }

    public String getPath(Uri uri) {
        String[] projection = { MediaStore.Images.Media.DATA };
        Cursor cursor = managedQuery(uri, projection, null, null, null);
        startManagingCursor(cursor);
        int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
        cursor.moveToFirst();
        return cursor.getString(column_index);
    }

    public void simpan(View v) {
        nama = findViewById(R.id.nama);
        email = findViewById(R.id.email);
        no_hp = findViewById(R.id.no_hp);

        jk = findViewById(R.id.jenis_kelamin);

        int jenis = jk.getCheckedRadioButtonId();
        kelamin = findViewById(jenis);
        String jenkel = kelamin.getText().toString();

        Log.e("Name",imageName);

        Friends friend = new Friends(nama.getText().toString(),"friends_icon",email.getText().toString(),no_hp.getText().toString(),jenkel);

        friendData.open();
        friendData.addFriend(friend);
        friendData.close();

        Intent i = new Intent(this,MainActivity.class);
        startActivity(i);
    }
}
