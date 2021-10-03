package com.example.healthtest;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.Toast;

/**
 * Cette classe correspond à la troisième activité de l'application
 * Elle permet de passer à l'activité suivante
 * C'est la première page de test sur l'état du coeur
 * @author Ismaël SOUSANE
 * @version final
 */

public class MainActivityPage3 extends AppCompatActivity {

    private RadioButton y1, n1, y2, n2, y3, n3, y4, n4;
    private Spinner spinner1;
    private Spinner spinner2;
    private Person person;
    private int heartmark;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        processIntentData();
        setContentView(R.layout.activity_main3);
        y1 = findViewById(R.id.y1);
        y2 = findViewById(R.id.y2);
        y3 = findViewById(R.id.y3);
        y4 = findViewById(R.id.y4);
        n1 = findViewById(R.id.n1);
        n2 = findViewById(R.id.n2);
        n3 = findViewById(R.id.n3);
        n4 = findViewById(R.id.n4);
        spinner1 = findViewById(R.id.spinner1);
        spinner2 = findViewById(R.id.spinner2);


    }

    public void onClickPrevious2(View view) {
        onBackPressed();
    }

    public void onClickNext2(View v) {


        if ((y1.isChecked() || n1.isChecked()) &&
                (y2.isChecked() || n2.isChecked()) &&
                (y3.isChecked() || n3.isChecked()) &&
                (y4.isChecked() || n4.isChecked()) &&
                !(spinner1.getSelectedItem().toString().equalsIgnoreCase("Select an option")) &&
                !(spinner2.getSelectedItem().toString().equalsIgnoreCase("Select an option"))) {


            if (y1.isChecked()) {
                heartmark = heartmark -5;
             } else if (n1.isChecked()) {
                heartmark = heartmark  +5;
                }
            if (y2.isChecked()) {
                heartmark = heartmark -3;
            } else if (n2.isChecked()) {
                heartmark = heartmark +3;
            }
            if (y3.isChecked()) {
                heartmark = heartmark -4;
            } else if (n3.isChecked()) {
                heartmark = heartmark +2;
            }
            if (y4.isChecked()) {
                heartmark = heartmark -3;
            } else if (n4.isChecked()) {
                heartmark = heartmark +3;
            }
            if (spinner1.getSelectedItem().toString().equalsIgnoreCase("Yes")) {
                heartmark = heartmark -3;
            } else if (spinner1.getSelectedItem().toString().equalsIgnoreCase("No")) {
                heartmark = heartmark +3;
            } else { heartmark = heartmark -1; }


            if (spinner2.getSelectedItem().toString().equalsIgnoreCase("Yes")) { heartmark = heartmark + 1; }
            else if (spinner2.getSelectedItem().toString().equalsIgnoreCase("No")) { heartmark = heartmark -2; }
            else{ heartmark = heartmark -3; }


            this.person.print();

            Intent intent = new Intent(this, MainActivityPage4.class);
            person.setHeartMark(heartmark);
            intent.putExtra("FromMain3ToMain4", this.person);
            startActivity(intent);
        }

            else {

            Toast.makeText(this, "Please fill in the form", Toast.LENGTH_SHORT).show();
        }


    }

        private void processIntentData(){
            Intent intent = getIntent();
            if (intent != null) {
                Person transferredPerson = intent.getParcelableExtra("FromMain2ToMain3");
                if (transferredPerson != null) {
                    this.person = transferredPerson;
                    this.person.print();
                    Log.d(MainActivityPage2.TAG, "ok");
                } else {
                    Log.d(MainActivityPage2.TAG, "No Person found after transfer from Activity2");
                }
            } else {
                Log.d(MainActivityPage2.TAG, "Error when transferring from Activity2");
            }
        }




    }




