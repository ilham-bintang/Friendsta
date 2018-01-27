package io.github.nullphantom.friendsta.friends;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

import io.github.nullphantom.friendsta.R;
import io.github.nullphantom.friendsta.activity.MainActivity;

/**
 * Created by Ilham Bintang on 26/01/2018.
 */

public class FriendsAdapter extends RecyclerView.Adapter<FriendsAdapter.MyViewHolder> {

    private Context mContext;
    private List<Friends> friendsList;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView name, hp;
        public ImageView thumbnail, overflow;

        public MyViewHolder(View view) {
            super(view);
            name = view.findViewById(R.id.friends_name);
            hp = view.findViewById(R.id.no_hp);
            thumbnail = view.findViewById(R.id.thumbnail);
        }
    }

    public FriendsAdapter(Context mContext, List<Friends> friendsList) {
        this.mContext = mContext;
        this.friendsList = friendsList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_grid_layout, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {
        Friends friends = friendsList.get(position);
        holder.name.setText(friends.getNama());
        holder.hp.setText(friends.getNo_hp());

        Glide.with(mContext).load(getImageId(mContext, friends.getGambar())).into(holder.thumbnail);

    }

    @Override
    public int getItemCount() {
        return friendsList.size();
    }

    public static int getImageId(Context context, String imageName) {
        return context.getResources().getIdentifier("drawable/" + imageName, null, context.getPackageName());
    }
}
