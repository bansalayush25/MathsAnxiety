package com.example.mathsanxiety.Utilities;

import android.util.Log;

import com.example.mathsanxiety.Models.ExerciseInfo;
import com.example.mathsanxiety.Models.PlaylistInfo;
import com.example.mathsanxiety.Models.Types;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class ConvertJson {
    public static ArrayList<PlaylistInfo> toPlaylist(JSONObject jsonObject) throws JSONException {
        ArrayList<PlaylistInfo> playlists = new ArrayList<>();
        JSONArray playlistArray = jsonObject.getJSONArray("items");
        for(int i=0; i<playlistArray.length(); i++){
            JSONObject play = playlistArray.getJSONObject(i);
            String playlistId = play.getString("id");
            JSONObject snippet = play.getJSONObject("snippet");
            String title = snippet.getString("title");
            String description = snippet.getString("description");
            String thumbnail = snippet.getJSONObject("thumbnails").getJSONObject("medium").getString("url");
            PlaylistInfo currPlaylist = new PlaylistInfo(Types.PLAYLIST_TYPE, playlistId, title, description, thumbnail);
            playlists.add(currPlaylist);
        }
        return playlists;
    }

    public static ArrayList<ExerciseInfo> toVideosPlaylist(JSONObject jsonObject) throws JSONException {
        ArrayList<ExerciseInfo> exercisesPlaylist = new ArrayList<>();
        JSONArray exercisesPlaylistArray = jsonObject.getJSONArray("items");
        for (int i=0; i<exercisesPlaylistArray.length(); i++){
            JSONObject exercise = exercisesPlaylistArray.getJSONObject(i);
            JSONObject snippet = exercise.getJSONObject("snippet");
            String videoId = snippet.getJSONObject("resourceId").getString("videoId");
            String title = snippet.getString("title");
            String description = snippet.getString("description");
            String thumbnail = snippet.getJSONObject("thumbnails").getJSONObject("medium").getString("url");
            ExerciseInfo currExercise = new ExerciseInfo(Types.EXERCISE_TYPE, videoId, title, description, thumbnail);
            exercisesPlaylist.add(currExercise);
        }
        return exercisesPlaylist;
    }
}
