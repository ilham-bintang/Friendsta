package io.github.nullphantom.friendsta.friends;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import io.github.nullphantom.friendsta.R;

/**
 * Created by Ilham Bintang on 26/01/2018.
 */

public class AllFriendsAdapter extends ArrayAdapter<Friends> {

        List<Friends> dataList;
        Context context;
        private LayoutInflater mInflater;

        public AllFriendsAdapter (Context context, List<Friends> objects) {
            super(context, 0, objects);
            this.context = context;
            this.mInflater = LayoutInflater.from(context);
            dataList = objects;
        }

        @Nullable
        @Override
        public Friends getItem(int position) {
            return dataList.get(position);
        }

        @NonNull
        @Override
        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
            final ViewHolder vh;
            if (convertView == null) {
                View view = mInflater.inflate(R.layout.item_all_friends, parent, false);
                vh = ViewHolder.create((RelativeLayout) view);
                view.setTag(vh);
            } else {
                vh = (ViewHolder) convertView.getTag();
            }

            Friends item = getItem(position);

            vh.textViewName.setText(item.getNama());
            vh.textViewEmail.setText(item.getEmail());
            vh.textViewNoHp.setText(item.getNo_hp());
            Picasso.with(context).load(getImageId(context, item.getGambar())).placeholder(android.R.drawable.ic_input_add).error(android.R.drawable.btn_default_small).into(vh.imageView);

            return vh.rootView;
        }

        private static class ViewHolder {
            public final RelativeLayout rootView;
            public final ImageView imageView;
            public final TextView textViewName;
            public final TextView textViewEmail;
            public final TextView textViewNoHp;

            private ViewHolder(RelativeLayout rootView, TextView textViewName, ImageView imageView,TextView textViewEmail, TextView textViewNoHp) {
                this.rootView = rootView;
                this.imageView = imageView;
                this.textViewName = textViewName;
                this.textViewEmail = textViewEmail;
                this.textViewNoHp = textViewNoHp;
            }

            public static ViewHolder create(RelativeLayout rootView) {
                ImageView imageView = rootView.findViewById(R.id.gambar);
                TextView textViewName = rootView.findViewById(R.id.nama);
                TextView textViewEmail = rootView.findViewById(R.id.email);
                TextView textViewNoHp = rootView.findViewById(R.id.no_hp);
                return new ViewHolder(rootView, textViewName, imageView, textViewEmail, textViewNoHp);
            }
        }

        public static int getImageId(Context context, String imageName) {
            return context.getResources().getIdentifier("drawable/" + imageName, null, context.getPackageName());
        }
    }