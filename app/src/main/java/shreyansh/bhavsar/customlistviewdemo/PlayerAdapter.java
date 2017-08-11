package shreyansh.bhavsar.customlistviewdemo;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by shreyansh.bhavsar on 6/11/2017.
 */

class PlayerAdapter extends BaseAdapter {

    public final MainActivity mainActivity;
    public final List<Player> players;


    public PlayerAdapter(MainActivity mainActivity, List<Player> players) {

        this.mainActivity=mainActivity;
        this.players=players;

    }

    @Override
    public int getCount() {
        return players.size();
    }

    @Override
    public Object getItem(int position) {
        return players.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        convertView = LayoutInflater.from(mainActivity).inflate(R.layout.list_row,parent,false);

        ImageView imageview = (ImageView)convertView.findViewById(R.id.row_image);
        TextView nameTv = (TextView)convertView.findViewById(R.id.row_name);
        TextView runsTv = (TextView)convertView.findViewById(R.id.row_runs);



        Player player = players.get(position);
        nameTv.setText(player.getName());
        runsTv.setText(""+player.getRuns());


        Picasso.with(mainActivity).load("http://shreyanshbhavsar.esy.es/"+player.name+".jpg").into(imageview);


        return convertView;
    }

    void add(String name,int runs)
    {
        players.add(new Player(name,runs));
    }

    public void delete(Player player) {

        players.remove(player);
    }
}
