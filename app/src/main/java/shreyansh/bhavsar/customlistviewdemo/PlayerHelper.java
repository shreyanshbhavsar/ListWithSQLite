package shreyansh.bhavsar.customlistviewdemo;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by shreyansh.bhavsar on 7/1/2017.
 */

public class PlayerHelper {


        public String DATABASE_NAME = "players.db";
        public int DATABASE_VERSION = 1;
        public final String TABLE_NAME = "playertable";
        public final String COLUMN_ID = "id";
        public final String COLUMN_NAME = "name";
        public final String COLUMN_RUNS = "runs";
        public final String CREATE_TABLE = "create table "+TABLE_NAME+" ( "+COLUMN_ID+" integer auto_increment, "+COLUMN_NAME+" text, "+COLUMN_RUNS+" integer);";

        PlayerOpenHelper playerOpenHelper;
        Context context;
        SQLiteDatabase db;
        PlayerHelper(Context context)
        {
            this.context=context;
            playerOpenHelper = new PlayerOpenHelper(context);
            db = playerOpenHelper.getWritableDatabase();
        }

        void addPlayer(Player player)
        {
            ContentValues values = new ContentValues();
            values.put(COLUMN_NAME,player.name);
            values.put(COLUMN_RUNS,player.runs);

            long res = db.insert(TABLE_NAME,null,values);
            if(res != -1)
            {
                Toast.makeText(context,"Added",Toast.LENGTH_SHORT).show();
            }
            else
            {
                Toast.makeText(context,"Something Went Wrong",Toast.LENGTH_SHORT).show();

            }

        }

        List<Player> fetchAllPlayers()
        {
            List<Player> players = new ArrayList<>();

            Cursor cursor = db.query(TABLE_NAME,null,null,null,null,null,null);

            if(cursor!=null && cursor.moveToFirst())
            {
                do
                {
                    String name  = cursor.getString(cursor.getColumnIndex(COLUMN_NAME));
                    int runs = cursor.getInt(cursor.getColumnIndex(COLUMN_RUNS));

                    players.add(new Player(name,runs));

                }while(cursor.moveToNext());
            }

            return players;
        }


    void deletePlayer(String name)
    {

        int res=db.delete(TABLE_NAME,COLUMN_NAME+"=?",new String[]{name});

        if(res != -1)
        {
            Toast.makeText(context,"Deleted",Toast.LENGTH_SHORT).show();
            return;
        }
    }








    class PlayerOpenHelper extends SQLiteOpenHelper
    {

        public PlayerOpenHelper(Context context) {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {

            db.execSQL(CREATE_TABLE);
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        }
    }

}
