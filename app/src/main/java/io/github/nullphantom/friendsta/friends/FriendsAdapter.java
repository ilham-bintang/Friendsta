package io.github.nullphantom.friendsta.friends;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.util.List;

import io.github.nullphantom.friendsta.Database.FriendsOperation;
import io.github.nullphantom.friendsta.R;
import io.github.nullphantom.friendsta.activity.AllFriends;
import io.github.nullphantom.friendsta.activity.Insert;
import io.github.nullphantom.friendsta.activity.MainActivity;
import io.github.nullphantom.friendsta.activity.Profil;

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
            overflow = (ImageView) view.findViewById(R.id.overflow);
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
        final Friends friends = friendsList.get(position);
        holder.name.setText(friends.getNama());
        holder.hp.setText(friends.getNo_hp());
        Glide.with(mContext).load(getImageId(mContext, friends.getGambar())).into(holder.thumbnail);

        holder.thumbnail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent in = new Intent(mContext, Profil.class);
                Long x = friends.getFriendsId();
                Log.e("Item ID",x.toString());
                in.putExtra("id", x);
                mContext.startActivity(in);
            }
        });

        holder.overflow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Long x = friends.getFriendsId();
                showPopupMenu(holder.overflow,x);
            }
        });
    }
    private void showPopupMenu(View view, long id) {
        // inflate menu
        PopupMenu popup = new PopupMenu(mContext, view);
        MenuInflater inflater = popup.getMenuInflater();
        inflater.inflate(R.menu.card_menu, popup.getMenu());
        popup.setOnMenuItemClickListener(new MyMenuItemClickListener(id));
        popup.show();
    }

    class MyMenuItemClickListener implements PopupMenu.OnMenuItemClickListener {
        long id;
        FriendsOperation friendData;
        Friends f;

        public MyMenuItemClickListener(long id) {
            this.id =id;
        }

        @Override
        public boolean onMenuItemClick(MenuItem menuItem) {
            switch (menuItem.getItemId()) {
                case R.id.delete:

                    friendData = new FriendsOperation(mContext);
                    friendData.open();
                    friendData.deleteFriend(id);
                    friendData.close();
                    Toast.makeText(mContext, "Berhasil di hapus : " + id, Toast.LENGTH_SHORT).show();
                    Intent i = new Intent(mContext,MainActivity.class);
                    mContext.startActivity(i);
                    return true;
                case R.id.edit:
                    Intent in = new Intent(mContext,Insert.class);
                    in.putExtra("id",id);
                    mContext.startActivity(in);
                    return true;
                default:
            }
            return false;
        }
    }

    @Override
    public int getItemCount() {
        return friendsList.size();
    }

    public static int getImageId(Context context, String imageName) {
        return context.getResources().getIdentifier("drawable/" + imageName, null, context.getPackageName());
    }
}
