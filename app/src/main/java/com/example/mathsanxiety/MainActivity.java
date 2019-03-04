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

import com.example.mathsanxiety.Models.PlaylistInfo;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.Scanner;

import com.example.mathsanxiety.Pager.ChaptersPager;
import com.example.mathsanxiety.Utilities.*;

public class MainActivity extends AppCompatActivity {

    private static final String APIKEY = "AIzaSyBHvT9NR-1fpOEjjKwTARqQ3dEqDo7Ru3E";
    private static final String CHANNEL = "UCk2LItw7-RzhihHWlMW8agg";
    private static String url = "https://www.googleapis.com/youtube/v3/playlists";

    List<PlaylistInfo> playlists;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getPlaylistInfo();

    }

    public URL getJsonData() throws MalformedURLException {
        Uri uri = Uri.parse(url).buildUpon()
                .appendQueryParameter("part", "snippet")
                .appendQueryParameter("channelId", CHANNEL)
                .appendQueryParameter("maxResults", "20")
                .appendQueryParameter("key", APIKEY)
                .build();
        return new URL(uri.toString());
    }

    public void getPlaylistInfo() {
        try {
            URL finalUrl = getJsonData();
            new RequestPlaylistAPI().executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, finalUrl);

        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }

    public class RequestPlaylistAPI extends AsyncTask<URL, Void, JSONObject> {

        @Override
        protected JSONObject doInBackground(URL... urls) {
            HttpURLConnection urlConnection = null;
            try {
                urlConnection = (HttpURLConnection) urls[0].openConnection();
                InputStream in = urlConnection.getInputStream();
                Scanner scanner = new Scanner(in);
                scanner.useDelimiter("\\A");

                boolean hasInput = scanner.hasNext();
                if (hasInput) {
                    String x = scanner.next();
                    return new JSONObject(x);
                } else {
                    return null;
                }
            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            } finally {
                urlConnection.disconnect();
            }

            return null;
        }

        @Override
        protected void onPostExecute(JSONObject jsonObject) {
            try {
                Log.d("MAIN", "onPostExecute: " + jsonObject.toString());
                playlists = ConvertJson.toPlaylist(jsonObject);

                ChaptersPager fragment = new ChaptersPager();
//                Bundle bundle = new Bundle();
//                bundle.putParcelableArrayList("playlist", (Parcelable) playlists);
//                fragment.setArguments(bundle);
                getSupportFragmentManager().beginTransaction().add(R.id.parentLayout, fragment, ChaptersPager.class.getSimpleName()).commit();

            } catch (JSONException e) {
                e.printStackTrace();
            }
        }


    }
}
