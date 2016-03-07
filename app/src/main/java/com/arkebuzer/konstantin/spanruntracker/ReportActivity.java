package com.arkebuzer.konstantin.spanruntracker;

import android.app.ActionBar;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.arkebuzer.konstantin.spanruntracker.data.TimeUnit;
import com.arkebuzer.konstantin.spanruntracker.data.TrainingData;

public class ReportActivity extends AppCompatActivity {

    private TableLayout paceTable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report);
        // Получаем сообщение из объекта intent
        Intent intent = getIntent();
        TrainingData trainingData = intent.getExtras().getParcelable(MainActivity.EXTRA_TRAINING_DATA);
        //ToDo. Сделать таблицу читабельной.
        paceTable = (TableLayout) findViewById(R.id.pace_table);
        //Work
        addPaceValueCell(trainingData, 200, TimeUnit.SECOND, true, 1);
        addPaceValueCell(trainingData, 400, TimeUnit.SECOND, true, 1);
        addPaceValueCell(trainingData, 1000, TimeUnit.MINUTE, true, 1);
        //Rest
        addPaceValueCell(trainingData, 200, TimeUnit.SECOND, false, 2);
        addPaceValueCell(trainingData, 400, TimeUnit.SECOND, false, 2);
        addPaceValueCell(trainingData, 1000, TimeUnit.MINUTE, false, 2);
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

    private void addPaceValueCell(TrainingData trainingData,
                                  Integer distance, TimeUnit timeUnit, boolean workPace, Integer rowNum) {
        TextView valCell = new TextView(this);
        valCell.setTextSize(32);//, TypedValue.COMPLEX_UNIT_SP);
        valCell.setText(trainingData.countAveragePace(distance, timeUnit, workPace));
        valCell.setWidth(0);
        TableRow row = (TableRow) paceTable.getChildAt(rowNum);
        row.addView(valCell, new TableRow.LayoutParams(0, TableRow.LayoutParams.WRAP_CONTENT, 1f));
    }

}
