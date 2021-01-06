package com.company;

public class Publisher {
    private String name;
    private String id;
    private String publishedFrom;
    private String publishedTo;
    private long avgContentLength;

    public Publisher(String name, String id, String publishedFrom, String publishedTo, long avgContentLength) {
        this.name = name;
        this.id = id;
        this.publishedFrom = publishedFrom;
        this.publishedTo = publishedTo;
        this.avgContentLength = avgContentLength;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPublishedFrom() {
        return publishedFrom;
    }

    public void setPublishedFrom(String publishedFrom) {
        this.publishedFrom = publishedFrom;
    }

    public String getPublishedTo() {
        return publishedTo;
    }

    public void setPublishedTo(String publishedTo) {
        this.publishedTo = publishedTo;
    }

    public long getAvgContentLength() {
        return avgContentLength;
    }

    public void setAvgContentLength(long avgContentLength) {
        this.avgContentLength = avgContentLength;
    }

    @Override
    public String toString() {
        return "ID: " + id + ","+ " Name: " + name + "," + " Published From: " + publishedFrom
                + "," + " Published To: " + publishedTo + "," + " Average Content Length: " + avgContentLength;
    }
}
