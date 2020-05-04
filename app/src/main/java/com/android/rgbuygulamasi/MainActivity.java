package com.android.rgbuygulamasi;

import android.content.SharedPreferences;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private TextView txt_red, txt_green, txt_blue;
    private SeekBar seek_red, seek_green, seek_blue;
    private RelativeLayout relativeLayout;
    private Button btn_degistir, btn_kaydet, btn_sifirla;
    int red, green, blue;
    String red1, green1, blue1;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        relativeLayout = (RelativeLayout) findViewById(R.id.pnl_relative);

        seek_red = (SeekBar) findViewById(R.id.red_seekBar);
        seek_blue = (SeekBar) findViewById(R.id.blue_seekBar);
        seek_green = (SeekBar) findViewById(R.id.green_seekBar);

        txt_red = (TextView) findViewById(R.id.txt_red);
        txt_green = (TextView) findViewById(R.id.txt_green);
        txt_blue = (TextView) findViewById(R.id.txt_blue);

        sharedPreferences = getPreferences(MODE_PRIVATE);
        editor = sharedPreferences.edit();

        relativeLayout.setBackgroundColor(Color.rgb(sharedPreferences.getInt("red", 0),
                sharedPreferences.getInt("green", 0),
                sharedPreferences.getInt("blue", 0)));

        txt_red.setText("R:" + sharedPreferences.getString("red1", ""));
        txt_green.setText("G:" + sharedPreferences.getString("green1", ""));
        txt_blue.setText("B:" + sharedPreferences.getString("blue1", ""));

        seek_red.setProgress(Integer.parseInt(sharedPreferences.getString("red1", "0")));
        seek_blue.setProgress(Integer.parseInt(sharedPreferences.getString("green1", "0")));
        seek_green.setProgress(Integer.parseInt(sharedPreferences.getString("blue1", "0")));

        seek_red.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                txt_red.setText("R:" + Integer.toString(progress));
                red = progress;
                red1 = Integer.toString(red);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        seek_blue.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                txt_blue.setText("G:" + Integer.toString(progress));
                blue = progress;
                blue1 = Integer.toString(blue);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        seek_green.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                txt_green.setText("B:" + Integer.toString(progress));
                green = progress;
                green1 = Integer.toString(green);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        btn_degistir = (Button) findViewById(R.id.btn_degistir);
        btn_degistir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                relativeLayout.setBackgroundColor(Color.rgb(red, green, blue));
            }
        });

        btn_kaydet = (Button) findViewById(R.id.btn_kaydet);
        btn_kaydet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editor.putInt("red", red);
                editor.putInt("green", green);
                editor.putInt("blue", blue);

                editor.putString("red1", red1);
                editor.putString("green1", green1);
                editor.putString("blue1", blue1);

                editor.commit();

                Toast.makeText(MainActivity.this, "Arka Plan Rengi Kaydedildi!", Toast.LENGTH_SHORT).show();
            }
        });

        btn_sifirla = (Button) findViewById(R.id.btn_sıfırla);
        btn_sifirla.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                red = 255;
                green = 255;
                blue = 255;

                editor.putInt("red", red);
                editor.putInt("green", green);
                editor.putInt("blue", blue);

                editor.putString("red1", null);
                editor.putString("green1", null);
                editor.putString("blue1", null);

                editor.commit();

                seek_red.setProgress(0);
                seek_blue.setProgress(0);
                seek_green.setProgress(0);

                relativeLayout.setBackgroundColor(Color.WHITE);

                Toast.makeText(MainActivity.this, "Ayarlar Sıfırlandı!", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
