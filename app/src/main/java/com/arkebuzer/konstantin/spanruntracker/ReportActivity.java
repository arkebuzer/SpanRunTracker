package com.arkebuzer.konstantin.spanruntracker;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.arkebuzer.konstantin.spanruntracker.data.TimeUnit;
import com.arkebuzer.konstantin.spanruntracker.data.TrainingData;

public class ReportActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report);
        //
        // Получаем сообщение из объекта intent
        Intent intent = getIntent();
        TrainingData trainingData = intent.getExtras().getParcelable(MainActivity.EXTRA_TRAINING_DATA);
        //ToDo. Вывести на экран результаты тренировки.
        //ToDo. Сделать таблицу читабельной.
        //ToDo.Разобраться с размером шрифта
        TableLayout paceTable = (TableLayout) findViewById(R.id.pace_table);
        TextView work200 = new TextView(this);
        work200.setTextSize(14);
        work200.setText(trainingData.countAveragePace(200, TimeUnit.SECOND, true));
        TableRow rowTwo = (TableRow) paceTable.getChildAt(1);
        rowTwo.addView(work200, new TableRow.LayoutParams(1));
        /*Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);*/
    }

}
