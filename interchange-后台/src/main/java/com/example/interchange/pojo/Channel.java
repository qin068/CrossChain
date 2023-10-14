package com.example.interchange.pojo;

/**
 * @author 秃头小刘
 * @date 2021-10-14 10:27 AM
 */


public class Channel {
    String channelId;
    String from;
    String to;
    String port;

    public Channel(String channelId, String from, String to, String port) {
        this.channelId = channelId;
        this.from = from;
        this.to = to;
        this.port = port;
    }

    public String getChannelId() {
        return channelId;
    }

    public void setChannelId(String channelId) {
        this.channelId = channelId;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getPort() {
        return port;
    }

    public void setPort(String port) {
        this.port = port;
    }

    @Override
    public String toString() {
        return "Channel{" +
                "channelId='" + channelId + '\'' +
                ", from='" + from + '\'' +
                ", to='" + to + '\'' +
                ", port='" + port + '\'' +
                '}';
    }
}
