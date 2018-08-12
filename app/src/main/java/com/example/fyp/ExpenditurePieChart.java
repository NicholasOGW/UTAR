package com.example.fyp;

import android.database.Cursor;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.github.mikephil.charting.animation.Easing;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;

public class ExpenditurePieChart extends AppCompatActivity {

    PieChart pieChart;
    Expenditure expenditure;
    ActionBar actionBar;

    @Override
    protected void onCreate(Bundle savedInstanState){
        super.onCreate(savedInstanState);
        setContentView(R.layout.menu_expenditure_piechart);

        actionBar = getSupportActionBar();
        actionBar.setTitle("Expenses Summary");

        pieChart = findViewById(R.id.pieChart);

        pieChart.setUsePercentValues(true);
        pieChart.getDescription().setEnabled(false);
        pieChart.setExtraOffsets(5, 10, 5, 5);
        pieChart.setDragDecelerationFrictionCoef(0.95f);
        pieChart.setDrawHoleEnabled(true);
        pieChart.setTransparentCircleRadius(61f);
        pieChart.animateY(1000, Easing.EasingOption.EaseInOutCubic);

        ArrayList<PieEntry> yValues = new ArrayList<>();

        ExpenditureDbQueries dbq = new ExpenditureDbQueries(new DbHelper(getApplicationContext()));

        final String[] columns = {
                ExpenditureContract.ExpenditureEntry._ID,
                ExpenditureContract.ExpenditureEntry.COLUMN_TYPE,
                ExpenditureContract.ExpenditureEntry.COLUMN_AMOUNT,
                ExpenditureContract.ExpenditureEntry.COLUMN_TITLE,
                ExpenditureContract.ExpenditureEntry.COLUMN_DATE
        };

        Cursor cursor = dbq.query(columns, null, null, null, null, null);

        while (cursor.moveToNext()) {
            expenditure = new Expenditure(
                    cursor.getLong(cursor.getColumnIndex(ExpenditureContract.ExpenditureEntry._ID)),
                    cursor.getString(cursor.getColumnIndex(ExpenditureContract.ExpenditureEntry.COLUMN_TYPE)),
                    cursor.getString(cursor.getColumnIndex(ExpenditureContract.ExpenditureEntry.COLUMN_AMOUNT)),
                    cursor.getString(cursor.getColumnIndex(ExpenditureContract.ExpenditureEntry.COLUMN_TITLE)),
                    cursor.getString(cursor.getColumnIndex(ExpenditureContract.ExpenditureEntry.COLUMN_DATE))
            );
            double tempDouble = Double.parseDouble(expenditure.getAmount());
            int tempInt = (int)tempDouble *100;
            yValues.add(new PieEntry(tempInt, titleSetter(expenditure.getType())));
        }

        PieDataSet dataSet = new PieDataSet((yValues), "");
        dataSet.setSliceSpace(3f);
        dataSet.setSelectionShift(5f);
        dataSet.setColors(ColorTemplate.JOYFUL_COLORS);

        PieData data = new PieData(dataSet);
        data.setValueTextSize(10f);
        data.setValueTextColor(Color.BLACK);
        pieChart.setData(data);
    }

    private String titleSetter(String type) {
        if (type.equals("0")){
            return "Education";
        }
        else if (type.equals("1")){
            return "Entertainment";
        }
        else if (type.equals("2")){
            return "Shopping";
        }
        else if (type.equals("3")){
            return "Auto and Transport";
        }
        else if (type.equals("4")){
            return "Personal Care";
        }
        else if (type.equals("5")){
            return "Health and Fitness";
        }
        else if (type.equals("6")){
            return "Food and Dining";
        }
        else if (type.equals("7")){
            return "Fees and Charges";
        }
        else if (type.equals("8")){
            return "Others";
        }
        else
            return null;
    }
}
