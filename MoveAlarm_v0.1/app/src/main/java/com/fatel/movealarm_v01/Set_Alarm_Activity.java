package com.fatel.movealarm_v01;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.NumberPicker;
import android.widget.Spinner;

public class Set_Alarm_Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.set_alarm_layout);
        createSpinner(12, R.id.start_hr,true);
        createSpinner(60, R.id.start_min,false);
        createSpinner(12, R.id.fin_hr,true);
        createSpinner(60, R.id.fin_min,false);
        createSpinnerAmPm(R.id.start_AP);
        createSpinnerAmPm(R.id.fin_AP);
        createSpinnerFrq(R.id.frq_min);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_set__alarm_, menu);
        return true;
    }

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

    public void linkMain(View view)
    {
        Intent intent = new Intent(this,MainActivity.class);
        startActivity(intent);
    }
    public void createSpinner(int num,int id,boolean isHr)
    {
        Spinner spinner = (Spinner)findViewById(id);
        String[] numm = new String[num];
        ArrayAdapter<String> adapter;
        if(isHr) {
            for (int i = 1; i <= num; i++) {
                if(i<10)
                    numm[i - 1] = "0" + i;
                else
                    numm[i - 1] = "" + i;
            }
            adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, numm);
            spinner.setAdapter(adapter);
            spinner.setSelection(11);
        }
        else {
            for (int i = 0; i < num; i++) {
                if(i<10)
                    numm[i] = "0" + i;
                else
                    numm[i] = "" + i;
            }
            adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, numm);
            spinner.setAdapter(adapter);
        }

    }

    public void createSpinnerAmPm(int id)
    {
        Spinner spinner = (Spinner)findViewById(id);
        String[] numm = new String[]{"AM","PM"};
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, numm);
        spinner.setAdapter(adapter);
    }

    public void createSpinnerFrq(int id)
    {
        Spinner spinner = (Spinner)findViewById(id);
        String[] numm = new String[]{"15","30","45","60","75","90"};
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, numm);
        spinner.setAdapter(adapter);
    }
}
