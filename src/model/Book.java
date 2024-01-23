package model;

import java.io.Serializable;

public class Book implements Serializable {
    String id;
    String title;

    Integer page;
    String summary;
    Author author;
    Long publicationTimestamp;
    String date;

    public Book(String id, String title, Integer page, String summary, Author author, Long publicationTimestamp) {
        this.id = id;
        this.title = title;
        this.page = page;
        this.summary = summary;
        this.author = author;
        this.publicationTimestamp = publicationTimestamp;
    }

    public Book() {

    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Book compareTo(Book book) {
        if (publicationTimestamp > book.getPublicationTimestamp()) {
            return this;
        }
        return book;
    }

    public Long getPublicationTimestamp() {
        return publicationTimestamp;
    }

    public void setPublicationTimestamp(Long publicationTimestamp) {
        this.publicationTimestamp = publicationTimestamp;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
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

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    @Override
    public String toString() {
        return "Book{" +
                "id='" + id + '\'' +
                ", title='" + title + '\'' +
                ", page=" + page +
                ", summary='" + summary + '\'' +
                ", author=" + author +
                ", publicationTimestamp=" + publicationTimestamp +
                ", date=" + date +
                '}';
    }
}
