package infamous_software.desktopclock;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.net.Uri;
import android.os.Build;
import android.preference.PreferenceManager;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.skydoves.colorpickerpreference.ColorEnvelope;
import com.skydoves.colorpickerpreference.ColorListener;
import com.skydoves.colorpickerpreference.ColorPickerDialog;


public class activity_main extends AppCompatActivity {

    AdView adView1;
    Switch switch01;
    Switch switch02;
    Switch switch03;
    Switch switch04;
    Switch switch05;
    SeekBar brightnessBar;


    //String key_admob = "ca-app-pub-5248686580922732~6116847560";
    //String key_admobTest ="ca-app-pub-3940256099942544~3347511713";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);

        MobileAds.initialize(this, "ca-app-pub-5248686580922732~6116847560");
        AdRequest adRequest = new AdRequest.Builder().addTestDevice(AdRequest.DEVICE_ID_EMULATOR).build();

        adView1 = findViewById(R.id.adView1);
        adView1.loadAd(adRequest);
        adView1.setAdListener(new AdListener() {
            @Override
            public void onAdLoaded() {
                // Code to be executed when an ad finishes loading.
            }

            @Override
            public void onAdFailedToLoad(int errorCode) {
                // Code to be executed when an ad request fails.
            }

            @Override
            public void onAdOpened() {
                // Code to be executed when an ad opens an overlay that
                // covers the screen.
            }

            @Override
            public void onAdLeftApplication() {
                // Code to be executed when the user has left the app.
            }

            @Override
            public void onAdClosed() {
                // Code to be executed when when the user is about to return
                // to the app after tapping on an ad.
            }
        });

        final SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this.getApplicationContext());

        String state_switch1 = sharedPreferences.getString("switch1", "true");
        switch01 = findViewById(R.id.switch_show_date);
        switch01.setChecked(Boolean.parseBoolean(state_switch1));
        switch01.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                boolean state = isChecked;
                SharedPreferences.Editor mEditor = sharedPreferences.edit();
                mEditor.putString("switch1", String.valueOf(state)).commit();
            }
        });

        String state_switch2 = sharedPreferences.getString("switch2", "true");
        switch02 = findViewById(R.id.switch_show_seconds);
        switch02.setChecked(Boolean.parseBoolean(state_switch2));
        switch02.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                boolean state = isChecked;
                SharedPreferences.Editor mEditor = sharedPreferences.edit();
                mEditor.putString("switch2", String.valueOf(state)).commit();
            }
        });

        String state_switch3 = sharedPreferences.getString("switch3", "false");
        switch03 = findViewById(R.id.switch_show_24h);
        switch03.setChecked(Boolean.parseBoolean(state_switch3));
        switch03.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                boolean state = isChecked;
                SharedPreferences.Editor mEditor = sharedPreferences.edit();
                mEditor.putString("switch3", String.valueOf(state)).commit();
            }
        });

        String state_switch4 = sharedPreferences.getString("switch4", "false");
        switch04 = findViewById(R.id.switch_show_battery);
        switch04.setChecked(Boolean.parseBoolean(state_switch4));
        switch04.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                boolean state = isChecked;
                SharedPreferences.Editor mEditor = sharedPreferences.edit();
                mEditor.putString("switch4", String.valueOf(state)).commit();
            }
        });

        String state_switch5 = sharedPreferences.getString("switch5", "false");
        switch05 = findViewById(R.id.switch_night_mode);
        switch05.setChecked(Boolean.parseBoolean(state_switch5));
        switch05.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                boolean state = isChecked;
                SharedPreferences.Editor mEditor = sharedPreferences.edit();
                mEditor.putString("switch5", String.valueOf(state)).commit();
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        if (id == R.id.start) {
            Intent intent = new Intent(getApplicationContext(), activity_clock.class);
            startActivity(intent);
            return true;
        }

        if (id == R.id.acerca_de) {
            Intent intent = new Intent(this, activity_about.class);
            startActivity(intent);
            return true;
        }

        if (id == R.id.salir) {
            finishAndRemoveTask();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onPause() {
        if (adView1 != null) {
            adView1.pause();
        }
        super.onPause();
    }

    @Override
    public void onResume() {
        super.onResume();
        if (adView1 != null) {
            adView1.resume();
        }
    }

    @Override
    public void onDestroy() {
        if (adView1 != null) {
            adView1.destroy();
        }
        super.onDestroy();
    }
}
