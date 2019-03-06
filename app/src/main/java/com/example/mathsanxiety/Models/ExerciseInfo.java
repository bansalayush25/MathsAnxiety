package com.example.mathsanxiety.Models;

import android.os.Parcel;
import android.os.Parcelable;

public class ExerciseInfo implements Parcelable{
    int id;
    String videoId;
    String playlistId;
    String title;
    String description;
    String thumbnail;

    public ExerciseInfo(int id, String videoId, String playlistId, String title, String description, String thumbnail) {
        this.id = id;
        this.videoId = videoId;
        this.playlistId = playlistId;
        this.title = title;
        this.description = description;
        this.thumbnail = thumbnail;
    }

    public ExerciseInfo(Parcel parcel) {
        id = parcel.readInt();
        videoId = parcel.readString();
        playlistId = parcel.readString();
        title = parcel.readString();
        description = parcel.readString();
        thumbnail = parcel.readString();
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

    public static final Creator<ExerciseInfo>CREATOR = new Creator<ExerciseInfo>() {
        @Override
        public ExerciseInfo createFromParcel(Parcel parcel) {
            return new ExerciseInfo(parcel);
        }

        @Override
        public ExerciseInfo[] newArray(int size) {
            return new ExerciseInfo[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(id);
        parcel.writeString(videoId);
        parcel.writeString(playlistId);
        parcel.writeString(title);
        parcel.writeString(description);
        parcel.writeString(thumbnail);
    }
}
