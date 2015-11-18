package com.fatel.mamtv1;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.media.Image;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class EventActivity extends AppCompatActivity {

    TextView txtR;
    TextView txtA;
    TextView txtDes;
    ImageView imgView;
    AnimationDrawable frameAnimation;
    int count=0;
    //int[] imageId = new int[] {-1,-1,-1,-1};
    ArrayList<Posture> img ;
    int exerciseImg;
    String exerciseDes;

    private static final String FORMAT = "%02d:%02d";
    public static Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.i("Activity", "Can go");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity);
        final Window win= getWindow();
        win.addFlags(WindowManager.LayoutParams.FLAG_SHOW_WHEN_LOCKED | WindowManager.LayoutParams.FLAG_DISMISS_KEYGUARD);
        win.addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON | WindowManager.LayoutParams.FLAG_TURN_SCREEN_ON);

        img = new ArrayList<>();
        txtR=(TextView) findViewById(R.id.rtime);
        txtA=(TextView) findViewById(R.id.atime);
        txtDes=(TextView) findViewById(R.id.des);
        imgView=(ImageView) findViewById(R.id.img);
        ActivityHandle activityHandle=new ActivityHandle();
        context=getApplicationContext();
        PostureCollection postureCollection= PostureCollection.getInstance(this);

        Log.i("Activity","can go +1");
        img = postureCollection.getPosture(activityHandle.getImageId());
        Log.i("Activity","can go +1"+img);
        Log.i("Activity","can go +2");
        exerciseImg=(img.get(count)).getImage();
        Log.i("Activity",""+(img.get(count)).getImage());
        Log.i("Activity","can go +3 id:"+img.get(count));
        exerciseDes=(img.get(count)).getDescription();
        Log.i("Activity",""+(img.get(count)).getDescription());
        Log.i("Activity","can go +4");
        txtDes.setText(exerciseDes);
        Log.i("Activity", "can go +5");
        imgView.setBackgroundResource(exerciseImg);
        Log.i("Activity", "can go +6");
        // Get the background, which has been compiled to an AnimationDrawable object.
        frameAnimation = (AnimationDrawable) imgView.getBackground();
        Log.i("Activity","can go +7");
        // Start the animation (looped playback by default).
        frameAnimation.start();
        Log.i("Activity", "can go +8");

        new CountDownTimer(15000, 1000) {

            public void onTick(long millisUntilFinished) {
                txtR.setText("Remain Time   " + String.format(FORMAT,
                        TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished) - TimeUnit.HOURS.toMinutes(
                                TimeUnit.MILLISECONDS.toHours(millisUntilFinished)),
                        TimeUnit.MILLISECONDS.toSeconds(millisUntilFinished) - TimeUnit.MINUTES.toSeconds(
                                TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished))));

            }

            public void onFinish() {
                txtR.setText("Remain Time done!");
                frameAnimation.stop();
                count++;
                if(count<4) {

                    exerciseImg=(img.get(count)).getImage();
                    exerciseDes=(img.get(count)).getDescription();
                    txtDes.setText(exerciseDes);
                    imgView.setBackgroundResource(exerciseImg);
                    // Get the background, which has been compiled to an AnimationDrawable object.
                    frameAnimation = (AnimationDrawable) imgView.getBackground();
                    // Start the animation (looped playback by default).
                    frameAnimation.start();

                    start();
                }

            }
        }.start();



        new CountDownTimer(60000, 1000) {

            public void onTick(long millisUntilFinished) {
                txtA.setText("Activity Time   "+String.format(FORMAT,
                        TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished) - TimeUnit.HOURS.toMinutes(
                                TimeUnit.MILLISECONDS.toHours(millisUntilFinished)),
                        TimeUnit.MILLISECONDS.toSeconds(millisUntilFinished) - TimeUnit.MINUTES.toSeconds(
                                TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished))));
            }

            public void onFinish() {
                txtA.setText("Activity Time done!");
                //sent data to serve


                //history
                //go to main
                Intent i1 = new Intent(EventActivity.this, MainActivity.class);
                // Bundle b1 = new Bundle();
                //b1.putExtra("key", "main");
                //i1.putExtra("key", "main");
                startActivity(i1);
                //Intent intent = new Intent(Activity.this, MainActivity.class);
                //startActivity(intent);

                //set frq

                //send score to back
                requesAddscore();
            }
        }.start();

    }
  /*  public void random(){
        for(int i=0;i<4;i++){
            boolean same=true;
            int x=0;
            while(same){
                same=false;
                x=(int)(Math.random() * 9);
                for(int j=0;j<i;j++) {
                    if (x == imageId[j]){
                        same=true;
                        break;
                    }
                }
            }
            imageId[i]=x;
        }

    }*/
/*
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_, menu);
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
    }*/

    public void linkHome(View view)
    {
        //history
        HistorygroupHelper mhistorygroupHelper = new HistorygroupHelper(this);
        Historygroup historygroup = mhistorygroupHelper.getHistoryGroup(UserManage.getInstance(this).getCurrentIdGroup());
        if(historygroup!=null){
            historygroup.subaccept(1);
            historygroup.addcancel(1);
            historygroup.save(this);
        }
        else{
            Log.i("Usergroup","can't cancel");
        }
        Log.i("historycancel", UserManage.getInstance(this).getCurrentIdGroup() + "");
        Log.i("historycancel", historygroup.getCancelEvent() + "");
        Log.i("historycancel", historygroup.gettotal() + "");
        Intent i1 = new Intent(EventActivity.this, MainActivity.class);
        // Bundle b1 = new Bundle();
        //b1.putExtra("key", "main");
        //i1.putExtra("key", "main");
        startActivity(i1);
        //sendBroadcast(i1);
        //AlarmManager manager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        //int interval = 60*1000*1;
        //PendingIntent pendingIntent = PendingIntent.getBroadcast(Activity.this, 0, i, 0);
        // manager.setExact(AlarmManager.RTC_WAKEUP, System.currentTimeMillis() + interval, pendingIntent);
    }

    public void requesAddscore()
    {
        final Converter converter = Converter.getInstance();
        String url = HttpConnector.URL + "group/increaseScore";
        StringRequest eventRequest = new StringRequest(Request.Method.POST, url, //create new string request with POST method
                new Response.Listener<String>() { //create new listener to traces the data
                    @Override
                    public void onResponse(String response) { //when listener is activated
                        Log.i("volley", response);

                        HashMap<String, Object> data = converter.JSONToHashMap(response);
                        if((boolean) data.get("status")) {
                            makeToast("Sync process completed.");
                        }
                        else {
                            makeToast(converter.toString(data.get("description")));
                        }
                    }
                }, new Response.ErrorListener() { //create error listener to trace an error if download process fail
            @Override
            public void onErrorResponse(VolleyError volleyError) { //when error listener is activated
                Log.i("volley", volleyError.toString());
                makeToast("Cannot connect to server. Please check the Internet setting.");
            }
        }) { //define POST parameters
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> map = new HashMap<String, String>(); //create map to keep variables
                HashMap<String, Object> JSON = new HashMap<>();
                HashMap<String, Object> groupData = new HashMap<>();
                groupData.put("id", "" + UserManage.getInstance(EventActivity.this).getCurrentIdGroup());
                int point = 2;

                JSON.put("score", point);
                JSON.put("group", groupData);
                JSON.put("description", "Group Event! Score x2");
                Log.i("JSON", converter.HashMapToJSON(JSON));
                map.put("JSON", converter.HashMapToJSON(JSON));

                return map;
            }
        };

        HttpConnector.getInstance((Context) Cache.getInstance().getData("MainActivityContext")).addToRequestQueue(eventRequest);
    }

    public void makeToast(String text)
    {
        Toast.makeText(this, text, Toast.LENGTH_SHORT).show();
    }
}