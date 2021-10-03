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
 * Elle permet de passer au bilan
 * Elle test l'activité sportive
 * @author Ismaël SOUSANE
 * @version final
 */

public class MainActivityPage5 extends AppCompatActivity {

    private RadioButton yes2,no2,yes3,no3;
    private Spinner spinner6, spinner7, spinner8;
    private Person person;
    private int physicalMark;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        processIntentData();
        setContentView(R.layout.activity_main5);
        yes2 = findViewById(R.id.yes2);
        yes3 = findViewById(R.id.yes3);
        no2 = findViewById(R.id.no2);
        no3 = findViewById(R.id.no3);
        spinner6 = findViewById(R.id.spinner6);
        spinner7 = findViewById(R.id.spinner7);
        spinner8 = findViewById(R.id.spinner8);

    }


    public void onClickNext5(View v) {
        if ((yes2.isChecked() || no2.isChecked()) &&
                (yes3.isChecked() || no3.isChecked()) &&
                !(spinner6.getSelectedItem().toString().equalsIgnoreCase("Select an option")) &&
                !(spinner7.getSelectedItem().toString().equalsIgnoreCase("Select an option")) &&
                !(spinner8.getSelectedItem().toString().equalsIgnoreCase("Select an option"))) {

            if (yes2.isChecked()) {
                physicalMark = physicalMark +5;
            } else if (no2.isChecked()) {
                physicalMark = physicalMark  -4;
            }
            if (yes3.isChecked()) {
                physicalMark = physicalMark +4;
            } else if (no3.isChecked()) {
                physicalMark = physicalMark -2;
            }

            if (spinner6.getSelectedItem().toString().equalsIgnoreCase("3+")) {
                physicalMark = physicalMark +6; }
            else if (spinner6.getSelectedItem().toString().equalsIgnoreCase("2")) {
                physicalMark = physicalMark +4; }
            else if (spinner6.getSelectedItem().toString().equalsIgnoreCase("1")) {
                physicalMark = physicalMark +2; }
            else { physicalMark = physicalMark -6; }

            if (spinner7.getSelectedItem().toString().equalsIgnoreCase("Always")) {
                physicalMark = physicalMark +7; }
            else if (spinner7.getSelectedItem().toString().equalsIgnoreCase("Usually")) {
                physicalMark = physicalMark +4; }
            else if (spinner7.getSelectedItem().toString().equalsIgnoreCase("Sometimes")) {
                physicalMark = physicalMark +2; }
            else if (spinner7.getSelectedItem().toString().equalsIgnoreCase("Occasionally")) {
                physicalMark = physicalMark -2; }
            else { physicalMark = physicalMark -4; }


            if (spinner8.getSelectedItem().toString().equalsIgnoreCase("Watch TV")) {
                physicalMark = physicalMark -6; }
            else if (spinner8.getSelectedItem().toString().equalsIgnoreCase("Walk around")) {
                physicalMark = physicalMark +4; }
            else if (spinner8.getSelectedItem().toString().equalsIgnoreCase("Play video games")) {
                physicalMark = physicalMark -4; }
            else { physicalMark = physicalMark +6; }

                this.person.print();
                Intent intent;
                person.setPhysicalMark(physicalMark);
                intent = new Intent(this, Bilan.class);
                intent.putExtra("activity5ToBilan", this.person);
                startActivity(intent);
            }

         else {

            Toast.makeText(this, "Please fill in the form", Toast.LENGTH_SHORT).show();
        }
    }


    public void onClickPrevious5(View view) {
        onBackPressed(); }

    private void processIntentData(){
        Intent intent = getIntent();
        if (intent != null) {
            Person transferredPerson = intent.getParcelableExtra("FromMain4ToMain5");
            if (transferredPerson != null) {
                this.person = transferredPerson;
                this.person.print();
                Log.d(MainActivityPage2.TAG, "ok");
            } else {
                Log.d(MainActivityPage2.TAG, "No Person found after transfer from Activity4");
            }
        } else {
            Log.d(MainActivityPage2.TAG, "Error when transferring from Activity4");
        }
    }
}