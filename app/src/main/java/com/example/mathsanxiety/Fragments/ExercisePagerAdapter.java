package com.example.mathsanxiety.Fragments;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.mathsanxiety.Models.ExerciseInfo;

import java.util.ArrayList;
import java.util.List;

public class ExercisePagerAdapter extends FragmentPagerAdapter {
    private List<Fragment> fragmentList = new ArrayList<>();
    private ArrayList<ExerciseInfo> exercise = new ArrayList<>();

    public ExercisePagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int i) {
        return fragmentList.get(i);
    }

    @Override
    public int getCount() {
        return exercise.size();
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return exercise.get(position).getTitle();
    }

    public void addFragment(Fragment fr, ExerciseInfo currExercise){
        fragmentList.add(fr);
        exercise.add(currExercise);
    }
}
