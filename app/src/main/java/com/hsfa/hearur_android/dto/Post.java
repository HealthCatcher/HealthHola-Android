package com.hsfa.hearur_android.dto;

public class Post {
    private String title;
    private String content;
    private int views;
    private int likes;
    private String imageName;

    public Post(String title, String content, int views, int likes, String imageName) {
        this.title = title;
        this.content = content;
        this.views = views;
        this.likes = likes;
        this.imageName = imageName;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    public String getShortContent(int maxLength) {
        if (content.length() > maxLength) {
            return content.substring(0, maxLength) + "...";
        }
        return content;
    }

    public int getViews() {
        return views;
    }

    public int getLikes() {
        return likes;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setViews(int views) {
        this.views = views;
    }

    public void setLikes(int likes) {
        this.likes = likes;
    }

    public String getImageName() {
        return imageName;
    }

    public void setImageName(String imageName) {
        this.imageName = imageName;
    }
}
