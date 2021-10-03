package com.example.healthtest;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Cette classe correspond à la deuxième activité de l'application
 * Elle permet de passer à l'activité suivante
 * Elle retourne le pseudo, prends l'age et le sexe.
 * @author Ismaël SOUSANE
 * @version final
 */

public class MainActivityPage2 extends AppCompatActivity {

    public static final String TAG = "MainActivityPage2";
    private TextView display;
    private RadioButton y3;
    private RadioButton n3;
    private EditText editAge;
    private Person person;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        processIntentData();
        setContentView(R.layout.activity_main2);
        display = (TextView) findViewById(R.id.display);
        display.setText(person.getName());
        editAge = findViewById(R.id.editAge);
        y3 = findViewById(R.id.y3);
        n3 = findViewById(R.id.n3);
    }

    public void onClickNext1(View view)
    {
        if ((y3.isChecked()||n3.isChecked()) && (!editAge.getText().toString().equals(""))) {

             if (Integer.parseInt(editAge.getText().toString()) < 150 && Integer.parseInt(editAge.getText().toString())>0 ){

                if (y3.isChecked()) {person.setSexe("Woman");}
                else {person.setSexe("Man");}

                Intent intent = new Intent(this, MainActivityPage3.class);
                int age = Integer.parseInt(editAge.getText().toString());
                person.setAge(age);
                intent.putExtra("FromMain2ToMain3", this.person);
                startActivity(intent); }

             else {Toast.makeText(this, "Age invalid", Toast.LENGTH_LONG).show(); }}

        else { Toast.makeText(this, "Please fill in the form", Toast.LENGTH_SHORT).show(); }
    }


    private void processIntentData() {
        Intent intent = getIntent();
        if (intent != null) {
            Person transferredPerson = intent.getParcelableExtra("Main1toMain2");
            if (transferredPerson != null) {
                this.person = transferredPerson;
                this.person.print();
                Log.d(MainActivityPage2.TAG, "ok");
            } else {
                Log.d(MainActivityPage2.TAG, "No Person found after transfer from Activity1");
            }
        } else {
            Log.d(MainActivityPage2.TAG, "Error when transferring from Activity1");
        }
    }

            public void onClickPrevious1(View view) {
            onBackPressed();

    }
}