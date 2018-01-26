package io.github.nullphantom.friendsta.activity;

import android.content.res.Resources;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.TypedValue;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import io.github.nullphantom.friendsta.R;
import io.github.nullphantom.friendsta.friends.AllFriendsAdapter;
import io.github.nullphantom.friendsta.friends.Friends;
import io.github.nullphantom.friendsta.friends.FriendsAdapter;

public class AllFriends extends AppCompatActivity {

    private ListView listView;
    private AllFriendsAdapter adapter;
    private List<Friends> friendsList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_friends);

        listView = findViewById(R.id.list_view);
        friendsList = new ArrayList<>();
        adapter = new AllFriendsAdapter(this, friendsList);

        listView.setAdapter(adapter);

    }

    private int dpToPx(int dp) {
        Resources r = getResources();
        return Math.round(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, r.getDisplayMetrics()));
    }
}
