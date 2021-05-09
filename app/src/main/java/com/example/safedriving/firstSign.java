package com.example.safedriving;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class firstSign extends AppCompatActivity {

    static double user_weigh = 0.0, gender_cont = 0.0;

    String gender = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first_sign);

        TextView welcomeName = findViewById(R.id.welcomeMessage);
        welcomeName.setText("Welcome " + MainActivity.name);

        String[] weights = new String[150];
        int min = 100;
        for (int i = 0; i < 150; i++ ) {
            weights[i] = String.valueOf(min);
            min++;
        }

        Spinner spinner = (Spinner) findViewById(R.id.spinner15);
        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, weights);
        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        spinner.setAdapter(adapter);
    }


    public void submit(View view) {

        CheckBox isMale = findViewById(R.id.checkBox4);
        CheckBox isFemale = findViewById(R.id.checkBox3);
        Spinner weight = findViewById(R.id.spinner15);

        user_weigh = Double.parseDouble(weight.getSelectedItem().toString());
        if (isMale.isChecked() && !isFemale.isChecked()) {
            gender_cont = 0.73;
            gender = "male";
        } else if (isFemale.isChecked() && !isMale.isChecked()) {
            gender_cont = 0.66;
            gender = "female";
        } else {
            Toast.makeText(getApplicationContext(),
                    (CharSequence) ("Select One Gender"),
                    Toast.LENGTH_SHORT).show();
        }

        // ADDING FIRST TIME USER TO DATABASE
        if (MainActivity.firsttime.getGender() == null) {
            MainActivity.profiles.addUser(new User(MainActivity.currentaccId, gender, user_weigh));
            MainActivity.firsttime = null;
        }
        // TILL HERE

        Intent intent = new Intent(this, DrinkSelecter.class);
        startActivity(intent);
    }
}