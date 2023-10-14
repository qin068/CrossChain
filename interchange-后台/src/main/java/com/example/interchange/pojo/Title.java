package com.example.interchange.pojo;

/**
 * @author 秃头小刘
 * @date 2022-05-13 8:33 PM
 */


public class Title {
    String title;
    String content;
    String height;
    String txhash;
    String timestamp;

    public Title() {
    }

    public Title(String title, String content, String height, String txhash, String timestamp) {
        this.title = title;
        this.content = content;
        this.height = height;
        this.txhash = txhash;
        this.timestamp = timestamp;
    }

    @Override
    public String toString() {
        return "Title{" +
                "title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", height='" + height + '\'' +
                ", txhash='" + txhash + '\'' +
                ", timestamp='" + timestamp + '\'' +
                '}';
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

    public String getHeight() {
        return height;
    }

    public void setHeight(String height) {
        this.height = height;
    }

    public String getTxhash() {
        return txhash;
    }

    public void setTxhash(String txhash) {
        this.txhash = txhash;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }
}
