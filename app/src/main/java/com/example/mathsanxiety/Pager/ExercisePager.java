package com.example.mathsanxiety.Pager;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.mathsanxiety.Fragments.ExercisePagerAdapter;
import com.example.mathsanxiety.Fragments.TabFragment;
import com.example.mathsanxiety.Models.ExerciseInfo;
import com.example.mathsanxiety.R;

import java.util.ArrayList;

public class ExercisePager extends Fragment {
    View rootView;
    TabLayout exerciseTab;
    ViewPager exercisePager;
    ArrayList<ExerciseInfo> exercise;
    Fragment fr = null;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        exercise = getArguments().getParcelableArrayList("exercise");
        rootView = inflater.inflate(R.layout.exercise_tab, container, false);
        exerciseTab = rootView.findViewById(R.id.exerciseTab);
        exercisePager = rootView.findViewById(R.id.exercisePager);
//        exercises = getArguments().getParcelable("exercise");
        setupExercisePager(exercisePager);
        exerciseTab.setupWithViewPager(exercisePager);
        exerciseTab.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                exercisePager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
        return rootView;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    public void setupExercisePager(ViewPager viewPager){
        ExercisePagerAdapter adapter = new ExercisePagerAdapter(getChildFragmentManager());
        for(int i=0; i<exercise.size(); i++){
            fr = new TabFragment();
            adapter.addFragment(fr, exercise.get(i));
        }
        viewPager.setAdapter(adapter);
    }
}
