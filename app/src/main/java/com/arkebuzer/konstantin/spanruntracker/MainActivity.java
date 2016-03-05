package com.arkebuzer.konstantin.spanruntracker;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Vibrator;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.arkebuzer.konstantin.spanruntracker.data.TrainingData;

public class MainActivity extends AppCompatActivity {

    public final static String EXTRA_TRAINING_DATA = "com.arkebuzer.konstantin.spanruntracker.TrainingData";

    private Toast toast = null;
    private TrainingData trainingData = null;
    private boolean onRun = false;
    private long runStartTime = 0;
    private TextView runTimerArea;
    private long circleStartTime = 0;
    private TextView circleTimerArea;
    private boolean workSpan = false;
    private int circleNum = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        /*ActivityMainBinding binding =  DataBindingUtil.setContentView(this, R.layout.content_main);
        TrainingData = new TrainingData(1,2,3);
        binding.setTrainingData(TrainingData);*/
        setContentView(R.layout.activity_main);
        runTimerArea = (TextView) findViewById(R.id.run_timer_area);
        circleTimerArea = (TextView) findViewById(R.id.circle_timer_area);
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

    /*@Override
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
    }*/

    public void startRun(View view) {
        if (!onRun) { //Тренировка не начата
            if (checkSettings()) {
                vibrate();
                onRun = true;
                workSpan = true;
                //Change background
                View mainView = findViewById(R.id.main_activity);
                mainView.setBackgroundColor(getResources().getColor(R.color.colorWork));
                //Disable input
                EditText editCirclesCnt = (EditText) findViewById(R.id.edit_circles_cnt);
                editCirclesCnt.setEnabled(false);
                EditText editWorkDistance = (EditText) findViewById(R.id.edit_work_distance);
                editWorkDistance.setEnabled(false);
                EditText editRestDistance = (EditText) findViewById(R.id.edit_rest_distance);
                editRestDistance.setEnabled(false);
                //Change button label
                Button button = (Button) view;
                button.setText(R.string.work);
                //Start Timers
                circleNum = trainingData.getCirclesCnt();
                runStartTime = System.currentTimeMillis();
                runTimerHandler.postDelayed(runTimerRunnable, 0);
                circleStartTime = runStartTime;
                circleTimerHandler.postDelayed(circleTimerRunnable, 0);
            }
        } else { //Тренировка начата
            vibrate();
            if (workSpan) { //Рабочий отрезок
                workSpan = false;
                //Change background
                View mainView = findViewById(R.id.main_activity);
                mainView.setBackgroundColor(getResources().getColor(R.color.colorRest));
                //Change button label
                Button button = (Button) view;
                button.setText(R.string.rest);
            } else { //Отрезок для отдыхы
                circleNum--;
                if (circleNum == 0) { //Конец тренировки
                    onRun = false;
                    workSpan = false;
                    //Stop Timers
                    runTimerHandler.removeCallbacks(runTimerRunnable);
                    circleTimerHandler.removeCallbacks(circleTimerRunnable);
                    //Change background
                    View mainView = findViewById(R.id.main_activity);
                    mainView.setBackgroundColor(getResources().getColor(R.color.colorIdle));
                    //Change button label
                    Button button = (Button) view;
                    button.setText(R.string.start);
                    //Enable input
                    EditText editCirclesCnt = (EditText) findViewById(R.id.edit_circles_cnt);
                    editCirclesCnt.setEnabled(true);
                    EditText editWorkDistance = (EditText) findViewById(R.id.edit_work_distance);
                    editWorkDistance.setEnabled(true);
                    EditText editRestDistance = (EditText) findViewById(R.id.edit_rest_distance);
                    editRestDistance.setEnabled(true);
                    //Show Report
                    showReport();
                } else {
                    workSpan = true;
                    //Change background
                    View mainView = findViewById(R.id.main_activity);
                    mainView.setBackgroundColor(getResources().getColor(R.color.colorWork));
                    //Change button label
                    Button button = (Button) view;
                    button.setText(R.string.work);
                    circleStartTime = System.currentTimeMillis();
                }
            }
        }
    }

    private boolean checkSettings() {
        try {
            EditText editCirclesCnt = (EditText) findViewById(R.id.edit_circles_cnt);
            String circlesCnt = editCirclesCnt.getText().toString();
            EditText editWorkDistance = (EditText) findViewById(R.id.edit_work_distance);
            String workDistance = editWorkDistance.getText().toString();
            EditText editRestDistance = (EditText) findViewById(R.id.edit_rest_distance);
            String restDistance = editRestDistance.getText().toString();

            trainingData = new TrainingData(Integer.parseInt(circlesCnt),
                    Integer.parseInt(workDistance), Integer.parseInt(restDistance));
            if (toast != null) {
                toast.cancel();
            }
            return true;
        } catch (NumberFormatException e) {
            Context context = getApplicationContext();
            CharSequence text = "Заполните информацию о тренировке";
            int duration = Toast.LENGTH_SHORT;
            if (toast != null) {
                toast.cancel();
            }
            toast = Toast.makeText(context, text, duration);
            toast.setGravity(Gravity.CENTER, 0, 0);
            toast.show();
            return false;
        }
    }

    //runs without a timer by reposting this handler at the end of the runnable
    private Handler runTimerHandler = new Handler();
    private Runnable runTimerRunnable = new Runnable() {

        @Override
        public void run() {
            long millis = System.currentTimeMillis() - runStartTime;
            int centis = (int) (millis / 10);
            int seconds = (int) (millis / 1000);
            int minutes = seconds / 60;
            seconds = seconds % 60;
            centis = centis % 100;

            runTimerArea.setText(String.format("%02d:%02d.%02d", minutes, seconds, centis));
            //ToDo. Разобраться с выводом сотых секунд
            runTimerHandler.postDelayed(this, 20);
        }
    };

    //runs without a timer by reposting this handler at the end of the runnable
    //ToDo. Подумать о сокращении дублированной логики
    private Handler circleTimerHandler = new Handler();
    private Runnable circleTimerRunnable = new Runnable() {

        @Override
        public void run() {
            long millis = System.currentTimeMillis() - circleStartTime;
            int centis = (int) (millis / 10);
            int seconds = (int) (millis / 1000);
            int minutes = seconds / 60;
            seconds = seconds % 60;
            centis = centis % 100;

            circleTimerArea.setText(String.format("%02d:%02d.%02d", minutes, seconds, centis));
            //ToDo. Разобраться с выводом сотых секунд
            circleTimerHandler.postDelayed(this, 20);
        }
    };

    private void vibrate() {
        Vibrator v = (Vibrator) this.getSystemService(Context.VIBRATOR_SERVICE);
        // Vibrate for 500 milliseconds
        v.vibrate(500);
    }

    public void showReport() {
        // Создаем объект Intent для вызова новой Activity
        Intent intent = new Intent(this, ReportActivity.class);
        // Добавляем с помощью свойства putExtra объект - первый параметр - ключ,
        // второй параметр - хначение этого объекта
        intent.putExtra(EXTRA_TRAINING_DATA, trainingData);
        // запуск activity
        startActivity(intent);
    }

    @Override
    protected void onStop() {
        super.onStop();
        //toast.cancel();
    }
}
