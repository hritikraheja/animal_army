package com.example.animalarmy.modelclasses;

import java.io.Serializable;

public class Video implements Serializable {
    int videoLocation;
    int videoThumbnailLocation;
    int iconLocation;
    String title;
    String organisationName;
    String description;

    public Video(int videoLocation, int videoThumbnailLocation, int iconLocation, String title, String organisationName, String description) {
        this.videoLocation = videoLocation;
        this.videoThumbnailLocation = videoThumbnailLocation;
        this.iconLocation = iconLocation;
        this.title = title;
        this.organisationName = organisationName;
        this.description = description;
    }

    public int getVideoLocation() {
        return videoLocation;
    }

    public int getVideoThumbnailLocation() {
        return videoThumbnailLocation;
    }

    public int getIconLocation() {
        return iconLocation;
    }

    public String getTitle() {
        return title;
    }

    public String getOrganisationName(){
        return organisationName;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public String toString() {
        return "Video{" +
                "videoLocation=" + videoLocation +
                ", videoThumbnailLocation=" + videoThumbnailLocation +
                ", iconLocation=" + iconLocation +
                ", title='" + title + '\'' +
                ", organisationName='" + organisationName + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
