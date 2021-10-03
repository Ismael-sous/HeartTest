package com.example.healthtest;

import android.os.Build;
import android.os.Parcel;
import android.os.Parcelable;
import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;

/**
 /**
 * Cette classe correspond à la classe personne.
 * Elle permet d'avoir toutes les informations de l'utilisateur.
 * @author Ismaël SOUSANE
 * @version final
 * https://developer.android.com/reference/android/os/Parcelable#java
 */

public class Person implements Parcelable {

        private String name;
        private int age;
        private String sexe;
        private int mData;
        private int heartMark;
        private int heartCheckmark;
        private boolean cardiacCheckUp;
        private int physicalMark;

        public static final String EMAIL="ismael.sousane@gmail.com";
        public static final String PASSWORD="*******";


    public Person() {

            this.setName("None");
            this.setSexe("None");
            this.setAge(0);
            this.setCardiacCheckUp(false);
            this.setHeartMark(0);
            this.setHeartCheckmark(0);
            this.setPhysicalMark(0);
    }

    public String getName() { return name; }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getSexe() {
        return sexe;
    }

    public void setSexe(String sexe) {
        this.sexe = sexe;
    }

    public int getmData() {
        return mData;
    }

    public void setmData(int mData) {
        this.mData = mData;
    }

    public boolean getCardiacCheckUp() {
        return cardiacCheckUp;
    }

    public void setCardiacCheckUp(boolean cardiacCheckUp) {
        this.cardiacCheckUp = cardiacCheckUp;
    }

    public String getFullname()  {
            return this.name + " " + this.age;
        }

    public int getHeartMark() {
        return heartMark;
    }

    public void setHeartMark(int heartMark) {
        this.heartMark = heartMark;
    }

    public int getHeartCheckmark() {
        return heartCheckmark;
    }

    public void setHeartCheckmark(int heartCheckmark) {
        this.heartCheckmark = heartCheckmark;
    }

    public int getPhysicalMark() {
        return physicalMark;
    }

    public void setPhysicalMark(int physicalMark) {
        this.physicalMark = physicalMark;
    }


    public int describeContents() {
            return 0;
        }

    @NonNull
    public String toString() {
        StringBuilder sBuilder = new StringBuilder("\t name: " + this.getName() + "\n");
        sBuilder.append("\t Genre: ").append(this.getSexe()).append("\n");
        sBuilder.append("\t age: ").append(this.getAge()).append("\n");
        sBuilder.append("\t Cardiac Checkup: ").append(this.getCardiacCheckUp()).append("\n");
        sBuilder.append("\t heartMark: ").append(this.getHeartMark()).append("\n");
        sBuilder.append("\t heartCheckMark: ").append(this.getHeartCheckmark()).append("\n");
        sBuilder.append("\t PhysicalMark: ").append(this.getPhysicalMark()).append("\n");


        return sBuilder.toString();
    }

    public void print(){
        System.out.println("Person :");
        System.out.print(this);
        System.out.println();
    }

    @RequiresApi(api = Build.VERSION_CODES.Q)
    @Override // Parcelable method
    public void writeToParcel(Parcel dest, int flags) {

        dest.writeString(this.getName());
        dest.writeString(this.getSexe());
        dest.writeInt(this.getAge());
        dest.writeInt(this.getHeartMark());
        dest.writeInt(this.getHeartCheckmark());
        dest.writeInt(this.getPhysicalMark());
        dest.writeBoolean(this.getCardiacCheckUp());
    }
    /**
     * https://developer.android.com/reference/android/os/Parcelable#java
     */
    public static final Parcelable.Creator<Person> CREATOR
            = new Parcelable.Creator<Person>() {
        @RequiresApi(api = Build.VERSION_CODES.Q)
        public Person createFromParcel(Parcel in) {
            return new Person(in);
        }
        public Person[] newArray(int size) {
            return new Person[size];
        }
    };
    /**
     * https://developer.android.com/reference/android/os/Parcelable#java
     */
    @RequiresApi(api = Build.VERSION_CODES.Q)
    private Person(Parcel in) {
        this.setName(in.readString());
        this.setSexe(in.readString());
        this.setAge(in.readInt());
        this.setHeartMark(in.readInt());
        this.setHeartCheckmark(in.readInt());
        this.setPhysicalMark(in.readInt());
        this.setCardiacCheckUp(in.readBoolean());
    }
    }





