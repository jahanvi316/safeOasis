package com.example.safeoasis;

public class FeedHelper {

    String Post;

    public FeedHelper() {

    }
    public FeedHelper(String Post) {
        this.Post = Post;
    }

    public String getPost() {
        return Post;
    }

    public void setPost(String Post) {
        this.Post = Post;
    }

    @Override
    public String toString() {
        return this.Post;
    }
}
