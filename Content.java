package com.example.congraduation;

import android.media.Image;
import android.widget.ImageView;

public class Content {

    private static String title;
    private static String content;
    private static String period;
    private static String feeling;
    private ImageView imageView;

    public Content() {
        super();
    }

    public Content(String title, String content, String period, String feeling) {
        this.title = title;
        this.content = content;
        this.period = period;
        this.feeling = feeling;
    }

    public Content(String title) {
        this.title = title;
    }

    public static String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public static String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public static String getPeriod() {
        return period;
    }

    public void setPeriod(String period) {
        this.period = period;
    }

    public static String getFeeling() {
        return feeling;
    }

    public void setFeeling(String feeling) {
        this.feeling = feeling;
    }

    public ImageView getImageView() {
        return imageView;
    }

    public void setImageView(ImageView imageView) {
        this.imageView = imageView;
    }
}
