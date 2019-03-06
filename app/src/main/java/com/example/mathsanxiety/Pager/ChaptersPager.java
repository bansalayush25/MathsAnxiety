package com.example.mathsanxiety.Pager;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.mathsanxiety.Fragments.ChapterPagerAdapter;
import com.example.mathsanxiety.Models.ExercisesList;
import com.example.mathsanxiety.Models.PlaylistInfo;
import com.example.mathsanxiety.R;

import java.util.ArrayList;

public class ChaptersPager extends Fragment {

    ArrayList<PlaylistInfo> chapters;
    ArrayList<ExercisesList> exercises;
    View rootView;
    TabLayout chaptersTab;
    ViewPager chaptersPager;
    Fragment fr = null;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        chapters = getArguments().getParcelableArrayList("chapters");
        exercises = getArguments().getParcelableArrayList("exercises");
        rootView = inflater.inflate(R.layout.chapters_tab, container, false);
        chaptersTab = rootView.findViewById(R.id.chaptersTab);
        chaptersPager = rootView.findViewById(R.id.chaptersPager);

        setupChapterViewPager(chaptersPager);
        chaptersTab.setupWithViewPager(chaptersPager);
        chaptersTab.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                chaptersPager.setCurrentItem(tab.getPosition());
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

    public void setupChapterViewPager(ViewPager viewPager){
        ChapterPagerAdapter chapterPagerAdapter= new ChapterPagerAdapter(getChildFragmentManager());
        for(int i=0; i<chapters.size(); i++){
            Bundle bundle = new Bundle();
            bundle.putParcelableArrayList("exercise", exercises.get(i).getExerciseList());
            fr = new ExercisePager();
            fr.setArguments(bundle);
            chapterPagerAdapter.addFragment(fr, chapters.get(i));
        }
        viewPager.setAdapter(chapterPagerAdapter);
    }
}
