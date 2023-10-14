package com.example.interchange.pojo;

public class Encrypt {

    String id;
    String title;
    String body;
    String creator;

    public Encrypt(){

    }

    public Encrypt(String id, String title, String body, String creator) {
        this.id = id;
        this.title = title;
        this.body = body;
        this.creator = creator;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    @Override
    public String toString() {
        return "Message{" +
                "id='" + id + '\'' +
                ", title='" + title + '\'' +
                ", body='" + body + '\'' +
                ", creator='" + creator + '\'' +
                '}';
    }

}
