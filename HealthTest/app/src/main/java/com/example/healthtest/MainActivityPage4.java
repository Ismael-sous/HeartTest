package com.example.healthtest;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.RadioButton;
import android.widget.Toast;

/**
 * Cette classe correspond à la quatrième activité de l'application
 * Elle permet de passer à l'activité suivante
 * Elle correspond à la deuxième page de test sur le suivi du coeur
 * @author Ismaël SOUSANE
 * @version final
 */

public class MainActivityPage4 extends AppCompatActivity {

    private RadioButton y1,n1,y2,n2,y3,n3;
    private Person person;
    private int heartCheckmark;
    public static final String TAG = "MainActivityPage4";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        processIntentData();
        setContentView(R.layout.activity_main4);
        y1 = findViewById(R.id.y1);
        y2 = findViewById(R.id.y2);
        y3 = findViewById(R.id.y3);
        n1 = findViewById(R.id.n1);
        n2 = findViewById(R.id.n2);
        n3 = findViewById(R.id.n3);
    }

    public void onClickPrevious3(View view) {
        onBackPressed(); }

    public void onClickNext3(View v)
    {
        if (    (y1.isChecked() || n1.isChecked()) &&
                (y2.isChecked() || n2.isChecked()) &&
                (y3.isChecked() || n3.isChecked())
                ) {

            if (y1.isChecked()) { heartCheckmark = heartCheckmark +6; }
            else if (n1.isChecked() && person.getAge()>40) { heartCheckmark = heartCheckmark  -8; }
                else {heartCheckmark = heartCheckmark  -4;}

            if (y2.isChecked()) { heartCheckmark = heartCheckmark +8; }
            else if (n2.isChecked() && person.getAge()>50) { heartCheckmark = heartCheckmark -6; }
                else {heartCheckmark = heartCheckmark  -2;}

            if (y3.isChecked()) { heartCheckmark = heartCheckmark +6; }
            else if (n3.isChecked() && person.getAge()>60) { heartCheckmark = heartCheckmark -8; }
                else {heartCheckmark = heartCheckmark  -6;}


            this.person.print();

            Intent intent;
            person.setHeartCheckmark(heartCheckmark);
            intent = new Intent(this, MainActivityPage5.class);
            intent.putExtra("FromMain4ToMain5", this.person);
            startActivity(intent);}

        else {

            Toast.makeText(this, "Please fill in the form", Toast.LENGTH_SHORT).show();
        }
    }

    private void processIntentData(){
        Intent intent = getIntent();
        if (intent != null) {
            Person transferredPerson = intent.getParcelableExtra("FromMain3ToMain4");
            if (transferredPerson != null) {
                this.person = transferredPerson;
                this.person.print();
                Log.d(MainActivityPage2.TAG, "ok");
            } else {
                Log.d(MainActivityPage2.TAG, "No Person found after transfer from Activity3");
            }
        } else {
            Log.d(MainActivityPage2.TAG, "Error when transferring from Activity3");
        }
    }
}