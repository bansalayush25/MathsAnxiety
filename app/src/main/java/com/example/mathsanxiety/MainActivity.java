package com.example.mathsanxiety;

import android.net.Uri;
import android.os.AsyncTask;
import android.os.Parcelable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.example.mathsanxiety.Models.ExerciseInfo;
import com.example.mathsanxiety.Models.ExercisesList;
import com.example.mathsanxiety.Models.PlaylistInfo;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

import com.example.mathsanxiety.Pager.ChaptersPager;
import com.example.mathsanxiety.Utilities.*;

public class MainActivity extends AppCompatActivity {


    ArrayList<PlaylistInfo> chapters;
    HashMap<String, ArrayList<ExerciseInfo>> exercises;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        chapters = getIntent().getParcelableArrayListExtra("chapters");
        exercises = (HashMap<String, ArrayList<ExerciseInfo>>) getIntent().getSerializableExtra("exercises");
        ChaptersPager fragment = new ChaptersPager();
        Bundle bundle = new Bundle();
        bundle.putParcelableArrayList("chapters", chapters);
        bundle.putSerializable("exercises", exercises);
        fragment.setArguments(bundle);
        getSupportFragmentManager().beginTransaction().add(R.id.parentLayout, fragment, ChaptersPager.class.getSimpleName()).commit();

    }


}
