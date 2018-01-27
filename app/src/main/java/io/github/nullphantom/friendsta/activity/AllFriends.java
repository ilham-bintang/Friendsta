package io.github.nullphantom.friendsta.activity;

import android.content.Intent;
import android.content.res.Resources;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.util.TypedValue;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import io.github.nullphantom.friendsta.Database.FriendsOperation;
import io.github.nullphantom.friendsta.R;
import io.github.nullphantom.friendsta.friends.AllFriendsAdapter;
import io.github.nullphantom.friendsta.friends.Friends;
import io.github.nullphantom.friendsta.friends.FriendsAdapter;

public class AllFriends extends AppCompatActivity {

    private ListView listView;
    private AllFriendsAdapter adapter;
    private List<Friends> friendsList;
    private FriendsOperation friendData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_friends);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setTitle("All Friends");

        listView = findViewById(R.id.list_view);
        friendsList = new ArrayList<>();

        friendData = new FriendsOperation(this);
        friendData.open();
        friendsList = friendData.getFriends();
        friendData.close();

        adapter = new AllFriendsAdapter(this, friendsList);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent in = new Intent(AllFriends.this,Profil.class);
                Long x = listView.getItemIdAtPosition(i);
                in.putExtra("id", x+1);
                startActivity(in);
            }
        });
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
}