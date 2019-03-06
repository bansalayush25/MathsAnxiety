package com.example.mathsanxiety.Models;

import android.os.Parcel;
import android.os.Parcelable;

public class PlaylistInfo implements Parcelable{
    int id;
    String playlistId;
    String title;
    String description;
    String thumbnail;

    public PlaylistInfo(int id, String playlistId, String title, String description, String thumbnail) {
        this.id = id;
        this.playlistId = playlistId;
        this.title = title;
        this.description = description;
        this.thumbnail = thumbnail;
    }

    public PlaylistInfo(Parcel parcel) {
        id = parcel.readInt();
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

    public String getPlaylistId() {
        return playlistId;
    }

    public void setPlaylistId(String playlistId) {
        this.playlistId = playlistId;
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

    public static final Creator<PlaylistInfo>CREATOR = new Creator<PlaylistInfo>() {
        @Override
        public PlaylistInfo createFromParcel(Parcel parcel) {
            return new PlaylistInfo(parcel);
        }

        @Override
        public PlaylistInfo[] newArray(int size) {
            return new PlaylistInfo[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(id);
        parcel.writeString(playlistId);
        parcel.writeString(title);
        parcel.writeString(description);
        parcel.writeString(thumbnail);
    }
}
