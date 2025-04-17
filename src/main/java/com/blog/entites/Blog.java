package com.blog.entites;



public class Blog {

    private Long id;
    private String title;
    private String content;

    private static Long idCounter = 0L;

    public Blog() {
        this.id = ++idCounter;
    }

    public Blog(String title, String content) {
        this.id = ++idCounter;
        this.title = title;
        this.content = content;
    }

    // Getters
    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    // Setters
    public void setTitle(String title) {
        this.title = title;
    }

    public void setContent(String content) {
        this.content = content;
    }

    // Optional toString
    @Override
    public String toString() {
        return "Blog{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                '}';
    }
}
