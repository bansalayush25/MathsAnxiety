package com.example.mathsanxiety;

import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
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

public class MainScreen extends AppCompatActivity {

    private static final String APIKEY = "AIzaSyBHvT9NR-1fpOEjjKwTARqQ3dEqDo7Ru3E";
    private static final String CHANNEL = "UCk2LItw7-RzhihHWlMW8agg";
    private static String playlists_url = "https://www.googleapis.com/youtube/v3/playlists";
    private static String videos_playlist_url = "https://www.googleapis.com/youtube/v3/playlistItems";

    ArrayList<PlaylistInfo> playlists;
//    ArrayList<ExercisesList> exercises;
    HashMap<String, ArrayList<ExerciseInfo>> exercises;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
        getPlaylistInfo();

    }

    public URL getPlaylistJsonData() throws MalformedURLException {
        Uri uri = Uri.parse(playlists_url).buildUpon()
                .appendQueryParameter("part", "snippet")
                .appendQueryParameter("channelId", CHANNEL)
                .appendQueryParameter("maxResults", "20")
                .appendQueryParameter("key", APIKEY)
                .build();
        return new URL(uri.toString());
    }

    public URL getVideoPlaylistJsonData(String playlist_id) throws MalformedURLException {
        Uri uri = Uri.parse(videos_playlist_url).buildUpon()
                .appendQueryParameter("part", "snippet")
                .appendQueryParameter("playlistId", playlist_id)
                .appendQueryParameter("maxResults", "20")
                .appendQueryParameter("key", APIKEY)
                .build();
        return new URL(uri.toString());
    }

    public void getPlaylistInfo() {
        try {
            URL finalUrl = getPlaylistJsonData();
            new RequestPlaylistAPI().executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, finalUrl);

        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }

    public class RequestVideoPlaylistAPI extends AsyncTask<Params, Void, JSONObject> {
        @Override
        protected JSONObject doInBackground(Params... params) {
            Params currParam = params[0];
            HttpURLConnection urlConnection = null;
            try {
                urlConnection = (HttpURLConnection) currParam.url.openConnection();
                InputStream in = urlConnection.getInputStream();
                Scanner scanner = new Scanner(in);
                scanner.useDelimiter("\\A");

                boolean hasInput = scanner.hasNext();
                if (hasInput) {
                    String x = scanner.next();
                    JSONObject tempJsonObject = new JSONObject(x);
                    tempJsonObject.put("playlistId", currParam.playlistId);
                    return tempJsonObject;
                } else {
                    return null;
                }
            } catch (IOException e) {
                Log.e("IOEXception", e.getMessage());
            } catch (JSONException e) {
                Log.e("JsonEXception", e.getMessage());
            } finally {
                urlConnection.disconnect();
            }

            return null;
        }

        @Override
        protected void onPostExecute(JSONObject jsonObject) {
            try {
                if(exercises==null)
                    exercises = new HashMap<>();
                String playlistId = jsonObject.getString("playlistId");
                jsonObject.remove("playlistId");
                ArrayList<ExerciseInfo> currExercises = ConvertJson.toVideosPlaylist(jsonObject);
                if(!exercises.containsKey(playlistId)){
                    exercises.put(playlistId, currExercises);
                }else{
                    ArrayList<ExerciseInfo> newExercises = exercises.get(playlistId);
                    for(ExerciseInfo e:currExercises)
                        newExercises.add(e);
                    exercises.put(playlistId, newExercises);
                }


                    if (exercises.size()==playlists.size()){
                        Intent intent = new Intent(MainScreen.this, MainActivity.class);
                        intent.putParcelableArrayListExtra("chapters", playlists);
                        intent.putExtra("exercises", exercises);
                        startActivity(intent);
                    }



            } catch (JSONException e) {
                e.printStackTrace();
            }
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
                Log.e("IOEXception", e.getMessage());
            } catch (JSONException e) {
                Log.e("JsonEXception", e.getMessage());
            } finally {
                urlConnection.disconnect();
            }

            return null;
        }

        @Override
        protected void onPostExecute(JSONObject jsonObject) {
            try {
                playlists = ConvertJson.toPlaylist(jsonObject);

                for(int i=0; i<playlists.size(); i++){
                    PlaylistInfo currPlaylist = playlists.get(i);
                    try {
                        URL finalUrl = getVideoPlaylistJsonData(currPlaylist.getPlaylistId());
                        Params params = new Params(finalUrl, currPlaylist.getPlaylistId());
                        new RequestVideoPlaylistAPI().executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, params);

                    } catch (MalformedURLException e) {
                        e.printStackTrace();
                    }
                }

            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

    }

    private class Params{
        URL url;
        String playlistId;

        public Params(URL url, String playlistId){
            this.url = url;
            this.playlistId = playlistId;
        }
    }
}
