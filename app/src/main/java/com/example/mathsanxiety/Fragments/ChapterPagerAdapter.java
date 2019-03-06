package com.example.mathsanxiety.Fragments;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.ViewGroup;

import com.example.mathsanxiety.Models.PlaylistInfo;

import java.util.ArrayList;
import java.util.List;

public class ChapterPagerAdapter extends FragmentPagerAdapter {

    private List<Fragment> fragmentList = new ArrayList<>();
    private List<PlaylistInfo> chapters = new ArrayList<>();
    private List<String> title = new ArrayList<>();
    public ChapterPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int i) {
        return fragmentList.get(i);
    }

    @Override
    public int getCount() {
        return title.size();
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
//        return chapters.get(position).getTitle();
        return title.get(position);
    }

    public void addFragment(Fragment fr, String chapter){
        fragmentList.add(fr);
//        chapters.add(chapter);
        title.add(chapter);
    }
}
