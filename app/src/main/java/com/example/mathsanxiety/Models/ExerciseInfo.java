package com.example.mathsanxiety.Models;

import android.os.Parcel;
import android.os.Parcelable;

public class ExerciseInfo{
    int id;
    String videoId;
    String title;
    String description;
    String thumbnail;

    public ExerciseInfo(int id, String videoId, String title, String description, String thumbnail) {
        this.id = id;
        this.videoId = videoId;
        this.title = title;
        this.description = description;
        this.thumbnail = thumbnail;
    }



    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getVideoId() {
        return videoId;
    }

    public void setVideoId(String videoId) {
        this.videoId = videoId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }
}
