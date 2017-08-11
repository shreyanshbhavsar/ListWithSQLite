package shreyansh.bhavsar.customlistviewdemo;

import android.app.Dialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemLongClickListener {


    ListView playerlist;
    List<Player> players;
    PlayerAdapter playerAdapter;
    PlayerHelper playerHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        playerHelper = new PlayerHelper(this);

        playerlist = (ListView)findViewById(R.id.playerlist);

        players = playerHelper.fetchAllPlayers();


        playerAdapter = new PlayerAdapter(this,players);

        playerlist.setAdapter(playerAdapter);
        playerlist.setOnItemLongClickListener(this);
    }

    void addPlayer(String name,int runs)
    {

        playerHelper.addPlayer(new Player(name,runs));

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        if(id == R.id.menu_add)
        {

            showAddDialog();
        }

        return super.onOptionsItemSelected(item);
    }

    void showAddDialog()
    {
        final Dialog dialog = new Dialog(MainActivity.this);
        dialog.setTitle("Add Player");
        dialog.setContentView(R.layout.dialog);
        dialog.getWindow().setLayout(WindowManager.LayoutParams.MATCH_PARENT,WindowManager.LayoutParams.WRAP_CONTENT);
        dialog.show();

        final EditText nameEt = (EditText) dialog.findViewById(R.id.dialog_name);
        final EditText runsEt = (EditText) dialog.findViewById(R.id.dialog_runs);
        final Button addBtn = (Button)dialog.findViewById(R.id.addplayerbtn);

        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String name = nameEt.getText().toString();
                int runs = Integer.parseInt(runsEt.getText().toString());

                addPlayer(name,runs);
                playerAdapter.add(name,runs);
                playerAdapter.notifyDataSetChanged();
                dialog.dismiss();

            }
        });



    }

    @Override
    public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {

       playerHelper.deletePlayer(players.get(position).name);
       playerAdapter.delete(players.get(position));
        playerAdapter.notifyDataSetChanged();



        return false;
    }
}
