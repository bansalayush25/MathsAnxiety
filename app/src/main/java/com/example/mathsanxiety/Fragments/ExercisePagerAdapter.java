package com.example.mathsanxiety.Fragments;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.util.Log;

import com.example.mathsanxiety.Models.ExerciseInfo;
import com.example.mathsanxiety.Models.PlaylistInfo;

import java.util.ArrayList;
import java.util.List;

public class ExercisePagerAdapter extends FragmentPagerAdapter {
    private List<Fragment> fragmentList = new ArrayList<>();
    private List<ExerciseInfo> exercises = new ArrayList<>();
    private List<String> titles = new ArrayList<>();

    public ExercisePagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int i) {
        return fragmentList.get(i);
    }

    @Override
    public int getCount() {
        return titles.size();
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        Log.d("ExerciseAdapter", "getPageTitle: ");
//        return exercises.get(position).getTitle();
        return titles.get(position);
    }

    public void addFragment(Fragment fr, String title){
        fragmentList.add(fr);
//        exercises.add(exercise);
        titles.add(title);
    }
}
