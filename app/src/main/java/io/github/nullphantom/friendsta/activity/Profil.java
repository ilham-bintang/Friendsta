package io.github.nullphantom.friendsta.activity;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

import io.github.nullphantom.friendsta.Database.FriendsOperation;
import io.github.nullphantom.friendsta.R;
import io.github.nullphantom.friendsta.friends.Friends;

public class Profil extends AppCompatActivity {

    private List<Friends> friendsList;
    private FriendsOperation friendData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profil);

        Intent id = getIntent();
        Long id_friend = id.getLongExtra("id",0);

        friendData = new FriendsOperation(this);
        friendData.open();
        Friends f = friendData.getFriend(id_friend);
        friendData.close();

        ImageView gambar = findViewById(R.id.gambar);
        TextView nama = findViewById(R.id.nama);
        TextView email = findViewById(R.id.email);
        TextView no_hp = findViewById(R.id.hp);
        TextView gender = findViewById(R.id.gender);

        Glide.with(Profil.this).load(getImageId(this, f.getGambar())).into(gambar);
        nama.setText(f.getNama());
        email.setText(f.getEmail());
        no_hp.setText(f.getNo_hp());
        gender.setText(f.getGender());

    }
    public void goHome(View v) {
        Intent i = new Intent(this,MainActivity.class);
        startActivity(i);
    }
    @Override
    public void onBackPressed() {
        Intent i = new Intent(this,MainActivity.class);
        startActivity(i);
    }

    public static int getImageId(Context context, String imageName) {
        return context.getResources().getIdentifier("drawable/" + imageName, null, context.getPackageName());
    }
}
