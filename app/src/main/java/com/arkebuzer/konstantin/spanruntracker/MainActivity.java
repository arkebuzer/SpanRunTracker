package com.arkebuzer.konstantin.spanruntracker;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private Toast toast = null;
    private TrainingInput trainingInput = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        /*ActivityMainBinding binding =  DataBindingUtil.setContentView(this, R.layout.content_main);
        trainingInput = new TrainingInput(1,2,3);
        binding.setTrainingInput(trainingInput);*/
        setContentView(R.layout.activity_main);
        /*Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });*/
    }

    /*@Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }*/

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void startRun(View view) {
        try {
            EditText editCirclesCnt = (EditText)findViewById(R.id.edit_circles_cnt);
            String circlesCnt = editCirclesCnt.getText().toString();
            EditText editWorkDistance = (EditText)findViewById(R.id.edit_work_distance);
            String workDistance = editWorkDistance.getText().toString();
            EditText editRestDistance = (EditText)findViewById(R.id.edit_rest_distance);
            String restDistance = editRestDistance.getText().toString();

            trainingInput = new TrainingInput(Integer.parseInt(circlesCnt),
                    Integer.parseInt(workDistance),Integer.parseInt(restDistance));

            Context context = getApplicationContext();
            CharSequence text = trainingInput.toString();
            int duration = Toast.LENGTH_LONG;

            toast = Toast.makeText(context, text, duration);
            toast.setGravity(Gravity.CENTER, 0, 0);
            toast.show();
        }
        catch (Exception e) {
            Context context = getApplicationContext();
            CharSequence text = "Заполните информацию о тренировке";
                    //trainingInput.toString();//"Hello toast!";
            int duration = Toast.LENGTH_LONG;

            toast = Toast.makeText(context, text, duration);
            toast.setGravity(Gravity.CENTER, 0, 0);
            toast.show();
        }
    }

    @Override
    protected void onStop () {
        super.onStop();
        toast.cancel();
    }
}
