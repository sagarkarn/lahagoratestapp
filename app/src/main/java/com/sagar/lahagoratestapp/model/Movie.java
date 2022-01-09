package com.sagar.lahagoratestapp.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Movie implements Serializable {
    private int num;
    private String name;
    private String streamType;
    private long streamId;
    private String streamIcon;
    private String rating;
    @SerializedName("rating_5based")
    private String rating5based;
    private String added;
    private String categoryId;
    private String containerExtension;
    private String customSid;
    private String directSource;

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStreamType() {
        return streamType;
    }

    public void setStreamType(String streamType) {
        this.streamType = streamType;
    }

    public long getStreamId() {
        return streamId;
    }

    public void setStreamId(long streamId) {
        this.streamId = streamId;
    }

    public String getStreamIcon() {
        return streamIcon;
    }

    public void setStreamIcon(String streamIcon) {
        this.streamIcon = streamIcon;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public String getRating5based() {
        return rating5based;
    }

    public void setRating5based(String rating5based) {
        this.rating5based = rating5based;
    }

    public String getAdded() {
        return added;
    }

    public void setAdded(String added) {
        this.added = added;
    }

    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }

    public String getDirectSource() {
        return directSource;
    }

    public void setDirectSource(String directSource) {
        this.directSource = directSource;
    }

    public String getCustomSid() {
        return customSid;
    }

    public void setCustomSid(String customSid) {
        this.customSid = customSid;
    }

    public String getContainerExtension() {
        return containerExtension;
    }

    public void setContainerExtension(String containerExtension) {
        this.containerExtension = containerExtension;
    }

    @Override
    public String toString() {
        return "Movie{" +
                "num=" + num +
                ", name='" + name + '\'' +
                ", streamType='" + streamType + '\'' +
                ", streamId=" + streamId +
                ", streamIcon='" + streamIcon + '\'' +
                ", rating='" + rating + '\'' +
                ", rating5based='" + rating5based + '\'' +
                ", added='" + added + '\'' +
                ", categoryId='" + categoryId + '\'' +
                ", containerExtension='" + containerExtension + '\'' +
                ", customSid='" + customSid + '\'' +
                ", directSource='" + directSource + '\'' +
                '}';
    }
}
