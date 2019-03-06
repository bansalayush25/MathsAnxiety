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
import com.example.mathsanxiety.Fragments.ExercisePagerAdapter;
import com.example.mathsanxiety.Models.PlaylistInfo;
import com.example.mathsanxiety.R;

import java.util.List;

public class ChaptersPager extends Fragment {

    List<PlaylistInfo> chapters;
    View rootView;
    TabLayout chaptersTab;
    ViewPager chaptersPager;
    Fragment fr = null;

    @Override
    public void setArguments(@Nullable Bundle args) {
        super.setArguments(args);
    }

    String[] chapters2 = {"1", "2", "3", "4"};

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
//        chapters = getArguments().getParcelable("playlist");
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
        for(int i=0; i<chapters2.length; i++){
            Bundle bundle = new Bundle();
            bundle.putString("title", chapters2[i]);
//            bundle.putParcelable("chapter_id", chapters.get(i));
            fr = new ExercisePager();
            fr.setArguments(bundle);
            chapterPagerAdapter.addFragment(fr, chapters2[i]);
        }
        viewPager.setAdapter(chapterPagerAdapter);
    }
}
