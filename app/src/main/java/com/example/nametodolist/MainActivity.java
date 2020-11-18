package com.example.nametodolist;

import android.content.DialogInterface;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;


import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    private ListView mTaskListView;
    private ArrayAdapter<String> mAdapter;
    private List<String> taskList = new ArrayList<String>() {

        {
            add("OS lecture");
            add("OS tutorial");
            add("OS lab");
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mTaskListView = findViewById(R.id.list_todo);
        mAdapter = new ArrayAdapter <String>( this ,
                R.layout.todo_item ,
                R.id.task_title ,
                taskList ) ;
        mTaskListView.setAdapter(mAdapter);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_add_task:
                final EditText taskEditText = new EditText ( this );
                AlertDialog dialog = new AlertDialog . Builder (    this )
                        . setTitle (" Add a new task " )
                        . setMessage ( " What do you want to do next ?")
                        . setView ( taskEditText )
                        . setPositiveButton (" Add " , new DialogInterface.
                                OnClickListener () {
                            @Override
                            public void onClick ( DialogInterface dialog , int which )
                            {
                                String task = String . valueOf ( taskEditText . getText ());
                                addItem(task);
                                Log .d(TAG , " Task to add : " + task ) ;
                            }
                        })
                        . setNegativeButton (" Cancel " , null )
                        . create () ;
                dialog.show();
                return true ;
            default:
                return super.onOptionsItemSelected(item);
        }
    }



    private void addItem ( String itemText ){
        taskList.add( itemText );
        mAdapter.notifyDataSetChanged () ;
    }
    private void removeItem ( String itemText ) {
        taskList.remove ( itemText );
        mAdapter.notifyDataSetChanged () ;
    }

    public void deleteTask(View view) {
        View parent = (View) view.getParent();
        TextView taskTextView = parent.findViewById(R.id.task_title);
        String task = String.valueOf(taskTextView.getText());
        removeItem(task);
    }
}
