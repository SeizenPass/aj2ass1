package com.company;

public class Article {
    private String id;
    private String sourceId;
    private String sourceName;
    private String title;
    private String content;
    private String publishedAt;

    public Article() {}

    public Article(String id, String sourceId, String sourceName, String title, String content, String publishedAt) {
        this.id = id;
        this.sourceId = sourceId;
        this.sourceName = sourceName;
        this.title = title;
        this.content = content;
        this.publishedAt = publishedAt;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSourceId() {
        return sourceId;
    }

    public void setSourceId(String sourceId) {
        this.sourceId = sourceId;
    }

    public String getSourceName() {
        return sourceName;
    }

    public void setSourceName(String sourceName) {
        this.sourceName = sourceName;
    }

    public String getTitle() {
        return title;
    }

    @Override
    public String toString() {
        return id + "," + sourceId + "," + sourceName + ","+ title + "," + content + "," + publishedAt;
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

    public String getPublishedAt() {
        return publishedAt;
    }

    public void setPublishedAt(String publishedAt) {
        this.publishedAt = publishedAt;
    }


}
