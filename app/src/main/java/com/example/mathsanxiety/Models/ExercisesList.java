package com.example.mathsanxiety.Models;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

public class ExercisesList implements Parcelable {

    ArrayList<ExerciseInfo> exerciseList;

    public ExercisesList(ArrayList<ExerciseInfo> exerciseList) {
        this.exerciseList = exerciseList;
    }

    public ExercisesList(Parcel parcel){
        exerciseList = new ArrayList<>();
        parcel.readList(exerciseList, ExerciseInfo.class.getClassLoader());
    }


    public ArrayList<ExerciseInfo> getExerciseList() {
        return exerciseList;
    }

    public void setExerciseList(ArrayList<ExerciseInfo> exerciseList) {
        this.exerciseList = exerciseList;
    }

    public static final Creator<ExercisesList>CREATOR = new Creator<ExercisesList>() {
        @Override
        public ExercisesList createFromParcel(Parcel parcel) {
            return new ExercisesList(parcel);
        }

        @Override
        public ExercisesList[] newArray(int size) {
            return new ExercisesList[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeList(exerciseList);
    }
}
