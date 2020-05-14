package com.example.flags;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.Switch;

public class MainActivity extends AppCompatActivity {
//Please note i have omitted some comments on some files in order to prevent duplication if they are the same code
    Switch timeSwitch;
    boolean timerOn = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//listener to read if the switch is in the on or off position and then sets the boolean to true or false depending on the state
        timeSwitch = findViewById(R.id.switch1);
        timeSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(timeSwitch.isChecked()){
                    timerOn = true;
                }else{
                    timerOn = false;
                }
            }
        });
    }

    public void launchGuessCountry(View view) {
        //creates the intent to start another activity on click - passes through the boolean value with the name state.
        Intent intent = new Intent(getBaseContext(), guessthecountry.class);
        intent.putExtra("state", timerOn);
        startActivity(intent);
    }

    public void launchGuessFlag(View view){
        Intent intent = new Intent(getBaseContext(), guesstheflag.class);
        intent.putExtra("state", timerOn);
        startActivity(intent);
    }

    public void launchAdvanced(View view){
        Intent intent = new Intent(getBaseContext(), advancedlevel.class);
        intent.putExtra("state", timerOn);
        startActivity(intent);
    }

    public void launchGuessHints(View view){
        Intent intent = new Intent(getBaseContext(), guesshints.class);
        intent.putExtra("state", timerOn);
        startActivity(intent);
    }
}
