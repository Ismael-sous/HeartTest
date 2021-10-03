package com.example.healthtest;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.text.Html;
import android.text.Spanned;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Cette classe correspond à la dernière page de l'application, le Bilan
 * Elle permet d'afficher les resultats et de nombreuses fonctionnalités
 * Elle retourne les scores, permets de les partager, de les telecharger et de se rendre à l'acceuil ou de quitter
 * @author Ismaël SOUSANE
 * @version final
 */

public class Bilan extends AppCompatActivity {

    private Person person;
    private String data;



    @SuppressLint({"IntentReset", "SetTextI18n"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        processIntentData();
        setContentView(R.layout.activity_bilan);
        //Environment.getExternalStorageState();



        TextView textView31 = findViewById(R.id.textView31);
        TextView textView32 = findViewById(R.id.textView32);
        TextView textView33 = findViewById(R.id.textView33);
        TextView textView34 = findViewById(R.id.textView34);
        TextView textView35 = findViewById(R.id.textView35);
        TextView textView36 = findViewById(R.id.textView36);

        textView31.setText(person.getName());
        textView32.setText(person.getSexe());
        textView33.setText(String.valueOf(person.getAge()));

        //textView34.setText(String.valueOf(((person.getHeartMark()+25)/37)*10));//Convertir le resultat pour l'afficher sur 10.

        textView34.setText(person.getHeartMark() + 25 + "/42");

        if (person.getAge()>40) { textView35.setText(((person.getHeartCheckmark()+22+"/42"))); }
            else{ textView35.setText(((person.getHeartCheckmark()+12)+"/32")); }

        textView36.setText(person.getPhysicalMark() + 22 + "/50");//Convertir le resultat pour l'afficher sur 10.


        Button send1 = findViewById(R.id.send1);
        send1.setOnClickListener(view -> {
            Log.i("Send email", "jlvh;h;hbv");

            //String[] TO = {person.getMail()};
            Intent emailIntent = new Intent(Intent.ACTION_SEND);
            emailIntent.setData(Uri.parse("ismael.sousane@gmail.com"));
            emailIntent.setType("text/plain");

            //emailIntent.putExtra(Intent.EXTRA_EMAIL, TO);
            emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Résultat de l'activité");
            emailIntent.putExtra(Intent.EXTRA_TEXT, person.getName()+" a obtenu "+
                    (person.getHeartCheckmark()+person.getHeartMark()+person.getPhysicalMark()+69)+"/124 à ce test, essaie le vite !\n" +
                    "Vous retrouverez l'APK à installer sur votre mobile ainsi que le projet Androïd studio ici: https://drive.google.com/drive/folders/1-jKw6KSFX0f8WhvxnHXWiNavRQV_vDAw?usp=sharing");

            try {
                startActivity(Intent.createChooser(emailIntent, "Send mail..."));
                finish();
                Log.i("Finished sending email...", "");
            } catch (android.content.ActivityNotFoundException ex) {
                Toast.makeText(Bilan.this,
                        "There is no email client installed.", Toast.LENGTH_SHORT).show();
            }
        }); }

    public void goHome(android.view.View v) {
        Intent intent;
        intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    public void action_Exit(android.view.View v){
        AlertDialog.Builder builder;
        builder = new AlertDialog.Builder(this, android.R.style.Theme_Material_Dialog_Alert);
        builder.setTitle("Quitter")
                .setMessage("Voulez-vous quitter cette application HeartTest ?")
                .setPositiveButton(android.R.string.yes, (dialog, which) -> {
                    Intent intent = new Intent(Intent.ACTION_MAIN);
                    intent.addCategory(Intent.CATEGORY_HOME);
                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(intent);        })
                .setNegativeButton(android.R.string.no, (dialog, which) -> {

                })
                .setIcon(android.R.drawable.ic_menu_close_clear_cancel)
                .show();
    }

    public void showInfo1(android.view.View v){

        AlertDialog.Builder builder;
        builder = new AlertDialog.Builder(this, android.R.style.Theme_Material_Dialog_Alert);
        Spanned myURL = (Html.fromHtml("<a href=\"https://fedecardio.org/je-m-informe/controler-le-diabete-par-la-nutrition/\">Cliquez ici</a>"));

        if (person.getHeartMark()>0) {
            builder.setTitle("Mon Coeur")
                    .setMessage("Vous semblez en bonne santé mais ne négligez pas l'état de votre coeur.\n" +
                            "Parlez des risques cardiovasculaire avec votre médecin traitant.")
                    .setPositiveButton(android.R.string.ok, (dialog, which) -> { })
                    .setIcon(android.R.drawable.ic_dialog_info)
                    .show();
        }
        else{
            builder.setTitle("Mon Coeur")
                    .setMessage("Vous avez eu "+((person.getHeartMark()+25)+"/42")+"\n Pensez à vous faire suivre régulièrement par un cardiologue. \n Ne cedez pas à l'automédication. " +
                            "\nLe taux de mauvais cholestérol (LDL-cholestérol) dans le sang à ne pas dépasser dépend de votre risque cardiovasculaire" +
                            "\nSi vous êtes diabétique, controlez votre nutrition ici:"+myURL+
                            "\nLes maladies cardiovasculaires peuvent être héréditaires, parlez-en à votre médecin.")
                    .setPositiveButton(android.R.string.ok, (dialog, which) -> { })
                    .setIcon(android.R.drawable.ic_dialog_info)
                    .show();
        }
    }

    public void showInfo2(android.view.View v){

        AlertDialog.Builder builder;
        builder = new AlertDialog.Builder(this, android.R.style.Theme_Material_Dialog_Alert);

        if (person.getHeartCheckmark()>4) {
            builder.setTitle("Mon Suivi Cardiaque")
                    .setMessage("Vous avez de bon résultat, vous savez prendre soin de votre coeur, mais pensez à le suivre régulièrement !")
                    .setPositiveButton(android.R.string.ok, (dialog, which) -> { })
                    .setIcon(android.R.drawable.ic_dialog_info)
                    .show();
        }
        else if (person.getHeartCheckmark() < 4 && person.getHeartCheckmark() > -16) {
            builder.setTitle("Mon Suivi Cardiaque")
                    .setMessage("Vos résultats sont moyens, parlez à votre medecin des risques cardiovasculaires et éventuellement réalisez un bilan cardiaque.")
                    .setPositiveButton(android.R.string.ok, (dialog, which) -> { })
                    .setIcon(android.R.drawable.ic_dialog_info)
                    .show(); }
        else{
            builder.setTitle("Mon Suivi Cardiaque")
                    .setMessage("Vous avez "+person.getAge()+" ans et vous commencer à prendre de l'âge.\n " +
                            "Votre score est inquiétant alors pensez à effectuer votre Bilan Cardiaque à partir de 45 ans chez l’homme et de 50 ans chez la femme.")
                    .setPositiveButton(android.R.string.ok, (dialog, which) -> { })
                    .setIcon(android.R.drawable.ic_dialog_info)
                    .show();
        }
    }

    public void showInfo3(android.view.View v){

        AlertDialog.Builder builder;
        builder = new AlertDialog.Builder(this, android.R.style.Theme_Material_Dialog_Alert);

        if (person.getPhysicalMark()>10) {
            builder.setTitle("Mon Activité Physique")
                    .setMessage("Vous avez un bon score avec "+(person.getPhysicalMark()+22)+" point. Continuez à maintenir une activité physique régulière.")
                    .setPositiveButton(android.R.string.ok, (dialog, which) -> { })
                    .setIcon(android.R.drawable.ic_dialog_info)
                    .show();
        }
        else if (person.getPhysicalMark()<10 && person.getPhysicalMark()> 0 ) {
            builder.setTitle("Mon Activité Physique")
                    .setMessage("Vous avez un score moyen avec "+(person.getPhysicalMark()+22) +"/50 point. Pensez à pratiquer une activité physique régulière.")
                    .setPositiveButton(android.R.string.ok, (dialog, which) -> { })
                    .setIcon(android.R.drawable.ic_dialog_info)
                    .show();
        }

        else {
            builder.setTitle("Mon Activité Physique")
                    .setMessage("Vous avez un score insuffisant avec "+(person.getPhysicalMark() + 22 + "/50")+" point. La marche est l’activité physique la plus simple à mettre en place dans votre routine quotidienne : " +
                            "\ndescendre une station de bus ou de métro avant votre arrêt habituel, téléphoner debout ou réaliser les petits trajets quotidiens à pied ou à vélo.\n" +
                            "30 minutes d’activité physique par jour diminuent de 25 à 30 % le risque de mortalité cardiovasculaire.")
                    .setPositiveButton(android.R.string.ok, (dialog, which) -> { })
                    .setIcon(android.R.drawable.ic_dialog_info)
                    .show();
        }
    }

    public void action_Website(View view) {
        Intent intent;
        intent = new Intent(Intent.ACTION_VIEW);
        String url = "https://fedecardio.org/je-me-teste/test-3-minutes/";
        intent.setData(Uri.parse(url));
        startActivity(intent);
    }

    private void processIntentData() {
        Intent intent = getIntent();
        if (intent != null) {
            Person transferredPerson = intent.getParcelableExtra("activity5ToBilan");
            if (transferredPerson != null) {
                this.person = transferredPerson;
                this.person.print();
                Log.d(MainActivityPage2.TAG, "ok");
            } else {
                Log.d(MainActivityPage2.TAG, "No Person found after transfer from Activity5");
            }
        } else {
            Log.d(MainActivityPage2.TAG, "Error when transferring from Activity5");
        }
    }


    private void writeTo(String data) {

        try {

            File chemin = this.getExternalFilesDir(Environment.DIRECTORY_DOWNLOADS);
            Log.d("TAG", chemin.getPath());
            File fichier = new File(chemin, "fichier.txt");
            Toast.makeText(this, "Telechargement effectué, fichier dans "+chemin.getPath(), Toast.LENGTH_LONG).show();
            FileWriter filewriter = new FileWriter(fichier,false);
            filewriter.write(data);
            filewriter.close();

        } catch (IOException e) {
            Log.e("Exception", "File write failed: " + e.toString());
        }

    }


    public void writeToFile(View view) {
        String data = ("Voici les resultat de "+person.getName()+": "+(person.getHeartCheckmark()+person.getHeartMark()+person.getPhysicalMark()+69+"/124."+
                "\n Vous pouvez consulter le site sur lequel est basé ce test ici:\n https://fedecardio.org/je-me-teste/test-3-minutes/\n" +
                "Vous retrouverez l'APK à installer sur votre mobile ainsi que le projet Androïd studio ici: https://drive.google.com/drive/folders/1-jKw6KSFX0f8WhvxnHXWiNavRQV_vDAw?usp=sharing\n"+person.toString()));
        writeTo(new String(data));
    }

}
