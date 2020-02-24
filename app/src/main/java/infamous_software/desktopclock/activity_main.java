package infamous_software.desktopclock;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.WindowManager;
import android.widget.CompoundButton;
import android.widget.Switch;

public class activity_main extends AppCompatActivity {

    Switch switch01;
    Switch switch02;
    Switch switch03;
    Switch switch04;
    Switch switch05;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);

        final SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this.getApplicationContext());

        String state_switch1 = sharedPreferences.getString("switch1", "true");
        switch01 = findViewById(R.id.switch_show_date);
        switch01.setChecked(Boolean.parseBoolean(state_switch1));
        switch01.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                boolean state = isChecked;
                SharedPreferences.Editor mEditor = sharedPreferences.edit();
                mEditor.putString("switch1", String.valueOf(state)).apply();
            }
        });

        String state_switch2 = sharedPreferences.getString("switch2", "true");
        switch02 = findViewById(R.id.switch_show_seconds);
        switch02.setChecked(Boolean.parseBoolean(state_switch2));
        switch02.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                SharedPreferences.Editor mEditor = sharedPreferences.edit();
                mEditor.putString("switch2", String.valueOf(isChecked)).apply();
            }
        });

        String state_switch3 = sharedPreferences.getString("switch3", "false");
        switch03 = findViewById(R.id.switch_show_24h);
        switch03.setChecked(Boolean.parseBoolean(state_switch3));
        switch03.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                SharedPreferences.Editor mEditor = sharedPreferences.edit();
                mEditor.putString("switch3", String.valueOf(isChecked)).apply();
            }
        });

        String state_switch4 = sharedPreferences.getString("switch4", "false");
        switch04 = findViewById(R.id.switch_show_battery);
        switch04.setChecked(Boolean.parseBoolean(state_switch4));
        switch04.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                SharedPreferences.Editor mEditor = sharedPreferences.edit();
                mEditor.putString("switch4", String.valueOf(isChecked)).apply();
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
                mEditor.putString("switch5", String.valueOf(state)).apply();
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

        if (id == R.id.salir) {
            finishAndRemoveTask();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onPause() {
        super.onPause();
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}
