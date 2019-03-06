package com.example.mathsanxiety.Fragments;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.mathsanxiety.Models.PlaylistInfo;

import java.util.ArrayList;
import java.util.List;

public class ChapterPagerAdapter extends FragmentPagerAdapter {

    private List<Fragment> fragmentList = new ArrayList<>();
    private List<PlaylistInfo> chapters = new ArrayList<>();

    public ChapterPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int i) {
        return fragmentList.get(i);
    }

    @Override
    public int getCount() {
        return chapters.size();
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return chapters.get(position).getTitle();
    }

    public void addFragment(Fragment fr, PlaylistInfo chapter){
        fragmentList.add(fr);
        chapters.add(chapter);
    }
}
