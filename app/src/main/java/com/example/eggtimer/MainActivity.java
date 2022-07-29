package com.example.eggtimer;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    TextView countertextView;
    SeekBar timerSeekBar;
    Boolean counterIsActive = false;
    //  private Object CountDownTimer;
    Button goButton;
    CountDownTimer  countDownTimer;
    MediaPlayer mplayer;
    ImageView rawEgg,chickenEgg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // Image View
         rawEgg =  findViewById(R.id.rawEgg);
         chickenEgg =  findViewById(R.id.chickenEgg);

        timerSeekBar = findViewById(R.id.timerseekBar);
        countertextView = findViewById(R.id.countertextView);
        goButton = findViewById(R.id.goButton);
        timerSeekBar.setMax(3600);
        timerSeekBar.setProgress(30);


        timerSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                updateTimer(i);


            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }


        });
    }


// Button Methods
    public void ten(View view){
        Toast.makeText(this, "10 Minutes Selected", Toast.LENGTH_SHORT).show();

        timerSeekBar.setProgress(600);

    }


    public void fifteen(View view){
        Toast.makeText(this, "15 Minutes Selected", Toast.LENGTH_SHORT).show();

        timerSeekBar.setProgress(900);

    }


    public void thirty(View view){
        Toast.makeText(this, "30 Minutes Selected", Toast.LENGTH_SHORT).show();

        timerSeekBar.setProgress(1800);

    }


    public void fourty(View view){
        Toast.makeText(this, "45 Minutes Selected", Toast.LENGTH_SHORT).show();

        timerSeekBar.setProgress(2700);

    }







    public void resetTimer(){
       // countertextView.setText("0:30");
        //timerSeekBar.setProgress(30);
        timerSeekBar.setEnabled(true);
        countDownTimer.cancel();
        goButton.setText("GO!");
        counterIsActive = false;

    }




    public  void buttonClick(View view){


            Toast.makeText(this, "Timer Set", Toast.LENGTH_SHORT).show();

            countDownTimer   = new CountDownTimer(timerSeekBar.getProgress() *1000+100,1000){
                @Override
                public void onTick(long millisUntilFinished) {
                    updateTimer((int) millisUntilFinished / 1000);

                }

                @Override
                public void onFinish() {


                     mplayer = MediaPlayer.create(getApplicationContext(),R.raw.alarmsound);
                    mplayer.start();
                    resetTimer();
                    mplayer.setLooping(true);


//                    rawEgg.setVisibility(View.INVISIBLE);
//                    chickenEgg.setVisibility(View.VISIBLE);
                    chickenEgg.setVisibility(View.VISIBLE);

                    rawEgg.animate().alpha(0).setDuration(200);
                    chickenEgg.animate().alpha(1).setDuration(200);







                }
            }.start();


        }




    public void stop(View view){



           resetTimer();


        counterIsActive = true;
        timerSeekBar.setEnabled(false);
        mplayer.stop();
//        ImageView imageView1 =(ImageView) findViewById(R.id.);
//        ImageView imageView2 =(ImageView) findViewById(R.id.);

        rawEgg.animate().alpha(1).setDuration(100);
        chickenEgg.animate().alpha(0).setDuration(100);

//        rawEgg.setVisibility(View.VISIBLE);






    }
    public void updateTimer(int secondsLeft){
        int minutes = secondsLeft/ 60;
        int seconds = secondsLeft - (minutes * 60);

        String secondString = Integer.toString(seconds);
        if(seconds<=9) {
            secondString = "0"+ secondString;

        }

        countertextView.setText(Integer.toString(minutes) + " : " +secondString);



    }


}