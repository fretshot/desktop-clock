package infamous_software.desktopclock;

import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.net.Uri;
import android.os.BatteryManager;
import android.os.Build;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class activity_clock extends AppCompatActivity{

    TextView time;
    TextView date;
    TextView battery;
    ImageView battery_icon;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clock);

        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);

        getWindow().getDecorView().setBackgroundColor(Color.BLACK);

        getWindow().getDecorView().setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);


        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this.getApplicationContext());
        String state_switch1 = sharedPreferences.getString("switch1", "true");
        String state_switch2 = sharedPreferences.getString("switch2", "true");
        String state_switch3 = sharedPreferences.getString("switch3", "false");
        String state_switch4 = sharedPreferences.getString("switch4", "false");
        String state_switch5 = sharedPreferences.getString("switch5", "false");

        String grayColor = "#151515";

        boolean state_sw1 = Boolean.parseBoolean(state_switch1);
        final boolean states_w2 = Boolean.parseBoolean(state_switch2);
        final boolean states_w3 = Boolean.parseBoolean(state_switch3);
        boolean state_sw4 = Boolean.parseBoolean(state_switch4);
        boolean state_sw5 = Boolean.parseBoolean(state_switch5);

        time = findViewById(R.id.time);
        date = findViewById(R.id.date);
        battery = findViewById(R.id.battery_percent);
        battery_icon = findViewById(R.id.battery_icon);



        if(state_sw5 == true) {
            time.setTextColor(Color.parseColor(grayColor));
            date.setTextColor(Color.parseColor(grayColor));
        }else{
            time.setTextColor(Color.WHITE);
            date.setTextColor(Color.WHITE);
        }

        if (state_sw1 == true ){
            date.setVisibility(View.VISIBLE);
        }else{
            date.setVisibility(View.INVISIBLE);
        }

        if (state_sw4 == true ){
            battery.setVisibility(View.VISIBLE);
            battery_icon.setVisibility(View.VISIBLE);
        }else{
            battery.setVisibility(View.INVISIBLE);
            battery_icon.setVisibility(View.INVISIBLE);
        }

        Thread thread = new Thread() {
            @Override
            public void run() {
                try {
                    while (!isInterrupted()) {


                        Thread.sleep(1000);


                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {


                                BatteryManager batteryManager = (BatteryManager)getSystemService(BATTERY_SERVICE);
                                int batteryLevel = batteryManager.getIntProperty(BatteryManager.BATTERY_PROPERTY_CAPACITY);

                                String x = String.valueOf(batteryLevel)+"%";
                                battery.setText(x);

                                if (states_w2 == false && states_w3 == false){
                                    DateFormat dateFormat_time = new SimpleDateFormat("hh:mm a");
                                    final String string_time = dateFormat_time.format(Calendar.getInstance().getTime());
                                    time.setText(string_time);
                                }

                                if (states_w2 == true && states_w3 == false){
                                    DateFormat dateFormat_time = new SimpleDateFormat("hh:mm:ss a");
                                    final String string_time = dateFormat_time.format(Calendar.getInstance().getTime());
                                    time.setText(string_time);
                                }

                                if (states_w2 == false && states_w3 == true){
                                    DateFormat dateFormat_time = new SimpleDateFormat("HH:mm");
                                    final String string_time = dateFormat_time.format(Calendar.getInstance().getTime());
                                    time.setText(string_time);
                                }

                                if (states_w2 == true && states_w3 == true){
                                    DateFormat dateFormat_time = new SimpleDateFormat("HH:mm:ss");
                                    final String string_time = dateFormat_time.format(Calendar.getInstance().getTime());
                                    time.setText(string_time);
                                }


                                DateFormat dateFormat_date = new SimpleDateFormat("EEE  d MMM  yyyy");
                                final String string_date = dateFormat_date.format(Calendar.getInstance().getTime());
                                date.setText(string_date);

                            }
                        });

                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };

        thread.start();





    }

}
