package com.example.interchange.pojo;

/**
 * @author 秃头小刘
 * @date 2021-09-23 4:16 PM
 */


public class Post {
    String postId;
    String title;
    String content;
    String creator;
    public Post(){

    }

    public Post(String postId, String title, String content, String creator) {
        this.postId = postId;
        this.title = title;
        this.content = content;
        this.creator = creator;
    }

    public String getId() {
        return postId;
    }

    public void setId(String postId) {
        this.postId = postId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    @Override
    public String toString() {
        return "Post{" +
                "postId='" + postId + '\'' +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", creator='" + creator + '\'' +
                '}';
    }
}
