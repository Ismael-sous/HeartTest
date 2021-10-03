package com.example.healthtest;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;
import android.widget.Toast;


/**
 * Cette classe correspond à la première activité de l'application
 * Elle permet de passer à l'activité suivante
 * Elle permet aussi de gérer l'autorisation de stockage des données
 * @author Ismaël SOUSANE
 * @version final
 */

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        verifyStoragePermissions(this);

    }

    public void action_Start(android.view.View v) {
        final EditText yName = findViewById(R.id.yName);
        String name = String.valueOf(yName.getText());
        Person person = new Person();

        if (name.equals("")) {
            Toast.makeText(this, "Please enter your name", Toast.LENGTH_SHORT).show();
        } else {
            Log.d("MainActivity", name);
            Intent intent = new Intent(this, MainActivityPage2.class);
            person.setName(name);
            intent.putExtra("Main1toMain2", person);
            startActivity(intent);
        }

    }

    public void action_Exit(android.view.View v) {
        AlertDialog.Builder builder;
        builder = new AlertDialog.Builder(this, android.R.style.Theme_Material_Dialog_Alert);
        builder.setTitle("Quitter")
                .setMessage("Voulez-vous quitter cette application ?")
                .setPositiveButton(android.R.string.yes, (dialog, which) -> {


                    Intent intent = new Intent(Intent.ACTION_MAIN);
                    intent.addCategory(Intent.CATEGORY_HOME);
                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(intent);

                   /*  System.exit(0);
                    finish(); */

                })
                .setNegativeButton(android.R.string.no, (dialog, which) -> {

                })
                .setIcon(android.R.drawable.ic_dialog_alert)
                .show();

    }

    /**
     * Listes des permissions
     */
    private static final int REQUEST_EXTERNAL_STORAGE = 1;
    private static final String[] PERMISSIONS_STORAGE = {
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE
    };

    /**
     * Cette méthode permet d'afficher un popup pour que l'utilisateur autorise le stockage de données sur son smatphone
     */
    public static void verifyStoragePermissions(Activity activity) {
        // Vérifie si nous avons les droits d'écriture
        int permission = ActivityCompat.checkSelfPermission(activity,
                Manifest.permission.WRITE_EXTERNAL_STORAGE);
        if (permission != PackageManager.PERMISSION_GRANTED) {
            // Demander les droits à l'utilisateur
            ActivityCompat.requestPermissions(
                    activity,
                    PERMISSIONS_STORAGE,
                    REQUEST_EXTERNAL_STORAGE
            );
        }
    }
}





