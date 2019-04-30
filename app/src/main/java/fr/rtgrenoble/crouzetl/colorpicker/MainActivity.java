package fr.rtgrenoble.crouzetl.colorpicker;


import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.graphics.Color;
import android.nfc.Tag;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import static java.lang.Integer.toHexString;

public class MainActivity extends AppCompatActivity implements SeekBar.OnSeekBarChangeListener {

    private TextView redTextView;
    private TextView greenTextView;
    private TextView blueTextView;
    private TextView colorView;
    private TextView colorHexa;
    private SeekBar redSeekBar;
    private SeekBar greenSeekBar;
    private SeekBar blueSeekBar;
    private Spinner colorNamesSpinner;

    private static final String TAG = "colorpicker";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        redTextView=(TextView) findViewById(R.id.red_text_view);
        greenTextView=(TextView) findViewById(R.id.green_text_view);
        blueTextView=(TextView) findViewById(R.id.blue_text_view);

        colorView=(TextView) findViewById(R.id.color_view);
        colorHexa=(TextView) findViewById(R.id.color_hexa);

        redSeekBar=(SeekBar) findViewById(R.id.red_seek_bar);
        greenSeekBar=(SeekBar) findViewById(R.id.green_seek_bar);
        blueSeekBar=(SeekBar) findViewById(R.id.blue_seek_bar);

        colorNamesSpinner=(Spinner) findViewById(R.id.color_name);

        redSeekBar.setOnSeekBarChangeListener(this);
        greenSeekBar.setOnSeekBarChangeListener(this);
        blueSeekBar.setOnSeekBarChangeListener(this);

        colorHexa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast toast = Toast.makeText(MainActivity.this, R.string.toast, Toast.LENGTH_SHORT);
                toast.setGravity(Gravity.BOTTOM,0,5);
                toast.show();


                ClipboardManager clipboard = (ClipboardManager)
                        getSystemService(Context.CLIPBOARD_SERVICE);
                ClipData clip = ClipData.newPlainText("simple text",colorHexa.getText());
                clipboard.setPrimaryClip(clip);

            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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

    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
        String color = "";
        int bleuRGB = this.blueSeekBar.getProgress();
        int rougeRGB = this.redSeekBar.getProgress();
        int vertRGB = this.greenSeekBar.getProgress();
        redTextView.setText("" + rougeRGB);
        blueTextView.setText("" + bleuRGB);
        greenTextView.setText("" + vertRGB);

        if (seekBar == redSeekBar){
            Log.d(TAG, "Progress red = "+ rougeRGB);
        }
        else if (seekBar == blueSeekBar){color = "blue";
            Log.d(TAG, "Progress blue = "+ bleuRGB);
        }
        else { // (seekBar == greenSeekBar){color = "green";
            Log.d(TAG, "Progress green = "+ vertRGB);
        }


        int RGB = Color.rgb(rougeRGB,vertRGB,bleuRGB);
        Log.d(TAG, "rougeRGB= "+ rougeRGB + " bleuRGB= "+bleuRGB+" vertRGB= "+vertRGB);
        colorView.setBackgroundColor(RGB);


        colorHexa.setText(toHexString(RGB));

    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {}

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {}


}
