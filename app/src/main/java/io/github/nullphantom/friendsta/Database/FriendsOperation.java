package io.github.nullphantom.friendsta.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import io.github.nullphantom.friendsta.friends.Friends;

/**
 * Created by Ilham Bintang on 27/01/2018.
 */

public class FriendsOperation {
    public static final String LOGTAG = "EMP_MNGMNT_SYS";

        SQLiteOpenHelper dbhandler;
        SQLiteDatabase database;

        private static final String[] allColumns = {
                DBHelper.COLUMN_ID,
                DBHelper.COLUMN_NAME,
                DBHelper.COLUMN_GENDER,
                DBHelper.COLUMN_EMAIL,
                DBHelper.COLUMN_NO_HP,
                DBHelper.COLUMN_GAMBAR
        };

        public FriendsOperation(Context context){
            dbhandler = new DBHelper(context);
        }

        public void open(){
            Log.i(LOGTAG,"Database Opened");
            database = dbhandler.getWritableDatabase();
        }
        public void close(){
            Log.i(LOGTAG, "Database Closed");
            dbhandler.close();
        }
        public Friends addFriend(Friends friends){
            ContentValues values  = new ContentValues();
            values.put(DBHelper.COLUMN_NAME,friends.getNama());
            values.put(DBHelper.COLUMN_GENDER,friends.getGender());
            values.put(DBHelper.COLUMN_EMAIL,friends.getEmail());
            values.put(DBHelper.COLUMN_NO_HP,friends.getNo_hp());
            values.put(DBHelper.COLUMN_GAMBAR,friends.getGambar());
            long insertid = database.insert(DBHelper.TABLE_FRIEND,null,values);
            friends.setFriendsId(insertid);
            return friends;
        }

        // Getting single Employee
        public Friends getFriend(long id) {

            Cursor cursor = database.query(DBHelper.TABLE_FRIEND,allColumns,DBHelper.COLUMN_ID + "=?",new String[]{String.valueOf(id)},null,null, null, null);
            if (cursor != null)
                cursor.moveToFirst();

            Friends f = new Friends(cursor.getString(1),cursor.getString(2),cursor.getString(3),cursor.getString(4),cursor.getString(5));
            // return Friends
            return f;
        }

        public List<Friends> getFriends() {

            Cursor cursor = database.query(DBHelper.TABLE_FRIEND,allColumns,null,null,null, null, null);

            List<Friends> friends = new ArrayList<>();
            if(cursor.getCount() > 0){
                while(cursor.moveToNext()){
                    Friends friend = new Friends();
                    friend.setFriendsId(cursor.getLong(cursor.getColumnIndex(DBHelper.COLUMN_ID)));
                    friend.setNama(cursor.getString(cursor.getColumnIndex(DBHelper.COLUMN_NAME)));
                    friend.setGender(cursor.getString(cursor.getColumnIndex(DBHelper.COLUMN_GENDER)));
                    friend.setEmail(cursor.getString(cursor.getColumnIndex(DBHelper.COLUMN_EMAIL)));
                    friend.setNo_hp(cursor.getString(cursor.getColumnIndex(DBHelper.COLUMN_NO_HP)));
                    friend.setGambar(cursor.getString(cursor.getColumnIndex(DBHelper.COLUMN_GAMBAR)));
                    friends.add(friend);
                }
            }
            // return All Employees
            return friends;
        }

        // Updating Employee
        public int updateFriend(Friends friend) {

            ContentValues values = new ContentValues();
            values.put(DBHelper.COLUMN_NAME, friend.getNama());
            values.put(DBHelper.COLUMN_GENDER, friend.getGender());
            values.put(DBHelper.COLUMN_EMAIL, friend.getEmail());
            values.put(DBHelper.COLUMN_NO_HP, friend.getNo_hp());
            values.put(DBHelper.COLUMN_GAMBAR, friend.getGambar());
            // updating row
            return database.update(DBHelper.TABLE_FRIEND, values,
                    DBHelper.COLUMN_ID + "=?",new String[] { String.valueOf(friend.getFriendsId())});
        }

        // Deleting Employee
        public void deleteFriend(long id) {
            database.delete (DBHelper.TABLE_FRIEND, DBHelper.COLUMN_ID + "=" + Long.toString(id), null);
        }
}

